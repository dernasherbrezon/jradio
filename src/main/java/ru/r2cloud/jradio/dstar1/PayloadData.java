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
	private long RTC_val;
	private float batteryChargeIn;
	private float batteryChargeOut;
	private float batteryVoltage;
	private float supply5V;
	private float supply3v3;
	private float PCUTotalCurrent;
	private float solarXP;
	private float solarXM;
	private float solarYP;
	private float solarYM;
	private float solarZP;
	private float solarZM;
	private float solarTotal;
	private float VCCout0;
	private float VCCout1;
	private float VCCout2;
	private float VCCout3;
	private float VCCout4;
	private float VCCout5;
	private float VCCout6;
	private float VCCout7;
	private float SSTotalCurrent;
	private float EEPROM1Current;
	private float EEPROM2Current;
	private float ExtADC1;
	private float ExtADC2;
	private float ExtADC3;
	private float ExtADC4;
	private float RTCCurrent;
	private float ChargerDCDC;
	private float SystemV;
	private float OBCCurrent;
	private int switches;
	private int BatteryTemperature;
	private int scheduledCommands;
	private Mode mode;
	private int crc16;

	public PayloadData(byte[] data) throws IOException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		length = dis.readUnsignedByte();
		id = dis.readUnsignedByte();
		time = LittleEndianDataInputStream.readUnsignedInt(dis);
		reboots = LittleEndianDataInputStream.readUnsignedInt(dis);
		RTC_val = LittleEndianDataInputStream.readUnsignedInt(dis);
		batteryChargeIn = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 0.05f);
		batteryChargeOut = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 0.033f);
		batteryVoltage = dis.readUnsignedShort() * 2.5f / 4096 * ((124 + 27.4f) / 27.4f);
		supply5V = dis.readUnsignedShort() * (2.5f / 4096) * ((30.1f + 18.2f) / 18.2f);
		supply3v3 = dis.readUnsignedShort() * (2.5f / 4096) * ((18.2f + 18.2f) / 18.2f);
		PCUTotalCurrent = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 1);
		solarXP = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 0.1f);
		solarXM = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 0.1f);
		solarYP = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 0.1f);
		solarYM = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 0.1f);
		solarZP = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 0.1f);
		solarZM = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 0.1f);
		solarTotal = dis.readUnsignedShort() * (2.5f / 4096) * ((30.1f + 18.2f) / 18.2f);
		VCCout0 = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 0.1f);
		VCCout1 = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 0.1f);
		VCCout2 = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 0.1f);
		VCCout3 = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 0.1f);
		VCCout4 = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 0.05f);
		VCCout5 = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 0.05f);
		VCCout6 = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 0.05f);
		VCCout7 = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 0.05f);
		SSTotalCurrent = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 0.1f);
		EEPROM1Current = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 0.2f);
		EEPROM2Current = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 1);
		ExtADC1 = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 1);
		ExtADC2 = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 1);
		ExtADC3 = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 1);
		ExtADC4 = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 1);
		RTCCurrent = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 1);
		ChargerDCDC = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 0.1f);
		SystemV = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 0.1f);
		OBCCurrent = dis.readUnsignedShort() * 2.5f / (4096 * 20 * 0.1f);
		switches = (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | (dis.readUnsignedByte());
		dis.skipBytes(1);
		BatteryTemperature = LittleEndianDataInputStream.readShort(dis);
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

	public long getRTC_val() {
		return RTC_val;
	}

	public void setRTC_val(long rTC_val) {
		RTC_val = rTC_val;
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

	public float getPCUTotalCurrent() {
		return PCUTotalCurrent;
	}

	public void setPCUTotalCurrent(float pCUTotalCurrent) {
		PCUTotalCurrent = pCUTotalCurrent;
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

	public float getVCCout0() {
		return VCCout0;
	}

	public void setVCCout0(float vCCout0) {
		VCCout0 = vCCout0;
	}

	public float getVCCout1() {
		return VCCout1;
	}

	public void setVCCout1(float vCCout1) {
		VCCout1 = vCCout1;
	}

	public float getVCCout2() {
		return VCCout2;
	}

	public void setVCCout2(float vCCout2) {
		VCCout2 = vCCout2;
	}

	public float getVCCout3() {
		return VCCout3;
	}

	public void setVCCout3(float vCCout3) {
		VCCout3 = vCCout3;
	}

	public float getVCCout4() {
		return VCCout4;
	}

	public void setVCCout4(float vCCout4) {
		VCCout4 = vCCout4;
	}

	public float getVCCout5() {
		return VCCout5;
	}

	public void setVCCout5(float vCCout5) {
		VCCout5 = vCCout5;
	}

	public float getVCCout6() {
		return VCCout6;
	}

	public void setVCCout6(float vCCout6) {
		VCCout6 = vCCout6;
	}

	public float getVCCout7() {
		return VCCout7;
	}

	public void setVCCout7(float vCCout7) {
		VCCout7 = vCCout7;
	}

	public float getSSTotalCurrent() {
		return SSTotalCurrent;
	}

	public void setSSTotalCurrent(float sSTotalCurrent) {
		SSTotalCurrent = sSTotalCurrent;
	}

	public float getEEPROM1Current() {
		return EEPROM1Current;
	}

	public void setEEPROM1Current(float eEPROM1Current) {
		EEPROM1Current = eEPROM1Current;
	}

	public float getEEPROM2Current() {
		return EEPROM2Current;
	}

	public void setEEPROM2Current(float eEPROM2Current) {
		EEPROM2Current = eEPROM2Current;
	}

	public float getExtADC1() {
		return ExtADC1;
	}

	public void setExtADC1(float extADC1) {
		ExtADC1 = extADC1;
	}

	public float getExtADC2() {
		return ExtADC2;
	}

	public void setExtADC2(float extADC2) {
		ExtADC2 = extADC2;
	}

	public float getExtADC3() {
		return ExtADC3;
	}

	public void setExtADC3(float extADC3) {
		ExtADC3 = extADC3;
	}

	public float getExtADC4() {
		return ExtADC4;
	}

	public void setExtADC4(float extADC4) {
		ExtADC4 = extADC4;
	}

	public float getRTCCurrent() {
		return RTCCurrent;
	}

	public void setRTCCurrent(float rTCCurrent) {
		RTCCurrent = rTCCurrent;
	}

	public float getChargerDCDC() {
		return ChargerDCDC;
	}

	public void setChargerDCDC(float chargerDCDC) {
		ChargerDCDC = chargerDCDC;
	}

	public float getSystemV() {
		return SystemV;
	}

	public void setSystemV(float systemV) {
		SystemV = systemV;
	}

	public float getOBCCurrent() {
		return OBCCurrent;
	}

	public void setOBCCurrent(float oBCCurrent) {
		OBCCurrent = oBCCurrent;
	}

	public int getSwitches() {
		return switches;
	}

	public void setSwitches(int switches) {
		this.switches = switches;
	}

	public int getBatteryTemperature() {
		return BatteryTemperature;
	}

	public void setBatteryTemperature(int batteryTemperature) {
		BatteryTemperature = batteryTemperature;
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
