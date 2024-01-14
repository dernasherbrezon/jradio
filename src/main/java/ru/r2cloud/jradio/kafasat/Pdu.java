package ru.r2cloud.jradio.kafasat;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Pdu {

	private int camVout;
	private int hrm1Vout;
	private int antVout;
	private int wheelVout;
	private int trxvuVout;
	private int sbandVout;
	private int hrm2Vout;
	private int mtqrVout;
	private int adcsVout;
	private short camCout;
	private short hrm1Cout;
	private short antCout;
	private short wheelCout;
	private short trxvuCout;
	private short sbandCout;
	private short hrm2Cout;
	private short mtqrCout;
	private short adcsCout;

	public Pdu() {
		// do nothing
	}

	public Pdu(LittleEndianDataInputStream dis) throws IOException {
		camVout = dis.readUnsignedShort();
		hrm1Vout = dis.readUnsignedShort();
		antVout = dis.readUnsignedShort();
		wheelVout = dis.readUnsignedShort();
		trxvuVout = dis.readUnsignedShort();
		sbandVout = dis.readUnsignedShort();
		hrm2Vout = dis.readUnsignedShort();
		mtqrVout = dis.readUnsignedShort();
		adcsVout = dis.readUnsignedShort();
		camCout = dis.readShort();
		hrm1Cout = dis.readShort();
		antCout = dis.readShort();
		wheelCout = dis.readShort();
		trxvuCout = dis.readShort();
		sbandCout = dis.readShort();
		hrm2Cout = dis.readShort();
		mtqrCout = dis.readShort();
		adcsCout = dis.readShort();
	}

	public int getCamVout() {
		return camVout;
	}

	public void setCamVout(int camVout) {
		this.camVout = camVout;
	}

	public int getHrm1Vout() {
		return hrm1Vout;
	}

	public void setHrm1Vout(int hrm1Vout) {
		this.hrm1Vout = hrm1Vout;
	}

	public int getAntVout() {
		return antVout;
	}

	public void setAntVout(int antVout) {
		this.antVout = antVout;
	}

	public int getWheelVout() {
		return wheelVout;
	}

	public void setWheelVout(int wheelVout) {
		this.wheelVout = wheelVout;
	}

	public int getTrxvuVout() {
		return trxvuVout;
	}

	public void setTrxvuVout(int trxvuVout) {
		this.trxvuVout = trxvuVout;
	}

	public int getSbandVout() {
		return sbandVout;
	}

	public void setSbandVout(int sbandVout) {
		this.sbandVout = sbandVout;
	}

	public int getHrm2Vout() {
		return hrm2Vout;
	}

	public void setHrm2Vout(int hrm2Vout) {
		this.hrm2Vout = hrm2Vout;
	}

	public int getMtqrVout() {
		return mtqrVout;
	}

	public void setMtqrVout(int mtqrVout) {
		this.mtqrVout = mtqrVout;
	}

	public int getAdcsVout() {
		return adcsVout;
	}

	public void setAdcsVout(int adcsVout) {
		this.adcsVout = adcsVout;
	}

	public short getCamCout() {
		return camCout;
	}

	public void setCamCout(short camCout) {
		this.camCout = camCout;
	}

	public short getHrm1Cout() {
		return hrm1Cout;
	}

	public void setHrm1Cout(short hrm1Cout) {
		this.hrm1Cout = hrm1Cout;
	}

	public short getAntCout() {
		return antCout;
	}

	public void setAntCout(short antCout) {
		this.antCout = antCout;
	}

	public short getWheelCout() {
		return wheelCout;
	}

	public void setWheelCout(short wheelCout) {
		this.wheelCout = wheelCout;
	}

	public short getTrxvuCout() {
		return trxvuCout;
	}

	public void setTrxvuCout(short trxvuCout) {
		this.trxvuCout = trxvuCout;
	}

	public short getSbandCout() {
		return sbandCout;
	}

	public void setSbandCout(short sbandCout) {
		this.sbandCout = sbandCout;
	}

	public short getHrm2Cout() {
		return hrm2Cout;
	}

	public void setHrm2Cout(short hrm2Cout) {
		this.hrm2Cout = hrm2Cout;
	}

	public short getMtqrCout() {
		return mtqrCout;
	}

	public void setMtqrCout(short mtqrCout) {
		this.mtqrCout = mtqrCout;
	}

	public short getAdcsCout() {
		return adcsCout;
	}

	public void setAdcsCout(short adcsCout) {
		this.adcsCout = adcsCout;
	}

}
