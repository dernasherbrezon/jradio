package ru.r2cloud.jradio.gomx1;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.fec.Golay;
import ru.r2cloud.jradio.fec.ccsds.Randomize;
import ru.r2cloud.jradio.fec.ccsds.ReedSolomon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class AX100Decoder implements MessageInput {

	private static final Golay golay = new Golay();
	private static final Logger LOG = LoggerFactory.getLogger(AX100Decoder.class);

	private final MessageInput input;
	private final boolean forceViterbi;
	private final boolean forceScrambler;
	private final boolean forceReedSolomon;

	public AX100Decoder(MessageInput input, boolean forceViterbi, boolean forceScrambler, boolean forceReedSolomon) {
		this.input = input;
		this.forceViterbi = forceViterbi;
		this.forceScrambler = forceScrambler;
		this.forceReedSolomon = forceReedSolomon;
	}

	@Override
	public byte[] readBytes() throws IOException {
		while (true) {
			byte[] raw = input.readBytes();
			try {
				return decode(raw);
			} catch (UncorrectableException e) {
				if (LOG.isDebugEnabled()) {
					LOG.debug("unable to decode: {}", e.getMessage());
				}
			}
		}
	}

	public byte[] decode(byte[] raw) throws IOException, UncorrectableException {
		int lengthField = ((raw[0] & 0xFF) << 16) | ((raw[1] & 0xFF) << 8) | (raw[2] & 0xFF);
		lengthField = golay.decode(lengthField);
		int frameLength = lengthField & 0xFF;
		int viterbiFlag = lengthField & 0x100;
		int scramblerFlag = lengthField & 0x200;
		int rsFlag = lengthField & 0x400;
		if (LOG.isDebugEnabled()) {
			LOG.debug("golay decoded. frameLength: {} viterbiFlag: {}, scramblerFlag: {}, rsFlag: {}", frameLength, viterbiFlag, scramblerFlag, rsFlag);
		}
		if (frameLength + 3 > raw.length) {
			throw new IOException("not enough data: " + raw.length + " expected: " + frameLength);
		}
		int minFrameLength = 0;
		if (rsFlag > 0 || forceReedSolomon) {
			minFrameLength = 255 - 222;
		}
		if (viterbiFlag > 0 || forceViterbi) {
			minFrameLength = minFrameLength * 2 + ru.r2cloud.jradio.fec.Viterbi.TAIL;
		}
		// sometimes frameLength might be corrupted and less than min frame length.
		// fail fast here, rather than do computational heavy FEC
		if (frameLength < minFrameLength) {
			throw new UncorrectableException("frameLength is " + frameLength + " min expected: " + minFrameLength);
		}
		byte[] data = new byte[frameLength];
		System.arraycopy(raw, 3, data, 0, frameLength);
		if (viterbiFlag > 0 || forceViterbi) {
			data = ru.r2cloud.jradio.fec.Viterbi.decode(data, (byte) 0x6d, (byte) 0x4f, false);
		}
		if (scramblerFlag > 0 || forceScrambler) {
			Randomize.shuffle(data);
		}
		if (rsFlag > 0 || forceReedSolomon) {
			data = ReedSolomon.decode(data);
		}
		return data;
	}

	@Override
	public Context getContext() {
		return input.getContext();
	}

	@Override
	public void close() throws IOException {
		input.close();
	}
}
