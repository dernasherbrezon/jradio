package ru.r2cloud.jradio.fmn1;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.Test;

import pl.pojo.tester.api.assertion.Method;

public class Fmn1BeaconTest {

	@Test
	public void testPojo() {
		assertPojoMethodsFor(Fmn1Beacon.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
	}
	
}
