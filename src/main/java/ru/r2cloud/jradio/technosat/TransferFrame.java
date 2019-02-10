package ru.r2cloud.jradio.technosat;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TransferFrame {

	private int spacecraftIdentifier;
	private byte tfVersionNumber;
	private int tfCounter;

	private List<SourcePacket> packets = new ArrayList<>();

	public void readExternal(byte[] data) throws IOException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		int rawId = dis.readUnsignedShort();
		spacecraftIdentifier = rawId >> 4;
		tfVersionNumber = (byte) (rawId & 0xF);
		tfCounter = dis.readUnsignedShort();
		while (true) {
			try {
				SourcePacket cur = new SourcePacket();
				cur.readExternal(dis);
				packets.add(cur);
			} catch (IOException e) {
				break;
			}
		}
	}

	public int getSpacecraftIdentifier() {
		return spacecraftIdentifier;
	}

	public void setSpacecraftIdentifier(int spacecraftIdentifier) {
		this.spacecraftIdentifier = spacecraftIdentifier;
	}

	public byte getTfVersionNumber() {
		return tfVersionNumber;
	}

	public void setTfVersionNumber(byte tfVersionNumber) {
		this.tfVersionNumber = tfVersionNumber;
	}

	public int getTfCounter() {
		return tfCounter;
	}

	public void setTfCounter(int tfCounter) {
		this.tfCounter = tfCounter;
	}

	public List<SourcePacket> getPackets() {
		return packets;
	}

	public void setPackets(List<SourcePacket> packets) {
		this.packets = packets;
	}

}
