package ru.r2cloud.jradio.gaspacs;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.ssdv.SsdvPacket;

public class GaspacsPacketSource implements Iterator<SsdvPacket> {

	private static final Logger LOG = LoggerFactory.getLogger(GaspacsPacketSource.class);

	private SsdvPacket next = null;
	private final Iterator<GaspacsBeacon> beacons;

	public GaspacsPacketSource(Iterator<GaspacsBeacon> beacons) {
		this.beacons = beacons;
	}

	@Override
	public boolean hasNext() {
		byte[] imagePayload = null;
		while (beacons.hasNext()) {
			GaspacsBeacon cur = beacons.next();
			if (cur.getSsdvImage() != null) {
				imagePayload = cur.getSsdvImage();
				break;
			}
		}
		if (imagePayload == null) {
			return false;
		}
		try {
			next = convert(imagePayload);
			return true;
		} catch (IOException e) {
			LOG.error("unable to read the packet", e);
			return false;
		}
	}

	private static SsdvPacket convert(byte[] imagePayload) throws IOException {
		SsdvPacket result = new SsdvPacket();
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(imagePayload));
		while (!Thread.currentThread().isInterrupted()) {
			int currentByte = dis.readUnsignedByte();
			if (currentByte != 0x55) {
				result.setPacketType(currentByte);
				break;
			}
		}
		// skip callsign
		dis.skipBytes(4);
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

		byte[] payload = new byte[dis.available() - 32 - 4];
		dis.readFully(payload);
		result.setPayload(payload);
		result.setChecksum(dis.readInt());
		byte[] fec = new byte[32];
		dis.readFully(fec);
		result.setFec(fec);
		return result;
	}

	@Override
	public SsdvPacket next() {
		if (next == null) {
			throw new NoSuchElementException();
		}
		return next;
	}

}
