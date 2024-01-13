package ru.r2cloud.jradio.bdsat;

public class Bds {

	private String state;
	private String progId;
	private int hwState;
	private String cron;
	private float tempC0;
	private float tempC1;
	private float tempE10;
	private float tempE11;
	private float tempE12;
	private float tempE13;
	private float tempE20;
	private float tempE21;
	private float tempE22;
	private float tempE23;
	private float tempEi0;
	private float tempEi1;
	private float pressureEi0;
	private float pressureEi1;

	public Bds() {
		// do nothing
	}

	public Bds(String[] parts) {
		int i = 1;
		state = parts[i++];
		progId = parts[i++];
		hwState = Integer.valueOf(parts[i++]);
		cron = parts[i++];
		tempC0 = Integer.valueOf(parts[i++]) * 0.01f;
		tempC1 = Integer.valueOf(parts[i++]) * 0.01f;
		tempE10 = Integer.valueOf(parts[i++]) * 0.01f;
		tempE11 = Integer.valueOf(parts[i++]) * 0.01f;
		tempE12 = Integer.valueOf(parts[i++]) * 0.01f;
		tempE13 = Integer.valueOf(parts[i++]) * 0.01f;
		tempE20 = Integer.valueOf(parts[i++]) * 0.01f;
		tempE21 = Integer.valueOf(parts[i++]) * 0.01f;
		tempE22 = Integer.valueOf(parts[i++]) * 0.01f;
		tempE23 = Integer.valueOf(parts[i++]) * 0.01f;
		tempEi0 = Float.parseFloat(parts[i++]);
		tempEi1 = Float.parseFloat(parts[i++]);
		pressureEi0 = Float.parseFloat(parts[i++]);
		pressureEi1 = Float.parseFloat(parts[i++]);
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getProgId() {
		return progId;
	}

	public void setProgId(String progId) {
		this.progId = progId;
	}

	public int getHwState() {
		return hwState;
	}

	public void setHwState(int hwState) {
		this.hwState = hwState;
	}

	public String getCron() {
		return cron;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}

	public float getTempC0() {
		return tempC0;
	}

	public void setTempC0(float tempC0) {
		this.tempC0 = tempC0;
	}

	public float getTempC1() {
		return tempC1;
	}

	public void setTempC1(float tempC1) {
		this.tempC1 = tempC1;
	}

	public float getTempE10() {
		return tempE10;
	}

	public void setTempE10(float tempE10) {
		this.tempE10 = tempE10;
	}

	public float getTempE11() {
		return tempE11;
	}

	public void setTempE11(float tempE11) {
		this.tempE11 = tempE11;
	}

	public float getTempE12() {
		return tempE12;
	}

	public void setTempE12(float tempE12) {
		this.tempE12 = tempE12;
	}

	public float getTempE13() {
		return tempE13;
	}

	public void setTempE13(float tempE13) {
		this.tempE13 = tempE13;
	}

	public float getTempE20() {
		return tempE20;
	}

	public void setTempE20(float tempE20) {
		this.tempE20 = tempE20;
	}

	public float getTempE21() {
		return tempE21;
	}

	public void setTempE21(float tempE21) {
		this.tempE21 = tempE21;
	}

	public float getTempE22() {
		return tempE22;
	}

	public void setTempE22(float tempE22) {
		this.tempE22 = tempE22;
	}

	public float getTempE23() {
		return tempE23;
	}

	public void setTempE23(float tempE23) {
		this.tempE23 = tempE23;
	}

	public float getTempEi0() {
		return tempEi0;
	}

	public void setTempEi0(float tempEi0) {
		this.tempEi0 = tempEi0;
	}

	public float getTempEi1() {
		return tempEi1;
	}

	public void setTempEi1(float tempEi1) {
		this.tempEi1 = tempEi1;
	}

	public float getPressureEi0() {
		return pressureEi0;
	}

	public void setPressureEi0(float pressureEi0) {
		this.pressureEi0 = pressureEi0;
	}

	public float getPressureEi1() {
		return pressureEi1;
	}

	public void setPressureEi1(float pressureEi1) {
		this.pressureEi1 = pressureEi1;
	}

}
