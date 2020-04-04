package ru.r2cloud.jradio.swampsat2;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Battery {

	private double voltage;
	private double current;
	private double temperatureMotherboard;
	private double temperatureDaughterboard1;
	private double temperatureDaughterboard2;
	private double temperatureDaughterboard3;
	private double temperatureDaughterboard4;

	private boolean heaterstatus1;
	private boolean heaterstatus2;
	private boolean heaterstatus3;
	private boolean heaterstatus4;

	public Battery() {
		// do nothing
	}

	public Battery(LittleEndianDataInputStream dis) throws IOException {
		voltage = dis.readUnsignedShort() * 0.008993;
		current = dis.readUnsignedShort() * 14.662757 / 1000;
		temperatureMotherboard = dis.readUnsignedShort() * 0.372434 - 273.15;
		temperatureDaughterboard1 = dis.readUnsignedShort() * 0.3976 - 238.57;
		temperatureDaughterboard2 = dis.readUnsignedShort() * 0.3976 - 238.57;
		temperatureDaughterboard3 = dis.readUnsignedShort() * 0.3976 - 238.57;
		temperatureDaughterboard4 = dis.readUnsignedShort() * 0.3976 - 238.57;

		int raw = dis.readUnsignedByte();
		heaterstatus1 = ((raw >> 0) & 0x1) > 0;
		heaterstatus2 = ((raw >> 1) & 0x1) > 0;
		heaterstatus3 = ((raw >> 2) & 0x1) > 0;
		heaterstatus4 = ((raw >> 3) & 0x1) > 0;
	}

	public double getVoltage() {
		return voltage;
	}

	public void setVoltage(double voltage) {
		this.voltage = voltage;
	}

	public double getCurrent() {
		return current;
	}

	public void setCurrent(double current) {
		this.current = current;
	}

	public double getTemperatureMotherboard() {
		return temperatureMotherboard;
	}

	public void setTemperatureMotherboard(double temperatureMotherboard) {
		this.temperatureMotherboard = temperatureMotherboard;
	}

	public double getTemperatureDaughterboard1() {
		return temperatureDaughterboard1;
	}

	public void setTemperatureDaughterboard1(double temperatureDaughterboard1) {
		this.temperatureDaughterboard1 = temperatureDaughterboard1;
	}

	public double getTemperatureDaughterboard2() {
		return temperatureDaughterboard2;
	}

	public void setTemperatureDaughterboard2(double temperatureDaughterboard2) {
		this.temperatureDaughterboard2 = temperatureDaughterboard2;
	}

	public double getTemperatureDaughterboard3() {
		return temperatureDaughterboard3;
	}

	public void setTemperatureDaughterboard3(double temperatureDaughterboard3) {
		this.temperatureDaughterboard3 = temperatureDaughterboard3;
	}

	public double getTemperatureDaughterboard4() {
		return temperatureDaughterboard4;
	}

	public void setTemperatureDaughterboard4(double temperatureDaughterboard4) {
		this.temperatureDaughterboard4 = temperatureDaughterboard4;
	}

	public boolean isHeaterstatus1() {
		return heaterstatus1;
	}

	public void setHeaterstatus1(boolean heaterstatus1) {
		this.heaterstatus1 = heaterstatus1;
	}

	public boolean isHeaterstatus2() {
		return heaterstatus2;
	}

	public void setHeaterstatus2(boolean heaterstatus2) {
		this.heaterstatus2 = heaterstatus2;
	}

	public boolean isHeaterstatus3() {
		return heaterstatus3;
	}

	public void setHeaterstatus3(boolean heaterstatus3) {
		this.heaterstatus3 = heaterstatus3;
	}

	public boolean isHeaterstatus4() {
		return heaterstatus4;
	}

	public void setHeaterstatus4(boolean heaterstatus4) {
		this.heaterstatus4 = heaterstatus4;
	}

}
