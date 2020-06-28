package ru.r2cloud.jradio.bsusat1;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianBitInputStream;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class EpsFullTelemetry extends EpsShortTelemetry {

	private float currentToGamma;
	private float currentToIrSensor;
	private float currentToExternalFlash;
	private float currentToSunSensors;
	private float currentToCoilsControl;
	private float currentToCoilX;
	private float currentToCoilY;
	private float currentToCoilPz;
	private float currentToCoilNz;
	private float battery1Temperature;
	private float battery2Temperature;
	private int obcOvercurrentCounter;
	private int gammaOvercurrentCounter;
	private int modem1OvercurrentCounter;
	private int modem2OvercurrentCounter;
	private int externalFlashOvercurrentCounter;
	private int irSensorOvercurrentCounter;
	private int coilXOvercurrentCounter;
	private int coilYOvercurrentCounter;
	private int coilPzOvercurrentCounter;
	private int coilNzOvercurrentCounter;
	private int coilsControlOvercurrentCounter;
	private int sunSensorOvercurrentCounter;
	private int epsRebootCounter;
	private int epsResetReason;

	private boolean systemPower;
	private boolean modem1Power;
	private boolean modem2Power;
	private boolean sunSensorsPower;
	private boolean gammaSensorPower;
	private boolean irSensorPower;
	private boolean externalFlashPower;
	private boolean xAxisControlPower;
	private boolean yAxisControlPower;
	private boolean zAxisControlPower;

	public EpsFullTelemetry() {
		// do nothing
	}

	public EpsFullTelemetry(LittleEndianDataInputStream dis) throws IOException {
		super(dis);
		LittleEndianBitInputStream bis = new LittleEndianBitInputStream(dis);
		currentToGamma = bis.readUnsignedInt(12) * 2.5f / 4096 / 0.1f / 50 * 1000;
		currentToIrSensor = bis.readUnsignedInt(12) * 2.5f / 4096 / 0.3f / 50 * 1000;
		currentToExternalFlash = bis.readUnsignedInt(12) * 2.5f / 4096 / 0.3f / 50 * 1000;
		currentToSunSensors = bis.readUnsignedInt(12) * 2.5f / 4096 / 0.2f / 50 * 1000;
		currentToCoilsControl = bis.readUnsignedInt(12) * 2.5f / 4096 / 0.1f / 50 * 1000;
		currentToCoilX = bis.readUnsignedInt(12) * 2.5f / 4096 / 0.3f / 20 * 1000;
		currentToCoilY = bis.readUnsignedInt(12) * 2.5f / 4096 / 0.3f / 20 * 1000;
		currentToCoilPz = bis.readUnsignedInt(12) * 2.5f / 4096 / 0.5f / 50 * 1000;
		currentToCoilNz = bis.readUnsignedInt(12) * 2.5f / 4096 / 0.5f / 50 * 1000;
		battery1Temperature = 1 / (1 / 298.15f + 1 / 3988f * (float) Math.log(10 / (5406.72 / bis.readUnsignedInt(12) - 1))) - 273.15f;
		battery2Temperature = 1 / (1 / 298.15f + 1 / 3988f * (float) Math.log(10 / (5406.72 / bis.readUnsignedInt(12) - 1))) - 273.15f;
		obcOvercurrentCounter = bis.readUnsignedByte();
		gammaOvercurrentCounter = bis.readUnsignedByte();
		modem1OvercurrentCounter = bis.readUnsignedByte();
		modem2OvercurrentCounter = bis.readUnsignedByte();
		externalFlashOvercurrentCounter = bis.readUnsignedByte();
		irSensorOvercurrentCounter = bis.readUnsignedByte();
		coilXOvercurrentCounter = bis.readUnsignedByte();
		coilYOvercurrentCounter = bis.readUnsignedByte();
		coilPzOvercurrentCounter = bis.readUnsignedByte();
		coilNzOvercurrentCounter = bis.readUnsignedByte();
		coilsControlOvercurrentCounter = bis.readUnsignedByte();
		sunSensorOvercurrentCounter = bis.readUnsignedByte();
		epsRebootCounter = bis.readUnsignedShort();
		epsResetReason = bis.readUnsignedByte();

		int raw = bis.readUnsignedByte();
		systemPower = (raw & 0x1) > 0;
		modem1Power = ((raw >> 1) & 0x1) > 0;
		modem2Power = ((raw >> 2) & 0x1) > 0;
		sunSensorsPower = ((raw >> 3) & 0x1) > 0;
		gammaSensorPower = ((raw >> 4) & 0x1) > 0;
		irSensorPower = ((raw >> 5) & 0x1) > 0;
		externalFlashPower = ((raw >> 6) & 0x1) > 0;
		xAxisControlPower = ((raw >> 7) & 0x1) > 0;

		raw = bis.readUnsignedByte();
		yAxisControlPower = (raw & 0x1) > 0;
		zAxisControlPower = ((raw >> 1) & 0x1) > 0;
	}

	public float getCurrentToGamma() {
		return currentToGamma;
	}

	public void setCurrentToGamma(float currentToGamma) {
		this.currentToGamma = currentToGamma;
	}

	public float getCurrentToIrSensor() {
		return currentToIrSensor;
	}

	public void setCurrentToIrSensor(float currentToIrSensor) {
		this.currentToIrSensor = currentToIrSensor;
	}

	public float getCurrentToExternalFlash() {
		return currentToExternalFlash;
	}

	public void setCurrentToExternalFlash(float currentToExternalFlash) {
		this.currentToExternalFlash = currentToExternalFlash;
	}

	public float getCurrentToSunSensors() {
		return currentToSunSensors;
	}

	public void setCurrentToSunSensors(float currentToSunSensors) {
		this.currentToSunSensors = currentToSunSensors;
	}

	public float getCurrentToCoilsControl() {
		return currentToCoilsControl;
	}

	public void setCurrentToCoilsControl(float currentToCoilsControl) {
		this.currentToCoilsControl = currentToCoilsControl;
	}

	public float getCurrentToCoilX() {
		return currentToCoilX;
	}

	public void setCurrentToCoilX(float currentToCoilX) {
		this.currentToCoilX = currentToCoilX;
	}

	public float getCurrentToCoilY() {
		return currentToCoilY;
	}

	public void setCurrentToCoilY(float currentToCoilY) {
		this.currentToCoilY = currentToCoilY;
	}

	public float getCurrentToCoilPz() {
		return currentToCoilPz;
	}

	public void setCurrentToCoilPz(float currentToCoilPz) {
		this.currentToCoilPz = currentToCoilPz;
	}

	public float getCurrentToCoilNz() {
		return currentToCoilNz;
	}

	public void setCurrentToCoilNz(float currentToCoilNz) {
		this.currentToCoilNz = currentToCoilNz;
	}

	public float getBattery1Temperature() {
		return battery1Temperature;
	}

	public void setBattery1Temperature(float battery1Temperature) {
		this.battery1Temperature = battery1Temperature;
	}

	public float getBattery2Temperature() {
		return battery2Temperature;
	}

	public void setBattery2Temperature(float battery2Temperature) {
		this.battery2Temperature = battery2Temperature;
	}

	public int getObcOvercurrentCounter() {
		return obcOvercurrentCounter;
	}

	public void setObcOvercurrentCounter(int obcOvercurrentCounter) {
		this.obcOvercurrentCounter = obcOvercurrentCounter;
	}

	public int getGammaOvercurrentCounter() {
		return gammaOvercurrentCounter;
	}

	public void setGammaOvercurrentCounter(int gammaOvercurrentCounter) {
		this.gammaOvercurrentCounter = gammaOvercurrentCounter;
	}

	public int getModem1OvercurrentCounter() {
		return modem1OvercurrentCounter;
	}

	public void setModem1OvercurrentCounter(int modem1OvercurrentCounter) {
		this.modem1OvercurrentCounter = modem1OvercurrentCounter;
	}

	public int getModem2OvercurrentCounter() {
		return modem2OvercurrentCounter;
	}

	public void setModem2OvercurrentCounter(int modem2OvercurrentCounter) {
		this.modem2OvercurrentCounter = modem2OvercurrentCounter;
	}

	public int getExternalFlashOvercurrentCounter() {
		return externalFlashOvercurrentCounter;
	}

	public void setExternalFlashOvercurrentCounter(int externalFlashOvercurrentCounter) {
		this.externalFlashOvercurrentCounter = externalFlashOvercurrentCounter;
	}

	public int getIrSensorOvercurrentCounter() {
		return irSensorOvercurrentCounter;
	}

	public void setIrSensorOvercurrentCounter(int irSensorOvercurrentCounter) {
		this.irSensorOvercurrentCounter = irSensorOvercurrentCounter;
	}

	public int getCoilXOvercurrentCounter() {
		return coilXOvercurrentCounter;
	}

	public void setCoilXOvercurrentCounter(int coilXOvercurrentCounter) {
		this.coilXOvercurrentCounter = coilXOvercurrentCounter;
	}

	public int getCoilYOvercurrentCounter() {
		return coilYOvercurrentCounter;
	}

	public void setCoilYOvercurrentCounter(int coilYOvercurrentCounter) {
		this.coilYOvercurrentCounter = coilYOvercurrentCounter;
	}

	public int getCoilPzOvercurrentCounter() {
		return coilPzOvercurrentCounter;
	}

	public void setCoilPzOvercurrentCounter(int coilPzOvercurrentCounter) {
		this.coilPzOvercurrentCounter = coilPzOvercurrentCounter;
	}

	public int getCoilNzOvercurrentCounter() {
		return coilNzOvercurrentCounter;
	}

	public void setCoilNzOvercurrentCounter(int coilNzOvercurrentCounter) {
		this.coilNzOvercurrentCounter = coilNzOvercurrentCounter;
	}

	public int getCoilsControlOvercurrentCounter() {
		return coilsControlOvercurrentCounter;
	}

	public void setCoilsControlOvercurrentCounter(int coilsControlOvercurrentCounter) {
		this.coilsControlOvercurrentCounter = coilsControlOvercurrentCounter;
	}

	public int getSunSensorOvercurrentCounter() {
		return sunSensorOvercurrentCounter;
	}

	public void setSunSensorOvercurrentCounter(int sunSensorOvercurrentCounter) {
		this.sunSensorOvercurrentCounter = sunSensorOvercurrentCounter;
	}

	public int getEpsRebootCounter() {
		return epsRebootCounter;
	}

	public void setEpsRebootCounter(int epsRebootCounter) {
		this.epsRebootCounter = epsRebootCounter;
	}

	public int getEpsResetReason() {
		return epsResetReason;
	}

	public void setEpsResetReason(int epsResetReason) {
		this.epsResetReason = epsResetReason;
	}

	public boolean isSystemPower() {
		return systemPower;
	}

	public void setSystemPower(boolean systemPower) {
		this.systemPower = systemPower;
	}

	public boolean isModem1Power() {
		return modem1Power;
	}

	public void setModem1Power(boolean modem1Power) {
		this.modem1Power = modem1Power;
	}

	public boolean isModem2Power() {
		return modem2Power;
	}

	public void setModem2Power(boolean modem2Power) {
		this.modem2Power = modem2Power;
	}

	public boolean isSunSensorsPower() {
		return sunSensorsPower;
	}

	public void setSunSensorsPower(boolean sunSensorsPower) {
		this.sunSensorsPower = sunSensorsPower;
	}

	public boolean isGammaSensorPower() {
		return gammaSensorPower;
	}

	public void setGammaSensorPower(boolean gammaSensorPower) {
		this.gammaSensorPower = gammaSensorPower;
	}

	public boolean isIrSensorPower() {
		return irSensorPower;
	}

	public void setIrSensorPower(boolean irSensorPower) {
		this.irSensorPower = irSensorPower;
	}

	public boolean isExternalFlashPower() {
		return externalFlashPower;
	}

	public void setExternalFlashPower(boolean externalFlashPower) {
		this.externalFlashPower = externalFlashPower;
	}

	public boolean isXAxisControlPower() {
		return xAxisControlPower;
	}

	public void setXAxisControlPower(boolean xAxisControlPower) {
		this.xAxisControlPower = xAxisControlPower;
	}

	public boolean isYAxisControlPower() {
		return yAxisControlPower;
	}

	public void setYAxisControlPower(boolean yAxisControlPower) {
		this.yAxisControlPower = yAxisControlPower;
	}

	public boolean isZAxisControlPower() {
		return zAxisControlPower;
	}

	public void setZAxisControlPower(boolean zAxisControlPower) {
		this.zAxisControlPower = zAxisControlPower;
	}

}
