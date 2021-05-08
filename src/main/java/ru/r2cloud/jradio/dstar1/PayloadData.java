package ru.r2cloud.jradio.dstar1;

import java.io.IOException;

import ru.r2cloud.jradio.util.GapDataInputStream;

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

	public PayloadData(GapDataInputStream dis) {
		try {
			length = dis.readUnsignedByte();
			id = dis.readUnsignedByte();
			time = dis.readLittleEndianUnsignedInt();
			reboots = dis.readLittleEndianUnsignedInt();
			rtcVal = dis.readLittleEndianUnsignedInt();
			batteryChargeIn = read005Adjusted(dis);
			Integer unsignedShort = dis.readUnsignedShort();
			if (unsignedShort != null) {
				batteryChargeOut = unsignedShort * 2.5f / (4096 * 20 * 0.033f);
			}
			unsignedShort = dis.readUnsignedShort();
			if (unsignedShort != null) {
				batteryVoltage = unsignedShort * 2.5f / 4096 * ((124 + 27.4f) / 27.4f);
			}
			unsignedShort = dis.readUnsignedShort();
			if (unsignedShort != null) {
				supply5V = unsignedShort * (2.5f / 4096) * ((30.1f + 18.2f) / 18.2f);
			}
			unsignedShort = dis.readUnsignedShort();
			if (unsignedShort != null) {
				supply3v3 = unsignedShort * (2.5f / 4096) * ((18.2f + 18.2f) / 18.2f);
			}
			pcuTotalCurrent = read1Adjusted(dis);
			solarXP = read01Adjusted(dis);
			solarXM = read01Adjusted(dis);
			solarYP = read01Adjusted(dis);
			solarYM = read01Adjusted(dis);
			solarZP = read01Adjusted(dis);
			solarZM = read01Adjusted(dis);
			unsignedShort = dis.readUnsignedShort();
			if (unsignedShort != null) {
				solarTotal = unsignedShort * (2.5f / 4096) * ((30.1f + 18.2f) / 18.2f);
			}
			vccOut0 = read01Adjusted(dis);
			vccOut1 = read01Adjusted(dis);
			vccOut2 = read01Adjusted(dis);
			vccOut3 = read01Adjusted(dis);
			vccOut4 = read005Adjusted(dis);
			vccOut5 = read005Adjusted(dis);
			vccOut6 = read005Adjusted(dis);
			vccOut7 = read005Adjusted(dis);
			ssTotalCurrent = read01Adjusted(dis);
			unsignedShort = dis.readUnsignedShort();
			if (unsignedShort != null) {
				eePROM1Current = unsignedShort * 2.5f / (4096 * 20 * 0.2f);
			}
			eePROM2Current = read1Adjusted(dis);
			extADC1 = read1Adjusted(dis);
			extADC2 = read1Adjusted(dis);
			extADC3 = read1Adjusted(dis);
			extADC4 = read1Adjusted(dis);
			rtcCurrent = read1Adjusted(dis);
			chargerDCDC = read01Adjusted(dis);
			systemV = read01Adjusted(dis);
			obcCurrent = read01Adjusted(dis);

			Integer b1 = dis.readUnsignedByte();
			Integer b2 = dis.readUnsignedByte();
			Integer b3 = dis.readUnsignedByte();
			if (b1 != null && b2 != null && b3 != null) {
				switches = (b1 << 16) | (b2 << 8) | (b3);
			}
			dis.skipBytes(1);
			batteryTemperature = dis.readLittleEndianShort();
			scheduledCommands = dis.readUnsignedByte();
			dis.skipBytes(10);
			Integer modeInt = dis.readUnsignedByte();
			if (modeInt != null) {
				mode = Mode.valueOfCode(modeInt);
			}
			dis.skipBytes(10);
			crc16 = dis.readLittleEndianShort();
		} catch (IOException e) {
			// not all blocks could be recovered
			// so payload might be partially read
		}
	}

	private static Float read1Adjusted(GapDataInputStream dis) throws IOException {
		Integer value = dis.readUnsignedShort();
		if (value == null) {
			return null;
		}
		return value * 2.5f / (4096 * 20 * 1);
	}

	private static Float read005Adjusted(GapDataInputStream dis) throws IOException {
		Integer value = dis.readUnsignedShort();
		if (value == null) {
			return null;
		}
		return value * 2.5f / (4096 * 20 * 0.05f);
	}

	private static Float read01Adjusted(GapDataInputStream dis) throws IOException {
		Integer value = dis.readUnsignedShort();
		if (value == null) {
			return null;
		}
		return value * 2.5f / (4096 * 20 * 0.1f);
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public Long getReboots() {
		return reboots;
	}

	public void setReboots(Long reboots) {
		this.reboots = reboots;
	}

	public Long getRtcVal() {
		return rtcVal;
	}

	public void setRtcVal(Long rtcVal) {
		this.rtcVal = rtcVal;
	}

	public Float getBatteryChargeIn() {
		return batteryChargeIn;
	}

	public void setBatteryChargeIn(Float batteryChargeIn) {
		this.batteryChargeIn = batteryChargeIn;
	}

	public Float getBatteryChargeOut() {
		return batteryChargeOut;
	}

	public void setBatteryChargeOut(Float batteryChargeOut) {
		this.batteryChargeOut = batteryChargeOut;
	}

	public Float getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(Float batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public Float getSupply5V() {
		return supply5V;
	}

	public void setSupply5V(Float supply5v) {
		supply5V = supply5v;
	}

	public Float getSupply3v3() {
		return supply3v3;
	}

	public void setSupply3v3(Float supply3v3) {
		this.supply3v3 = supply3v3;
	}

	public Float getPcuTotalCurrent() {
		return pcuTotalCurrent;
	}

	public void setPcuTotalCurrent(Float pcuTotalCurrent) {
		this.pcuTotalCurrent = pcuTotalCurrent;
	}

	public Float getSolarXP() {
		return solarXP;
	}

	public void setSolarXP(Float solarXP) {
		this.solarXP = solarXP;
	}

	public Float getSolarXM() {
		return solarXM;
	}

	public void setSolarXM(Float solarXM) {
		this.solarXM = solarXM;
	}

	public Float getSolarYP() {
		return solarYP;
	}

	public void setSolarYP(Float solarYP) {
		this.solarYP = solarYP;
	}

	public Float getSolarYM() {
		return solarYM;
	}

	public void setSolarYM(Float solarYM) {
		this.solarYM = solarYM;
	}

	public Float getSolarZP() {
		return solarZP;
	}

	public void setSolarZP(Float solarZP) {
		this.solarZP = solarZP;
	}

	public Float getSolarZM() {
		return solarZM;
	}

	public void setSolarZM(Float solarZM) {
		this.solarZM = solarZM;
	}

	public Float getSolarTotal() {
		return solarTotal;
	}

	public void setSolarTotal(Float solarTotal) {
		this.solarTotal = solarTotal;
	}

	public Float getVccOut0() {
		return vccOut0;
	}

	public void setVccOut0(Float vccOut0) {
		this.vccOut0 = vccOut0;
	}

	public Float getVccOut1() {
		return vccOut1;
	}

	public void setVccOut1(Float vccOut1) {
		this.vccOut1 = vccOut1;
	}

	public Float getVccOut2() {
		return vccOut2;
	}

	public void setVccOut2(Float vccOut2) {
		this.vccOut2 = vccOut2;
	}

	public Float getVccOut3() {
		return vccOut3;
	}

	public void setVccOut3(Float vccOut3) {
		this.vccOut3 = vccOut3;
	}

	public Float getVccOut4() {
		return vccOut4;
	}

	public void setVccOut4(Float vccOut4) {
		this.vccOut4 = vccOut4;
	}

	public Float getVccOut5() {
		return vccOut5;
	}

	public void setVccOut5(Float vccOut5) {
		this.vccOut5 = vccOut5;
	}

	public Float getVccOut6() {
		return vccOut6;
	}

	public void setVccOut6(Float vccOut6) {
		this.vccOut6 = vccOut6;
	}

	public Float getVccOut7() {
		return vccOut7;
	}

	public void setVccOut7(Float vccOut7) {
		this.vccOut7 = vccOut7;
	}

	public Float getSsTotalCurrent() {
		return ssTotalCurrent;
	}

	public void setSsTotalCurrent(Float ssTotalCurrent) {
		this.ssTotalCurrent = ssTotalCurrent;
	}

	public Float getEePROM1Current() {
		return eePROM1Current;
	}

	public void setEePROM1Current(Float eePROM1Current) {
		this.eePROM1Current = eePROM1Current;
	}

	public Float getEePROM2Current() {
		return eePROM2Current;
	}

	public void setEePROM2Current(Float eePROM2Current) {
		this.eePROM2Current = eePROM2Current;
	}

	public Float getExtADC1() {
		return extADC1;
	}

	public void setExtADC1(Float extADC1) {
		this.extADC1 = extADC1;
	}

	public Float getExtADC2() {
		return extADC2;
	}

	public void setExtADC2(Float extADC2) {
		this.extADC2 = extADC2;
	}

	public Float getExtADC3() {
		return extADC3;
	}

	public void setExtADC3(Float extADC3) {
		this.extADC3 = extADC3;
	}

	public Float getExtADC4() {
		return extADC4;
	}

	public void setExtADC4(Float extADC4) {
		this.extADC4 = extADC4;
	}

	public Float getRtcCurrent() {
		return rtcCurrent;
	}

	public void setRtcCurrent(Float rtcCurrent) {
		this.rtcCurrent = rtcCurrent;
	}

	public Float getChargerDCDC() {
		return chargerDCDC;
	}

	public void setChargerDCDC(Float chargerDCDC) {
		this.chargerDCDC = chargerDCDC;
	}

	public Float getSystemV() {
		return systemV;
	}

	public void setSystemV(Float systemV) {
		this.systemV = systemV;
	}

	public Float getObcCurrent() {
		return obcCurrent;
	}

	public void setObcCurrent(Float obcCurrent) {
		this.obcCurrent = obcCurrent;
	}

	public Integer getSwitches() {
		return switches;
	}

	public void setSwitches(Integer switches) {
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

	public void setScheduledCommands(Integer scheduledCommands) {
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
