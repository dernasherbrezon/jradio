package ru.r2cloud.jradio.fox;

public class IhuDiagnostic {

	private static final int SPININFO_1 = 0;
	private static final int SPININFO_2 = 1;
	private static final int SPININFO_3 = 2;
	private static final int SPININFO_4 = 3;
	private static final int LAST3_DL_STATES = 4;
	private static final int COMMAND_RING = 5;
	private static final int COMMAND_COUNT = 6;
	private static final int I2C1_ERRORS = 7;
	private static final int I2C2_ERRORS = 8;
	private static final int GYRO1Z_DATA = 11;
	private static final int GYRO1V_DATA = 12;
	private static final int GYRO2V_DATA = 13;
	private static final int UNKNOWN = 15;
	private static final int IHU_SW_VERSION = 14;
	private static final int ISIS_STATUS = 16;
	private static final int IHU_TEMP_CALIBRATION_VOLTAGE = 17;
	private static final int AUTO_SAFE_VOLTAGES = 18;

	private Integer spin1;
	private Integer spin2;
	private Integer spin3;
	private Integer spin4;

	private IhuDownlinkState downlinkState1;
	private IhuDownlinkState downlinkState2;
	private IhuDownlinkState downlinkState3;

	private Integer uplinkedCommands1;
	private Integer uplinkedCommands2;
	private Integer uplinkedCommands3;
	private Integer uplinkedCommands4;
	private Integer uplinkedCommands5;
	private Integer uplinkedCommands6;

	private Integer commandsReceivedSinceBoot;

	private Integer writeTimeout;
	private Integer busBusyTimeout;
	private Integer readTimeout;

	private Integer writeTimeout2;
	private Integer busBusyTimeout2;
	private Integer readTimeout2;

	private Integer antStatus;
	private Integer bus0Status;
	private Integer bus1Status;

	private Float busVoltage;

	private Integer tempCalibrationVoltage;

	private Integer swType;
	private Integer swMajor;
	private Integer swMinor;

	private Float autoSafeVoltageIn;
	private Float autoSafeVoltageOut;

	private Float gyro1Volt;
	private Integer cameraChecksumErrors;

	private Float gyro2Volt;
	private Integer hsAudioBufferUnderflows;

	private Float gyro1Z;

	public IhuDiagnostic() {
		// do nothing
	}

	public IhuDiagnostic(int rawValue, String lookupTablePrefix) {
		int type = rawValue & 0xff;
		String ihuvBatteryTableName = lookupTablePrefix + "_IHUVBATT";
		switch (type) {
		case SPININFO_1:
			spin1 = rawValue >> 8;
			break;
		case SPININFO_2:
			spin2 = rawValue >> 8;
			break;
		case SPININFO_3:
			spin3 = rawValue >> 8;
			break;
		case SPININFO_4:
			spin4 = rawValue >> 8;
			break;
		case LAST3_DL_STATES: // Last3 states of DownlinkControl State Machine
			downlinkState1 = IhuDownlinkState.valueOfCode((rawValue >> 8) & 0xff);
			downlinkState2 = IhuDownlinkState.valueOfCode((rawValue >> 16) & 0xff);
			downlinkState3 = IhuDownlinkState.valueOfCode((rawValue >> 24) & 0xff);
			break;
		case COMMAND_RING: // CommandRing of last 5 uplink commands
			uplinkedCommands1 = (rawValue >> 8) & 0x0f; // first nibble after the 8 type bits
			uplinkedCommands2 = (rawValue >> 12) & 0x0f;
			uplinkedCommands3 = (rawValue >> 16) & 0x0f;
			uplinkedCommands4 = (rawValue >> 20) & 0x0f;
			uplinkedCommands5 = (rawValue >> 24) & 0x0f;
			uplinkedCommands6 = (rawValue >> 28) & 0x0f;
			break;
		case COMMAND_COUNT: // CommandCount - number of commands received since boot
			commandsReceivedSinceBoot = (rawValue >> 8) & 0xffffff; // 24 bit value after the type
			break;
		case I2C1_ERRORS: // I2C1Errors
			writeTimeout = (rawValue >> 8) & 0xff;
			busBusyTimeout = (rawValue >> 16) & 0xff;
			readTimeout = (rawValue >> 24) & 0xff;
			break;
		case I2C2_ERRORS: // I2C2Errors
			writeTimeout2 = (rawValue >> 8) & 0xff;
			busBusyTimeout2 = (rawValue >> 16) & 0xff;
			readTimeout2 = (rawValue >> 24) & 0xff;
			break;
		case GYRO1Z_DATA: // Gyro1Z - this is the "extra" Z axis reading. We have 2 chips, each with 2 axis. So we have Z twice
			gyro1Z = PayloadData.calcMemsValue((rawValue >> 8) & 0xfff, 2045);
			break;
		case GYRO1V_DATA: // Gyro1V
			gyro1Volt = LookupTables.lookup(ihuvBatteryTableName, (rawValue >> 8) & 0xfff) / 2;
			cameraChecksumErrors = (rawValue >> 24) & 0xff; // last 8 bits
			cameraChecksumErrors = cameraChecksumErrors - 1; // This is initialized to 1, so we subtract that initial value
			break;
		case GYRO2V_DATA: // Gyro2V
			gyro2Volt = LookupTables.lookup(ihuvBatteryTableName, (rawValue >> 8) & 0xfff) / 2;
			hsAudioBufferUnderflows = (rawValue >> 24) & 0xff; // last 8 bits
			break;
		case IHU_SW_VERSION: // Version of the software on the IHU
			swType = (rawValue >> 8) & 0xff;
			swMajor = (rawValue >> 16) & 0xff;
			swMinor = (rawValue >> 24) & 0xff;
			break;
		case ISIS_STATUS: // Version of the software on the IHU
			antStatus = (rawValue >> 8) & 0xffff; // shift away the type byte and get 16 bit status
			// Bus status - 0 - did not try to read, 1 tried and failed, tried and succeeded
			bus0Status = (rawValue >> 24) & 0xf;
			bus1Status = (rawValue >> 28) & 0xf;
			break;
		case UNKNOWN: // IHU measurement of bus voltage
			busVoltage = LookupTables.lookup(ihuvBatteryTableName, (rawValue >> 8) & 0xfff);
			break;
		case IHU_TEMP_CALIBRATION_VOLTAGE: // IHU measurement of bus voltage
			tempCalibrationVoltage = (rawValue >> 8) & 0xffffff; // 24 bits of temp calibration
			break;
		case AUTO_SAFE_VOLTAGES:
			autoSafeVoltageIn = LookupTables.lookup(ihuvBatteryTableName, (rawValue >> 8) & 0xfff) * 99 / 25;
			autoSafeVoltageOut = LookupTables.lookup(ihuvBatteryTableName, (rawValue >> 20) & 0xfff) * 99 / 25;
			break;
		default:
			break;
		}
	}

