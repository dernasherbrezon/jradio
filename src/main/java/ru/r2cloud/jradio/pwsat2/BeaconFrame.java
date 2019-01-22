package ru.r2cloud.jradio.pwsat2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ru.r2cloud.jradio.util.LittleEndianBitInputStream;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;
import ru.r2cloud.jradio.util.MathUtils;

public class BeaconFrame {

	private long bootCounter;
	private List<Integer> bootSlots;
	private BootReason bootReason;
	private int programCRC;
	private long missionTimeMillis;
	private long externalTimeSeconds;

	private int comm;
	private int eps;
	private int rtc;
	private int imtq;
	private int n25Q1;
	private int n25Q2;
	private int n25Q3;
	private int n25qTMR;
	private int fRAMTMR;
	private int payload;
	private int camera;
	private int expSuns;
	private int ANTPrimary;
	private int ANTBackup;

	private int primaryFlashScrubbingPointer;
	private int secondaryFlashScrubbingPointer;
	private long RAMScrubbingPointer;
	private int uptimeSeconds;
	private long freeSpace;

	private boolean Antenna1DeploymentSwitchChA;
	private boolean Antenna2DeploymentSwitchChA;
	private boolean Antenna3DeploymentSwitchChA;
	private boolean Antenna4DeploymentSwitchChA;
	private boolean Antenna1DeploymentSwitchChB;
	private boolean Antenna2DeploymentSwitchChB;
	private boolean Antenna3DeploymentSwitchChB;
	private boolean Antenna4DeploymentSwitchChB;

	private boolean Antenna1TimeLimitReachedChA;
	private boolean Antenna2TimeLimitReachedChA;
	private boolean Antenna3TimeLimitReachedChA;
	private boolean Antenna4TimeLimitReachedChA;
	private boolean Antenna1TimeLimitReachedChB;
	private boolean Antenna2TimeLimitReachedChB;
	private boolean Antenna3TimeLimitReachedChB;
	private boolean Antenna4TimeLimitReachedChB;

	private boolean Antenna1BurnActiveChA;
	private boolean Antenna2BurnActiveChA;
	private boolean Antenna3BurnActiveChA;
	private boolean Antenna4BurnActiveChA;
	private boolean Antenna1BurnActiveChB;
	private boolean Antenna2BurnActiveChB;
	private boolean Antenna3BurnActiveChB;
	private boolean Antenna4BurnActiveChB;

	private boolean SystemIndependentBurnChA;
	private boolean SystemIndependentBurnChB;
	private boolean IgnoringSwitchesChA;
	private boolean IgnoringSwitchesChB;
	private boolean ArmedChA;
	private boolean ArmedChB;
	private int Antenna1ActivationCountChA;
	private int Antenna2ActivationCountChA;
	private int Antenna3ActivationCountChA;
	private int Antenna4ActivationCountChA;
	private int Antenna1ActivationCountChB;
	private int Antenna2ActivationCountChB;
	private int Antenna3ActivationCountChB;
	private int Antenna4ActivationCountChB;

	private int Antenna1ActivationTimeChA;
	private int Antenna2ActivationTimeChA;
	private int Antenna3ActivationTimeChA;
	private int Antenna4ActivationTimeChA;
	private int Antenna1ActivationTimeChB;
	private int Antenna2ActivationTimeChB;
	private int Antenna3ActivationTimeChB;
	private int Antenna4ActivationTimeChB;

	private ExperimentType experimentType;
	private StartResult startResult;
	private IterationResult iterationResult;

	private float gyroX;
	private float gyroY;
	private float gyroZ;
	private float gyroTemperature;

	private int transmitterUptime;
	private int transmitterBitrate;
	private float lastTransmissionRFReflectedPower;
	private float lastTransmissionPowerAmplifierTemperature;
	private float lastTransmissionRFForwardPower;
	private float lastTransmissionTransmitterCurrent;
	private float nowRFForwardPower;
	private float nowTransmitterCurrent;
	private boolean transmitterIdleState;
	private boolean beaconState;
	private int receiverUptime;
	private float lastReceivedDopplerOffset;
	private float lastReceivedRSSI;
	private float nowDopplerOffset;
	private float nowReceiverCurrent;
	private float nowPowerSupplyVoltage;
	private float nowOscillatorTemperature;
	private float nowPowerAmplifierTemperature;
	private float nowRSSI;

	private boolean sailDeployed;

	private float MCUTemperature;

	private float MPPT_XSOL_VOLT;
	private float MPPT_XSOL_CURR;
	private float MPPT_XSOL_OUT_VOLT;
	private float MPPT_XTemperature;
	private int MPPT_XState;
	private float MPPT_YPSOL_VOLT;
	private float MPPT_YPSOL_CURR;
	private float MPPT_YPSOL_OUT_VOLT;
	private float MPPT_YPTemperature;
	private int MPPT_YPState;
	private float MPPT_YMSOL_VOLT;
	private float MPPT_YMSOL_CURR;
	private float MPPT_YMSOL_OUT_VOLT;
	private float MPPT_YMTemperature;
	private float MPPT_YMState;
	private float DISTRVOLT_3V3;
	private float DISTRCURR_3V3;
	private float DISTRVOLT_5V;
	private float DISTRCURR_5V;
	private float DISTRVOLT_VBAT;
	private float DISTRCURR_VBAT;
	private int DISTRLCL_STATE;
	private int DISTRLCL_FLAGS;
	private float BATCVOLT_A;
	private float BATCCHRG_CURR;
	private float BATCDCHRG_CURR;
	private float BATCTemperature;
	private int BATCState;
	private float BPTemperatureA;
	private float BPTemperatureB;
	private int SafetyCounterA;
	private int PowerCycleCountA;
	private long UptimeA;
	private float TemperatureA;
	private float SUPP_TEMPA;
	private float ControllerB3V3dA;
	private float DCDC3V3Temperature;
	private float DCDC5VTemperature;

	private float BPTemperature;
	private float BATCVOLT_B;
	private int SafetyCounterB;
	private int PowerCycleCountB;
	private long UptimeB;
	private float TemperatureB;
	private float SUPP_TEMPB;
	private float ControllerA3V3dB;

	private float MagnetometerMeasurement1;
	private float MagnetometerMeasurement2;
	private float MagnetometerMeasurement3;

	private boolean coilsActiveDuringMeasurement;

	private float IMTQDipole1;
	private float IMTQDipole2;
	private float IMTQDipole3;

	private float IMTQBDot1;
	private float IMTQBDot2;
	private float IMTQBDot3;

	private float IMTQDigitalVoltage;
	private float IMTQAnalogVoltage;
	private float IMTQDigitalCurrent;
	private float IMTQAnalogCurrent;
	private short IMTQMCUTemperature;

	private float IMTQCoilCurrent1;
	private float IMTQCoilCurrent2;
	private float IMTQCoilCurrent3;

	private short IMTQCoilTemperature1;
	private short IMTQCoilTemperature2;
	private short IMTQCoilTemperature3;

	private int IMTQStatus;
	private int IMTQMode;
	private boolean IMTQErrorDuringPreviousIteration;
	private boolean IMTQConfigurationChanged;
	private long IMTQUptime;

	private int IMTQError1;
	private int IMTQError2;
	private int IMTQError3;
	private int IMTQError4;
	private int IMTQError5;
	private int IMTQError6;
	private int IMTQError7;
	private int IMTQError8;

