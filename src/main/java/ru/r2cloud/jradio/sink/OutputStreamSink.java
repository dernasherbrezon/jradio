package ru.r2cloud.jradio.sink;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;

import ru.r2cloud.jradio.FloatInput;

public class OutputStreamSink implements Closeable {

	private final FloatInput source;

	public OutputStreamSink(FloatInput source) {
		this.source = source;
	}

	public void process(OutputStream out) throws IOException {
		while (true) {
			try {
				int v = Float.floatToIntBits(source.readFloat());
				out.write((v >>> 0) & 0xFF);
				out.write((v >>> 8) & 0xFF);
				out.write((v >>> 16) & 0xFF);
				out.write((v >>> 24) & 0xFF);
			} catch (EOFException e) {
				out.flush();
				break;
			}
		}
	}

	@Override
	public void close() throws IOException {
		source.close();
	}

}
