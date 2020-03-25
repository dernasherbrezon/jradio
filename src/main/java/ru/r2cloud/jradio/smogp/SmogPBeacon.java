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
	private AtlTelemetry1 smogTelemetry;
	private AtlTelemetry2 smogTelemetry2;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		LittleEndianDataInputStream dis = new LittleEndianDataInputStream(new DataInputStream(new ByteArrayInputStream(data)));
		int type = dis.readUnsignedByte();
		switch (type) {
		case 1:
			validateTelemetry1(data);
			telemetry1 = new Telemetry1(dis);
			break;
		case 2:
			validateTelemetry2(data);
			telemetry2 = new Telemetry2(dis);
			break;
		case 3:
			validateTelemetry3(data);
			telemetry3 = new Telemetry3(dis);
			break;
		case 4:
			validateBeacon(data);
			beacon = new ru.r2cloud.jradio.smogp.Beacon(dis);
			break;
		case 5:
			validateSpectrumResult(data);
			spectrumResult = new SpectrumResult(dis);
			break;
		case 6:
			validateFileInfo(data);
			fileInfo = new FileInfo(dis);
			break;
		case 7:
			validateFileFragment(data);
			fileFragment = new FileFragment(dis);
			break;
		case 33:
			validateAtlTelemetry1(data);
			smogTelemetry = new AtlTelemetry1(dis, AtlTelemetry1.SMOG_CONST_TX_PWR_LEVEL_TO_MW);
			break;
		case 34:
			validateAtlTelemetry2(data);
			smogTelemetry2 = new AtlTelemetry2(dis);
			break;
		default:
			throw new UncorrectableException("unknown packet type: " + type);
		}
	}

	public static void validateTelemetry1(byte[] data) throws UncorrectableException {
		if (data.length != 128) {
			throw new UncorrectableException("invalid packet length");
		}
		int offset = 5;
		for (int i = 0; i < 6; i++) {
			if ((data[offset + 16] & 0x07) > 0) {
				throw new UncorrectableException("invalid value");
			}
			offset += 17;
		}
		validateUnusedBytes(data, 116, 2);
	}

	public static void validateTelemetry2(byte[] data) throws UncorrectableException {
		if (data.length != 128) {
			throw new UncorrectableException("invalid packet length");
		}

		int offset = 5;
		for (int i = 0; i < 2; i++) {
			if ((data[offset + 4] & 0x03) > 0) {
				throw new UncorrectableException("invalid value");
			}
			offset += 9;
		}
		for (int i = 0; i < 2; i++) {
			if ((data[offset + 16] & 0x0F) > 0) {
				throw new UncorrectableException("invalid value");
			}
			offset += 17;
		}
		for (int i = 0; i < 2; i++) {
			if ((data[offset + 10] & 0x03) > 0) {
				throw new UncorrectableException("invalid value");
			}
			offset += 11;
		}
		for (int i = 0; i < 2; i++) {
			if ((data[offset + 12] & 0x3F) > 0) {
				throw new UncorrectableException("invalid value");
			}
			offset += 13;
		}
		validateUnusedBytes(data, 114, 4);
	}

	public static void validateTelemetry3(byte[] data) throws UncorrectableException {
		if (data.length != 128) {
			throw new UncorrectableException("invalid packet length");
		}
		if ((data[17] & 0x07) > 0) {
			throw new UncorrectableException("invalid value");
		}
		if ((data[22] & 0x03) > 0) {
			throw new UncorrectableException("invalid value");
		}
		if (data[70] != 1 && data[70] != 0) {
			throw new UncorrectableException("invalid value");
		}
	}

	public static void validateBeacon(byte[] data) throws UncorrectableException {
		if (data.length != 128) {
			throw new UncorrectableException("invalid packet length");
		}
		validateUnusedBytes(data, 110, 8);
	}

	public static void validateSpectrumResult(byte[] data) throws UncorrectableException {
		if (data.length != 256) {
			throw new UncorrectableException("invalid packet length");
		}
		if (data[13] > 9) {
			throw new UncorrectableException("invalid value");
		}
		validateUnusedBytes(data, 18, 2);
	}

	public static void validateFileInfo(byte[] data) throws UncorrectableException {
		if (data.length != 128) {
			throw new UncorrectableException("invalid packet length");
		}
		int offset = 5;
		for (int i = 0; i < 5; i++) {
			if (data[offset + 4] != 0) {
				throw new UncorrectableException("invalid value");
			}
			offset += 21;
		}
	}

	public static void validateFileFragment(byte[] data) throws UncorrectableException {
		if (data.length != 256) {
			throw new UncorrectableException("invalid packet length");
		}
		if (data[12] != 0) {
			throw new UncorrectableException("invalid value");
		}
	}

	public static void validateAtlTelemetry1(byte[] data) throws UncorrectableException {
		if (data.length != 128) {
			throw new UncorrectableException("invalid packet length");
		}
		if (((char) data[9] != '1') && ((char) data[9] != '0')) {
			throw new UncorrectableException("invalid value");
		}
		if (((char) data[10] != 'E') && ((char) data[10] != 'I')) {
			throw new UncorrectableException("invalid value");
		}
		if (((char) data[11] != 'V') && ((char) data[11] != 'N')) {
			throw new UncorrectableException("invalid value");
		}
		if ((data[63] != 2) && (data[63] != 1)) {
			throw new UncorrectableException("invalid value");
		}
		if (data[117] != 0) {
			throw new UncorrectableException("invalid value");
		}
	}

	public static void validateAtlTelemetry2(byte[] data) throws UncorrectableException {
		if (data.length != 128) {
			throw new UncorrectableException("invalid packet length");
		}
		if (((char) data[1] != 'V') && ((char) data[1] != 'N')) {
			throw new UncorrectableException("invalid value");
		}
		validateUnusedBytes(data, 116, 2);
	}

	public static void validateUnusedBytes(byte[] data, int offset, int length) throws UncorrectableException {
		for (int i = 0; i < length; i++) {
			if (data[offset + i] != 0) {
				throw new UncorrectableException("invalid unused bytes");
			}
		}
	}

	public AtlTelemetry2 getSmogTelemetry2() {
		return smogTelemetry2;
	}

	public void setSmogTelemetry2(AtlTelemetry2 smogTelemetry2) {
		this.smogTelemetry2 = smogTelemetry2;
	}

	public AtlTelemetry1 getSmogTelemetry() {
		return smogTelemetry;
	}

	public void setSmogTelemetry(AtlTelemetry1 smogTelemetry) {
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

}
