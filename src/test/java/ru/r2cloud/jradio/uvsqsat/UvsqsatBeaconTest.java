package ru.r2cloud.jradio.uvsqsat;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class UvsqsatBeaconTest {

	@Test
	public void testSuccess() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("9882a89a9ea6e09882a89a9ea66303f00801c00000d820031900000002386d7c860000000f0380ca22ad1100160ed10000049b000000140000027b000149e600402e65012d04024700196e96c95e62765012a04124600196e96b000852084d0855099f099f09a404f102a0000007f903861f9001c800781f93fe12ff89006f000080000b661f90140507f91f8e00b90050140c003600061407016800381404004000130d5a01cb002f0d5c004c00081a054101800100010023e4480000021200a50006000000130000000400050004000600050006ffffd3a4ffffd6cdffffe680ffffdaa2ffffd6a4ffffd556489f");
		UvsqsatBeacon result = new UvsqsatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("UvsqsatBeacon-beacon.json", result);
	}

	@Test
	public void testPojo() {
		assertThat(UvsqsatBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
