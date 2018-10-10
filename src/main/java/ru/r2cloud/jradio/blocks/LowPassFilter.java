package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.util.CircularComplexArray;
import ru.r2cloud.jradio.util.Metrics;

public class LowPassFilter implements FloatInput {

	private final MetricRegistry registry = Metrics.getRegistry();
	private final Meter samples;
	private final float[] currentComplex = new float[2];

	private final FloatInput source;
	private final FIRFilter filter;
	private final CircularComplexArray array;

	private boolean real = true;


	public LowPassFilter(FloatInput source, double gain, double cutoff_freq, double transition_width, Window window_type, double beta) {
		this.source = source;
		float[] taps = Firdes.lowPass(gain, source.getContext().getSampleRate(), cutoff_freq, transition_width, window_type, beta);
		this.filter = new FIRFilter(taps);
		array = new CircularComplexArray(taps.length);
		if (registry != null) {
			samples = registry.meter(LowPassFilter.class.getName());
		} else {
			samples = null;
		}
	}

	@Override
	public float readFloat() throws IOException {
		float result;
		if (real) {
			array.add(source.readFloat(), source.readFloat());
			filter.filterComplex(currentComplex, array.getHistoryReal(), array.getHistoryImg(), array.getCurrentPos());
			if (samples != null) {
				samples.mark();
			}
			result = currentComplex[0];
		} else {
			result = currentComplex[1];
		}
		real = !real;
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
