package ru.r2cloud.jradio.sstk1;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class StratosatTk1PicoBeaconTest {

	@Test
	public void testSuccess() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("5253353253424F5F6F5440851EB9C0699999BF9C4000C0CF08003F1C4000C3260000C41A0000C29000008000000080000000800000008000000080000000C025C28F41333333C2FE0000C2FE0000C2FE0000C2F40000C2FE00004084DD2F4228000046E02E00");
		StratosatTk1PicoBeacon result = new StratosatTk1PicoBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("StratosatTk1PicoBeacon.json", result);
	}
	
	@Test
	public void testPojo() {
		assertThat(StratosatTk1PicoBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
