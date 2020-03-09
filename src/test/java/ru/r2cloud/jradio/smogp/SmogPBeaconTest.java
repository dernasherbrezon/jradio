package ru.r2cloud.jradio.smogp;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.Test;

import pl.pojo.tester.api.assertion.Method;
import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class SmogPBeaconTest {

	@Test
	public void testTelemetry1() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("0137AD615E2AAA615E34F500000000000000000000300FAB615E34F500000000000000000000508CAA615E34F500000000000000000000680BAB615E34F50000000000000000000088C5AA615E34F500000000000000000000B0F5AA615E34F500000000000000000000D00A0C4A090C4B080C4E0000E5C38932F821B02D8A7B");
		SmogPBeacon result = new SmogPBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("SmogPBeaconTelemetry1.json", result);
	}

	@Test
	public void testTelemetry2() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("0238AD615E38AD615EEC0D0040282FAD615EEC0D00402833AD615E25002700740D00000500A5020034AD615E29002B00760D00000500A0020031AD615E560F00002A000C32AD615E5F0F000029000C36AD615E580FB70C00000B000036AD615E650FA10C00000E00000A0C4A090C4B080C4E00000000542D85C6607BEF176033");
		SmogPBeacon result = new SmogPBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("SmogPBeaconTelemetry2.json", result);
	}

	@Test
	public void testTelemetry3() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("0353AD615EEC0C00001A00FF7FC900BA00784F001500100000000000000000000000000000000000000000FEFF010000004A00300190FF000000000000F9005753AD615E174A01BA0C43011401FFA9615E47016D1312BA2213507F9D0000AA615E43016D13130B9614CA219D000A0C4A090C4B080C4E8173E778BF56D6FCA4B2");
		SmogPBeacon result = new SmogPBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("SmogPBeaconTelemetry3.json", result);
	}

	@Test
	public void testBeacon() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("0457AD615E4669727374206F7065726174696E672031505120736174656C6C69746520696E2073706163652100000000000000000000000000000000000000000000000000000000000000000000000000000000003B0000000000000001000B00CB1000000A0C4A090C4B080C4E00000000000000005F32826335D67C873633");
		SmogPBeacon result = new SmogPBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("SmogPBeaconBeacon.json", result);
	}

	@Test
	public void testSMOGPTelemetry1() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("21EB6A070055AD615E31455655AD615EEC0CB00447073308BB060300D402D1029C0C8F041507F70791060600C402BE0280010101C6FDFDFDFDFDFD010101010238F902000F4F0015007E0000000000000880BA8C0100000000154200000000250CF6C52F01B3628C018C7F23EBFFFFFFFF03003430000FAFB830D2F422E06A23");
		SmogPBeacon result = new SmogPBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("SmogPBeaconSMOGPTelemetry1.json", result);
	}

	@Test
	public void testSMOGPTelemetry2() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("2256BEAD615ED1A6B441381FC3BF5E895A3F2C513B3E420036019CFF5904000D012DFFA8006F01FC009A99313E3433323E9A992B3E00005EC0CEEC9BBF3437A2C10A0C4A090C4B080C4E070C4F060C51040C6C030C69020C6A010C67000C66FE0B59FD0B5AFC0B5AFB0B5AFA0B59F90B59F80B58000039247E0FD1111F55CC65");
		SmogPBeacon result = new SmogPBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("SmogPBeaconSMOGPTelemetry2.json", result);
	}

	@Test
	public void testPojo() {
		assertPojoMethodsFor(SmogPBeacon.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(Telemetry1.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(Telemetry2.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(Telemetry3.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(AtlTelemetry1.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(AtlTelemetry2.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(Mppt.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(AckInfo.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(PcuDep.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(PcuSdc.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(PcuBat.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(PcuBus.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(Msen.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(Com.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(Tid.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(File.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(FileFragment.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(Beacon.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
	}
}
