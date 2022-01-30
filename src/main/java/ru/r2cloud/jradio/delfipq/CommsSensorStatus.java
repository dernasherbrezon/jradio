package ru.r2cloud.jradio.delfipq;

import java.io.DataInputStream;
import java.io.IOException;

public class CommsSensorStatus {

	private SensorBitStatus inaStatus;
	private SensorBitStatus tmpStatus;
	private SensorBitStatus transmitINAStatus;
	private SensorBitStatus amplifierINAStatus;
	private SensorBitStatus phasingTMPStatus;
	private SensorBitStatus amplifierTMPStatus;

	public CommsSensorStatus() {
		// do nothing
	}

	public CommsSensorStatus(DataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		inaStatus = SensorBitStatus.valueOfCode((raw >> 7) & 0b1);
		tmpStatus = SensorBitStatus.valueOfCode((raw >> 6) & 0b1);
		transmitINAStatus = SensorBitStatus.valueOfCode((raw >> 5) & 0b1);
		amplifierINAStatus = SensorBitStatus.valueOfCode((raw >> 4) & 0b1);
		phasingTMPStatus = SensorBitStatus.valueOfCode((raw >> 3) & 0b1);
		amplifierTMPStatus = SensorBitStatus.valueOfCode((raw >> 2) & 0b1);
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

	public SensorBitStatus getTransmitINAStatus() {
		return transmitINAStatus;
	}

	public void setTransmitINAStatus(SensorBitStatus transmitINAStatus) {
		this.transmitINAStatus = transmitINAStatus;
	}

	public SensorBitStatus getAmplifierINAStatus() {
		return amplifierINAStatus;
	}

	public void setAmplifierINAStatus(SensorBitStatus amplifierINAStatus) {
		this.amplifierINAStatus = amplifierINAStatus;
	}

	public SensorBitStatus getPhasingTMPStatus() {
		return phasingTMPStatus;
	}

	public void setPhasingTMPStatus(SensorBitStatus phasingTMPStatus) {
		this.phasingTMPStatus = phasingTMPStatus;
	}

	public SensorBitStatus getAmplifierTMPStatus() {
		return amplifierTMPStatus;
	}

	public void setAmplifierTMPStatus(SensorBitStatus amplifierTMPStatus) {
		this.amplifierTMPStatus = amplifierTMPStatus;
	}

}
