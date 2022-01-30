package ru.r2cloud.jradio.delfipq;

import java.io.DataInputStream;
import java.io.IOException;

public class ObcTelemetryv2 {

	private float mcuTemperature;
	private SensorBitStatus inaStatus;
	private SensorBitStatus tmpStatus;
	private float busVoltage;
	private float busCurrent;

	public ObcTelemetryv2() {
		// do nothing
	}

	public ObcTelemetryv2(DataInputStream dis) throws IOException {
		mcuTemperature = dis.readShort() / 10.0f;

		int raw = dis.readUnsignedByte();
		inaStatus = SensorBitStatus.valueOfCode((raw >> 7) & 0x1);
		tmpStatus = SensorBitStatus.valueOfCode((raw >> 6) & 0x1);

		busVoltage = dis.readUnsignedShort() / 1000.0f;
		busCurrent = dis.readUnsignedShort() / 1000.0f;
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

	public float getBusVoltage() {
		return busVoltage;
	}

	public void setBusVoltage(float busVoltage) {
		this.busVoltage = busVoltage;
	}

	public float getBusCurrent() {
		return busCurrent;
	}

	public void setBusCurrent(float busCurrent) {
		this.busCurrent = busCurrent;
	}

}
