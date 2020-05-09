package ru.r2cloud.jradio.fox;

import java.io.IOException;

import ru.r2cloud.jradio.util.LsbBitInputStream;

public class PayloadRealtime extends PayloadData {

	private IhuDiagnostic ihuDiagnosticData;

	private boolean experiment1Failure;
	private boolean experiment2Failure;
	private boolean experiment3Failure;
	private boolean experiment4Failure;

	private boolean battI2CFailure;
	private boolean psu1I2CFailure;
	private boolean psu2I2CFailure;

	private int numberOfGroundCommandedTlmResets;
	private boolean rxAntenna;
	private boolean txAntenna;

	public PayloadRealtime() {
		// do nothing
	}

	public PayloadRealtime(LsbBitInputStream dis) throws IOException {
		super(dis);
		ihuDiagnosticData = new IhuDiagnostic(dis.readBitsAsInt(32));

		experiment1Failure = dis.readBit();
		experiment2Failure = dis.readBit();
		experiment3Failure = dis.readBit();
		experiment4Failure = dis.readBit();
		battI2CFailure = dis.readBit();
		psu1I2CFailure = dis.readBit();
		psu2I2CFailure = dis.readBit();

		numberOfGroundCommandedTlmResets = dis.readBitsAsInt(4);

		rxAntenna = dis.readBit();
		txAntenna = dis.readBit();

		dis.readBitsAsInt(51);
	}

	public IhuDiagnostic getIhuDiagnosticData() {
		return ihuDiagnosticData;
	}

	public void setIhuDiagnosticData(IhuDiagnostic ihuDiagnosticData) {
		this.ihuDiagnosticData = ihuDiagnosticData;
	}

	public boolean isExperiment1Failure() {
		return experiment1Failure;
	}

	public void setExperiment1Failure(boolean experiment1Failure) {
		this.experiment1Failure = experiment1Failure;
	}

	public boolean isExperiment2Failure() {
		return experiment2Failure;
	}

	public void setExperiment2Failure(boolean experiment2Failure) {
		this.experiment2Failure = experiment2Failure;
	}

	public boolean isExperiment3Failure() {
		return experiment3Failure;
	}

	public void setExperiment3Failure(boolean experiment3Failure) {
		this.experiment3Failure = experiment3Failure;
	}

	public boolean isExperiment4Failure() {
		return experiment4Failure;
	}

	public void setExperiment4Failure(boolean experiment4Failure) {
		this.experiment4Failure = experiment4Failure;
	}

	public boolean isBattI2CFailure() {
		return battI2CFailure;
	}

	public void setBattI2CFailure(boolean battI2CFailure) {
		this.battI2CFailure = battI2CFailure;
	}

	public boolean isPsu1I2CFailure() {
		return psu1I2CFailure;
	}

	public void setPsu1I2CFailure(boolean psu1i2cFailure) {
		psu1I2CFailure = psu1i2cFailure;
	}

	public boolean isPsu2I2CFailure() {
		return psu2I2CFailure;
	}

	public void setPsu2I2CFailure(boolean psu2i2cFailure) {
		psu2I2CFailure = psu2i2cFailure;
	}

	public int getNumberOfGroundCommandedTlmResets() {
		return numberOfGroundCommandedTlmResets;
	}

	public void setNumberOfGroundCommandedTlmResets(int numberOfGroundCommandedTlmResets) {
		this.numberOfGroundCommandedTlmResets = numberOfGroundCommandedTlmResets;
	}

	public boolean isRxAntenna() {
		return rxAntenna;
	}

	public void setRxAntenna(boolean rxAntenna) {
		this.rxAntenna = rxAntenna;
	}

	public boolean isTxAntenna() {
		return txAntenna;
	}

	public void setTxAntenna(boolean txAntenna) {
		this.txAntenna = txAntenna;
	}

}
