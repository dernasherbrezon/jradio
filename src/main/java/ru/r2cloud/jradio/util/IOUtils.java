package ru.r2cloud.jradio.util;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public class IOUtils {

	public static void readFully(InputStream is, byte b[]) throws IOException {
		int n = 0;
		while (n < b.length) {
			int count = is.read(b, n, b.length - n);
			if (count < 0) {
				throw new EOFException();
			}
			n += count;
		}
	}

}
