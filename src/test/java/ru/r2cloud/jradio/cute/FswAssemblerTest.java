package ru.r2cloud.jradio.cute;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.cute.fsw.FswAnalogs;
import ru.r2cloud.jradio.cute.fsw.FswAttCmd;
import ru.r2cloud.jradio.cute.fsw.FswAttCtrl;
import ru.r2cloud.jradio.cute.fsw.FswAttDet;
import ru.r2cloud.jradio.cute.fsw.FswCal;
import ru.r2cloud.jradio.cute.fsw.FswClockSync;
import ru.r2cloud.jradio.cute.fsw.FswCommandTlm;
import ru.r2cloud.jradio.cute.fsw.FswCss;
import ru.r2cloud.jradio.cute.fsw.FswEventCheck;
import ru.r2cloud.jradio.cute.fsw.FswGeneral;
import ru.r2cloud.jradio.cute.fsw.FswGps;
import ru.r2cloud.jradio.cute.fsw.FswImu;
import ru.r2cloud.jradio.cute.fsw.FswMag;
import ru.r2cloud.jradio.cute.fsw.FswMomentum;
import ru.r2cloud.jradio.cute.fsw.FswPayload;
import ru.r2cloud.jradio.cute.fsw.FswPower;
import ru.r2cloud.jradio.cute.fsw.FswRadio;
import ru.r2cloud.jradio.cute.fsw.FswRefs;
import ru.r2cloud.jradio.cute.fsw.FswRwDrive;
import ru.r2cloud.jradio.cute.fsw.FswTables;
import ru.r2cloud.jradio.cute.fsw.FswTime;
import ru.r2cloud.jradio.cute.fsw.FswTlmProc;
import ru.r2cloud.jradio.cute.fsw.FswTracker;
import ru.r2cloud.jradio.cute.fsw.FswTrackerCtrl;
import ru.r2cloud.jradio.fec.ViterbiTest;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class FswAssemblerTest {

	@Test
	public void testSuccess() throws Exception {
		List<CuteBeacon> beacons = new ArrayList<>();
		beacons.add(parse("8486A84040406086AAA88A4040E103F00055B40E000A010303010101088DF9"));
		beacons.add(parse("8486A84040406086A6929A4040E103F0003F98E7006C32010003DE4C31080808080000C610000000000000774FC8DFFDFBF6A4FE6AF8FDF8BFF8894D0817C6FC590DD40C65FFFFC143FFFFA932020302000305FFFFFFFF000000010200FFFFFFFF0000000000000000000000000000000000000000000000014100FB0101020000CC41"));
		beacons.addAll(createValidMessage());
		List<BctFsw> result = FswAssembler.assemble(beacons);
		assertEquals(1, result.size());
		AssertJson.assertObjectsEqual("BctFswAssembled.json", result.get(0));
	}

	@Test
	public void testSkipMissing() throws Exception {
		List<CuteBeacon> beacons = createValidMessage();
		beacons.remove(0);
		assertEquals(0, FswAssembler.assemble(beacons).size());

		beacons = createValidMessage();
		beacons.remove(beacons.size() - 1);
		assertEquals(0, FswAssembler.assemble(beacons).size());

		beacons = createValidMessage();
		beacons.remove(2);
		assertEquals(0, FswAssembler.assemble(beacons).size());
	}

	@Test
	public void testPojo() {
		assertThat(BctFsw.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(FswCommandTlm.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(FswGeneral.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(FswTime.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(FswRefs.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(FswAttDet.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(FswAttCmd.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(FswRwDrive.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(FswTracker.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(FswAttCtrl.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(FswMomentum.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(FswCss.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(FswMag.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(FswImu.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(FswClockSync.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(FswPayload.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(FswTlmProc.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(FswAnalogs.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(FswTables.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(FswTracker.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(FswGps.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(FswEventCheck.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(FswRadio.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(FswCal.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(FswTrackerCtrl.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(FswPower.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}

	private static List<CuteBeacon> createValidMessage() throws IOException, UncorrectableException {
		List<CuteBeacon> beacons = new ArrayList<>();
		beacons.add(parse(
				"8486A84040406086AAA88A4040E103F00855740800F9EA1E577F020005000023BEEFDEADDEADBEEF834B793FBEEFDEAD84BA0000000000000000000000039904D5001F010C0A0004FFFF1F04090D01FFFFFF000000000101003100C70018000000C9000000030000210C00000000000800000800000000000000000001680053B2566D67303036315F632041000000000000000000002E00000000000000BA25FB83000000000B5EFFFFFF06FFFFFFA6FFFFFF060000073A00000032FFFFFFA6000000320000086600000000000000000000000000000000000000D0D1426800000E3B00258806000000960000006B0100000000D0D142690006F61A000EB21B1A180AF03596DEDB0F096171F6FC"));
		beacons.add(parse(
				"8486A84040406086AAA88A4040E103F00055340900F90AFDCFB002297488EE9F44E20B05F4BF2185718CDB52F23FB49ABDF7F2D510FFCE3CF7B2B4ACDC2E48590006BE689E58FFF4000152DCCC5AFE0FE71E0D57FE9119DAF804F7CE613FF7CAFC70B23532CA1E16030101000100A1DB9CDD62ADD371F0EB3105738BB400031333FFFEA6DCFFFD6CD6FFFC541C00001975FFFDF70CFCCA0AE801A004000000000000102F00000000000000DD010001FF0100A026C6DD5ECAB071EFE41505708844FFFC348B0000000000000000FFFFFE8000000000000000009E58000000000000000100AF010100005A1192FFA3048E05B20A880016FF5FFFCB03720004FAAD000000000000001D004DFFD50008"));
		beacons.add(parse(
				"8486A84040406086AAA88A4040E103F00055340A00F90007794494B3000000990000000D0000011D00000000000000000000000004FD04F804C8000000000000010101000000010101010203010101000001000000000000000000000000000000007A12000000000000000000003FEC3FEC00005A4200005A42000D21EA00000000000000000000000000001000BC00000000000000000202000002020200020200000000000000000319000249F005C0000000000000013B006C000000000033310F08120100000000000000020301FFFFDC1C000049610001AC9EFFFFE07FFFFFE6850002091F000100040004010601060106002C002C002C00230000002A002000000030000100D403920702"));
		beacons.add(parse(
				"8486A84040406086AAA88A4040E103F00055340B00F902FFFBFFFF0003FFFB0000FFFE0000FFFF0177017701770F2C02FFD5FF5F001503030305050101010101010000D8FBFFECFE22000400000676064C0673060400C4013A00FA00A3010300EC014001700101002774069FF087F92FFD760F4E0E20001D1837106FFEA7FE05C95907FAE7C7010200002E00A1008FFF5F008FFFD2794498D1FFFE000B000B00013200010100000F422349189F6C0053B47E79461DB40000000300000000080808080801000000014763008400000000010000000003007851C9E407C700A8016D003701BE4830181EFE5F097E09140000FFFE9FD5000010BC000000000000000000000800000000000000000000"));
		beacons.add(parse(
				"8486A84040406086AAA88A4040E103F00055340C00F9302795D64CE1086624DF6F104C5346C8F4BCEC7FFFFFFF7FFFFFFF7FFFFFFF00000000000000000000000000001000BC00000000000000000201000002020202020200000000000000007E19000249F005C00000000000000000006C000000000000000000B7010000000100000002000129C3708602F087CBFDD208FC144FB1E506CD60A7A59CDB65F584A20749189F6C00000FCA01790A0A0000010029FDB10101000000000000000000000000FFBB8101000000000000000000000000000880000000000000000000000000000003F48A000000010003E2E000000001000016200000002900000F3F37571C17014100010001000E5129"));
		beacons.add(parse(
				"8486A84040406086AAA88A4040E103F00055340D00F907A120000046BC0236FAF0000001002315014100000000000000FD4D46564ED60376E6FC539FFFD60376E603AC60010000000000000000000000003B9ACA00D60376E6FC539FFFD60376E603AC6001000000000000000000000000000000007FFFFFFF7FFFFFFF7FFFFFFF7FFFFFFF7FFFFFFF7FFFFFFF000000000384010000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000077359400773594000029FC891A0000000003AC60010000000029FC891A0000000003AC60013B9ACA000000010000000000"));
		beacons.add(parse("8486A84040406086AAA88A4040E103F00055B40E000A010303010101088DF9"));
		return beacons;
	}

	private static CuteBeacon parse(String str) throws IOException, UncorrectableException {
		byte[] data = ViterbiTest.hexStringToByteArray(str);
		CuteBeacon result = new CuteBeacon();
		result.readBeacon(data);
		return result;
	}

}
