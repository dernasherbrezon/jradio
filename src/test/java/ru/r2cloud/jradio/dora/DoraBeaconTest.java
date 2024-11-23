package ru.r2cloud.jradio.dora;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class DoraBeaconTest {

	@Test
	public void testSecondPacket() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("400000004b4537444851352ef85300038001001bffffffffffffffffffffffffffffffffffffffffffffffffffffffff0d00");
		DoraBeacon result = new DoraBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("DoraSecondPacket.json", result);
	}

	@Test
	public void testPojo() {
		assertThat(DoraBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Adcs.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Eps.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Battery.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Payload1.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Payload2.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(FirstPacket.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(SecondPacket.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}

}
