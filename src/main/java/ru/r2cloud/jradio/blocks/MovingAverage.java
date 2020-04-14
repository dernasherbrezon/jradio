package ru.r2cloud.jradio.blocks;

import ru.r2cloud.jradio.util.BoundedQueue;

class MovingAverage {

	private final BoundedQueue delayLine;
	private final int length;
	private float out;
	private float outD2;

	MovingAverage(int length) {
		this.length = length;
		delayLine = new BoundedQueue(length - 1);
		for (int i = 0; i < delayLine.getSize(); i++) {
			delayLine.add(0.0f);
		}
	}

	float filter(float x) {
		float outD1 = out;
		out = delayLine.poll();
		delayLine.add(x);
		float y = x - outD1 + outD2;
		outD2 = y;
		return y / length;
	}

	float delayedSig() {
		return out;
	}

}
