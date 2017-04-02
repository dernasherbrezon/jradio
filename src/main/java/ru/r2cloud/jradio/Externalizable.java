package ru.r2cloud.jradio;

import java.io.IOException;

public interface Externalizable {

	void readExternal(byte[] data) throws IOException;
	
}
