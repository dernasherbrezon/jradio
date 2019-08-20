package ru.r2cloud.jradio.aistechsat3;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class PayloadBeacon {

	private DataFieldMeta curMeta;
	private int cur1v2;
	private int cur2v5;
	private int cur3v3Fpga;
	private int cur3v3Adc;
	private int cur5v0Board;
	private int cur3v3Board;
	private int cur3v3Sd;
	private int avgFps10sec;
	private int avgFps1min;
	private int avgFps5min;
	private long planeCount;
	private long frameCount;
	private long crcCorrected;
	private long lastIcao24;
	private float lastLat;
	private float lastLon;
	private long lastAlt;
	private long lastTs;
	private long bootCount;
	private int bootCause;
	private long currentTime;
	private long totFrames;
	private long totPlanes;
	private long totCrcCor;
	private long fpgaCrcCnt;
	private short coreTemp;
	private short softAdcTemp;
	private short[] femTemp;
	private short[] femAdcTemp;
	private int coreVcc;
	private int vccAux;
	private int vccBram;
	private int vccpInt;
	private int vccpAux;
	private int vccOddr;
	private int vreFp;
	private int vreFn;
	private long unixTime;
	private int[] fem1v3b;
	private int[] femlna1ma;
	private int[] femlna2ma;
	private int[] fem1v3ama;
	private int[] fem1v8ama;
	private int[] fem1v3a;
	private int[] fem1v8;
	private int[] fem1v3bma;
	private long uptime;
	private long[] loads;
	private long freeRam;
	private int procs;
	private int ps1v8mw;
	private int ps1vmw;
	private int ps3v3mw;
	private int pl1v8mw;
	private int pl1vmw;
	private int ram1v35mw;
	private int pl3v3mw;
	private int softvin7;
	private long bootCount2;

	public PayloadBeacon() {
		// do nothing
	}

	public PayloadBeacon(DataInputStream dis) throws IOException {
		curMeta = new DataFieldMeta(dis);
		cur1v2 = dis.readUnsignedShort();
		cur2v5 = dis.readUnsignedShort();
		cur3v3Fpga = dis.readUnsignedShort();
		cur3v3Adc = dis.readUnsignedShort();
		cur5v0Board = dis.readUnsignedShort();
		cur3v3Board = dis.readUnsignedShort();
		cur3v3Sd = dis.readUnsignedShort();
		avgFps10sec = dis.readUnsignedShort();
		avgFps1min = dis.readUnsignedShort();
		avgFps5min = dis.readUnsignedShort();
		planeCount = StreamUtils.readUnsignedInt(dis);
		frameCount = StreamUtils.readUnsignedInt(dis);
		crcCorrected = StreamUtils.readUnsignedInt(dis);
		lastIcao24 = StreamUtils.readUnsignedInt(dis);
		lastLat = Float.intBitsToFloat(dis.readInt());
		lastLon = Float.intBitsToFloat(dis.readInt());
		lastAlt = StreamUtils.readUnsignedInt(dis);
		lastTs = StreamUtils.readUnsignedInt(dis);
		bootCount = StreamUtils.readUnsignedInt(dis);
		bootCause = dis.readUnsignedShort();
		currentTime = StreamUtils.readUnsignedInt(dis);
		totFrames = StreamUtils.readUnsignedInt(dis);
		totPlanes = StreamUtils.readUnsignedInt(dis);
		totCrcCor = StreamUtils.readUnsignedInt(dis);
		fpgaCrcCnt = StreamUtils.readUnsignedInt(dis);
		coreTemp = dis.readShort();
		softAdcTemp = dis.readShort();
		femTemp = StreamUtils.readShortArray(dis, 3);
		femAdcTemp = StreamUtils.readShortArray(dis, 3);
		coreVcc = dis.readUnsignedShort();
		vccAux = dis.readUnsignedShort();
		vccBram = dis.readUnsignedShort();
		vccpInt = dis.readUnsignedShort();
		vccpAux = dis.readUnsignedShort();
		vccOddr = dis.readUnsignedShort();
		vreFp = dis.readUnsignedShort();
		vreFn = dis.readUnsignedShort();
		unixTime = StreamUtils.readUnsignedInt(dis);
		fem1v3b = StreamUtils.readUnsignedShortArray(dis, 3);
		femlna1ma = StreamUtils.readUnsignedShortArray(dis, 3);
		femlna2ma = StreamUtils.readUnsignedShortArray(dis, 3);
		fem1v3ama = StreamUtils.readUnsignedShortArray(dis, 3);
		fem1v8ama = StreamUtils.readUnsignedShortArray(dis, 3);
		fem1v3a = StreamUtils.readUnsignedShortArray(dis, 3);
		fem1v8 = StreamUtils.readUnsignedShortArray(dis, 3);
		fem1v3bma = StreamUtils.readUnsignedShortArray(dis, 3);
		uptime = StreamUtils.readUnsignedInt(dis);
		loads = StreamUtils.readUnsignedIntArray(dis, 3);
		freeRam = StreamUtils.readUnsignedInt(dis);
		procs = dis.readUnsignedShort();
		ps1v8mw = dis.readUnsignedShort();
		ps1vmw = dis.readUnsignedShort();
		ps3v3mw = dis.readUnsignedShort();
		pl1v8mw = dis.readUnsignedShort();
		pl1vmw = dis.readUnsignedShort();
		ram1v35mw = dis.readUnsignedShort();
		pl3v3mw = dis.readUnsignedShort();
		softvin7 = dis.readUnsignedShort();
		bootCount2 = StreamUtils.readUnsignedInt(dis);
	}

	public int getCur1v2() {
		return cur1v2;
	}

	public void setCur1v2(int cur1v2) {
		this.cur1v2 = cur1v2;
	}

	public int getCur2v5() {
		return cur2v5;
	}

	public void setCur2v5(int cur2v5) {
		this.cur2v5 = cur2v5;
	}

	public int getCur3v3Fpga() {
		return cur3v3Fpga;
	}

	public void setCur3v3Fpga(int cur3v3Fpga) {
		this.cur3v3Fpga = cur3v3Fpga;
	}

	public int getCur3v3Adc() {
		return cur3v3Adc;
	}

	public void setCur3v3Adc(int cur3v3Adc) {
		this.cur3v3Adc = cur3v3Adc;
	}

	public int getCur5v0Board() {
		return cur5v0Board;
	}

	public void setCur5v0Board(int cur5v0Board) {
		this.cur5v0Board = cur5v0Board;
	}

	public int getCur3v3Board() {
		return cur3v3Board;
	}

	public void setCur3v3Board(int cur3v3Board) {
		this.cur3v3Board = cur3v3Board;
	}

	public int getCur3v3Sd() {
		return cur3v3Sd;
	}

	public void setCur3v3Sd(int cur3v3Sd) {
		this.cur3v3Sd = cur3v3Sd;
	}

	public int getAvgFps10sec() {
		return avgFps10sec;
	}

	public void setAvgFps10sec(int avgFps10sec) {
		this.avgFps10sec = avgFps10sec;
	}

	public int getAvgFps1min() {
		return avgFps1min;
	}

	public void setAvgFps1min(int avgFps1min) {
		this.avgFps1min = avgFps1min;
	}

	public int getAvgFps5min() {
		return avgFps5min;
	}

	public void setAvgFps5min(int avgFps5min) {
		this.avgFps5min = avgFps5min;
	}

	public long getPlaneCount() {
		return planeCount;
	}

	public void setPlaneCount(long planeCount) {
		this.planeCount = planeCount;
	}

	public long getFrameCount() {
		return frameCount;
	}

	public void setFrameCount(long frameCount) {
		this.frameCount = frameCount;
	}

	public long getCrcCorrected() {
		return crcCorrected;
	}

	public void setCrcCorrected(long crcCorrected) {
		this.crcCorrected = crcCorrected;
	}

	public long getLastIcao24() {
		return lastIcao24;
	}

	public void setLastIcao24(long lastIcao24) {
		this.lastIcao24 = lastIcao24;
	}

	public float getLastLat() {
		return lastLat;
	}

	public void setLastLat(float lastLat) {
		this.lastLat = lastLat;
	}

	public float getLastLon() {
		return lastLon;
	}

	public void setLastLon(float lastLon) {
		this.lastLon = lastLon;
	}

	public long getLastAlt() {
		return lastAlt;
	}

	public void setLastAlt(long lastAlt) {
		this.lastAlt = lastAlt;
	}

	public long getLastTs() {
		return lastTs;
	}

	public void setLastTs(long lastTs) {
		this.lastTs = lastTs;
	}

	public long getBootCount() {
		return bootCount;
	}

	public void setBootCount(long bootCount) {
		this.bootCount = bootCount;
	}

	public int getBootCause() {
		return bootCause;
	}

	public void setBootCause(int bootCause) {
		this.bootCause = bootCause;
	}

	public long getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(long currentTime) {
		this.currentTime = currentTime;
	}

	public long getTotFrames() {
		return totFrames;
	}

	public void setTotFrames(long totFrames) {
		this.totFrames = totFrames;
	}

	public long getTotPlanes() {
		return totPlanes;
	}

	public void setTotPlanes(long totPlanes) {
		this.totPlanes = totPlanes;
	}

	public long getTotCrcCor() {
		return totCrcCor;
	}

	public void setTotCrcCor(long totCrcCor) {
		this.totCrcCor = totCrcCor;
	}

	public long getFpgaCrcCnt() {
		return fpgaCrcCnt;
	}

	public void setFpgaCrcCnt(long fpgaCrcCnt) {
		this.fpgaCrcCnt = fpgaCrcCnt;
	}

	public short getCoreTemp() {
		return coreTemp;
	}

	public void setCoreTemp(short coreTemp) {
		this.coreTemp = coreTemp;
	}

	public short getSoftAdcTemp() {
		return softAdcTemp;
	}

	public void setSoftAdcTemp(short softAdcTemp) {
		this.softAdcTemp = softAdcTemp;
	}

	public short[] getFemTemp() {
		return femTemp;
	}

	public void setFemTemp(short[] femTemp) {
		this.femTemp = femTemp;
	}

	public short[] getFemAdcTemp() {
		return femAdcTemp;
	}

	public void setFemAdcTemp(short[] femAdcTemp) {
		this.femAdcTemp = femAdcTemp;
	}

	public int getCoreVcc() {
		return coreVcc;
	}

	public void setCoreVcc(int coreVcc) {
		this.coreVcc = coreVcc;
	}

	public int getVccAux() {
		return vccAux;
	}

	public void setVccAux(int vccAux) {
		this.vccAux = vccAux;
	}

	public int getVccBram() {
		return vccBram;
	}

	public void setVccBram(int vccBram) {
		this.vccBram = vccBram;
	}

	public int getVccpInt() {
		return vccpInt;
	}

	public void setVccpInt(int vccpInt) {
		this.vccpInt = vccpInt;
	}

	public int getVccpAux() {
		return vccpAux;
	}

	public void setVccpAux(int vccpAux) {
		this.vccpAux = vccpAux;
	}

	public int getVccOddr() {
		return vccOddr;
	}

	public void setVccOddr(int vccOddr) {
		this.vccOddr = vccOddr;
	}

	public int getVreFp() {
		return vreFp;
	}

	public void setVreFp(int vreFp) {
		this.vreFp = vreFp;
	}

	public int getVreFn() {
		return vreFn;
	}

	public void setVreFn(int vreFn) {
		this.vreFn = vreFn;
	}

	public long getUnixTime() {
		return unixTime;
	}

	public void setUnixTime(long unixTime) {
		this.unixTime = unixTime;
	}

	public int[] getFem1v3b() {
		return fem1v3b;
	}

	public void setFem1v3b(int[] fem1v3b) {
		this.fem1v3b = fem1v3b;
	}

	public int[] getFemlna1ma() {
		return femlna1ma;
	}

	public void setFemlna1ma(int[] femlna1ma) {
		this.femlna1ma = femlna1ma;
	}

	public int[] getFemlna2ma() {
		return femlna2ma;
	}

	public void setFemlna2ma(int[] femlna2ma) {
		this.femlna2ma = femlna2ma;
	}

	public int[] getFem1v3ama() {
		return fem1v3ama;
	}

	public void setFem1v3ama(int[] fem1v3ama) {
		this.fem1v3ama = fem1v3ama;
	}

	public int[] getFem1v8ama() {
		return fem1v8ama;
	}

	public void setFem1v8ama(int[] fem1v8ama) {
		this.fem1v8ama = fem1v8ama;
	}

	public int[] getFem1v3a() {
		return fem1v3a;
	}

	public void setFem1v3a(int[] fem1v3a) {
		this.fem1v3a = fem1v3a;
	}

	public int[] getFem1v8() {
		return fem1v8;
	}

	public void setFem1v8(int[] fem1v8) {
		this.fem1v8 = fem1v8;
	}

	public int[] getFem1v3bma() {
		return fem1v3bma;
	}

	public void setFem1v3bma(int[] fem1v3bma) {
		this.fem1v3bma = fem1v3bma;
	}

	public long getUptime() {
		return uptime;
	}

	public void setUptime(long uptime) {
		this.uptime = uptime;
	}

	public long[] getLoads() {
		return loads;
	}

	public void setLoads(long[] loads) {
		this.loads = loads;
	}

	public long getFreeRam() {
		return freeRam;
	}

	public void setFreeRam(long freeRam) {
		this.freeRam = freeRam;
	}

	public int getProcs() {
		return procs;
	}

	public void setProcs(int procs) {
		this.procs = procs;
	}

	public int getPs1v8mw() {
		return ps1v8mw;
	}

	public void setPs1v8mw(int ps1v8mw) {
		this.ps1v8mw = ps1v8mw;
	}

	public int getPs1vmw() {
		return ps1vmw;
	}

	public void setPs1vmw(int ps1vmw) {
		this.ps1vmw = ps1vmw;
	}

	public int getPs3v3mw() {
		return ps3v3mw;
	}

	public void setPs3v3mw(int ps3v3mw) {
		this.ps3v3mw = ps3v3mw;
	}

	public int getPl1v8mw() {
		return pl1v8mw;
	}

	public void setPl1v8mw(int pl1v8mw) {
		this.pl1v8mw = pl1v8mw;
	}

	public int getPl1vmw() {
		return pl1vmw;
	}

	public void setPl1vmw(int pl1vmw) {
		this.pl1vmw = pl1vmw;
	}

	public int getRam1v35mw() {
		return ram1v35mw;
	}

	public void setRam1v35mw(int ram1v35mw) {
		this.ram1v35mw = ram1v35mw;
	}

	public int getPl3v3mw() {
		return pl3v3mw;
	}

	public void setPl3v3mw(int pl3v3mw) {
		this.pl3v3mw = pl3v3mw;
	}

	public int getSoftvin7() {
		return softvin7;
	}

	public void setSoftvin7(int softvin7) {
		this.softvin7 = softvin7;
	}

	public long getBootCount2() {
		return bootCount2;
	}

	public void setBootCount2(long bootCount2) {
		this.bootCount2 = bootCount2;
	}

	public DataFieldMeta getCurMeta() {
		return curMeta;
	}
	
	public void setCurMeta(DataFieldMeta curMeta) {
		this.curMeta = curMeta;
	}
}
