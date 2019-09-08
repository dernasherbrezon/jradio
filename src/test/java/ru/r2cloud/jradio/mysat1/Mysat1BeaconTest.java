package ru.r2cloud.jradio.mysat1;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.Test;

import pl.pojo.tester.api.assertion.Method;

public class Mysat1BeaconTest {

	@Test
	public void testPojo() {
		assertPojoMethodsFor(Mysat1Beacon.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
	}
	
}
