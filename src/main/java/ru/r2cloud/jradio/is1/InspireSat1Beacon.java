package ru.r2cloud.jradio.is1;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.ax25.Ax25Beacon;
import ru.r2cloud.jradio.ccsds.PrimaryHeader;
import ru.r2cloud.jradio.ctim.SecondaryHeader;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.BitInputStream;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class InspireSat1Beacon extends Ax25Beacon {

	private PrimaryHeader primary;
	private SecondaryHeader secondary;
	private InspireSat1Telemetry telemetry;
	private byte[] unknownPayload;

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		BitInputStream bis = new BitInputStream(dis);
		primary = new PrimaryHeader(bis);
		if (primary.isSecondaryHeader()) {
			secondary = new SecondaryHeader(dis);
		}
		if (primary.getApplicationProcessId() == 0x01) {
			telemetry = new InspireSat1Telemetry(new LittleEndianDataInputStream(dis));
		} else {
			unknownPayload = new byte[dis.available()];
			dis.readFully(unknownPayload);
		}
	}

	public PrimaryHeader getPrimary() {
		return primary;
	}

	public void setPrimary(PrimaryHeader primary) {
		this.primary = primary;
	}

	public SecondaryHeader getSecondary() {
		return secondary;
	}

	public void setSecondary(SecondaryHeader secondary) {
		this.secondary = secondary;
	}

	public InspireSat1Telemetry getTelemetry() {
		return telemetry;
	}

	public void setTelemetry(InspireSat1Telemetry telemetry) {
		this.telemetry = telemetry;
	}

	public byte[] getUnknownPayload() {
		return unknownPayload;
	}

	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
	}

}
