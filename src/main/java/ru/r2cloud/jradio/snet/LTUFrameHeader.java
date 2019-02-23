package ru.r2cloud.jradio.snet;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class LTUFrameHeader {

	private int srcId;
	private int dstId;
	private int frCntTx;
	private int frCntRx;
	private int snr;
	private int aiTypeSrc;
	private int aiTypeDst;
	private int dfcId;
	private boolean caller;
	private boolean arq;
	private boolean pduTypeId;
	private boolean bchRq;
	private boolean hailing;
	private boolean udf11;
	private int pduLength;
	private int crc13;
	private int crc5;

	public LTUFrameHeader(byte[] data) throws IOException {
		BitInputStream bis = new BitInputStream(data);
		srcId = bis.readUnsignedInt(7);
		dstId = bis.readUnsignedInt(7);
		frCntTx = bis.readUnsignedInt(4);
		frCntRx = bis.readUnsignedInt(4);
		snr = bis.readUnsignedInt(4);
		aiTypeSrc = bis.readUnsignedInt(4);
		aiTypeDst = bis.readUnsignedInt(4);
		dfcId = bis.readUnsignedInt(2);
		caller = bis.readBoolean();
		arq = bis.readBoolean();
		pduTypeId = bis.readBoolean();
		bchRq = bis.readBoolean();
		hailing = bis.readBoolean();
		udf11 = bis.readBoolean();
		pduLength = bis.readUnsignedInt(10);
		crc13 = bis.readUnsignedInt(13);
		crc5 = bis.readUnsignedInt(5);
	}

	public int getSrcId() {
		return srcId;
	}

	public void setSrcId(int srcId) {
		this.srcId = srcId;
	}

	public int getDstId() {
		return dstId;
	}

	public void setDstId(int dstId) {
		this.dstId = dstId;
	}

	public int getFrCntTx() {
		return frCntTx;
	}

	public void setFrCntTx(int frCntTx) {
		this.frCntTx = frCntTx;
	}

	public int getFrCntRx() {
		return frCntRx;
	}

	public void setFrCntRx(int frCntRx) {
		this.frCntRx = frCntRx;
	}

	public int getSnr() {
		return snr;
	}

	public void setSnr(int snr) {
		this.snr = snr;
	}

	public int getAiTypeSrc() {
		return aiTypeSrc;
	}

	public void setAiTypeSrc(int aiTypeSrc) {
		this.aiTypeSrc = aiTypeSrc;
	}

	public int getAiTypeDst() {
		return aiTypeDst;
	}

	public void setAiTypeDst(int aiTypeDst) {
		this.aiTypeDst = aiTypeDst;
	}

	public int getDfcId() {
		return dfcId;
	}

	public void setDfcId(int dfcId) {
		this.dfcId = dfcId;
	}

	public boolean isCaller() {
		return caller;
	}

	public void setCaller(boolean caller) {
		this.caller = caller;
	}

	public boolean isArq() {
		return arq;
	}

	public void setArq(boolean arq) {
		this.arq = arq;
	}

	public boolean isPduTypeId() {
		return pduTypeId;
	}

	public void setPduTypeId(boolean pduTypeId) {
		this.pduTypeId = pduTypeId;
	}

	public boolean isBchRq() {
		return bchRq;
	}

	public void setBchRq(boolean bchRq) {
		this.bchRq = bchRq;
	}

	public boolean isHailing() {
		return hailing;
	}

	public void setHailing(boolean hailing) {
		this.hailing = hailing;
	}

	public boolean isUdf11() {
		return udf11;
	}

	public void setUdf11(boolean udf11) {
		this.udf11 = udf11;
	}

	public int getPduLength() {
		return pduLength;
	}

	public void setPduLength(int pduLength) {
		this.pduLength = pduLength;
	}

	public int getCrc13() {
		return crc13;
	}

	public void setCrc13(int crc13) {
		this.crc13 = crc13;
	}

	public int getCrc5() {
		return crc5;
	}

	public void setCrc5(int crc5) {
		this.crc5 = crc5;
	}

}
