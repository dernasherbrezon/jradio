package ru.r2cloud.jradio.suomi100;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class Beacon0Obc {

	private long timestamp;
	private int[] current;
	private short[] temp;

	public Beacon0Obc(DataInputStream dis) throws IOException {
		timestamp = StreamUtils.readUnsignedInt(dis);
		current = new int[6];
		for (int i = 0; i < current.length; i++) {
			current[i] = dis.readUnsignedShort();
		}
		temp = new short[2];
		temp[0] = dis.readShort();
		temp[1] = dis.readShort();
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public short[] getTemp() {
		return temp;
	}

	public void setTemp(short[] temp) {
		this.temp = temp;
	}

	public int[] getCurrent() {
		return current;
	}

	public void setCurrent(int[] current) {
		this.current = current;
	}

}
