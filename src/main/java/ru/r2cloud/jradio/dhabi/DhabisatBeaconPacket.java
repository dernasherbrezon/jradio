package ru.r2cloud.jradio.dhabi;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class DhabisatBeaconPacket {

	private String callsign;
	private ObcMode obcMode;
	private long obcResetCount;
	private long obcTimestamp;
	private long obcUptime;
	private int subsystemSafetyCriteria;
	private long telemetryDistributionCounter;
	private int obcTemperature;
	private float cameraVoltage;
	private int cameraCurrent;
	private float batteryTemperature;
	private float batteryVoltage;
	private float adcs5vVoltage;
	private float adcs5vCurrent;
	private float adcs5vPower;
	private float adcs3v3Voltage;
	private float adcs3v3Current;
	private float adcs3v3Power;
	private int epsMode;
	private int epsResetCause;
	private long epsUptime;
	private int epsError;
	private int epsSystemResetCounterPowerOn;
	private int epsSystemResetCounterWatchdog;
	private int epsSystemResetCounterCommanded;
	private int epsSystemResetCounterController;
	private int epsSystemResetCounterLowPower;
	private int panelXpTemperature;
	private int panelXnTemperature;
	private int panelYpTemperature;
	private int panelYnTemperature;
	private int panelZpTemperature;
	private int panelZnTemperature;
	private float txPaTemp;
	private int attitudeEstimationMode;
	private int attitudeControlMode;
	private int adcsRunMode;
	private int asgp4Mode;
	private boolean cubecontrolSignalEnabled;
	private boolean cubecontrolMotorEnabled;
	private boolean cubesense1Enabled;
	private boolean cubesense2Enabled;
	private boolean cubewheel1Enabled;
	private boolean cubewheel2Enabled;
	private boolean cubewheel3Enabled;
	private boolean cubestarEnabled;
	private boolean gpsReceiverEnabled;
	private boolean gpsLnaPowerEnabled;
	private boolean motorDriverEnabled;
	private boolean sunIsAboveLocalHorizon;
	private boolean cubesense1CommunicationsError;
	private boolean cubesense2CommunicationsError;
	private boolean cubecontrolSignalCommunicationsError;
	private boolean cubecontrolMotorCommunicationsError;
	private boolean cubewheel1CommunicationsError;
	private boolean cubewheel2CommunicationsError;
	private boolean cubewheel3CommunicationsError;
	private boolean cubestarCommunicationsError;
	private boolean magnetometerRangeError;
	private boolean cam1SramOvercurrentDetected;
	private boolean cam13v3OvercurrentDetected;
	private boolean cam1SensorBusyError;
	private boolean cam1SensorDetectionError;
	private boolean sunSensorRangeError;
	private boolean cam2SramOvercurrentDetected;
	private boolean cam23v3OvercurrentDetected;
	private boolean cam2SensorBusyError;
	private boolean cam2SensorDetectionError;
	private boolean nadirSensorRangeError;
	private boolean rateSensorRangeError;
	private boolean wheelSpeedRangeError;
	private boolean coarseSunSensorError;
	private boolean startrackerMatchError;
	private boolean startrackerOvercurrentDetected;
	private boolean orbitParametersAreInvalid;
	private boolean configurationIsInvalid;
	private boolean controlModeChangeIsNotAllowed;
	private boolean estimatorChangeIsNotAllowed;
	private int currentMagnetometerSamplingMode;
	private boolean modelledAndMeasuredMagneticFieldDiffersInSize;
	private boolean nodeRecoveryError;
	private boolean cubesense1RuntimeError;
	private boolean cubesense2RuntimeError;
	private boolean cubecontrolSignalRuntimeError;
	private boolean cubecontrolMotorRuntimeError;
	private boolean cubewheel1RuntimeError;
	private boolean cubewheel2RuntimeError;
	private boolean cubewheel3RuntimeError;
	private boolean cubestarRuntimeError;
	private boolean magnetometerError;
	private boolean rateSensorFailure;
	private float estimatedRollAngle;
	private float estimatedPitchAngle;
	private float estimatedYawAngle;
	private float estimatedQ1;
	private float estimatedQ2;
	private float estimatedQ3;
	private float estimatedXAngularRate;
	private float estimatedYAngularRate;
	private float estimatedZAngularRate;
	private float xPosition;
	private float yPosition;
	private float zPosition;
	private float xVelocity;
	private float yVelocity;
	private float zVelocity;
	private float latitude;
	private float longitude;
	private float altitude;
	private float ecefPositionX;
	private float ecefPositionY;
	private float ecefPositionZ;
	private float cubesense13v3Current;
	private float cubesense1CamSramCurrent;
	private float cubesense23v3Current;
	private float cubesense2CamSramCurrent;
	private float cubecontrol3v3Current;
	private float cubecontrol5vCurrent;
	private float cubecontrolVbatCurrent;
	private float wheel1current;
	private float wheel2current;
	private float wheel3current;
	private float cubestarcurrent;
	private float magnetorquercurrent;
	private float cubestarMcuTemperature;
	private short adcsMcuTemperature;
	private float magnetometerTemperature;
	private float redundantMagnetometerTemperature;
	private short xrateSensorTemperature;
	private short yrateSensorTemperature;
	private short zrateSensorTemperature;
	private float xAngularRate;
	private float yAngularRate;
	private float zAngularRate;
	private short xWheelspeed;
	private short yWheelspeed;
	private short zWheelspeed;

	public DhabisatBeaconPacket() {
		// do nothing
	}

	public DhabisatBeaconPacket(LittleEndianDataInputStream dis) throws IOException {
		byte[] callsignBytes = new byte[5];
		dis.readFully(callsignBytes);
		callsign = new String(callsignBytes, StandardCharsets.UTF_8);
		obcMode = ObcMode.valueOfCode(dis.readUnsignedByte());
		obcResetCount = dis.readUnsignedInt();
		obcTimestamp = dis.readUnsignedInt();
		obcUptime = dis.readUnsignedInt();
		subsystemSafetyCriteria = dis.readUnsignedByte();
		telemetryDistributionCounter = dis.readUnsignedInt();
		obcTemperature = dis.readUnsignedByte() - 128;
		cameraVoltage = dis.readUnsignedByte() / 10.0f;
		cameraCurrent = dis.readUnsignedShort();
		batteryTemperature = dis.readShort() / 100.0f;
		batteryVoltage = dis.readShort() / 1000.0f;
		adcs5vVoltage = dis.readUnsignedShort() / 1000.0f;
		adcs5vCurrent = dis.readUnsignedShort() / 1000.0f;
		adcs5vPower = dis.readUnsignedShort() / 100.0f;
		adcs3v3Voltage = dis.readUnsignedShort() / 1000.0f;
		adcs3v3Current = dis.readUnsignedShort() / 1000.0f;
		adcs3v3Power = dis.readUnsignedShort() / 100.0f;
		epsMode = dis.readUnsignedByte();
		epsResetCause = dis.readUnsignedByte();
		epsUptime = dis.readUnsignedInt();
		epsError = dis.readUnsignedShort();
		epsSystemResetCounterPowerOn = dis.readUnsignedShort();
		epsSystemResetCounterWatchdog = dis.readUnsignedShort();
		epsSystemResetCounterCommanded = dis.readUnsignedShort();
		epsSystemResetCounterController = dis.readUnsignedShort();
		epsSystemResetCounterLowPower = dis.readUnsignedShort();
		panelXpTemperature = dis.readUnsignedByte() - 128;
		panelXnTemperature = dis.readUnsignedByte() - 128;
		panelYpTemperature = dis.readUnsignedByte() - 128;
		panelYnTemperature = dis.readUnsignedByte() - 128;
		panelZpTemperature = dis.readUnsignedByte() - 128;
		panelZnTemperature = dis.readUnsignedByte() - 128;
		txPaTemp = (dis.readShort() * -0.07669f) + 195.6037f;
		int raw = dis.readUnsignedByte();
		attitudeEstimationMode = (raw >> 4) & 0b1111;
		attitudeControlMode = raw & 0b1111;
		raw = dis.readUnsignedByte();
		adcsRunMode = (raw >> 6) & 0b11;
		asgp4Mode = (raw >> 4) & 0b11;
		cubecontrolSignalEnabled = ((raw >> 3) & 0b1) > 0;
		cubecontrolMotorEnabled = ((raw >> 2) & 0b1) > 0;
		cubesense1Enabled = ((raw >> 1) & 0b1) > 0;
		cubesense2Enabled = (raw & 0b1) > 0;

		raw = dis.readUnsignedByte();
		cubewheel1Enabled = ((raw >> 7) & 0b1) > 0;
		cubewheel2Enabled = ((raw >> 6) & 0b1) > 0;
		cubewheel3Enabled = ((raw >> 5) & 0b1) > 0;
		cubestarEnabled = ((raw >> 4) & 0b1) > 0;
		gpsReceiverEnabled = ((raw >> 3) & 0b1) > 0;
		gpsLnaPowerEnabled = ((raw >> 2) & 0b1) > 0;
		motorDriverEnabled = ((raw >> 1) & 0b1) > 0;
		sunIsAboveLocalHorizon = (raw & 0b1) > 0;

		raw = dis.readUnsignedByte();
		cubesense1CommunicationsError = ((raw >> 7) & 0b1) > 0;
		cubesense2CommunicationsError = ((raw >> 6) & 0b1) > 0;
		cubecontrolSignalCommunicationsError = ((raw >> 5) & 0b1) > 0;
		cubecontrolMotorCommunicationsError = ((raw >> 4) & 0b1) > 0;
		cubewheel1CommunicationsError = ((raw >> 3) & 0b1) > 0;
		cubewheel2CommunicationsError = ((raw >> 2) & 0b1) > 0;
		cubewheel3CommunicationsError = ((raw >> 1) & 0b1) > 0;
		cubestarCommunicationsError = (raw & 0b1) > 0;

		raw = dis.readUnsignedByte();
		magnetometerRangeError = ((raw >> 7) & 0b1) > 0;
		cam1SramOvercurrentDetected = ((raw >> 6) & 0b1) > 0;
		cam13v3OvercurrentDetected = ((raw >> 5) & 0b1) > 0;
		cam1SensorBusyError = ((raw >> 4) & 0b1) > 0;
		cam1SensorDetectionError = ((raw >> 3) & 0b1) > 0;
		sunSensorRangeError = ((raw >> 2) & 0b1) > 0;
		cam2SramOvercurrentDetected = ((raw >> 1) & 0b1) > 0;
		cam23v3OvercurrentDetected = (raw & 0b1) > 0;

		raw = dis.readUnsignedByte();
		cam2SensorBusyError = ((raw >> 7) & 0b1) > 0;
		cam2SensorDetectionError = ((raw >> 6) & 0b1) > 0;
		nadirSensorRangeError = ((raw >> 5) & 0b1) > 0;
		rateSensorRangeError = ((raw >> 4) & 0b1) > 0;
		wheelSpeedRangeError = ((raw >> 3) & 0b1) > 0;
		coarseSunSensorError = ((raw >> 2) & 0b1) > 0;
		startrackerMatchError = ((raw >> 1) & 0b1) > 0;
		startrackerOvercurrentDetected = (raw & 0b1) > 0;

		raw = dis.readUnsignedByte();
		orbitParametersAreInvalid = ((raw >> 7) & 0b1) > 0;
		configurationIsInvalid = ((raw >> 6) & 0b1) > 0;
		controlModeChangeIsNotAllowed = ((raw >> 5) & 0b1) > 0;
		estimatorChangeIsNotAllowed = ((raw >> 4) & 0b1) > 0;
		currentMagnetometerSamplingMode = ((raw >> 3) & 0b11);
		modelledAndMeasuredMagneticFieldDiffersInSize = ((raw >> 1) & 0b1) > 0;
		nodeRecoveryError = (raw & 0b1) > 0;

		raw = dis.readUnsignedByte();
		cubesense1RuntimeError = ((raw >> 7) & 0b1) > 0;
		cubesense2RuntimeError = ((raw >> 6) & 0b1) > 0;
		cubecontrolSignalRuntimeError = ((raw >> 5) & 0b1) > 0;
		cubecontrolMotorRuntimeError = ((raw >> 4) & 0b1) > 0;
		cubewheel1RuntimeError = ((raw >> 3) & 0b1) > 0;
		cubewheel2RuntimeError = ((raw >> 2) & 0b1) > 0;
		cubewheel3RuntimeError = ((raw >> 1) & 0b1) > 0;
		cubestarRuntimeError = (raw & 0b1) > 0;

		raw = dis.readUnsignedByte();
		magnetometerError = ((raw >> 7) & 0b1) > 0;
		rateSensorFailure = ((raw >> 6) & 0b1) > 0;
		dis.skipBytes(3);

		estimatedRollAngle = dis.readShort() / 100.0f;
		estimatedPitchAngle = dis.readShort() / 100.0f;
		estimatedYawAngle = dis.readShort() / 100.0f;
		estimatedQ1 = dis.readShort() / 100000.0f;
		estimatedQ2 = dis.readShort() / 100000.0f;
		estimatedQ3 = dis.readShort() / 100000.0f;
		estimatedXAngularRate = dis.readShort() / 100.0f;
		estimatedYAngularRate = dis.readShort() / 100.0f;
		estimatedZAngularRate = dis.readShort() / 100.0f;
		xPosition = dis.readShort() / 100.0f;
		yPosition = dis.readShort() / 100.0f;
		zPosition = dis.readShort() / 100.0f;
		xVelocity = dis.readShort() / 100.0f;
		yVelocity = dis.readShort() / 100.0f;
		zVelocity = dis.readShort() / 100.0f;
		latitude = dis.readShort() / 100.0f;
		longitude = dis.readShort() / 100.0f;
		altitude = dis.readShort() / 100.0f;
		ecefPositionX = dis.readShort() / 100.0f;
		ecefPositionY = dis.readShort() / 100.0f;
		ecefPositionZ = dis.readShort() / 100.0f;
		cubesense13v3Current = dis.readUnsignedShort() * 0.1f;
		cubesense1CamSramCurrent = dis.readUnsignedShort() * 0.1f;
		cubesense23v3Current = dis.readUnsignedShort() * 0.1f;
		cubesense2CamSramCurrent = dis.readUnsignedShort() * 0.1f;
		cubecontrol3v3Current = dis.readUnsignedShort() * 0.48828125f;
		cubecontrol5vCurrent = dis.readUnsignedShort() * 0.48828125f;
		cubecontrolVbatCurrent = dis.readUnsignedShort() * 0.48828125f;
		wheel1current = dis.readUnsignedShort() * 0.01f;
		wheel2current = dis.readUnsignedShort() * 0.01f;
		wheel3current = dis.readUnsignedShort() * 0.01f;
		cubestarcurrent = dis.readUnsignedShort() * 0.01f;
		magnetorquercurrent = dis.readUnsignedShort() * 0.1f;
		cubestarMcuTemperature = dis.readShort() / 100.0f;
		adcsMcuTemperature = dis.readShort();
		magnetometerTemperature = dis.readShort() / 10.0f;
		redundantMagnetometerTemperature = dis.readShort() / 10.0f;
		xrateSensorTemperature = dis.readShort();
		yrateSensorTemperature = dis.readShort();
		zrateSensorTemperature = dis.readShort();
		xAngularRate = dis.readShort() / 100.0f;
		yAngularRate = dis.readShort() / 100.0f;
		zAngularRate = dis.readShort() / 100.0f;
		xWheelspeed = dis.readShort();
		yWheelspeed = dis.readShort();
		zWheelspeed = dis.readShort();
	}

	public String getCallsign() {
		return callsign;
	}

	public void setCallsign(String callsign) {
		this.callsign = callsign;
	}

	public ObcMode getObcMode() {
		return obcMode;
	}

	public void setObcMode(ObcMode obcMode) {
		this.obcMode = obcMode;
	}

	public long getObcResetCount() {
		return obcResetCount;
	}

	public void setObcResetCount(long obcResetCount) {
		this.obcResetCount = obcResetCount;
	}

	public long getObcTimestamp() {
		return obcTimestamp;
	}

	public void setObcTimestamp(long obcTimestamp) {
		this.obcTimestamp = obcTimestamp;
	}

	public long getObcUptime() {
		return obcUptime;
	}

	public void setObcUptime(long obcUptime) {
		this.obcUptime = obcUptime;
	}

	public int getSubsystemSafetyCriteria() {
		return subsystemSafetyCriteria;
	}

	public void setSubsystemSafetyCriteria(int subsystemSafetyCriteria) {
		this.subsystemSafetyCriteria = subsystemSafetyCriteria;
	}

	public long getTelemetryDistributionCounter() {
		return telemetryDistributionCounter;
	}

	public void setTelemetryDistributionCounter(long telemetryDistributionCounter) {
		this.telemetryDistributionCounter = telemetryDistributionCounter;
	}

	public int getObcTemperature() {
		return obcTemperature;
	}

	public void setObcTemperature(int obcTemperature) {
		this.obcTemperature = obcTemperature;
	}

	public float getCameraVoltage() {
		return cameraVoltage;
	}

	public void setCameraVoltage(float cameraVoltage) {
		this.cameraVoltage = cameraVoltage;
	}

	public int getCameraCurrent() {
		return cameraCurrent;
	}

	public void setCameraCurrent(int cameraCurrent) {
		this.cameraCurrent = cameraCurrent;
	}

	public float getBatteryTemperature() {
		return batteryTemperature;
	}

	public void setBatteryTemperature(float batteryTemperature) {
		this.batteryTemperature = batteryTemperature;
	}

	public float getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(float batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public float getAdcs5vVoltage() {
		return adcs5vVoltage;
	}

	public void setAdcs5vVoltage(float adcs5vVoltage) {
		this.adcs5vVoltage = adcs5vVoltage;
	}

	public float getAdcs5vCurrent() {
		return adcs5vCurrent;
	}

	public void setAdcs5vCurrent(float adcs5vCurrent) {
		this.adcs5vCurrent = adcs5vCurrent;
	}

	public float getAdcs5vPower() {
		return adcs5vPower;
	}

	public void setAdcs5vPower(float adcs5vPower) {
		this.adcs5vPower = adcs5vPower;
	}

	public float getAdcs3v3Voltage() {
		return adcs3v3Voltage;
	}

	public void setAdcs3v3Voltage(float adcs3v3Voltage) {
		this.adcs3v3Voltage = adcs3v3Voltage;
	}

	public float getAdcs3v3Current() {
		return adcs3v3Current;
	}

	public void setAdcs3v3Current(float adcs3v3Current) {
		this.adcs3v3Current = adcs3v3Current;
	}

	public float getAdcs3v3Power() {
		return adcs3v3Power;
	}

	public void setAdcs3v3Power(float adcs3v3Power) {
		this.adcs3v3Power = adcs3v3Power;
	}

	public int getEpsMode() {
		return epsMode;
	}

	public void setEpsMode(int epsMode) {
		this.epsMode = epsMode;
	}

	public int getEpsResetCause() {
		return epsResetCause;
	}

	public void setEpsResetCause(int epsResetCause) {
		this.epsResetCause = epsResetCause;
	}

	public long getEpsUptime() {
		return epsUptime;
	}

	public void setEpsUptime(long epsUptime) {
		this.epsUptime = epsUptime;
	}

	public int getEpsError() {
		return epsError;
	}

	public void setEpsError(int epsError) {
		this.epsError = epsError;
	}

	public int getEpsSystemResetCounterPowerOn() {
		return epsSystemResetCounterPowerOn;
	}

	public void setEpsSystemResetCounterPowerOn(int epsSystemResetCounterPowerOn) {
		this.epsSystemResetCounterPowerOn = epsSystemResetCounterPowerOn;
	}

	public int getEpsSystemResetCounterWatchdog() {
		return epsSystemResetCounterWatchdog;
	}

	public void setEpsSystemResetCounterWatchdog(int epsSystemResetCounterWatchdog) {
		this.epsSystemResetCounterWatchdog = epsSystemResetCounterWatchdog;
	}

	public int getEpsSystemResetCounterCommanded() {
		return epsSystemResetCounterCommanded;
	}

	public void setEpsSystemResetCounterCommanded(int epsSystemResetCounterCommanded) {
		this.epsSystemResetCounterCommanded = epsSystemResetCounterCommanded;
	}

	public int getEpsSystemResetCounterController() {
		return epsSystemResetCounterController;
	}

	public void setEpsSystemResetCounterController(int epsSystemResetCounterController) {
		this.epsSystemResetCounterController = epsSystemResetCounterController;
	}

	public int getEpsSystemResetCounterLowPower() {
		return epsSystemResetCounterLowPower;
	}

	public void setEpsSystemResetCounterLowPower(int epsSystemResetCounterLowPower) {
		this.epsSystemResetCounterLowPower = epsSystemResetCounterLowPower;
	}

	public int getPanelXpTemperature() {
		return panelXpTemperature;
	}

	public void setPanelXpTemperature(int panelXpTemperature) {
		this.panelXpTemperature = panelXpTemperature;
	}

	public int getPanelXnTemperature() {
		return panelXnTemperature;
	}

	public void setPanelXnTemperature(int panelXnTemperature) {
		this.panelXnTemperature = panelXnTemperature;
	}

	public int getPanelYpTemperature() {
		return panelYpTemperature;
	}

	public void setPanelYpTemperature(int panelYpTemperature) {
		this.panelYpTemperature = panelYpTemperature;
	}

	public int getPanelYnTemperature() {
		return panelYnTemperature;
	}

	public void setPanelYnTemperature(int panelYnTemperature) {
		this.panelYnTemperature = panelYnTemperature;
	}

	public int getPanelZpTemperature() {
		return panelZpTemperature;
	}

	public void setPanelZpTemperature(int panelZpTemperature) {
		this.panelZpTemperature = panelZpTemperature;
	}

	public int getPanelZnTemperature() {
		return panelZnTemperature;
	}

	public void setPanelZnTemperature(int panelZnTemperature) {
		this.panelZnTemperature = panelZnTemperature;
	}

	public float getTxPaTemp() {
		return txPaTemp;
	}

	public void setTxPaTemp(float txPaTemp) {
		this.txPaTemp = txPaTemp;
	}

	public int getAttitudeEstimationMode() {
		return attitudeEstimationMode;
	}

	public void setAttitudeEstimationMode(int attitudeEstimationMode) {
		this.attitudeEstimationMode = attitudeEstimationMode;
	}

	public int getAttitudeControlMode() {
		return attitudeControlMode;
	}

	public void setAttitudeControlMode(int attitudeControlMode) {
		this.attitudeControlMode = attitudeControlMode;
	}

	public int getAdcsRunMode() {
		return adcsRunMode;
	}

	public void setAdcsRunMode(int adcsRunMode) {
		this.adcsRunMode = adcsRunMode;
	}

	public int getAsgp4Mode() {
		return asgp4Mode;
	}

	public void setAsgp4Mode(int asgp4Mode) {
		this.asgp4Mode = asgp4Mode;
	}

	public boolean isCubecontrolSignalEnabled() {
		return cubecontrolSignalEnabled;
	}

	public void setCubecontrolSignalEnabled(boolean cubecontrolSignalEnabled) {
		this.cubecontrolSignalEnabled = cubecontrolSignalEnabled;
	}

	public boolean isCubecontrolMotorEnabled() {
		return cubecontrolMotorEnabled;
	}

	public void setCubecontrolMotorEnabled(boolean cubecontrolMotorEnabled) {
		this.cubecontrolMotorEnabled = cubecontrolMotorEnabled;
	}

	public boolean isCubesense1Enabled() {
		return cubesense1Enabled;
	}

	public void setCubesense1Enabled(boolean cubesense1Enabled) {
		this.cubesense1Enabled = cubesense1Enabled;
	}

	public boolean isCubesense2Enabled() {
		return cubesense2Enabled;
	}

	public void setCubesense2Enabled(boolean cubesense2Enabled) {
		this.cubesense2Enabled = cubesense2Enabled;
	}

	public boolean isCubewheel1Enabled() {
		return cubewheel1Enabled;
	}

	public void setCubewheel1Enabled(boolean cubewheel1Enabled) {
		this.cubewheel1Enabled = cubewheel1Enabled;
	}

	public boolean isCubewheel2Enabled() {
		return cubewheel2Enabled;
	}

	public void setCubewheel2Enabled(boolean cubewheel2Enabled) {
		this.cubewheel2Enabled = cubewheel2Enabled;
	}

	public boolean isCubewheel3Enabled() {
		return cubewheel3Enabled;
	}

	public void setCubewheel3Enabled(boolean cubewheel3Enabled) {
		this.cubewheel3Enabled = cubewheel3Enabled;
	}

	public boolean isCubestarEnabled() {
		return cubestarEnabled;
	}

	public void setCubestarEnabled(boolean cubestarEnabled) {
		this.cubestarEnabled = cubestarEnabled;
	}

	public boolean isGpsReceiverEnabled() {
		return gpsReceiverEnabled;
	}

	public void setGpsReceiverEnabled(boolean gpsReceiverEnabled) {
		this.gpsReceiverEnabled = gpsReceiverEnabled;
	}

	public boolean isGpsLnaPowerEnabled() {
		return gpsLnaPowerEnabled;
	}

	public void setGpsLnaPowerEnabled(boolean gpsLnaPowerEnabled) {
		this.gpsLnaPowerEnabled = gpsLnaPowerEnabled;
	}

	public boolean isMotorDriverEnabled() {
		return motorDriverEnabled;
	}

	public void setMotorDriverEnabled(boolean motorDriverEnabled) {
		this.motorDriverEnabled = motorDriverEnabled;
	}

	public boolean isSunIsAboveLocalHorizon() {
		return sunIsAboveLocalHorizon;
	}

	public void setSunIsAboveLocalHorizon(boolean sunIsAboveLocalHorizon) {
		this.sunIsAboveLocalHorizon = sunIsAboveLocalHorizon;
	}

	public boolean isCubesense1CommunicationsError() {
		return cubesense1CommunicationsError;
	}

	public void setCubesense1CommunicationsError(boolean cubesense1CommunicationsError) {
		this.cubesense1CommunicationsError = cubesense1CommunicationsError;
	}

	public boolean isCubesense2CommunicationsError() {
		return cubesense2CommunicationsError;
	}

	public void setCubesense2CommunicationsError(boolean cubesense2CommunicationsError) {
		this.cubesense2CommunicationsError = cubesense2CommunicationsError;
	}

	public boolean isCubecontrolSignalCommunicationsError() {
		return cubecontrolSignalCommunicationsError;
	}

	public void setCubecontrolSignalCommunicationsError(boolean cubecontrolSignalCommunicationsError) {
		this.cubecontrolSignalCommunicationsError = cubecontrolSignalCommunicationsError;
	}

	public boolean isCubecontrolMotorCommunicationsError() {
		return cubecontrolMotorCommunicationsError;
	}

	public void setCubecontrolMotorCommunicationsError(boolean cubecontrolMotorCommunicationsError) {
		this.cubecontrolMotorCommunicationsError = cubecontrolMotorCommunicationsError;
	}

	public boolean isCubewheel1CommunicationsError() {
		return cubewheel1CommunicationsError;
	}

	public void setCubewheel1CommunicationsError(boolean cubewheel1CommunicationsError) {
		this.cubewheel1CommunicationsError = cubewheel1CommunicationsError;
	}

	public boolean isCubewheel2CommunicationsError() {
		return cubewheel2CommunicationsError;
	}

	public void setCubewheel2CommunicationsError(boolean cubewheel2CommunicationsError) {
		this.cubewheel2CommunicationsError = cubewheel2CommunicationsError;
	}

	public boolean isCubewheel3CommunicationsError() {
		return cubewheel3CommunicationsError;
	}

	public void setCubewheel3CommunicationsError(boolean cubewheel3CommunicationsError) {
		this.cubewheel3CommunicationsError = cubewheel3CommunicationsError;
	}

	public boolean isCubestarCommunicationsError() {
		return cubestarCommunicationsError;
	}

	public void setCubestarCommunicationsError(boolean cubestarCommunicationsError) {
		this.cubestarCommunicationsError = cubestarCommunicationsError;
	}

	public boolean isMagnetometerRangeError() {
		return magnetometerRangeError;
	}

	public void setMagnetometerRangeError(boolean magnetometerRangeError) {
		this.magnetometerRangeError = magnetometerRangeError;
	}

	public boolean isCam1SramOvercurrentDetected() {
		return cam1SramOvercurrentDetected;
	}

	public void setCam1SramOvercurrentDetected(boolean cam1SramOvercurrentDetected) {
		this.cam1SramOvercurrentDetected = cam1SramOvercurrentDetected;
	}

	public boolean isCam13v3OvercurrentDetected() {
		return cam13v3OvercurrentDetected;
	}

	public void setCam13v3OvercurrentDetected(boolean cam13v3OvercurrentDetected) {
		this.cam13v3OvercurrentDetected = cam13v3OvercurrentDetected;
	}

	public boolean isCam1SensorBusyError() {
		return cam1SensorBusyError;
	}

	public void setCam1SensorBusyError(boolean cam1SensorBusyError) {
		this.cam1SensorBusyError = cam1SensorBusyError;
	}

	public boolean isCam1SensorDetectionError() {
		return cam1SensorDetectionError;
	}

	public void setCam1SensorDetectionError(boolean cam1SensorDetectionError) {
		this.cam1SensorDetectionError = cam1SensorDetectionError;
	}

	public boolean isSunSensorRangeError() {
		return sunSensorRangeError;
	}

	public void setSunSensorRangeError(boolean sunSensorRangeError) {
		this.sunSensorRangeError = sunSensorRangeError;
	}

	public boolean isCam2SramOvercurrentDetected() {
		return cam2SramOvercurrentDetected;
	}

	public void setCam2SramOvercurrentDetected(boolean cam2SramOvercurrentDetected) {
		this.cam2SramOvercurrentDetected = cam2SramOvercurrentDetected;
	}

	public boolean isCam23v3OvercurrentDetected() {
		return cam23v3OvercurrentDetected;
	}

	public void setCam23v3OvercurrentDetected(boolean cam23v3OvercurrentDetected) {
		this.cam23v3OvercurrentDetected = cam23v3OvercurrentDetected;
	}

	public boolean isCam2SensorBusyError() {
		return cam2SensorBusyError;
	}

	public void setCam2SensorBusyError(boolean cam2SensorBusyError) {
		this.cam2SensorBusyError = cam2SensorBusyError;
	}

	public boolean isCam2SensorDetectionError() {
		return cam2SensorDetectionError;
	}

	public void setCam2SensorDetectionError(boolean cam2SensorDetectionError) {
		this.cam2SensorDetectionError = cam2SensorDetectionError;
	}

	public boolean isNadirSensorRangeError() {
		return nadirSensorRangeError;
	}

	public void setNadirSensorRangeError(boolean nadirSensorRangeError) {
		this.nadirSensorRangeError = nadirSensorRangeError;
	}

	public boolean isRateSensorRangeError() {
		return rateSensorRangeError;
	}

	public void setRateSensorRangeError(boolean rateSensorRangeError) {
		this.rateSensorRangeError = rateSensorRangeError;
	}

	public boolean isWheelSpeedRangeError() {
		return wheelSpeedRangeError;
	}

	public void setWheelSpeedRangeError(boolean wheelSpeedRangeError) {
		this.wheelSpeedRangeError = wheelSpeedRangeError;
	}

	public boolean isCoarseSunSensorError() {
		return coarseSunSensorError;
	}

	public void setCoarseSunSensorError(boolean coarseSunSensorError) {
		this.coarseSunSensorError = coarseSunSensorError;
	}

	public boolean isStartrackerMatchError() {
		return startrackerMatchError;
	}

	public void setStartrackerMatchError(boolean startrackerMatchError) {
		this.startrackerMatchError = startrackerMatchError;
	}

	public boolean isStartrackerOvercurrentDetected() {
		return startrackerOvercurrentDetected;
	}

	public void setStartrackerOvercurrentDetected(boolean startrackerOvercurrentDetected) {
		this.startrackerOvercurrentDetected = startrackerOvercurrentDetected;
	}

	public boolean isOrbitParametersAreInvalid() {
		return orbitParametersAreInvalid;
	}

	public void setOrbitParametersAreInvalid(boolean orbitParametersAreInvalid) {
		this.orbitParametersAreInvalid = orbitParametersAreInvalid;
	}

	public boolean isConfigurationIsInvalid() {
		return configurationIsInvalid;
	}

	public void setConfigurationIsInvalid(boolean configurationIsInvalid) {
		this.configurationIsInvalid = configurationIsInvalid;
	}

	public boolean isControlModeChangeIsNotAllowed() {
		return controlModeChangeIsNotAllowed;
	}

	public void setControlModeChangeIsNotAllowed(boolean controlModeChangeIsNotAllowed) {
		this.controlModeChangeIsNotAllowed = controlModeChangeIsNotAllowed;
	}

	public boolean isEstimatorChangeIsNotAllowed() {
		return estimatorChangeIsNotAllowed;
	}

	public void setEstimatorChangeIsNotAllowed(boolean estimatorChangeIsNotAllowed) {
		this.estimatorChangeIsNotAllowed = estimatorChangeIsNotAllowed;
	}

	public int getCurrentMagnetometerSamplingMode() {
		return currentMagnetometerSamplingMode;
	}

	public void setCurrentMagnetometerSamplingMode(int currentMagnetometerSamplingMode) {
		this.currentMagnetometerSamplingMode = currentMagnetometerSamplingMode;
	}

	public boolean isModelledAndMeasuredMagneticFieldDiffersInSize() {
		return modelledAndMeasuredMagneticFieldDiffersInSize;
	}

	public void setModelledAndMeasuredMagneticFieldDiffersInSize(boolean modelledAndMeasuredMagneticFieldDiffersInSize) {
		this.modelledAndMeasuredMagneticFieldDiffersInSize = modelledAndMeasuredMagneticFieldDiffersInSize;
	}

	public boolean isNodeRecoveryError() {
		return nodeRecoveryError;
	}

	public void setNodeRecoveryError(boolean nodeRecoveryError) {
		this.nodeRecoveryError = nodeRecoveryError;
	}

	public boolean isCubesense1RuntimeError() {
		return cubesense1RuntimeError;
	}

	public void setCubesense1RuntimeError(boolean cubesense1RuntimeError) {
		this.cubesense1RuntimeError = cubesense1RuntimeError;
	}

	public boolean isCubesense2RuntimeError() {
		return cubesense2RuntimeError;
	}

	public void setCubesense2RuntimeError(boolean cubesense2RuntimeError) {
		this.cubesense2RuntimeError = cubesense2RuntimeError;
	}

	public boolean isCubecontrolSignalRuntimeError() {
		return cubecontrolSignalRuntimeError;
	}

	public void setCubecontrolSignalRuntimeError(boolean cubecontrolSignalRuntimeError) {
		this.cubecontrolSignalRuntimeError = cubecontrolSignalRuntimeError;
	}

	public boolean isCubecontrolMotorRuntimeError() {
		return cubecontrolMotorRuntimeError;
	}

	public void setCubecontrolMotorRuntimeError(boolean cubecontrolMotorRuntimeError) {
		this.cubecontrolMotorRuntimeError = cubecontrolMotorRuntimeError;
	}

	public boolean isCubewheel1RuntimeError() {
		return cubewheel1RuntimeError;
	}

	public void setCubewheel1RuntimeError(boolean cubewheel1RuntimeError) {
		this.cubewheel1RuntimeError = cubewheel1RuntimeError;
	}

	public boolean isCubewheel2RuntimeError() {
		return cubewheel2RuntimeError;
	}

	public void setCubewheel2RuntimeError(boolean cubewheel2RuntimeError) {
		this.cubewheel2RuntimeError = cubewheel2RuntimeError;
	}

	public boolean isCubewheel3RuntimeError() {
		return cubewheel3RuntimeError;
	}

	public void setCubewheel3RuntimeError(boolean cubewheel3RuntimeError) {
		this.cubewheel3RuntimeError = cubewheel3RuntimeError;
	}

	public boolean isCubestarRuntimeError() {
		return cubestarRuntimeError;
	}

	public void setCubestarRuntimeError(boolean cubestarRuntimeError) {
		this.cubestarRuntimeError = cubestarRuntimeError;
	}

	public boolean isMagnetometerError() {
		return magnetometerError;
	}

	public void setMagnetometerError(boolean magnetometerError) {
		this.magnetometerError = magnetometerError;
	}

	public boolean isRateSensorFailure() {
		return rateSensorFailure;
	}

	public void setRateSensorFailure(boolean rateSensorFailure) {
		this.rateSensorFailure = rateSensorFailure;
	}

	public float getEstimatedRollAngle() {
		return estimatedRollAngle;
	}

	public void setEstimatedRollAngle(float estimatedRollAngle) {
		this.estimatedRollAngle = estimatedRollAngle;
	}

	public float getEstimatedPitchAngle() {
		return estimatedPitchAngle;
	}

	public void setEstimatedPitchAngle(float estimatedPitchAngle) {
		this.estimatedPitchAngle = estimatedPitchAngle;
	}

	public float getEstimatedYawAngle() {
		return estimatedYawAngle;
	}

	public void setEstimatedYawAngle(float estimatedYawAngle) {
		this.estimatedYawAngle = estimatedYawAngle;
	}

	public float getEstimatedQ1() {
		return estimatedQ1;
	}

	public void setEstimatedQ1(float estimatedQ1) {
		this.estimatedQ1 = estimatedQ1;
	}

	public float getEstimatedQ2() {
		return estimatedQ2;
	}

	public void setEstimatedQ2(float estimatedQ2) {
		this.estimatedQ2 = estimatedQ2;
	}

	public float getEstimatedQ3() {
		return estimatedQ3;
	}

	public void setEstimatedQ3(float estimatedQ3) {
		this.estimatedQ3 = estimatedQ3;
	}

	public float getEstimatedXAngularRate() {
		return estimatedXAngularRate;
	}

	public void setEstimatedXAngularRate(float estimatedXAngularRate) {
		this.estimatedXAngularRate = estimatedXAngularRate;
	}

	public float getEstimatedYAngularRate() {
		return estimatedYAngularRate;
	}

	public void setEstimatedYAngularRate(float estimatedYAngularRate) {
		this.estimatedYAngularRate = estimatedYAngularRate;
	}

	public float getEstimatedZAngularRate() {
		return estimatedZAngularRate;
	}

	public void setEstimatedZAngularRate(float estimatedZAngularRate) {
		this.estimatedZAngularRate = estimatedZAngularRate;
	}

	public float getxPosition() {
		return xPosition;
	}

	public void setxPosition(float xPosition) {
		this.xPosition = xPosition;
	}

	public float getyPosition() {
		return yPosition;
	}

	public void setyPosition(float yPosition) {
		this.yPosition = yPosition;
	}

	public float getzPosition() {
		return zPosition;
	}

	public void setzPosition(float zPosition) {
		this.zPosition = zPosition;
	}

	public float getxVelocity() {
		return xVelocity;
	}

	public void setxVelocity(float xVelocity) {
		this.xVelocity = xVelocity;
	}

	public float getyVelocity() {
		return yVelocity;
	}

	public void setyVelocity(float yVelocity) {
		this.yVelocity = yVelocity;
	}

	public float getzVelocity() {
		return zVelocity;
	}

	public void setzVelocity(float zVelocity) {
		this.zVelocity = zVelocity;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getAltitude() {
		return altitude;
	}

	public void setAltitude(float altitude) {
		this.altitude = altitude;
	}

	public float getEcefPositionX() {
		return ecefPositionX;
	}

	public void setEcefPositionX(float ecefPositionX) {
		this.ecefPositionX = ecefPositionX;
	}

	public float getEcefPositionY() {
		return ecefPositionY;
	}

	public void setEcefPositionY(float ecefPositionY) {
		this.ecefPositionY = ecefPositionY;
	}

	public float getEcefPositionZ() {
		return ecefPositionZ;
	}

	public void setEcefPositionZ(float ecefPositionZ) {
		this.ecefPositionZ = ecefPositionZ;
	}

	public float getCubesense13v3Current() {
		return cubesense13v3Current;
	}

	public void setCubesense13v3Current(float cubesense13v3Current) {
		this.cubesense13v3Current = cubesense13v3Current;
	}

	public float getCubesense1CamSramCurrent() {
		return cubesense1CamSramCurrent;
	}

	public void setCubesense1CamSramCurrent(float cubesense1CamSramCurrent) {
		this.cubesense1CamSramCurrent = cubesense1CamSramCurrent;
	}

	public float getCubesense23v3Current() {
		return cubesense23v3Current;
	}

	public void setCubesense23v3Current(float cubesense23v3Current) {
		this.cubesense23v3Current = cubesense23v3Current;
	}

	public float getCubesense2CamSramCurrent() {
		return cubesense2CamSramCurrent;
	}

	public void setCubesense2CamSramCurrent(float cubesense2CamSramCurrent) {
		this.cubesense2CamSramCurrent = cubesense2CamSramCurrent;
	}

	public float getCubecontrol3v3Current() {
		return cubecontrol3v3Current;
	}

	public void setCubecontrol3v3Current(float cubecontrol3v3Current) {
		this.cubecontrol3v3Current = cubecontrol3v3Current;
	}

	public float getCubecontrol5vCurrent() {
		return cubecontrol5vCurrent;
	}

	public void setCubecontrol5vCurrent(float cubecontrol5vCurrent) {
		this.cubecontrol5vCurrent = cubecontrol5vCurrent;
	}

	public float getCubecontrolVbatCurrent() {
		return cubecontrolVbatCurrent;
	}

	public void setCubecontrolVbatCurrent(float cubecontrolVbatCurrent) {
		this.cubecontrolVbatCurrent = cubecontrolVbatCurrent;
	}

	public float getWheel1current() {
		return wheel1current;
	}

	public void setWheel1current(float wheel1current) {
		this.wheel1current = wheel1current;
	}

	public float getWheel2current() {
		return wheel2current;
	}

	public void setWheel2current(float wheel2current) {
		this.wheel2current = wheel2current;
	}

	public float getWheel3current() {
		return wheel3current;
	}

	public void setWheel3current(float wheel3current) {
		this.wheel3current = wheel3current;
	}

	public float getCubestarcurrent() {
		return cubestarcurrent;
	}

	public void setCubestarcurrent(float cubestarcurrent) {
		this.cubestarcurrent = cubestarcurrent;
	}

	public float getMagnetorquercurrent() {
		return magnetorquercurrent;
	}

	public void setMagnetorquercurrent(float magnetorquercurrent) {
		this.magnetorquercurrent = magnetorquercurrent;
	}

	public float getCubestarMcuTemperature() {
		return cubestarMcuTemperature;
	}

	public void setCubestarMcuTemperature(float cubestarMcuTemperature) {
		this.cubestarMcuTemperature = cubestarMcuTemperature;
	}

	public short getAdcsMcuTemperature() {
		return adcsMcuTemperature;
	}

	public void setAdcsMcuTemperature(short adcsMcuTemperature) {
		this.adcsMcuTemperature = adcsMcuTemperature;
	}

	public float getMagnetometerTemperature() {
		return magnetometerTemperature;
	}

	public void setMagnetometerTemperature(float magnetometerTemperature) {
		this.magnetometerTemperature = magnetometerTemperature;
	}

	public float getRedundantMagnetometerTemperature() {
		return redundantMagnetometerTemperature;
	}

	public void setRedundantMagnetometerTemperature(float redundantMagnetometerTemperature) {
		this.redundantMagnetometerTemperature = redundantMagnetometerTemperature;
	}

	public short getXrateSensorTemperature() {
		return xrateSensorTemperature;
	}

	public void setXrateSensorTemperature(short xrateSensorTemperature) {
		this.xrateSensorTemperature = xrateSensorTemperature;
	}

	public short getYrateSensorTemperature() {
		return yrateSensorTemperature;
	}

	public void setYrateSensorTemperature(short yrateSensorTemperature) {
		this.yrateSensorTemperature = yrateSensorTemperature;
	}

	public short getZrateSensorTemperature() {
		return zrateSensorTemperature;
	}

	public void setZrateSensorTemperature(short zrateSensorTemperature) {
		this.zrateSensorTemperature = zrateSensorTemperature;
	}

	public float getxAngularRate() {
		return xAngularRate;
	}

	public void setxAngularRate(float xAngularRate) {
		this.xAngularRate = xAngularRate;
	}

	public float getyAngularRate() {
		return yAngularRate;
	}

	public void setyAngularRate(float yAngularRate) {
		this.yAngularRate = yAngularRate;
	}

	public float getzAngularRate() {
		return zAngularRate;
	}

	public void setzAngularRate(float zAngularRate) {
		this.zAngularRate = zAngularRate;
	}

	public short getxWheelspeed() {
		return xWheelspeed;
	}

	public void setxWheelspeed(short xWheelspeed) {
		this.xWheelspeed = xWheelspeed;
	}

	public short getyWheelspeed() {
		return yWheelspeed;
	}

	public void setyWheelspeed(short yWheelspeed) {
		this.yWheelspeed = yWheelspeed;
	}

	public short getzWheelspeed() {
		return zWheelspeed;
	}

	public void setzWheelspeed(short zWheelspeed) {
		this.zWheelspeed = zWheelspeed;
	}

}
