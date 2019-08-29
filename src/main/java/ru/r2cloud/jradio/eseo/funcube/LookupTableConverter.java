package ru.r2cloud.jradio.eseo.funcube;

public class LookupTableConverter {
	
	private static final float[] TransponderRSSI_LUT;
	private static final float[] CommandRSSI_LUT;
	private static final float[] CommandDoppler_LUT;
	
	static {
		//taken from spec
		TransponderRSSI_LUT = setupLut(new double[][]{ { -120, Double.MIN_VALUE }, { -120, 108 }, { -118, 110 }, { -116, 112 }, { -115, 114 }, { -114, 116 }, { -113, 117 }, { -112, 118 }, { -111, 120 }, { -110, 122 }, { -109, 123 }, { -108, 124 }, { -107, 126 }, { -106, 128 }, { -105, 130 }, { -104, 131 }, { -103, 133 }, { -102, 134 }, { -101, 136 }, { -100, 137 }, { -99, 139 }, { -98, 140 }, { -96, 144 }, { -94, 147 }, { -92, 151 }, { -90, 154 }, { -88, 157 }, { -86, 159 }, { -84, 161 }, { -82, 163 }, { -80, 165 }, { -78, 168 }, { -76, 171 }, { -74, 174 }, { -72, 176 }, { -70, 178 }, { -68, 179 }, { -66, 180 }, { -64, 181 }, { -64, Double.MAX_VALUE } });
		CommandRSSI_LUT = setupLut(new double[][] { { -120, Double.MIN_VALUE }, { -120, 93 }, { -118, 95 }, { -117, 96 }, { -116, 98 }, { -114, 100 }, { -113, 101 }, { -112, 103 }, { -111, 104 }, { -109, 106 }, { -108, 108 }, { -107, 109 }, { -106, 110 }, { -105, 111 }, { -104, 113 }, { -103, 114 }, { -102, 116 }, { -101, 117 }, { -100, 118 }, { -99, 119 }, { -98, 121 }, { -96, 124 }, { -94, 127 }, { - 92, 130 }, { -90, 133 }, { -88, 135 }, { -86, 136 }, { -84, 138 }, { -82, 140 }, { -80, 142 }, { -78, 145 }, { -76, 147 }, { - 74, 150 }, { -72, 152 }, { -70, 153 }, { -68, 155 }, { -64, 156 }, { -64, Double.MAX_VALUE } });
		CommandDoppler_LUT = setupLut(new double[][]{ { -9.0, Double.MIN_VALUE }, { -9.0, 140 }, { -8.0, 139 }, { -7.0, 138 }, { -6.0, 136 }, { -5.0, 134 }, { -4.0, 131 }, { -3.0, 128 }, { -2.0, 124 }, { -1.0, 120 }, { 0.0, 115 }, { +1.0, 110 }, { +2.0, 105 }, { +3.0, 100 }, { +4.0, 95 }, { +5.0, 91 }, { +6.0, 87 }, { +7.0, 84 }, { +8.0, 82 }, { +9.0, 80 }, { +10, 78 }, { +11, 77 }, { +12, 76 }, { +12, Double.MAX_VALUE } });
	}

	public static final float getTransponderRSSI(int value) {
		return TransponderRSSI_LUT[value];
	}
	
	public static final float getCommandRSSI(int value) {
		return CommandRSSI_LUT[value];
	}
	
	public static final float getCommandDoppler(int value) {
		return CommandDoppler_LUT[value];
	}
	
	private static float[] setupLut(double[][] mapping) {
		float[] result = new float[256];
		// calc values for all possible 8bit values
        for (int adc = 0; adc < 256; ++adc) {
            for (int j = 0; j < mapping.length; j++) {
                if (adc != 0 && adc < mapping[j][1]) {
                    final double t1 = mapping[j][0];
                    final double a1 = mapping[j][1];
                    final double diffa = mapping[j - 1][1] - a1;
                    final double difft = mapping[j - 1][0] - t1;
                    final double value = ((adc - a1) * (difft / diffa)) + t1;
                    result[adc] = (float)value;
                    break;
                }
            }
        }
        return result;
	}
	
	private LookupTableConverter() {
		// do nothing
	}

}
