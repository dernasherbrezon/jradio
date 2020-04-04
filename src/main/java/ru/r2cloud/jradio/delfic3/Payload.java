package ru.r2cloud.jradio.delfic3;

import java.io.IOException;

import ru.r2cloud.jradio.util.LsbBitInputStream;

public class Payload {

	private IVPoint[] zpXp;
	private IVPoint[] zpXm;
	private IVPoint[] zmYm;
	private IVPoint[] zmYp;
	private double tfscZpXpTemperature;
	private double tfscZpXmTemperature;
	private double tfscZmYmTemperature;
	private double tfscZmYpTemperature;

	private RdBlock rd;

	private AwssFrame awss;

	public Payload() {
		// do nothing
	}

	public Payload(LsbBitInputStream bis) throws IOException {
		bis.readBitsAsInt(6);
		zpXp = readCurve(bis, 8);
		zpXm = readCurve(bis, 8);
		zmYm = readCurve(bis, 8);
		zmYp = readCurve(bis, 8);

		tfscZpXpTemperature = -235.0 + bis.readBitsAsInt(8) * 1.389;
		tfscZpXmTemperature = -235.0 + bis.readBitsAsInt(8) * 1.492;
		tfscZmYmTemperature = -235.0 + bis.readBitsAsInt(8) * 1.445;
		tfscZmYpTemperature = -235.0 + bis.readBitsAsInt(8) * 1.445;
		
		rd = new RdBlock(bis);

		awss = new AwssFrame(bis);
	}

	private static IVPoint[] readCurve(LsbBitInputStream bis, int size) throws IOException {
		IVPoint[] result = new IVPoint[size];
		for (int i = 0; i < result.length; i++) {
			result[i] = new IVPoint(bis);
		}
		return result;
	}

	public IVPoint[] getZpXp() {
		return zpXp;
	}

	public void setZpXp(IVPoint[] zpXp) {
		this.zpXp = zpXp;
	}

	public IVPoint[] getZpXm() {
		return zpXm;
	}

	public void setZpXm(IVPoint[] zpXm) {
		this.zpXm = zpXm;
	}

	public IVPoint[] getZmYm() {
		return zmYm;
	}

	public void setZmYm(IVPoint[] zmYm) {
		this.zmYm = zmYm;
	}

	public IVPoint[] getZmYp() {
		return zmYp;
	}

	public void setZmYp(IVPoint[] zmYp) {
		this.zmYp = zmYp;
	}

	public double getTfscZpXpTemperature() {
		return tfscZpXpTemperature;
	}

	public void setTfscZpXpTemperature(double tfscZpXpTemperature) {
		this.tfscZpXpTemperature = tfscZpXpTemperature;
	}

	public double getTfscZpXmTemperature() {
		return tfscZpXmTemperature;
	}

	public void setTfscZpXmTemperature(double tfscZpXmTemperature) {
		this.tfscZpXmTemperature = tfscZpXmTemperature;
	}

	public double getTfscZmYmTemperature() {
		return tfscZmYmTemperature;
	}

	public void setTfscZmYmTemperature(double tfscZmYmTemperature) {
		this.tfscZmYmTemperature = tfscZmYmTemperature;
	}

	public double getTfscZmYpTemperature() {
		return tfscZmYpTemperature;
	}

	public void setTfscZmYpTemperature(double tfscZmYpTemperature) {
		this.tfscZmYpTemperature = tfscZmYpTemperature;
	}

	public RdBlock getRd() {
		return rd;
	}
	
	public void setRd(RdBlock rd) {
		this.rd = rd;
	}

	public AwssFrame getAwss() {
		return awss;
	}

	public void setAwss(AwssFrame awss) {
		this.awss = awss;
	}

}
