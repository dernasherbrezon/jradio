package ru.r2cloud.jradio.norbi;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class NorbiBeaconTest {

	@Test
	public void testSuccess() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray(
				"9CA6AA4040406086AA84A682A86103F08EFFFFFFFF0A0601C916E800000000F10F0000FF16C69C050042524B204D57205645523A30325F31320000000000000E0000D105000000071A0000CD0A8A001004000000202009600000000023B1CE01326DB100B0927427250000000000000000000000000016040C0F0F0F8F0F0F001214B76C12600AB8380E0C000C00006500F4139D0E1416120060106F20D920");
		NorbiBeacon result = new NorbiBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("NorbiBeacon.json", result);
	}

	@Test
	public void tesLoraBeacon() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray(
				"8EFFFFFFFF0A0601C96B7200000000F10F00005BA564BC7A2942524B204D57205645523A3035615F303100000000000E0100F90700000002160008D80A82F2B90200000000000000B21A5400B5732D0276BD310065BC7A292900D5FF85FF05000000000000000E04040F0F0F0F0F0F000707B76C92200263360E0C000C00003E09380F1E060C0E0A00601076208FFC");
		NorbiBeacon result = new NorbiBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("NorbiLoraBeacon.json", result);
	}

	@Test
	public void testPojo() {
		assertThat(NorbiBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
