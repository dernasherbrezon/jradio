package ru.r2cloud.jradio.vzlusat;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.csp.CspBeacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Vzlusat2Beacon extends CspBeacon {

	private Vzlusat2Drop drop;
	private Vzlusat2Telemetry telemetry;
	private byte[] unknownPayload;

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		int cmd = dis.readUnsignedByte();
		if (header.getSource() == 1 && header.getDestination() == 26 && header.getSourcePort() == 18 && header.getDestinationPort() == 18) {
			switch (cmd) {
			case 0x56:
				telemetry = new Vzlusat2Telemetry(dis);
				return;
			case 0x03:
				drop = new Vzlusat2Drop(dis);
				return;
			default:
				break;
			}
		}
		unknownPayload = new byte[dis.available()];
		dis.readFully(unknownPayload);
	}

	public Vzlusat2Drop getDrop() {
		return drop;
	}

	public void setDrop(Vzlusat2Drop drop) {
		this.drop = drop;
	}

	public Vzlusat2Telemetry getTelemetry() {
		return telemetry;
	}

	public void setTelemetry(Vzlusat2Telemetry telemetry) {
		this.telemetry = telemetry;
	}

	public byte[] getUnknownPayload() {
		return unknownPayload;
	}

	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
	}

}
