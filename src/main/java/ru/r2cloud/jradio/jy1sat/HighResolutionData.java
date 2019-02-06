package ru.r2cloud.jradio.jy1sat;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class HighResolutionData {

	private float sunSensorXP;
	private float sunSensorXM;
	private float sunSensorYP;
	private float sunSensorYM;
	private float sunSensorZP;
	private float sunSensorZM;

	private int mtmXP;
	private int mtmYP;
	private int mtmZP;

	public HighResolutionData(BitInputStream dis) throws IOException {
		sunSensorXP = RealtimeTelemetry.readXPlusTemperature(dis);
		sunSensorXM = RealtimeTelemetry.readXMinusTemperature(dis);
		sunSensorYP = RealtimeTelemetry.readYPlusTemperature(dis);
		sunSensorYM = RealtimeTelemetry.readYMinusTemperature(dis);
		sunSensorZP = RealtimeTelemetry.readYPlusTemperature(dis);
		sunSensorZM = RealtimeTelemetry.readYMinusTemperature(dis);

		mtmXP = dis.readUnsignedInt(24);
		mtmYP = dis.readUnsignedInt(24);
		mtmZP = dis.readUnsignedInt(24);
	}

	public float getSunSensorXM() {
		return sunSensorXM;
	}

	public void setSunSensorXM(float sunSensorXM) {
		this.sunSensorXM = sunSensorXM;
	}

	public float getSunSensorXP() {
		return sunSensorXP;
	}

	public void setSunSensorXP(float sunSensorXP) {
		this.sunSensorXP = sunSensorXP;
	}

	public float getSunSensorYP() {
		return sunSensorYP;
	}

	public void setSunSensorYP(float sunSensorYP) {
		this.sunSensorYP = sunSensorYP;
	}

	public float getSunSensorYM() {
		return sunSensorYM;
	}

	public void setSunSensorYM(float sunSensorYM) {
		this.sunSensorYM = sunSensorYM;
	}

	public float getSunSensorZP() {
		return sunSensorZP;
	}

	public void setSunSensorZP(float sunSensorZP) {
		this.sunSensorZP = sunSensorZP;
	}

	public float getSunSensorZM() {
		return sunSensorZM;
	}

	public void setSunSensorZM(float sunSensorZM) {
		this.sunSensorZM = sunSensorZM;
	}

	public int getMtmXP() {
		return mtmXP;
	}

	public void setMtmXP(int mtmXP) {
		this.mtmXP = mtmXP;
	}

	public int getMtmYP() {
		return mtmYP;
	}

	public void setMtmYP(int mtmYP) {
		this.mtmYP = mtmYP;
	}

	public int getMtmZP() {
		return mtmZP;
	}

	public void setMtmZP(int mtmZP) {
		this.mtmZP = mtmZP;
	}

}
