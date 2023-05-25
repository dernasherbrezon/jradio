package ru.r2cloud.jradio.cirbe;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class SohL0 {

	private int wdt2secCounter;
	private boolean resetArmed;
	private boolean wdtStatus;
	private boolean wdtEnableStatus;
	private boolean tableSelect;
	private boolean bootRelay;

	private int l0AcceptCounter;
	private int l0RejectCounter;
	private int hardwareSecondCounter;
	private long timeTag;

	public SohL0() {
		// do nothing
	}

	public SohL0(DataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		wdt2secCounter = (raw >> 5) & 0b111;
		resetArmed = ((raw >> 4) & 0x1) > 0;
		wdtStatus = ((raw >> 3) & 0x1) > 0;
		wdtEnableStatus = ((raw >> 2) & 0x1) > 0;
		tableSelect = ((raw >> 1) & 0x1) > 0;
		bootRelay = (raw & 0x1) > 0;

		l0AcceptCounter = dis.readUnsignedByte();
		l0RejectCounter = dis.readUnsignedByte();
		hardwareSecondCounter = dis.readUnsignedByte();
		dis.skipBytes(8);
		timeTag = StreamUtils.readUnsignedInt(dis);
		dis.skipBytes(16);
	}

	public int getWdt2secCounter() {
		return wdt2secCounter;
	}

	public void setWdt2secCounter(int wdt2secCounter) {
		this.wdt2secCounter = wdt2secCounter;
	}

	public boolean isResetArmed() {
		return resetArmed;
	}

	public void setResetArmed(boolean resetArmed) {
		this.resetArmed = resetArmed;
	}

	public boolean isWdtStatus() {
		return wdtStatus;
	}

	public void setWdtStatus(boolean wdtStatus) {
		this.wdtStatus = wdtStatus;
	}

	public boolean isWdtEnableStatus() {
		return wdtEnableStatus;
	}

	public void setWdtEnableStatus(boolean wdtEnableStatus) {
		this.wdtEnableStatus = wdtEnableStatus;
	}

	public boolean isTableSelect() {
		return tableSelect;
	}

	public void setTableSelect(boolean tableSelect) {
		this.tableSelect = tableSelect;
	}

	public boolean isBootRelay() {
		return bootRelay;
	}

	public void setBootRelay(boolean bootRelay) {
		this.bootRelay = bootRelay;
	}

	public int getL0AcceptCounter() {
		return l0AcceptCounter;
	}

	public void setL0AcceptCounter(int l0AcceptCounter) {
		this.l0AcceptCounter = l0AcceptCounter;
	}

	public int getL0RejectCounter() {
		return l0RejectCounter;
	}

	public void setL0RejectCounter(int l0RejectCounter) {
		this.l0RejectCounter = l0RejectCounter;
	}

	public int getHardwareSecondCounter() {
		return hardwareSecondCounter;
	}

	public void setHardwareSecondCounter(int hardwareSecondCounter) {
		this.hardwareSecondCounter = hardwareSecondCounter;
	}

	public long getTimeTag() {
		return timeTag;
	}

	public void setTimeTag(long timeTag) {
		this.timeTag = timeTag;
	}

}
