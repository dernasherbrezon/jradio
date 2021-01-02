package ru.r2cloud.jradio.util;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class StreamUtils {

	public static long readUnsignedInt(DataInputStream dis) throws IOException {
		return ((dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte()) & 0xFFFFFFFFL;
	}

	public static float[] readFloatArray(DataInputStream dis, int count) throws IOException {
		float[] result = new float[count];
		for (int i = 0; i < result.length; i++) {
			result[i] = Float.intBitsToFloat(dis.readInt());
		}
		return result;
	}

	public static long[] readUnsignedIntArray(DataInputStream dis, int count) throws IOException {
		long[] result = new long[count];
		for (int i = 0; i < result.length; i++) {
			result[i] = readUnsignedInt(dis);
		}
		return result;
	}
	
	public static int[] readUnsignedShortArray(DataInputStream dis, int count) throws IOException {
		int[] result = new int[count];
		for (int i = 0; i < result.length; i++) {
			result[i] = dis.readUnsignedShort();
		}
		return result;
	}

	public static int[] readUnsignedByteArray(DataInputStream dis, int count) throws IOException {
		int[] result = new int[count];
		for (int i = 0; i < result.length; i++) {
			result[i] = dis.readUnsignedByte();
		}
		return result;
	}

	public static short[] readShortArray(DataInputStream dis, int count) throws IOException {
		short[] result = new short[count];
		for (int i = 0; i < result.length; i++) {
			result[i] = dis.readShort();
		}
		return result;
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
	
	public static String readString(DataInputStream dis, int length) throws IOException {
		byte[] result = new byte[length];
		dis.readFully(result);
		return new String(result, StandardCharsets.US_ASCII).trim();
	}

	private StreamUtils() {
		// do nothing
	}
}
