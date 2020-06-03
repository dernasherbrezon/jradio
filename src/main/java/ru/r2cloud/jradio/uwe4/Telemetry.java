package ru.r2cloud.jradio.uwe4;

import java.io.EOFException;
import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Telemetry {

	private int command;
	private int idOfVariable;
	private int typeAndLength;
	private long timestamp;
	private long beaconRate;
	private int bitmaskHousekeeping;
	private long uptimeSeconds;

	private boolean ppu2;
	private boolean ppu1;
	private boolean aocs;
	private boolean radio2Active;
	private boolean powerPathBDisabled;
	private boolean powerPathADisabled;
	private boolean mcuBActive;

	private int obcMode;
	private boolean panelXMinus;
	private boolean panelXPlus;
	private boolean panelYMinus;
	private boolean panelYPlus;
	private boolean panelZMinus;
	private boolean panelZPlus;

	private byte batteryATemperature;
	private byte batteryAStateOfCharge;
	private byte batteryBTemperature;
	private byte batteryBStateOfCharge;
	private short batteryACurrent;
	private short batteryAVoltage;
	private short batteryBCurrent;
	private short batteryBVoltage;

	private short powerConsumptionTotal;
	private byte obcTemperature;
	private byte panelXPlusTemperature;
	private byte panelXMinusTemperature;
	private byte panelYPlusTemperature;
	private byte panelYMinusTemperature;
	private byte panelZPlusTemperature;
	private byte panelZMinusTemperature;

	private int frequency;

	public Telemetry() {
		// do nothing
	}

	public Telemetry(LittleEndianDataInputStream ldis) throws IOException {
		command = ldis.readUnsignedByte();
		idOfVariable = ldis.readUnsignedShort();
		typeAndLength = ldis.readUnsignedShort();
		timestamp = read6BytesInt(ldis);
		beaconRate = ldis.readUnsignedInt();
		bitmaskHousekeeping = ldis.readUnsignedShort();
		uptimeSeconds = ldis.readUnsignedInt();

		int raw = ldis.readUnsignedByte();
		ppu2 = ((raw >> 6) & 0x1) > 0;
		ppu1 = ((raw >> 5) & 0x1) > 0;
		aocs = ((raw >> 4) & 0x1) > 0;
		radio2Active = ((raw >> 3) & 0x1) > 0;
		powerPathBDisabled = ((raw >> 2) & 0x1) > 0;
		powerPathADisabled = ((raw >> 1) & 0x1) > 0;
		mcuBActive = (raw & 0x1) > 0;

		raw = ldis.readUnsignedByte();
		obcMode = ((raw >> 6) & 0b11);
		panelXMinus = ((raw >> 5) & 0x1) > 0;
		panelXPlus = ((raw >> 4) & 0x1) > 0;
		panelYMinus = ((raw >> 3) & 0x1) > 0;
		panelYPlus = ((raw >> 2) & 0x1) > 0;
		panelZMinus = ((raw >> 1) & 0x1) > 0;
		panelZPlus = (raw & 0x1) > 0;

		batteryATemperature = ldis.readByte();
		batteryAStateOfCharge = ldis.readByte();
		batteryBTemperature = ldis.readByte();
		batteryBStateOfCharge = ldis.readByte();
		batteryACurrent = ldis.readShort();
		batteryAVoltage = ldis.readShort();
		batteryBCurrent = ldis.readShort();
		batteryBVoltage = ldis.readShort();

		powerConsumptionTotal = ldis.readShort();
		obcTemperature = ldis.readByte();

		panelXPlusTemperature = ldis.readByte();
		panelXMinusTemperature = ldis.readByte();

		panelYPlusTemperature = ldis.readByte();
		panelYMinusTemperature = ldis.readByte();

		panelZPlusTemperature = ldis.readByte();
		panelZMinusTemperature = ldis.readByte();

		frequency = ldis.readUnsignedShort();
	}

	public int getCommand() {
		return command;
	}

	public void setCommand(int command) {
		this.command = command;
	}

	public int getIdOfVariable() {
		return idOfVariable;
	}

	public void setIdOfVariable(int idOfVariable) {
		this.idOfVariable = idOfVariable;
	}

	public int getTypeAndLength() {
		return typeAndLength;
	}

	public void setTypeAndLength(int typeAndLength) {
		this.typeAndLength = typeAndLength;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public long getBeaconRate() {
		return beaconRate;
	}

	public void setBeaconRate(long beaconRate) {
		this.beaconRate = beaconRate;
	}

	public int getBitmaskHousekeeping() {
		return bitmaskHousekeeping;
	}

	public void setBitmaskHousekeeping(int bitmaskHousekeeping) {
		this.bitmaskHousekeeping = bitmaskHousekeeping;
	}

	public long getUptimeSeconds() {
		return uptimeSeconds;
	}

	public void setUptimeSeconds(long uptimeSeconds) {
		this.uptimeSeconds = uptimeSeconds;
	}

	public boolean isPpu2() {
		return ppu2;
	}

	public void setPpu2(boolean ppu2) {
		this.ppu2 = ppu2;
	}

	public boolean isPpu1() {
		return ppu1;
	}

	public void setPpu1(boolean ppu1) {
		this.ppu1 = ppu1;
	}

	public boolean isAocs() {
		return aocs;
	}

	public void setAocs(boolean aocs) {
		this.aocs = aocs;
	}

	public boolean isRadio2Active() {
		return radio2Active;
	}

	public void setRadio2Active(boolean radio2Active) {
		this.radio2Active = radio2Active;
	}

	public boolean isPowerPathBDisabled() {
		return powerPathBDisabled;
	}

	public void setPowerPathBDisabled(boolean powerPathBDisabled) {
		this.powerPathBDisabled = powerPathBDisabled;
	}

	public boolean isPowerPathADisabled() {
		return powerPathADisabled;
	}

	public void setPowerPathADisabled(boolean powerPathADisabled) {
		this.powerPathADisabled = powerPathADisabled;
	}

	public boolean isMcuBActive() {
		return mcuBActive;
	}

	public void setMcuBActive(boolean mcuBActive) {
		this.mcuBActive = mcuBActive;
	}

	public int getObcMode() {
		return obcMode;
	}

	public void setObcMode(int obcMode) {
		this.obcMode = obcMode;
	}

	public boolean isPanelXMinus() {
		return panelXMinus;
	}

	public void setPanelXMinus(boolean panelXMinus) {
		this.panelXMinus = panelXMinus;
	}

	public boolean isPanelXPlus() {
		return panelXPlus;
	}

	public void setPanelXPlus(boolean panelXPlus) {
		this.panelXPlus = panelXPlus;
	}

	public boolean isPanelYMinus() {
		return panelYMinus;
	}

	public void setPanelYMinus(boolean panelYMinus) {
		this.panelYMinus = panelYMinus;
	}

	public boolean isPanelYPlus() {
		return panelYPlus;
	}

	public void setPanelYPlus(boolean panelYPlus) {
		this.panelYPlus = panelYPlus;
	}

	public boolean isPanelZMinus() {
		return panelZMinus;
	}

	public void setPanelZMinus(boolean panelZMinus) {
		this.panelZMinus = panelZMinus;
	}

	public boolean isPanelZPlus() {
		return panelZPlus;
	}

	public void setPanelZPlus(boolean panelZPlus) {
		this.panelZPlus = panelZPlus;
	}

	public byte getBatteryATemperature() {
		return batteryATemperature;
	}

	public void setBatteryATemperature(byte batteryATemperature) {
		this.batteryATemperature = batteryATemperature;
	}

	public byte getBatteryAStateOfCharge() {
		return batteryAStateOfCharge;
	}

	public void setBatteryAStateOfCharge(byte batteryAStateOfCharge) {
		this.batteryAStateOfCharge = batteryAStateOfCharge;
	}

	public byte getBatteryBTemperature() {
		return batteryBTemperature;
	}

	public void setBatteryBTemperature(byte batteryBTemperature) {
		this.batteryBTemperature = batteryBTemperature;
	}

	public byte getBatteryBStateOfCharge() {
		return batteryBStateOfCharge;
	}

	public void setBatteryBStateOfCharge(byte batteryBStateOfCharge) {
		this.batteryBStateOfCharge = batteryBStateOfCharge;
	}

	public short getBatteryACurrent() {
		return batteryACurrent;
	}

	public void setBatteryACurrent(short batteryACurrent) {
		this.batteryACurrent = batteryACurrent;
	}

	public short getBatteryAVoltage() {
		return batteryAVoltage;
	}

	public void setBatteryAVoltage(short batteryAVoltage) {
		this.batteryAVoltage = batteryAVoltage;
	}

	public short getBatteryBCurrent() {
		return batteryBCurrent;
	}

	public void setBatteryBCurrent(short batteryBCurrent) {
		this.batteryBCurrent = batteryBCurrent;
	}

	public short getBatteryBVoltage() {
		return batteryBVoltage;
	}

	public void setBatteryBVoltage(short batteryBVoltage) {
		this.batteryBVoltage = batteryBVoltage;
	}

	public short getPowerConsumptionTotal() {
		return powerConsumptionTotal;
	}

	public void setPowerConsumptionTotal(short powerConsumptionTotal) {
		this.powerConsumptionTotal = powerConsumptionTotal;
	}

	public byte getObcTemperature() {
		return obcTemperature;
	}

	public void setObcTemperature(byte obcTemperature) {
		this.obcTemperature = obcTemperature;
	}

	public byte getPanelXPlusTemperature() {
		return panelXPlusTemperature;
	}

	public void setPanelXPlusTemperature(byte panelXPlusTemperature) {
		this.panelXPlusTemperature = panelXPlusTemperature;
	}

	public byte getPanelXMinusTemperature() {
		return panelXMinusTemperature;
	}

	public void setPanelXMinusTemperature(byte panelXMinusTemperature) {
		this.panelXMinusTemperature = panelXMinusTemperature;
	}

	public byte getPanelYPlusTemperature() {
		return panelYPlusTemperature;
	}

	public void setPanelYPlusTemperature(byte panelYPlusTemperature) {
		this.panelYPlusTemperature = panelYPlusTemperature;
	}

	public byte getPanelYMinusTemperature() {
		return panelYMinusTemperature;
	}

	public void setPanelYMinusTemperature(byte panelYMinusTemperature) {
		this.panelYMinusTemperature = panelYMinusTemperature;
	}

	public byte getPanelZPlusTemperature() {
		return panelZPlusTemperature;
	}

	public void setPanelZPlusTemperature(byte panelZPlusTemperature) {
		this.panelZPlusTemperature = panelZPlusTemperature;
	}

	public byte getPanelZMinusTemperature() {
		return panelZMinusTemperature;
	}

	public void setPanelZMinusTemperature(byte panelZMinusTemperature) {
		this.panelZMinusTemperature = panelZMinusTemperature;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	private static long read6BytesInt(LittleEndianDataInputStream dis) throws IOException {
		long ch1 = dis.read();
		long ch2 = dis.read();
		long ch3 = dis.read();
		long ch4 = dis.read();
		long ch5 = dis.read();
		long ch6 = dis.read();
		if ((ch1 | ch2 | ch3 | ch4 | ch5 | ch6) < 0)
			throw new EOFException();
		return ((ch6 << 40) | (ch5 << 32) | (ch4 << 24) | (ch3 << 16) | (ch2 << 8) | ch1);

	}
}
