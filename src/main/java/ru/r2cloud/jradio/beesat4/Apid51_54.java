package ru.r2cloud.jradio.beesat4;

import java.io.DataInputStream;
import java.io.IOException;

public class Apid51_54 {

	private int ALMIDX; // Almanac ID of PDH almanac data, unique per Slot
	private byte[] ALMDAT; // Almanac Data

	public Apid51_54(DataInputStream dis) throws IOException {
		ALMIDX = dis.readUnsignedByte();
		ALMDAT = new byte[125];
		dis.readFully(ALMDAT);
	}

	public int getALMIDX() {
		return ALMIDX;
	}

	public void setALMIDX(int aLMIDX) {
		ALMIDX = aLMIDX;
	}

	public byte[] getALMDAT() {
		return ALMDAT;
	}

	public void setALMDAT(byte[] aLMDAT) {
		ALMDAT = aLMDAT;
	}

}
