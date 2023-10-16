package ru.r2cloud.jradio.sapling;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;
import ru.r2cloud.jradio.util.StreamUtils;

public class SaplingGiganteumBeacon extends Beacon {

	private int toAddress;
	private int fromAddress;
	private int id;
	private int flags;
	private String callsign;
	private State state;
	private int txPower;
	private boolean solarCharging;
	private boolean enableRf;
	private int memFree;
	private int boot;
	private int gsResp;
	private int crcErr;
	private int stateErr;
	private float vbatt;
	private float vsys;
	private float isys;
	private int tbatt;
	private int rssi;
	private float snr;
	private int[] lux;
	private float[] mag;
	private float[] gyro;
	private int imuTemp;
	private float lat;
	private float lon;
	private float alt;
	private Double datetime;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		toAddress = dis.readUnsignedByte();
		fromAddress = dis.readUnsignedByte();
		id = dis.readUnsignedByte();
		flags = dis.readUnsignedByte();
		callsign = StreamUtils.readString(dis, 6);
		// no way to tell if packet valid telemetry or unsupported communication type
		// use known callsign to filter out all false-positive reception packets
		if (!callsign.equalsIgnoreCase("KN6HCC")) {
			throw new UncorrectableException("invalid callsign: " + callsign);
		}

		int raw = dis.readUnsignedByte();
		if ((raw & 1) == 1) {
			state = State.IDLE;
		} else {
			state = State.SAFE;
		}
		txPower = ((raw >> 1) & 0b11111) + 5;
		solarCharging = ((raw >> 6) & 0b1) > 0;
		enableRf = ((raw >> 7) & 0b1) > 0;
		memFree = dis.readUnsignedByte() * 1000;
		boot = dis.readUnsignedByte();
		gsResp = dis.readUnsignedByte();
		crcErr = dis.readUnsignedByte();
		stateErr = dis.readUnsignedByte();
		vbatt = dis.readUnsignedByte() / 75.0f + 5;
		vsys = dis.readUnsignedByte() / 75.0f + 5;
		isys = dis.readUnsignedByte() * 70.0f / 255.0f;
		tbatt = dis.readUnsignedByte() - 100;
		rssi = dis.readUnsignedByte() - 137;
		raw = dis.readUnsignedByte();
		if (raw > 127) {
			raw = (256 - raw) * -1;
		}
		snr = raw / 4.0f;
		lux = new int[9];
		for (int i = 0, j = 0; i < 3; i++, j++) {
			int b1 = dis.readUnsignedByte();
			int b2 = dis.readUnsignedByte();
			int b3 = dis.readUnsignedByte();
			// first positive
			lux[2 * j] = ((b3 << 4) | (b2 >> 4)) << 3;
			// second negative
			lux[2 * j + 1] = (((b2 & 0b00001111) << 8) | b1) << 3;
		}
		mag = new float[3];
		for (int i = 0; i < mag.length; i++) {
			int b1 = dis.readUnsignedByte();
			int b2 = dis.readUnsignedByte();
			mag[i] = ((b2 << 8) | b1) / 256.0f - 127.0f;
		}
		gyro = new float[3];
		for (int i = 0; i < gyro.length; i++) {
			int b1 = dis.readUnsignedByte();
			int b2 = dis.readUnsignedByte();
			gyro[i] = ((b2 << 8) | b1) / 1024.0f - 31.0f;
		}
		imuTemp = dis.readUnsignedByte() - 100;
		LittleEndianDataInputStream ldis = new LittleEndianDataInputStream(dis);
		lat = ldis.readUnsignedShort() / 364.0f;
		lon = ldis.readUnsignedShort() / 182.0f - 180.0f;
		alt = ldis.readUnsignedShort() * 9.155f;
		// 2023-03-01 00:00:00
		long epoch = 1677628800000L;
		int b1 = dis.readUnsignedByte();
		int b2 = dis.readUnsignedByte();
		int b3 = dis.readUnsignedByte();
		int b4 = dis.readUnsignedByte();
		if (b1 != 255 && b2 != 255 && b3 != 255 && b4 != 255) {
			datetime = epoch + (double) Float.intBitsToFloat(((b1 << 24) + (b2 << 16) + (b3 << 8) + b4));
		}
	}

	public int getToAddress() {
		return toAddress;
	}

	public void setToAddress(int toAddress) {
		this.toAddress = toAddress;
	}

	public int getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(int fromAddress) {
		this.fromAddress = fromAddress;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFlags() {
		return flags;
	}

	public void setFlags(int flags) {
		this.flags = flags;
	}

	public String getCallsign() {
		return callsign;
	}

	public void setCallsign(String callsign) {
		this.callsign = callsign;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public int getTxPower() {
		return txPower;
	}

	public void setTxPower(int txPower) {
		this.txPower = txPower;
	}

	public boolean isSolarCharging() {
		return solarCharging;
	}

	public void setSolarCharging(boolean solarCharging) {
		this.solarCharging = solarCharging;
	}

	public boolean isEnableRf() {
		return enableRf;
	}

	public void setEnableRf(boolean enableRf) {
		this.enableRf = enableRf;
	}

	public int getMemFree() {
		return memFree;
	}

	public void setMemFree(int memFree) {
		this.memFree = memFree;
	}

	public int getBoot() {
		return boot;
	}

	public void setBoot(int boot) {
		this.boot = boot;
	}

	public int getGsResp() {
		return gsResp;
	}

	public void setGsResp(int gsResp) {
		this.gsResp = gsResp;
	}

	public int getCrcErr() {
		return crcErr;
	}

	public void setCrcErr(int crcErr) {
		this.crcErr = crcErr;
	}

	public int getStateErr() {
		return stateErr;
	}

	public void setStateErr(int stateErr) {
		this.stateErr = stateErr;
	}

	public float getVbatt() {
		return vbatt;
	}

	public void setVbatt(float vbatt) {
		this.vbatt = vbatt;
	}

	public float getVsys() {
		return vsys;
	}

	public void setVsys(float vsys) {
		this.vsys = vsys;
	}

	public float getIsys() {
		return isys;
	}

	public void setIsys(float isys) {
		this.isys = isys;
	}

	public int getTbatt() {
		return tbatt;
	}

	public void setTbatt(int tbatt) {
		this.tbatt = tbatt;
	}

	public int getRssi() {
		return rssi;
	}

	public void setRssi(int rssi) {
		this.rssi = rssi;
	}

	public float getSnr() {
		return snr;
	}

	public void setSnr(float snr) {
		this.snr = snr;
	}

	public int[] getLux() {
		return lux;
	}

	public void setLux(int[] lux) {
		this.lux = lux;
	}

	public float[] getMag() {
		return mag;
	}

	public void setMag(float[] mag) {
		this.mag = mag;
	}

	public float[] getGyro() {
		return gyro;
	}

	public void setGyro(float[] gyro) {
		this.gyro = gyro;
	}

	public int getImuTemp() {
		return imuTemp;
	}

	public void setImuTemp(int imuTemp) {
		this.imuTemp = imuTemp;
	}

	public float getLat() {
		return lat;
	}

	public void setLat(float lat) {
		this.lat = lat;
	}

	public float getLon() {
		return lon;
	}

	public void setLon(float lon) {
		this.lon = lon;
	}

	public float getAlt() {
		return alt;
	}

	public void setAlt(float alt) {
		this.alt = alt;
	}

	public Double getDatetime() {
		return datetime;
	}

	public void setDatetime(Double datetime) {
		this.datetime = datetime;
	}

}
