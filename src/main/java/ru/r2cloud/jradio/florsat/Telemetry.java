package ru.r2cloud.jradio.florsat;

import java.io.DataInputStream;
import java.io.IOException;

public class Telemetry {

	private boolean obdhStatusFlag;
	private boolean imuFlag;
	private boolean obdhMiscFlag;
	private boolean solarPanelsSensorsFlag;
	private boolean mainRadioFlag;
	private boolean solarPanelsFlag;
	private boolean epsMiscFlag;
	private boolean batteryMonitorFlag;
	private boolean temperaturesFlag;
	private boolean energyLevelFlag;
	private boolean payload1Flag;
	private boolean payload2Flag;
	private boolean wholeOrbitDataFlag;

	private int systemTimeSeconds;
	private int systemTimeMinutes;
	private int systemTimeHours;

	private Integer resetCounter;
	private Integer resetCause;
	private Integer clockFaultFlags;
	private Boolean imuStatus;
	private Boolean usdStatus;
	private Boolean rushStatus;
	private Boolean epsStatus;
	private Boolean antennaStatus;

	private Double imu1AccelX;
	private Double imu1AccelY;
	private Double imu1AccelZ;
	private Double imu1GyroX;
	private Double imu1GyroY;
	private Double imu1GyroZ;
	private Double imu2AccelX;
	private Double imu2AccelY;
	private Double imu2AccelZ;
	private Double imu2GyroX;
	private Double imu2GyroY;
	private Double imu2GyroZ;

	private Double mspTemperature;
	private Double supplyVoltage;
	private Double supplyCurrent;

	private int[] solarPanelsSensors;
	private int[] mainRadio;

	private double[] solarCurrent;
	private double[] solarVoltage;

	private Double boostVoltage;
	private Double mainPowerVoltage;
	private Double beaconEpsCurrent;
	private Double adcTemperature;

	private Double batAverageCurrent;
	private Double batTemperature;
	private Double bat1Voltage;
	private Double bat2Voltage;
	private Double batCurrent;
	private Double batAccumulatedCurrent;
	private Integer protectionRegister;
	private Integer statusRegister;
	private Integer cycleCounterRegister;
	private Double activeAbsoluteCapacity;
	private Double standbyAbsoluteCapacity;
	private Integer activeRelativeCapacity;
	private Integer standbyRelativeCapacity;

	private double[] rtdMeasurement;
	private Integer eps;
	private int[] payload1;
	private int[] payload2;

	public Telemetry() {
		// do nothing
	}

