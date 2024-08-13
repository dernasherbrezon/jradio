package ru.r2cloud.jradio.eirsat;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class Bat {

	private float batterycurrent2;
	private float batteryvoltage2;
	private float batterytemperature0;
	private float batterytemperature1;
	private float batterytemperature2;
	private boolean batterycurrentdir0;
	private boolean packedheaterstatus0;
	private boolean packedheaterstatus1;
	private boolean packedheaterstatus2;

	public Bat() {
		// do nothing
	}

	public Bat(BitInputStream dis) throws IOException {
		batterycurrent2 = dis.readUnsignedInt(10) * 14.662757f;
		batteryvoltage2 = dis.readUnsignedInt(10) * 0.008993f;
		batterytemperature0 = dis.readUnsignedInt(10) * 0.3976f - 238.57f;
		batterytemperature1 = dis.readUnsignedInt(10) * 0.3976f - 238.57f;
		batterytemperature2 = dis.readUnsignedInt(10) * 0.3976f - 238.57f;
		batterycurrentdir0 = dis.readBoolean();
		packedheaterstatus0 = dis.readBoolean();
		packedheaterstatus1 = dis.readBoolean();
		packedheaterstatus2 = dis.readBoolean();
	}

	public float getBatterycurrent2() {
		return batterycurrent2;
	}

	public void setBatterycurrent2(float batterycurrent2) {
		this.batterycurrent2 = batterycurrent2;
	}

	public float getBatteryvoltage2() {
		return batteryvoltage2;
	}

	public void setBatteryvoltage2(float batteryvoltage2) {
		this.batteryvoltage2 = batteryvoltage2;
	}

	public float getBatterytemperature0() {
		return batterytemperature0;
	}

	public void setBatterytemperature0(float batterytemperature0) {
		this.batterytemperature0 = batterytemperature0;
	}

	public float getBatterytemperature1() {
		return batterytemperature1;
	}

	public void setBatterytemperature1(float batterytemperature1) {
		this.batterytemperature1 = batterytemperature1;
	}

	public float getBatterytemperature2() {
		return batterytemperature2;
	}

	public void setBatterytemperature2(float batterytemperature2) {
		this.batterytemperature2 = batterytemperature2;
	}

	public boolean isBatterycurrentdir0() {
		return batterycurrentdir0;
	}

	public void setBatterycurrentdir0(boolean batterycurrentdir0) {
		this.batterycurrentdir0 = batterycurrentdir0;
	}

	public boolean isPackedheaterstatus0() {
		return packedheaterstatus0;
	}

	public void setPackedheaterstatus0(boolean packedheaterstatus0) {
		this.packedheaterstatus0 = packedheaterstatus0;
	}

	public boolean isPackedheaterstatus1() {
		return packedheaterstatus1;
	}

	public void setPackedheaterstatus1(boolean packedheaterstatus1) {
		this.packedheaterstatus1 = packedheaterstatus1;
	}

	public boolean isPackedheaterstatus2() {
		return packedheaterstatus2;
	}

	public void setPackedheaterstatus2(boolean packedheaterstatus2) {
		this.packedheaterstatus2 = packedheaterstatus2;
	}

}
