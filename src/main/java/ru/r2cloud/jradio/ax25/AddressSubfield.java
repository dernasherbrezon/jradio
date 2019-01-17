package ru.r2cloud.jradio.ax25;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class AddressSubfield {

	private String callsign;
	private int ssid;

	public AddressSubfield(DataInputStream dis) throws IOException {
		byte[] callsignData = new byte[6];
		dis.readFully(callsignData);
		for (int i = 0; i < callsignData.length; i++) {
			callsignData[i] = (byte) ((callsignData[i] & 0xFF) >> 1);
		}
		callsign = new String(callsignData, StandardCharsets.ISO_8859_1).trim();
		int raw = dis.readUnsignedByte();
		ssid = (raw >> 1) & 0b1111;
	}

	public String getCallsign() {
		return callsign;
	}

	public void setCallsign(String callsign) {
		this.callsign = callsign;
	}

	public int getSsid() {
		return ssid;
	}

	public void setSsid(int ssid) {
		this.ssid = ssid;
	}

}
