package ru.r2cloud.jradio.jy1sat;

import java.io.IOException;

import ru.r2cloud.jradio.ao73.AntennasTemperatures;
import ru.r2cloud.jradio.ao73.PaTemperature;
import ru.r2cloud.jradio.util.BitInputStream;

public class RealtimeTelemetry {

	// EPS i2c address 0x02
	private int solarPanelVoltageX;
	private int solarPanelVoltageY;
	private int solarPanelVoltageZ;
	private int batteryVoltage;
	private int panelCurr1;
	private int panelCurr2;
	private int panelCurr3;
	private int totPhotoCurr;
	private int totSystemCurr;
	private int rebootCount;
	private int boostTemp1;
	private int boostTemp2;
	private int boostTemp3;
	private int batteryTemp;
	private int latchUpCount5v;
	private int latchUpCount5vSW;
	private int resetCause;
	private int pptMode;

	// iMTQ i2c address 0x10
	private long imtqMode;
	private long imtqErrorCode;
	private boolean imtqConfigSet;
	private long imtqMcuTemp;

	// ASIB i2c address 0x35
	private float sunSensorXP;
	private float sunSensorXM;
	private float sunSensorYP;
	private float sunSensorYM;
	private float sunSensorZP;
	private float sunSensorZM;
	private int busVoltage33;
	private int iMTQuptime;
	private int busVoltage50;
	private int receiverDoppler;
	private int receiverRSSI;

	// RF i2c address 0x33
	private float temperature;
	private float receiveCurrent;
	private float transmitCurrent33VBus;
	private float transmitCurrent50VBus;
	private float forwardPower;
	private float reversePower;

	// PA i2c address 0x34
	private double deviceTemperature;
	private float busCurrent;
	private float antennaTemp0;
	private float antennaTemp1;

	// ANTS i2c address 0x31(A) & 0x32(B)
	private boolean antennaDeploymentVHFA;
	private boolean antennaDeploymentVHFB;
	private boolean antennaDeploymentUHFA;
	private boolean antennaDeploymentUHFB;
	private int sequenceNumber;
	private int commandCount;

	// SW
	private int lastCommand;
	private boolean commandSuccess;
	private boolean dataValidASIB;
	private boolean dataValidEPS;
	private boolean dataValidPA;
	private boolean dataValidRF;
	private boolean dataValidMSE;
	private boolean dataValidANTSBusA;
	private boolean dataValidANTSBusB;
	private boolean inEclipseMode;
	private boolean inSafeMode;
	private boolean hardwareABF;
	private boolean softwareABF;
	private boolean deploymentWaitAtNextBoot;

