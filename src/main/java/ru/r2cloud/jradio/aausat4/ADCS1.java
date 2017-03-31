package ru.r2cloud.jradio.aausat4;

import java.io.DataInputStream;
import java.io.IOException;

public class ADCS1 {

	private short bdot1;
	private short bdot2;
	private short bdot3;
	private int state;

	public ADCS1(DataInputStream data) throws IOException {
		bdot1 = data.readShort();
		bdot2 = data.readShort();
		bdot3 = data.readShort();
		state = data.readUnsignedByte();
	}

	public short getBdot1() {
		return bdot1;
	}

	public void setBdot1(short bdot1) {
		this.bdot1 = bdot1;
	}

	public short getBdot2() {
		return bdot2;
	}

	public void setBdot2(short bdot2) {
		this.bdot2 = bdot2;
	}

	public short getBdot3() {
		return bdot3;
	}

	public void setBdot3(short bdot3) {
		this.bdot3 = bdot3;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
