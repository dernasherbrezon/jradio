package ru.r2cloud.jradio.mysat1;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class Mysat1BeaconTest {

	@Test
	public void testPojo() {
		assertThat(Mysat1Beacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
	
}