	public RealtimeTelemetry(BitInputStream dis) throws IOException {
		solarPanelVoltageX = dis.readUnsignedInt(14);
		solarPanelVoltageY = dis.readUnsignedInt(14);
		solarPanelVoltageZ = dis.readUnsignedInt(14);
		batteryVoltage = dis.readUnsignedInt(14);
		panelCurr1 = dis.readUnsignedInt(10);
		panelCurr2 = dis.readUnsignedInt(10);
		panelCurr3 = dis.readUnsignedInt(10);
		totPhotoCurr = dis.readUnsignedInt(10);
		totSystemCurr = dis.readUnsignedInt(10);
		rebootCount = dis.readUnsignedByte();
		boostTemp1 = dis.readUnsignedByte();
		boostTemp2 = dis.readUnsignedByte();
		boostTemp3 = dis.readUnsignedByte();
		batteryTemp = dis.readUnsignedByte();
		latchUpCount5v = dis.readUnsignedByte();
		latchUpCount5vSW = dis.readUnsignedByte();
		resetCause = dis.readUnsignedInt(4);
		pptMode = dis.readUnsignedInt(4);

		// iMTQ i2c address 0x10
		imtqMode = dis.readUnsignedInt(2);
		imtqErrorCode = dis.readUnsignedInt(3);
		imtqConfigSet = dis.readBoolean();
		imtqMcuTemp = dis.readUnsignedInt(8);

		// ASIB i2c address 0x35
		sunSensorXP = readXPlusTemperature(dis);
		sunSensorXM = readXMinusTemperature(dis);
		sunSensorYP = readYPlusTemperature(dis);
		sunSensorYM = readYMinusTemperature(dis);
		sunSensorZP = readYPlusTemperature(dis);
		sunSensorZM = readYMinusTemperature(dis);
		busVoltage33 = dis.readUnsignedInt(10) * 4;
		iMTQuptime = dis.readUnsignedInt(20);
		busVoltage50 = dis.readUnsignedInt(10) * 6;
		receiverDoppler = dis.readUnsignedByte();
		receiverRSSI = dis.readUnsignedByte();

		// RF i2c address 0x33
		temperature = -0.98f * dis.readUnsignedByte() + 234.58f;
		receiveCurrent = 0.636f * dis.readUnsignedByte();
		transmitCurrent33VBus = 0.636f * dis.readUnsignedByte();
		transmitCurrent50VBus = 0.636f * dis.readUnsignedByte();
		forwardPower = 0.005f * (float) Math.pow(dis.readUnsignedByte(), 2.0629);
		reversePower = 0.005f * (float) Math.pow(dis.readUnsignedByte(), 2.0629);

		// PA i2c address 0x34
		deviceTemperature = PaTemperature.getPaTemp(dis.readUnsignedByte());
		busCurrent = 0.5496f * dis.readUnsignedByte() + 2.5435f;
		antennaTemp0 = AntennasTemperatures.getTemperature(dis.readUnsignedByte());
		antennaTemp1 = AntennasTemperatures.getTemperature(dis.readUnsignedByte());

		// ANTS i2c address 0x31(A) & 0x32(B)
		antennaDeploymentVHFA = dis.readBoolean();
		antennaDeploymentVHFB = dis.readBoolean();
		antennaDeploymentUHFA = dis.readBoolean();
		antennaDeploymentUHFB = dis.readBoolean();
		sequenceNumber = dis.readUnsignedInt(24);
		commandCount = dis.readUnsignedInt(6);

		// SW
		lastCommand = dis.readUnsignedInt(5);
		commandSuccess = dis.readBoolean();
		dataValidASIB = dis.readBoolean();
		dataValidEPS = dis.readBoolean();
		dataValidPA = dis.readBoolean();
		dataValidRF = dis.readBoolean();
		dataValidMSE = dis.readBoolean();
		dataValidANTSBusA = dis.readBoolean();
		dataValidANTSBusB = dis.readBoolean();
		inEclipseMode = dis.readBoolean();
		inSafeMode = dis.readBoolean();
		hardwareABF = dis.readBoolean();
		softwareABF = dis.readBoolean();
		deploymentWaitAtNextBoot = dis.readBoolean();
	}

	public int getSolarPanelVoltageX() {
		return solarPanelVoltageX;
	}

	public void setSolarPanelVoltageX(int solarPanelVoltageX) {
		this.solarPanelVoltageX = solarPanelVoltageX;
	}

	public int getSolarPanelVoltageY() {
		return solarPanelVoltageY;
	}

	public void setSolarPanelVoltageY(int solarPanelVoltageY) {
		this.solarPanelVoltageY = solarPanelVoltageY;
	}

	public int getSolarPanelVoltageZ() {
		return solarPanelVoltageZ;
	}

	public void setSolarPanelVoltageZ(int solarPanelVoltageZ) {
		this.solarPanelVoltageZ = solarPanelVoltageZ;
	}

