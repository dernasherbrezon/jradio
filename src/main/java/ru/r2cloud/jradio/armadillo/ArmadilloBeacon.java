package ru.r2cloud.jradio.armadillo;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import ru.r2cloud.jradio.ax25.Ax25Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class ArmadilloBeacon extends Ax25Beacon {

	private long timeOfDay;
	private long uptime;
	private long availableNvMemory;

	private float positionX;
	private float positionY;
	private float positionZ;

	private float velocityX;
	private float velocityY;
	private float velocityZ;

	private SpacecraftMode mode;
	private boolean pddPowerState;
	private boolean antennaPowerState;
	private boolean adcPowerState;
	private boolean gpsPowerState;

	private int batteryVoltage;
	private int inputCurrent;
	private int outputCurrent;

	private long bootCount;
	private BootCause bootCause;

	private short epsTemperature1;
	private short epsTemperature2;
	private short epsTemperature3;
	private short epsTemperature4;
	private short epsTemperatureBp4a;
	private short epsTemperatureBp4b;

	private int epsOutput1Current;
	private int epsOutput2Current;
	private int epsOutput3Current;
	private int epsOutput4Current;
	private int epsOutput5Current;
	private int epsOutput6Current;

	private float reactionWheelXTemperature;
	private float reactionWheelYTemperature;
	private float reactionWheelZTemperature;

	private float gyroXTemperature;
	private float gyroYTemperature;
	private float gyroZTemperature;

	private float desiredQuaternionA;
	private float desiredQuaternionB;
	private float desiredQuaternionC;
	private float desiredQuaternionD;
	private float estimatedQuaternionA;
	private float estimatedQuaternionB;
	private float estimatedQuaternionC;
	private float estimatedQuaternionD;

	private float rotationRateX;
	private float rotationRateY;
	private float rotationRateZ;
	private int sunSensorAddress;

	private String message;

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		LittleEndianDataInputStream ldis = new LittleEndianDataInputStream(dis);
		ldis.skipBytes(5);
		timeOfDay = ldis.readUnsignedInt();
		uptime = ldis.readUnsignedInt();
		availableNvMemory = ldis.readUnsignedInt();

		positionX = ldis.readFloat();
		positionY = ldis.readFloat();
		positionZ = ldis.readFloat();

		velocityX = ldis.readFloat();
		velocityY = ldis.readFloat();
		velocityZ = ldis.readFloat();

		int raw = ldis.readUnsignedByte();
		mode = SpacecraftMode.valueOfCode(raw & 0x1);
		pddPowerState = ((raw >> 1) & 0x1) > 0;
		antennaPowerState = ((raw >> 2) & 0x1) > 0;
		adcPowerState = ((raw >> 3) & 0x1) > 0;
		gpsPowerState = ((raw >> 4) & 0x1) > 0;

		batteryVoltage = ldis.readUnsignedShort();
		inputCurrent = ldis.readUnsignedShort();
		outputCurrent = ldis.readUnsignedShort();

		bootCount = ldis.readUnsignedInt();
		bootCause = BootCause.valueOfCode(ldis.readUnsignedByte());

		epsTemperature1 = ldis.readShort();
		epsTemperature2 = ldis.readShort();
		epsTemperature3 = ldis.readShort();
		epsTemperature4 = ldis.readShort();
		epsTemperatureBp4a = ldis.readShort();
		epsTemperatureBp4b = ldis.readShort();

		epsOutput1Current = ldis.readUnsignedShort();
		epsOutput2Current = ldis.readUnsignedShort();
		epsOutput3Current = ldis.readUnsignedShort();
		epsOutput4Current = ldis.readUnsignedShort();
		epsOutput5Current = ldis.readUnsignedShort();
		epsOutput6Current = ldis.readUnsignedShort();

		reactionWheelXTemperature = ldis.readFloat();
		reactionWheelYTemperature = ldis.readFloat();
		reactionWheelZTemperature = ldis.readFloat();

		gyroXTemperature = ldis.readFloat();
		gyroYTemperature = ldis.readFloat();
		gyroZTemperature = ldis.readFloat();

		desiredQuaternionA = ldis.readFloat();
		desiredQuaternionB = ldis.readFloat();
		desiredQuaternionC = ldis.readFloat();
		desiredQuaternionD = ldis.readFloat();
		estimatedQuaternionA = ldis.readFloat();
		estimatedQuaternionB = ldis.readFloat();
		estimatedQuaternionC = ldis.readFloat();
		estimatedQuaternionD = ldis.readFloat();

		rotationRateX = ldis.readFloat();
		rotationRateY = ldis.readFloat();
		rotationRateZ = ldis.readFloat();

		sunSensorAddress = ldis.readUnsignedByte();

		byte[] dataMessage = new byte[ldis.available()];
		ldis.readFully(dataMessage);
		message = new String(dataMessage, StandardCharsets.US_ASCII).trim();
	}

	public long getTimeOfDay() {
		return timeOfDay;
	}

	public void setTimeOfDay(long timeOfDay) {
		this.timeOfDay = timeOfDay;
	}

	public long getUptime() {
		return uptime;
	}

	public void setUptime(long uptime) {
		this.uptime = uptime;
	}

	public long getAvailableNvMemory() {
		return availableNvMemory;
	}

	public void setAvailableNvMemory(long availableNvMemory) {
		this.availableNvMemory = availableNvMemory;
	}

	public float getPositionX() {
		return positionX;
	}

	public void setPositionX(float positionX) {
		this.positionX = positionX;
	}

	public float getPositionY() {
		return positionY;
	}

	public void setPositionY(float positionY) {
		this.positionY = positionY;
	}

	public float getPositionZ() {
		return positionZ;
	}

	public void setPositionZ(float positionZ) {
		this.positionZ = positionZ;
	}

	public float getVelocityX() {
		return velocityX;
	}

	public void setVelocityX(float velocityX) {
		this.velocityX = velocityX;
	}

	public float getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(float velocityY) {
		this.velocityY = velocityY;
	}

	public float getVelocityZ() {
		return velocityZ;
	}

	public void setVelocityZ(float velocityZ) {
		this.velocityZ = velocityZ;
	}

	public SpacecraftMode getMode() {
		return mode;
	}

	public void setMode(SpacecraftMode mode) {
		this.mode = mode;
	}

	public boolean isPddPowerState() {
		return pddPowerState;
	}

	public void setPddPowerState(boolean pddPowerState) {
		this.pddPowerState = pddPowerState;
	}

	public boolean isAntennaPowerState() {
		return antennaPowerState;
	}

	public void setAntennaPowerState(boolean antennaPowerState) {
		this.antennaPowerState = antennaPowerState;
	}

	public boolean isAdcPowerState() {
		return adcPowerState;
	}

	public void setAdcPowerState(boolean adcPowerState) {
		this.adcPowerState = adcPowerState;
	}

	public boolean isGpsPowerState() {
		return gpsPowerState;
	}

	public void setGpsPowerState(boolean gpsPowerState) {
		this.gpsPowerState = gpsPowerState;
	}

	public int getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(int batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public int getInputCurrent() {
		return inputCurrent;
	}

	public void setInputCurrent(int inputCurrent) {
		this.inputCurrent = inputCurrent;
	}

	public int getOutputCurrent() {
		return outputCurrent;
	}

	public void setOutputCurrent(int outputCurrent) {
		this.outputCurrent = outputCurrent;
	}

	public long getBootCount() {
		return bootCount;
	}

	public void setBootCount(long bootCount) {
		this.bootCount = bootCount;
	}

	public BootCause getBootCause() {
		return bootCause;
	}

	public void setBootCause(BootCause bootCause) {
		this.bootCause = bootCause;
	}

	public short getEpsTemperature1() {
		return epsTemperature1;
	}

	public void setEpsTemperature1(short epsTemperature1) {
		this.epsTemperature1 = epsTemperature1;
	}

	public short getEpsTemperature2() {
		return epsTemperature2;
	}

	public void setEpsTemperature2(short epsTemperature2) {
		this.epsTemperature2 = epsTemperature2;
	}

	public short getEpsTemperature3() {
		return epsTemperature3;
	}

	public void setEpsTemperature3(short epsTemperature3) {
		this.epsTemperature3 = epsTemperature3;
	}

	public short getEpsTemperature4() {
		return epsTemperature4;
	}

	public void setEpsTemperature4(short epsTemperature4) {
		this.epsTemperature4 = epsTemperature4;
	}

	public short getEpsTemperatureBp4a() {
		return epsTemperatureBp4a;
	}

	public void setEpsTemperatureBp4a(short epsTemperatureBp4a) {
		this.epsTemperatureBp4a = epsTemperatureBp4a;
	}

	public short getEpsTemperatureBp4b() {
		return epsTemperatureBp4b;
	}

	public void setEpsTemperatureBp4b(short epsTemperatureBp4b) {
		this.epsTemperatureBp4b = epsTemperatureBp4b;
	}

	public int getEpsOutput1Current() {
		return epsOutput1Current;
	}

	public void setEpsOutput1Current(int epsOutput1Current) {
		this.epsOutput1Current = epsOutput1Current;
	}

	public int getEpsOutput2Current() {
		return epsOutput2Current;
	}

	public void setEpsOutput2Current(int epsOutput2Current) {
		this.epsOutput2Current = epsOutput2Current;
	}

	public int getEpsOutput3Current() {
		return epsOutput3Current;
	}

	public void setEpsOutput3Current(int epsOutput3Current) {
		this.epsOutput3Current = epsOutput3Current;
	}

	public int getEpsOutput4Current() {
		return epsOutput4Current;
	}

	public void setEpsOutput4Current(int epsOutput4Current) {
		this.epsOutput4Current = epsOutput4Current;
	}

	public int getEpsOutput5Current() {
		return epsOutput5Current;
	}

	public void setEpsOutput5Current(int epsOutput5Current) {
		this.epsOutput5Current = epsOutput5Current;
	}

	public int getEpsOutput6Current() {
		return epsOutput6Current;
	}

	public void setEpsOutput6Current(int epsOutput6Current) {
		this.epsOutput6Current = epsOutput6Current;
	}

	public float getReactionWheelXTemperature() {
		return reactionWheelXTemperature;
	}

	public void setReactionWheelXTemperature(float reactionWheelXTemperature) {
		this.reactionWheelXTemperature = reactionWheelXTemperature;
	}

	public float getReactionWheelYTemperature() {
		return reactionWheelYTemperature;
	}

	public void setReactionWheelYTemperature(float reactionWheelYTemperature) {
		this.reactionWheelYTemperature = reactionWheelYTemperature;
	}

	public float getReactionWheelZTemperature() {
		return reactionWheelZTemperature;
	}

	public void setReactionWheelZTemperature(float reactionWheelZTemperature) {
		this.reactionWheelZTemperature = reactionWheelZTemperature;
	}

	public float getGyroXTemperature() {
		return gyroXTemperature;
	}

	public void setGyroXTemperature(float gyroXTemperature) {
		this.gyroXTemperature = gyroXTemperature;
	}

	public float getGyroYTemperature() {
		return gyroYTemperature;
	}

	public void setGyroYTemperature(float gyroYTemperature) {
		this.gyroYTemperature = gyroYTemperature;
	}

	public float getGyroZTemperature() {
		return gyroZTemperature;
	}

	public void setGyroZTemperature(float gyroZTemperature) {
		this.gyroZTemperature = gyroZTemperature;
	}

	public float getDesiredQuaternionA() {
		return desiredQuaternionA;
	}

	public void setDesiredQuaternionA(float desiredQuaternionA) {
		this.desiredQuaternionA = desiredQuaternionA;
	}

	public float getDesiredQuaternionB() {
		return desiredQuaternionB;
	}

	public void setDesiredQuaternionB(float desiredQuaternionB) {
		this.desiredQuaternionB = desiredQuaternionB;
	}

	public float getDesiredQuaternionC() {
		return desiredQuaternionC;
	}

	public void setDesiredQuaternionC(float desiredQuaternionC) {
		this.desiredQuaternionC = desiredQuaternionC;
	}

	public float getDesiredQuaternionD() {
		return desiredQuaternionD;
	}

	public void setDesiredQuaternionD(float desiredQuaternionD) {
		this.desiredQuaternionD = desiredQuaternionD;
	}

	public float getEstimatedQuaternionA() {
		return estimatedQuaternionA;
	}

	public void setEstimatedQuaternionA(float estimatedQuaternionA) {
		this.estimatedQuaternionA = estimatedQuaternionA;
	}

	public float getEstimatedQuaternionB() {
		return estimatedQuaternionB;
	}

	public void setEstimatedQuaternionB(float estimatedQuaternionB) {
		this.estimatedQuaternionB = estimatedQuaternionB;
	}

	public float getEstimatedQuaternionC() {
		return estimatedQuaternionC;
	}

	public void setEstimatedQuaternionC(float estimatedQuaternionC) {
		this.estimatedQuaternionC = estimatedQuaternionC;
	}

	public float getEstimatedQuaternionD() {
		return estimatedQuaternionD;
	}

	public void setEstimatedQuaternionD(float estimatedQuaternionD) {
		this.estimatedQuaternionD = estimatedQuaternionD;
	}

	public float getRotationRateX() {
		return rotationRateX;
	}

	public void setRotationRateX(float rotationRateX) {
		this.rotationRateX = rotationRateX;
	}

	public float getRotationRateY() {
		return rotationRateY;
	}

	public void setRotationRateY(float rotationRateY) {
		this.rotationRateY = rotationRateY;
	}

	public float getRotationRateZ() {
		return rotationRateZ;
	}

	public void setRotationRateZ(float rotationRateZ) {
		this.rotationRateZ = rotationRateZ;
	}

	public int getSunSensorAddress() {
		return sunSensorAddress;
	}

	public void setSunSensorAddress(int sunSensorAddress) {
		this.sunSensorAddress = sunSensorAddress;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
