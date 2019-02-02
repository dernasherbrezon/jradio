package ru.r2cloud.jradio.eseo.funcube;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class OBDEquipmentStatus {

	private boolean TMTCMain;
	private boolean TMTCRedundant;
	private boolean PowerManagementUnitMain;
	private boolean PowerManagementUnitRedundant;
	private boolean SunSensorMain;
	private boolean SunSensorRedundant;
	private boolean EarthSensor;
	private boolean MagnetometerMain;
	private boolean MagnetometerRedundant;
	private boolean MagneticTorquerMain;
	private boolean MagneticTorquerRedundant;
	private boolean MomentumWheelMain;
	private boolean MomentumWheelRedundant;
	private boolean TRITEL;
	private boolean LangmuirProbe;
	private boolean uCAM;
	private boolean Deorbitmechanism;
	private boolean AMSATUK;
	private boolean SBand;
	private boolean GPSReceiver;

	public OBDEquipmentStatus(BitInputStream dis) throws IOException {
		TMTCMain = dis.readBoolean();
		TMTCRedundant = dis.readBoolean();
		PowerManagementUnitMain = dis.readBoolean();
		PowerManagementUnitRedundant = dis.readBoolean();
		SunSensorMain = dis.readBoolean();
		SunSensorRedundant = dis.readBoolean();
		EarthSensor = dis.readBoolean();
		MagnetometerMain = dis.readBoolean();
		MagnetometerRedundant = dis.readBoolean();
		MagneticTorquerMain = dis.readBoolean();
		MagneticTorquerRedundant = dis.readBoolean();
		MomentumWheelMain = dis.readBoolean();
		MomentumWheelRedundant = dis.readBoolean();
		TRITEL = dis.readBoolean();
		LangmuirProbe = dis.readBoolean();
		uCAM = dis.readBoolean();
		Deorbitmechanism = dis.readBoolean();
		AMSATUK = dis.readBoolean();
		SBand = dis.readBoolean();
		GPSReceiver = dis.readBoolean();
	}

	public boolean isTMTCMain() {
		return TMTCMain;
	}

	public void setTMTCMain(boolean tMTCMain) {
		TMTCMain = tMTCMain;
	}

	public boolean isTMTCRedundant() {
		return TMTCRedundant;
	}

	public void setTMTCRedundant(boolean tMTCRedundant) {
		TMTCRedundant = tMTCRedundant;
	}

	public boolean isPowerManagementUnitMain() {
		return PowerManagementUnitMain;
	}

	public void setPowerManagementUnitMain(boolean powerManagementUnitMain) {
		PowerManagementUnitMain = powerManagementUnitMain;
	}

	public boolean isPowerManagementUnitRedundant() {
		return PowerManagementUnitRedundant;
	}

	public void setPowerManagementUnitRedundant(boolean powerManagementUnitRedundant) {
		PowerManagementUnitRedundant = powerManagementUnitRedundant;
	}

	public boolean isSunSensorMain() {
		return SunSensorMain;
	}

	public void setSunSensorMain(boolean sunSensorMain) {
		SunSensorMain = sunSensorMain;
	}

	public boolean isSunSensorRedundant() {
		return SunSensorRedundant;
	}

	public void setSunSensorRedundant(boolean sunSensorRedundant) {
		SunSensorRedundant = sunSensorRedundant;
	}

	public boolean isEarthSensor() {
		return EarthSensor;
	}

	public void setEarthSensor(boolean earthSensor) {
		EarthSensor = earthSensor;
	}

	public boolean isMagnetometerMain() {
		return MagnetometerMain;
	}

	public void setMagnetometerMain(boolean magnetometerMain) {
		MagnetometerMain = magnetometerMain;
	}

	public boolean isMagnetometerRedundant() {
		return MagnetometerRedundant;
	}

	public void setMagnetometerRedundant(boolean magnetometerRedundant) {
		MagnetometerRedundant = magnetometerRedundant;
	}

	public boolean isMagneticTorquerMain() {
		return MagneticTorquerMain;
	}

	public void setMagneticTorquerMain(boolean magneticTorquerMain) {
		MagneticTorquerMain = magneticTorquerMain;
	}

	public boolean isMagneticTorquerRedundant() {
		return MagneticTorquerRedundant;
	}

	public void setMagneticTorquerRedundant(boolean magneticTorquerRedundant) {
		MagneticTorquerRedundant = magneticTorquerRedundant;
	}

	public boolean isMomentumWheelMain() {
		return MomentumWheelMain;
	}

	public void setMomentumWheelMain(boolean momentumWheelMain) {
		MomentumWheelMain = momentumWheelMain;
	}

	public boolean isMomentumWheelRedundant() {
		return MomentumWheelRedundant;
	}

	public void setMomentumWheelRedundant(boolean momentumWheelRedundant) {
		MomentumWheelRedundant = momentumWheelRedundant;
	}

	public boolean isTRITEL() {
		return TRITEL;
	}

	public void setTRITEL(boolean tRITEL) {
		TRITEL = tRITEL;
	}

	public boolean isLangmuirProbe() {
		return LangmuirProbe;
	}

	public void setLangmuirProbe(boolean langmuirProbe) {
		LangmuirProbe = langmuirProbe;
	}

	public boolean isuCAM() {
		return uCAM;
	}

	public void setuCAM(boolean uCAM) {
		this.uCAM = uCAM;
	}

	public boolean isDeorbitmechanism() {
		return Deorbitmechanism;
	}

	public void setDeorbitmechanism(boolean deorbitmechanism) {
		Deorbitmechanism = deorbitmechanism;
	}

	public boolean isAMSATUK() {
		return AMSATUK;
	}

	public void setAMSATUK(boolean aMSATUK) {
		AMSATUK = aMSATUK;
	}

	public boolean isSBand() {
		return SBand;
	}

	public void setSBand(boolean sBand) {
		SBand = sBand;
	}

	public boolean isGPSReceiver() {
		return GPSReceiver;
	}

	public void setGPSReceiver(boolean gPSReceiver) {
		GPSReceiver = gPSReceiver;
	}

}
