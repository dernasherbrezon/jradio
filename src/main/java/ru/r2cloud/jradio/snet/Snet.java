package ru.r2cloud.jradio.snet;

import java.io.IOException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.blocks.UnpackedToPacked;
import ru.r2cloud.jradio.fec.Bch15;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.Deinterleave;

public class Snet extends BeaconSource<SnetBeacon> {

	private static final Logger LOG = LoggerFactory.getLogger(Snet.class);

	private static final int HEADER_LENGTH_WITH_FEC_BITS = 210;
	private static final int HEADER_LENGTH_BYTES = 9;
	private static final int CHUNK_LENGTH_BITS = 15;
	private static final int NUMBER_OF_HEADER_CHUNKS = 14;
	private static final int CODEWORDS_PER_BLOCK = 16;

	public Snet(MessageInput input) {
		super(input);
	}

	@Override
	protected SnetBeacon parseBeacon(byte[] raw) {
		// 1. de-interleave header
		byte[] headerBits = Deinterleave.deinterleaveBitsUnpacked(raw, 0, CHUNK_LENGTH_BITS, NUMBER_OF_HEADER_CHUNKS);
		// 2. correct errors BCH(15,5,7)
		for (int i = 0; i < NUMBER_OF_HEADER_CHUNKS; i++) {
			try {
				Bch15.decode(headerBits, i * CHUNK_LENGTH_BITS, 7);
			} catch (UncorrectableException e) {
				if (LOG.isDebugEnabled()) {
					LOG.debug("unable to decode Bch15: {}", e.getMessage());
				}
				return null;
			}
		}
		// 3. extract data. last 5 bits of each chunk, LSB
		// round 70 bits to 9 bytes. last 2 bits are expected to be 0
		byte[] headerDataBits = new byte[HEADER_LENGTH_BYTES * 8];
		int dstIndex = 0;
		for (int i = 0; i < NUMBER_OF_HEADER_CHUNKS; i++) {
			for (int j = 0; j < 5; j++) {
				int srcIndex = (i + 1) * CHUNK_LENGTH_BITS - 1 - j;
				headerDataBits[dstIndex] = headerBits[srcIndex];
				dstIndex++;
			}
		}

		// 4. pack for parsing
		byte[] header = UnpackedToPacked.pack(headerDataBits);

		LTUFrameHeader ltuHeader;
		try {
			ltuHeader = new LTUFrameHeader(header);
		} catch (IOException e) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("unable to read", e);
			}
			return null;
		}

		// 5. prepare data bits for crc5 calculation. replace last 7 with hardcoded 1011011
		headerDataBits[headerDataBits.length - 7] = 1;
		headerDataBits[headerDataBits.length - 6] = 0;
		headerDataBits[headerDataBits.length - 5] = 1;
		headerDataBits[headerDataBits.length - 4] = 1;
		headerDataBits[headerDataBits.length - 3] = 0;
		headerDataBits[headerDataBits.length - 2] = 1;
		headerDataBits[headerDataBits.length - 1] = 1;

		// 6. check header CRC5
		int actualCrc = Crc5Snet.calculateCrc5(headerDataBits);
		if (actualCrc != ltuHeader.getCrc5()) {
			// force CRC5 bugs. replace 4th byte from the end with the contents of 3rd byte from the end
			for (int i = 0; i < 8; i++) {
				headerDataBits[(HEADER_LENGTH_BYTES - 1 - 4) * 8 + i] = headerDataBits[(HEADER_LENGTH_BYTES - 1 - 3) * 8 + i];
			}
			actualCrc = Crc5Snet.calculateCrc5(headerDataBits);
			if (actualCrc != ltuHeader.getCrc5()) {
				if (LOG.isDebugEnabled()) {
					LOG.debug("crc5 mismatch");
				}
				return null;
			}
		}

		if (ltuHeader.getPduLength() == 0) {
			SnetBeacon result = new SnetBeacon();
			result.setHeader(ltuHeader);
			result.setRawData(header);
			return result;
		}

		// 7. extract PDU
		byte[] pdu;
		try {
			pdu = extractPdu(ltuHeader, raw);
		} catch (UncorrectableException e1) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("unable to decode Bch15: {}", e1.getMessage());
			}
			return null;
		}

		// 8. calculate CRC13
		actualCrc = Crc13Snet.calculateCrc13(pdu);
		if (actualCrc != ltuHeader.getCrc13()) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("crc5 mismatch");
			}
			return null;
		}

		// 9. Parse data
		SnetBeacon result = new SnetBeacon();
		result.setHeader(ltuHeader);
		try {
			result.readExternal(UnpackedToPacked.pack(pdu));
		} catch (IOException e) {
			LOG.error("unable to parse beacon", e);
			return null;
		}
		return result;
	}

	private static byte[] extractPdu(LTUFrameHeader header, byte[] bits) throws UncorrectableException {
		int bchD;
		int dataBitsPerCodeword;
		byte[] pdu = null;
		switch (header.getAiTypeSrc()) {
		case 0:
			bchD = 0;
			dataBitsPerCodeword = 0;
			pdu = Arrays.copyOfRange(bits, HEADER_LENGTH_WITH_FEC_BITS, HEADER_LENGTH_WITH_FEC_BITS + header.getPduLength() * 8);
			break;
		case 1:
			bchD = 3;
			dataBitsPerCodeword = 11;
			break;
		case 2:
			bchD = 5;
			dataBitsPerCodeword = 7;
			break;
		case 3:
			bchD = 7;
			dataBitsPerCodeword = 5;
			break;
		default:
			throw new UncorrectableException("unsupported aiTypeSrc: " + header.getAiTypeSrc());
		}

		if (pdu == null && dataBitsPerCodeword != 0) {
			int dataBytesPerBlock = CODEWORDS_PER_BLOCK * dataBitsPerCodeword / 8;
			int numBlocks = (int) Math.ceil((float) header.getPduLength() / dataBytesPerBlock);
			// correct errors BCH
			pdu = new byte[numBlocks * dataBytesPerBlock * 8];
			int dstIndex = 0;
			for (int i = 0; i < numBlocks; i++) {
				byte[] curBlock = Deinterleave.deinterleaveBitsUnpacked(bits, HEADER_LENGTH_WITH_FEC_BITS + i * CODEWORDS_PER_BLOCK * CHUNK_LENGTH_BITS, CHUNK_LENGTH_BITS, CODEWORDS_PER_BLOCK);

				for (int j = 0; j < CODEWORDS_PER_BLOCK; j++) {
					Bch15.decode(curBlock, j * CHUNK_LENGTH_BITS, bchD);
				}

				for (int j = 0; j < CODEWORDS_PER_BLOCK; j++) {
					for (int k = 0; k < dataBitsPerCodeword; k++) {
						int srcIndex = j * CHUNK_LENGTH_BITS + (CHUNK_LENGTH_BITS - dataBitsPerCodeword) + k;
						pdu[dstIndex] = curBlock[srcIndex];
						dstIndex++;
					}
				}
			}

			pdu = Arrays.copyOfRange(pdu, 0, header.getPduLength() * 8);
		}

		// for some reason eclipse thinks PDU could be null here.
		if (pdu == null) {
			return null;
		}

		// convert LSB to MSB
		for (int i = 0; i < pdu.length; i += 8) {
			for (int j = 0; j < 4; j++) {
				byte temp = pdu[i + j];
				pdu[i + j] = pdu[i + 7 - j];
				pdu[i + 7 - j] = temp;
			}
		}

		return pdu;
	}

//	private static byte[] pack(byte[] raw) {
//		byte[] result = new byte[raw.length / 8];
//		for (int i = 0; i < raw.length; i++) {
//			result[i / 8] <<= 1;
//			result[i / 8] |= raw[i];
//		}
//		return result;
//	}

}
