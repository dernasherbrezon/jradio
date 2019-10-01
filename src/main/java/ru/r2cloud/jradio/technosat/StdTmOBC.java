package ru.r2cloud.jradio.technosat;

import java.io.DataInputStream;
import java.io.IOException;

public class StdTmOBC {

	private byte nodeNo; // redundant node number
	private boolean rstEn; // the watchdog application is enabled to reset the node
	private byte botSlt; // currently running internal software slot
	private boolean synPps; // shall the node synchronize with the PPS signal
	private boolean disUTC; // shall the node distribute the UTC time at the next PPS signal
	private boolean dulBsy; // Indicates the state of the UploadManagers Flash Controller
	private HistoryBufferType tmmtfHist; // indicates the history buffer used to fill up transfer frames with history telemetry
	private SatelliteMode satMode; // the current mode of the satellite
	private HistoryBufferType tmmhsHist; // indicates the history buffer used to fill up the high speed downlink
	private HighSpeedState tmmhsStat; // indicates the current state of highspeed telemetry transmissions

	public StdTmOBC(DataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		nodeNo = (byte) (raw >> 7);
		rstEn = ((raw >> 6) & 0x1) > 0;
		botSlt = (byte) ((raw >> 3) & 0x7);
		synPps = ((raw >> 2) & 0x1) > 0;
		disUTC = ((raw >> 1) & 0x1) > 0;
		dulBsy = (raw & 0x1) > 0;
		raw = dis.readUnsignedByte();
		tmmtfHist = HistoryBufferType.valueOfCode(raw >> 6);
		satMode = SatelliteMode.valueOfCode((raw >> 4) & 0x3);
		tmmhsHist = HistoryBufferType.valueOfCode((raw >> 2) & 0x3);
		tmmhsStat = HighSpeedState.valueOfCode(raw & 0x3);

		dis.skipBytes(11);
	}

	public byte getNodeNo() {
		return nodeNo;
	}

	public void setNodeNo(byte nodeNo) {
		this.nodeNo = nodeNo;
	}

	public boolean isRstEn() {
		return rstEn;
	}

	public void setRstEn(boolean rstEn) {
		this.rstEn = rstEn;
	}

	public byte getBotSlt() {
		return botSlt;
	}

	public void setBotSlt(byte botSlt) {
		this.botSlt = botSlt;
	}

	public boolean isSynPps() {
		return synPps;
	}

	public void setSynPps(boolean synPps) {
		this.synPps = synPps;
	}

	public boolean isDisUTC() {
		return disUTC;
	}

	public void setDisUTC(boolean disUTC) {
		this.disUTC = disUTC;
	}

	public boolean isDulBsy() {
		return dulBsy;
	}

	public void setDulBsy(boolean dulBsy) {
		this.dulBsy = dulBsy;
	}

	public HistoryBufferType getTmmtfHist() {
		return tmmtfHist;
	}

	public void setTmmtfHist(HistoryBufferType tmmtfHist) {
		this.tmmtfHist = tmmtfHist;
	}

	public SatelliteMode getSatMode() {
		return satMode;
	}

	public void setSatMode(SatelliteMode satMode) {
		this.satMode = satMode;
	}

	public HistoryBufferType getTmmhsHist() {
		return tmmhsHist;
	}

	public void setTmmhsHist(HistoryBufferType tmmhsHist) {
		this.tmmhsHist = tmmhsHist;
	}

	public HighSpeedState getTmmhsStat() {
		return tmmhsStat;
	}

	public void setTmmhsStat(HighSpeedState tmmhsStat) {
		this.tmmhsStat = tmmhsStat;
	}

}
