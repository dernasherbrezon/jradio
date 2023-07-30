package ru.r2cloud.jradio.mrc100;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Mrc100Beacon extends Beacon {

	private FileDl fileDl;
	private FileDlStartBlock fileStartBlock;
	private FileInfo fileInfo;
	private SpaTruncated spa2Truncated;
	private Spa spa2;
	private SpaTruncated spa1Truncated;
	private Spa spa1;
	private ProgMap progmap;
	private ru.r2cloud.jradio.mrc100.Beacon beacon;
	private AdcsTelemetry adcsTelemetry;
	private TelemetryCustom telemetryCustom;
	private Telemetry6 telemetry6;
	private Telemetry5 telemetry5;
	private Telemetry4 telemetry4;
	private Telemetry3 telemetry3;
	private Telemetry2 telemetry2;
	private Telemetry1 telemetry1;
	private UplinkFeedback uplinkFeedback;
	private byte[] unknownPayload;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		PacketType type = PacketType.values()[dis.readUnsignedByte() - 1];
		// skip crc
		dis.skipBytes(2);
		LittleEndianDataInputStream ldis = new LittleEndianDataInputStream(dis);
		switch (type) {
		case DOWNLINK_PCKT_FILEDL:
			fileDl = new FileDl(ldis);
			break;
		case DOWNLINK_PCKT_FILEDL_STARTBLOCK:
			fileStartBlock = new FileDlStartBlock(ldis);
			break;
		case DOWNLINK_PCKT_FILEINFO:
			fileInfo = new FileInfo(ldis);
			break;
		case DOWNLINK_PCKT_UPLINK_FEEDBACK:
			uplinkFeedback = new UplinkFeedback(ldis);
			break;
		case DOWNLINK_PCKT_SPA2_TRUNCATED:
			spa2Truncated = new SpaTruncated(ldis);
			break;
		case DOWNLINK_PCKT_SPA2:
			spa2 = new Spa(ldis);
			break;
		case DOWNLINK_PCKT_SPA1_TRUNCATED:
			spa1Truncated = new SpaTruncated(ldis);
			break;
		case DOWNLINK_PCKT_SPA1:
			spa1 = new Spa(ldis);
			break;
		case DOWNLINK_PCKT_PROGMAP:
			progmap = new ProgMap(ldis);
			break;
		case DOWNLINK_PCKT_BEACON:
			beacon = new ru.r2cloud.jradio.mrc100.Beacon(ldis);
			break;
		case DOWNLINK_PCKT_ADCS_APP_TELEMETRY:
			adcsTelemetry = new AdcsTelemetry(ldis);
			break;
		case DOWNLINK_PCKT_TELEMETRY_CUSTOM:
			telemetryCustom = new TelemetryCustom(ldis);
			break;
		case DOWNLINK_PCKT_TELEMETRY6:
			telemetry6 = new Telemetry6(ldis);
			break;
		case DOWNLINK_PCKT_TELEMETRY5:
			telemetry5 = new Telemetry5(ldis);
			break;
		case DOWNLINK_PCKT_TELEMETRY4:
			telemetry4 = new Telemetry4(ldis);
			break;
		case DOWNLINK_PCKT_TELEMETRY3:
			telemetry3 = new Telemetry3(ldis);
			break;
		case DOWNLINK_PCKT_TELEMETRY2:
			telemetry2 = new Telemetry2(ldis);
			break;
		case DOWNLINK_PCKT_TELEMETRY1:
			telemetry1 = new Telemetry1(ldis);
			break;
		default:
			unknownPayload = new byte[ldis.available()];
			ldis.readFully(unknownPayload);
		}
	}

	public FileDl getFileDl() {
		return fileDl;
	}

	public void setFileDl(FileDl fileDl) {
		this.fileDl = fileDl;
	}

	public FileDlStartBlock getFileStartBlock() {
		return fileStartBlock;
	}

	public void setFileStartBlock(FileDlStartBlock fileStartBlock) {
		this.fileStartBlock = fileStartBlock;
	}

	public FileInfo getFileInfo() {
		return fileInfo;
	}

	public void setFileInfo(FileInfo fileInfo) {
		this.fileInfo = fileInfo;
	}

	public SpaTruncated getSpa2Truncated() {
		return spa2Truncated;
	}

	public void setSpa2Truncated(SpaTruncated spa2Truncated) {
		this.spa2Truncated = spa2Truncated;
	}

	public Spa getSpa2() {
		return spa2;
	}

	public void setSpa2(Spa spa2) {
		this.spa2 = spa2;
	}

	public SpaTruncated getSpa1Truncated() {
		return spa1Truncated;
	}

	public void setSpa1Truncated(SpaTruncated spa1Truncated) {
		this.spa1Truncated = spa1Truncated;
	}

	public Spa getSpa1() {
		return spa1;
	}

	public void setSpa1(Spa spa1) {
		this.spa1 = spa1;
	}

	public ProgMap getProgmap() {
		return progmap;
	}

	public void setProgmap(ProgMap progmap) {
		this.progmap = progmap;
	}

	public ru.r2cloud.jradio.mrc100.Beacon getBeacon() {
		return beacon;
	}

	public void setBeacon(ru.r2cloud.jradio.mrc100.Beacon beacon) {
		this.beacon = beacon;
	}

	public AdcsTelemetry getAdcsTelemetry() {
		return adcsTelemetry;
	}

	public void setAdcsTelemetry(AdcsTelemetry adcsTelemetry) {
		this.adcsTelemetry = adcsTelemetry;
	}

	public TelemetryCustom getTelemetryCustom() {
		return telemetryCustom;
	}

	public void setTelemetryCustom(TelemetryCustom telemetryCustom) {
		this.telemetryCustom = telemetryCustom;
	}

	public Telemetry6 getTelemetry6() {
		return telemetry6;
	}

	public void setTelemetry6(Telemetry6 telemetry6) {
		this.telemetry6 = telemetry6;
	}

	public Telemetry5 getTelemetry5() {
		return telemetry5;
	}

	public void setTelemetry5(Telemetry5 telemetry5) {
		this.telemetry5 = telemetry5;
	}

	public Telemetry4 getTelemetry4() {
		return telemetry4;
	}

	public void setTelemetry4(Telemetry4 telemetry4) {
		this.telemetry4 = telemetry4;
	}

	public Telemetry3 getTelemetry3() {
		return telemetry3;
	}

	public void setTelemetry3(Telemetry3 telemetry3) {
		this.telemetry3 = telemetry3;
	}

	public Telemetry2 getTelemetry2() {
		return telemetry2;
	}

	public void setTelemetry2(Telemetry2 telemetry2) {
		this.telemetry2 = telemetry2;
	}

	public Telemetry1 getTelemetry1() {
		return telemetry1;
	}

	public void setTelemetry1(Telemetry1 telemetry1) {
		this.telemetry1 = telemetry1;
	}

	public byte[] getUnknownPayload() {
		return unknownPayload;
	}

	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
	}

	public UplinkFeedback getUplinkFeedback() {
		return uplinkFeedback;
	}

	public void setUplinkFeedback(UplinkFeedback uplinkFeedback) {
		this.uplinkFeedback = uplinkFeedback;
	}
}
