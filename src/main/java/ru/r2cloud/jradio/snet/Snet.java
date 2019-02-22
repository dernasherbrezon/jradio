package ru.r2cloud.jradio.snet;

import java.io.IOException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.fec.Bch15;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Snet extends BeaconSource<SnetBeacon> {

	private static final Logger LOG = LoggerFactory.getLogger(Snet.class);

	private static final int HEADER_LENGTH_WITH_FEC_BITS = 210;
	private static final int HEADER_LENGTH_BYTES = 9;
	private static final int CHUNK_LENGTH_BITS = 15;
	private static final int NUMBER_OF_HEADER_CHUNKS = 14;

	public Snet(MessageInput input) {
		super(input);
	}

	@Override
	protected SnetBeacon parseBeacon(byte[] raw) {
		// 1. de-interleave header
		byte[] headerBits = new byte[HEADER_LENGTH_WITH_FEC_BITS];
		for (int i = 0; i < headerBits.length; i++) {
			headerBits[i] = -1;
		}
		for (int i = 0; i < CHUNK_LENGTH_BITS; i++) {
			for (int j = 0; j < NUMBER_OF_HEADER_CHUNKS; j++) {
				headerBits[j * CHUNK_LENGTH_BITS + i] = raw[i * NUMBER_OF_HEADER_CHUNKS + j];
			}
		}
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
				headerDataBits[dstIndex] |= headerBits[srcIndex];
				dstIndex++;
			}
		}

		// 4. pack for parsing
		byte[] header = new byte[HEADER_LENGTH_BYTES];
		for (int i = 0; i < headerDataBits.length; i++) {
			header[i / 8] <<= 1;
			header[i / 8] |= headerDataBits[i];
		}

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
		int actualCrc = calculateCrc5(headerDataBits);
		if (actualCrc != ltuHeader.getCrc5()) {
			// force CRC5 bugs. replace 4th byte from the end with the contents of 3rd byte from the end
			for (int i = 0; i < 8; i++) {
				headerDataBits[(HEADER_LENGTH_BYTES - 1 - 4) * 8 + i] = headerDataBits[(HEADER_LENGTH_BYTES - 1 - 3) * 8 + i];
			}
			actualCrc = calculateCrc5(headerDataBits);
			if (actualCrc != ltuHeader.getCrc5()) {
				if (LOG.isDebugEnabled()) {
					LOG.debug("crc5 mismatch");
				}
				return null;
			}
		}
		
		// 7. extract PDU
		byte[] pdu;
		try {
			pdu = extractPdu(ltuHeader.getAiTypeSrc(), Arrays.copyOfRange(raw, HEADER_LENGTH_WITH_FEC_BITS, HEADER_LENGTH_WITH_FEC_BITS + ltuHeader.getPduLength() * 8));
		} catch (UncorrectableException e1) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("unable to decode Bch15: {}", e1.getMessage());
			}
			return null;
		}
		
		// 8. calculate CRC13
		actualCrc = calculateCrc13(pdu);
		if( actualCrc != ltuHeader.getCrc13() ) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("crc5 mismatch");
			}
			return null;
		}
		
		// 9. Parse data
		SnetBeacon result = new SnetBeacon();
		result.setHeader(ltuHeader);
		try {
			result.readExternal(pdu);
		} catch (IOException e) {
			LOG.error("unable to parse beacon", e);
			return null;
		}
		return result;
	}
	
	private static byte[] extractPdu(int aiTypeSrc, byte[] raw) throws UncorrectableException {
		int bchD;
		switch (aiTypeSrc) {
		case 0:
			return raw;
		case 1:
			bchD = 3;
			break;
		case 2:
			bchD = 5;
			break;
		case 3:
			bchD = 7;
			break;
		default:
			throw new UncorrectableException("unsupported aiTypeSrc: " + aiTypeSrc);
		}
		
		return null;
	}
	
	private static int calculateCrc13(byte[] pdu) {
		return -1;
	}

	private static int calculateCrc5(byte[] headerDataBits) {
		int crc = 0x1f;
		for (int i = headerDataBits.length / 8 - 1; i >= 0; i--) {
			for (int j = 0; j < 8; j++) {
				int c = crc & 0x10;
				c >>= 4;
				crc <<= 1;
				int currentBit = headerDataBits[i * 8 + j];
				if (c != currentBit) {
					crc ^= 0x15;
				}
				crc &= 0x1F;
			}
		}
		return crc;
	}

}
