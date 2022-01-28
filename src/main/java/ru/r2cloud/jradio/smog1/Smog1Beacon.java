package ru.r2cloud.jradio.smog1;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.smogp.FileFragment;
import ru.r2cloud.jradio.smogp.Telemetry1;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Smog1Beacon extends Beacon {

	private Telemetry1 telemetry1;
	private Smog1Telemetry2 telemetry2;
	private Smog1Telemetry3 telemetry3;
	private Smog1BeaconMessage beacon;
	private Smog1SpectrumResult spectrumResult;
	private FileFragment fileFragment;
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
			telemetry2 = new Smog1Telemetry2(dis);
			break;
		case 3:
			telemetry3 = new Smog1Telemetry3(dis);
			break;
		case 4:
			beacon = new Smog1BeaconMessage(dis);
			break;
		case 5:
			spectrumResult = new Smog1SpectrumResult(dis);
			break;
		case 7:
			fileFragment = new FileFragment(dis);
			break;
		default:
			unknownPayload = new byte[dis.available()];
			dis.readFully(unknownPayload);
			break;
		}
	}

	public Telemetry1 getTelemetry1() {
		return telemetry1;
	}

	public void setTelemetry1(Telemetry1 telemetry1) {
		this.telemetry1 = telemetry1;
	}

	public Smog1Telemetry2 getTelemetry2() {
		return telemetry2;
	}

	public void setTelemetry2(Smog1Telemetry2 telemetry2) {
		this.telemetry2 = telemetry2;
	}

	public Smog1Telemetry3 getTelemetry3() {
		return telemetry3;
	}

	public void setTelemetry3(Smog1Telemetry3 telemetry3) {
		this.telemetry3 = telemetry3;
	}

	public Smog1BeaconMessage getBeacon() {
		return beacon;
	}

	public void setBeacon(Smog1BeaconMessage beacon) {
		this.beacon = beacon;
	}

	public Smog1SpectrumResult getSpectrumResult() {
		return spectrumResult;
	}

	public void setSpectrumResult(Smog1SpectrumResult spectrumResult) {
		this.spectrumResult = spectrumResult;
	}

	public FileFragment getFileFragment() {
		return fileFragment;
	}

	public void setFileFragment(FileFragment fileFragment) {
		this.fileFragment = fileFragment;
	}

	public byte[] getUnknownPayload() {
		return unknownPayload;
	}

	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
	}

}
