package ru.r2cloud.jradio.veronika;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.ax25.Ax25Beacon;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class VeronikaBeaconTest extends Ax25Beacon {

	@Test
	public void testMgs() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("86A240404040609E9A72AC8AA46103F04D47532C313534302C323238342C383934362C2D343035352C333036382C2D3130302C2D33352C2D3132392C302C302C302C302C302C302C302C30");
		VeronikaBeacon result = new VeronikaBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("VeronikaBeaconMgs.json", result);
	}
	
	@Test
	public void testUhf() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("86A240404040609E9A72AC8AA46103F0552C353530313033372C353530313036302C312C302C2D37332C2D3135322C2D3136382C34322C4B4534415A5A2C3239362C3735363939382C38392C393500");
		VeronikaBeacon result = new VeronikaBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("VeronikaBeaconUhf.json", result);
	}
	
	@Test
	public void testObc() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("86A240404040609E9A72AC8AA46103F04F42432C35352C3335333730362C353439393638362C383137332C2D36392C32383135");
		VeronikaBeacon result = new VeronikaBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("VeronikaBeaconObc.json", result);
	}
	
	@Test
	public void testPsu() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("86A240404040609E9A72AC8AA46103F05053552C312C353439333831312C353439333931372C383137322C3631332C3437312C3432352C3133312C35662C312C3536");
		VeronikaBeacon result = new VeronikaBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("VeronikaBeaconPsu.json", result);
	}
	
	@Test
	public void testMessage() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("86A240404040609E9A72AC8AA46103F04974277320636F6C642075702068657265");
		VeronikaBeacon result = new VeronikaBeacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("VeronikaBeaconMessage.json", result);
	}
}
