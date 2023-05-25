package ru.r2cloud.jradio.cirbe;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class SohRadio {

	private long sdMinuteCur;
	private int sdPercentUsedTotal;
	private boolean sdOk;
	private int sdFaultCount;
	private byte sqChannel;
	private int sqTrapCount;
	private byte sqTemp;

	private long sdrTxTxFrames;
	private int sdrTxTxPower;
	private byte sdrTxTemp;
	private boolean sdrTxCommError;

	public SohRadio() {
		// do nothing
	}

	public SohRadio(DataInputStream dis) throws IOException {
		sdMinuteCur = StreamUtils.readUnsignedInt(dis);
		sdPercentUsedTotal = dis.readUnsignedByte();
		sdOk = dis.readBoolean();
		sdFaultCount = dis.readUnsignedByte();

		sqChannel = dis.readByte();
		sqTrapCount = dis.readUnsignedByte();
		sqTemp = dis.readByte();

		sdrTxTxFrames = StreamUtils.readUnsignedInt(dis);
		sdrTxTxPower = dis.readUnsignedByte();
		sdrTxTemp = dis.readByte();
		sdrTxCommError = dis.readBoolean();
	}

	public long getSdMinuteCur() {
		return sdMinuteCur;
	}

	public void setSdMinuteCur(long sdMinuteCur) {
		this.sdMinuteCur = sdMinuteCur;
	}

	public int getSdPercentUsedTotal() {
		return sdPercentUsedTotal;
	}

	public void setSdPercentUsedTotal(int sdPercentUsedTotal) {
		this.sdPercentUsedTotal = sdPercentUsedTotal;
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

	public long getSdrTxTxFrames() {
		return sdrTxTxFrames;
	}

	public void setSdrTxTxFrames(long sdrTxTxFrames) {
		this.sdrTxTxFrames = sdrTxTxFrames;
	}

	public boolean isSdrTxCommError() {
		return sdrTxCommError;
	}

	public void setSdrTxCommError(boolean sdrTxCommError) {
		this.sdrTxCommError = sdrTxCommError;
	}

	public byte getSqChannel() {
		return sqChannel;
	}

	public void setSqChannel(byte sqChannel) {
		this.sqChannel = sqChannel;
	}

	public int getSqTrapCount() {
		return sqTrapCount;
	}

	public void setSqTrapCount(int sqTrapCount) {
		this.sqTrapCount = sqTrapCount;
	}

	public byte getSqTemp() {
		return sqTemp;
	}

	public void setSqTemp(byte sqTemp) {
		this.sqTemp = sqTemp;
	}

	public int getSdrTxTxPower() {
		return sdrTxTxPower;
	}

	public void setSdrTxTxPower(int sdrTxTxPower) {
		this.sdrTxTxPower = sdrTxTxPower;
	}

	public byte getSdrTxTemp() {
		return sdrTxTemp;
	}

	public void setSdrTxTemp(byte sdrTxTemp) {
		this.sdrTxTemp = sdrTxTemp;
	}

}
