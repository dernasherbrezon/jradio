package ru.r2cloud.jradio.astrocast;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.ccsds.OperationalControlField;
import ru.r2cloud.jradio.ccsds.TransferFramePrimaryHeader;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.BitInputStream;

public class Astrocast9k6Beacon extends Beacon {

	private static final int FEC_LENGTH_BYTES = 2;
	private TransferFramePrimaryHeader header;
	private OperationalControlField operationalControl;
	private byte[] payload;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		BitInputStream bis = new BitInputStream(dis);
		header = new TransferFramePrimaryHeader(bis);
		int payloadLength;
		if (header.isOcf()) {
			payloadLength = dis.available() - OperationalControlField.LENGTH - FEC_LENGTH_BYTES;
		} else {
			payloadLength = dis.available() - FEC_LENGTH_BYTES;
		}
		payload = new byte[payloadLength];
		bis.readFully(payload);
		if (header.isOcf()) {
			operationalControl = new OperationalControlField(bis);
		}
	}

	public OperationalControlField getOperationalControl() {
		return operationalControl;
	}

	public void setOperationalControl(OperationalControlField operationalControl) {
		this.operationalControl = operationalControl;
	}

	public byte[] getPayload() {
		return payload;
	}

	public void setPayload(byte[] payload) {
		this.payload = payload;
	}

	public TransferFramePrimaryHeader getHeader() {
		return header;
	}

	public void setHeader(TransferFramePrimaryHeader header) {
		this.header = header;
	}
}
