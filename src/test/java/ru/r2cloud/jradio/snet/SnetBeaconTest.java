package ru.r2cloud.jradio.snet;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.Test;

import pl.pojo.tester.api.assertion.Method;
import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class SnetBeaconTest {

	@Test
	public void testDeserializeAdcs() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("F35010E000002C3998390D4601010F0214000004045308EAFF1800EBFF2EF244012A069A3C5DB785AE00000000000000000000000000000000E0C7E925E3110000001F607B");
		SnetBeacon beacon = new SnetBeacon();
		beacon.readBeacon(data);
		AssertJson.assertObjectsEqual("SnetBeacon-adcs.json", beacon);
	}

	@Test
	public void testDeserializeEps() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("F35027D824002C329AC74D47080000000000080017000F00BB04BA580000F9153258D4001E187255A662ED64000B000C0F002A00BE0C0F00682D1D00862F");
		SnetBeacon beacon = new SnetBeacon();
		beacon.readBeacon(data);
		AssertJson.assertObjectsEqual("SnetBeacon-eps.json", beacon);
	}

	@Test
	public void testPojo() {
		assertPojoMethodsFor(SnetBeacon.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(LTUFrameHeader.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(SnetFrameHeader.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(ADCSTelemetry.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(EPSTelemetry.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(BatteryCurrent.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(Battery.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
	}

}
