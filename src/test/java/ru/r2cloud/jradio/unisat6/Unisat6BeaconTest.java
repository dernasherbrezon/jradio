package ru.r2cloud.jradio.unisat6;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;

public class Unisat6BeaconTest {

	@Test
	public void testSuccess() throws Exception {
		byte[] data = new byte[] { -122, -94, 64, 64, 64, 64, 96, -110, -110, 96, -86, -90, 64, -31, 3, -16, 85, 83, 54, 49, 53, -103, -72, 1, 56, -126, 0, 12, 93, -58, 5, -73, -4, -47, 94, 12, 12, -76, -1, -60, -1, 50, 0, -27, 6, 23, -8, 70, -4, -5, 0, 0, 28, 50, 55, 70, 111, -115, 62, 38, 0, 13, 0, 24, 4, -9, 3, 73, 4, 0, 0, 22, 1, -86, 2, 11, 0, 15, 0, 0, 5, 11 };
		Unisat6Beacon result = new Unisat6Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Unisat6Beacon.json", result);
	}
	
	@Test
	public void testPojo() {
		assertThat(Unisat6Beacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Beacon2.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Eps.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
