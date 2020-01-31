package ru.r2cloud.jradio.astrocast;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.ax25.Header;
import ru.r2cloud.jradio.util.StreamUtils;

public class AstrocastBeacon extends Beacon {

	private static final Logger LOG = LoggerFactory.getLogger(AstrocastBeacon.class);

	private Header header;
	private NMEA0183 gpsBeacon;
	private Telemetry telemetry;

	@Override
	public void readBeacon(byte[] data) throws IOException {
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
		} catch (ParseException e) {
			LOG.info("unable to parse gps beacon: {}", e.getMessage());
		}
		try {
			telemetry = new Telemetry(str.substring(endOfGpsBeacon + 1));
		} catch (ParseException e) {
			LOG.info("unable to parse telemetry: {}", e.getMessage());
		} catch (NumberFormatException e) {
			LOG.info("unable to parse telemetry: {}", e.getMessage());
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

}
