package ru.r2cloud.jradio.catsat;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.bobcat1.BeaconElementHeader;
import ru.r2cloud.jradio.util.StreamUtils;

public class Crit1 {

	private BeaconElementHeader hk141;
	private short obcTempMcu;
	private int obcBootCnt;
	private long obcClock;
	private BeaconElementHeader hk191;
	private int bpxVbatt;
	private short bpxTemp;
	private long bpxBootCnt;
	private BeaconElementHeader hk541;
	private short ax100TempBrd;
	private int ax100BootCnt;
	private long ax100LastContact;
	private BeaconElementHeader hk841;
	private long p60BootCnt;
	private int p60BattMode;
	private int p60BattVoltage;
	private short p60BattCurrent;
	private BeaconElementHeader hk94;
	private short[] pduX2Cout;

	public Crit1() {
		// do nothing
	}

	public Crit1(DataInputStream dis) throws IOException {
		hk141 = new BeaconElementHeader(dis);
		obcTempMcu = dis.readShort();
		obcBootCnt = dis.readUnsignedShort();
		obcClock = StreamUtils.readUnsignedInt(dis);
		hk191 = new BeaconElementHeader(dis);
		bpxVbatt = dis.readUnsignedShort();
		bpxTemp = dis.readShort();
		bpxBootCnt = StreamUtils.readUnsignedInt(dis);
		hk541 = new BeaconElementHeader(dis);
		ax100TempBrd = dis.readShort();
		ax100BootCnt = dis.readUnsignedShort();
		ax100LastContact = StreamUtils.readUnsignedInt(dis);
		hk841 = new BeaconElementHeader(dis);
		p60BootCnt = StreamUtils.readUnsignedInt(dis);
		p60BattMode = dis.readUnsignedByte();
		p60BattVoltage = dis.readUnsignedShort();
		p60BattCurrent = dis.readShort();
		hk94 = new BeaconElementHeader(dis);
		pduX2Cout = StreamUtils.readShortArray(dis, 9);
	}

	public BeaconElementHeader getHk141() {
		return hk141;
	}

	public void setHk141(BeaconElementHeader hk141) {
		this.hk141 = hk141;
	}

	public short getObcTempMcu() {
		return obcTempMcu;
	}

	public void setObcTempMcu(short obcTempMcu) {
		this.obcTempMcu = obcTempMcu;
	}

	public int getObcBootCnt() {
		return obcBootCnt;
	}

	public void setObcBootCnt(int obcBootCnt) {
		this.obcBootCnt = obcBootCnt;
	}

	public long getObcClock() {
		return obcClock;
	}

	public void setObcClock(long obcClock) {
		this.obcClock = obcClock;
	}

	public BeaconElementHeader getHk191() {
		return hk191;
	}

	public void setHk191(BeaconElementHeader hk191) {
		this.hk191 = hk191;
	}

	public int getBpxVbatt() {
		return bpxVbatt;
	}

	public void setBpxVbatt(int bpxVbatt) {
		this.bpxVbatt = bpxVbatt;
	}

	public short getBpxTemp() {
		return bpxTemp;
	}

	public void setBpxTemp(short bpxTemp) {
		this.bpxTemp = bpxTemp;
	}

	public long getBpxBootCnt() {
		return bpxBootCnt;
	}

	public void setBpxBootCnt(long bpxBootCnt) {
		this.bpxBootCnt = bpxBootCnt;
	}

	public BeaconElementHeader getHk541() {
		return hk541;
	}

	public void setHk541(BeaconElementHeader hk541) {
		this.hk541 = hk541;
	}

	public short getAx100TempBrd() {
		return ax100TempBrd;
	}

	public void setAx100TempBrd(short ax100TempBrd) {
		this.ax100TempBrd = ax100TempBrd;
	}

	public int getAx100BootCnt() {
		return ax100BootCnt;
	}

	public void setAx100BootCnt(int ax100BootCnt) {
		this.ax100BootCnt = ax100BootCnt;
	}

	public long getAx100LastContact() {
		return ax100LastContact;
	}

	public void setAx100LastContact(long ax100LastContact) {
		this.ax100LastContact = ax100LastContact;
	}

	public BeaconElementHeader getHk841() {
		return hk841;
	}

	public void setHk841(BeaconElementHeader hk841) {
		this.hk841 = hk841;
	}

	public long getP60BootCnt() {
		return p60BootCnt;
	}

	public void setP60BootCnt(long p60BootCnt) {
		this.p60BootCnt = p60BootCnt;
	}

	public int getP60BattMode() {
		return p60BattMode;
	}

	public void setP60BattMode(int p60BattMode) {
		this.p60BattMode = p60BattMode;
	}

	public int getP60BattVoltage() {
		return p60BattVoltage;
	}

	public void setP60BattVoltage(int p60BattVoltage) {
		this.p60BattVoltage = p60BattVoltage;
	}

	public short getP60BattCurrent() {
		return p60BattCurrent;
	}

	public void setP60BattCurrent(short p60BattCurrent) {
		this.p60BattCurrent = p60BattCurrent;
	}

	public BeaconElementHeader getHk94() {
		return hk94;
	}

	public void setHk94(BeaconElementHeader hk94) {
		this.hk94 = hk94;
	}

	public short[] getPduX2Cout() {
		return pduX2Cout;
	}

	public void setPduX2Cout(short[] pduX2Cout) {
		this.pduX2Cout = pduX2Cout;
	}

}
