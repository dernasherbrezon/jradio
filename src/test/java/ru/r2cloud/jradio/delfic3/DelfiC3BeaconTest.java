package ru.r2cloud.jradio.delfic3;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.Test;

import pl.pojo.tester.api.assertion.Method;
import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class DelfiC3BeaconTest {

	@Test
	public void testHousekeeping() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("A8989B4040400088988C9286660103F0E108D701DE84D4FF00000000000000000000000000000000FF3FF9F96B0000950053144000003D848403000402A00200CE03301900F40100000000000000000000000000000000000000000000000000000000000000000000000000000000000000165E009F58540A00");
		DelfiC3Beacon result = new DelfiC3Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("DelfiC3HousekeepingBeacon.json", result);
	}

	@Test
	public void testPojo() {
		assertPojoMethodsFor(DelfiC3Beacon.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(Payload.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(Housekeeping.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(DeployStatus.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(AwssFrame.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(AwssBlock.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(IVPoint.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
		assertPojoMethodsFor(RdBlock.class).testing(Method.GETTER, Method.SETTER).areWellImplemented();
	}
}
