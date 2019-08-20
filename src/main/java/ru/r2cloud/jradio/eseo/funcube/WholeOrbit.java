package ru.r2cloud.jradio.eseo.funcube;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class WholeOrbit {

	private float processorTemperature;
	private float enclosureTemperature;
	private float dcdcConverterTemperature;
	private float dcdcConverterOutputCurrent;
	private float dcdcConverterOutputVoltage;
	private float transponder69VVoltage;
	private float voltage9V;
	private float voltage33V;
	private float transponder69VCurrent;
	private float current33V;
	private float current9V;

	private float transponderReceiverRSSI;
	private float commandReceiverDoppler;
	private float commandReceiverRSSI;
	private float cmdReceiverOscillatorTemperature;

	private float bpskPowerAmplifierTemperature;
	private float transmitterForwardPower;
	private float transmitterReflectedPower;
	private float bpskPowerAmplifierCurrent;
	private float fmPowerAmplifierTemperature;

	private int commandWatchdogTimeRemaining;
	private int noOfUplinkPacketsReceived;
	private int ramMemoryErrorCount;
	private int eseoMasterCANopenTransactions;
	private int amsMasterPayloadNumber;
	private int amsMasterCANopenTransactions;

	private byte pmmVoltageSp1;
	private byte pmmVoltageSp2;
	private byte pmmVoltageSp3;
	private byte pmmCurrentBp1;
	private int pmmVoltageMb;

	private int absoluteAngularRotation;

	public WholeOrbit(BitInputStream dis) throws IOException {
		processorTemperature = -0.7725984f * dis.readUnsignedByte() + 94.95152f;
		enclosureTemperature = -0.7385868f * dis.readUnsignedByte() + 97.74249f;
		dcdcConverterTemperature = -0.7796212f * dis.readUnsignedByte() + 98.19402f;
		dcdcConverterOutputCurrent = 5.131579f * dis.readUnsignedByte();
		dcdcConverterOutputVoltage = 0.100f * dis.readUnsignedByte();
		transponder69VVoltage = 0.0885753425f * dis.readUnsignedByte();
		voltage9V = 0.088171498f * dis.readUnsignedByte();
		voltage33V = 0.031141509f * dis.readUnsignedByte();
		transponder69VCurrent = (dis.readUnsignedByte() - 2) * 2.5941175f;
		current33V = 3.75f * dis.readUnsignedByte();
		current9V = 2.52778f * dis.readUnsignedByte();

		transponderReceiverRSSI = LookupTableConverter.getTransponderRSSI(dis.readUnsignedByte());
		commandReceiverDoppler = LookupTableConverter.getCommandDoppler(dis.readUnsignedByte());
		commandReceiverRSSI = LookupTableConverter.getCommandRSSI(dis.readUnsignedByte());
		cmdReceiverOscillatorTemperature = -0.8592393f * dis.readUnsignedByte() + 94.30121f;

		bpskPowerAmplifierTemperature = -0.8104347f * dis.readUnsignedByte() + 91.93637f;
		int raw = dis.readUnsignedByte();
		transmitterForwardPower = 0.0136f * raw * raw + 0.4995f * raw + 1E-12f;
		raw = dis.readUnsignedByte();
		transmitterReflectedPower = 0.0136f * raw * raw + 0.4995f * raw + 1E-12f;
		bpskPowerAmplifierCurrent = 2.18f * dis.readUnsignedByte();
		fmPowerAmplifierTemperature = -0.789929f * dis.readUnsignedByte() + 97.5934f;

		commandWatchdogTimeRemaining = dis.readUnsignedByte();
		noOfUplinkPacketsReceived = dis.readUnsignedInt(4);
		ramMemoryErrorCount = dis.readUnsignedByte();
		eseoMasterCANopenTransactions = dis.readUnsignedInt(13);
		amsMasterPayloadNumber = dis.readUnsignedInt(4);
		amsMasterCANopenTransactions = dis.readUnsignedInt(13);

		pmmVoltageSp1 = dis.readByte();
		pmmVoltageSp2 = dis.readByte();
		pmmVoltageSp3 = dis.readByte();
		pmmCurrentBp1 = dis.readByte();
		pmmVoltageMb = dis.readUnsignedShort();

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

	public float getDcdcConverterTemperature() {
		return dcdcConverterTemperature;
	}

	public void setDcdcConverterTemperature(float dcdcConverterTemperature) {
		this.dcdcConverterTemperature = dcdcConverterTemperature;
	}

	public float getDcdcConverterOutputCurrent() {
		return dcdcConverterOutputCurrent;
	}

	public void setDcdcConverterOutputCurrent(float dcdcConverterOutputCurrent) {
		this.dcdcConverterOutputCurrent = dcdcConverterOutputCurrent;
	}

	public float getDcdcConverterOutputVoltage() {
		return dcdcConverterOutputVoltage;
	}

	public void setDcdcConverterOutputVoltage(float dcdcConverterOutputVoltage) {
		this.dcdcConverterOutputVoltage = dcdcConverterOutputVoltage;
	}

	public float getTransponder69VVoltage() {
		return transponder69VVoltage;
	}

	public void setTransponder69VVoltage(float transponder69vVoltage) {
		transponder69VVoltage = transponder69vVoltage;
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
		return transponder69VCurrent;
	}

	public void setTransponder69VCurrent(float transponder69vCurrent) {
		transponder69VCurrent = transponder69vCurrent;
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

	public float getCmdReceiverOscillatorTemperature() {
		return cmdReceiverOscillatorTemperature;
	}

	public void setCmdReceiverOscillatorTemperature(float cmdReceiverOscillatorTemperature) {
		this.cmdReceiverOscillatorTemperature = cmdReceiverOscillatorTemperature;
	}

	public float getBpskPowerAmplifierTemperature() {
		return bpskPowerAmplifierTemperature;
	}

	public void setBpskPowerAmplifierTemperature(float bpskPowerAmplifierTemperature) {
		this.bpskPowerAmplifierTemperature = bpskPowerAmplifierTemperature;
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

	public float getBpskPowerAmplifierCurrent() {
		return bpskPowerAmplifierCurrent;
	}

	public void setBpskPowerAmplifierCurrent(float bpskPowerAmplifierCurrent) {
		this.bpskPowerAmplifierCurrent = bpskPowerAmplifierCurrent;
	}

	public float getFmPowerAmplifierTemperature() {
		return fmPowerAmplifierTemperature;
	}

	public void setFmPowerAmplifierTemperature(float fmPowerAmplifierTemperature) {
		this.fmPowerAmplifierTemperature = fmPowerAmplifierTemperature;
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

	public int getRamMemoryErrorCount() {
		return ramMemoryErrorCount;
	}

	public void setRamMemoryErrorCount(int ramMemoryErrorCount) {
		this.ramMemoryErrorCount = ramMemoryErrorCount;
	}

	public int getEseoMasterCANopenTransactions() {
		return eseoMasterCANopenTransactions;
	}

	public void setEseoMasterCANopenTransactions(int eseoMasterCANopenTransactions) {
		this.eseoMasterCANopenTransactions = eseoMasterCANopenTransactions;
	}

	public int getAmsMasterPayloadNumber() {
		return amsMasterPayloadNumber;
	}

	public void setAmsMasterPayloadNumber(int amsMasterPayloadNumber) {
		this.amsMasterPayloadNumber = amsMasterPayloadNumber;
	}

	public int getAmsMasterCANopenTransactions() {
		return amsMasterCANopenTransactions;
	}

	public void setAmsMasterCANopenTransactions(int amsMasterCANopenTransactions) {
		this.amsMasterCANopenTransactions = amsMasterCANopenTransactions;
	}

	public byte getPmmVoltageSp1() {
		return pmmVoltageSp1;
	}

	public void setPmmVoltageSp1(byte pmmVoltageSp1) {
		this.pmmVoltageSp1 = pmmVoltageSp1;
	}

	public byte getPmmVoltageSp2() {
		return pmmVoltageSp2;
	}

	public void setPmmVoltageSp2(byte pmmVoltageSp2) {
		this.pmmVoltageSp2 = pmmVoltageSp2;
	}

	public byte getPmmVoltageSp3() {
		return pmmVoltageSp3;
	}

	public void setPmmVoltageSp3(byte pmmVoltageSp3) {
		this.pmmVoltageSp3 = pmmVoltageSp3;
	}

	public byte getPmmCurrentBp1() {
		return pmmCurrentBp1;
	}

	public void setPmmCurrentBp1(byte pmmCurrentBp1) {
		this.pmmCurrentBp1 = pmmCurrentBp1;
	}

	public int getPmmVoltageMb() {
		return pmmVoltageMb;
	}

	public void setPmmVoltageMb(int pmmVoltageMb) {
		this.pmmVoltageMb = pmmVoltageMb;
	}

	public int getAbsoluteAngularRotation() {
		return absoluteAngularRotation;
	}

	public void setAbsoluteAngularRotation(int absoluteAngularRotation) {
		this.absoluteAngularRotation = absoluteAngularRotation;
	}

}
