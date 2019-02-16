package ru.r2cloud.jradio.rhw;

// see datasheet for the formulas used
// www.vishay.com/docs/29049/ntcle100.pdf
public class Ntcle100 {

	private static final int ADC_MAX_VALUE = 4095;
	private static final int TEMP_CALIB_R1_OHM = 99800;

	private static final float A_1 = 3.354016E-03f;
	private static final float B_1 = 2.460382E-04f;
	private static final float C_1 = 3.405377E-06f;
	private static final float D_1 = 1.034240E-07f;
	// R_REF in Hello World
	private static final int R_REF = 100000;

	public static float calculate(int adc) {
		if (adc == ADC_MAX_VALUE) {
			return Float.NaN;
		}
		float adcRatio = (float)adc / ADC_MAX_VALUE;
		float r2 = adcRatio * TEMP_CALIB_R1_OHM / (1 - adcRatio);
		if (r2 == 0) {
			return Float.NaN;
		}
		return resistanceToCelsius(r2);
	}

	private static float resistanceToCelsius(float resistance) {
		double temp = 1 / (A_1 + B_1 * Math.log(resistance / R_REF) + C_1 * Math.pow(Math.log(resistance / R_REF), 2) + D_1 * Math.pow(Math.log(resistance / R_REF), 3));
		return (float) temp - 273;
	}

	private Ntcle100() {
		// do nothing
	}
}
