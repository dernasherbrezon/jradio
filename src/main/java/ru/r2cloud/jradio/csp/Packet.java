package ru.r2cloud.jradio.csp;

import java.io.IOException;
import java.util.Arrays;

import ru.r2cloud.jradio.Externalizable;

public class Packet implements Externalizable {

	private int length;
	private Priority priority;

	private boolean ffrag; // Use fragmentation
	private boolean fhmac; // Use HMAC verification
	private boolean fxtea; // Use XTEA encryption
	private boolean frdp; // Use RDP protocol
	private boolean fcrc32; // Use CRC32 checksum

	private byte[] data;
	private byte[] rawData;
	
	private byte[] hmac;

	@Override
	public void readExternal(byte[] data) throws IOException {
		if (data.length < 6) {
			throw new IOException("invalid csp header size: " + data.length);
		}
		this.rawData = data;
		length = data[0] << 8 | data[1];
		priority = Priority.valufOfCode(data[2] >> 6);
		ffrag = (data[5] & 0x10) > 0 ? true : false;
		fhmac = (data[5] & 0x08) > 0 ? true : false;
		fxtea = (data[5] & 0x04) > 0 ? true : false;
		frdp = (data[5] & 0x02) > 0 ? true : false;
		fcrc32 = (data[5] & 0x01) > 0 ? true : false;
		int endIndex = data.length;
		if (fhmac) {
			hmac = Arrays.copyOfRange(data, endIndex - 2, endIndex);
			endIndex = endIndex - 2;
		}
		this.data = Arrays.copyOfRange(data, 2 + 4, endIndex);
	}
	
	public byte[] getHmac() {
		return hmac;
	}
	
	public void setHmac(byte[] hmac) {
		this.hmac = hmac;
	}

	public byte[] getData() {
		return data;
	}

	public int getLength() {
		return length;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public boolean isFfrag() {
		return ffrag;
	}

	public void setFfrag(boolean ffrag) {
		this.ffrag = ffrag;
	}

	public boolean isFhmac() {
		return fhmac;
	}

	public void setFhmac(boolean fhmac) {
		this.fhmac = fhmac;
	}

	public boolean isFxtea() {
		return fxtea;
	}

	public void setFxtea(boolean fxtea) {
		this.fxtea = fxtea;
	}

	public boolean isFrdp() {
		return frdp;
	}

	public void setFrdp(boolean frdp) {
		this.frdp = frdp;
	}

	public boolean isFcrc32() {
		return fcrc32;
	}

	public void setFcrc32(boolean fcrc32) {
		this.fcrc32 = fcrc32;
	}

	public byte[] getRawData() {
		return rawData;
	}
}
