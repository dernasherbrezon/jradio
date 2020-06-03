package ru.r2cloud.jradio.uwe4;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.ax25.Header;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Uwe4Beacon extends Beacon {

	private Header header;

	private boolean routeSet;
	private boolean size1;
	private boolean abuf;
	private boolean buf;
	private boolean apiSet;
	private boolean refSet;
	private boolean pidSet;
	private boolean api16b;
	private boolean size2;
	private boolean time1;
	private boolean time2;
	private boolean metaSet;
	private boolean crcSet;
	private boolean sgnSet;

	private int packetId;
	private int fromSystemId;
	private int fromSubSystemId;
	private int toSystemId;
	private int toSubSystemId;
	private int api;
	private int payloadSize;

	private Telemetry telemetry;
	private byte[] unknownPayload;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		header = new Header(dis);
		LittleEndianDataInputStream ldis = new LittleEndianDataInputStream(dis);
		int raw = ldis.readUnsignedByte();
		routeSet = ((raw >> 6) & 0x1) > 0;
		size1 = ((raw >> 5) & 0x1) > 0;
		abuf = ((raw >> 4) & 0x1) > 0;
		buf = ((raw >> 3) & 0x1) > 0;
		apiSet = ((raw >> 2) & 0x1) > 0;
		refSet = ((raw >> 1) & 0x1) > 0;
		pidSet = (raw & 0x1) > 0;

		raw = ldis.readUnsignedByte();
		api16b = ((raw >> 6) & 0x1) > 0;
		size2 = ((raw >> 5) & 0x1) > 0;
		time1 = ((raw >> 4) & 0x1) > 0;
		time2 = ((raw >> 3) & 0x1) > 0;
		metaSet = ((raw >> 2) & 0x1) > 0;
		crcSet = ((raw >> 1) & 0x1) > 0;
		sgnSet = (raw & 0x1) > 0;

		packetId = ldis.readUnsignedShort();

		fromSystemId = ldis.readUnsignedByte();
		fromSubSystemId = ldis.readUnsignedByte();
		toSystemId = ldis.readUnsignedByte();
		toSubSystemId = ldis.readUnsignedByte();
		api = ldis.readUnsignedByte();
		payloadSize = ldis.readUnsignedByte();

		if (api != 14) {
			unknownPayload = new byte[ldis.available()];
			ldis.readFully(unknownPayload);
			return;
		}
		telemetry = new Telemetry(ldis);
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public boolean isRouteSet() {
		return routeSet;
	}

	public void setRouteSet(boolean routeSet) {
		this.routeSet = routeSet;
	}

	public boolean isSize1() {
		return size1;
	}

	public void setSize1(boolean size1) {
		this.size1 = size1;
	}

	public boolean isAbuf() {
		return abuf;
	}

	public void setAbuf(boolean abuf) {
		this.abuf = abuf;
	}

	public boolean isBuf() {
		return buf;
	}

	public void setBuf(boolean buf) {
		this.buf = buf;
	}

	public boolean isApiSet() {
		return apiSet;
	}

	public void setApiSet(boolean apiSet) {
		this.apiSet = apiSet;
	}

	public boolean isRefSet() {
		return refSet;
	}

	public void setRefSet(boolean refSet) {
		this.refSet = refSet;
	}

	public boolean isPidSet() {
		return pidSet;
	}

	public void setPidSet(boolean pidSet) {
		this.pidSet = pidSet;
	}

	public boolean isApi16b() {
		return api16b;
	}

	public void setApi16b(boolean api16b) {
		this.api16b = api16b;
	}

	public boolean isSize2() {
		return size2;
	}

	public void setSize2(boolean size2) {
		this.size2 = size2;
	}

	public boolean isTime1() {
		return time1;
	}

	public void setTime1(boolean time1) {
		this.time1 = time1;
	}

	public boolean isTime2() {
		return time2;
	}

	public void setTime2(boolean time2) {
		this.time2 = time2;
	}

	public boolean isMetaSet() {
		return metaSet;
	}

	public void setMetaSet(boolean metaSet) {
		this.metaSet = metaSet;
	}

	public boolean isCrcSet() {
		return crcSet;
	}

	public void setCrcSet(boolean crcSet) {
		this.crcSet = crcSet;
	}

	public boolean isSgnSet() {
		return sgnSet;
	}

	public void setSgnSet(boolean sgnSet) {
		this.sgnSet = sgnSet;
	}

	public int getPacketId() {
		return packetId;
	}

	public void setPacketId(int packetId) {
		this.packetId = packetId;
	}

	public int getFromSystemId() {
		return fromSystemId;
	}

	public void setFromSystemId(int fromSystemId) {
		this.fromSystemId = fromSystemId;
	}

	public int getFromSubSystemId() {
		return fromSubSystemId;
	}

	public void setFromSubSystemId(int fromSubSystemId) {
		this.fromSubSystemId = fromSubSystemId;
	}

	public int getToSystemId() {
		return toSystemId;
	}

	public void setToSystemId(int toSystemId) {
		this.toSystemId = toSystemId;
	}

	public int getToSubSystemId() {
		return toSubSystemId;
	}

	public void setToSubSystemId(int toSubSystemId) {
		this.toSubSystemId = toSubSystemId;
	}

	public int getApi() {
		return api;
	}

	public void setApi(int api) {
		this.api = api;
	}

	public int getPayloadSize() {
		return payloadSize;
	}

	public void setPayloadSize(int payloadSize) {
		this.payloadSize = payloadSize;
	}

	public byte[] getUnknownPayload() {
		return unknownPayload;
	}

	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
	}

	public Telemetry getTelemetry() {
		return telemetry;
	}

	public void setTelemetry(Telemetry telemetry) {
		this.telemetry = telemetry;
	}
}
