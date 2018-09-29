package ru.r2cloud.jradio;

import java.io.Closeable;
import java.io.IOException;

public interface FloatInput extends Closeable {

	float readFloat() throws IOException;
	
	Context getContext();
	
}
