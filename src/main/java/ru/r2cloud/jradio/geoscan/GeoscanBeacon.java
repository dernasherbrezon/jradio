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
	private static final long GEOSCANHEADER_TYPE1 = 0x01003E01L;
	private static final long GEOSCANHEADER_TYPE2 = 0x01003E05L;

	private Header header;
	private GeoscanTelemetry telemetry;
	private GeoscanHeader geoscanHeader;
	private GeoscanAdc adc;
	private GeoscanEps eps;
	private GeoscanFakel fakel;
	private GeoscanGnss gnss;
	private GeoscanPhoto photo;
	private byte[] payload;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		long type = peakIntoUnsignedInt(data, 0);
		if (type != AX25HEADER_TYPE && type != GEOSCANHEADER_TYPE1 && type != GEOSCANHEADER_TYPE2) {
			dis.skipBytes(5);
			// peak into next 4 bytes to determine type
			type = peakIntoUnsignedInt(data, 5);
		}
		if (type == AX25HEADER_TYPE) {
			header = new Header(dis, false);
			telemetry = new GeoscanTelemetry(new LittleEndianDataInputStream(dis));
		} else if (type == GEOSCANHEADER_TYPE1 || type == GEOSCANHEADER_TYPE2) {
			LittleEndianDataInputStream ldis = new LittleEndianDataInputStream(dis);
			geoscanHeader = new GeoscanHeader(ldis);
			int commandType = ldis.readUnsignedByte();
			switch (commandType) {
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
				photo = new GeoscanPhoto(ldis);
				break;
			default:
				payload = new byte[ldis.available()];
				ldis.readFully(payload);
			}
		} else {
			// 2 - for checksum
			payload = new byte[dis.available() - 2];
			dis.readFully(payload);
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

	public GeoscanPhoto getPhoto() {
		return photo;
	}

	public void setPhoto(GeoscanPhoto photo) {
		this.photo = photo;
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
