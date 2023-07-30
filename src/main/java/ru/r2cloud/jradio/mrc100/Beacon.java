package ru.r2cloud.jradio.mrc100;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Beacon {

	private long timestamp;
	private BeaconInfo[] infos;

	private UshortCvtValue txMaxCurrent;
	private UshortCvtValue txMinVoltage;
	private BooleanValue com2Active;

	private Integer txMcsTimeAgo;
	private String txMcs;

	private UbyteValue txPowerLevel;
	private UshortValue comCycleSleep;
	private BooleanValue emergencyMode;

	private String message;

	public Beacon() {
		// do nothing
	}

	public Beacon(LittleEndianDataInputStream dis) throws IOException {
		timestamp = dis.readUnsignedInt();
		infos = new BeaconInfo[8];
		for (int i = 0; i < infos.length; i++) {
			infos[i] = new BeaconInfo(dis);
		}
		txMaxCurrent = new UshortCvtValue(dis);
		txMinVoltage = new UshortCvtValue(dis);
		com2Active = new BooleanValue(dis);

		txMcsTimeAgo = BeaconInfo.convertByteSecondsAgo(dis.readUnsignedByte());
		byte[] txMcsBytes = new byte[4];
		dis.readFully(txMcsBytes);
		txMcs = new String(txMcsBytes, StandardCharsets.UTF_8).trim();

		txPowerLevel = new UbyteValue(dis);

		comCycleSleep = new UshortValue(dis);

		emergencyMode = new BooleanValue(dis);

		byte[] messageBytes = new byte[48];
		dis.readFully(messageBytes);
		message = new String(messageBytes, StandardCharsets.UTF_8).trim();
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public BeaconInfo[] getInfos() {
		return infos;
	}

	public void setInfos(BeaconInfo[] infos) {
		this.infos = infos;
	}

	public UshortCvtValue getTxMaxCurrent() {
		return txMaxCurrent;
	}

	public void setTxMaxCurrent(UshortCvtValue txMaxCurrent) {
		this.txMaxCurrent = txMaxCurrent;
	}

	public UshortCvtValue getTxMinVoltage() {
		return txMinVoltage;
	}

	public void setTxMinVoltage(UshortCvtValue txMinVoltage) {
		this.txMinVoltage = txMinVoltage;
	}

	public BooleanValue getCom2Active() {
		return com2Active;
	}

	public void setCom2Active(BooleanValue com2Active) {
		this.com2Active = com2Active;
	}

	public Integer getTxMcsTimeAgo() {
		return txMcsTimeAgo;
	}

	public void setTxMcsTimeAgo(Integer txMcsTimeAgo) {
		this.txMcsTimeAgo = txMcsTimeAgo;
	}

	public String getTxMcs() {
		return txMcs;
	}

	public void setTxMcs(String txMcs) {
		this.txMcs = txMcs;
	}

	public UbyteValue getTxPowerLevel() {
		return txPowerLevel;
	}

	public void setTxPowerLevel(UbyteValue txPowerLevel) {
		this.txPowerLevel = txPowerLevel;
	}

	public UshortValue getComCycleSleep() {
		return comCycleSleep;
	}

	public void setComCycleSleep(UshortValue comCycleSleep) {
		this.comCycleSleep = comCycleSleep;
	}

	public BooleanValue getEmergencyMode() {
		return emergencyMode;
	}

	public void setEmergencyMode(BooleanValue emergencyMode) {
		this.emergencyMode = emergencyMode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
