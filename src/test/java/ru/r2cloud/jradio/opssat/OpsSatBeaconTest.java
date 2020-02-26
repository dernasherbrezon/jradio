package ru.r2cloud.jradio.opssat;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.Test;

import pl.pojo.tester.api.assertion.Method;

public class OpsSatBeaconTest {

	@Test
	public void testPojo() {
		assertPojoMethodsFor(OpsSatBeacon.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(Telemetry.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
	}
}
