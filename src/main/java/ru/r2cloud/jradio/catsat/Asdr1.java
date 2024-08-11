package ru.r2cloud.jradio.catsat;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.bobcat1.BeaconElementHeader;
import ru.r2cloud.jradio.util.StreamUtils;

public class Asdr1 {

	private BeaconElementHeader hk14021;
	private CoreType coreLoaded;
	private BeaconElementHeader hk14121;
	private int[] sectorHistory;
	private int[] mbytesHistory;
	private long exposure;
	private float gain;
	private BeaconElementHeader hk141221;
	private int chanRefLock;
	private BeaconElementHeader hk141321;
	private float chanTemp;
	private BeaconElementHeader hk141621;
	private int chanInited;
	private BeaconElementHeader hk141821;
	private float chanWritten;
	private int chanRecStatus;
	private int chanReqMbytes;
	private float chanTime;

	public Asdr1() {
		// do nothing
	}

	public Asdr1(DataInputStream dis) throws IOException {
		hk14021 = new BeaconElementHeader(dis);
		coreLoaded = CoreType.valueOfCode(dis.readUnsignedByte());
		hk14121 = new BeaconElementHeader(dis);
		sectorHistory = StreamUtils.readUnsignedShortArray(dis, 16);
		mbytesHistory = StreamUtils.readUnsignedShortArray(dis, 16);
		exposure = StreamUtils.readUnsignedInt(dis);
		gain = dis.readFloat();
		hk141221 = new BeaconElementHeader(dis);
		chanRefLock = dis.readUnsignedByte();
		hk141321 = new BeaconElementHeader(dis);
		chanTemp = dis.readFloat();
		hk141621 = new BeaconElementHeader(dis);
		chanInited = dis.readUnsignedByte();
		hk141821 = new BeaconElementHeader(dis);
		chanWritten = dis.readFloat();
		chanRecStatus = dis.readUnsignedByte();
		chanReqMbytes = dis.readInt();
		chanTime = dis.readFloat();
	}

	public BeaconElementHeader getHk14021() {
		return hk14021;
	}

	public void setHk14021(BeaconElementHeader hk14021) {
		this.hk14021 = hk14021;
	}

	public CoreType getCoreLoaded() {
		return coreLoaded;
	}

	public void setCoreLoaded(CoreType coreLoaded) {
		this.coreLoaded = coreLoaded;
	}

	public BeaconElementHeader getHk14121() {
		return hk14121;
	}

	public void setHk14121(BeaconElementHeader hk14121) {
		this.hk14121 = hk14121;
	}

	public int[] getSectorHistory() {
		return sectorHistory;
	}

	public void setSectorHistory(int[] sectorHistory) {
		this.sectorHistory = sectorHistory;
	}

	public int[] getMbytesHistory() {
		return mbytesHistory;
	}

	public void setMbytesHistory(int[] mbytesHistory) {
		this.mbytesHistory = mbytesHistory;
	}

	public long getExposure() {
		return exposure;
	}

	public void setExposure(long exposure) {
		this.exposure = exposure;
	}

	public float getGain() {
		return gain;
	}

	public void setGain(float gain) {
		this.gain = gain;
	}

	public BeaconElementHeader getHk141221() {
		return hk141221;
	}

	public void setHk141221(BeaconElementHeader hk141221) {
		this.hk141221 = hk141221;
	}

	public int getChanRefLock() {
		return chanRefLock;
	}

	public void setChanRefLock(int chanRefLock) {
		this.chanRefLock = chanRefLock;
	}

	public BeaconElementHeader getHk141321() {
		return hk141321;
	}

	public void setHk141321(BeaconElementHeader hk141321) {
		this.hk141321 = hk141321;
	}

	public float getChanTemp() {
		return chanTemp;
	}

	public void setChanTemp(float chanTemp) {
		this.chanTemp = chanTemp;
	}

	public BeaconElementHeader getHk141621() {
		return hk141621;
	}

	public void setHk141621(BeaconElementHeader hk141621) {
		this.hk141621 = hk141621;
	}

	public int getChanInited() {
		return chanInited;
	}

	public void setChanInited(int chanInited) {
		this.chanInited = chanInited;
	}

	public BeaconElementHeader getHk141821() {
		return hk141821;
	}

	public void setHk141821(BeaconElementHeader hk141821) {
		this.hk141821 = hk141821;
	}

	public float getChanWritten() {
		return chanWritten;
	}

	public void setChanWritten(float chanWritten) {
		this.chanWritten = chanWritten;
	}

	public int getChanRecStatus() {
		return chanRecStatus;
	}

	public void setChanRecStatus(int chanRecStatus) {
		this.chanRecStatus = chanRecStatus;
	}

	public int getChanReqMbytes() {
		return chanReqMbytes;
	}

	public void setChanReqMbytes(int chanReqMbytes) {
		this.chanReqMbytes = chanReqMbytes;
	}

	public float getChanTime() {
		return chanTime;
	}

	public void setChanTime(float chanTime) {
		this.chanTime = chanTime;
	}

}
