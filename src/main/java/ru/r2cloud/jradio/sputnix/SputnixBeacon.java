package ru.r2cloud.jradio.sputnix;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.usp.UspBeacon;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class SputnixBeacon extends UspBeacon {

	private int type;
	private int size;

	private SputnixTelemetry telemetry;
	private FileHeader fileHeader;
	private FileSize fileSize;
	private FileData fileData;

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		LittleEndianDataInputStream ldis = new LittleEndianDataInputStream(dis);
		type = ldis.readUnsignedShort();
		size = ldis.readShort();
		ldis.skipBytes(4);
		if (type == 0x4216) {
			telemetry = new SputnixTelemetry(ldis);
		} else if (type == 0x0C20) {
			fileHeader = new FileHeader(ldis);
		} else if (type == 0x0C2B) {
			fileSize = new FileSize(ldis);
		} else if (type == 0x0C24) {
			fileData = new FileData(ldis);
		} else {
			byte[] unknownPayload = new byte[ldis.available()];
			ldis.readFully(unknownPayload);
			setPayload(unknownPayload);
		}
	}

	public FileHeader getFileHeader() {
		return fileHeader;
	}

	public void setFileHeader(FileHeader fileHeader) {
		this.fileHeader = fileHeader;
	}

	public FileSize getFileSize() {
		return fileSize;
	}

	public void setFileSize(FileSize fileSize) {
		this.fileSize = fileSize;
	}

	public FileData getFileData() {
		return fileData;
	}

	public void setFileData(FileData fileData) {
		this.fileData = fileData;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public SputnixTelemetry getTelemetry() {
		return telemetry;
	}

	public void setTelemetry(SputnixTelemetry telemetry) {
		this.telemetry = telemetry;
	}
}
