package ru.r2cloud.jradio.pwsat2;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class PwSat2BeaconTest {

	@Test
	public void testBeaconFrame() throws Exception {
		String data = "A0AEA682A864E0A0AEA682A8646103F0CD02000000076600C2372CBE970500000000E0DA6E380000000000000000000000000000000CBC0200D7090081E30D000000000000000000000000000000000080FCFFF57F0280386FC9092AE476A79CB0016000503971552AC7534A3661D09BBD4777223D00052AE06AAB28061000575B8EE88000D8DABA45C18803DC4C01E0B71380163A1E55880A000F00647301001757ECA2C61AEB9327C00340260A0040C617C708000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
		PwSat2Beacon beacon = new PwSat2Beacon();
		beacon.readExternal(ViterbiTest.hexStringToByteArray(data));
		AssertJson.assertObjectsEqual("PwSat2Beacon-Telemetry.json", beacon);
	}

	@Test
	public void testGenericFrame() throws Exception {
		String data = "A0AEA682A864E0A0AEA682A8646103F01300000300";
		PwSat2Beacon beacon = new PwSat2Beacon();
		beacon.readExternal(ViterbiTest.hexStringToByteArray(data));
		AssertJson.assertObjectsEqual("PwSat2Beacon-GenericFrame.json", beacon);
	}

	@Test
	public void testBootSlotsInfoFrame() throws Exception {
		String data = "A0AEA682A864E0A0AEA682A8646103F007000067003807";
		PwSat2Beacon beacon = new PwSat2Beacon();
		beacon.readExternal(ViterbiTest.hexStringToByteArray(data));
		AssertJson.assertObjectsEqual("PwSat2Beacon-BootSlotsInfoFrame.json", beacon);
	}

	@Test
	public void testFileListFrame() throws Exception {
		String data = "A0AEA682A864E0A0AEA682A8646103F08C00000500743134775F3438305F3000070000007431346E5F3438305F30000A4D000074656C656D657472792E70726576696F7573006F0008007261646665745F323100000000007261646665745F32316100600E000074656C656D657472792E63757272656E7400E31506006C6F73742B666F756E6400F0070000";
		PwSat2Beacon beacon = new PwSat2Beacon();
		beacon.readExternal(ViterbiTest.hexStringToByteArray(data));
		AssertJson.assertObjectsEqual("PwSat2Beacon-FileListFrame.json", beacon);
	}

	@Test
	public void testFileSendFrame() throws Exception {
		String data = "A0AEA682A864E0A0AEA682A8646103F04B0E002800FCD648978D3C673687EE13DC9FE94E9EE63846F132AEDE983D2B364BA768023C9B501FBB9E16A84A8D7520DA311AF573DFE94200BBBB595CAA83B73D054725CC2B061A239E8BCF02A2BA9110F936EBCFF135556512848CB8080FCC7A93F4A771D86CD7F2C8C00240518183DAA28D6691888E3249EE0569476B22CDE4DAE992CB2E3382A738AE8ED743D7E54059EDECC1FE1037103F0ACDC9219CC5BE8F3CA434AEB1AF73D4D4925AE9B13ED7BB791BFB88B926BBB4F0ADA31437773717047DE0CD80C7E83B568A6833001B00FA01BA541F32D840BB4E7245473F611E60BF643298ADB4E9A500";
		PwSat2Beacon beacon = new PwSat2Beacon();
		beacon.readExternal(ViterbiTest.hexStringToByteArray(data));
		AssertJson.assertObjectsEqual("PwSat2Beacon-FileSendFrame.json", beacon);
	}

	@Test
	public void testPojo() {
		assertThat(PwSat2Beacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(BeaconFrame.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(GenericFrame.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(ErrorCountersFrame.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(BootSlotsInfoFrame.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(FileRemoveFrame.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(FileSendFrame.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(FileListFrame.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
