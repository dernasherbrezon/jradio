package ru.r2cloud.jradio.meznsat;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.ax25.Ax25Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class MeznsatBeacon extends Ax25Beacon {

	private MeznsatTelemetry telemetry;
	private byte[] unknownPayload;

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		if (dis.available() != 78) {
			unknownPayload = new byte[dis.available()];
			dis.readFully(unknownPayload);
			return;
		}
		LittleEndianDataInputStream ldis = new LittleEndianDataInputStream(dis);
		telemetry = new MeznsatTelemetry(ldis);
	}

	public MeznsatTelemetry getTelemetry() {
		return telemetry;
	}

	public void setTelemetry(MeznsatTelemetry telemetry) {
		this.telemetry = telemetry;
	}

	public byte[] getUnknownPayload() {
		return unknownPayload;
	}

	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
	}

}
