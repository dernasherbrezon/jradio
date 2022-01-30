package ru.r2cloud.jradio.delfipq;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class SubsystemTelemetry {

	private int softwareImage;
	private int bootCounter;
	private ResetCause resetCause;
	private long uptime;
	private long totalUptime;
	private int tlmVersion;

	public SubsystemTelemetry() {
		// do nothing
	}

	public SubsystemTelemetry(DataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		softwareImage = (raw & 0b1111);
		bootCounter = dis.readUnsignedByte();
		resetCause = new ResetCause(dis);
		uptime = StreamUtils.readUnsignedInt(dis);
		totalUptime = StreamUtils.readUnsignedInt(dis);
		tlmVersion = dis.readUnsignedByte();
	}

	public int getSoftwareImage() {
		return softwareImage;
	}

	public void setSoftwareImage(int softwareImage) {
		this.softwareImage = softwareImage;
	}

	public int getBootCounter() {
		return bootCounter;
	}

	public void setBootCounter(int bootCounter) {
		this.bootCounter = bootCounter;
	}

	public ResetCause getResetCause() {
		return resetCause;
	}

	public void setResetCause(ResetCause resetCause) {
		this.resetCause = resetCause;
	}

	public long getUptime() {
		return uptime;
	}

	public void setUptime(long uptime) {
		this.uptime = uptime;
	}

	public long getTotalUptime() {
		return totalUptime;
	}

	public void setTotalUptime(long totalUptime) {
		this.totalUptime = totalUptime;
	}

	public int getTlmVersion() {
		return tlmVersion;
	}

	public void setTlmVersion(int tlmVersion) {
		this.tlmVersion = tlmVersion;
	}

}
