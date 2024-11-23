package ru.r2cloud.jradio.dora;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class FirstPacket {

	private long timestamp;
	private long missionticks;
	private int state;
	private long receivedPackets;
	private int lastLoggedErrorThread;
	private int lastLoggedErrorCode;
	private long obcRebootCount;
	private Adcs adcs;
	private Eps eps;
	private Battery battery;
	private Payload1 payload1;

	public FirstPacket() {
		// do nothing
	}

	public FirstPacket(DataInputStream dis) throws IOException {
		timestamp = dis.readLong();
		missionticks = dis.readLong() & 0xFFFFFFFFFFFFL;
		state = dis.readUnsignedByte();
		receivedPackets = StreamUtils.readUnsignedInt(dis);
		lastLoggedErrorThread = dis.readUnsignedByte();
		lastLoggedErrorCode = dis.readUnsignedByte();
		obcRebootCount = StreamUtils.readUnsignedInt(dis);
		adcs = new Adcs(dis);
		eps = new Eps(dis);
		battery = new Battery(dis);
		payload1 = new Payload1(dis);
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public long getMissionticks() {
		return missionticks;
	}

	public void setMissionticks(long missionticks) {
		this.missionticks = missionticks;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public long getReceivedPackets() {
		return receivedPackets;
	}

	public void setReceivedPackets(long receivedPackets) {
		this.receivedPackets = receivedPackets;
	}

	public int getLastLoggedErrorThread() {
		return lastLoggedErrorThread;
	}

	public void setLastLoggedErrorThread(int lastLoggedErrorThread) {
		this.lastLoggedErrorThread = lastLoggedErrorThread;
	}

	public int getLastLoggedErrorCode() {
		return lastLoggedErrorCode;
	}

	public void setLastLoggedErrorCode(int lastLoggedErrorCode) {
		this.lastLoggedErrorCode = lastLoggedErrorCode;
	}

	public long getObcRebootCount() {
		return obcRebootCount;
	}

	public void setObcRebootCount(long obcRebootCount) {
		this.obcRebootCount = obcRebootCount;
	}

	public Adcs getAdcs() {
		return adcs;
	}

	public void setAdcs(Adcs adcs) {
		this.adcs = adcs;
	}

	public Eps getEps() {
		return eps;
	}

	public void setEps(Eps eps) {
		this.eps = eps;
	}

	public Battery getBattery() {
		return battery;
	}

	public void setBattery(Battery battery) {
		this.battery = battery;
	}

	public Payload1 getPayload1() {
		return payload1;
	}

	public void setPayload1(Payload1 payload1) {
		this.payload1 = payload1;
	}

}
