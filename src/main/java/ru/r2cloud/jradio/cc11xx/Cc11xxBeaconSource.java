package ru.r2cloud.jradio.cc11xx;

import java.io.IOException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.blocks.AdditiveScrambler;
import ru.r2cloud.jradio.blocks.CorrelateSyncword;
import ru.r2cloud.jradio.blocks.SoftToHard;
import ru.r2cloud.jradio.blocks.UnpackedToPacked;
import ru.r2cloud.jradio.crc.Crc16Cc11xx;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Cc11xxBeaconSource<T extends Beacon> extends BeaconSource<T> {

	private static final Logger LOG = LoggerFactory.getLogger(Cc11xxBeaconSource.class);

	private final Class<T> clazz;
	private final AdditiveScrambler scrambler;
	private final boolean hasWhitening;
	private final boolean hasCrc;

	public Cc11xxBeaconSource(ByteInput source, Class<T> clazz) {
		this(source, clazz, "00110101001011100011010100101110", 120, true, true);
	}

	public Cc11xxBeaconSource(ByteInput source, Class<T> clazz, String syncword, int length, boolean hasWhitening, boolean hasCrc) {
		if (!source.getContext().getSoftBits()) {
			throw new IllegalArgumentException("expected soft bits");
		}
		this.input = new CorrelateSyncword(new SoftToHard(source), 6, syncword, length * 8);
		this.clazz = clazz;
		scrambler = new AdditiveScrambler(0x21, 0x1ff, 8, 8);
		this.hasWhitening = hasWhitening;
		this.hasCrc = hasCrc;
	}

	@Override
	protected T parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		byte[] parsed = decode(raw);
		if (parsed == null) {
			return null;
		}
		T result;
		try {
			result = clazz.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			LOG.error("unable to init beacon", e);
			return null;
		}
		result.readExternal(parsed);
		return result;
	}

	private byte[] decode(byte[] raw) {
		raw = UnpackedToPacked.pack(raw);
		if (hasWhitening) {
			scrambler.shuffle(raw);
		}
		int frameLength = raw[0] & 0xFF;
		int endIndex = frameLength + 1;
		int dataEndIndex = endIndex;
		if (hasCrc) {
			dataEndIndex += 2;
		}
		if (dataEndIndex > raw.length) {
			return null;
		}
		// 1 - skip first byte which is frameLength
		byte[] result = Arrays.copyOfRange(raw, 1, endIndex);
		if (!hasCrc) {
			return result;
		}

		// crc should include everything from 0 byte + frame + 2 crc bytes
		if (Crc16Cc11xx.calculate(raw, 0, endIndex + 2) != 0) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("crc mismatch");
			}
			return null;
		}
		return result;
	}

}
