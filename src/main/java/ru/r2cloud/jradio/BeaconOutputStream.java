package ru.r2cloud.jradio;

import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class BeaconOutputStream implements Closeable {

	private final DataOutputStream dos;

	public BeaconOutputStream(OutputStream os) {
		dos = new DataOutputStream(os);
	}

	public void write(Beacon beacon) throws IOException {
		dos.writeInt(beacon.getRawData().length);
		dos.write(beacon.getRawData());
		dos.writeLong(beacon.getBeginMillis());
		dos.writeLong(beacon.getBeginSample());
	}

	@Override
	public void close() throws IOException {
		dos.close();
	}

}
