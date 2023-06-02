package ru.r2cloud.jradio.selfiesat;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.crc.Crc32c;
import ru.r2cloud.jradio.csp.Header;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class SelfieSatBeacon extends Beacon {

	private Header header;
	private Housekeeping telemetry;
	private byte[] unknownPayload;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		header = new Header(dis);
		// it seems they use CSP 2.0 or CSP 2.1 version
		// that contains length field
		dis.skipBytes(2);
		if (header.isFcrc32()) {
			long actualCrc32 = Crc32c.calculate(data, Header.LENGTH + 2, data.length - Header.LENGTH - 2 - 4);
			long expectedCrc32 = ((data[data.length - 4] & 0xFFL) << 24) | ((data[data.length - 3] & 0xFFL) << 16) | ((data[data.length - 2] & 0xFFL) << 8) | (data[data.length - 1] & 0xFFL);
			if (expectedCrc32 != actualCrc32) {
				throw new UncorrectableException("crc mismatch");
			}
		}

		int remaining = dis.available();
		if (header.getSource() == 2 && header.getSourcePort() == 5) {
			try {
				dis.skipBytes(8);
				telemetry = new Housekeeping(dis);
				return;
			} catch (EOFException e) {
				// ignore EOF
			}
		}
		unknownPayload = new byte[remaining];
		System.arraycopy(data, data.length - remaining, unknownPayload, 0, unknownPayload.length);
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public Housekeeping getTelemetry() {
		return telemetry;
	}
	
	public void setTelemetry(Housekeeping telemetry) {
		this.telemetry = telemetry;
	}

	public byte[] getUnknownPayload() {
		return unknownPayload;
	}

	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
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
