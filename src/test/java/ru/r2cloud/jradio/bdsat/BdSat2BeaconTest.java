package ru.r2cloud.jradio.bdsat;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.ax25.Ax25Beacon;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class BdSat2BeaconTest extends Ax25Beacon {

	@Test
	public void testObc() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("86A240404040609E96608488A86103F04F42432C34302C31373632313337342C33323336353830342C383338372C313731382C313737352C313333372C313033312C323038372C313331382C3936322C31393035");
		BdSat2Beacon result = new BdSat2Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("BdSat2BeaconObc.json", result);
	}

	@Test
	public void testBds() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("86A240404040609E96608488A86103F04244532C342C382C31312C312C313636382C313638312C313433372C313430362C313431322C313339332C313235362C313235302C313233312C313231382C362E34312C362E37352C302E3837352C302E393031");
		BdSat2Beacon result = new BdSat2Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("BdSat2BeaconBds.json", result);
	}

	@Test
	public void testPsu() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("86A240404040609E96608488A86103F05053552C332C31373633363735352C33323339323035352C383238392C313739302C313739302C3134332C3134352C37662C312C3935");
		BdSat2Beacon result = new BdSat2Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("BdSat2BeaconPsu.json", result);
	}

	@Test
	public void testMessage() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("86A240404040609E96608488A86103F048656C6C6F2066726F6D2042445341542D32207465616D2C2063656C6562726174696E672031207965617220696E206F7262697421");
		BdSat2Beacon result = new BdSat2Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("BdSat2BeaconMessage.json", result);
	}

	@Test
	public void testUhf() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("86A240404040609E96608488A86103F0552C31373633323735302C33323338323135312C362C302C313439312C313735372C313538372C3139312C4B4534415A5A2C3236352C323035353137382C39332C31313000");
		BdSat2Beacon result = new BdSat2Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("BdSat2BeaconUhf.json", result);
	}

}
