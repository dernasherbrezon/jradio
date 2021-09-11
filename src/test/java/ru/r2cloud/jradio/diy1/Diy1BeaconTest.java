package ru.r2cloud.jradio.diy1;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class Diy1BeaconTest {

	@Test
	public void testPojo() {
		assertThat(Diy1Beacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
