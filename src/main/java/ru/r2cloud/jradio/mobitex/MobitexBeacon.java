package ru.r2cloud.jradio.mobitex;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.crc.Crc16Ccitt;
import ru.r2cloud.jradio.fec.Hamming;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.Deinterleave;
import ru.r2cloud.jradio.util.GapData;

public class MobitexBeacon extends Beacon {

	public static final int BLOCK_SIZE_BYTES = 18;
	public static final int MAX_SIZE = 3 + 32 * 30;
	private Header header;
	private byte[] payload;

	private final Integer forceNumberOfBlocks;

	public MobitexBeacon() {
		forceNumberOfBlocks = null;
	}

	public MobitexBeacon(int forceNumberOfBlocks) {
		this.forceNumberOfBlocks = forceNumberOfBlocks;
	}

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		header = new Header(dis);
		MobitexRandomizer randomizer = new MobitexRandomizer();
		switch (header.getControl1().getType()) {
		case ACK:
		case ERROR_CORRECTION:
			GapData gap = new GapData(1);
			gap.write(readShortDataBlock(randomizer, dis));
			readBeacon(gap);
			break;
		default:
			int numberOfBlocks;
			if (forceNumberOfBlocks != null) {
				numberOfBlocks = forceNumberOfBlocks;
			} else {
				numberOfBlocks = header.getControl1().getNumberOfBlocks();
			}
			readBeacon(readGapDataBlocks(numberOfBlocks, randomizer, dis));
			break;
		}
	}

	public void readBeacon(GapData data) throws IOException, UncorrectableException {
		// by default do not accept gapped data
		if (data.getNonEmptyBlocks() != header.getControl1().getNumberOfBlocks()) {
			throw new UncorrectableException("not all blocks recovered");
		}
		int total = 0;
		for (int cur : data.getLengths()) {
			total += cur;
		}
		byte[] payload = new byte[total];
		int totalWritten = 0;
		for (byte[] cur : data.getChunks()) {
			System.arraycopy(cur, 0, payload, totalWritten, cur.length);
			totalWritten += cur.length;
		}
		try {
			readBeacon(new DataInputStream(new ByteArrayInputStream(payload)));
		} catch (EOFException e) {
			this.payload = payload;
		}

	}

	@SuppressWarnings("unused")
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		payload = new byte[dis.available()];
		dis.readFully(payload);
	}

	private static GapData readGapDataBlocks(int numberOfBlocks, MobitexRandomizer randomizer, DataInputStream dis) throws IOException, UncorrectableException {
		GapData result = null;
		int gaps = 0;
		for (int i = 0; i < numberOfBlocks; i++) {
			try {
				byte[] block = readDatablock(randomizer, dis, BLOCK_SIZE_BYTES);
				if (result == null) {
					result = new GapData(numberOfBlocks);
					for (int j = 0; j < gaps; j++) {
						result.gap(BLOCK_SIZE_BYTES);
					}
				}
				result.write(block);
			} catch (UncorrectableException e) {
				if (result != null) {
					result.gap(BLOCK_SIZE_BYTES);
				}
				gaps++;
			}
		}
		if (result == null) {
			throw new UncorrectableException("no blocks recovered");
		}
		return result;
	}

	private static byte[] readShortDataBlock(MobitexRandomizer randomizer, DataInputStream dis) throws IOException, UncorrectableException {
		return readDatablock(randomizer, dis, 4);
	}

	private static byte[] readDatablock(MobitexRandomizer randomizer, DataInputStream dis, int length) throws IOException, UncorrectableException {
		byte[] blockData = new byte[length + 2 + (length + 2) * 4 / 8];
		dis.readFully(blockData);
		randomizer.shuffle(blockData);

		byte[] data = Arrays.copyOfRange(blockData, 0, length + 2);
		byte[] fecData = Arrays.copyOfRange(blockData, length + 2, blockData.length);

		byte[] deinterleaved = Deinterleave.deinterleaveBits(data, 8, data.length);
		byte[] fecDeinterleaved = Deinterleave.deinterleaveBits(fecData, 4, data.length);
		for (int i = 0; i < deinterleaved.length; i++) {
			deinterleaved[i] = (byte) Hamming.decode12b8((deinterleaved[i] << 4) | (fecDeinterleaved[i] & 0xFF));
		}

		int crc16 = Crc16Ccitt.calculateReverse(deinterleaved, 0, length);
		int expectedCrc = ((deinterleaved[deinterleaved.length - 2] & 0xFF) << 8) | (deinterleaved[deinterleaved.length - 1] & 0xFF);
		if (crc16 != expectedCrc) {
			throw new UncorrectableException("bad data block. crc mismatch");
		}
		return Arrays.copyOfRange(deinterleaved, 0, length);
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public byte[] getPayload() {
		return payload;
	}

	public void setPayload(byte[] payload) {
		this.payload = payload;
	}

	@Override
	public String toString() {
		if (header == null) {
			return "null";
		} else {
			return header.toString();
		}
	}

}
