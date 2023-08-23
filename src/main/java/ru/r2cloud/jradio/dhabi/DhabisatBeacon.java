package ru.r2cloud.jradio.dhabi;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import ru.r2cloud.jradio.ax25.Ax25Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class DhabisatBeacon extends Ax25Beacon {

	private DhabisatHeader dhabisatHeader;
	private DhabisatBeaconPacket beacon;
	private String genericPacket;

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		int length = dis.available();
		LittleEndianDataInputStream ldis = new LittleEndianDataInputStream(dis);
		dhabisatHeader = new DhabisatHeader(ldis);
		if (length == 187) {
			beacon = new DhabisatBeaconPacket(ldis);
		} else {
			byte[] remaining = new byte[dis.available()];
			dis.readFully(remaining);
			genericPacket = new String(remaining, StandardCharsets.US_ASCII);
		}
	}

	public DhabisatHeader getDhabisatHeader() {
		return dhabisatHeader;
	}

	public void setDhabisatHeader(DhabisatHeader dhabisatHeader) {
		this.dhabisatHeader = dhabisatHeader;
	}

	public DhabisatBeaconPacket getBeacon() {
		return beacon;
	}

	public void setBeacon(DhabisatBeaconPacket beacon) {
		this.beacon = beacon;
	}

	public String getGenericPacket() {
		return genericPacket;
	}

	public void setGenericPacket(String genericPacket) {
		this.genericPacket = genericPacket;
	}

}
