package ru.r2cloud.jradio.lume1;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.ccsds.PrimaryHeader;
import ru.r2cloud.jradio.ecss.SecondaryHeader;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class Lume1BeaconTest {

	@Test
	public void testId2() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("82F39D000411D4000E0801FFFF0091100319896803E8461302376D1800020000000000000000000000000000000000000000000000000000000000000000050507014C001A024F009B003D003F00000228000100000137000D000E000C000C000800080401000000010000000000000000000000000000000000000000000000000000000001010001000000000002C5050000000020690F2A0FC81021000016010000000BF10B");
		Lume1Beacon beacon = new Lume1Beacon();
		beacon.readBeacon(data);
		AssertJson.assertObjectsEqual("Lume1Beacon-id2.json", beacon);
	}

	@Test
	public void testId3() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("82F39D000411D6000E0801FFFF005A100319896903E8461302376DBE00039F00010011A00001000D9F000100089F0001000E00AD0759FF9E0009A85000000B3000010951000000B3086B58B200444FFC000C59170000623900DA00000001FFA0010309B9FD652306EC3B0000000B6595");
		Lume1Beacon beacon = new Lume1Beacon();
		beacon.readBeacon(data);
		AssertJson.assertObjectsEqual("Lume1Beacon-id3.json", beacon);
	}

	@Test
	public void testId4() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("82F39D000411D7000E0801FFFF0065100319896A03E8461302376E2A000401C3510D6842ED3332C301547300000000000000000000000000000000000000000000000000013EA2F0003E9D2548BED7FB4BC38143D0439429E9C24167040101000100010000000100FB00010000000000070000668B0000000B33C6");
		Lume1Beacon beacon = new Lume1Beacon();
		beacon.readBeacon(data);
		AssertJson.assertObjectsEqual("Lume1Beacon-id4.json", beacon);
	}

	@Test
	public void testId5() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("82F39D000411D9000E0801FFFF006C100319896B03E8461302376E3D0005416000004100000041500000437F00004250000000000000413C0000416C000041894000414A00004208E000416E00000000000000000000000000004136BD2400610067000D000E000C000C0008000800920096417BAE1400AD00DA5EDE0000000B5F98");
		Lume1Beacon beacon = new Lume1Beacon();
		beacon.readBeacon(data);
		AssertJson.assertObjectsEqual("Lume1Beacon-id5.json", beacon);
	}

	@Test
	public void testPojo() {
		assertThat(Lume1Beacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(B1Obc.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(B2Eps.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(B3TtcGssb.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(B4Adcs.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(B5Temps.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		// even if they belong to separate package, they were introduced only here
		// test them here
		assertThat(PrimaryHeader.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(SecondaryHeader.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}

}
