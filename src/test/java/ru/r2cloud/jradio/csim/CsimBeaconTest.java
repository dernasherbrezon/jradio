package ru.r2cloud.jradio.csim;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class CsimBeaconTest {

	@Test
	public void testLongBeacon() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray(
				"8486A84040406086A6929A4040E103F0083F605200F90002E9C30400050000D5BEEFDEADDEADBEEF3A7C7481BEEFDEAD00C40000000000000000000000000033000000FF35000041BCB0DB68CCCCCD000000C35000000000000000000BEBC20000000000000000009E58000000009E5800000000FFFFFFF2000000000000000000001388000000000000000000000051CB5EFA284760CF323980EF3A19A5D3FFFF7F3700002DF2FFEA0E31000000000000000000000001010101010000000100DA000101FFFD00AAF4FF01010103000000002D002034E600304F3E00000000004CA3F90000008100910600010000FFFF00010909050303030401FFC1002AD8F000000003F27F00581A18000101030000");
		CsimBeacon result = new CsimBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("CsimBeaconLong.json", result);
	}

	@Test
	public void testShortBeacon() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray(
				"8486A84040406086A6929A4040E103F0003FA05B006C3201000E921409090909090002EA04000000000000774FC8DFFEFFF8F5FFF1FACFFA6AFA8048CA17E9FEF5076D070AFFFFB813FFFFAE3F03000000072CFFFFFFFF000000010210FFFFFFFF0000000000000000000000000000000000000000000000014100FB0101000000CC01");
		CsimBeacon result = new CsimBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("CsimBeaconShort.json", result);
	}

	@Test
	public void testPojo() {
		assertThat(CsimBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(BeaconShort.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(BeaconLong.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
