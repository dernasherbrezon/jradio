package ru.r2cloud.jradio.cute.soh;

import java.io.DataInputStream;
import java.io.IOException;

public class SohEventCheck {

	private int latchedRespFirePack1;
	private int latchedRespFirePack2;

	public SohEventCheck() {
		// do nothing
	}

	public SohEventCheck(DataInputStream dis) throws IOException {
		latchedRespFirePack1 = dis.readUnsignedByte();
		latchedRespFirePack2 = dis.readUnsignedByte();
	}

	public int getLatchedRespFirePack1() {
		return latchedRespFirePack1;
	}

	public void setLatchedRespFirePack1(int latchedRespFirePack1) {
		this.latchedRespFirePack1 = latchedRespFirePack1;
	}

	public int getLatchedRespFirePack2() {
		return latchedRespFirePack2;
	}

	public void setLatchedRespFirePack2(int latchedRespFirePack2) {
		this.latchedRespFirePack2 = latchedRespFirePack2;
	}

}
