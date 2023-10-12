package ru.r2cloud.jradio.sharjahsat;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class Sharjahsat1BeaconTest {
	
	@Test
	public void testTelemetry() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("826C64AA9EA6E0826C60AA9EA66103F04553455250F600000000FF1FA101023A440000382A276561058E055505810803028608340D2000090083082D0D27052303110A2300FF0F8703E9FF06035C0302000300E80263026102610285030D00050349005B03130011001700F102F502020082035504FB153C8ECFF1F995F402C2F4FFAA008E0100000000000000000000000000000000000000000C003E030000E904000000000000000000000000000000009B01AB01FD0051020601FC0007010701070103006C001400AD01020002000200010002000300020004000200020002000200020007002B022B0218021802A10223029C029F029D02F703F403F2039C029F029F029E029F029E0290034700");
		Sharjahsat1Beacon result = new Sharjahsat1Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Sharjahsat1Beacon.json", result);
	}

	@Test
	public void testImage() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("826C64AA9EA6E0826C60AA9EA66103F04553455241F60D0000005A9868246E29B4C42534D310DA4A08128A620C538AD001B78A50B482C232E2A3340991914DAA24526973914806E29698006E08A9617DB52CA193C985ACDCFCCD5512645330E5AAC5BC78AD599246BDAC5D2B47EE8C0AE591D51562DC2D8879AACEFCD42DCD48CB51BB8AB20915EAC4736DA968D108F266888F1CD2026DC1451BF70A92C41270050F2E4629D8647BA9C1AA843852D228753A9943C53EA463C53C5234255152A8A451285A902D4964C169C16A4B24515328A45122AD4EA952325D94C993E5A64B39EC5262B73806E2A222801845466A84308A8CD0034D37EB4C421A69A42186A26A60308A61AA2469A650219498AA2069");
		Sharjahsat1Beacon result = new Sharjahsat1Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Sharjahsat1BeaconImage.json", result);
	}
	
	@Test
	public void testPojo() {
		assertThat(Sharjahsat1Beacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Sharjahsat1Header.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Telemetry.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(SystemInfo.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Obc.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(InterfaceBrdRtc.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Battery.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Eps.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Adcs.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(UhfVhfModem.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(SBandModem.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(SolarPanels.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}

}
