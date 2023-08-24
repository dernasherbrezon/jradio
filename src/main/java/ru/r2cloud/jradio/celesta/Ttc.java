package ru.r2cloud.jradio.celesta;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Ttc {

	private TtcMode ttcMode;
	private int numberOfTtcResets;
	private LastResetCause lastResetCause;
	private int numberOfReceivedValidPackets;
	private int numberOfTransmittedPackets;
	private int measuredTransmissionPower;
	private LastErrorCode lastErrorCode;
	private int powerConfiguration;
	private byte powerAmplifierTemperature;
	private int rssiOfLastReceivedPacket;
	private int frequencyDeviationOfLastReceivedPacket;
	private int beaconPeriod;

	public Ttc() {
		// do nothing
	}

	public Ttc(DataInputStream dis) throws IOException {
		LittleEndianDataInputStream ldis = new LittleEndianDataInputStream(dis);
		ttcMode = TtcMode.valueOfCode(dis.readUnsignedByte());
		numberOfTtcResets = ldis.readUnsignedShort();
		lastResetCause = LastResetCause.valueOfCode(dis.readUnsignedByte());
		numberOfReceivedValidPackets = ldis.readUnsignedShort();
		numberOfTransmittedPackets = ldis.readUnsignedShort();
		measuredTransmissionPower = ldis.readUnsignedShort();
		lastErrorCode = LastErrorCode.valueOfCode(dis.readUnsignedByte());
		powerConfiguration = dis.readUnsignedByte();
		powerAmplifierTemperature = dis.readByte();
		rssiOfLastReceivedPacket = dis.readUnsignedByte() * -1;
		frequencyDeviationOfLastReceivedPacket = dis.readUnsignedByte() * 17;
		beaconPeriod = dis.readUnsignedByte();
	}

	public TtcMode getTtcMode() {
		return ttcMode;
	}

	public void setTtcMode(TtcMode ttcMode) {
		this.ttcMode = ttcMode;
	}

	public int getNumberOfTtcResets() {
		return numberOfTtcResets;
	}

	public void setNumberOfTtcResets(int numberOfTtcResets) {
		this.numberOfTtcResets = numberOfTtcResets;
	}

	public LastResetCause getLastResetCause() {
		return lastResetCause;
	}

	public void setLastResetCause(LastResetCause lastResetCause) {
		this.lastResetCause = lastResetCause;
	}

	public int getNumberOfReceivedValidPackets() {
		return numberOfReceivedValidPackets;
	}

	public void setNumberOfReceivedValidPackets(int numberOfReceivedValidPackets) {
		this.numberOfReceivedValidPackets = numberOfReceivedValidPackets;
	}

	public int getNumberOfTransmittedPackets() {
		return numberOfTransmittedPackets;
	}

	public void setNumberOfTransmittedPackets(int numberOfTransmittedPackets) {
		this.numberOfTransmittedPackets = numberOfTransmittedPackets;
	}

	public int getMeasuredTransmissionPower() {
		return measuredTransmissionPower;
	}

	public void setMeasuredTransmissionPower(int measuredTransmissionPower) {
		this.measuredTransmissionPower = measuredTransmissionPower;
	}

	public LastErrorCode getLastErrorCode() {
		return lastErrorCode;
	}

	public void setLastErrorCode(LastErrorCode lastErrorCode) {
		this.lastErrorCode = lastErrorCode;
	}

	public int getPowerConfiguration() {
		return powerConfiguration;
	}

	public void setPowerConfiguration(int powerConfiguration) {
		this.powerConfiguration = powerConfiguration;
	}

	public byte getPowerAmplifierTemperature() {
		return powerAmplifierTemperature;
	}

	public void setPowerAmplifierTemperature(byte powerAmplifierTemperature) {
		this.powerAmplifierTemperature = powerAmplifierTemperature;
	}

	public int getRssiOfLastReceivedPacket() {
		return rssiOfLastReceivedPacket;
	}

	public void setRssiOfLastReceivedPacket(int rssiOfLastReceivedPacket) {
		this.rssiOfLastReceivedPacket = rssiOfLastReceivedPacket;
	}

	public int getFrequencyDeviationOfLastReceivedPacket() {
		return frequencyDeviationOfLastReceivedPacket;
	}

	public void setFrequencyDeviationOfLastReceivedPacket(int frequencyDeviationOfLastReceivedPacket) {
		this.frequencyDeviationOfLastReceivedPacket = frequencyDeviationOfLastReceivedPacket;
	}

	public int getBeaconPeriod() {
		return beaconPeriod;
	}

	public void setBeaconPeriod(int beaconPeriod) {
		this.beaconPeriod = beaconPeriod;
	}

}
