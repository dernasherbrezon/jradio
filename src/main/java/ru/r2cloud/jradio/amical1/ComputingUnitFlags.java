package ru.r2cloud.jradio.amical1;

public class ComputingUnitFlags {

	private boolean onyxOn;
	private boolean llcOnyxFault;
	private boolean llcSramFault;
	private boolean fault1v8r;
	private boolean fault1v8m;
	private boolean fault3v3v12;
	private boolean picReadyRaw;
	private boolean picReadyConv;
	private boolean picReadyCompressed;
	private boolean picReadyCompressed8;
	private boolean sdPicWriteOk;
	private boolean sdPicReadOk;
	private boolean sdGetInfoOk;
	private boolean sdEraseOk;
	private boolean sdFull;
	private boolean adcReady;

	public ComputingUnitFlags() {
		// do nothing
	}

	public ComputingUnitFlags(int value) {
		onyxOn = (value & 0x1) > 0;
		llcOnyxFault = ((value >> 1) & 0x1) > 0;
		llcSramFault = ((value >> 2) & 0x1) > 0;
		fault1v8r = ((value >> 3) & 0x1) > 0;
		fault1v8m = ((value >> 4) & 0x1) > 0;
		fault3v3v12 = ((value >> 5) & 0x1) > 0;
		picReadyRaw = ((value >> 6) & 0x1) > 0;
		picReadyConv = ((value >> 7) & 0x1) > 0;
		picReadyCompressed = ((value >> 8) & 0x1) > 0;
		picReadyCompressed8 = ((value >> 9) & 0x1) > 0;
		sdPicWriteOk = ((value >> 10) & 0x1) > 0;
		sdPicReadOk = ((value >> 11) & 0x1) > 0;
		sdGetInfoOk = ((value >> 12) & 0x1) > 0;
		sdEraseOk = ((value >> 13) & 0x1) > 0;
		sdFull = ((value >> 14) & 0x1) > 0;
		adcReady = ((value >> 15) & 0x1) > 0;
	}

	public boolean isOnyxOn() {
		return onyxOn;
	}

	public void setOnyxOn(boolean onyxOn) {
		this.onyxOn = onyxOn;
	}

	public boolean isLlcOnyxFault() {
		return llcOnyxFault;
	}

	public void setLlcOnyxFault(boolean llcOnyxFault) {
		this.llcOnyxFault = llcOnyxFault;
	}

	public boolean isLlcSramFault() {
		return llcSramFault;
	}

	public void setLlcSramFault(boolean llcSramFault) {
		this.llcSramFault = llcSramFault;
	}

	public boolean isFault1v8r() {
		return fault1v8r;
	}

	public void setFault1v8r(boolean fault1v8r) {
		this.fault1v8r = fault1v8r;
	}

	public boolean isFault1v8m() {
		return fault1v8m;
	}

	public void setFault1v8m(boolean fault1v8m) {
		this.fault1v8m = fault1v8m;
	}

	public boolean isFault3v3v12() {
		return fault3v3v12;
	}

	public void setFault3v3v12(boolean fault3v3v12) {
		this.fault3v3v12 = fault3v3v12;
	}

	public boolean isPicReadyRaw() {
		return picReadyRaw;
	}

	public void setPicReadyRaw(boolean picReadyRaw) {
		this.picReadyRaw = picReadyRaw;
	}

	public boolean isPicReadyConv() {
		return picReadyConv;
	}

	public void setPicReadyConv(boolean picReadyConv) {
		this.picReadyConv = picReadyConv;
	}

	public boolean isPicReadyCompressed() {
		return picReadyCompressed;
	}

	public void setPicReadyCompressed(boolean picReadyCompressed) {
		this.picReadyCompressed = picReadyCompressed;
	}

	public boolean isPicReadyCompressed8() {
		return picReadyCompressed8;
	}

	public void setPicReadyCompressed8(boolean picReadyCompressed8) {
		this.picReadyCompressed8 = picReadyCompressed8;
	}

	public boolean isSdPicWriteOk() {
		return sdPicWriteOk;
	}

	public void setSdPicWriteOk(boolean sdPicWriteOk) {
		this.sdPicWriteOk = sdPicWriteOk;
	}

	public boolean isSdPicReadOk() {
		return sdPicReadOk;
	}

	public void setSdPicReadOk(boolean sdPicReadOk) {
		this.sdPicReadOk = sdPicReadOk;
	}

	public boolean isSdGetInfoOk() {
		return sdGetInfoOk;
	}

	public void setSdGetInfoOk(boolean sdGetInfoOk) {
		this.sdGetInfoOk = sdGetInfoOk;
	}

	public boolean isSdEraseOk() {
		return sdEraseOk;
	}

	public void setSdEraseOk(boolean sdEraseOk) {
		this.sdEraseOk = sdEraseOk;
	}

	public boolean isSdFull() {
		return sdFull;
	}

	public void setSdFull(boolean sdFull) {
		this.sdFull = sdFull;
	}

	public boolean isAdcReady() {
		return adcReady;
	}

	public void setAdcReady(boolean adcReady) {
		this.adcReady = adcReady;
	}

}
