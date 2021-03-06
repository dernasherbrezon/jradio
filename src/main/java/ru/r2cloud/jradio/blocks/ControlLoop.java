package ru.r2cloud.jradio.blocks;

public class ControlLoop {

	private float phase;
	private float frequency;
	private float maxFrequency;
	private float minFrequency;
	private float damping;
	private float alpha;
	private float beta;

	public ControlLoop(float loopBandwidth, float maxFrequency, float minFrequency) {
		this.phase = 0.0f;
		this.frequency = 0.0f;
		this.damping = (float) (Math.sqrt(2.0f) / 2.0f);
		this.maxFrequency = maxFrequency;
		this.minFrequency = minFrequency;
		float denom = (1.0f + 2.0f * damping * loopBandwidth + loopBandwidth * loopBandwidth);
		alpha = (4 * damping * loopBandwidth) / denom;
		beta = (4 * loopBandwidth * loopBandwidth) / denom;
	}

	public void advanceLoop(float error) {
		frequency = frequency + beta * error;
		phase = phase + frequency + alpha * error;
		phaseWrap();
		frequencyLimit();
	}

	private void phaseWrap() {
		while (phase > (2 * Math.PI)) {
			phase -= (2 * Math.PI);
		}
		while (phase < -(2 * Math.PI)) {
			phase += (2 * Math.PI);
		}
	}

	private void frequencyLimit() {
		if (frequency > maxFrequency) {
			frequency = maxFrequency;
		} else if (frequency < minFrequency) {
			frequency = minFrequency;
		}
	}
	
	public float getPhase() {
		return phase;
	}
}
