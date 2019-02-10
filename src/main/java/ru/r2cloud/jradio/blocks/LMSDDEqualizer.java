package ru.r2cloud.jradio.blocks;

import java.io.EOFException;
import java.io.IOException;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.util.CircularComplexArray;

public class LMSDDEqualizer implements FloatInput {

	private final FloatInput source;
	private final float d_mu;
	private final int sps;
	private final Constellation constellation;
	private final float[] currentComplex = new float[2];
	private final float[] mappedPoint = new float[2];
	private final ComplexFIRFilter filter;
	private final CircularComplexArray array;
	private boolean real = true;
	private EOFException endOfStream = null;
	private int skip;

	public LMSDDEqualizer(FloatInput source, int numTaps, float gain, int sps, Constellation constellation) {
		if (source.getContext().getChannels() != 2) {
			throw new IllegalArgumentException("not a complex input: " + source.getContext().getChannels());
		}
		this.source = source;
		this.d_mu = gain;
		this.sps = sps;
		this.constellation = constellation;

		float[] tapsReal = new float[numTaps];
		float[] tapsImg = new float[numTaps];
		for (int i = 0; i < numTaps; i++) {
			tapsReal[i] = 0.0f;
			tapsImg[i] = 0.0f;
		}
		tapsReal[0] = 1.0f;
		filter = new ComplexFIRFilter(tapsReal, tapsImg);
		array = new CircularComplexArray(numTaps);
		// gnuradio reads only 1 item at the first loop. then sps items
		skip = 1;
		// simulate history buffer 1 item (or maybe sps-1?)
		array.add(0.0f, 0.0f);
	}

	@Override
	public float readFloat() throws IOException {
		float result;
		if (real) {
			if (endOfStream != null) {
				throw endOfStream;
			}
			for (int i = 0; i < skip; i++) {
				array.add(source.readFloat(), source.readFloat());
			}
			filter.filterComplex(currentComplex, array);

			constellation.mapToPoints(constellation.hardDecisionMaker(currentComplex), mappedPoint);
			float errorReal = mappedPoint[0] - currentComplex[0];
			float errorImag = mappedPoint[1] - currentComplex[1];
			// tap += d_mu*conj(in)*d_error;
			int i = 0;
			for (int j = array.getCurrentPos(); j < array.getSize(); j++, i++) {
				float real = filter.getTapsReal()[i] + d_mu * (array.getHistoryReal()[j] * errorReal + array.getHistoryImg()[j] * errorImag);
				float imag = filter.getTapsImg()[i] + d_mu * (array.getHistoryReal()[j] * errorImag - array.getHistoryImg()[j] * errorReal);
				filter.getTapsReal()[i] = real;
				filter.getTapsImg()[i] = imag;
			}
			for (int j = 0; j < array.getCurrentPos(); j++, i++) {
				float real = filter.getTapsReal()[i] + d_mu * (array.getHistoryReal()[j] * errorReal + array.getHistoryImg()[j] * errorImag);
				float imag = filter.getTapsImg()[i] + d_mu * (array.getHistoryReal()[j] * errorImag - array.getHistoryImg()[j] * errorReal);
				filter.getTapsReal()[i] = real;
				filter.getTapsImg()[i] = imag;
			}

			skip = sps;
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
