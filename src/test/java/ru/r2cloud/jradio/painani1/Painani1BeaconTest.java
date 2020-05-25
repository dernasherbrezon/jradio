package ru.r2cloud.jradio.painani1;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.Test;

import pl.pojo.tester.api.assertion.Method;
import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class Painani1BeaconTest {
	
	@Test
	public void testBeacon() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("8A82A4A89040E0A6A082868A406103F03CC33584000000000000000000002A7C000000CDFFF300070100870004170028270FDF0000FFD40000000CBD0F44009B136904A60F5E0472");
		Painani1Beacon result = new Painani1Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Painani1Beacon.json", result);
	}

	@Test
	public void testShortBeacon() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("8A82A4A89040E0A6A082868A406103F0000005A2FEFE509603510000050C");
		Painani1Beacon result = new Painani1Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Painani1BeaconShort.json", result);
	}
	
	@Test
	public void testPojo() {
		assertPojoMethodsFor(Painani1Beacon.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(Telemetry.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(ShortTelemetry.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
	}	
}
