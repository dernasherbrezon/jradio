package ru.r2cloud.jradio.sapling;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class SaplingGiganteumBeaconTest {

	@Test
	public void testTelemetry() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("FFFD00004B4E36484343DF8E17060001D8DC08600000AEC00460A003251115108A906C5060F278327EC9776DFFFFFFFFFFFFFFFFFFFF");
		SaplingGiganteumBeacon result = new SaplingGiganteumBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("SaplingGiganteumBeacon.json", result);
	}
	
	@Test(expected = UncorrectableException.class)
	public void testFalsePositive() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("DCCCCF81AE3379095F299902DA7599AAD2E43EA5D0B50CACC1C98DD106510D53265EF152EB8D451F6D16A7D5CC026311CDF5A733DCE6822E9540867AB0596D74F37AEAA259D7B0C5CC113AF25AFBD1CDA7F843831574A99ED341FFE87B5EACD20001FDF3507DDC3477D1EEDA774B7A1B46D88B56B39F330B5B19078A6CC6B69DCFF1A266AA551FE7F2598CEF3E27626A2E23B83F17FA4CDD");
		SaplingGiganteumBeacon result = new SaplingGiganteumBeacon();
		result.readBeacon(data);
	}
	
	@Test
	public void testPojo() {
		assertThat(SaplingGiganteumBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
