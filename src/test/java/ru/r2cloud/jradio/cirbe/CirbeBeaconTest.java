package ru.r2cloud.jradio.cirbe;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.cute.BctSoh;
import ru.r2cloud.jradio.cute.soh.SohEventCheck;
import ru.r2cloud.jradio.cute.soh.SohRwDrive;
import ru.r2cloud.jradio.cute.soh.SohTracker;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class CirbeBeaconTest {
	
	@Test
	public void testBctBeacon() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("8486A8404040608692A4848A40E103F00850FBB600D22BFF306B00DF050000EC0000000000000000A17E5D4114606900000000000000000000000000001900E7000000000141C5FF98357F5E74010F085FDF9B038B63081253560FC2AAF816C91B87AD2679153EB7FABE0D0001217600090000010033DA9F53EE8BEBCA96493DBA067A69520D005C10732107A7030300050202020000094E6203920100030005100303037717E3D7E4FB01B0000000172A970DA700B801972700920124FE00000B170067A31A060606060718060000134701F70FC40008000184033001004100050000431521210100");
		CirbeBeacon result = new CirbeBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("CirbeBeacon-bct.json", result);
	}
	
	@Test
	public void testPojo() {
		assertThat(CirbeBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(BctSoh.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
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
