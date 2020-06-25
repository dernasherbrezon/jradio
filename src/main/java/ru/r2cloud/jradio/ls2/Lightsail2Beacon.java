package ru.r2cloud.jradio.ls2;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.ax25.Ax25Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.ip.Header;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Lightsail2Beacon extends Ax25Beacon {

	private Header ipHeader;
	private ru.r2cloud.jradio.udp.Header udpHeader;
	private byte[] unknownPayload;
	private BeaconData beaconData;

	// The beacon data is encapsulated in AX.25, IP, and UDP. The AX.25 source callsign is KK6HIT with SSID 10. The destination callsign is N6CP SSID 1. The source IP is 129.65.147.35 and the destination IP 224.0.0.1. The source port is 50000 and the destination port 2.

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		// accept only IP packets
		if (getHeader().getPid() != 0xCC) {
			unknownPayload = new byte[dis.available()];
			dis.readFully(unknownPayload);
			return;
		}
		ipHeader = new Header(dis);
		if (ipHeader.getProtocol() != 17) {
			throw new UncorrectableException("unsupported procotol: " + ipHeader.getProtocol());
		}
		udpHeader = new ru.r2cloud.jradio.udp.Header(dis);
		LittleEndianDataInputStream ldis = new LittleEndianDataInputStream(dis);
		beaconData = new BeaconData(ldis);
	}

	public Header getIpHeader() {
		return ipHeader;
	}

	public void setIpHeader(Header ipHeader) {
		this.ipHeader = ipHeader;
	}

	public ru.r2cloud.jradio.udp.Header getUdpHeader() {
		return udpHeader;
	}

	public void setUdpHeader(ru.r2cloud.jradio.udp.Header udpHeader) {
		this.udpHeader = udpHeader;
	}

	public byte[] getUnknownPayload() {
		return unknownPayload;
	}

	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
	}

	public BeaconData getBeaconData() {
		return beaconData;
	}

	public void setBeaconData(BeaconData beaconData) {
		this.beaconData = beaconData;
	}

}