	public int getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(int batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public int getPanelCurr1() {
		return panelCurr1;
	}

	public void setPanelCurr1(int panelCurr1) {
		this.panelCurr1 = panelCurr1;
	}

	public int getPanelCurr2() {
		return panelCurr2;
	}

	public void setPanelCurr2(int panelCurr2) {
		this.panelCurr2 = panelCurr2;
	}

	public int getPanelCurr3() {
		return panelCurr3;
	}

	public void setPanelCurr3(int panelCurr3) {
		this.panelCurr3 = panelCurr3;
	}

	public int getTotPhotoCurr() {
		return totPhotoCurr;
	}

	public void setTotPhotoCurr(int totPhotoCurr) {
		this.totPhotoCurr = totPhotoCurr;
	}

	public int getTotSystemCurr() {
		return totSystemCurr;
	}

	public void setTotSystemCurr(int totSystemCurr) {
		this.totSystemCurr = totSystemCurr;
	}

	public int getRebootCount() {
		return rebootCount;
	}

	public void setRebootCount(int rebootCount) {
		this.rebootCount = rebootCount;
	}

	public int getBoostTemp1() {
		return boostTemp1;
	}

	public void setBoostTemp1(int boostTemp1) {
		this.boostTemp1 = boostTemp1;
	}

	public int getBoostTemp2() {
		return boostTemp2;
	}

	public void setBoostTemp2(int boostTemp2) {
		this.boostTemp2 = boostTemp2;
	}

	public int getBoostTemp3() {
		return boostTemp3;
	}

	public void setBoostTemp3(int boostTemp3) {
		this.boostTemp3 = boostTemp3;
	}

	public int getBatteryTemp() {
		return batteryTemp;
	}

	public void setBatteryTemp(int batteryTemp) {
		this.batteryTemp = batteryTemp;
	}

	public int getLatchUpCount5v() {
		return latchUpCount5v;
	}

	public void setLatchUpCount5v(int latchUpCount5v) {
		this.latchUpCount5v = latchUpCount5v;
	}

	public int getLatchUpCount5vSW() {
		return latchUpCount5vSW;
	}

	public void setLatchUpCount5vSW(int latchUpCount5vSW) {
		this.latchUpCount5vSW = latchUpCount5vSW;
	}

	public int getResetCause() {
		return resetCause;
	}

	public void setResetCause(int resetCause) {
		this.resetCause = resetCause;
	}

	public int getPptMode() {
		return pptMode;
	}

	public void setPptMode(int pptMode) {
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

	public float getSunSensorXP() {
		return sunSensorXP;
	}

	public void setSunSensorXP(float sunSensorXP) {
		this.sunSensorXP = sunSensorXP;
	}

	public float getSunSensorXM() {
		return sunSensorXM;
	}

	public void setSunSensorXM(float sunSensorXM) {
		this.sunSensorXM = sunSensorXM;
	}

	public float getSunSensorYP() {
		return sunSensorYP;
	}

	public void setSunSensorYP(float sunSensorYP) {
		this.sunSensorYP = sunSensorYP;
	}

	public float getSunSensorYM() {
		return sunSensorYM;
	}

	public void setSunSensorYM(float sunSensorYM) {
		this.sunSensorYM = sunSensorYM;
	}

	public float getSunSensorZP() {
		return sunSensorZP;
	}

	public void setSunSensorZP(float sunSensorZP) {
		this.sunSensorZP = sunSensorZP;
	}

	public float getSunSensorZM() {
		return sunSensorZM;
	}

	public void setSunSensorZM(float sunSensorZM) {
		this.sunSensorZM = sunSensorZM;
	}

	public int getBusVoltage33() {
		return busVoltage33;
	}

	public void setBusVoltage33(int busVoltage33) {
		this.busVoltage33 = busVoltage33;
	}

	public int getiMTQuptime() {
		return iMTQuptime;
	}

	public void setiMTQuptime(int iMTQuptime) {
		this.iMTQuptime = iMTQuptime;
	}

	public int getBusVoltage50() {
		return busVoltage50;
	}

	public void setBusVoltage50(int busVoltage50) {
		this.busVoltage50 = busVoltage50;
	}

	public int getReceiverDoppler() {
		return receiverDoppler;
	}

	public void setReceiverDoppler(int receiverDoppler) {
		this.receiverDoppler = receiverDoppler;
	}

	public int getReceiverRSSI() {
		return receiverRSSI;
	}

	public void setReceiverRSSI(int receiverRSSI) {
		this.receiverRSSI = receiverRSSI;
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public float getReceiveCurrent() {
		return receiveCurrent;
	}

	public void setReceiveCurrent(float receiveCurrent) {
		this.receiveCurrent = receiveCurrent;
	}

	public float getTransmitCurrent33VBus() {
		return transmitCurrent33VBus;
	}

	public void setTransmitCurrent33VBus(float transmitCurrent33VBus) {
		this.transmitCurrent33VBus = transmitCurrent33VBus;
	}

	public float getTransmitCurrent50VBus() {
		return transmitCurrent50VBus;
	}

	public void setTransmitCurrent50VBus(float transmitCurrent50VBus) {
		this.transmitCurrent50VBus = transmitCurrent50VBus;
	}

	public float getForwardPower() {
		return forwardPower;
	}

	public void setForwardPower(float forwardPower) {
		this.forwardPower = forwardPower;
	}

	public float getReversePower() {
		return reversePower;
	}

	public void setReversePower(float reversePower) {
		this.reversePower = reversePower;
	}

	public double getDeviceTemperature() {
		return deviceTemperature;
	}

	public void setDeviceTemperature(double deviceTemperature) {
		this.deviceTemperature = deviceTemperature;
	}

	public float getBusCurrent() {
		return busCurrent;
	}

	public void setBusCurrent(float busCurrent) {
		this.busCurrent = busCurrent;
	}

	public float getAntennaTemp0() {
		return antennaTemp0;
	}

	public void setAntennaTemp0(float antennaTemp0) {
		this.antennaTemp0 = antennaTemp0;
	}

	public float getAntennaTemp1() {
		return antennaTemp1;
	}

	public void setAntennaTemp1(float antennaTemp1) {
		this.antennaTemp1 = antennaTemp1;
	}

	public boolean isAntennaDeploymentVHFA() {
		return antennaDeploymentVHFA;
	}

	public void setAntennaDeploymentVHFA(boolean antennaDeploymentVHFA) {
		this.antennaDeploymentVHFA = antennaDeploymentVHFA;
	}

	public boolean isAntennaDeploymentVHFB() {
		return antennaDeploymentVHFB;
	}

	public void setAntennaDeploymentVHFB(boolean antennaDeploymentVHFB) {
		this.antennaDeploymentVHFB = antennaDeploymentVHFB;
	}

	public boolean isAntennaDeploymentUHFA() {
		return antennaDeploymentUHFA;
	}

	public void setAntennaDeploymentUHFA(boolean antennaDeploymentUHFA) {
		this.antennaDeploymentUHFA = antennaDeploymentUHFA;
	}

	public boolean isAntennaDeploymentUHFB() {
		return antennaDeploymentUHFB;
	}

	public void setAntennaDeploymentUHFB(boolean antennaDeploymentUHFB) {
		this.antennaDeploymentUHFB = antennaDeploymentUHFB;
	}

	public int getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public int getCommandCount() {
		return commandCount;
	}

	public void setCommandCount(int commandCount) {
		this.commandCount = commandCount;
	}

	public int getLastCommand() {
		return lastCommand;
	}

	public void setLastCommand(int lastCommand) {
		this.lastCommand = lastCommand;
	}

	public boolean isCommandSuccess() {
		return commandSuccess;
	}

	public void setCommandSuccess(boolean commandSuccess) {
		this.commandSuccess = commandSuccess;
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

	public boolean isDataValidMSE() {
		return dataValidMSE;
	}

	public void setDataValidMSE(boolean dataValidMSE) {
		this.dataValidMSE = dataValidMSE;
	}

	public boolean isDataValidANTSBusA() {
		return dataValidANTSBusA;
	}

	public void setDataValidANTSBusA(boolean dataValidANTSBusA) {
		this.dataValidANTSBusA = dataValidANTSBusA;
	}

	public boolean isDataValidANTSBusB() {
		return dataValidANTSBusB;
	}

	public void setDataValidANTSBusB(boolean dataValidANTSBusB) {
		this.dataValidANTSBusB = dataValidANTSBusB;
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

	public boolean isHardwareABF() {
		return hardwareABF;
	}

	public void setHardwareABF(boolean hardwareABF) {
		this.hardwareABF = hardwareABF;
	}

	public boolean isSoftwareABF() {
		return softwareABF;
	}

	public void setSoftwareABF(boolean softwareABF) {
		this.softwareABF = softwareABF;
	}

	public boolean isDeploymentWaitAtNextBoot() {
		return deploymentWaitAtNextBoot;
	}

	public void setDeploymentWaitAtNextBoot(boolean deploymentWaitAtNextBoot) {
		this.deploymentWaitAtNextBoot = deploymentWaitAtNextBoot;
	}

	static float readXPlusTemperature(BitInputStream dis) throws IOException {
		return (-0.2073f * dis.readUnsignedInt(10)) + 158.239f;
	}

	static float readXMinusTemperature(BitInputStream dis) throws IOException {
		return (-0.2083f * dis.readUnsignedInt(10)) + 159.227f;
	}

	static float readYPlusTemperature(BitInputStream dis) throws IOException {
		return (-0.2076f * dis.readUnsignedInt(10)) + 158.656f;
	}

	static float readYMinusTemperature(BitInputStream dis) throws IOException {
		return (-0.2087f * dis.readUnsignedInt(10)) + 159.045f;
	}
}
