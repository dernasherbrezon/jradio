package ru.r2cloud.jradio.mrc100;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Telemetry5 {

	private long timestamp;
	private Telemetry5Item[] items;

	public Telemetry5() {
		// do nothing
	}

	public Telemetry5(LittleEndianDataInputStream dis) throws IOException {
		timestamp = dis.readUnsignedInt();
		items = new Telemetry5Item[5];
		for (int i = 0; i < items.length; i++) {
			items[i] = new Telemetry5Item(dis);
		}
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public Telemetry5Item[] getItems() {
		return items;
	}

	public void setItems(Telemetry5Item[] items) {
		this.items = items;
	}

}