	public Integer getSpin1() {
		return spin1;
	}

	public void setSpin1(Integer spin1) {
		this.spin1 = spin1;
	}

	public Integer getSpin2() {
		return spin2;
	}

	public void setSpin2(Integer spin2) {
		this.spin2 = spin2;
	}

	public Integer getSpin3() {
		return spin3;
	}

	public void setSpin3(Integer spin3) {
		this.spin3 = spin3;
	}

	public Integer getSpin4() {
		return spin4;
	}

	public void setSpin4(Integer spin4) {
		this.spin4 = spin4;
	}

	public IhuDownlinkState getDownlinkState1() {
		return downlinkState1;
	}

	public void setDownlinkState1(IhuDownlinkState downlinkState1) {
		this.downlinkState1 = downlinkState1;
	}

	public IhuDownlinkState getDownlinkState2() {
		return downlinkState2;
	}

	public void setDownlinkState2(IhuDownlinkState downlinkState2) {
		this.downlinkState2 = downlinkState2;
	}

	public IhuDownlinkState getDownlinkState3() {
		return downlinkState3;
	}

	public void setDownlinkState3(IhuDownlinkState downlinkState3) {
		this.downlinkState3 = downlinkState3;
	}

	public Integer getUplinkedCommands1() {
		return uplinkedCommands1;
	}

	public void setUplinkedCommands1(Integer uplinkedCommands1) {
		this.uplinkedCommands1 = uplinkedCommands1;
	}

	public Integer getUplinkedCommands2() {
		return uplinkedCommands2;
	}

	public void setUplinkedCommands2(Integer uplinkedCommands2) {
		this.uplinkedCommands2 = uplinkedCommands2;
	}

	public Integer getUplinkedCommands3() {
		return uplinkedCommands3;
	}

	public void setUplinkedCommands3(Integer uplinkedCommands3) {
		this.uplinkedCommands3 = uplinkedCommands3;
	}

	public Integer getUplinkedCommands4() {
		return uplinkedCommands4;
	}

	public void setUplinkedCommands4(Integer uplinkedCommands4) {
		this.uplinkedCommands4 = uplinkedCommands4;
	}

	public Integer getUplinkedCommands5() {
		return uplinkedCommands5;
	}

	public void setUplinkedCommands5(Integer uplinkedCommands5) {
		this.uplinkedCommands5 = uplinkedCommands5;
	}

