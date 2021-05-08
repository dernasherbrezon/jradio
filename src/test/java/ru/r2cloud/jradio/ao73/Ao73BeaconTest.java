package ru.r2cloud.jradio.ao73;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class Ao73BeaconTest {

	@Test
	public void testPojo() {
		assertThat(Ao73Beacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(RealtimeTelemetry.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(HighResolutionData.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(HighResolutionDataBatch.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(WholeOrbit.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(WholeOrbitDataBatch.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
