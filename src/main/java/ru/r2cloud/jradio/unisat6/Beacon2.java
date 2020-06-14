package ru.r2cloud.jradio.unisat6;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Beacon2 {

	private int payloadSize;
	private int rebootCounter;
	private long uptime; // Milliseconds since last reboot
	private long unixTime; // Seconds since 1970
	private byte tempMcu; // Temperature CPU
	private byte tempFpga; // Temperature FPGA
	private short[] magnetometer; // Magnetometer X, Y, Z readings
	private short[] gyroscope; // Gyroscope X, Y, Z readings
	private int cpuCurrent;
	private byte tempRadio;
	private int[] payloadReserved;
	private int temperatureBottom;
	private int temperatureUpperPart;
	private int payloadReserved2;

	private Eps eps;
	private int payloadReserved3;
	private int satelliteErrorFlags;
	private int satelliteOperationStatus;

	public Beacon2() {
		// do nothing
	}

	public Beacon2(LittleEndianDataInputStream dis) throws IOException {
		// uint8_t crc; //Last 8 bits of a 16bit CRC-CCITT-BR
		payloadSize = dis.readUnsignedByte();
		rebootCounter = dis.readUnsignedShort();
		uptime = dis.readUnsignedInt();
		unixTime = dis.readUnsignedInt();
		tempMcu = dis.readByte();
		tempFpga = dis.readByte();
		magnetometer = dis.readShort(3);
		gyroscope = dis.readShort(3);
		cpuCurrent = dis.readUnsignedShort();
		tempRadio = dis.readByte();
		payloadReserved = dis.readUnsignedByte(2);
		temperatureBottom = dis.readUnsignedByte();
		temperatureUpperPart = dis.readUnsignedByte();
		payloadReserved2 = dis.readUnsignedByte();

		eps = new Eps(dis);
		payloadReserved3 = dis.readUnsignedByte();
		satelliteErrorFlags = dis.readUnsignedShort();
		satelliteOperationStatus = dis.readUnsignedByte();

		dis.skipBytes(1);
	}

	public int getPayloadSize() {
		return payloadSize;
	}

	public void setPayloadSize(int payloadSize) {
		this.payloadSize = payloadSize;
	}

	public int getRebootCounter() {
		return rebootCounter;
	}

	public void setRebootCounter(int rebootCounter) {
		this.rebootCounter = rebootCounter;
	}

	public long getUptime() {
		return uptime;
	}

	public void setUptime(long uptime) {
		this.uptime = uptime;
	}

	public long getUnixTime() {
		return unixTime;
	}

	public void setUnixTime(long unixTime) {
		this.unixTime = unixTime;
	}

	public byte getTempMcu() {
		return tempMcu;
	}

	public void setTempMcu(byte tempMcu) {
		this.tempMcu = tempMcu;
	}

	public byte getTempFpga() {
		return tempFpga;
	}

	public void setTempFpga(byte tempFpga) {
		this.tempFpga = tempFpga;
	}

	public short[] getMagnetometer() {
		return magnetometer;
	}

	public void setMagnetometer(short[] magnetometer) {
		this.magnetometer = magnetometer;
	}

	public short[] getGyroscope() {
		return gyroscope;
	}

	public void setGyroscope(short[] gyroscope) {
		this.gyroscope = gyroscope;
	}

	public int getCpuCurrent() {
		return cpuCurrent;
	}

	public void setCpuCurrent(int cpuCurrent) {
		this.cpuCurrent = cpuCurrent;
	}

	public byte getTempRadio() {
		return tempRadio;
	}

	public void setTempRadio(byte tempRadio) {
		this.tempRadio = tempRadio;
	}

	public int[] getPayloadReserved() {
		return payloadReserved;
	}

	public void setPayloadReserved(int[] payloadReserved) {
		this.payloadReserved = payloadReserved;
	}

	public int getTemperatureBottom() {
		return temperatureBottom;
	}

	public void setTemperatureBottom(int temperatureBottom) {
		this.temperatureBottom = temperatureBottom;
	}

	public int getTemperatureUpperPart() {
		return temperatureUpperPart;
	}

	public void setTemperatureUpperPart(int temperatureUpperPart) {
		this.temperatureUpperPart = temperatureUpperPart;
	}

	public int getPayloadReserved2() {
		return payloadReserved2;
	}

	public void setPayloadReserved2(int payloadReserved2) {
		this.payloadReserved2 = payloadReserved2;
	}

	public Eps getEps() {
		return eps;
	}

	public void setEps(Eps eps) {
		this.eps = eps;
	}

	public int getPayloadReserved3() {
		return payloadReserved3;
	}

	public void setPayloadReserved3(int payloadReserved3) {
		this.payloadReserved3 = payloadReserved3;
	}

	public int getSatelliteErrorFlags() {
		return satelliteErrorFlags;
	}

	public void setSatelliteErrorFlags(int satelliteErrorFlags) {
		this.satelliteErrorFlags = satelliteErrorFlags;
	}

	public int getSatelliteOperationStatus() {
		return satelliteOperationStatus;
	}

	public void setSatelliteOperationStatus(int satelliteOperationStatus) {
		this.satelliteOperationStatus = satelliteOperationStatus;
	}

}
