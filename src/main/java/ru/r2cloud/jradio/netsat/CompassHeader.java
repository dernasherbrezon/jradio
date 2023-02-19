package ru.r2cloud.jradio.netsat;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class CompassHeader {

	private Integer pid;
	private PayloadSize payloadSizeType;
	private Address source;
	private Integer hopCounter;
	private Address[] hops;
	private Address target;
	private Integer api;
	private Integer refPid;
	private Long time;
	private int payloadSize;

	private boolean useAdtn;
	private boolean useDtn;
	private boolean crc;
	private boolean sgn;
	private boolean error;
	private boolean urgent;
	private boolean encrypted;
	private boolean zipped;

	public CompassHeader() {
		// do nothing
	}

	public CompassHeader(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		boolean lastHeaderByte = ((raw >> 7) & 0x1) > 0;
		boolean routingSet = ((raw >> 6) & 0x1) > 0;
		int sizeLen1 = ((raw >> 5) & 0x1);
		useAdtn = ((raw >> 4) & 0x1) > 0;
		useDtn = ((raw >> 3) & 0x1) > 0;
		boolean apiSet = ((raw >> 2) & 0x1) > 0;
		boolean refSet = ((raw >> 1) & 0x1) > 0;
		boolean pidSet = (raw & 0x1) > 0;
		int sizeLen2 = 0;
		boolean twoBytesApi = false;
		boolean timeIncluded = false;
		if (!lastHeaderByte) {
			raw = dis.readUnsignedByte();
			lastHeaderByte = ((raw >> 7) & 0x1) > 0;
			twoBytesApi = ((raw >> 6) & 0x1) > 0;
			sizeLen2 = ((raw >> 5) & 0x1);
			timeIncluded = ((raw >> 4) & 0x1) > 0;
			crc = ((raw >> 1) & 0x1) > 0;
			sgn = (raw & 0x1) > 0;
			if (!lastHeaderByte) {
				raw = dis.readUnsignedByte();
				error = ((raw >> 3) & 0x1) > 0;
				urgent = ((raw >> 2) & 0x1) > 0;
				encrypted = ((raw >> 1) & 0x1) > 0;
				zipped = (raw & 0x1) > 0;
			}
		}
		if (pidSet) {
			pid = dis.readUnsignedShort();
		}
		payloadSizeType = PayloadSize.valueOfCode((sizeLen1 << 1) + sizeLen2);
		if (routingSet) {
			raw = dis.readUnsignedByte();
			int sysSize = ((raw >> 6) & 0b11);
			hopCounter = ((raw >> 3) & 0b111);
			int numberOfHops = raw & 0b111;
			hops = new Address[numberOfHops];
			source = readAddress(sysSize, dis);
			for (int i = 0; i < hops.length; i++) {
				hops[i] = readAddress(sysSize, dis);
			}
			target = readAddress(sysSize, dis);
		} else {
			source = new Address(dis.readUnsignedByte(), dis.readUnsignedByte());
			target = new Address(dis.readUnsignedByte(), dis.readUnsignedByte());
		}
		if (refSet) {
			refPid = dis.readUnsignedShort();
		}
		if (apiSet) {
			if (twoBytesApi) {
				api = dis.readUnsignedShort();
			} else {
				api = dis.readUnsignedByte();
			}
		}
		if (timeIncluded) {
			// not clear from the spec what time length should be.
			// Absolute time (UNIX, ms) or duration 2-6
			time = dis.readUnsignedInt();
		}
		payloadSize = dis.readUnsignedByte();
	}

	private static Address readAddress(int numberOfBytes, LittleEndianDataInputStream dis) throws IOException {
		Address result = new Address();
		switch (numberOfBytes) {
		case 0:
			break;
		case 1:
			result.setSystemId(dis.readUnsignedByte());
			break;
		case 2:
			result.setSystemId(dis.readUnsignedShort());
			break;
		case 4:
			result.setSystemId(dis.readUnsignedInt());
			break;
		default:
			break;
		}
		result.setSubsystemId(dis.readUnsignedByte());
		return result;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public PayloadSize getPayloadSizeType() {
		return payloadSizeType;
	}

	public void setPayloadSizeType(PayloadSize payloadSizeType) {
		this.payloadSizeType = payloadSizeType;
	}

	public Address getSource() {
		return source;
	}

	public void setSource(Address source) {
		this.source = source;
	}

	public Integer getHopCounter() {
		return hopCounter;
	}

	public void setHopCounter(Integer hopCounter) {
		this.hopCounter = hopCounter;
	}

	public Address[] getHops() {
		return hops;
	}

	public void setHops(Address[] hops) {
		this.hops = hops;
	}

	public Address getTarget() {
		return target;
	}

	public void setTarget(Address target) {
		this.target = target;
	}

	public Integer getApi() {
		return api;
	}

	public void setApi(Integer api) {
		this.api = api;
	}

	public Integer getRefPid() {
		return refPid;
	}

	public void setRefPid(Integer refPid) {
		this.refPid = refPid;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public int getPayloadSize() {
		return payloadSize;
	}

	public void setPayloadSize(int payloadSize) {
		this.payloadSize = payloadSize;
	}

	public boolean isCrc() {
		return crc;
	}

	public void setCrc(boolean crc) {
		this.crc = crc;
	}

	public boolean isSgn() {
		return sgn;
	}

	public void setSgn(boolean sgn) {
		this.sgn = sgn;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public boolean isUrgent() {
		return urgent;
	}

	public void setUrgent(boolean urgent) {
		this.urgent = urgent;
	}

	public boolean isEncrypted() {
		return encrypted;
	}

	public void setEncrypted(boolean encrypted) {
		this.encrypted = encrypted;
	}

	public boolean isZipped() {
		return zipped;
	}

	public void setZipped(boolean zipped) {
		this.zipped = zipped;
	}

	public boolean isUseAdtn() {
		return useAdtn;
	}

	public void setUseAdtn(boolean useAdtn) {
		this.useAdtn = useAdtn;
	}

	public boolean isUseDtn() {
		return useDtn;
	}

	public void setUseDtn(boolean useDtn) {
		this.useDtn = useDtn;
	}

}
