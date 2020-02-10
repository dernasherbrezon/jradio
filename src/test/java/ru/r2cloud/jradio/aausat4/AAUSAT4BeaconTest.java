package ru.r2cloud.jradio.aausat4;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.Test;

import pl.pojo.tester.api.assertion.Method;

public class AAUSAT4BeaconTest {

	@Test
	public void testPojo() {
		assertPojoMethodsFor(AAUSAT4Beacon.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(EPS.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(COM.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(ADCS1.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(ADCS2.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(AIS.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
	}
}
