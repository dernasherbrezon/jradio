package ru.r2cloud.jradio.smogp;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class PcuDep {

	private long timestamp;

	private int deploymentSwitch;
	private boolean removeBeforeFlight;
	private boolean pcuDeployment;
	private boolean antennaDeployment;

	private int pcuBootCounter;
	private int pcuUptimeMinutes;

	public PcuDep() {
		// do nothing
	}

	public PcuDep(LittleEndianDataInputStream dis) throws IOException {
		timestamp = dis.readUnsignedInt();
		int b = dis.readUnsignedByte();
		deploymentSwitch = (b >> 6) & 0b11;
		removeBeforeFlight = ((b >> 5) & 0x1) > 0;
		pcuDeployment = ((b >> 3) & 0x1) > 0;
		antennaDeployment = ((b >> 2) & 0x1) > 0;

		pcuBootCounter = dis.readUnsignedShort();
		pcuUptimeMinutes = dis.readUnsignedShort();
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public int getDeploymentSwitch() {
		return deploymentSwitch;
	}

	public void setDeploymentSwitch(int deploymentSwitch) {
		this.deploymentSwitch = deploymentSwitch;
	}

	public boolean isRemoveBeforeFlight() {
		return removeBeforeFlight;
	}

	public void setRemoveBeforeFlight(boolean removeBeforeFlight) {
		this.removeBeforeFlight = removeBeforeFlight;
	}

	public boolean isPcuDeployment() {
		return pcuDeployment;
	}

	public void setPcuDeployment(boolean pcuDeployment) {
		this.pcuDeployment = pcuDeployment;
	}

	public boolean isAntennaDeployment() {
		return antennaDeployment;
	}

	public void setAntennaDeployment(boolean antennaDeployment) {
		this.antennaDeployment = antennaDeployment;
	}

	public int getPcuBootCounter() {
		return pcuBootCounter;
	}

	public void setPcuBootCounter(int pcuBootCounter) {
		this.pcuBootCounter = pcuBootCounter;
	}

	public int getPcuUptimeMinutes() {
		return pcuUptimeMinutes;
	}

	public void setPcuUptimeMinutes(int pcuUptimeMinutes) {
		this.pcuUptimeMinutes = pcuUptimeMinutes;
	}

}
