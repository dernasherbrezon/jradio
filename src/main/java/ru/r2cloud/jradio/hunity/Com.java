package ru.r2cloud.jradio.hunity;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Com {

	private SampleTime modeTime;
	private int mode;

	private SampleTime txDataRateTime;
	private DataRate txDataRate;

	private SampleTime txPowerTime;
	private int txPower;

	private SampleTime rxDataRateTime;
	private DataRate rxDataRate;

	public Com() {
		// do nothing
	}

	public Com(LittleEndianDataInputStream dis) throws IOException {
		modeTime = SampleTime.valueOfByte(dis.readUnsignedByte());
		mode = dis.readUnsignedByte();

		txDataRateTime = SampleTime.valueOfByte(dis.readUnsignedByte());
		txDataRate = DataRate.valueOfCode(dis.readByte());

		txPowerTime = SampleTime.valueOfByte(dis.readUnsignedByte());
		txPower = dis.readUnsignedByte();

		rxDataRateTime = SampleTime.valueOfByte(dis.readUnsignedByte());
		rxDataRate = DataRate.valueOfCode(dis.readByte());
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public SampleTime getModeTime() {
		return modeTime;
	}

	public void setModeTime(SampleTime modeTime) {
		this.modeTime = modeTime;
	}

	public DataRate getTxDataRate() {
		return txDataRate;
	}

	public void setTxDataRate(DataRate txDataRate) {
		this.txDataRate = txDataRate;
	}

	public SampleTime getTxDataRateTime() {
		return txDataRateTime;
	}

	public void setTxDataRateTime(SampleTime txDataRateTime) {
		this.txDataRateTime = txDataRateTime;
	}

	public int getTxPower() {
		return txPower;
	}

	public void setTxPower(int txPower) {
		this.txPower = txPower;
	}

	public SampleTime getTxPowerTime() {
		return txPowerTime;
	}

	public void setTxPowerTime(SampleTime txPowerTime) {
		this.txPowerTime = txPowerTime;
	}

	public DataRate getRxDataRate() {
		return rxDataRate;
	}

	public void setRxDataRate(DataRate rxDataRate) {
		this.rxDataRate = rxDataRate;
	}

	public SampleTime getRxDataRateTime() {
		return rxDataRateTime;
	}

	public void setRxDataRateTime(SampleTime rxDataRateTime) {
		this.rxDataRateTime = rxDataRateTime;
	}

}
