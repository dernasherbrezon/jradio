package ru.r2cloud.jradio.crocube;

public class Mgs {

	private float tempIntMag;
	private float tempIntGyr;
	private long xIntMag;
	private long yIntMag;
	private long zIntMag;
	private long xIntGyr;
	private long yIntGyr;
	private long zIntGyr;
	private float tempExtMag;
	private float tempExtGyr;
	private long xExtMag;
	private long yExtMag;
	private long zExtMag;
	private long xExtGyr;
	private long yExtGyr;
	private long zExtGyr;

	public Mgs() {
		// do nothing
	}

	public Mgs(String[] parts) {
		tempIntMag = Long.valueOf(parts[1]) * 0.01f;
		tempIntGyr = Long.valueOf(parts[2]) * 0.01f;
		xIntMag = Long.valueOf(parts[3]);
		yIntMag = Long.valueOf(parts[4]);
		zIntMag = Long.valueOf(parts[5]);
		xIntGyr = Long.valueOf(parts[6]);
		yIntGyr = Long.valueOf(parts[7]);
		zIntGyr = Long.valueOf(parts[8]);
		tempExtMag = Long.valueOf(parts[9]) * 0.01f;
		tempExtGyr = Long.valueOf(parts[11]) * 0.01f;
		xExtMag = Long.valueOf(parts[11]);
		yExtMag = Long.valueOf(parts[12]);
		zExtMag = Long.valueOf(parts[13]);
		xExtGyr = Long.valueOf(parts[14]);
		yExtGyr = Long.valueOf(parts[15]);
		zExtGyr = Long.valueOf(parts[16]);
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

	public long getxIntMag() {
		return xIntMag;
	}

	public void setxIntMag(long xIntMag) {
		this.xIntMag = xIntMag;
	}

	public long getyIntMag() {
		return yIntMag;
	}

	public void setyIntMag(long yIntMag) {
		this.yIntMag = yIntMag;
	}

	public long getzIntMag() {
		return zIntMag;
	}

	public void setzIntMag(long zIntMag) {
		this.zIntMag = zIntMag;
	}

	public long getxIntGyr() {
		return xIntGyr;
	}

	public void setxIntGyr(long xIntGyr) {
		this.xIntGyr = xIntGyr;
	}

	public long getyIntGyr() {
		return yIntGyr;
	}

	public void setyIntGyr(long yIntGyr) {
		this.yIntGyr = yIntGyr;
	}

	public long getzIntGyr() {
		return zIntGyr;
	}

	public void setzIntGyr(long zIntGyr) {
		this.zIntGyr = zIntGyr;
	}

	public float getTempExtMag() {
		return tempExtMag;
	}

	public void setTempExtMag(float tempExtMag) {
		this.tempExtMag = tempExtMag;
	}

	public float getTempExtGyr() {
		return tempExtGyr;
	}

	public void setTempExtGyr(float tempExtGyr) {
		this.tempExtGyr = tempExtGyr;
	}

	public long getxExtMag() {
		return xExtMag;
	}

	public void setxExtMag(long xExtMag) {
		this.xExtMag = xExtMag;
	}

	public long getyExtMag() {
		return yExtMag;
	}

	public void setyExtMag(long yExtMag) {
		this.yExtMag = yExtMag;
	}

	public long getzExtMag() {
		return zExtMag;
	}

	public void setzExtMag(long zExtMag) {
		this.zExtMag = zExtMag;
	}

	public long getxExtGyr() {
		return xExtGyr;
	}

	public void setxExtGyr(long xExtGyr) {
		this.xExtGyr = xExtGyr;
	}

	public long getyExtGyr() {
		return yExtGyr;
	}

	public void setyExtGyr(long yExtGyr) {
		this.yExtGyr = yExtGyr;
	}

	public long getzExtGyr() {
		return zExtGyr;
	}

	public void setzExtGyr(long zExtGyr) {
		this.zExtGyr = zExtGyr;
	}

}
