package ru.r2cloud.jradio.astrocast;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;

import ru.r2cloud.jradio.ax25.Ax25Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.StreamUtils;

public class AstrocastBeacon extends Ax25Beacon {

	private NMEA0183 gpsBeacon;
	private Telemetry telemetry;

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
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
