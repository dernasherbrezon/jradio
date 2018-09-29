package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.util.Metrics;

public class LowPassFilter implements FloatInput {

	private final MetricRegistry registry = Metrics.getRegistry();
	private final Meter samples;
	private final float[] currentComplex = new float[2];

	private final FloatInput source;
	private final FIRFilter filter;
	private final float[] historyReal;
	private final float[] historyImg;
	private int historyPos;

	private boolean real = true;


	public LowPassFilter(FloatInput source, double gain, double sampling_freq, double cutoff_freq, double transition_width, Window window_type, double beta) {
		this.source = source;
		float[] taps = Firdes.lowPass(gain, sampling_freq, cutoff_freq, transition_width, window_type, beta);
		this.filter = new FIRFilter(taps);
		historyReal = new float[taps.length];
		historyImg = new float[taps.length];
		historyPos = historyImg.length - 1;
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
			historyReal[historyPos] = source.readFloat();
			historyImg[historyPos] = source.readFloat();
			filter.filterComplex(currentComplex, historyReal, historyImg, historyPos);
			historyPos--;
			if( historyPos < 0 ) {
				historyPos = historyImg.length - 1;
			}
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
