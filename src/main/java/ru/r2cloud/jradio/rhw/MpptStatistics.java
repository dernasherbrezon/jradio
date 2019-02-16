package ru.r2cloud.jradio.rhw;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class MpptStatistics {

	private int currentMpptValue1;
	private int currentMpptValue2;

	public MpptStatistics(LittleEndianDataInputStream dis) throws IOException {
		currentMpptValue1 = dis.readUnsignedShort();
		currentMpptValue2 = dis.readUnsignedShort();
	}

	public int getCurrentMpptValue1() {
		return currentMpptValue1;
	}

	public void setCurrentMpptValue1(int currentMpptValue1) {
		this.currentMpptValue1 = currentMpptValue1;
	}

	public int getCurrentMpptValue2() {
		return currentMpptValue2;
	}

	public void setCurrentMpptValue2(int currentMpptValue2) {
		this.currentMpptValue2 = currentMpptValue2;
	}

}
