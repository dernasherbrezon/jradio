package ru.r2cloud.jradio.lrpt;

import java.io.Closeable;
import java.io.IOException;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.BufferedByteInput;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.Tag;
import ru.r2cloud.jradio.blocks.CorrelateAccessCodeTag;
import ru.r2cloud.jradio.blocks.TaggedStreamToPdu;
import ru.r2cloud.jradio.fec.ViterbiSoft;
import ru.r2cloud.jradio.fec.ccsds.Randomize;
import ru.r2cloud.jradio.fec.ccsds.ReedSolomon;

public class LRPT implements Iterable<VCDU>, Iterator<VCDU>, Closeable {

	private static final Logger LOG = LoggerFactory.getLogger(LRPT.class);

	// phase might be incorrectly locked, so there are 4 different sync markers possible:
	// 1ACFFC1D itself + rotated clockwise 3 times
	// this is lookup table to quickly convert:
	// 00 -> 01 (1 phase difference)
	// 00 -> 10 (3 phase difference)
	// 00 -> 11 don't need lookup table as it simply inverting bits
	private static final int[] rotate_iq_tab = new int[256];

	public static final long[] SYNCHRONIZATION_MARKERS = new long[4];

	private final Context context;
	private final TaggedStreamToPdu input;
	private final BufferedByteInput buffer;
	private byte[] current;
	//previous is used for restoring partial packets
	private VCDU previous = null;

	static {
		for (int i = 0; i <= 255; i++) {
			rotate_iq_tab[i] = ((((i & 0x55) ^ 0x55) << 1) | ((i & 0xAA) >> 1));
		}
		// synchronization marker 1ACFFC1D encoded by viterbi
		long syncMarkerEncoded = 0x035d49c24ff2686bL;
		for (int i = 0; i < 4; i++) {
			SYNCHRONIZATION_MARKERS[i] = rotate_iq_qw(syncMarkerEncoded, i);
		}
	}

	public LRPT(Context context, TaggedStreamToPdu input, BufferedByteInput buffer) {
		this.context = context;
		this.input = input;
		this.buffer = buffer;
	}

	@Override
	public VCDU next() {
		VCDU result = new VCDU();
		result.readExternal(previous, current);
		previous = result;
		return result;
	}

	@Override
	public boolean hasNext() {
		try {
			byte[] rawBytes = input.readBytes();
			if (rawBytes != null && rawBytes.length != 0) {
				Tag currentTag = context.getCurrent();
				int index = getIndex((Long) currentTag.get(CorrelateAccessCodeTag.ACCESS_CODE));
				if (index != 0) {
					// phase was incorrectly locked,
					// rotate data the same number of turns as synchronization marker
					for (int i = 0; i < rawBytes.length; i++) {
						rawBytes[i] = (byte) rotate_iq(rawBytes[i], index);
					}
				}
				byte[] viterbi = ViterbiSoft.decode(rawBytes, (byte) 0x4f, (byte) 0x6d, false);
				byte[] deShuffled = Randomize.shuffle(viterbi);
				try {
					current = ReedSolomon.decode(deShuffled, 4);
					// viterbi needs last 2 bytes for tail.
					// need to reset source stream back 2 bytes
					// since next packet might start exactly after previous
					buffer.reset(8 * 2);
				} catch (Exception e) {
					LOG.info("unable to decode reed solomon: " + e.getMessage());
					return hasNext();
				}
				return true;
			} else {
				return false;
			}
		} catch (IOException e) {
			return false;
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

	private static long rotate_iq_qw(long data, int shift) {
		int i;
		long result = 0;
		int bdata;

		for (i = 0; i < 8; i++) {
			bdata = (int) ((data >> (56 - 8 * i)) & 0xff);
			result <<= 8;
			result |= rotate_iq(bdata, shift);
		}

		return (result);
	}

	private static int rotate_iq(int data, int shift) {
		int result = data;
		if ((shift == 1) | (shift == 3))
			result = rotate_iq_tab[result];

		if ((shift == 2) | (shift == 3))
			result = result ^ 0xFF;

		return result;
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
