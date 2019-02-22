package ru.r2cloud.jradio.au02;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.csp.Header;
import ru.r2cloud.jradio.util.StreamUtils;

public class Au02Beacon extends Beacon {

	private Header header;
	private long timestamp;
	private String callsign;
	private byte flags;
	private int batt_voltage;
	private float currentIn;
	private float currentOut;
	private float rail3Current;
	private float rail5Current;
	private byte comTemp;
	private byte epsTemp;
	private byte batTemp;

	@Override
	public void readBeacon(byte[] data) throws IOException {
		header = new Header(Arrays.copyOfRange(data, 0, Header.LENGTH));
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		int actualSkipped = dis.skipBytes(Header.LENGTH);
		if (actualSkipped < Header.LENGTH) {
			throw new EOFException();
		}
		timestamp = StreamUtils.readUnsignedInt(dis);
		byte[] callsignBuf = new byte[6];
		dis.readFully(callsignBuf);
		callsign = new String(callsignBuf, StandardCharsets.ISO_8859_1);
		flags = dis.readByte();
		batt_voltage = dis.readUnsignedByte() * 16 + 4420;
		currentIn = (float) (dis.readUnsignedByte() * 2700.0 / 255.0);
		currentOut = (float) (dis.readUnsignedByte() * 4000.0 / 255.0);
		rail3Current = (float) (dis.readUnsignedByte() * 5500.0 / 255.0);
		rail5Current = (float) (dis.readUnsignedByte() * 4500.0 / 255.0);
		comTemp = dis.readByte();
		epsTemp = dis.readByte();
		batTemp = dis.readByte();
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

	public int getBatt_voltage() {
		return batt_voltage;
	}

	public void setBatt_voltage(int batt_voltage) {
		this.batt_voltage = batt_voltage;
	}

	public float getCurrentIn() {
		return currentIn;
	}

	public void setCurrentIn(float currentIn) {
		this.currentIn = currentIn;
	}

	public float getCurrentOut() {
		return currentOut;
	}

	public void setCurrentOut(float currentOut) {
		this.currentOut = currentOut;
	}

	public float getRail3Current() {
		return rail3Current;
	}

	public void setRail3Current(float rail3Current) {
		this.rail3Current = rail3Current;
	}

	public float getRail5Current() {
		return rail5Current;
	}

	public void setRail5Current(float rail5Current) {
		this.rail5Current = rail5Current;
	}

	public byte getComTemp() {
		return comTemp;
	}

	public void setComTemp(byte comTemp) {
		this.comTemp = comTemp;
	}

	public byte getEpsTemp() {
		return epsTemp;
	}

	public void setEpsTemp(byte epsTemp) {
		this.epsTemp = epsTemp;
	}

	public byte getBatTemp() {
		return batTemp;
	}

	public void setBatTemp(byte batTemp) {
		this.batTemp = batTemp;
	}

}
