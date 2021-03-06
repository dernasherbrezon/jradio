package ru.r2cloud.jradio.huskysat1;

import java.io.IOException;

import ru.r2cloud.jradio.fox.LookupTables;
import ru.r2cloud.jradio.util.LsbBitInputStream;

public class PayloadData {

	private static final String HUSKYSAT_IHUVBATT = "HUSKYSAT_IHUVBATT";
	private static final float PA_CURRENT_INA194_FACTOR = 50;
	private static final float VOLTAGE_STEP_FOR_2V5_SENSORS = 2.5f / 4096;

	private float vBattVoltage;
	private float gyroTemperature;
	private float txPowerAmplifierCurrent;
	private float txTemperature;
	private float fwdPower;
	private float rssi;
	private float ihuCpuTemperature;
	private float antennaTemperature;
	private float xSpin;
	private float ySpin;
	private float zSpin;
	private float xAcceleration;
	private float yAcceleration;
	private float zAcceleration;
	private float xMag;
	private float yMag;
	private float zMag;
	private Float reflectedPower;
	private float icr3VProtected;
	private float distBoardStatus;
	private float icr2dot5V;
	private float panelMinusX;
	private float panelPlusX;
	private float panelPlusY;
	private float sensorsVoltage;
	private float ltVgaControl;

	private double ant1Time;
	private double ant2Time;
	private double ant3Time;
	private double ant4Time;
	private int ant1Cnt;
	private int ant2Cnt;
	private int ant3Cnt;
	private int ant4Cnt;

	public PayloadData() {
		// do nothing
	}

	public PayloadData(LsbBitInputStream dis) throws IOException {
		vBattVoltage = LookupTables.lookup(HUSKYSAT_IHUVBATT, dis.readBitsAsInt(12)) / 2 / 0.2424f;
		gyroTemperature = dis.readBitsAsInt(12) * 0.1f - 40;
		txPowerAmplifierCurrent = convert(dis);
		txTemperature = convertTemperature(dis);
		fwdPower = convertFwdPower(dis);
		rssi = convertRssi(dis);
		ihuCpuTemperature = LookupTables.lookup("HUSKYSAT_IHUTEMP", dis.readBitsAsInt(12));
		antennaTemperature = convertAntennaTemperature(dis);
		xSpin = convertSpin(dis);
		ySpin = convertSpin(dis);
		zSpin = convertSpin(dis);
		xAcceleration = convertAccelerator(dis);
		yAcceleration = convertAccelerator(dis);
		zAcceleration = convertAccelerator(dis);
		xMag = convertMagnetometer(dis);
		yMag = convertMagnetometer(dis);
		zMag = convertMagnetometer(dis);
		reflectedPower = convertReflectedPower(dis);
		icr3VProtected = convertIcr(dis);
		distBoardStatus = dis.readBitsAsInt(12) * VOLTAGE_STEP_FOR_2V5_SENSORS / 0.758f;
		icr2dot5V = convertIcr(dis);
		panelMinusX = dis.readBitsAsInt(12) * VOLTAGE_STEP_FOR_2V5_SENSORS / 0.324f;
		panelPlusX = dis.readBitsAsInt(12) * VOLTAGE_STEP_FOR_2V5_SENSORS / 0.324f;
		panelPlusY = dis.readBitsAsInt(12) * VOLTAGE_STEP_FOR_2V5_SENSORS / 0.324f;
		sensorsVoltage = convertIcr(dis);
		ltVgaControl = convertLtVga(dis);
		dis.readBitsAsInt(4);
		ant1Time = dis.readBitsAsInt(16) / 20.0d;
		ant2Time = dis.readBitsAsInt(16) / 20.0d;
		ant3Time = dis.readBitsAsInt(16) / 20.0d;
		ant4Time = dis.readBitsAsInt(16) / 20.0d;
		ant1Cnt = dis.readBitsAsInt(8);
		ant2Cnt = dis.readBitsAsInt(8);
		ant3Cnt = dis.readBitsAsInt(8);
		ant4Cnt = dis.readBitsAsInt(8);
	}

	public float getvBattVoltage() {
		return vBattVoltage;
	}

	public void setvBattVoltage(float vBattVoltage) {
		this.vBattVoltage = vBattVoltage;
	}

	public float getGyroTemperature() {
		return gyroTemperature;
	}

	public void setGyroTemperature(float gyroTemperature) {
		this.gyroTemperature = gyroTemperature;
	}

