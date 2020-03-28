package ru.r2cloud.jradio.itasat1;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.Test;

import pl.pojo.tester.api.assertion.Method;

public class Itasat1BeaconTest {

	@Test
	public void testPojo() {
		assertPojoMethodsFor(Itasat1Beacon.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
	}
}
