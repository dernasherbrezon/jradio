package ru.r2cloud.jradio.dora;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Adcs {

	private boolean adcsCommissioningDone;
	private int adcsCurrentCommissioningState;
	private int bootStatus;
	private int bootCount;
	private int currentAdcsControlmode;
	private int currentAdcsAttitudeestimation;
	private boolean cubesense2Enabled;
	private boolean cubesense1Enabled;
	private boolean cubecontrolmotorEnabled;
	private boolean cubecontrolsignalEnabled;
	private int asgp4Mode;
	private boolean currentadcsAdcsRunMode;
	private boolean sunIsAboveLocalHorizon;
	private boolean motorDriverEnabled;
	private boolean gpsLnaPowerEnabled;
	private boolean gpsReceiverEnable;
	private boolean cubestarEnabled;
	private boolean cubewheel3Enabled;
	private boolean cubewheel2Enabled;
	private boolean cubewheel1Enabled;
	private boolean cubestarCommsError;
	private boolean cubewheel3CommsError;
	private boolean cubewheel2CommsError;
	private boolean cubewheel1CommsError;
	private boolean cubecontrolMotorCommsError;
	private boolean cubecontrolSignalCommsError;
	private boolean cubesense2CommsError;
	private boolean cubesense1CommsError;
	private boolean cam23v3OvercurrentDetected;
	private boolean cam2SramOvercurrentDetected;
	private boolean sunSensorRangeError;
	private boolean cam1SensorDetectionError;
	private boolean cam1SensorBusyError;
	private boolean cam13v3OvercurrentDetected;
	private boolean cam1SramOvercurrentDetected;
	private boolean magnetometerRangeError;
	private boolean starTrackerOvercurrentDetected;
	private boolean startrackerMatchError;
	private boolean coarseSunSensorError;
	private boolean wheelSpeedRangeError;
	private boolean rateSensorRangeError;
	private boolean nadirSensorRangeError;
	private boolean cam2SensorDetectionError;
	private boolean cam2SensorBusyError;

	private float cubecontrlCur3v3;
	private float cubecontrlCur5v;
	private float cubecontrolCurVbat;
	private float fineEstAngRatesX;
	private float fineEstAngRatesY;
	private float fineEstAngRatesZ;
	private float fineSvX;
	private float fineSvY;
	private float fineSvZ;
	private float mcuTempa;
	private float tempMag;
	private float tempRedmag;
	private float wheel1Current;
	private float wheel2Current;
	private float wheel3Current;
	private float magnetorquerCurrent;
	private float cubesenes13v3Current;
	private float cubesenes1SramCurrent;
	private float cubesenes23v3Current;
	private float cubesenes2SramCurrent;

	public Adcs() {
		// do nothing
	}

	public Adcs(DataInputStream dis) throws IOException {
		BitInputStream bis = new BitInputStream(dis);
		adcsCommissioningDone = bis.readBoolean();
		adcsCurrentCommissioningState = bis.readUnsignedInt(7);
		bootStatus = bis.readUnsignedByte();
		bootCount = bis.readUnsignedShort();
		currentAdcsControlmode = bis.readUnsignedInt(4);
		currentAdcsAttitudeestimation = bis.readUnsignedInt(4);
		cubesense2Enabled = bis.readBoolean();
		cubesense1Enabled = bis.readBoolean();
		cubecontrolmotorEnabled = bis.readBoolean();
		cubecontrolsignalEnabled = bis.readBoolean();
		asgp4Mode = bis.readUnsignedInt(2);
		currentadcsAdcsRunMode = bis.readBoolean();
		sunIsAboveLocalHorizon = bis.readBoolean();
		motorDriverEnabled = bis.readBoolean();
		gpsLnaPowerEnabled = bis.readBoolean();
		gpsReceiverEnable = bis.readBoolean();
		cubestarEnabled = bis.readBoolean();
		cubewheel3Enabled = bis.readBoolean();
		cubewheel2Enabled = bis.readBoolean();
		cubewheel1Enabled = bis.readBoolean();
		cubestarCommsError = bis.readBoolean();
		cubewheel3CommsError = bis.readBoolean();
		cubewheel2CommsError = bis.readBoolean();
		cubewheel1CommsError = bis.readBoolean();
		cubecontrolMotorCommsError = bis.readBoolean();
		cubecontrolSignalCommsError = bis.readBoolean();
		cubesense2CommsError = bis.readBoolean();
		cubesense1CommsError = bis.readBoolean();
		cam23v3OvercurrentDetected = bis.readBoolean();
		cam2SramOvercurrentDetected = bis.readBoolean();
		sunSensorRangeError = bis.readBoolean();
		cam1SensorDetectionError = bis.readBoolean();
		cam1SensorBusyError = bis.readBoolean();
		cam13v3OvercurrentDetected = bis.readBoolean();
		cam1SramOvercurrentDetected = bis.readBoolean();
		magnetometerRangeError = bis.readBoolean();
		starTrackerOvercurrentDetected = bis.readBoolean();
		startrackerMatchError = bis.readBoolean();
		coarseSunSensorError = bis.readBoolean();
		wheelSpeedRangeError = bis.readBoolean();
		rateSensorRangeError = bis.readBoolean();
		nadirSensorRangeError = bis.readBoolean();
		cam2SensorDetectionError = bis.readBoolean();
		cam2SensorBusyError = bis.readBoolean();

		LittleEndianDataInputStream ldis = new LittleEndianDataInputStream(dis);
		cubecontrlCur3v3 = ldis.readUnsignedShort() * 0.48828125f;
		cubecontrlCur5v = ldis.readUnsignedShort() * 0.48828125f;
		cubecontrolCurVbat = ldis.readUnsignedShort() * 0.48828125f;
		fineEstAngRatesX = ldis.readUnsignedShort() * 0.001f;
		fineEstAngRatesY = ldis.readUnsignedShort() * 0.001f;
		fineEstAngRatesZ = ldis.readUnsignedShort() * 0.001f;
		fineSvX = ldis.readUnsignedShort() * 0.0001f;
		fineSvY = ldis.readUnsignedShort() * 0.0001f;
		fineSvZ = ldis.readUnsignedShort() * 0.0001f;
		mcuTempa = ldis.readUnsignedShort() * 0.1f;
		tempMag = ldis.readUnsignedShort() * 0.1f;
		tempRedmag = ldis.readUnsignedShort() * 0.1f;
		wheel1Current = ldis.readUnsignedShort() * 0.01f;
		wheel2Current = ldis.readUnsignedShort() * 0.01f;
		wheel3Current = ldis.readUnsignedShort() * 0.01f;
		magnetorquerCurrent = ldis.readUnsignedShort() * 0.01f;
		cubesenes13v3Current = ldis.readUnsignedShort() * 0.01f;
		cubesenes1SramCurrent = ldis.readUnsignedShort() * 0.01f;
		cubesenes23v3Current = ldis.readUnsignedShort() * 0.01f;
		cubesenes2SramCurrent = ldis.readUnsignedShort() * 0.01f;
	}

	public boolean isAdcsCommissioningDone() {
		return adcsCommissioningDone;
	}

	public void setAdcsCommissioningDone(boolean adcsCommissioningDone) {
		this.adcsCommissioningDone = adcsCommissioningDone;
	}

	public int getAdcsCurrentCommissioningState() {
		return adcsCurrentCommissioningState;
	}

	public void setAdcsCurrentCommissioningState(int adcsCurrentCommissioningState) {
		this.adcsCurrentCommissioningState = adcsCurrentCommissioningState;
	}

	public int getBootStatus() {
		return bootStatus;
	}

	public void setBootStatus(int bootStatus) {
		this.bootStatus = bootStatus;
	}

	public int getBootCount() {
		return bootCount;
	}

	public void setBootCount(int bootCount) {
		this.bootCount = bootCount;
	}

	public int getCurrentAdcsControlmode() {
		return currentAdcsControlmode;
	}

	public void setCurrentAdcsControlmode(int currentAdcsControlmode) {
		this.currentAdcsControlmode = currentAdcsControlmode;
	}

	public int getCurrentAdcsAttitudeestimation() {
		return currentAdcsAttitudeestimation;
	}

	public void setCurrentAdcsAttitudeestimation(int currentAdcsAttitudeestimation) {
		this.currentAdcsAttitudeestimation = currentAdcsAttitudeestimation;
	}

	public boolean isCubesense2Enabled() {
		return cubesense2Enabled;
	}

	public void setCubesense2Enabled(boolean cubesense2Enabled) {
		this.cubesense2Enabled = cubesense2Enabled;
	}

	public boolean isCubesense1Enabled() {
		return cubesense1Enabled;
	}

	public void setCubesense1Enabled(boolean cubesense1Enabled) {
		this.cubesense1Enabled = cubesense1Enabled;
	}

	public boolean isCubecontrolmotorEnabled() {
		return cubecontrolmotorEnabled;
	}

	public void setCubecontrolmotorEnabled(boolean cubecontrolmotorEnabled) {
		this.cubecontrolmotorEnabled = cubecontrolmotorEnabled;
	}

	public boolean isCubecontrolsignalEnabled() {
		return cubecontrolsignalEnabled;
	}

	public void setCubecontrolsignalEnabled(boolean cubecontrolsignalEnabled) {
		this.cubecontrolsignalEnabled = cubecontrolsignalEnabled;
	}

	public int getAsgp4Mode() {
		return asgp4Mode;
	}

	public void setAsgp4Mode(int asgp4Mode) {
		this.asgp4Mode = asgp4Mode;
	}

	public boolean isCurrentadcsAdcsRunMode() {
		return currentadcsAdcsRunMode;
	}

	public void setCurrentadcsAdcsRunMode(boolean currentadcsAdcsRunMode) {
		this.currentadcsAdcsRunMode = currentadcsAdcsRunMode;
	}

	public boolean isSunIsAboveLocalHorizon() {
		return sunIsAboveLocalHorizon;
	}

	public void setSunIsAboveLocalHorizon(boolean sunIsAboveLocalHorizon) {
		this.sunIsAboveLocalHorizon = sunIsAboveLocalHorizon;
	}

	public boolean isMotorDriverEnabled() {
		return motorDriverEnabled;
	}

	public void setMotorDriverEnabled(boolean motorDriverEnabled) {
		this.motorDriverEnabled = motorDriverEnabled;
	}

	public boolean isGpsLnaPowerEnabled() {
		return gpsLnaPowerEnabled;
	}

	public void setGpsLnaPowerEnabled(boolean gpsLnaPowerEnabled) {
		this.gpsLnaPowerEnabled = gpsLnaPowerEnabled;
	}

	public boolean isGpsReceiverEnable() {
		return gpsReceiverEnable;
	}

	public void setGpsReceiverEnable(boolean gpsReceiverEnable) {
		this.gpsReceiverEnable = gpsReceiverEnable;
	}

	public boolean isCubestarEnabled() {
		return cubestarEnabled;
	}

	public void setCubestarEnabled(boolean cubestarEnabled) {
		this.cubestarEnabled = cubestarEnabled;
	}

	public boolean isCubewheel3Enabled() {
		return cubewheel3Enabled;
	}

	public void setCubewheel3Enabled(boolean cubewheel3Enabled) {
		this.cubewheel3Enabled = cubewheel3Enabled;
	}

	public boolean isCubewheel2Enabled() {
		return cubewheel2Enabled;
	}

	public void setCubewheel2Enabled(boolean cubewheel2Enabled) {
		this.cubewheel2Enabled = cubewheel2Enabled;
	}

	public boolean isCubewheel1Enabled() {
		return cubewheel1Enabled;
	}

	public void setCubewheel1Enabled(boolean cubewheel1Enabled) {
		this.cubewheel1Enabled = cubewheel1Enabled;
	}

	public boolean isCubestarCommsError() {
		return cubestarCommsError;
	}

	public void setCubestarCommsError(boolean cubestarCommsError) {
		this.cubestarCommsError = cubestarCommsError;
	}

	public boolean isCubewheel3CommsError() {
		return cubewheel3CommsError;
	}

	public void setCubewheel3CommsError(boolean cubewheel3CommsError) {
		this.cubewheel3CommsError = cubewheel3CommsError;
	}

	public boolean isCubewheel2CommsError() {
		return cubewheel2CommsError;
	}

	public void setCubewheel2CommsError(boolean cubewheel2CommsError) {
		this.cubewheel2CommsError = cubewheel2CommsError;
	}

	public boolean isCubewheel1CommsError() {
		return cubewheel1CommsError;
	}

	public void setCubewheel1CommsError(boolean cubewheel1CommsError) {
		this.cubewheel1CommsError = cubewheel1CommsError;
	}

	public boolean isCubecontrolMotorCommsError() {
		return cubecontrolMotorCommsError;
	}

	public void setCubecontrolMotorCommsError(boolean cubecontrolMotorCommsError) {
		this.cubecontrolMotorCommsError = cubecontrolMotorCommsError;
	}

	public boolean isCubecontrolSignalCommsError() {
		return cubecontrolSignalCommsError;
	}

	public void setCubecontrolSignalCommsError(boolean cubecontrolSignalCommsError) {
		this.cubecontrolSignalCommsError = cubecontrolSignalCommsError;
	}

	public boolean isCubesense2CommsError() {
		return cubesense2CommsError;
	}

	public void setCubesense2CommsError(boolean cubesense2CommsError) {
		this.cubesense2CommsError = cubesense2CommsError;
	}

	public boolean isCubesense1CommsError() {
		return cubesense1CommsError;
	}

	public void setCubesense1CommsError(boolean cubesense1CommsError) {
		this.cubesense1CommsError = cubesense1CommsError;
	}

	public boolean isCam23v3OvercurrentDetected() {
		return cam23v3OvercurrentDetected;
	}

	public void setCam23v3OvercurrentDetected(boolean cam23v3OvercurrentDetected) {
		this.cam23v3OvercurrentDetected = cam23v3OvercurrentDetected;
	}

	public boolean isCam2SramOvercurrentDetected() {
		return cam2SramOvercurrentDetected;
	}

	public void setCam2SramOvercurrentDetected(boolean cam2SramOvercurrentDetected) {
		this.cam2SramOvercurrentDetected = cam2SramOvercurrentDetected;
	}

	public boolean isSunSensorRangeError() {
		return sunSensorRangeError;
	}

	public void setSunSensorRangeError(boolean sunSensorRangeError) {
		this.sunSensorRangeError = sunSensorRangeError;
	}

	public boolean isCam1SensorDetectionError() {
		return cam1SensorDetectionError;
	}

	public void setCam1SensorDetectionError(boolean cam1SensorDetectionError) {
		this.cam1SensorDetectionError = cam1SensorDetectionError;
	}

	public boolean isCam1SensorBusyError() {
		return cam1SensorBusyError;
	}

	public void setCam1SensorBusyError(boolean cam1SensorBusyError) {
		this.cam1SensorBusyError = cam1SensorBusyError;
	}

	public boolean isCam13v3OvercurrentDetected() {
		return cam13v3OvercurrentDetected;
	}

	public void setCam13v3OvercurrentDetected(boolean cam13v3OvercurrentDetected) {
		this.cam13v3OvercurrentDetected = cam13v3OvercurrentDetected;
	}

	public boolean isCam1SramOvercurrentDetected() {
		return cam1SramOvercurrentDetected;
	}

	public void setCam1SramOvercurrentDetected(boolean cam1SramOvercurrentDetected) {
		this.cam1SramOvercurrentDetected = cam1SramOvercurrentDetected;
	}

	public boolean isMagnetometerRangeError() {
		return magnetometerRangeError;
	}

	public void setMagnetometerRangeError(boolean magnetometerRangeError) {
		this.magnetometerRangeError = magnetometerRangeError;
	}

	public boolean isStarTrackerOvercurrentDetected() {
		return starTrackerOvercurrentDetected;
	}

	public void setStarTrackerOvercurrentDetected(boolean starTrackerOvercurrentDetected) {
		this.starTrackerOvercurrentDetected = starTrackerOvercurrentDetected;
	}

	public boolean isStartrackerMatchError() {
		return startrackerMatchError;
	}

	public void setStartrackerMatchError(boolean startrackerMatchError) {
		this.startrackerMatchError = startrackerMatchError;
	}

	public boolean isCoarseSunSensorError() {
		return coarseSunSensorError;
	}

	public void setCoarseSunSensorError(boolean coarseSunSensorError) {
		this.coarseSunSensorError = coarseSunSensorError;
	}

	public boolean isWheelSpeedRangeError() {
		return wheelSpeedRangeError;
	}

	public void setWheelSpeedRangeError(boolean wheelSpeedRangeError) {
		this.wheelSpeedRangeError = wheelSpeedRangeError;
	}

	public boolean isRateSensorRangeError() {
		return rateSensorRangeError;
	}

	public void setRateSensorRangeError(boolean rateSensorRangeError) {
		this.rateSensorRangeError = rateSensorRangeError;
	}

	public boolean isNadirSensorRangeError() {
		return nadirSensorRangeError;
	}

	public void setNadirSensorRangeError(boolean nadirSensorRangeError) {
		this.nadirSensorRangeError = nadirSensorRangeError;
	}

	public boolean isCam2SensorDetectionError() {
		return cam2SensorDetectionError;
	}

	public void setCam2SensorDetectionError(boolean cam2SensorDetectionError) {
		this.cam2SensorDetectionError = cam2SensorDetectionError;
	}

	public boolean isCam2SensorBusyError() {
		return cam2SensorBusyError;
	}

	public void setCam2SensorBusyError(boolean cam2SensorBusyError) {
		this.cam2SensorBusyError = cam2SensorBusyError;
	}

	public float getCubecontrlCur3v3() {
		return cubecontrlCur3v3;
	}

	public void setCubecontrlCur3v3(float cubecontrlCur3v3) {
		this.cubecontrlCur3v3 = cubecontrlCur3v3;
	}

	public float getCubecontrlCur5v() {
		return cubecontrlCur5v;
	}

	public void setCubecontrlCur5v(float cubecontrlCur5v) {
		this.cubecontrlCur5v = cubecontrlCur5v;
	}

	public float getCubecontrolCurVbat() {
		return cubecontrolCurVbat;
	}

	public void setCubecontrolCurVbat(float cubecontrolCurVbat) {
		this.cubecontrolCurVbat = cubecontrolCurVbat;
	}

	public float getFineEstAngRatesX() {
		return fineEstAngRatesX;
	}

	public void setFineEstAngRatesX(float fineEstAngRatesX) {
		this.fineEstAngRatesX = fineEstAngRatesX;
	}

	public float getFineEstAngRatesY() {
		return fineEstAngRatesY;
	}

	public void setFineEstAngRatesY(float fineEstAngRatesY) {
		this.fineEstAngRatesY = fineEstAngRatesY;
	}

	public float getFineEstAngRatesZ() {
		return fineEstAngRatesZ;
	}

	public void setFineEstAngRatesZ(float fineEstAngRatesZ) {
		this.fineEstAngRatesZ = fineEstAngRatesZ;
	}

	public float getFineSvX() {
		return fineSvX;
	}

	public void setFineSvX(float fineSvX) {
		this.fineSvX = fineSvX;
	}

	public float getFineSvY() {
		return fineSvY;
	}

	public void setFineSvY(float fineSvY) {
		this.fineSvY = fineSvY;
	}

	public float getFineSvZ() {
		return fineSvZ;
	}

	public void setFineSvZ(float fineSvZ) {
		this.fineSvZ = fineSvZ;
	}

	public float getMcuTempa() {
		return mcuTempa;
	}

	public void setMcuTempa(float mcuTempa) {
		this.mcuTempa = mcuTempa;
	}

	public float getTempMag() {
		return tempMag;
	}

	public void setTempMag(float tempMag) {
		this.tempMag = tempMag;
	}

	public float getTempRedmag() {
		return tempRedmag;
	}

	public void setTempRedmag(float tempRedmag) {
		this.tempRedmag = tempRedmag;
	}

	public float getWheel1Current() {
		return wheel1Current;
	}

	public void setWheel1Current(float wheel1Current) {
		this.wheel1Current = wheel1Current;
	}

	public float getWheel2Current() {
		return wheel2Current;
	}

	public void setWheel2Current(float wheel2Current) {
		this.wheel2Current = wheel2Current;
	}

	public float getWheel3Current() {
		return wheel3Current;
	}

	public void setWheel3Current(float wheel3Current) {
		this.wheel3Current = wheel3Current;
	}

	public float getMagnetorquerCurrent() {
		return magnetorquerCurrent;
	}

	public void setMagnetorquerCurrent(float magnetorquerCurrent) {
		this.magnetorquerCurrent = magnetorquerCurrent;
	}

	public float getCubesenes13v3Current() {
		return cubesenes13v3Current;
	}

	public void setCubesenes13v3Current(float cubesenes13v3Current) {
		this.cubesenes13v3Current = cubesenes13v3Current;
	}

	public float getCubesenes1SramCurrent() {
		return cubesenes1SramCurrent;
	}

	public void setCubesenes1SramCurrent(float cubesenes1SramCurrent) {
		this.cubesenes1SramCurrent = cubesenes1SramCurrent;
	}

	public float getCubesenes23v3Current() {
		return cubesenes23v3Current;
	}

	public void setCubesenes23v3Current(float cubesenes23v3Current) {
		this.cubesenes23v3Current = cubesenes23v3Current;
	}

	public float getCubesenes2SramCurrent() {
		return cubesenes2SramCurrent;
	}

	public void setCubesenes2SramCurrent(float cubesenes2SramCurrent) {
		this.cubesenes2SramCurrent = cubesenes2SramCurrent;
	}

}
