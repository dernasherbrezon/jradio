package ru.r2cloud.jradio.csim;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.ax25.Ax25Beacon;
import ru.r2cloud.jradio.ax25.Header;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class CsimBeacon extends Ax25Beacon {

	private BeaconShort beaconShort;
	private BeaconLong beaconLong;

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		if (dis.available() == (131 - Header.LENGTH_BYTES)) {
			beaconShort = new BeaconShort(dis);
		} else if (dis.available() == (272 - Header.LENGTH_BYTES)) {
			beaconLong = new BeaconLong(dis);
		} else {
			// this will setup unknown payload
			super.readBeacon(dis);
		}
	}

	public BeaconShort getBeaconShort() {
		return beaconShort;
	}

	public void setBeaconShort(BeaconShort beaconShort) {
		this.beaconShort = beaconShort;
	}

	public BeaconLong getBeaconLong() {
		return beaconLong;
	}

	public void setBeaconLong(BeaconLong beaconLong) {
		this.beaconLong = beaconLong;
	}

}
