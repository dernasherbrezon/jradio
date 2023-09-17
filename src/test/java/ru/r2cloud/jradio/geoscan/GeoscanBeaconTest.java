package ru.r2cloud.jradio.geoscan;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class GeoscanBeaconTest {

	@Test
	public void testAx25Telemetry() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("848A82869E9C60A4A66460A640E103F0535A0565BD047C087AEB98EA0C0D0617800802040F791DEE050100000000000000000000000000000000000000000000");
		GeoscanBeacon result = new GeoscanBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("GeoscanBeaconAx25.json", result);
	}

	@Test
	public void testPhoto() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("01003E0509CCCA0B5784790D264AA9CE0E3B7B564C87CD7DDD7DAA42CA630D1703A7BD2632472A3CC28D94CECC9E0B0F7155CE33C546EC1890C6A782C6596DCC");
		GeoscanBeacon result = new GeoscanBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("GeoscanBeaconPhoto.json", result);
	}

	@Test
	public void testPojo() {
		assertThat(GeoscanBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(GeoscanTelemetry.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(GeoscanHeader.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(GeoscanAdc.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(GeoscanEps.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(GeoscanFakel.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(GeoscanGnss.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(GeoscanPhoto.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}

}
