package ru.r2cloud.jradio.opssat;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.csp.CspBeacon;
import ru.r2cloud.jradio.csp.Header;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class OpsSatBeacon extends CspBeacon {

	private byte[] unknownPayload;
	private Telemetry telemetry;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		header = new Header(dis);
		// enforce crc32 check for opssat
		// header doesn't contain crc32=true
		readBeaconWithCrc32Check(data, dis);
	}

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		// source = 5 is the telemetry
		// according to the spec, opssat can sometimes send non-telemetry packets on UHF
		if (getHeader().getSource() == 5 && getHeader().getDestination() == 10 && dis.available() == 54) {
			telemetry = new Telemetry(dis);
			return;
		}

		// 4 is for crc-32
		unknownPayload = new byte[dis.available()];
		dis.readFully(unknownPayload);
	}

	public Telemetry getTelemetry() {
		return telemetry;
	}

	public void setTelemetry(Telemetry telemetry) {
		this.telemetry = telemetry;
	}

	public byte[] getUnknownPayload() {
		return unknownPayload;
	}

	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
	}
}
