package ru.r2cloud.jradio.eseo.funcube;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class WholeOrbit {

	private float processorTemperature;
	private float enclosureTemperature;
	private float DCDCConverterTemperature;
	private float DCDCConverterOutputCurrent;
	private float DCDCConverterOutputVoltage;
	private float Transponder69VVoltage;
	private float voltage9V;
	private float voltage33V;
	private float Transponder69VCurrent;
	private float current33V;
	private float current9V;

	private float transponderReceiverRSSI;
	private float commandReceiverDoppler;
	private float commandReceiverRSSI;
	private float CMDReceiverOscillatorTemperature;

	private float BPSKpowerAmplifierTemperature;
	private float transmitterForwardPower;
	private float transmitterReflectedPower;
	private float BPSKpowerAmplifierCurrent;
	private float FMPowerAmplifierTemperature;

	private int commandWatchdogTimeRemaining;
	private int noOfUplinkPacketsReceived;
	private int RAMMemoryErrorCount;
	private int ESEOMasterCANopenTransactions;
	private int AMSMasterPayloadNumber;
	private int AMSMasterCANopenTransactions;

	private byte PmmVoltageSp1;
	private byte PmmVoltageSp2;
	private byte PmmVoltageSp3;
	private byte PMMCurrentBp1;
	private int PMMVoltageMb;

	private int absoluteAngularRotation;

	public WholeOrbit(BitInputStream dis) throws IOException {
		processorTemperature = -0.7725984f * dis.readUnsignedByte() + 94.95152f;
		enclosureTemperature = -0.7385868f * dis.readUnsignedByte() + 97.74249f;
		DCDCConverterTemperature = -0.7796212f * dis.readUnsignedByte() + 98.19402f;
		DCDCConverterOutputCurrent = 5.131579f * dis.readUnsignedByte();
		DCDCConverterOutputVoltage = 0.100f * dis.readUnsignedByte();
		Transponder69VVoltage = 0.0885753425f * dis.readUnsignedByte();
		voltage9V = 0.088171498f * dis.readUnsignedByte();
		voltage33V = 0.031141509f * dis.readUnsignedByte();
		Transponder69VCurrent = (dis.readUnsignedByte() - 2) * 2.5941175f;
		current33V = 3.75f * dis.readUnsignedByte();
		current9V = 2.52778f * dis.readUnsignedByte();

		transponderReceiverRSSI = LookupTableConverter.getTransponderRSSI(dis.readUnsignedByte());
		commandReceiverDoppler = LookupTableConverter.getCommandDoppler(dis.readUnsignedByte());
		commandReceiverRSSI = LookupTableConverter.getCommandRSSI(dis.readUnsignedByte());
		CMDReceiverOscillatorTemperature = -0.8592393f * dis.readUnsignedByte() + 94.30121f;

		BPSKpowerAmplifierTemperature = -0.8104347f * dis.readUnsignedByte() + 91.93637f;
		int raw = dis.readUnsignedByte();
		transmitterForwardPower = 0.0136f * raw * raw + 0.4995f * raw + 1E-12f;
		raw = dis.readUnsignedByte();
		transmitterReflectedPower = 0.0136f * raw * raw + 0.4995f * raw + 1E-12f;
		BPSKpowerAmplifierCurrent = 2.18f * dis.readUnsignedByte();
		FMPowerAmplifierTemperature = -0.789929f * dis.readUnsignedByte() + 97.5934f;

		commandWatchdogTimeRemaining = dis.readUnsignedByte();
		noOfUplinkPacketsReceived = dis.readUnsignedInt(4);
		RAMMemoryErrorCount = dis.readUnsignedByte();
		ESEOMasterCANopenTransactions = dis.readUnsignedInt(13);
		AMSMasterPayloadNumber = dis.readUnsignedInt(4);
		AMSMasterCANopenTransactions = dis.readUnsignedInt(13);

		PmmVoltageSp1 = dis.readByte();
		PmmVoltageSp2 = dis.readByte();
		PmmVoltageSp3 = dis.readByte();
		PMMCurrentBp1 = dis.readByte();
		PMMVoltageMb = dis.readUnsignedShort();

		absoluteAngularRotation = dis.readInt();
	}

	public float getProcessorTemperature() {
		return processorTemperature;
	}

	public void setProcessorTemperature(float processorTemperature) {
		this.processorTemperature = processorTemperature;
	}

	public float getEnclosureTemperature() {
		return enclosureTemperature;
	}

	public void setEnclosureTemperature(float enclosureTemperature) {
		this.enclosureTemperature = enclosureTemperature;
	}

	public float getDCDCConverterTemperature() {
		return DCDCConverterTemperature;
	}

	public void setDCDCConverterTemperature(float dCDCConverterTemperature) {
		DCDCConverterTemperature = dCDCConverterTemperature;
	}

	public float getDCDCConverterOutputCurrent() {
		return DCDCConverterOutputCurrent;
	}

	public void setDCDCConverterOutputCurrent(float dCDCConverterOutputCurrent) {
		DCDCConverterOutputCurrent = dCDCConverterOutputCurrent;
	}

	public float getDCDCConverterOutputVoltage() {
		return DCDCConverterOutputVoltage;
	}

	public void setDCDCConverterOutputVoltage(float dCDCConverterOutputVoltage) {
		DCDCConverterOutputVoltage = dCDCConverterOutputVoltage;
	}

	public float getTransponder69VVoltage() {
		return Transponder69VVoltage;
	}

	public void setTransponder69VVoltage(float transponder69vVoltage) {
		Transponder69VVoltage = transponder69vVoltage;
	}

	public float getVoltage9V() {
		return voltage9V;
	}

	public void setVoltage9V(float voltage9v) {
		voltage9V = voltage9v;
	}

	public float getVoltage33V() {
		return voltage33V;
	}

	public void setVoltage33V(float voltage33v) {
		voltage33V = voltage33v;
	}

	public float getTransponder69VCurrent() {
		return Transponder69VCurrent;
	}

	public void setTransponder69VCurrent(float transponder69vCurrent) {
		Transponder69VCurrent = transponder69vCurrent;
	}

	public float getCurrent33V() {
		return current33V;
	}

	public void setCurrent33V(float current33v) {
		current33V = current33v;
	}

	public float getCurrent9V() {
		return current9V;
	}

	public void setCurrent9V(float current9v) {
		current9V = current9v;
	}

	public float getTransponderReceiverRSSI() {
		return transponderReceiverRSSI;
	}

	public void setTransponderReceiverRSSI(float transponderReceiverRSSI) {
		this.transponderReceiverRSSI = transponderReceiverRSSI;
	}

	public float getCommandReceiverDoppler() {
		return commandReceiverDoppler;
	}

	public void setCommandReceiverDoppler(float commandReceiverDoppler) {
		this.commandReceiverDoppler = commandReceiverDoppler;
	}

	public float getCommandReceiverRSSI() {
		return commandReceiverRSSI;
	}

	public void setCommandReceiverRSSI(float commandReceiverRSSI) {
		this.commandReceiverRSSI = commandReceiverRSSI;
	}

	public float getCMDReceiverOscillatorTemperature() {
		return CMDReceiverOscillatorTemperature;
	}

	public void setCMDReceiverOscillatorTemperature(float cMDReceiverOscillatorTemperature) {
		CMDReceiverOscillatorTemperature = cMDReceiverOscillatorTemperature;
	}

	public float getBPSKpowerAmplifierTemperature() {
		return BPSKpowerAmplifierTemperature;
	}

	public void setBPSKpowerAmplifierTemperature(float bPSKpowerAmplifierTemperature) {
		BPSKpowerAmplifierTemperature = bPSKpowerAmplifierTemperature;
	}

	public float getTransmitterForwardPower() {
		return transmitterForwardPower;
	}

	public void setTransmitterForwardPower(float transmitterForwardPower) {
		this.transmitterForwardPower = transmitterForwardPower;
	}

	public float getTransmitterReflectedPower() {
		return transmitterReflectedPower;
	}

	public void setTransmitterReflectedPower(float transmitterReflectedPower) {
		this.transmitterReflectedPower = transmitterReflectedPower;
	}

	public float getBPSKpowerAmplifierCurrent() {
		return BPSKpowerAmplifierCurrent;
	}

	public void setBPSKpowerAmplifierCurrent(float bPSKpowerAmplifierCurrent) {
		BPSKpowerAmplifierCurrent = bPSKpowerAmplifierCurrent;
	}

	public float getFMPowerAmplifierTemperature() {
		return FMPowerAmplifierTemperature;
	}

	public void setFMPowerAmplifierTemperature(float fMPowerAmplifierTemperature) {
		FMPowerAmplifierTemperature = fMPowerAmplifierTemperature;
	}

	public int getCommandWatchdogTimeRemaining() {
		return commandWatchdogTimeRemaining;
	}

	public void setCommandWatchdogTimeRemaining(int commandWatchdogTimeRemaining) {
		this.commandWatchdogTimeRemaining = commandWatchdogTimeRemaining;
	}

	public int getNoOfUplinkPacketsReceived() {
		return noOfUplinkPacketsReceived;
	}

	public void setNoOfUplinkPacketsReceived(int noOfUplinkPacketsReceived) {
		this.noOfUplinkPacketsReceived = noOfUplinkPacketsReceived;
	}

	public int getRAMMemoryErrorCount() {
		return RAMMemoryErrorCount;
	}

	public void setRAMMemoryErrorCount(int rAMMemoryErrorCount) {
		RAMMemoryErrorCount = rAMMemoryErrorCount;
	}

	public int getESEOMasterCANopenTransactions() {
		return ESEOMasterCANopenTransactions;
	}

	public void setESEOMasterCANopenTransactions(int eSEOMasterCANopenTransactions) {
		ESEOMasterCANopenTransactions = eSEOMasterCANopenTransactions;
	}

	public int getAMSMasterPayloadNumber() {
		return AMSMasterPayloadNumber;
	}

	public void setAMSMasterPayloadNumber(int aMSMasterPayloadNumber) {
		AMSMasterPayloadNumber = aMSMasterPayloadNumber;
	}

	public int getAMSMasterCANopenTransactions() {
		return AMSMasterCANopenTransactions;
	}

	public void setAMSMasterCANopenTransactions(int aMSMasterCANopenTransactions) {
		AMSMasterCANopenTransactions = aMSMasterCANopenTransactions;
	}

	public byte getPmmVoltageSp1() {
		return PmmVoltageSp1;
	}

	public void setPmmVoltageSp1(byte pmmVoltageSp1) {
		PmmVoltageSp1 = pmmVoltageSp1;
	}

	public byte getPmmVoltageSp2() {
		return PmmVoltageSp2;
	}

	public void setPmmVoltageSp2(byte pmmVoltageSp2) {
		PmmVoltageSp2 = pmmVoltageSp2;
	}

	public byte getPmmVoltageSp3() {
		return PmmVoltageSp3;
	}

	public void setPmmVoltageSp3(byte pmmVoltageSp3) {
		PmmVoltageSp3 = pmmVoltageSp3;
	}

	public byte getPMMCurrentBp1() {
		return PMMCurrentBp1;
	}

	public void setPMMCurrentBp1(byte pMMCurrentBp1) {
		PMMCurrentBp1 = pMMCurrentBp1;
	}

	public int getPMMVoltageMb() {
		return PMMVoltageMb;
	}

	public void setPMMVoltageMb(int pMMVoltageMb) {
		PMMVoltageMb = pMMVoltageMb;
	}

	public int getAbsoluteAngularRotation() {
		return absoluteAngularRotation;
	}

	public void setAbsoluteAngularRotation(int absoluteAngularRotation) {
		this.absoluteAngularRotation = absoluteAngularRotation;
	}

}
