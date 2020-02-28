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
	private int framTMR;
	private int payload;
	private int camera;
	private int expSuns;
	private int antPrimary;
	private int antBackup;

	private int primaryFlashScrubbingPointer;
	private int secondaryFlashScrubbingPointer;
	private long ramScrubbingPointer;
	private int uptimeSeconds;
	private long freeSpace;

	private boolean antenna1DeploymentSwitchChA;
	private boolean antenna2DeploymentSwitchChA;
	private boolean antenna3DeploymentSwitchChA;
	private boolean antenna4DeploymentSwitchChA;
	private boolean antenna1DeploymentSwitchChB;
	private boolean antenna2DeploymentSwitchChB;
	private boolean antenna3DeploymentSwitchChB;
	private boolean antenna4DeploymentSwitchChB;

	private boolean antenna1TimeLimitReachedChA;
	private boolean antenna2TimeLimitReachedChA;
	private boolean antenna3TimeLimitReachedChA;
	private boolean antenna4TimeLimitReachedChA;
	private boolean antenna1TimeLimitReachedChB;
	private boolean antenna2TimeLimitReachedChB;
	private boolean antenna3TimeLimitReachedChB;
	private boolean antenna4TimeLimitReachedChB;

	private boolean antenna1BurnActiveChA;
	private boolean antenna2BurnActiveChA;
	private boolean antenna3BurnActiveChA;
	private boolean antenna4BurnActiveChA;
	private boolean antenna1BurnActiveChB;
	private boolean antenna2BurnActiveChB;
	private boolean antenna3BurnActiveChB;
	private boolean antenna4BurnActiveChB;

	private boolean systemIndependentBurnChA;
	private boolean systemIndependentBurnChB;
	private boolean ignoringSwitchesChA;
	private boolean ignoringSwitchesChB;
	private boolean armedChA;
	private boolean armedChB;
	private int antenna1ActivationCountChA;
	private int antenna2ActivationCountChA;
	private int antenna3ActivationCountChA;
	private int antenna4ActivationCountChA;
	private int antenna1ActivationCountChB;
	private int antenna2ActivationCountChB;
	private int antenna3ActivationCountChB;
	private int antenna4ActivationCountChB;

	private int antenna1ActivationTimeChA;
	private int antenna2ActivationTimeChA;
	private int antenna3ActivationTimeChA;
	private int antenna4ActivationTimeChA;
	private int antenna1ActivationTimeChB;
	private int antenna2ActivationTimeChB;
	private int antenna3ActivationTimeChB;
	private int antenna4ActivationTimeChB;

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

	private float mcuTemperature;

	private float mpptXSolVolt;
	private float mpptXSolCurr;
	private float mpptXSolOutVolt;
	private float mpptXTemperature;
	private int mpptXState;
	private float mpptYSolVolt;
	private float mpptYPSolCurr;
	private float mpptYPSolOutVolt;
	private float mpptYPTemperature;
	private int mpptYPState;
	private float mpptYMSolVolt;
	private float mpptYMSolCurr;
	private float mpptYMSolOutVolt;
	private float mpptYMTemperature;
	private float mpptYMState;
	private float distrVolt3V3;
	private float distrCurr3V3;
	private float distrVolt5V;
	private float distrCurr5V;
	private float distrVoltVbat;
	private float distrCurrVbat;
	private int distrLCLState;
	private int distrLCLFlags;
	private float batcVoltA;
	private float batcChrgCurr;
	private float batcDchrgCurr;
	private float batcTemperature;
	private int batcState;
	private float bpTemperatureA;
	private float bpTemperatureB;
	private int safetyCounterA;
	private int powerCycleCountA;
	private long uptimeA;
	private float temperatureA;
	private float suppTempA;
	private float controllerB3V3dA;
	private float dcdc3V3Temperature;
	private float dcdc5VTemperature;

	private float bpTemperature;
	private float batcVoltB;
	private int safetyCounterB;
	private int powerCycleCountB;
	private long uptimeB;
	private float temperatureB;
	private float suppTempB;
	private float controllerA3V3dB;

	private float magnetometerMeasurement1;
	private float magnetometerMeasurement2;
	private float magnetometerMeasurement3;

	private boolean coilsActiveDuringMeasurement;

	private float imtqDipole1;
	private float imtqDipole2;
	private float imtqDipole3;

	private float imtqBDot1;
	private float imtqBDot2;
	private float imtqBDot3;

	private float imtqDigitalVoltage;
	private float imtqAnalogVoltage;
	private float imtqDigitalCurrent;
	private float imtqAnalogCurrent;
	private short imtqMCUTemperature;

	private float imtqCoilCurrent1;
	private float imtqCoilCurrent2;
	private float imtqCoilCurrent3;

	private short imtqCoilTemperature1;
	private short imtqCoilTemperature2;
	private short imtqCoilTemperature3;

	private int imtqStatus;
	private int imtqMode;
	private boolean imtqErrorDuringPreviousIteration;
	private boolean imtqConfigurationChanged;
	private long imtqUptime;

	private int imtqError1;
	private int imtqError2;
	private int imtqError3;
	private int imtqError4;
	private int imtqError5;
	private int imtqError6;
	private int imtqError7;
	private int imtqError8;
	
	public BeaconFrame() {
		//do nothing
	}

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
		framTMR = dis.readUnsignedByte();
		payload = dis.readUnsignedByte();
		camera = dis.readUnsignedByte();
		expSuns = dis.readUnsignedByte();
		antPrimary = dis.readUnsignedByte();
		antBackup = dis.readUnsignedByte();

		primaryFlashScrubbingPointer = dis.readUnsignedInt(3);
		secondaryFlashScrubbingPointer = dis.readUnsignedInt(3);
		ramScrubbingPointer = dis.readUnsignedInt();
		uptimeSeconds = dis.readUnsignedInt(22);
		freeSpace = dis.readUnsignedLong(32);

		int raw = dis.readUnsignedByte();
		antenna1DeploymentSwitchChA = (raw & 0x1) > 0;
		antenna2DeploymentSwitchChA = ((raw >> 1) & 0x1) > 0;
		antenna3DeploymentSwitchChA = ((raw >> 2) & 0x1) > 0;
		antenna4DeploymentSwitchChA = ((raw >> 3) & 0x1) > 0;
		antenna1DeploymentSwitchChB = ((raw >> 4) & 0x1) > 0;
		antenna2DeploymentSwitchChB = ((raw >> 5) & 0x1) > 0;
		antenna3DeploymentSwitchChB = ((raw >> 6) & 0x1) > 0;
		antenna4DeploymentSwitchChB = ((raw >> 7) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		antenna1TimeLimitReachedChA = (raw & 0x1) > 0;
		antenna2TimeLimitReachedChA = ((raw >> 1) & 0x1) > 0;
		antenna3TimeLimitReachedChA = ((raw >> 2) & 0x1) > 0;
		antenna4TimeLimitReachedChA = ((raw >> 3) & 0x1) > 0;
		antenna1TimeLimitReachedChB = ((raw >> 4) & 0x1) > 0;
		antenna2TimeLimitReachedChB = ((raw >> 5) & 0x1) > 0;
		antenna3TimeLimitReachedChB = ((raw >> 6) & 0x1) > 0;
		antenna4TimeLimitReachedChB = ((raw >> 7) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		antenna1BurnActiveChA = (raw & 0x1) > 0;
		antenna2BurnActiveChA = ((raw >> 1) & 0x1) > 0;
		antenna3BurnActiveChA = ((raw >> 2) & 0x1) > 0;
		antenna4BurnActiveChA = ((raw >> 3) & 0x1) > 0;
		antenna1BurnActiveChB = ((raw >> 4) & 0x1) > 0;
		antenna2BurnActiveChB = ((raw >> 5) & 0x1) > 0;
		antenna3BurnActiveChB = ((raw >> 6) & 0x1) > 0;
		antenna4BurnActiveChB = ((raw >> 7) & 0x1) > 0;

		systemIndependentBurnChA = dis.readBoolean();
		systemIndependentBurnChB = dis.readBoolean();
		ignoringSwitchesChA = dis.readBoolean();
		ignoringSwitchesChB = dis.readBoolean();
		armedChA = dis.readBoolean();
		armedChB = dis.readBoolean();

		antenna1ActivationCountChA = dis.readUnsignedInt(3);
		antenna2ActivationCountChA = dis.readUnsignedInt(3);
		antenna3ActivationCountChA = dis.readUnsignedInt(3);
		antenna4ActivationCountChA = dis.readUnsignedInt(3);
		antenna1ActivationCountChB = dis.readUnsignedInt(3);
		antenna2ActivationCountChB = dis.readUnsignedInt(3);
		antenna3ActivationCountChB = dis.readUnsignedInt(3);
		antenna4ActivationCountChB = dis.readUnsignedInt(3);

		antenna1ActivationTimeChA = dis.readUnsignedByte();
		antenna2ActivationTimeChA = dis.readUnsignedByte();
		antenna3ActivationTimeChA = dis.readUnsignedByte();
		antenna4ActivationTimeChA = dis.readUnsignedByte();
		antenna1ActivationTimeChB = dis.readUnsignedByte();
		antenna2ActivationTimeChB = dis.readUnsignedByte();
		antenna3ActivationTimeChB = dis.readUnsignedByte();
		antenna4ActivationTimeChB = dis.readUnsignedByte();

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

		mcuTemperature = 25.0f - (2297.0f - dis.readUnsignedInt(12)) / -6.3f;

		mpptXSolVolt = readMPPTVoltage(dis);
		mpptXSolCurr = readMPPTCurrent(dis);
		mpptXSolOutVolt = readMPPTVoltage(dis);
		mpptXTemperature = readMPPTTemperature(dis);
		mpptXState = dis.readUnsignedInt(3);
		mpptYSolVolt = readMPPTVoltage(dis);
		mpptYPSolCurr = readMPPTCurrent(dis);
		mpptYPSolOutVolt = readMPPTVoltage(dis);
		mpptYPTemperature = readMPPTTemperature(dis);
		mpptYPState = dis.readUnsignedInt(3);
		mpptYMSolVolt = readMPPTVoltage(dis);
		mpptYMSolCurr = readMPPTCurrent(dis);
		mpptYMSolOutVolt = readMPPTVoltage(dis);
		mpptYMTemperature = readMPPTTemperature(dis);
		mpptYMState = dis.readUnsignedInt(3);
		distrVolt3V3 = readDistributionVoltage(dis);
		distrCurr3V3 = readDistributionCurrent(dis);
		distrVolt5V = readDistributionVoltage(dis);
		distrCurr5V = readDistributionCurrent(dis);
		distrVoltVbat = readDistributionVoltage(dis);
		distrCurrVbat = readDistributionCurrent(dis);
		distrLCLState = dis.readUnsignedInt(7);
		distrLCLFlags = dis.readUnsignedInt(6);
		batcVoltA = readBATCVoltage(dis);
		batcChrgCurr = readDistributionCurrent(dis);
		batcDchrgCurr = readDistributionCurrent(dis);
		batcTemperature = readLMT87Temperature(dis);
		batcState = dis.readUnsignedInt(3);
		bpTemperatureA = readTMP121Temperature(dis);
		bpTemperatureB = readTMP121Temperature(dis);
		safetyCounterA = dis.readUnsignedByte();
		powerCycleCountA = dis.readUnsignedShort();
		uptimeA = dis.readUnsignedInt();
		temperatureA = readLMT87Temperature(dis);
		suppTempA = readLMT87Temperature(dis);
		controllerB3V3dA = readLocal3V3dVoltage(dis);
		dcdc3V3Temperature = readLMT87Temperature(dis);
		dcdc5VTemperature = readLMT87Temperature(dis);

		bpTemperature = readPT1000Temperature(dis);
		batcVoltB = readBATCVoltage(dis);
		safetyCounterB = dis.readUnsignedByte();
		powerCycleCountB = dis.readUnsignedShort();
		uptimeB = dis.readUnsignedInt();
		temperatureB = readLMT87Temperature(dis);
		suppTempB = readLMT87Temperature(dis);
		controllerA3V3dB = readLocal3V3dVoltage(dis);

		magnetometerMeasurement1 = dis.readLongTwosComplement(32) * 1e-3f;
		magnetometerMeasurement2 = dis.readLongTwosComplement(32) * 1e-3f;
		magnetometerMeasurement3 = dis.readLongTwosComplement(32) * 1e-3f;

		coilsActiveDuringMeasurement = dis.readBoolean();

		imtqDipole1 = dis.readIntTwosComplement(16) * 1e-1f;
		imtqDipole2 = dis.readIntTwosComplement(16) * 1e-1f;
		imtqDipole3 = dis.readIntTwosComplement(16) * 1e-1f;

		imtqBDot1 = dis.readLongTwosComplement(32) * 1e-3f;
		imtqBDot2 = dis.readLongTwosComplement(32) * 1e-3f;
		imtqBDot3 = dis.readLongTwosComplement(32) * 1e-3f;

		imtqDigitalVoltage = dis.readUnsignedShort() / 1000.0f;
		imtqAnalogVoltage = dis.readUnsignedShort() / 1000.0f;
		imtqDigitalCurrent = dis.readUnsignedShort() * 1e-1f;
		imtqAnalogCurrent = dis.readUnsignedShort() * 1e-1f;
		imtqMCUTemperature = (short) dis.readIntTwosComplement(16);

		imtqCoilCurrent1 = dis.readUnsignedShort() * 1e-1f;
		imtqCoilCurrent2 = dis.readUnsignedShort() * 1e-1f;
		imtqCoilCurrent3 = dis.readUnsignedShort() * 1e-1f;

		imtqCoilTemperature1 = (short) dis.readIntTwosComplement(16);
		imtqCoilTemperature2 = (short) dis.readIntTwosComplement(16);
		imtqCoilTemperature3 = (short) dis.readIntTwosComplement(16);

		imtqStatus = dis.readUnsignedByte();
		imtqMode = dis.readUnsignedInt(2);
		imtqErrorDuringPreviousIteration = dis.readUnsignedByte() > 0;
		imtqConfigurationChanged = dis.readBoolean();
		imtqUptime = dis.readUnsignedInt();

		imtqError1 = dis.readUnsignedByte();
		imtqError2 = dis.readUnsignedByte();
		imtqError3 = dis.readUnsignedByte();
		imtqError4 = dis.readUnsignedByte();
		imtqError5 = dis.readUnsignedByte();
		imtqError6 = dis.readUnsignedByte();
		imtqError7 = dis.readUnsignedByte();
		imtqError8 = dis.readUnsignedByte();
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

	public int getFramTMR() {
		return framTMR;
	}
	
	public void setFramTMR(int framTMR) {
		this.framTMR = framTMR;
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

	public int getAntPrimary() {
		return antPrimary;
	}

	public void setAntPrimary(int antPrimary) {
		this.antPrimary = antPrimary;
	}

	public int getAntBackup() {
		return antBackup;
	}

	public void setAntBackup(int antBackup) {
		this.antBackup = antBackup;
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

	public long getRamScrubbingPointer() {
		return ramScrubbingPointer;
	}

	public void setRamScrubbingPointer(long ramScrubbingPointer) {
		this.ramScrubbingPointer = ramScrubbingPointer;
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
		return antenna1DeploymentSwitchChA;
	}

	public void setAntenna1DeploymentSwitchChA(boolean antenna1DeploymentSwitchChA) {
		this.antenna1DeploymentSwitchChA = antenna1DeploymentSwitchChA;
	}

	public boolean isAntenna2DeploymentSwitchChA() {
		return antenna2DeploymentSwitchChA;
	}

	public void setAntenna2DeploymentSwitchChA(boolean antenna2DeploymentSwitchChA) {
		this.antenna2DeploymentSwitchChA = antenna2DeploymentSwitchChA;
	}

	public boolean isAntenna3DeploymentSwitchChA() {
		return antenna3DeploymentSwitchChA;
	}

	public void setAntenna3DeploymentSwitchChA(boolean antenna3DeploymentSwitchChA) {
		this.antenna3DeploymentSwitchChA = antenna3DeploymentSwitchChA;
	}

	public boolean isAntenna4DeploymentSwitchChA() {
		return antenna4DeploymentSwitchChA;
	}

	public void setAntenna4DeploymentSwitchChA(boolean antenna4DeploymentSwitchChA) {
		this.antenna4DeploymentSwitchChA = antenna4DeploymentSwitchChA;
	}

	public boolean isAntenna1DeploymentSwitchChB() {
		return antenna1DeploymentSwitchChB;
	}

	public void setAntenna1DeploymentSwitchChB(boolean antenna1DeploymentSwitchChB) {
		this.antenna1DeploymentSwitchChB = antenna1DeploymentSwitchChB;
	}

	public boolean isAntenna2DeploymentSwitchChB() {
		return antenna2DeploymentSwitchChB;
	}

	public void setAntenna2DeploymentSwitchChB(boolean antenna2DeploymentSwitchChB) {
		this.antenna2DeploymentSwitchChB = antenna2DeploymentSwitchChB;
	}

	public boolean isAntenna3DeploymentSwitchChB() {
		return antenna3DeploymentSwitchChB;
	}

	public void setAntenna3DeploymentSwitchChB(boolean antenna3DeploymentSwitchChB) {
		this.antenna3DeploymentSwitchChB = antenna3DeploymentSwitchChB;
	}

	public boolean isAntenna4DeploymentSwitchChB() {
		return antenna4DeploymentSwitchChB;
	}

	public void setAntenna4DeploymentSwitchChB(boolean antenna4DeploymentSwitchChB) {
		this.antenna4DeploymentSwitchChB = antenna4DeploymentSwitchChB;
	}

	public boolean isAntenna1TimeLimitReachedChA() {
		return antenna1TimeLimitReachedChA;
	}

	public void setAntenna1TimeLimitReachedChA(boolean antenna1TimeLimitReachedChA) {
		this.antenna1TimeLimitReachedChA = antenna1TimeLimitReachedChA;
	}

	public boolean isAntenna2TimeLimitReachedChA() {
		return antenna2TimeLimitReachedChA;
	}

	public void setAntenna2TimeLimitReachedChA(boolean antenna2TimeLimitReachedChA) {
		this.antenna2TimeLimitReachedChA = antenna2TimeLimitReachedChA;
	}

	public boolean isAntenna3TimeLimitReachedChA() {
		return antenna3TimeLimitReachedChA;
	}

	public void setAntenna3TimeLimitReachedChA(boolean antenna3TimeLimitReachedChA) {
		this.antenna3TimeLimitReachedChA = antenna3TimeLimitReachedChA;
	}

	public boolean isAntenna4TimeLimitReachedChA() {
		return antenna4TimeLimitReachedChA;
	}

	public void setAntenna4TimeLimitReachedChA(boolean antenna4TimeLimitReachedChA) {
		this.antenna4TimeLimitReachedChA = antenna4TimeLimitReachedChA;
	}

	public boolean isAntenna1TimeLimitReachedChB() {
		return antenna1TimeLimitReachedChB;
	}

	public void setAntenna1TimeLimitReachedChB(boolean antenna1TimeLimitReachedChB) {
		this.antenna1TimeLimitReachedChB = antenna1TimeLimitReachedChB;
	}

	public boolean isAntenna2TimeLimitReachedChB() {
		return antenna2TimeLimitReachedChB;
	}

	public void setAntenna2TimeLimitReachedChB(boolean antenna2TimeLimitReachedChB) {
		this.antenna2TimeLimitReachedChB = antenna2TimeLimitReachedChB;
	}

	public boolean isAntenna3TimeLimitReachedChB() {
		return antenna3TimeLimitReachedChB;
	}

	public void setAntenna3TimeLimitReachedChB(boolean antenna3TimeLimitReachedChB) {
		this.antenna3TimeLimitReachedChB = antenna3TimeLimitReachedChB;
	}

	public boolean isAntenna4TimeLimitReachedChB() {
		return antenna4TimeLimitReachedChB;
	}

	public void setAntenna4TimeLimitReachedChB(boolean antenna4TimeLimitReachedChB) {
		this.antenna4TimeLimitReachedChB = antenna4TimeLimitReachedChB;
	}

	public boolean isAntenna1BurnActiveChA() {
		return antenna1BurnActiveChA;
	}

	public void setAntenna1BurnActiveChA(boolean antenna1BurnActiveChA) {
		this.antenna1BurnActiveChA = antenna1BurnActiveChA;
	}

	public boolean isAntenna2BurnActiveChA() {
		return antenna2BurnActiveChA;
	}

	public void setAntenna2BurnActiveChA(boolean antenna2BurnActiveChA) {
		this.antenna2BurnActiveChA = antenna2BurnActiveChA;
	}

	public boolean isAntenna3BurnActiveChA() {
		return antenna3BurnActiveChA;
	}

	public void setAntenna3BurnActiveChA(boolean antenna3BurnActiveChA) {
		this.antenna3BurnActiveChA = antenna3BurnActiveChA;
	}

	public boolean isAntenna4BurnActiveChA() {
		return antenna4BurnActiveChA;
	}

	public void setAntenna4BurnActiveChA(boolean antenna4BurnActiveChA) {
		this.antenna4BurnActiveChA = antenna4BurnActiveChA;
	}

	public boolean isAntenna1BurnActiveChB() {
		return antenna1BurnActiveChB;
	}

	public void setAntenna1BurnActiveChB(boolean antenna1BurnActiveChB) {
		this.antenna1BurnActiveChB = antenna1BurnActiveChB;
	}

	public boolean isAntenna2BurnActiveChB() {
		return antenna2BurnActiveChB;
	}

	public void setAntenna2BurnActiveChB(boolean antenna2BurnActiveChB) {
		this.antenna2BurnActiveChB = antenna2BurnActiveChB;
	}

	public boolean isAntenna3BurnActiveChB() {
		return antenna3BurnActiveChB;
	}

	public void setAntenna3BurnActiveChB(boolean antenna3BurnActiveChB) {
		this.antenna3BurnActiveChB = antenna3BurnActiveChB;
	}

	public boolean isAntenna4BurnActiveChB() {
		return antenna4BurnActiveChB;
	}

	public void setAntenna4BurnActiveChB(boolean antenna4BurnActiveChB) {
		this.antenna4BurnActiveChB = antenna4BurnActiveChB;
	}

	public boolean isSystemIndependentBurnChA() {
		return systemIndependentBurnChA;
	}

	public void setSystemIndependentBurnChA(boolean systemIndependentBurnChA) {
		this.systemIndependentBurnChA = systemIndependentBurnChA;
	}

	public boolean isSystemIndependentBurnChB() {
		return systemIndependentBurnChB;
	}

	public void setSystemIndependentBurnChB(boolean systemIndependentBurnChB) {
		this.systemIndependentBurnChB = systemIndependentBurnChB;
	}

	public boolean isIgnoringSwitchesChA() {
		return ignoringSwitchesChA;
	}

	public void setIgnoringSwitchesChA(boolean ignoringSwitchesChA) {
		this.ignoringSwitchesChA = ignoringSwitchesChA;
	}

	public boolean isIgnoringSwitchesChB() {
		return ignoringSwitchesChB;
	}

	public void setIgnoringSwitchesChB(boolean ignoringSwitchesChB) {
		this.ignoringSwitchesChB = ignoringSwitchesChB;
	}

	public boolean isArmedChA() {
		return armedChA;
	}

	public void setArmedChA(boolean armedChA) {
		this.armedChA = armedChA;
	}

	public boolean isArmedChB() {
		return armedChB;
	}

	public void setArmedChB(boolean armedChB) {
		this.armedChB = armedChB;
	}

	public int getAntenna1ActivationCountChA() {
		return antenna1ActivationCountChA;
	}

	public void setAntenna1ActivationCountChA(int antenna1ActivationCountChA) {
		this.antenna1ActivationCountChA = antenna1ActivationCountChA;
	}

	public int getAntenna2ActivationCountChA() {
		return antenna2ActivationCountChA;
	}

	public void setAntenna2ActivationCountChA(int antenna2ActivationCountChA) {
		this.antenna2ActivationCountChA = antenna2ActivationCountChA;
	}

	public int getAntenna3ActivationCountChA() {
		return antenna3ActivationCountChA;
	}

	public void setAntenna3ActivationCountChA(int antenna3ActivationCountChA) {
		this.antenna3ActivationCountChA = antenna3ActivationCountChA;
	}

	public int getAntenna4ActivationCountChA() {
		return antenna4ActivationCountChA;
	}

	public void setAntenna4ActivationCountChA(int antenna4ActivationCountChA) {
		this.antenna4ActivationCountChA = antenna4ActivationCountChA;
	}

	public int getAntenna1ActivationCountChB() {
		return antenna1ActivationCountChB;
	}

	public void setAntenna1ActivationCountChB(int antenna1ActivationCountChB) {
		this.antenna1ActivationCountChB = antenna1ActivationCountChB;
	}

	public int getAntenna2ActivationCountChB() {
		return antenna2ActivationCountChB;
	}

	public void setAntenna2ActivationCountChB(int antenna2ActivationCountChB) {
		this.antenna2ActivationCountChB = antenna2ActivationCountChB;
	}

	public int getAntenna3ActivationCountChB() {
		return antenna3ActivationCountChB;
	}

	public void setAntenna3ActivationCountChB(int antenna3ActivationCountChB) {
		this.antenna3ActivationCountChB = antenna3ActivationCountChB;
	}

	public int getAntenna4ActivationCountChB() {
		return antenna4ActivationCountChB;
	}

	public void setAntenna4ActivationCountChB(int antenna4ActivationCountChB) {
		this.antenna4ActivationCountChB = antenna4ActivationCountChB;
	}

	public int getAntenna1ActivationTimeChA() {
		return antenna1ActivationTimeChA;
	}

	public void setAntenna1ActivationTimeChA(int antenna1ActivationTimeChA) {
		this.antenna1ActivationTimeChA = antenna1ActivationTimeChA;
	}

	public int getAntenna2ActivationTimeChA() {
		return antenna2ActivationTimeChA;
	}

	public void setAntenna2ActivationTimeChA(int antenna2ActivationTimeChA) {
		this.antenna2ActivationTimeChA = antenna2ActivationTimeChA;
	}

	public int getAntenna3ActivationTimeChA() {
		return antenna3ActivationTimeChA;
	}

	public void setAntenna3ActivationTimeChA(int antenna3ActivationTimeChA) {
		this.antenna3ActivationTimeChA = antenna3ActivationTimeChA;
	}

	public int getAntenna4ActivationTimeChA() {
		return antenna4ActivationTimeChA;
	}

	public void setAntenna4ActivationTimeChA(int antenna4ActivationTimeChA) {
		this.antenna4ActivationTimeChA = antenna4ActivationTimeChA;
	}

	public int getAntenna1ActivationTimeChB() {
		return antenna1ActivationTimeChB;
	}

	public void setAntenna1ActivationTimeChB(int antenna1ActivationTimeChB) {
		this.antenna1ActivationTimeChB = antenna1ActivationTimeChB;
	}

	public int getAntenna2ActivationTimeChB() {
		return antenna2ActivationTimeChB;
	}

	public void setAntenna2ActivationTimeChB(int antenna2ActivationTimeChB) {
		this.antenna2ActivationTimeChB = antenna2ActivationTimeChB;
	}

	public int getAntenna3ActivationTimeChB() {
		return antenna3ActivationTimeChB;
	}

	public void setAntenna3ActivationTimeChB(int antenna3ActivationTimeChB) {
		this.antenna3ActivationTimeChB = antenna3ActivationTimeChB;
	}

	public int getAntenna4ActivationTimeChB() {
		return antenna4ActivationTimeChB;
	}

	public void setAntenna4ActivationTimeChB(int antenna4ActivationTimeChB) {
		this.antenna4ActivationTimeChB = antenna4ActivationTimeChB;
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

	public float getMcuTemperature() {
		return mcuTemperature;
	}

	public void setMcuTemperature(float mcuTemperature) {
		this.mcuTemperature = mcuTemperature;
	}

	public float getMpptXSolVolt() {
		return mpptXSolVolt;
	}

	public void setMpptXSolVolt(float mpptXSolVolt) {
		this.mpptXSolVolt = mpptXSolVolt;
	}

	public float getMpptXSolCurr() {
		return mpptXSolCurr;
	}

	public void setMpptXSolCurr(float mpptXSolCurr) {
		this.mpptXSolCurr = mpptXSolCurr;
	}

	public float getMpptXSolOutVolt() {
		return mpptXSolOutVolt;
	}

	public void setMpptXSolOutVolt(float mpptXSolOutVolt) {
		this.mpptXSolOutVolt = mpptXSolOutVolt;
	}

	public float getMpptXTemperature() {
		return mpptXTemperature;
	}

	public void setMpptXTemperature(float mpptXTemperature) {
		this.mpptXTemperature = mpptXTemperature;
	}

	public int getMpptXState() {
		return mpptXState;
	}

	public void setMpptXState(int mpptXState) {
		this.mpptXState = mpptXState;
	}

	public float getMpptYSolVolt() {
		return mpptYSolVolt;
	}

	public void setMpptYSolVolt(float mpptYSolVolt) {
		this.mpptYSolVolt = mpptYSolVolt;
	}

	public float getMpptYPSolCurr() {
		return mpptYPSolCurr;
	}

	public void setMpptYPSolCurr(float mpptYPSolCurr) {
		this.mpptYPSolCurr = mpptYPSolCurr;
	}

	public float getMpptYPSolOutVolt() {
		return mpptYPSolOutVolt;
	}

	public void setMpptYPSolOutVolt(float mpptYPSolOutVolt) {
		this.mpptYPSolOutVolt = mpptYPSolOutVolt;
	}

	public float getMpptYPTemperature() {
		return mpptYPTemperature;
	}

	public void setMpptYPTemperature(float mpptYPTemperature) {
		this.mpptYPTemperature = mpptYPTemperature;
	}

	public int getMpptYPState() {
		return mpptYPState;
	}

	public void setMpptYPState(int mpptYPState) {
		this.mpptYPState = mpptYPState;
	}

	public float getMpptYMSolVolt() {
		return mpptYMSolVolt;
	}

	public void setMpptYMSolVolt(float mpptYMSolVolt) {
		this.mpptYMSolVolt = mpptYMSolVolt;
	}

	public float getMpptYMSolCurr() {
		return mpptYMSolCurr;
	}

	public void setMpptYMSolCurr(float mpptYMSolCurr) {
		this.mpptYMSolCurr = mpptYMSolCurr;
	}

	public float getMpptYMSolOutVolt() {
		return mpptYMSolOutVolt;
	}

	public void setMpptYMSolOutVolt(float mpptYMSolOutVolt) {
		this.mpptYMSolOutVolt = mpptYMSolOutVolt;
	}

	public float getMpptYMTemperature() {
		return mpptYMTemperature;
	}

	public void setMpptYMTemperature(float mpptYMTemperature) {
		this.mpptYMTemperature = mpptYMTemperature;
	}

	public float getMpptYMState() {
		return mpptYMState;
	}

	public void setMpptYMState(float mpptYMState) {
		this.mpptYMState = mpptYMState;
	}

	public float getDistrVolt3V3() {
		return distrVolt3V3;
	}

	public void setDistrVolt3V3(float distrVolt3V3) {
		this.distrVolt3V3 = distrVolt3V3;
	}

	public float getDistrCurr3V3() {
		return distrCurr3V3;
	}

	public void setDistrCurr3V3(float distrCurr3V3) {
		this.distrCurr3V3 = distrCurr3V3;
	}

	public float getDistrVolt5V() {
		return distrVolt5V;
	}

	public void setDistrVolt5V(float distrVolt5V) {
		this.distrVolt5V = distrVolt5V;
	}

	public float getDistrCurr5V() {
		return distrCurr5V;
	}

	public void setDistrCurr5V(float distrCurr5V) {
		this.distrCurr5V = distrCurr5V;
	}

	public float getDistrVoltVbat() {
		return distrVoltVbat;
	}

	public void setDistrVoltVbat(float distrVoltVbat) {
		this.distrVoltVbat = distrVoltVbat;
	}

	public float getDistrCurrVbat() {
		return distrCurrVbat;
	}

	public void setDistrCurrVbat(float distrCurrVbat) {
		this.distrCurrVbat = distrCurrVbat;
	}

	public int getDistrLCLState() {
		return distrLCLState;
	}

	public void setDistrLCLState(int distrLCLState) {
		this.distrLCLState = distrLCLState;
	}

	public int getDistrLCLFlags() {
		return distrLCLFlags;
	}

	public void setDistrLCLFlags(int distrLCLFlags) {
		this.distrLCLFlags = distrLCLFlags;
	}

	public float getBatcVoltA() {
		return batcVoltA;
	}

	public void setBatcVoltA(float batcVoltA) {
		this.batcVoltA = batcVoltA;
	}

	public float getBatcChrgCurr() {
		return batcChrgCurr;
	}

	public void setBatcChrgCurr(float batcChrgCurr) {
		this.batcChrgCurr = batcChrgCurr;
	}

	public float getBatcDchrgCurr() {
		return batcDchrgCurr;
	}

	public void setBatcDchrgCurr(float batcDchrgCurr) {
		this.batcDchrgCurr = batcDchrgCurr;
	}

	public float getBatcTemperature() {
		return batcTemperature;
	}

	public void setBatcTemperature(float batcTemperature) {
		this.batcTemperature = batcTemperature;
	}

	public int getBatcState() {
		return batcState;
	}

	public void setBatcState(int batcState) {
		this.batcState = batcState;
	}

	public float getBpTemperatureA() {
		return bpTemperatureA;
	}

	public void setBpTemperatureA(float bpTemperatureA) {
		this.bpTemperatureA = bpTemperatureA;
	}

	public float getBpTemperatureB() {
		return bpTemperatureB;
	}

	public void setBpTemperatureB(float bpTemperatureB) {
		this.bpTemperatureB = bpTemperatureB;
	}

	public int getSafetyCounterA() {
		return safetyCounterA;
	}

	public void setSafetyCounterA(int safetyCounterA) {
		this.safetyCounterA = safetyCounterA;
	}

	public int getPowerCycleCountA() {
		return powerCycleCountA;
	}

	public void setPowerCycleCountA(int powerCycleCountA) {
		this.powerCycleCountA = powerCycleCountA;
	}

	public long getUptimeA() {
		return uptimeA;
	}

	public void setUptimeA(long uptimeA) {
		this.uptimeA = uptimeA;
	}

	public float getTemperatureA() {
		return temperatureA;
	}

	public void setTemperatureA(float temperatureA) {
		this.temperatureA = temperatureA;
	}

	public float getSuppTempA() {
		return suppTempA;
	}

	public void setSuppTempA(float suppTempA) {
		this.suppTempA = suppTempA;
	}

	public float getControllerB3V3dA() {
		return controllerB3V3dA;
	}

	public void setControllerB3V3dA(float controllerB3V3dA) {
		this.controllerB3V3dA = controllerB3V3dA;
	}

	public float getDcdc3V3Temperature() {
		return dcdc3V3Temperature;
	}

	public void setDcdc3V3Temperature(float dcdc3v3Temperature) {
		dcdc3V3Temperature = dcdc3v3Temperature;
	}

	public float getDcdc5VTemperature() {
		return dcdc5VTemperature;
	}

	public void setDcdc5VTemperature(float dcdc5vTemperature) {
		dcdc5VTemperature = dcdc5vTemperature;
	}

	public float getBpTemperature() {
		return bpTemperature;
	}

	public void setBpTemperature(float bpTemperature) {
		this.bpTemperature = bpTemperature;
	}

	public float getBatcVoltB() {
		return batcVoltB;
	}

	public void setBatcVoltB(float batcVoltB) {
		this.batcVoltB = batcVoltB;
	}

	public int getSafetyCounterB() {
		return safetyCounterB;
	}

	public void setSafetyCounterB(int safetyCounterB) {
		this.safetyCounterB = safetyCounterB;
	}

	public int getPowerCycleCountB() {
		return powerCycleCountB;
	}

	public void setPowerCycleCountB(int powerCycleCountB) {
		this.powerCycleCountB = powerCycleCountB;
	}

	public long getUptimeB() {
		return uptimeB;
	}

	public void setUptimeB(long uptimeB) {
		this.uptimeB = uptimeB;
	}

	public float getTemperatureB() {
		return temperatureB;
	}

	public void setTemperatureB(float temperatureB) {
		this.temperatureB = temperatureB;
	}

	public float getSuppTempB() {
		return suppTempB;
	}

	public void setSuppTempB(float suppTempB) {
		this.suppTempB = suppTempB;
	}

	public float getControllerA3V3dB() {
		return controllerA3V3dB;
	}

	public void setControllerA3V3dB(float controllerA3V3dB) {
		this.controllerA3V3dB = controllerA3V3dB;
	}

	public float getMagnetometerMeasurement1() {
		return magnetometerMeasurement1;
	}

	public void setMagnetometerMeasurement1(float magnetometerMeasurement1) {
		this.magnetometerMeasurement1 = magnetometerMeasurement1;
	}

	public float getMagnetometerMeasurement2() {
		return magnetometerMeasurement2;
	}

	public void setMagnetometerMeasurement2(float magnetometerMeasurement2) {
		this.magnetometerMeasurement2 = magnetometerMeasurement2;
	}

	public float getMagnetometerMeasurement3() {
		return magnetometerMeasurement3;
	}

	public void setMagnetometerMeasurement3(float magnetometerMeasurement3) {
		this.magnetometerMeasurement3 = magnetometerMeasurement3;
	}

	public boolean isCoilsActiveDuringMeasurement() {
		return coilsActiveDuringMeasurement;
	}

	public void setCoilsActiveDuringMeasurement(boolean coilsActiveDuringMeasurement) {
		this.coilsActiveDuringMeasurement = coilsActiveDuringMeasurement;
	}

	public float getImtqDipole1() {
		return imtqDipole1;
	}

	public void setImtqDipole1(float imtqDipole1) {
		this.imtqDipole1 = imtqDipole1;
	}

	public float getImtqDipole2() {
		return imtqDipole2;
	}

	public void setImtqDipole2(float imtqDipole2) {
		this.imtqDipole2 = imtqDipole2;
	}

	public float getImtqDipole3() {
		return imtqDipole3;
	}

	public void setImtqDipole3(float imtqDipole3) {
		this.imtqDipole3 = imtqDipole3;
	}

	public float getImtqBDot1() {
		return imtqBDot1;
	}

	public void setImtqBDot1(float imtqBDot1) {
		this.imtqBDot1 = imtqBDot1;
	}

	public float getImtqBDot2() {
		return imtqBDot2;
	}

	public void setImtqBDot2(float imtqBDot2) {
		this.imtqBDot2 = imtqBDot2;
	}

	public float getImtqBDot3() {
		return imtqBDot3;
	}

	public void setImtqBDot3(float imtqBDot3) {
		this.imtqBDot3 = imtqBDot3;
	}

	public float getImtqDigitalVoltage() {
		return imtqDigitalVoltage;
	}

	public void setImtqDigitalVoltage(float imtqDigitalVoltage) {
		this.imtqDigitalVoltage = imtqDigitalVoltage;
	}

	public float getImtqAnalogVoltage() {
		return imtqAnalogVoltage;
	}

	public void setImtqAnalogVoltage(float imtqAnalogVoltage) {
		this.imtqAnalogVoltage = imtqAnalogVoltage;
	}

	public float getImtqDigitalCurrent() {
		return imtqDigitalCurrent;
	}

	public void setImtqDigitalCurrent(float imtqDigitalCurrent) {
		this.imtqDigitalCurrent = imtqDigitalCurrent;
	}

	public float getImtqAnalogCurrent() {
		return imtqAnalogCurrent;
	}

	public void setImtqAnalogCurrent(float imtqAnalogCurrent) {
		this.imtqAnalogCurrent = imtqAnalogCurrent;
	}

	public short getImtqMCUTemperature() {
		return imtqMCUTemperature;
	}

	public void setImtqMCUTemperature(short imtqMCUTemperature) {
		this.imtqMCUTemperature = imtqMCUTemperature;
	}

	public float getImtqCoilCurrent1() {
		return imtqCoilCurrent1;
	}

	public void setImtqCoilCurrent1(float imtqCoilCurrent1) {
		this.imtqCoilCurrent1 = imtqCoilCurrent1;
	}

	public float getImtqCoilCurrent2() {
		return imtqCoilCurrent2;
	}

	public void setImtqCoilCurrent2(float imtqCoilCurrent2) {
		this.imtqCoilCurrent2 = imtqCoilCurrent2;
	}

	public float getImtqCoilCurrent3() {
		return imtqCoilCurrent3;
	}

	public void setImtqCoilCurrent3(float imtqCoilCurrent3) {
		this.imtqCoilCurrent3 = imtqCoilCurrent3;
	}

	public short getImtqCoilTemperature1() {
		return imtqCoilTemperature1;
	}

	public void setImtqCoilTemperature1(short imtqCoilTemperature1) {
		this.imtqCoilTemperature1 = imtqCoilTemperature1;
	}

	public short getImtqCoilTemperature2() {
		return imtqCoilTemperature2;
	}

	public void setImtqCoilTemperature2(short imtqCoilTemperature2) {
		this.imtqCoilTemperature2 = imtqCoilTemperature2;
	}

	public short getImtqCoilTemperature3() {
		return imtqCoilTemperature3;
	}

	public void setImtqCoilTemperature3(short imtqCoilTemperature3) {
		this.imtqCoilTemperature3 = imtqCoilTemperature3;
	}

	public int getImtqStatus() {
		return imtqStatus;
	}

	public void setImtqStatus(int imtqStatus) {
		this.imtqStatus = imtqStatus;
	}

	public int getImtqMode() {
		return imtqMode;
	}

	public void setImtqMode(int imtqMode) {
		this.imtqMode = imtqMode;
	}

	public boolean isImtqErrorDuringPreviousIteration() {
		return imtqErrorDuringPreviousIteration;
	}

	public void setImtqErrorDuringPreviousIteration(boolean imtqErrorDuringPreviousIteration) {
		this.imtqErrorDuringPreviousIteration = imtqErrorDuringPreviousIteration;
	}

	public boolean isImtqConfigurationChanged() {
		return imtqConfigurationChanged;
	}

	public void setImtqConfigurationChanged(boolean imtqConfigurationChanged) {
		this.imtqConfigurationChanged = imtqConfigurationChanged;
	}

	public long getImtqUptime() {
		return imtqUptime;
	}

	public void setImtqUptime(long imtqUptime) {
		this.imtqUptime = imtqUptime;
	}

	public int getImtqError1() {
		return imtqError1;
	}

	public void setImtqError1(int imtqError1) {
		this.imtqError1 = imtqError1;
	}

	public int getImtqError2() {
		return imtqError2;
	}

	public void setImtqError2(int imtqError2) {
		this.imtqError2 = imtqError2;
	}

	public int getImtqError3() {
		return imtqError3;
	}

	public void setImtqError3(int imtqError3) {
		this.imtqError3 = imtqError3;
	}

	public int getImtqError4() {
		return imtqError4;
	}

	public void setImtqError4(int imtqError4) {
		this.imtqError4 = imtqError4;
	}

	public int getImtqError5() {
		return imtqError5;
	}

	public void setImtqError5(int imtqError5) {
		this.imtqError5 = imtqError5;
	}

	public int getImtqError6() {
		return imtqError6;
	}

	public void setImtqError6(int imtqError6) {
		this.imtqError6 = imtqError6;
	}

	public int getImtqError7() {
		return imtqError7;
	}

	public void setImtqError7(int imtqError7) {
		this.imtqError7 = imtqError7;
	}

	public int getImtqError8() {
		return imtqError8;
	}

	public void setImtqError8(int imtqError8) {
		this.imtqError8 = imtqError8;
	}

}
