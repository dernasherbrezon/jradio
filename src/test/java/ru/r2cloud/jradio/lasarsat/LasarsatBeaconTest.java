package ru.r2cloud.jradio.lasarsat;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class LasarsatBeaconTest {

	@Test
	public void testUhf() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("86A240404040609E966098A6A46103F0552C333336343432322C333336343433332C312C302C313134332C313031312C3936382C38362C444C374E44522C313132332C3233343931322C37332C383400");
		LasarsatBeacon result = new LasarsatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("LasarsatBeaconUhf.json", result);
	}

	@Test
	public void testObc() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("86A240404040609E966098A6A46103F04F42432C312C333336343439342C333336343530382C313132372C313231343400");
		LasarsatBeacon result = new LasarsatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("LasarsatBeaconObc.json", result);
	}

	@Test
	public void testPsu() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("86A240404040609E966098A6A46103F05053552C312C333336373835342C333336373839382C383132372C313331302C313030352C3233382C3134312C36362C312C343500");
		LasarsatBeacon result = new LasarsatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("LasarsatBeaconPsu.json", result);
	}

	@Test
	public void testMgs() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("86A240404040609E966098A6A46103F04D47532C313134302C323532322C31313434372C2D363937322C323532372C2D33323736382C2D33323736382C2D333237363800");
		LasarsatBeacon result = new LasarsatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("LasarsatBeaconMgs.json", result);
	}

	@Test
	public void testNav() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("86A240404040609E966098A6A46103F04E41562C31352C31353736363735392C343239343936373239352C343239343936373239352C343239343936373239352C2D312C2D312C353936322C302C302C31382C3136382C3000");
		LasarsatBeacon result = new LasarsatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("LasarsatBeaconNav.json", result);
	}

	@Test
	public void testDos() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("86A240404040609E966098A6A46103F0444F532C38302C302C302C302C3533312C313034342C2D3233342C36353437322C3430362C31332C35332C3130392C3130372C3131342C3137302C343036303500");
		LasarsatBeacon result = new LasarsatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("LasarsatBeaconDos.json", result);
	}

	@Test
	public void testSol() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("86A240404040609E966098A6A46103F0534F4C2C323830362C3431322C3633312C2D313738322C313731382C313134332C3633342C3330372C3330312C3136342C3137382C38393700");
		LasarsatBeacon result = new LasarsatBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("LasarsatBeaconSol.json", result);
	}
	
	@Test
	public void testPojo() {
		assertThat(LasarsatBeacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Dos.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Mgs.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Nav.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(Obc.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
