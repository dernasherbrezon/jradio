package ru.r2cloud.jradio.csp;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.crc.Crc32c;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class CspBeacon extends Beacon {

	protected Header header;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		header = new Header(dis);
		if (header.isFcrc32()) {
			readBeaconWithCrc32Check(data, dis);
		} else {
			readBeacon(dis);
		}
	}

	protected void readBeaconWithCrc32Check(byte[] data, DataInputStream dis) throws UncorrectableException, IOException {
		// crc32 for header + body
		long expectedCrc32 = Crc32c.calculate(data, 0, data.length - 4);
		long actualCrc32 = ((data[data.length - 4] & 0xFFL) << 24) | ((data[data.length - 3] & 0xFFL) << 16) | ((data[data.length - 2] & 0xFFL) << 8) | (data[data.length - 1] & 0xFFL);
		if (expectedCrc32 != actualCrc32) {
			throw new UncorrectableException("crc mismatch");
		}
		// cut-off crc32 from the end
		// some beacons might have "unknownPayload" field
		// which should exclude crc32
		byte[] body = new byte[dis.available() - 4];
		dis.readFully(body);
		dis = new DataInputStream(new ByteArrayInputStream(body));
		readBeacon(dis);
	}

	@SuppressWarnings("unused")
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		// do nothing
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
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
