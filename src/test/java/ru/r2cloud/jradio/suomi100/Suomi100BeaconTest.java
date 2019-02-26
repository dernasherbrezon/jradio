package ru.r2cloud.jradio.suomi100;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.Test;

import pl.pojo.tester.api.assertion.Method;
import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class Suomi100BeaconTest {

	@Test
	public void testBeacon1() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("82A78001015C73C15100001C040002A1B7000000090000000000000002000000020000000C0000010000000000000000000000000101010101010000015C73C15100000A98B0000312D606D10CEF00C4400207750000010000595461000238FF0100008A3B00001AB95C73C1500101010100000009010BFC000001005C73C14FC5B9E59C02A42D63");
		Suomi100Beacon beacon = new Suomi100Beacon();
		beacon.readExternal(data);
		AssertJson.assertObjectsEqual("Suomi100Beacon-1.json", beacon);
	}
	
	@Test
	public void testPojo() {
		assertPojoMethodsFor(Suomi100Beacon.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(Beacon0.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(Beacon1.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(Beacon0Eps.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(Beacon1Eps.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(Beacon0Com.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(Beacon1Com.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(Beacon0Obc.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(Beacon1Obc.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
	}
}
