package ru.r2cloud.jradio.snuglite;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class SnugliteBeaconTest {

	@Test
	public void testFullBeacon() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray(
				"4453304448000044533044480000030082A3E300534E55474C3E0719041B083505FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF03206800820E00090036006F00030007000018D5118A082001CE0073002EBE0E2793BE446D3C3EED400F3F5A9BBEBFA1DF86C01DF23A3D9F11BD3E8EAB50BEC923943D03116BBF84B8C4C0340A8F3BE7BFC002381887A337B696A73726EB2936E4C0900101BBF735080005EB0500010000000000000190003C4954458CA610B03ABC630BC2E17E2D417BEE5C1BD878A916D5B4E350DEFD6F18DF5803");
		SnugliteBeacon result = new SnugliteBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("SnugliteBeaconFullBeacon.json", result);
	}

	@Test
	public void testSimpleBeacon() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("4453304448000044533044480000030082A2F500534E55474C3E0719041B083519FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF0320393C49544590EA9255EC1CD7B43CFB5319577A6E95F53F77A90CBA9063D74FF671A60E2422");
		SnugliteBeacon result = new SnugliteBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("SnugliteBeaconSimpleBeacon.json", result);
	}

	@Test
	public void testPojo() {
		assertThat(SnugliteBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(SimpleBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(FullBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
