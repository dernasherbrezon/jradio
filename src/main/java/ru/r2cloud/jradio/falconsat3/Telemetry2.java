package ru.r2cloud.jradio.falconsat3;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Telemetry2 extends TelemetryFrame {

	private float magnetometerXVoltage;
	private float magnetometerYVoltage;
	private float magnetometerZVoltage;
	private int magnetometer2p5VMon;
	private int minusXTorqueCurrent;
	private int plusXTorqueCurrent;
	private int minusYTorqueCurrent;
	private int plusYTorqueCurrent;
	private int suppressVFdbk;
	private int planeTSenseA;
	private int extraTlmSig;
	private int minusZTorqueCurrent;
	private int plusZTorqueCurrent;
	private int cgBoomTemp;
	private int edacAdr7;
	private int edacAdr15;
	private int edacAdr23;
	private int edacBank0;
	private int edacBank1;
	private int edacBank2;

	public Telemetry2() {
		// do nothing
	}

	public Telemetry2(DataInputStream dis) throws IOException {
		super(dis);
	}

	@Override
	public void read(LittleEndianDataInputStream ldis) throws IOException {
		magnetometerXVoltage = 0.001908f + ldis.readUnsignedShort() * 0.001011f;
		magnetometerYVoltage = 0.001908f + ldis.readUnsignedShort() * 0.001011f;
		magnetometerZVoltage = 0.001908f + ldis.readUnsignedShort() * 0.001011f;
		magnetometer2p5VMon = ldis.readUnsignedShort();

		ldis.skipBytes(20);

		minusXTorqueCurrent = ldis.readUnsignedShort();
		plusXTorqueCurrent = ldis.readUnsignedShort();
		minusYTorqueCurrent = ldis.readUnsignedShort();
		plusYTorqueCurrent = ldis.readUnsignedShort();

		ldis.skipBytes(17 * 2);

		suppressVFdbk = ldis.readUnsignedShort();

		ldis.skipBytes(2);

		planeTSenseA = ldis.readUnsignedShort();
		extraTlmSig = ldis.readUnsignedShort();
		minusZTorqueCurrent = ldis.readUnsignedShort();

		ldis.skipBytes(2);

		plusZTorqueCurrent = ldis.readUnsignedShort();
		cgBoomTemp = ldis.readUnsignedShort();

		ldis.skipBytes(14 * 2);

		edacAdr7 = ldis.readUnsignedShort();
		edacAdr15 = ldis.readUnsignedShort();
		edacAdr23 = ldis.readUnsignedShort();
		edacBank0 = ldis.readUnsignedShort();
		edacBank1 = ldis.readUnsignedShort();
		edacBank2 = ldis.readUnsignedShort();
	}

	public float getMagnetometerXVoltage() {
		return magnetometerXVoltage;
	}

	public void setMagnetometerXVoltage(float magnetometerXVoltage) {
		this.magnetometerXVoltage = magnetometerXVoltage;
	}

	public float getMagnetometerYVoltage() {
		return magnetometerYVoltage;
	}

	public void setMagnetometerYVoltage(float magnetometerYVoltage) {
		this.magnetometerYVoltage = magnetometerYVoltage;
	}

	public float getMagnetometerZVoltage() {
		return magnetometerZVoltage;
	}

	public void setMagnetometerZVoltage(float magnetometerZVoltage) {
		this.magnetometerZVoltage = magnetometerZVoltage;
	}

	public int getMagnetometer2p5VMon() {
		return magnetometer2p5VMon;
	}

	public void setMagnetometer2p5VMon(int magnetometer2p5vMon) {
		magnetometer2p5VMon = magnetometer2p5vMon;
	}

	public int getMinusXTorqueCurrent() {
		return minusXTorqueCurrent;
	}

	public void setMinusXTorqueCurrent(int minusXTorqueCurrent) {
		this.minusXTorqueCurrent = minusXTorqueCurrent;
	}

	public int getPlusXTorqueCurrent() {
		return plusXTorqueCurrent;
	}

	public void setPlusXTorqueCurrent(int plusXTorqueCurrent) {
		this.plusXTorqueCurrent = plusXTorqueCurrent;
	}

	public int getMinusYTorqueCurrent() {
		return minusYTorqueCurrent;
	}

	public void setMinusYTorqueCurrent(int minusYTorqueCurrent) {
		this.minusYTorqueCurrent = minusYTorqueCurrent;
	}

	public int getPlusYTorqueCurrent() {
		return plusYTorqueCurrent;
	}

	public void setPlusYTorqueCurrent(int plusYTorqueCurrent) {
		this.plusYTorqueCurrent = plusYTorqueCurrent;
	}

	public int getSuppressVFdbk() {
		return suppressVFdbk;
	}

	public void setSuppressVFdbk(int suppressVFdbk) {
		this.suppressVFdbk = suppressVFdbk;
	}

	public int getPlaneTSenseA() {
		return planeTSenseA;
	}

	public void setPlaneTSenseA(int planeTSenseA) {
		this.planeTSenseA = planeTSenseA;
	}

	public int getExtraTlmSig() {
		return extraTlmSig;
	}

	public void setExtraTlmSig(int extraTlmSig) {
		this.extraTlmSig = extraTlmSig;
	}

	public int getMinusZTorqueCurrent() {
		return minusZTorqueCurrent;
	}

	public void setMinusZTorqueCurrent(int minusZTorqueCurrent) {
		this.minusZTorqueCurrent = minusZTorqueCurrent;
	}

	public int getPlusZTorqueCurrent() {
		return plusZTorqueCurrent;
	}

	public void setPlusZTorqueCurrent(int plusZTorqueCurrent) {
		this.plusZTorqueCurrent = plusZTorqueCurrent;
	}

	public int getCgBoomTemp() {
		return cgBoomTemp;
	}

	public void setCgBoomTemp(int cgBoomTemp) {
		this.cgBoomTemp = cgBoomTemp;
	}

	public int getEdacAdr7() {
		return edacAdr7;
	}

	public void setEdacAdr7(int edacAdr7) {
		this.edacAdr7 = edacAdr7;
	}

	public int getEdacAdr15() {
		return edacAdr15;
	}

	public void setEdacAdr15(int edacAdr15) {
		this.edacAdr15 = edacAdr15;
	}

	public int getEdacAdr23() {
		return edacAdr23;
	}

	public void setEdacAdr23(int edacAdr23) {
		this.edacAdr23 = edacAdr23;
	}

	public int getEdacBank0() {
		return edacBank0;
	}

	public void setEdacBank0(int edacBank0) {
		this.edacBank0 = edacBank0;
	}

	public int getEdacBank1() {
		return edacBank1;
	}

	public void setEdacBank1(int edacBank1) {
		this.edacBank1 = edacBank1;
	}

	public int getEdacBank2() {
		return edacBank2;
	}

	public void setEdacBank2(int edacBank2) {
		this.edacBank2 = edacBank2;
	}

}
