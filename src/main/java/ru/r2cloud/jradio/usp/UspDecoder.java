package ru.r2cloud.jradio.usp;

import java.io.EOFException;
import java.io.IOException;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.blocks.CorrelateSyncword;
import ru.r2cloud.jradio.ccsds.Scrambler;
import ru.r2cloud.jradio.fec.PlsDecoder;
import ru.r2cloud.jradio.fec.ViterbiSoft;
import ru.r2cloud.jradio.fec.ccsds.ReedSolomon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class UspDecoder implements MessageInput {

	private static final Logger LOG = LoggerFactory.getLogger(UspDecoder.class);

	private static final int PLS_CODE_LENGTH = 64;
	private static final int REED_SOLOMON_LENGTH = 255;
	private static final int RECOMMENDED_THRESHOLD = 13;
	private static final int SHORT_FRAME = 48;
	private static final int LONG_FRAME = 223;
	private static final int REEDSOLOMON_PARITY_LENGTH = 32;
	private static final String SYNC = Long.toBinaryString(0x5072F64B2D90B1F5L);

	private MessageInput input;
	private PlsDecoder pls;
	private ViterbiSoft viterbiShort;
	private ViterbiSoft viterbiLong;

	private byte[] paddedFrame;
	private byte[] shortFrame;

	public UspDecoder(ByteInput input) {
		this.input = new CorrelateSyncword(input, RECOMMENDED_THRESHOLD, Collections.singleton(SYNC), PLS_CODE_LENGTH + (REED_SOLOMON_LENGTH + 1) * 2 * 8);
		this.pls = new PlsDecoder();
		this.viterbiShort = new ViterbiSoft((byte) 0x4f, (byte) 0x6d, true, (SHORT_FRAME + REEDSOLOMON_PARITY_LENGTH + 1) * 2 * 8);
		this.viterbiLong = new ViterbiSoft((byte) 0x4f, (byte) 0x6d, true, (LONG_FRAME + REEDSOLOMON_PARITY_LENGTH + 1) * 2 * 8);
		this.paddedFrame = new byte[LONG_FRAME + REEDSOLOMON_PARITY_LENGTH];
		this.shortFrame = new byte[SHORT_FRAME];
	}

	@Override
	public byte[] readBytes() throws IOException {
		while (!Thread.currentThread().isInterrupted()) {
			byte[] raw = input.readBytes();
			try {
				return decode(raw);
			} catch (UncorrectableException e) {
				if (LOG.isDebugEnabled()) {
					LOG.debug("unable to decode: {}", e.getMessage());
				}
			}
		}
		throw new EOFException();
	}

	public byte[] decode(byte[] raw) throws UncorrectableException {
		int code = pls.decode(raw, 0);
		int length;
		if (code == 0) {
			length = LONG_FRAME;
		} else {
			length = SHORT_FRAME;
		}
		byte[] dataFrame = new byte[(length + 32 + 1) * 2 * 8];
		System.arraycopy(raw, PLS_CODE_LENGTH, dataFrame, 0, dataFrame.length);
		byte[] decoded;
		// a bit of checks to pre-init Viterbi on startup and speed up things a bit
		if (length == LONG_FRAME) {
			decoded = viterbiLong.decode(dataFrame);
		} else {
			decoded = viterbiShort.decode(dataFrame);
		}
		Scrambler.shuffle(decoded);
		byte[] result;
		if (code == 0) {
			result = ReedSolomon.CCSDS.decodeDualBasis(decoded);
		} else {
			System.arraycopy(decoded, 0, paddedFrame, paddedFrame.length - decoded.length, decoded.length);
			byte[] padded = ReedSolomon.CCSDS.decodeDualBasis(paddedFrame);
			System.arraycopy(padded, padded.length - shortFrame.length, shortFrame, 0, shortFrame.length);
			result = shortFrame;
		}
		// extract ax25 frame
		if (result[0] == (byte) 0x08 && result[1] == (byte) 0xFF) {
			int ax25Length = ((result[3] & 0xFF) << 8) + (result[2] & 0xFF);
			if (ax25Length <= result.length - 4) {
				byte[] ax25Frame = new byte[ax25Length];
				System.arraycopy(result, 4, ax25Frame, 0, ax25Frame.length);
				result = ax25Frame;
			}
		}
		return result;
	}

	@Override
	public void close() throws IOException {
		input.close();
	}

	@Override
	public Context getContext() {
		return input.getContext();
	}

}
