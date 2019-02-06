package ru.r2cloud.jradio.jy1sat;

import java.io.IOException;

import ru.r2cloud.jradio.ao73.PaTemperature;
import ru.r2cloud.jradio.util.BitInputStream;

public class WholeOrbit {

	private float cctMictocontrollerTemp;
	private float rfBoardCrystalTemp;
	private float powerAmplifierTemp;

	private float solarPanelTempXP;
	private float solarPanelTempXM;
	private float solarPanelTempYP;
	private float solarPanelTempYM;
	private float solarPanelTempZP;
	private float solarPanelTempZM;

	private int sunSensorXP;
	private int sunSensorXM;
	private int sunSensorYP;
	private int sunSensorYM;
	private int sunSensorZP;
	private int sunSensorZM;

	private int batteryTemp;
	private int totPhotoCurr;
	private int batteryVoltage;
	private int totSystemCurr;

	public WholeOrbit(BitInputStream dis) throws IOException {
		cctMictocontrollerTemp = dis.readUnsignedInt(12);
		rfBoardCrystalTemp = ((dis.readUnsignedInt(12) & 1023) * -0.98f) + 234.58f;
		powerAmplifierTemp = PaTemperature.getPaTemp(dis.readUnsignedInt(12));

		solarPanelTempXP = -0.2073f * dis.readUnsignedInt(10) + 158.239f;
		solarPanelTempXM = -0.2083f * dis.readUnsignedInt(10) + 159.227f;
		solarPanelTempYP = -0.2076f * dis.readUnsignedInt(10) + 158.656f;
		solarPanelTempYM = -0.2087f * dis.readUnsignedInt(10) + 159.045f;
		solarPanelTempZP = -0.2076f * dis.readUnsignedInt(10) + 158.656f;
		solarPanelTempZM = -0.2087f * dis.readUnsignedInt(10) + 159.045f;

		sunSensorXP = dis.readUnsignedInt(10);
		sunSensorXM = dis.readUnsignedInt(10);
		sunSensorYP = dis.readUnsignedInt(10);
		sunSensorYM = dis.readUnsignedInt(10);
		sunSensorZP = dis.readUnsignedInt(10);
		sunSensorZM = dis.readUnsignedInt(10);

		batteryTemp = dis.readUnsignedInt(8);
		totPhotoCurr = dis.readUnsignedInt(10);
		batteryVoltage = dis.readUnsignedInt(14);
		totSystemCurr = dis.readUnsignedInt(12);
	}

	public float getCctMictocontrollerTemp() {
		return cctMictocontrollerTemp;
	}

	public void setCctMictocontrollerTemp(float cctMictocontrollerTemp) {
		this.cctMictocontrollerTemp = cctMictocontrollerTemp;
	}

	public float getRfBoardCrystalTemp() {
		return rfBoardCrystalTemp;
	}

	public void setRfBoardCrystalTemp(float rfBoardCrystalTemp) {
		this.rfBoardCrystalTemp = rfBoardCrystalTemp;
	}

	public float getPowerAmplifierTemp() {
		return powerAmplifierTemp;
	}

	public void setPowerAmplifierTemp(float powerAmplifierTemp) {
		this.powerAmplifierTemp = powerAmplifierTemp;
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

	public float getSolarPanelTempZP() {
		return solarPanelTempZP;
	}

	public void setSolarPanelTempZP(float solarPanelTempZP) {
		this.solarPanelTempZP = solarPanelTempZP;
	}

	public float getSolarPanelTempZM() {
		return solarPanelTempZM;
	}

	public void setSolarPanelTempZM(float solarPanelTempZM) {
		this.solarPanelTempZM = solarPanelTempZM;
	}

	public int getSunSensorXP() {
		return sunSensorXP;
	}

	public void setSunSensorXP(int sunSensorXP) {
		this.sunSensorXP = sunSensorXP;
	}

	public int getSunSensorXM() {
		return sunSensorXM;
	}

	public void setSunSensorXM(int sunSensorXM) {
		this.sunSensorXM = sunSensorXM;
	}

	public int getSunSensorYP() {
		return sunSensorYP;
	}

	public void setSunSensorYP(int sunSensorYP) {
		this.sunSensorYP = sunSensorYP;
	}

	public int getSunSensorYM() {
		return sunSensorYM;
	}

	public void setSunSensorYM(int sunSensorYM) {
		this.sunSensorYM = sunSensorYM;
	}

	public int getSunSensorZP() {
		return sunSensorZP;
	}

	public void setSunSensorZP(int sunSensorZP) {
		this.sunSensorZP = sunSensorZP;
	}

	public int getSunSensorZM() {
		return sunSensorZM;
	}

	public void setSunSensorZM(int sunSensorZM) {
		this.sunSensorZM = sunSensorZM;
	}

	public int getBatteryTemp() {
		return batteryTemp;
	}

	public void setBatteryTemp(int batteryTemp) {
		this.batteryTemp = batteryTemp;
	}

	public int getTotPhotoCurr() {
		return totPhotoCurr;
	}

	public void setTotPhotoCurr(int totPhotoCurr) {
		this.totPhotoCurr = totPhotoCurr;
	}

	public int getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(int batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public int getTotSystemCurr() {
		return totSystemCurr;
	}

	public void setTotSystemCurr(int totSystemCurr) {
		this.totSystemCurr = totSystemCurr;
	}

}
