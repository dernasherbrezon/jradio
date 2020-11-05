package ru.r2cloud.jradio.snet;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class SnetFrameHeaderExtension {

	private int versionNo;
	private int dfcID;
	private int extensionRfu;
	private int channelInfo;
	private boolean qos;
	private boolean pduTypeId;
	private boolean arq;
	private int controlRfu;
	private int timeTagSub;
	private int scid;
	private int seqNo;

	public SnetFrameHeaderExtension() {
		// do nothing
	}

	public SnetFrameHeaderExtension(BitInputStream bis) throws IOException {
		versionNo = bis.readUnsignedInt(2);
		dfcID = bis.readUnsignedInt(2);
		extensionRfu = bis.readUnsignedInt(4);
		channelInfo = bis.readUnsignedInt(8);
		qos = bis.readBoolean();
		pduTypeId = bis.readBoolean();
		arq = bis.readBoolean();
		controlRfu = bis.readUnsignedInt(5);
		timeTagSub = bis.readUnsignedInt(16);
		scid = bis.readUnsignedInt(10);
		seqNo = bis.readUnsignedInt(14);
	}

	public int getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(int versionNo) {
		this.versionNo = versionNo;
	}

	public int getDfcID() {
		return dfcID;
	}

	public void setDfcID(int dfcID) {
		this.dfcID = dfcID;
	}

	public int getExtensionRfu() {
		return extensionRfu;
	}

	public void setExtensionRfu(int extensionRfu) {
		this.extensionRfu = extensionRfu;
	}

	public int getChannelInfo() {
		return channelInfo;
	}

	public void setChannelInfo(int channelInfo) {
		this.channelInfo = channelInfo;
	}

	public boolean isQos() {
		return qos;
	}

	public void setQos(boolean qos) {
		this.qos = qos;
	}

	public boolean isPduTypeId() {
		return pduTypeId;
	}

	public void setPduTypeId(boolean pduTypeId) {
		this.pduTypeId = pduTypeId;
	}

	public boolean isArq() {
		return arq;
	}

	public void setArq(boolean arq) {
		this.arq = arq;
	}

	public int getControlRfu() {
		return controlRfu;
	}

	public void setControlRfu(int controlRfu) {
		this.controlRfu = controlRfu;
	}

	public int getTimeTagSub() {
		return timeTagSub;
	}

	public void setTimeTagSub(int timeTagSub) {
		this.timeTagSub = timeTagSub;
	}

	public int getScid() {
		return scid;
	}

	public void setScid(int scid) {
		this.scid = scid;
	}

	public int getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}

}
