package ru.r2cloud.jradio.lucky7;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Lucky7Beacon extends Beacon {

	private int vcid;
	private int counter;
	private Integer missionCounter;
	private String callsign;
	private String satelliteName;
	private Integer totalResetCounter;
	private Integer swapResetCounter;
	private Float batteryVoltage;
	private Byte mcuTemperature;
	private Byte paTemperature;
	private Integer processorCurrent;
	private Float mcuVoltage3V3;
	private Float mcuVoltage1V2;
	private Short angularRateX;
	private Short angularRateY;
	private Short angularRateZ;
	private Boolean antennaBurnwire;

	private byte[] unknownPayload;

	private Integer imageTotalChunks;
	private Integer imageChunk;
	private byte[] imageData;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		vcid = dis.readUnsignedByte();
		counter = (dis.readUnsignedByte() << 8) | (dis.readUnsignedByte());
		if (vcid == 0x80 && counter >= 0xC000) {
			imageChunk = (dis.readUnsignedByte() << 8) | (dis.readUnsignedByte());
			imageTotalChunks = (dis.readUnsignedByte() << 8) | (dis.readUnsignedByte());
			// some image chunks contain 0xFACE in the imageChunk
			// with some unknown payload. The total chunks value seems ok
			if (imageChunk > imageTotalChunks) {
				unknownPayload = new byte[dis.available() - 2];
				dis.readFully(unknownPayload);
			} else {
				// the last 2 bytes are CRC
				imageData = new byte[dis.available() - 2];
				dis.readFully(imageData);
			}
			return;
		}
		if ((vcid != 0x80 && vcid != 0x00) || counter != 0) {
			unknownPayload = new byte[dis.available() - 2];
			dis.readFully(unknownPayload);
			return;
		}
		missionCounter = (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | (dis.readUnsignedByte());
		callsign = readString(dis, 6);
		satelliteName = readString(dis, 6);
		if (!satelliteName.equalsIgnoreCase("LUCKY7")) {
			throw new UncorrectableException("invalid satellite name: " + satelliteName);
		}
		totalResetCounter = dis.readUnsignedShort();
		swapResetCounter = dis.readUnsignedShort();
		batteryVoltage = dis.readUnsignedByte() / 50.0f;
		mcuTemperature = dis.readByte();
		paTemperature = dis.readByte();
		processorCurrent = dis.readUnsignedByte();
		mcuVoltage3V3 = dis.readUnsignedByte() / 50.0f;
		mcuVoltage1V2 = dis.readUnsignedByte() / 50.0f;
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

	public Integer getImageChunk() {
		return imageChunk;
	}

	public void setImageChunk(Integer imageChunk) {
		this.imageChunk = imageChunk;
	}

	public Integer getImageTotalChunks() {
		return imageTotalChunks;
	}

	public void setImageTotalChunks(Integer imageTotalChunks) {
		this.imageTotalChunks = imageTotalChunks;
	}

	public byte[] getImageData() {
		return imageData;
	}
	
	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

	public int getVcid() {
		return vcid;
	}

	public void setVcid(int vcid) {
		this.vcid = vcid;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public int getCounter() {
		return counter;
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

	public Float getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(Float batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public Float getMcuVoltage3V3() {
		return mcuVoltage3V3;
	}

	public void setMcuVoltage3V3(Float mcuVoltage3V3) {
		this.mcuVoltage3V3 = mcuVoltage3V3;
	}

	public Float getMcuVoltage1V2() {
		return mcuVoltage1V2;
	}

	public void setMcuVoltage1V2(Float mcuVoltage1V2) {
		this.mcuVoltage1V2 = mcuVoltage1V2;
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
