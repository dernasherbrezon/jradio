package ru.r2cloud.jradio.catsat;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.bobcat1.BeaconElementHeader;
import ru.r2cloud.jradio.util.StreamUtils;

public class Pdu1 {

	private BeaconElementHeader hk844;
	private short[] p60Cout;
	private int[] p60OutEn;
	private short[] p60Temp;
	private long p60Bootcause;
	private long p60Uptime;
	private int p60Resetcause;
	private int[] p60Latchup;
	private short p60VccC;
	private int p60BattV;
	private int p60DearmStatus;
	private long p60WdtCntGnd;
	private long p60WdtCntCan;
	private long p60WdtCntLeft;
	private short p60BattChrg;
	private short p60BattDchrg;
	private int ant6Depl;
	private int ar6Depl;
	private BeaconElementHeader hk944;
	private short[] pduX2Vout;
	private short pduX2Temp;
	private int[] pduX2OutEn;
	private long pduX2Bootcause;
	private long pduX2BootCnt;
	private long pduX2Uptime;
	private int pduX2Resetcause;
	private int[] pduX2Latchup;

	public Pdu1() {
		// do nothing
	}

	public Pdu1(DataInputStream dis) throws IOException {
		hk844 = new BeaconElementHeader(dis);
		p60Cout = StreamUtils.readShortArray(dis, 13);
		p60OutEn = StreamUtils.readUnsignedByteArray(dis, 13);
		p60Temp = StreamUtils.readShortArray(dis, 2);
		p60Bootcause = StreamUtils.readUnsignedInt(dis);
		p60Uptime = StreamUtils.readUnsignedInt(dis);
		p60Resetcause = dis.readUnsignedShort();
		p60Latchup = StreamUtils.readUnsignedShortArray(dis, 13);
		p60VccC = dis.readShort();
		p60BattV = dis.readUnsignedShort();
		p60DearmStatus = dis.readUnsignedByte();
		p60WdtCntGnd = StreamUtils.readUnsignedInt(dis);
		p60WdtCntCan = StreamUtils.readUnsignedInt(dis);
		p60WdtCntLeft = StreamUtils.readUnsignedInt(dis);
		p60BattChrg = dis.readShort();
		p60BattDchrg = dis.readShort();
		ant6Depl = dis.readUnsignedByte();
		ar6Depl = dis.readUnsignedByte();
		hk944 = new BeaconElementHeader(dis);
		pduX2Vout = StreamUtils.readShortArray(dis, 9);
		pduX2Temp = dis.readShort();
		pduX2OutEn = StreamUtils.readUnsignedByteArray(dis, 9);
		pduX2Bootcause = StreamUtils.readUnsignedInt(dis);
		pduX2BootCnt = StreamUtils.readUnsignedInt(dis);
		pduX2Uptime = StreamUtils.readUnsignedInt(dis);
		pduX2Resetcause = dis.readUnsignedShort();
		pduX2Latchup = StreamUtils.readUnsignedShortArray(dis, 9);
	}

	public BeaconElementHeader getHk844() {
		return hk844;
	}

	public void setHk844(BeaconElementHeader hk844) {
		this.hk844 = hk844;
	}

	public short[] getP60Cout() {
		return p60Cout;
	}

	public void setP60Cout(short[] p60Cout) {
		this.p60Cout = p60Cout;
	}

	public int[] getP60OutEn() {
		return p60OutEn;
	}

	public void setP60OutEn(int[] p60OutEn) {
		this.p60OutEn = p60OutEn;
	}

	public short[] getP60Temp() {
		return p60Temp;
	}

	public void setP60Temp(short[] p60Temp) {
		this.p60Temp = p60Temp;
	}

	public long getP60Bootcause() {
		return p60Bootcause;
	}

	public void setP60Bootcause(long p60Bootcause) {
		this.p60Bootcause = p60Bootcause;
	}

	public long getP60Uptime() {
		return p60Uptime;
	}

	public void setP60Uptime(long p60Uptime) {
		this.p60Uptime = p60Uptime;
	}

	public int getP60Resetcause() {
		return p60Resetcause;
	}

