package ru.r2cloud.jradio.grifex;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.ax25.Ax25Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class GrifexBeacon extends Ax25Beacon {

	private MxlHeader mxlHeader;

	private long rtcUnixTime;
	private int numresets;
	private int avgnumactivetasks1;
	private int avgnumactivetasks5;
	private int avgnumactivetasks15;
	private int curnumrunnabletasks;
	private int totnumprocesses;
	private int lastprocesspid;
	private int totmem;
	private int freemem;

	private int adcsEnableStatus;
	private long sdUsage;
	private long datamntUsage;

	private int lithiumOpCount;
	private int lithiumMsp430Temp;
	private int lithiumRssi;
	private long lithiumRx;
	private long lithiumTx;

	private int fcpuTemp0;
	private int fcpuTemp1;
	private int li3v3Voltage;
	private int fcpu3v3Current;
	private int li3v3Current;
	private int fcpu3v3Voltage;
	private int liVbattVoltage;
	private int liVbattCurrent;
	private int batteryVoltage;
	private int batteryCurrent;
	private int batteryTemperature;
	private int batteryBusVoltage;
	private int batteryBusCurrent;
	private int busVoltage5v;
	private int busCurrent5v;
	private int inputCurrent5v;
	private int busVoltage3v3;
	private int busCurrent3v3;
	private int inputCurrent3v3;
	private int outputRegulatorTemperature;
	private int eps5vVoltage;
	private int eps5vCurrent;
	private int eps3v3Voltage;
	private int eps3v3Current;
	private int channel1PanelVoltageB;
	private int channel1PanelCurrentB;
	private int channel1OutputVoltage;
	private int channel1OutputCurrent;
	private int channel1ModuleTemperature;
	private int channel1BoardTemperature;
	private int channel2PanelVoltageB;
	private int channel2PanelCurrentB;
	private int channel2OutputVoltage;
	private int channel2OutputCurrent;
	private int channel2ModuleTemperature;
	private int channel2BoardTemperature;
	private int channel3PanelVoltageB;
	private int channel3PanelCurrentB;
	private int channel3OutputVoltage;
	private int channel3OutputCurrent;
	private int channel3ModuleTemperature;
	private int channel3BoardTemperature;
	private int channel4PanelVoltageB;
	private int channel4PanelCurrentB;
	private int channel4OutputVoltage;
	private int channel4OutputCurrent;
	private int channel4ModuleTemperature;
	private int channel4BoardTemperature;
	private int epsIoeState;
	private int epsIoeMode;
	private int tcbTemp0;
	private int tcbTemp1;
	private int tcbTemp2;
	private int adcs1IoeState;
	private int adcs1IoeMode;
	private int posyMagX;
	private int posyMagY;
	private int posyMagZ;
	private int posxMagX;
	private int posxMagY;
	private int posxMagZ;
	private int negyMagX;
	private int negyMagY;
	private int negyMagZ;
	private int negxMagX;
	private int negxMagY;
	private int negxMagZ;
	private int posyInternalTemperature;
	private int posyExternalTemperature;
	private int posxInternalTemperature;
	private int posxExternalTemperature;
	private int negyInternalTemperature;
	private int negyExternalTemperature;
	private int negxInternalTemperature;
	private int negxExternalTemperature;
	private int posyPhotodiode;
	private int posxPhotodiode;
	private int negyPhotodiode;
	private int negxPhotodiode;
	private int mzintIoe1State;
	private int mzintIoe1Mode;
	private int mzintIoe2State;
	private int mzintIoe2Mode;
	private int marinaGpioStatusData;
	private int marinaCompletedRuns;
	private int marinaAbortedRuns;
	private int marinaVbattVoltage;
	private int marinaVbattCurrent;
	private int marinaTemperature;
	private int marina2v5Voltage;
	private int marina1v0Voltage;
	private int marinaExitStatus;
	private int variable1;
	private int variable2;
	private int variable3;
	private int variable4;
	private int variable5;
	private int variable6;
	private int variable7;
	private int variable8;
	private int variable9;
	private int variable10;

	private byte[] unknownPayload;

	@Override
	public void readBeacon(DataInputStream dis) throws IOException, UncorrectableException {
		mxlHeader = new MxlHeader(dis);
		if (mxlHeader.getPacketLength() != dis.available() + MxlHeader.LENGTH_BYTES) {
			throw new UncorrectableException("not enough bytes in the input");
		}
		if (mxlHeader.getSecondaryId() != 0x42) {
			throw new UncorrectableException("unknown spacecraft: " + mxlHeader.getSecondaryId());
		}
		LittleEndianDataInputStream ldis = new LittleEndianDataInputStream(dis);
		rtcUnixTime = ldis.readUnsignedInt();
		numresets = ldis.readUnsignedShort();
		avgnumactivetasks1 = ldis.readUnsignedShort();
		avgnumactivetasks5 = ldis.readUnsignedShort();
		avgnumactivetasks15 = ldis.readUnsignedShort();
		curnumrunnabletasks = ldis.readUnsignedShort();
		totnumprocesses = ldis.readUnsignedShort();
		lastprocesspid = ldis.readUnsignedShort();
		totmem = ldis.readUnsignedShort();
		freemem = ldis.readUnsignedShort();

		adcsEnableStatus = ldis.readUnsignedByte();
		sdUsage = ldis.readUnsignedInt();
		datamntUsage = ldis.readUnsignedInt();

		lithiumOpCount = ldis.readUnsignedShort();
		lithiumMsp430Temp = ldis.readUnsignedShort();
		lithiumRssi = ldis.readUnsignedByte();
		lithiumRx = ldis.readUnsignedInt();
		lithiumTx = ldis.readUnsignedInt();
		fcpuTemp0 = ldis.readUnsignedShort();
		fcpuTemp1 = ldis.readUnsignedShort();
		li3v3Voltage = ldis.readUnsignedShort();
		fcpu3v3Current = ldis.readUnsignedShort();
		li3v3Current = ldis.readUnsignedShort();
		fcpu3v3Voltage = ldis.readUnsignedShort();
		liVbattVoltage = ldis.readUnsignedShort();
		liVbattCurrent = ldis.readUnsignedShort();
		batteryVoltage = ldis.readUnsignedShort();
		batteryCurrent = ldis.readUnsignedShort();
		batteryTemperature = ldis.readUnsignedShort();
		batteryBusVoltage = ldis.readUnsignedShort();
		batteryBusCurrent = ldis.readUnsignedShort();
		busVoltage5v = ldis.readUnsignedShort();
		busCurrent5v = ldis.readUnsignedShort();
		inputCurrent5v = ldis.readUnsignedShort();
		busVoltage3v3 = ldis.readUnsignedShort();
		busCurrent3v3 = ldis.readUnsignedShort();
		inputCurrent3v3 = ldis.readUnsignedShort();
		outputRegulatorTemperature = ldis.readUnsignedShort();
		eps5vVoltage = ldis.readUnsignedShort();
		eps5vCurrent = ldis.readUnsignedShort();
		eps3v3Voltage = ldis.readUnsignedShort();
		eps3v3Current = ldis.readUnsignedShort();
		channel1PanelVoltageB = ldis.readUnsignedShort();
		channel1PanelCurrentB = ldis.readUnsignedShort();
		channel1OutputVoltage = ldis.readUnsignedShort();
		channel1OutputCurrent = ldis.readUnsignedShort();
		channel1ModuleTemperature = ldis.readUnsignedShort();
		channel1BoardTemperature = ldis.readUnsignedShort();
		channel2PanelVoltageB = ldis.readUnsignedShort();
		channel2PanelCurrentB = ldis.readUnsignedShort();
		channel2OutputVoltage = ldis.readUnsignedShort();
		channel2OutputCurrent = ldis.readUnsignedShort();
		channel2ModuleTemperature = ldis.readUnsignedShort();
		channel2BoardTemperature = ldis.readUnsignedShort();
		channel3PanelVoltageB = ldis.readUnsignedShort();
		channel3PanelCurrentB = ldis.readUnsignedShort();
		channel3OutputVoltage = ldis.readUnsignedShort();
		channel3OutputCurrent = ldis.readUnsignedShort();
		channel3ModuleTemperature = ldis.readUnsignedShort();
		channel3BoardTemperature = ldis.readUnsignedShort();
		channel4PanelVoltageB = ldis.readUnsignedShort();
		channel4PanelCurrentB = ldis.readUnsignedShort();
		channel4OutputVoltage = ldis.readUnsignedShort();
		channel4OutputCurrent = ldis.readUnsignedShort();
		channel4ModuleTemperature = ldis.readUnsignedShort();
		channel4BoardTemperature = ldis.readUnsignedShort();

		epsIoeState = ldis.readUnsignedByte();
		epsIoeMode = ldis.readUnsignedByte();
		tcbTemp0 = ldis.readUnsignedShort();
		tcbTemp1 = ldis.readUnsignedShort();
		tcbTemp2 = ldis.readUnsignedShort();
		adcs1IoeState = ldis.readUnsignedByte();
		adcs1IoeMode = ldis.readUnsignedByte();
		posyMagX = ldis.readShort();
		posyMagY = ldis.readShort();
		posyMagZ = ldis.readShort();
		posxMagX = ldis.readShort();
		posxMagY = ldis.readShort();
		posxMagZ = ldis.readShort();
		negyMagX = ldis.readShort();
		negyMagY = ldis.readShort();
		negyMagZ = ldis.readShort();
		negxMagX = ldis.readShort();
		negxMagY = ldis.readShort();
		negxMagZ = ldis.readShort();
		posyInternalTemperature = ldis.readUnsignedShort();
		posyExternalTemperature = ldis.readUnsignedShort();
		posxInternalTemperature = ldis.readUnsignedShort();
		posxExternalTemperature = ldis.readUnsignedShort();
		negyInternalTemperature = ldis.readUnsignedShort();
		negyExternalTemperature = ldis.readUnsignedShort();
		negxInternalTemperature = ldis.readUnsignedShort();
		negxExternalTemperature = ldis.readUnsignedShort();
		posyPhotodiode = ldis.readUnsignedShort();
		posxPhotodiode = ldis.readUnsignedShort();
		negyPhotodiode = ldis.readUnsignedShort();
		negxPhotodiode = ldis.readUnsignedShort();
		mzintIoe1State = ldis.readUnsignedByte();
		mzintIoe1Mode = ldis.readUnsignedByte();
		mzintIoe2State = ldis.readUnsignedByte();
		mzintIoe2Mode = ldis.readUnsignedByte();
		marinaGpioStatusData = ldis.readUnsignedByte();
		marinaCompletedRuns = ldis.readUnsignedShort();
		marinaAbortedRuns = ldis.readUnsignedShort();
		marinaVbattVoltage = ldis.readUnsignedShort();
		marinaVbattCurrent = ldis.readUnsignedShort();
		marinaTemperature = ldis.readUnsignedShort();
		marina2v5Voltage = ldis.readUnsignedShort();
		marina1v0Voltage = ldis.readUnsignedShort();
		marinaExitStatus = ldis.readUnsignedByte();
		variable1 = ldis.readUnsignedByte();
		variable2 = ldis.readUnsignedByte();
		variable3 = ldis.readUnsignedByte();
		variable4 = ldis.readUnsignedByte();
		variable5 = ldis.readUnsignedByte();
		variable6 = ldis.readUnsignedByte();
		variable7 = ldis.readUnsignedByte();
		variable8 = ldis.readUnsignedByte();
		variable9 = ldis.readUnsignedByte();
		variable10 = ldis.readUnsignedByte();
	}

	public MxlHeader getMxlHeader() {
		return mxlHeader;
	}

	public void setMxlHeader(MxlHeader mxlHeader) {
		this.mxlHeader = mxlHeader;
	}

	public long getRtcUnixTime() {
		return rtcUnixTime;
	}

	public void setRtcUnixTime(long rtcUnixTime) {
		this.rtcUnixTime = rtcUnixTime;
	}

	public int getNumresets() {
		return numresets;
	}

	public void setNumresets(int numresets) {
		this.numresets = numresets;
	}

	public int getAvgnumactivetasks1() {
		return avgnumactivetasks1;
	}

	public void setAvgnumactivetasks1(int avgnumactivetasks1) {
		this.avgnumactivetasks1 = avgnumactivetasks1;
	}

	public int getAvgnumactivetasks5() {
		return avgnumactivetasks5;
	}

	public void setAvgnumactivetasks5(int avgnumactivetasks5) {
		this.avgnumactivetasks5 = avgnumactivetasks5;
	}

	public int getAvgnumactivetasks15() {
		return avgnumactivetasks15;
	}

	public void setAvgnumactivetasks15(int avgnumactivetasks15) {
		this.avgnumactivetasks15 = avgnumactivetasks15;
	}

	public int getCurnumrunnabletasks() {
		return curnumrunnabletasks;
	}

	public void setCurnumrunnabletasks(int curnumrunnabletasks) {
		this.curnumrunnabletasks = curnumrunnabletasks;
	}

	public int getTotnumprocesses() {
		return totnumprocesses;
	}

	public void setTotnumprocesses(int totnumprocesses) {
		this.totnumprocesses = totnumprocesses;
	}

	public int getLastprocesspid() {
		return lastprocesspid;
	}

	public void setLastprocesspid(int lastprocesspid) {
		this.lastprocesspid = lastprocesspid;
	}

	public int getTotmem() {
		return totmem;
	}

	public void setTotmem(int totmem) {
		this.totmem = totmem;
	}

	public int getFreemem() {
		return freemem;
	}

	public void setFreemem(int freemem) {
		this.freemem = freemem;
	}

	public int getAdcsEnableStatus() {
		return adcsEnableStatus;
	}

	public void setAdcsEnableStatus(int adcsEnableStatus) {
		this.adcsEnableStatus = adcsEnableStatus;
	}

	public long getSdUsage() {
		return sdUsage;
	}

	public void setSdUsage(long sdUsage) {
		this.sdUsage = sdUsage;
	}

	public long getDatamntUsage() {
		return datamntUsage;
	}

	public void setDatamntUsage(long datamntUsage) {
		this.datamntUsage = datamntUsage;
	}

	public int getLithiumOpCount() {
		return lithiumOpCount;
	}

	public void setLithiumOpCount(int lithiumOpCount) {
		this.lithiumOpCount = lithiumOpCount;
	}

	public int getLithiumMsp430Temp() {
		return lithiumMsp430Temp;
	}

	public void setLithiumMsp430Temp(int lithiumMsp430Temp) {
		this.lithiumMsp430Temp = lithiumMsp430Temp;
	}

	public int getLithiumRssi() {
		return lithiumRssi;
	}

	public void setLithiumRssi(int lithiumRssi) {
		this.lithiumRssi = lithiumRssi;
	}

	public long getLithiumRx() {
		return lithiumRx;
	}

	public void setLithiumRx(long lithiumRx) {
		this.lithiumRx = lithiumRx;
	}

	public long getLithiumTx() {
		return lithiumTx;
	}

	public void setLithiumTx(long lithiumTx) {
		this.lithiumTx = lithiumTx;
	}

	public int getFcpuTemp0() {
		return fcpuTemp0;
	}

	public void setFcpuTemp0(int fcpuTemp0) {
		this.fcpuTemp0 = fcpuTemp0;
	}

	public int getFcpuTemp1() {
		return fcpuTemp1;
	}

	public void setFcpuTemp1(int fcpuTemp1) {
		this.fcpuTemp1 = fcpuTemp1;
	}

	public int getLi3v3Voltage() {
		return li3v3Voltage;
	}

	public void setLi3v3Voltage(int li3v3Voltage) {
		this.li3v3Voltage = li3v3Voltage;
	}

	public int getFcpu3v3Current() {
		return fcpu3v3Current;
	}

	public void setFcpu3v3Current(int fcpu3v3Current) {
		this.fcpu3v3Current = fcpu3v3Current;
	}

	public int getLi3v3Current() {
		return li3v3Current;
	}

	public void setLi3v3Current(int li3v3Current) {
		this.li3v3Current = li3v3Current;
	}

	public int getFcpu3v3Voltage() {
		return fcpu3v3Voltage;
	}

	public void setFcpu3v3Voltage(int fcpu3v3Voltage) {
		this.fcpu3v3Voltage = fcpu3v3Voltage;
	}

	public int getLiVbattVoltage() {
		return liVbattVoltage;
	}

	public void setLiVbattVoltage(int liVbattVoltage) {
		this.liVbattVoltage = liVbattVoltage;
	}

	public int getLiVbattCurrent() {
		return liVbattCurrent;
	}

	public void setLiVbattCurrent(int liVbattCurrent) {
		this.liVbattCurrent = liVbattCurrent;
	}

	public int getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(int batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public int getBatteryCurrent() {
		return batteryCurrent;
	}

	public void setBatteryCurrent(int batteryCurrent) {
		this.batteryCurrent = batteryCurrent;
	}

	public int getBatteryTemperature() {
		return batteryTemperature;
	}

	public void setBatteryTemperature(int batteryTemperature) {
		this.batteryTemperature = batteryTemperature;
	}

	public int getBatteryBusVoltage() {
		return batteryBusVoltage;
	}

	public void setBatteryBusVoltage(int batteryBusVoltage) {
		this.batteryBusVoltage = batteryBusVoltage;
	}

	public int getBatteryBusCurrent() {
		return batteryBusCurrent;
	}

	public void setBatteryBusCurrent(int batteryBusCurrent) {
		this.batteryBusCurrent = batteryBusCurrent;
	}

	public int getBusVoltage5v() {
		return busVoltage5v;
	}

	public void setBusVoltage5v(int busVoltage5v) {
		this.busVoltage5v = busVoltage5v;
	}

	public int getBusCurrent5v() {
		return busCurrent5v;
	}

	public void setBusCurrent5v(int busCurrent5v) {
		this.busCurrent5v = busCurrent5v;
	}

	public int getInputCurrent5v() {
		return inputCurrent5v;
	}

	public void setInputCurrent5v(int inputCurrent5v) {
		this.inputCurrent5v = inputCurrent5v;
	}

	public int getBusVoltage3v3() {
		return busVoltage3v3;
	}

	public void setBusVoltage3v3(int busVoltage3v3) {
		this.busVoltage3v3 = busVoltage3v3;
	}

	public int getBusCurrent3v3() {
		return busCurrent3v3;
	}

	public void setBusCurrent3v3(int busCurrent3v3) {
		this.busCurrent3v3 = busCurrent3v3;
	}

	public int getInputCurrent3v3() {
		return inputCurrent3v3;
	}

	public void setInputCurrent3v3(int inputCurrent3v3) {
		this.inputCurrent3v3 = inputCurrent3v3;
	}

	public int getOutputRegulatorTemperature() {
		return outputRegulatorTemperature;
	}

	public void setOutputRegulatorTemperature(int outputRegulatorTemperature) {
		this.outputRegulatorTemperature = outputRegulatorTemperature;
	}

	public int getEps5vVoltage() {
		return eps5vVoltage;
	}

	public void setEps5vVoltage(int eps5vVoltage) {
		this.eps5vVoltage = eps5vVoltage;
	}

	public int getEps5vCurrent() {
		return eps5vCurrent;
	}

	public void setEps5vCurrent(int eps5vCurrent) {
		this.eps5vCurrent = eps5vCurrent;
	}

	public int getEps3v3Voltage() {
		return eps3v3Voltage;
	}

	public void setEps3v3Voltage(int eps3v3Voltage) {
		this.eps3v3Voltage = eps3v3Voltage;
	}

	public int getEps3v3Current() {
		return eps3v3Current;
	}

	public void setEps3v3Current(int eps3v3Current) {
		this.eps3v3Current = eps3v3Current;
	}

	public int getChannel1PanelVoltageB() {
		return channel1PanelVoltageB;
	}

	public void setChannel1PanelVoltageB(int channel1PanelVoltageB) {
		this.channel1PanelVoltageB = channel1PanelVoltageB;
	}

	public int getChannel1PanelCurrentB() {
		return channel1PanelCurrentB;
	}

	public void setChannel1PanelCurrentB(int channel1PanelCurrentB) {
		this.channel1PanelCurrentB = channel1PanelCurrentB;
	}

	public int getChannel1OutputVoltage() {
		return channel1OutputVoltage;
	}

	public void setChannel1OutputVoltage(int channel1OutputVoltage) {
		this.channel1OutputVoltage = channel1OutputVoltage;
	}

	public int getChannel1OutputCurrent() {
		return channel1OutputCurrent;
	}

	public void setChannel1OutputCurrent(int channel1OutputCurrent) {
		this.channel1OutputCurrent = channel1OutputCurrent;
	}

	public int getChannel1ModuleTemperature() {
		return channel1ModuleTemperature;
	}

	public void setChannel1ModuleTemperature(int channel1ModuleTemperature) {
		this.channel1ModuleTemperature = channel1ModuleTemperature;
	}

	public int getChannel1BoardTemperature() {
		return channel1BoardTemperature;
	}

	public void setChannel1BoardTemperature(int channel1BoardTemperature) {
		this.channel1BoardTemperature = channel1BoardTemperature;
	}

	public int getChannel2PanelVoltageB() {
		return channel2PanelVoltageB;
	}

	public void setChannel2PanelVoltageB(int channel2PanelVoltageB) {
		this.channel2PanelVoltageB = channel2PanelVoltageB;
	}

	public int getChannel2PanelCurrentB() {
		return channel2PanelCurrentB;
	}

	public void setChannel2PanelCurrentB(int channel2PanelCurrentB) {
		this.channel2PanelCurrentB = channel2PanelCurrentB;
	}

	public int getChannel2OutputVoltage() {
		return channel2OutputVoltage;
	}

	public void setChannel2OutputVoltage(int channel2OutputVoltage) {
		this.channel2OutputVoltage = channel2OutputVoltage;
	}

	public int getChannel2OutputCurrent() {
		return channel2OutputCurrent;
	}

	public void setChannel2OutputCurrent(int channel2OutputCurrent) {
		this.channel2OutputCurrent = channel2OutputCurrent;
	}

	public int getChannel2ModuleTemperature() {
		return channel2ModuleTemperature;
	}

	public void setChannel2ModuleTemperature(int channel2ModuleTemperature) {
		this.channel2ModuleTemperature = channel2ModuleTemperature;
	}

	public int getChannel2BoardTemperature() {
		return channel2BoardTemperature;
	}

	public void setChannel2BoardTemperature(int channel2BoardTemperature) {
		this.channel2BoardTemperature = channel2BoardTemperature;
	}

	public int getChannel3PanelVoltageB() {
		return channel3PanelVoltageB;
	}

	public void setChannel3PanelVoltageB(int channel3PanelVoltageB) {
		this.channel3PanelVoltageB = channel3PanelVoltageB;
	}

	public int getChannel3PanelCurrentB() {
		return channel3PanelCurrentB;
	}

	public void setChannel3PanelCurrentB(int channel3PanelCurrentB) {
		this.channel3PanelCurrentB = channel3PanelCurrentB;
	}

	public int getChannel3OutputVoltage() {
		return channel3OutputVoltage;
	}

	public void setChannel3OutputVoltage(int channel3OutputVoltage) {
		this.channel3OutputVoltage = channel3OutputVoltage;
	}

	public int getChannel3OutputCurrent() {
		return channel3OutputCurrent;
	}

	public void setChannel3OutputCurrent(int channel3OutputCurrent) {
		this.channel3OutputCurrent = channel3OutputCurrent;
	}

	public int getChannel3ModuleTemperature() {
		return channel3ModuleTemperature;
	}

	public void setChannel3ModuleTemperature(int channel3ModuleTemperature) {
		this.channel3ModuleTemperature = channel3ModuleTemperature;
	}

	public int getChannel3BoardTemperature() {
		return channel3BoardTemperature;
	}

	public void setChannel3BoardTemperature(int channel3BoardTemperature) {
		this.channel3BoardTemperature = channel3BoardTemperature;
	}

	public int getChannel4PanelVoltageB() {
		return channel4PanelVoltageB;
	}

	public void setChannel4PanelVoltageB(int channel4PanelVoltageB) {
		this.channel4PanelVoltageB = channel4PanelVoltageB;
	}

	public int getChannel4PanelCurrentB() {
		return channel4PanelCurrentB;
	}

	public void setChannel4PanelCurrentB(int channel4PanelCurrentB) {
		this.channel4PanelCurrentB = channel4PanelCurrentB;
	}

	public int getChannel4OutputVoltage() {
		return channel4OutputVoltage;
	}

	public void setChannel4OutputVoltage(int channel4OutputVoltage) {
		this.channel4OutputVoltage = channel4OutputVoltage;
	}

	public int getChannel4OutputCurrent() {
		return channel4OutputCurrent;
	}

	public void setChannel4OutputCurrent(int channel4OutputCurrent) {
		this.channel4OutputCurrent = channel4OutputCurrent;
	}

	public int getChannel4ModuleTemperature() {
		return channel4ModuleTemperature;
	}

	public void setChannel4ModuleTemperature(int channel4ModuleTemperature) {
		this.channel4ModuleTemperature = channel4ModuleTemperature;
	}

	public int getChannel4BoardTemperature() {
		return channel4BoardTemperature;
	}

	public void setChannel4BoardTemperature(int channel4BoardTemperature) {
		this.channel4BoardTemperature = channel4BoardTemperature;
	}

	public int getEpsIoeState() {
		return epsIoeState;
	}

	public void setEpsIoeState(int epsIoeState) {
		this.epsIoeState = epsIoeState;
	}

	public int getEpsIoeMode() {
		return epsIoeMode;
	}

	public void setEpsIoeMode(int epsIoeMode) {
		this.epsIoeMode = epsIoeMode;
	}

	public int getTcbTemp0() {
		return tcbTemp0;
	}

	public void setTcbTemp0(int tcbTemp0) {
		this.tcbTemp0 = tcbTemp0;
	}

	public int getTcbTemp1() {
		return tcbTemp1;
	}

	public void setTcbTemp1(int tcbTemp1) {
		this.tcbTemp1 = tcbTemp1;
	}

	public int getTcbTemp2() {
		return tcbTemp2;
	}

	public void setTcbTemp2(int tcbTemp2) {
		this.tcbTemp2 = tcbTemp2;
	}

	public int getAdcs1IoeState() {
		return adcs1IoeState;
	}

	public void setAdcs1IoeState(int adcs1IoeState) {
		this.adcs1IoeState = adcs1IoeState;
	}

	public int getAdcs1IoeMode() {
		return adcs1IoeMode;
	}

	public void setAdcs1IoeMode(int adcs1IoeMode) {
		this.adcs1IoeMode = adcs1IoeMode;
	}

	public int getPosyMagX() {
		return posyMagX;
	}

	public void setPosyMagX(int posyMagX) {
		this.posyMagX = posyMagX;
	}

	public int getPosyMagY() {
		return posyMagY;
	}

	public void setPosyMagY(int posyMagY) {
		this.posyMagY = posyMagY;
	}

	public int getPosyMagZ() {
		return posyMagZ;
	}

	public void setPosyMagZ(int posyMagZ) {
		this.posyMagZ = posyMagZ;
	}

	public int getPosxMagX() {
		return posxMagX;
	}

	public void setPosxMagX(int posxMagX) {
		this.posxMagX = posxMagX;
	}

	public int getPosxMagY() {
		return posxMagY;
	}

	public void setPosxMagY(int posxMagY) {
		this.posxMagY = posxMagY;
	}

	public int getPosxMagZ() {
		return posxMagZ;
	}

	public void setPosxMagZ(int posxMagZ) {
		this.posxMagZ = posxMagZ;
	}

	public int getNegyMagX() {
		return negyMagX;
	}

	public void setNegyMagX(int negyMagX) {
		this.negyMagX = negyMagX;
	}

	public int getNegyMagY() {
		return negyMagY;
	}

	public void setNegyMagY(int negyMagY) {
		this.negyMagY = negyMagY;
	}

	public int getNegyMagZ() {
		return negyMagZ;
	}

	public void setNegyMagZ(int negyMagZ) {
		this.negyMagZ = negyMagZ;
	}

	public int getNegxMagX() {
		return negxMagX;
	}

	public void setNegxMagX(int negxMagX) {
		this.negxMagX = negxMagX;
	}

	public int getNegxMagY() {
		return negxMagY;
	}

	public void setNegxMagY(int negxMagY) {
		this.negxMagY = negxMagY;
	}

	public int getNegxMagZ() {
		return negxMagZ;
	}

	public void setNegxMagZ(int negxMagZ) {
		this.negxMagZ = negxMagZ;
	}

	public int getPosyInternalTemperature() {
		return posyInternalTemperature;
	}

	public void setPosyInternalTemperature(int posyInternalTemperature) {
		this.posyInternalTemperature = posyInternalTemperature;
	}

	public int getPosyExternalTemperature() {
		return posyExternalTemperature;
	}

	public void setPosyExternalTemperature(int posyExternalTemperature) {
		this.posyExternalTemperature = posyExternalTemperature;
	}

	public int getPosxInternalTemperature() {
		return posxInternalTemperature;
	}

	public void setPosxInternalTemperature(int posxInternalTemperature) {
		this.posxInternalTemperature = posxInternalTemperature;
	}

	public int getPosxExternalTemperature() {
		return posxExternalTemperature;
	}

	public void setPosxExternalTemperature(int posxExternalTemperature) {
		this.posxExternalTemperature = posxExternalTemperature;
	}

	public int getNegyInternalTemperature() {
		return negyInternalTemperature;
	}

	public void setNegyInternalTemperature(int negyInternalTemperature) {
		this.negyInternalTemperature = negyInternalTemperature;
	}

	public int getNegyExternalTemperature() {
		return negyExternalTemperature;
	}

	public void setNegyExternalTemperature(int negyExternalTemperature) {
		this.negyExternalTemperature = negyExternalTemperature;
	}

	public int getNegxInternalTemperature() {
		return negxInternalTemperature;
	}

	public void setNegxInternalTemperature(int negxInternalTemperature) {
		this.negxInternalTemperature = negxInternalTemperature;
	}

	public int getNegxExternalTemperature() {
		return negxExternalTemperature;
	}

	public void setNegxExternalTemperature(int negxExternalTemperature) {
		this.negxExternalTemperature = negxExternalTemperature;
	}

	public int getPosyPhotodiode() {
		return posyPhotodiode;
	}

	public void setPosyPhotodiode(int posyPhotodiode) {
		this.posyPhotodiode = posyPhotodiode;
	}

	public int getPosxPhotodiode() {
		return posxPhotodiode;
	}

	public void setPosxPhotodiode(int posxPhotodiode) {
		this.posxPhotodiode = posxPhotodiode;
	}

	public int getNegyPhotodiode() {
		return negyPhotodiode;
	}

	public void setNegyPhotodiode(int negyPhotodiode) {
		this.negyPhotodiode = negyPhotodiode;
	}

	public int getNegxPhotodiode() {
		return negxPhotodiode;
	}

	public void setNegxPhotodiode(int negxPhotodiode) {
		this.negxPhotodiode = negxPhotodiode;
	}

	public int getMzintIoe1State() {
		return mzintIoe1State;
	}

	public void setMzintIoe1State(int mzintIoe1State) {
		this.mzintIoe1State = mzintIoe1State;
	}

	public int getMzintIoe1Mode() {
		return mzintIoe1Mode;
	}

	public void setMzintIoe1Mode(int mzintIoe1Mode) {
		this.mzintIoe1Mode = mzintIoe1Mode;
	}

	public int getMzintIoe2State() {
		return mzintIoe2State;
	}

	public void setMzintIoe2State(int mzintIoe2State) {
		this.mzintIoe2State = mzintIoe2State;
	}

	public int getMzintIoe2Mode() {
		return mzintIoe2Mode;
	}

	public void setMzintIoe2Mode(int mzintIoe2Mode) {
		this.mzintIoe2Mode = mzintIoe2Mode;
	}

	public int getMarinaGpioStatusData() {
		return marinaGpioStatusData;
	}

	public void setMarinaGpioStatusData(int marinaGpioStatusData) {
		this.marinaGpioStatusData = marinaGpioStatusData;
	}

	public int getMarinaCompletedRuns() {
		return marinaCompletedRuns;
	}

	public void setMarinaCompletedRuns(int marinaCompletedRuns) {
		this.marinaCompletedRuns = marinaCompletedRuns;
	}

	public int getMarinaAbortedRuns() {
		return marinaAbortedRuns;
	}

	public void setMarinaAbortedRuns(int marinaAbortedRuns) {
		this.marinaAbortedRuns = marinaAbortedRuns;
	}

	public int getMarinaVbattVoltage() {
		return marinaVbattVoltage;
	}

	public void setMarinaVbattVoltage(int marinaVbattVoltage) {
		this.marinaVbattVoltage = marinaVbattVoltage;
	}

	public int getMarinaVbattCurrent() {
		return marinaVbattCurrent;
	}

	public void setMarinaVbattCurrent(int marinaVbattCurrent) {
		this.marinaVbattCurrent = marinaVbattCurrent;
	}

	public int getMarinaTemperature() {
		return marinaTemperature;
	}

	public void setMarinaTemperature(int marinaTemperature) {
		this.marinaTemperature = marinaTemperature;
	}

	public int getMarina2v5Voltage() {
		return marina2v5Voltage;
	}

	public void setMarina2v5Voltage(int marina2v5Voltage) {
		this.marina2v5Voltage = marina2v5Voltage;
	}

	public int getMarina1v0Voltage() {
		return marina1v0Voltage;
	}

	public void setMarina1v0Voltage(int marina1v0Voltage) {
		this.marina1v0Voltage = marina1v0Voltage;
	}

	public int getMarinaExitStatus() {
		return marinaExitStatus;
	}

	public void setMarinaExitStatus(int marinaExitStatus) {
		this.marinaExitStatus = marinaExitStatus;
	}

	public int getVariable1() {
		return variable1;
	}

	public void setVariable1(int variable1) {
		this.variable1 = variable1;
	}

	public int getVariable2() {
		return variable2;
	}

	public void setVariable2(int variable2) {
		this.variable2 = variable2;
	}

	public int getVariable3() {
		return variable3;
	}

	public void setVariable3(int variable3) {
		this.variable3 = variable3;
	}

	public int getVariable4() {
		return variable4;
	}

	public void setVariable4(int variable4) {
		this.variable4 = variable4;
	}

	public int getVariable5() {
		return variable5;
	}

	public void setVariable5(int variable5) {
		this.variable5 = variable5;
	}

	public int getVariable6() {
		return variable6;
	}

	public void setVariable6(int variable6) {
		this.variable6 = variable6;
	}

	public int getVariable7() {
		return variable7;
	}

	public void setVariable7(int variable7) {
		this.variable7 = variable7;
	}

	public int getVariable8() {
		return variable8;
	}

	public void setVariable8(int variable8) {
		this.variable8 = variable8;
	}

	public int getVariable9() {
		return variable9;
	}

	public void setVariable9(int variable9) {
		this.variable9 = variable9;
	}

	public int getVariable10() {
		return variable10;
	}

	public void setVariable10(int variable10) {
		this.variable10 = variable10;
	}

	public byte[] getUnknownPayload() {
		return unknownPayload;
	}

	public void setUnknownPayload(byte[] unknownPayload) {
		this.unknownPayload = unknownPayload;
	}

}
