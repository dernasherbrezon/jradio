package ru.r2cloud.jradio.silversat;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import ru.r2cloud.jradio.il2p.Il2pBeacon;
import ru.r2cloud.ssdv.SsdvPacket;

public class SilversatPacketSource implements Iterator<SsdvPacket> {

	private final Iterator<Il2pBeacon> it;
	private SsdvPacket next;

	public SilversatPacketSource(Iterator<Il2pBeacon> it) {
		this.it = it;
	}

	@Override
	public boolean hasNext() {
		while (it.hasNext()) {
			try {
				Il2pBeacon beacon = it.next();
				DataInputStream dis = new DataInputStream(new ByteArrayInputStream(beacon.getPayload()));
				int sync;
				sync = dis.readUnsignedByte();
				if (sync != 0x55) {
					continue;
				}
				next = new SsdvPacket();
				next.setPacketType(dis.readUnsignedByte());
				// callsign is de eb 79 6c
				dis.skipBytes(4);
				next.setImageId(dis.readUnsignedByte());
				next.setPacketId(dis.readUnsignedShort());
				next.setWidthMcu(dis.readUnsignedByte());
				next.setHeightMcu(dis.readUnsignedByte());

				int flags = dis.readUnsignedByte();
				next.setJpegQualityLevel(4);
				next.setLastPacket(((flags >> 2) & 0b1) > 0);
				next.setSubsamplingMode(flags & 0b11);

				next.setMcuOffset(dis.readUnsignedByte());
				next.setMcuIndex(dis.readUnsignedShort());
				byte[] payload = new byte[dis.available() - 4];
				dis.readFully(payload);
				next.setPayload(payload);
				next.setChecksum(dis.readInt());
				return true;
			} catch (IOException e) {
				continue;
			}
		}
		next = null;
		return false;
	}

	@Override
	public SsdvPacket next() {
		if (next == null) {
			throw new NoSuchElementException();
		}
		return next;
	}

}
