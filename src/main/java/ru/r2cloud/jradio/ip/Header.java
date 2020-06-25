package ru.r2cloud.jradio.ip;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class Header {

	private int version;
	private int ihl;
	private int dscp;
	private int ecn;
	private int totalLength;
	private int identification;
	private int flags;
	private int fragmentOffset;
	private int timeToLive;
	private int protocol;
	private int headerChecksum;
	private long sourceIpAddress;
	private long destinationIpAddress;
	private byte[] options;

	public Header() {
		// do nothing
	}

	public Header(DataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		version = (raw >> 4) & 0b1111;
		ihl = raw & 0b1111;
		raw = dis.readUnsignedByte();
		dscp = (raw >> 2) & 0b111111;
		ecn = (raw & 0b11);
		totalLength = dis.readUnsignedShort();
		identification = dis.readUnsignedShort();
		raw = dis.readUnsignedShort();
		flags = (raw >> 13) & 0b111;
		fragmentOffset = raw & 0b1111111111111; // 13
		timeToLive = dis.readUnsignedByte();
		protocol = dis.readUnsignedByte();
		headerChecksum = dis.readUnsignedShort();
		sourceIpAddress = StreamUtils.readUnsignedInt(dis);
		destinationIpAddress = StreamUtils.readUnsignedInt(dis);
		if (ihl > 5) {
			options = new byte[16];
			dis.readFully(options);
		}
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getIhl() {
		return ihl;
	}

	public void setIhl(int ihl) {
		this.ihl = ihl;
	}

	public int getDscp() {
		return dscp;
	}

	public void setDscp(int dscp) {
		this.dscp = dscp;
	}

	public int getEcn() {
		return ecn;
	}

	public void setEcn(int ecn) {
		this.ecn = ecn;
	}

	public int getTotalLength() {
		return totalLength;
	}

	public void setTotalLength(int totalLength) {
		this.totalLength = totalLength;
	}

	public int getIdentification() {
		return identification;
	}

	public void setIdentification(int identification) {
		this.identification = identification;
	}

	public int getFlags() {
		return flags;
	}

	public void setFlags(int flags) {
		this.flags = flags;
	}

	public int getFragmentOffset() {
		return fragmentOffset;
	}

	public void setFragmentOffset(int fragmentOffset) {
		this.fragmentOffset = fragmentOffset;
	}

	public int getTimeToLive() {
		return timeToLive;
	}

	public void setTimeToLive(int timeToLive) {
		this.timeToLive = timeToLive;
	}

	public int getProtocol() {
		return protocol;
	}

	public void setProtocol(int protocol) {
		this.protocol = protocol;
	}

	public int getHeaderChecksum() {
		return headerChecksum;
	}

	public void setHeaderChecksum(int headerChecksum) {
		this.headerChecksum = headerChecksum;
	}

	public long getSourceIpAddress() {
		return sourceIpAddress;
	}

	public void setSourceIpAddress(long sourceIpAddress) {
		this.sourceIpAddress = sourceIpAddress;
	}

	public long getDestinationIpAddress() {
		return destinationIpAddress;
	}

	public void setDestinationIpAddress(long destinationIpAddress) {
		this.destinationIpAddress = destinationIpAddress;
	}

	public byte[] getOptions() {
		return options;
	}

	public void setOptions(byte[] options) {
		this.options = options;
	}

}
