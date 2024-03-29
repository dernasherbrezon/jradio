package ru.r2cloud.jradio.csp;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

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

	public Header(DataInputStream dis) throws IOException {
		this(StreamUtils.readUnsignedInt(dis));
	}

	public Header(byte[] data) {
		this((((data[0] & 0xFF) << 24) | ((data[1] & 0xFF) << 16) | ((data[2] & 0xFF) << 8) | (data[3] & 0xFF)) & 0xFFFFFFFFL);
	}

	private Header(long packed) {
		priority = Priority.valufOfCode((int) (packed >> 30 & 0x3));
		source = (int) ((packed >> 25) & 0x1f);
		destination = (int) ((packed >> 20) & 0x1f);
		destinationPort = (int) ((packed >> 14) & 0x3f);
		sourcePort = (int) ((packed >> 8) & 0x3f);
		ffrag = ((packed >> 4) & 0x1) > 0;
		fhmac = ((packed >> 3) & 0x1) > 0;
		fxtea = ((packed >> 2) & 0x1) > 0;
		frdp = ((packed >> 1) & 0x1) > 0;
		fcrc32 = (packed & 0x1) > 0;
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

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(source).append(":").append(sourcePort).append(" To ").append(destination).append(":").append(destinationPort);
		result.append(" ").append(priority.name());
		return result.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + destination;
		result = prime * result + destinationPort;
		result = prime * result + (fcrc32 ? 1231 : 1237);
		result = prime * result + (ffrag ? 1231 : 1237);
		result = prime * result + (fhmac ? 1231 : 1237);
		result = prime * result + (frdp ? 1231 : 1237);
		result = prime * result + (fxtea ? 1231 : 1237);
		result = prime * result + ((priority == null) ? 0 : priority.hashCode());
		result = prime * result + source;
		result = prime * result + sourcePort;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Header other = (Header) obj;
		if (destination != other.destination)
			return false;
		if (destinationPort != other.destinationPort)
			return false;
		if (fcrc32 != other.fcrc32)
			return false;
		if (ffrag != other.ffrag)
			return false;
		if (fhmac != other.fhmac)
			return false;
		if (frdp != other.frdp)
			return false;
		if (fxtea != other.fxtea)
			return false;
		if (priority != other.priority)
			return false;
		if (source != other.source)
			return false;
		if (sourcePort != other.sourcePort)
			return false;
		return true;
	}

}
