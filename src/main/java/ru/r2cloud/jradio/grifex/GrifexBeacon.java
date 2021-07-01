package ru.r2cloud.jradio.grifex;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.ax25.Ax25Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class GrifexBeacon extends Ax25Beacon {

	private MxlHeader mxlHeader;
	private GrifexTelemetry telemetry;

	private byte[] unknownPayload;

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		mxlHeader = new MxlHeader(dis);
		if (mxlHeader.getPacketLength() != dis.available() + MxlHeader.LENGTH_BYTES) {
			throw new UncorrectableException("not enough bytes in the input");
		}
		if (mxlHeader.getSecondaryId() != 0x42) {
			throw new UncorrectableException("unknown spacecraft: " + mxlHeader.getSecondaryId());
		}
		if (mxlHeader.getPacketLength() == 245) {
			telemetry = new GrifexTelemetry(new LittleEndianDataInputStream(dis));
		} else {
			unknownPayload = new byte[dis.available()];
			dis.readFully(unknownPayload);
		}
	}

	public MxlHeader getMxlHeader() {
		return mxlHeader;
	}

	public void setMxlHeader(MxlHeader mxlHeader) {
		this.mxlHeader = mxlHeader;
	}

	public GrifexTelemetry getTelemetry() {
		return telemetry;
	}

	public void setTelemetry(GrifexTelemetry telemetry) {
		this.telemetry = telemetry;
	}

	public byte[] getUnknownPayload() {
		return unknownPayload;
	}

	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
	}

}
