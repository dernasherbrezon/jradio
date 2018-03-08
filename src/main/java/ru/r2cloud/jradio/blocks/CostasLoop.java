package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.util.MathUtils;

public class CostasLoop implements FloatInput {

	private static final double ONE_AND_HALF_PI = 3 * Math.PI / 2;
	private static final double HALF_PI = Math.PI / 2;
	private final static float M_TWOPI = (float) (2.0 * Math.PI);
	private final FloatInput source;

	private final float d_damping;
	private final float d_loop_bw;
	private final float d_alpha;
	private final float d_beta;
	private final float d_max_freq = 1.0f;
	private final float d_min_freq = -1.0f;
	private float d_phase = 0.0f;
	private float d_freq = 0.0f;
	private float d_error = 0.0f;
	private final int order;

	private boolean outputReal = true;
	private float img;

	public CostasLoop(FloatInput source, float loopBw, int order, boolean useSnr) {
		d_damping = (float) Math.sqrt(2.0) / 2.0f;
		this.d_loop_bw = loopBw;
		float denom = (float) (1.0 + 2.0 * d_damping * d_loop_bw + d_loop_bw * d_loop_bw);
		d_alpha = (4 * d_damping * d_loop_bw) / denom;
		d_beta = (4 * d_loop_bw * d_loop_bw) / denom;
		if (useSnr || order != 4) {
			throw new IllegalArgumentException("unsupported snr: " + useSnr + " and order: " + order);
		}
		this.order = order;
		this.source = source;
	}

	@Override
	public float readFloat() throws IOException {
		if (outputReal) {
			float origReal = source.readFloat();
			img = source.readFloat();

			float phaseToCalc = -d_phase;
			double sinImg = Math.sin(phaseToCalc);
			float cosReal = cos(phaseToCalc, sinImg);

			float real = origReal * cosReal - img * (float) sinImg;
			img = origReal * (float) sinImg + img * cosReal;

			switch (order) {
			case 4:
				if (real > 0.0f) {
					d_error = 1.0f;
				} else {
					d_error = -1.0f;
				}
				d_error *= img;
				if (img > 0.0f) {
					d_error -= (1.0f) * real;
				} else {
					d_error -= (-1.0f) * real;
				}
				break;
			default:
				throw new IllegalArgumentException("unsupported order: " + order);
			}
			d_error = MathUtils.branchless_clip(d_error, 1.0f);

			d_freq = d_freq + d_beta * d_error;
			d_phase = d_phase + d_freq + d_alpha * d_error;

			while (d_phase > M_TWOPI) {
				d_phase -= M_TWOPI;
			}
			while (d_phase < -M_TWOPI) {
				d_phase += M_TWOPI;
			}

			if (d_freq > d_max_freq) {
				d_freq = d_max_freq;
			} else if (d_freq < d_min_freq) {
				d_freq = d_min_freq;
			}
			outputReal = !outputReal;
			return real;
		}
		outputReal = !outputReal;
		return img;
	}

	private static float cos(float phaseToCalc, double sinImg) {
		float cosReal = (float) Math.sqrt(1 - sinImg * sinImg);
		if (phaseToCalc >= 0 && (phaseToCalc > HALF_PI) && sinImg >= 0.0) {
			cosReal = -cosReal;
		} else if (phaseToCalc >= 0 && sinImg < 0.0 && phaseToCalc < ONE_AND_HALF_PI) {
			cosReal = -cosReal;
		} else if (phaseToCalc < 0 && sinImg >= 0.0 && phaseToCalc > -ONE_AND_HALF_PI) {
			cosReal = -cosReal;
		} else if (phaseToCalc < 0 && sinImg < 0.0 && phaseToCalc < -HALF_PI) {
			cosReal = -cosReal;
		}
		return cosReal;
	}

	@Override
	public void close() throws IOException {
		source.close();
	}

}
