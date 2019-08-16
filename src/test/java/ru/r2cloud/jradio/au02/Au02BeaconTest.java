package ru.r2cloud.jradio.au02;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.Test;

import pl.pojo.tester.api.assertion.Method;

public class Au02BeaconTest {

	@Test
	public void testPojo() {
		assertPojoMethodsFor(Au02Beacon.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
	}
	
}
