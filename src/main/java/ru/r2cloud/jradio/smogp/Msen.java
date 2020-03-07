package ru.r2cloud.jradio.smogp;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Msen {

	private short[] msenGyroscope;
	private short[] msenMagneto;
	private float msenTemperature;

	public Msen() {
		// do nothing
	}

	public Msen(LittleEndianDataInputStream dis) throws IOException {
		msenGyroscope = new short[3];
		for (int i = 0; i < msenGyroscope.length; i++) {
			msenGyroscope[i] = dis.readShort();
		}
		msenMagneto = new short[3];
		for (int i = 0; i < msenMagneto.length; i++) {
			msenMagneto[i] = dis.readShort();
		}
		dis.skipBytes(6);
		msenTemperature = dis.readShort() / 10.0f;
	}

	public short[] getMsenGyroscope() {
		return msenGyroscope;
	}

	public void setMsenGyroscope(short[] msenGyroscope) {
		this.msenGyroscope = msenGyroscope;
	}

	public short[] getMsenMagneto() {
		return msenMagneto;
	}

	public void setMsenMagneto(short[] msenMagneto) {
		this.msenMagneto = msenMagneto;
	}

	public float getMsenTemperature() {
		return msenTemperature;
	}

	public void setMsenTemperature(float msenTemperature) {
		this.msenTemperature = msenTemperature;
	}

}
