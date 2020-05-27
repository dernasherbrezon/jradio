package ru.r2cloud.jradio.fox;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.Test;

import pl.pojo.tester.api.assertion.Method;
import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class Fox1BBeaconTest {

	@Test
	public void testRadExperiment() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("5A0218D5264000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
		Fox1BBeacon result = new Fox1BBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Fox1BBeaconRadExp.json", result);
	}

	@Test
	public void testPayloadRealtime() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("5A0240D526107EA8CECC0C841A9882FA587D0130000760001210000110000110000110000D09005B2B7EBF6748BF6284FE965F003000080000000738F4010020");
		Fox1BBeacon result = new Fox1BBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Fox1BBeaconRealtime.json", result);
	}

	@Test
	public void testMaxValues() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("5A02C8D6262070AADE7E9D8CA5688BF43A85C88DDBB30DDAB00DDA0BA9866F788758788540FAFF1B9C8739E893C9A2A7693AA704D0C83F0000084A001DC00428");
		Fox1BBeacon result = new Fox1BBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Fox1BBeaconMaxValues.json", result);
	}

	@Test
	public void testMinValues() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("5A02F0D4263078F7BCB9FB6CCB066D2B2467001000001000011000000000000000000000A8060052E05465250C88C2555AC54F003000050000004A001DC00428");
		Fox1BBeacon result = new Fox1BBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Fox1BBeaconMinValues.json", result);
	}

	@Test
	public void testPojo() {
		assertPojoMethodsFor(Fox1BBeacon.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(FoxHeader.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
	}
}
