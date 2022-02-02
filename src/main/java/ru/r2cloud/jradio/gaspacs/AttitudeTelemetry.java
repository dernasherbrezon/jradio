package ru.r2cloud.jradio.gaspacs;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class AttitudeTelemetry {

	private long timestamp;
	private float solarSensor1;
	private float solarSensor2;
	private float solarSensor3;
	private float solarSensor4;
	private float solarSensor5;
	private float magneticFieldX;
	private float magneticFieldY;
	private float magneticFieldZ;

	public AttitudeTelemetry() {
		// do nothing
	}

	public AttitudeTelemetry(DataInputStream dis) throws IOException {
		timestamp = StreamUtils.readUnsignedInt(dis);
		solarSensor1 = dis.readFloat();
		solarSensor2 = dis.readFloat();
		solarSensor3 = dis.readFloat();
		solarSensor4 = dis.readFloat();
		solarSensor5 = dis.readFloat();
		magneticFieldX = dis.readFloat();
		magneticFieldY = dis.readFloat();
		magneticFieldZ = dis.readFloat();
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public float getSolarSensor1() {
		return solarSensor1;
	}

	public void setSolarSensor1(float solarSensor1) {
		this.solarSensor1 = solarSensor1;
	}

	public float getSolarSensor2() {
		return solarSensor2;
	}

	public void setSolarSensor2(float solarSensor2) {
		this.solarSensor2 = solarSensor2;
	}

	public float getSolarSensor3() {
		return solarSensor3;
	}

	public void setSolarSensor3(float solarSensor3) {
		this.solarSensor3 = solarSensor3;
	}

	public float getSolarSensor4() {
		return solarSensor4;
	}

	public void setSolarSensor4(float solarSensor4) {
		this.solarSensor4 = solarSensor4;
	}

	public float getSolarSensor5() {
		return solarSensor5;
	}

	public void setSolarSensor5(float solarSensor5) {
		this.solarSensor5 = solarSensor5;
	}

	public float getMagneticFieldX() {
		return magneticFieldX;
	}

	public void setMagneticFieldX(float magneticFieldX) {
		this.magneticFieldX = magneticFieldX;
	}

	public float getMagneticFieldY() {
		return magneticFieldY;
	}

	public void setMagneticFieldY(float magneticFieldY) {
		this.magneticFieldY = magneticFieldY;
	}

	public float getMagneticFieldZ() {
		return magneticFieldZ;
	}

	public void setMagneticFieldZ(float magneticFieldZ) {
		this.magneticFieldZ = magneticFieldZ;
	}

}
