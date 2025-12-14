package ru.r2cloud.jradio.hunity;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class HunityBeaconTest {

	@Test
	public void testObc1() throws Exception {
		byte[] data = ViterbiTest
				.hexStringToByteArray("AAD5F17F3900006800BE210501DF40A2D1C88AC1AC396911203C0100E002000000093D0080969800E5FC8867E0FD01420001A5A601007E00007E2800000049880600D166FEFF710441882105D166011B1100B18701005100010016FF16031616160301FF0103011601037E080000000001AAAAD0B2A4D61B22576C8E242D");
		HunityBeacon result = new HunityBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("HunityObc1.json", result);
	}

	@Test
	public void testObc2() throws Exception {
		byte[] data = ViterbiTest
				.hexStringToByteArray("AA1184993900006000BE210501DF41D055862068AD3969010008002114F06501001800A10CD067210CF08001000000210A9867A10AE86601000000010000002112F06501000000010000000100000001000000010000000100000001000000080706050403027F7F01AAAAAAAAAAAAAAAAAAAA69B9FC193ABD402B291E4D");
		HunityBeacon result = new HunityBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("HunityObc2.json", result);
	}

	@Test
	public void testObc3() throws Exception {
		byte[] data = ViterbiTest
				.hexStringToByteArray("AA851E9A3900006800BE210501DF424D6CF6C46CAD3969E102B931B900D181790291320101D181C902612F4101D1814103893FB90009827900891D1900D181F106D13DF9043181F90081207901112BF9008124F100D12A00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FF00FFAAAAE08E8AF22D71AEEFA8928C");
		HunityBeacon result = new HunityBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("HunityObc3.json", result);
	}

	@Test
	public void testObc4() throws Exception {
		byte[] data = ViterbiTest
				.hexStringToByteArray("AAA7189B3900006A00BE210501DF4385EA329770AD39694083080248FE20FE9083A00110FE18FE0000E6073F00207F08C10000C820BF00001673BF00806E1DC100007891BF00A66E00000078CF000000CC2B0000008E28000000678C000000B99CFFFF08800880088008800880088008800880780EF735ED3C4658788722");
		HunityBeacon result = new HunityBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("HunityObc4.json", result);
	}

	@Test
	public void testObc5() throws Exception {
		byte[] data = ViterbiTest
				.hexStringToByteArray("AAACFC9C3900003200BE210501DF442099576574AD3969D800000000000000000000000000000000000000000000002001005D66050000D85C0500AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA1DBEFA54AEE2789E81D256");
		HunityBeacon result = new HunityBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("HunityObc5.json", result);
	}

	@Test
	public void testPojo() {
		assertThat(HunityBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Com1.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Obc1.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Obc2.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Obc3.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Obc4.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Obc5.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(SampleTime.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Pcu.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Sdc.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Com.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(SensorStatus.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Mptt.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(IrSensor.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Bat.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
