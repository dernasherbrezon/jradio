package ru.r2cloud.jradio.randev;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.ax25.Ax25Beacon;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class RandevBeaconTest extends Ax25Beacon {

	@Test
	public void testTelemetry() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray(
				"82A686984040E0A4829C888AAC6103F00A3AF5A372FFFFFFFF0079007D1111111111111111121212121212121213131313131313130101010101010101020202020202020203030303030303030000000000020386000B0011000603810007035C000E03050002000200020002000200030003030800000000000300000003000303080002000200010002038A000C035B02FD0003F2F2095D06B6067F012300480249000009500954007E0044067E01210048024A0000094F0953F5F5F5F5000000020000818100004242F7F7F7F7F7F7F7F7F7F7F7F7F7F7F7F7F7F7F7F7F7F7F7F7F7F7F700800080000000000000000000000000");
		RandevBeacon result = new RandevBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("RandevBeacon.json", result);
	}

	@Test
	public void testPojo() {
		assertThat(RandevBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Telemetry.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Obc.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Eps.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(CommRx.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(CommTx.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(CommAntenna.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Adcs.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Hstx.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
