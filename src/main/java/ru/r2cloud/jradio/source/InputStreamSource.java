package ru.r2cloud.jradio.source;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricRegistry;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.util.Metrics;

public class InputStreamSource implements FloatInput, ByteInput {

	private final MetricRegistry registry = Metrics.getRegistry();
	private final InputStream is;
	private final Counter bytes;
	private final Context context;
	private long framePos = 0;

	private long currentFloat;

	public InputStreamSource(InputStream is) {
		this(is, new Context());
	}

	public InputStreamSource(InputStream is, Context context) {
		if (!(is instanceof BufferedInputStream)) {
			this.is = new BufferedInputStream(is);
		} else {
			this.is = is;
		}
		if (registry != null) {
			bytes = registry.counter(InputStreamSource.class.getName());
		} else {
			bytes = null;
		}
		this.context = context;
		this.context.setCurrentSample(() -> framePos);
	}

	@Override
	public float readFloat() throws IOException {
		float result = Float.intBitsToFloat(readInt(is));
		if (bytes != null) {
			bytes.inc(4);
		}
		currentFloat++;
		if (currentFloat % getContext().getChannels() == 0) {
			framePos++;
		}
		return result;
	}

	@Override
	public byte readByte() throws IOException {
		int result = is.read();
		if (result < 0) {
			throw new EOFException();
		}
		if (bytes != null) {
			bytes.inc();
		}
		return (byte) result;
	}

	@Override
	public void close() throws IOException {
		is.close();
	}

	// little endian
	private static int readInt(InputStream is) throws IOException {
		int ch1 = is.read();
		int ch2 = is.read();
		int ch3 = is.read();
		int ch4 = is.read();
		if ((ch1 | ch2 | ch3 | ch4) < 0) {
			throw new EOFException();
		}
		return ((ch4 << 24) + (ch3 << 16) + (ch2 << 8) + (ch1));
	}

	@Override
	public Context getContext() {
		return context;
	}
}
