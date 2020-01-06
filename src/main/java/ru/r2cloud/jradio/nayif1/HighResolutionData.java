package ru.r2cloud.jradio.nayif1;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class HighResolutionData {

	private int sunSensorXP;
	private int sunSensorXM;
	private int sunSensorYP;
	private int sunSensorYM;
	private int sunSensorZP;
	private int sunSensorZM;

	private double xCalibMtm;
	private boolean xMeasValid;
	private boolean coilsActive;

	private double yCalibMtm;
	private boolean yMeasValid;
	private boolean measStartError;

	private double zCalibMtm;
	private boolean zMeasValid;
	private boolean measRetreiveError;

	public HighResolutionData(BitInputStream dis) throws IOException {
		sunSensorXP = dis.readUnsignedInt(10);
		sunSensorXM = dis.readUnsignedInt(10);
		sunSensorYP = dis.readUnsignedInt(10);
		sunSensorYM = dis.readUnsignedInt(10);
		sunSensorZP = dis.readUnsignedInt(10);
		sunSensorZM = dis.readUnsignedInt(10);

		xCalibMtm = calcMTM(dis.readUnsignedLong(22));
		xMeasValid = dis.readBoolean();
		coilsActive = dis.readBoolean();

		yCalibMtm = calcMTM(dis.readUnsignedLong(22));
		yMeasValid = dis.readBoolean();
		measStartError = dis.readBoolean();

		zCalibMtm = calcMTM(dis.readUnsignedLong(22));
		zMeasValid = dis.readBoolean();
		measRetreiveError = dis.readBoolean();
	}

	private static double calcMTM(long adc) {
		if ((adc & 0x00200000) == 0x00200000) {
			adc |= 0xFFC00000;
		}
		return adc * 4.0;
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

	public double getxCalibMtm() {
		return xCalibMtm;
	}

	public void setxCalibMtm(double xCalibMtm) {
		this.xCalibMtm = xCalibMtm;
	}

	public boolean isxMeasValid() {
		return xMeasValid;
	}

	public void setxMeasValid(boolean xMeasValid) {
		this.xMeasValid = xMeasValid;
	}

	public boolean isCoilsActive() {
		return coilsActive;
	}

	public void setCoilsActive(boolean coilsActive) {
		this.coilsActive = coilsActive;
	}

	public double getyCalibMtm() {
		return yCalibMtm;
	}

	public void setyCalibMtm(double yCalibMtm) {
		this.yCalibMtm = yCalibMtm;
	}

	public boolean isyMeasValid() {
		return yMeasValid;
	}

	public void setyMeasValid(boolean yMeasValid) {
		this.yMeasValid = yMeasValid;
	}

	public boolean isMeasStartError() {
		return measStartError;
	}

	public void setMeasStartError(boolean measStartError) {
		this.measStartError = measStartError;
	}

	public double getzCalibMtm() {
		return zCalibMtm;
	}

	public void setzCalibMtm(double zCalibMtm) {
		this.zCalibMtm = zCalibMtm;
	}

	public boolean iszMeasValid() {
		return zMeasValid;
	}

	public void setzMeasValid(boolean zMeasValid) {
		this.zMeasValid = zMeasValid;
	}

	public boolean isMeasRetreiveError() {
		return measRetreiveError;
	}

	public void setMeasRetreiveError(boolean measRetreiveError) {
		this.measRetreiveError = measRetreiveError;
	}

}
