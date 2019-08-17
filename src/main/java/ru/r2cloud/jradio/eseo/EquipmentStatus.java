package ru.r2cloud.jradio.eseo;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class EquipmentStatus {

	private boolean powerManagementUnitMain;
	private boolean powerManagementUnitRedundant;
	private boolean tMTCMain;
	private boolean tMTCRedundant;
	private boolean sunSensorMain;
	private boolean sunSensorRedundant;
	private boolean earthSensor;
	private boolean momentumWheelMain;
	private boolean momentumWheelRedundant;
	private boolean micropropulsion;
	private boolean magnetometerMain;
	private boolean magnetometerRedundant;
	private boolean magneticTorquerMain;
	private boolean magneticTorquerRedundant;
	private boolean tRITEL;
	private boolean langmuirProbe;
	private boolean pCAM;
	private boolean amsatUk;
	private boolean sBand;
	private boolean gpsReceiver;
	private boolean aDE;
	private boolean sCAM;
	private boolean deOrbitMechanism;

	public EquipmentStatus() {
		// do nothing
	}

	public EquipmentStatus(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		powerManagementUnitMain = ((raw >> 5) & 0x1) > 0;
		powerManagementUnitRedundant = ((raw >> 4) & 0x1) > 0;
		tMTCMain = ((raw >> 3) & 0x1) > 0;
		tMTCRedundant = ((raw >> 2) & 0x1) > 0;
		sunSensorMain = ((raw >> 1) & 0x1) > 0;
		sunSensorRedundant = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		earthSensor = ((raw >> 7) & 0x1) > 0;
		momentumWheelMain = ((raw >> 6) & 0x1) > 0;
		momentumWheelRedundant = ((raw >> 5) & 0x1) > 0;
		micropropulsion = ((raw >> 4) & 0x1) > 0;
		magnetometerMain = ((raw >> 3) & 0x1) > 0;
		magnetometerRedundant = ((raw >> 2) & 0x1) > 0;
		magneticTorquerMain = ((raw >> 1) & 0x1) > 0;
		magneticTorquerRedundant = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		tRITEL = ((raw >> 7) & 0x1) > 0;
		langmuirProbe = ((raw >> 6) & 0x1) > 0;
		pCAM = ((raw >> 5) & 0x1) > 0;
		amsatUk = ((raw >> 4) & 0x1) > 0;
		sBand = ((raw >> 3) & 0x1) > 0;
		gpsReceiver = ((raw >> 2) & 0x1) > 0;
		aDE = ((raw >> 1) & 0x1) > 0;
		sCAM = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		deOrbitMechanism = ((raw >> 7) & 0x1) > 0;
	}

	public boolean isPowerManagementUnitMain() {
		return powerManagementUnitMain;
	}

	public void setPowerManagementUnitMain(boolean powerManagementUnitMain) {
		this.powerManagementUnitMain = powerManagementUnitMain;
	}

	public boolean isPowerManagementUnitRedundant() {
		return powerManagementUnitRedundant;
	}

	public void setPowerManagementUnitRedundant(boolean powerManagementUnitRedundant) {
		this.powerManagementUnitRedundant = powerManagementUnitRedundant;
	}

	public boolean isTMTCMain() {
		return tMTCMain;
	}

	public void setTMTCMain(boolean tMTCMain) {
		this.tMTCMain = tMTCMain;
	}

	public boolean isTMTCRedundant() {
		return tMTCRedundant;
	}

	public void setTMTCRedundant(boolean tMTCRedundant) {
		this.tMTCRedundant = tMTCRedundant;
	}

	public boolean isSunSensorMain() {
		return sunSensorMain;
	}

	public void setSunSensorMain(boolean sunSensorMain) {
		this.sunSensorMain = sunSensorMain;
	}

	public boolean isSunSensorRedundant() {
		return sunSensorRedundant;
	}

	public void setSunSensorRedundant(boolean sunSensorRedundant) {
		this.sunSensorRedundant = sunSensorRedundant;
	}

	public boolean isEarthSensor() {
		return earthSensor;
	}

	public void setEarthSensor(boolean earthSensor) {
		this.earthSensor = earthSensor;
	}

	public boolean isMomentumWheelMain() {
		return momentumWheelMain;
	}

	public void setMomentumWheelMain(boolean momentumWheelMain) {
		this.momentumWheelMain = momentumWheelMain;
	}

	public boolean isMomentumWheelRedundant() {
		return momentumWheelRedundant;
	}

	public void setMomentumWheelRedundant(boolean momentumWheelRedundant) {
		this.momentumWheelRedundant = momentumWheelRedundant;
	}

	public boolean isMicropropulsion() {
		return micropropulsion;
	}

	public void setMicropropulsion(boolean micropropulsion) {
		this.micropropulsion = micropropulsion;
	}

	public boolean isMagnetometerMain() {
		return magnetometerMain;
	}

	public void setMagnetometerMain(boolean magnetometerMain) {
		this.magnetometerMain = magnetometerMain;
	}

	public boolean isMagnetometerRedundant() {
		return magnetometerRedundant;
	}

	public void setMagnetometerRedundant(boolean magnetometerRedundant) {
		this.magnetometerRedundant = magnetometerRedundant;
	}

	public boolean isMagneticTorquerMain() {
		return magneticTorquerMain;
	}

	public void setMagneticTorquerMain(boolean magneticTorquerMain) {
		this.magneticTorquerMain = magneticTorquerMain;
	}

	public boolean isMagneticTorquerRedundant() {
		return magneticTorquerRedundant;
	}

	public void setMagneticTorquerRedundant(boolean magneticTorquerRedundant) {
		this.magneticTorquerRedundant = magneticTorquerRedundant;
	}

	public boolean isTRITEL() {
		return tRITEL;
	}

	public void setTRITEL(boolean tRITEL) {
		this.tRITEL = tRITEL;
	}

	public boolean isLangmuirProbe() {
		return langmuirProbe;
	}

	public void setLangmuirProbe(boolean langmuirProbe) {
		this.langmuirProbe = langmuirProbe;
	}

	public boolean isPCAM() {
		return pCAM;
	}

	public void setPCAM(boolean pCAM) {
		this.pCAM = pCAM;
	}

	public boolean isAmsatUk() {
		return amsatUk;
	}

	public void setAmsatUk(boolean amsatUk) {
		this.amsatUk = amsatUk;
	}

	public boolean isSBand() {
		return sBand;
	}

	public void setSBand(boolean sBand) {
		this.sBand = sBand;
	}

	public boolean isGpsReceiver() {
		return gpsReceiver;
	}

	public void setGpsReceiver(boolean gpsReceiver) {
		this.gpsReceiver = gpsReceiver;
	}

	public boolean isADE() {
		return aDE;
	}

	public void setADE(boolean aDE) {
		this.aDE = aDE;
	}

	public boolean isSCAM() {
		return sCAM;
	}

	public void setSCAM(boolean sCAM) {
		this.sCAM = sCAM;
	}

	public boolean isDeOrbitMechanism() {
		return deOrbitMechanism;
	}

	public void setDeOrbitMechanism(boolean deOrbitMechanism) {
		this.deOrbitMechanism = deOrbitMechanism;
	}

}
