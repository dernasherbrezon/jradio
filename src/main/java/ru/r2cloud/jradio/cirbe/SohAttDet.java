package ru.r2cloud.jradio.cirbe;

import java.io.DataInputStream;
import java.io.IOException;

public class SohAttDet {

	private float qBodyWrtEci1;
	private float qBodyWrtEci2;
	private float qBodyWrtEci3;
	private float qBodyWrtEci4;
	private boolean trackerSolMixed;
	private boolean tracker2DataValid;
	private boolean tracker1DataValid;
	private boolean imuDataValid;
	private boolean measRateValid;
	private boolean measAttValid;
	private boolean attitudeValid;

	public SohAttDet() {
		// do nothing
	}

	public SohAttDet(DataInputStream dis) throws IOException {
		qBodyWrtEci1 = dis.readInt() * 5e-10f;
		qBodyWrtEci2 = dis.readInt() * 5e-10f;
		qBodyWrtEci3 = dis.readInt() * 5e-10f;
		qBodyWrtEci4 = dis.readInt() * 5e-10f;
		int raw = dis.readUnsignedByte();
		trackerSolMixed = ((raw >> 7) & 0x1) > 0;
		tracker2DataValid = ((raw >> 5) & 0x1) > 0;
		tracker1DataValid = ((raw >> 4) & 0x1) > 0;
		imuDataValid = ((raw >> 3) & 0x1) > 0;
		measRateValid = ((raw >> 2) & 0x1) > 0;
		measAttValid = ((raw >> 1) & 0x1) > 0;
		attitudeValid = (raw & 0x1) > 0;
	}

	public float getqBodyWrtEci1() {
		return qBodyWrtEci1;
	}

	public void setqBodyWrtEci1(float qBodyWrtEci1) {
		this.qBodyWrtEci1 = qBodyWrtEci1;
	}

	public float getqBodyWrtEci2() {
		return qBodyWrtEci2;
	}

	public void setqBodyWrtEci2(float qBodyWrtEci2) {
		this.qBodyWrtEci2 = qBodyWrtEci2;
	}

	public float getqBodyWrtEci3() {
		return qBodyWrtEci3;
	}

	public void setqBodyWrtEci3(float qBodyWrtEci3) {
		this.qBodyWrtEci3 = qBodyWrtEci3;
	}

	public float getqBodyWrtEci4() {
		return qBodyWrtEci4;
	}

	public void setqBodyWrtEci4(float qBodyWrtEci4) {
		this.qBodyWrtEci4 = qBodyWrtEci4;
	}

	public boolean isTrackerSolMixed() {
		return trackerSolMixed;
	}

	public void setTrackerSolMixed(boolean trackerSolMixed) {
		this.trackerSolMixed = trackerSolMixed;
	}

	public boolean isTracker2DataValid() {
		return tracker2DataValid;
	}

	public void setTracker2DataValid(boolean tracker2DataValid) {
		this.tracker2DataValid = tracker2DataValid;
	}

	public boolean isTracker1DataValid() {
		return tracker1DataValid;
	}

	public void setTracker1DataValid(boolean tracker1DataValid) {
		this.tracker1DataValid = tracker1DataValid;
	}

	public boolean isImuDataValid() {
		return imuDataValid;
	}

	public void setImuDataValid(boolean imuDataValid) {
		this.imuDataValid = imuDataValid;
	}

	public boolean isMeasRateValid() {
		return measRateValid;
	}

	public void setMeasRateValid(boolean measRateValid) {
		this.measRateValid = measRateValid;
	}

	public boolean isMeasAttValid() {
		return measAttValid;
	}

	public void setMeasAttValid(boolean measAttValid) {
		this.measAttValid = measAttValid;
	}

	public boolean isAttitudeValid() {
		return attitudeValid;
	}

	public void setAttitudeValid(boolean attitudeValid) {
		this.attitudeValid = attitudeValid;
	}

}
