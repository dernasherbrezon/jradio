package ru.r2cloud.jradio.aausat4;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class Aausat4BeaconTest {

	@Test
	public void testPojo() {
		assertThat(Aausat4Beacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Eps.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Com.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Adcs1.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Adcs2.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Ais.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
