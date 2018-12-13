package ru.r2cloud.jradio.au02;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import ru.r2cloud.jradio.Externalizable;
import ru.r2cloud.jradio.csp.Header;

public class Au02Beacon implements Externalizable {

	private Header header;
	private long timestamp;
	private String callsign;
	private byte flags;
	private float batt_voltage;
	private float current_in;
	private float current_out;
	private float rail3_current;
	private float rail5_current;
	private byte com_temp;
	private byte eps_temp;
	private byte bat_temp;

	private long beginSample;
	private long beginMillis;
	private byte[] rawData;

	@Override
	public void readExternal(byte[] data) throws IOException {
		this.rawData = data;
		header = new Header(Arrays.copyOfRange(data, 0, Header.LENGTH));
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		int actualSkipped = dis.skipBytes(Header.LENGTH);
		if (actualSkipped < Header.LENGTH) {
			throw new EOFException();
		}
		timestamp = (dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
		byte[] callsignBuf = new byte[6];
		dis.readFully(callsignBuf);
		callsign = new String(callsignBuf, StandardCharsets.ISO_8859_1);
		flags = dis.readByte();
		batt_voltage = dis.readUnsignedByte() * 16 + 4420;
		current_in = (float) (dis.readUnsignedByte() * 2700.0 / 255.0);
		current_out = (float) (dis.readUnsignedByte() * 4000.0 / 255.0);
		rail3_current = (float) (dis.readUnsignedByte() * 5500.0 / 255.0);
		rail5_current = (float) (dis.readUnsignedByte() * 4500.0 / 255.0);
		com_temp = dis.readByte();
		eps_temp = dis.readByte();
		bat_temp = dis.readByte();
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getCallsign() {
		return callsign;
	}

	public void setCallsign(String callsign) {
		this.callsign = callsign;
	}

	public byte getFlags() {
		return flags;
	}

	public void setFlags(byte flags) {
		this.flags = flags;
	}

	public float getBatt_voltage() {
		return batt_voltage;
	}

	public void setBatt_voltage(float batt_voltage) {
		this.batt_voltage = batt_voltage;
	}

	public float getCurrent_in() {
		return current_in;
	}

	public void setCurrent_in(float current_in) {
		this.current_in = current_in;
	}

	public float getCurrent_out() {
		return current_out;
	}

	public void setCurrent_out(float current_out) {
		this.current_out = current_out;
	}

	public float getRail3_current() {
		return rail3_current;
	}

	public void setRail3_current(float rail3_current) {
		this.rail3_current = rail3_current;
	}

	public float getRail5_current() {
		return rail5_current;
	}

	public void setRail5_current(float rail5_current) {
		this.rail5_current = rail5_current;
	}

	public byte getCom_temp() {
		return com_temp;
	}

	public void setCom_temp(byte com_temp) {
		this.com_temp = com_temp;
	}

	public byte getEps_temp() {
		return eps_temp;
	}

	public void setEps_temp(byte eps_temp) {
		this.eps_temp = eps_temp;
	}

	public byte getBat_temp() {
		return bat_temp;
	}

	public void setBat_temp(byte bat_temp) {
		this.bat_temp = bat_temp;
	}

	public long getBeginSample() {
		return beginSample;
	}

	public void setBeginSample(long beginSample) {
		this.beginSample = beginSample;
	}

	public long getBeginMillis() {
		return beginMillis;
	}

	public void setBeginMillis(long beginMillis) {
		this.beginMillis = beginMillis;
	}

	public byte[] getRawData() {
		return rawData;
	}

	public void setRawData(byte[] rawData) {
		this.rawData = rawData;
	}

}
