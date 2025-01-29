package ru.r2cloud.jradio.crocube;

public class Atr {

	private long id;
	private long defective;
	private long rst1;
	private long rst2;
	private long rst3;
	private long uptime1;
	private long uptime2;
	private long uptime3;
	private long checksum;

	public Atr() {
		// do nothing
	}

	public Atr(String[] parts) {
		id = Long.valueOf(parts[1]);
		defective = Long.valueOf(parts[2]);
		rst1 = Long.valueOf(parts[3]);
		rst2 = Long.valueOf(parts[4]);
		rst3 = Long.valueOf(parts[5]);
		uptime1 = Long.valueOf(parts[6]);
		uptime2 = Long.valueOf(parts[7]);
		uptime3 = Long.valueOf(parts[8]);
		checksum = Long.valueOf(parts[9]);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getDefective() {
		return defective;
	}

	public void setDefective(long defective) {
		this.defective = defective;
	}

	public long getRst1() {
		return rst1;
	}

	public void setRst1(long rst1) {
		this.rst1 = rst1;
	}

	public long getRst2() {
		return rst2;
	}

	public void setRst2(long rst2) {
		this.rst2 = rst2;
	}

	public long getRst3() {
		return rst3;
	}

	public void setRst3(long rst3) {
		this.rst3 = rst3;
	}

	public long getUptime1() {
		return uptime1;
	}

	public void setUptime1(long uptime1) {
		this.uptime1 = uptime1;
	}

	public long getUptime2() {
		return uptime2;
	}

	public void setUptime2(long uptime2) {
		this.uptime2 = uptime2;
	}

	public long getUptime3() {
		return uptime3;
	}

	public void setUptime3(long uptime3) {
		this.uptime3 = uptime3;
	}

	public long getChecksum() {
		return checksum;
	}

	public void setChecksum(long checksum) {
		this.checksum = checksum;
	}

}
