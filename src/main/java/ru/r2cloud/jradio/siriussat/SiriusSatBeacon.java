package ru.r2cloud.jradio.siriussat;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.ax25.Ax25Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class SiriusSatBeacon extends Ax25Beacon {

	private ShortBeacon beacon;
	private ExtendedBeacon extendedBeacon;
	private byte[] unknownPayload;

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		int type = dis.readUnsignedByte();
		dis.skipBytes(7);
		switch (type) {
		case 22:
			beacon = new ShortBeacon(new LittleEndianDataInputStream(dis));
			break;
		case 23:
			extendedBeacon = new ExtendedBeacon(new LittleEndianDataInputStream(dis));
			break;
		default:
			unknownPayload = new byte[dis.available()];
			dis.readFully(unknownPayload);
		}
	}

	public ShortBeacon getBeacon() {
		return beacon;
	}

	public void setBeacon(ShortBeacon beacon) {
		this.beacon = beacon;
	}

	public ExtendedBeacon getExtendedBeacon() {
		return extendedBeacon;
	}

	public void setExtendedBeacon(ExtendedBeacon extendedBeacon) {
		this.extendedBeacon = extendedBeacon;
	}

	public byte[] getUnknownPayload() {
		return unknownPayload;
	}

	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
	}

}
