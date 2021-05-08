package ru.r2cloud.jradio.entrysat;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class EntrysatBeaconTest {

	@Test
	public void testPojo() {
		assertThat(EntrysatBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}

}
