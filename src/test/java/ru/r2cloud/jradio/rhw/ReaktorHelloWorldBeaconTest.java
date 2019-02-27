package ru.r2cloud.jradio.rhw;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.Test;

import pl.pojo.tester.api.assertion.Method;
import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class ReaktorHelloWorldBeaconTest {

	@Test
	public void testSuccess() throws Exception {
		byte[] data = new byte[] { (byte) 0x01, (byte) 0x07, (byte) 0x00, (byte) 0xC3, (byte) 0x00, (byte) 0x00, (byte) 0x62, (byte) 0x81, (byte) 0xF8, (byte) 0x00, (byte) 0x5C, (byte) 0xAC, (byte) 0x60, (byte) 0x03, (byte) 0x00, (byte) 0x77, (byte) 0x7A, (byte) 0x35, (byte) 0x00, (byte) 0x8F, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x5E, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x0A, (byte) 0x00, (byte) 0x02, (byte) 0x06, (byte) 0x02, (byte) 0x02, (byte) 0x02, (byte) 0x02,
				(byte) 0x02, (byte) 0x02, (byte) 0x02, (byte) 0x06, (byte) 0x02, (byte) 0x02, (byte) 0x06, (byte) 0xDE, (byte) 0x72, (byte) 0x01, (byte) 0x00, (byte) 0xC6, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xFE, (byte) 0xFF, (byte) 0x03, (byte) 0x00, (byte) 0x77, (byte) 0x00, (byte) 0xBB, (byte) 0x00, (byte) 0x27, (byte) 0x00, (byte) 0xE0, (byte) 0x05, (byte) 0x07, (byte) 0x05, (byte) 0xFF, (byte) 0x07, (byte) 0xA5, (byte) 0x0D, (byte) 0x2E, (byte) 0x00, (byte) 0x05,
				(byte) 0x00, (byte) 0x97, (byte) 0x01, (byte) 0x01, (byte) 0x00, (byte) 0xF6, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x7A, (byte) 0x08, (byte) 0xB3, (byte) 0x0C, (byte) 0x03, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x7E, (byte) 0x0A, (byte) 0x18, (byte) 0x0B, (byte) 0xB5, (byte) 0x07, (byte) 0x9D, (byte) 0x08, (byte) 0xC3, (byte) 0x06, (byte) 0xC3, (byte) 0x06, (byte) 0x00, (byte) 0x04, (byte) 0x00, (byte) 0x3F, (byte) 0x20, (byte) 0x23, (byte) 0x04, (byte) 0x26,
				(byte) 0xFD, (byte) 0x7A, (byte) 0xAB, (byte) 0xFF };
		ReaktorHelloWorldBeacon result = new ReaktorHelloWorldBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("ReaktorHelloWorldBeacon-eps.json", result);
	}

	@Test
	public void testUHFStatistics() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("010500C300002F74E9500142691900FC5D00008A03000006000001001C3610005E008FB2040006000000E0780B000500000000000000D6BD42B15943A5DA");
		ReaktorHelloWorldBeacon result = new ReaktorHelloWorldBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("ReaktorHelloWorldBeacon-uhf.json", result);
	}

	@Test
	public void testPojo() {
		assertPojoMethodsFor(ReaktorHelloWorldBeacon.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(CanStatistics.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(EpsStatistics.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(ADCData.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(MpptStatistics.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(PowerStatistics.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(UHFStatistics.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(PowerLevels.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
	}

}
