package ru.r2cloud.jradio.siriussat;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class ExtendedBeacon {

	private short carrierBoardTemperature;
	private float magneticFieldX;
	private float magneticFieldY;
	private float magneticFieldZ;
	private float angularVelocityX;
	private float angularVelocityY;
	private float angularVelocityZ;
	private int resetCounter;
	private int lastResetReason;
	private int flags;
	private int lastTelemetryTimestamp;
	private int payloadFlags;
	private int lastPayloadTelemetryTimestamp;
	private short payloadBoardTemperature;
	private short payloadMpuTemperature;
	private int payloadCurrentSensor1;
	private int payloadCurrentSensor2;
	private int payloadResetCounter;
	private int payloadLastResetTimestamp;
	private int channel1CountRate;
	private int channel2CountRate;
	private int channel3CountRate;
	private int channel4CountRate;
	private int channel5CountRate;
	private int channel6CountRate;
	private int lastTransmittedBufferSegment1Pointer;
	private int writePointerSegment1;
	private int lastTransmittedBufferSegment2Pointer;
	private int writePointerSegment2;
	private int lastTransmittedBufferSegment3Pointer;
	private int writePointerSegment3;
	private int lastEventChannel1M1l1;
	private int lastEventChannel1L2h1;
	private int lastEventChannel1H2m2;
	private int lastEventChannel2M1l1;
	private int lastEventChannel2L2h1;
	private int lastEventChannel2H2m2;

	public ExtendedBeacon() {
		// do nothing
	}

	public ExtendedBeacon(LittleEndianDataInputStream dis) throws IOException {
		carrierBoardTemperature = dis.readShort();
		magneticFieldX = dis.readFloat();
		magneticFieldY = dis.readFloat();
		magneticFieldZ = dis.readFloat();
		angularVelocityX = dis.readFloat();
		angularVelocityY = dis.readFloat();
		angularVelocityZ = dis.readFloat();
		resetCounter = dis.readUnsignedByte();
		lastResetReason = dis.readUnsignedByte();
		flags = dis.readUnsignedByte();
		lastTelemetryTimestamp = dis.readInt();
		payloadFlags = dis.readUnsignedByte();
		lastPayloadTelemetryTimestamp = dis.readInt();
		payloadBoardTemperature = dis.readShort();
		payloadMpuTemperature = dis.readShort();
		payloadCurrentSensor1 = dis.readUnsignedShort();
		payloadCurrentSensor2 = dis.readUnsignedShort();
		payloadResetCounter = dis.readUnsignedByte();
		payloadLastResetTimestamp = dis.readInt();
		channel1CountRate = dis.readUnsignedShort();
		channel2CountRate = dis.readUnsignedShort();
		channel3CountRate = dis.readUnsignedShort();
		channel4CountRate = dis.readUnsignedShort();
		channel5CountRate = dis.readUnsignedShort();
		channel6CountRate = dis.readUnsignedShort();
		lastTransmittedBufferSegment1Pointer = dis.readUnsignedShort();
		writePointerSegment1 = dis.readUnsignedShort();
		lastTransmittedBufferSegment2Pointer = dis.readUnsignedShort();
		writePointerSegment2 = dis.readUnsignedShort();
		lastTransmittedBufferSegment3Pointer = dis.readUnsignedShort();
		writePointerSegment3 = dis.readUnsignedShort();

		lastEventChannel1M1l1 = dis.readUnsignedByte();
		lastEventChannel1L2h1 = dis.readUnsignedByte();
		lastEventChannel1H2m2 = dis.readUnsignedByte();

		lastEventChannel2M1l1 = dis.readUnsignedByte();
		lastEventChannel2L2h1 = dis.readUnsignedByte();
		lastEventChannel2H2m2 = dis.readUnsignedByte();
	}

	public short getCarrierBoardTemperature() {
		return carrierBoardTemperature;
	}

	public void setCarrierBoardTemperature(short carrierBoardTemperature) {
		this.carrierBoardTemperature = carrierBoardTemperature;
	}

	public float getMagneticFieldX() {
		return magneticFieldX;
	}

	public void setMagneticFieldX(float magneticFieldX) {
		this.magneticFieldX = magneticFieldX;
	}

	public float getMagneticFieldY() {
		return magneticFieldY;
	}

	public void setMagneticFieldY(float magneticFieldY) {
		this.magneticFieldY = magneticFieldY;
	}

	public float getMagneticFieldZ() {
		return magneticFieldZ;
	}

	public void setMagneticFieldZ(float magneticFieldZ) {
		this.magneticFieldZ = magneticFieldZ;
	}

	public float getAngularVelocityX() {
		return angularVelocityX;
	}

	public void setAngularVelocityX(float angularVelocityX) {
		this.angularVelocityX = angularVelocityX;
	}

	public float getAngularVelocityY() {
		return angularVelocityY;
	}

	public void setAngularVelocityY(float angularVelocityY) {
		this.angularVelocityY = angularVelocityY;
	}

	public float getAngularVelocityZ() {
		return angularVelocityZ;
	}

	public void setAngularVelocityZ(float angularVelocityZ) {
		this.angularVelocityZ = angularVelocityZ;
	}

	public int getResetCounter() {
		return resetCounter;
	}

	public void setResetCounter(int resetCounter) {
		this.resetCounter = resetCounter;
	}

	public int getLastResetReason() {
		return lastResetReason;
	}

	public void setLastResetReason(int lastResetReason) {
		this.lastResetReason = lastResetReason;
	}

	public int getFlags() {
		return flags;
	}

	public void setFlags(int flags) {
		this.flags = flags;
	}

	public int getLastTelemetryTimestamp() {
		return lastTelemetryTimestamp;
	}

	public void setLastTelemetryTimestamp(int lastTelemetryTimestamp) {
		this.lastTelemetryTimestamp = lastTelemetryTimestamp;
	}

	public int getPayloadFlags() {
		return payloadFlags;
	}

	public void setPayloadFlags(int payloadFlags) {
		this.payloadFlags = payloadFlags;
	}

	public int getLastPayloadTelemetryTimestamp() {
		return lastPayloadTelemetryTimestamp;
	}

	public void setLastPayloadTelemetryTimestamp(int lastPayloadTelemetryTimestamp) {
		this.lastPayloadTelemetryTimestamp = lastPayloadTelemetryTimestamp;
	}

	public short getPayloadBoardTemperature() {
		return payloadBoardTemperature;
	}

	public void setPayloadBoardTemperature(short payloadBoardTemperature) {
		this.payloadBoardTemperature = payloadBoardTemperature;
	}

	public short getPayloadMpuTemperature() {
		return payloadMpuTemperature;
	}

	public void setPayloadMpuTemperature(short payloadMpuTemperature) {
		this.payloadMpuTemperature = payloadMpuTemperature;
	}

	public int getPayloadCurrentSensor1() {
		return payloadCurrentSensor1;
	}

	public void setPayloadCurrentSensor1(int payloadCurrentSensor1) {
		this.payloadCurrentSensor1 = payloadCurrentSensor1;
	}

	public int getPayloadCurrentSensor2() {
		return payloadCurrentSensor2;
	}

	public void setPayloadCurrentSensor2(int payloadCurrentSensor2) {
		this.payloadCurrentSensor2 = payloadCurrentSensor2;
	}

	public int getPayloadResetCounter() {
		return payloadResetCounter;
	}

	public void setPayloadResetCounter(int payloadResetCounter) {
		this.payloadResetCounter = payloadResetCounter;
	}

	public int getPayloadLastResetTimestamp() {
		return payloadLastResetTimestamp;
	}

	public void setPayloadLastResetTimestamp(int payloadLastResetTimestamp) {
		this.payloadLastResetTimestamp = payloadLastResetTimestamp;
	}

	public int getChannel1CountRate() {
		return channel1CountRate;
	}

	public void setChannel1CountRate(int channel1CountRate) {
		this.channel1CountRate = channel1CountRate;
	}

	public int getChannel2CountRate() {
		return channel2CountRate;
	}

	public void setChannel2CountRate(int channel2CountRate) {
		this.channel2CountRate = channel2CountRate;
	}

	public int getChannel3CountRate() {
		return channel3CountRate;
	}

	public void setChannel3CountRate(int channel3CountRate) {
		this.channel3CountRate = channel3CountRate;
	}

	public int getChannel4CountRate() {
		return channel4CountRate;
	}

	public void setChannel4CountRate(int channel4CountRate) {
		this.channel4CountRate = channel4CountRate;
	}

	public int getChannel5CountRate() {
		return channel5CountRate;
	}

	public void setChannel5CountRate(int channel5CountRate) {
		this.channel5CountRate = channel5CountRate;
	}

	public int getChannel6CountRate() {
		return channel6CountRate;
	}

	public void setChannel6CountRate(int channel6CountRate) {
		this.channel6CountRate = channel6CountRate;
	}

	public int getLastTransmittedBufferSegment1Pointer() {
		return lastTransmittedBufferSegment1Pointer;
	}

	public void setLastTransmittedBufferSegment1Pointer(int lastTransmittedBufferSegment1Pointer) {
		this.lastTransmittedBufferSegment1Pointer = lastTransmittedBufferSegment1Pointer;
	}

	public int getWritePointerSegment1() {
		return writePointerSegment1;
	}

	public void setWritePointerSegment1(int writePointerSegment1) {
		this.writePointerSegment1 = writePointerSegment1;
	}

	public int getLastTransmittedBufferSegment2Pointer() {
		return lastTransmittedBufferSegment2Pointer;
	}

	public void setLastTransmittedBufferSegment2Pointer(int lastTransmittedBufferSegment2Pointer) {
		this.lastTransmittedBufferSegment2Pointer = lastTransmittedBufferSegment2Pointer;
	}

	public int getWritePointerSegment2() {
		return writePointerSegment2;
	}

	public void setWritePointerSegment2(int writePointerSegment2) {
		this.writePointerSegment2 = writePointerSegment2;
	}

	public int getLastTransmittedBufferSegment3Pointer() {
		return lastTransmittedBufferSegment3Pointer;
	}

	public void setLastTransmittedBufferSegment3Pointer(int lastTransmittedBufferSegment3Pointer) {
		this.lastTransmittedBufferSegment3Pointer = lastTransmittedBufferSegment3Pointer;
	}

	public int getWritePointerSegment3() {
		return writePointerSegment3;
	}

	public void setWritePointerSegment3(int writePointerSegment3) {
		this.writePointerSegment3 = writePointerSegment3;
	}

	public int getLastEventChannel1M1l1() {
		return lastEventChannel1M1l1;
	}

	public void setLastEventChannel1M1l1(int lastEventChannel1M1l1) {
		this.lastEventChannel1M1l1 = lastEventChannel1M1l1;
	}

	public int getLastEventChannel1L2h1() {
		return lastEventChannel1L2h1;
	}

	public void setLastEventChannel1L2h1(int lastEventChannel1L2h1) {
		this.lastEventChannel1L2h1 = lastEventChannel1L2h1;
	}

	public int getLastEventChannel1H2m2() {
		return lastEventChannel1H2m2;
	}

	public void setLastEventChannel1H2m2(int lastEventChannel1H2m2) {
		this.lastEventChannel1H2m2 = lastEventChannel1H2m2;
	}

	public int getLastEventChannel2M1l1() {
		return lastEventChannel2M1l1;
	}

	public void setLastEventChannel2M1l1(int lastEventChannel2M1l1) {
		this.lastEventChannel2M1l1 = lastEventChannel2M1l1;
	}

	public int getLastEventChannel2L2h1() {
		return lastEventChannel2L2h1;
	}

	public void setLastEventChannel2L2h1(int lastEventChannel2L2h1) {
		this.lastEventChannel2L2h1 = lastEventChannel2L2h1;
	}

	public int getLastEventChannel2H2m2() {
		return lastEventChannel2H2m2;
	}

	public void setLastEventChannel2H2m2(int lastEventChannel2H2m2) {
		this.lastEventChannel2H2m2 = lastEventChannel2H2m2;
	}

}
