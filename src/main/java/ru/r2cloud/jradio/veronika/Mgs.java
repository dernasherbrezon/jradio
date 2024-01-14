package ru.r2cloud.jradio.veronika;

public class Mgs {

	private int tempIntMag;
	private int tempIntGyr;
	private int intMagX;
	private int intMagY;
	private int intMagZ;
	private int intGyrX;
	private int intGyrY;
	private int intGyrZ;
	private int tempExtMag;
	private int tempExtGyr;
	private int extMagX;
	private int extMagY;
	private int extMagZ;
	private int extGyrX;
	private int extGyrY;
	private int extGyrZa;

	public Mgs() {
		// do nothing
	}

	public Mgs(String[] parts) {
		int i = 1;
		tempIntMag = Integer.parseInt(parts[i++]);
		tempIntGyr = Integer.parseInt(parts[i++]);
		intMagX = Integer.parseInt(parts[i++]);
		intMagY = Integer.parseInt(parts[i++]);
		intMagZ = Integer.parseInt(parts[i++]);
		intGyrX = Integer.parseInt(parts[i++]);
		intGyrY = Integer.parseInt(parts[i++]);
		intGyrZ = Integer.parseInt(parts[i++]);
		tempExtMag = Integer.parseInt(parts[i++]);
		tempExtGyr = Integer.parseInt(parts[i++]);
		extMagX = Integer.parseInt(parts[i++]);
		extMagY = Integer.parseInt(parts[i++]);
		extMagZ = Integer.parseInt(parts[i++]);
		extGyrX = Integer.parseInt(parts[i++]);
		extGyrY = Integer.parseInt(parts[i++]);
		extGyrZa = Integer.parseInt(parts[i++]);
	}

	public int getTempIntMag() {
		return tempIntMag;
	}

	public void setTempIntMag(int tempIntMag) {
		this.tempIntMag = tempIntMag;
	}

	public int getTempIntGyr() {
		return tempIntGyr;
	}

	public void setTempIntGyr(int tempIntGyr) {
		this.tempIntGyr = tempIntGyr;
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

	public int getTempExtMag() {
		return tempExtMag;
	}

	public void setTempExtMag(int tempExtMag) {
		this.tempExtMag = tempExtMag;
	}

	public int getTempExtGyr() {
		return tempExtGyr;
	}

	public void setTempExtGyr(int tempExtGyr) {
		this.tempExtGyr = tempExtGyr;
	}

	public int getExtMagX() {
		return extMagX;
	}

	public void setExtMagX(int extMagX) {
		this.extMagX = extMagX;
	}

	public int getExtMagY() {
		return extMagY;
	}

	public void setExtMagY(int extMagY) {
		this.extMagY = extMagY;
	}

	public int getExtMagZ() {
		return extMagZ;
	}

	public void setExtMagZ(int extMagZ) {
		this.extMagZ = extMagZ;
	}

	public int getExtGyrX() {
		return extGyrX;
	}

	public void setExtGyrX(int extGyrX) {
		this.extGyrX = extGyrX;
	}

	public int getExtGyrY() {
		return extGyrY;
	}

	public void setExtGyrY(int extGyrY) {
		this.extGyrY = extGyrY;
	}

	public int getExtGyrZa() {
		return extGyrZa;
	}

	public void setExtGyrZa(int extGyrZa) {
		this.extGyrZa = extGyrZa;
	}

}
