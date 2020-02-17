package ru.r2cloud.jradio.dstar1;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class PayloadData {

	// all fields are explicitly Objects
	// they can be null if payload was partially recovered
	// this could happen because mobitex blocks are processed independantly
	// and not all blocks can be recovered
	private Integer length;
	private Integer id;
	private Long time;
	private Long reboots;
	private Long rtcVal;
	private Float batteryChargeIn;
	private Float batteryChargeOut;
	private Float batteryVoltage;
	private Float supply5V;
	private Float supply3v3;
	private Float pcuTotalCurrent;
	private Float solarXP;
	private Float solarXM;
	private Float solarYP;
	private Float solarYM;
	private Float solarZP;
	private Float solarZM;
	private Float solarTotal;
	private Float vccOut0;
	private Float vccOut1;
	private Float vccOut2;
	private Float vccOut3;
	private Float vccOut4;
	private Float vccOut5;
	private Float vccOut6;
	private Float vccOut7;
	private Float ssTotalCurrent;
	private Float eePROM1Current;
	private Float eePROM2Current;
	private Float extADC1;
	private Float extADC2;
	private Float extADC3;
	private Float extADC4;
	private Float rtcCurrent;
	private Float chargerDCDC;
	private Float systemV;
	private Float obcCurrent;
	private Integer switches;
	private Short batteryTemperature;
	private Integer scheduledCommands;
	private Mode mode;
	private Short crc16;

	public PayloadData() {
		// do nothing
	}

	public PayloadData(byte[] data) {
		try {
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
		} catch (IOException e) {
			// not all blocks could be recovered
			// so payload might be partially read
		}
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public Long getReboots() {
		return reboots;
	}

	public void setReboots(long reboots) {
		this.reboots = reboots;
	}

	public Long getRtcVal() {
		return rtcVal;
	}

	public void setRtcVal(long rtcVal) {
		this.rtcVal = rtcVal;
	}

	public Float getBatteryChargeIn() {
		return batteryChargeIn;
	}

	public void setBatteryChargeIn(float batteryChargeIn) {
		this.batteryChargeIn = batteryChargeIn;
	}

	public Float getBatteryChargeOut() {
		return batteryChargeOut;
	}

	public void setBatteryChargeOut(float batteryChargeOut) {
		this.batteryChargeOut = batteryChargeOut;
	}

	public Float getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(float batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public Float getSupply5V() {
		return supply5V;
	}

	public void setSupply5V(float supply5v) {
		supply5V = supply5v;
	}

	public Float getSupply3v3() {
		return supply3v3;
	}

	public void setSupply3v3(float supply3v3) {
		this.supply3v3 = supply3v3;
	}

	public Float getPcuTotalCurrent() {
		return pcuTotalCurrent;
	}

	public void setPcuTotalCurrent(float pcuTotalCurrent) {
		this.pcuTotalCurrent = pcuTotalCurrent;
	}

	public Float getSolarXP() {
		return solarXP;
	}

	public void setSolarXP(float solarXP) {
		this.solarXP = solarXP;
	}

	public Float getSolarXM() {
		return solarXM;
	}

	public void setSolarXM(float solarXM) {
		this.solarXM = solarXM;
	}

	public Float getSolarYP() {
		return solarYP;
	}

	public void setSolarYP(float solarYP) {
		this.solarYP = solarYP;
	}

	public Float getSolarYM() {
		return solarYM;
	}

	public void setSolarYM(float solarYM) {
		this.solarYM = solarYM;
	}

	public Float getSolarZP() {
		return solarZP;
	}

	public void setSolarZP(float solarZP) {
		this.solarZP = solarZP;
	}

	public Float getSolarZM() {
		return solarZM;
	}

	public void setSolarZM(float solarZM) {
		this.solarZM = solarZM;
	}

	public Float getSolarTotal() {
		return solarTotal;
	}

	public void setSolarTotal(float solarTotal) {
		this.solarTotal = solarTotal;
	}

	public Float getVccOut0() {
		return vccOut0;
	}

	public void setVccOut0(float vccOut0) {
		this.vccOut0 = vccOut0;
	}

	public Float getVccOut1() {
		return vccOut1;
	}

	public void setVccOut1(float vccOut1) {
		this.vccOut1 = vccOut1;
	}

	public Float getVccOut2() {
		return vccOut2;
	}

	public void setVccOut2(float vccOut2) {
		this.vccOut2 = vccOut2;
	}

	public Float getVccOut3() {
		return vccOut3;
	}

	public void setVccOut3(float vccOut3) {
		this.vccOut3 = vccOut3;
	}

	public Float getVccOut4() {
		return vccOut4;
	}

	public void setVccOut4(float vccOut4) {
		this.vccOut4 = vccOut4;
	}

	public Float getVccOut5() {
		return vccOut5;
	}

	public void setVccOut5(float vccOut5) {
		this.vccOut5 = vccOut5;
	}

	public Float getVccOut6() {
		return vccOut6;
	}

	public void setVccOut6(float vccOut6) {
		this.vccOut6 = vccOut6;
	}

	public Float getVccOut7() {
		return vccOut7;
	}

	public void setVccOut7(float vccOut7) {
		this.vccOut7 = vccOut7;
	}

	public Float getSsTotalCurrent() {
		return ssTotalCurrent;
	}

	public void setSsTotalCurrent(float ssTotalCurrent) {
		this.ssTotalCurrent = ssTotalCurrent;
	}

	public Float getEePROM1Current() {
		return eePROM1Current;
	}

	public void setEePROM1Current(float eePROM1Current) {
		this.eePROM1Current = eePROM1Current;
	}

	public Float getEePROM2Current() {
		return eePROM2Current;
	}

	public void setEePROM2Current(float eePROM2Current) {
		this.eePROM2Current = eePROM2Current;
	}

	public Float getExtADC1() {
		return extADC1;
	}

	public void setExtADC1(float extADC1) {
		this.extADC1 = extADC1;
	}

	public Float getExtADC2() {
		return extADC2;
	}

	public void setExtADC2(float extADC2) {
		this.extADC2 = extADC2;
	}

	public Float getExtADC3() {
		return extADC3;
	}

	public void setExtADC3(float extADC3) {
		this.extADC3 = extADC3;
	}

	public Float getExtADC4() {
		return extADC4;
	}

	public void setExtADC4(float extADC4) {
		this.extADC4 = extADC4;
	}

	public Float getRtcCurrent() {
		return rtcCurrent;
	}

	public void setRtcCurrent(float rtcCurrent) {
		this.rtcCurrent = rtcCurrent;
	}

	public Float getChargerDCDC() {
		return chargerDCDC;
	}

	public void setChargerDCDC(float chargerDCDC) {
		this.chargerDCDC = chargerDCDC;
	}

	public Float getSystemV() {
		return systemV;
	}

	public void setSystemV(float systemV) {
		this.systemV = systemV;
	}

	public Float getObcCurrent() {
		return obcCurrent;
	}

	public void setObcCurrent(float obcCurrent) {
		this.obcCurrent = obcCurrent;
	}

	public Integer getSwitches() {
		return switches;
	}

	public void setSwitches(int switches) {
		this.switches = switches;
	}

	public Short getBatteryTemperature() {
		return batteryTemperature;
	}

	public void setBatteryTemperature(Short batteryTemperature) {
		this.batteryTemperature = batteryTemperature;
	}

	public Integer getScheduledCommands() {
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

	public Short getCrc16() {
		return crc16;
	}

	public void setCrc16(Short crc16) {
		this.crc16 = crc16;
	}

}
