package ru.r2cloud.jradio.meteor;

import java.io.DataInputStream;
import java.io.IOException;

public class DigitalTelemetry {

	private ChannelMode channel1Mode;
	private ChannelMode channel2Mode;
	private ChannelMode channel3Mode;
	private ChannelMode channel4Mode;
	private ChannelMode channel5Mode;
	private ChannelMode channel6Mode;

	private FpStatus fp1Status;
	private FpStatus fp2Status;
	private FpStatus fp3Status;

	private boolean protectiveCover1Opened;
	private boolean protectiveCover2Opened;
	private boolean puRhWorking;
	private boolean vipSynchronized;
	private boolean dnsSynchronized;
	private boolean dnsBackupActive;
	private boolean alignmentInChannels123Active;
	private boolean bosBackupActive;

	private boolean internalCalibrationEnabled;

	public DigitalTelemetry() {
		// do nothing
	}

	public DigitalTelemetry(DataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		channel1Mode = ChannelMode.valueOfCode(raw & 0b1111);
		channel2Mode = ChannelMode.valueOfCode((raw >> 4) & 0b1111);
		raw = dis.readUnsignedByte();
		channel3Mode = ChannelMode.valueOfCode(raw & 0b1111);
		channel4Mode = ChannelMode.valueOfCode((raw >> 4) & 0b1111);
		raw = dis.readUnsignedByte();
		channel5Mode = ChannelMode.valueOfCode(raw & 0b1111);
		channel6Mode = ChannelMode.valueOfCode((raw >> 4) & 0b1111);
		fp1Status = new FpStatus(dis);
		fp2Status = new FpStatus(dis);
		raw = dis.readUnsignedByte();
		protectiveCover1Opened = (raw & 0b1) > 0;
		protectiveCover2Opened = ((raw >> 1) & 0b1) > 0;
		puRhWorking = ((raw >> 2) & 0b1) > 0;
		vipSynchronized = ((raw >> 3) & 0b1) > 0;
		dnsSynchronized = ((raw >> 4) & 0b1) > 0;
		dnsBackupActive = ((raw >> 5) & 0b1) > 0;
		alignmentInChannels123Active = ((raw >> 6) & 0b1) > 0;
		bosBackupActive = ((raw >> 7) & 0b1) > 0;
		fp3Status = new FpStatus(dis);
		raw = dis.readUnsignedByte();
		internalCalibrationEnabled = (raw & 0b1) > 0;
		dis.skipBytes(8);
	}

	public ChannelMode getChannel1Mode() {
		return channel1Mode;
	}

	public void setChannel1Mode(ChannelMode channel1Mode) {
		this.channel1Mode = channel1Mode;
	}

	public ChannelMode getChannel2Mode() {
		return channel2Mode;
	}

	public void setChannel2Mode(ChannelMode channel2Mode) {
		this.channel2Mode = channel2Mode;
	}

	public ChannelMode getChannel3Mode() {
		return channel3Mode;
	}

	public void setChannel3Mode(ChannelMode channel3Mode) {
		this.channel3Mode = channel3Mode;
	}

	public ChannelMode getChannel4Mode() {
		return channel4Mode;
	}

	public void setChannel4Mode(ChannelMode channel4Mode) {
		this.channel4Mode = channel4Mode;
	}

	public ChannelMode getChannel5Mode() {
		return channel5Mode;
	}

	public void setChannel5Mode(ChannelMode channel5Mode) {
		this.channel5Mode = channel5Mode;
	}

	public ChannelMode getChannel6Mode() {
		return channel6Mode;
	}

	public void setChannel6Mode(ChannelMode channel6Mode) {
		this.channel6Mode = channel6Mode;
	}

	public FpStatus getFp1Status() {
		return fp1Status;
	}

	public void setFp1Status(FpStatus fp1Status) {
		this.fp1Status = fp1Status;
	}

	public FpStatus getFp2Status() {
		return fp2Status;
	}

	public void setFp2Status(FpStatus fp2Status) {
		this.fp2Status = fp2Status;
	}

	public FpStatus getFp3Status() {
		return fp3Status;
	}

	public void setFp3Status(FpStatus fp3Status) {
		this.fp3Status = fp3Status;
	}

	public boolean isProtectiveCover1Opened() {
		return protectiveCover1Opened;
	}

	public void setProtectiveCover1Opened(boolean protectiveCover1Opened) {
		this.protectiveCover1Opened = protectiveCover1Opened;
	}

	public boolean isProtectiveCover2Opened() {
		return protectiveCover2Opened;
	}

	public void setProtectiveCover2Opened(boolean protectiveCover2Opened) {
		this.protectiveCover2Opened = protectiveCover2Opened;
	}

	public boolean isPuRhWorking() {
		return puRhWorking;
	}

	public void setPuRhWorking(boolean puRhWorking) {
		this.puRhWorking = puRhWorking;
	}

	public boolean isVipSynchronized() {
		return vipSynchronized;
	}

	public void setVipSynchronized(boolean vipSynchronized) {
		this.vipSynchronized = vipSynchronized;
	}

	public boolean isDnsSynchronized() {
		return dnsSynchronized;
	}

	public void setDnsSynchronized(boolean dnsSynchronized) {
		this.dnsSynchronized = dnsSynchronized;
	}

	public boolean isDnsBackupActive() {
		return dnsBackupActive;
	}

	public void setDnsBackupActive(boolean dnsBackupActive) {
		this.dnsBackupActive = dnsBackupActive;
	}

	public boolean isAlignmentInChannels123Active() {
		return alignmentInChannels123Active;
	}

	public void setAlignmentInChannels123Active(boolean alignmentInChannels123Active) {
		this.alignmentInChannels123Active = alignmentInChannels123Active;
	}

	public boolean isBosBackupActive() {
		return bosBackupActive;
	}

	public void setBosBackupActive(boolean bosBackupActive) {
		this.bosBackupActive = bosBackupActive;
	}

	public boolean isInternalCalibrationEnabled() {
		return internalCalibrationEnabled;
	}

	public void setInternalCalibrationEnabled(boolean internalCalibrationEnabled) {
		this.internalCalibrationEnabled = internalCalibrationEnabled;
	}

}
