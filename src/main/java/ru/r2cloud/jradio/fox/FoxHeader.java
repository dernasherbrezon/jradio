package ru.r2cloud.jradio.fox;

import java.io.IOException;

import ru.r2cloud.jradio.util.LsbBitInputStream;

public class FoxHeader {

	private int type;
	private long uptime;
	private int resetCount;
	private int foxId;

	public FoxHeader() {
		// do nothing
	}
	
	public FoxHeader(LsbBitInputStream dis) throws IOException {
		foxId = dis.readBitsAsInt(3);
		resetCount = dis.readBitsAsInt(16);
		uptime = dis.readBitsAsInt(25);
		type = dis.readBitsAsInt(4);
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public long getUptime() {
		return uptime;
	}

	public void setUptime(long uptime) {
		this.uptime = uptime;
	}

	public int getResetCount() {
		return resetCount;
	}

	public void setResetCount(int resetCount) {
		this.resetCount = resetCount;
	}

	public int getFoxId() {
		return foxId;
	}

	public void setFoxId(int foxId) {
		this.foxId = foxId;
	}

}
