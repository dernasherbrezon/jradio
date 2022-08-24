package ru.r2cloud.jradio.cute;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class CutePayloadSwStat {

	private long shCoarse;
	private int shFine;
	private int pldSwVerMaj;
	private int pldSwVerMin;
	private int pldSwVerPatch;
	private boolean sdStateCard1;
	private boolean sdStateCard0;
	private float zynqTemp;
	private float zynqVccInt;
	private float zynqVccAux;
	private float zynqVccBram;
	private float zynqVccPint;
	private float zynqVccPaux;
	private float zynqVccPdr0;
	private int zynqStatus;
	private float procTemp;
	private float ccdP5;
	private float ccdP15;
	private float ccdP32;
	private float ccdN5;
	private int cmdRecvCount;
	private int cmdRjctCount;
	private int cmdSuccCount;
	private CommandOperation cmdSuccOp;
	private CommandOperation cmdRjctOp;
	private CommandFailCode cmdFailCode;
	private boolean armStateSc;
	private boolean armStateDbg;
	private int logWriteCount;
	private int logDropCount;
	private boolean ccdEnaState;
	private CcdCtrlState ccdCtrlState;
	private CcdShutter ccdShutter;
	private boolean shutterOverride;
	private long frameId;
	private int osCpuUsage;
	private int osCpuMax;
	private int timePpsCount;
	private int timeRecvCount;
	private int timeMissCount;
	private FswMode fswMode;
	private boolean tecState;
	private float tecSlewRate;
	private float tecSetpoint;
	private float tecCcdRtd;
	private float tecScRtd5;
	private float tecScRtd4;
	private float tecScRtd3;
	private float tecScRtd2;
	private float tecScRtd1;
	private float tecShutter;
	private int tecVolt;
	private int tecAvgCurr;
	private int tecCurr;
	private int imgState;
	private ImgProcType imgCurrProcType;

	public CutePayloadSwStat() {
		// do nothing
	}

	public CutePayloadSwStat(DataInputStream dis) throws IOException {
		shCoarse = StreamUtils.readUnsignedInt(dis);
		shFine = dis.readUnsignedShort();
		pldSwVerMaj = dis.readUnsignedByte();
		pldSwVerMin = dis.readUnsignedByte();
		pldSwVerPatch = dis.readUnsignedByte();

		int raw = dis.readUnsignedByte();
		sdStateCard1 = ((raw >> 1) & 0x1) > 0;
		sdStateCard0 = (raw & 0x1) > 0;
		zynqTemp = 0.007690f * dis.readUnsignedShort() - 273.150000f;
		zynqVccInt = 0.000046f * dis.readUnsignedShort();
		zynqVccAux = 0.000046f * dis.readUnsignedShort();
		zynqVccBram = 0.000046f * dis.readUnsignedShort();
		zynqVccPint = 0.000046f * dis.readUnsignedShort();
		zynqVccPaux = 0.000046f * dis.readUnsignedShort();
		zynqVccPdr0 = 0.000046f * dis.readUnsignedShort();
		zynqStatus = dis.readUnsignedByte();
		dis.skipBytes(1);
		procTemp = 0.00390625f * dis.readShort();
		ccdP5 = 0.00161172f * dis.readUnsignedShort();
		ccdP15 = 0.00447253f * dis.readUnsignedShort();
		ccdP32 = 0.00886447f * dis.readUnsignedShort();
		ccdN5 = 0.00313565f * dis.readUnsignedShort() - 7.229570f;
		dis.skipBytes(2);
		cmdRecvCount = dis.readUnsignedShort();
		cmdRjctCount = dis.readUnsignedShort();
		cmdSuccCount = dis.readUnsignedShort();
		cmdSuccOp = CommandOperation.valueOfCode(dis.readUnsignedShort());
		cmdRjctOp = CommandOperation.valueOfCode(dis.readUnsignedShort());
		cmdFailCode = CommandFailCode.valueOfCode(dis.readUnsignedByte());

		raw = dis.readUnsignedByte();
		armStateSc = ((raw >> 1) & 0x1) > 0;
		armStateDbg = (raw & 0x1) > 0;
		logWriteCount = dis.readUnsignedShort();
		logDropCount = dis.readUnsignedShort();
		ccdEnaState = dis.readBoolean();
		ccdCtrlState = CcdCtrlState.valueOfCode(dis.readUnsignedByte());
		ccdShutter = CcdShutter.valueOfCode(dis.readUnsignedByte());
		shutterOverride = dis.readBoolean();
		frameId = StreamUtils.readUnsignedInt(dis);
		osCpuUsage = dis.readUnsignedShort();
		osCpuMax = dis.readUnsignedShort();
		timePpsCount = dis.readUnsignedShort();
		timeRecvCount = dis.readUnsignedShort();
		timeMissCount = dis.readUnsignedShort();
		fswMode = FswMode.valueOfCode(dis.readUnsignedByte());
		tecState = dis.readBoolean();
		tecSlewRate = dis.readFloat();
		tecSetpoint = dis.readFloat();
		tecCcdRtd = 0.07926885578181089f * dis.readUnsignedShort() - 259.7402597402597f;
		tecScRtd5 = 0.07926885578181089f * dis.readUnsignedShort() - 259.7402597402597f;
		tecScRtd4 = 0.07926885578181089f * dis.readUnsignedShort() - 259.7402597402597f;
		tecScRtd3 = 0.07926885578181089f * dis.readUnsignedShort() - 259.7402597402597f;
		tecScRtd2 = 0.07926885578181089f * dis.readUnsignedShort() - 259.7402597402597f;
		tecScRtd1 = 0.07926885578181089f * dis.readUnsignedShort() - 259.7402597402597f;
		tecShutter = 1.910352233280647e-4f * dis.readUnsignedShort();
		tecVolt = dis.readUnsignedShort();
		tecAvgCurr = dis.readUnsignedShort();
		tecCurr = dis.readUnsignedShort();
		imgState = dis.readUnsignedByte();
		imgCurrProcType = ImgProcType.valueOfCode(dis.readUnsignedByte());
	}

	public long getShCoarse() {
		return shCoarse;
	}

	public void setShCoarse(long shCoarse) {
		this.shCoarse = shCoarse;
	}

	public int getShFine() {
		return shFine;
	}

	public void setShFine(int shFine) {
		this.shFine = shFine;
	}

	public int getPldSwVerMaj() {
		return pldSwVerMaj;
	}

	public void setPldSwVerMaj(int pldSwVerMaj) {
		this.pldSwVerMaj = pldSwVerMaj;
	}

	public int getPldSwVerMin() {
		return pldSwVerMin;
	}

	public void setPldSwVerMin(int pldSwVerMin) {
		this.pldSwVerMin = pldSwVerMin;
	}

	public int getPldSwVerPatch() {
		return pldSwVerPatch;
	}

	public void setPldSwVerPatch(int pldSwVerPatch) {
		this.pldSwVerPatch = pldSwVerPatch;
	}

	public boolean isSdStateCard1() {
		return sdStateCard1;
	}

	public void setSdStateCard1(boolean sdStateCard1) {
		this.sdStateCard1 = sdStateCard1;
	}

	public boolean isSdStateCard0() {
		return sdStateCard0;
	}

	public void setSdStateCard0(boolean sdStateCard0) {
		this.sdStateCard0 = sdStateCard0;
	}

	public float getZynqTemp() {
		return zynqTemp;
	}

	public void setZynqTemp(float zynqTemp) {
		this.zynqTemp = zynqTemp;
	}

	public float getZynqVccInt() {
		return zynqVccInt;
	}

	public void setZynqVccInt(float zynqVccInt) {
		this.zynqVccInt = zynqVccInt;
	}

	public float getZynqVccAux() {
		return zynqVccAux;
	}

	public void setZynqVccAux(float zynqVccAux) {
		this.zynqVccAux = zynqVccAux;
	}

	public float getZynqVccBram() {
		return zynqVccBram;
	}

	public void setZynqVccBram(float zynqVccBram) {
		this.zynqVccBram = zynqVccBram;
	}

	public float getZynqVccPint() {
		return zynqVccPint;
	}

	public void setZynqVccPint(float zynqVccPint) {
		this.zynqVccPint = zynqVccPint;
	}

	public float getZynqVccPaux() {
		return zynqVccPaux;
	}

	public void setZynqVccPaux(float zynqVccPaux) {
		this.zynqVccPaux = zynqVccPaux;
	}

	public float getZynqVccPdr0() {
		return zynqVccPdr0;
	}

	public void setZynqVccPdr0(float zynqVccPdr0) {
		this.zynqVccPdr0 = zynqVccPdr0;
	}

	public int getZynqStatus() {
		return zynqStatus;
	}

	public void setZynqStatus(int zynqStatus) {
		this.zynqStatus = zynqStatus;
	}

	public float getProcTemp() {
		return procTemp;
	}

	public void setProcTemp(float procTemp) {
		this.procTemp = procTemp;
	}

	public float getCcdP5() {
		return ccdP5;
	}

	public void setCcdP5(float ccdP5) {
		this.ccdP5 = ccdP5;
	}

	public float getCcdP15() {
		return ccdP15;
	}

	public void setCcdP15(float ccdP15) {
		this.ccdP15 = ccdP15;
	}

	public float getCcdP32() {
		return ccdP32;
	}

	public void setCcdP32(float ccdP32) {
		this.ccdP32 = ccdP32;
	}

	public float getCcdN5() {
		return ccdN5;
	}

	public void setCcdN5(float ccdN5) {
		this.ccdN5 = ccdN5;
	}

	public int getCmdRecvCount() {
		return cmdRecvCount;
	}

	public void setCmdRecvCount(int cmdRecvCount) {
		this.cmdRecvCount = cmdRecvCount;
	}

	public int getCmdRjctCount() {
		return cmdRjctCount;
	}

	public void setCmdRjctCount(int cmdRjctCount) {
		this.cmdRjctCount = cmdRjctCount;
	}

	public int getCmdSuccCount() {
		return cmdSuccCount;
	}

	public void setCmdSuccCount(int cmdSuccCount) {
		this.cmdSuccCount = cmdSuccCount;
	}

	public CommandOperation getCmdSuccOp() {
		return cmdSuccOp;
	}

	public void setCmdSuccOp(CommandOperation cmdSuccOp) {
		this.cmdSuccOp = cmdSuccOp;
	}

	public CommandOperation getCmdRjctOp() {
		return cmdRjctOp;
	}

	public void setCmdRjctOp(CommandOperation cmdRjctOp) {
		this.cmdRjctOp = cmdRjctOp;
	}

	public CommandFailCode getCmdFailCode() {
		return cmdFailCode;
	}

	public void setCmdFailCode(CommandFailCode cmdFailCode) {
		this.cmdFailCode = cmdFailCode;
	}

	public boolean isArmStateSc() {
		return armStateSc;
	}

	public void setArmStateSc(boolean armStateSc) {
		this.armStateSc = armStateSc;
	}

	public boolean isArmStateDbg() {
		return armStateDbg;
	}

	public void setArmStateDbg(boolean armStateDbg) {
		this.armStateDbg = armStateDbg;
	}

	public int getLogWriteCount() {
		return logWriteCount;
	}

	public void setLogWriteCount(int logWriteCount) {
		this.logWriteCount = logWriteCount;
	}

	public int getLogDropCount() {
		return logDropCount;
	}

	public void setLogDropCount(int logDropCount) {
		this.logDropCount = logDropCount;
	}

	public boolean isCcdEnaState() {
		return ccdEnaState;
	}

	public void setCcdEnaState(boolean ccdEnaState) {
		this.ccdEnaState = ccdEnaState;
	}

	public CcdCtrlState getCcdCtrlState() {
		return ccdCtrlState;
	}

	public void setCcdCtrlState(CcdCtrlState ccdCtrlState) {
		this.ccdCtrlState = ccdCtrlState;
	}

	public CcdShutter getCcdShutter() {
		return ccdShutter;
	}

	public void setCcdShutter(CcdShutter ccdShutter) {
		this.ccdShutter = ccdShutter;
	}

	public boolean isShutterOverride() {
		return shutterOverride;
	}

	public void setShutterOverride(boolean shutterOverride) {
		this.shutterOverride = shutterOverride;
	}

	public long getFrameId() {
		return frameId;
	}

	public void setFrameId(long frameId) {
		this.frameId = frameId;
	}

	public int getOsCpuUsage() {
		return osCpuUsage;
	}

	public void setOsCpuUsage(int osCpuUsage) {
		this.osCpuUsage = osCpuUsage;
	}

	public int getOsCpuMax() {
		return osCpuMax;
	}

	public void setOsCpuMax(int osCpuMax) {
		this.osCpuMax = osCpuMax;
	}

	public int getTimePpsCount() {
		return timePpsCount;
	}

	public void setTimePpsCount(int timePpsCount) {
		this.timePpsCount = timePpsCount;
	}

	public int getTimeRecvCount() {
		return timeRecvCount;
	}

	public void setTimeRecvCount(int timeRecvCount) {
		this.timeRecvCount = timeRecvCount;
	}

	public int getTimeMissCount() {
		return timeMissCount;
	}

	public void setTimeMissCount(int timeMissCount) {
		this.timeMissCount = timeMissCount;
	}

	public FswMode getFswMode() {
		return fswMode;
	}

	public void setFswMode(FswMode fswMode) {
		this.fswMode = fswMode;
	}

	public boolean isTecState() {
		return tecState;
	}

	public void setTecState(boolean tecState) {
		this.tecState = tecState;
	}

	public float getTecSlewRate() {
		return tecSlewRate;
	}

	public void setTecSlewRate(float tecSlewRate) {
		this.tecSlewRate = tecSlewRate;
	}

	public float getTecSetpoint() {
		return tecSetpoint;
	}

	public void setTecSetpoint(float tecSetpoint) {
		this.tecSetpoint = tecSetpoint;
	}

	public float getTecCcdRtd() {
		return tecCcdRtd;
	}

	public void setTecCcdRtd(float tecCcdRtd) {
		this.tecCcdRtd = tecCcdRtd;
	}

	public float getTecScRtd5() {
		return tecScRtd5;
	}

	public void setTecScRtd5(float tecScRtd5) {
		this.tecScRtd5 = tecScRtd5;
	}

	public float getTecScRtd4() {
		return tecScRtd4;
	}

	public void setTecScRtd4(float tecScRtd4) {
		this.tecScRtd4 = tecScRtd4;
	}

	public float getTecScRtd3() {
		return tecScRtd3;
	}

	public void setTecScRtd3(float tecScRtd3) {
		this.tecScRtd3 = tecScRtd3;
	}

	public float getTecScRtd2() {
		return tecScRtd2;
	}

	public void setTecScRtd2(float tecScRtd2) {
		this.tecScRtd2 = tecScRtd2;
	}

	public float getTecScRtd1() {
		return tecScRtd1;
	}

	public void setTecScRtd1(float tecScRtd1) {
		this.tecScRtd1 = tecScRtd1;
	}

	public float getTecShutter() {
		return tecShutter;
	}

	public void setTecShutter(float tecShutter) {
		this.tecShutter = tecShutter;
	}

	public int getTecVolt() {
		return tecVolt;
	}

	public void setTecVolt(int tecVolt) {
		this.tecVolt = tecVolt;
	}

	public int getTecAvgCurr() {
		return tecAvgCurr;
	}

	public void setTecAvgCurr(int tecAvgCurr) {
		this.tecAvgCurr = tecAvgCurr;
	}

	public int getTecCurr() {
		return tecCurr;
	}

	public void setTecCurr(int tecCurr) {
		this.tecCurr = tecCurr;
	}

	public int getImgState() {
		return imgState;
	}

	public void setImgState(int imgState) {
		this.imgState = imgState;
	}

	public ImgProcType getImgCurrProcType() {
		return imgCurrProcType;
	}

	public void setImgCurrProcType(ImgProcType imgCurrProcType) {
		this.imgCurrProcType = imgCurrProcType;
	}

}
