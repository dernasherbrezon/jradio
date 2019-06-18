package ru.r2cloud.jradio.dstar1;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class PayloadData {

	private int length;
	private int id;
	private long time;
	private long reboots;
	private long rtcVal;
	private float batteryChargeIn;
	private float batteryChargeOut;
	private float batteryVoltage;
	private float supply5V;
	private float supply3v3;
	private float pcuTotalCurrent;
	private float solarXP;
	private float solarXM;
	private float solarYP;
	private float solarYM;
	private float solarZP;
	private float solarZM;
	private float solarTotal;
	private float vccOut0;
	private float vccOut1;
	private float vccOut2;
	private float vccOut3;
	private float vccOut4;
	private float vccOut5;
	private float vccOut6;
	private float vccOut7;
	private float ssTotalCurrent;
	private float eePROM1Current;
	private float eePROM2Current;
	private float extADC1;
	private float extADC2;
	private float extADC3;
	private float extADC4;
	private float rtcCurrent;
	private float chargerDCDC;
	private float systemV;
	private float obcCurrent;
	private int switches;
	private int batteryTemperature;
	private int scheduledCommands;
	private Mode mode;
	private int crc16;

	public PayloadData(byte[] data) throws IOException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		length = dis.readUnsignedByte();
		id = dis.readUnsignedByte();
		time = LittleEndianDataInputStream.readUnsignedInt(dis);
		reboots = LittleEndianDataInputStream.readUnsignedInt(dis);
		rtcVal = LittleEndianDataInputStream.readUnsignedInt(dis);
		batteryChargeIn = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 0.05f);
		batteryChargeOut = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 0.033f);
		batteryVoltage = dis.readUnsignedShort() * 2.5f / 4096 * ((124 + 27.4f) / 27.4f);
		supply5V = dis.readUnsignedShort() * (2.5f / 4096) * ((30.1f + 18.2f) / 18.2f);
		supply3v3 = dis.readUnsignedShort() * (2.5f / 4096) * ((18.2f + 18.2f) / 18.2f);
		pcuTotalCurrent = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 1);
		solarXP = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 0.1f);
		solarXM = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 0.1f);
		solarYP = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 0.1f);
		solarYM = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 0.1f);
		solarZP = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 0.1f);
		solarZM = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 0.1f);
		solarTotal = dis.readUnsignedShort() * (2.5f / 4096) * ((30.1f + 18.2f) / 18.2f);
		vccOut0 = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 0.1f);
		vccOut1 = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 0.1f);
		vccOut2 = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 0.1f);
		vccOut3 = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 0.1f);
		vccOut4 = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 0.05f);
		vccOut5 = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 0.05f);
		vccOut6 = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 0.05f);
		vccOut7 = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 0.05f);
		ssTotalCurrent = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 0.1f);
		eePROM1Current = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 0.2f);
		eePROM2Current = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 1);
		extADC1 = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 1);
		extADC2 = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 1);
		extADC3 = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 1);
		extADC4 = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 1);
		rtcCurrent = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 1);
		chargerDCDC = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 0.1f);
		systemV = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 0.1f);
		obcCurrent = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 0.1f);
		switches = (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | (dis.readUnsignedByte());
		dis.skipBytes(1);
		batteryTemperature = LittleEndianDataInputStream.readShort(dis);
		scheduledCommands = dis.readUnsignedByte();
		dis.skipBytes(10);
		mode = Mode.valueOfCode(dis.readUnsignedByte());
		dis.skipBytes(10);
		crc16 = LittleEndianDataInputStream.readShort(dis);
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public long getReboots() {
		return reboots;
	}

	public void setReboots(long reboots) {
		this.reboots = reboots;
	}

	public long getRtcVal() {
		return rtcVal;
	}

	public void setRtcVal(long rtcVal) {
		this.rtcVal = rtcVal;
	}

	public float getBatteryChargeIn() {
		return batteryChargeIn;
	}

	public void setBatteryChargeIn(float batteryChargeIn) {
		this.batteryChargeIn = batteryChargeIn;
	}

	public float getBatteryChargeOut() {
		return batteryChargeOut;
	}

	public void setBatteryChargeOut(float batteryChargeOut) {
		this.batteryChargeOut = batteryChargeOut;
	}

	public float getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(float batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public float getSupply5V() {
		return supply5V;
	}

	public void setSupply5V(float supply5v) {
		supply5V = supply5v;
	}

	public float getSupply3v3() {
		return supply3v3;
	}

	public void setSupply3v3(float supply3v3) {
		this.supply3v3 = supply3v3;
	}

	public float getPcuTotalCurrent() {
		return pcuTotalCurrent;
	}

	public void setPcuTotalCurrent(float pcuTotalCurrent) {
		this.pcuTotalCurrent = pcuTotalCurrent;
	}

	public float getSolarXP() {
		return solarXP;
	}

	public void setSolarXP(float solarXP) {
		this.solarXP = solarXP;
	}

	public float getSolarXM() {
		return solarXM;
	}

	public void setSolarXM(float solarXM) {
		this.solarXM = solarXM;
	}

	public float getSolarYP() {
		return solarYP;
	}

	public void setSolarYP(float solarYP) {
		this.solarYP = solarYP;
	}

	public float getSolarYM() {
		return solarYM;
	}

	public void setSolarYM(float solarYM) {
		this.solarYM = solarYM;
	}

	public float getSolarZP() {
		return solarZP;
	}

	public void setSolarZP(float solarZP) {
		this.solarZP = solarZP;
	}

	public float getSolarZM() {
		return solarZM;
	}

	public void setSolarZM(float solarZM) {
		this.solarZM = solarZM;
	}

	public float getSolarTotal() {
		return solarTotal;
	}

	public void setSolarTotal(float solarTotal) {
		this.solarTotal = solarTotal;
	}

	public float getVccOut0() {
		return vccOut0;
	}

	public void setVccOut0(float vccOut0) {
		this.vccOut0 = vccOut0;
	}

	public float getVccOut1() {
		return vccOut1;
	}

	public void setVccOut1(float vccOut1) {
		this.vccOut1 = vccOut1;
	}

	public float getVccOut2() {
		return vccOut2;
	}

	public void setVccOut2(float vccOut2) {
		this.vccOut2 = vccOut2;
	}

	public float getVccOut3() {
		return vccOut3;
	}

	public void setVccOut3(float vccOut3) {
		this.vccOut3 = vccOut3;
	}

	public float getVccOut4() {
		return vccOut4;
	}

	public void setVccOut4(float vccOut4) {
		this.vccOut4 = vccOut4;
	}

	public float getVccOut5() {
		return vccOut5;
	}

	public void setVccOut5(float vccOut5) {
		this.vccOut5 = vccOut5;
	}

	public float getVccOut6() {
		return vccOut6;
	}

	public void setVccOut6(float vccOut6) {
		this.vccOut6 = vccOut6;
	}

	public float getVccOut7() {
		return vccOut7;
	}

	public void setVccOut7(float vccOut7) {
		this.vccOut7 = vccOut7;
	}

	public float getSsTotalCurrent() {
		return ssTotalCurrent;
	}

	public void setSsTotalCurrent(float ssTotalCurrent) {
		this.ssTotalCurrent = ssTotalCurrent;
	}

	public float getEePROM1Current() {
		return eePROM1Current;
	}

	public void setEePROM1Current(float eePROM1Current) {
		this.eePROM1Current = eePROM1Current;
	}

	public float getEePROM2Current() {
		return eePROM2Current;
	}

	public void setEePROM2Current(float eePROM2Current) {
		this.eePROM2Current = eePROM2Current;
	}

	public float getExtADC1() {
		return extADC1;
	}

	public void setExtADC1(float extADC1) {
		this.extADC1 = extADC1;
	}

	public float getExtADC2() {
		return extADC2;
	}

	public void setExtADC2(float extADC2) {
		this.extADC2 = extADC2;
	}

	public float getExtADC3() {
		return extADC3;
	}

	public void setExtADC3(float extADC3) {
		this.extADC3 = extADC3;
	}

	public float getExtADC4() {
		return extADC4;
	}

	public void setExtADC4(float extADC4) {
		this.extADC4 = extADC4;
	}

	public float getRtcCurrent() {
		return rtcCurrent;
	}

	public void setRtcCurrent(float rtcCurrent) {
		this.rtcCurrent = rtcCurrent;
	}

	public float getChargerDCDC() {
		return chargerDCDC;
	}

	public void setChargerDCDC(float chargerDCDC) {
		this.chargerDCDC = chargerDCDC;
	}

	public float getSystemV() {
		return systemV;
	}

	public void setSystemV(float systemV) {
		this.systemV = systemV;
	}

	public float getObcCurrent() {
		return obcCurrent;
	}

	public void setObcCurrent(float obcCurrent) {
		this.obcCurrent = obcCurrent;
	}

	public int getSwitches() {
		return switches;
	}

	public void setSwitches(int switches) {
		this.switches = switches;
	}

	public int getBatteryTemperature() {
		return batteryTemperature;
	}

	public void setBatteryTemperature(int batteryTemperature) {
		this.batteryTemperature = batteryTemperature;
	}

	public int getScheduledCommands() {
		return scheduledCommands;
	}

	public void setScheduledCommands(int scheduledCommands) {
		this.scheduledCommands = scheduledCommands;
	}

	public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}

	public int getCrc16() {
		return crc16;
	}

	public void setCrc16(int crc16) {
		this.crc16 = crc16;
	}

}
