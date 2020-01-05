package ru.r2cloud.jradio.ao73;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.BeaconInputStream;

public class AggregateBeaconsTest {

	@Test
	public void testAggregation() throws IOException {
		List<Ao73Beacon> beacons = new ArrayList<>();
		try (BeaconInputStream<Ao73Beacon> bis = new BeaconInputStream<>(new BufferedInputStream(AggregateBeaconsTest.class.getClassLoader().getResourceAsStream("ao73Decoded.bin")), Ao73Beacon.class)) {
			while (bis.hasNext()) {
				beacons.add(bis.next());
			}
		}

		AssertJson.assertObjectsEqual("Ao73AggregationFitter.json", AggregateBeacons.readFitterMessages(beacons).toArray(new FitterMessage[0]), FitterMessage[].class);
		AssertJson.assertObjectsEqual("Ao73AggregationHighResolution.json", AggregateBeacons.readHighResolutionData(beacons).toArray(new HighResolutionDataBatch[0]), HighResolutionDataBatch[].class);
		AssertJson.assertObjectsEqual("Ao73AggregationWholeOrbit.json", AggregateBeacons.readWholeOrbit(beacons).toArray(new WholeOrbitDataBatch[0]), WholeOrbitDataBatch[].class);
	}

}
