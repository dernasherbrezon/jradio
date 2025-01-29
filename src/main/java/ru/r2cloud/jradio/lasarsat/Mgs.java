package ru.r2cloud.jradio.lasarsat;

public class Mgs {

	private float tempIntMag;
	private float tempIntGyr;
	private int intMagX;
	private int intMagY;
	private int intMagZ;
	private int intGyrX;
	private int intGyrY;
	private int intGyrZ;

	public Mgs() {
		// do nothing
	}

	public Mgs(String[] parts) {
		int i = 1;
		tempIntMag = Integer.parseInt(parts[i++]) * 0.01f;
		tempIntGyr = Integer.parseInt(parts[i++]) * 0.01f;
		intMagX = Integer.parseInt(parts[i++]);
		intMagY = Integer.parseInt(parts[i++]);
		intMagZ = Integer.parseInt(parts[i++]);
		intGyrX = Integer.parseInt(parts[i++]);
		intGyrY = Integer.parseInt(parts[i++]);
		intGyrZ = Integer.parseInt(parts[i++]);
	}

	public int getIntMagX() {
		return intMagX;
	}

	public void setIntMagX(int intMagX) {
		this.intMagX = intMagX;
	}

	public int getIntMagY() {
		return intMagY;
	}

	public void setIntMagY(int intMagY) {
		this.intMagY = intMagY;
	}

	public int getIntMagZ() {
		return intMagZ;
	}

	public void setIntMagZ(int intMagZ) {
		this.intMagZ = intMagZ;
	}

	public int getIntGyrX() {
		return intGyrX;
	}

	public void setIntGyrX(int intGyrX) {
		this.intGyrX = intGyrX;
	}

	public int getIntGyrY() {
		return intGyrY;
	}

	public void setIntGyrY(int intGyrY) {
		this.intGyrY = intGyrY;
	}

	public int getIntGyrZ() {
		return intGyrZ;
	}

	public void setIntGyrZ(int intGyrZ) {
		this.intGyrZ = intGyrZ;
	}

	public float getTempIntMag() {
		return tempIntMag;
	}

	public void setTempIntMag(float tempIntMag) {
		this.tempIntMag = tempIntMag;
	}

	public float getTempIntGyr() {
		return tempIntGyr;
	}

	public void setTempIntGyr(float tempIntGyr) {
		this.tempIntGyr = tempIntGyr;
	}

}
