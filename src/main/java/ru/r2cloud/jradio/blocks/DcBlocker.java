package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.util.BoundedQueue;

public class DcBlocker implements FloatInput {

	private final FloatInput source;
	private final MovingAverage ma0;
	private final MovingAverage ma1;
	private final MovingAverage ma2;
	private final MovingAverage ma3;
	private final BoundedQueue delayLine;

	public DcBlocker(FloatInput source, int length, boolean longForm) {
		if (!longForm) {
			throw new IllegalArgumentException("short form unsupported");
		}
		this.source = new TailFloatInput(source, (length - 1) * 4);
		ma0 = new MovingAverage(length);
		ma1 = new MovingAverage(length);
		ma2 = new MovingAverage(length);
		ma3 = new MovingAverage(length);
		delayLine = new BoundedQueue(length - 1);
		for (int i = 0; i < delayLine.getSize(); i++) {
			delayLine.add(0.0f);
		}
	}

	@Override
	public float readFloat() throws IOException {
		float y1 = ma0.filter(source.readFloat());
		float y2 = ma1.filter(y1);
		float y3 = ma2.filter(y2);
		float y4 = ma3.filter(y3);

		float d = delayLine.poll();
		delayLine.add(ma0.delayedSig());
		return d - y4;
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
