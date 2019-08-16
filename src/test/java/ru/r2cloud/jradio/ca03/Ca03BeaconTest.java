package ru.r2cloud.jradio.ca03;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.Test;

import pl.pojo.tester.api.assertion.Method;

public class Ca03BeaconTest {

	@Test
	public void testPojo() {
		assertPojoMethodsFor(Ca03Beacon.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
	}
	
}
