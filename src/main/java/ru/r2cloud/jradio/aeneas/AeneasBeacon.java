package ru.r2cloud.jradio.aeneas;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.ax25.Ax25Beacon;
import ru.r2cloud.jradio.crc.Crc16Ccitt;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class AeneasBeacon extends Ax25Beacon {

	private static final byte[] CAERUS_HEADER = new byte[] { 52, 51, 52, 49, 52, 53, 53, 50, 53, 53, 53, 51 };
	private static final int BEACONLENGTH = 29;

	private AeneasTelemetry telemetry;
	private byte[] unknownPayload;

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		AsciiDataInputStream adis = new AsciiDataInputStream(dis);
		adis.skipBytes(CAERUS_HEADER.length / 2);
		int length = adis.readUnsignedShortLittleEndian();
		if (length > 200) {
			throw new UncorrectableException("invalid packet length");
		}
		byte[] data = new byte[length];
		adis.readFully(data);
		int expectedCrc = adis.readUnsignedShortLittleEndian();
		int actual = Crc16Ccitt.calculateReverse(data);
		if (expectedCrc != actual) {
			throw new UncorrectableException("crc mismatched");
		}
		if (data.length != BEACONLENGTH) {
			unknownPayload = data;
			return;
		}
		telemetry = new AeneasTelemetry(new DataInputStream(new ByteArrayInputStream(data)));
	}

	public AeneasTelemetry getTelemetry() {
		return telemetry;
	}

	public void setTelemetry(AeneasTelemetry telemetry) {
		this.telemetry = telemetry;
	}

	public byte[] getUnknownPayload() {
		return unknownPayload;
	}

	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
	}

}
