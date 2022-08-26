package ru.r2cloud.jradio.cute;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.cute.soh.SohAnalogs;
import ru.r2cloud.jradio.cute.soh.SohAttCmd;
import ru.r2cloud.jradio.cute.soh.SohAttCtrl;
import ru.r2cloud.jradio.cute.soh.SohAttDet;
import ru.r2cloud.jradio.cute.soh.SohClockSync;
import ru.r2cloud.jradio.cute.soh.SohCommandTlm;
import ru.r2cloud.jradio.cute.soh.SohCss;
import ru.r2cloud.jradio.cute.soh.SohEventCheck;
import ru.r2cloud.jradio.cute.soh.SohGeneral;
import ru.r2cloud.jradio.cute.soh.SohGps;
import ru.r2cloud.jradio.cute.soh.SohImu;
import ru.r2cloud.jradio.cute.soh.SohL0;
import ru.r2cloud.jradio.cute.soh.SohMag;
import ru.r2cloud.jradio.cute.soh.SohMomentum;
import ru.r2cloud.jradio.cute.soh.SohRadio;
import ru.r2cloud.jradio.cute.soh.SohRefs;
import ru.r2cloud.jradio.cute.soh.SohRwDrive;
import ru.r2cloud.jradio.cute.soh.SohTime;
import ru.r2cloud.jradio.cute.soh.SohTracker;
import ru.r2cloud.jradio.cute.soh.SohTrackerCtrl;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class CuteBeaconTest {

	@Test
	public void testBctSoh() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray(
				"8486A84040406086AAA88A4040E103F00856FA9500E82A969D270200050000D0BEEFDEADDEADBEEFD6AAAAE3BEEFDEADA19B00000000000000000000002E00B5000008000000D4F111C501043EC5D8F4B4AE3710CC0CD3D3F5F4C73BEDBBF2332ADA2E0161B1B163EE125F76E625C3403CA3469EFFFE9F68000008A90000012300000000000000000000005701010101001DC8C400AF01040002BF06AC00FC03000021FFFF5535FFFFE9D0FFFFF0C700010083022D0700020001FFFA01210B0303030501D8F4FF7EFEFB0001FF17F500F444013201001FAC1307FA9D4D161802FC5902020200008000010008000259CD2E01000000465F13014100FC010100");
		CuteBeacon result = new CuteBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("CuteBeaconBctSoh.json", result);
	}

	@Test
	public void testUnknown() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray(
				"8486A84040406086A6929A4040E103F0003F98E7006C32010003DE4C31080808080000C610000000000000774FC8DFFDFBF6A4FE6AF8FDF8BFF8894D0817C6FC590DD40C65FFFFC143FFFFA932020302000305FFFFFFFF000000010200FFFFFFFF0000000000000000000000000000000000000000000000014100FB0101020000CC41");
		CuteBeacon result = new CuteBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("CuteBeacon-unknown.json", result);
	}

	@Test
	public void testPayload() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray(
				"8486A84040406086AAA88A4040E103F009FFF4BF0077EA1E57EC00200801CAF5006B29C3741B01C5020001039366565F9829560D562097E87E42010105E00BD20D010DFD02990000006E0001006D00C00082050003C400000100000000000187002726C0CCB1CE4B09290100BDCCCCCD412000000C410C8A0CAA0C2B0C150C0A3E010004151E0004000000001AC5");
		CuteBeacon result = new CuteBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("CuteBeaconPayload.json", result);
	}

	@Test
	public void testBctFsw() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("8486A84040406086AAA88A4040E103F00055B506000A010303010101088DF90000");
		CuteBeacon result = new CuteBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("CuteBeaconBctFsw.json", result);
	}

	@Test
	public void testPojo() {
		assertThat(CuteBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(BctSoh.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(CutePayloadSwStat.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(SohL0.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(SohCommandTlm.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(SohGeneral.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(SohTime.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(SohRefs.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(SohAttDet.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(SohAttCmd.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(SohRwDrive.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(SohTracker.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(SohAttCtrl.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(SohMomentum.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(SohCss.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(SohMag.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(SohImu.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(SohClockSync.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(SohAnalogs.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(SohTracker.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(SohGps.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(SohEventCheck.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(SohRadio.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(SohTrackerCtrl.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
