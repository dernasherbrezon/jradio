package ru.r2cloud.jradio.kafasat;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.ax25.Ax25Beacon;
import ru.r2cloud.jradio.ccsds.PacketPrimaryHeader;
import ru.r2cloud.jradio.ccsds.PacketSecondaryHeader;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.BitInputStream;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class KafasatBeacon extends Ax25Beacon {

	private PacketPrimaryHeader primary;
	private PacketSecondaryHeader secondary;
	private Telemetry telemetry;

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		BitInputStream bis = new BitInputStream(dis);
		primary = new PacketPrimaryHeader(bis);
		if (primary.isSecondaryHeader()) {
			secondary = new PacketSecondaryHeader(bis);
		}
		if (secondary != null && secondary.getServiceType() == 3 && secondary.getServiceSubType() == 25) {
			LittleEndianDataInputStream ldis = new LittleEndianDataInputStream(dis);
			telemetry = new Telemetry(ldis);
		} else {
			super.readBeacon(dis);
		}
	}

	public PacketPrimaryHeader getPrimary() {
		return primary;
	}

	public void setPrimary(PacketPrimaryHeader primary) {
		this.primary = primary;
	}

	public PacketSecondaryHeader getSecondary() {
		return secondary;
	}

	public void setSecondary(PacketSecondaryHeader secondary) {
		this.secondary = secondary;
	}

	public Telemetry getTelemetry() {
		return telemetry;
	}

	public void setTelemetry(Telemetry telemetry) {
		this.telemetry = telemetry;
	}

}
