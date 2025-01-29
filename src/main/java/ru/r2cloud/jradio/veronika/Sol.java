package ru.r2cloud.jradio.veronika;

public class Sol {

	private float tempZp;
	private float tempXp;
	private float tempYp;
	private float tempZn;
	private float tempXn;
	private float tempYn;
	private int diodeZp;
	private int diodeXp;
	private int diodeYp;
	private int diodeZn;
	private int diodeXn;
	private int diodeYn;

	public Sol() {
		// do nothing
	}

	public Sol(String[] parts) {
		int i = 1;
		tempZp = Integer.parseInt(parts[i++]) * 0.01f;
		tempXp = Integer.parseInt(parts[i++]) * 0.01f;
		tempYp = Integer.parseInt(parts[i++]) * 0.01f;
		tempZn = Integer.parseInt(parts[i++]) * 0.01f;
		tempXn = Integer.parseInt(parts[i++]) * 0.01f;
		tempYn = Integer.parseInt(parts[i++]) * 0.01f;
		diodeZp = Integer.parseInt(parts[i++]);
		diodeXp = Integer.parseInt(parts[i++]);
		diodeYp = Integer.parseInt(parts[i++]);
		diodeZn = Integer.parseInt(parts[i++]);
		diodeXn = Integer.parseInt(parts[i++]);
		diodeYn = Integer.parseInt(parts[i++]);
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

	public void setTempYn(int tempYn) {
		this.tempYn = tempYn;
	}

	public int getDiodeZp() {
		return diodeZp;
	}

	public void setDiodeZp(int diodeZp) {
		this.diodeZp = diodeZp;
	}

	public int getDiodeXp() {
		return diodeXp;
	}

	public void setDiodeXp(int diodeXp) {
		this.diodeXp = diodeXp;
	}

	public int getDiodeYp() {
		return diodeYp;
	}

	public void setDiodeYp(int diodeYp) {
		this.diodeYp = diodeYp;
	}

	public int getDiodeZn() {
		return diodeZn;
	}

	public void setDiodeZn(int diodeZn) {
		this.diodeZn = diodeZn;
	}

	public int getDiodeXn() {
		return diodeXn;
	}

	public void setDiodeXn(int diodeXn) {
		this.diodeXn = diodeXn;
	}

	public int getDiodeYn() {
		return diodeYn;
	}

	public void setDiodeYn(int diodeYn) {
		this.diodeYn = diodeYn;
	}

}
