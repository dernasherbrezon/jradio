package ru.r2cloud.jradio.grbalpha;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.csp.Header;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class GRBAlphaBeacon extends Beacon {

	private ru.r2cloud.jradio.ax25.Header ax25Header;
	private Header cspHeader;
	private ComTelemetry comTelemetry;
	private byte[] unknownPayload;
	private String unknownMessage;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		try {
			DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
			ax25Header = new ru.r2cloud.jradio.ax25.Header(dis);
			byte[] bodyBytes = new byte[dis.available()];
			dis.readFully(bodyBytes);
			String body = new String(bodyBytes, StandardCharsets.US_ASCII);
			if (body.startsWith(",COMd")) {
				comTelemetry = new ComTelemetry(body);
			} else {
				unknownMessage = body;
			}
		} catch (Exception e) {
			DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
			cspHeader = new Header(dis);
			unknownPayload = new byte[dis.available()];
			dis.readFully(unknownPayload);
		}
	}
	
	public String getUnknownMessage() {
		return unknownMessage;
	}
	
	public void setUnknownMessage(String unknownMessage) {
		this.unknownMessage = unknownMessage;
	}

	public ru.r2cloud.jradio.ax25.Header getAx25Header() {
		return ax25Header;
	}

	public void setAx25Header(ru.r2cloud.jradio.ax25.Header ax25Header) {
		this.ax25Header = ax25Header;
	}

	public Header getCspHeader() {
		return cspHeader;
	}

	public void setCspHeader(Header cspHeader) {
		this.cspHeader = cspHeader;
	}

	public ComTelemetry getComTelemetry() {
		return comTelemetry;
	}

	public void setComTelemetry(ComTelemetry comTelemetry) {
		this.comTelemetry = comTelemetry;
	}

	public byte[] getUnknownPayload() {
		return unknownPayload;
	}

	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
	}

}
