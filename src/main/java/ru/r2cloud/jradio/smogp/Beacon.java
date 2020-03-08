package ru.r2cloud.jradio.smogp;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Beacon {

	private long timestamp;
	private String beaconMessage;

	private long validPackets;
	private int rxErrorWrongSize;
	private int rxErrorGolayFailed;
	private int rxErrorWrongSignature;
	private int rxErrorInvalidSerial;
	private long obcComTrxErrorStatistic;

	private AckInfo[] ackInfo;

	public Beacon() {
		// do nothing
	}

	public Beacon(LittleEndianDataInputStream dis) throws IOException {
		timestamp = dis.readUnsignedInt();
		byte[] messageData = new byte[80];
		dis.readFully(messageData);
		beaconMessage = new String(messageData, StandardCharsets.ISO_8859_1);
		validPackets = dis.readUnsignedInt();
		rxErrorWrongSize = dis.readUnsignedShort();
		rxErrorGolayFailed = dis.readUnsignedShort();
		rxErrorWrongSignature = dis.readUnsignedShort();
		rxErrorInvalidSerial = dis.readUnsignedShort();
		obcComTrxErrorStatistic = dis.readUnsignedInt();
		ackInfo = new AckInfo[3];
		for (int i = 0; i < ackInfo.length; i++) {
			ackInfo[i] = new AckInfo(dis);
		}
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getBeaconMessage() {
		return beaconMessage;
	}

	public void setBeaconMessage(String beaconMessage) {
		this.beaconMessage = beaconMessage;
	}

	public long getValidPackets() {
		return validPackets;
	}

	public void setValidPackets(long validPackets) {
		this.validPackets = validPackets;
	}

	public int getRxErrorWrongSize() {
		return rxErrorWrongSize;
	}

	public void setRxErrorWrongSize(int rxErrorWrongSize) {
		this.rxErrorWrongSize = rxErrorWrongSize;
	}

	public int getRxErrorGolayFailed() {
		return rxErrorGolayFailed;
	}

	public void setRxErrorGolayFailed(int rxErrorGolayFailed) {
		this.rxErrorGolayFailed = rxErrorGolayFailed;
	}

	public int getRxErrorWrongSignature() {
		return rxErrorWrongSignature;
	}

	public void setRxErrorWrongSignature(int rxErrorWrongSignature) {
		this.rxErrorWrongSignature = rxErrorWrongSignature;
	}

	public int getRxErrorInvalidSerial() {
		return rxErrorInvalidSerial;
	}

	public void setRxErrorInvalidSerial(int rxErrorInvalidSerial) {
		this.rxErrorInvalidSerial = rxErrorInvalidSerial;
	}

	public long getObcComTrxErrorStatistic() {
		return obcComTrxErrorStatistic;
	}

	public void setObcComTrxErrorStatistic(long obcComTrxErrorStatistic) {
		this.obcComTrxErrorStatistic = obcComTrxErrorStatistic;
	}

	public AckInfo[] getAckInfo() {
		return ackInfo;
	}

	public void setAckInfo(AckInfo[] ackInfo) {
		this.ackInfo = ackInfo;
	}

}
