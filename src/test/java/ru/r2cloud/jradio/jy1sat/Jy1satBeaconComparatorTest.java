package ru.r2cloud.jradio.jy1sat;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import ru.r2cloud.jradio.util.BitInputStream;

public class Jy1satBeaconComparatorTest {

	@Test
	public void testSortAscending() {
		List<Jy1satBeacon> list = new ArrayList<>();
		list.add(create(2));
		list.add(create(1));
		Collections.sort(list, Jy1satBeaconComparator.INSTACE);
		assertEquals(1, list.get(0).getRealtimeTelemetry().getSequenceNumber());
		assertEquals(2, list.get(1).getRealtimeTelemetry().getSequenceNumber());
	}

	static Jy1satBeacon create(int sequenceNumber) {
		RealtimeTelemetry telemetry;
		try {
			telemetry = new RealtimeTelemetry(new BitInputStream(new byte[54]));
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
		telemetry.setSequenceNumber(sequenceNumber);
		Jy1satBeacon result = new Jy1satBeacon();
		result.setRealtimeTelemetry(telemetry);
		return result;
	}

}
