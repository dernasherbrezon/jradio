package ru.r2cloud.jradio.alsat1n;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Alsat1nBeacon extends Beacon {

	private String callsign;
	private int sequence;
	private int length;
	private int stcvrId;

	private int payloadId;
	private int messageId;
	private int responseCode;

	private byte[] unknownPayload;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		byte[] callsignBytes = new byte[5];
		dis.readFully(callsignBytes);
		callsign = new String(callsignBytes, StandardCharsets.ISO_8859_1).trim();
		if (!callsign.equalsIgnoreCase("AL1N")) {
			throw new UncorrectableException("invalid callsign");
		}
		sequence = dis.readUnsignedByte();
		length = dis.readUnsignedShort();
		stcvrId = dis.readUnsignedShort();

		payloadId = dis.readUnsignedByte();
		messageId = dis.readUnsignedByte();
		responseCode = dis.readUnsignedByte();

		unknownPayload = new byte[length - 4];
		dis.readFully(unknownPayload);
	}

	public String getCallsign() {
		return callsign;
	}

	public void setCallsign(String callsign) {
		this.callsign = callsign;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getStcvrId() {
		return stcvrId;
	}

	public void setStcvrId(int stcvrId) {
		this.stcvrId = stcvrId;
	}

	public int getPayloadId() {
		return payloadId;
	}

	public void setPayloadId(int payloadId) {
		this.payloadId = payloadId;
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public byte[] getUnknownPayload() {
		return unknownPayload;
	}

	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
	}

}
