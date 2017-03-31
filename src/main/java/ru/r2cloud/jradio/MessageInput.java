package ru.r2cloud.jradio;

import java.io.Closeable;
import java.io.IOException;

public interface MessageInput extends Closeable {

	byte[] readBytes() throws IOException;
	
}
