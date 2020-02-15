package ru.r2cloud.jradio.dslwp;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.util.IOUtils;
import ru.r2cloud.ssdv.SsdvPacket;

public class DslwpSsdvPacketSource implements Iterator<SsdvPacket> {

	private static final Logger LOG = LoggerFactory.getLogger(DslwpSsdvPacketSource.class);
	private static final int DSLWP_IMAGE_PACKET_SIZE = 218;
	private static final byte[] EMPTY_FEC = new byte[32];

	private SsdvPacket next = null;
	private final InputStream is;
	private final byte[] payload = new byte[DSLWP_IMAGE_PACKET_SIZE];
	private boolean hasNext = true;

	public DslwpSsdvPacketSource(InputStream is) {
		this.is = is;
	}

	@Override
	public boolean hasNext() {
		if (!hasNext) {
			return false;
		}
		try {
			IOUtils.readFully(is, payload);
		} catch (IOException e) {
			hasNext = false;
			return hasNext;
		}
		try {
			next = convert(payload);
			return true;
		} catch (IOException e) {
			LOG.error("unable to read the packet", e);
			return false;
		}
	}

	@Override
	public SsdvPacket next() {
		if (next == null) {
			throw new NoSuchElementException();
		}
		return next;
	}

	private static SsdvPacket convert(byte[] currentPayload) throws IOException {
		SsdvPacket result = new SsdvPacket();
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(currentPayload));
		// callsign is 00 0e 72 40
		result.setPacketType(0x66); // hardcoded
		result.setImageId(dis.readUnsignedByte());
		result.setPacketId(dis.readUnsignedShort());
		result.setWidthMcu(dis.readUnsignedByte());
		result.setHeightMcu(dis.readUnsignedByte());

		int flags = dis.readUnsignedByte();
		result.setJpegQualityLevel(((flags >> 3) & 0b111) ^ 4);
		result.setLastPacket(((flags >> 2) & 0b1) > 0);
		result.setSubsamplingMode(flags & 0b11);

		result.setMcuOffset(dis.readUnsignedByte());
		result.setMcuIndex(dis.readUnsignedShort());

		byte[] payload = new byte[205];
		dis.readFully(payload);
		result.setPayload(payload);
		result.setChecksum(dis.readInt());
		result.setFec(EMPTY_FEC);
		return result;
	}

}
