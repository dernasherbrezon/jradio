package ru.r2cloud.jradio.ax25;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class AddressSubfield {

	private static final Pattern PATTERN = Pattern.compile("^[\\-A-Za-z0-9]*$");
	private String callsign;
	private int ssid;
	private int extensionBit;

	public AddressSubfield() {
		// do nothing
	}

	public AddressSubfield(DataInputStream dis) throws IOException, UncorrectableException {
		byte[] callsignData = new byte[6];
		dis.readFully(callsignData);
		for (int i = 0; i < callsignData.length; i++) {
			callsignData[i] = (byte) ((callsignData[i] & 0xFF) >> 1);
		}
		callsign = new String(callsignData, StandardCharsets.ISO_8859_1).trim();
		if (!PATTERN.matcher(callsign).find()) {
			throw new UncorrectableException("invalid callsign: " + callsign);
		}
		int raw = dis.readUnsignedByte();
		ssid = (raw >> 1) & 0b1111;
		extensionBit = (raw & 0x1);
	}
	
	public int getExtensionBit() {
		return extensionBit;
	}
	
	public void setExtensionBit(int extensionBit) {
		this.extensionBit = extensionBit;
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

	@Override
	public String toString() {
		return callsign;
	}

}
