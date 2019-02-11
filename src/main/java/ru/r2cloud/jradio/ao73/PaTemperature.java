package ru.r2cloud.jradio.ao73;

public class PaTemperature {
	
	private static final float[] PA_TEMPS = new float[256];
	
	static {
		// Data from adc 79 to 252 measured,
        // 0-79 continues using gradient of last
        // three values, 252 to 255 likewise

        final double[][] tempToAdc = {{87.983, Double.MIN_VALUE}, {87.983, 0}, {
                /*
                 * first measured value
                 */55.3, 79}, {49.6, 91}, {45.3, 103}, {41.1, 115}, {37.6, 125}, {35.7, 129}, {33.6, 137}, {30.6, 145},
                {27.6, 154}, {25.1, 161}, {22.6, 169}, {20, 176}, {17.6, 183}, {15.1, 189}, {12.6, 196}, {10, 203},
                {7.5, 208}, {5, 214}, {2.4, 220}, {0, 224}, {-2.9, 230}, {-5, 233}, {-7.5, 237}, {-10, 241},
                {-12.3, 244}, {-15, 247}, {-20, 252}, {-22.846, 255}, {-22.846, Double.MAX_VALUE}};

        // calc values for all possible 8bit values
        for (int adc = 0; adc < 256; ++adc) {
            for (int j = 0; j < tempToAdc.length; j++) {
                if (adc != 0 && adc < tempToAdc[j][1]) {
                    final double t1 = tempToAdc[j][0];
                    final double a1 = tempToAdc[j][1];
                    final double diffa = tempToAdc[j - 1][1] - a1;
                    final double difft = tempToAdc[j - 1][0] - t1;
                    final double value = ((adc - a1) * (difft / diffa)) + t1;
                    PA_TEMPS[adc] = (float)value;
                    break;
                }
            }
        }
	}

	public static final float getPaTemp(int value) {
		return PA_TEMPS[value];
	}
	
	private PaTemperature() {
		//do nothing
	}
}
