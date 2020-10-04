package ru.r2cloud.jradio.strand1;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Strand1Beacon extends Beacon {

	private int sequenceNumber;
	private int length;

	private Integer magX;
	private Integer magY;
	private Integer magZ;

	private Long unixTime;

	private CurrentDirection battery0CurrentDirection;
	private Float battery0Current;
	private Float battery0Voltage;
	private Float battery0Temperature;

	private CurrentDirection battery1CurrentDirection;
	private Float battery1Current;
	private Float battery1Voltage;
	private Float battery1Temperature;

	private Float arrayYPlusCurrent;
	private Float arrayYPlusTemperature;
	private Float arrayYVoltage;
	private Float arrayYMinusCurrent;
	private Float arrayYMinusTemperature;
	private Float arrayXVoltage;
	private Float arrayXMinusCurrent;
	private Float arrayXMinusTemperature;
	private Float arrayZVoltage;
	private Float arrayZPlusCurrent;
	private Float arrayZPlusTemperature;
	private Float arrayXPlusCurrent;
	private Float arrayXPlusTemperature;
	private Float batteryBusCurrent;
	private Float bus5vCurrent;
	private Float bus3v3Current;
	private Float arrayZMinusTemperature;
	private Float arrayZMinusCurrent;

	private SwitchBoard pptPowerSupply;
	private SwitchBoard ppt1And2;
	private SwitchBoard phone5VWebcam;
	private SwitchBoard warpValve;
	private SwitchBoard warpHeater;
	private SwitchBoard digiWi9c;
	private SwitchBoard sgr05;
	private SwitchBoard reactionWheels;
	private SwitchBoard solarPanelDeployArm;
	private SwitchBoard solarPanelDeployFire;

	private byte[] unknownPayload;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		dis.skipBytes(2);
		sequenceNumber = dis.readUnsignedByte();
		length = dis.readUnsignedByte();

		int id = dis.readUnsignedByte();
		if (id != 0x02) {
			return;
		}
		int i2cAddress = dis.readUnsignedByte();
		int nodeChannel = dis.readUnsignedByte();
		int dataLength = dis.readUnsignedByte();
		if (dis.available() < dataLength) {
			throw new UncorrectableException("data length is less expected");
		}
		LittleEndianDataInputStream ldis = new LittleEndianDataInputStream(dis);
		switch (i2cAddress) {
		case 0x89:
			switch (nodeChannel) {
			case 0x03:
				magX = ldis.readInt();
				magY = ldis.readInt();
				return;
			case 0x05:
				magZ = ldis.readInt();
				return;
			}
			break;
		case 0x80:
			switch (nodeChannel) {
			case 0x0c:
				unixTime = ldis.readUnsignedInt();
				return;
			}
			break;
		case 0x2c:
			long dataValue = ldis.readVariableUnsignedInt(dataLength);
			switch (nodeChannel) {
			case 0x00:
				if (dataValue < 30) {
					battery0CurrentDirection = CurrentDirection.CHARGE;
				} else {
					battery0CurrentDirection = CurrentDirection.DISCHARGE;
				}
				return;
			case 0x01:
				battery0Current = -3.4969f * dataValue + 3185.1551f;
				return;
			case 0x03:
				battery0Voltage = -0.00945f * dataValue + 9.7488f;
				return;
			case 0x04:
				battery0Temperature = -0.163f * dataValue + 111.187f;
				return;
			case 0x05:
				if (dataValue < 30) {
					battery1CurrentDirection = CurrentDirection.CHARGE;
				} else {
					battery1CurrentDirection = CurrentDirection.DISCHARGE;
				}
				return;
			case 0x06:
				battery1Current = -3.4768f * dataValue + 3173.1106f;
				return;
			case 0x08:
				battery1Voltage = -0.00946f * dataValue + 9.7526f;
				return;
			case 0x09:
				battery1Temperature = -0.163f * dataValue + 111.187f;
				return;
			}
			break;
		case 0x2d:
			dataValue = ldis.readVariableUnsignedInt(dataLength);
			switch (nodeChannel) {
			case 0x01:
				arrayYPlusCurrent = -0.542490348f * dataValue + 528.0441026f;
				return;
			case 0x02:
				arrayYPlusTemperature = -0.163f * dataValue + 110.338f;
				return;
			case 0x03:
				arrayYVoltage = -0.035254639f * dataValue + 34.6505381f;
				return;
			case 0x04:
				arrayYMinusCurrent = -0.537846059f * dataValue + 523.1519466f;
				return;
			case 0x05:
				arrayYMinusTemperature = -0.163f * dataValue + 110.338f;
				return;
			case 0x06:
				arrayXVoltage = -0.035579727f * dataValue + 34.76510021f;
				return;
			case 0x07:
				arrayXMinusCurrent = -0.541228423f * dataValue + 526.8412823f;
				return;
			case 0x08:
				arrayXMinusTemperature = -0.163f * dataValue + 110.338f;
				return;
			case 0x09:
				arrayZVoltage = -0.00914561f * dataValue + 8.782534345f;
				return;
			case 0x0a:
				arrayZPlusCurrent = -0.52264946f * dataValue + 508.5204547f;
				return;
			case 0x0b:
				arrayZPlusTemperature = -0.163f * dataValue + 110.338f;
				return;
			case 0x0d:
				arrayXPlusCurrent = -0.518702129f * dataValue + 512.807352f;
				return;
			case 0x0e:
				arrayXPlusTemperature = -0.163f * dataValue + 110.338f;
				return;
			case 0x11:
				batteryBusCurrent = -4.926127936f * dataValue + 4414.027999f;
				return;
			case 0x1a:
				bus5vCurrent = -5.431052862f * dataValue + 4636.008505f;
				return;
			case 0x1b:
				bus3v3Current = -3.626006798f * dataValue + 3080.538997f;
				return;
			case 0x1e:
				arrayZMinusTemperature = -0.163f * dataValue + 110.338f;
				return;
			case 0x1f:
				arrayZMinusCurrent = -0.52947555f * dataValue + 515.5141451f;
				return;
			}
			break;
		case 0x66:
			SwitchStatus status = SwitchStatus.valueOfCode(dis.readUnsignedByte());
			// this is big endian
			int currentValue = dis.readUnsignedShort();
			int voltageValue = dis.readUnsignedShort();
			switch (nodeChannel) {
			case 0x81:
				pptPowerSupply = new SwitchBoard(status, 0.259549f * currentValue - 1.516825f, 2.300107f * voltageValue - 1113.424579f);
				return;
			case 0x86:
				ppt1And2 = new SwitchBoard(status, 0.258359f * currentValue - 1.554162f, 2.315349f * voltageValue - 1136.056829f);
				return;
			case 0x8b:
				phone5VWebcam = new SwitchBoard(status, 0.259325f * currentValue - 1.595903f, 2.3315f * voltageValue - 1187.043977f);
				return;
			case 0x90:
				warpValve = new SwitchBoard(status, 0.518526f * currentValue - 8.756971f, 3.667785f * voltageValue - 7266.803691f);
				return;
			case 0x95:
				warpHeater = new SwitchBoard(status, 0.534516f * currentValue - 3.25046f, 2.603641f * voltageValue - 0.504061f);
				return;
			case 0x9a:
				digiWi9c = new SwitchBoard(status, 0.528245f * currentValue - 2.974109f, 2.233264f * voltageValue - 930.303516f);
				return;
			case 0x9f:
				sgr05 = new SwitchBoard(status, 0.260476f * currentValue - 0.91132f, 2.254974f * voltageValue - 993.915009f);
				return;
			case 0xa4:
				reactionWheels = new SwitchBoard(status, 0.532941f * currentValue - 3.152331f, 2.592693f * voltageValue + 3.656067f);
				return;
			case 0xa9:
				solarPanelDeployArm = new SwitchBoard(status, currentValue, voltageValue);
				return;
			case 0xac:
				solarPanelDeployFire = new SwitchBoard(status, currentValue, voltageValue);
				return;
			}
			break;
		}

		unknownPayload = new byte[ldis.available()];
		ldis.readFully(unknownPayload);
	}

	public int getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public Integer getMagX() {
		return magX;
	}

	public void setMagX(Integer magX) {
		this.magX = magX;
	}

	public Integer getMagY() {
		return magY;
	}

	public void setMagY(Integer magY) {
		this.magY = magY;
	}

	public Integer getMagZ() {
		return magZ;
	}

	public void setMagZ(Integer magZ) {
		this.magZ = magZ;
	}

	public Long getUnixTime() {
		return unixTime;
	}

	public void setUnixTime(Long unixTime) {
		this.unixTime = unixTime;
	}

	public CurrentDirection getBattery0CurrentDirection() {
		return battery0CurrentDirection;
	}

	public void setBattery0CurrentDirection(CurrentDirection battery0CurrentDirection) {
		this.battery0CurrentDirection = battery0CurrentDirection;
	}

	public Float getBattery0Current() {
		return battery0Current;
	}

	public void setBattery0Current(Float battery0Current) {
		this.battery0Current = battery0Current;
	}

	public Float getBattery0Voltage() {
		return battery0Voltage;
	}

	public void setBattery0Voltage(Float battery0Voltage) {
		this.battery0Voltage = battery0Voltage;
	}

	public Float getBattery0Temperature() {
		return battery0Temperature;
	}

	public void setBattery0Temperature(Float battery0Temperature) {
		this.battery0Temperature = battery0Temperature;
	}

	public CurrentDirection getBattery1CurrentDirection() {
		return battery1CurrentDirection;
	}

	public void setBattery1CurrentDirection(CurrentDirection battery1CurrentDirection) {
		this.battery1CurrentDirection = battery1CurrentDirection;
	}

	public Float getBattery1Current() {
		return battery1Current;
	}

	public void setBattery1Current(Float battery1Current) {
		this.battery1Current = battery1Current;
	}

	public Float getBattery1Voltage() {
		return battery1Voltage;
	}

	public void setBattery1Voltage(Float battery1Voltage) {
		this.battery1Voltage = battery1Voltage;
	}

	public Float getBattery1Temperature() {
		return battery1Temperature;
	}

	public void setBattery1Temperature(Float battery1Temperature) {
		this.battery1Temperature = battery1Temperature;
	}

	public Float getArrayYPlusCurrent() {
		return arrayYPlusCurrent;
	}

	public void setArrayYPlusCurrent(Float arrayYPlusCurrent) {
		this.arrayYPlusCurrent = arrayYPlusCurrent;
	}

	public Float getArrayYPlusTemperature() {
		return arrayYPlusTemperature;
	}

	public void setArrayYPlusTemperature(Float arrayYPlusTemperature) {
		this.arrayYPlusTemperature = arrayYPlusTemperature;
	}

	public Float getArrayYVoltage() {
		return arrayYVoltage;
	}

	public void setArrayYVoltage(Float arrayYVoltage) {
		this.arrayYVoltage = arrayYVoltage;
	}

	public Float getArrayYMinusCurrent() {
		return arrayYMinusCurrent;
	}

	public void setArrayYMinusCurrent(Float arrayYMinusCurrent) {
		this.arrayYMinusCurrent = arrayYMinusCurrent;
	}

	public Float getArrayYMinusTemperature() {
		return arrayYMinusTemperature;
	}

	public void setArrayYMinusTemperature(Float arrayYMinusTemperature) {
		this.arrayYMinusTemperature = arrayYMinusTemperature;
	}

	public Float getArrayXVoltage() {
		return arrayXVoltage;
	}

	public void setArrayXVoltage(Float arrayXVoltage) {
		this.arrayXVoltage = arrayXVoltage;
	}

	public Float getArrayXMinusCurrent() {
		return arrayXMinusCurrent;
	}

	public void setArrayXMinusCurrent(Float arrayXMinusCurrent) {
		this.arrayXMinusCurrent = arrayXMinusCurrent;
	}

	public Float getArrayXMinusTemperature() {
		return arrayXMinusTemperature;
	}

	public void setArrayXMinusTemperature(Float arrayXMinusTemperature) {
		this.arrayXMinusTemperature = arrayXMinusTemperature;
	}

	public Float getArrayZVoltage() {
		return arrayZVoltage;
	}

	public void setArrayZVoltage(Float arrayZVoltage) {
		this.arrayZVoltage = arrayZVoltage;
	}

	public Float getArrayZPlusCurrent() {
		return arrayZPlusCurrent;
	}

	public void setArrayZPlusCurrent(Float arrayZPlusCurrent) {
		this.arrayZPlusCurrent = arrayZPlusCurrent;
	}

	public Float getArrayZPlusTemperature() {
		return arrayZPlusTemperature;
	}

	public void setArrayZPlusTemperature(Float arrayZPlusTemperature) {
		this.arrayZPlusTemperature = arrayZPlusTemperature;
	}

	public Float getArrayXPlusCurrent() {
		return arrayXPlusCurrent;
	}

	public void setArrayXPlusCurrent(Float arrayXPlusCurrent) {
		this.arrayXPlusCurrent = arrayXPlusCurrent;
	}

	public Float getArrayXPlusTemperature() {
		return arrayXPlusTemperature;
	}

	public void setArrayXPlusTemperature(Float arrayXPlusTemperature) {
		this.arrayXPlusTemperature = arrayXPlusTemperature;
	}

	public Float getBatteryBusCurrent() {
		return batteryBusCurrent;
	}

	public void setBatteryBusCurrent(Float batteryBusCurrent) {
		this.batteryBusCurrent = batteryBusCurrent;
	}

	public Float getBus5vCurrent() {
		return bus5vCurrent;
	}

	public void setBus5vCurrent(Float bus5vCurrent) {
		this.bus5vCurrent = bus5vCurrent;
	}

	public Float getBus3v3Current() {
		return bus3v3Current;
	}

	public void setBus3v3Current(Float bus3v3Current) {
		this.bus3v3Current = bus3v3Current;
	}

	public Float getArrayZMinusTemperature() {
		return arrayZMinusTemperature;
	}

	public void setArrayZMinusTemperature(Float arrayZMinusTemperature) {
		this.arrayZMinusTemperature = arrayZMinusTemperature;
	}

	public Float getArrayZMinusCurrent() {
		return arrayZMinusCurrent;
	}

	public void setArrayZMinusCurrent(Float arrayZMinusCurrent) {
		this.arrayZMinusCurrent = arrayZMinusCurrent;
	}

	public SwitchBoard getPptPowerSupply() {
		return pptPowerSupply;
	}

	public void setPptPowerSupply(SwitchBoard pptPowerSupply) {
		this.pptPowerSupply = pptPowerSupply;
	}

	public SwitchBoard getPpt1And2() {
		return ppt1And2;
	}

	public void setPpt1And2(SwitchBoard ppt1And2) {
		this.ppt1And2 = ppt1And2;
	}

	public SwitchBoard getPhone5VWebcam() {
		return phone5VWebcam;
	}

	public void setPhone5VWebcam(SwitchBoard phone5vWebcam) {
		phone5VWebcam = phone5vWebcam;
	}

	public SwitchBoard getWarpValve() {
		return warpValve;
	}

	public void setWarpValve(SwitchBoard warpValve) {
		this.warpValve = warpValve;
	}

	public SwitchBoard getWarpHeater() {
		return warpHeater;
	}

	public void setWarpHeater(SwitchBoard warpHeater) {
		this.warpHeater = warpHeater;
	}

	public SwitchBoard getDigiWi9c() {
		return digiWi9c;
	}

	public void setDigiWi9c(SwitchBoard digiWi9c) {
		this.digiWi9c = digiWi9c;
	}

	public SwitchBoard getSgr05() {
		return sgr05;
	}

	public void setSgr05(SwitchBoard sgr05) {
		this.sgr05 = sgr05;
	}

	public SwitchBoard getReactionWheels() {
		return reactionWheels;
	}

	public void setReactionWheels(SwitchBoard reactionWheels) {
		this.reactionWheels = reactionWheels;
	}

	public SwitchBoard getSolarPanelDeployArm() {
		return solarPanelDeployArm;
	}

	public void setSolarPanelDeployArm(SwitchBoard solarPanelDeployArm) {
		this.solarPanelDeployArm = solarPanelDeployArm;
	}

	public SwitchBoard getSolarPanelDeployFire() {
		return solarPanelDeployFire;
	}

	public void setSolarPanelDeployFire(SwitchBoard solarPanelDeployFire) {
		this.solarPanelDeployFire = solarPanelDeployFire;
	}

	public byte[] getUnknownPayload() {
		return unknownPayload;
	}

	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
	}

}
