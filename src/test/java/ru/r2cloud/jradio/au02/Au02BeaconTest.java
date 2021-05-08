package ru.r2cloud.jradio.au02;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class Au02BeaconTest {

	@Test
	public void testPojo() {
		assertThat(Au02Beacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
	
}
