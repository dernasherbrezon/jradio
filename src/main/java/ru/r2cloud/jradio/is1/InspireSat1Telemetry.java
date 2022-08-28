package ru.r2cloud.jradio.is1;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class InspireSat1Telemetry {

	private int cmdRecvCount;
	private int cmdFailCount;
	private int cmdSuccCount;
	private CmdOpCode cmdSuccOp;
	private CmdOpCode cmdFailOp;
	private CmdFailCode cmdFailCode;
	private PowerStatus pwrStatus;
	private long sdReadMisc;
	private long sdReadScic;
	private long sdReadScid;
	private long sdReadAdcs;
	private long sdReadBeacon;
	private long sdReadLog;
	private long sdWriteMisc;
	private long sdWriteScic;
	private long sdWriteScid;
	private long sdWriteAdcs;
	private long sdWriteBeacon;
	private long sdWriteLog;
	private long cmdLossTimer;
	private AliveFlags aliveFlags;
	private long cipComstat;
	private float cipTemp1;
	private float cipTemp2;
	private float cipTemp3;
	private UhfTemp uhfTemp;
	private UhfConfig uhfConfig;
	private float sbandPaCurr;
	private float sbandPaVolt;
	private float sbandRfPwr;
	private float sbandPaTemp;
	private float sbandTopTemp;
	private float sbandBottomTemp;
	private int adcsCmdAcpt;
	private int adcsCmdFail;
	private long adcsTime;
	private AdcsInfo adcsInfo;
	private float adcsStarTemp;
	private float adcsWheelTemp1;
	private float adcsWheelTemp2;
	private float adcsWheelTemp3;
	private float adcsDigiBusVolt;
	private float adcsSunVec1;
	private float adcsSunVec2;
	private float adcsSunVec3;
	private float adcsWheelSp1;
	private float adcsWheelSp2;
	private float adcsWheelSp3;
	private float adcsBodyRt1;
	private float adcsBodyRt2;
	private float adcsBodyRt3;
	private long daxssTimeSec;
	private int daxssCmdOp;
	private int daxssCmdSucc;
	private int daxssCmdFail;
	private int daxssCdhEnables;
	private float daxssCdhTemp;
	private long daxssSpsRate;
	private int daxssSpsX;
	private int daxssSpsY;
	private int daxssSlowCount;
	private float batFg1;
	private float daxssCurr;
	private float daxssVolt;
	private float cdhCurr;
	private float cdhVolt;
	private float sbandCurr;
	private float sbandVolt;
	private float uhfCurr;
	private float uhfVolt;
	private float heaterCurr;
	private float heaterVolt;
	private float sp2Curr;
	private float sp2Volt;
	private float sp1Curr;
	private float sp1Volt;
	private float sp0Curr;
	private float sp0Volt;
	private int batVcell;
	private float gps12v2Curr;
	private float gps12v2Volt;
	private float gps12v1Curr;
	private float gps12v1Volt;
	private float batCurr;
	private float batVolt;
	private float adcsCurr;
	private float adcsVolt;
	private float current3p3;
	private float voltage3p3;
	private float cipCurr;
	private float cipVolt;
	private float obcTemp;
	private float epsTemp;
	private float intTemp;
	private float sp0Temp;
	private float bat0Temp;
	private float sp1Temp;
	private float bat1Temp;
	private float sp2Temp;
	private float batFg3;
	private float bat0TempConv;
	private float bat1TempConv;
	private SystemMode mode;

	public InspireSat1Telemetry() {
		// do nothing
	}

	public InspireSat1Telemetry(LittleEndianDataInputStream dis) throws IOException {
		cmdRecvCount = dis.readUnsignedByte();
		cmdFailCount = dis.readUnsignedByte();
		cmdSuccCount = dis.readUnsignedByte();
		cmdSuccOp = CmdOpCode.valueOfCode(dis.readUnsignedByte());
		cmdFailOp = CmdOpCode.valueOfCode(dis.readUnsignedByte());
		cmdFailCode = CmdFailCode.valueOfCode(dis.readUnsignedByte());
		pwrStatus = new PowerStatus(dis);
		sdReadMisc = dis.readUnsignedInt();
		sdReadScic = dis.readUnsignedInt();
		sdReadScid = dis.readUnsignedInt();
		sdReadAdcs = dis.readUnsignedInt();
		sdReadBeacon = dis.readUnsignedInt();
		sdReadLog = dis.readUnsignedInt();
		sdWriteMisc = dis.readUnsignedInt();
		sdWriteScic = dis.readUnsignedInt();
		sdWriteScid = dis.readUnsignedInt();
		sdWriteAdcs = dis.readUnsignedInt();
		sdWriteBeacon = dis.readUnsignedInt();
		sdWriteLog = dis.readUnsignedInt();
		cmdLossTimer = dis.readUnsignedInt();
		aliveFlags = new AliveFlags(dis);
		cipComstat = dis.readUnsignedInt();
		cipTemp1 = 0.007813f * dis.readShort();
		cipTemp2 = 0.007813f * dis.readShort();
		cipTemp3 = 0.007813f * dis.readShort();
		uhfTemp = new UhfTemp(dis);
		uhfConfig = new UhfConfig(dis);
		sbandPaCurr = 4e-05f * dis.readUnsignedShort();
		sbandPaVolt = 0.004f * dis.readUnsignedShort();
		sbandRfPwr = 0.001139f * dis.readUnsignedShort();
		sbandPaTemp = -50.0f + 0.073242f * dis.readUnsignedShort();
		sbandTopTemp = 0.0625f * dis.readUnsignedShort();
		sbandBottomTemp = 0.0625f * dis.readUnsignedShort();
		adcsCmdAcpt = dis.readUnsignedByte();
		adcsCmdFail = dis.readUnsignedByte();
		adcsTime = dis.readUnsignedInt();
		adcsInfo = new AdcsInfo(dis);
		adcsStarTemp = 0.8f * dis.readByte();
		adcsWheelTemp1 = 0.005f * dis.readShort();
		adcsWheelTemp2 = 0.005f * dis.readShort();
		adcsWheelTemp3 = 0.005f * dis.readShort();
		adcsDigiBusVolt = 0.00125f * dis.readUnsignedShort();
		adcsSunVec1 = 0.0001f * dis.readShort();
		adcsSunVec2 = 0.0001f * dis.readShort();
		adcsSunVec3 = 0.0001f * dis.readShort();
		adcsWheelSp1 = 0.4f * dis.readShort();
		adcsWheelSp2 = 0.4f * dis.readShort();
		adcsWheelSp3 = 0.4f * dis.readShort();
		adcsBodyRt1 = 5.0e-9f * dis.readInt();
		adcsBodyRt2 = 5.0e-9f * dis.readInt();
		adcsBodyRt3 = 5.0e-9f * dis.readInt();
		dis.skipBytes(12);
		daxssTimeSec = dis.readUnsignedInt();
		daxssCmdOp = dis.readUnsignedByte();
		daxssCmdSucc = dis.readUnsignedByte();
		daxssCmdFail = dis.readUnsignedByte();
		daxssCdhEnables = dis.readUnsignedShort();
		daxssCdhTemp = 0.003906f * dis.readShort();
		daxssSpsRate = dis.readUnsignedInt();
		daxssSpsX = dis.readUnsignedShort();
		daxssSpsY = dis.readUnsignedShort();
		daxssSlowCount = dis.readUnsignedShort();
		batFg1 = 0.003906f * dis.readUnsignedShort();
		daxssCurr = 0.0005f * dis.readUnsignedShort();
		daxssVolt = 0.001f * dis.readUnsignedShort();
		cdhCurr = 0.0005f * dis.readUnsignedShort();
		cdhVolt = 0.001f * dis.readUnsignedShort();
		sbandCurr = 0.0005f * dis.readUnsignedShort();
		sbandVolt = 0.001f * dis.readUnsignedShort();
		uhfCurr = 0.0005f * dis.readUnsignedShort();
		uhfVolt = 0.001f * dis.readUnsignedShort();
		heaterCurr = 0.0005f * dis.readUnsignedShort();
		heaterVolt = 0.001f * dis.readUnsignedShort();
		sp2Curr = 0.0005f * dis.readUnsignedShort();
		sp2Volt = 0.001f * dis.readUnsignedShort();
		sp1Curr = 0.0005f * dis.readUnsignedShort();
		sp1Volt = 0.001f * dis.readUnsignedShort();
		sp0Curr = 0.00125f * dis.readUnsignedShort();
		sp0Volt = 0.00125f * dis.readUnsignedShort();
		batVcell = dis.readUnsignedShort();
		gps12v2Curr = 0.0005f * dis.readUnsignedShort();
		gps12v2Volt = 0.001f * dis.readUnsignedShort();
		gps12v1Curr = 0.0005f * dis.readUnsignedShort();
		gps12v1Volt = 0.001f * dis.readUnsignedShort();
		batCurr = 0.0005f * dis.readUnsignedShort();
		batVolt = 0.001f * dis.readUnsignedShort();
		adcsCurr = 0.0005f * dis.readUnsignedShort();
		adcsVolt = 0.001f * dis.readUnsignedShort();
		current3p3 = 0.0005f * dis.readUnsignedShort();
		voltage3p3 = 0.001f * dis.readUnsignedShort();
		cipCurr = 0.0005f * dis.readUnsignedShort();
		cipVolt = 0.001f * dis.readUnsignedShort();
		obcTemp = readTemperature(dis);
		epsTemp = readTemperature(dis);
		intTemp = readTemperature(dis);
		sp0Temp = readTemperature(dis);
		bat0Temp = readBatteryTemperature(dis);
		sp1Temp = readTemperature(dis);
		bat1Temp = readBatteryTemperature(dis);
		sp2Temp = readTemperature(dis);
		batFg3 = 0.003906f * dis.readUnsignedShort();
		bat0TempConv = dis.readFloat();
		bat1TempConv = dis.readFloat();
		mode = SystemMode.valueOfCode(dis.readUnsignedByte());
	}

	private static float readTemperature(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedShort();
		return 91.394f + -0.0894932f * raw + 3.55e-05f * raw * raw + -6.26e-9f * raw * raw * raw + 1.89e-13f * raw * raw * raw * raw;
	}

	private static float readBatteryTemperature(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedShort();
		return -259.74f + 1.471182747f * raw + 3.59e-4f * raw * raw + 8.77e-8f * raw * raw * raw + 2.14e-11f * raw * raw * raw * raw;
	}

	public int getCmdRecvCount() {
		return cmdRecvCount;
	}

	public void setCmdRecvCount(int cmdRecvCount) {
		this.cmdRecvCount = cmdRecvCount;
	}

	public int getCmdFailCount() {
		return cmdFailCount;
	}

	public void setCmdFailCount(int cmdFailCount) {
		this.cmdFailCount = cmdFailCount;
	}

	public int getCmdSuccCount() {
		return cmdSuccCount;
	}

	public void setCmdSuccCount(int cmdSuccCount) {
		this.cmdSuccCount = cmdSuccCount;
	}

	public CmdOpCode getCmdSuccOp() {
		return cmdSuccOp;
	}

	public void setCmdSuccOp(CmdOpCode cmdSuccOp) {
		this.cmdSuccOp = cmdSuccOp;
	}

	public CmdOpCode getCmdFailOp() {
		return cmdFailOp;
	}

	public void setCmdFailOp(CmdOpCode cmdFailOp) {
		this.cmdFailOp = cmdFailOp;
	}

	public CmdFailCode getCmdFailCode() {
		return cmdFailCode;
	}

	public void setCmdFailCode(CmdFailCode cmdFailCode) {
		this.cmdFailCode = cmdFailCode;
	}

	public PowerStatus getPwrStatus() {
		return pwrStatus;
	}

	public void setPwrStatus(PowerStatus pwrStatus) {
		this.pwrStatus = pwrStatus;
	}

	public long getSdReadMisc() {
		return sdReadMisc;
	}

	public void setSdReadMisc(long sdReadMisc) {
		this.sdReadMisc = sdReadMisc;
	}

	public long getSdReadScic() {
		return sdReadScic;
	}

	public void setSdReadScic(long sdReadScic) {
		this.sdReadScic = sdReadScic;
	}

	public long getSdReadScid() {
		return sdReadScid;
	}

	public void setSdReadScid(long sdReadScid) {
		this.sdReadScid = sdReadScid;
	}

	public long getSdReadAdcs() {
		return sdReadAdcs;
	}

	public void setSdReadAdcs(long sdReadAdcs) {
		this.sdReadAdcs = sdReadAdcs;
	}

	public long getSdReadBeacon() {
		return sdReadBeacon;
	}

	public void setSdReadBeacon(long sdReadBeacon) {
		this.sdReadBeacon = sdReadBeacon;
	}

	public long getSdReadLog() {
		return sdReadLog;
	}

	public void setSdReadLog(long sdReadLog) {
		this.sdReadLog = sdReadLog;
	}

	public long getSdWriteMisc() {
		return sdWriteMisc;
	}

	public void setSdWriteMisc(long sdWriteMisc) {
		this.sdWriteMisc = sdWriteMisc;
	}

	public long getSdWriteScic() {
		return sdWriteScic;
	}

	public void setSdWriteScic(long sdWriteScic) {
		this.sdWriteScic = sdWriteScic;
	}

	public long getSdWriteScid() {
		return sdWriteScid;
	}

	public void setSdWriteScid(long sdWriteScid) {
		this.sdWriteScid = sdWriteScid;
	}

	public long getSdWriteAdcs() {
		return sdWriteAdcs;
	}

	public void setSdWriteAdcs(long sdWriteAdcs) {
		this.sdWriteAdcs = sdWriteAdcs;
	}

	public long getSdWriteBeacon() {
		return sdWriteBeacon;
	}

	public void setSdWriteBeacon(long sdWriteBeacon) {
		this.sdWriteBeacon = sdWriteBeacon;
	}

	public long getSdWriteLog() {
		return sdWriteLog;
	}

	public void setSdWriteLog(long sdWriteLog) {
		this.sdWriteLog = sdWriteLog;
	}

	public long getCmdLossTimer() {
		return cmdLossTimer;
	}

	public void setCmdLossTimer(long cmdLossTimer) {
		this.cmdLossTimer = cmdLossTimer;
	}

	public AliveFlags getAliveFlags() {
		return aliveFlags;
	}

	public void setAliveFlags(AliveFlags aliveFlags) {
		this.aliveFlags = aliveFlags;
	}

	public long getCipComstat() {
		return cipComstat;
	}

	public void setCipComstat(long cipComstat) {
		this.cipComstat = cipComstat;
	}

	public float getCipTemp1() {
		return cipTemp1;
	}

	public void setCipTemp1(float cipTemp1) {
		this.cipTemp1 = cipTemp1;
	}

	public float getCipTemp2() {
		return cipTemp2;
	}

	public void setCipTemp2(float cipTemp2) {
		this.cipTemp2 = cipTemp2;
	}

	public float getCipTemp3() {
		return cipTemp3;
	}

	public void setCipTemp3(float cipTemp3) {
		this.cipTemp3 = cipTemp3;
	}

	public UhfTemp getUhfTemp() {
		return uhfTemp;
	}

	public void setUhfTemp(UhfTemp uhfTemp) {
		this.uhfTemp = uhfTemp;
	}

	public UhfConfig getUhfConfig() {
		return uhfConfig;
	}

	public void setUhfConfig(UhfConfig uhfConfig) {
		this.uhfConfig = uhfConfig;
	}

	public float getSbandPaCurr() {
		return sbandPaCurr;
	}

	public void setSbandPaCurr(float sbandPaCurr) {
		this.sbandPaCurr = sbandPaCurr;
	}

	public float getSbandPaVolt() {
		return sbandPaVolt;
	}

	public void setSbandPaVolt(float sbandPaVolt) {
		this.sbandPaVolt = sbandPaVolt;
	}

	public float getSbandRfPwr() {
		return sbandRfPwr;
	}

	public void setSbandRfPwr(float sbandRfPwr) {
		this.sbandRfPwr = sbandRfPwr;
	}

	public float getSbandPaTemp() {
		return sbandPaTemp;
	}

	public void setSbandPaTemp(float sbandPaTemp) {
		this.sbandPaTemp = sbandPaTemp;
	}

	public float getSbandTopTemp() {
		return sbandTopTemp;
	}

	public void setSbandTopTemp(float sbandTopTemp) {
		this.sbandTopTemp = sbandTopTemp;
	}

	public float getSbandBottomTemp() {
		return sbandBottomTemp;
	}

	public void setSbandBottomTemp(float sbandBottomTemp) {
		this.sbandBottomTemp = sbandBottomTemp;
	}

	public int getAdcsCmdAcpt() {
		return adcsCmdAcpt;
	}

	public void setAdcsCmdAcpt(int adcsCmdAcpt) {
		this.adcsCmdAcpt = adcsCmdAcpt;
	}

	public int getAdcsCmdFail() {
		return adcsCmdFail;
	}

	public void setAdcsCmdFail(int adcsCmdFail) {
		this.adcsCmdFail = adcsCmdFail;
	}

	public long getAdcsTime() {
		return adcsTime;
	}

	public void setAdcsTime(long adcsTime) {
		this.adcsTime = adcsTime;
	}

	public AdcsInfo getAdcsInfo() {
		return adcsInfo;
	}

	public void setAdcsInfo(AdcsInfo adcsInfo) {
		this.adcsInfo = adcsInfo;
	}

	public float getAdcsStarTemp() {
		return adcsStarTemp;
	}

	public void setAdcsStarTemp(float adcsStarTemp) {
		this.adcsStarTemp = adcsStarTemp;
	}

	public float getAdcsWheelTemp1() {
		return adcsWheelTemp1;
	}

	public void setAdcsWheelTemp1(float adcsWheelTemp1) {
		this.adcsWheelTemp1 = adcsWheelTemp1;
	}

	public float getAdcsWheelTemp2() {
		return adcsWheelTemp2;
	}

	public void setAdcsWheelTemp2(float adcsWheelTemp2) {
		this.adcsWheelTemp2 = adcsWheelTemp2;
	}

	public float getAdcsWheelTemp3() {
		return adcsWheelTemp3;
	}

	public void setAdcsWheelTemp3(float adcsWheelTemp3) {
		this.adcsWheelTemp3 = adcsWheelTemp3;
	}

	public float getAdcsDigiBusVolt() {
		return adcsDigiBusVolt;
	}

	public void setAdcsDigiBusVolt(float adcsDigiBusVolt) {
		this.adcsDigiBusVolt = adcsDigiBusVolt;
	}

	public float getAdcsSunVec1() {
		return adcsSunVec1;
	}

	public void setAdcsSunVec1(float adcsSunVec1) {
		this.adcsSunVec1 = adcsSunVec1;
	}

	public float getAdcsSunVec2() {
		return adcsSunVec2;
	}

	public void setAdcsSunVec2(float adcsSunVec2) {
		this.adcsSunVec2 = adcsSunVec2;
	}

	public float getAdcsSunVec3() {
		return adcsSunVec3;
	}

	public void setAdcsSunVec3(float adcsSunVec3) {
		this.adcsSunVec3 = adcsSunVec3;
	}

	public float getAdcsWheelSp1() {
		return adcsWheelSp1;
	}

	public void setAdcsWheelSp1(float adcsWheelSp1) {
		this.adcsWheelSp1 = adcsWheelSp1;
	}

	public float getAdcsWheelSp2() {
		return adcsWheelSp2;
	}

	public void setAdcsWheelSp2(float adcsWheelSp2) {
		this.adcsWheelSp2 = adcsWheelSp2;
	}

	public float getAdcsWheelSp3() {
		return adcsWheelSp3;
	}

	public void setAdcsWheelSp3(float adcsWheelSp3) {
		this.adcsWheelSp3 = adcsWheelSp3;
	}

	public float getAdcsBodyRt1() {
		return adcsBodyRt1;
	}

	public void setAdcsBodyRt1(float adcsBodyRt1) {
		this.adcsBodyRt1 = adcsBodyRt1;
	}

	public float getAdcsBodyRt2() {
		return adcsBodyRt2;
	}

	public void setAdcsBodyRt2(float adcsBodyRt2) {
		this.adcsBodyRt2 = adcsBodyRt2;
	}

	public float getAdcsBodyRt3() {
		return adcsBodyRt3;
	}

	public void setAdcsBodyRt3(float adcsBodyRt3) {
		this.adcsBodyRt3 = adcsBodyRt3;
	}

	public long getDaxssTimeSec() {
		return daxssTimeSec;
	}

	public void setDaxssTimeSec(long daxssTimeSec) {
		this.daxssTimeSec = daxssTimeSec;
	}

	public int getDaxssCmdOp() {
		return daxssCmdOp;
	}

	public void setDaxssCmdOp(int daxssCmdOp) {
		this.daxssCmdOp = daxssCmdOp;
	}

	public int getDaxssCmdSucc() {
		return daxssCmdSucc;
	}

	public void setDaxssCmdSucc(int daxssCmdSucc) {
		this.daxssCmdSucc = daxssCmdSucc;
	}

	public int getDaxssCmdFail() {
		return daxssCmdFail;
	}

	public void setDaxssCmdFail(int daxssCmdFail) {
		this.daxssCmdFail = daxssCmdFail;
	}

	public int getDaxssCdhEnables() {
		return daxssCdhEnables;
	}

	public void setDaxssCdhEnables(int daxssCdhEnables) {
		this.daxssCdhEnables = daxssCdhEnables;
	}

	public float getDaxssCdhTemp() {
		return daxssCdhTemp;
	}

	public void setDaxssCdhTemp(float daxssCdhTemp) {
		this.daxssCdhTemp = daxssCdhTemp;
	}

	public long getDaxssSpsRate() {
		return daxssSpsRate;
	}

	public void setDaxssSpsRate(long daxssSpsRate) {
		this.daxssSpsRate = daxssSpsRate;
	}

	public int getDaxssSpsX() {
		return daxssSpsX;
	}

	public void setDaxssSpsX(int daxssSpsX) {
		this.daxssSpsX = daxssSpsX;
	}

	public int getDaxssSpsY() {
		return daxssSpsY;
	}

	public void setDaxssSpsY(int daxssSpsY) {
		this.daxssSpsY = daxssSpsY;
	}

	public int getDaxssSlowCount() {
		return daxssSlowCount;
	}

	public void setDaxssSlowCount(int daxssSlowCount) {
		this.daxssSlowCount = daxssSlowCount;
	}

	public float getBatFg1() {
		return batFg1;
	}

	public void setBatFg1(float batFg1) {
		this.batFg1 = batFg1;
	}

	public float getDaxssCurr() {
		return daxssCurr;
	}

	public void setDaxssCurr(float daxssCurr) {
		this.daxssCurr = daxssCurr;
	}

	public float getDaxssVolt() {
		return daxssVolt;
	}

	public void setDaxssVolt(float daxssVolt) {
		this.daxssVolt = daxssVolt;
	}

	public float getCdhCurr() {
		return cdhCurr;
	}

	public void setCdhCurr(float cdhCurr) {
		this.cdhCurr = cdhCurr;
	}

	public float getCdhVolt() {
		return cdhVolt;
	}

	public void setCdhVolt(float cdhVolt) {
		this.cdhVolt = cdhVolt;
	}

	public float getSbandCurr() {
		return sbandCurr;
	}

	public void setSbandCurr(float sbandCurr) {
		this.sbandCurr = sbandCurr;
	}

	public float getSbandVolt() {
		return sbandVolt;
	}

	public void setSbandVolt(float sbandVolt) {
		this.sbandVolt = sbandVolt;
	}

	public float getUhfCurr() {
		return uhfCurr;
	}

	public void setUhfCurr(float uhfCurr) {
		this.uhfCurr = uhfCurr;
	}

	public float getUhfVolt() {
		return uhfVolt;
	}

	public void setUhfVolt(float uhfVolt) {
		this.uhfVolt = uhfVolt;
	}

	public float getHeaterCurr() {
		return heaterCurr;
	}

	public void setHeaterCurr(float heaterCurr) {
		this.heaterCurr = heaterCurr;
	}

	public float getHeaterVolt() {
		return heaterVolt;
	}

	public void setHeaterVolt(float heaterVolt) {
		this.heaterVolt = heaterVolt;
	}

	public float getSp2Curr() {
		return sp2Curr;
	}

	public void setSp2Curr(float sp2Curr) {
		this.sp2Curr = sp2Curr;
	}

	public float getSp2Volt() {
		return sp2Volt;
	}

	public void setSp2Volt(float sp2Volt) {
		this.sp2Volt = sp2Volt;
	}

	public float getSp1Curr() {
		return sp1Curr;
	}

	public void setSp1Curr(float sp1Curr) {
		this.sp1Curr = sp1Curr;
	}

	public float getSp1Volt() {
		return sp1Volt;
	}

	public void setSp1Volt(float sp1Volt) {
		this.sp1Volt = sp1Volt;
	}

	public float getSp0Curr() {
		return sp0Curr;
	}

	public void setSp0Curr(float sp0Curr) {
		this.sp0Curr = sp0Curr;
	}

	public float getSp0Volt() {
		return sp0Volt;
	}

	public void setSp0Volt(float sp0Volt) {
		this.sp0Volt = sp0Volt;
	}

	public int getBatVcell() {
		return batVcell;
	}

	public void setBatVcell(int batVcell) {
		this.batVcell = batVcell;
	}

	public float getGps12v2Curr() {
		return gps12v2Curr;
	}

	public void setGps12v2Curr(float gps12v2Curr) {
		this.gps12v2Curr = gps12v2Curr;
	}

	public float getGps12v2Volt() {
		return gps12v2Volt;
	}

	public void setGps12v2Volt(float gps12v2Volt) {
		this.gps12v2Volt = gps12v2Volt;
	}

	public float getGps12v1Curr() {
		return gps12v1Curr;
	}

	public void setGps12v1Curr(float gps12v1Curr) {
		this.gps12v1Curr = gps12v1Curr;
	}

	public float getGps12v1Volt() {
		return gps12v1Volt;
	}

	public void setGps12v1Volt(float gps12v1Volt) {
		this.gps12v1Volt = gps12v1Volt;
	}

	public float getBatCurr() {
		return batCurr;
	}

	public void setBatCurr(float batCurr) {
		this.batCurr = batCurr;
	}

	public float getBatVolt() {
		return batVolt;
	}

	public void setBatVolt(float batVolt) {
		this.batVolt = batVolt;
	}

	public float getAdcsCurr() {
		return adcsCurr;
	}

	public void setAdcsCurr(float adcsCurr) {
		this.adcsCurr = adcsCurr;
	}

	public float getAdcsVolt() {
		return adcsVolt;
	}

	public void setAdcsVolt(float adcsVolt) {
		this.adcsVolt = adcsVolt;
	}

	public float getCurrent3p3() {
		return current3p3;
	}

	public void setCurrent3p3(float current3p3) {
		this.current3p3 = current3p3;
	}

	public float getVoltage3p3() {
		return voltage3p3;
	}

	public void setVoltage3p3(float voltage3p3) {
		this.voltage3p3 = voltage3p3;
	}

	public float getCipCurr() {
		return cipCurr;
	}

	public void setCipCurr(float cipCurr) {
		this.cipCurr = cipCurr;
	}

	public float getCipVolt() {
		return cipVolt;
	}

	public void setCipVolt(float cipVolt) {
		this.cipVolt = cipVolt;
	}

	public float getObcTemp() {
		return obcTemp;
	}

	public void setObcTemp(float obcTemp) {
		this.obcTemp = obcTemp;
	}

	public float getEpsTemp() {
		return epsTemp;
	}

	public void setEpsTemp(float epsTemp) {
		this.epsTemp = epsTemp;
	}

	public float getIntTemp() {
		return intTemp;
	}

	public void setIntTemp(float intTemp) {
		this.intTemp = intTemp;
	}

	public float getSp0Temp() {
		return sp0Temp;
	}

	public void setSp0Temp(float sp0Temp) {
		this.sp0Temp = sp0Temp;
	}

	public float getBat0Temp() {
		return bat0Temp;
	}

	public void setBat0Temp(float bat0Temp) {
		this.bat0Temp = bat0Temp;
	}

	public float getSp1Temp() {
		return sp1Temp;
	}

	public void setSp1Temp(float sp1Temp) {
		this.sp1Temp = sp1Temp;
	}

	public float getBat1Temp() {
		return bat1Temp;
	}

	public void setBat1Temp(float bat1Temp) {
		this.bat1Temp = bat1Temp;
	}

	public float getSp2Temp() {
		return sp2Temp;
	}

	public void setSp2Temp(float sp2Temp) {
		this.sp2Temp = sp2Temp;
	}

	public float getBatFg3() {
		return batFg3;
	}

	public void setBatFg3(float batFg3) {
		this.batFg3 = batFg3;
	}

	public float getBat0TempConv() {
		return bat0TempConv;
	}

	public void setBat0TempConv(float bat0TempConv) {
		this.bat0TempConv = bat0TempConv;
	}

	public float getBat1TempConv() {
		return bat1TempConv;
	}

	public void setBat1TempConv(float bat1TempConv) {
		this.bat1TempConv = bat1TempConv;
	}

	public SystemMode getMode() {
		return mode;
	}

	public void setMode(SystemMode mode) {
		this.mode = mode;
	}

}
