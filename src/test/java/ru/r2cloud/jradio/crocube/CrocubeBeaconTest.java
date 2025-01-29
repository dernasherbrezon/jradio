package ru.r2cloud.jradio.crocube;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class CrocubeBeaconTest {

	@Test
	public void testUHF() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("86A240404040607282608686406103F0552C3738343633302C333237393330312C31322C302C3930382C313035342C3736382C35332C444C374E44522C38362C3230343235332C38392C393700");
		CrocubeBeacon result = new CrocubeBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("CrocubeBeaconUhf.json", result);
	}

	@Test
	public void testMessage() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("86A240404040607282608686406103F0446F20796F752077616E7420796F75722043726F437562652051534C20636172643F");
		CrocubeBeacon result = new CrocubeBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("CrocubeBeaconMessage.json", result);
	}

	@Test
	public void testSol() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("86A240404040607282608686406103F0534F4C2C3533312C3138312C2D313136392C2D3738322C2D3935302C3430302C3838372C3336302C3331322C3135312C3135352C313335");
		CrocubeBeacon result = new CrocubeBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("CrocubeBeaconSol.json", result);
	}

	@Test
	public void testPsu() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("86A240404040607282608686406103F05053552C31312C313739383837342C333335373232342C373933332C3936322C3734332C3335392C3138342C37662C312C3934");
		CrocubeBeacon result = new CrocubeBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("CrocubeBeaconPsu.json", result);
	}

	@Test
	public void testAtr() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("86A240404040607282608686406103F04154522C302C302C34362C3238362C3232352C3130322C35392C35382C313335");
		CrocubeBeacon result = new CrocubeBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("CrocubeBeaconAtr.json", result);
	}

	@Test
	public void testObc() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("86A240404040607282608686406103F04F42432C31312C313639343634322C333235373239362C383139322C313131372C34393139");
		CrocubeBeacon result = new CrocubeBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("CrocubeBeaconObc.json", result);
	}

	@Test
	public void testPojo() {
		assertThat(CrocubeBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Atr.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Mgs.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Obc.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Psu.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Sol.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Trx.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
