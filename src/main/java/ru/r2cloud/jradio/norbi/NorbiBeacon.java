package ru.r2cloud.jradio.norbi;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import ru.r2cloud.jradio.ax25.Ax25Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class NorbiBeacon extends Ax25Beacon {

	private int length;
	private long receiverAddress;
	private long transmitterAddress;
	private int transactionNumber;
	private short msgTypeId;
	private int frameDefinition;
	private int frameNumber;
	private long frameGenerationTime;
	private String brkTitle;
	private int brkNumberActive;
	private long brkRestartsCountActive;
	private int brkCurrentModeId;
	private byte brkTransmitterPowerActive;
	private byte brkTempActive;
	private int brkModeStateActive;
	private int brkVoltageOffsetAmplifierActive;
	private byte brkLastReceivedPacketRssiActive;
	private byte brkLastReceivedPacketSnrActive;
	private int brkArchiveRecordPointer;
	private byte brkLastReceivedPackedSnrInactive;
	private int msModuleState;
	private int msPayloadState;
	private byte msTemp;
	private int msPayloadPowerState;
	private int sopAltitudeGlonass;
	private int sopLatitudeGlonass;
	private int sopLongitudeGlonass;
	private long sopDateTimeGlonass;
	private int sopMagneticInductionModule;
	private int sopAngularVelocityVectorX;
	private int sopAngularVelocityVectorY;
	private int sopAngularVelocityVectorZ;
	private int sopAnglePriority1;
	private int sopAnglePriority2;
	private byte sopMedianTemperature1;
	private byte sopMedianTemperature6;
	private byte sopBoardTemp;
	private int sopState;
	private int sopStateDsg1;
	private int sopStateDsg2;
	private int sopStateDsg3;
	private int sopOrientationNumber;
	private byte sesMedianPanelXTempPositive;
	private byte sesMedianPanelXTempNegative;
	private long sesSolarPanelsState;
	private int sesChargeLevel;
	private int sesBatteryState;
	private int sesChargingKeysState;
	private int sesPowerLineState;
	private short sesTotalChargingPower;
	private int sesTotalGeneratedPower;
	private int sesTotalPowerLoad;
	private byte sesMedianPmmTemp;
	private byte sesMedianPamTemp;
	private byte sesMedianPdmTemp;
	private long sesModuleState;

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		LittleEndianDataInputStream ldis = new LittleEndianDataInputStream(dis);
		length = ldis.readUnsignedByte();
		receiverAddress = ldis.readUnsignedInt();
		transmitterAddress = ldis.readUnsignedInt();
		transactionNumber = ldis.readUnsignedShort();
		ldis.skipBytes(2);
		msgTypeId = ldis.readShort();
		ldis.skipBytes(2);
		frameDefinition = ldis.readUnsignedShort();
		frameNumber = ldis.readUnsignedShort();
		frameGenerationTime = ldis.readUnsignedInt();
		byte[] brkTitleBytes = new byte[24];
		ldis.readFully(brkTitleBytes);
		brkTitle = new String(brkTitleBytes, Charset.forName("windows-1251")).trim();

		brkNumberActive = ldis.readUnsignedByte();
		brkRestartsCountActive = ldis.readUnsignedInt();
		brkCurrentModeId = ldis.readUnsignedByte();
		brkTransmitterPowerActive = ldis.readByte();
		brkTempActive = ldis.readByte();
		brkModeStateActive = ldis.readUnsignedShort();
		brkVoltageOffsetAmplifierActive = ldis.readUnsignedShort();
		brkLastReceivedPacketRssiActive = ldis.readByte();
		brkLastReceivedPacketSnrActive = ldis.readByte();
		brkArchiveRecordPointer = ldis.readUnsignedShort();
		brkLastReceivedPackedSnrInactive = ldis.readByte();
		msModuleState = ldis.readUnsignedShort();
		msPayloadState = ldis.readUnsignedShort();
		msTemp = ldis.readByte();
		msPayloadPowerState = ldis.readUnsignedByte();
		sopAltitudeGlonass = ldis.readInt();
		sopLatitudeGlonass = ldis.readInt();
		sopLongitudeGlonass = ldis.readInt();
		sopDateTimeGlonass = ldis.readUnsignedInt();
		sopMagneticInductionModule = ldis.readUnsignedShort();
		sopAngularVelocityVectorX = ldis.readUnsignedShort();
		sopAngularVelocityVectorY = ldis.readUnsignedShort();
		sopAngularVelocityVectorZ = ldis.readUnsignedShort();
		sopAnglePriority1 = ldis.readUnsignedShort();
		sopAnglePriority2 = ldis.readUnsignedShort();
		sopMedianTemperature1 = ldis.readByte();
		sopMedianTemperature6 = ldis.readByte();
		sopBoardTemp = ldis.readByte();
		sopState = ldis.readUnsignedShort();
		sopStateDsg1 = ldis.readUnsignedShort();
		sopStateDsg2 = ldis.readUnsignedShort();
		sopStateDsg3 = ldis.readUnsignedShort();
		sopOrientationNumber = ldis.readUnsignedByte();
		sesMedianPanelXTempPositive = ldis.readByte();
		sesMedianPanelXTempNegative = ldis.readByte();
		sesSolarPanelsState = ldis.readUnsigned5Bytes();
		sesChargeLevel = ldis.readUnsignedShort();
		sesBatteryState = ldis.readUnsigned3Bytes();
		sesChargingKeysState = ldis.readUnsignedShort();
		sesPowerLineState = ldis.readUnsignedByte();
		sesTotalChargingPower = ldis.readShort();
		sesTotalGeneratedPower = ldis.readUnsignedShort();
		sesTotalPowerLoad = ldis.readUnsignedShort();
		sesMedianPmmTemp = ldis.readByte();
		sesMedianPamTemp = ldis.readByte();
		sesMedianPdmTemp = ldis.readByte();
		sesModuleState = ldis.readUnsigned5Bytes();
		// crc16 should be calculated on top level
		ldis.skipBytes(2);
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public long getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(long receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public long getTransmitterAddress() {
		return transmitterAddress;
	}

	public void setTransmitterAddress(long transmitterAddress) {
		this.transmitterAddress = transmitterAddress;
	}

	public int getTransactionNumber() {
		return transactionNumber;
	}

	public void setTransactionNumber(int transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

	public short getMsgTypeId() {
		return msgTypeId;
	}

	public void setMsgTypeId(short msgTypeId) {
		this.msgTypeId = msgTypeId;
	}

	public int getFrameDefinition() {
		return frameDefinition;
	}

	public void setFrameDefinition(int frameDefinition) {
		this.frameDefinition = frameDefinition;
	}

	public int getFrameNumber() {
		return frameNumber;
	}

	public void setFrameNumber(int frameNumber) {
		this.frameNumber = frameNumber;
	}

	public long getFrameGenerationTime() {
		return frameGenerationTime;
	}

	public void setFrameGenerationTime(long frameGenerationTime) {
		this.frameGenerationTime = frameGenerationTime;
	}

	public String getBrkTitle() {
		return brkTitle;
	}

	public void setBrkTitle(String brkTitle) {
		this.brkTitle = brkTitle;
	}

	public int getBrkNumberActive() {
		return brkNumberActive;
	}

	public void setBrkNumberActive(int brkNumberActive) {
		this.brkNumberActive = brkNumberActive;
	}

	public long getBrkRestartsCountActive() {
		return brkRestartsCountActive;
	}

	public void setBrkRestartsCountActive(long brkRestartsCountActive) {
		this.brkRestartsCountActive = brkRestartsCountActive;
	}

	public int getBrkCurrentModeId() {
		return brkCurrentModeId;
	}

	public void setBrkCurrentModeId(int brkCurrentModeId) {
		this.brkCurrentModeId = brkCurrentModeId;
	}

	public byte getBrkTransmitterPowerActive() {
		return brkTransmitterPowerActive;
	}

	public void setBrkTransmitterPowerActive(byte brkTransmitterPowerActive) {
		this.brkTransmitterPowerActive = brkTransmitterPowerActive;
	}

	public byte getBrkTempActive() {
		return brkTempActive;
	}

	public void setBrkTempActive(byte brkTempActive) {
		this.brkTempActive = brkTempActive;
	}

	public int getBrkModeStateActive() {
		return brkModeStateActive;
	}

	public void setBrkModeStateActive(int brkModeStateActive) {
		this.brkModeStateActive = brkModeStateActive;
	}

	public int getBrkVoltageOffsetAmplifierActive() {
		return brkVoltageOffsetAmplifierActive;
	}

	public void setBrkVoltageOffsetAmplifierActive(int brkVoltageOffsetAmplifierActive) {
		this.brkVoltageOffsetAmplifierActive = brkVoltageOffsetAmplifierActive;
	}

	public byte getBrkLastReceivedPacketRssiActive() {
		return brkLastReceivedPacketRssiActive;
	}

	public void setBrkLastReceivedPacketRssiActive(byte brkLastReceivedPacketRssiActive) {
		this.brkLastReceivedPacketRssiActive = brkLastReceivedPacketRssiActive;
	}

	public byte getBrkLastReceivedPacketSnrActive() {
		return brkLastReceivedPacketSnrActive;
	}

	public void setBrkLastReceivedPacketSnrActive(byte brkLastReceivedPacketSnrActive) {
		this.brkLastReceivedPacketSnrActive = brkLastReceivedPacketSnrActive;
	}

	public int getBrkArchiveRecordPointer() {
		return brkArchiveRecordPointer;
	}

	public void setBrkArchiveRecordPointer(int brkArchiveRecordPointer) {
		this.brkArchiveRecordPointer = brkArchiveRecordPointer;
	}

	public byte getBrkLastReceivedPackedSnrInactive() {
		return brkLastReceivedPackedSnrInactive;
	}

	public void setBrkLastReceivedPackedSnrInactive(byte brkLastReceivedPackedSnrInactive) {
		this.brkLastReceivedPackedSnrInactive = brkLastReceivedPackedSnrInactive;
	}

	public int getMsModuleState() {
		return msModuleState;
	}

	public void setMsModuleState(int msModuleState) {
		this.msModuleState = msModuleState;
	}

	public int getMsPayloadState() {
		return msPayloadState;
	}

	public void setMsPayloadState(int msPayloadState) {
		this.msPayloadState = msPayloadState;
	}

	public byte getMsTemp() {
		return msTemp;
	}

	public void setMsTemp(byte msTemp) {
		this.msTemp = msTemp;
	}

	public int getMsPayloadPowerState() {
		return msPayloadPowerState;
	}

	public void setMsPayloadPowerState(int msPayloadPowerState) {
		this.msPayloadPowerState = msPayloadPowerState;
	}

	public int getSopAltitudeGlonass() {
		return sopAltitudeGlonass;
	}

	public void setSopAltitudeGlonass(int sopAltitudeGlonass) {
		this.sopAltitudeGlonass = sopAltitudeGlonass;
	}

	public int getSopLatitudeGlonass() {
		return sopLatitudeGlonass;
	}

	public void setSopLatitudeGlonass(int sopLatitudeGlonass) {
		this.sopLatitudeGlonass = sopLatitudeGlonass;
	}

	public int getSopLongitudeGlonass() {
		return sopLongitudeGlonass;
	}

	public void setSopLongitudeGlonass(int sopLongitudeGlonass) {
		this.sopLongitudeGlonass = sopLongitudeGlonass;
	}

	public long getSopDateTimeGlonass() {
		return sopDateTimeGlonass;
	}

	public void setSopDateTimeGlonass(long sopDateTimeGlonass) {
		this.sopDateTimeGlonass = sopDateTimeGlonass;
	}

	public int getSopMagneticInductionModule() {
		return sopMagneticInductionModule;
	}

	public void setSopMagneticInductionModule(int sopMagneticInductionModule) {
		this.sopMagneticInductionModule = sopMagneticInductionModule;
	}

	public int getSopAngularVelocityVectorX() {
		return sopAngularVelocityVectorX;
	}

	public void setSopAngularVelocityVectorX(int sopAngularVelocityVectorX) {
		this.sopAngularVelocityVectorX = sopAngularVelocityVectorX;
	}

	public int getSopAngularVelocityVectorY() {
		return sopAngularVelocityVectorY;
	}

	public void setSopAngularVelocityVectorY(int sopAngularVelocityVectorY) {
		this.sopAngularVelocityVectorY = sopAngularVelocityVectorY;
	}

	public int getSopAngularVelocityVectorZ() {
		return sopAngularVelocityVectorZ;
	}

	public void setSopAngularVelocityVectorZ(int sopAngularVelocityVectorZ) {
		this.sopAngularVelocityVectorZ = sopAngularVelocityVectorZ;
	}

	public int getSopAnglePriority1() {
		return sopAnglePriority1;
	}

	public void setSopAnglePriority1(int sopAnglePriority1) {
		this.sopAnglePriority1 = sopAnglePriority1;
	}

	public int getSopAnglePriority2() {
		return sopAnglePriority2;
	}

	public void setSopAnglePriority2(int sopAnglePriority2) {
		this.sopAnglePriority2 = sopAnglePriority2;
	}

	public byte getSopMedianTemperature1() {
		return sopMedianTemperature1;
	}

	public void setSopMedianTemperature1(byte sopMedianTemperature1) {
		this.sopMedianTemperature1 = sopMedianTemperature1;
	}

	public byte getSopMedianTemperature6() {
		return sopMedianTemperature6;
	}

	public void setSopMedianTemperature6(byte sopMedianTemperature6) {
		this.sopMedianTemperature6 = sopMedianTemperature6;
	}

	public byte getSopBoardTemp() {
		return sopBoardTemp;
	}

	public void setSopBoardTemp(byte sopBoardTemp) {
		this.sopBoardTemp = sopBoardTemp;
	}

	public int getSopState() {
		return sopState;
	}

	public void setSopState(int sopState) {
		this.sopState = sopState;
	}

	public int getSopStateDsg1() {
		return sopStateDsg1;
	}

	public void setSopStateDsg1(int sopStateDsg1) {
		this.sopStateDsg1 = sopStateDsg1;
	}

	public int getSopStateDsg2() {
		return sopStateDsg2;
	}

	public void setSopStateDsg2(int sopStateDsg2) {
		this.sopStateDsg2 = sopStateDsg2;
	}

	public int getSopStateDsg3() {
		return sopStateDsg3;
	}

	public void setSopStateDsg3(int sopStateDsg3) {
		this.sopStateDsg3 = sopStateDsg3;
	}

	public int getSopOrientationNumber() {
		return sopOrientationNumber;
	}

	public void setSopOrientationNumber(int sopOrientationNumber) {
		this.sopOrientationNumber = sopOrientationNumber;
	}

	public byte getSesMedianPanelXTempPositive() {
		return sesMedianPanelXTempPositive;
	}

	public void setSesMedianPanelXTempPositive(byte sesMedianPanelXTempPositive) {
		this.sesMedianPanelXTempPositive = sesMedianPanelXTempPositive;
	}

	public byte getSesMedianPanelXTempNegative() {
		return sesMedianPanelXTempNegative;
	}

	public void setSesMedianPanelXTempNegative(byte sesMedianPanelXTempNegative) {
		this.sesMedianPanelXTempNegative = sesMedianPanelXTempNegative;
	}

	public long getSesSolarPanelsState() {
		return sesSolarPanelsState;
	}

	public void setSesSolarPanelsState(long sesSolarPanelsState) {
		this.sesSolarPanelsState = sesSolarPanelsState;
	}

	public int getSesChargeLevel() {
		return sesChargeLevel;
	}

	public void setSesChargeLevel(int sesChargeLevel) {
		this.sesChargeLevel = sesChargeLevel;
	}

	public int getSesBatteryState() {
		return sesBatteryState;
	}

	public void setSesBatteryState(int sesBatteryState) {
		this.sesBatteryState = sesBatteryState;
	}

	public int getSesChargingKeysState() {
		return sesChargingKeysState;
	}

	public void setSesChargingKeysState(int sesChargingKeysState) {
		this.sesChargingKeysState = sesChargingKeysState;
	}

	public int getSesPowerLineState() {
		return sesPowerLineState;
	}

	public void setSesPowerLineState(int sesPowerLineState) {
		this.sesPowerLineState = sesPowerLineState;
	}

	public short getSesTotalChargingPower() {
		return sesTotalChargingPower;
	}

	public void setSesTotalChargingPower(short sesTotalChargingPower) {
		this.sesTotalChargingPower = sesTotalChargingPower;
	}

	public int getSesTotalGeneratedPower() {
		return sesTotalGeneratedPower;
	}

	public void setSesTotalGeneratedPower(int sesTotalGeneratedPower) {
		this.sesTotalGeneratedPower = sesTotalGeneratedPower;
	}

	public int getSesTotalPowerLoad() {
		return sesTotalPowerLoad;
	}

	public void setSesTotalPowerLoad(int sesTotalPowerLoad) {
		this.sesTotalPowerLoad = sesTotalPowerLoad;
	}

	public byte getSesMedianPmmTemp() {
		return sesMedianPmmTemp;
	}

	public void setSesMedianPmmTemp(byte sesMedianPmmTemp) {
		this.sesMedianPmmTemp = sesMedianPmmTemp;
	}

	public byte getSesMedianPamTemp() {
		return sesMedianPamTemp;
	}

	public void setSesMedianPamTemp(byte sesMedianPamTemp) {
		this.sesMedianPamTemp = sesMedianPamTemp;
	}

	public byte getSesMedianPdmTemp() {
		return sesMedianPdmTemp;
	}

	public void setSesMedianPdmTemp(byte sesMedianPdmTemp) {
		this.sesMedianPdmTemp = sesMedianPdmTemp;
	}

	public long getSesModuleState() {
		return sesModuleState;
	}

	public void setSesModuleState(long sesModuleState) {
		this.sesModuleState = sesModuleState;
	}

}
