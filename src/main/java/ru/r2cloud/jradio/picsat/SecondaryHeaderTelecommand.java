package ru.r2cloud.jradio.picsat;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class SecondaryHeaderTelecommand {

	private boolean reqAckReception;
	private boolean reqFmtReception;
	private boolean reqExeReception;
	private int telecommandId;
	private int emitterId;
	private byte[] signature;

	public SecondaryHeaderTelecommand() {
		// do nothing
	}

	public SecondaryHeaderTelecommand(BitInputStream bis) throws IOException {
		reqAckReception = bis.readBoolean();
		reqFmtReception = bis.readBoolean();
		reqExeReception = bis.readBoolean();
		telecommandId = bis.readUnsignedInt(10);
		emitterId = bis.readUnsignedInt(3);
		signature = new byte[16];
		for (int i = 0; i < signature.length; i++) {
			signature[i] = bis.readByte();
		}
	}

	public boolean isReqAckReception() {
		return reqAckReception;
	}

	public void setReqAckReception(boolean reqAckReception) {
		this.reqAckReception = reqAckReception;
	}

	public boolean isReqFmtReception() {
		return reqFmtReception;
	}

	public void setReqFmtReception(boolean reqFmtReception) {
		this.reqFmtReception = reqFmtReception;
	}

	public boolean isReqExeReception() {
		return reqExeReception;
	}

	public void setReqExeReception(boolean reqExeReception) {
		this.reqExeReception = reqExeReception;
	}

	public int getTelecommandId() {
		return telecommandId;
	}

	public void setTelecommandId(int telecommandId) {
		this.telecommandId = telecommandId;
	}

	public int getEmitterId() {
		return emitterId;
	}

	public void setEmitterId(int emitterId) {
		this.emitterId = emitterId;
	}

	public byte[] getSignature() {
		return signature;
	}

	public void setSignature(byte[] signature) {
		this.signature = signature;
	}

}
