package ru.r2cloud.jradio.grbbeta;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class GRBBetaBeaconTest {

	@Test
	public void testBeacon() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("86A240404040609082648EA4846103F0552C323633373233312C323635393030372C322C302C313237352C313237342C313231382C302C2C313830382C3237393833322C37352C393200");
		GRBBetaBeacon result = new GRBBetaBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("GRBBetaBeaconTrx.json", result);
	}

	@Test
	public void testCspBeacon() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("4050EA3EB97297B4EAEC190B3621EE6447C69D532D");
		GRBBetaBeacon result = new GRBBetaBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("GRBBetaBeaconCsp.json", result);
	}

	@Test
	public void testPojo() {
		assertThat(GRBBetaBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(TrxBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}

}