	public Telemetry(DataInputStream dis) throws IOException {
		int b1 = dis.readUnsignedByte();
		int b2 = dis.readUnsignedByte();
		int flags = (b2 << 8) | (b1);
		wholeOrbitDataFlag = (flags & 0x8000) > 0;
		if (wholeOrbitDataFlag) {
			flags = 0xffff;
		}
		obdhStatusFlag = (flags & 0x0001) > 0;
		imuFlag = (flags & 0x0002) > 0;
		obdhMiscFlag = (flags & 0x0004) > 0;
		solarPanelsSensorsFlag = (flags & 0x0008) > 0;
		mainRadioFlag = (flags & 0x0010) > 0;
		solarPanelsFlag = (flags & 0x0020) > 0;
		epsMiscFlag = (flags & 0x0040) > 0;
		batteryMonitorFlag = (flags & 0x0080) > 0;
		temperaturesFlag = (flags & 0x0100) > 0;
		energyLevelFlag = (flags & 0x0200) > 0;
		payload1Flag = (flags & 0x0400) > 0;
		payload2Flag = (flags & 0x0800) > 0;

		systemTimeSeconds = dis.readUnsignedByte();
		int time1 = dis.readUnsignedByte();
		int time2 = dis.readUnsignedByte();
		int time3 = dis.readUnsignedByte();
		systemTimeMinutes = ((time1 << 16) | (time2 << 8) | time3) % 60;
		systemTimeHours = ((time1 << 16) | (time2 << 8) | time3) / 60;

		if (obdhStatusFlag) {
			resetCounter = (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | (dis.readUnsignedByte());
			resetCause = dis.readUnsignedByte();
			clockFaultFlags = dis.readUnsignedByte();
			int test_module_flags = dis.readUnsignedByte();
			imuStatus = ((test_module_flags >> 4) & 1) > 0;
			usdStatus = ((test_module_flags >> 3) & 1) > 0;
			rushStatus = ((test_module_flags >> 1) & 1) > 0;
			epsStatus = ((test_module_flags) & 1) > 0;
			antennaStatus = ((test_module_flags >> 5) & 1) > 0;
		}

		if (imuFlag) {
			imu1AccelX = IMUAccelConv(dis.readShort());
			imu1AccelY = IMUAccelConv(dis.readShort());
			imu1AccelZ = IMUAccelConv(dis.readShort());
			imu1GyroX = IMUGyroConv(dis.readShort());
			imu1GyroY = IMUGyroConv(dis.readShort());
			imu1GyroZ = IMUGyroConv(dis.readShort());
			imu2AccelX = IMUAccelConv(dis.readShort());
			imu2AccelY = IMUAccelConv(dis.readShort());
			imu2AccelZ = IMUAccelConv(dis.readShort());
			imu2GyroX = IMUGyroConv(dis.readShort());
			imu2GyroY = IMUGyroConv(dis.readShort());
			imu2GyroZ = IMUGyroConv(dis.readShort());
		}
		if (obdhMiscFlag) {
			mspTemperature = MSPInternalTempConv(dis.readUnsignedShort());
			supplyVoltage = OBDHSupplyVoltConv(dis.readUnsignedShort());
			supplyCurrent = OBDHSupplyCurrentConv(dis.readUnsignedShort());
		}
		if (solarPanelsSensorsFlag) {
			solarPanelsSensors = readUnsignedBytes(dis, 12);
		}
		if (mainRadioFlag) {
			mainRadio = readUnsignedBytes(dis, 19);
		}
		if (solarPanelsFlag) {
			solarCurrent = new double[6];
			for (int i = 0; i < solarCurrent.length; i++) {
				solarCurrent[i] = SolarPanelCurrentConv(dis.readUnsignedShort());
			}
			solarVoltage = new double[3];
			for (int i = 0; i < solarVoltage.length; i++) {
				solarVoltage[i] = SolarPanelVoltageConv(dis.readUnsignedShort());
			}
		}
		if (epsMiscFlag) {
			boostVoltage = ADCVoltConv(dis.readUnsignedShort());
			mainPowerVoltage = ADCVoltConv(dis.readUnsignedShort());
			beaconEpsCurrent = BeaconEPSCurrentConv(dis.readUnsignedShort());
			adcTemperature = ADCInternalTempConv(dis.readUnsignedShort());
		}
		if (batteryMonitorFlag) {
			batAverageCurrent = BatCurrentConv(dis.readShort());
			batTemperature = BatMonitorTempConv(dis.readShort());
			bat1Voltage = BatVoltConv(dis.readShort());
			bat2Voltage = BatVoltConv(dis.readShort());
			batCurrent = BatCurrentConv(dis.readShort());
			batAccumulatedCurrent = BatAccumulatedCurrentConv(dis.readUnsignedShort());
			protectionRegister = dis.readUnsignedByte();
			statusRegister = dis.readUnsignedByte();
			cycleCounterRegister = dis.readUnsignedByte() * 2;
			activeAbsoluteCapacity = RemainingAbsoluteCapacityConv(dis.readUnsignedShort());
			standbyAbsoluteCapacity = RemainingAbsoluteCapacityConv(dis.readUnsignedShort());
			activeRelativeCapacity = dis.readUnsignedByte();
			standbyRelativeCapacity = dis.readUnsignedByte();
		}
		if (temperaturesFlag) {
			rtdMeasurement = new double[7];
			for (int i = 0; i < rtdMeasurement.length; i++) {
				rtdMeasurement[i] = ADCConv((dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | (dis.readUnsignedByte()));
			}
		}
		if (energyLevelFlag) {
			eps = dis.readUnsignedByte();
		}
		if (payload1Flag) {
			payload1 = readUnsignedBytes(dis, 40);
		}
		if (payload2Flag) {
			payload2 = readUnsignedBytes(dis, 7);
		}
	}

	private static double ADCConv(int val) {
		return ((val * (1.65 * 2 / Math.pow(2, 24)) * 1e3) - 1000) * 1 / 3.85;
	}

	private static double RemainingAbsoluteCapacityConv(int val) {
		return val * 1.6;
	}

	private static double BatAccumulatedCurrentConv(int val) {
		return val * (6.25e-6 / 0.01);
	}

	private static double BatVoltConv(short val) {
		return (val / 32.0f) * 4.883e-3;
	}

	private static double BatMonitorTempConv(short val) {
		return val * 0.125 / 32.0;
	}

	private static double BatCurrentConv(short val) {
		return val * (1.5625e-6 / 0.01);
	}

	private static double ADCInternalTempConv(int val) {
		return (val * (2.5 / 4095.0) - 0.680) * 70.0 / 0.170;
	}

	private static double BeaconEPSCurrentConv(int val) {
		return val * (2.5 / 4095.0) * (1.0 / (0.075 * 0.025 * 4020));
	}

	private static double ADCVoltConv(int val) {
		return val * (2.5 / 4095.0) * (300e3 + 100e3) / (100e3);
	}

	private static double SolarPanelCurrentConv(int val) {
		return val * (2.5 / 4095) * (1 / (0.05 * 0.025 * 3300));
	}

	private static double SolarPanelVoltageConv(int val) {
		return val * (2.5 / 4095) * (100e3 + 93.1e3) / 100e3;
	}

	private static int[] readUnsignedBytes(DataInputStream dis, int size) throws IOException {
		int[] result = new int[size];
		for (int i = 0; i < result.length; i++) {
			result[i] = dis.readUnsignedByte();
		}
		return result;
	}

	private static double IMUAccelConv(short val) {
		return val * 16.0 / 32768.0;
	}

	private static double IMUGyroConv(short val) {
		return val * 250.0 / 32768.0;
	}

	private static double MSPInternalTempConv(int val) {
		return ((val * 2.0 - 2048.0) * 55.0) / (2432.0 - 2048.0) + 30.0; // CAL1 = 2145 and CAL2 = 2508 for the OBDH MSP (F6659)
	}

	private static double OBDHSupplyVoltConv(int val) {
		return (val * 2 * 3.0) / 4095.0;
	}

	private static double OBDHSupplyCurrentConv(int val) {
		return (1000 * val * 3.0) / (4095 * 0.05 * 0.025 * 20000);
	}

	public boolean isObdhStatusFlag() {
		return obdhStatusFlag;
	}

	public void setObdhStatusFlag(boolean obdhStatusFlag) {
		this.obdhStatusFlag = obdhStatusFlag;
	}

	public boolean isImuFlag() {
		return imuFlag;
	}

	public void setImuFlag(boolean imuFlag) {
		this.imuFlag = imuFlag;
	}

	public boolean isObdhMiscFlag() {
		return obdhMiscFlag;
	}

	public void setObdhMiscFlag(boolean obdhMiscFlag) {
		this.obdhMiscFlag = obdhMiscFlag;
	}

	public boolean isSolarPanelsSensorsFlag() {
		return solarPanelsSensorsFlag;
	}

	public void setSolarPanelsSensorsFlag(boolean solarPanelsSensorsFlag) {
		this.solarPanelsSensorsFlag = solarPanelsSensorsFlag;
	}

	public boolean isMainRadioFlag() {
		return mainRadioFlag;
	}

	public void setMainRadioFlag(boolean mainRadioFlag) {
		this.mainRadioFlag = mainRadioFlag;
	}

	public boolean isSolarPanelsFlag() {
		return solarPanelsFlag;
	}

	public void setSolarPanelsFlag(boolean solarPanelsFlag) {
		this.solarPanelsFlag = solarPanelsFlag;
	}

	public boolean isEpsMiscFlag() {
		return epsMiscFlag;
	}

	public void setEpsMiscFlag(boolean epsMiscFlag) {
		this.epsMiscFlag = epsMiscFlag;
	}

	public boolean isBatteryMonitorFlag() {
		return batteryMonitorFlag;
	}

	public void setBatteryMonitorFlag(boolean batteryMonitorFlag) {
		this.batteryMonitorFlag = batteryMonitorFlag;
	}

	public boolean isTemperaturesFlag() {
		return temperaturesFlag;
	}

	public void setTemperaturesFlag(boolean temperaturesFlag) {
		this.temperaturesFlag = temperaturesFlag;
	}

	public boolean isEnergyLevelFlag() {
		return energyLevelFlag;
	}

	public void setEnergyLevelFlag(boolean energyLevelFlag) {
		this.energyLevelFlag = energyLevelFlag;
	}

	public boolean isPayload1Flag() {
		return payload1Flag;
	}

	public void setPayload1Flag(boolean payload1Flag) {
		this.payload1Flag = payload1Flag;
	}

	public boolean isPayload2Flag() {
		return payload2Flag;
	}

	public void setPayload2Flag(boolean payload2Flag) {
		this.payload2Flag = payload2Flag;
	}

	public boolean isWholeOrbitDataFlag() {
		return wholeOrbitDataFlag;
	}

	public void setWholeOrbitDataFlag(boolean wholeOrbitDataFlag) {
		this.wholeOrbitDataFlag = wholeOrbitDataFlag;
	}

	public int getSystemTimeSeconds() {
		return systemTimeSeconds;
	}

	public void setSystemTimeSeconds(int systemTimeSeconds) {
		this.systemTimeSeconds = systemTimeSeconds;
	}

	public int getSystemTimeMinutes() {
		return systemTimeMinutes;
	}

	public void setSystemTimeMinutes(int systemTimeMinutes) {
		this.systemTimeMinutes = systemTimeMinutes;
	}

	public int getSystemTimeHours() {
		return systemTimeHours;
	}

	public void setSystemTimeHours(int systemTimeHours) {
		this.systemTimeHours = systemTimeHours;
	}

	public Integer getResetCounter() {
		return resetCounter;
	}

	public void setResetCounter(Integer resetCounter) {
		this.resetCounter = resetCounter;
	}

	public Integer getResetCause() {
		return resetCause;
	}

	public void setResetCause(Integer resetCause) {
		this.resetCause = resetCause;
	}

	public Integer getClockFaultFlags() {
		return clockFaultFlags;
	}

	public void setClockFaultFlags(Integer clockFaultFlags) {
		this.clockFaultFlags = clockFaultFlags;
	}

	public Boolean getImuStatus() {
		return imuStatus;
	}

	public void setImuStatus(Boolean imuStatus) {
		this.imuStatus = imuStatus;
	}

	public Boolean getUsdStatus() {
		return usdStatus;
	}

	public void setUsdStatus(Boolean usdStatus) {
		this.usdStatus = usdStatus;
	}

	public Boolean getRushStatus() {
		return rushStatus;
	}

	public void setRushStatus(Boolean rushStatus) {
		this.rushStatus = rushStatus;
	}

	public Boolean getEpsStatus() {
		return epsStatus;
	}

	public void setEpsStatus(Boolean epsStatus) {
		this.epsStatus = epsStatus;
	}

	public Boolean getAntennaStatus() {
		return antennaStatus;
	}

	public void setAntennaStatus(Boolean antennaStatus) {
		this.antennaStatus = antennaStatus;
	}

	public Double getImu1AccelX() {
		return imu1AccelX;
	}

	public void setImu1AccelX(Double imu1AccelX) {
		this.imu1AccelX = imu1AccelX;
	}

	public Double getImu1AccelY() {
		return imu1AccelY;
	}

	public void setImu1AccelY(Double imu1AccelY) {
		this.imu1AccelY = imu1AccelY;
	}

	public Double getImu1AccelZ() {
		return imu1AccelZ;
	}

	public void setImu1AccelZ(Double imu1AccelZ) {
		this.imu1AccelZ = imu1AccelZ;
	}

	public Double getImu1GyroX() {
		return imu1GyroX;
	}

	public void setImu1GyroX(Double imu1GyroX) {
		this.imu1GyroX = imu1GyroX;
	}

	public Double getImu1GyroY() {
		return imu1GyroY;
	}

	public void setImu1GyroY(Double imu1GyroY) {
		this.imu1GyroY = imu1GyroY;
	}

	public Double getImu1GyroZ() {
		return imu1GyroZ;
	}

	public void setImu1GyroZ(Double imu1GyroZ) {
		this.imu1GyroZ = imu1GyroZ;
	}

	public Double getImu2AccelX() {
		return imu2AccelX;
	}

	public void setImu2AccelX(Double imu2AccelX) {
		this.imu2AccelX = imu2AccelX;
	}

	public Double getImu2AccelY() {
		return imu2AccelY;
	}

	public void setImu2AccelY(Double imu2AccelY) {
		this.imu2AccelY = imu2AccelY;
	}

	public Double getImu2AccelZ() {
		return imu2AccelZ;
	}

	public void setImu2AccelZ(Double imu2AccelZ) {
		this.imu2AccelZ = imu2AccelZ;
	}

	public Double getImu2GyroX() {
		return imu2GyroX;
	}

	public void setImu2GyroX(Double imu2GyroX) {
		this.imu2GyroX = imu2GyroX;
	}

	public Double getImu2GyroY() {
		return imu2GyroY;
	}

	public void setImu2GyroY(Double imu2GyroY) {
		this.imu2GyroY = imu2GyroY;
	}

	public Double getImu2GyroZ() {
		return imu2GyroZ;
	}

	public void setImu2GyroZ(Double imu2GyroZ) {
		this.imu2GyroZ = imu2GyroZ;
	}

	public Double getMspTemperature() {
		return mspTemperature;
	}

	public void setMspTemperature(Double mspTemperature) {
		this.mspTemperature = mspTemperature;
	}

	public Double getSupplyVoltage() {
		return supplyVoltage;
	}

	public void setSupplyVoltage(Double supplyVoltage) {
		this.supplyVoltage = supplyVoltage;
	}

	public Double getSupplyCurrent() {
		return supplyCurrent;
	}

	public void setSupplyCurrent(Double supplyCurrent) {
		this.supplyCurrent = supplyCurrent;
	}

	public int[] getSolarPanelsSensors() {
		return solarPanelsSensors;
	}

	public void setSolarPanelsSensors(int[] solarPanelsSensors) {
		this.solarPanelsSensors = solarPanelsSensors;
	}

	public int[] getMainRadio() {
		return mainRadio;
	}

	public void setMainRadio(int[] mainRadio) {
		this.mainRadio = mainRadio;
	}

	public double[] getSolarCurrent() {
		return solarCurrent;
	}

	public void setSolarCurrent(double[] solarCurrent) {
		this.solarCurrent = solarCurrent;
	}

	public double[] getSolarVoltage() {
		return solarVoltage;
	}

	public void setSolarVoltage(double[] solarVoltage) {
		this.solarVoltage = solarVoltage;
	}

	public Double getBoostVoltage() {
		return boostVoltage;
	}

	public void setBoostVoltage(Double boostVoltage) {
		this.boostVoltage = boostVoltage;
	}

	public Double getMainPowerVoltage() {
		return mainPowerVoltage;
	}

	public void setMainPowerVoltage(Double mainPowerVoltage) {
		this.mainPowerVoltage = mainPowerVoltage;
	}

	public Double getBeaconEpsCurrent() {
		return beaconEpsCurrent;
	}

	public void setBeaconEpsCurrent(Double beaconEpsCurrent) {
		this.beaconEpsCurrent = beaconEpsCurrent;
	}

	public Double getAdcTemperature() {
		return adcTemperature;
	}

	public void setAdcTemperature(Double adcTemperature) {
		this.adcTemperature = adcTemperature;
	}

	public Double getBatAverageCurrent() {
		return batAverageCurrent;
	}

	public void setBatAverageCurrent(Double batAverageCurrent) {
		this.batAverageCurrent = batAverageCurrent;
	}

	public Double getBatTemperature() {
		return batTemperature;
	}

	public void setBatTemperature(Double batTemperature) {
		this.batTemperature = batTemperature;
	}

	public Double getBat1Voltage() {
		return bat1Voltage;
	}

	public void setBat1Voltage(Double bat1Voltage) {
		this.bat1Voltage = bat1Voltage;
	}

	public Double getBat2Voltage() {
		return bat2Voltage;
	}

	public void setBat2Voltage(Double bat2Voltage) {
		this.bat2Voltage = bat2Voltage;
	}

	public Double getBatCurrent() {
		return batCurrent;
	}

	public void setBatCurrent(Double batCurrent) {
		this.batCurrent = batCurrent;
	}

	public Double getBatAccumulatedCurrent() {
		return batAccumulatedCurrent;
	}

	public void setBatAccumulatedCurrent(Double batAccumulatedCurrent) {
		this.batAccumulatedCurrent = batAccumulatedCurrent;
	}

	public Integer getProtectionRegister() {
		return protectionRegister;
	}

	public void setProtectionRegister(Integer protectionRegister) {
		this.protectionRegister = protectionRegister;
	}

	public Integer getStatusRegister() {
		return statusRegister;
	}

	public void setStatusRegister(Integer statusRegister) {
		this.statusRegister = statusRegister;
	}

	public Integer getCycleCounterRegister() {
		return cycleCounterRegister;
	}

	public void setCycleCounterRegister(Integer cycleCounterRegister) {
		this.cycleCounterRegister = cycleCounterRegister;
	}

	public Double getActiveAbsoluteCapacity() {
		return activeAbsoluteCapacity;
	}

	public void setActiveAbsoluteCapacity(Double activeAbsoluteCapacity) {
		this.activeAbsoluteCapacity = activeAbsoluteCapacity;
	}

	public Double getStandbyAbsoluteCapacity() {
		return standbyAbsoluteCapacity;
	}

	public void setStandbyAbsoluteCapacity(Double standbyAbsoluteCapacity) {
		this.standbyAbsoluteCapacity = standbyAbsoluteCapacity;
	}

	public Integer getActiveRelativeCapacity() {
		return activeRelativeCapacity;
	}

	public void setActiveRelativeCapacity(Integer activeRelativeCapacity) {
		this.activeRelativeCapacity = activeRelativeCapacity;
	}

	public Integer getStandbyRelativeCapacity() {
		return standbyRelativeCapacity;
	}

	public void setStandbyRelativeCapacity(Integer standbyRelativeCapacity) {
		this.standbyRelativeCapacity = standbyRelativeCapacity;
	}

	public double[] getRtdMeasurement() {
		return rtdMeasurement;
	}

	public void setRtdMeasurement(double[] rtdMeasurement) {
		this.rtdMeasurement = rtdMeasurement;
	}

	public Integer getEps() {
		return eps;
	}

	public void setEps(Integer eps) {
		this.eps = eps;
	}

	public int[] getPayload1() {
		return payload1;
	}

	public void setPayload1(int[] payload1) {
		this.payload1 = payload1;
	}

	public int[] getPayload2() {
		return payload2;
	}

	public void setPayload2(int[] payload2) {
		this.payload2 = payload2;
	}

}
