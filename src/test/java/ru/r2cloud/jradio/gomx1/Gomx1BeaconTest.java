package ru.r2cloud.jradio.gomx1;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class Gomx1BeaconTest {

	@Test
	public void testPojo() {
		assertThat(Gomx1Beacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(TypeA.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(TypeB.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
	
}
