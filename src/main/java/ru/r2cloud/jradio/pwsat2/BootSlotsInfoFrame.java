package ru.r2cloud.jradio.pwsat2;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class BootSlotsInfoFrame extends GenericFrame {

	private int primary;
	private int failsafe;

	public BootSlotsInfoFrame() {
		// do nothing
	}

	public BootSlotsInfoFrame(LittleEndianDataInputStream dis) throws IOException {
		super(dis);
	}

	@Override
	public void readExternal(LittleEndianDataInputStream dis) throws IOException {
		primary = dis.readUnsignedByte();
		failsafe = dis.readUnsignedByte();
	}

	public int getPrimary() {
		return primary;
	}

	public void setPrimary(int primary) {
		this.primary = primary;
	}

	public int getFailsafe() {
		return failsafe;
	}

	public void setFailsafe(int failsafe) {
		this.failsafe = failsafe;
	}

}
