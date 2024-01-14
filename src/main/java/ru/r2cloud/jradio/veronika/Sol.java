package ru.r2cloud.jradio.veronika;

public class Sol {

	private int tempZp;
	private int tempXp;
	private int tempYp;
	private int tempZn;
	private int tempXn;
	private int tempYn;
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
		tempZp = Integer.parseInt(parts[i++]);
		tempXp = Integer.parseInt(parts[i++]);
		tempYp = Integer.parseInt(parts[i++]);
		tempZn = Integer.parseInt(parts[i++]);
		tempXn = Integer.parseInt(parts[i++]);
		tempYn = Integer.parseInt(parts[i++]);
		diodeZp = Integer.parseInt(parts[i++]);
		diodeXp = Integer.parseInt(parts[i++]);
		diodeYp = Integer.parseInt(parts[i++]);
		diodeZn = Integer.parseInt(parts[i++]);
		diodeXn = Integer.parseInt(parts[i++]);
		diodeYn = Integer.parseInt(parts[i++]);
	}

	public int getTempZp() {
		return tempZp;
	}

	public void setTempZp(int tempZp) {
		this.tempZp = tempZp;
	}

	public int getTempXp() {
		return tempXp;
	}

	public void setTempXp(int tempXp) {
		this.tempXp = tempXp;
	}

	public int getTempYp() {
		return tempYp;
	}

	public void setTempYp(int tempYp) {
		this.tempYp = tempYp;
	}

	public int getTempZn() {
		return tempZn;
	}

	public void setTempZn(int tempZn) {
		this.tempZn = tempZn;
	}

	public int getTempXn() {
		return tempXn;
	}

	public void setTempXn(int tempXn) {
		this.tempXn = tempXn;
	}

	public int getTempYn() {
		return tempYn;
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
