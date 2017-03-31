package ru.r2cloud.jradio;

import java.io.Closeable;
import java.io.IOException;

public interface ByteInput  extends Closeable {

	byte readByte() throws IOException;
	
}
