package ru.r2cloud.jradio.jy1sat;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.ssdv.SsdvPacket;

public class Jy1satSsdvPacketSource implements Iterator<SsdvPacket> {

	private static final Logger LOG = LoggerFactory.getLogger(Jy1satSsdvPacketSource.class);

	private SsdvPacket next = null;
	private final Iterator<Jy1satBeacon> beacons;

	public Jy1satSsdvPacketSource(Iterator<Jy1satBeacon> beacons) {
		this.beacons = beacons;
	}

	@Override
	public boolean hasNext() {
		if (!beacons.hasNext()) {
			return false;
		}
		try {
			next = convert(beacons.next().getPayload());
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

	private static SsdvPacket convert(byte[] jy1satPayload) throws IOException {
		SsdvPacket result = new SsdvPacket();
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(jy1satPayload));
		while (!Thread.currentThread().isInterrupted()) {
			int currentByte = dis.readByte();
			if (currentByte != 0x55) {
				result.setPacketType(currentByte);
				break;
			}
		}
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

		byte[] payload = new byte[189];
		dis.readFully(payload);
		result.setPayload(payload);
		return result;
	}

}
