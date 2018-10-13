package ru.r2cloud.jradio.blocks;

import ru.r2cloud.jradio.util.MathUtils;

public class Rotator {

	private int counter;
	private final float[] phase;
	private final float[] phaseIncrement;

	private final float[] tempPhase = new float[2];

	public Rotator(float[] phase, float[] phaseIncrement) {
		this.phase = phase;
		this.phaseIncrement = phaseIncrement;

		float abs = MathUtils.abs(phase[0], phase[1]);
		this.phase[0] = phase[0] / abs;
		this.phase[1] = phase[1] / abs;

		abs = MathUtils.abs(phaseIncrement[0], phaseIncrement[1]);
		this.phaseIncrement[0] = phaseIncrement[0] / abs;
		this.phaseIncrement[1] = phaseIncrement[1] / abs;
	}

	public void rotate(float[] result, float[] in) {
		counter++;

		MathUtils.multiply(result, in[0], in[1], phase[0], phase[1]);

		MathUtils.multiply(tempPhase, phase[0], phase[1], phaseIncrement[0], phaseIncrement[1]);

		phase[0] = tempPhase[0];
		phase[1] = tempPhase[1];

		if ((counter % 512) == 0) {
			float abs = MathUtils.abs(phase[0], phase[1]);
			phase[0] = phase[0] / abs;
			phase[1] = phase[1] / abs;
		}

	}

}
