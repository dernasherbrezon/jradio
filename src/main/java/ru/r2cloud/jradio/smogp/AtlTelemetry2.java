package ru.r2cloud.jradio.smogp;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class AtlTelemetry2 {

	private boolean msenDataValid;
	private long timestamp;
	private float temperature;
	private float[] msenGyroscope;
	private short[] msenMagnetoRaw;
	private boolean msenMagntoMinMaxValid;
	private short[] msenMagnetoMinMax;
	private float[] msenMagnetoScale;
	private float[] msenMagneto;
	private AckInfo[] ackInfo;

	public AtlTelemetry2() {
		// do nothing
	}

	public AtlTelemetry2(LittleEndianDataInputStream dis) throws IOException {
		int b = dis.readUnsignedByte();
		msenDataValid = (((char) b) == 'V');
		timestamp = dis.readUnsignedInt();
		temperature = dis.readFloat();
		msenGyroscope = dis.readFloat(3);
		msenMagnetoRaw = dis.readShort(3);
		b = dis.readUnsignedByte();
		msenMagntoMinMaxValid = (((char) b) == 'Y');
		msenMagnetoMinMax = dis.readShort(6);
		msenMagnetoScale = dis.readFloat(3);
		msenMagneto = dis.readFloat(3);

		ackInfo = new AckInfo[3];
		for (int i = 0; i < ackInfo.length; i++) {
			ackInfo[i] = new AckInfo(dis);
		}
	}

	public boolean isMsenDataValid() {
		return msenDataValid;
	}

	public void setMsenDataValid(boolean msenDataValid) {
		this.msenDataValid = msenDataValid;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public float[] getMsenGyroscope() {
		return msenGyroscope;
	}

	public void setMsenGyroscope(float[] msenGyroscope) {
		this.msenGyroscope = msenGyroscope;
	}

	public short[] getMsenMagnetoRaw() {
		return msenMagnetoRaw;
	}
	
	public void setMsenMagnetoRaw(short[] msenMagnetoRaw) {
		this.msenMagnetoRaw = msenMagnetoRaw;
	}

	public boolean isMsenMagntoMinMaxValid() {
		return msenMagntoMinMaxValid;
	}

	public void setMsenMagntoMinMaxValid(boolean msenMagntoMinMaxValid) {
		this.msenMagntoMinMaxValid = msenMagntoMinMaxValid;
	}

	public short[] getMsenMagnetoMinMax() {
		return msenMagnetoMinMax;
	}

	public void setMsenMagnetoMinMax(short[] msenMagnetoMinMax) {
		this.msenMagnetoMinMax = msenMagnetoMinMax;
	}

	public float[] getMsenMagnetoScale() {
		return msenMagnetoScale;
	}

	public void setMsenMagnetoScale(float[] msenMagnetoScale) {
		this.msenMagnetoScale = msenMagnetoScale;
	}

	public float[] getMsenMagneto() {
		return msenMagneto;
	}

	public void setMsenMagneto(float[] msenMagneto) {
		this.msenMagneto = msenMagneto;
	}

	public AckInfo[] getAckInfo() {
		return ackInfo;
	}

	public void setAckInfo(AckInfo[] ackInfo) {
		this.ackInfo = ackInfo;
	}

}
