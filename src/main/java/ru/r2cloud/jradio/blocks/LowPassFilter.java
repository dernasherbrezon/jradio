package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.FloatInput;

public class LowPassFilter implements FloatInput {
	
	private final FloatInput source;
	
	public LowPassFilter(FloatInput source) {
		this.source = source;
	}

	@Override
	public float readFloat() throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void close() throws IOException {
		source.close();
	}

}
