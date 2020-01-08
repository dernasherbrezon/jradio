package ru.r2cloud.jradio.ao73;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class WholeOrbit {

	private float tempThermistorBlackChassis;
	private float tempThermistorSilverChassis;
	private float tempThermistorBlackPanel;
	private float tempThermistorSilverPanel;

	private float solarPanelTempXP;
	private float solarPanelTempXM;
	private float solarPanelTempYP;
	private float solarPanelTempYM;

	private int solarPanelVoltageX;
	private int solarPanelVoltageY;
	private int solarPanelVoltageZ;
	private int totalPhotoCurrent;
	private int batteryVoltage;
	private int totalSystemCurrent;
	
	public WholeOrbit() {
		// do nothing
	}

	public WholeOrbit(BitInputStream dis) throws IOException {
		tempThermistorBlackChassis = (-0.024f * dis.readUnsignedInt(12)) + 75.244f;
		tempThermistorSilverChassis = (-0.024f * dis.readUnsignedInt(12)) + 74.750f;
		tempThermistorBlackPanel = (-0.024f * dis.readUnsignedInt(12)) + 75.039f;
		tempThermistorSilverPanel = (-0.024f * dis.readUnsignedInt(12)) + 75.987f;

		solarPanelTempXP = -0.2073f * dis.readUnsignedInt(10) + 158.239f;
		solarPanelTempXM = -0.2083f * dis.readUnsignedInt(10) + 159.227f;
		solarPanelTempYP = -0.2076f * dis.readUnsignedInt(10) + 158.656f;
		solarPanelTempYM = -0.2087f * dis.readUnsignedInt(10) + 159.045f;

		solarPanelVoltageX = dis.readUnsignedShort();
		solarPanelVoltageY = dis.readUnsignedShort();
		solarPanelVoltageZ = dis.readUnsignedShort();
		totalPhotoCurrent = dis.readUnsignedShort();
		batteryVoltage = dis.readUnsignedShort();
		totalSystemCurrent = dis.readUnsignedShort();
	}

	public float getTempThermistorBlackChassis() {
		return tempThermistorBlackChassis;
	}

	public void setTempThermistorBlackChassis(float tempThermistorBlackChassis) {
		this.tempThermistorBlackChassis = tempThermistorBlackChassis;
	}

	public float getTempThermistorSilverChassis() {
		return tempThermistorSilverChassis;
	}

	public void setTempThermistorSilverChassis(float tempThermistorSilverChassis) {
		this.tempThermistorSilverChassis = tempThermistorSilverChassis;
	}

	public float getTempThermistorBlackPanel() {
		return tempThermistorBlackPanel;
	}

	public void setTempThermistorBlackPanel(float tempThermistorBlackPanel) {
		this.tempThermistorBlackPanel = tempThermistorBlackPanel;
	}

	public float getTempThermistorSilverPanel() {
		return tempThermistorSilverPanel;
	}

	public void setTempThermistorSilverPanel(float tempThermistorSilverPanel) {
		this.tempThermistorSilverPanel = tempThermistorSilverPanel;
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

}
