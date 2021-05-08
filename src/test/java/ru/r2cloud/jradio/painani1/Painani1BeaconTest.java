package ru.r2cloud.jradio.painani1;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;
import ru.r2cloud.jradio.opssat.Telemetry;

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
		assertThat(Painani1Beacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Telemetry.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(ShortTelemetry.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}	
}
