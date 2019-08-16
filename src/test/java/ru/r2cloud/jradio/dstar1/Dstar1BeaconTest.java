package ru.r2cloud.jradio.dstar1;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.Test;

import pl.pojo.tester.api.assertion.Method;
import ru.r2cloud.jradio.tubix20.CMX909bHeader;

public class Dstar1BeaconTest {

	@Test
	public void testPojo() {
		assertPojoMethodsFor(Dstar1Beacon.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(PayloadData.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(CMX909bHeader.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
	}
	
}
