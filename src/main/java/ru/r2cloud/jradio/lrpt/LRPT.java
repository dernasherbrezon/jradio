package ru.r2cloud.jradio.lrpt;

import java.io.Closeable;
import java.io.IOException;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricRegistry;

import ru.r2cloud.jradio.BufferedByteInput;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.Tag;
import ru.r2cloud.jradio.blocks.CorrelateAccessCodeTag;
import ru.r2cloud.jradio.blocks.TaggedStreamToPdu;
import ru.r2cloud.jradio.fec.ViterbiSoft;
import ru.r2cloud.jradio.fec.ccsds.Randomize;
import ru.r2cloud.jradio.fec.ccsds.ReedSolomon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.Metrics;

public class LRPT implements Iterable<VCDU>, Iterator<VCDU>, Closeable {

	private static final Logger LOG = LoggerFactory.getLogger(LRPT.class);
	private final MetricRegistry registry = Metrics.getRegistry();
	// phase might be incorrectly locked, so there are 4 different sync markers possible:
	// 1ACFFC1D itself + rotated clockwise 3 times
	// this is lookup table to quickly convert:
	// 00 -> 01 (1 phase difference)
	// 00 -> 10 (3 phase difference)
	// 00 -> 11 don't need lookup table as it simply inverting bits
	// @see table 1 from https://ntrs.nasa.gov/archive/nasa/casi.ntrs.nasa.gov/19890016010.pdf
	// normal sense ambiguity could be resolved using single table
	private static final int[] rotate_iq_tab = new int[256];
	// reverse sense ambiguity require 4 different tables
	// QT IT
	private static final int[] phase4 = new int[256];
	// IT -QT
	private static final int[] phase5 = new int[256];
	// -QT -IT
	private static final int[] phase6 = new int[256];
	// -IT QT
	private static final int[] phase7 = new int[256];

	public static final long[] SYNCHRONIZATION_MARKERS = new long[8];

	private final Context context;
	private final TaggedStreamToPdu input;
	private final BufferedByteInput buffer;
	private final Counter count;

	private VCDU currentVcdu;
	// previous is used for restoring partial packets
	private VCDU previous = null;

	static {
		for (int i = 0; i < 256; i++) {
			rotate_iq_tab[i] = ((((i & 0x55) ^ 0x55) << 1) | ((i & 0xAA) >> 1));
			int curPhase4 = 0;
			int curPhase5 = 0;
			int curPhase6 = 0;
			int curPhase7 = 0;
			for (int j = 3; j >= 0; j--) {
				int lastTwoBits = i >> (2 * j) & 0b11;
				switch (lastTwoBits) {
				case 0b11:
					curPhase4 = (curPhase4 << 2) | 0b11;
					curPhase5 = (curPhase5 << 2) | 0b10;
					curPhase6 = (curPhase6 << 2) | 0b00;
					curPhase7 = (curPhase7 << 2) | 0b01;
					break;
				case 0b10:
					curPhase4 = (curPhase4 << 2) | 0b01;
					curPhase5 = (curPhase5 << 2) | 0b11;
					curPhase6 = (curPhase6 << 2) | 0b10;
					curPhase7 = (curPhase7 << 2) | 0b00;
					break;
				case 0b01:
					curPhase4 = (curPhase4 << 2) | 0b10;
					curPhase5 = (curPhase5 << 2) | 0b00;
					curPhase6 = (curPhase6 << 2) | 0b01;
					curPhase7 = (curPhase7 << 2) | 0b11;
					break;
				case 0b00:
					curPhase4 = (curPhase4 << 2) | 0b00;
					curPhase5 = (curPhase5 << 2) | 0b01;
					curPhase6 = (curPhase6 << 2) | 0b11;
					curPhase7 = (curPhase7 << 2) | 0b10;
					break;
				default:
					throw new IllegalArgumentException("unsupported last 2 bits: " + lastTwoBits);
				}
			}
			phase4[i] = curPhase4;
			phase5[i] = curPhase5;
			phase6[i] = curPhase6;
			phase7[i] = curPhase7;
		}
		// synchronization marker 1ACFFC1D encoded by viterbi
		long syncMarkerEncoded = 0x035d49c24ff2686bL;
		for (int i = 0; i < SYNCHRONIZATION_MARKERS.length; i++) {
			SYNCHRONIZATION_MARKERS[i] = rotateClockWise(syncMarkerEncoded, i);
		}
	}

	public LRPT(Context context, TaggedStreamToPdu input, BufferedByteInput buffer) {
		this.context = context;
		this.input = input;
		this.buffer = buffer;
		if (registry != null) {
			count = registry.counter(LRPT.class.getName());
		} else {
			count = null;
		}
	}

