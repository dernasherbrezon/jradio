package ru.r2cloud.jradio.entrysat;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.Test;

import pl.pojo.tester.api.assertion.Method;

public class EntrysatBeaconTest {

	@Test
	public void testPojo() {
		assertPojoMethodsFor(EntrysatBeacon.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
	}

}
