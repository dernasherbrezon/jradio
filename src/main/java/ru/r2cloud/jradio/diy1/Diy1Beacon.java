package ru.r2cloud.jradio.diy1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Diy1Beacon extends Beacon {

	private static final Pattern COMMA = Pattern.compile(",");

	private int hoursSinceLastReset;
	private int minutesSinceLastReset;
	private int secondsSinceLastReset;

	private int solarChargerCurrent;
	private int logicCurrent;
	private int transceiverRxCurrent;
	private int transceiverTxCurrent;
	private float batteryVoltage;
	private int rssi;
	private float obcTemperature;
	private float transceiverPaTemperature;
	private int numberOfResets;

	private boolean paMediumPower;
	private boolean robotLoggerFull;
	private boolean robotCallsignChange;
	private boolean robotOpOn;
	private boolean loggerFull;
	private boolean paLowPower;
	private boolean rtcSet;
	private boolean commandReceived;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		String str = new String(data, 6, data.length - 6 - 2, StandardCharsets.US_ASCII);
		String[] parts = COMMA.split(str);
		hoursSinceLastReset = Integer.valueOf(parts[0].trim().substring(0, 2));
		minutesSinceLastReset = Integer.valueOf(parts[0].trim().substring(2, 4));
		secondsSinceLastReset = Integer.valueOf(parts[0].trim().substring(4, 6));

		solarChargerCurrent = Integer.valueOf(parts[1].trim());
		logicCurrent = Integer.valueOf(parts[2].trim());
		transceiverRxCurrent = Integer.valueOf(parts[3].trim());
		transceiverTxCurrent = Integer.valueOf(parts[4].trim());
		batteryVoltage = Integer.valueOf(parts[5].trim()) / 100.0f;
		rssi = Integer.valueOf(parts[6].trim());
		obcTemperature = Integer.valueOf(parts[7].trim()) / 10.0f;
		transceiverPaTemperature = Integer.valueOf(parts[8].trim()) / 10.0f;
		numberOfResets = Integer.valueOf(parts[9].trim());

		// cut wildcard from the end
		int raw = Integer.parseInt(parts[10].substring(0, parts[10].trim().length() - 1).trim(), 16);
		paMediumPower = ((raw) & 0x1) > 0;
		robotLoggerFull = ((raw >> 1) & 0x1) > 0;
		robotCallsignChange = ((raw >> 2) & 0x1) > 0;
		robotOpOn = ((raw >> 3) & 0x1) > 0;
		loggerFull = ((raw >> 4) & 0x1) > 0;
		paLowPower = ((raw >> 5) & 0x1) > 0;
		rtcSet = ((raw >> 6) & 0x1) > 0;
		commandReceived = ((raw >> 7) & 0x1) > 0;
	}

	public int getHoursSinceLastReset() {
		return hoursSinceLastReset;
	}

	public void setHoursSinceLastReset(int hoursSinceLastReset) {
		this.hoursSinceLastReset = hoursSinceLastReset;
	}

	public int getMinutesSinceLastReset() {
		return minutesSinceLastReset;
	}

	public void setMinutesSinceLastReset(int minutesSinceLastReset) {
		this.minutesSinceLastReset = minutesSinceLastReset;
	}

	public int getSecondsSinceLastReset() {
		return secondsSinceLastReset;
	}

	public void setSecondsSinceLastReset(int secondsSinceLastReset) {
		this.secondsSinceLastReset = secondsSinceLastReset;
	}

	public int getSolarChargerCurrent() {
		return solarChargerCurrent;
	}

	public void setSolarChargerCurrent(int solarChargerCurrent) {
		this.solarChargerCurrent = solarChargerCurrent;
	}

	public int getLogicCurrent() {
		return logicCurrent;
	}

	public void setLogicCurrent(int logicCurrent) {
		this.logicCurrent = logicCurrent;
	}

	public int getTransceiverRxCurrent() {
		return transceiverRxCurrent;
	}

	public void setTransceiverRxCurrent(int transceiverRxCurrent) {
		this.transceiverRxCurrent = transceiverRxCurrent;
	}

	public int getTransceiverTxCurrent() {
		return transceiverTxCurrent;
	}

	public void setTransceiverTxCurrent(int transceiverTxCurrent) {
		this.transceiverTxCurrent = transceiverTxCurrent;
	}

	public float getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(float batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public int getRssi() {
		return rssi;
	}

	public void setRssi(int rssi) {
		this.rssi = rssi;
	}

	public float getObcTemperature() {
		return obcTemperature;
	}

	public void setObcTemperature(float obcTemperature) {
		this.obcTemperature = obcTemperature;
	}

	public float getTransceiverPaTemperature() {
		return transceiverPaTemperature;
	}

	public void setTransceiverPaTemperature(float transceiverPaTemperature) {
		this.transceiverPaTemperature = transceiverPaTemperature;
	}

	public int getNumberOfResets() {
		return numberOfResets;
	}

	public void setNumberOfResets(int numberOfResets) {
		this.numberOfResets = numberOfResets;
	}

	public boolean isPaMediumPower() {
		return paMediumPower;
	}

	public void setPaMediumPower(boolean paMediumPower) {
		this.paMediumPower = paMediumPower;
	}

	public boolean isRobotLoggerFull() {
		return robotLoggerFull;
	}

	public void setRobotLoggerFull(boolean robotLoggerFull) {
		this.robotLoggerFull = robotLoggerFull;
	}

	public boolean isRobotCallsignChange() {
		return robotCallsignChange;
	}

	public void setRobotCallsignChange(boolean robotCallsignChange) {
		this.robotCallsignChange = robotCallsignChange;
	}

	public boolean isRobotOpOn() {
		return robotOpOn;
	}

	public void setRobotOpOn(boolean robotOpOn) {
		this.robotOpOn = robotOpOn;
	}

	public boolean isLoggerFull() {
		return loggerFull;
	}

	public void setLoggerFull(boolean loggerFull) {
		this.loggerFull = loggerFull;
	}

	public boolean isPaLowPower() {
		return paLowPower;
	}

	public void setPaLowPower(boolean paLowPower) {
		this.paLowPower = paLowPower;
	}

	public boolean isRtcSet() {
		return rtcSet;
	}

	public void setRtcSet(boolean rtcSet) {
		this.rtcSet = rtcSet;
	}

	public boolean isCommandReceived() {
		return commandReceived;
	}

	public void setCommandReceived(boolean commandReceived) {
		this.commandReceived = commandReceived;
	}

}