	@Override
	public VCDU next() {
		return currentVcdu;
	}

	@Override
	public boolean hasNext() {
		while (true) {
			try {
				byte[] rawBytes = input.readBytes();
				if (rawBytes != null && rawBytes.length != 0) {
					Tag currentTag = context.getCurrent();
					int index = getIndex((Long) currentTag.get(CorrelateAccessCodeTag.ACCESS_CODE));
					// phase was incorrectly locked,
					// rotate data the same number of turns as synchronization marker
					if (index != 0) {
						rotateIqCounterClockWise(rawBytes, index);
					}
					byte[] viterbi = ViterbiSoft.decode(rawBytes, (byte) 0x4f, (byte) 0x6d, false);
					byte[] deShuffled = Randomize.shuffle(viterbi);
					try {
						byte[] current = ReedSolomon.decode(deShuffled, 4);
						currentVcdu = new VCDU();
						currentVcdu.readExternal(previous, current);
						// reed solomon might pass for array [0,0,0,0,0,0...0]
						// ensure version is not 0 (according to spec it should be 01)
						if (currentVcdu.getVersion() == 0) {
							continue;
						}
						if (previous == null) {
							LOG.info("detected meteor-m image. frame: " + currentVcdu.getCounter());
						}
						if (count != null) {
							count.inc();
						}
						previous = currentVcdu;
						// viterbi needs last 2 bytes for tail.
						// need to reset source stream back 2 bytes
						// since next packet might start exactly after previous
						buffer.reset(8 * 2);
						return true;
					} catch (UncorrectableException e) {
						if (LOG.isDebugEnabled()) {
							LOG.debug("unable to decode reed solomon: " + e.getMessage());
						}
						continue;
					}
				} else {
					return false;
				}
			} catch (IOException e) {
				return false;
			}
		}
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator<VCDU> iterator() {
		return this;
	}

	@Override
	public void close() throws IOException {
		input.close();
	}

	private static void rotateIqCounterClockWise(byte[] rawBytes, int index) {
		int hardDecision = 0;
		for (int i = 0, bits = 7; i < rawBytes.length; i++, bits--) {
			if (rawBytes[i] > 0) {
				hardDecision = (hardDecision << 1) | 1;
			} else {
				hardDecision = (hardDecision << 1) | 0;
			}
			// full byte was assembled
			if (bits == 0) {
				int rotated = rotateIqCounterClockWise(hardDecision, index);
				// unroll back to soft decision
				// look for the last 8 bytes and invert them
				for (int j = 7; j >= 0; j--) {
					// invert only unmatched bits
					if (((rotated >> j) & 0x1) == 1) {
						if (rawBytes[i - j] < 0) {
							rawBytes[i - j] ^= 0xFF;
						}
					} else {
						if (rawBytes[i - j] > 0) {
							rawBytes[i - j] ^= 0xFF;
						}
					}
				}
				hardDecision = 0;
				bits = 8;
			}
		}
	}
	
	private static long rotateClockWise(long data, int shift) {
		int i;
		long result = 0;
		int bdata;

		for (i = 0; i < 8; i++) {
			bdata = (int) ((data >> (56 - 8 * i)) & 0xff);
			result <<= 8;
			int val = rotateIqCounterClockWise(bdata, shift);
			// invert back since it was rotated counter clock wise
			if (shift == 1 || shift == 3) {
				val = val ^ 0xFF;
			}
			result |= val;
		}

		return (result);
	}

	private static int rotateIqCounterClockWise(int data, int shift) {
		int result = data;
		switch (shift) {
		case 0:
			return result;
		case 1:
			return rotate_iq_tab[result & 0xFF] ^ 0xFF;
		case 2:
			return result ^ 0xFF;
		case 3:
			return rotate_iq_tab[result & 0xFF];
		case 4:
			return phase4[result & 0xFF];
		case 5:
			return phase5[result & 0xFF];
		case 6:
			return phase6[result & 0xFF];
		case 7:
			return phase7[result & 0xFF];
		default:
			throw new IllegalArgumentException("unsupported shift: " + shift);
		}
	}

	private static int getIndex(long synchronizationMarker) {
		for (int i = 0; i < SYNCHRONIZATION_MARKERS.length; i++) {
			if (synchronizationMarker == SYNCHRONIZATION_MARKERS[i]) {
				return i;
			}
		}
		throw new IllegalArgumentException("unsupported marker: " + synchronizationMarker);
	}
}
