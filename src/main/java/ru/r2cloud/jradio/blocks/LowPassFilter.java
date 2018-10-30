package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.util.CircularArray;
import ru.r2cloud.jradio.util.Metrics;

public class LowPassFilter implements FloatInput {

	private final MetricRegistry registry = Metrics.getRegistry();
	private final Meter samples;

	private final FloatInput source;
	private final FIRFilter filter;
	private final CircularArray array;

	public LowPassFilter(FloatInput source, double gain, double cutoff_freq, double transition_width, Window window_type, double beta) {
		if (source.getContext().getChannels() != 1) {
			throw new IllegalArgumentException("not a float input: " + source.getContext().getChannels());
		}
		this.source = source;
		float[] taps = Firdes.lowPass(gain, source.getContext().getSampleRate(), cutoff_freq, transition_width, window_type, beta);
		this.filter = new FIRFilter(taps);
		array = new CircularArray(taps.length);
		if (registry != null) {
			samples = registry.meter(LowPassFilter.class.getName());
		} else {
			samples = null;
		}
	}

	@Override
	public float readFloat() throws IOException {
		array.add(source.readFloat());
		float result = filter.filter(array);
		if (samples != null) {
			samples.mark();
		}
		return result;
	}

	@Override
	public void close() throws IOException {
		source.close();
	}

	@Override
	public Context getContext() {
		return source.getContext();
	}
}
