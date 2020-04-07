package ru.r2cloud.jradio.swampsat2;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.Test;

import pl.pojo.tester.api.assertion.Method;
import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class Swampsat2BeaconTest {

	@Test
	public void testAck() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("AEA468AA8C40E0AE9664B092886103F04761746F72204E6174696F6E2049732045766572797768657265212046726F6D205377616D70536174204949");
		Swampsat2Beacon result = new Swampsat2Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Swampsat2AckBeacon.json", result);
	}

	@Test
	public void testShortFrame() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("AEA468AA8C40E0AE9664B092886103F01600940302007C030200940324005A031900050309030E03120017002700020003000200020002005B030200030002000503060004030D0005031D0029024D022B0105003602860240010B00590029025C021B0202001C00170108000E001A0107008C00004082007700840000003C003C00D300FF0314800503FF03E102E802E902010001001100031E050D0B02D601FE000F0E4700003A03E8047301600147020000");
		Swampsat2Beacon result = new Swampsat2Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Swampsat2ShortBeacon.json", result);
	}

	@Test
	public void testUnknownPayload() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("AEA468AA8C40E0AE9664B092886103F000");
		Swampsat2Beacon result = new Swampsat2Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Swampsat2UnknownBeacon.json", result);
	}
	
	@Test
	public void testPojo() {
		assertPojoMethodsFor(Swampsat2Beacon.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(Eps.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(Battery.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(Vutrx.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(Antennas.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(Stx.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
	}
}
