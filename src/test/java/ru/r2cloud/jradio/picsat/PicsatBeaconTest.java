package ru.r2cloud.jradio.picsat;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class PicsatBeaconTest {

	@Test
	public void testPayloadBeacon() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("a09286a682a8e0a09286a682a86503f00b67c34500494af2031a3ee0200000416c6c207468696e677320617265206d616465206f662061746f0000000410280000000000000000000000000000000000000000000000000000000000000000");
		PicsatBeacon result = new PicsatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("PicsatBeaconPayload.json", result);
	}

	@Test
	public void testCategoryBeacon() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray(
				"a09286a682a8e0a09286a682a86503f00901d09f006c4b1e02b7d06a0300000000000000000000000000000002890287090e068a046e0de603d90a21fffafffa04206c000000ff000000000000001d00000000000000000000000000000000000000000000000000000000000000000004a4620000017d5170995600040000000000");
		PicsatBeacon result = new PicsatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("PicsatBeaconCategory.json", result);
	}

	@Test
	public void testUnknown() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("A09286A682A8E0A09286A682A86503F00A7DC00200114AF20254029000C8288750414176333630");
		PicsatBeacon result = new PicsatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("PicsatBeaconUnknown.json", result);
	}

	@Test
	public void testPojo() {
		assertThat(PicsatBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(PrimaryHeader.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(SecondaryHeaderTelecommand.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(SecondaryHeaderTelemetry.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(PayloadBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(CategoryBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(AuxHeader.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Housekeeping.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
