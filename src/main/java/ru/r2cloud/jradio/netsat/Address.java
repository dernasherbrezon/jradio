package ru.r2cloud.jradio.netsat;

public class Address {

	private long systemId;
	private int subsystemId;

	public Address() {
		// do nothing
	}

	public Address(long systemId, int subsystemId) {
		this.systemId = systemId;
		this.subsystemId = subsystemId;
	}

	public long getSystemId() {
		return systemId;
	}

	public void setSystemId(long systemId) {
		this.systemId = systemId;
	}

	public int getSubsystemId() {
		return subsystemId;
	}

	public void setSubsystemId(int subsystemId) {
		this.subsystemId = subsystemId;
	}

}
