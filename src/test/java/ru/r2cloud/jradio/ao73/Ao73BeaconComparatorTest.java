package ru.r2cloud.jradio.ao73;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import ru.r2cloud.jradio.util.BitInputStream;

public class Ao73BeaconComparatorTest {

	@Test
	public void testSortAscending() {
		List<Ao73Beacon> list = new ArrayList<>();
		list.add(create(2));
		list.add(create(1));
		Collections.sort(list, Ao73BeaconComparator.INSTACE);
		assertEquals(1, list.get(0).getRealtimeTelemetry().getSequenceNumber());
		assertEquals(2, list.get(1).getRealtimeTelemetry().getSequenceNumber());
	}

	static Ao73Beacon create(int sequenceNumber) {
		RealtimeTelemetry telemetry;
		try {
			telemetry = new RealtimeTelemetry(new BitInputStream(new byte[55]));
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
		telemetry.setSequenceNumber(sequenceNumber);
		Ao73Beacon result = new Ao73Beacon();
		result.setRealtimeTelemetry(telemetry);
		return result;
	}

}