	public void setP60Resetcause(int p60Resetcause) {
		this.p60Resetcause = p60Resetcause;
	}

	public int[] getP60Latchup() {
		return p60Latchup;
	}

	public void setP60Latchup(int[] p60Latchup) {
		this.p60Latchup = p60Latchup;
	}

	public short getP60VccC() {
		return p60VccC;
	}

	public void setP60VccC(short p60VccC) {
		this.p60VccC = p60VccC;
	}

	public int getP60BattV() {
		return p60BattV;
	}

	public void setP60BattV(int p60BattV) {
		this.p60BattV = p60BattV;
	}

	public int getP60DearmStatus() {
		return p60DearmStatus;
	}

	public void setP60DearmStatus(int p60DearmStatus) {
		this.p60DearmStatus = p60DearmStatus;
	}

	public long getP60WdtCntGnd() {
		return p60WdtCntGnd;
	}

	public void setP60WdtCntGnd(long p60WdtCntGnd) {
		this.p60WdtCntGnd = p60WdtCntGnd;
	}

	public long getP60WdtCntCan() {
		return p60WdtCntCan;
	}

	public void setP60WdtCntCan(long p60WdtCntCan) {
		this.p60WdtCntCan = p60WdtCntCan;
	}

	public long getP60WdtCntLeft() {
		return p60WdtCntLeft;
	}

	public void setP60WdtCntLeft(long p60WdtCntLeft) {
		this.p60WdtCntLeft = p60WdtCntLeft;
	}

	public short getP60BattChrg() {
		return p60BattChrg;
	}

	public void setP60BattChrg(short p60BattChrg) {
		this.p60BattChrg = p60BattChrg;
	}

	public short getP60BattDchrg() {
		return p60BattDchrg;
	}

	public void setP60BattDchrg(short p60BattDchrg) {
		this.p60BattDchrg = p60BattDchrg;
	}

	public int getAnt6Depl() {
		return ant6Depl;
	}

	public void setAnt6Depl(int ant6Depl) {
		this.ant6Depl = ant6Depl;
	}

	public int getAr6Depl() {
		return ar6Depl;
	}

	public void setAr6Depl(int ar6Depl) {
		this.ar6Depl = ar6Depl;
	}

	public BeaconElementHeader getHk944() {
		return hk944;
	}

	public void setHk944(BeaconElementHeader hk944) {
		this.hk944 = hk944;
	}

	public short[] getPduX2Vout() {
		return pduX2Vout;
	}

	public void setPduX2Vout(short[] pduX2Vout) {
		this.pduX2Vout = pduX2Vout;
	}

	public short getPduX2Temp() {
		return pduX2Temp;
	}

	public void setPduX2Temp(short pduX2Temp) {
		this.pduX2Temp = pduX2Temp;
	}

	public int[] getPduX2OutEn() {
		return pduX2OutEn;
	}

	public void setPduX2OutEn(int[] pduX2OutEn) {
		this.pduX2OutEn = pduX2OutEn;
	}

	public long getPduX2Bootcause() {
		return pduX2Bootcause;
	}

	public void setPduX2Bootcause(long pduX2Bootcause) {
		this.pduX2Bootcause = pduX2Bootcause;
	}

	public long getPduX2BootCnt() {
		return pduX2BootCnt;
	}

	public void setPduX2BootCnt(long pduX2BootCnt) {
		this.pduX2BootCnt = pduX2BootCnt;
	}

	public long getPduX2Uptime() {
		return pduX2Uptime;
	}

	public void setPduX2Uptime(long pduX2Uptime) {
		this.pduX2Uptime = pduX2Uptime;
	}

	public int getPduX2Resetcause() {
		return pduX2Resetcause;
	}

	public void setPduX2Resetcause(int pduX2Resetcause) {
		this.pduX2Resetcause = pduX2Resetcause;
	}

	public int[] getPduX2Latchup() {
		return pduX2Latchup;
	}

	public void setPduX2Latchup(int[] pduX2Latchup) {
		this.pduX2Latchup = pduX2Latchup;
	}

}
