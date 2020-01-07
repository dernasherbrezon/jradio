package ru.r2cloud.jradio.nayif1;

import java.io.IOException;

import ru.r2cloud.jradio.ao73.PaTemperature;
import ru.r2cloud.jradio.util.BitInputStream;

public class WholeOrbit {

	private double cctMictocontrollerTemp;
	private double rfBoardCrystalTemp;
	private double powerAmplifierTemp;

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

	private long batteryTemp;
	private int totPhotoCurr;
	private int batteryVoltage;
	private int totSystemCurr;
	
	public WholeOrbit() {
		// do nothing
	}

	public WholeOrbit(BitInputStream dis) throws IOException {
		cctMictocontrollerTemp = calcMcuTemp(dis.readUnsignedInt(12));
		rfBoardCrystalTemp = ((dis.readUnsignedInt(12) & 1023) * -0.3465) + 266.70646;
		powerAmplifierTemp = PaTemperature.getPaTemp1024(dis.readUnsignedInt(12) & 1023);

		solarPanelTempXP = -0.2080f * dis.readUnsignedInt(10) + 158.792f;
		solarPanelTempXM = -0.2080f * dis.readUnsignedInt(10) + 158.792f;
		solarPanelTempYP = -0.2080f * dis.readUnsignedInt(10) + 158.792f;
		solarPanelTempYM = -0.2080f * dis.readUnsignedInt(10) + 158.792f;
		solarPanelTempZP = -0.2080f * dis.readUnsignedInt(10) + 158.792f;
		solarPanelTempZM = -0.2080f * dis.readUnsignedInt(10) + 158.792f;

		sunSensorXP = dis.readUnsignedInt(10);
		sunSensorXM = dis.readUnsignedInt(10);
		sunSensorYP = dis.readUnsignedInt(10);
		sunSensorYM = dis.readUnsignedInt(10);
		sunSensorZP = dis.readUnsignedInt(10);
		sunSensorZM = dis.readUnsignedInt(10);

		batteryTemp = twosComplement(dis.readUnsignedInt(8));
		totPhotoCurr = dis.readUnsignedInt(10);
		batteryVoltage = dis.readUnsignedInt(14);
		totSystemCurr = dis.readUnsignedInt(12);
	}

	private static double calcMcuTemp(long adc) {
		double vt = adc * 0.00078753; // Convert the ADC reading into voltage

		if (vt >= 0.7012) // Check for Hot or Cold Slope
		{
			return 25.0 - ((vt - 0.7012) / 0.001646); // Cold Slope)
		} else {
			return 25.0 - ((vt - 0.7012) / 0.001749); // Hot Slope
		}
	}

	private static long twosComplement(long value) {
		long channelValue = value;
		if (channelValue >= 128) {
			channelValue = ~channelValue ^ 255;
		}
		return channelValue;
	}

	public double getCctMictocontrollerTemp() {
		return cctMictocontrollerTemp;
	}

	public void setCctMictocontrollerTemp(double cctMictocontrollerTemp) {
		this.cctMictocontrollerTemp = cctMictocontrollerTemp;
	}

	public double getRfBoardCrystalTemp() {
		return rfBoardCrystalTemp;
	}

	public void setRfBoardCrystalTemp(double rfBoardCrystalTemp) {
		this.rfBoardCrystalTemp = rfBoardCrystalTemp;
	}

	public double getPowerAmplifierTemp() {
		return powerAmplifierTemp;
	}

	public void setPowerAmplifierTemp(double powerAmplifierTemp) {
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

	public long getBatteryTemp() {
		return batteryTemp;
	}

	public void setBatteryTemp(long batteryTemp) {
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
