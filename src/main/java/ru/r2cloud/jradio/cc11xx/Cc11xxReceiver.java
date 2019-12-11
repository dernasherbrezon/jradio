package ru.r2cloud.jradio.cc11xx;

import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.blocks.AdditiveScrambler;
import ru.r2cloud.jradio.crc.Crc16Cc11xx;

public class Cc11xxReceiver implements MessageInput {

	private static final Logger LOG = LoggerFactory.getLogger(Cc11xxReceiver.class);

	private final MessageInput source;
	private final AdditiveScrambler scrambler;
	private final boolean hasWhitening;
	private final boolean hasCrc;

	public Cc11xxReceiver(MessageInput source, boolean hasWhitening, boolean hasCrc) {
		this.source = source;
		scrambler = new AdditiveScrambler(0x21, 0x1ff, 8, 8);
		this.hasWhitening = hasWhitening;
		this.hasCrc = hasCrc;
	}

	@Override
	public byte[] readBytes() throws IOException {
		while (!Thread.currentThread().isInterrupted()) {
			byte[] raw = source.readBytes();
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
				continue;
			}
			// 1 - skip first byte which is frameLength
			byte[] result = Arrays.copyOfRange(raw, 1, endIndex);
			if (!hasCrc) {
				return result;
			}

			//crc should include everything from 0 byte + frame + 2 crc bytes
			if (Crc16Cc11xx.calculate(raw, 0, endIndex + 2) != 0) {
				if (LOG.isDebugEnabled()) {
					LOG.debug("crc mismatch");
				}
				continue;
			}
			return result;
		}
		throw new EOFException();
	}

	@Override
	public void close() throws IOException {
		source.close();
	}

	@Override
	public Context getContext() {
		return source.getContext();
	}

}