	public float getTxPowerAmplifierCurrent() {
		return txPowerAmplifierCurrent;
	}

	public void setTxPowerAmplifierCurrent(float txPowerAmplifierCurrent) {
		this.txPowerAmplifierCurrent = txPowerAmplifierCurrent;
	}

	public float getTxTemperature() {
		return txTemperature;
	}

	public void setTxTemperature(float txTemperature) {
		this.txTemperature = txTemperature;
	}

	public float getFwdPower() {
		return fwdPower;
	}

	public void setFwdPower(float fwdPower) {
		this.fwdPower = fwdPower;
	}

	public float getRssi() {
		return rssi;
	}

	public void setRssi(float rssi) {
		this.rssi = rssi;
	}

	public float getIhuCpuTemperature() {
		return ihuCpuTemperature;
	}

	public void setIhuCpuTemperature(float ihuCpuTemperature) {
		this.ihuCpuTemperature = ihuCpuTemperature;
	}

	public float getAntennaTemperature() {
		return antennaTemperature;
	}

	public void setAntennaTemperature(float antennaTemperature) {
		this.antennaTemperature = antennaTemperature;
	}

	public float getxSpin() {
		return xSpin;
	}

	public void setxSpin(float xSpin) {
		this.xSpin = xSpin;
	}

	public float getySpin() {
		return ySpin;
	}

	public void setySpin(float ySpin) {
		this.ySpin = ySpin;
	}

	public float getzSpin() {
		return zSpin;
	}

	public void setzSpin(float zSpin) {
		this.zSpin = zSpin;
	}

	public float getxAcceleration() {
		return xAcceleration;
	}

	public void setxAcceleration(float xAcceleration) {
		this.xAcceleration = xAcceleration;
	}

	public float getyAcceleration() {
		return yAcceleration;
	}

	public void setyAcceleration(float yAcceleration) {
		this.yAcceleration = yAcceleration;
	}

	public float getzAcceleration() {
		return zAcceleration;
	}

	public void setzAcceleration(float zAcceleration) {
		this.zAcceleration = zAcceleration;
	}

	public float getxMag() {
		return xMag;
	}

	public void setxMag(float xMag) {
		this.xMag = xMag;
	}

	public float getyMag() {
		return yMag;
	}

	public void setyMag(float yMag) {
		this.yMag = yMag;
	}

	public float getzMag() {
		return zMag;
	}

	public void setzMag(float zMag) {
		this.zMag = zMag;
	}

	public Float getReflectedPower() {
		return reflectedPower;
	}

	public void setReflectedPower(Float reflectedPower) {
		this.reflectedPower = reflectedPower;
	}

	public float getIcr3VProtected() {
		return icr3VProtected;
	}

	public void setIcr3VProtected(float icr3vProtected) {
		icr3VProtected = icr3vProtected;
	}

	public float getDistBoardStatus() {
		return distBoardStatus;
	}

	public void setDistBoardStatus(float distBoardStatus) {
		this.distBoardStatus = distBoardStatus;
	}

	public float getIcr2dot5V() {
		return icr2dot5V;
	}

	public void setIcr2dot5V(float icr2dot5v) {
		icr2dot5V = icr2dot5v;
	}

	public float getPanelMinusX() {
		return panelMinusX;
	}

	public void setPanelMinusX(float panelMinusX) {
		this.panelMinusX = panelMinusX;
	}

	public float getPanelPlusX() {
		return panelPlusX;
	}

	public void setPanelPlusX(float panelPlusX) {
		this.panelPlusX = panelPlusX;
	}

	public float getPanelPlusY() {
		return panelPlusY;
	}

	public void setPanelPlusY(float panelPlusY) {
		this.panelPlusY = panelPlusY;
	}

	public float getSensorsVoltage() {
		return sensorsVoltage;
	}

	public void setSensorsVoltage(float sensorsVoltage) {
		this.sensorsVoltage = sensorsVoltage;
	}

	public float getLtVgaControl() {
		return ltVgaControl;
	}

	public void setLtVgaControl(float ltVgaControl) {
		this.ltVgaControl = ltVgaControl;
	}

	public double getAnt1Time() {
		return ant1Time;
	}

	public void setAnt1Time(double ant1Time) {
		this.ant1Time = ant1Time;
	}

	public double getAnt2Time() {
		return ant2Time;
	}

	public void setAnt2Time(double ant2Time) {
		this.ant2Time = ant2Time;
	}

	public double getAnt3Time() {
		return ant3Time;
	}

