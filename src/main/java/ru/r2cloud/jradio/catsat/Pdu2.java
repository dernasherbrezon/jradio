package ru.r2cloud.jradio.catsat;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.bobcat1.BeaconElementHeader;
import ru.r2cloud.jradio.util.StreamUtils;

public class Pdu2 {

	private BeaconElementHeader hk1045;
	private short[] pduX3Vout;
	private short pduX3Temp;
	private int[] pduX3OutEn;
	private long pduX3Bootcause;
	private long pduX3BootCnt;
	private long pduX3Uptime;
	private int pduX3Resetcause;
	private int[] pduX3Latchup;
	private BeaconElementHeader hk1145;
	private short[] acuCin;
	private int[] acuVin;
	private int acuVbatt;
	private short[] acuTemp;
	private MpttMode acuMpptMode;
	private int[] acuVboost;
	private long acuBootcause;
	private long acuBootCnt;
	private long acuUptime;
	private int acuResetcause;

	public Pdu2() {
		// do nothing
	}

	public Pdu2(DataInputStream dis) throws IOException {
		hk1045 = new BeaconElementHeader(dis);
		pduX3Vout = StreamUtils.readShortArray(dis, 9);
		pduX3Temp = dis.readShort();
		pduX3OutEn = StreamUtils.readUnsignedByteArray(dis, 9);
		pduX3Bootcause = StreamUtils.readUnsignedInt(dis);
		pduX3BootCnt = StreamUtils.readUnsignedInt(dis);
		pduX3Uptime = StreamUtils.readUnsignedInt(dis);
		pduX3Resetcause = dis.readUnsignedShort();
		pduX3Latchup = StreamUtils.readUnsignedShortArray(dis, 9);
		hk1145 = new BeaconElementHeader(dis);
		acuCin = StreamUtils.readShortArray(dis, 6);
		acuVin = StreamUtils.readUnsignedShortArray(dis, 6);
		acuVbatt = dis.readUnsignedShort();
		acuTemp = StreamUtils.readShortArray(dis, 3);
		acuMpptMode = MpttMode.valueOfCode(dis.readUnsignedByte());
		acuVboost = StreamUtils.readUnsignedShortArray(dis, 6);
		acuBootcause = StreamUtils.readUnsignedInt(dis);
		acuBootCnt = StreamUtils.readUnsignedInt(dis);
		acuUptime = StreamUtils.readUnsignedInt(dis);
		acuResetcause = dis.readUnsignedShort();
	}

	public BeaconElementHeader getHk1045() {
		return hk1045;
	}

	public void setHk1045(BeaconElementHeader hk1045) {
		this.hk1045 = hk1045;
	}

	public short[] getPduX3Vout() {
		return pduX3Vout;
	}

	public void setPduX3Vout(short[] pduX3Vout) {
		this.pduX3Vout = pduX3Vout;
	}

	public short getPduX3Temp() {
		return pduX3Temp;
	}

	public void setPduX3Temp(short pduX3Temp) {
		this.pduX3Temp = pduX3Temp;
	}

	public int[] getPduX3OutEn() {
		return pduX3OutEn;
	}

	public void setPduX3OutEn(int[] pduX3OutEn) {
		this.pduX3OutEn = pduX3OutEn;
	}

	public long getPduX3Bootcause() {
		return pduX3Bootcause;
	}

	public void setPduX3Bootcause(long pduX3Bootcause) {
		this.pduX3Bootcause = pduX3Bootcause;
	}

	public long getPduX3BootCnt() {
		return pduX3BootCnt;
	}

	public void setPduX3BootCnt(long pduX3BootCnt) {
		this.pduX3BootCnt = pduX3BootCnt;
	}

	public long getPduX3Uptime() {
		return pduX3Uptime;
	}

	public void setPduX3Uptime(long pduX3Uptime) {
		this.pduX3Uptime = pduX3Uptime;
	}

	public int getPduX3Resetcause() {
		return pduX3Resetcause;
	}

	public void setPduX3Resetcause(int pduX3Resetcause) {
		this.pduX3Resetcause = pduX3Resetcause;
	}

	public int[] getPduX3Latchup() {
		return pduX3Latchup;
	}

	public void setPduX3Latchup(int[] pduX3Latchup) {
		this.pduX3Latchup = pduX3Latchup;
	}

	public BeaconElementHeader getHk1145() {
		return hk1145;
	}

	public void setHk1145(BeaconElementHeader hk1145) {
		this.hk1145 = hk1145;
	}

	public short[] getAcuCin() {
		return acuCin;
	}

	public void setAcuCin(short[] acuCin) {
		this.acuCin = acuCin;
	}

	public int[] getAcuVin() {
		return acuVin;
	}

	public void setAcuVin(int[] acuVin) {
		this.acuVin = acuVin;
	}

	public int getAcuVbatt() {
		return acuVbatt;
	}

	public void setAcuVbatt(int acuVbatt) {
		this.acuVbatt = acuVbatt;
	}

	public short[] getAcuTemp() {
		return acuTemp;
	}

	public void setAcuTemp(short[] acuTemp) {
		this.acuTemp = acuTemp;
	}

	public MpttMode getAcuMpptMode() {
		return acuMpptMode;
	}

	public void setAcuMpptMode(MpttMode acuMpptMode) {
		this.acuMpptMode = acuMpptMode;
	}

	public int[] getAcuVboost() {
		return acuVboost;
	}

	public void setAcuVboost(int[] acuVboost) {
		this.acuVboost = acuVboost;
	}

	public long getAcuBootcause() {
		return acuBootcause;
	}

	public void setAcuBootcause(long acuBootcause) {
		this.acuBootcause = acuBootcause;
	}

	public long getAcuBootCnt() {
		return acuBootCnt;
	}

	public void setAcuBootCnt(long acuBootCnt) {
		this.acuBootCnt = acuBootCnt;
	}

	public long getAcuUptime() {
		return acuUptime;
	}

	public void setAcuUptime(long acuUptime) {
		this.acuUptime = acuUptime;
	}

	public int getAcuResetcause() {
		return acuResetcause;
	}

	public void setAcuResetcause(int acuResetcause) {
		this.acuResetcause = acuResetcause;
	}

}
