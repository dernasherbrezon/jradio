package ru.r2cloud.jradio.celesta;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class HamMessage {

	private int lastMessageRssi;
	private String radioMessage;

	public HamMessage() {
		// do nothing
	}

	public HamMessage(DataInputStream dis) throws IOException {
		lastMessageRssi = dis.readUnsignedByte() * -1;
		byte[] radioMessageBytes = new byte[133];
		dis.readFully(radioMessageBytes);
		radioMessage = new String(radioMessageBytes, StandardCharsets.US_ASCII).trim();
	}

	public int getLastMessageRssi() {
		return lastMessageRssi;
	}
	
	public void setLastMessageRssi(int lastMessageRssi) {
		this.lastMessageRssi = lastMessageRssi;
	}

	public String getRadioMessage() {
		return radioMessage;
	}

	public void setRadioMessage(String radioMessage) {
		this.radioMessage = radioMessage;
	}

}
