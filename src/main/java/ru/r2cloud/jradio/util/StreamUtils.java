package ru.r2cloud.jradio.util;

import java.io.DataInputStream;
import java.io.IOException;

public class StreamUtils {

	public static long readUnsignedInt(DataInputStream dis) throws IOException {
		return ((dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte()) & 0xFFFFFFFFL;
	}
	
	private StreamUtils() {
		//do nothing
	}
}
