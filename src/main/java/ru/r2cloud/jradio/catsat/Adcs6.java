package ru.r2cloud.jradio.catsat;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.bobcat1.BeaconElementHeader;
import ru.r2cloud.jradio.util.StreamUtils;

public class Adcs6 {

	private BeaconElementHeader hk4116;
	private int adcsSwloadCnt1;
	private BeaconElementHeader hk4416;
	private int adcsFsMounted;
	private short adcsTempMcu;
	private short adcsTempRam;
	private long adcsResetcause;
	private long adcsBootcause;
	private int adcsBootCnt;
	private long adcsClock;
	private long adcsUptime;

	public Adcs6() {
		// do nothing
	}

	public Adcs6(DataInputStream dis) throws IOException {
		hk4116 = new BeaconElementHeader(dis);
		adcsSwloadCnt1 = dis.readUnsignedShort();
		hk4416 = new BeaconElementHeader(dis);
		adcsFsMounted = dis.readUnsignedByte();
		adcsTempMcu = dis.readShort();
		adcsTempRam = dis.readShort();
		adcsResetcause = StreamUtils.readUnsignedInt(dis);
		adcsBootcause = StreamUtils.readUnsignedInt(dis);
		adcsBootCnt = dis.readUnsignedShort();
		adcsClock = StreamUtils.readUnsignedInt(dis);
		adcsUptime = StreamUtils.readUnsignedInt(dis);
	}

	public BeaconElementHeader getHk4116() {
		return hk4116;
	}

	public void setHk4116(BeaconElementHeader hk4116) {
		this.hk4116 = hk4116;
	}

	public int getAdcsSwloadCnt1() {
		return adcsSwloadCnt1;
	}

	public void setAdcsSwloadCnt1(int adcsSwloadCnt1) {
		this.adcsSwloadCnt1 = adcsSwloadCnt1;
	}

	public BeaconElementHeader getHk4416() {
		return hk4416;
	}

	public void setHk4416(BeaconElementHeader hk4416) {
		this.hk4416 = hk4416;
	}

	public int getAdcsFsMounted() {
		return adcsFsMounted;
	}

	public void setAdcsFsMounted(int adcsFsMounted) {
		this.adcsFsMounted = adcsFsMounted;
	}

	public short getAdcsTempMcu() {
		return adcsTempMcu;
	}

	public void setAdcsTempMcu(short adcsTempMcu) {
		this.adcsTempMcu = adcsTempMcu;
	}

	public short getAdcsTempRam() {
		return adcsTempRam;
	}

	public void setAdcsTempRam(short adcsTempRam) {
		this.adcsTempRam = adcsTempRam;
	}

	public long getAdcsResetcause() {
		return adcsResetcause;
	}

	public void setAdcsResetcause(long adcsResetcause) {
		this.adcsResetcause = adcsResetcause;
	}

	public long getAdcsBootcause() {
		return adcsBootcause;
	}

	public void setAdcsBootcause(long adcsBootcause) {
		this.adcsBootcause = adcsBootcause;
	}

	public int getAdcsBootCnt() {
		return adcsBootCnt;
	}

	public void setAdcsBootCnt(int adcsBootCnt) {
		this.adcsBootCnt = adcsBootCnt;
	}

	public long getAdcsClock() {
		return adcsClock;
	}

	public void setAdcsClock(long adcsClock) {
		this.adcsClock = adcsClock;
	}

	public long getAdcsUptime() {
		return adcsUptime;
	}

	public void setAdcsUptime(long adcsUptime) {
		this.adcsUptime = adcsUptime;
	}

}
