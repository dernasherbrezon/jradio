package ru.r2cloud.jradio.pwsat2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class ErrorCountersFrame extends GenericFrame {

	private List<ErrorCounter> counters;

	public ErrorCountersFrame(LittleEndianDataInputStream dis) throws IOException {
		super(dis);
	}

	@Override
	public void readExternal(LittleEndianDataInputStream dis) {
		counters = new ArrayList<>();
		try {
			for (int i = 0;; i++) {
				ErrorCounter cur = new ErrorCounter();
				cur.setDevice(i);
				cur.setCurrent(dis.readUnsignedByte());
				cur.setLimit(dis.readUnsignedByte());
				cur.setIncrement(dis.readUnsignedByte());
				cur.setDecrement(dis.readUnsignedByte());
				counters.add(cur);
			}
		} catch (IOException e) {
			// do nothing
		}
	}
	
	public List<ErrorCounter> getCounters() {
		return counters;
	}
	
	public void setCounters(List<ErrorCounter> counters) {
		this.counters = counters;
	}

}
