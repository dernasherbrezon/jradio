package ru.r2cloud.jradio.uvsqsat;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class MainboardAllScience {

	private long mainboardScienceTime;
	private int teachWearOn;
	private int frequenceOfAcquisitions;
	private long gain;
	private int numberOfAcquisitionCommanded;
	private int numberOfRealAcquisition;
	private long hkPlus5v;
	private long hkMinus5v;
	private long hkMinus5vPolar;
	private long hkTempAdc;
	private long hkFeePlusXVref;
	private long hkFeeMinusXVref;
	private long hkFeePlusYVref;
	private long hkFeeMinusYVref;
	private long feePlusXErs1Signal;
	private long feePlusXErs1Temperature;
	private long feePlusXErs2Signal;
	private long feePlusXErs2Temperature;
	private long feePlusXErs3Signal;
	private long feePlusXErs3Temperature;
	private long feePlusXUvsSignal;
	private long feeMinusXErs1Signal;
	private long feeMinusXErs1Temperature;
	private long feeMinusXErs2Signal;
	private long feeMinusXErs2Temperature;
	private long feeMinusXErs3Signal;
	private long feeMinusXErs3Temperature;
	private long feeMinusXUvsSignal;
	private long feePlusYErs1Signal;
	private long feePlusYErs1Temperature;
	private long feePlusYErs2Signal;
	private long feePlusYErs2Temperature;
	private long feePlusYErs3Signal;
	private long feePlusYErs3Temperature;
	private long feePlusYUvsSignal;
	private long feeMinusYErs1Signal;
	private long feeMinusYErs1Temperature;
	private long feeMinusYErs2Signal;
	private long feeMinusYErs2Temperature;
	private long feeMinusYErs3Signal;
	private long feeMinusYErs3Temperature;
	private long feeMinusYUvsSignal;
	private int teachwearAccX;
	private int teachwearAccY;
	private int teachwearAccZ;
	private int teachwearDegC;
	private int teachwearGyroX;
	private int teachwearGyroY;
	private int teachwearGyroZ;
	private int teachwearMagnX;
	private int teachwearMagnY;
	private int teachwearMagnZ;
	private int teachwearState;
	private int teachwearResetReason;
	private int teachwearCrc;
	private long nbTmSinceFirstStart;

	public MainboardAllScience() {
		// do nothing
	}

	public MainboardAllScience(DataInputStream dis) throws IOException {
		mainboardScienceTime = StreamUtils.readUnsignedInt(dis);
		teachWearOn = dis.readUnsignedByte();
		frequenceOfAcquisitions = dis.readUnsignedShort();
		gain = StreamUtils.readUnsignedInt(dis);
		numberOfAcquisitionCommanded = dis.readUnsignedShort();
		numberOfRealAcquisition = dis.readUnsignedShort();
		hkPlus5v = StreamUtils.readUnsignedInt(dis);
		hkMinus5v = StreamUtils.readUnsignedInt(dis);
		hkMinus5vPolar = StreamUtils.readUnsignedInt(dis);
		hkTempAdc = StreamUtils.readUnsignedInt(dis);
		hkFeePlusXVref = StreamUtils.readUnsignedInt(dis);
		hkFeeMinusXVref = StreamUtils.readUnsignedInt(dis);
		hkFeePlusYVref = StreamUtils.readUnsignedInt(dis);
		hkFeeMinusYVref = StreamUtils.readUnsignedInt(dis);
		feePlusXErs1Signal = StreamUtils.readUnsignedInt(dis);
		feePlusXErs1Temperature = StreamUtils.readUnsignedInt(dis);
		feePlusXErs2Signal = StreamUtils.readUnsignedInt(dis);
		feePlusXErs2Temperature = StreamUtils.readUnsignedInt(dis);
		feePlusXErs3Signal = StreamUtils.readUnsignedInt(dis);
		feePlusXErs3Temperature = StreamUtils.readUnsignedInt(dis);
		feePlusXUvsSignal = StreamUtils.readUnsignedInt(dis);
		feeMinusXErs1Signal = StreamUtils.readUnsignedInt(dis);
		feeMinusXErs1Temperature = StreamUtils.readUnsignedInt(dis);
		feeMinusXErs2Signal = StreamUtils.readUnsignedInt(dis);
		feeMinusXErs2Temperature = StreamUtils.readUnsignedInt(dis);
		feeMinusXErs3Signal = StreamUtils.readUnsignedInt(dis);
		feeMinusXErs3Temperature = StreamUtils.readUnsignedInt(dis);
		feeMinusXUvsSignal = StreamUtils.readUnsignedInt(dis);
		feePlusYErs1Signal = StreamUtils.readUnsignedInt(dis);
		feePlusYErs1Temperature = StreamUtils.readUnsignedInt(dis);
		feePlusYErs2Signal = StreamUtils.readUnsignedInt(dis);
		feePlusYErs2Temperature = StreamUtils.readUnsignedInt(dis);
		feePlusYErs3Signal = StreamUtils.readUnsignedInt(dis);
		feePlusYErs3Temperature = StreamUtils.readUnsignedInt(dis);
		feePlusYUvsSignal = StreamUtils.readUnsignedInt(dis);
		feeMinusYErs1Signal = StreamUtils.readUnsignedInt(dis);
		feeMinusYErs1Temperature = StreamUtils.readUnsignedInt(dis);
		feeMinusYErs2Signal = StreamUtils.readUnsignedInt(dis);
		feeMinusYErs2Temperature = StreamUtils.readUnsignedInt(dis);
		feeMinusYErs3Signal = StreamUtils.readUnsignedInt(dis);
		feeMinusYErs3Temperature = StreamUtils.readUnsignedInt(dis);
		feeMinusYUvsSignal = StreamUtils.readUnsignedInt(dis);
		teachwearAccX = dis.readUnsignedShort();
		teachwearAccY = dis.readUnsignedShort();
		teachwearAccZ = dis.readUnsignedShort();
		teachwearDegC = dis.readUnsignedShort();
		teachwearGyroX = dis.readUnsignedShort();
		teachwearGyroY = dis.readUnsignedShort();
		teachwearGyroZ = dis.readUnsignedShort();
		teachwearMagnX = dis.readUnsignedShort();
		teachwearMagnY = dis.readUnsignedShort();
		teachwearMagnZ = dis.readUnsignedShort();
		teachwearState = dis.readUnsignedByte();
		teachwearResetReason = dis.readUnsignedByte();
		teachwearCrc = dis.readUnsignedShort();
		nbTmSinceFirstStart = StreamUtils.readUnsignedInt(dis);
	}

	public long getMainboardScienceTime() {
		return mainboardScienceTime;
	}

	public void setMainboardScienceTime(long mainboardScienceTime) {
		this.mainboardScienceTime = mainboardScienceTime;
	}

	public int getTeachWearOn() {
		return teachWearOn;
	}

	public void setTeachWearOn(int teachWearOn) {
		this.teachWearOn = teachWearOn;
	}

	public int getFrequenceOfAcquisitions() {
		return frequenceOfAcquisitions;
	}

	public void setFrequenceOfAcquisitions(int frequenceOfAcquisitions) {
		this.frequenceOfAcquisitions = frequenceOfAcquisitions;
	}

	public long getGain() {
		return gain;
	}

	public void setGain(long gain) {
		this.gain = gain;
	}

	public int getNumberOfAcquisitionCommanded() {
		return numberOfAcquisitionCommanded;
	}

	public void setNumberOfAcquisitionCommanded(int numberOfAcquisitionCommanded) {
		this.numberOfAcquisitionCommanded = numberOfAcquisitionCommanded;
	}

	public int getNumberOfRealAcquisition() {
		return numberOfRealAcquisition;
	}

	public void setNumberOfRealAcquisition(int numberOfRealAcquisition) {
		this.numberOfRealAcquisition = numberOfRealAcquisition;
	}

	public long getHkPlus5v() {
		return hkPlus5v;
	}

	public void setHkPlus5v(long hkPlus5v) {
		this.hkPlus5v = hkPlus5v;
	}

	public long getHkMinus5v() {
		return hkMinus5v;
	}

	public void setHkMinus5v(long hkMinus5v) {
		this.hkMinus5v = hkMinus5v;
	}

	public long getHkMinus5vPolar() {
		return hkMinus5vPolar;
	}

	public void setHkMinus5vPolar(long hkMinus5vPolar) {
		this.hkMinus5vPolar = hkMinus5vPolar;
	}

	public long getHkTempAdc() {
		return hkTempAdc;
	}

	public void setHkTempAdc(long hkTempAdc) {
		this.hkTempAdc = hkTempAdc;
	}

	public long getHkFeePlusXVref() {
		return hkFeePlusXVref;
	}

	public void setHkFeePlusXVref(long hkFeePlusXVref) {
		this.hkFeePlusXVref = hkFeePlusXVref;
	}

	public long getHkFeeMinusXVref() {
		return hkFeeMinusXVref;
	}

	public void setHkFeeMinusXVref(long hkFeeMinusXVref) {
		this.hkFeeMinusXVref = hkFeeMinusXVref;
	}

	public long getHkFeePlusYVref() {
		return hkFeePlusYVref;
	}

	public void setHkFeePlusYVref(long hkFeePlusYVref) {
		this.hkFeePlusYVref = hkFeePlusYVref;
	}

	public long getHkFeeMinusYVref() {
		return hkFeeMinusYVref;
	}

	public void setHkFeeMinusYVref(long hkFeeMinusYVref) {
		this.hkFeeMinusYVref = hkFeeMinusYVref;
	}

	public long getFeePlusXErs1Signal() {
		return feePlusXErs1Signal;
	}

	public void setFeePlusXErs1Signal(long feePlusXErs1Signal) {
		this.feePlusXErs1Signal = feePlusXErs1Signal;
	}

	public long getFeePlusXErs1Temperature() {
		return feePlusXErs1Temperature;
	}

	public void setFeePlusXErs1Temperature(long feePlusXErs1Temperature) {
		this.feePlusXErs1Temperature = feePlusXErs1Temperature;
	}

	public long getFeePlusXErs2Signal() {
		return feePlusXErs2Signal;
	}

	public void setFeePlusXErs2Signal(long feePlusXErs2Signal) {
		this.feePlusXErs2Signal = feePlusXErs2Signal;
	}

	public long getFeePlusXErs2Temperature() {
		return feePlusXErs2Temperature;
	}

	public void setFeePlusXErs2Temperature(long feePlusXErs2Temperature) {
		this.feePlusXErs2Temperature = feePlusXErs2Temperature;
	}

	public long getFeePlusXErs3Signal() {
		return feePlusXErs3Signal;
	}

	public void setFeePlusXErs3Signal(long feePlusXErs3Signal) {
		this.feePlusXErs3Signal = feePlusXErs3Signal;
	}

	public long getFeePlusXErs3Temperature() {
		return feePlusXErs3Temperature;
	}

	public void setFeePlusXErs3Temperature(long feePlusXErs3Temperature) {
		this.feePlusXErs3Temperature = feePlusXErs3Temperature;
	}

	public long getFeePlusXUvsSignal() {
		return feePlusXUvsSignal;
	}

	public void setFeePlusXUvsSignal(long feePlusXUvsSignal) {
		this.feePlusXUvsSignal = feePlusXUvsSignal;
	}

	public long getFeeMinusXErs1Signal() {
		return feeMinusXErs1Signal;
	}

	public void setFeeMinusXErs1Signal(long feeMinusXErs1Signal) {
		this.feeMinusXErs1Signal = feeMinusXErs1Signal;
	}

	public long getFeeMinusXErs1Temperature() {
		return feeMinusXErs1Temperature;
	}

	public void setFeeMinusXErs1Temperature(long feeMinusXErs1Temperature) {
		this.feeMinusXErs1Temperature = feeMinusXErs1Temperature;
	}

	public long getFeeMinusXErs2Signal() {
		return feeMinusXErs2Signal;
	}

	public void setFeeMinusXErs2Signal(long feeMinusXErs2Signal) {
		this.feeMinusXErs2Signal = feeMinusXErs2Signal;
	}

	public long getFeeMinusXErs2Temperature() {
		return feeMinusXErs2Temperature;
	}

	public void setFeeMinusXErs2Temperature(long feeMinusXErs2Temperature) {
		this.feeMinusXErs2Temperature = feeMinusXErs2Temperature;
	}

	public long getFeeMinusXErs3Signal() {
		return feeMinusXErs3Signal;
	}

	public void setFeeMinusXErs3Signal(long feeMinusXErs3Signal) {
		this.feeMinusXErs3Signal = feeMinusXErs3Signal;
	}

	public long getFeeMinusXErs3Temperature() {
		return feeMinusXErs3Temperature;
	}

	public void setFeeMinusXErs3Temperature(long feeMinusXErs3Temperature) {
		this.feeMinusXErs3Temperature = feeMinusXErs3Temperature;
	}

	public long getFeeMinusXUvsSignal() {
		return feeMinusXUvsSignal;
	}

	public void setFeeMinusXUvsSignal(long feeMinusXUvsSignal) {
		this.feeMinusXUvsSignal = feeMinusXUvsSignal;
	}

	public long getFeePlusYErs1Signal() {
		return feePlusYErs1Signal;
	}

	public void setFeePlusYErs1Signal(long feePlusYErs1Signal) {
		this.feePlusYErs1Signal = feePlusYErs1Signal;
	}

	public long getFeePlusYErs1Temperature() {
		return feePlusYErs1Temperature;
	}

	public void setFeePlusYErs1Temperature(long feePlusYErs1Temperature) {
		this.feePlusYErs1Temperature = feePlusYErs1Temperature;
	}

	public long getFeePlusYErs2Signal() {
		return feePlusYErs2Signal;
	}

	public void setFeePlusYErs2Signal(long feePlusYErs2Signal) {
		this.feePlusYErs2Signal = feePlusYErs2Signal;
	}

	public long getFeePlusYErs2Temperature() {
		return feePlusYErs2Temperature;
	}

	public void setFeePlusYErs2Temperature(long feePlusYErs2Temperature) {
		this.feePlusYErs2Temperature = feePlusYErs2Temperature;
	}

	public long getFeePlusYErs3Signal() {
		return feePlusYErs3Signal;
	}

	public void setFeePlusYErs3Signal(long feePlusYErs3Signal) {
		this.feePlusYErs3Signal = feePlusYErs3Signal;
	}

	public long getFeePlusYErs3Temperature() {
		return feePlusYErs3Temperature;
	}

	public void setFeePlusYErs3Temperature(long feePlusYErs3Temperature) {
		this.feePlusYErs3Temperature = feePlusYErs3Temperature;
	}

	public long getFeePlusYUvsSignal() {
		return feePlusYUvsSignal;
	}

	public void setFeePlusYUvsSignal(long feePlusYUvsSignal) {
		this.feePlusYUvsSignal = feePlusYUvsSignal;
	}

	public long getFeeMinusYErs1Signal() {
		return feeMinusYErs1Signal;
	}

	public void setFeeMinusYErs1Signal(long feeMinusYErs1Signal) {
		this.feeMinusYErs1Signal = feeMinusYErs1Signal;
	}

	public long getFeeMinusYErs1Temperature() {
		return feeMinusYErs1Temperature;
	}

	public void setFeeMinusYErs1Temperature(long feeMinusYErs1Temperature) {
		this.feeMinusYErs1Temperature = feeMinusYErs1Temperature;
	}

	public long getFeeMinusYErs2Signal() {
		return feeMinusYErs2Signal;
	}

	public void setFeeMinusYErs2Signal(long feeMinusYErs2Signal) {
		this.feeMinusYErs2Signal = feeMinusYErs2Signal;
	}

	public long getFeeMinusYErs2Temperature() {
		return feeMinusYErs2Temperature;
	}

	public void setFeeMinusYErs2Temperature(long feeMinusYErs2Temperature) {
		this.feeMinusYErs2Temperature = feeMinusYErs2Temperature;
	}

	public long getFeeMinusYErs3Signal() {
		return feeMinusYErs3Signal;
	}

	public void setFeeMinusYErs3Signal(long feeMinusYErs3Signal) {
		this.feeMinusYErs3Signal = feeMinusYErs3Signal;
	}

	public long getFeeMinusYErs3Temperature() {
		return feeMinusYErs3Temperature;
	}

	public void setFeeMinusYErs3Temperature(long feeMinusYErs3Temperature) {
		this.feeMinusYErs3Temperature = feeMinusYErs3Temperature;
	}

	public long getFeeMinusYUvsSignal() {
		return feeMinusYUvsSignal;
	}

	public void setFeeMinusYUvsSignal(long feeMinusYUvsSignal) {
		this.feeMinusYUvsSignal = feeMinusYUvsSignal;
	}

	public int getTeachwearAccX() {
		return teachwearAccX;
	}

	public void setTeachwearAccX(int teachwearAccX) {
		this.teachwearAccX = teachwearAccX;
	}

	public int getTeachwearAccY() {
		return teachwearAccY;
	}

	public void setTeachwearAccY(int teachwearAccY) {
		this.teachwearAccY = teachwearAccY;
	}

	public int getTeachwearAccZ() {
		return teachwearAccZ;
	}

	public void setTeachwearAccZ(int teachwearAccZ) {
		this.teachwearAccZ = teachwearAccZ;
	}

	public int getTeachwearDegC() {
		return teachwearDegC;
	}

	public void setTeachwearDegC(int teachwearDegC) {
		this.teachwearDegC = teachwearDegC;
	}

	public int getTeachwearGyroX() {
		return teachwearGyroX;
	}

	public void setTeachwearGyroX(int teachwearGyroX) {
		this.teachwearGyroX = teachwearGyroX;
	}

	public int getTeachwearGyroY() {
		return teachwearGyroY;
	}

	public void setTeachwearGyroY(int teachwearGyroY) {
		this.teachwearGyroY = teachwearGyroY;
	}

	public int getTeachwearGyroZ() {
		return teachwearGyroZ;
	}

	public void setTeachwearGyroZ(int teachwearGyroZ) {
		this.teachwearGyroZ = teachwearGyroZ;
	}

	public int getTeachwearMagnX() {
		return teachwearMagnX;
	}

	public void setTeachwearMagnX(int teachwearMagnX) {
		this.teachwearMagnX = teachwearMagnX;
	}

	public int getTeachwearMagnY() {
		return teachwearMagnY;
	}

	public void setTeachwearMagnY(int teachwearMagnY) {
		this.teachwearMagnY = teachwearMagnY;
	}

	public int getTeachwearMagnZ() {
		return teachwearMagnZ;
	}

	public void setTeachwearMagnZ(int teachwearMagnZ) {
		this.teachwearMagnZ = teachwearMagnZ;
	}

	public int getTeachwearState() {
		return teachwearState;
	}

	public void setTeachwearState(int teachwearState) {
		this.teachwearState = teachwearState;
	}

	public int getTeachwearResetReason() {
		return teachwearResetReason;
	}

	public void setTeachwearResetReason(int teachwearResetReason) {
		this.teachwearResetReason = teachwearResetReason;
	}

	public int getTeachwearCrc() {
		return teachwearCrc;
	}

	public void setTeachwearCrc(int teachwearCrc) {
		this.teachwearCrc = teachwearCrc;
	}

	public long getNbTmSinceFirstStart() {
		return nbTmSinceFirstStart;
	}

	public void setNbTmSinceFirstStart(long nbTmSinceFirstStart) {
		this.nbTmSinceFirstStart = nbTmSinceFirstStart;
	}

}
