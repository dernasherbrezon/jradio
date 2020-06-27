package ru.r2cloud.jradio.bsusat1;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianBitInputStream;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class RfResponse {

	private boolean fecCrcStatus;
	private RfMessageState messageState;
	private int rssi;

	public RfResponse() {
		// do nothing
	}

	public RfResponse(LittleEndianDataInputStream dis) throws IOException {
		LittleEndianBitInputStream bis = new LittleEndianBitInputStream(dis);
		fecCrcStatus = !bis.readBoolean();
		messageState = RfMessageState.valueOfCode(bis.readUnsignedInt(7));
		rssi = bis.readUnsignedByte();
	}

	public boolean isFecCrcStatus() {
		return fecCrcStatus;
	}

	public void setFecCrcStatus(boolean fecCrcStatus) {
		this.fecCrcStatus = fecCrcStatus;
	}

	public RfMessageState getMessageState() {
		return messageState;
	}

	public void setMessageState(RfMessageState messageState) {
		this.messageState = messageState;
	}

	public int getRssi() {
		return rssi;
	}

	public void setRssi(int rssi) {
		this.rssi = rssi;
	}

}
