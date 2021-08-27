package ru.r2cloud.jradio.ledsat;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.csp.CspBeacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class LedSatBeacon extends CspBeacon {

	private LedSatTelemetry telemetry;
	private LedSatPictureChunk pictureChunk; 
	private byte[] unknownPayload;

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		int destinationPort = getHeader().getDestinationPort();
		switch (destinationPort) {
		case 8:
			telemetry = new LedSatTelemetry(dis);
			break;
		case 11:
			pictureChunk = new LedSatPictureChunk(dis);
			break;
		default:
			unknownPayload = new byte[dis.available()];
			dis.readFully(unknownPayload);
			break;
		}
	}

	public byte[] getUnknownPayload() {
		return unknownPayload;
	}
	
	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
	}
	
	public LedSatTelemetry getTelemetry() {
		return telemetry;
	}
	
	public void setTelemetry(LedSatTelemetry telemetry) {
		this.telemetry = telemetry;
	}
	
	public LedSatPictureChunk getPictureChunk() {
		return pictureChunk;
	}
	
	public void setPictureChunk(LedSatPictureChunk pictureChunk) {
		this.pictureChunk = pictureChunk;
	}

}
