package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.crc.Crc16Ccitt;

public class HdlcTransmitter implements ByteInput {

	private final static int CRC16_LEN_BYTES = 2;
	private final static int BITS_IN_BYTES = 8;
	private final static int BIT_STUFFING_PESSIMISTIC_EXCESS_MULTIPLIER = 2;
	private final static int FRAMING_BITS_LEN = 8;
	private final MessageInput input;
	private final Context ctx;

	private byte[] bitsToSend;
	private int bitsToSendLength;
	private int currentBitToSend;
	private int successiveOnes;

	public HdlcTransmitter(MessageInput input) {
		this.input = input;
		ctx = new Context(input.getContext());
		ctx.setSoftBits(false);
	}

	@Override
	public byte readByte() throws IOException {
		if (bitsToSend == null || currentBitToSend >= bitsToSendLength) {
			currentBitToSend = 0;
			bitsToSendLength = 0;

			byte[] messageToSend = input.readBytes();
			int crc16 = Crc16Ccitt.calculateReverse(messageToSend);
			int requiredBitsToSendLength = (messageToSend.length + CRC16_LEN_BYTES) * BITS_IN_BYTES * BIT_STUFFING_PESSIMISTIC_EXCESS_MULTIPLIER + 2 * FRAMING_BITS_LEN;
			// init new buffer only if previous was smaller
			if (bitsToSend == null || requiredBitsToSendLength > bitsToSend.length) {
				bitsToSend = new byte[requiredBitsToSendLength];
			}

			appendNonStuffed(0x7E);
			for (int i = 0; i < messageToSend.length; i++) {
				appendStuffed(messageToSend[i] & 0xFF);
			}
			appendStuffed(crc16 & 0xFF);
			appendStuffed((crc16 >> 8) & 0xFF);
			appendNonStuffed(0x7E);
		}
		byte result = bitsToSend[currentBitToSend];
		currentBitToSend++;
		return result;
	}

	private void appendStuffed(int curByte) {
		for (int i = 0; i < BITS_IN_BYTES; i++) {
			byte curBit = (byte) ((curByte >> i) & 0x1);
			if (curBit == 1) {
				successiveOnes++;
			} else {
				successiveOnes = 0;
			}
			bitsToSend[bitsToSendLength] = curBit;
			bitsToSendLength++;
			if (successiveOnes == 5) {
				bitsToSend[bitsToSendLength] = 0;
				bitsToSendLength++;
				successiveOnes = 0;
			}
		}
	}

	private void appendNonStuffed(int curByte) {
		for (int i = 0; i < BITS_IN_BYTES; i++) {
			bitsToSend[bitsToSendLength] = (byte) ((curByte >> i) & 0x1);
			bitsToSendLength++;
		}
	}

	@Override
	public Context getContext() {
		return ctx;
	}

	@Override
	public void close() throws IOException {
		input.close();
	}

}
