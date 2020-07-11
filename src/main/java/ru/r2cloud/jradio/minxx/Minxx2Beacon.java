package ru.r2cloud.jradio.minxx;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.ax25.Ax25Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Minxx2Beacon extends Ax25Beacon {

	private int spacecraftMode;
	private boolean eclipse;
	private int pointingMode;
	private int commandAcceptCount;
	private float cdhBoardTemperature;
	private boolean enableX123;
	private boolean enableSps;
	private float commBoardTemperature;
	private float motherboardTemperature;
	private float epsBoardTemperature;
	private float batteryVoltage;
	private float solarPanelMinusYCurrent;
	private float solarPanelMinusYVoltage;
	private float solarPanelPlusXCurrent;
	private float solarPanelPlusXVoltage;
	private float solarPanelPlusYCurrent;
	private float solarPanelPlusYVoltage;
	private float solarPanelMinusYTemperature;
	private float solarPanelPlusXTemperature;
	private float solarPanelPlusYTemperature;
	private float batteryChargeCurrent;
	private float batteryDischargeCurrent;
	private float batteryTemperature;
	private long xp;
	private float spsX;
	private float spsY;

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		int startSync = dis.readUnsignedShort();
		if (startSync != 0x0819) {
			return;
		}
		LittleEndianDataInputStream ldis = new LittleEndianDataInputStream(dis);
		ldis.skipBytes(10);
		int raw = ldis.readUnsignedByte();
		spacecraftMode = raw & 0x07;
		eclipse = ((raw & 0x08) >> 3) > 0;
		pointingMode = ldis.readUnsignedByte() & 0x01;
		ldis.skipBytes(2);
		commandAcceptCount = ldis.readUnsignedShort();
		ldis.skipBytes(33);
		decodeFlightModel(ldis.readUnsignedByte());
		ldis.skipBytes(34);
		cdhBoardTemperature = ldis.readShort() / 256.0f;
		raw = ldis.readUnsignedShort();
		enableX123 = ((raw & 0x0002) >> 1) > 0;
		enableSps = ((raw & 0x0004) >> 2) > 0;
		ldis.skipBytes(32);
		commBoardTemperature = ldis.readShort() / 256.0f;
		motherboardTemperature = ldis.readShort() / 256.0f;
		ldis.skipBytes(2);
		epsBoardTemperature = ldis.readShort() / 256.0f;
		ldis.skipBytes(2);
		batteryVoltage = ldis.readUnsignedShort() / 6415.0f;
		ldis.skipBytes(2);
		solarPanelMinusYCurrent = ldis.readUnsignedShort() * 163.8f / 327.68f;
		solarPanelMinusYVoltage = ldis.readUnsignedShort() * 32.76f / 32768.0f;
		solarPanelPlusXCurrent = ldis.readUnsignedShort() * 163.8f / 327.68f;
		solarPanelPlusXVoltage = ldis.readUnsignedShort() * 32.76f / 32768.0f;
		solarPanelPlusYCurrent = ldis.readUnsignedShort() * 163.8f / 327.68f;
		solarPanelPlusYVoltage = ldis.readUnsignedShort() * 32.76f / 32768.0f;
		ldis.skipBytes(12);
		solarPanelMinusYTemperature = ldis.readUnsignedShort() * 0.1744f - 216.0f;
		solarPanelPlusXTemperature = ldis.readUnsignedShort() * 0.1744f - 216.0f;
		solarPanelPlusYTemperature = ldis.readUnsignedShort() * 0.1744f - 216.0f;
		ldis.skipBytes(2);
		batteryChargeCurrent = ldis.readUnsignedShort() * 3.5568f - 61.6f;
		ldis.skipBytes(2);
		batteryDischargeCurrent = ldis.readUnsignedShort() * 3.5568f - 61.6f;
		batteryTemperature = ldis.readUnsignedShort() * 0.18766f - 250.2f;
		ldis.skipBytes(16);
		xp = ldis.readUnsignedInt();
		ldis.skipBytes(10);
		spsX = ldis.readUnsignedShort() / 1e4f * 3.0f;
		spsY = ldis.readUnsignedShort() / 1e4f * 3.0f;
	}

	public int getSpacecraftMode() {
		return spacecraftMode;
	}

	public void setSpacecraftMode(int spacecraftMode) {
		this.spacecraftMode = spacecraftMode;
	}

	public boolean isEclipse() {
		return eclipse;
	}

	public void setEclipse(boolean eclipse) {
		this.eclipse = eclipse;
	}

	public int getPointingMode() {
		return pointingMode;
	}

	public void setPointingMode(int pointingMode) {
		this.pointingMode = pointingMode;
	}

	public int getCommandAcceptCount() {
		return commandAcceptCount;
	}

	public void setCommandAcceptCount(int commandAcceptCount) {
		this.commandAcceptCount = commandAcceptCount;
	}

	public float getCdhBoardTemperature() {
		return cdhBoardTemperature;
	}

	public void setCdhBoardTemperature(float cdhBoardTemperature) {
		this.cdhBoardTemperature = cdhBoardTemperature;
	}

	public boolean isEnableX123() {
		return enableX123;
	}

	public void setEnableX123(boolean enableX123) {
		this.enableX123 = enableX123;
	}

	public boolean isEnableSps() {
		return enableSps;
	}

	public void setEnableSps(boolean enableSps) {
		this.enableSps = enableSps;
	}

	public float getCommBoardTemperature() {
		return commBoardTemperature;
	}

	public void setCommBoardTemperature(float commBoardTemperature) {
		this.commBoardTemperature = commBoardTemperature;
	}

	public float getMotherboardTemperature() {
		return motherboardTemperature;
	}

	public void setMotherboardTemperature(float motherboardTemperature) {
		this.motherboardTemperature = motherboardTemperature;
	}

	public float getEpsBoardTemperature() {
		return epsBoardTemperature;
	}

	public void setEpsBoardTemperature(float epsBoardTemperature) {
		this.epsBoardTemperature = epsBoardTemperature;
	}

	public float getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(float batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public float getSolarPanelMinusYCurrent() {
		return solarPanelMinusYCurrent;
	}

	public void setSolarPanelMinusYCurrent(float solarPanelMinusYCurrent) {
		this.solarPanelMinusYCurrent = solarPanelMinusYCurrent;
	}

	public float getSolarPanelMinusYVoltage() {
		return solarPanelMinusYVoltage;
	}

	public void setSolarPanelMinusYVoltage(float solarPanelMinusYVoltage) {
		this.solarPanelMinusYVoltage = solarPanelMinusYVoltage;
	}

	public float getSolarPanelPlusXCurrent() {
		return solarPanelPlusXCurrent;
	}

	public void setSolarPanelPlusXCurrent(float solarPanelPlusXCurrent) {
		this.solarPanelPlusXCurrent = solarPanelPlusXCurrent;
	}

	public float getSolarPanelPlusXVoltage() {
		return solarPanelPlusXVoltage;
	}

	public void setSolarPanelPlusXVoltage(float solarPanelPlusXVoltage) {
		this.solarPanelPlusXVoltage = solarPanelPlusXVoltage;
	}

	public float getSolarPanelPlusYCurrent() {
		return solarPanelPlusYCurrent;
	}

	public void setSolarPanelPlusYCurrent(float solarPanelPlusYCurrent) {
		this.solarPanelPlusYCurrent = solarPanelPlusYCurrent;
	}

	public float getSolarPanelPlusYVoltage() {
		return solarPanelPlusYVoltage;
	}

	public void setSolarPanelPlusYVoltage(float solarPanelPlusYVoltage) {
		this.solarPanelPlusYVoltage = solarPanelPlusYVoltage;
	}

	public float getSolarPanelMinusYTemperature() {
		return solarPanelMinusYTemperature;
	}

	public void setSolarPanelMinusYTemperature(float solarPanelMinusYTemperature) {
		this.solarPanelMinusYTemperature = solarPanelMinusYTemperature;
	}

	public float getSolarPanelPlusXTemperature() {
		return solarPanelPlusXTemperature;
	}

	public void setSolarPanelPlusXTemperature(float solarPanelPlusXTemperature) {
		this.solarPanelPlusXTemperature = solarPanelPlusXTemperature;
	}

	public float getSolarPanelPlusYTemperature() {
		return solarPanelPlusYTemperature;
	}

	public void setSolarPanelPlusYTemperature(float solarPanelPlusYTemperature) {
		this.solarPanelPlusYTemperature = solarPanelPlusYTemperature;
	}

	public float getBatteryChargeCurrent() {
		return batteryChargeCurrent;
	}

	public void setBatteryChargeCurrent(float batteryChargeCurrent) {
		this.batteryChargeCurrent = batteryChargeCurrent;
	}

	public float getBatteryDischargeCurrent() {
		return batteryDischargeCurrent;
	}

	public void setBatteryDischargeCurrent(float batteryDischargeCurrent) {
		this.batteryDischargeCurrent = batteryDischargeCurrent;
	}

	public float getBatteryTemperature() {
		return batteryTemperature;
	}

	public void setBatteryTemperature(float batteryTemperature) {
		this.batteryTemperature = batteryTemperature;
	}

	public long getXp() {
		return xp;
	}

	public void setXp(long xp) {
		this.xp = xp;
	}

	public float getSpsX() {
		return spsX;
	}

	public void setSpsX(float spsX) {
		this.spsX = spsX;
	}

	public float getSpsY() {
		return spsY;
	}

	public void setSpsY(float spsY) {
		this.spsY = spsY;
	}

	private static int decodeFlightModel(int raw) {
		int result = (raw & 0x0030) >> 4;
		if (result == 3) {
			return 2;
		}
		if (result == 4) {
			return 3;
		}
		return result;
	}
}
