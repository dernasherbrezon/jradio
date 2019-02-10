package ru.r2cloud.jradio.tubix20;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.Externalizable;
import ru.r2cloud.jradio.crc.Crc16Ccitt;
import ru.r2cloud.jradio.fec.Crc16CcittFec;
import ru.r2cloud.jradio.fec.Hamming;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.Deinterleave;

public abstract class CMX909bBeacon implements Externalizable {

	private static final Logger LOG = LoggerFactory.getLogger(CMX909bBeacon.class);

	public static final int MAX_SIZE = 1 + 1 + 1 + 6 + 2 + 32 * 30;

	private CMX909bHeader header;

	// unable to decode
	private byte[] shortDataBlock;
	private String callsign;

	private long beginSample;
	private long beginMillis;
	private byte[] rawData;

	@Override
	public void readExternal(byte[] data) throws IOException {
		this.rawData = data;
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		try {
			header = new CMX909bHeader(dis);
		} catch (UncorrectableException e) {
			throw new IOException(e);
		}

		byte[] callsignBytes = new byte[6];
		dis.readFully(callsignBytes);
		int expectedCallsignCrc = (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
		int crc16 = Crc16Ccitt.calculate(callsignBytes);
		if (crc16 != expectedCallsignCrc) {
			if (!Crc16CcittFec.fix1bitUsingCrc(callsignBytes, expectedCallsignCrc)) {
				LOG.info("bad call sign. crc mismatch");
			}
		}
		callsign = new String(callsignBytes, StandardCharsets.ISO_8859_1);
		if (header.getControl1().getType() == null) {
			return;
		}
		MobitexRandomizer randomizer = new MobitexRandomizer();
		switch (header.getControl1().getType()) {
		case ACK:
		case ERROR_CORRECTION:
			shortDataBlock = readShortDataBlock(randomizer, dis);
			break;
		default:
			byte[] dataFromBlocks;
			try {
				dataFromBlocks = readDataBlocks(header, randomizer, dis);
				readFrameData(dataFromBlocks);
			} catch (UncorrectableException e) {
				if (LOG.isDebugEnabled()) {
					LOG.debug("unable to correct data block");
				}
			}
			break;
		}
	}

	public static byte[] readDataBlocks(CMX909bHeader header, MobitexRandomizer randomizer, DataInputStream dis) throws IOException, UncorrectableException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		boolean atLeastOneBlockRecovered = false;
		int blockLength = 18;
		for (int i = 0; i < header.getControl1().getNumberOfBlocks(); i++) {
			byte[] cur = readDatablock(randomizer, dis, blockLength);
			if (cur == null) {
				cur = new byte[blockLength];
			} else {
				atLeastOneBlockRecovered = true;
			}
			baos.write(cur);
		}
		if (atLeastOneBlockRecovered) {
			return baos.toByteArray();
		}
		throw new UncorrectableException("no blocks recovered");
	}

	protected abstract void readFrameData(byte[] data) throws IOException;

	public static byte[] readShortDataBlock(MobitexRandomizer randomizer, DataInputStream dis) throws IOException {
		return readDatablock(randomizer, dis, 4);
	}

	private static byte[] readDatablock(MobitexRandomizer randomizer, DataInputStream dis, int length) throws IOException {
		byte[] blockData = new byte[length + 2 + (length + 2) * 4 / 8];
		dis.readFully(blockData);
		randomizer.shuffle(blockData);

		byte[] data = Arrays.copyOfRange(blockData, 0, length + 2);
		byte[] fecData = Arrays.copyOfRange(blockData, length + 2, blockData.length);

		byte[] deinterleaved = Deinterleave.deinterleaveBits(data, 8, data.length);
		byte[] fecDeinterleaved = Deinterleave.deinterleaveBits(fecData, 4, data.length);
		for (int i = 0; i < deinterleaved.length; i++) {
			try {
				deinterleaved[i] = (byte) Hamming.decode12_8((deinterleaved[i] << 4) | (fecDeinterleaved[i] & 0xFF));
			} catch (UncorrectableException e) {
				if (LOG.isDebugEnabled()) {
					LOG.debug("unable to correct data block");
				}
				return null;
			}
		}

		byte[] result = Arrays.copyOfRange(deinterleaved, 0, length);
		int crc16 = Crc16Ccitt.calculateReverse(result);
		int expectedCrc = ((deinterleaved[deinterleaved.length - 2] & 0xFF) << 8) | (deinterleaved[deinterleaved.length - 1] & 0xFF);
		if (crc16 != expectedCrc) {
			LOG.info("bad data block. crc mismatch");
			return null;
		}
		return result;
	}

	public CMX909bHeader getHeader() {
		return header;
	}

	public void setHeader(CMX909bHeader header) {
		this.header = header;
	}

	public byte[] getShortDataBlock() {
		return shortDataBlock;
	}

	public void setShortDataBlock(byte[] shortDataBlock) {
		this.shortDataBlock = shortDataBlock;
	}

	public String getCallsign() {
		return callsign;
	}

	public void setCallsign(String callsign) {
		this.callsign = callsign;
	}

	public long getBeginSample() {
		return beginSample;
	}

	public void setBeginSample(long beginSample) {
		this.beginSample = beginSample;
	}

	public long getBeginMillis() {
		return beginMillis;
	}

	public void setBeginMillis(long beginMillis) {
		this.beginMillis = beginMillis;
	}

	public byte[] getRawData() {
		return rawData;
	}

	public void setRawData(byte[] rawData) {
		this.rawData = rawData;
	}

}
