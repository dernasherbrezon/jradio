package ru.r2cloud.jradio.aistechsat3;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.csp.Header;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Aistechsat3Beacon extends Beacon {

	private static final Logger LOG = LoggerFactory.getLogger(Aistechsat3Beacon.class);

	private Header header;
	private int protocolVersion;
	private int beaconType;
	private int version;
	private int satelliteId;

	private ADCSBeacon2 adcsBeacon2;
	private PlatformBeacon platformBeacon;
	private UHFAntennaTelemetryBeacon uhfAntennaTelemetryBeacon;
	private ADCSBeacon0 adcsBeacon0;
	private ADCSFineSunSensorBeacon adcsFineSunSensorBeacon;
	private ADCSBeacon3 adcsBeacon3;
	private ADCSBeacon6 adcsBeacon6;
	private PayloadBeacon payloadBeacon;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		header = new Header(dis);
		protocolVersion = dis.readUnsignedByte();
		beaconType = dis.readUnsignedByte();
		version = dis.readUnsignedByte();
		satelliteId = dis.readUnsignedShort();
		switch (beaconType) {
		case 10:
			platformBeacon = new PlatformBeacon(dis);
			break;
		case 11:
			uhfAntennaTelemetryBeacon = new UHFAntennaTelemetryBeacon(dis);
			break;
		case 20:
			adcsBeacon0 = new ADCSBeacon0(dis);
			break;
		case 21:
			adcsFineSunSensorBeacon = new ADCSFineSunSensorBeacon(dis);
			break;
		case 22:
			adcsBeacon2 = new ADCSBeacon2(dis);
			break;
		case 23:
			adcsBeacon3 = new ADCSBeacon3(dis);
			break;
		case 26:
			adcsBeacon6 = new ADCSBeacon6(dis);
			break;
		case 30:
			payloadBeacon = new PayloadBeacon(dis);
			break;
		default:
			LOG.info("Unknown beacon type: {}", beaconType);
		}
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public int getProtocolVersion() {
		return protocolVersion;
	}

	public void setProtocolVersion(int protocolVersion) {
		this.protocolVersion = protocolVersion;
	}

	public int getBeaconType() {
		return beaconType;
	}

	public void setBeaconType(int beaconType) {
		this.beaconType = beaconType;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getSatelliteId() {
		return satelliteId;
	}

	public void setSatelliteId(int satelliteId) {
		this.satelliteId = satelliteId;
	}

	public ADCSBeacon2 getAdcsBeacon2() {
		return adcsBeacon2;
	}

	public void setAdcsBeacon2(ADCSBeacon2 adcsBeacon2) {
		this.adcsBeacon2 = adcsBeacon2;
	}

	public PlatformBeacon getPlatformBeacon() {
		return platformBeacon;
	}

	public void setPlatformBeacon(PlatformBeacon platformBeacon) {
		this.platformBeacon = platformBeacon;
	}

	public UHFAntennaTelemetryBeacon getUhfAntennaTelemetryBeacon() {
		return uhfAntennaTelemetryBeacon;
	}

	public void setUhfAntennaTelemetryBeacon(UHFAntennaTelemetryBeacon uhfAntennaTelemetryBeacon) {
		this.uhfAntennaTelemetryBeacon = uhfAntennaTelemetryBeacon;
	}

	public ADCSBeacon0 getAdcsBeacon0() {
		return adcsBeacon0;
	}

	public void setAdcsBeacon0(ADCSBeacon0 adcsBeacon0) {
		this.adcsBeacon0 = adcsBeacon0;
	}

	public ADCSFineSunSensorBeacon getAdcsFineSunSensorBeacon() {
		return adcsFineSunSensorBeacon;
	}

	public void setAdcsFineSunSensorBeacon(ADCSFineSunSensorBeacon adcsFineSunSensorBeacon) {
		this.adcsFineSunSensorBeacon = adcsFineSunSensorBeacon;
	}

	public ADCSBeacon3 getAdcsBeacon3() {
		return adcsBeacon3;
	}

	public void setAdcsBeacon3(ADCSBeacon3 adcsBeacon3) {
		this.adcsBeacon3 = adcsBeacon3;
	}

	public ADCSBeacon6 getAdcsBeacon6() {
		return adcsBeacon6;
	}

	public void setAdcsBeacon6(ADCSBeacon6 adcsBeacon6) {
		this.adcsBeacon6 = adcsBeacon6;
	}

	public PayloadBeacon getPayloadBeacon() {
		return payloadBeacon;
	}

	public void setPayloadBeacon(PayloadBeacon payloadBeacon) {
		this.payloadBeacon = payloadBeacon;
	}

}
