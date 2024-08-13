package ru.r2cloud.jradio.eirsat;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ru.r2cloud.jradio.ccsds.TransferFrame;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class EirsatBeacon extends TransferFrame {

	private List<Packet> packets = new ArrayList<>();

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		if (getHeader().getFieldStatus().getFirstHeaderPointer() != 0) {
			byte[] payload = new byte[getHeader().getFieldStatus().getFirstHeaderPointer()];
			dis.readFully(payload);
			Packet partial = new Packet();
			partial.setPayload(payload);
			packets.add(partial);
		}
		while (!Thread.currentThread().isInterrupted()) {
			try {
				packets.add(new Packet(dis));
			} catch (UncorrectableException e) {
				// at least one packet was recovered
				if (packets.size() > 0) {
					break;
				}
				throw e;
			} catch (EOFException e) {
				break;
			}
		}
	}

	public List<Packet> getPackets() {
		return packets;
	}

	public void setPackets(List<Packet> packets) {
		this.packets = packets;
	}
}
