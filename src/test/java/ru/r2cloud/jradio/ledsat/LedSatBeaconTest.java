package ru.r2cloud.jradio.ledsat;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class LedSatBeaconTest {

	@Test
	public void testTelemetry() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray(
				"82922000162903596128895457A305AD06430601005301E300D908030008000800080007000400040080006A2073007801DF007B00004B3600000052FF8B0383008000830142FEE3FD7AFDCCF161EFE006A4051407B70834FFE709150041022800360008000D0133180127F7000000FF000000000000000000000000000000000200B05E07EBA0A04FC8CADD5B040000");
		LedSatBeacon result = new LedSatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("LedSatBeacon-telemetry.json", result);
	}

	@Test
	public void testUnknown() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray(
				"00239282162902F0612C415027750DEB0DE80DFD00D800AB01840803FFFF000000000000FFFFFFFF0124006A2073007801E0FFCB0000A24000000167FF900383FFD6FFD90095005100EB0180F4C9E335F542F6D2FA6FF911FFE7F89401130000000000F4000001CC98012806000000FF000000000000000000000000000000000200FFCCF9AC01731835000404EE000071FFEAFE");
		LedSatBeacon result = new LedSatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("LedSatBeacon-unknown.json", result);
	}

	@Test
	public void testPojo() {
		assertThat(LedSatBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(LedSatPictureChunk.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(LedSatTelemetry.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
