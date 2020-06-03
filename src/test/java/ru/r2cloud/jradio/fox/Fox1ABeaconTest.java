package ru.r2cloud.jradio.fox;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.Test;

import pl.pojo.tester.api.assertion.Method;
import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class Fox1ABeaconTest {

	@Test
	public void testMinValues() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("610138080030000000000000000000000000011000012000012000478FF40670F4477FF4FF0700D5433DE63726CB2280F9677EF58E01040000002C00F500002A");
		Fox1ABeacon result = new Fox1ABeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Fox1ABeaconMinValues.json", result);
	}

	@Test
	public void testMaxValues() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("6101480C0020000000000000000000000000011000013000023000488FF4477FF4487FF4060800CFF77CEF8781D0B283526881FC9E01000200D72C003D01002A");
		Fox1ABeacon result = new Fox1ABeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Fox1ABeaconMaxValues.json", result);
	}
	
	@Test
	public void testRadExperiment() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("C1A7080F004000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
		Fox1ABeacon result = new Fox1ABeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Fox1ABeaconRadExp.json", result);
	}
	
	@Test
	public void testPojo() {
		assertPojoMethodsFor(Fox1ABeacon.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(PayloadData.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(PayloadRealtime.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(PayloadMaxValues.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(PayloadMinValues.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(PayloadRadExpData.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(IhuSoftError1A.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
	}

}
