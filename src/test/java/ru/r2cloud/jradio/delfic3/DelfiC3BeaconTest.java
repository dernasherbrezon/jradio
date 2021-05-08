package ru.r2cloud.jradio.delfic3;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class DelfiC3BeaconTest {

	@Test
	public void testHousekeeping() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("A8989B4040400088988C9286660103F0E108D701DE84D4FF00000000000000000000000000000000FF3FF9F96B0000950053144000003D848403000402A00200CE03301900F40100000000000000000000000000000000000000000000000000000000000000000000000000000000000000165E009F58540A00");
		DelfiC3Beacon result = new DelfiC3Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("DelfiC3HousekeepingBeacon.json", result);
	}

	@Test
	public void testPojo() {
		assertThat(DelfiC3Beacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Payload.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Housekeeping.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(DeployStatus.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(AwssFrame.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(AwssBlock.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(IVPoint.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(RdBlock.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
