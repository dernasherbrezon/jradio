package ru.r2cloud.jradio;

import java.io.IOException;

import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public interface Externalizable {

	void readExternal(byte[] data) throws IOException, UncorrectableException;
	
}
