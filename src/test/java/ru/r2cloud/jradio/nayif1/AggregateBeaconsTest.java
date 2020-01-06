package ru.r2cloud.jradio.nayif1;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.BeaconInputStream;
import ru.r2cloud.jradio.jy1sat.FitterMessage;

public class AggregateBeaconsTest {

	@Test
	public void testAggregation() throws IOException {
		List<Nayif1Beacon> beacons = new ArrayList<>();
		try (BeaconInputStream<Nayif1Beacon> bis = new BeaconInputStream<>(new BufferedInputStream(AggregateBeaconsTest.class.getClassLoader().getResourceAsStream("nayif1Decoded.bin")), Nayif1Beacon.class)) {
			while (bis.hasNext()) {
				beacons.add(bis.next());
			}
		}

		AssertJson.assertObjectsEqual("Nayif1AggregationFitter.json", AggregateBeacons.readFitterMessages(beacons).toArray(new FitterMessage[0]), FitterMessage[].class);
		AssertJson.assertObjectsEqual("Nayif1AggregationHighResolution.json", AggregateBeacons.readHighResolutionData(beacons).toArray(new HighResolutionDataBatch[0]), HighResolutionDataBatch[].class);
		AssertJson.assertObjectsEqual("Nayif1AggregationWholeOrbit.json", AggregateBeacons.readWholeOrbit(beacons).toArray(new WholeOrbitDataBatch[0]), WholeOrbitDataBatch[].class);
	}

}
