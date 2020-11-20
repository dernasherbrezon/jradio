package ru.r2cloud.jradio.norbi;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.Test;

import pl.pojo.tester.api.assertion.Method;
import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class NorbiBeaconTest {

	@Test
	public void testSuccess() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("9CA6AA4040406086AA84A682A86103F08EFFFFFFFF0A0601C916E800000000F10F0000FF16C69C050042524B204D57205645523A30325F31320000000000000E0000D105000000071A0000CD0A8A001004000000202009600000000023B1CE01326DB100B0927427250000000000000000000000000016040C0F0F0F8F0F0F001214B76C12600AB8380E0C000C00006500F4139D0E1416120060106F20D920");
		NorbiBeacon result = new NorbiBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("NorbiBeacon.json", result);
	}

	@Test
	public void testPojo() {
		assertPojoMethodsFor(NorbiBeacon.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
	}
}
