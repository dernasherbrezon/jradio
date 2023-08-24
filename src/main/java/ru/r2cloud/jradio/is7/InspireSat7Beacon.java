package ru.r2cloud.jradio.is7;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.ax25.Ax25Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;
import ru.r2cloud.jradio.uvsqsat.UvsqsatBeacon;

public class InspireSat7Beacon extends Ax25Beacon {

	private Digi digi;
	private SpinoPayload spinoPayload;
	private UvsqsatBeacon uvsqSatPayload;

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
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
			uvsqSatPayload = new UvsqsatBeacon();
			uvsqSatPayload.readBeacon(dis);
		}
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
