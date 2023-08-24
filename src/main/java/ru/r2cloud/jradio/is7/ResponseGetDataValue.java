package ru.r2cloud.jradio.is7;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class ResponseGetDataValue {

	private int size;
	private int fieldId;
	private int fieldSize;
	private Integer version;
	private Integer spinoDelay;
	private Integer spinoDelayCrc;
	private String callsign;
	private Integer callsignCrc;
	private byte[] valueData2;

	public ResponseGetDataValue() {
		// do nothing
	}

	public ResponseGetDataValue(LittleEndianDataInputStream dis) throws IOException {
		size = dis.readUnsignedShort();
		fieldId = dis.readUnsignedByte();
		fieldSize = dis.readUnsignedByte();
		switch (fieldId) {
		case 0x80:
			version = dis.readUnsignedShort();
			break;
		case 0x01:
			spinoDelay = dis.readUnsignedByte();
			spinoDelayCrc = dis.readUnsignedShort();
			break;
		case 0x02:
		case 0x03:
		case 0x04:
			callsign = dis.readString(6);
			callsignCrc = dis.readUnsignedShort();
			break;
		}
		valueData2 = new byte[dis.available()];
		dis.readFully(valueData2);
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getFieldId() {
		return fieldId;
	}

	public void setFieldId(int fieldId) {
		this.fieldId = fieldId;
	}

	public int getFieldSize() {
		return fieldSize;
	}

	public void setFieldSize(int fieldSize) {
		this.fieldSize = fieldSize;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getSpinoDelay() {
		return spinoDelay;
	}

	public void setSpinoDelay(Integer spinoDelay) {
		this.spinoDelay = spinoDelay;
	}

	public Integer getSpinoDelayCrc() {
		return spinoDelayCrc;
	}

	public void setSpinoDelayCrc(Integer spinoDelayCrc) {
		this.spinoDelayCrc = spinoDelayCrc;
	}

	public String getCallsign() {
		return callsign;
	}

	public void setCallsign(String callsign) {
		this.callsign = callsign;
	}

	public Integer getCallsignCrc() {
		return callsignCrc;
	}

	public void setCallsignCrc(Integer callsignCrc) {
		this.callsignCrc = callsignCrc;
	}

	public byte[] getValueData2() {
		return valueData2;
	}

	public void setValueData2(byte[] valueData2) {
		this.valueData2 = valueData2;
	}

}
