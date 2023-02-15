package ru.r2cloud.jradio;

import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class BeaconOutputStream implements Closeable {

	public static final int PROTOCOL_V2 = 1;
	private final DataOutputStream dos;

	public BeaconOutputStream(OutputStream os) {
		dos = new DataOutputStream(os);
	}

	public void write(Beacon beacon) throws IOException {
		if (beacon.getRawData() == null || beacon.getRawData().length == 0) {
			return;
		}
		dos.writeInt(0);
		dos.writeInt(PROTOCOL_V2);
		dos.writeInt(beacon.getRawData().length);
		dos.write(beacon.getRawData());
		dos.writeLong(beacon.getBeginMillis());
		dos.writeLong(beacon.getBeginSample());
		RxMetadata meta = beacon.getRxMeta();
		if (meta != null) {
			if (meta.getRssi() != null) {
				dos.writeFloat(meta.getRssi());
			} else {
				dos.writeFloat(0.0f);
			}
			if (meta.getSnr() != null) {
				dos.writeFloat(meta.getSnr());
			} else {
				dos.writeFloat(0.0f);
			}
			if (meta.getFrequencyError() != null) {
				dos.writeLong(meta.getFrequencyError());
			} else {
				dos.writeLong(0);
			}
		} else {
			dos.writeFloat(0.0f);
			dos.writeFloat(0.0f);
			dos.writeLong(0);
		}
	}

	@Override
	public void close() throws IOException {
		dos.close();
	}

}
