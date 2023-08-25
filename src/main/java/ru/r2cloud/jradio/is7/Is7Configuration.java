package ru.r2cloud.jradio.is7;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Is7Configuration {

	private long spinoTxFrequency;
	private long spinoRxFrequency;
	private int spinoTxModemSpeed;
	private int spinoRxModemSpeed;
	private int spinoRxModemMode;
	private int spinoTxModemMode;
	private int telemetryDelay;
	private int multimode;
	private int infoMessageActif;
	private int delayInfoMessage;
	private String srcCallsign;
	private String destCallsign;
	private String payloadCallsign;

	public Is7Configuration() {
		// do nothing
	}

	public Is7Configuration(LittleEndianDataInputStream dis) throws IOException {
		spinoTxFrequency = dis.readUnsignedInt();
		spinoRxFrequency = dis.readUnsignedInt();
		spinoTxModemSpeed = dis.readUnsignedShort();
		spinoRxModemSpeed = dis.readUnsignedShort();
		spinoRxModemMode = dis.readUnsignedByte();
		spinoTxModemMode = dis.readUnsignedByte();
		telemetryDelay = dis.readUnsignedByte();
		multimode = dis.readUnsignedByte();
		infoMessageActif = dis.readUnsignedByte();
		delayInfoMessage = dis.readUnsignedByte();
		srcCallsign = dis.readString(6);
		destCallsign = dis.readString(6);
		payloadCallsign = dis.readString(6);
	}

	public long getSpinoTxFrequency() {
		return spinoTxFrequency;
	}

	public void setSpinoTxFrequency(long spinoTxFrequency) {
		this.spinoTxFrequency = spinoTxFrequency;
	}

	public long getSpinoRxFrequency() {
		return spinoRxFrequency;
	}

	public void setSpinoRxFrequency(long spinoRxFrequency) {
		this.spinoRxFrequency = spinoRxFrequency;
	}

	public int getSpinoTxModemSpeed() {
		return spinoTxModemSpeed;
	}

	public void setSpinoTxModemSpeed(int spinoTxModemSpeed) {
		this.spinoTxModemSpeed = spinoTxModemSpeed;
	}

	public int getSpinoRxModemSpeed() {
		return spinoRxModemSpeed;
	}

	public void setSpinoRxModemSpeed(int spinoRxModemSpeed) {
		this.spinoRxModemSpeed = spinoRxModemSpeed;
	}

	public int getSpinoRxModemMode() {
		return spinoRxModemMode;
	}

	public void setSpinoRxModemMode(int spinoRxModemMode) {
		this.spinoRxModemMode = spinoRxModemMode;
	}

	public int getSpinoTxModemMode() {
		return spinoTxModemMode;
	}

	public void setSpinoTxModemMode(int spinoTxModemMode) {
		this.spinoTxModemMode = spinoTxModemMode;
	}

	public int getTelemetryDelay() {
		return telemetryDelay;
	}

	public void setTelemetryDelay(int telemetryDelay) {
		this.telemetryDelay = telemetryDelay;
	}

	public int getMultimode() {
		return multimode;
	}

	public void setMultimode(int multimode) {
		this.multimode = multimode;
	}

	public int getInfoMessageActif() {
		return infoMessageActif;
	}

	public void setInfoMessageActif(int infoMessageActif) {
		this.infoMessageActif = infoMessageActif;
	}

	public int getDelayInfoMessage() {
		return delayInfoMessage;
	}

	public void setDelayInfoMessage(int delayInfoMessage) {
		this.delayInfoMessage = delayInfoMessage;
	}

	public String getSrcCallsign() {
		return srcCallsign;
	}

	public void setSrcCallsign(String srcCallsign) {
		this.srcCallsign = srcCallsign;
	}

	public String getDestCallsign() {
		return destCallsign;
	}

	public void setDestCallsign(String destCallsign) {
		this.destCallsign = destCallsign;
	}

	public String getPayloadCallsign() {
		return payloadCallsign;
	}

	public void setPayloadCallsign(String payloadCallsign) {
		this.payloadCallsign = payloadCallsign;
	}

}
