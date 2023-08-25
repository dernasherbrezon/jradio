package ru.r2cloud.jradio.sstk1;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class StratosatTk1PicoBeacon extends Beacon {

	private String callsign;
	private float timestamp;
	private float mainVoltage;
	private float bcvmTemp;
	private float imuX;
	private float imuY;
	private float imuZ;
	private float magX;
	private float magY;
	private float magZ;
	private float solarCellA;
	private float solarCellB;
	private float solarCellC;
	private float solarCellD;
	private float solarCellE;
	private float preassure;
	private float iDischarge;
	private float tempSolarCellA;
	private float tempSolarCellB;
	private float tempSolarCellC;
	private float tempSolarCellD;
	private float tempSolarCellE;
	private float vCharge;
	private float reset;
	private float txIndex;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		byte[] callsignBytes = new byte[6];
		dis.readFully(callsignBytes);
		callsign = new String(callsignBytes, StandardCharsets.US_ASCII);
		timestamp = dis.readFloat();
		mainVoltage = dis.readFloat();
		bcvmTemp = dis.readFloat();
		imuX = dis.readFloat();
		imuY = dis.readFloat();
		imuZ = dis.readFloat();
		magX = dis.readFloat();
		magY = dis.readFloat();
		magZ = dis.readFloat();
		solarCellA = dis.readFloat();
		solarCellB = dis.readFloat();
		solarCellC = dis.readFloat();
		solarCellD = dis.readFloat();
		solarCellE = dis.readFloat();
		preassure = dis.readFloat();
		iDischarge = dis.readFloat();
		tempSolarCellA = dis.readFloat();
		tempSolarCellB = dis.readFloat();
		tempSolarCellC = dis.readFloat();
		tempSolarCellD = dis.readFloat();
		tempSolarCellE = dis.readFloat();
		vCharge = dis.readFloat();
		reset = dis.readFloat();
		txIndex = dis.readFloat();
	}

	public String getCallsign() {
		return callsign;
	}

	public void setCallsign(String callsign) {
		this.callsign = callsign;
	}

	public float getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(float timestamp) {
		this.timestamp = timestamp;
	}

	public float getMainVoltage() {
		return mainVoltage;
	}

	public void setMainVoltage(float mainVoltage) {
		this.mainVoltage = mainVoltage;
	}

	public float getBcvmTemp() {
		return bcvmTemp;
	}

	public void setBcvmTemp(float bcvmTemp) {
		this.bcvmTemp = bcvmTemp;
	}

	public float getImuX() {
		return imuX;
	}

	public void setImuX(float imuX) {
		this.imuX = imuX;
	}

	public float getImuY() {
		return imuY;
	}

	public void setImuY(float imuY) {
		this.imuY = imuY;
	}

	public float getImuZ() {
		return imuZ;
	}

	public void setImuZ(float imuZ) {
		this.imuZ = imuZ;
	}

	public float getMagX() {
		return magX;
	}

	public void setMagX(float magX) {
		this.magX = magX;
	}

	public float getMagY() {
		return magY;
	}

	public void setMagY(float magY) {
		this.magY = magY;
	}

	public float getMagZ() {
		return magZ;
	}

	public void setMagZ(float magZ) {
		this.magZ = magZ;
	}

	public float getSolarCellA() {
		return solarCellA;
	}

	public void setSolarCellA(float solarCellA) {
		this.solarCellA = solarCellA;
	}

	public float getSolarCellB() {
		return solarCellB;
	}

	public void setSolarCellB(float solarCellB) {
		this.solarCellB = solarCellB;
	}

	public float getSolarCellC() {
		return solarCellC;
	}

	public void setSolarCellC(float solarCellC) {
		this.solarCellC = solarCellC;
	}

	public float getSolarCellD() {
		return solarCellD;
	}

	public void setSolarCellD(float solarCellD) {
		this.solarCellD = solarCellD;
	}

	public float getSolarCellE() {
		return solarCellE;
	}

	public void setSolarCellE(float solarCellE) {
		this.solarCellE = solarCellE;
	}

	public float getPreassure() {
		return preassure;
	}

	public void setPreassure(float preassure) {
		this.preassure = preassure;
	}

	public float getiDischarge() {
		return iDischarge;
	}

	public void setiDischarge(float iDischarge) {
		this.iDischarge = iDischarge;
	}

	public float getTempSolarCellA() {
		return tempSolarCellA;
	}

	public void setTempSolarCellA(float tempSolarCellA) {
		this.tempSolarCellA = tempSolarCellA;
	}

	public float getTempSolarCellB() {
		return tempSolarCellB;
	}

	public void setTempSolarCellB(float tempSolarCellB) {
		this.tempSolarCellB = tempSolarCellB;
	}

	public float getTempSolarCellC() {
		return tempSolarCellC;
	}

	public void setTempSolarCellC(float tempSolarCellC) {
		this.tempSolarCellC = tempSolarCellC;
	}

	public float getTempSolarCellD() {
		return tempSolarCellD;
	}

	public void setTempSolarCellD(float tempSolarCellD) {
		this.tempSolarCellD = tempSolarCellD;
	}

	public float getTempSolarCellE() {
		return tempSolarCellE;
	}

	public void setTempSolarCellE(float tempSolarCellE) {
		this.tempSolarCellE = tempSolarCellE;
	}

	public float getvCharge() {
		return vCharge;
	}

	public void setvCharge(float vCharge) {
		this.vCharge = vCharge;
	}

	public float getReset() {
		return reset;
	}

	public void setReset(float reset) {
		this.reset = reset;
	}

	public float getTxIndex() {
		return txIndex;
	}

	public void setTxIndex(float txIndex) {
		this.txIndex = txIndex;
	}

}
