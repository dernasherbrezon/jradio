package ru.r2cloud.jradio.rhw;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.csp.Header;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;
import ru.r2cloud.jradio.util.StreamUtils;

public class ReaktorHelloWorldBeacon extends Beacon {

	private RadioPacketType type;

	// relay
	private byte[] relayPayload;

	// csp
	private Header header;
	private int cspLength;

	private EpsBeacon epsBeacon;
	private UHFStatistics uhfStatistics;

	private byte[] unknownCspPayload;

	private long packetNumber;
	private long signature;

	@Override
	public void readBeacon(byte[] data) throws IOException {
		type = RadioPacketType.valueOfCode(data[0]);
		if (type == null) {
			return;
		}
		switch (type) {
		case RELAY:
			relayPayload = Arrays.copyOfRange(data, 1, data.length);
			return;
		case CSP:
			DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
			dis.skipBytes(1); // skip type
			header = new Header(dis);
			cspLength = dis.readUnsignedShort();
			LittleEndianDataInputStream ldis = new LittleEndianDataInputStream(dis);
			// all other fields are little endian
			if (cspLength == 98) {
				epsBeacon = new EpsBeacon(ldis);
			} else if (cspLength == 47) {
				uhfStatistics = new UHFStatistics(ldis);
			} else {
				unknownCspPayload = new byte[cspLength];
				dis.readFully(unknownCspPayload);
			}

			// trailing data in big endian
			packetNumber = StreamUtils.readUnsignedInt(dis);
			signature = StreamUtils.readUnsignedInt(dis);
			return;
		default:
			break;
		}
	}

	public byte[] getUnknownCspPayload() {
		return unknownCspPayload;
	}

	public void setUnknownCspPayload(byte[] unknownCspPayload) {
		this.unknownCspPayload = unknownCspPayload;
	}

	public RadioPacketType getType() {
		return type;
	}

	public void setType(RadioPacketType type) {
		this.type = type;
	}

	public byte[] getRelayPayload() {
		return relayPayload;
	}

	public void setRelayPayload(byte[] relayPayload) {
		this.relayPayload = relayPayload;
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public int getCspLength() {
		return cspLength;
	}

	public void setCspLength(int cspLength) {
		this.cspLength = cspLength;
	}

	public EpsBeacon getEpsBeacon() {
		return epsBeacon;
	}

	public void setEpsBeacon(EpsBeacon epsBeacon) {
		this.epsBeacon = epsBeacon;
	}

	public long getPacketNumber() {
		return packetNumber;
	}

	public void setPacketNumber(long packetNumber) {
		this.packetNumber = packetNumber;
	}

	public long getSignature() {
		return signature;
	}

	public void setSignature(long signature) {
		this.signature = signature;
	}

	public UHFStatistics getUhfStatistics() {
		return uhfStatistics;
	}

	public void setUhfStatistics(UHFStatistics uhfStatistics) {
		this.uhfStatistics = uhfStatistics;
	}

}
