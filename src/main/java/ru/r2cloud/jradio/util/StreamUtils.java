package ru.r2cloud.jradio.util;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class StreamUtils {

	public static long readUnsignedInt(DataInputStream dis) throws IOException {
		return ((dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte()) & 0xFFFFFFFFL;
	}

	public static byte[] toByteArray(LittleEndianDataInputStream is) throws IOException {
		byte[] buffer = new byte[64];
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int n = 0;
		while (-1 != (n = is.read(buffer))) {
			baos.write(buffer, 0, n);
		}
		return baos.toByteArray();
	}
	
	public static byte[] toByteArray(DataInputStream is) throws IOException {
		byte[] buffer = new byte[64];
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int n = 0;
		while (-1 != (n = is.read(buffer))) {
			baos.write(buffer, 0, n);
		}
		return baos.toByteArray();
	}

	private StreamUtils() {
		// do nothing
	}
}
