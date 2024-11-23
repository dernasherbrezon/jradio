package ru.r2cloud.jradio.dora;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class Battery {

	private float motherboardTemperature;
	private float outputVoltageBattery;
	private float currentMagnitude;
	private float daughterTemp1;
	private float daughterTemp2;
	private float daughterTemp3;
	private boolean heaterStatus1;
	private boolean heaterStatus2;
	private boolean heaterStatus3;
	private int currentDirection;

	public Battery() {
		// do nothing
	}

	public Battery(DataInputStream dis) throws IOException {
		motherboardTemperature = dis.readUnsignedShort() * 0.372434f - 273.15f;
		outputVoltageBattery = dis.readUnsignedShort() * 0.008993f;
		currentMagnitude = dis.readUnsignedShort() * 14.662757f;
		daughterTemp1 = dis.readUnsignedShort() * 0.397600f - 238.57f;
		daughterTemp2 = dis.readUnsignedShort() * 0.397600f - 238.57f;
		daughterTemp3 = dis.readUnsignedShort() * 0.397600f - 238.57f;
		BitInputStream bis = new BitInputStream(dis);
		heaterStatus1 = bis.readBoolean();
		heaterStatus2 = bis.readBoolean();
		heaterStatus3 = bis.readBoolean();
		currentDirection = bis.readUnsignedInt(5);
	}

	public float getMotherboardTemperature() {
		return motherboardTemperature;
	}

	public void setMotherboardTemperature(float motherboardTemperature) {
		this.motherboardTemperature = motherboardTemperature;
	}

	public float getOutputVoltageBattery() {
		return outputVoltageBattery;
	}

	public void setOutputVoltageBattery(float outputVoltageBattery) {
		this.outputVoltageBattery = outputVoltageBattery;
	}

	public float getCurrentMagnitude() {
		return currentMagnitude;
	}

	public void setCurrentMagnitude(float currentMagnitude) {
		this.currentMagnitude = currentMagnitude;
	}

	public float getDaughterTemp1() {
		return daughterTemp1;
	}

	public void setDaughterTemp1(float daughterTemp1) {
		this.daughterTemp1 = daughterTemp1;
	}

	public float getDaughterTemp2() {
		return daughterTemp2;
	}

	public void setDaughterTemp2(float daughterTemp2) {
		this.daughterTemp2 = daughterTemp2;
	}

	public float getDaughterTemp3() {
		return daughterTemp3;
	}

	public void setDaughterTemp3(float daughterTemp3) {
		this.daughterTemp3 = daughterTemp3;
	}

	public boolean isHeaterStatus1() {
		return heaterStatus1;
	}

	public void setHeaterStatus1(boolean heaterStatus1) {
		this.heaterStatus1 = heaterStatus1;
	}

	public boolean isHeaterStatus2() {
		return heaterStatus2;
	}

	public void setHeaterStatus2(boolean heaterStatus2) {
		this.heaterStatus2 = heaterStatus2;
	}

	public boolean isHeaterStatus3() {
		return heaterStatus3;
	}

	public void setHeaterStatus3(boolean heaterStatus3) {
		this.heaterStatus3 = heaterStatus3;
	}

	public int getCurrentDirection() {
		return currentDirection;
	}

	public void setCurrentDirection(int currentDirection) {
		this.currentDirection = currentDirection;
	}

}
