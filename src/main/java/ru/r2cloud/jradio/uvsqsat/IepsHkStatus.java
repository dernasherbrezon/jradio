package ru.r2cloud.jradio.uvsqsat;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class IepsHkStatus {

	private int hKStid;
	private int hKIvid;
	private int hKRc;
	private int hKBid;
	private long hKCmderr;
	private long hKStat;
	private float voltBrdSupRaw;
	private float tempRaw;
	private float vDistInputRaw;
	private float iDistInputRaw;
	private float pDistInputRaw;
	private float vBattInputRaw;
	private float iBattInputRaw;
	private float pBattInputRaw;
	private int statObcOn;
	private int statObcOcf;
	private int batStat;
	private float batTemp2Raw;
	private int voltVd0;
	private int voltVd1;
	private int voltVd2;
	private float vObc00;
	private float iObc00;
	private float pObc00;
	private float vObc01;
	private float iObc01;
	private float pObc01;
	private float vObc02;
	private float iObc02;
	private float pObc02;
	private float vObc03;
	private float iObc03;
	private float pObc03;
	private float vObc05;
	private float iObc05;
	private float pObc05;
	private float vObc06;
	private float iObc06;
	private float pObc06;
	private byte[] cc1;
	private byte[] cc2;
	private byte[] cc3;
	private int statusStid;
	private int statusIvid;
	private int statusRc;
	private int statusBid;
	private long statusCmderr;
	private long statusStat;
	private int mode;
	private int conf;
	private int resetCause;
	private long uptime;
	private int error;
	private int rcCntPwron;
	private int rcCntWdg;
	private int rcCntCmd;
	private int rcCntMcu;
	private int rcCntEmlopo;
	private int prevcmdElapsed;

	public IepsHkStatus() {
		// do nothing
	}

	public IepsHkStatus(BitInputStream dis) throws IOException {
		hKStid = dis.readUnsignedByte();
		hKIvid = dis.readUnsignedByte();
		hKRc = dis.readUnsignedByte();
		hKBid = dis.readUnsignedByte();
		hKCmderr = dis.readUnsignedInt(4);
		hKStat = dis.readUnsignedInt(4);
		voltBrdSupRaw = dis.readUnsignedShort() * 1000.0F / 819.0F;
		tempRaw = ((dis.readUnsignedShort() - 1168) * 220.0F / 9.0F) / 100.0F;
		vDistInputRaw = (dis.readUnsignedShort() * 125.0F / 128.0F) / 1000.0F;
		iDistInputRaw = dis.readUnsignedShort() * 3125.0F / 10240.0F;
		pDistInputRaw = dis.readUnsignedShort() * 3125.0F / 3200.0F;
		vBattInputRaw = (dis.readUnsignedShort() * 125.0F / 128.0F) / 1000.0F;
		iBattInputRaw = 1000.0F * (dis.readUnsignedShort() * 3125.0F / 10240.0F);
		pBattInputRaw = dis.readUnsignedShort() * 3125.0F / 3200.0F;
		statObcOn = dis.readUnsignedShort();
		statObcOcf = dis.readUnsignedShort();
		batStat = dis.readUnsignedShort();
		batTemp2Raw = dis.readUnsignedShort() * -0.047715407918F + 98.38261483F;
		voltVd0 = dis.readUnsignedShort();
		voltVd1 = dis.readUnsignedShort();
		voltVd2 = dis.readUnsignedShort();
		vObc00 = (dis.readUnsignedShort() * 125.0F / 128.0F) / 1000.0F;
		iObc00 = dis.readUnsignedShort() * 3125.0F / 20480.0F;
		pObc00 = dis.readUnsignedShort() * 3125.0F / 6400.0F;
		vObc01 = (dis.readUnsignedShort() * 125.0F / 128.0F) / 1000.0F;
		iObc01 = dis.readUnsignedShort() * 3125.0F / 20480.0F;
		pObc01 = dis.readUnsignedShort() * 3125.0F / 6400.0F;
		vObc02 = (dis.readUnsignedShort() * 125.0F / 128.0F) / 1000.0F;
		iObc02 = dis.readUnsignedShort() * 3125.0F / 20480.0F;
		pObc02 = dis.readUnsignedShort() * 3125.0F / 6400.0F;
		vObc03 = (dis.readUnsignedShort() * 125.0F / 128.0F) / 1000.0F;
		iObc03 = dis.readUnsignedShort() * 3125.0F / 20480.0F;
		pObc03 = dis.readUnsignedShort() * 3125.0F / 6400.0F;
		vObc05 = (dis.readUnsignedShort() * 125.0F / 128.0F) / 1000.0F;
		iObc05 = dis.readUnsignedShort() * 3125.0F / 20480.0F;
		pObc05 = dis.readUnsignedShort() * 3125.0F / 6400.0F;
		vObc06 = (dis.readUnsignedShort() * 125.0F / 128.0F) / 1000.0F;
		iObc06 = dis.readUnsignedShort() * 3125.0F / 20480.0F;
		pObc06 = dis.readUnsignedShort() * 3125.0F / 6400.0F;
		cc1 = new byte[8];
		dis.readFully(cc1);
		cc2 = new byte[8];
		dis.readFully(cc2);
		cc3 = new byte[8];
		dis.readFully(cc3);
		statusStid = dis.readUnsignedByte();
		statusIvid = dis.readUnsignedByte();
		statusRc = dis.readUnsignedByte();
		statusBid = dis.readUnsignedByte();
		statusCmderr = dis.readUnsignedInt(4);
		statusStat = dis.readUnsignedInt(4);
		mode = dis.readUnsignedByte();
		conf = dis.readUnsignedByte();
		resetCause = dis.readUnsignedByte();
		uptime = dis.readUnsignedLong(32);
		error = dis.readUnsignedShort();
		rcCntPwron = dis.readUnsignedShort();
		rcCntWdg = dis.readUnsignedShort();
		rcCntCmd = dis.readUnsignedShort();
		rcCntMcu = dis.readUnsignedShort();
		rcCntEmlopo = dis.readUnsignedShort();
		prevcmdElapsed = dis.readUnsignedShort();
	}

	public int gethKStid() {
		return hKStid;
	}

	public void sethKStid(int hKStid) {
		this.hKStid = hKStid;
	}

	public int gethKIvid() {
		return hKIvid;
	}

	public void sethKIvid(int hKIvid) {
		this.hKIvid = hKIvid;
	}

	public int gethKRc() {
		return hKRc;
	}

	public void sethKRc(int hKRc) {
		this.hKRc = hKRc;
	}

	public int gethKBid() {
		return hKBid;
	}

	public void sethKBid(int hKBid) {
		this.hKBid = hKBid;
	}

	public long gethKCmderr() {
		return hKCmderr;
	}

	public void sethKCmderr(long hKCmderr) {
		this.hKCmderr = hKCmderr;
	}

	public long gethKStat() {
		return hKStat;
	}

	public void sethKStat(long hKStat) {
		this.hKStat = hKStat;
	}

	public float getVoltBrdSupRaw() {
		return voltBrdSupRaw;
	}

	public void setVoltBrdSupRaw(float voltBrdSupRaw) {
		this.voltBrdSupRaw = voltBrdSupRaw;
	}

	public float getTempRaw() {
		return tempRaw;
	}

	public void setTempRaw(float tempRaw) {
		this.tempRaw = tempRaw;
	}

	public float getvDistInputRaw() {
		return vDistInputRaw;
	}

	public void setvDistInputRaw(float vDistInputRaw) {
		this.vDistInputRaw = vDistInputRaw;
	}

	public float getiDistInputRaw() {
		return iDistInputRaw;
	}

	public void setiDistInputRaw(float iDistInputRaw) {
		this.iDistInputRaw = iDistInputRaw;
	}

	public float getpDistInputRaw() {
		return pDistInputRaw;
	}

	public void setpDistInputRaw(float pDistInputRaw) {
		this.pDistInputRaw = pDistInputRaw;
	}

	public float getvBattInputRaw() {
		return vBattInputRaw;
	}

	public void setvBattInputRaw(float vBattInputRaw) {
		this.vBattInputRaw = vBattInputRaw;
	}

	public float getiBattInputRaw() {
		return iBattInputRaw;
	}

	public void setiBattInputRaw(float iBattInputRaw) {
		this.iBattInputRaw = iBattInputRaw;
	}

	public float getpBattInputRaw() {
		return pBattInputRaw;
	}

	public void setpBattInputRaw(float pBattInputRaw) {
		this.pBattInputRaw = pBattInputRaw;
	}

	public int getStatObcOn() {
		return statObcOn;
	}

	public void setStatObcOn(int statObcOn) {
		this.statObcOn = statObcOn;
	}

	public int getStatObcOcf() {
		return statObcOcf;
	}

	public void setStatObcOcf(int statObcOcf) {
		this.statObcOcf = statObcOcf;
	}

	public int getBatStat() {
		return batStat;
	}

	public void setBatStat(int batStat) {
		this.batStat = batStat;
	}

	public float getBatTemp2Raw() {
		return batTemp2Raw;
	}

	public void setBatTemp2Raw(float batTemp2Raw) {
		this.batTemp2Raw = batTemp2Raw;
	}

	public int getVoltVd0() {
		return voltVd0;
	}

	public void setVoltVd0(int voltVd0) {
		this.voltVd0 = voltVd0;
	}

	public int getVoltVd1() {
		return voltVd1;
	}

	public void setVoltVd1(int voltVd1) {
		this.voltVd1 = voltVd1;
	}

	public int getVoltVd2() {
		return voltVd2;
	}

	public void setVoltVd2(int voltVd2) {
		this.voltVd2 = voltVd2;
	}

	public float getvObc00() {
		return vObc00;
	}

	public void setvObc00(float vObc00) {
		this.vObc00 = vObc00;
	}

	public float getiObc00() {
		return iObc00;
	}

	public void setiObc00(float iObc00) {
		this.iObc00 = iObc00;
	}

	public float getpObc00() {
		return pObc00;
	}

	public void setpObc00(float pObc00) {
		this.pObc00 = pObc00;
	}

	public float getvObc01() {
		return vObc01;
	}

	public void setvObc01(float vObc01) {
		this.vObc01 = vObc01;
	}

	public float getiObc01() {
		return iObc01;
	}

	public void setiObc01(float iObc01) {
		this.iObc01 = iObc01;
	}

	public float getpObc01() {
		return pObc01;
	}

	public void setpObc01(float pObc01) {
		this.pObc01 = pObc01;
	}

	public float getvObc02() {
		return vObc02;
	}

	public void setvObc02(float vObc02) {
		this.vObc02 = vObc02;
	}

	public float getiObc02() {
		return iObc02;
	}

	public void setiObc02(float iObc02) {
		this.iObc02 = iObc02;
	}

	public float getpObc02() {
		return pObc02;
	}

	public void setpObc02(float pObc02) {
		this.pObc02 = pObc02;
	}

	public float getvObc03() {
		return vObc03;
	}

	public void setvObc03(float vObc03) {
		this.vObc03 = vObc03;
	}

	public float getiObc03() {
		return iObc03;
	}

	public void setiObc03(float iObc03) {
		this.iObc03 = iObc03;
	}

	public float getpObc03() {
		return pObc03;
	}

	public void setpObc03(float pObc03) {
		this.pObc03 = pObc03;
	}

	public float getvObc05() {
		return vObc05;
	}

	public void setvObc05(float vObc05) {
		this.vObc05 = vObc05;
	}

	public float getiObc05() {
		return iObc05;
	}

	public void setiObc05(float iObc05) {
		this.iObc05 = iObc05;
	}

	public float getpObc05() {
		return pObc05;
	}

	public void setpObc05(float pObc05) {
		this.pObc05 = pObc05;
	}

	public float getvObc06() {
		return vObc06;
	}

	public void setvObc06(float vObc06) {
		this.vObc06 = vObc06;
	}

	public float getiObc06() {
		return iObc06;
	}

	public void setiObc06(float iObc06) {
		this.iObc06 = iObc06;
	}

	public float getpObc06() {
		return pObc06;
	}

	public void setpObc06(float pObc06) {
		this.pObc06 = pObc06;
	}

	public byte[] getCc1() {
		return cc1;
	}

	public void setCc1(byte[] cc1) {
		this.cc1 = cc1;
	}

	public byte[] getCc2() {
		return cc2;
	}

	public void setCc2(byte[] cc2) {
		this.cc2 = cc2;
	}

	public byte[] getCc3() {
		return cc3;
	}

	public void setCc3(byte[] cc3) {
		this.cc3 = cc3;
	}

	public int getStatusStid() {
		return statusStid;
	}

	public void setStatusStid(int statusStid) {
		this.statusStid = statusStid;
	}

	public int getStatusIvid() {
		return statusIvid;
	}

	public void setStatusIvid(int statusIvid) {
		this.statusIvid = statusIvid;
	}

	public int getStatusRc() {
		return statusRc;
	}

	public void setStatusRc(int statusRc) {
		this.statusRc = statusRc;
	}

	public int getStatusBid() {
		return statusBid;
	}

	public void setStatusBid(int statusBid) {
		this.statusBid = statusBid;
	}

	public long getStatusCmderr() {
		return statusCmderr;
	}

	public void setStatusCmderr(long statusCmderr) {
		this.statusCmderr = statusCmderr;
	}

	public long getStatusStat() {
		return statusStat;
	}

	public void setStatusStat(long statusStat) {
		this.statusStat = statusStat;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public int getConf() {
		return conf;
	}

	public void setConf(int conf) {
		this.conf = conf;
	}

	public int getResetCause() {
		return resetCause;
	}

	public void setResetCause(int resetCause) {
		this.resetCause = resetCause;
	}

	public long getUptime() {
		return uptime;
	}

	public void setUptime(long uptime) {
		this.uptime = uptime;
	}

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	public int getRcCntPwron() {
		return rcCntPwron;
	}

	public void setRcCntPwron(int rcCntPwron) {
		this.rcCntPwron = rcCntPwron;
	}

	public int getRcCntWdg() {
		return rcCntWdg;
	}

	public void setRcCntWdg(int rcCntWdg) {
		this.rcCntWdg = rcCntWdg;
	}

	public int getRcCntCmd() {
		return rcCntCmd;
	}

	public void setRcCntCmd(int rcCntCmd) {
		this.rcCntCmd = rcCntCmd;
	}

	public int getRcCntMcu() {
		return rcCntMcu;
	}

	public void setRcCntMcu(int rcCntMcu) {
		this.rcCntMcu = rcCntMcu;
	}

	public int getRcCntEmlopo() {
		return rcCntEmlopo;
	}

	public void setRcCntEmlopo(int rcCntEmlopo) {
		this.rcCntEmlopo = rcCntEmlopo;
	}

	public int getPrevcmdElapsed() {
		return prevcmdElapsed;
	}

	public void setPrevcmdElapsed(int prevcmdElapsed) {
		this.prevcmdElapsed = prevcmdElapsed;
	}

}
