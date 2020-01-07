package ru.r2cloud.jradio.nayif1;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class RealtimeTelemetry {

	private long panelVolts1;
	private long panelVolts2;
	private long panelVolts3;
	private long batteryVolts;

	private long panelCurr1;
	private long panelCurr2;
	private long panelCurr3;
	private long totPhotoCurr;
	private long totSystemCurr;

	private int sequenceNumber;

	private long rebootCount;
	private long epsErrorCount;
	private long boostTemp1;
	private long boostTemp2;
	private long boostTemp3;
	private long batteryTemp;
	private long latchUpCount5v;
	private long latchUpCount3v3;
	private long latchUpCount5vSW;
	private long resetCause;
	private long pptMode;

	private long imtqMode;
	private long imtqErrorCode;
	private boolean imtqConfigSet;
	private long imtqMcuTemp;

	private long solXPlus;
	private long solXMinus;
	private long solYPlus;
	private long solYMinus;
	private long solZPlus;
	private long solZMinus;

	private long busVolts3v3;
	private long busCurr3v3;
	private long busVolts5;
	private long busCurr5;

	private long rxDoppler;
	private long rxRSSI;
	private long rxTemp;
	private long rxCurr;
	private long txBusCurr3v3;
	private long txBusCurr5v;
	private long txRevPwr;
	private long txFwdPwr;
	private long txTemp;
	private long txCurr;
	private long antTemp0;
	private long antTemp1;

	private boolean antDepl0;
	private boolean antDepl1;
	private boolean antDepl2;
	private boolean antDepl3;

	private long channelCurren5V;

	private long imtqUptime;

	private long dtmfCmdCount;

	private long dtmfLastCmd;

	private boolean dtmfCmdSuccess;

	private boolean dataValidASIB;

	private boolean dataValidEPS;

	private boolean dataValidPA;

	private boolean dataValidRF;

	private boolean dataValidiMTQ;

	private boolean dataValidAntsBusB;

	private boolean dataValidAntsBusA;

	private boolean inEclipseMode;

	private boolean inSafeMode;

	private boolean hardwareABFOnOff;

	private boolean softwareABFOnOff;

	private boolean deploymentWait;
	
	public RealtimeTelemetry() {
		// do nothing
	}

	public RealtimeTelemetry(BitInputStream dis) throws IOException {
		panelVolts1 = dis.readUnsignedInt(14);
		panelVolts2 = dis.readUnsignedInt(14);
		panelVolts3 = dis.readUnsignedInt(14);
		batteryVolts = dis.readUnsignedInt(14);
		panelCurr1 = dis.readUnsignedInt(10);
		panelCurr2 = dis.readUnsignedInt(10);
		panelCurr3 = dis.readUnsignedInt(10);
		totPhotoCurr = dis.readUnsignedInt(10);
		totSystemCurr = dis.readUnsignedInt(10);
		rebootCount = dis.readUnsignedInt(8);
		boostTemp1 = dis.readUnsignedInt(8);
		boostTemp2 = dis.readUnsignedInt(8);
		boostTemp3 = dis.readUnsignedInt(8);
		batteryTemp = dis.readUnsignedInt(8);
		latchUpCount5v = dis.readUnsignedInt(8);
		channelCurren5V = dis.readUnsignedInt(8);
		resetCause = dis.readUnsignedInt(4);
		pptMode = dis.readUnsignedInt(4);
		imtqMode = dis.readUnsignedInt(2);
		imtqErrorCode = dis.readUnsignedInt(3);
		imtqConfigSet = dis.readBoolean();
		imtqMcuTemp = dis.readUnsignedInt(8);
		solXPlus = dis.readUnsignedInt(10);
		solXMinus = dis.readUnsignedInt(10);
		solYPlus = dis.readUnsignedInt(10);
		solYMinus = dis.readUnsignedInt(10);
		solZPlus = dis.readUnsignedInt(10);
		solZMinus = dis.readUnsignedInt(10);
		busVolts3v3 = dis.readUnsignedInt(10);
		imtqUptime = dis.readUnsignedInt(20);
		busVolts5 = dis.readUnsignedInt(10);
		rxDoppler = dis.readUnsignedInt(8);
		rxRSSI = dis.readUnsignedInt(8);
		rxTemp = dis.readUnsignedInt(8);
		rxCurr = dis.readUnsignedInt(8);
		txBusCurr3v3 = dis.readUnsignedInt(8);
		txBusCurr5v = dis.readUnsignedInt(8);
		txRevPwr = dis.readUnsignedInt(8);
		txFwdPwr = dis.readUnsignedInt(8);
		txTemp = dis.readUnsignedInt(8);
		txCurr = dis.readUnsignedInt(8);
		antTemp0 = dis.readUnsignedInt(8);
		antTemp1 = dis.readUnsignedInt(8);
		antDepl0 = dis.readBoolean();
		antDepl1 = dis.readBoolean();
		antDepl2 = dis.readBoolean();
		antDepl3 = dis.readBoolean();
		sequenceNumber = dis.readUnsignedInt(24);
		dtmfCmdCount = dis.readUnsignedInt(6);
		dtmfLastCmd = dis.readUnsignedInt(5);
		dtmfCmdSuccess = dis.readBoolean();
		dataValidASIB = dis.readBoolean();
		dataValidEPS = dis.readBoolean();
		dataValidPA = dis.readBoolean();
		dataValidRF = dis.readBoolean();
		dataValidiMTQ = dis.readBoolean();
		dataValidAntsBusB = dis.readBoolean();
		dataValidAntsBusA = dis.readBoolean();
		inEclipseMode = dis.readBoolean();
		inSafeMode = dis.readBoolean();
		hardwareABFOnOff = dis.readBoolean();
		softwareABFOnOff = dis.readBoolean();
		deploymentWait = dis.readBoolean();
	}

	public long getPanelVolts1() {
		return panelVolts1;
	}

	public void setPanelVolts1(long panelVolts1) {
		this.panelVolts1 = panelVolts1;
	}

	public long getPanelVolts2() {
		return panelVolts2;
	}

	public void setPanelVolts2(long panelVolts2) {
		this.panelVolts2 = panelVolts2;
	}

	public long getPanelVolts3() {
		return panelVolts3;
	}

	public void setPanelVolts3(long panelVolts3) {
		this.panelVolts3 = panelVolts3;
	}

	public long getBatteryVolts() {
		return batteryVolts;
	}

	public void setBatteryVolts(long batteryVolts) {
		this.batteryVolts = batteryVolts;
	}

	public long getPanelCurr1() {
		return panelCurr1;
	}

	public void setPanelCurr1(long panelCurr1) {
		this.panelCurr1 = panelCurr1;
	}

	public long getPanelCurr2() {
		return panelCurr2;
	}

	public void setPanelCurr2(long panelCurr2) {
		this.panelCurr2 = panelCurr2;
	}

	public long getPanelCurr3() {
		return panelCurr3;
	}

	public void setPanelCurr3(long panelCurr3) {
		this.panelCurr3 = panelCurr3;
	}

	public long getTotPhotoCurr() {
		return totPhotoCurr;
	}

	public void setTotPhotoCurr(long totPhotoCurr) {
		this.totPhotoCurr = totPhotoCurr;
	}

	public long getTotSystemCurr() {
		return totSystemCurr;
	}

	public void setTotSystemCurr(long totSystemCurr) {
		this.totSystemCurr = totSystemCurr;
	}

	public int getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public long getRebootCount() {
		return rebootCount;
	}

	public void setRebootCount(long rebootCount) {
		this.rebootCount = rebootCount;
	}

	public long getEpsErrorCount() {
		return epsErrorCount;
	}

	public void setEpsErrorCount(long epsErrorCount) {
		this.epsErrorCount = epsErrorCount;
	}

	public long getBoostTemp1() {
		return boostTemp1;
	}

	public void setBoostTemp1(long boostTemp1) {
		this.boostTemp1 = boostTemp1;
	}

	public long getBoostTemp2() {
		return boostTemp2;
	}

	public void setBoostTemp2(long boostTemp2) {
		this.boostTemp2 = boostTemp2;
	}

	public long getBoostTemp3() {
		return boostTemp3;
	}

	public void setBoostTemp3(long boostTemp3) {
		this.boostTemp3 = boostTemp3;
	}

	public long getBatteryTemp() {
		return batteryTemp;
	}

	public void setBatteryTemp(long batteryTemp) {
		this.batteryTemp = batteryTemp;
	}

	public long getLatchUpCount5v() {
		return latchUpCount5v;
	}

	public void setLatchUpCount5v(long latchUpCount5v) {
		this.latchUpCount5v = latchUpCount5v;
	}

	public long getLatchUpCount3v3() {
		return latchUpCount3v3;
	}

	public void setLatchUpCount3v3(long latchUpCount3v3) {
		this.latchUpCount3v3 = latchUpCount3v3;
	}

	public long getLatchUpCount5vSW() {
		return latchUpCount5vSW;
	}

	public void setLatchUpCount5vSW(long latchUpCount5vSW) {
		this.latchUpCount5vSW = latchUpCount5vSW;
	}

	public long getResetCause() {
		return resetCause;
	}

	public void setResetCause(long resetCause) {
		this.resetCause = resetCause;
	}

	public long getPptMode() {
		return pptMode;
	}

	public void setPptMode(long pptMode) {
		this.pptMode = pptMode;
	}

	public long getImtqMode() {
		return imtqMode;
	}

	public void setImtqMode(long imtqMode) {
		this.imtqMode = imtqMode;
	}

	public long getImtqErrorCode() {
		return imtqErrorCode;
	}

	public void setImtqErrorCode(long imtqErrorCode) {
		this.imtqErrorCode = imtqErrorCode;
	}

	public boolean isImtqConfigSet() {
		return imtqConfigSet;
	}

	public void setImtqConfigSet(boolean imtqConfigSet) {
		this.imtqConfigSet = imtqConfigSet;
	}

	public long getImtqMcuTemp() {
		return imtqMcuTemp;
	}

	public void setImtqMcuTemp(long imtqMcuTemp) {
		this.imtqMcuTemp = imtqMcuTemp;
	}

	public long getSolXPlus() {
		return solXPlus;
	}

	public void setSolXPlus(long solXPlus) {
		this.solXPlus = solXPlus;
	}

	public long getSolXMinus() {
		return solXMinus;
	}

	public void setSolXMinus(long solXMinus) {
		this.solXMinus = solXMinus;
	}

	public long getSolYPlus() {
		return solYPlus;
	}

	public void setSolYPlus(long solYPlus) {
		this.solYPlus = solYPlus;
	}

	public long getSolYMinus() {
		return solYMinus;
	}

	public void setSolYMinus(long solYMinus) {
		this.solYMinus = solYMinus;
	}

	public long getSolZPlus() {
		return solZPlus;
	}

	public void setSolZPlus(long solZPlus) {
		this.solZPlus = solZPlus;
	}

	public long getSolZMinus() {
		return solZMinus;
	}

	public void setSolZMinus(long solZMinus) {
		this.solZMinus = solZMinus;
	}

	public long getBusVolts3v3() {
		return busVolts3v3;
	}

	public void setBusVolts3v3(long busVolts3v3) {
		this.busVolts3v3 = busVolts3v3;
	}

	public long getBusCurr3v3() {
		return busCurr3v3;
	}

	public void setBusCurr3v3(long busCurr3v3) {
		this.busCurr3v3 = busCurr3v3;
	}

	public long getBusVolts5() {
		return busVolts5;
	}

	public void setBusVolts5(long busVolts5) {
		this.busVolts5 = busVolts5;
	}

	public long getBusCurr5() {
		return busCurr5;
	}

	public void setBusCurr5(long busCurr5) {
		this.busCurr5 = busCurr5;
	}

	public long getRxDoppler() {
		return rxDoppler;
	}

	public void setRxDoppler(long rxDoppler) {
		this.rxDoppler = rxDoppler;
	}

	public long getRxRSSI() {
		return rxRSSI;
	}

	public void setRxRSSI(long rxRSSI) {
		this.rxRSSI = rxRSSI;
	}

	public long getRxTemp() {
		return rxTemp;
	}

	public void setRxTemp(long rxTemp) {
		this.rxTemp = rxTemp;
	}

	public long getRxCurr() {
		return rxCurr;
	}

	public void setRxCurr(long rxCurr) {
		this.rxCurr = rxCurr;
	}

	public long getTxBusCurr3v3() {
		return txBusCurr3v3;
	}

	public void setTxBusCurr3v3(long txBusCurr3v3) {
		this.txBusCurr3v3 = txBusCurr3v3;
	}

	public long getTxBusCurr5v() {
		return txBusCurr5v;
	}

	public void setTxBusCurr5v(long txBusCurr5v) {
		this.txBusCurr5v = txBusCurr5v;
	}

	public long getTxRevPwr() {
		return txRevPwr;
	}

	public void setTxRevPwr(long txRevPwr) {
		this.txRevPwr = txRevPwr;
	}

	public long getTxFwdPwr() {
		return txFwdPwr;
	}

	public void setTxFwdPwr(long txFwdPwr) {
		this.txFwdPwr = txFwdPwr;
	}

	public long getTxTemp() {
		return txTemp;
	}

	public void setTxTemp(long txTemp) {
		this.txTemp = txTemp;
	}

	public long getTxCurr() {
		return txCurr;
	}

	public void setTxCurr(long txCurr) {
		this.txCurr = txCurr;
	}

	public long getAntTemp0() {
		return antTemp0;
	}

	public void setAntTemp0(long antTemp0) {
		this.antTemp0 = antTemp0;
	}

	public long getAntTemp1() {
		return antTemp1;
	}

	public void setAntTemp1(long antTemp1) {
		this.antTemp1 = antTemp1;
	}

	public boolean isAntDepl0() {
		return antDepl0;
	}

	public void setAntDepl0(boolean antDepl0) {
		this.antDepl0 = antDepl0;
	}

	public boolean isAntDepl1() {
		return antDepl1;
	}

	public void setAntDepl1(boolean antDepl1) {
		this.antDepl1 = antDepl1;
	}

	public boolean isAntDepl2() {
		return antDepl2;
	}

	public void setAntDepl2(boolean antDepl2) {
		this.antDepl2 = antDepl2;
	}

	public boolean isAntDepl3() {
		return antDepl3;
	}

	public void setAntDepl3(boolean antDepl3) {
		this.antDepl3 = antDepl3;
	}

	public long getChannelCurren5V() {
		return channelCurren5V;
	}

	public void setChannelCurren5V(long channelCurren5V) {
		this.channelCurren5V = channelCurren5V;
	}

	public long getImtqUptime() {
		return imtqUptime;
	}

	public void setImtqUptime(long imtqUptime) {
		this.imtqUptime = imtqUptime;
	}

	public long getDtmfCmdCount() {
		return dtmfCmdCount;
	}

	public void setDtmfCmdCount(long dtmfCmdCount) {
		this.dtmfCmdCount = dtmfCmdCount;
	}

	public long getDtmfLastCmd() {
		return dtmfLastCmd;
	}

	public void setDtmfLastCmd(long dtmfLastCmd) {
		this.dtmfLastCmd = dtmfLastCmd;
	}

	public boolean isDtmfCmdSuccess() {
		return dtmfCmdSuccess;
	}

	public void setDtmfCmdSuccess(boolean dtmfCmdSuccess) {
		this.dtmfCmdSuccess = dtmfCmdSuccess;
	}

	public boolean isDataValidASIB() {
		return dataValidASIB;
	}

	public void setDataValidASIB(boolean dataValidASIB) {
		this.dataValidASIB = dataValidASIB;
	}

	public boolean isDataValidEPS() {
		return dataValidEPS;
	}

	public void setDataValidEPS(boolean dataValidEPS) {
		this.dataValidEPS = dataValidEPS;
	}

	public boolean isDataValidPA() {
		return dataValidPA;
	}

	public void setDataValidPA(boolean dataValidPA) {
		this.dataValidPA = dataValidPA;
	}

	public boolean isDataValidRF() {
		return dataValidRF;
	}

	public void setDataValidRF(boolean dataValidRF) {
		this.dataValidRF = dataValidRF;
	}

	public boolean isDataValidiMTQ() {
		return dataValidiMTQ;
	}

	public void setDataValidiMTQ(boolean dataValidiMTQ) {
		this.dataValidiMTQ = dataValidiMTQ;
	}

	public boolean isDataValidAntsBusB() {
		return dataValidAntsBusB;
	}

	public void setDataValidAntsBusB(boolean dataValidAntsBusB) {
		this.dataValidAntsBusB = dataValidAntsBusB;
	}

	public boolean isDataValidAntsBusA() {
		return dataValidAntsBusA;
	}

	public void setDataValidAntsBusA(boolean dataValidAntsBusA) {
		this.dataValidAntsBusA = dataValidAntsBusA;
	}

	public boolean isInEclipseMode() {
		return inEclipseMode;
	}

	public void setInEclipseMode(boolean inEclipseMode) {
		this.inEclipseMode = inEclipseMode;
	}

	public boolean isInSafeMode() {
		return inSafeMode;
	}

	public void setInSafeMode(boolean inSafeMode) {
		this.inSafeMode = inSafeMode;
	}

	public boolean isHardwareABFOnOff() {
		return hardwareABFOnOff;
	}

	public void setHardwareABFOnOff(boolean hardwareABFOnOff) {
		this.hardwareABFOnOff = hardwareABFOnOff;
	}

	public boolean isSoftwareABFOnOff() {
		return softwareABFOnOff;
	}

	public void setSoftwareABFOnOff(boolean softwareABFOnOff) {
		this.softwareABFOnOff = softwareABFOnOff;
	}

	public boolean isDeploymentWait() {
		return deploymentWait;
	}

	public void setDeploymentWait(boolean deploymentWait) {
		this.deploymentWait = deploymentWait;
	}

}
