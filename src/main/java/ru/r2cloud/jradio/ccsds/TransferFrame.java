package ru.r2cloud.jradio.ccsds;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.crc.Crc16CcittFalse;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.BitInputStream;

//as defined at CCSDS 132.0-B-3
public class TransferFrame extends Beacon {

	private TransferFramePrimaryHeader header;
	private TransferFrameSecondaryHeader secondaryHeader;
	private byte[] payload;
	private OperationalControlField ocf;

	private transient final boolean hasCrc;

	public TransferFrame() {
		this(false);
	}

	// crc defined statically for each spacecraft
	public TransferFrame(boolean hasCrc) {
		this.hasCrc = hasCrc;
	}

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		BitInputStream bis = new BitInputStream(dis);
		header = new TransferFramePrimaryHeader(bis);
		if (header.getFieldStatus().isSecondaryHeader()) {
			secondaryHeader = new TransferFrameSecondaryHeader(bis);
		}
		int payloadLength;
		if (header.isOcf()) {
			payloadLength = dis.available() - OperationalControlField.LENGTH;
		} else {
			payloadLength = dis.available();
		}
		if (hasCrc) {
			payloadLength -= 2;
		}
		if (payloadLength < 0) {
			throw new IOException();
		}
		byte[] payload = new byte[payloadLength];
		dis.readFully(payload);
		if (header.isOcf()) {
			ocf = new OperationalControlField(bis);
		}
		if (hasCrc) {
			int expected = dis.readUnsignedShort();
			int actual = Crc16CcittFalse.calculate(data, 0, data.length - 2);
			if (expected != actual) {
				throw new UncorrectableException("crc mismatch");
			}
		}
		try {
			// pass in only payload bytes stripped off ocf
			readBeacon(new DataInputStream(new ByteArrayInputStream(payload)));
		} catch (EOFException e) {
			this.payload = payload;
		}
	}

	@SuppressWarnings("unused")
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		payload = new byte[dis.available()];
		dis.readFully(payload);
	}

	public TransferFramePrimaryHeader getHeader() {
		return header;
	}

	public void setHeader(TransferFramePrimaryHeader header) {
		this.header = header;
	}

	public TransferFrameSecondaryHeader getSecondaryHeader() {
		return secondaryHeader;
	}

	public void setSecondaryHeader(TransferFrameSecondaryHeader secondaryHeader) {
		this.secondaryHeader = secondaryHeader;
	}

	public OperationalControlField getOcf() {
		return ocf;
	}

	public void setOcf(OperationalControlField ocf) {
		this.ocf = ocf;
	}

	@Override
	public String toString() {
		if (header == null) {
			return "null";
		} else {
			return header.toString();
		}
	}

}
