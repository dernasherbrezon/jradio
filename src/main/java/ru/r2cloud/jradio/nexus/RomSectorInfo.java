package ru.r2cloud.jradio.nexus;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class RomSectorInfo {

	private int romNum;
	private boolean[] sectorAvailable;

	public RomSectorInfo() {
		// do nothing
	}

	public RomSectorInfo(DataInputStream dis) throws IOException {
		romNum = dis.readUnsignedByte();
		BitInputStream bis = new BitInputStream(dis);
		sectorAvailable = new boolean[64];
		for (int i = 0; i < sectorAvailable.length; i++) {
			sectorAvailable[i] = bis.readBoolean();
		}
	}

	public int getRomNum() {
		return romNum;
	}

	public void setRomNum(int romNum) {
		this.romNum = romNum;
	}

	public boolean[] getSectorAvailable() {
		return sectorAvailable;
	}

	public void setSectorAvailable(boolean[] sectorAvailable) {
		this.sectorAvailable = sectorAvailable;
	}

}
