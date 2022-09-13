package ru.r2cloud.jradio.vzlusat;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import ru.r2cloud.jradio.util.StreamUtils;

public class Vzlusat2Telemetry {

	private String callsign;
	private long obcTimestamp;
	private long obcBootCount;
	private long obcResetCause;
	private int epsVbatt;
	private int epsCursun;
	private int epsCursys;
	private int epsTempBat;
	private float radioTempPa;
	private long radioTotTxCount;
	private long radioTotRxCount;

	public Vzlusat2Telemetry() {
		// do nothing
	}

	public Vzlusat2Telemetry(DataInputStream dis) throws IOException {
		byte[] callsignBytes = new byte[8];
		dis.readFully(callsignBytes);
		callsign = new String(callsignBytes, StandardCharsets.UTF_8);
		obcTimestamp = StreamUtils.readUnsignedInt(dis);
		obcBootCount = StreamUtils.readUnsignedInt(dis);
		obcResetCause = StreamUtils.readUnsignedInt(dis);
		epsVbatt = dis.readUnsignedShort();
		epsCursun = dis.readUnsignedShort();
		epsCursys = dis.readUnsignedShort();
		epsTempBat = dis.readShort();
		radioTempPa = dis.readShort() * 0.1f;
		radioTotTxCount = StreamUtils.readUnsignedInt(dis);
		radioTotRxCount = StreamUtils.readUnsignedInt(dis);
	}

	public String getCallsign() {
		return callsign;
	}

	public void setCallsign(String callsign) {
		this.callsign = callsign;
	}

	public long getObcTimestamp() {
		return obcTimestamp;
	}

	public void setObcTimestamp(long obcTimestamp) {
		this.obcTimestamp = obcTimestamp;
	}

	public long getObcBootCount() {
		return obcBootCount;
	}

	public void setObcBootCount(long obcBootCount) {
		this.obcBootCount = obcBootCount;
	}

	public long getObcResetCause() {
		return obcResetCause;
	}

	public void setObcResetCause(long obcResetCause) {
		this.obcResetCause = obcResetCause;
	}

	public int getEpsVbatt() {
		return epsVbatt;
	}

	public void setEpsVbatt(int epsVbatt) {
		this.epsVbatt = epsVbatt;
	}

	public int getEpsCursun() {
		return epsCursun;
	}

	public void setEpsCursun(int epsCursun) {
		this.epsCursun = epsCursun;
	}

	public int getEpsCursys() {
		return epsCursys;
	}

	public void setEpsCursys(int epsCursys) {
		this.epsCursys = epsCursys;
	}

	public int getEpsTempBat() {
		return epsTempBat;
	}

	public void setEpsTempBat(int epsTempBat) {
		this.epsTempBat = epsTempBat;
	}

	public float getRadioTempPa() {
		return radioTempPa;
	}

	public void setRadioTempPa(float radioTempPa) {
		this.radioTempPa = radioTempPa;
	}

	public long getRadioTotTxCount() {
		return radioTotTxCount;
	}

	public void setRadioTotTxCount(long radioTotTxCount) {
		this.radioTotTxCount = radioTotTxCount;
	}

	public long getRadioTotRxCount() {
		return radioTotRxCount;
	}

	public void setRadioTotRxCount(long radioTotRxCount) {
		this.radioTotRxCount = radioTotRxCount;
	}

}
