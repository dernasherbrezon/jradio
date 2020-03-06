package ru.r2cloud.jradio.lucky7;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Lucky7Beacon extends Beacon {

	private int obcId;
	private Integer missionCounter;
	private String callsign;
	private String satelliteName;
	private Integer totalResetCounter;
	private Integer swapResetCounter;
	private Integer batteryVoltage;
	private Byte mcuTemperature;
	private Byte paTemperature;
	private Integer processorCurrent;
	private Integer mcuVoltage3V3;
	private Integer mcuVoltage1V2;
	private Short angularRateX;
	private Short angularRateY;
	private Short angularRateZ;
	private Boolean antennaBurnwire;

	private byte[] unknownPayload;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		obcId = (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | (dis.readUnsignedByte());
		if (obcId != 0x800000 && obcId != 0x000000) {
			unknownPayload = new byte[dis.available()];
			dis.readFully(unknownPayload);
			return;
		}
		missionCounter = (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | (dis.readUnsignedByte());
		callsign = readString(dis, 6);
		satelliteName = readString(dis, 6);
		totalResetCounter = dis.readUnsignedShort();
		swapResetCounter = dis.readUnsignedShort();
		batteryVoltage = dis.readUnsignedByte() * 50;
		mcuTemperature = dis.readByte();
		paTemperature = dis.readByte();
		processorCurrent = dis.readUnsignedByte();
		mcuVoltage3V3 = dis.readUnsignedByte() * 50;
		mcuVoltage1V2 = dis.readUnsignedByte() * 50;
		angularRateX = dis.readShort();
		angularRateY = dis.readShort();
		angularRateZ = dis.readShort();
		antennaBurnwire = dis.readUnsignedByte() > 0;
	}

	private static String readString(DataInputStream dis, int numberOfBytes) throws IOException {
		byte[] callsignBytes = new byte[numberOfBytes];
		dis.readFully(callsignBytes);
		return new String(callsignBytes, StandardCharsets.ISO_8859_1);
	}

	public int getObcId() {
		return obcId;
	}

	public void setObcId(int obcId) {
		this.obcId = obcId;
	}

	public Integer getMissionCounter() {
		return missionCounter;
	}

	public void setMissionCounter(Integer missionCounter) {
		this.missionCounter = missionCounter;
	}

	public String getCallsign() {
		return callsign;
	}

	public void setCallsign(String callsign) {
		this.callsign = callsign;
	}

	public String getSatelliteName() {
		return satelliteName;
	}

	public void setSatelliteName(String satelliteName) {
		this.satelliteName = satelliteName;
	}

	public Integer getTotalResetCounter() {
		return totalResetCounter;
	}

	public void setTotalResetCounter(Integer totalResetCounter) {
		this.totalResetCounter = totalResetCounter;
	}

	public Integer getSwapResetCounter() {
		return swapResetCounter;
	}

	public void setSwapResetCounter(Integer swapResetCounter) {
		this.swapResetCounter = swapResetCounter;
	}

	public Integer getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(Integer batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public Byte getMcuTemperature() {
		return mcuTemperature;
	}

	public void setMcuTemperature(Byte mcuTemperature) {
		this.mcuTemperature = mcuTemperature;
	}

	public Byte getPaTemperature() {
		return paTemperature;
	}

	public void setPaTemperature(Byte paTemperature) {
		this.paTemperature = paTemperature;
	}

	public Integer getProcessorCurrent() {
		return processorCurrent;
	}

	public void setProcessorCurrent(Integer processorCurrent) {
		this.processorCurrent = processorCurrent;
	}

	public Integer getMcuVoltage3V3() {
		return mcuVoltage3V3;
	}

	public void setMcuVoltage3V3(Integer mcuVoltage3V3) {
		this.mcuVoltage3V3 = mcuVoltage3V3;
	}

	public Integer getMcuVoltage1V2() {
		return mcuVoltage1V2;
	}

	public void setMcuVoltage1V2(Integer mcuVoltage1V2) {
		this.mcuVoltage1V2 = mcuVoltage1V2;
	}

	public Short getAngularRateX() {
		return angularRateX;
	}

	public void setAngularRateX(Short angularRateX) {
		this.angularRateX = angularRateX;
	}

	public Short getAngularRateY() {
		return angularRateY;
	}

	public void setAngularRateY(Short angularRateY) {
		this.angularRateY = angularRateY;
	}

	public Short getAngularRateZ() {
		return angularRateZ;
	}

	public void setAngularRateZ(Short angularRateZ) {
		this.angularRateZ = angularRateZ;
	}

	public Boolean getAntennaBurnwire() {
		return antennaBurnwire;
	}

	public void setAntennaBurnwire(Boolean antennaBurnwire) {
		this.antennaBurnwire = antennaBurnwire;
	}

	public byte[] getUnknownPayload() {
		return unknownPayload;
	}

	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
	}

}
