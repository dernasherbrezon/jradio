package ru.r2cloud.jradio.eirsat;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class Housekeeping {

	private int structureId;
	private HkStruct00 struct00;
	private HkStruct02 struct02;
	private byte[] unknown;

	public Housekeeping() {
		// do nothing
	}

	public Housekeeping(BitInputStream bis) throws IOException {
		structureId = bis.readUnsignedByte();
		switch (structureId) {
		case 0:
			struct00 = new HkStruct00(bis);
			break;
		case 2:
			struct02 = new HkStruct02(bis);
			break;
		default:
			unknown = new byte[bis.available()];
			bis.readFully(unknown);
		}
	}

	public int getStructureId() {
		return structureId;
	}

	public void setStructureId(int structureId) {
		this.structureId = structureId;
	}

	public HkStruct00 getStruct00() {
		return struct00;
	}

	public void setStruct00(HkStruct00 struct00) {
		this.struct00 = struct00;
	}

	public HkStruct02 getStruct02() {
		return struct02;
	}

	public void setStruct02(HkStruct02 struct02) {
		this.struct02 = struct02;
	}

	public byte[] getUnknown() {
		return unknown;
	}

	public void setUnknown(byte[] unknown) {
		this.unknown = unknown;
	}

}
