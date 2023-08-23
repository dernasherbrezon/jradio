package ru.r2cloud.jradio.connecta;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class Connecta11BeaconTest {


	@Test
	public void testBeacon() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("A098829CA662E0869E9CA862626103F087046D0001030000000000000000E4004500C5060708734E43030600E904AE9CE564DB5F04001300000005000000D5A400006E0158016A018501000000000000000000000000F11F00000000ED000000000000008600DC000000000000000000360000000D0D9A13F214392F19000000080808081AAC9CE564E23CE16402000000076A00000000FE0000000000B4660F007FF5F8F9EEF8F33F3F150501010000800C000000000200000015006F014E01D20000000000000000000000000000BFB6E9000A0056F1870030260000007905BC022DFCEDFF08003600FBFF0800060000000000000097001A0001000C00FBFFF3FF0000");
		Connecta11Beacon result = new Connecta11Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Connecta11Beacon.json", result);
	}

	@Test
	public void testPojo() {
		assertThat(Connecta11Beacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Connecta11BeaconData.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Connecta11Header.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
	
}
