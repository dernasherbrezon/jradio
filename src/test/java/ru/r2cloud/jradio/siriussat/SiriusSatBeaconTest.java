package ru.r2cloud.jradio.siriussat;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class SiriusSatBeaconTest {

	@Test
	public void testBeacon() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("A464829C8C4060A4A66266A6406300F016420200010042008C140212EB0AA60002001B003A000000000004000100070007000600070000200000141F2707000020FA385FBA04070787851F0FF40E21FA385F260700001C00431F");
		SiriusSatBeacon result = new SiriusSatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("SiriusSatBeacon-short.json", result);
	}

	@Test
	public void testUnknownBeacon() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray(
				"A464829C8C4060A4A66266A6406300F03B541A000100EE000C4EB058375F030A00000300000000000300010000000500000000000500010000000400010000000400000000000700000000000400000000000700000000000600000000000300000000000500000000000000000000000500000000000800010000000000000000000400000000000500000000000800000000000500000000000000000000000600000000000400000000000400000000000100010000000900000000000500020000000400000000000300000000000300000000000200010000000300010000000400020000000200000000000700000000000600000000000400000000000300000000B5");
		SiriusSatBeacon result = new SiriusSatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("SiriusSatBeacon-unknown.json", result);
	}

	@Test
	public void testExtendedBeacon() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("A464829C8C4060A4A66266A6406300F017420200010051000E00EB999B44E7E0A4C55750FB4600135DC0400B4DC100426FC00000008E27485F008E27485F00000000000000006E7614475F000000000000000000000000CB57CA57000000000000FF7F000000000000");
		SiriusSatBeacon result = new SiriusSatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("SiriusSatBeacon-extended.json", result);
	}

	@Test
	public void testPojo() {
		assertThat(SiriusSatBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(ShortBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(ExtendedBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
