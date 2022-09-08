package ru.r2cloud.jradio.selfiesat;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class SelfieSatBeaconTest {

	@Test
	public void testSuccess() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("0500850100E00000000000000000000000000031207300A0000053656C6669655361740053656C6669655361740053656C6669655361740053656C6669655361740053656C6669655361740053656C6669655361740053656C6669655361740053656C6669655361740053656C6669655361740053656C6669655361740053656C6669655361740053656C6669655361740053656C6669655361740053656C6669655361740053656C6669655361740053656C6669655361740053656C6669655361740053656C6669655361740053656C6669655361740053656C66696553617400175A9244");
		SelfieSatBeacon result = new SelfieSatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("SelfieSatBeacon.json", result);
	}
	
	@Test
	public void testPojo() {
		assertThat(SelfieSatBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Telemetry.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
