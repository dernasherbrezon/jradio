package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.util.MathUtils;

public class CostasLoop implements FloatInput {

	private final FloatInput source;
	private final ControlLoop controlLoop;

	private float error = 0.0f;
	private final int order;
	private final boolean useSnr;

	private boolean outputReal = true;
	private final float[] currentComplex = new float[2];

	public CostasLoop(FloatInput source, float loopBw, int order, boolean useSnr) {
		if (source.getContext().getChannels() != 2) {
			throw new IllegalArgumentException("not a complex input: " + source.getContext().getChannels());
		}
		controlLoop = new ControlLoop(loopBw, 1.0f, -1.0f);
		if (useSnr || (order != 4 && order != 2)) {
			throw new IllegalArgumentException("unsupported snr: " + useSnr + " and order: " + order);
		}
		this.order = order;
		this.useSnr = useSnr;
		this.source = source;
	}

	@Override
	public float readFloat() throws IOException {
		if (outputReal) {
			float sourceReal = source.readFloat();
			float sourceImg = source.readFloat();

			float phase = -controlLoop.getPhase();
			double phaseImg = Math.sin(phase);
			float phaseReal = (float) MathUtils.fastCos(phase, phaseImg);

			MathUtils.multiply(currentComplex, sourceReal, sourceImg, phaseReal, (float)phaseImg);

			switch (order) {
			case 4:
				if (!useSnr) {
					if (currentComplex[0] > 0.0f) {
						error = 1.0f;
					} else {
						error = -1.0f;
					}
					error *= currentComplex[1];
					if (currentComplex[1] > 0.0f) {
						error -= (1.0f) * currentComplex[0];
					} else {
						error -= (-1.0f) * currentComplex[0];
					}
				}
				break;
			case 2:
				if (!useSnr) {
					error = currentComplex[0] * currentComplex[1];
				}
				break;
			default:
				throw new IllegalArgumentException("unsupported order: " + order);
			}
			error = MathUtils.branchlessClip(error, 1.0f);

			controlLoop.advanceLoop(error);

			outputReal = !outputReal;
			return currentComplex[0];
		}
		outputReal = !outputReal;
		return currentComplex[1];
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
