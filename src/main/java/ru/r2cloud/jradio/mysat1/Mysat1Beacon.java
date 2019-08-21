package ru.r2cloud.jradio.mysat1;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.ax25.Header;
import ru.r2cloud.jradio.ax25.UFrameControlType;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.StreamUtils;

public class Mysat1Beacon extends Beacon {

	private Header header;
	private String callsign;

	private int obcMode;
	private long obcResetCounter;
	private long obcUptime;
	private int gyroNorm;
	private long epsCounterBoot;
	private int epsLastBootCause;
	private int epsBatteryMode;
	private long timestamp;
	private float obcTemp;
	private float obcDaughterBoardTemp;
	private float epsBatteryTemp;
	private float epsBoardTemp;
	private float antsTemp;
	private float trxvuTemp;
	private float adcsTemp;
	private float obc3v3Voltage;
	private float obc5v0Voltage;
	private float trxvuVoltage;
	private float epsBattVoltage;
	private float obc5v0Current;
	private float epsTotalPvCurrent;
	private float epsTotalSystemCurrent;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		header = new Header(dis);
		if (!header.getuControlType().equals(UFrameControlType.UI)) {
			return;
		}
		byte[] callsignBytes = new byte[5];
		dis.readFully(callsignBytes);
		callsign = new String(callsignBytes, StandardCharsets.ISO_8859_1);

		obcMode = dis.readUnsignedByte();
		obcResetCounter = StreamUtils.readUnsignedInt(dis);
		obcUptime = StreamUtils.readUnsignedInt(dis);
		gyroNorm = dis.readUnsignedByte();
		epsCounterBoot = StreamUtils.readUnsignedInt(dis);
		epsLastBootCause = dis.readUnsignedByte();
		epsBatteryMode = dis.readUnsignedByte();
		timestamp = StreamUtils.readUnsignedInt(dis);
		obcTemp = dis.readUnsignedByte() - 128;
		obcDaughterBoardTemp = dis.readUnsignedByte() - 128.0f;
		epsBatteryTemp = dis.readUnsignedByte() - 128.0f;
		epsBoardTemp = dis.readUnsignedByte() - 128.0f;
		antsTemp = dis.readUnsignedByte() - 128.0f;
		trxvuTemp = dis.readUnsignedByte() - 128.0f;
		adcsTemp = dis.readUnsignedByte() - 128.0f;
		obc3v3Voltage = dis.readUnsignedByte() / 10.0f;
		obc5v0Voltage = dis.readUnsignedByte() / 10.0f;
		trxvuVoltage = dis.readUnsignedByte() / 10.0f;
		epsBattVoltage = dis.readUnsignedByte() / 10.0f;
		obc5v0Current = dis.readUnsignedShort() / 1000.0f;
		epsTotalPvCurrent = dis.readUnsignedShort() / 1000.0f;
		epsTotalSystemCurrent = dis.readUnsignedShort() / 1000.0f;
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public String getCallsign() {
		return callsign;
	}

	public void setCallsign(String callsign) {
		this.callsign = callsign;
	}

	public int getObcMode() {
		return obcMode;
	}

	public void setObcMode(int obcMode) {
		this.obcMode = obcMode;
	}

	public long getObcResetCounter() {
		return obcResetCounter;
	}

	public void setObcResetCounter(long obcResetCounter) {
		this.obcResetCounter = obcResetCounter;
	}

	public long getObcUptime() {
		return obcUptime;
	}

	public void setObcUptime(long obcUptime) {
		this.obcUptime = obcUptime;
	}

	public int getGyroNorm() {
		return gyroNorm;
	}

	public void setGyroNorm(int gyroNorm) {
		this.gyroNorm = gyroNorm;
	}

	public long getEpsCounterBoot() {
		return epsCounterBoot;
	}

	public void setEpsCounterBoot(long epsCounterBoot) {
		this.epsCounterBoot = epsCounterBoot;
	}

	public int getEpsLastBootCause() {
		return epsLastBootCause;
	}

	public void setEpsLastBootCause(int epsLastBootCause) {
		this.epsLastBootCause = epsLastBootCause;
	}

	public int getEpsBatteryMode() {
		return epsBatteryMode;
	}

	public void setEpsBatteryMode(int epsBatteryMode) {
		this.epsBatteryMode = epsBatteryMode;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public float getObcTemp() {
		return obcTemp;
	}

	public void setObcTemp(float obcTemp) {
		this.obcTemp = obcTemp;
	}

	public float getObcDaughterBoardTemp() {
		return obcDaughterBoardTemp;
	}

	public void setObcDaughterBoardTemp(float obcDaughterBoardTemp) {
		this.obcDaughterBoardTemp = obcDaughterBoardTemp;
	}

	public float getEpsBatteryTemp() {
		return epsBatteryTemp;
	}

	public void setEpsBatteryTemp(float epsBatteryTemp) {
		this.epsBatteryTemp = epsBatteryTemp;
	}

	public float getEpsBoardTemp() {
		return epsBoardTemp;
	}

	public void setEpsBoardTemp(float epsBoardTemp) {
		this.epsBoardTemp = epsBoardTemp;
	}

	public float getAntsTemp() {
		return antsTemp;
	}

	public void setAntsTemp(float antsTemp) {
		this.antsTemp = antsTemp;
	}

	public float getTrxvuTemp() {
		return trxvuTemp;
	}

	public void setTrxvuTemp(float trxvuTemp) {
		this.trxvuTemp = trxvuTemp;
	}

	public float getAdcsTemp() {
		return adcsTemp;
	}

	public void setAdcsTemp(float adcsTemp) {
		this.adcsTemp = adcsTemp;
	}

	public float getObc3v3Voltage() {
		return obc3v3Voltage;
	}

	public void setObc3v3Voltage(float obc3v3Voltage) {
		this.obc3v3Voltage = obc3v3Voltage;
	}

	public float getObc5v0Voltage() {
		return obc5v0Voltage;
	}

	public void setObc5v0Voltage(float obc5v0Voltage) {
		this.obc5v0Voltage = obc5v0Voltage;
	}

	public float getTrxvuVoltage() {
		return trxvuVoltage;
	}

	public void setTrxvuVoltage(float trxvuVoltage) {
		this.trxvuVoltage = trxvuVoltage;
	}

	public float getEpsBattVoltage() {
		return epsBattVoltage;
	}

	public void setEpsBattVoltage(float epsBattVoltage) {
		this.epsBattVoltage = epsBattVoltage;
	}

	public float getObc5v0Current() {
		return obc5v0Current;
	}

	public void setObc5v0Current(float obc5v0Current) {
		this.obc5v0Current = obc5v0Current;
	}

	public float getEpsTotalPvCurrent() {
		return epsTotalPvCurrent;
	}

	public void setEpsTotalPvCurrent(float epsTotalPvCurrent) {
		this.epsTotalPvCurrent = epsTotalPvCurrent;
	}

	public float getEpsTotalSystemCurrent() {
		return epsTotalSystemCurrent;
	}

	public void setEpsTotalSystemCurrent(float epsTotalSystemCurrent) {
		this.epsTotalSystemCurrent = epsTotalSystemCurrent;
	}

}
