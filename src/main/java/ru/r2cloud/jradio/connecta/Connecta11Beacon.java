package ru.r2cloud.jradio.connecta;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.ax25.Ax25Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Connecta11Beacon extends Ax25Beacon {

	private Connecta11Header connecta11Header;
	private Connecta11BeaconData beaconData;
	private byte[] unknownPayload;

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		LittleEndianDataInputStream ldis = new LittleEndianDataInputStream(dis);
		connecta11Header = new Connecta11Header(ldis);
		if (connecta11Header.getBeaconId() == 3) {
			beaconData = new Connecta11BeaconData(ldis);
		} else {
			unknownPayload = new byte[ldis.available()];
			ldis.readFully(unknownPayload);
		}
	}

	public Connecta11Header getConnecta11Header() {
		return connecta11Header;
	}

	public void setConnecta11Header(Connecta11Header connecta11Header) {
		this.connecta11Header = connecta11Header;
	}

	public Connecta11BeaconData getBeaconData() {
		return beaconData;
	}

	public void setBeaconData(Connecta11BeaconData beaconData) {
		this.beaconData = beaconData;
	}

	public byte[] getUnknownPayload() {
		return unknownPayload;
	}

	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
	}

}
