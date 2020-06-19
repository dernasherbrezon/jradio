package ru.r2cloud.jradio.eseo;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Type6 {

	private float triTmpX; // X-axis detector temperature
	private float triTmpY; // Y-axis detector temperature
	private float triTmpZ; // Z-axis detector temperature
	private float triTmpPsu; // Power Supply Unit temperature
	private float triTmpCpu; // Central Processing Unit temperature
	private float triTmpAdcX; // X-axis ADC-converter temperature
	private float triTmpAdcY; // Y-axis ADC-converter temperature
	private float triTmpAdcZ; // Z-axis ADC-converter temperature
	private int triUinput; // Input voltage
	private int triIinput; // Input current intensity
	private int tri60v; // Internal 60 V
	private int triNeg10v; // Internal -10V
	private int tri5v; // Internal 5V
	private int tri33v; // Internal 3.3V
	private int tri65v; // Internal 6.5V
	private int triNeg65v; // Internal -6.5V
	private int triMode; // Measurement mode
	private int triFreq; // Impulse generator frequency
	private int triError; // HK parameter warnings / errors
	private int eeprom; // EEPROM corruption
	private byte lmpTtpsu; // Temperature Telemetry
	private float lmpVtp12; // +12V Power Supply Voltage Telemetry
	private float lmpVtm12; // -12V Power Supply Voltage Telemetry
	private float lmpVtp5; // +5V Power Supply Voltage Telemetry
	private float lmpVtm5; // -5 Power Supply Voltage Telemetry
	private float lmpCtdig; // +3.3V Power Supply Current Telemetry
	private float lmpVtdig; // +3.3V Power Supply Voltage Telemetry
	private int lmpMem; // 8 bit that indicates the memory usage of the external flash memory
	private float lmpOfst; // Measures the offset voltage of the signal conditioning circuitry of the A/D converter.
	private int lmpSw; // 24bits that indicate the state of the LMP experiment including status information on hardware, strat-up configuration and actual status of the software.
	private int pcamMcurCurr; // MCU + SRAM current consumption
	private int pcamImgCurr; // Image sensor current consumption
	private float pcamMcuTemp; // MCU temperature
	private float pcamImgTemp; // Image sensor temperature
	private float pcamDcdcTemp; // DC-DC converter temperature
	private int scamMcuCurr; // MCU current consumption
	private int scamImgCurr; // Image sensor current consumption
	private int scamRamCurr; // SDRAM current consumption
	private float scamMcuTemp; // MCU temperature
	private float scamImgTemp; // Image sensor temperature
	private float scamSdr1Temp; // SDRAM1 temperature
	private float scamSdr2Temp; // SDRAM2 temperature
	private long amsObcPUp; // Uplink packet counter
	private long amsObcPUpDropped; // Failed uplink packet counter
	private long amsObcMemStat1; // RAM Read/Write/ECC Checks
	private long amsObcMemStat2; // FLASH Read/Write/ECC Checks
	private int amsEpsDcdcTemp; // EPS DC/DC Converter temperature
	private int amsVhfFmPaTemp; // FM power amplifier temperature
	private int amsVhfBpskPaTemp; // BPSK power amplifier temperature
	private int stxVol1; // DC/DC converter output voltage (RF Power Amplifier dc supply)
	private int stxVol2; // DC/DC converter output voltage (Digital board dc supply)
	private int stxCur1; // DC/DC converter output current (RF Power Amplifier dc supply)
	private int stxCur2; // DC/DC converter output current (Digital board dc supply)
	private float stxTemp1; // FPGA temperature
	private float stxTemp2; // Transceiver Chip temperature (RF modulator LIME RF circuit)
	private float stxTemp3; // DC/DC converter temperature (microwave Power Amplifier dc supply)
	private float stxTemp4; // DC/DC converter temperature (Digital supply)
	private HSTXStatus stxStat; // HSTX status condition, described below.
	private HSTXCommunicationCondition stxCom; // HSTX communication condition, described below.
	private HSTXMemoryCondition stxMem; // HSTX memory condition, described below.
	private int gpsCurrent3v3; // Current absorption on 3V3 power bus
	private int gpsCurrent5v; // Current absorption on 5V power bus
	private int gpsWeek; // GPS week
	private float gpsTemperature1; // PCB mounted temperature sensor value
	private float gpsTemperature2; // Chassis mounted temperature sensor value
	private int gpsFrendMVolt; // Main COTS front-end input voltage
	private int gpsFrendRVolt; // Redundant COTS front-end input voltage
	private long gpsSecondsOfWeek; // Seconds of the GPS week accurate to the millisecond
	private int adeInEstimatorOn; // Indicates which estimator is on
	private int adeInOmega; // Indicates which angular velocity estimate is used: 0 as computed by the ESEO AOCS, 1 as computed by ADE MEKF algorithm
	private float adeOprqQ1; // First element of the quaternion estimated from the filtered K matrix
	private float adeOprqQ2; // Second element of the quaternion estimated from the filtered K matrix
	private float adeOprqQ3; // Third element of the quaternion estimated from the filtered K matrix

	public Type6() {
		// do nothing
	}

	public Type6(DataInputStream source) throws IOException {
		LittleEndianDataInputStream dis = new LittleEndianDataInputStream(source);
		triTmpX = dis.readUnsignedByte() * 0.5f - 40.0f;
		triTmpY = dis.readUnsignedByte() * 0.5f - 40.0f;
		triTmpZ = dis.readUnsignedByte() * 0.5f - 40.0f;
		triTmpPsu = dis.readUnsignedByte() * 0.5f - 40.0f;
		triTmpCpu = dis.readUnsignedByte() * 0.5f - 40.0f;
		triTmpAdcX = dis.readUnsignedByte() * 0.5f - 40.0f;
		triTmpAdcY = dis.readUnsignedByte() * 0.5f - 40.0f;
		triTmpAdcZ = dis.readUnsignedByte() * 0.5f - 40.0f;
		triUinput = dis.readUnsignedByte() * 150;
		triIinput = dis.readUnsignedByte() * 2;
		tri60v = dis.readUnsignedByte() * 300;
		triNeg10v = dis.readUnsignedByte() * 30;
		tri5v = dis.readUnsignedByte() * 20;
		tri33v = dis.readUnsignedByte() * 100;
		tri65v = dis.readUnsignedByte() * 50;
		triNeg65v = dis.readUnsignedByte() * 50;
		triMode = dis.readUnsignedByte();
		triFreq = dis.readUnsignedByte();
		triError = dis.readUnsignedByte();
		eeprom = dis.readUnsignedByte();
		lmpTtpsu = dis.readByte();
		lmpVtp12 = dis.readUnsignedByte() * 0.078f;
		lmpVtm12 = dis.readUnsignedByte() * -0.078f;
		lmpVtp5 = dis.readUnsignedByte() * 0.029f;
		lmpVtm5 = dis.readUnsignedByte() * -0.028f;
		lmpCtdig = dis.readUnsignedByte() * 1.259f;
		lmpVtdig = dis.readUnsignedByte() * 0.020f;
		lmpMem = dis.readUnsignedByte() * 4096;
		lmpOfst = dis.readByte() * 4.88f;
		lmpSw = dis.readUnsigned3Bytes();
		pcamMcurCurr = dis.readUnsignedByte();
		pcamImgCurr = dis.readUnsignedByte();
		pcamMcuTemp = dis.readShort() * 0.1f;
		pcamImgTemp = dis.readShort() * 0.1f;
		pcamDcdcTemp = dis.readShort() * 0.1f;
		scamMcuCurr = dis.readUnsignedByte();
		scamImgCurr = dis.readUnsignedByte();
		scamRamCurr = dis.readUnsignedByte();
		scamMcuTemp = dis.readShort() * 0.1f;
		scamImgTemp = dis.readShort() * 0.1f;
		scamSdr1Temp = dis.readShort() * 0.1f;
		scamSdr2Temp = dis.readShort() * 0.1f;
		amsObcPUp = dis.readUnsignedInt();
		amsObcPUpDropped = dis.readUnsignedInt();
		amsObcMemStat1 = dis.readUnsignedInt();
		amsObcMemStat2 = dis.readUnsignedInt();
		amsEpsDcdcTemp = dis.readUnsignedByte();
		amsVhfFmPaTemp = dis.readUnsignedByte();
		amsVhfBpskPaTemp = dis.readUnsignedByte();
		stxVol1 = dis.readUnsignedByte() * 50;
		stxVol2 = dis.readUnsignedByte() * 20;
		stxCur1 = dis.readUnsignedByte() * 10;
		stxCur2 = dis.readUnsignedByte() * 10;
		stxTemp1 = dis.readUnsignedByte() * 0.5f + 230.0f;
		stxTemp2 = dis.readUnsignedByte() * 0.5f + 230.0f;
		stxTemp3 = dis.readUnsignedByte() * 0.5f + 230.0f;
		stxTemp4 = dis.readUnsignedByte() * 0.5f + 230.0f;
		stxStat = new HSTXStatus(dis);
		stxCom = new HSTXCommunicationCondition(dis);
		stxMem = new HSTXMemoryCondition(dis);
		gpsCurrent3v3 = dis.readUnsignedShort();
		gpsCurrent5v = dis.readUnsignedShort();
		gpsWeek = dis.readUnsignedShort();
		gpsTemperature1 = dis.readShort() * 0.1f;
		gpsTemperature2 = dis.readShort() * 0.1f;
		gpsFrendMVolt = dis.readUnsignedShort();
		gpsFrendRVolt = dis.readUnsignedShort();
		gpsSecondsOfWeek = dis.readUnsignedInt();
		adeInEstimatorOn = dis.readUnsignedByte();
		adeInOmega = dis.readUnsignedByte();
		adeOprqQ1 = dis.readFloat();
		adeOprqQ2 = dis.readFloat();
		adeOprqQ3 = dis.readFloat();
	}

	public float getTriTmpX() {
		return triTmpX;
	}

	public void setTriTmpX(float triTmpX) {
		this.triTmpX = triTmpX;
	}

	public float getTriTmpY() {
		return triTmpY;
	}

	public void setTriTmpY(float triTmpY) {
		this.triTmpY = triTmpY;
	}

	public float getTriTmpZ() {
		return triTmpZ;
	}

	public void setTriTmpZ(float triTmpZ) {
		this.triTmpZ = triTmpZ;
	}

	public float getTriTmpPsu() {
		return triTmpPsu;
	}

	public void setTriTmpPsu(float triTmpPsu) {
		this.triTmpPsu = triTmpPsu;
	}

	public float getTriTmpCpu() {
		return triTmpCpu;
	}

	public void setTriTmpCpu(float triTmpCpu) {
		this.triTmpCpu = triTmpCpu;
	}

	public float getTriTmpAdcX() {
		return triTmpAdcX;
	}

	public void setTriTmpAdcX(float triTmpAdcX) {
		this.triTmpAdcX = triTmpAdcX;
	}

	public float getTriTmpAdcY() {
		return triTmpAdcY;
	}

	public void setTriTmpAdcY(float triTmpAdcY) {
		this.triTmpAdcY = triTmpAdcY;
	}

	public float getTriTmpAdcZ() {
		return triTmpAdcZ;
	}

	public void setTriTmpAdcZ(float triTmpAdcZ) {
		this.triTmpAdcZ = triTmpAdcZ;
	}

	public int getTriUinput() {
		return triUinput;
	}

	public void setTriUinput(int triUinput) {
		this.triUinput = triUinput;
	}

	public int getTriIinput() {
		return triIinput;
	}

	public void setTriIinput(int triIinput) {
		this.triIinput = triIinput;
	}

	public int getTri60v() {
		return tri60v;
	}

	public void setTri60v(int tri60v) {
		this.tri60v = tri60v;
	}

	public int getTriNeg10v() {
		return triNeg10v;
	}

	public void setTriNeg10v(int triNeg10v) {
		this.triNeg10v = triNeg10v;
	}

	public int getTri5v() {
		return tri5v;
	}

	public void setTri5v(int tri5v) {
		this.tri5v = tri5v;
	}

	public int getTri33v() {
		return tri33v;
	}

	public void setTri33v(int tri33v) {
		this.tri33v = tri33v;
	}

	public int getTri65v() {
		return tri65v;
	}

	public void setTri65v(int tri65v) {
		this.tri65v = tri65v;
	}

	public int getTriNeg65v() {
		return triNeg65v;
	}

	public void setTriNeg65v(int triNeg65v) {
		this.triNeg65v = triNeg65v;
	}

	public int getTriMode() {
		return triMode;
	}

	public void setTriMode(int triMode) {
		this.triMode = triMode;
	}

	public int getTriFreq() {
		return triFreq;
	}

	public void setTriFreq(int triFreq) {
		this.triFreq = triFreq;
	}

	public int getTriError() {
		return triError;
	}

	public void setTriError(int triError) {
		this.triError = triError;
	}

	public int getEeprom() {
		return eeprom;
	}

	public void setEeprom(int eeprom) {
		this.eeprom = eeprom;
	}

	public byte getLmpTtpsu() {
		return lmpTtpsu;
	}

	public void setLmpTtpsu(byte lmpTtpsu) {
		this.lmpTtpsu = lmpTtpsu;
	}

	public float getLmpVtp12() {
		return lmpVtp12;
	}

	public void setLmpVtp12(float lmpVtp12) {
		this.lmpVtp12 = lmpVtp12;
	}

	public float getLmpVtm12() {
		return lmpVtm12;
	}

	public void setLmpVtm12(float lmpVtm12) {
		this.lmpVtm12 = lmpVtm12;
	}

	public float getLmpVtp5() {
		return lmpVtp5;
	}

	public void setLmpVtp5(float lmpVtp5) {
		this.lmpVtp5 = lmpVtp5;
	}

	public float getLmpVtm5() {
		return lmpVtm5;
	}

	public void setLmpVtm5(float lmpVtm5) {
		this.lmpVtm5 = lmpVtm5;
	}

	public float getLmpCtdig() {
		return lmpCtdig;
	}

	public void setLmpCtdig(float lmpCtdig) {
		this.lmpCtdig = lmpCtdig;
	}

	public float getLmpVtdig() {
		return lmpVtdig;
	}

	public void setLmpVtdig(float lmpVtdig) {
		this.lmpVtdig = lmpVtdig;
	}

	public int getLmpMem() {
		return lmpMem;
	}

	public void setLmpMem(int lmpMem) {
		this.lmpMem = lmpMem;
	}

	public float getLmpOfst() {
		return lmpOfst;
	}

	public void setLmpOfst(float lmpOfst) {
		this.lmpOfst = lmpOfst;
	}

	public int getLmpSw() {
		return lmpSw;
	}

	public void setLmpSw(int lmpSw) {
		this.lmpSw = lmpSw;
	}

	public int getPcamMcurCurr() {
		return pcamMcurCurr;
	}

	public void setPcamMcurCurr(int pcamMcurCurr) {
		this.pcamMcurCurr = pcamMcurCurr;
	}

	public int getPcamImgCurr() {
		return pcamImgCurr;
	}

	public void setPcamImgCurr(int pcamImgCurr) {
		this.pcamImgCurr = pcamImgCurr;
	}

	public float getPcamMcuTemp() {
		return pcamMcuTemp;
	}

	public void setPcamMcuTemp(float pcamMcuTemp) {
		this.pcamMcuTemp = pcamMcuTemp;
	}

	public float getPcamImgTemp() {
		return pcamImgTemp;
	}

	public void setPcamImgTemp(float pcamImgTemp) {
		this.pcamImgTemp = pcamImgTemp;
	}

	public float getPcamDcdcTemp() {
		return pcamDcdcTemp;
	}

	public void setPcamDcdcTemp(float pcamDcdcTemp) {
		this.pcamDcdcTemp = pcamDcdcTemp;
	}

	public int getScamMcuCurr() {
		return scamMcuCurr;
	}

	public void setScamMcuCurr(int scamMcuCurr) {
		this.scamMcuCurr = scamMcuCurr;
	}

	public int getScamImgCurr() {
		return scamImgCurr;
	}

	public void setScamImgCurr(int scamImgCurr) {
		this.scamImgCurr = scamImgCurr;
	}

	public int getScamRamCurr() {
		return scamRamCurr;
	}

	public void setScamRamCurr(int scamRamCurr) {
		this.scamRamCurr = scamRamCurr;
	}

	public float getScamMcuTemp() {
		return scamMcuTemp;
	}

	public void setScamMcuTemp(float scamMcuTemp) {
		this.scamMcuTemp = scamMcuTemp;
	}

	public float getScamImgTemp() {
		return scamImgTemp;
	}

	public void setScamImgTemp(float scamImgTemp) {
		this.scamImgTemp = scamImgTemp;
	}

	public float getScamSdr1Temp() {
		return scamSdr1Temp;
	}

	public void setScamSdr1Temp(float scamSdr1Temp) {
		this.scamSdr1Temp = scamSdr1Temp;
	}

	public float getScamSdr2Temp() {
		return scamSdr2Temp;
	}

	public void setScamSdr2Temp(float scamSdr2Temp) {
		this.scamSdr2Temp = scamSdr2Temp;
	}

	public long getAmsObcPUp() {
		return amsObcPUp;
	}

	public void setAmsObcPUp(long amsObcPUp) {
		this.amsObcPUp = amsObcPUp;
	}

	public long getAmsObcPUpDropped() {
		return amsObcPUpDropped;
	}

	public void setAmsObcPUpDropped(long amsObcPUpDropped) {
		this.amsObcPUpDropped = amsObcPUpDropped;
	}

	public long getAmsObcMemStat1() {
		return amsObcMemStat1;
	}

	public void setAmsObcMemStat1(long amsObcMemStat1) {
		this.amsObcMemStat1 = amsObcMemStat1;
	}

	public long getAmsObcMemStat2() {
		return amsObcMemStat2;
	}

	public void setAmsObcMemStat2(long amsObcMemStat2) {
		this.amsObcMemStat2 = amsObcMemStat2;
	}

	public int getAmsEpsDcdcTemp() {
		return amsEpsDcdcTemp;
	}

	public void setAmsEpsDcdcTemp(int amsEpsDcdcTemp) {
		this.amsEpsDcdcTemp = amsEpsDcdcTemp;
	}

	public int getAmsVhfFmPaTemp() {
		return amsVhfFmPaTemp;
	}

	public void setAmsVhfFmPaTemp(int amsVhfFmPaTemp) {
		this.amsVhfFmPaTemp = amsVhfFmPaTemp;
	}

	public int getAmsVhfBpskPaTemp() {
		return amsVhfBpskPaTemp;
	}

	public void setAmsVhfBpskPaTemp(int amsVhfBpskPaTemp) {
		this.amsVhfBpskPaTemp = amsVhfBpskPaTemp;
	}

	public int getStxVol1() {
		return stxVol1;
	}

	public void setStxVol1(int stxVol1) {
		this.stxVol1 = stxVol1;
	}

	public int getStxVol2() {
		return stxVol2;
	}

	public void setStxVol2(int stxVol2) {
		this.stxVol2 = stxVol2;
	}

	public int getStxCur1() {
		return stxCur1;
	}

	public void setStxCur1(int stxCur1) {
		this.stxCur1 = stxCur1;
	}

	public int getStxCur2() {
		return stxCur2;
	}

	public void setStxCur2(int stxCur2) {
		this.stxCur2 = stxCur2;
	}

	public float getStxTemp1() {
		return stxTemp1;
	}

	public void setStxTemp1(float stxTemp1) {
		this.stxTemp1 = stxTemp1;
	}

	public float getStxTemp2() {
		return stxTemp2;
	}

	public void setStxTemp2(float stxTemp2) {
		this.stxTemp2 = stxTemp2;
	}

	public float getStxTemp3() {
		return stxTemp3;
	}

	public void setStxTemp3(float stxTemp3) {
		this.stxTemp3 = stxTemp3;
	}

	public float getStxTemp4() {
		return stxTemp4;
	}

	public void setStxTemp4(float stxTemp4) {
		this.stxTemp4 = stxTemp4;
	}

	public HSTXStatus getStxStat() {
		return stxStat;
	}

	public void setStxStat(HSTXStatus stxStat) {
		this.stxStat = stxStat;
	}

	public HSTXCommunicationCondition getStxCom() {
		return stxCom;
	}

	public void setStxCom(HSTXCommunicationCondition stxCom) {
		this.stxCom = stxCom;
	}

	public HSTXMemoryCondition getStxMem() {
		return stxMem;
	}

	public void setStxMem(HSTXMemoryCondition stxMem) {
		this.stxMem = stxMem;
	}

	public int getGpsCurrent3v3() {
		return gpsCurrent3v3;
	}

	public void setGpsCurrent3v3(int gpsCurrent3v3) {
		this.gpsCurrent3v3 = gpsCurrent3v3;
	}

	public int getGpsCurrent5v() {
		return gpsCurrent5v;
	}

	public void setGpsCurrent5v(int gpsCurrent5v) {
		this.gpsCurrent5v = gpsCurrent5v;
	}

	public int getGpsWeek() {
		return gpsWeek;
	}

	public void setGpsWeek(int gpsWeek) {
		this.gpsWeek = gpsWeek;
	}

	public float getGpsTemperature1() {
		return gpsTemperature1;
	}

	public void setGpsTemperature1(float gpsTemperature1) {
		this.gpsTemperature1 = gpsTemperature1;
	}

	public float getGpsTemperature2() {
		return gpsTemperature2;
	}

	public void setGpsTemperature2(float gpsTemperature2) {
		this.gpsTemperature2 = gpsTemperature2;
	}

	public int getGpsFrendMVolt() {
		return gpsFrendMVolt;
	}

	public void setGpsFrendMVolt(int gpsFrendMVolt) {
		this.gpsFrendMVolt = gpsFrendMVolt;
	}

	public int getGpsFrendRVolt() {
		return gpsFrendRVolt;
	}

	public void setGpsFrendRVolt(int gpsFrendRVolt) {
		this.gpsFrendRVolt = gpsFrendRVolt;
	}

	public long getGpsSecondsOfWeek() {
		return gpsSecondsOfWeek;
	}

	public void setGpsSecondsOfWeek(long gpsSecondsOfWeek) {
		this.gpsSecondsOfWeek = gpsSecondsOfWeek;
	}

	public int getAdeInEstimatorOn() {
		return adeInEstimatorOn;
	}

	public void setAdeInEstimatorOn(int adeInEstimatorOn) {
		this.adeInEstimatorOn = adeInEstimatorOn;
	}

	public int getAdeInOmega() {
		return adeInOmega;
	}

	public void setAdeInOmega(int adeInOmega) {
		this.adeInOmega = adeInOmega;
	}

	public float getAdeOprqQ1() {
		return adeOprqQ1;
	}

	public void setAdeOprqQ1(float adeOprqQ1) {
		this.adeOprqQ1 = adeOprqQ1;
	}

	public float getAdeOprqQ2() {
		return adeOprqQ2;
	}

	public void setAdeOprqQ2(float adeOprqQ2) {
		this.adeOprqQ2 = adeOprqQ2;
	}

	public float getAdeOprqQ3() {
		return adeOprqQ3;
	}

	public void setAdeOprqQ3(float adeOprqQ3) {
		this.adeOprqQ3 = adeOprqQ3;
	}

}
