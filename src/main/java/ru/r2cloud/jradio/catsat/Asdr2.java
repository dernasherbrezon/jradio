package ru.r2cloud.jradio.catsat;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.bobcat1.BeaconElementHeader;

public class Asdr2 {

	private BeaconElementHeader hk142922;
	private int chanPpsPresent;
	private int chanPpsCount;
	private BeaconElementHeader hk143722;
	private int recInited;
	private BeaconElementHeader hk143822;
	private float recWritten;
	private int recRecStatus;
	private int recReqMbytes;
	private float recTime;
	private BeaconElementHeader hk144322;
	private float recTemp;
	private BeaconElementHeader hk145222;
	private int transInited;
	private float transMbytesSent;
	private BeaconElementHeader hk145322;
	private long transSystemTime;
	private BeaconElementHeader hk143322;
	private float mis1Temp;
	private BeaconElementHeader hk143422;
	private int mis1FskIncr;
	private BeaconElementHeader hk143522;
	private long mis1SystemTime;

	public Asdr2() {
		// do nothing
	}

	public Asdr2(DataInputStream dis) throws IOException {
		hk142922 = new BeaconElementHeader(dis);
		chanPpsPresent = dis.readUnsignedByte();
		chanPpsCount = dis.readInt();
		hk143722 = new BeaconElementHeader(dis);
		recInited = dis.readUnsignedByte();
		hk143822 = new BeaconElementHeader(dis);
		recWritten = dis.readFloat();
		recRecStatus = dis.readUnsignedByte();
		recReqMbytes = dis.readInt();
		recTime = dis.readFloat();
		hk144322 = new BeaconElementHeader(dis);
		recTemp = dis.readFloat();
		hk145222 = new BeaconElementHeader(dis);
		transInited = dis.readUnsignedByte();
		transMbytesSent = dis.readFloat();
		hk145322 = new BeaconElementHeader(dis);
		transSystemTime = dis.readLong();
		hk143322 = new BeaconElementHeader(dis);
		mis1Temp = dis.readFloat();
		hk143422 = new BeaconElementHeader(dis);
		mis1FskIncr = dis.readInt();
		hk143522 = new BeaconElementHeader(dis);
		mis1SystemTime = dis.readLong();
	}

	public BeaconElementHeader getHk142922() {
		return hk142922;
	}

	public void setHk142922(BeaconElementHeader hk142922) {
		this.hk142922 = hk142922;
	}

	public int getChanPpsPresent() {
		return chanPpsPresent;
	}

	public void setChanPpsPresent(int chanPpsPresent) {
		this.chanPpsPresent = chanPpsPresent;
	}

	public int getChanPpsCount() {
		return chanPpsCount;
	}

	public void setChanPpsCount(int chanPpsCount) {
		this.chanPpsCount = chanPpsCount;
	}

	public BeaconElementHeader getHk143722() {
		return hk143722;
	}

	public void setHk143722(BeaconElementHeader hk143722) {
		this.hk143722 = hk143722;
	}

	public int getRecInited() {
		return recInited;
	}

	public void setRecInited(int recInited) {
		this.recInited = recInited;
	}

	public BeaconElementHeader getHk143822() {
		return hk143822;
	}

	public void setHk143822(BeaconElementHeader hk143822) {
		this.hk143822 = hk143822;
	}

	public float getRecWritten() {
		return recWritten;
	}

	public void setRecWritten(float recWritten) {
		this.recWritten = recWritten;
	}

	public int getRecRecStatus() {
		return recRecStatus;
	}

	public void setRecRecStatus(int recRecStatus) {
		this.recRecStatus = recRecStatus;
	}

	public int getRecReqMbytes() {
		return recReqMbytes;
	}

	public void setRecReqMbytes(int recReqMbytes) {
		this.recReqMbytes = recReqMbytes;
	}

	public float getRecTime() {
		return recTime;
	}

	public void setRecTime(float recTime) {
		this.recTime = recTime;
	}

	public BeaconElementHeader getHk144322() {
		return hk144322;
	}

	public void setHk144322(BeaconElementHeader hk144322) {
		this.hk144322 = hk144322;
	}

	public float getRecTemp() {
		return recTemp;
	}

	public void setRecTemp(float recTemp) {
		this.recTemp = recTemp;
	}

	public BeaconElementHeader getHk145222() {
		return hk145222;
	}

	public void setHk145222(BeaconElementHeader hk145222) {
		this.hk145222 = hk145222;
	}

	public int getTransInited() {
		return transInited;
	}

	public void setTransInited(int transInited) {
		this.transInited = transInited;
	}

	public float getTransMbytesSent() {
		return transMbytesSent;
	}

	public void setTransMbytesSent(float transMbytesSent) {
		this.transMbytesSent = transMbytesSent;
	}

	public BeaconElementHeader getHk145322() {
		return hk145322;
	}

	public void setHk145322(BeaconElementHeader hk145322) {
		this.hk145322 = hk145322;
	}

	public long getTransSystemTime() {
		return transSystemTime;
	}

	public void setTransSystemTime(long transSystemTime) {
		this.transSystemTime = transSystemTime;
	}

	public BeaconElementHeader getHk143322() {
		return hk143322;
	}

	public void setHk143322(BeaconElementHeader hk143322) {
		this.hk143322 = hk143322;
	}

	public float getMis1Temp() {
		return mis1Temp;
	}

	public void setMis1Temp(float mis1Temp) {
		this.mis1Temp = mis1Temp;
	}

	public BeaconElementHeader getHk143422() {
		return hk143422;
	}

	public void setHk143422(BeaconElementHeader hk143422) {
		this.hk143422 = hk143422;
	}

	public int getMis1FskIncr() {
		return mis1FskIncr;
	}

	public void setMis1FskIncr(int mis1FskIncr) {
		this.mis1FskIncr = mis1FskIncr;
	}

	public BeaconElementHeader getHk143522() {
		return hk143522;
	}

	public void setHk143522(BeaconElementHeader hk143522) {
		this.hk143522 = hk143522;
	}

	public long getMis1SystemTime() {
		return mis1SystemTime;
	}

	public void setMis1SystemTime(long mis1SystemTime) {
		this.mis1SystemTime = mis1SystemTime;
	}

}
