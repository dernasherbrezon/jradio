package ru.r2cloud.jradio.gomx1;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.Test;

import pl.pojo.tester.api.assertion.Method;

public class Gomx1BeaconTest {

	@Test
	public void testPojo() {
		assertPojoMethodsFor(Gomx1Beacon.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(TypeA.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(TypeB.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
	}
	
}