	public Integer getUplinkedCommands6() {
		return uplinkedCommands6;
	}

	public void setUplinkedCommands6(Integer uplinkedCommands6) {
		this.uplinkedCommands6 = uplinkedCommands6;
	}

	public Integer getCommandsReceivedSinceBoot() {
		return commandsReceivedSinceBoot;
	}

	public void setCommandsReceivedSinceBoot(Integer commandsReceivedSinceBoot) {
		this.commandsReceivedSinceBoot = commandsReceivedSinceBoot;
	}

	public Integer getWriteTimeout() {
		return writeTimeout;
	}

	public void setWriteTimeout(Integer writeTimeout) {
		this.writeTimeout = writeTimeout;
	}

	public Integer getBusBusyTimeout() {
		return busBusyTimeout;
	}

	public void setBusBusyTimeout(Integer busBusyTimeout) {
		this.busBusyTimeout = busBusyTimeout;
	}

	public Integer getReadTimeout() {
		return readTimeout;
	}

	public void setReadTimeout(Integer readTimeout) {
		this.readTimeout = readTimeout;
	}

	public Integer getWriteTimeout2() {
		return writeTimeout2;
	}

	public void setWriteTimeout2(Integer writeTimeout2) {
		this.writeTimeout2 = writeTimeout2;
	}

	public Integer getBusBusyTimeout2() {
		return busBusyTimeout2;
	}

	public void setBusBusyTimeout2(Integer busBusyTimeout2) {
		this.busBusyTimeout2 = busBusyTimeout2;
	}

	public Integer getReadTimeout2() {
		return readTimeout2;
	}

	public void setReadTimeout2(Integer readTimeout2) {
		this.readTimeout2 = readTimeout2;
	}

	public Integer getAntStatus() {
		return antStatus;
	}

	public void setAntStatus(Integer antStatus) {
		this.antStatus = antStatus;
	}

	public Integer getBus0Status() {
		return bus0Status;
	}

	public void setBus0Status(Integer bus0Status) {
		this.bus0Status = bus0Status;
	}

	public Integer getBus1Status() {
		return bus1Status;
	}

	public void setBus1Status(Integer bus1Status) {
		this.bus1Status = bus1Status;
	}

	public Float getBusVoltage() {
		return busVoltage;
	}

	public void setBusVoltage(Float busVoltage) {
		this.busVoltage = busVoltage;
	}

	public Integer getTempCalibrationVoltage() {
		return tempCalibrationVoltage;
	}

	public void setTempCalibrationVoltage(Integer tempCalibrationVoltage) {
		this.tempCalibrationVoltage = tempCalibrationVoltage;
	}

	public Integer getSwType() {
		return swType;
	}

	public void setSwType(Integer swType) {
		this.swType = swType;
	}

	public Integer getSwMajor() {
		return swMajor;
	}

	public void setSwMajor(Integer swMajor) {
		this.swMajor = swMajor;
	}

	public Integer getSwMinor() {
		return swMinor;
	}

	public void setSwMinor(Integer swMinor) {
		this.swMinor = swMinor;
	}

	public Float getAutoSafeVoltageIn() {
		return autoSafeVoltageIn;
	}

	public void setAutoSafeVoltageIn(Float autoSafeVoltageIn) {
		this.autoSafeVoltageIn = autoSafeVoltageIn;
	}

	public Float getAutoSafeVoltageOut() {
		return autoSafeVoltageOut;
	}

	public void setAutoSafeVoltageOut(Float autoSafeVoltageOut) {
		this.autoSafeVoltageOut = autoSafeVoltageOut;
	}

	public Float getGyro1Volt() {
		return gyro1Volt;
	}

	public void setGyro1Volt(Float gyro1Volt) {
		this.gyro1Volt = gyro1Volt;
	}

	public Integer getCameraChecksumErrors() {
		return cameraChecksumErrors;
	}

	public void setCameraChecksumErrors(Integer cameraChecksumErrors) {
		this.cameraChecksumErrors = cameraChecksumErrors;
	}

	public Float getGyro2Volt() {
		return gyro2Volt;
	}

	public void setGyro2Volt(Float gyro2Volt) {
		this.gyro2Volt = gyro2Volt;
	}

	public Integer getHsAudioBufferUnderflows() {
		return hsAudioBufferUnderflows;
	}

	public void setHsAudioBufferUnderflows(Integer hsAudioBufferUnderflows) {
		this.hsAudioBufferUnderflows = hsAudioBufferUnderflows;
	}

	public Float getGyro1Z() {
		return gyro1Z;
	}

	public void setGyro1Z(Float gyro1z) {
		gyro1Z = gyro1z;
	}

}