	public BeaconFrame(LittleEndianDataInputStream dataInput) throws IOException {
		LittleEndianBitInputStream dis = new LittleEndianBitInputStream(dataInput);
		bootCounter = dis.readUnsignedInt();
		int bootIndex = dis.readUnsignedByte();
		if (bootIndex != 0b01000000) {
			bootSlots = new ArrayList<>();
			for (int i = 0; i < 7; i++) {
				int mask = 1 << i;
				if ((bootIndex & mask) == mask) {
					bootSlots.add(i);
				}
			}
		}
		bootReason = BootReason.valueOfCode(dis.readUnsignedShort());
		programCRC = dis.readUnsignedShort();
		missionTimeMillis = dis.readLong();
		externalTimeSeconds = dis.readUnsignedInt();

		comm = dis.readUnsignedByte();
		eps = dis.readUnsignedByte();
		rtc = dis.readUnsignedByte();
		imtq = dis.readUnsignedByte();
		n25Q1 = dis.readUnsignedByte();
		n25Q2 = dis.readUnsignedByte();
		n25Q3 = dis.readUnsignedByte();
		n25qTMR = dis.readUnsignedByte();
		fRAMTMR = dis.readUnsignedByte();
		payload = dis.readUnsignedByte();
		camera = dis.readUnsignedByte();
		expSuns = dis.readUnsignedByte();
		ANTPrimary = dis.readUnsignedByte();
		ANTBackup = dis.readUnsignedByte();

		primaryFlashScrubbingPointer = dis.readUnsignedInt(3);
		secondaryFlashScrubbingPointer = dis.readUnsignedInt(3);
		RAMScrubbingPointer = dis.readUnsignedInt();
		uptimeSeconds = dis.readUnsignedInt(22);
		freeSpace = dis.readUnsignedLong(32);

		int raw = dis.readUnsignedByte();
		Antenna1DeploymentSwitchChA = ((raw >> 0) & 0x1) > 0;
		Antenna2DeploymentSwitchChA = ((raw >> 1) & 0x1) > 0;
		Antenna3DeploymentSwitchChA = ((raw >> 2) & 0x1) > 0;
		Antenna4DeploymentSwitchChA = ((raw >> 3) & 0x1) > 0;
		Antenna1DeploymentSwitchChB = ((raw >> 4) & 0x1) > 0;
		Antenna2DeploymentSwitchChB = ((raw >> 5) & 0x1) > 0;
		Antenna3DeploymentSwitchChB = ((raw >> 6) & 0x1) > 0;
		Antenna4DeploymentSwitchChB = ((raw >> 7) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		Antenna1TimeLimitReachedChA = ((raw >> 0) & 0x1) > 0;
		Antenna2TimeLimitReachedChA = ((raw >> 1) & 0x1) > 0;
		Antenna3TimeLimitReachedChA = ((raw >> 2) & 0x1) > 0;
		Antenna4TimeLimitReachedChA = ((raw >> 3) & 0x1) > 0;
		Antenna1TimeLimitReachedChB = ((raw >> 4) & 0x1) > 0;
		Antenna2TimeLimitReachedChB = ((raw >> 5) & 0x1) > 0;
		Antenna3TimeLimitReachedChB = ((raw >> 6) & 0x1) > 0;
		Antenna4TimeLimitReachedChB = ((raw >> 7) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		Antenna1BurnActiveChA = ((raw >> 0) & 0x1) > 0;
		Antenna2BurnActiveChA = ((raw >> 1) & 0x1) > 0;
		Antenna3BurnActiveChA = ((raw >> 2) & 0x1) > 0;
		Antenna4BurnActiveChA = ((raw >> 3) & 0x1) > 0;
		Antenna1BurnActiveChB = ((raw >> 4) & 0x1) > 0;
		Antenna2BurnActiveChB = ((raw >> 5) & 0x1) > 0;
		Antenna3BurnActiveChB = ((raw >> 6) & 0x1) > 0;
		Antenna4BurnActiveChB = ((raw >> 7) & 0x1) > 0;

		SystemIndependentBurnChA = dis.readBoolean();
		SystemIndependentBurnChB = dis.readBoolean();
		IgnoringSwitchesChA = dis.readBoolean();
		IgnoringSwitchesChB = dis.readBoolean();
		ArmedChA = dis.readBoolean();
		ArmedChB = dis.readBoolean();

		Antenna1ActivationCountChA = dis.readUnsignedInt(3);
		Antenna2ActivationCountChA = dis.readUnsignedInt(3);
		Antenna3ActivationCountChA = dis.readUnsignedInt(3);
		Antenna4ActivationCountChA = dis.readUnsignedInt(3);
		Antenna1ActivationCountChB = dis.readUnsignedInt(3);
		Antenna2ActivationCountChB = dis.readUnsignedInt(3);
		Antenna3ActivationCountChB = dis.readUnsignedInt(3);
		Antenna4ActivationCountChB = dis.readUnsignedInt(3);

		Antenna1ActivationTimeChA = dis.readUnsignedByte();
		Antenna2ActivationTimeChA = dis.readUnsignedByte();
		Antenna3ActivationTimeChA = dis.readUnsignedByte();
		Antenna4ActivationTimeChA = dis.readUnsignedByte();
		Antenna1ActivationTimeChB = dis.readUnsignedByte();
		Antenna2ActivationTimeChB = dis.readUnsignedByte();
		Antenna3ActivationTimeChB = dis.readUnsignedByte();
		Antenna4ActivationTimeChB = dis.readUnsignedByte();

		experimentType = ExperimentType.valueOfCode(dis.readUnsignedInt(4));
		startResult = StartResult.valueOfCode(dis.readUnsignedByte());
		iterationResult = IterationResult.valueOfCode(dis.readUnsignedByte());

		gyroX = dis.readShortTwosComplement() / 14.375f;
		gyroY = dis.readShortTwosComplement() / 14.375f;
		gyroZ = dis.readShortTwosComplement() / 14.375f;
		gyroTemperature = (dis.readShortTwosComplement() + 23000) / 280.0f;

		transmitterUptime = dis.readUnsignedInt(17);
		raw = dis.readUnsignedInt(2);
		switch (raw) {
		case 0:
			transmitterBitrate = 1200;
			break;
		case 1:
			transmitterBitrate = 2400;
			break;
		case 2:
			transmitterBitrate = 4800;
			break;
		case 3:
			transmitterBitrate = 9600;
			break;
		default:
			break;
		}
		lastTransmissionRFReflectedPower = readRfPower(dis);
		lastTransmissionPowerAmplifierTemperature = readCommTemperature(dis);
		lastTransmissionRFForwardPower = readRfPower(dis);
		lastTransmissionTransmitterCurrent = dis.readUnsignedInt(12) * 0.0897f;
		nowRFForwardPower = readRfPower(dis);
		nowTransmitterCurrent = dis.readUnsignedInt(12) * 0.0897f;
		transmitterIdleState = dis.readBoolean();
		beaconState = dis.readBoolean();
		receiverUptime = dis.readUnsignedInt(17);
		lastReceivedDopplerOffset = readDopplerOffset(dis);
		lastReceivedRSSI = readSignalStrength(dis);
		nowDopplerOffset = readDopplerOffset(dis);
		nowReceiverCurrent = dis.readUnsignedInt(12) * 0.0305f;
		nowPowerSupplyVoltage = dis.readUnsignedInt(12) * 0.00488f;
		nowOscillatorTemperature = readCommTemperature(dis);
		nowPowerAmplifierTemperature = readCommTemperature(dis);
		nowRSSI = readSignalStrength(dis);

		sailDeployed = dis.readBoolean();

		MCUTemperature = 25.0f - (2297.0f - dis.readUnsignedInt(12)) / -6.3f;

		MPPT_XSOL_VOLT = readMPPTVoltage(dis);
		MPPT_XSOL_CURR = readMPPTCurrent(dis);
		MPPT_XSOL_OUT_VOLT = readMPPTVoltage(dis);
		MPPT_XTemperature = readMPPTTemperature(dis);
		MPPT_XState = dis.readUnsignedInt(3);
		MPPT_YPSOL_VOLT = readMPPTVoltage(dis);
		MPPT_YPSOL_CURR = readMPPTCurrent(dis);
		MPPT_YPSOL_OUT_VOLT = readMPPTVoltage(dis);
		MPPT_YPTemperature = readMPPTTemperature(dis);
		MPPT_YPState = dis.readUnsignedInt(3);
		MPPT_YMSOL_VOLT = readMPPTVoltage(dis);
		MPPT_YMSOL_CURR = readMPPTCurrent(dis);
		MPPT_YMSOL_OUT_VOLT = readMPPTVoltage(dis);
		MPPT_YMTemperature = readMPPTTemperature(dis);
		MPPT_YMState = dis.readUnsignedInt(3);
		DISTRVOLT_3V3 = readDistributionVoltage(dis);
		DISTRCURR_3V3 = readDistributionCurrent(dis);
		DISTRVOLT_5V = readDistributionVoltage(dis);
		DISTRCURR_5V = readDistributionCurrent(dis);
		DISTRVOLT_VBAT = readDistributionVoltage(dis);
		DISTRCURR_VBAT = readDistributionCurrent(dis);
		DISTRLCL_STATE = dis.readUnsignedInt(7);
		DISTRLCL_FLAGS = dis.readUnsignedInt(6);
		BATCVOLT_A = readBATCVoltage(dis);
		BATCCHRG_CURR = readDistributionCurrent(dis);
		BATCDCHRG_CURR = readDistributionCurrent(dis);
		BATCTemperature = readLMT87Temperature(dis);
		BATCState = dis.readUnsignedInt(3);
		BPTemperatureA = readTMP121Temperature(dis);
		BPTemperatureB = readTMP121Temperature(dis);
		SafetyCounterA = dis.readUnsignedByte();
		PowerCycleCountA = dis.readUnsignedShort();
		UptimeA = dis.readUnsignedInt();
		TemperatureA = readLMT87Temperature(dis);
		SUPP_TEMPA = readLMT87Temperature(dis);
		ControllerB3V3dA = readLocal3V3dVoltage(dis);
		DCDC3V3Temperature = readLMT87Temperature(dis);
		DCDC5VTemperature = readLMT87Temperature(dis);

		BPTemperature = readPT1000Temperature(dis);
		BATCVOLT_B = readBATCVoltage(dis);
		SafetyCounterB = dis.readUnsignedByte();
		PowerCycleCountB = dis.readUnsignedShort();
		UptimeB = dis.readUnsignedInt();
		TemperatureB = readLMT87Temperature(dis);
		SUPP_TEMPB = readLMT87Temperature(dis);
		ControllerA3V3dB = readLocal3V3dVoltage(dis);

		MagnetometerMeasurement1 = dis.readLongTwosComplement(32) * 1e-3f;
		MagnetometerMeasurement2 = dis.readLongTwosComplement(32) * 1e-3f;
		MagnetometerMeasurement3 = dis.readLongTwosComplement(32) * 1e-3f;

		coilsActiveDuringMeasurement = dis.readBoolean();

		IMTQDipole1 = dis.readIntTwosComplement(16) * 1e-1f;
		IMTQDipole2 = dis.readIntTwosComplement(16) * 1e-1f;
		IMTQDipole3 = dis.readIntTwosComplement(16) * 1e-1f;

		IMTQBDot1 = dis.readLongTwosComplement(32) * 1e-3f;
		IMTQBDot2 = dis.readLongTwosComplement(32) * 1e-3f;
		IMTQBDot3 = dis.readLongTwosComplement(32) * 1e-3f;

		IMTQDigitalVoltage = dis.readUnsignedShort() / 1000.0f;
		IMTQAnalogVoltage = dis.readUnsignedShort() / 1000.0f;
		IMTQDigitalCurrent = dis.readUnsignedShort() * 1e-1f;
		IMTQAnalogCurrent = dis.readUnsignedShort() * 1e-1f;
		IMTQMCUTemperature = (short) dis.readIntTwosComplement(16);

		IMTQCoilCurrent1 = dis.readUnsignedShort() * 1e-1f;
		IMTQCoilCurrent2 = dis.readUnsignedShort() * 1e-1f;
		IMTQCoilCurrent3 = dis.readUnsignedShort() * 1e-1f;

		IMTQCoilTemperature1 = (short) dis.readIntTwosComplement(16);
		IMTQCoilTemperature2 = (short) dis.readIntTwosComplement(16);
		IMTQCoilTemperature3 = (short) dis.readIntTwosComplement(16);

		IMTQStatus = dis.readUnsignedByte();
		IMTQMode = dis.readUnsignedInt(2);
		IMTQErrorDuringPreviousIteration = dis.readUnsignedByte() > 0;
		IMTQConfigurationChanged = dis.readBoolean();
		IMTQUptime = dis.readUnsignedInt();

		IMTQError1 = dis.readUnsignedByte();
		IMTQError2 = dis.readUnsignedByte();
		IMTQError3 = dis.readUnsignedByte();
		IMTQError4 = dis.readUnsignedByte();
		IMTQError5 = dis.readUnsignedByte();
		IMTQError6 = dis.readUnsignedByte();
		IMTQError7 = dis.readUnsignedByte();
		IMTQError8 = dis.readUnsignedByte();
	}

	private static float readRfPower(LittleEndianBitInputStream dis) throws IOException {
		return (float) (20.0f * Math.log10((dis.readUnsignedInt(12) + 1e-99) * 0.00767f));
	}

	private static float readCommTemperature(LittleEndianBitInputStream dis) throws IOException {
		return (dis.readUnsignedInt(12) * -0.0546f) + 189.5522f;
	}

	private static float readDopplerOffset(LittleEndianBitInputStream dis) throws IOException {
		return (dis.readUnsignedInt(12) * 13.352f - 22300);
	}

	private static float readSignalStrength(LittleEndianBitInputStream dis) throws IOException {
		return (dis.readUnsignedInt(12) * 0.03f - 152);
	}

	private static float readMPPTVoltage(LittleEndianBitInputStream dis) throws IOException {
		int raw = dis.readUnsignedInt(12);
		if (raw > 4095 || raw < 0) {
			raw = 4095;
		}
		float result = (raw / 4096.0f) * 3.3f;
		return MathUtils.round(result * ((4.7f + 1.0f) / (1.0f)), 2);
	}

	private static float readMPPTCurrent(LittleEndianBitInputStream dis) throws IOException {
		int raw = dis.readUnsignedInt(12);
		if (raw > 4095 || raw < 0) {
			raw = 4095;
		}
		float result = (raw / 4096.0f) * 3.3f;
		return MathUtils.round(result * (1.0f / (0.068f * 50.0f)), 3);
	}

	private static float readMPPTTemperature(LittleEndianBitInputStream dis) throws IOException {
		int raw = dis.readUnsignedInt(12);
		if (raw > 4095 || raw < 0) {
			raw = 4095;
		}
		float result = ((raw / 4096.0f) * 3.3f) * 1000.0f;
		return MathUtils.round(((13.582f - (float) Math.sqrt((-13.582 * (-13.582)) + 4 * 0.00433f * (2230.8f - result))) / (2 * (-0.00433f))) + 30.0f, 1);
	}

	private static float readDistributionVoltage(LittleEndianBitInputStream dis) throws IOException {
		int raw = dis.readUnsignedInt(10);
		if (raw > 1023 || raw < 0) {
			raw = 1023;
		}
		float result = (raw / 1024.0f) * 3.0f;
		return MathUtils.round(result * ((4.7f + 2.2f) / 2.2f), 2);
	}

	private static float readDistributionCurrent(LittleEndianBitInputStream dis) throws IOException {
		int raw = dis.readUnsignedInt(10);
		if (raw > 1023 || raw < 0) {
			raw = 1023;
		}
		float result = (raw / 1024.0f) * 3.0f;
		return MathUtils.round(result * (1.0f / (0.025f * 50.0f)), 3);
	}

	private static float readBATCVoltage(LittleEndianBitInputStream dis) throws IOException {
		int raw = dis.readUnsignedInt(10);
		if (raw > 1023 || raw < 0) {
			raw = 1023;
		}
		float result = (raw / 1024.0f) * 3.0f;
		return MathUtils.round(result * ((470.0f + 150.0f) / 150.0f), 2);
	}

	private static float readLMT87Temperature(LittleEndianBitInputStream dis) throws IOException {
		int raw = dis.readUnsignedInt(10);
		if (raw > 1023 || raw < 0) {
			raw = 1023;
		}
		float result = ((raw / 1024.0f) * 3.0f) * 1000.0f;
		return MathUtils.round(((13.582f - (float) Math.sqrt((-13.582f * (-13.582f)) + 4 * 0.00433f * (2230.8f - result))) / (2 * (-0.00433f))) + 30.0f, 1);
	}

	private static float readTMP121Temperature(LittleEndianBitInputStream dis) throws IOException {
		return dis.readIntTwosComplement(13) * 0.0625f;
	}

	private static float readLocal3V3dVoltage(LittleEndianBitInputStream dis) throws IOException {
		int raw = dis.readUnsignedInt(10);
		if (raw > 1023 || raw < 0) {
			raw = 1023;
		}
		float result = (raw / 1024.0f) * 3.0f;
		return MathUtils.round(result * 2.0f, 2);
	}

	private static float readPT1000Temperature(LittleEndianBitInputStream dis) throws IOException {
		int raw = dis.readUnsignedInt(10);
		if (raw > 1023 || raw < 0) {
			raw = 1023;
		}
		float result = (raw / 1024.0f) * 3.0f;
		float resistance = result / ((3.0f - result) / 3320.68f);
		return MathUtils.round(PT1000Sensors.getTemperature(resistance), 1);
	}

	public long getBootCounter() {
		return bootCounter;
	}

	public void setBootCounter(long bootCounter) {
		this.bootCounter = bootCounter;
	}

	public List<Integer> getBootSlots() {
		return bootSlots;
	}

	public void setBootSlots(List<Integer> bootSlots) {
		this.bootSlots = bootSlots;
	}

	public BootReason getBootReason() {
		return bootReason;
	}

	public void setBootReason(BootReason bootReason) {
		this.bootReason = bootReason;
	}

	public int getProgramCRC() {
		return programCRC;
	}

	public void setProgramCRC(int programCRC) {
		this.programCRC = programCRC;
	}

	public long getMissionTimeMillis() {
		return missionTimeMillis;
	}

	public void setMissionTimeMillis(long missionTimeMillis) {
		this.missionTimeMillis = missionTimeMillis;
	}

	public long getExternalTimeSeconds() {
		return externalTimeSeconds;
	}

	public void setExternalTimeSeconds(long externalTimeSeconds) {
		this.externalTimeSeconds = externalTimeSeconds;
	}

	public int getComm() {
		return comm;
	}

	public void setComm(int comm) {
		this.comm = comm;
	}

	public int getEps() {
		return eps;
	}

	public void setEps(int eps) {
		this.eps = eps;
	}

	public int getRtc() {
		return rtc;
	}

	public void setRtc(int rtc) {
		this.rtc = rtc;
	}

	public int getImtq() {
		return imtq;
	}

	public void setImtq(int imtq) {
		this.imtq = imtq;
	}

	public int getN25Q1() {
		return n25Q1;
	}

	public void setN25Q1(int n25q1) {
		n25Q1 = n25q1;
	}

	public int getN25Q2() {
		return n25Q2;
	}

	public void setN25Q2(int n25q2) {
		n25Q2 = n25q2;
	}

	public int getN25Q3() {
		return n25Q3;
	}

	public void setN25Q3(int n25q3) {
		n25Q3 = n25q3;
	}

	public int getN25qTMR() {
		return n25qTMR;
	}

	public void setN25qTMR(int n25qTMR) {
		this.n25qTMR = n25qTMR;
	}

	public int getfRAMTMR() {
		return fRAMTMR;
	}

	public void setfRAMTMR(int fRAMTMR) {
		this.fRAMTMR = fRAMTMR;
	}

	public int getPayload() {
		return payload;
	}

	public void setPayload(int payload) {
		this.payload = payload;
	}

	public int getCamera() {
		return camera;
	}

	public void setCamera(int camera) {
		this.camera = camera;
	}

	public int getExpSuns() {
		return expSuns;
	}

	public void setExpSuns(int expSuns) {
		this.expSuns = expSuns;
	}

	public int getANTPrimary() {
		return ANTPrimary;
	}

	public void setANTPrimary(int aNTPrimary) {
		ANTPrimary = aNTPrimary;
	}

	public int getANTBackup() {
		return ANTBackup;
	}

	public void setANTBackup(int aNTBackup) {
		ANTBackup = aNTBackup;
	}

	public int getPrimaryFlashScrubbingPointer() {
		return primaryFlashScrubbingPointer;
	}

	public void setPrimaryFlashScrubbingPointer(int primaryFlashScrubbingPointer) {
		this.primaryFlashScrubbingPointer = primaryFlashScrubbingPointer;
	}

	public int getSecondaryFlashScrubbingPointer() {
		return secondaryFlashScrubbingPointer;
	}

	public void setSecondaryFlashScrubbingPointer(int secondaryFlashScrubbingPointer) {
		this.secondaryFlashScrubbingPointer = secondaryFlashScrubbingPointer;
	}

	public long getRAMScrubbingPointer() {
		return RAMScrubbingPointer;
	}

	public void setRAMScrubbingPointer(long rAMScrubbingPointer) {
		RAMScrubbingPointer = rAMScrubbingPointer;
	}

	public int getUptimeSeconds() {
		return uptimeSeconds;
	}

	public void setUptimeSeconds(int uptimeSeconds) {
		this.uptimeSeconds = uptimeSeconds;
	}

	public long getFreeSpace() {
		return freeSpace;
	}

	public void setFreeSpace(long freeSpace) {
		this.freeSpace = freeSpace;
	}

	public boolean isAntenna1DeploymentSwitchChA() {
		return Antenna1DeploymentSwitchChA;
	}

	public void setAntenna1DeploymentSwitchChA(boolean antenna1DeploymentSwitchChA) {
		Antenna1DeploymentSwitchChA = antenna1DeploymentSwitchChA;
	}

	public boolean isAntenna2DeploymentSwitchChA() {
		return Antenna2DeploymentSwitchChA;
	}

	public void setAntenna2DeploymentSwitchChA(boolean antenna2DeploymentSwitchChA) {
		Antenna2DeploymentSwitchChA = antenna2DeploymentSwitchChA;
	}

	public boolean isAntenna3DeploymentSwitchChA() {
		return Antenna3DeploymentSwitchChA;
	}

	public void setAntenna3DeploymentSwitchChA(boolean antenna3DeploymentSwitchChA) {
		Antenna3DeploymentSwitchChA = antenna3DeploymentSwitchChA;
	}

	public boolean isAntenna4DeploymentSwitchChA() {
		return Antenna4DeploymentSwitchChA;
	}

	public void setAntenna4DeploymentSwitchChA(boolean antenna4DeploymentSwitchChA) {
		Antenna4DeploymentSwitchChA = antenna4DeploymentSwitchChA;
	}

	public boolean isAntenna1DeploymentSwitchChB() {
		return Antenna1DeploymentSwitchChB;
	}

	public void setAntenna1DeploymentSwitchChB(boolean antenna1DeploymentSwitchChB) {
		Antenna1DeploymentSwitchChB = antenna1DeploymentSwitchChB;
	}

	public boolean isAntenna2DeploymentSwitchChB() {
		return Antenna2DeploymentSwitchChB;
	}

	public void setAntenna2DeploymentSwitchChB(boolean antenna2DeploymentSwitchChB) {
		Antenna2DeploymentSwitchChB = antenna2DeploymentSwitchChB;
	}

	public boolean isAntenna3DeploymentSwitchChB() {
		return Antenna3DeploymentSwitchChB;
	}

	public void setAntenna3DeploymentSwitchChB(boolean antenna3DeploymentSwitchChB) {
		Antenna3DeploymentSwitchChB = antenna3DeploymentSwitchChB;
	}

	public boolean isAntenna4DeploymentSwitchChB() {
		return Antenna4DeploymentSwitchChB;
	}

	public void setAntenna4DeploymentSwitchChB(boolean antenna4DeploymentSwitchChB) {
		Antenna4DeploymentSwitchChB = antenna4DeploymentSwitchChB;
	}

	public boolean isAntenna1TimeLimitReachedChA() {
		return Antenna1TimeLimitReachedChA;
	}

	public void setAntenna1TimeLimitReachedChA(boolean antenna1TimeLimitReachedChA) {
		Antenna1TimeLimitReachedChA = antenna1TimeLimitReachedChA;
	}

	public boolean isAntenna2TimeLimitReachedChA() {
		return Antenna2TimeLimitReachedChA;
	}

	public void setAntenna2TimeLimitReachedChA(boolean antenna2TimeLimitReachedChA) {
		Antenna2TimeLimitReachedChA = antenna2TimeLimitReachedChA;
	}

	public boolean isAntenna3TimeLimitReachedChA() {
		return Antenna3TimeLimitReachedChA;
	}

	public void setAntenna3TimeLimitReachedChA(boolean antenna3TimeLimitReachedChA) {
		Antenna3TimeLimitReachedChA = antenna3TimeLimitReachedChA;
	}

	public boolean isAntenna4TimeLimitReachedChA() {
		return Antenna4TimeLimitReachedChA;
	}

	public void setAntenna4TimeLimitReachedChA(boolean antenna4TimeLimitReachedChA) {
		Antenna4TimeLimitReachedChA = antenna4TimeLimitReachedChA;
	}

	public boolean isAntenna1TimeLimitReachedChB() {
		return Antenna1TimeLimitReachedChB;
	}

	public void setAntenna1TimeLimitReachedChB(boolean antenna1TimeLimitReachedChB) {
		Antenna1TimeLimitReachedChB = antenna1TimeLimitReachedChB;
	}

	public boolean isAntenna2TimeLimitReachedChB() {
		return Antenna2TimeLimitReachedChB;
	}

	public void setAntenna2TimeLimitReachedChB(boolean antenna2TimeLimitReachedChB) {
		Antenna2TimeLimitReachedChB = antenna2TimeLimitReachedChB;
	}

	public boolean isAntenna3TimeLimitReachedChB() {
		return Antenna3TimeLimitReachedChB;
	}

	public void setAntenna3TimeLimitReachedChB(boolean antenna3TimeLimitReachedChB) {
		Antenna3TimeLimitReachedChB = antenna3TimeLimitReachedChB;
	}

	public boolean isAntenna4TimeLimitReachedChB() {
		return Antenna4TimeLimitReachedChB;
	}

	public void setAntenna4TimeLimitReachedChB(boolean antenna4TimeLimitReachedChB) {
		Antenna4TimeLimitReachedChB = antenna4TimeLimitReachedChB;
	}

	public boolean isAntenna1BurnActiveChA() {
		return Antenna1BurnActiveChA;
	}

	public void setAntenna1BurnActiveChA(boolean antenna1BurnActiveChA) {
		Antenna1BurnActiveChA = antenna1BurnActiveChA;
	}

	public boolean isAntenna2BurnActiveChA() {
		return Antenna2BurnActiveChA;
	}

	public void setAntenna2BurnActiveChA(boolean antenna2BurnActiveChA) {
		Antenna2BurnActiveChA = antenna2BurnActiveChA;
	}

	public boolean isAntenna3BurnActiveChA() {
		return Antenna3BurnActiveChA;
	}

	public void setAntenna3BurnActiveChA(boolean antenna3BurnActiveChA) {
		Antenna3BurnActiveChA = antenna3BurnActiveChA;
	}

	public boolean isAntenna4BurnActiveChA() {
		return Antenna4BurnActiveChA;
	}

	public void setAntenna4BurnActiveChA(boolean antenna4BurnActiveChA) {
		Antenna4BurnActiveChA = antenna4BurnActiveChA;
	}

	public boolean isAntenna1BurnActiveChB() {
		return Antenna1BurnActiveChB;
	}

	public void setAntenna1BurnActiveChB(boolean antenna1BurnActiveChB) {
		Antenna1BurnActiveChB = antenna1BurnActiveChB;
	}

	public boolean isAntenna2BurnActiveChB() {
		return Antenna2BurnActiveChB;
	}

	public void setAntenna2BurnActiveChB(boolean antenna2BurnActiveChB) {
		Antenna2BurnActiveChB = antenna2BurnActiveChB;
	}

	public boolean isAntenna3BurnActiveChB() {
		return Antenna3BurnActiveChB;
	}

	public void setAntenna3BurnActiveChB(boolean antenna3BurnActiveChB) {
		Antenna3BurnActiveChB = antenna3BurnActiveChB;
	}

	public boolean isAntenna4BurnActiveChB() {
		return Antenna4BurnActiveChB;
	}

	public void setAntenna4BurnActiveChB(boolean antenna4BurnActiveChB) {
		Antenna4BurnActiveChB = antenna4BurnActiveChB;
	}

	public boolean isSystemIndependentBurnChA() {
		return SystemIndependentBurnChA;
	}

	public void setSystemIndependentBurnChA(boolean systemIndependentBurnChA) {
		SystemIndependentBurnChA = systemIndependentBurnChA;
	}

	public boolean isSystemIndependentBurnChB() {
		return SystemIndependentBurnChB;
	}

	public void setSystemIndependentBurnChB(boolean systemIndependentBurnChB) {
		SystemIndependentBurnChB = systemIndependentBurnChB;
	}

	public boolean isIgnoringSwitchesChA() {
		return IgnoringSwitchesChA;
	}

	public void setIgnoringSwitchesChA(boolean ignoringSwitchesChA) {
		IgnoringSwitchesChA = ignoringSwitchesChA;
	}

	public boolean isIgnoringSwitchesChB() {
		return IgnoringSwitchesChB;
	}

	public void setIgnoringSwitchesChB(boolean ignoringSwitchesChB) {
		IgnoringSwitchesChB = ignoringSwitchesChB;
	}

	public boolean isArmedChA() {
		return ArmedChA;
	}

	public void setArmedChA(boolean armedChA) {
		ArmedChA = armedChA;
	}

	public boolean isArmedChB() {
		return ArmedChB;
	}

	public void setArmedChB(boolean armedChB) {
		ArmedChB = armedChB;
	}

	public int getAntenna1ActivationCountChA() {
		return Antenna1ActivationCountChA;
	}

	public void setAntenna1ActivationCountChA(int antenna1ActivationCountChA) {
		Antenna1ActivationCountChA = antenna1ActivationCountChA;
	}

	public int getAntenna2ActivationCountChA() {
		return Antenna2ActivationCountChA;
	}

	public void setAntenna2ActivationCountChA(int antenna2ActivationCountChA) {
		Antenna2ActivationCountChA = antenna2ActivationCountChA;
	}

	public int getAntenna3ActivationCountChA() {
		return Antenna3ActivationCountChA;
	}

	public void setAntenna3ActivationCountChA(int antenna3ActivationCountChA) {
		Antenna3ActivationCountChA = antenna3ActivationCountChA;
	}

	public int getAntenna4ActivationCountChA() {
		return Antenna4ActivationCountChA;
	}

	public void setAntenna4ActivationCountChA(int antenna4ActivationCountChA) {
		Antenna4ActivationCountChA = antenna4ActivationCountChA;
	}

	public int getAntenna1ActivationCountChB() {
		return Antenna1ActivationCountChB;
	}

	public void setAntenna1ActivationCountChB(int antenna1ActivationCountChB) {
		Antenna1ActivationCountChB = antenna1ActivationCountChB;
	}

	public int getAntenna2ActivationCountChB() {
		return Antenna2ActivationCountChB;
	}

	public void setAntenna2ActivationCountChB(int antenna2ActivationCountChB) {
		Antenna2ActivationCountChB = antenna2ActivationCountChB;
	}

	public int getAntenna3ActivationCountChB() {
		return Antenna3ActivationCountChB;
	}

	public void setAntenna3ActivationCountChB(int antenna3ActivationCountChB) {
		Antenna3ActivationCountChB = antenna3ActivationCountChB;
	}

	public int getAntenna4ActivationCountChB() {
		return Antenna4ActivationCountChB;
	}

	public void setAntenna4ActivationCountChB(int antenna4ActivationCountChB) {
		Antenna4ActivationCountChB = antenna4ActivationCountChB;
	}

	public int getAntenna1ActivationTimeChA() {
		return Antenna1ActivationTimeChA;
	}

	public void setAntenna1ActivationTimeChA(int antenna1ActivationTimeChA) {
		Antenna1ActivationTimeChA = antenna1ActivationTimeChA;
	}

	public int getAntenna2ActivationTimeChA() {
		return Antenna2ActivationTimeChA;
	}

	public void setAntenna2ActivationTimeChA(int antenna2ActivationTimeChA) {
		Antenna2ActivationTimeChA = antenna2ActivationTimeChA;
	}

	public int getAntenna3ActivationTimeChA() {
		return Antenna3ActivationTimeChA;
	}

	public void setAntenna3ActivationTimeChA(int antenna3ActivationTimeChA) {
		Antenna3ActivationTimeChA = antenna3ActivationTimeChA;
	}

	public int getAntenna4ActivationTimeChA() {
		return Antenna4ActivationTimeChA;
	}

	public void setAntenna4ActivationTimeChA(int antenna4ActivationTimeChA) {
		Antenna4ActivationTimeChA = antenna4ActivationTimeChA;
	}

	public int getAntenna1ActivationTimeChB() {
		return Antenna1ActivationTimeChB;
	}

	public void setAntenna1ActivationTimeChB(int antenna1ActivationTimeChB) {
		Antenna1ActivationTimeChB = antenna1ActivationTimeChB;
	}

	public int getAntenna2ActivationTimeChB() {
		return Antenna2ActivationTimeChB;
	}

	public void setAntenna2ActivationTimeChB(int antenna2ActivationTimeChB) {
		Antenna2ActivationTimeChB = antenna2ActivationTimeChB;
	}

	public int getAntenna3ActivationTimeChB() {
		return Antenna3ActivationTimeChB;
	}

	public void setAntenna3ActivationTimeChB(int antenna3ActivationTimeChB) {
		Antenna3ActivationTimeChB = antenna3ActivationTimeChB;
	}

	public int getAntenna4ActivationTimeChB() {
		return Antenna4ActivationTimeChB;
	}

	public void setAntenna4ActivationTimeChB(int antenna4ActivationTimeChB) {
		Antenna4ActivationTimeChB = antenna4ActivationTimeChB;
	}

	public ExperimentType getExperimentType() {
		return experimentType;
	}

	public void setExperimentType(ExperimentType experimentType) {
		this.experimentType = experimentType;
	}

	public StartResult getStartResult() {
		return startResult;
	}

	public void setStartResult(StartResult startResult) {
		this.startResult = startResult;
	}

	public IterationResult getIterationResult() {
		return iterationResult;
	}

	public void setIterationResult(IterationResult iterationResult) {
		this.iterationResult = iterationResult;
	}

	public float getGyroX() {
		return gyroX;
	}

	public void setGyroX(float gyroX) {
		this.gyroX = gyroX;
	}

	public float getGyroY() {
		return gyroY;
	}

	public void setGyroY(float gyroY) {
		this.gyroY = gyroY;
	}

	public float getGyroZ() {
		return gyroZ;
	}

	public void setGyroZ(float gyroZ) {
		this.gyroZ = gyroZ;
	}

	public float getGyroTemperature() {
		return gyroTemperature;
	}

	public void setGyroTemperature(float gyroTemperature) {
		this.gyroTemperature = gyroTemperature;
	}

	public int getTransmitterUptime() {
		return transmitterUptime;
	}

	public void setTransmitterUptime(int transmitterUptime) {
		this.transmitterUptime = transmitterUptime;
	}

	public int getTransmitterBitrate() {
		return transmitterBitrate;
	}

	public void setTransmitterBitrate(int transmitterBitrate) {
		this.transmitterBitrate = transmitterBitrate;
	}

	public float getLastTransmissionRFReflectedPower() {
		return lastTransmissionRFReflectedPower;
	}

	public void setLastTransmissionRFReflectedPower(float lastTransmissionRFReflectedPower) {
		this.lastTransmissionRFReflectedPower = lastTransmissionRFReflectedPower;
	}

	public float getLastTransmissionPowerAmplifierTemperature() {
		return lastTransmissionPowerAmplifierTemperature;
	}

	public void setLastTransmissionPowerAmplifierTemperature(float lastTransmissionPowerAmplifierTemperature) {
		this.lastTransmissionPowerAmplifierTemperature = lastTransmissionPowerAmplifierTemperature;
	}

	public float getLastTransmissionRFForwardPower() {
		return lastTransmissionRFForwardPower;
	}

	public void setLastTransmissionRFForwardPower(float lastTransmissionRFForwardPower) {
		this.lastTransmissionRFForwardPower = lastTransmissionRFForwardPower;
	}

	public float getLastTransmissionTransmitterCurrent() {
		return lastTransmissionTransmitterCurrent;
	}

	public void setLastTransmissionTransmitterCurrent(float lastTransmissionTransmitterCurrent) {
		this.lastTransmissionTransmitterCurrent = lastTransmissionTransmitterCurrent;
	}

	public float getNowRFForwardPower() {
		return nowRFForwardPower;
	}

	public void setNowRFForwardPower(float nowRFForwardPower) {
		this.nowRFForwardPower = nowRFForwardPower;
	}

	public float getNowTransmitterCurrent() {
		return nowTransmitterCurrent;
	}

	public void setNowTransmitterCurrent(float nowTransmitterCurrent) {
		this.nowTransmitterCurrent = nowTransmitterCurrent;
	}

	public boolean isTransmitterIdleState() {
		return transmitterIdleState;
	}

	public void setTransmitterIdleState(boolean transmitterIdleState) {
		this.transmitterIdleState = transmitterIdleState;
	}

	public boolean isBeaconState() {
		return beaconState;
	}

	public void setBeaconState(boolean beaconState) {
		this.beaconState = beaconState;
	}

	public int getReceiverUptime() {
		return receiverUptime;
	}

	public void setReceiverUptime(int receiverUptime) {
		this.receiverUptime = receiverUptime;
	}

	public float getLastReceivedDopplerOffset() {
		return lastReceivedDopplerOffset;
	}

	public void setLastReceivedDopplerOffset(float lastReceivedDopplerOffset) {
		this.lastReceivedDopplerOffset = lastReceivedDopplerOffset;
	}

	public float getLastReceivedRSSI() {
		return lastReceivedRSSI;
	}

	public void setLastReceivedRSSI(float lastReceivedRSSI) {
		this.lastReceivedRSSI = lastReceivedRSSI;
	}

	public float getNowDopplerOffset() {
		return nowDopplerOffset;
	}

	public void setNowDopplerOffset(float nowDopplerOffset) {
		this.nowDopplerOffset = nowDopplerOffset;
	}

	public float getNowReceiverCurrent() {
		return nowReceiverCurrent;
	}

	public void setNowReceiverCurrent(float nowReceiverCurrent) {
		this.nowReceiverCurrent = nowReceiverCurrent;
	}

	public float getNowPowerSupplyVoltage() {
		return nowPowerSupplyVoltage;
	}

	public void setNowPowerSupplyVoltage(float nowPowerSupplyVoltage) {
		this.nowPowerSupplyVoltage = nowPowerSupplyVoltage;
	}

	public float getNowOscillatorTemperature() {
		return nowOscillatorTemperature;
	}

	public void setNowOscillatorTemperature(float nowOscillatorTemperature) {
		this.nowOscillatorTemperature = nowOscillatorTemperature;
	}

	public float getNowPowerAmplifierTemperature() {
		return nowPowerAmplifierTemperature;
	}

	public void setNowPowerAmplifierTemperature(float nowPowerAmplifierTemperature) {
		this.nowPowerAmplifierTemperature = nowPowerAmplifierTemperature;
	}

	public float getNowRSSI() {
		return nowRSSI;
	}

	public void setNowRSSI(float nowRSSI) {
		this.nowRSSI = nowRSSI;
	}

	public boolean isSailDeployed() {
		return sailDeployed;
	}

	public void setSailDeployed(boolean sailDeployed) {
		this.sailDeployed = sailDeployed;
	}

	public float getMCUTemperature() {
		return MCUTemperature;
	}

	public void setMCUTemperature(float mCUTemperature) {
		MCUTemperature = mCUTemperature;
	}

	public float getMPPT_XSOL_VOLT() {
		return MPPT_XSOL_VOLT;
	}

	public void setMPPT_XSOL_VOLT(float mPPT_XSOL_VOLT) {
		MPPT_XSOL_VOLT = mPPT_XSOL_VOLT;
	}

	public float getMPPT_XSOL_CURR() {
		return MPPT_XSOL_CURR;
	}

	public void setMPPT_XSOL_CURR(float mPPT_XSOL_CURR) {
		MPPT_XSOL_CURR = mPPT_XSOL_CURR;
	}

	public float getMPPT_XSOL_OUT_VOLT() {
		return MPPT_XSOL_OUT_VOLT;
	}

	public void setMPPT_XSOL_OUT_VOLT(float mPPT_XSOL_OUT_VOLT) {
		MPPT_XSOL_OUT_VOLT = mPPT_XSOL_OUT_VOLT;
	}

	public float getMPPT_XTemperature() {
		return MPPT_XTemperature;
	}

	public void setMPPT_XTemperature(float mPPT_XTemperature) {
		MPPT_XTemperature = mPPT_XTemperature;
	}

	public int getMPPT_XState() {
		return MPPT_XState;
	}

	public void setMPPT_XState(int mPPT_XState) {
		MPPT_XState = mPPT_XState;
	}

	public float getMPPT_YPSOL_VOLT() {
		return MPPT_YPSOL_VOLT;
	}

	public void setMPPT_YPSOL_VOLT(float mPPT_YPSOL_VOLT) {
		MPPT_YPSOL_VOLT = mPPT_YPSOL_VOLT;
	}

	public float getMPPT_YPSOL_CURR() {
		return MPPT_YPSOL_CURR;
	}

	public void setMPPT_YPSOL_CURR(float mPPT_YPSOL_CURR) {
		MPPT_YPSOL_CURR = mPPT_YPSOL_CURR;
	}

	public float getMPPT_YPSOL_OUT_VOLT() {
		return MPPT_YPSOL_OUT_VOLT;
	}

	public void setMPPT_YPSOL_OUT_VOLT(float mPPT_YPSOL_OUT_VOLT) {
		MPPT_YPSOL_OUT_VOLT = mPPT_YPSOL_OUT_VOLT;
	}

	public float getMPPT_YPTemperature() {
		return MPPT_YPTemperature;
	}

	public void setMPPT_YPTemperature(float mPPT_YPTemperature) {
		MPPT_YPTemperature = mPPT_YPTemperature;
	}

	public int getMPPT_YPState() {
		return MPPT_YPState;
	}

	public void setMPPT_YPState(int mPPT_YPState) {
		MPPT_YPState = mPPT_YPState;
	}

	public float getMPPT_YMSOL_VOLT() {
		return MPPT_YMSOL_VOLT;
	}

	public void setMPPT_YMSOL_VOLT(float mPPT_YMSOL_VOLT) {
		MPPT_YMSOL_VOLT = mPPT_YMSOL_VOLT;
	}

	public float getMPPT_YMSOL_CURR() {
		return MPPT_YMSOL_CURR;
	}

	public void setMPPT_YMSOL_CURR(float mPPT_YMSOL_CURR) {
		MPPT_YMSOL_CURR = mPPT_YMSOL_CURR;
	}

	public float getMPPT_YMSOL_OUT_VOLT() {
		return MPPT_YMSOL_OUT_VOLT;
	}

	public void setMPPT_YMSOL_OUT_VOLT(float mPPT_YMSOL_OUT_VOLT) {
		MPPT_YMSOL_OUT_VOLT = mPPT_YMSOL_OUT_VOLT;
	}

	public float getMPPT_YMTemperature() {
		return MPPT_YMTemperature;
	}

	public void setMPPT_YMTemperature(float mPPT_YMTemperature) {
		MPPT_YMTemperature = mPPT_YMTemperature;
	}

	public float getMPPT_YMState() {
		return MPPT_YMState;
	}

	public void setMPPT_YMState(float mPPT_YMState) {
		MPPT_YMState = mPPT_YMState;
	}

	public float getDISTRVOLT_3V3() {
		return DISTRVOLT_3V3;
	}

	public void setDISTRVOLT_3V3(float dISTRVOLT_3V3) {
		DISTRVOLT_3V3 = dISTRVOLT_3V3;
	}

	public float getDISTRCURR_3V3() {
		return DISTRCURR_3V3;
	}

	public void setDISTRCURR_3V3(float dISTRCURR_3V3) {
		DISTRCURR_3V3 = dISTRCURR_3V3;
	}

	public float getDISTRVOLT_5V() {
		return DISTRVOLT_5V;
	}

	public void setDISTRVOLT_5V(float dISTRVOLT_5V) {
		DISTRVOLT_5V = dISTRVOLT_5V;
	}

	public float getDISTRCURR_5V() {
		return DISTRCURR_5V;
	}

	public void setDISTRCURR_5V(float dISTRCURR_5V) {
		DISTRCURR_5V = dISTRCURR_5V;
	}

	public float getDISTRVOLT_VBAT() {
		return DISTRVOLT_VBAT;
	}

	public void setDISTRVOLT_VBAT(float dISTRVOLT_VBAT) {
		DISTRVOLT_VBAT = dISTRVOLT_VBAT;
	}

	public float getDISTRCURR_VBAT() {
		return DISTRCURR_VBAT;
	}

	public void setDISTRCURR_VBAT(float dISTRCURR_VBAT) {
		DISTRCURR_VBAT = dISTRCURR_VBAT;
	}

	public int getDISTRLCL_STATE() {
		return DISTRLCL_STATE;
	}

	public void setDISTRLCL_STATE(int dISTRLCL_STATE) {
		DISTRLCL_STATE = dISTRLCL_STATE;
	}

	public int getDISTRLCL_FLAGS() {
		return DISTRLCL_FLAGS;
	}

	public void setDISTRLCL_FLAGS(int dISTRLCL_FLAGS) {
		DISTRLCL_FLAGS = dISTRLCL_FLAGS;
	}

	public float getBATCVOLT_A() {
		return BATCVOLT_A;
	}

	public void setBATCVOLT_A(float bATCVOLT_A) {
		BATCVOLT_A = bATCVOLT_A;
	}

	public float getBATCCHRG_CURR() {
		return BATCCHRG_CURR;
	}

	public void setBATCCHRG_CURR(float bATCCHRG_CURR) {
		BATCCHRG_CURR = bATCCHRG_CURR;
	}

	public float getBATCDCHRG_CURR() {
		return BATCDCHRG_CURR;
	}

	public void setBATCDCHRG_CURR(float bATCDCHRG_CURR) {
		BATCDCHRG_CURR = bATCDCHRG_CURR;
	}

	public float getBATCTemperature() {
		return BATCTemperature;
	}

	public void setBATCTemperature(float bATCTemperature) {
		BATCTemperature = bATCTemperature;
	}

	public int getBATCState() {
		return BATCState;
	}

	public void setBATCState(int bATCState) {
		BATCState = bATCState;
	}

	public float getBPTemperatureA() {
		return BPTemperatureA;
	}

	public void setBPTemperatureA(float bPTemperatureA) {
		BPTemperatureA = bPTemperatureA;
	}

	public float getBPTemperatureB() {
		return BPTemperatureB;
	}

	public void setBPTemperatureB(float bPTemperatureB) {
		BPTemperatureB = bPTemperatureB;
	}

	public int getSafetyCounterA() {
		return SafetyCounterA;
	}

	public void setSafetyCounterA(int safetyCounterA) {
		SafetyCounterA = safetyCounterA;
	}

	public int getPowerCycleCountA() {
		return PowerCycleCountA;
	}

	public void setPowerCycleCountA(int powerCycleCountA) {
		PowerCycleCountA = powerCycleCountA;
	}

	public long getUptimeA() {
		return UptimeA;
	}

	public void setUptimeA(long uptimeA) {
		UptimeA = uptimeA;
	}

	public float getTemperatureA() {
		return TemperatureA;
	}

	public void setTemperatureA(float temperatureA) {
		TemperatureA = temperatureA;
	}

	public float getSUPP_TEMPA() {
		return SUPP_TEMPA;
	}

	public void setSUPP_TEMPA(float sUPP_TEMPA) {
		SUPP_TEMPA = sUPP_TEMPA;
	}

	public float getControllerB3V3dA() {
		return ControllerB3V3dA;
	}

	public void setControllerB3V3dA(float controllerB3V3dA) {
		ControllerB3V3dA = controllerB3V3dA;
	}

	public float getDCDC3V3Temperature() {
		return DCDC3V3Temperature;
	}

	public void setDCDC3V3Temperature(float dCDC3V3Temperature) {
		DCDC3V3Temperature = dCDC3V3Temperature;
	}

	public float getDCDC5VTemperature() {
		return DCDC5VTemperature;
	}

	public void setDCDC5VTemperature(float dCDC5VTemperature) {
		DCDC5VTemperature = dCDC5VTemperature;
	}

	public float getBPTemperature() {
		return BPTemperature;
	}

	public void setBPTemperature(float bPTemperature) {
		BPTemperature = bPTemperature;
	}

	public float getBATCVOLT_B() {
		return BATCVOLT_B;
	}

	public void setBATCVOLT_B(float bATCVOLT_B) {
		BATCVOLT_B = bATCVOLT_B;
	}

	public int getSafetyCounterB() {
		return SafetyCounterB;
	}

	public void setSafetyCounterB(int safetyCounterB) {
		SafetyCounterB = safetyCounterB;
	}

	public int getPowerCycleCountB() {
		return PowerCycleCountB;
	}

	public void setPowerCycleCountB(int powerCycleCountB) {
		PowerCycleCountB = powerCycleCountB;
	}

	public long getUptimeB() {
		return UptimeB;
	}

	public void setUptimeB(long uptimeB) {
		UptimeB = uptimeB;
	}

	public float getTemperatureB() {
		return TemperatureB;
	}

	public void setTemperatureB(float temperatureB) {
		TemperatureB = temperatureB;
	}

	public float getSUPP_TEMPB() {
		return SUPP_TEMPB;
	}

	public void setSUPP_TEMPB(float sUPP_TEMPB) {
		SUPP_TEMPB = sUPP_TEMPB;
	}

	public float getControllerA3V3dB() {
		return ControllerA3V3dB;
	}

	public void setControllerA3V3dB(float controllerA3V3dB) {
		ControllerA3V3dB = controllerA3V3dB;
	}

	public float getMagnetometerMeasurement1() {
		return MagnetometerMeasurement1;
	}

	public void setMagnetometerMeasurement1(float magnetometerMeasurement1) {
		MagnetometerMeasurement1 = magnetometerMeasurement1;
	}

	public float getMagnetometerMeasurement2() {
		return MagnetometerMeasurement2;
	}

	public void setMagnetometerMeasurement2(float magnetometerMeasurement2) {
		MagnetometerMeasurement2 = magnetometerMeasurement2;
	}

	public float getMagnetometerMeasurement3() {
		return MagnetometerMeasurement3;
	}

	public void setMagnetometerMeasurement3(float magnetometerMeasurement3) {
		MagnetometerMeasurement3 = magnetometerMeasurement3;
	}

	public boolean isCoilsActiveDuringMeasurement() {
		return coilsActiveDuringMeasurement;
	}

	public void setCoilsActiveDuringMeasurement(boolean coilsActiveDuringMeasurement) {
		this.coilsActiveDuringMeasurement = coilsActiveDuringMeasurement;
	}

	public float getIMTQDipole1() {
		return IMTQDipole1;
	}

	public void setIMTQDipole1(float iMTQDipole1) {
		IMTQDipole1 = iMTQDipole1;
	}

	public float getIMTQDipole2() {
		return IMTQDipole2;
	}

	public void setIMTQDipole2(float iMTQDipole2) {
		IMTQDipole2 = iMTQDipole2;
	}

	public float getIMTQDipole3() {
		return IMTQDipole3;
	}

	public void setIMTQDipole3(float iMTQDipole3) {
		IMTQDipole3 = iMTQDipole3;
	}

	public float getIMTQBDot1() {
		return IMTQBDot1;
	}

	public void setIMTQBDot1(float iMTQBDot1) {
		IMTQBDot1 = iMTQBDot1;
	}

	public float getIMTQBDot2() {
		return IMTQBDot2;
	}

	public void setIMTQBDot2(float iMTQBDot2) {
		IMTQBDot2 = iMTQBDot2;
	}

	public float getIMTQBDot3() {
		return IMTQBDot3;
	}

	public void setIMTQBDot3(float iMTQBDot3) {
		IMTQBDot3 = iMTQBDot3;
	}

	public float getIMTQDigitalVoltage() {
		return IMTQDigitalVoltage;
	}

	public void setIMTQDigitalVoltage(float iMTQDigitalVoltage) {
		IMTQDigitalVoltage = iMTQDigitalVoltage;
	}

	public float getIMTQAnalogVoltage() {
		return IMTQAnalogVoltage;
	}

	public void setIMTQAnalogVoltage(float iMTQAnalogVoltage) {
		IMTQAnalogVoltage = iMTQAnalogVoltage;
	}

	public float getIMTQDigitalCurrent() {
		return IMTQDigitalCurrent;
	}

	public void setIMTQDigitalCurrent(float iMTQDigitalCurrent) {
		IMTQDigitalCurrent = iMTQDigitalCurrent;
	}

	public float getIMTQAnalogCurrent() {
		return IMTQAnalogCurrent;
	}

	public void setIMTQAnalogCurrent(float iMTQAnalogCurrent) {
		IMTQAnalogCurrent = iMTQAnalogCurrent;
	}

	public short getIMTQMCUTemperature() {
		return IMTQMCUTemperature;
	}

	public void setIMTQMCUTemperature(short iMTQMCUTemperature) {
		IMTQMCUTemperature = iMTQMCUTemperature;
	}

	public float getIMTQCoilCurrent1() {
		return IMTQCoilCurrent1;
	}

	public void setIMTQCoilCurrent1(float iMTQCoilCurrent1) {
		IMTQCoilCurrent1 = iMTQCoilCurrent1;
	}

	public float getIMTQCoilCurrent2() {
		return IMTQCoilCurrent2;
	}

	public void setIMTQCoilCurrent2(float iMTQCoilCurrent2) {
		IMTQCoilCurrent2 = iMTQCoilCurrent2;
	}

	public float getIMTQCoilCurrent3() {
		return IMTQCoilCurrent3;
	}

	public void setIMTQCoilCurrent3(float iMTQCoilCurrent3) {
		IMTQCoilCurrent3 = iMTQCoilCurrent3;
	}

	public short getIMTQCoilTemperature1() {
		return IMTQCoilTemperature1;
	}

	public void setIMTQCoilTemperature1(short iMTQCoilTemperature1) {
		IMTQCoilTemperature1 = iMTQCoilTemperature1;
	}

	public short getIMTQCoilTemperature2() {
		return IMTQCoilTemperature2;
	}

	public void setIMTQCoilTemperature2(short iMTQCoilTemperature2) {
		IMTQCoilTemperature2 = iMTQCoilTemperature2;
	}

	public short getIMTQCoilTemperature3() {
		return IMTQCoilTemperature3;
	}

	public void setIMTQCoilTemperature3(short iMTQCoilTemperature3) {
		IMTQCoilTemperature3 = iMTQCoilTemperature3;
	}

	public int getIMTQStatus() {
		return IMTQStatus;
	}

	public void setIMTQStatus(int iMTQStatus) {
		IMTQStatus = iMTQStatus;
	}

	public int getIMTQMode() {
		return IMTQMode;
	}

	public void setIMTQMode(int iMTQMode) {
		IMTQMode = iMTQMode;
	}

	public boolean isIMTQErrorDuringPreviousIteration() {
		return IMTQErrorDuringPreviousIteration;
	}

	public void setIMTQErrorDuringPreviousIteration(boolean iMTQErrorDuringPreviousIteration) {
		IMTQErrorDuringPreviousIteration = iMTQErrorDuringPreviousIteration;
	}

	public boolean isIMTQConfigurationChanged() {
		return IMTQConfigurationChanged;
	}

	public void setIMTQConfigurationChanged(boolean iMTQConfigurationChanged) {
		IMTQConfigurationChanged = iMTQConfigurationChanged;
	}

	public long getIMTQUptime() {
		return IMTQUptime;
	}

	public void setIMTQUptime(long iMTQUptime) {
		IMTQUptime = iMTQUptime;
	}

	public int getIMTQError1() {
		return IMTQError1;
	}

	public void setIMTQError1(int iMTQError1) {
		IMTQError1 = iMTQError1;
	}

	public int getIMTQError2() {
		return IMTQError2;
	}

	public void setIMTQError2(int iMTQError2) {
		IMTQError2 = iMTQError2;
	}

	public int getIMTQError3() {
		return IMTQError3;
	}

	public void setIMTQError3(int iMTQError3) {
		IMTQError3 = iMTQError3;
	}

	public int getIMTQError4() {
		return IMTQError4;
	}

	public void setIMTQError4(int iMTQError4) {
		IMTQError4 = iMTQError4;
	}

	public int getIMTQError5() {
		return IMTQError5;
	}

	public void setIMTQError5(int iMTQError5) {
		IMTQError5 = iMTQError5;
	}

	public int getIMTQError6() {
		return IMTQError6;
	}

	public void setIMTQError6(int iMTQError6) {
		IMTQError6 = iMTQError6;
	}

	public int getIMTQError7() {
		return IMTQError7;
	}

	public void setIMTQError7(int iMTQError7) {
		IMTQError7 = iMTQError7;
	}

	public int getIMTQError8() {
		return IMTQError8;
	}

	public void setIMTQError8(int iMTQError8) {
		IMTQError8 = iMTQError8;
	}

}
