package ru.r2cloud.jradio.delfipq;

import java.io.DataInputStream;
import java.io.IOException;

public class AdbTelemetryv2 {

	private float mcuTemperature;

	private SensorBitStatus inaStatus;
	private SensorBitStatus tmpStatus;
	private float current;
	private float voltage;

	public AdbTelemetryv2() {
		// do nothing
	}

	public AdbTelemetryv2(DataInputStream dis) throws IOException {
		mcuTemperature = dis.readShort() / 10.0f;

		int raw = dis.readUnsignedByte();
		inaStatus = SensorBitStatus.valueOfCode((raw >> 7) & 0x1);
		tmpStatus = SensorBitStatus.valueOfCode((raw >> 6) & 0x1);

		current = dis.readShort() / 1000.0f;
		voltage = dis.readUnsignedShort() / 1000.0f;
	}

	public float getMcuTemperature() {
		return mcuTemperature;
	}

	public void setMcuTemperature(float mcuTemperature) {
		this.mcuTemperature = mcuTemperature;
	}

	public SensorBitStatus getInaStatus() {
		return inaStatus;
	}

	public void setInaStatus(SensorBitStatus inaStatus) {
		this.inaStatus = inaStatus;
	}

	public SensorBitStatus getTmpStatus() {
		return tmpStatus;
	}

	public void setTmpStatus(SensorBitStatus tmpStatus) {
		this.tmpStatus = tmpStatus;
	}

	public float getCurrent() {
		return current;
	}

	public void setCurrent(float current) {
		this.current = current;
	}

	public float getVoltage() {
		return voltage;
	}

	public void setVoltage(float voltage) {
		this.voltage = voltage;
	}

}
