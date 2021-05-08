package ru.r2cloud.jradio.nayif1;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class Nayif1BeaconTest {

	@Test
	public void testPojo() {
		assertThat(Nayif1Beacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(RealtimeTelemetry.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(HighResolutionData.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(HighResolutionDataBatch.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(WholeOrbit.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(WholeOrbitDataBatch.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
