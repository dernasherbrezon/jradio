package ru.r2cloud.jradio.sputnix;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class SputnixBeaconTest {

	@Test
	public void testSuccess() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("A464829C8C4060A4A66660A6406300F01642020001004200070007000500000002000000E3FF00001E0000000000040004000400050000200000E81E55D00000F8E50465D3040C0BFF7B2118F40EF9E5046520D000001E00FC1E");
		SputnixBeacon result = new SputnixBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("SputnixBeacon.json", result);
	}
	
	@Test
	public void testUnknown() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("A464829C8C4060A4A66472A6406300F0F1FF0F0001003F002A1843184A403A18401840C7D77A3E7D4E456C185818532C79FD1846EB18CAA118C2F01853D4184C18C8DD25FD3122E8C87D58202EAC3C186BE8185E74CD11");
		SputnixBeacon result = new SputnixBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("SputnixBeacon-unknown.json", result);
	}
	
	@Test
	public void testPojo() {
		assertThat(SputnixBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(SputnixTelemetry.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
