package ru.r2cloud.jradio.pwsat2;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class GenericFrame {

	private int correlationId;
	private int errorCode;

	public GenericFrame(LittleEndianDataInputStream dis) throws IOException {
		correlationId = dis.readUnsignedByte();
		errorCode = dis.readUnsignedByte();
		if (errorCode != 0) {
			// FIXME error response
			return;
		}
		readExternal(dis);
	}

	public int getCorrelationId() {
		return correlationId;
	}

	public void setCorrelationId(int correlationId) {
		this.correlationId = correlationId;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	@SuppressWarnings("unused")
	public void readExternal(LittleEndianDataInputStream dis) throws IOException {
		//do nothing
	}
}
