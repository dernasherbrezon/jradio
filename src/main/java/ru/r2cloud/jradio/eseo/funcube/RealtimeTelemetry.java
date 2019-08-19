package ru.r2cloud.jradio.eseo.funcube;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class RealtimeTelemetry {

	private float dcdcConverterOutputVoltage;
	private float dcdcConverterOutputCurrent;
	private float dcdcConverterTemperature;
	private float enclosureTemperature;
	private float processorTemperature;
	private float voltage33V;
	private float current33V;
	private float transponder69VVoltage;
	private float transponder69VCurrent;
	private float voltage9V;
	private float current9V;
	private float transmitterForwardPower;
	private float transmitterReflectedPower;
	private float fmPowerAmplifierTemperature;
	private float bpskPowerAmplifierTemperature;
	private float bpskPowerAmplifierCurrent;
	private float supplyCurrent33V;
	private float transponderReceiverRSSI;
	private float commandReceiverRSSI;
	private float commandReceiverDoppler;
	private float cmdReceiverOscillatorTemperature;
	private int sequenceNumber;
	private int lastCommand;
	private RFMode mode;
	private DataMode dataMode;
	private PayloadTransferStatus transferStatus;
	private boolean eseoEclipseState;
	private boolean autonomousModeState;
	private boolean ctcssDetectState;
	private boolean safeModeState;
	private boolean inSafeMode;
	private NonPayloadModeOBC nonPayloadObc;
	private PayloadModeOBC payloadObc;

	public RealtimeTelemetry(BitInputStream dis) throws IOException {
		dcdcConverterOutputVoltage = 0.100f * dis.readUnsignedByte();
		dcdcConverterOutputCurrent = 5.131579f * dis.readUnsignedByte();
		dcdcConverterTemperature = -0.7796212f * dis.readUnsignedByte() + 98.19402f;
		enclosureTemperature = -0.7385868f * dis.readUnsignedByte() + 97.74249f;
		processorTemperature = -0.7725984f * dis.readUnsignedByte() + 94.95152f;
		voltage33V = 0.031141509f * dis.readUnsignedByte();
		current33V = 3.75f * dis.readUnsignedByte();
		transponder69VVoltage = 0.0885753425f * dis.readUnsignedByte();
		transponder69VCurrent = (dis.readUnsignedByte() - 2) * 2.5941175f;
		voltage9V = 0.088171498f * dis.readUnsignedByte();
		current9V = 2.52778f * dis.readUnsignedByte();
		int raw = dis.readUnsignedByte();
		transmitterForwardPower = 0.0136f * raw * raw + 0.4995f * raw + 1E-12f;
		raw = dis.readUnsignedByte();
		transmitterReflectedPower = 0.0136f * raw * raw + 0.4995f * raw + 1E-12f;
		fmPowerAmplifierTemperature = -0.789929f * dis.readUnsignedByte() + 97.5934f;
		bpskPowerAmplifierTemperature = -0.8104347f * dis.readUnsignedByte() + 91.93637f;
		bpskPowerAmplifierCurrent = 2.18f * dis.readUnsignedByte();
		supplyCurrent33V = 0.8f * dis.readUnsignedByte();
		transponderReceiverRSSI = LookupTableConverter.getTransponderRSSI(dis.readUnsignedByte());
		commandReceiverRSSI = LookupTableConverter.getCommandRSSI(dis.readUnsignedByte());
		commandReceiverDoppler = LookupTableConverter.getCommandDoppler(dis.readUnsignedByte());
		cmdReceiverOscillatorTemperature = -0.8592393f * dis.readUnsignedByte() + 94.30121f;
		sequenceNumber = dis.readUnsignedInt(24);
		lastCommand = dis.readUnsignedByte();
		mode = RFMode.valueOfCode(dis.readUnsignedInt(3));
		dataMode = DataMode.valueOfCode(dis.readUnsignedInt(2));
		transferStatus = PayloadTransferStatus.valueOfCode(dis.readUnsignedInt(1));
		eseoEclipseState = dis.readBoolean();
		autonomousModeState = dis.readBoolean();
		ctcssDetectState = dis.readBoolean();
		safeModeState = dis.readBoolean();
		inSafeMode = dis.readBoolean();
		switch (dataMode) {
		case MODE_1K2:
			nonPayloadObc = new NonPayloadModeOBC(dis);
			break;
		case PAYLOAD_MODE_4K8:
			payloadObc = new PayloadModeOBC(dis);
			break;
		default:
			throw new IllegalArgumentException("unsupported data mode: " + dataMode);
		}
	}

	public float getDCDCConverterOutputVoltage() {
		return dcdcConverterOutputVoltage;
	}

	public void setDCDCConverterOutputVoltage(float dCDCConverterOutputVoltage) {
		dcdcConverterOutputVoltage = dCDCConverterOutputVoltage;
	}

	public float getDCDCConverterOutputCurrent() {
		return dcdcConverterOutputCurrent;
	}

	public void setDCDCConverterOutputCurrent(float dCDCConverterOutputCurrent) {
		dcdcConverterOutputCurrent = dCDCConverterOutputCurrent;
	}

	public float getDCDCConverterTemperature() {
		return dcdcConverterTemperature;
	}

	public void setDCDCConverterTemperature(float dCDCConverterTemperature) {
		dcdcConverterTemperature = dCDCConverterTemperature;
	}

	public float getEnclosureTemperature() {
		return enclosureTemperature;
	}

	public void setEnclosureTemperature(float enclosureTemperature) {
		this.enclosureTemperature = enclosureTemperature;
	}

	public float getProcessorTemperature() {
		return processorTemperature;
	}

	public void setProcessorTemperature(float processorTemperature) {
		this.processorTemperature = processorTemperature;
	}

	public float getVoltage33V() {
		return voltage33V;
	}

	public void setVoltage33V(float voltage33v) {
		voltage33V = voltage33v;
	}

	public float getCurrent33V() {
		return current33V;
	}

	public void setCurrent33V(float current33v) {
		current33V = current33v;
	}

	public float getTransponder69VVoltage() {
		return transponder69VVoltage;
	}

	public void setTransponder69VVoltage(float transponder69vVoltage) {
		transponder69VVoltage = transponder69vVoltage;
	}

	public float getTransponder69VCurrent() {
		return transponder69VCurrent;
	}

	public void setTransponder69VCurrent(float transponder69vCurrent) {
		transponder69VCurrent = transponder69vCurrent;
	}

	public float getVoltage9V() {
		return voltage9V;
	}

	public void setVoltage9V(float voltage9v) {
		voltage9V = voltage9v;
	}

	public float getCurrent9V() {
		return current9V;
	}

	public void setCurrent9V(float current9v) {
		current9V = current9v;
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

	public float getFMPowerAmplifierTemperature() {
		return fmPowerAmplifierTemperature;
	}

	public void setFMPowerAmplifierTemperature(float fMPowerAmplifierTemperature) {
		fmPowerAmplifierTemperature = fMPowerAmplifierTemperature;
	}

	public float getBPSKpowerAmplifierTemperature() {
		return bpskPowerAmplifierTemperature;
	}

	public void setBPSKpowerAmplifierTemperature(float bPSKpowerAmplifierTemperature) {
		bpskPowerAmplifierTemperature = bPSKpowerAmplifierTemperature;
	}

	public float getBPSKpowerAmplifierCurrent() {
		return bpskPowerAmplifierCurrent;
	}

	public void setBPSKpowerAmplifierCurrent(float bPSKpowerAmplifierCurrent) {
		bpskPowerAmplifierCurrent = bPSKpowerAmplifierCurrent;
	}

	public float getSupplyCurrent33V() {
		return supplyCurrent33V;
	}

	public void setSupplyCurrent33V(float supplyCurrent33V) {
		this.supplyCurrent33V = supplyCurrent33V;
	}

	public float getTransponderReceiverRSSI() {
		return transponderReceiverRSSI;
	}

	public void setTransponderReceiverRSSI(float transponderReceiverRSSI) {
		this.transponderReceiverRSSI = transponderReceiverRSSI;
	}

	public float getCommandReceiverRSSI() {
		return commandReceiverRSSI;
	}

	public void setCommandReceiverRSSI(float commandReceiverRSSI) {
		this.commandReceiverRSSI = commandReceiverRSSI;
	}

	public float getCommandReceiverDoppler() {
		return commandReceiverDoppler;
	}

	public void setCommandReceiverDoppler(float commandReceiverDoppler) {
		this.commandReceiverDoppler = commandReceiverDoppler;
	}

	public float getCMDReceiverOscillatorTemperature() {
		return cmdReceiverOscillatorTemperature;
	}

	public void setCMDReceiverOscillatorTemperature(float cMDReceiverOscillatorTemperature) {
		cmdReceiverOscillatorTemperature = cMDReceiverOscillatorTemperature;
	}

	public int getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public int getLastCommand() {
		return lastCommand;
	}

	public void setLastCommand(int lastCommand) {
		this.lastCommand = lastCommand;
	}

	public RFMode getMode() {
		return mode;
	}

	public void setMode(RFMode mode) {
		this.mode = mode;
	}

	public DataMode getDataMode() {
		return dataMode;
	}

	public void setDataMode(DataMode dataMode) {
		this.dataMode = dataMode;
	}

	public PayloadTransferStatus getTransferStatus() {
		return transferStatus;
	}

	public void setTransferStatus(PayloadTransferStatus transferStatus) {
		this.transferStatus = transferStatus;
	}

	public boolean isESEOEclipseState() {
		return eseoEclipseState;
	}

	public void setESEOEclipseState(boolean eSEOEclipseState) {
		eseoEclipseState = eSEOEclipseState;
	}

	public boolean isAutonomousModeState() {
		return autonomousModeState;
	}

	public void setAutonomousModeState(boolean autonomousModeState) {
		this.autonomousModeState = autonomousModeState;
	}

	public boolean isCTCSSDetectState() {
		return ctcssDetectState;
	}

	public void setCTCSSDetectState(boolean cTCSSDetectState) {
		ctcssDetectState = cTCSSDetectState;
	}

	public boolean isSafeModeState() {
		return safeModeState;
	}

	public void setSafeModeState(boolean safeModeState) {
		this.safeModeState = safeModeState;
	}

	public boolean isInSafeMode() {
		return inSafeMode;
	}

	public void setInSafeMode(boolean inSafeMode) {
		this.inSafeMode = inSafeMode;
	}

	public NonPayloadModeOBC getNonPayloadObc() {
		return nonPayloadObc;
	}

	public void setNonPayloadObc(NonPayloadModeOBC nonPayloadObc) {
		this.nonPayloadObc = nonPayloadObc;
	}

	public PayloadModeOBC getPayloadObc() {
		return payloadObc;
	}

	public void setPayloadObc(PayloadModeOBC payloadObc) {
		this.payloadObc = payloadObc;
	}
	
}
