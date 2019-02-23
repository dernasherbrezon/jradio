package ru.r2cloud.jradio.util;

public class LittleEndianUtils {
	
	// convert from big-endian to little-endian
	public static long convertUnsignedInt(long value) {
		long ch1 = (value & 0xFF000000) >> 24;
		long ch2 = (value & 0x00FF0000) >> 16;
		long ch3 = (value & 0x0000FF00) >> 8;
		long ch4 = (value & 0x000000FF);
		return (ch4 << 24) | (ch3 << 16) | (ch2 << 8) | ch1;
	}
	
	private LittleEndianUtils() {
		//do nothing
	}

}
