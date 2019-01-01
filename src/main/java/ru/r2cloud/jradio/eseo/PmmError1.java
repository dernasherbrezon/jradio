package ru.r2cloud.jradio.eseo;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class PmmError1 {

	private boolean SolarPanel1Sensor1TemperatureOutOfRange;
	private boolean SolarPanel1Sensor2TemperatureOutOfRange;
	private boolean SolarPanel2Sensor1TemperatureOutOfRange;
	private boolean SolarPanel2Sensor2TemperatureOutOfRange;
	private boolean SolarPanel3Sensor1TemperatureOutOfRange;
	private boolean SolarPanel3Sensor2TemperatureOutOfRange;
	private boolean BatteryPack1CurrentOutOfRange;
	private boolean BatteryPack2CurrentOutOfRange;
	private boolean BatteryPack3CurrentOutOfRange;
	private boolean BatteryPack4CurrentOutOfRange;
	private boolean BatteryPack5CurrentOutOfRange;
	private boolean BatteryPack6CurrentOutOfRange;
	private boolean Batterypack1Sensor1TemperatureOutOfRange;
	private boolean Batterypack1Sensor2TemperatureOutOfRange;
	private boolean Batterypack2Sensor1TemperatureOutOfRange;
	private boolean Batterypack2Sensor2TemperatureOutOfRange;
	private boolean Batterypack3Sensor1TemperatureOutOfRange;
	private boolean Batterypack3Sensor2TemperatureOutOfRange;
	private boolean Batterypack4Sensor1TemperatureOutOfRange;
	private boolean Batterypack4Sensor2TemperatureOutOfRange;
	private boolean Batterypack5Sensor1TemperatureOutOfRange;
	private boolean Batterypack5Sensor2TemperatureOutOfRange;
	private boolean Batterypack6Sensor1TemperatureOutOfRange;
	private boolean Batterypack6Sensor2TemperatureOutOfRange;

	private boolean MainBusVoltageoOutOfRange;
	private boolean PowerBoardSensor1TemperatureOutOfRange;
	private boolean PowerBoardSensor2TemperatureOutOfRange;

	public PmmError1(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		SolarPanel1Sensor1TemperatureOutOfRange = ((raw >> 7) & 0x1) > 0;
		SolarPanel1Sensor2TemperatureOutOfRange = ((raw >> 6) & 0x1) > 0;
		SolarPanel2Sensor1TemperatureOutOfRange = ((raw >> 5) & 0x1) > 0;
		SolarPanel2Sensor2TemperatureOutOfRange = ((raw >> 4) & 0x1) > 0;
		SolarPanel3Sensor1TemperatureOutOfRange = ((raw >> 3) & 0x1) > 0;
		SolarPanel3Sensor2TemperatureOutOfRange = ((raw >> 2) & 0x1) > 0;
		BatteryPack1CurrentOutOfRange 			= ((raw >> 0) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		BatteryPack2CurrentOutOfRange = ((raw >> 7) & 0x1) > 0;
		BatteryPack3CurrentOutOfRange = ((raw >> 6) & 0x1) > 0;
		BatteryPack4CurrentOutOfRange = ((raw >> 5) & 0x1) > 0;
		BatteryPack5CurrentOutOfRange = ((raw >> 4) & 0x1) > 0;
		BatteryPack6CurrentOutOfRange = ((raw >> 3) & 0x1) > 0;
		Batterypack1Sensor1TemperatureOutOfRange = ((raw >> 2) & 0x1) > 0;
		Batterypack1Sensor2TemperatureOutOfRange = ((raw >> 1) & 0x1) > 0;
		Batterypack2Sensor1TemperatureOutOfRange = ((raw >> 0) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		Batterypack2Sensor2TemperatureOutOfRange = ((raw >> 7) & 0x1) > 0;
		Batterypack3Sensor1TemperatureOutOfRange = ((raw >> 6) & 0x1) > 0;
		Batterypack3Sensor2TemperatureOutOfRange = ((raw >> 5) & 0x1) > 0;
		Batterypack4Sensor1TemperatureOutOfRange = ((raw >> 4) & 0x1) > 0;
		Batterypack4Sensor2TemperatureOutOfRange = ((raw >> 3) & 0x1) > 0;
		Batterypack5Sensor1TemperatureOutOfRange = ((raw >> 2) & 0x1) > 0;
		Batterypack5Sensor2TemperatureOutOfRange = ((raw >> 1) & 0x1) > 0;
		Batterypack6Sensor1TemperatureOutOfRange = ((raw >> 0) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		Batterypack6Sensor2TemperatureOutOfRange = ((raw >> 7) & 0x1) > 0;

		MainBusVoltageoOutOfRange = ((raw >> 5) & 0x1) > 0;
		PowerBoardSensor1TemperatureOutOfRange = ((raw >> 4) & 0x1) > 0;
		PowerBoardSensor2TemperatureOutOfRange = ((raw >> 3) & 0x1) > 0;
	}

	public boolean isSolarPanel1Sensor1TemperatureOutOfRange() {
		return SolarPanel1Sensor1TemperatureOutOfRange;
	}

	public void setSolarPanel1Sensor1TemperatureOutOfRange(boolean solarPanel1Sensor1TemperatureOutOfRange) {
		SolarPanel1Sensor1TemperatureOutOfRange = solarPanel1Sensor1TemperatureOutOfRange;
	}

	public boolean isSolarPanel1Sensor2TemperatureOutOfRange() {
		return SolarPanel1Sensor2TemperatureOutOfRange;
	}

	public void setSolarPanel1Sensor2TemperatureOutOfRange(boolean solarPanel1Sensor2TemperatureOutOfRange) {
		SolarPanel1Sensor2TemperatureOutOfRange = solarPanel1Sensor2TemperatureOutOfRange;
	}

	public boolean isSolarPanel2Sensor1TemperatureOutOfRange() {
		return SolarPanel2Sensor1TemperatureOutOfRange;
	}

	public void setSolarPanel2Sensor1TemperatureOutOfRange(boolean solarPanel2Sensor1TemperatureOutOfRange) {
		SolarPanel2Sensor1TemperatureOutOfRange = solarPanel2Sensor1TemperatureOutOfRange;
	}

	public boolean isSolarPanel2Sensor2TemperatureOutOfRange() {
		return SolarPanel2Sensor2TemperatureOutOfRange;
	}

	public void setSolarPanel2Sensor2TemperatureOutOfRange(boolean solarPanel2Sensor2TemperatureOutOfRange) {
		SolarPanel2Sensor2TemperatureOutOfRange = solarPanel2Sensor2TemperatureOutOfRange;
	}

	public boolean isSolarPanel3Sensor1TemperatureOutOfRange() {
		return SolarPanel3Sensor1TemperatureOutOfRange;
	}

	public void setSolarPanel3Sensor1TemperatureOutOfRange(boolean solarPanel3Sensor1TemperatureOutOfRange) {
		SolarPanel3Sensor1TemperatureOutOfRange = solarPanel3Sensor1TemperatureOutOfRange;
	}

	public boolean isSolarPanel3Sensor2TemperatureOutOfRange() {
		return SolarPanel3Sensor2TemperatureOutOfRange;
	}

	public void setSolarPanel3Sensor2TemperatureOutOfRange(boolean solarPanel3Sensor2TemperatureOutOfRange) {
		SolarPanel3Sensor2TemperatureOutOfRange = solarPanel3Sensor2TemperatureOutOfRange;
	}

	public boolean isBatteryPack1CurrentOutOfRange() {
		return BatteryPack1CurrentOutOfRange;
	}

	public void setBatteryPack1CurrentOutOfRange(boolean batteryPack1CurrentOutOfRange) {
		BatteryPack1CurrentOutOfRange = batteryPack1CurrentOutOfRange;
	}

	public boolean isBatteryPack2CurrentOutOfRange() {
		return BatteryPack2CurrentOutOfRange;
	}

	public void setBatteryPack2CurrentOutOfRange(boolean batteryPack2CurrentOutOfRange) {
		BatteryPack2CurrentOutOfRange = batteryPack2CurrentOutOfRange;
	}

	public boolean isBatteryPack3CurrentOutOfRange() {
		return BatteryPack3CurrentOutOfRange;
	}

	public void setBatteryPack3CurrentOutOfRange(boolean batteryPack3CurrentOutOfRange) {
		BatteryPack3CurrentOutOfRange = batteryPack3CurrentOutOfRange;
	}

	public boolean isBatteryPack4CurrentOutOfRange() {
		return BatteryPack4CurrentOutOfRange;
	}

	public void setBatteryPack4CurrentOutOfRange(boolean batteryPack4CurrentOutOfRange) {
		BatteryPack4CurrentOutOfRange = batteryPack4CurrentOutOfRange;
	}

	public boolean isBatteryPack5CurrentOutOfRange() {
		return BatteryPack5CurrentOutOfRange;
	}

	public void setBatteryPack5CurrentOutOfRange(boolean batteryPack5CurrentOutOfRange) {
		BatteryPack5CurrentOutOfRange = batteryPack5CurrentOutOfRange;
	}

	public boolean isBatteryPack6CurrentOutOfRange() {
		return BatteryPack6CurrentOutOfRange;
	}

	public void setBatteryPack6CurrentOutOfRange(boolean batteryPack6CurrentOutOfRange) {
		BatteryPack6CurrentOutOfRange = batteryPack6CurrentOutOfRange;
	}

	public boolean isBatterypack1Sensor1TemperatureOutOfRange() {
		return Batterypack1Sensor1TemperatureOutOfRange;
	}

	public void setBatterypack1Sensor1TemperatureOutOfRange(boolean batterypack1Sensor1TemperatureOutOfRange) {
		Batterypack1Sensor1TemperatureOutOfRange = batterypack1Sensor1TemperatureOutOfRange;
	}

	public boolean isBatterypack1Sensor2TemperatureOutOfRange() {
		return Batterypack1Sensor2TemperatureOutOfRange;
	}

	public void setBatterypack1Sensor2TemperatureOutOfRange(boolean batterypack1Sensor2TemperatureOutOfRange) {
		Batterypack1Sensor2TemperatureOutOfRange = batterypack1Sensor2TemperatureOutOfRange;
	}

	public boolean isBatterypack2Sensor1TemperatureOutOfRange() {
		return Batterypack2Sensor1TemperatureOutOfRange;
	}

	public void setBatterypack2Sensor1TemperatureOutOfRange(boolean batterypack2Sensor1TemperatureOutOfRange) {
		Batterypack2Sensor1TemperatureOutOfRange = batterypack2Sensor1TemperatureOutOfRange;
	}

	public boolean isBatterypack2Sensor2TemperatureOutOfRange() {
		return Batterypack2Sensor2TemperatureOutOfRange;
	}

	public void setBatterypack2Sensor2TemperatureOutOfRange(boolean batterypack2Sensor2TemperatureOutOfRange) {
		Batterypack2Sensor2TemperatureOutOfRange = batterypack2Sensor2TemperatureOutOfRange;
	}

	public boolean isBatterypack3Sensor1TemperatureOutOfRange() {
		return Batterypack3Sensor1TemperatureOutOfRange;
	}

	public void setBatterypack3Sensor1TemperatureOutOfRange(boolean batterypack3Sensor1TemperatureOutOfRange) {
		Batterypack3Sensor1TemperatureOutOfRange = batterypack3Sensor1TemperatureOutOfRange;
	}

	public boolean isBatterypack3Sensor2TemperatureOutOfRange() {
		return Batterypack3Sensor2TemperatureOutOfRange;
	}

	public void setBatterypack3Sensor2TemperatureOutOfRange(boolean batterypack3Sensor2TemperatureOutOfRange) {
		Batterypack3Sensor2TemperatureOutOfRange = batterypack3Sensor2TemperatureOutOfRange;
	}

	public boolean isBatterypack4Sensor1TemperatureOutOfRange() {
		return Batterypack4Sensor1TemperatureOutOfRange;
	}

	public void setBatterypack4Sensor1TemperatureOutOfRange(boolean batterypack4Sensor1TemperatureOutOfRange) {
		Batterypack4Sensor1TemperatureOutOfRange = batterypack4Sensor1TemperatureOutOfRange;
	}

	public boolean isBatterypack4Sensor2TemperatureOutOfRange() {
		return Batterypack4Sensor2TemperatureOutOfRange;
	}

	public void setBatterypack4Sensor2TemperatureOutOfRange(boolean batterypack4Sensor2TemperatureOutOfRange) {
		Batterypack4Sensor2TemperatureOutOfRange = batterypack4Sensor2TemperatureOutOfRange;
	}

	public boolean isBatterypack5Sensor1TemperatureOutOfRange() {
		return Batterypack5Sensor1TemperatureOutOfRange;
	}

	public void setBatterypack5Sensor1TemperatureOutOfRange(boolean batterypack5Sensor1TemperatureOutOfRange) {
		Batterypack5Sensor1TemperatureOutOfRange = batterypack5Sensor1TemperatureOutOfRange;
	}

	public boolean isBatterypack5Sensor2TemperatureOutOfRange() {
		return Batterypack5Sensor2TemperatureOutOfRange;
	}

	public void setBatterypack5Sensor2TemperatureOutOfRange(boolean batterypack5Sensor2TemperatureOutOfRange) {
		Batterypack5Sensor2TemperatureOutOfRange = batterypack5Sensor2TemperatureOutOfRange;
	}

	public boolean isBatterypack6Sensor1TemperatureOutOfRange() {
		return Batterypack6Sensor1TemperatureOutOfRange;
	}

	public void setBatterypack6Sensor1TemperatureOutOfRange(boolean batterypack6Sensor1TemperatureOutOfRange) {
		Batterypack6Sensor1TemperatureOutOfRange = batterypack6Sensor1TemperatureOutOfRange;
	}

	public boolean isBatterypack6Sensor2TemperatureOutOfRange() {
		return Batterypack6Sensor2TemperatureOutOfRange;
	}

	public void setBatterypack6Sensor2TemperatureOutOfRange(boolean batterypack6Sensor2TemperatureOutOfRange) {
		Batterypack6Sensor2TemperatureOutOfRange = batterypack6Sensor2TemperatureOutOfRange;
	}

	public boolean isMainBusVoltageoOutOfRange() {
		return MainBusVoltageoOutOfRange;
	}

	public void setMainBusVoltageoOutOfRange(boolean mainBusVoltageoOutOfRange) {
		MainBusVoltageoOutOfRange = mainBusVoltageoOutOfRange;
	}

	public boolean isPowerBoardSensor1TemperatureOutOfRange() {
		return PowerBoardSensor1TemperatureOutOfRange;
	}

	public void setPowerBoardSensor1TemperatureOutOfRange(boolean powerBoardSensor1TemperatureOutOfRange) {
		PowerBoardSensor1TemperatureOutOfRange = powerBoardSensor1TemperatureOutOfRange;
	}

	public boolean isPowerBoardSensor2TemperatureOutOfRange() {
		return PowerBoardSensor2TemperatureOutOfRange;
	}

	public void setPowerBoardSensor2TemperatureOutOfRange(boolean powerBoardSensor2TemperatureOutOfRange) {
		PowerBoardSensor2TemperatureOutOfRange = powerBoardSensor2TemperatureOutOfRange;
	}
	
	
}
