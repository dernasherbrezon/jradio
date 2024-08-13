package ru.r2cloud.jradio.ccsds;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class PacketSecondaryHeader {

	public static final int SIZE_BYTES = 3;

	private Boolean ccsdsSecondaryHeader;
	private int pusVersion;
	private Integer ack;
	private int serviceType;
	private int serviceSubType;

	public PacketSecondaryHeader() {
		// do nothing
	}

	public PacketSecondaryHeader(BitInputStream bis, boolean telemetry) throws IOException {
		if (telemetry) {
			bis.skipBits(1);
		} else {
			ccsdsSecondaryHeader = bis.readBoolean();
		}
		pusVersion = bis.readUnsignedInt(3);
		if (telemetry) {
			bis.skipBits(4);
		} else {
			ack = bis.readUnsignedInt(4);
		}
		serviceType = bis.readUnsignedByte();
		serviceSubType = bis.readUnsignedByte();
	}

	public Boolean getCcsdsSecondaryHeader() {
		return ccsdsSecondaryHeader;
	}

	public void setCcsdsSecondaryHeader(Boolean ccsdsSecondaryHeader) {
		this.ccsdsSecondaryHeader = ccsdsSecondaryHeader;
	}

	public int getPusVersion() {
		return pusVersion;
	}

	public void setPusVersion(int pusVersion) {
		this.pusVersion = pusVersion;
	}

	public Integer getAck() {
		return ack;
	}

	public void setAck(Integer ack) {
		this.ack = ack;
	}

	public int getServiceType() {
		return serviceType;
	}

	public void setServiceType(int serviceType) {
		this.serviceType = serviceType;
	}

	public int getServiceSubType() {
		return serviceSubType;
	}

	public void setServiceSubType(int serviceSubType) {
		this.serviceSubType = serviceSubType;
	}

}
