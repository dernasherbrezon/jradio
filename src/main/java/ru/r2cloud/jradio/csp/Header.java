package ru.r2cloud.jradio.csp;

public class Header {

	public static final int LENGTH = 4;
	private Priority priority;

	private int source;
	private int destination;
	private int sourcePort;
	private int destinationPort;

	private boolean ffrag; // Use fragmentation
	private boolean fhmac; // Use HMAC verification
	private boolean fxtea; // Use XTEA encryption
	private boolean frdp; // Use RDP protocol
	private boolean fcrc32; // Use CRC32 checksum

	public Header(byte[] data) {
		int packed = (data[0] << 24) | (data[1] << 16) | (data[2] << 8) | data[3];
		priority = Priority.valufOfCode(packed >> 30 & 0x3);
		source = (packed >> 25) & 0x1f;
		destination = (packed >> 20) & 0x1f;
		sourcePort = (packed >> 14) & 0x3f;
		destinationPort = (packed >> 8) & 0x3f;
		ffrag = (data[3] & 0x10) > 0;
		fhmac = (data[3] & 0x08) > 0;
		fxtea = (data[3] & 0x04) > 0;
		frdp = (data[3] & 0x02) > 0;
		fcrc32 = (data[3] & 0x01) > 0;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public int getDestination() {
		return destination;
	}

	public void setDestination(int destination) {
		this.destination = destination;
	}

	public int getSourcePort() {
		return sourcePort;
	}

	public void setSourcePort(int sourcePort) {
		this.sourcePort = sourcePort;
	}

	public int getDestinationPort() {
		return destinationPort;
	}

	public void setDestinationPort(int destinationPort) {
		this.destinationPort = destinationPort;
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

}
