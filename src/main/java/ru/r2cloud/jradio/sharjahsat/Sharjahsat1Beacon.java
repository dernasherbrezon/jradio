package ru.r2cloud.jradio.sharjahsat;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.ax25.Ax25Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Sharjahsat1Beacon extends Ax25Beacon {

	private Sharjahsat1Header sharjahsat1Header;
	private byte[] imagePayload;
	private Telemetry telemetry;

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		LittleEndianDataInputStream ldis = new LittleEndianDataInputStream(dis);
		sharjahsat1Header = new Sharjahsat1Header(ldis);
		if (sharjahsat1Header.getTmId() == 0x41) {
			imagePayload = new byte[dis.available()];
			dis.readFully(imagePayload);
		} else if (sharjahsat1Header.getTmId() == 0x50) {
			telemetry = new Telemetry(ldis);
		} else {
			super.readBeacon(dis);
		}
	}

	public Sharjahsat1Header getSharjahsat1Header() {
		return sharjahsat1Header;
	}

	public void setSharjahsat1Header(Sharjahsat1Header sharjahsat1Header) {
		this.sharjahsat1Header = sharjahsat1Header;
	}

	public byte[] getImagePayload() {
		return imagePayload;
	}

	public void setImagePayload(byte[] imagePayload) {
		this.imagePayload = imagePayload;
	}

	public Telemetry getTelemetry() {
		return telemetry;
	}

	public void setTelemetry(Telemetry telemetry) {
		this.telemetry = telemetry;
	}

}
