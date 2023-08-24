package ru.r2cloud.jradio.is7;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class ResponseSetDataValue {

	private int fieldId;
	private int crc;

	public ResponseSetDataValue() {
		// do nothing
	}

	public ResponseSetDataValue(LittleEndianDataInputStream ldis) throws IOException {
		fieldId = ldis.readUnsignedByte();
		crc = ldis.readUnsignedShort();
	}

	public int getFieldId() {
		return fieldId;
	}

	public void setFieldId(int fieldId) {
		this.fieldId = fieldId;
	}

	public int getCrc() {
		return crc;
	}

	public void setCrc(int crc) {
		this.crc = crc;
	}

}
