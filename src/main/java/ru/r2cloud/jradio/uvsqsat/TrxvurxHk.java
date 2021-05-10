package ru.r2cloud.jradio.uvsqsat;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class TrxvurxHk {

	private float trxvurxInstantaneousReceivedSignalDopplerOffsetAtTheReceiverPort;
	private float trxvurxInstantaneousReceivedSignalStrengthAtTheReceiverPort;
	private float trxvurxSupplyVoltage;
	private float trxvurxTotalSupplyCurrent;
	private float trxvurxTransmitterCurrent;
	private float trxvurxReceiverCurrent;
	private float trxvurxPowerAmplifierCurrent;
	private float trxvurxPowerAmplifierTemperature;
	private float trxvurxLocalOscillatorTemperature;
	private long trxvurxZeroPadding;
	private long trxvuRxUptime;

	public TrxvurxHk() {
		// do nothing
	}

	public TrxvurxHk(BitInputStream dis) throws IOException {
		trxvurxInstantaneousReceivedSignalDopplerOffsetAtTheReceiverPort = (dis.readUnsignedInt(12) * 13.552F) - 22300;
		trxvurxInstantaneousReceivedSignalStrengthAtTheReceiverPort = dis.readUnsignedInt(12) * 0.03F - 152;
		trxvurxSupplyVoltage = dis.readUnsignedInt(12) * 0.00488F;
		trxvurxTotalSupplyCurrent = dis.readUnsignedInt(12) * 0.16643964F;
		trxvurxTransmitterCurrent = dis.readUnsignedInt(12) * 0.16643964F;
		trxvurxReceiverCurrent = dis.readUnsignedInt(12) * 0.16643964F;
		trxvurxPowerAmplifierCurrent = 1000.0F * dis.readUnsignedInt(12) * 0.16643964F;
		trxvurxPowerAmplifierTemperature = dis.readUnsignedInt(12) * -0.07669F + 195.6037F;
		trxvurxLocalOscillatorTemperature = dis.readUnsignedInt(12) * -0.07669F + 195.6037F;
		trxvurxZeroPadding = dis.readUnsignedInt(4);
		trxvuRxUptime = dis.readUnsignedInt(32);
	}

	public float getTrxvurxInstantaneousReceivedSignalDopplerOffsetAtTheReceiverPort() {
		return trxvurxInstantaneousReceivedSignalDopplerOffsetAtTheReceiverPort;
	}

	public void setTrxvurxInstantaneousReceivedSignalDopplerOffsetAtTheReceiverPort(float trxvurxInstantaneousReceivedSignalDopplerOffsetAtTheReceiverPort) {
		this.trxvurxInstantaneousReceivedSignalDopplerOffsetAtTheReceiverPort = trxvurxInstantaneousReceivedSignalDopplerOffsetAtTheReceiverPort;
	}

	public float getTrxvurxInstantaneousReceivedSignalStrengthAtTheReceiverPort() {
		return trxvurxInstantaneousReceivedSignalStrengthAtTheReceiverPort;
	}

	public void setTrxvurxInstantaneousReceivedSignalStrengthAtTheReceiverPort(float trxvurxInstantaneousReceivedSignalStrengthAtTheReceiverPort) {
		this.trxvurxInstantaneousReceivedSignalStrengthAtTheReceiverPort = trxvurxInstantaneousReceivedSignalStrengthAtTheReceiverPort;
	}

	public float getTrxvurxSupplyVoltage() {
		return trxvurxSupplyVoltage;
	}

	public void setTrxvurxSupplyVoltage(float trxvurxSupplyVoltage) {
		this.trxvurxSupplyVoltage = trxvurxSupplyVoltage;
	}

	public float getTrxvurxTotalSupplyCurrent() {
		return trxvurxTotalSupplyCurrent;
	}

	public void setTrxvurxTotalSupplyCurrent(float trxvurxTotalSupplyCurrent) {
		this.trxvurxTotalSupplyCurrent = trxvurxTotalSupplyCurrent;
	}

	public float getTrxvurxTransmitterCurrent() {
		return trxvurxTransmitterCurrent;
	}

	public void setTrxvurxTransmitterCurrent(float trxvurxTransmitterCurrent) {
		this.trxvurxTransmitterCurrent = trxvurxTransmitterCurrent;
	}

	public float getTrxvurxReceiverCurrent() {
		return trxvurxReceiverCurrent;
	}

	public void setTrxvurxReceiverCurrent(float trxvurxReceiverCurrent) {
		this.trxvurxReceiverCurrent = trxvurxReceiverCurrent;
	}

	public float getTrxvurxPowerAmplifierCurrent() {
		return trxvurxPowerAmplifierCurrent;
	}

	public void setTrxvurxPowerAmplifierCurrent(float trxvurxPowerAmplifierCurrent) {
		this.trxvurxPowerAmplifierCurrent = trxvurxPowerAmplifierCurrent;
	}

	public float getTrxvurxPowerAmplifierTemperature() {
		return trxvurxPowerAmplifierTemperature;
	}

	public void setTrxvurxPowerAmplifierTemperature(float trxvurxPowerAmplifierTemperature) {
		this.trxvurxPowerAmplifierTemperature = trxvurxPowerAmplifierTemperature;
	}

	public float getTrxvurxLocalOscillatorTemperature() {
		return trxvurxLocalOscillatorTemperature;
	}

	public void setTrxvurxLocalOscillatorTemperature(float trxvurxLocalOscillatorTemperature) {
		this.trxvurxLocalOscillatorTemperature = trxvurxLocalOscillatorTemperature;
	}

	public long getTrxvurxZeroPadding() {
		return trxvurxZeroPadding;
	}

	public void setTrxvurxZeroPadding(long trxvurxZeroPadding) {
		this.trxvurxZeroPadding = trxvurxZeroPadding;
	}

	public long getTrxvuRxUptime() {
		return trxvuRxUptime;
	}

	public void setTrxvuRxUptime(long trxvuRxUptime) {
		this.trxvuRxUptime = trxvuRxUptime;
	}

}
