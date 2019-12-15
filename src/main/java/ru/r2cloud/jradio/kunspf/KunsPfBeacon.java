package ru.r2cloud.jradio.kunspf;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.csp.Header;

public class KunsPfBeacon extends Beacon {

	private Header header;
	private NormalBeacon beacon;
	private WodBeacon wodBeacon;
	private KunsPfImageChunk imageChunk;
	private byte[] unknownPayload;

	public KunsPfBeacon() {
		// do nothing
	}

	@Override
	public void readBeacon(byte[] rawData) throws IOException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(rawData));
		header = new Header(dis);
		if (rawData.length == 138) {
			imageChunk = new KunsPfImageChunk(dis);
		} else if (rawData.length >= 92) {
			wodBeacon = new WodBeacon(dis);
		} else if (rawData.length >= 34) {
			beacon = new NormalBeacon(dis);
		} else if (dis.available() > 4) {
			unknownPayload = new byte[dis.available() - 4];
			dis.readFully(unknownPayload);
		}
		// last 4 bytes are unknown
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public NormalBeacon getBeacon() {
		return beacon;
	}

	public void setBeacon(NormalBeacon beacon) {
		this.beacon = beacon;
	}

	public WodBeacon getWodBeacon() {
		return wodBeacon;
	}

	public void setWodBeacon(WodBeacon wodBeacon) {
		this.wodBeacon = wodBeacon;
	}

	public KunsPfImageChunk getImageChunk() {
		return imageChunk;
	}

	public void setImageChunk(KunsPfImageChunk imageChunk) {
		this.imageChunk = imageChunk;
	}

}