	public void setAnt3Time(double ant3Time) {
		this.ant3Time = ant3Time;
	}

	public double getAnt4Time() {
		return ant4Time;
	}

	public void setAnt4Time(double ant4Time) {
		this.ant4Time = ant4Time;
	}

	public int getAnt1Cnt() {
		return ant1Cnt;
	}

	public void setAnt1Cnt(int ant1Cnt) {
		this.ant1Cnt = ant1Cnt;
	}

	public int getAnt2Cnt() {
		return ant2Cnt;
	}

	public void setAnt2Cnt(int ant2Cnt) {
		this.ant2Cnt = ant2Cnt;
	}

	public int getAnt3Cnt() {
		return ant3Cnt;
	}

	public void setAnt3Cnt(int ant3Cnt) {
		this.ant3Cnt = ant3Cnt;
	}

	public int getAnt4Cnt() {
		return ant4Cnt;
	}

	public void setAnt4Cnt(int ant4Cnt) {
		this.ant4Cnt = ant4Cnt;
	}

	private static float convert(LsbBitInputStream dis) throws IOException {
		float voltspa = LookupTables.lookup(HUSKYSAT_IHUVBATT, dis.readBitsAsInt(12));
		voltspa = voltspa / 2;
		float pacurrent = voltspa / PA_CURRENT_INA194_FACTOR / 0.1f;
		return 1000 * pacurrent;
	}

	private static float convertTemperature(LsbBitInputStream dis) throws IOException {
		float volts = LookupTables.lookup(HUSKYSAT_IHUVBATT, dis.readBitsAsInt(12));
		volts = volts / 2;
		return 100 * volts - 50; // TMP36 sensor conversion graph is a straight line where 0.5V is 0C and 0.01V rise is 1C increase. So 0.75V is 25C
	}

	private static float convertFwdPower(LsbBitInputStream dis) throws IOException {
		float x = LookupTables.lookup(HUSKYSAT_IHUVBATT, dis.readBitsAsInt(12));
		x = x / 2;
		double y = 1.7685f * Math.pow(x, 3) - 13.107f * Math.pow(x, 2) + 36.436f * x - 13.019f;
		return (float) Math.pow(10, y / 10);
	}

	private static float convertRssi(LsbBitInputStream dis) throws IOException {
		float x = LookupTables.lookup(HUSKYSAT_IHUVBATT, dis.readBitsAsInt(12));
		x = x / 2;
		return 46.566f * x - 135.54f;
	}

	private static float convertAntennaTemperature(LsbBitInputStream dis) throws IOException {
		double v = (3.3 / 1023) * 1000 * dis.readBitsAsInt(12);
		return LookupTables.lookup("HUSKYSAT_ISISANTTEMP", (int) v);
	}

	private static float convertSpin(LsbBitInputStream dis) throws IOException {
		float spin = (float) dis.readBitsAsInt(16) - 32768; // signed 16 bit with 32768 added by IHU
		spin = spin / 131.0f; // 131 per dps
		return spin;
	}

	private static float convertAccelerator(LsbBitInputStream dis) throws IOException {
		float acc = (float) dis.readBitsAsInt(16) - 32768; // signed 16 bit with 32768 added by IHU
		acc = acc / 16384.0f; // 16384 per g. If we want this in m/s * 9.80665
		return acc;
	}

	private static float convertMagnetometer(LsbBitInputStream dis) throws IOException {
		float mag = (float) dis.readBitsAsInt(16) - 32768; // signed 16 bit with 32768 added by IHU
		mag = mag * 0.6f; // 0.6 micro Tesla per count value
		return mag;
	}

	private static Float convertReflectedPower(LsbBitInputStream dis) throws IOException {
		float x = dis.readBitsAsInt(12) * 1000 * VOLTAGE_STEP_FOR_2V5_SENSORS / 0.758f; // where 0.758 is the voltage divider
		float y = 0.1727f * x - 34.583f;
		float result = (float) Math.pow(10, y / 10);
		if (!Float.isFinite(result)) {
			return null;
		}
		return result;
	}

	private static float convertIcr(LsbBitInputStream dis) throws IOException {
		return dis.readBitsAsInt(12) * VOLTAGE_STEP_FOR_2V5_SENSORS / 0.758f;
	}

	private static float convertLtVga(LsbBitInputStream dis) throws IOException {
		float volts = LookupTables.lookup(HUSKYSAT_IHUVBATT, dis.readBitsAsInt(12));
		volts = volts / 2;
		return volts;
	}
}
