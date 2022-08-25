package ru.r2cloud.jradio.cute.fsw;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class FswTables {

	private long length32;
	private long offset32;
	private int checksum;
	private TableUploadStatus tableUploadStatus;
	private int whichTable;
	private int image;
	private boolean flashBurnArmed;

	public FswTables() {
		// do nothing
	}

	public FswTables(DataInputStream dis) throws IOException {
		length32 = StreamUtils.readUnsignedInt(dis);
		offset32 = StreamUtils.readUnsignedInt(dis);
		checksum = dis.readUnsignedShort();
		tableUploadStatus = TableUploadStatus.valueOfCode(dis.readUnsignedByte());
		whichTable = dis.readUnsignedByte();
		image = dis.readUnsignedByte();
		flashBurnArmed = dis.readBoolean();
	}

	public long getLength32() {
		return length32;
	}

	public void setLength32(long length32) {
		this.length32 = length32;
	}

	public long getOffset32() {
		return offset32;
	}

	public void setOffset32(long offset32) {
		this.offset32 = offset32;
	}

	public int getChecksum() {
		return checksum;
	}

	public void setChecksum(int checksum) {
		this.checksum = checksum;
	}

	public TableUploadStatus getTableUploadStatus() {
		return tableUploadStatus;
	}

	public void setTableUploadStatus(TableUploadStatus tableUploadStatus) {
		this.tableUploadStatus = tableUploadStatus;
	}

	public int getWhichTable() {
		return whichTable;
	}

	public void setWhichTable(int whichTable) {
		this.whichTable = whichTable;
	}

	public int getImage() {
		return image;
	}

	public void setImage(int image) {
		this.image = image;
	}

	public boolean isFlashBurnArmed() {
		return flashBurnArmed;
	}

	public void setFlashBurnArmed(boolean flashBurnArmed) {
		this.flashBurnArmed = flashBurnArmed;
	}

}
