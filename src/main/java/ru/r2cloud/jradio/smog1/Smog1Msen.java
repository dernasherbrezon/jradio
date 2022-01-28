package ru.r2cloud.jradio.smog1;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Smog1Msen {

	private short[] msenGyroscope;
	private short[] msenMagneto;
	private short[] msenAcceleration;
	private float msenTemperature;

	public Smog1Msen() {
		// do nothing
	}

	public Smog1Msen(LittleEndianDataInputStream dis) throws IOException {
		msenGyroscope = new short[3];
		for (int i = 0; i < msenGyroscope.length; i++) {
			msenGyroscope[i] = dis.readShort();
		}
		msenMagneto = new short[3];
		for (int i = 0; i < msenMagneto.length; i++) {
			msenMagneto[i] = dis.readShort();
		}
		msenAcceleration = new short[3];
		for (int i = 0; i < msenAcceleration.length; i++) {
			msenAcceleration[i] = dis.readShort();
		}
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

	public short[] getMsenAcceleration() {
		return msenAcceleration;
	}

	public void setMsenAcceleration(short[] msenAcceleration) {
		this.msenAcceleration = msenAcceleration;
	}

}
