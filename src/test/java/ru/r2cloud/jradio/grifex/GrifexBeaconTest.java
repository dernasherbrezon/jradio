package ru.r2cloud.jradio.grifex;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.Test;

import pl.pojo.tester.api.assertion.Method;
import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class GrifexBeaconTest {

	@Test
	public void testBeacon() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray(
				"968870A6A0A66086A240404040E103F0ABCD0000004203F500B47548186760E40408000A00090001002200A038EE7B9D03010000000047470700F0470F007300000000D3AA240086076B07030D60002800FD0CBB0E0D00A40E03068507B40E0F00660D20001400FF0C950054007D076A0D8800FD0CBB00FB09A101360F880090079107EA0AB200120F3E0088078D07D7075600290F140094079207EB072200160F08008E078E07FDFC9F07A0079F0700C04A002200A3FF7D00A1FFE6FF9B00E6FF2500DD000F00DA00070D080DBD0C950C650A420A710B600B3100A30321046600B8338B334C20001000010000009502000001000000FF723D00BEFFFFFFFF98BCB0151C40");
		GrifexBeacon result = new GrifexBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("GrifexBeacon.json", result);
	}

	@Test
	public void testPojo() {
		assertPojoMethodsFor(GrifexBeacon.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(MxlHeader.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
	}

}
