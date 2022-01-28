package ru.r2cloud.jradio.smog1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import ru.r2cloud.jradio.smogp.AckInfo;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Smog1BeaconMessage {

	private long timestamp;
	private String beaconMessage;

	private int numReceivePktGarbage;
	private int numReceivePktBadSerial;
	private int numReceivePktInvalid;
	private long lastUplinkTimestamp;
	private int obcUptimeMin;
	private int comUptimeMin;
	private int txVoltageDrop10mv;
	private int timedTaskCount;

	private int energyMode;
	private boolean tcxoWorks;
	private boolean filesystemWorks;
	private boolean filesystemUsesFlash2;

	private String version;

	private AckInfo[] ackInfo;

	public Smog1BeaconMessage() {
		// do nothing
	}

	public Smog1BeaconMessage(LittleEndianDataInputStream dis) throws IOException {
		timestamp = dis.readUnsignedInt();
		byte[] messageData = new byte[80];
		dis.readFully(messageData);
		beaconMessage = new String(messageData, StandardCharsets.ISO_8859_1).trim();
		dis.skipBytes(1);

		numReceivePktGarbage = dis.readUnsignedByte();
		numReceivePktBadSerial = dis.readUnsignedByte();
		numReceivePktInvalid = dis.readUnsignedByte();
		lastUplinkTimestamp = dis.readUnsignedInt();
		// big endian
		obcUptimeMin = (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
		// big endian
		comUptimeMin = (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
		txVoltageDrop10mv = dis.readUnsignedByte();
		timedTaskCount = dis.readUnsignedByte();

		int raw = dis.readUnsignedByte();
		energyMode = (raw >> 5) & 0b111;
		tcxoWorks = ((raw >> 4) & 0x1) > 0;
		filesystemWorks = ((raw >> 3) & 0x1) > 0;
		filesystemUsesFlash2 = ((raw >> 2) & 0x1) > 0;

		byte[] versionBytes = new byte[7];
		dis.readFully(versionBytes);
		version = new String(versionBytes, StandardCharsets.ISO_8859_1).trim();
		ackInfo = new AckInfo[3];
		for (int i = 0; i < ackInfo.length; i++) {
			ackInfo[i] = new AckInfo(dis);
		}
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getBeaconMessage() {
		return beaconMessage;
	}

	public void setBeaconMessage(String beaconMessage) {
		this.beaconMessage = beaconMessage;
	}

	public int getNumReceivePktGarbage() {
		return numReceivePktGarbage;
	}

	public void setNumReceivePktGarbage(int numReceivePktGarbage) {
		this.numReceivePktGarbage = numReceivePktGarbage;
	}

	public int getNumReceivePktBadSerial() {
		return numReceivePktBadSerial;
	}

	public void setNumReceivePktBadSerial(int numReceivePktBadSerial) {
		this.numReceivePktBadSerial = numReceivePktBadSerial;
	}

	public int getNumReceivePktInvalid() {
		return numReceivePktInvalid;
	}

	public void setNumReceivePktInvalid(int numReceivePktInvalid) {
		this.numReceivePktInvalid = numReceivePktInvalid;
	}

	public long getLastUplinkTimestamp() {
		return lastUplinkTimestamp;
	}

	public void setLastUplinkTimestamp(long lastUplinkTimestamp) {
		this.lastUplinkTimestamp = lastUplinkTimestamp;
	}

	public int getObcUptimeMin() {
		return obcUptimeMin;
	}

	public void setObcUptimeMin(int obcUptimeMin) {
		this.obcUptimeMin = obcUptimeMin;
	}

	public int getComUptimeMin() {
		return comUptimeMin;
	}

	public void setComUptimeMin(int comUptimeMin) {
		this.comUptimeMin = comUptimeMin;
	}

	public int getTxVoltageDrop10mv() {
		return txVoltageDrop10mv;
	}

	public void setTxVoltageDrop10mv(int txVoltageDrop10mv) {
		this.txVoltageDrop10mv = txVoltageDrop10mv;
	}

	public int getTimedTaskCount() {
		return timedTaskCount;
	}

	public void setTimedTaskCount(int timedTaskCount) {
		this.timedTaskCount = timedTaskCount;
	}

	public int getEnergyMode() {
		return energyMode;
	}

	public void setEnergyMode(int energyMode) {
		this.energyMode = energyMode;
	}

	public boolean isTcxoWorks() {
		return tcxoWorks;
	}

	public void setTcxoWorks(boolean tcxoWorks) {
		this.tcxoWorks = tcxoWorks;
	}

	public boolean isFilesystemWorks() {
		return filesystemWorks;
	}

	public void setFilesystemWorks(boolean filesystemWorks) {
		this.filesystemWorks = filesystemWorks;
	}

	public boolean isFilesystemUsesFlash2() {
		return filesystemUsesFlash2;
	}

	public void setFilesystemUsesFlash2(boolean filesystemUsesFlash2) {
		this.filesystemUsesFlash2 = filesystemUsesFlash2;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public AckInfo[] getAckInfo() {
		return ackInfo;
	}

	public void setAckInfo(AckInfo[] ackInfo) {
		this.ackInfo = ackInfo;
	}

}
