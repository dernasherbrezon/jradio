package ru.r2cloud.jradio.meznsat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class MeznsatTelemetry {

	private String callsign;
	private SatelliteMode mode;
	private int dayOfMonth;
	private int dayOfWeek;
	private int hour;
	private int minute;
	private int sec;
	private long obcResetCount;
	private short obcTemp;
	private int vbatt;
	private int sysCurrent;
	private short battTemp;
	private short extBatt1Temp;
	private short extBatt2Temp;
	private int epsReboots;
	private float receiverCurrent;
	private float transmitterCurrent;
	private float paTemp;
	private float paCurrent;
	private int adcsRunMode;
	private float estimatedXAngularRate;
	private float estimatedYAngularRate;
	private float estimatedZAngularRate;
	private int estimatedQ1;
	private int estimatedQ2;
	private int estimatedQ3;
	private float antTemp;
	private float minusZTemp;
	private float plusZTemp;
	private float minusYTemp;
	private float plusYTemp;
	private float minusXTemp;
	private float plusXTemp;

	public MeznsatTelemetry() {
		// do nothing
	}

	public MeznsatTelemetry(LittleEndianDataInputStream ldis) throws IOException {
		byte[] callsignBytes = new byte[7];
		ldis.readFully(callsignBytes);
		callsign = new String(callsignBytes, StandardCharsets.US_ASCII);
		mode = SatelliteMode.valueOfCode(ldis.readUnsignedByte());

		dayOfMonth = ldis.readUnsignedByte();
		dayOfWeek = ldis.readUnsignedByte();
		hour = ldis.readUnsignedByte();
		minute = ldis.readUnsignedByte();
		sec = ldis.readUnsignedByte();
		obcResetCount = ldis.readUnsignedInt();
		obcTemp = ldis.readShort();
		vbatt = ldis.readUnsignedShort();
		sysCurrent = ldis.readUnsignedShort();
		battTemp = ldis.readShort();
		extBatt1Temp = ldis.readShort();
		extBatt2Temp = ldis.readShort();
		epsReboots = ldis.readUnsignedShort();
		receiverCurrent = ldis.readUnsignedShort() * 0.16643964f;
		transmitterCurrent = ldis.readUnsignedShort() * 0.16643964f;
		paTemp = (ldis.readShort() * -0.07669f) + 195.6037f;
		paCurrent = ldis.readUnsignedShort() * 0.16643964f;
		adcsRunMode = ldis.readUnsignedByte();
		estimatedXAngularRate = ldis.readShort() * 0.01f;
		estimatedYAngularRate = ldis.readShort() * 0.01f;
		estimatedZAngularRate = ldis.readShort() * 0.01f;
		estimatedQ1 = ldis.readUnsignedShort();
		estimatedQ2 = ldis.readUnsignedShort();
		estimatedQ3 = ldis.readUnsignedShort();
		antTemp = (ldis.readShort() * -0.2922f) + 190.65f;
		minusZTemp = ldis.readInt() / 1024.0f;
		plusZTemp = ldis.readInt() / 1024.0f;
		minusYTemp = ldis.readInt() / 1024.0f;
		plusYTemp = ldis.readInt() / 1024.0f;
		minusXTemp = ldis.readInt() / 1024.0f;
		plusXTemp = ldis.readInt() / 1024.0f;
	}

	public String getCallsign() {
		return callsign;
	}

	public void setCallsign(String callsign) {
		this.callsign = callsign;
	}

	public SatelliteMode getMode() {
		return mode;
	}

	public void setMode(SatelliteMode mode) {
		this.mode = mode;
	}

	public int getDayOfMonth() {
		return dayOfMonth;
	}

	public void setDayOfMonth(int dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}

	public int getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(int dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getSec() {
		return sec;
	}

	public void setSec(int sec) {
		this.sec = sec;
	}

	public long getObcResetCount() {
		return obcResetCount;
	}

	public void setObcResetCount(long obcResetCount) {
		this.obcResetCount = obcResetCount;
	}

	public short getObcTemp() {
		return obcTemp;
	}

	public void setObcTemp(short obcTemp) {
		this.obcTemp = obcTemp;
	}

	public int getVbatt() {
		return vbatt;
	}

	public void setVbatt(int vbatt) {
		this.vbatt = vbatt;
	}

	public int getSysCurrent() {
		return sysCurrent;
	}

	public void setSysCurrent(int sysCurrent) {
		this.sysCurrent = sysCurrent;
	}

	public short getBattTemp() {
		return battTemp;
	}

	public void setBattTemp(short battTemp) {
		this.battTemp = battTemp;
	}

	public short getExtBatt1Temp() {
		return extBatt1Temp;
	}

	public void setExtBatt1Temp(short extBatt1Temp) {
		this.extBatt1Temp = extBatt1Temp;
	}

	public short getExtBatt2Temp() {
		return extBatt2Temp;
	}

	public void setExtBatt2Temp(short extBatt2Temp) {
		this.extBatt2Temp = extBatt2Temp;
	}

	public int getEpsReboots() {
		return epsReboots;
	}

	public void setEpsReboots(int epsReboots) {
		this.epsReboots = epsReboots;
	}

	public float getReceiverCurrent() {
		return receiverCurrent;
	}

	public void setReceiverCurrent(float receiverCurrent) {
		this.receiverCurrent = receiverCurrent;
	}

	public float getTransmitterCurrent() {
		return transmitterCurrent;
	}

	public void setTransmitterCurrent(float transmitterCurrent) {
		this.transmitterCurrent = transmitterCurrent;
	}

	public float getPaTemp() {
		return paTemp;
	}

	public void setPaTemp(float paTemp) {
		this.paTemp = paTemp;
	}

	public float getPaCurrent() {
		return paCurrent;
	}

	public void setPaCurrent(float paCurrent) {
		this.paCurrent = paCurrent;
	}

	public int getAdcsRunMode() {
		return adcsRunMode;
	}

	public void setAdcsRunMode(int adcsRunMode) {
		this.adcsRunMode = adcsRunMode;
	}

	public float getEstimatedXAngularRate() {
		return estimatedXAngularRate;
	}

	public void setEstimatedXAngularRate(float estimatedXAngularRate) {
		this.estimatedXAngularRate = estimatedXAngularRate;
	}

	public float getEstimatedYAngularRate() {
		return estimatedYAngularRate;
	}

	public void setEstimatedYAngularRate(float estimatedYAngularRate) {
		this.estimatedYAngularRate = estimatedYAngularRate;
	}

	public float getEstimatedZAngularRate() {
		return estimatedZAngularRate;
	}

	public void setEstimatedZAngularRate(float estimatedZAngularRate) {
		this.estimatedZAngularRate = estimatedZAngularRate;
	}

	public int getEstimatedQ1() {
		return estimatedQ1;
	}

	public void setEstimatedQ1(int estimatedQ1) {
		this.estimatedQ1 = estimatedQ1;
	}

	public int getEstimatedQ2() {
		return estimatedQ2;
	}

	public void setEstimatedQ2(int estimatedQ2) {
		this.estimatedQ2 = estimatedQ2;
	}

	public int getEstimatedQ3() {
		return estimatedQ3;
	}

	public void setEstimatedQ3(int estimatedQ3) {
		this.estimatedQ3 = estimatedQ3;
	}

	public float getAntTemp() {
		return antTemp;
	}

	public void setAntTemp(float antTemp) {
		this.antTemp = antTemp;
	}

	public float getMinusZTemp() {
		return minusZTemp;
	}

	public void setMinusZTemp(float minusZTemp) {
		this.minusZTemp = minusZTemp;
	}

	public float getPlusZTemp() {
		return plusZTemp;
	}

	public void setPlusZTemp(float plusZTemp) {
		this.plusZTemp = plusZTemp;
	}

	public float getMinusYTemp() {
		return minusYTemp;
	}

	public void setMinusYTemp(float minusYTemp) {
		this.minusYTemp = minusYTemp;
	}

	public float getPlusYTemp() {
		return plusYTemp;
	}

	public void setPlusYTemp(float plusYTemp) {
		this.plusYTemp = plusYTemp;
	}

	public float getMinusXTemp() {
		return minusXTemp;
	}

	public void setMinusXTemp(float minusXTemp) {
		this.minusXTemp = minusXTemp;
	}

	public float getPlusXTemp() {
		return plusXTemp;
	}

	public void setPlusXTemp(float plusXTemp) {
		this.plusXTemp = plusXTemp;
	}

}
