package ru.r2cloud.jradio.bugsat;

import static com.google.code.beanmatchers.BeanMatchers.*;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class BugsatBeaconTest {

	@Test
	public void testTelemetry() throws Exception {
		byte[] data = ViterbiTest
				.hexStringToByteArray("86A2404040406098AA6E828200E103F0FFFFF000010000658A0000658A00086E840828CD980101000068D802010000000027030100022F4C038500030344020D0369006804010EE203B2050100000000EE8BD471D47CFDDF01D302C8FFA7005DFFC9FFE70000FF00000BE000FFFD210000000000000000000601B16EFF60001F01");
		BugsatBeacon result = new BugsatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("BugsatBeacon-telemetry.json", result);
	}

	@Test
	public void testMessage() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("86A2404040406098AA6E828200E103F03A454D41494C202020203A7469746140736174656C6C6F6769632E636F6D2000045570743A2030373A31313A3434200104020403044261743A31312E37397620040454656D703A33372E37432005044779723A312E3834642F73200604");
		BugsatBeacon result = new BugsatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("BugsatBeacon-message.json", result);
	}

	@Test
	public void testPojo() {
		assertThat(BugsatBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(BugsatTelemetry.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
