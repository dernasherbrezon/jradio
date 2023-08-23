package ru.r2cloud.jradio.dhabi;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class DhabisatBeaconTest {
	
	@Test
	public void testBeacon() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("826C7096AA40E0826C709AB0406103F0C90000000000000000010001004136384D5801110000001150E56493FB0100005C4B00008D000000A5A5A5A5A5A5A5A5A5A5A5A5A5A5A5A5A5A5A5A5A5A5A5A5A5A5A5A5A5A5A5A5A5A592817E91849AF108FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
		DhabisatBeacon result = new DhabisatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("DhabisatBeacon.json", result);
	}

	@Test
	public void testGenericPacket() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("826C7096AA40E0826C709AB0406103F0CA000000000000000001000100596F2120546869732069732044484142495341542066726F6D20596168536174205370616365204C6162206174204B68616C69666120556E69766572736974792C204162752044686162692C205541452E");
		DhabisatBeacon result = new DhabisatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("DhabisatBeaconGeneric.json", result);
	}

	@Test
	public void testPojo() {
		assertThat(DhabisatBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(DhabisatBeaconPacket.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(DhabisatHeader.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
	
	
}
