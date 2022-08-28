package ru.r2cloud.jradio.is1;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class UhfTemp {

	private int tempBuff;
	private int temp;

	public UhfTemp() {
		// do nothing
	}

	public UhfTemp(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		tempBuff = (raw >> 6) & 0b11;
		temp = raw & 0b111111;
	}

	public int getTempBuff() {
		return tempBuff;
	}

	public void setTempBuff(int tempBuff) {
		this.tempBuff = tempBuff;
	}

	public int getTemp() {
		return temp;
	}

	public void setTemp(int temp) {
		this.temp = temp;
	}

}
