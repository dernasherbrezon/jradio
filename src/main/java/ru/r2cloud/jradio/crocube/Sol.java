package ru.r2cloud.jradio.crocube;

public class Sol {

	private float tempZp;
	private float tempXp;
	private float tempYp;
	private float tempZn;
	private float tempXn;
	private float tempYn;
	private long diodeZp;
	private long diodeXp;
	private long diodeYp;
	private long diodeZn;
	private long diodeXn;
	private long diodeYn;

	public Sol() {
		// do nothing
	}

	public Sol(String[] parts) {
		tempZp = Long.valueOf(parts[1]) * 0.01f;
		tempXp = Long.valueOf(parts[2]) * 0.01f;
		tempYp = Long.valueOf(parts[3]) * 0.01f;
		tempZn = Long.valueOf(parts[4]) * 0.01f;
		tempXn = Long.valueOf(parts[5]) * 0.01f;
		tempYn = Long.valueOf(parts[6]) * 0.01f;
		diodeZp = Long.valueOf(parts[7]);
		diodeXp = Long.valueOf(parts[8]);
		diodeYp = Long.valueOf(parts[9]);
		diodeZn = Long.valueOf(parts[11]);
		diodeXn = Long.valueOf(parts[11]);
		diodeYn = Long.valueOf(parts[12]);
	}

	public float getTempZp() {
		return tempZp;
	}

	public void setTempZp(float tempZp) {
		this.tempZp = tempZp;
	}

	public float getTempXp() {
		return tempXp;
	}

	public void setTempXp(float tempXp) {
		this.tempXp = tempXp;
	}

	public float getTempYp() {
		return tempYp;
	}

	public void setTempYp(float tempYp) {
		this.tempYp = tempYp;
	}

	public float getTempZn() {
		return tempZn;
	}

	public void setTempZn(float tempZn) {
		this.tempZn = tempZn;
	}

	public float getTempXn() {
		return tempXn;
	}

	public void setTempXn(float tempXn) {
		this.tempXn = tempXn;
	}

	public float getTempYn() {
		return tempYn;
	}

	public void setTempYn(float tempYn) {
		this.tempYn = tempYn;
	}

	public long getDiodeZp() {
		return diodeZp;
	}

	public void setDiodeZp(long diodeZp) {
		this.diodeZp = diodeZp;
	}

	public long getDiodeXp() {
		return diodeXp;
	}

	public void setDiodeXp(long diodeXp) {
		this.diodeXp = diodeXp;
	}

	public long getDiodeYp() {
		return diodeYp;
	}

	public void setDiodeYp(long diodeYp) {
		this.diodeYp = diodeYp;
	}

	public long getDiodeZn() {
		return diodeZn;
	}

	public void setDiodeZn(long diodeZn) {
		this.diodeZn = diodeZn;
	}

	public long getDiodeXn() {
		return diodeXn;
	}

	public void setDiodeXn(long diodeXn) {
		this.diodeXn = diodeXn;
	}

	public long getDiodeYn() {
		return diodeYn;
	}

	public void setDiodeYn(long diodeYn) {
		this.diodeYn = diodeYn;
	}

}
