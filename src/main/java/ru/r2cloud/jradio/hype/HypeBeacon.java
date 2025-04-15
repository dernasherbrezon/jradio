package ru.r2cloud.jradio.hype;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.RawBeacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class HypeBeacon extends RawBeacon {

	private HypeTelemetry telemetry;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		int service = dis.readUnsignedByte();
		// documentation says 0xFF, but real-world data has 0x00
		if (service != 0xFF && service != 0x00) {
			super.readBeacon(data);
			return;
		}
		telemetry = new HypeTelemetry(dis);
	}

	public HypeTelemetry getTelemetry() {
		return telemetry;
	}

	public void setTelemetry(HypeTelemetry telemetry) {
		this.telemetry = telemetry;
	}

}
