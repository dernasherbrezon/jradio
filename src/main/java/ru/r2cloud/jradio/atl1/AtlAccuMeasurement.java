package ru.r2cloud.jradio.atl1;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class AtlAccuMeasurement {

	private int valid;
	private int w1bus;
	private long timestamp;
	private double batteryCurrent;
	private double[] temperatures;

	private static final double[][] IBATTERY_CALIBRATION = new double[][] { { 0.0091578069, 305.6611509242 }, { 0.0092536381, 308.7354923643 }, { 0.0092357733, 306.997781812 }, { 0.0092097441, 308.1479044269 }, };

	private static final double[][][] RESIST_CORRECTION = new double[][][] { { { -0.0006651322, -0.0122560584, 0.751697002 }, { -0.0001612352, -0.054850064, 0.4956892858 }, { 0.0005175386, -0.0151862359, 1.584156215 }, { -0.0005616397, -0.0035293076, 1.2312336719 }, { -0.0006161208, 0.0329681997, 2.3084111427 }, },
			{ { -0.0008810698, -0.0005768639, 1.4462896472 }, { 0.0002554855, -0.0497453659, 1.2655688326 }, { -0.0002372522, 0.0156908555, 2.3968181466 }, { -0.0005689415, 0.0082033445, 2.05142517512 }, { -0.000324599, 0.0315826991, 2.973868843 }, }, { { -0.0005846387, -0.0212703774, 1.6960469081 }, { -0.0005227575, -0.029222141, 0.6464118577 }, { -0.0003972915, 0.0164286754, 2.2502388179 }, { 0.0003169663, -0.0174945998, 1.7588053065 }, { -0.0004615105, 0.0385790667, 2.7849059873 }, },
			{ { -0.0006318885, -0.0047932305, 1.6301837316 }, { -0.000244109, -0.039820197, 1.3241749543 }, { -0.0005641744, 0.022177914, 2.4338965601 }, { -0.0004342717, 0.0057748108, 1.9869618945 }, { -0.0005652303, 0.0447351507, 3.0252586698 }, }, };

	public AtlAccuMeasurement() {
		// do nothing
	}

	public AtlAccuMeasurement(int index, LittleEndianDataInputStream dis) throws IOException {
		valid = dis.readUnsignedByte();
		w1bus = dis.readUnsignedByte();
		timestamp = dis.readUnsignedInt();
		batteryCurrent = convertBatteryCurrent(index, dis.readUnsignedShort());
		int[] raw = dis.readUnsignedShort(6);
		temperatures = new double[raw.length - 1];
		for (int i = 0; i < temperatures.length; i++) {
			temperatures[i] = convertTemperature(index, i, raw[0], raw[i + 1]);
		}
	}

	private static double convertTemperature(int panel, int channel, int referenceAdc, int adc) {
		double uRefp = 2.046;
		double uRefm = 0.0;
		double r0 = 100.0;
		double rRef = 72.9;
		double a = 3.9083e-3;
		double b = -5.775e-7;
		double lsb = (uRefp - uRefm) / 65536.0;

		double iTemp;
		double rTemp;
		double rT;
		double tPt100;

		iTemp = (referenceAdc * lsb) / (100.0 * rRef);
		rTemp = ((adc * lsb) / (iTemp * 100.0));
		rTemp = rTemp - ((rTemp * rTemp * RESIST_CORRECTION[panel][channel][0]) + (rTemp * RESIST_CORRECTION[panel][channel][1]) + (RESIST_CORRECTION[panel][channel][2]));
		rT = rRef + rTemp;
		tPt100 = ((-1.0 * r0 * a) + Math.sqrt((r0 * r0 * a * a) - (4.0 * r0 * b * (r0 - rT)))) / (2.0 * r0 * b);
		return tPt100;
	}

	private static double convertBatteryCurrent(int index, int rawValue) {
		double iBat;
		// Ibat = (adc*lsb - Uref2p) / (100.0 * Rshunt);
		// kompenzáció értelmezése végett az egyenletet rendezni kell
		// Ibat = adc*(lsb/(100*Rshunt)) - (Uref2p/(100*Rshunt))
		iBat = rawValue * IBATTERY_CALIBRATION[index][0] - IBATTERY_CALIBRATION[index][1];
		return iBat / 1000.0;
	}

	public int getValid() {
		return valid;
	}

	public void setValid(int valid) {
		this.valid = valid;
	}

	public int getW1bus() {
		return w1bus;
	}

	public void setW1bus(int w1bus) {
		this.w1bus = w1bus;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public double getBatteryCurrent() {
		return batteryCurrent;
	}

	public void setBatteryCurrent(double batteryCurrent) {
		this.batteryCurrent = batteryCurrent;
	}

	public double[] getTemperatures() {
		return temperatures;
	}

	public void setTemperatures(double[] temperatures) {
		this.temperatures = temperatures;
	}
	
}
