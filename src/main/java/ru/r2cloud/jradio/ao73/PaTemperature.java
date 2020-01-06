package ru.r2cloud.jradio.ao73;

public class PaTemperature {

	private static final double[] PA_TEMPS = buildLookupTable(256);
	private static final double[] PA_TEMPS_1024 = buildLookupTable(1024);

	private static double[] buildLookupTable(int tableSize) {
		// Data from adc 30% to 98% measured, 0 to 30% continues using
		// gradient of last three values, 98 to 100% likewise
		double[][] tempToAdc = { { 87.983, Double.MIN_VALUE }, { 87.983, 0 }, { 55.3, 30.859 }, /* first measured value */
				{ 49.6, 35.547 }, { 45.3, 40.234 }, { 41.1, 44.922 }, { 37.6, 48.828 }, { 35.7, 50.391 }, { 33.6, 53.516 }, { 30.6, 56.641 }, { 27.6, 60.156 }, { 25.1, 62.891 }, { 22.6, 66.016 }, { 20, 68.75 }, { 17.6, 71.484 }, { 15.1, 73.828 }, { 12.6, 76.563 }, { 10, 79.297 }, { 7.5, 81.25 }, { 5, 83.594 }, { 2.4, 85.938 }, { 0, 87.5 }, { -2.9, 89.844 }, { -5, 91.016 }, { -7.5, 92.578 }, { -10, 94.141 }, { -12.3, 95.313 }, { -15, 96.484 }, { -20, 98.438 }, /* last measured value */
				{ -22.846, 100 }, { -22.846, Double.MAX_VALUE } };

		double[] result = new double[tableSize];
		// calc values for all possible 12bit values
		for (int adc = 0; adc < tableSize; ++adc) {
			double adc_percent = (100.0 / tableSize) * adc;
			for (int j = 0; j < tempToAdc.length; j++) {
				if (adc_percent < tempToAdc[j][1] && j != 0) {
					double t1 = tempToAdc[j][0];
					double a1 = tempToAdc[j][1];
					double diffa = tempToAdc[j - 1][1] - a1;
					double difft = tempToAdc[j - 1][0] - t1;
					result[adc] = ((adc_percent - a1) * (difft / diffa)) + t1;
					break;
				}
			}
		}
		return result;
	}

	public static final double getPaTemp(int value) {
		return PA_TEMPS[value];
	}
	
	public static final double getPaTemp1024(int value) {
		return PA_TEMPS_1024[value];
	}

	private PaTemperature() {
		// do nothing
	}
}
