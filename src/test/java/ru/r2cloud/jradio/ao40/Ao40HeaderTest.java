package ru.r2cloud.jradio.ao40;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.Test;

import pl.pojo.tester.api.assertion.Method;

public class Ao40HeaderTest {

	@Test
	public void testPojo() {
		assertPojoMethodsFor(Ao40Header.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
	}
	
}
