package ru.r2cloud.jradio.is7;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.ax25.AddressSubfield;
import ru.r2cloud.jradio.ax25.Ax25Beacon;
import ru.r2cloud.jradio.ax25.Header;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;
import ru.r2cloud.jradio.uvsqsat.UvsqsatBeacon;

public class InspireSat7Beacon extends Ax25Beacon {

	private Digi digi;
	private SpinoPayload spinoPayload;
	private UvsqsatBeacon uvsqSatPayload;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		AddressSubfield destinationAddress = new AddressSubfield(dis);
		// no reliable way to check spino beacon using binary
		if (destinationAddress.getCallsign().contains("SPINO")) {
			readSpinoBeacon(data);
		} else {
			super.readBeacon(data);
		}
	}

	private void readSpinoBeacon(byte[] data) throws IOException, UncorrectableException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		setHeader(new Header(dis, false));
		LittleEndianDataInputStream ldis = new LittleEndianDataInputStream(dis);
		switch (getHeader().getSourceAddress().getSsid()) {
		case 0x03: {
			digi = new Digi(ldis);
			break;
		}
		case 0x02:
		case 0x00:
		case 0x05:
			spinoPayload = new SpinoPayload(ldis);
			break;
		default:
			byte[] payload = new byte[dis.available()];
			dis.readFully(payload);
			setPayload(payload);
		}

	}

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		uvsqSatPayload = new UvsqsatBeacon();
		uvsqSatPayload.readBeacon(dis);
	}

	public Digi getDigi() {
		return digi;
	}

	public void setDigi(Digi digi) {
		this.digi = digi;
	}

	public SpinoPayload getSpinoPayload() {
		return spinoPayload;
	}

	public void setSpinoPayload(SpinoPayload spinoPayload) {
		this.spinoPayload = spinoPayload;
	}

}
