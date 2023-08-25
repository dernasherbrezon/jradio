package ru.r2cloud.jradio.is7;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class ResponseSetDataValue {

	private int fieldId;

	public ResponseSetDataValue() {
		// do nothing
	}

	public ResponseSetDataValue(LittleEndianDataInputStream ldis) throws IOException {
		fieldId = ldis.readUnsignedByte();
	}

	public int getFieldId() {
		return fieldId;
	}

	public void setFieldId(int fieldId) {
		this.fieldId = fieldId;
	}

}
