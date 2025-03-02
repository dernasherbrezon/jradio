package ru.r2cloud.jradio.geoscan;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.ax25.Header;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class GeoscanBeacon extends Beacon {

	private static final long AX25HEADER_TYPE = 0x848A8286L;

	private Header header;
	private GeoscanTelemetry telemetry;
	private GeoscanHeader geoscanHeader;
	private GeoscanAdc adc;
	private GeoscanEps eps;
	private GeoscanFakel fakel;
	private GeoscanGnss gnss;
	private GeoscanFile file;
	private byte[] payload;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		long type = peakIntoUnsignedInt(data, 5);
		if (type == AX25HEADER_TYPE) {
			dis.skipBytes(5);
		} else {
			type = peakIntoUnsignedInt(data, 0);
		}
		if (type == AX25HEADER_TYPE) {
			header = new Header(dis, false);
			telemetry = new GeoscanTelemetry(new LittleEndianDataInputStream(dis));
		} else {
			LittleEndianDataInputStream ldis = new LittleEndianDataInputStream(dis);
			geoscanHeader = new GeoscanHeader(ldis);
			int payloadLength = geoscanHeader.getFieldSize() - 6;
			if (payloadLength < 0) {
				throw new IOException("invalid message");
			}
			if (payloadLength > dis.available()) {
				payloadLength = dis.available();
			}
			switch (geoscanHeader.getSubsystemNumber()) {
			case 0x01:
				adc = new GeoscanAdc(ldis);
				break;
			case 0x02:
				eps = new GeoscanEps(ldis);
				break;
			case 0x05:
			case 0x06:
				fakel = new GeoscanFakel(ldis);
				break;
			case 0x07:
			case 0x08:
			case 0x09:
			case 0x0A:
				gnss = new GeoscanGnss(ldis);
				break;
			case 0x0B:
				file = new GeoscanFile(ldis, payloadLength);
				break;
			default:
				payload = new byte[payloadLength];
				ldis.readFully(payload);
			}
		}
	}

	private static long peakIntoUnsignedInt(byte[] data, int offset) {
		return (((data[offset + 0] & 0xFF) << 24) | ((data[offset + 1] & 0xFF) << 16) | ((data[offset + 2] & 0xFF) << 8) | (data[offset + 3] & 0xFF)) & 0xFFFFFFFFL;
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public GeoscanTelemetry getTelemetry() {
		return telemetry;
	}

	public void setTelemetry(GeoscanTelemetry telemetry) {
		this.telemetry = telemetry;
	}

	public byte[] getPayload() {
		return payload;
	}

	public void setPayload(byte[] payload) {
		this.payload = payload;
	}

	public GeoscanHeader getGeoscanHeader() {
		return geoscanHeader;
	}

	public void setGeoscanHeader(GeoscanHeader geoscanHeader) {
		this.geoscanHeader = geoscanHeader;
	}

	public GeoscanAdc getAdc() {
		return adc;
	}

	public void setAdc(GeoscanAdc adc) {
		this.adc = adc;
	}

	public GeoscanEps getEps() {
		return eps;
	}

	public void setEps(GeoscanEps eps) {
		this.eps = eps;
	}

	public GeoscanFakel getFakel() {
		return fakel;
	}

	public void setFakel(GeoscanFakel fakel) {
		this.fakel = fakel;
	}

	public GeoscanGnss getGnss() {
		return gnss;
	}

	public void setGnss(GeoscanGnss gnss) {
		this.gnss = gnss;
	}

	public GeoscanFile getFile() {
		return file;
	}

	public void setFile(GeoscanFile file) {
		this.file = file;
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
