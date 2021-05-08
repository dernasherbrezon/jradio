package ru.r2cloud.jradio.ca03;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class Ca03BeaconTest {

	@Test
	public void testPojo() {
		assertThat(Ca03Beacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
	
}
