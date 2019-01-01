package ru.r2cloud.jradio.eseo;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class EquipmentStatus {

	private boolean PowerManagementUnitMain;
	private boolean PowerManagementUnitRedundant;
	private boolean TMTCMain;
	private boolean TMTCRedundant;
	private boolean SunSensorMain;
	private boolean SunSensorRedundant;
	private boolean EarthSensor;
	private boolean MomentumWheelMain;
	private boolean MomentumWheelRedundant;
	private boolean Micropropulsion;
	private boolean MagnetometerMain;
	private boolean MagnetometerRedundant;
	private boolean MagneticTorquerMain;
	private boolean MagneticTorquerRedundant;
	private boolean TRITEL;
	private boolean LangmuirProbe;
	private boolean PCAM;
	private boolean AMSATUK;
	private boolean SBand;
	private boolean GPSReceiver;
	private boolean ADE;
	private boolean SCAM;
	private boolean DeOrbitMechanism;

	public EquipmentStatus(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		PowerManagementUnitMain = ((raw >> 5) & 0x1) > 0;
		PowerManagementUnitRedundant = ((raw >> 4) & 0x1) > 0;
		TMTCMain = ((raw >> 3) & 0x1) > 0;
		TMTCRedundant = ((raw >> 2) & 0x1) > 0;
		SunSensorMain = ((raw >> 1) & 0x1) > 0;
		SunSensorRedundant = ((raw >> 0) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		EarthSensor = ((raw >> 7) & 0x1) > 0;
		MomentumWheelMain = ((raw >> 6) & 0x1) > 0;
		MomentumWheelRedundant = ((raw >> 5) & 0x1) > 0;
		Micropropulsion = ((raw >> 4) & 0x1) > 0;
		MagnetometerMain = ((raw >> 3) & 0x1) > 0;
		MagnetometerRedundant = ((raw >> 2) & 0x1) > 0;
		MagneticTorquerMain = ((raw >> 1) & 0x1) > 0;
		MagneticTorquerRedundant = ((raw >> 0) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		TRITEL = ((raw >> 7) & 0x1) > 0;
		LangmuirProbe = ((raw >> 6) & 0x1) > 0;
		PCAM = ((raw >> 5) & 0x1) > 0;
		AMSATUK = ((raw >> 4) & 0x1) > 0;
		SBand = ((raw >> 3) & 0x1) > 0;
		GPSReceiver = ((raw >> 2) & 0x1) > 0;
		ADE = ((raw >> 1) & 0x1) > 0;
		SCAM = ((raw >> 0) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		DeOrbitMechanism = ((raw >> 7) & 0x1) > 0;
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

	public boolean isMicropropulsion() {
		return Micropropulsion;
	}

	public void setMicropropulsion(boolean micropropulsion) {
		Micropropulsion = micropropulsion;
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

	public boolean isPCAM() {
		return PCAM;
	}

	public void setPCAM(boolean pCAM) {
		PCAM = pCAM;
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

	public boolean isADE() {
		return ADE;
	}

	public void setADE(boolean aDE) {
		ADE = aDE;
	}

	public boolean isSCAM() {
		return SCAM;
	}

	public void setSCAM(boolean sCAM) {
		SCAM = sCAM;
	}

	public boolean isDeOrbitMechanism() {
		return DeOrbitMechanism;
	}

	public void setDeOrbitMechanism(boolean deOrbitMechanism) {
		DeOrbitMechanism = deOrbitMechanism;
	}

}
