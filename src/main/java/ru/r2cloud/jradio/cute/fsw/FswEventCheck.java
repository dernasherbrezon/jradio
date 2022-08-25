package ru.r2cloud.jradio.cute.fsw;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class FswEventCheck {

	private int responseFireCount;
	private int[] checkEnabledPack;
	private int[] responseEnabledPack;
	private int[] latchedRespFirePack;

	public FswEventCheck() {
		// do nothing
	}

	public FswEventCheck(DataInputStream dis) throws IOException {
		responseFireCount = dis.readUnsignedByte();
		checkEnabledPack = StreamUtils.readUnsignedByteArray(dis, 16);
		responseEnabledPack = StreamUtils.readUnsignedByteArray(dis, 16);
		latchedRespFirePack = StreamUtils.readUnsignedByteArray(dis, 16);
	}

	public int getResponseFireCount() {
		return responseFireCount;
	}

	public void setResponseFireCount(int responseFireCount) {
		this.responseFireCount = responseFireCount;
	}

	public int[] getCheckEnabledPack() {
		return checkEnabledPack;
	}

	public void setCheckEnabledPack(int[] checkEnabledPack) {
		this.checkEnabledPack = checkEnabledPack;
	}

	public int[] getResponseEnabledPack() {
		return responseEnabledPack;
	}

	public void setResponseEnabledPack(int[] responseEnabledPack) {
		this.responseEnabledPack = responseEnabledPack;
	}

	public int[] getLatchedRespFirePack() {
		return latchedRespFirePack;
	}

	public void setLatchedRespFirePack(int[] latchedRespFirePack) {
		this.latchedRespFirePack = latchedRespFirePack;
	}

}
