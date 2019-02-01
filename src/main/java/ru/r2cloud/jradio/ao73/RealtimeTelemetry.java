package ru.r2cloud.jradio.ao73;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class RealtimeTelemetry {

	// EPS
	private int solarPanelVoltageX;
	private int solarPanelVoltageY;
	private int solarPanelVoltageZ;
	private int totalPhotoCurrent;
	private int batteryVoltage;
	private int totalSystemCurrent;
	private int rebootCount;
	private int EPSSoftwareErrors;
	private int boostConverterTempX;
	private int boostConverterTempY;
	private int boostConverterTempZ;
	private int batteryTemp;
	private int latchUpCount5v;
	private int latchUpCount33v;
	private ResetCause resetCause;
	private PowerTrackingMode powerPointTrackingMode;

	// Sensor Subsystem
	private float sunSensorXP;
	private float sunSensorYP;
	private float sunSensorZP;
	private float solarPanelTempXP;
	private float solarPanelTempXM;
	private float solarPanelTempYP;
	private float solarPanelTempYM;
	private float busVoltage33;
	private float busCurrent33;
	private float busVoltage50;

	// RF
	private int receiverDoppler;
	private int receiverRSSI;
	private float temperature;
	private float receiveCurrent;
	private float transmitCurrent33VBus;
	private float transmitCurrent50VBus;

	// PA
	private float forwardPower;
	private float reversePower;
	private Float deviceTemperature;
	private float busCurrent;

	// ANTS
	private float antennaTemp0;
	private float antennaTemp1;
	private boolean antennaDeploymentVHFA;
	private boolean antennaDeploymentVHFB;
	private boolean antennaDeploymentUHFA;
	private boolean antennaDeploymentUHFB;

	// SW
	private int sequenceNumber;
	private int commandCount;
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
		solarPanelVoltageX = dis.readUnsignedShort();
		solarPanelVoltageY = dis.readUnsignedShort();
		solarPanelVoltageZ = dis.readUnsignedShort();
		totalPhotoCurrent = dis.readUnsignedShort();
		batteryVoltage = dis.readUnsignedShort();
		totalSystemCurrent = dis.readUnsignedShort();
		rebootCount = dis.readUnsignedShort();
		EPSSoftwareErrors = dis.readUnsignedShort();
		boostConverterTempX = dis.readUnsignedByte();
		boostConverterTempY = dis.readUnsignedByte();
		boostConverterTempZ = dis.readUnsignedByte();
		batteryTemp = dis.readUnsignedByte();
		latchUpCount5v = dis.readUnsignedByte();
		latchUpCount33v = dis.readUnsignedByte();
		resetCause = ResetCause.valueOfCode(dis.readUnsignedByte());
		powerPointTrackingMode = PowerTrackingMode.valueOfCode(dis.readUnsignedByte());

		// Sensor Subsystem
		sunSensorXP = readXPlusTemperature(dis);
		sunSensorYP = readYPlusTemperature(dis);
		sunSensorZP = readYPlusTemperature(dis);
		solarPanelTempXP = readXPlusTemperature(dis);
		solarPanelTempXM = readXMinusTemperature(dis);
		solarPanelTempYP = readYPlusTemperature(dis);
		solarPanelTempYM = readYMinusTemperature(dis);
		busVoltage33 = dis.readUnsignedInt(10) * 4;
		busCurrent33 = dis.readUnsignedInt(10);
		busVoltage50 = dis.readUnsignedInt(10) * 6;

		// RF
		receiverDoppler = dis.readUnsignedByte();
		receiverRSSI = dis.readUnsignedByte();
		temperature = (-0.857f * dis.readUnsignedByte()) + 193.672f;
		receiveCurrent = 0.636f * dis.readUnsignedByte();
		transmitCurrent33VBus = 0.636f * dis.readUnsignedByte();
		transmitCurrent50VBus = 1.272f * dis.readUnsignedByte();

		// PA
		forwardPower = 0.005f * (float)Math.pow(dis.readUnsignedByte(), 2.0629);
		reversePower = 0.005f * (float)Math.pow(dis.readUnsignedByte(), 2.0629);
		deviceTemperature = PaTemperature.getPaTemp(dis.readUnsignedByte());
		busCurrent = 0.5496f * dis.readUnsignedByte() + 2.5435f;

		// ANTS
		antennaTemp0 = AntennasTemperatures.getTemperature(dis.readUnsignedByte());
		antennaTemp1 = AntennasTemperatures.getTemperature(dis.readUnsignedByte());
		antennaDeploymentVHFA = dis.readBoolean();
		antennaDeploymentVHFB = dis.readBoolean();
		antennaDeploymentUHFA = dis.readBoolean();
		antennaDeploymentUHFB = dis.readBoolean();

		// SW
		sequenceNumber = dis.readUnsignedInt(24);
		commandCount = dis.readUnsignedInt(6);
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

	public int getTotalPhotoCurrent() {
		return totalPhotoCurrent;
	}

	public void setTotalPhotoCurrent(int totalPhotoCurrent) {
		this.totalPhotoCurrent = totalPhotoCurrent;
	}

	public int getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(int batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public int getTotalSystemCurrent() {
		return totalSystemCurrent;
	}

	public void setTotalSystemCurrent(int totalSystemCurrent) {
		this.totalSystemCurrent = totalSystemCurrent;
	}

	public int getRebootCount() {
		return rebootCount;
	}

	public void setRebootCount(int rebootCount) {
		this.rebootCount = rebootCount;
	}

	public int getEPSSoftwareErrors() {
		return EPSSoftwareErrors;
	}

	public void setEPSSoftwareErrors(int ePSSoftwareErrors) {
		EPSSoftwareErrors = ePSSoftwareErrors;
	}

	public int getBoostConverterTempX() {
		return boostConverterTempX;
	}

	public void setBoostConverterTempX(int boostConverterTempX) {
		this.boostConverterTempX = boostConverterTempX;
	}

	public int getBoostConverterTempY() {
		return boostConverterTempY;
	}

	public void setBoostConverterTempY(int boostConverterTempY) {
		this.boostConverterTempY = boostConverterTempY;
	}

	public int getBoostConverterTempZ() {
		return boostConverterTempZ;
	}

	public void setBoostConverterTempZ(int boostConverterTempZ) {
		this.boostConverterTempZ = boostConverterTempZ;
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

	public int getLatchUpCount33v() {
		return latchUpCount33v;
	}

	public void setLatchUpCount33v(int latchUpCount33v) {
		this.latchUpCount33v = latchUpCount33v;
	}

	public ResetCause getResetCause() {
		return resetCause;
	}
	
	public void setResetCause(ResetCause resetCause) {
		this.resetCause = resetCause;
	}

	public PowerTrackingMode getPowerPointTrackingMode() {
		return powerPointTrackingMode;
	}
	
	public void setPowerPointTrackingMode(PowerTrackingMode powerPointTrackingMode) {
		this.powerPointTrackingMode = powerPointTrackingMode;
	}

	public float getSunSensorXP() {
		return sunSensorXP;
	}

	public void setSunSensorXP(float sunSensorXP) {
		this.sunSensorXP = sunSensorXP;
	}

	public float getSunSensorYP() {
		return sunSensorYP;
	}

	public void setSunSensorYP(float sunSensorYP) {
		this.sunSensorYP = sunSensorYP;
	}

	public float getSunSensorZP() {
		return sunSensorZP;
	}

	public void setSunSensorZP(float sunSensorZP) {
		this.sunSensorZP = sunSensorZP;
	}

	public float getSolarPanelTempXP() {
		return solarPanelTempXP;
	}

	public void setSolarPanelTempXP(float solarPanelTempXP) {
		this.solarPanelTempXP = solarPanelTempXP;
	}

	public float getSolarPanelTempXM() {
		return solarPanelTempXM;
	}

	public void setSolarPanelTempXM(float solarPanelTempXM) {
		this.solarPanelTempXM = solarPanelTempXM;
	}

	public float getSolarPanelTempYP() {
		return solarPanelTempYP;
	}

	public void setSolarPanelTempYP(float solarPanelTempYP) {
		this.solarPanelTempYP = solarPanelTempYP;
	}

	public float getSolarPanelTempYM() {
		return solarPanelTempYM;
	}

	public void setSolarPanelTempYM(float solarPanelTempYM) {
		this.solarPanelTempYM = solarPanelTempYM;
	}

	public float getBusVoltage33() {
		return busVoltage33;
	}

	public void setBusVoltage33(float busVoltage33) {
		this.busVoltage33 = busVoltage33;
	}

	public float getBusCurrent33() {
		return busCurrent33;
	}

	public void setBusCurrent33(float busCurrent33) {
		this.busCurrent33 = busCurrent33;
	}

	public float getBusVoltage50() {
		return busVoltage50;
	}

	public void setBusVoltage50(float busVoltage50) {
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

	public Float getDeviceTemperature() {
		return deviceTemperature;
	}

	public void setDeviceTemperature(Float deviceTemperature) {
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

}
