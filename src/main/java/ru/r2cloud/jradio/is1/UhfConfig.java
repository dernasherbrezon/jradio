package ru.r2cloud.jradio.is1;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class UhfConfig {

	private boolean locked;
	private UhfFeedback feedback;
	private boolean swd;
	private boolean afc;
	private boolean echo;
	private UhfChannel channel;

	public UhfConfig() {
		// do nothing
	}

	public UhfConfig(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		locked = ((raw >> 7) & 0x1) > 0;
		feedback = UhfFeedback.valueOfCode(((raw >> 5) & 0b11));
		swd = ((raw >> 4) & 0x1) > 0;
		afc = ((raw >> 3) & 0x1) > 0;
		echo = ((raw >> 2) & 0x1) > 0;
		channel = UhfChannel.valueOfCode((raw & 0b11));
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public UhfFeedback getFeedback() {
		return feedback;
	}

	public void setFeedback(UhfFeedback feedback) {
		this.feedback = feedback;
	}

	public boolean isSwd() {
		return swd;
	}

	public void setSwd(boolean swd) {
		this.swd = swd;
	}

	public boolean isAfc() {
		return afc;
	}

	public void setAfc(boolean afc) {
		this.afc = afc;
	}

	public boolean isEcho() {
		return echo;
	}

	public void setEcho(boolean echo) {
		this.echo = echo;
	}

	public UhfChannel getChannel() {
		return channel;
	}

	public void setChannel(UhfChannel channel) {
		this.channel = channel;
	}

}
