package ru.r2cloud.jradio.salsat;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.blocks.CorrelateAccessCodeTag;
import ru.r2cloud.jradio.blocks.FixedLengthTagger;
import ru.r2cloud.jradio.blocks.SoftToHard;
import ru.r2cloud.jradio.blocks.TaggedStreamToPdu;
import ru.r2cloud.jradio.blocks.UnpackedToPacked;
import ru.r2cloud.jradio.fec.Bch15;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.snet.LTUFrameHeader;
import ru.r2cloud.jradio.snet.Snet;
import ru.r2cloud.jradio.snet.SnetBeacon;
import ru.r2cloud.jradio.util.Deinterleave;

public class Salsat extends BeaconSource<SnetBeacon> {

	private static final Logger LOG = LoggerFactory.getLogger(Salsat.class);

	private static final int HEADER_LENGTH_BYTES = 9;
	private static final int CHUNK_LENGTH_BITS = 15;
	private static final int NUMBER_OF_HEADER_CHUNKS = 14;

	public Salsat(ByteInput input) {
		super(new TaggedStreamToPdu(new FixedLengthTagger(new CorrelateAccessCodeTag(new SoftToHard(input), 6, "00000100110011110101111111001000", false), 512 * 8)));
	}

	@Override
	protected SnetBeacon parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		// 1. de-interleave header
		byte[] headerBits = Deinterleave.deinterleaveBitsUnpacked(raw, 0, CHUNK_LENGTH_BITS, NUMBER_OF_HEADER_CHUNKS);
		// 2. correct errors BCH(15,5,7)
		for (int i = 0; i < NUMBER_OF_HEADER_CHUNKS; i++) {
			Bch15.decode(headerBits, i * CHUNK_LENGTH_BITS, 7);
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
		ltuHeader = new LTUFrameHeader(header);

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
			if (LOG.isDebugEnabled()) {
				LOG.debug("crc5 mismatch");
			}
			return null;
		}

		if (ltuHeader.getPduLength() == 0) {
			SnetBeacon result = new SnetBeacon();
			result.setHeader(ltuHeader);
			result.setRawData(header);
			return result;
		}

		// 7. extract PDU
		byte[] pdu = Snet.extractPdu(ltuHeader, raw);

		// 8. calculate CRC13
		actualCrc = calculateCrc13(pdu);
		if (actualCrc != ltuHeader.getCrc13()) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("crc5 mismatch");
			}
			return null;
		}
		
		// 9. Parse data
		SnetBeacon result = new SnetBeacon();
		result.setHeader(ltuHeader);
		result.readExternal(UnpackedToPacked.pack(pdu));
		return result;
	}

	private static int calculateCrc13(byte[] pdu) {
		int crc = 0x1FFF;
		for (int i = 0; i < pdu.length; i++) {
			int c = crc & 0x1000;
			c >>= 12;
			crc <<= 1;
			if (c != pdu[i]) {
				crc ^= 0x1CF5;
			}
			crc &= 0x1FFF;
		}
		return crc;
	}

	private static int calculateCrc5(byte[] data) {
		int crc = 0x1f;
		for (int i = 0; i < data.length; i++) {
			int c = crc & 0x10;
			c >>= 4;
			crc <<= 1;
			if (c != data[i]) {
				crc ^= 0x15;
			}
			crc &= 0x1F;
		}
		return crc;
	}

}
