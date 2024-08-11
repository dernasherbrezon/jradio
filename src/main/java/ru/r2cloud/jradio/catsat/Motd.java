package ru.r2cloud.jradio.catsat;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import ru.r2cloud.jradio.bobcat1.BeaconElementHeader;

public class Motd {

	private BeaconElementHeader header;
	private String callsign;
	private String motd;

	public Motd() {
		// do nothing
	}

	public Motd(DataInputStream dis) throws IOException {
		header = new BeaconElementHeader(dis);
		byte[] callsignBytes = new byte[8];
		dis.readFully(callsignBytes);
		callsign = new String(callsignBytes, StandardCharsets.US_ASCII);
		byte[] motdBytes = new byte[80];
		dis.readFully(motdBytes);
		motd = new String(motdBytes, StandardCharsets.US_ASCII);
	}

	public BeaconElementHeader getHeader() {
		return header;
	}

	public void setHeader(BeaconElementHeader header) {
		this.header = header;
	}

	public String getCallsign() {
		return callsign;
	}

	public void setCallsign(String callsign) {
		this.callsign = callsign;
	}

	public String getMotd() {
		return motd;
	}

	public void setMotd(String motd) {
		this.motd = motd;
	}

}
