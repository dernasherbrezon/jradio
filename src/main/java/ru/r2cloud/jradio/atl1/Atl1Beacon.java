package ru.r2cloud.jradio.atl1;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.smogp.AtlTelemetry1;
import ru.r2cloud.jradio.smogp.AtlTelemetry2;
import ru.r2cloud.jradio.smogp.FileFragment;
import ru.r2cloud.jradio.smogp.FileInfo;
import ru.r2cloud.jradio.smogp.SmogPBeacon;
import ru.r2cloud.jradio.smogp.SpectrumResult;
import ru.r2cloud.jradio.smogp.Telemetry1;
import ru.r2cloud.jradio.smogp.Telemetry2;
import ru.r2cloud.jradio.smogp.Telemetry3;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Atl1Beacon extends Beacon {

	private SpectrumResult spectrumResult;
	private Telemetry1 telemetry1;
	private Telemetry2 telemetry2;
	private Telemetry3 telemetry3;
	private ru.r2cloud.jradio.smogp.Beacon beacon;
	private FileInfo fileInfo;
	private FileFragment fileFragment;

	private AtlTelemetry1 atlTelemetry;
	private AtlTelemetry2 atlTelemetry2;
	private AtlTelemetry3 atlTelemetry3;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		LittleEndianDataInputStream dis = new LittleEndianDataInputStream(new DataInputStream(new ByteArrayInputStream(data)));
		int type = dis.readUnsignedByte();
		switch (type) {
		case 1:
			SmogPBeacon.validateTelemetry1(data);
			telemetry1 = new Telemetry1(dis);
			break;
		case 2:
			SmogPBeacon.validateTelemetry2(data);
			telemetry2 = new Telemetry2(dis);
			break;
		case 3:
			SmogPBeacon.validateTelemetry3(data);
			telemetry3 = new Telemetry3(dis);
			break;
		case 4:
			SmogPBeacon.validateBeacon(data);
			beacon = new ru.r2cloud.jradio.smogp.Beacon(dis);
			break;
		case 5:
			SmogPBeacon.validateSpectrumResult(data);
			spectrumResult = new SpectrumResult(dis);
			break;
		case 6:
			SmogPBeacon.validateFileInfo(data);
			fileInfo = new FileInfo(dis);
			break;
		case 7:
			SmogPBeacon.validateFileFragment(data);
			fileFragment = new FileFragment(dis);
			break;
		case 129:
			SmogPBeacon.validateAtlTelemetry1(data);
			atlTelemetry = new AtlTelemetry1(dis, AtlTelemetry1.SMOG_CONST_TX_PWR_LEVEL_TO_MW);
			break;
		case 130:
			SmogPBeacon.validateAtlTelemetry2(data);
			atlTelemetry2 = new AtlTelemetry2(dis);
			break;
		case 131:
			validateAtlTelemetry3(data);
			atlTelemetry3 = new AtlTelemetry3(dis);
			break;
		default:
			throw new UncorrectableException("unknown packet type: " + type);
		}
	}

	private static void validateAtlTelemetry3(byte[] data) throws UncorrectableException {
		if (data.length != 128) {
			throw new UncorrectableException("invalid packet length");
		}
		SmogPBeacon.validateUnusedBytes(data, 85, 33);
	}

	public SpectrumResult getSpectrumResult() {
		return spectrumResult;
	}

	public void setSpectrumResult(SpectrumResult spectrumResult) {
		this.spectrumResult = spectrumResult;
	}

	public Telemetry1 getTelemetry1() {
		return telemetry1;
	}

	public void setTelemetry1(Telemetry1 telemetry1) {
		this.telemetry1 = telemetry1;
	}

	public Telemetry2 getTelemetry2() {
		return telemetry2;
	}

	public void setTelemetry2(Telemetry2 telemetry2) {
		this.telemetry2 = telemetry2;
	}

	public Telemetry3 getTelemetry3() {
		return telemetry3;
	}

	public void setTelemetry3(Telemetry3 telemetry3) {
		this.telemetry3 = telemetry3;
	}

	public ru.r2cloud.jradio.smogp.Beacon getBeacon() {
		return beacon;
	}

	public void setBeacon(ru.r2cloud.jradio.smogp.Beacon beacon) {
		this.beacon = beacon;
	}

	public FileInfo getFileInfo() {
		return fileInfo;
	}

	public void setFileInfo(FileInfo fileInfo) {
		this.fileInfo = fileInfo;
	}

	public FileFragment getFileFragment() {
		return fileFragment;
	}

	public void setFileFragment(FileFragment fileFragment) {
		this.fileFragment = fileFragment;
	}

	public AtlTelemetry1 getAtlTelemetry() {
		return atlTelemetry;
	}

	public void setAtlTelemetry(AtlTelemetry1 atlTelemetry) {
		this.atlTelemetry = atlTelemetry;
	}

	public AtlTelemetry2 getAtlTelemetry2() {
		return atlTelemetry2;
	}

	public void setAtlTelemetry2(AtlTelemetry2 atlTelemetry2) {
		this.atlTelemetry2 = atlTelemetry2;
	}

	public AtlTelemetry3 getAtlTelemetry3() {
		return atlTelemetry3;
	}
	
	public void setAtlTelemetry3(AtlTelemetry3 atlTelemetry3) {
		this.atlTelemetry3 = atlTelemetry3;
	}
}
