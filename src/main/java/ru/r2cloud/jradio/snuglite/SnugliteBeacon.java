package ru.r2cloud.jradio.snuglite;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.csp.Header;
import ru.r2cloud.jradio.fec.ccsds.ReedSolomon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.StreamUtils;

public class SnugliteBeacon extends Beacon {

	private String destination;
	private int destinationSsid;
	private String source;
	private int sourceSsid;
	private int pid;
	private Header header;
	private String startId;
	private SimpleBeacon simpleBeacon;
	private FullBeacon fullbeacon;
	private byte[] payload;
	private String endId;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		destination = StreamUtils.readString(dis, 5);
		destinationSsid = dis.readUnsignedShort();
		source = StreamUtils.readString(dis, 5);
		sourceSsid = dis.readUnsignedShort();
		pid = dis.readUnsignedShort();

		byte[] body = new byte[dis.available()];
		dis.readFully(body);
		byte[] result = ReedSolomon.decode(body);
		dis = new DataInputStream(new ByteArrayInputStream(result));
		header = new Header(dis);
		switch (header.getDestinationPort()) {
		case 11:
			startId = StreamUtils.readString(dis, 6);
			simpleBeacon = new SimpleBeacon(dis);
			endId = StreamUtils.readString(dis, 4);
			break;
		case 15:
			startId = StreamUtils.readString(dis, 6);
			fullbeacon = new FullBeacon(dis);
			endId = StreamUtils.readString(dis, 4);
			break;
		default:
			payload = new byte[dis.available()];
			dis.readFully(payload);
			break;
		}
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getDestinationSsid() {
		return destinationSsid;
	}

	public void setDestinationSsid(int destinationSsid) {
		this.destinationSsid = destinationSsid;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public int getSourceSsid() {
		return sourceSsid;
	}

	public void setSourceSsid(int sourceSsid) {
		this.sourceSsid = sourceSsid;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public String getStartId() {
		return startId;
	}

	public void setStartId(String startId) {
		this.startId = startId;
	}

	public SimpleBeacon getSimpleBeacon() {
		return simpleBeacon;
	}

	public void setSimpleBeacon(SimpleBeacon simpleBeacon) {
		this.simpleBeacon = simpleBeacon;
	}

	public FullBeacon getFullbeacon() {
		return fullbeacon;
	}

	public void setFullbeacon(FullBeacon fullbeacon) {
		this.fullbeacon = fullbeacon;
	}

	public byte[] getPayload() {
		return payload;
	}

	public void setPayload(byte[] payload) {
		this.payload = payload;
	}

	public String getEndId() {
		return endId;
	}

	public void setEndId(String endId) {
		this.endId = endId;
	}

}
