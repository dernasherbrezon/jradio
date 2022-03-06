package ru.r2cloud.jradio.blocks;

import ru.r2cloud.jradio.crc.Crc16Ccitt;

public class HdlcTransmitter {

	private static final int CRC16_LEN_BYTES = 2;
	private static final int BITS_IN_BYTE = 8;
	private static final int BIT_STUFFING_PESSIMISTIC_EXCESS_MULTIPLIER = 2;
	private static final int FRAMING_BITS_LEN = 8;

	private final int prepend;
	private final int append;
	private byte[] bitsToSend;
	private int bitsToSendLength;
	private int successiveOnes;

	public HdlcTransmitter() {
		this(0, 0);
	}

	public HdlcTransmitter(int prepend, int append) {
		this.prepend = prepend;
		this.append = append;
	}

	public byte[] encode(byte[] messageToSend) {
		bitsToSendLength = 0;
		int crc16 = Crc16Ccitt.calculateReverse(messageToSend);
		int requiredBitsToSendLength = (messageToSend.length + CRC16_LEN_BYTES) * BITS_IN_BYTE * BIT_STUFFING_PESSIMISTIC_EXCESS_MULTIPLIER + 2 * FRAMING_BITS_LEN;
		// init new buffer only if previous was smaller
		if (bitsToSend == null || requiredBitsToSendLength > bitsToSend.length) {
			bitsToSend = new byte[requiredBitsToSendLength];
		}

		for (int i = 0; i < prepend; i++) {
			appendNonStuffed(0x7E);
		}
		appendNonStuffed(0x7E);
		for (int i = 0; i < messageToSend.length; i++) {
			appendStuffed(messageToSend[i] & 0xFF);
		}
		appendStuffed(crc16 & 0xFF);
		appendStuffed((crc16 >> 8) & 0xFF);
		appendNonStuffed(0x7E);
		for (int i = 0; i < append; i++) {
			appendNonStuffed(0x7E);
		}

		byte[] result = new byte[bitsToSendLength];
		System.arraycopy(bitsToSend, 0, result, 0, result.length);
		return result;
	}

	private void appendStuffed(int curByte) {
		for (int i = 0; i < BITS_IN_BYTE; i++) {
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
		for (int i = 0; i < BITS_IN_BYTE; i++) {
			bitsToSend[bitsToSendLength] = (byte) ((curByte >> i) & 0x1);
			bitsToSendLength++;
		}
	}

}
