package ru.r2cloud.jradio.smogp;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class SmogPBeacon extends Beacon {

	private SpectrumResult spectrumResult;
	private Telemetry1 telemetry1;
	private Telemetry2 telemetry2;
	private Telemetry3 telemetry3;
	private ru.r2cloud.jradio.smogp.Beacon beacon;
	private FileInfo fileInfo;
	private FileFragment fileFragment;
	private SMOGPTelemetry1 smogTelemetry;

	private byte[] unknownPayload;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		LittleEndianDataInputStream dis = new LittleEndianDataInputStream(new DataInputStream(new ByteArrayInputStream(data)));
		int type = dis.readUnsignedByte();
		switch (type) {
		case 1:
			telemetry1 = new Telemetry1(dis);
			break;
		case 2:
			telemetry2 = new Telemetry2(dis);
			break;
		case 3:
			telemetry3 = new Telemetry3(dis);
			break;
		case 4:
			beacon = new ru.r2cloud.jradio.smogp.Beacon(dis);
			break;
		case 5:
			spectrumResult = new SpectrumResult(dis);
			break;
		case 6:
			fileInfo = new FileInfo(dis);
			break;
		case 7:
			fileFragment = new FileFragment(dis);
			break;
		case 33:
			smogTelemetry = new SMOGPTelemetry1(dis);
			break;
		default:
			unknownPayload = new byte[dis.available()];
			dis.readFully(unknownPayload);
			break;
		}
	}

	public SMOGPTelemetry1 getSmogTelemetry() {
		return smogTelemetry;
	}

	public void setSmogTelemetry(SMOGPTelemetry1 smogTelemetry) {
		this.smogTelemetry = smogTelemetry;
	}

	public FileFragment getFileFragment() {
		return fileFragment;
	}

	public void setFileFragment(FileFragment fileFragment) {
		this.fileFragment = fileFragment;
	}

	public FileInfo getFileInfo() {
		return fileInfo;
	}

	public void setFileInfo(FileInfo fileInfo) {
		this.fileInfo = fileInfo;
	}

	public ru.r2cloud.jradio.smogp.Beacon getBeacon() {
		return beacon;
	}

	public void setBeacon(ru.r2cloud.jradio.smogp.Beacon beacon) {
		this.beacon = beacon;
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

	public SpectrumResult getSpectrumResult() {
		return spectrumResult;
	}

	public void setSpectrumResult(SpectrumResult spectrumResult) {
		this.spectrumResult = spectrumResult;
	}

	public byte[] getUnknownPayload() {
		return unknownPayload;
	}

	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
	}

}
