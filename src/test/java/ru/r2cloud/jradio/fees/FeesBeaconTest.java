package ru.r2cloud.jradio.fees;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class FeesBeaconTest {

	@Test
	public void testTmi254() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("00FEE52FB0D5157B00A90000000100000000F907E10BD80852005400300050005100CF003B015A00800072008E34A8");
		FeesBeacon result = new FeesBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("FeesBeacon.json", result);
	}

	@Test
	public void testPojo() {
		assertThat(FeesBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(FeesHeader.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Tmi254.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
