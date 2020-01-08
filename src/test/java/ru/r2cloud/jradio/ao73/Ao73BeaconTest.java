package ru.r2cloud.jradio.ao73;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.Test;

import pl.pojo.tester.api.assertion.Method;

public class Ao73BeaconTest {

	@Test
	public void testPojo() {
		assertPojoMethodsFor(Ao73Beacon.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(RealtimeTelemetry.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(HighResolutionData.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(HighResolutionDataBatch.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(WholeOrbit.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(WholeOrbitDataBatch.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
	}
}
