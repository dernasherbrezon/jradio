package ru.r2cloud.jradio.eirsat;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class CoreEvent {

	private long eventcount0;
	private int lastevent0Severity;
	private int lastevent0EventId;
	private int lastevent0EventSourceId;
	private long lastevent0Info;

	public CoreEvent() {
		// do nothing
	}

	public CoreEvent(BitInputStream dis) throws IOException {
		eventcount0 = dis.readUnsignedLong(32);
		lastevent0Severity = dis.readUnsignedInt(2);
		lastevent0EventId = dis.readUnsignedInt(14);
		lastevent0EventSourceId = dis.readUnsignedShort();
		lastevent0Info = dis.readUnsignedLong(32);
	}

	public long getEventcount0() {
		return eventcount0;
	}

	public void setEventcount0(long eventcount0) {
		this.eventcount0 = eventcount0;
	}

	public int getLastevent0Severity() {
		return lastevent0Severity;
	}

	public void setLastevent0Severity(int lastevent0Severity) {
		this.lastevent0Severity = lastevent0Severity;
	}

	public int getLastevent0EventId() {
		return lastevent0EventId;
	}

	public void setLastevent0EventId(int lastevent0EventId) {
		this.lastevent0EventId = lastevent0EventId;
	}

	public int getLastevent0EventSourceId() {
		return lastevent0EventSourceId;
	}

	public void setLastevent0EventSourceId(int lastevent0EventSourceId) {
		this.lastevent0EventSourceId = lastevent0EventSourceId;
	}

	public long getLastevent0Info() {
		return lastevent0Info;
	}

	public void setLastevent0Info(long lastevent0Info) {
		this.lastevent0Info = lastevent0Info;
	}

}
