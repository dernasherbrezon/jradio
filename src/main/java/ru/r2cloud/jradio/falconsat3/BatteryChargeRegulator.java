package ru.r2cloud.jradio.falconsat3;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BatteryChargeRegulator {

	private static final Pattern PATTERN = Pattern.compile("^BCR:bv=(\\d+) bi=(\\d+) sens=(\\d+) top=(\\d+) low=(\\d+) t1=(\\d+) t2=(\\d+) sv=(\\d+) si=(\\d*)");

	private int bv;
	private int bi;
	private int sens;
	private int top;
	private int low;
	private int t1;
	private int t2;
	private int sv;
	private Integer si;

	public BatteryChargeRegulator() {
		// do nothing
	}

	public BatteryChargeRegulator(String message) throws IOException {
		Matcher m = PATTERN.matcher(message.trim());
		if (!m.find()) {
			throw new IOException("unsupported");
		}
		bv = Integer.parseInt(m.group(1));
		bi = Integer.parseInt(m.group(2));
		sens = Integer.parseInt(m.group(3));
		top = Integer.parseInt(m.group(4));
		low = Integer.parseInt(m.group(5));
		t1 = Integer.parseInt(m.group(6));
		t2 = Integer.parseInt(m.group(7));
		sv = Integer.parseInt(m.group(8));
		String siStr = m.group(9);
		if (siStr.trim().length() != 0) {
			si = Integer.parseInt(siStr);
		}
	}

	public int getBv() {
		return bv;
	}

	public void setBv(int bv) {
		this.bv = bv;
	}

	public int getBi() {
		return bi;
	}

	public void setBi(int bi) {
		this.bi = bi;
	}

	public int getSens() {
		return sens;
	}

	public void setSens(int sens) {
		this.sens = sens;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public int getLow() {
		return low;
	}

	public void setLow(int low) {
		this.low = low;
	}

	public int getT1() {
		return t1;
	}

	public void setT1(int t1) {
		this.t1 = t1;
	}

	public int getT2() {
		return t2;
	}

	public void setT2(int t2) {
		this.t2 = t2;
	}

	public int getSv() {
		return sv;
	}

	public void setSv(int sv) {
		this.sv = sv;
	}

	public Integer getSi() {
		return si;
	}

	public void setSi(Integer si) {
		this.si = si;
	}

}
