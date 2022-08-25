package ru.r2cloud.jradio.cute.fsw;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class FswRadio {

	private long sdMinuteCur;
	private long sdMinuteMin;
	private long sdMinuteMinFsw;
	private long sdMinuteMinSoh;
	private long sdMinuteMinLine;
	private long sdMinuteMinTbl;
	private long sdMinuteMinPay;
	private int sdPercentUsedTotal;
	private int sdPercentUsedFsw;
	private int sdPercentUsedSoh;
	private int sdPercentUsedLine;
	private int sdPercentUsedTbl;
	private int sdPercentUsedPay;
	private int sdReductionFlags;
	private boolean sdOk;
	private int sdFaultCount;
	private MountStatus priMountStatus;
	private MountStatus redMountStatus;
	private float sdrTxTxFreq;
	private long sdrTxTxSymRate;
	private long sdrTxTxFrames;
	private long sdrTxTxBytes;
	private TxMod sdrTxTxMod;
	private TxCode sdrTxTxCode;
	private TxPcm sdrTxTxPcm;
	private boolean sdrTxTx;
	private int sdrTxTxPower;
	private int sdrTxTemp;
	private boolean sdrTxCommError;
	private int sqChannel;
	private int sqChecksum1;
	private int sqChecksum2;
	private int sqChecksum3;
	private int sqWdtCount;
	private int sqTrapCount;
	private int sqTxPllCount;
	private boolean sqTxLockStat;
	private int sqTemp;
	private boolean sqEchoStat;
	private boolean sqAfcStat;
	private boolean sqSwdStat;
	private ReadbackStat sqReadbackStat;

	public FswRadio() {
		// do nothing
	}

	public FswRadio(DataInputStream dis) throws IOException {
		sdMinuteCur = StreamUtils.readUnsignedInt(dis);
		sdMinuteMin = StreamUtils.readUnsignedInt(dis);
		sdMinuteMinFsw = StreamUtils.readUnsignedInt(dis);
		sdMinuteMinSoh = StreamUtils.readUnsignedInt(dis);
		sdMinuteMinLine = StreamUtils.readUnsignedInt(dis);
		sdMinuteMinTbl = StreamUtils.readUnsignedInt(dis);
		sdMinuteMinPay = StreamUtils.readUnsignedInt(dis);
		sdPercentUsedTotal = dis.readUnsignedByte();
		sdPercentUsedFsw = dis.readUnsignedByte();
		sdPercentUsedSoh = dis.readUnsignedByte();
		sdPercentUsedLine = dis.readUnsignedByte();
		sdPercentUsedTbl = dis.readUnsignedByte();
		sdPercentUsedPay = dis.readUnsignedByte();
		sdReductionFlags = dis.readUnsignedByte();
		sdOk = dis.readBoolean();
		sdFaultCount = dis.readUnsignedByte();
		priMountStatus = MountStatus.valueOfCode(dis.readUnsignedByte());
		redMountStatus = MountStatus.valueOfCode(dis.readUnsignedByte());
		sdrTxTxFreq = StreamUtils.readUnsignedInt(dis) / 0.1f;
		sdrTxTxSymRate = StreamUtils.readUnsignedInt(dis);
		sdrTxTxFrames = StreamUtils.readUnsignedInt(dis);
		sdrTxTxBytes = StreamUtils.readUnsignedInt(dis);
		sdrTxTxMod = TxMod.valueOfCode(dis.readUnsignedByte());
		sdrTxTxCode = TxCode.valueOfCode(dis.readUnsignedByte());
		sdrTxTxPcm = TxPcm.valueOfCode(dis.readUnsignedByte());
		sdrTxTx = dis.readBoolean();
		sdrTxTxPower = dis.readUnsignedByte();
		sdrTxTemp = dis.readByte();
		sdrTxCommError = dis.readBoolean();
		sqChannel = dis.readByte();
		sqChecksum1 = dis.readUnsignedByte();
		sqChecksum2 = dis.readUnsignedByte();
		sqChecksum3 = dis.readUnsignedByte();
		sqWdtCount = dis.readUnsignedByte();
		sqTrapCount = dis.readUnsignedByte();
		sqTxPllCount = dis.readUnsignedByte();
		sqTxLockStat = dis.readBoolean();
		sqTemp = dis.readByte();
		sqEchoStat = (dis.readUnsignedByte() == 69);
		sqAfcStat = (dis.readUnsignedByte() == 70);
		sqSwdStat = (dis.readUnsignedByte() == 85);
		sqReadbackStat = ReadbackStat.valueOfCode(dis.readUnsignedByte());
	}

	public long getSdMinuteCur() {
		return sdMinuteCur;
	}

	public void setSdMinuteCur(long sdMinuteCur) {
		this.sdMinuteCur = sdMinuteCur;
	}

	public long getSdMinuteMin() {
		return sdMinuteMin;
	}

	public void setSdMinuteMin(long sdMinuteMin) {
		this.sdMinuteMin = sdMinuteMin;
	}

	public long getSdMinuteMinFsw() {
		return sdMinuteMinFsw;
	}

	public void setSdMinuteMinFsw(long sdMinuteMinFsw) {
		this.sdMinuteMinFsw = sdMinuteMinFsw;
	}

	public long getSdMinuteMinSoh() {
		return sdMinuteMinSoh;
	}

	public void setSdMinuteMinSoh(long sdMinuteMinSoh) {
		this.sdMinuteMinSoh = sdMinuteMinSoh;
	}

	public long getSdMinuteMinLine() {
		return sdMinuteMinLine;
	}

	public void setSdMinuteMinLine(long sdMinuteMinLine) {
		this.sdMinuteMinLine = sdMinuteMinLine;
	}

	public long getSdMinuteMinTbl() {
		return sdMinuteMinTbl;
	}

	public void setSdMinuteMinTbl(long sdMinuteMinTbl) {
		this.sdMinuteMinTbl = sdMinuteMinTbl;
	}

	public long getSdMinuteMinPay() {
		return sdMinuteMinPay;
	}

	public void setSdMinuteMinPay(long sdMinuteMinPay) {
		this.sdMinuteMinPay = sdMinuteMinPay;
	}

	public int getSdPercentUsedTotal() {
		return sdPercentUsedTotal;
	}

	public void setSdPercentUsedTotal(int sdPercentUsedTotal) {
		this.sdPercentUsedTotal = sdPercentUsedTotal;
	}

	public int getSdPercentUsedFsw() {
		return sdPercentUsedFsw;
	}

	public void setSdPercentUsedFsw(int sdPercentUsedFsw) {
		this.sdPercentUsedFsw = sdPercentUsedFsw;
	}

	public int getSdPercentUsedSoh() {
		return sdPercentUsedSoh;
	}

	public void setSdPercentUsedSoh(int sdPercentUsedSoh) {
		this.sdPercentUsedSoh = sdPercentUsedSoh;
	}

	public int getSdPercentUsedLine() {
		return sdPercentUsedLine;
	}

	public void setSdPercentUsedLine(int sdPercentUsedLine) {
		this.sdPercentUsedLine = sdPercentUsedLine;
	}

	public int getSdPercentUsedTbl() {
		return sdPercentUsedTbl;
	}

	public void setSdPercentUsedTbl(int sdPercentUsedTbl) {
		this.sdPercentUsedTbl = sdPercentUsedTbl;
	}

	public int getSdPercentUsedPay() {
		return sdPercentUsedPay;
	}

	public void setSdPercentUsedPay(int sdPercentUsedPay) {
		this.sdPercentUsedPay = sdPercentUsedPay;
	}

	public int getSdReductionFlags() {
		return sdReductionFlags;
	}

	public void setSdReductionFlags(int sdReductionFlags) {
		this.sdReductionFlags = sdReductionFlags;
	}

	public boolean isSdOk() {
		return sdOk;
	}

	public void setSdOk(boolean sdOk) {
		this.sdOk = sdOk;
	}

	public int getSdFaultCount() {
		return sdFaultCount;
	}

	public void setSdFaultCount(int sdFaultCount) {
		this.sdFaultCount = sdFaultCount;
	}

	public MountStatus getPriMountStatus() {
		return priMountStatus;
	}

	public void setPriMountStatus(MountStatus priMountStatus) {
		this.priMountStatus = priMountStatus;
	}

	public MountStatus getRedMountStatus() {
		return redMountStatus;
	}

	public void setRedMountStatus(MountStatus redMountStatus) {
		this.redMountStatus = redMountStatus;
	}

	public float getSdrTxTxFreq() {
		return sdrTxTxFreq;
	}

	public void setSdrTxTxFreq(float sdrTxTxFreq) {
		this.sdrTxTxFreq = sdrTxTxFreq;
	}

	public long getSdrTxTxSymRate() {
		return sdrTxTxSymRate;
	}

	public void setSdrTxTxSymRate(long sdrTxTxSymRate) {
		this.sdrTxTxSymRate = sdrTxTxSymRate;
	}

	public long getSdrTxTxFrames() {
		return sdrTxTxFrames;
	}

	public void setSdrTxTxFrames(long sdrTxTxFrames) {
		this.sdrTxTxFrames = sdrTxTxFrames;
	}

	public long getSdrTxTxBytes() {
		return sdrTxTxBytes;
	}

	public void setSdrTxTxBytes(long sdrTxTxBytes) {
		this.sdrTxTxBytes = sdrTxTxBytes;
	}

	public TxMod getSdrTxTxMod() {
		return sdrTxTxMod;
	}

	public void setSdrTxTxMod(TxMod sdrTxTxMod) {
		this.sdrTxTxMod = sdrTxTxMod;
	}

	public TxCode getSdrTxTxCode() {
		return sdrTxTxCode;
	}

	public void setSdrTxTxCode(TxCode sdrTxTxCode) {
		this.sdrTxTxCode = sdrTxTxCode;
	}

	public TxPcm getSdrTxTxPcm() {
		return sdrTxTxPcm;
	}

	public void setSdrTxTxPcm(TxPcm sdrTxTxPcm) {
		this.sdrTxTxPcm = sdrTxTxPcm;
	}

	public boolean isSdrTxTx() {
		return sdrTxTx;
	}

	public void setSdrTxTx(boolean sdrTxTx) {
		this.sdrTxTx = sdrTxTx;
	}

	public int getSdrTxTxPower() {
		return sdrTxTxPower;
	}

	public void setSdrTxTxPower(int sdrTxTxPower) {
		this.sdrTxTxPower = sdrTxTxPower;
	}

	public int getSdrTxTemp() {
		return sdrTxTemp;
	}

	public void setSdrTxTemp(int sdrTxTemp) {
		this.sdrTxTemp = sdrTxTemp;
	}

	public boolean isSdrTxCommError() {
		return sdrTxCommError;
	}

	public void setSdrTxCommError(boolean sdrTxCommError) {
		this.sdrTxCommError = sdrTxCommError;
	}

	public int getSqChannel() {
		return sqChannel;
	}

	public void setSqChannel(int sqChannel) {
		this.sqChannel = sqChannel;
	}

	public int getSqChecksum1() {
		return sqChecksum1;
	}

	public void setSqChecksum1(int sqChecksum1) {
		this.sqChecksum1 = sqChecksum1;
	}

	public int getSqChecksum2() {
		return sqChecksum2;
	}

	public void setSqChecksum2(int sqChecksum2) {
		this.sqChecksum2 = sqChecksum2;
	}

	public int getSqChecksum3() {
		return sqChecksum3;
	}

	public void setSqChecksum3(int sqChecksum3) {
		this.sqChecksum3 = sqChecksum3;
	}

	public int getSqWdtCount() {
		return sqWdtCount;
	}

	public void setSqWdtCount(int sqWdtCount) {
		this.sqWdtCount = sqWdtCount;
	}

	public int getSqTrapCount() {
		return sqTrapCount;
	}

	public void setSqTrapCount(int sqTrapCount) {
		this.sqTrapCount = sqTrapCount;
	}

	public int getSqTxPllCount() {
		return sqTxPllCount;
	}

	public void setSqTxPllCount(int sqTxPllCount) {
		this.sqTxPllCount = sqTxPllCount;
	}

	public boolean isSqTxLockStat() {
		return sqTxLockStat;
	}

	public void setSqTxLockStat(boolean sqTxLockStat) {
		this.sqTxLockStat = sqTxLockStat;
	}

	public int getSqTemp() {
		return sqTemp;
	}

	public void setSqTemp(int sqTemp) {
		this.sqTemp = sqTemp;
	}

	public boolean isSqEchoStat() {
		return sqEchoStat;
	}

	public void setSqEchoStat(boolean sqEchoStat) {
		this.sqEchoStat = sqEchoStat;
	}

	public boolean isSqAfcStat() {
		return sqAfcStat;
	}

	public void setSqAfcStat(boolean sqAfcStat) {
		this.sqAfcStat = sqAfcStat;
	}

	public boolean isSqSwdStat() {
		return sqSwdStat;
	}

	public void setSqSwdStat(boolean sqSwdStat) {
		this.sqSwdStat = sqSwdStat;
	}

	public ReadbackStat getSqReadbackStat() {
		return sqReadbackStat;
	}

	public void setSqReadbackStat(ReadbackStat sqReadbackStat) {
		this.sqReadbackStat = sqReadbackStat;
	}

}
