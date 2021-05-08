package ru.r2cloud.jradio.snet;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class SnetBeaconTest {

	@Test
	public void testDeserializeAdcs() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("F35010E000002C3998390D4601010F0214000004045308EAFF1800EBFF2EF244012A069A3C5DB785AE00000000000000000000000000000000E0C7E925E3110000001F607B");
		SnetBeacon beacon = new SnetBeacon();
		beacon.readBeacon(data);
		AssertJson.assertObjectsEqual("SnetBeacon-adcs.json", beacon);
	}

	@Test
	public void testDeserializeEps() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("F35027D824002C329AC74D47080000000000080017000F00BB04BA580000F9153258D4001E187255A662ED64000B000C0F002A00BE0C0F00682D1D00862F");
		SnetBeacon beacon = new SnetBeacon();
		beacon.readBeacon(data);
		AssertJson.assertObjectsEqual("SnetBeacon-eps.json", beacon);
	}

	@Test
	public void testPojo() {
		assertThat(SnetBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(LTUFrameHeader.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(SnetFrameHeader.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(SnetFrameHeaderExtension.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(ADCSTelemetry.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(EPSTelemetry.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(BatteryCurrent.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Battery.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}

}
