package ru.r2cloud.jradio.hype;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import ru.r2cloud.jradio.util.StreamUtils;

public class HypeTelemetry {

	private String callsign;
	private long commandCounter;
	private SatMode mode;
	private float vbus;
	private float balance;
	private float charge;
	private float satTemperature;
	private int swVersion;
	private long restartCounter;
	private long uptime;
	private int xrate;
	private int yrate;
	private int zrate;

	private boolean softwareSlotb;
	private int txPower;

	public HypeTelemetry() {
		// do nothing
	}

	public HypeTelemetry(DataInputStream dis) throws IOException {
		byte[] callsignBytes = new byte[4];
		dis.readFully(callsignBytes);
		callsign = new String(callsignBytes, StandardCharsets.US_ASCII);
		commandCounter = StreamUtils.readUnsignedInt(dis);
		mode = SatMode.valueOfCode(dis.readUnsignedByte());
		vbus = dis.readUnsignedShort() / 10.0f;
		balance = dis.readUnsignedShort() / 1000.0f;
		charge = dis.readUnsignedShort() * 10.0f;
		satTemperature = dis.readUnsignedShort() / 320.0f;
		swVersion = dis.readUnsignedShort();
		restartCounter = StreamUtils.readUnsignedInt(dis);
		uptime = StreamUtils.readUnsignedInt(dis);
		xrate = dis.readShort() * 100;
		yrate = dis.readShort() * 100;
		zrate = dis.readShort() * 100;

		int raw = dis.readUnsignedByte();
		softwareSlotb = ((raw >> 7) & 0x1) > 0;
		txPower = raw & 0b111111;
	}

	public String getCallsign() {
		return callsign;
	}

	public void setCallsign(String callsign) {
		this.callsign = callsign;
	}

	public long getCommandCounter() {
		return commandCounter;
	}

	public void setCommandCounter(long commandCounter) {
		this.commandCounter = commandCounter;
	}

	public SatMode getMode() {
		return mode;
	}

	public void setMode(SatMode mode) {
		this.mode = mode;
	}

	public float getVbus() {
		return vbus;
	}

	public void setVbus(float vbus) {
		this.vbus = vbus;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public float getCharge() {
		return charge;
	}

	public void setCharge(float charge) {
		this.charge = charge;
	}

	public float getSatTemperature() {
		return satTemperature;
	}

	public void setSatTemperature(float satTemperature) {
		this.satTemperature = satTemperature;
	}

	public int getSwVersion() {
		return swVersion;
	}

	public void setSwVersion(int swVersion) {
		this.swVersion = swVersion;
	}

	public long getRestartCounter() {
		return restartCounter;
	}

	public void setRestartCounter(long restartCounter) {
		this.restartCounter = restartCounter;
	}

	public long getUptime() {
		return uptime;
	}

	public void setUptime(long uptime) {
		this.uptime = uptime;
	}

	public int getXrate() {
		return xrate;
	}

	public void setXrate(int xrate) {
		this.xrate = xrate;
	}

	public int getYrate() {
		return yrate;
	}

	public void setYrate(int yrate) {
		this.yrate = yrate;
	}

	public int getZrate() {
		return zrate;
	}

	public void setZrate(int zrate) {
		this.zrate = zrate;
	}

	public boolean isSoftwareSlotb() {
		return softwareSlotb;
	}

	public void setSoftwareSlotb(boolean softwareSlotb) {
		this.softwareSlotb = softwareSlotb;
	}

	public int getTxPower() {
		return txPower;
	}

	public void setTxPower(int txPower) {
		this.txPower = txPower;
	}

}
