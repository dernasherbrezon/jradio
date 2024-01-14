package ru.r2cloud.jradio.randev;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianBitInputStream;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Adcs {

	private EstimationMode estimationMode;
	private ControlMode controlMode;
	private AdcsMode adcsMode;
	private Asgp4Mode asgp4Mode;
	private boolean cubecontrolSignalEnabled;
	private boolean cubecontrolMotorEnabled;
	private boolean cubesence1Enabled;
	private boolean cubesence2Enable;
	private boolean cubewheel1Enabled;
	private boolean cubewheel2Enabled;
	private boolean cubewheel3Enabled;
	private boolean cubestarEnabled;
	private boolean gpsReciverEnabled;
	private boolean gpsLnaPowerEnabled;
	private boolean motorDriverEnabled;
	private boolean sunIsAboveLocalHorizon;
	private boolean errors;
	private boolean cubesence1CommError;
	private boolean cubesence2CommError;
	private boolean cubecontrolSignalCommError;
	private boolean cubecontrolMotorCommError;
	private boolean cubewheel1CommError;
	private boolean cubewhee2CommError;
	private boolean cubewheel3CommError;
	private boolean cubestarCommError;
	private boolean magnetometerRangeError;
	private boolean sunsensorSramOvercurrentDetected;
	private boolean sunsensor3v3OvercurrentDetected;
	private boolean sunsensorBusyError;
	private boolean sunsensorDetectionError;
	private boolean sunsensorRangeError;
	private boolean nadirSensorSramOvercurrentDetected;
	private boolean nadirSensor3v3OvercurrentDetected;
	private boolean nadirSensorBusyError;
	private boolean nadirSensorDetectionError;
	private boolean nadirSensorRangeError;
	private boolean rateSensorRangeError;
	private boolean wheelSpeedRangeError;
	private boolean coarseSunsensorError;
	private boolean startrackerMatchError;
	private boolean startrackerOvercurrentDetected;
	private boolean orbitParametersInvalid;
	private boolean configurationInvalid;
	private boolean controlModeChangeAllowed;
	private boolean estimatorChangeNotAllowed;
	private MagnetometerMode magnetometerMode;
	private boolean modelledMeasuredMagneticFieldMissmatch;
	private boolean nodeRecoveryError;
	private boolean cubesense1RuntimeError;
	private boolean cubesense2RuntimeError;
	private boolean cubecontrolSignalRuntimeError;
	private boolean cubecontrolMotorUntimeError;
	private boolean cubewheel1RuntimeError;
	private boolean cubewheel2RuntimeError;
	private boolean cubewheel3RuntimeError;
	private boolean cubestarRuntimeError;
	private boolean magnetometerError;
	private boolean rateSensorFailure;
	private float rollDegree;
	private float pitchDegree;
	private float yawDegree;
	private float q1;
	private float q2;
	private float q3;
	private float xAngularRateDegreeS;
	private float yAngularRateDegreeS;
	private float zAngularRateDegreeS;

	public Adcs() {
		// do nothing
	}

	public Adcs(LittleEndianDataInputStream ldis) throws IOException {
		LittleEndianBitInputStream dis = new LittleEndianBitInputStream(ldis);
		estimationMode = EstimationMode.valueOfCode(dis.readUnsignedInt(4));
		controlMode = ControlMode.valueOfCode(dis.readUnsignedInt(4));
		adcsMode = AdcsMode.valueOfCode(dis.readUnsignedInt(2));
		asgp4Mode = Asgp4Mode.valueOfCode(dis.readUnsignedInt(2));
		cubecontrolSignalEnabled = dis.readBoolean();
		cubecontrolMotorEnabled = dis.readBoolean();
		cubesence1Enabled = dis.readBoolean();
		cubesence2Enable = dis.readBoolean();
		cubewheel1Enabled = dis.readBoolean();
		cubewheel2Enabled = dis.readBoolean();
		cubewheel3Enabled = dis.readBoolean();
		cubestarEnabled = dis.readBoolean();
		gpsReciverEnabled = dis.readBoolean();
		gpsLnaPowerEnabled = dis.readBoolean();
		motorDriverEnabled = dis.readBoolean();
		sunIsAboveLocalHorizon = dis.readBoolean();
		errors = dis.readBoolean();
		cubesence1CommError = dis.readBoolean();
		cubesence2CommError = dis.readBoolean();
		cubecontrolSignalCommError = dis.readBoolean();
		cubecontrolMotorCommError = dis.readBoolean();
		cubewheel1CommError = dis.readBoolean();
		cubewhee2CommError = dis.readBoolean();
		cubewheel3CommError = dis.readBoolean();
		cubestarCommError = dis.readBoolean();
		magnetometerRangeError = dis.readBoolean();
		sunsensorSramOvercurrentDetected = dis.readBoolean();
		sunsensor3v3OvercurrentDetected = dis.readBoolean();
		sunsensorBusyError = dis.readBoolean();
		sunsensorDetectionError = dis.readBoolean();
		sunsensorRangeError = dis.readBoolean();
		nadirSensorSramOvercurrentDetected = dis.readBoolean();
		nadirSensor3v3OvercurrentDetected = dis.readBoolean();
		nadirSensorBusyError = dis.readBoolean();
		nadirSensorDetectionError = dis.readBoolean();
		nadirSensorRangeError = dis.readBoolean();
		rateSensorRangeError = dis.readBoolean();
		wheelSpeedRangeError = dis.readBoolean();
		coarseSunsensorError = dis.readBoolean();
		startrackerMatchError = dis.readBoolean();
		startrackerOvercurrentDetected = dis.readBoolean();
		orbitParametersInvalid = dis.readBoolean();
		configurationInvalid = dis.readBoolean();
		controlModeChangeAllowed = dis.readBoolean();
		estimatorChangeNotAllowed = dis.readBoolean();
		magnetometerMode = MagnetometerMode.valueOfCode(dis.readUnsignedInt(2));
		modelledMeasuredMagneticFieldMissmatch = dis.readBoolean();
		nodeRecoveryError = dis.readBoolean();
		cubesense1RuntimeError = dis.readBoolean();
		cubesense2RuntimeError = dis.readBoolean();
		cubecontrolSignalRuntimeError = dis.readBoolean();
		cubecontrolMotorUntimeError = dis.readBoolean();
		cubewheel1RuntimeError = dis.readBoolean();
		cubewheel2RuntimeError = dis.readBoolean();
		cubewheel3RuntimeError = dis.readBoolean();
		cubestarRuntimeError = dis.readBoolean();
		magnetometerError = dis.readBoolean();
		rateSensorFailure = dis.readBoolean();
		rollDegree = dis.readShort() * 0.01f;
		pitchDegree = dis.readShort() * 0.01f;
		yawDegree = dis.readShort() * 0.01f;
		q1 = dis.readShort() * 0.0001f;
		q2 = dis.readShort() * 0.0001f;
		q3 = dis.readShort() * 0.0001f;
		xAngularRateDegreeS = dis.readShort() * 0.01f;
		yAngularRateDegreeS = dis.readShort() * 0.01f;
		zAngularRateDegreeS = dis.readShort() * 0.01f;
	}

	public EstimationMode getEstimationMode() {
		return estimationMode;
	}

	public void setEstimationMode(EstimationMode estimationMode) {
		this.estimationMode = estimationMode;
	}

	public ControlMode getControlMode() {
		return controlMode;
	}

	public void setControlMode(ControlMode controlMode) {
		this.controlMode = controlMode;
	}

	public AdcsMode getAdcsMode() {
		return adcsMode;
	}

	public void setAdcsMode(AdcsMode adcsMode) {
		this.adcsMode = adcsMode;
	}

	public Asgp4Mode getAsgp4Mode() {
		return asgp4Mode;
	}

	public void setAsgp4Mode(Asgp4Mode asgp4Mode) {
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

	public boolean isCubesence1Enabled() {
		return cubesence1Enabled;
	}

	public void setCubesence1Enabled(boolean cubesence1Enabled) {
		this.cubesence1Enabled = cubesence1Enabled;
	}

	public boolean isCubesence2Enable() {
		return cubesence2Enable;
	}

	public void setCubesence2Enable(boolean cubesence2Enable) {
		this.cubesence2Enable = cubesence2Enable;
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

	public boolean isGpsReciverEnabled() {
		return gpsReciverEnabled;
	}

	public void setGpsReciverEnabled(boolean gpsReciverEnabled) {
		this.gpsReciverEnabled = gpsReciverEnabled;
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

	public boolean isErrors() {
		return errors;
	}

	public void setErrors(boolean errors) {
		this.errors = errors;
	}

	public boolean isCubesence1CommError() {
		return cubesence1CommError;
	}

	public void setCubesence1CommError(boolean cubesence1CommError) {
		this.cubesence1CommError = cubesence1CommError;
	}

	public boolean isCubesence2CommError() {
		return cubesence2CommError;
	}

	public void setCubesence2CommError(boolean cubesence2CommError) {
		this.cubesence2CommError = cubesence2CommError;
	}

	public boolean isCubecontrolSignalCommError() {
		return cubecontrolSignalCommError;
	}

	public void setCubecontrolSignalCommError(boolean cubecontrolSignalCommError) {
		this.cubecontrolSignalCommError = cubecontrolSignalCommError;
	}

	public boolean isCubecontrolMotorCommError() {
		return cubecontrolMotorCommError;
	}

	public void setCubecontrolMotorCommError(boolean cubecontrolMotorCommError) {
		this.cubecontrolMotorCommError = cubecontrolMotorCommError;
	}

	public boolean isCubewheel1CommError() {
		return cubewheel1CommError;
	}

	public void setCubewheel1CommError(boolean cubewheel1CommError) {
		this.cubewheel1CommError = cubewheel1CommError;
	}

	public boolean isCubewhee2CommError() {
		return cubewhee2CommError;
	}

	public void setCubewhee2CommError(boolean cubewhee2CommError) {
		this.cubewhee2CommError = cubewhee2CommError;
	}

	public boolean isCubewheel3CommError() {
		return cubewheel3CommError;
	}

	public void setCubewheel3CommError(boolean cubewheel3CommError) {
		this.cubewheel3CommError = cubewheel3CommError;
	}

	public boolean isCubestarCommError() {
		return cubestarCommError;
	}

	public void setCubestarCommError(boolean cubestarCommError) {
		this.cubestarCommError = cubestarCommError;
	}

	public boolean isMagnetometerRangeError() {
		return magnetometerRangeError;
	}

	public void setMagnetometerRangeError(boolean magnetometerRangeError) {
		this.magnetometerRangeError = magnetometerRangeError;
	}

	public boolean isSunsensorSramOvercurrentDetected() {
		return sunsensorSramOvercurrentDetected;
	}

	public void setSunsensorSramOvercurrentDetected(boolean sunsensorSramOvercurrentDetected) {
		this.sunsensorSramOvercurrentDetected = sunsensorSramOvercurrentDetected;
	}

	public boolean isSunsensor3v3OvercurrentDetected() {
		return sunsensor3v3OvercurrentDetected;
	}

	public void setSunsensor3v3OvercurrentDetected(boolean sunsensor3v3OvercurrentDetected) {
		this.sunsensor3v3OvercurrentDetected = sunsensor3v3OvercurrentDetected;
	}

	public boolean isSunsensorBusyError() {
		return sunsensorBusyError;
	}

	public void setSunsensorBusyError(boolean sunsensorBusyError) {
		this.sunsensorBusyError = sunsensorBusyError;
	}

	public boolean isSunsensorDetectionError() {
		return sunsensorDetectionError;
	}

	public void setSunsensorDetectionError(boolean sunsensorDetectionError) {
		this.sunsensorDetectionError = sunsensorDetectionError;
	}

	public boolean isSunsensorRangeError() {
		return sunsensorRangeError;
	}

	public void setSunsensorRangeError(boolean sunsensorRangeError) {
		this.sunsensorRangeError = sunsensorRangeError;
	}

	public boolean isNadirSensorSramOvercurrentDetected() {
		return nadirSensorSramOvercurrentDetected;
	}

	public void setNadirSensorSramOvercurrentDetected(boolean nadirSensorSramOvercurrentDetected) {
		this.nadirSensorSramOvercurrentDetected = nadirSensorSramOvercurrentDetected;
	}

	public boolean isNadirSensor3v3OvercurrentDetected() {
		return nadirSensor3v3OvercurrentDetected;
	}

	public void setNadirSensor3v3OvercurrentDetected(boolean nadirSensor3v3OvercurrentDetected) {
		this.nadirSensor3v3OvercurrentDetected = nadirSensor3v3OvercurrentDetected;
	}

	public boolean isNadirSensorBusyError() {
		return nadirSensorBusyError;
	}

	public void setNadirSensorBusyError(boolean nadirSensorBusyError) {
		this.nadirSensorBusyError = nadirSensorBusyError;
	}

	public boolean isNadirSensorDetectionError() {
		return nadirSensorDetectionError;
	}

	public void setNadirSensorDetectionError(boolean nadirSensorDetectionError) {
		this.nadirSensorDetectionError = nadirSensorDetectionError;
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

	public boolean isCoarseSunsensorError() {
		return coarseSunsensorError;
	}

	public void setCoarseSunsensorError(boolean coarseSunsensorError) {
		this.coarseSunsensorError = coarseSunsensorError;
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

	public boolean isOrbitParametersInvalid() {
		return orbitParametersInvalid;
	}

	public void setOrbitParametersInvalid(boolean orbitParametersInvalid) {
		this.orbitParametersInvalid = orbitParametersInvalid;
	}

	public boolean isConfigurationInvalid() {
		return configurationInvalid;
	}

	public void setConfigurationInvalid(boolean configurationInvalid) {
		this.configurationInvalid = configurationInvalid;
	}

	public boolean isControlModeChangeAllowed() {
		return controlModeChangeAllowed;
	}

	public void setControlModeChangeAllowed(boolean controlModeChangeAllowed) {
		this.controlModeChangeAllowed = controlModeChangeAllowed;
	}

	public boolean isEstimatorChangeNotAllowed() {
		return estimatorChangeNotAllowed;
	}

	public void setEstimatorChangeNotAllowed(boolean estimatorChangeNotAllowed) {
		this.estimatorChangeNotAllowed = estimatorChangeNotAllowed;
	}

	public MagnetometerMode getMagnetometerMode() {
		return magnetometerMode;
	}

	public void setMagnetometerMode(MagnetometerMode magnetometerMode) {
		this.magnetometerMode = magnetometerMode;
	}

	public boolean isModelledMeasuredMagneticFieldMissmatch() {
		return modelledMeasuredMagneticFieldMissmatch;
	}

	public void setModelledMeasuredMagneticFieldMissmatch(boolean modelledMeasuredMagneticFieldMissmatch) {
		this.modelledMeasuredMagneticFieldMissmatch = modelledMeasuredMagneticFieldMissmatch;
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

	public boolean isCubecontrolMotorUntimeError() {
		return cubecontrolMotorUntimeError;
	}

	public void setCubecontrolMotorUntimeError(boolean cubecontrolMotorUntimeError) {
		this.cubecontrolMotorUntimeError = cubecontrolMotorUntimeError;
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

	public float getRollDegree() {
		return rollDegree;
	}

	public void setRollDegree(float rollDegree) {
		this.rollDegree = rollDegree;
	}

	public float getPitchDegree() {
		return pitchDegree;
	}

	public void setPitchDegree(float pitchDegree) {
		this.pitchDegree = pitchDegree;
	}

	public float getYawDegree() {
		return yawDegree;
	}

	public void setYawDegree(float yawDegree) {
		this.yawDegree = yawDegree;
	}

	public float getQ1() {
		return q1;
	}

	public void setQ1(float q1) {
		this.q1 = q1;
	}

	public float getQ2() {
		return q2;
	}

	public void setQ2(float q2) {
		this.q2 = q2;
	}

	public float getQ3() {
		return q3;
	}

	public void setQ3(float q3) {
		this.q3 = q3;
	}

	public float getxAngularRateDegreeS() {
		return xAngularRateDegreeS;
	}

	public void setxAngularRateDegreeS(float xAngularRateDegreeS) {
		this.xAngularRateDegreeS = xAngularRateDegreeS;
	}

	public float getyAngularRateDegreeS() {
		return yAngularRateDegreeS;
	}

	public void setyAngularRateDegreeS(float yAngularRateDegreeS) {
		this.yAngularRateDegreeS = yAngularRateDegreeS;
	}

	public float getzAngularRateDegreeS() {
		return zAngularRateDegreeS;
	}

	public void setzAngularRateDegreeS(float zAngularRateDegreeS) {
		this.zAngularRateDegreeS = zAngularRateDegreeS;
	}

}
