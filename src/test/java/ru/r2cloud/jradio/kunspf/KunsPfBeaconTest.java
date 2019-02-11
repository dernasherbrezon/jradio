package ru.r2cloud.jradio.kunspf;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class KunsPfBeaconTest {

	@Test
	public void testWodDecoding() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("009B91825B039688051504FE0505001C001B001D001C00030003000000532070002901302795003CFF7F000505C7012F0130000E0056000601540112FFBA019300A100F401B0010E0055003900FFFD8001C600A5000000010104000048F9B179");
		KunsPfBeacon beacon = new KunsPfBeacon();
		beacon.readExternal(data);
		AssertJson.assertObjectsEqual("KunsPfBeacon-Wod.json", beacon);
	}

	@Test
	public void testImage() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("00E792420000FFD8FFE000104A46494600010101000000000000FFDB0043000C08090B09080C0B0A0B0E0D0C0E121E1412111112251A1C161E2C262E2D2B262A293036453B30334134292A3C523D41474A4D4E4D2F3A555B544B5A454C4D4AFFDB0043010D0E0E121012231414234A322A324A4A4A4A4A4A4A4A4A4A4A4A4A4A4A4A4A4A4A4A0F51D13E");
		KunsPfBeacon beacon = new KunsPfBeacon();
		beacon.readExternal(data);
		AssertJson.assertObjectsEqual("KunsPfBeacon-Image.json", beacon);
	}
}
