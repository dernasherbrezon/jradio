package ru.r2cloud.jradio.eseo.funcube;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class OBDEquipmentStatus {

	private boolean tmtcMain;
	private boolean tmtcRedundant;
	private boolean powerManagementUnitMain;
	private boolean powerManagementUnitRedundant;
	private boolean sunSensorMain;
	private boolean sunSensorRedundant;
	private boolean earthSensor;
	private boolean magnetometerMain;
	private boolean magnetometerRedundant;
	private boolean magneticTorquerMain;
	private boolean magneticTorquerRedundant;
	private boolean momentumWheelMain;
	private boolean momentumWheelRedundant;
	private boolean tritel;
	private boolean langmuirProbe;
	private boolean uCAM;
	private boolean deorbitmechanism;
	private boolean amsatuk;
	private boolean sBand;
	private boolean gpsReceiver;

	public OBDEquipmentStatus(BitInputStream dis) throws IOException {
		tmtcMain = dis.readBoolean();
		tmtcRedundant = dis.readBoolean();
		powerManagementUnitMain = dis.readBoolean();
		powerManagementUnitRedundant = dis.readBoolean();
		sunSensorMain = dis.readBoolean();
		sunSensorRedundant = dis.readBoolean();
		earthSensor = dis.readBoolean();
		magnetometerMain = dis.readBoolean();
		magnetometerRedundant = dis.readBoolean();
		magneticTorquerMain = dis.readBoolean();
		magneticTorquerRedundant = dis.readBoolean();
		momentumWheelMain = dis.readBoolean();
		momentumWheelRedundant = dis.readBoolean();
		tritel = dis.readBoolean();
		langmuirProbe = dis.readBoolean();
		uCAM = dis.readBoolean();
		deorbitmechanism = dis.readBoolean();
		amsatuk = dis.readBoolean();
		sBand = dis.readBoolean();
		gpsReceiver = dis.readBoolean();
	}

	public boolean isTmtcMain() {
		return tmtcMain;
	}

	public void setTmtcMain(boolean tmtcMain) {
		this.tmtcMain = tmtcMain;
	}

	public boolean isTmtcRedundant() {
		return tmtcRedundant;
	}

	public void setTmtcRedundant(boolean tmtcRedundant) {
		this.tmtcRedundant = tmtcRedundant;
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

	public boolean isTritel() {
		return tritel;
	}

	public void setTritel(boolean tritel) {
		this.tritel = tritel;
	}

	public boolean isLangmuirProbe() {
		return langmuirProbe;
	}

	public void setLangmuirProbe(boolean langmuirProbe) {
		this.langmuirProbe = langmuirProbe;
	}

	public boolean isuCAM() {
		return uCAM;
	}

	public void setuCAM(boolean uCAM) {
		this.uCAM = uCAM;
	}

	public boolean isDeorbitmechanism() {
		return deorbitmechanism;
	}

	public void setDeorbitmechanism(boolean deorbitmechanism) {
		this.deorbitmechanism = deorbitmechanism;
	}

	public boolean isAmsatuk() {
		return amsatuk;
	}

	public void setAmsatuk(boolean amsatuk) {
		this.amsatuk = amsatuk;
	}

	public boolean issBand() {
		return sBand;
	}

	public void setsBand(boolean sBand) {
		this.sBand = sBand;
	}

	public boolean isGpsReceiver() {
		return gpsReceiver;
	}

	public void setGpsReceiver(boolean gpsReceiver) {
		this.gpsReceiver = gpsReceiver;
	}

}
