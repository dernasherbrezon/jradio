package ru.r2cloud.jradio.uvsqsat;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class TrxvutxHk {

	private float trxvutxInstantaneousRfReflectedPowerFromTransmitterPort;
	private float trxvutxInstantaneousRfForwardPowerFromTransmitterPort;
	private float trxvutxSupplyVoltage;
	private float trxvutxTotalSupplyCurrent;
	private float trxvutxTransmitterCurrent;
	private float trxvutxReceiverCurrent;
	private float trxvutxPowerAmplifierCurrent;
	private float trxvutxPowerAmplifierTemperature;
	private float trxvutxLocalOscillatorTemperature;
	private long trxvutxZeroPadding;
	private long trxvuTxUptime;
	private int trxvuTxState;

	public TrxvutxHk() {
		// do nothing
	}

	public TrxvutxHk(BitInputStream dis) throws IOException {
		int value = dis.readUnsignedInt(12);
		trxvutxInstantaneousRfReflectedPowerFromTransmitterPort = 1000.0F * value * value * 5.887F * 0.00001F;
		value = dis.readUnsignedInt(12);
		trxvutxInstantaneousRfForwardPowerFromTransmitterPort = 1000.0F * value * value * 5.887F * 0.00001F;
		trxvutxSupplyVoltage = 0.00488F * dis.readUnsignedInt(12);
		trxvutxTotalSupplyCurrent = 0.16643964F * dis.readUnsignedInt(12);
		trxvutxTransmitterCurrent = dis.readUnsignedInt(12) * 0.16643964F;
		trxvutxReceiverCurrent = dis.readUnsignedInt(12) * 0.16643964F;
		trxvutxPowerAmplifierCurrent = 1000.0F * dis.readUnsignedInt(12) * 0.16643964F;
		trxvutxPowerAmplifierTemperature = dis.readUnsignedInt(12) * -0.07669F + 195.6037F;
		trxvutxLocalOscillatorTemperature = dis.readUnsignedInt(12) * -0.07669F + 195.6037F;
		trxvutxZeroPadding = dis.readUnsignedInt(4);
		trxvuTxUptime = dis.readUnsignedLong(32);
		trxvuTxState = dis.readUnsignedByte();
	}

	public float getTrxvutxInstantaneousRfReflectedPowerFromTransmitterPort() {
		return trxvutxInstantaneousRfReflectedPowerFromTransmitterPort;
	}

	public void setTrxvutxInstantaneousRfReflectedPowerFromTransmitterPort(float trxvutxInstantaneousRfReflectedPowerFromTransmitterPort) {
		this.trxvutxInstantaneousRfReflectedPowerFromTransmitterPort = trxvutxInstantaneousRfReflectedPowerFromTransmitterPort;
	}

	public float getTrxvutxInstantaneousRfForwardPowerFromTransmitterPort() {
		return trxvutxInstantaneousRfForwardPowerFromTransmitterPort;
	}

	public void setTrxvutxInstantaneousRfForwardPowerFromTransmitterPort(float trxvutxInstantaneousRfForwardPowerFromTransmitterPort) {
		this.trxvutxInstantaneousRfForwardPowerFromTransmitterPort = trxvutxInstantaneousRfForwardPowerFromTransmitterPort;
	}

	public float getTrxvutxSupplyVoltage() {
		return trxvutxSupplyVoltage;
	}

	public void setTrxvutxSupplyVoltage(float trxvutxSupplyVoltage) {
		this.trxvutxSupplyVoltage = trxvutxSupplyVoltage;
	}

	public float getTrxvutxTotalSupplyCurrent() {
		return trxvutxTotalSupplyCurrent;
	}

	public void setTrxvutxTotalSupplyCurrent(float trxvutxTotalSupplyCurrent) {
		this.trxvutxTotalSupplyCurrent = trxvutxTotalSupplyCurrent;
	}

	public float getTrxvutxTransmitterCurrent() {
		return trxvutxTransmitterCurrent;
	}

	public void setTrxvutxTransmitterCurrent(float trxvutxTransmitterCurrent) {
		this.trxvutxTransmitterCurrent = trxvutxTransmitterCurrent;
	}

	public float getTrxvutxReceiverCurrent() {
		return trxvutxReceiverCurrent;
	}

	public void setTrxvutxReceiverCurrent(float trxvutxReceiverCurrent) {
		this.trxvutxReceiverCurrent = trxvutxReceiverCurrent;
	}

	public float getTrxvutxPowerAmplifierCurrent() {
		return trxvutxPowerAmplifierCurrent;
	}

	public void setTrxvutxPowerAmplifierCurrent(float trxvutxPowerAmplifierCurrent) {
		this.trxvutxPowerAmplifierCurrent = trxvutxPowerAmplifierCurrent;
	}

	public float getTrxvutxPowerAmplifierTemperature() {
		return trxvutxPowerAmplifierTemperature;
	}

	public void setTrxvutxPowerAmplifierTemperature(float trxvutxPowerAmplifierTemperature) {
		this.trxvutxPowerAmplifierTemperature = trxvutxPowerAmplifierTemperature;
	}

	public float getTrxvutxLocalOscillatorTemperature() {
		return trxvutxLocalOscillatorTemperature;
	}

	public void setTrxvutxLocalOscillatorTemperature(float trxvutxLocalOscillatorTemperature) {
		this.trxvutxLocalOscillatorTemperature = trxvutxLocalOscillatorTemperature;
	}

	public long getTrxvutxZeroPadding() {
		return trxvutxZeroPadding;
	}

	public void setTrxvutxZeroPadding(long trxvutxZeroPadding) {
		this.trxvutxZeroPadding = trxvutxZeroPadding;
	}

	public long getTrxvuTxUptime() {
		return trxvuTxUptime;
	}

	public void setTrxvuTxUptime(long trxvuTxUptime) {
		this.trxvuTxUptime = trxvuTxUptime;
	}

	public int getTrxvuTxState() {
		return trxvuTxState;
	}

	public void setTrxvuTxState(int trxvuTxState) {
		this.trxvuTxState = trxvuTxState;
	}

}
