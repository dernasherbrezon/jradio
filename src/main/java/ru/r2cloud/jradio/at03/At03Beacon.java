package ru.r2cloud.jradio.at03;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import ru.r2cloud.jradio.Externalizable;

/**
 * Based on http://spacedatacenter.at/pegasus/img/hamoperatorsmanual10.pdf
 *
 */
public class At03Beacon implements Externalizable {

	private BeaconType type;
	private String callsign;

	private StacieBeacon stacieBeacon;
	private EPSBeacon epsBeacon;
	private OBC1Beacon obc1Beacon;
	private OBC2Beacon obc2Beacon;

	private long beginSample;
	private long beginMillis;
	private byte[] rawData;

	@Override
	public void readExternal(byte[] data) throws IOException {
		this.rawData = data;
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));

		int beaconType = dis.readUnsignedByte();
		type = BeaconType.valueOfCode(beaconType);
		if (type == null) {
			throw new IOException("unsupported beacon type: " + beaconType);
		}
		byte[] callsignBytes = new byte[6];
		dis.readFully(callsignBytes);
		callsign = new String(callsignBytes, StandardCharsets.ISO_8859_1);
		switch (type) {
		case STACIE:
			stacieBeacon = new StacieBeacon(dis);
			break;
		case OBC1:
			obc1Beacon = new OBC1Beacon(dis);
			break;
		case EPS:
			epsBeacon = new EPSBeacon(dis);
			break;
		case OBC2:
			obc2Beacon = new OBC2Beacon(dis);
			break;
		default:
			throw new IOException("unsupported beacon type: " + type);
		}
	}

	public BeaconType getType() {
		return type;
	}

	public void setType(BeaconType type) {
		this.type = type;
	}

	public String getCallsign() {
		return callsign;
	}

	public void setCallsign(String callsign) {
		this.callsign = callsign;
	}

	public StacieBeacon getStacieBeacon() {
		return stacieBeacon;
	}

	public void setStacieBeacon(StacieBeacon stacieBeacon) {
		this.stacieBeacon = stacieBeacon;
	}

	public EPSBeacon getEpsBeacon() {
		return epsBeacon;
	}

	public void setEpsBeacon(EPSBeacon epsBeacon) {
		this.epsBeacon = epsBeacon;
	}

	public OBC1Beacon getObc1Beacon() {
		return obc1Beacon;
	}

	public void setObc1Beacon(OBC1Beacon obc1Beacon) {
		this.obc1Beacon = obc1Beacon;
	}

	public OBC2Beacon getObc2Beacon() {
		return obc2Beacon;
	}

	public void setObc2Beacon(OBC2Beacon obc2Beacon) {
		this.obc2Beacon = obc2Beacon;
	}

	public long getBeginSample() {
		return beginSample;
	}

	public void setBeginSample(long beginSample) {
		this.beginSample = beginSample;
	}

	public long getBeginMillis() {
		return beginMillis;
	}

	public void setBeginMillis(long beginMillis) {
		this.beginMillis = beginMillis;
	}

	public byte[] getRawData() {
		return rawData;
	}

	public void setRawData(byte[] rawData) {
		this.rawData = rawData;
	}

}
