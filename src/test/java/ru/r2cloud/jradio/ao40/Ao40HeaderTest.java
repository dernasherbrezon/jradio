package ru.r2cloud.jradio.ao40;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class Ao40HeaderTest {

	@Test
	public void testPojo() {
		assertThat(Ao40Header.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
	
}
