package ru.r2cloud.jradio.astrocasat;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;

import ru.r2cloud.jradio.Externalizable;
import ru.r2cloud.jradio.ax25.Header;
import ru.r2cloud.jradio.util.StreamUtils;

public class AstrocastBeacon implements Externalizable {

	private Header header;
	private NMEA0183 gpsBeacon;
	private Telemetry telemetry;

	private byte[] rawData;
	private long beginSample;
	private long beginMillis;

	@Override
	public void readExternal(byte[] data) throws IOException {
		this.rawData = data;
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		header = new Header(dis);
		byte[] strData = StreamUtils.toByteArray(dis);
		String str = new String(strData, StandardCharsets.ISO_8859_1);
		// just noticed that. No real description or spec was provided
		int endOfMessage = str.indexOf(' ');
		if (endOfMessage != -1) {
			str = str.substring(0, endOfMessage);
		}
		int endOfGpsBeacon = str.indexOf('*');
		if (endOfGpsBeacon == -1) {
			return;
		}
		try {
			gpsBeacon = new NMEA0183(str.substring(0, endOfGpsBeacon));
			telemetry = new Telemetry(str.substring(endOfGpsBeacon + 1));
		} catch (ParseException e) {
			throw new IOException(e);
		}
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public NMEA0183 getGpsBeacon() {
		return gpsBeacon;
	}

	public void setGpsBeacon(NMEA0183 gpsBeacon) {
		this.gpsBeacon = gpsBeacon;
	}

	public Telemetry getTelemetry() {
		return telemetry;
	}

	public void setTelemetry(Telemetry telemetry) {
		this.telemetry = telemetry;
	}

	public byte[] getRawData() {
		return rawData;
	}

	public void setRawData(byte[] rawData) {
		this.rawData = rawData;
	}

	public long getBeginSample() {
		return beginSample;
	}

	public void setBeginSample(long beginSample) {
		this.beginSample = beginSample;
	}

	public long getBeginMillis() {
		return beginMillis;
	}

	public void setBeginMillis(long beginMillis) {
		this.beginMillis = beginMillis;
	}

}
