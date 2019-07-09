package ru.r2cloud.jradio.eseo;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class ObdTempError {

	private boolean temp1PDU1;
	private boolean temp2BAT1;
	private boolean temp3PMB;
	private boolean temp4HPA2;
	private boolean temp8HPA1;
	private boolean temp10TNK;
	private boolean temp11BAT2;
	private boolean temp12MWM;
	private boolean temp13MWR;
	private boolean temp14MMM;
	private boolean temp15MMR;

	public ObdTempError(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		temp1PDU1 = ((raw >> 7) & 0x1) > 0;
		temp2BAT1 = ((raw >> 6) & 0x1) > 0;
		temp3PMB = ((raw >> 5) & 0x1) > 0;
		temp4HPA2 = ((raw >> 4) & 0x1) > 0;
		temp8HPA1 = ((raw >> 3) & 0x1) > 0;
		temp10TNK = ((raw >> 2) & 0x1) > 0;
		temp11BAT2 = ((raw >> 1) & 0x1) > 0;
		temp12MWM = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		temp13MWR = ((raw >> 7) & 0x1) > 0;
		temp14MMM = ((raw >> 6) & 0x1) > 0;
		temp15MMR = ((raw >> 5) & 0x1) > 0;
	}

	public boolean isTemp1PDU1() {
		return temp1PDU1;
	}

	public void setTemp1PDU1(boolean temp1pdu1) {
		temp1PDU1 = temp1pdu1;
	}

	public boolean isTemp2BAT1() {
		return temp2BAT1;
	}

	public void setTemp2BAT1(boolean temp2bat1) {
		temp2BAT1 = temp2bat1;
	}

	public boolean isTemp3PMB() {
		return temp3PMB;
	}

	public void setTemp3PMB(boolean temp3pmb) {
		temp3PMB = temp3pmb;
	}

	public boolean isTemp4HPA2() {
		return temp4HPA2;
	}

	public void setTemp4HPA2(boolean temp4hpa2) {
		temp4HPA2 = temp4hpa2;
	}

	public boolean isTemp8HPA1() {
		return temp8HPA1;
	}

	public void setTemp8HPA1(boolean temp8hpa1) {
		temp8HPA1 = temp8hpa1;
	}

	public boolean isTemp10TNK() {
		return temp10TNK;
	}

	public void setTemp10TNK(boolean temp10tnk) {
		temp10TNK = temp10tnk;
	}

	public boolean isTemp11BAT2() {
		return temp11BAT2;
	}

	public void setTemp11BAT2(boolean temp11bat2) {
		temp11BAT2 = temp11bat2;
	}

	public boolean isTemp12MWM() {
		return temp12MWM;
	}

	public void setTemp12MWM(boolean temp12mwm) {
		temp12MWM = temp12mwm;
	}

	public boolean isTemp13MWR() {
		return temp13MWR;
	}

	public void setTemp13MWR(boolean temp13mwr) {
		temp13MWR = temp13mwr;
	}

	public boolean isTemp14MMM() {
		return temp14MMM;
	}

	public void setTemp14MMM(boolean temp14mmm) {
		temp14MMM = temp14mmm;
	}

	public boolean isTemp15MMR() {
		return temp15MMR;
	}

	public void setTemp15MMR(boolean temp15mmr) {
		temp15MMR = temp15mmr;
	}

}
