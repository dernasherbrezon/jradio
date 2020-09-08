package ru.r2cloud.jradio.falconsat3;

import static org.junit.Assert.assertEquals;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;

public class FileExtractorTest {

	@Test
	public void testDirFrame() throws Exception {
		Falconsat3Beacon beacon1 = new Falconsat3Beacon();
		beacon1.readExternal(new byte[] { -94, -90, -88, 64, 64, 64, 2, -96, -116, -90, 102, 64, 64, 23, 3, -67, 32, 34, 60, 0, 0, 0, 0, 0, 0, -66, 4, 67, 95, -48, 4, 67, 95, -86, 85, 1, 0, 4, 34, 60, 0, 0, 2, 0, 8, 65, 76, 49, 50, 48, 56, 50, 51, 3, 0, 3, 32, 32, 32, 4, 0, 4, 116, 24, 0, 0, 5, 0, 4, -30, -75, 65, 95, 6, 0, 4, -66, 4, 67, 95, 18, 0, 4, -66, 4, 67, 95, 7, 0, 1, 0, 8, 0, 1, -55, 9, 0, 2, -11, -79, 10, 0, 2, 63, 12, 11, 0, 2, 80, 0, 0, 0, 0, -60, 71 });
		List<Falconsat3Beacon> beacons = new ArrayList<>();
		beacons.add(beacon1);
		List<PacsatDirEntry> result = FileExtractor.readDirectory(beacons);
		assertEquals(1, result.size());
		AssertJson.assertObjectsEqual("PacsatDirEntry.json", result.get(0));
	}

	@Test
	public void testSuccess() throws Exception {
		Falconsat3Beacon beacon1 = new Falconsat3Beacon();
		beacon1.readExternal(new byte[] { -94, -90, -88, 64, 64, 64, 2, -96, -116, -90, 102, 64, 64, 23, 3, -69, 2, -22, 59, 0, 0, 0, 0, 0, 0, -86, 85, 1, 0, 4, -22, 59, 0, 0, 2, 0, 8, 53, 102, 51, 100, 99, 98, 51, 52, 3, 0, 3, 32, 32, 32, 4, 0, 4, -67, 1, 0, 0, 5, 0, 4, -57, 55, 62, 95, 6, 0, 4, 0, 0, 0, 0, 7, 0, 1, 0, 8, 0, 1, 0, 9, 0, 2, -39, 70, 10, 0, 2, 28, 35, 11, 0, 2, -50, 0, 16, 0, 5, 83, 84, 50, 78, 72, 17, 0, 6, 83, 84, 50, 78, 72, 32, 18, 0, 4, 53, -53, 61, 95, 19, 0, 1, 0, 20, 0,
				3, 65, 76, 76, 21, 0, 6, 0, 0, 0, 0, 72, 0, 22, 0, 4, 0, 0, 0, 0, 23, 0, 4, 71, 44, 66, 95, 24, 0, 1, 0, 25, 0, 1, 0, 34, 0, 12, 84, 104, 97, 110, 100, 101, 114, 115, 116, 111, 114, 109, 35, 0, 4, 60, 87, 62, 32, 38, 0, 11, 83, 84, 50, 78, 72, 48, 50, 46, 84, 88, 84, 42, 0, 7, 65, 87, 85, 50, 46, 49, 48, 46, 0, 8, 18, 20, 63, -58, -36, 85, 47, 64, 47, 0, 8, 60, -67, 82, -106, 33, 70, 64, -64, 0, 0, 0, 84, 111, 32, 32, 58, 32, 65, 76, 76, 13, 10, 70, 114, 111, 109, 58, 32, 83, 84,
				50, 78, 72, 13, 10, 84, 105, 109, 101, 58, 32, 48, 56, 52, 49, 48, 52, 85, 84, 117, -12 });
		Falconsat3Beacon beacon2 = new Falconsat3Beacon();
		beacon2.readExternal(new byte[] { -94, -90, -88, 64, 64, 64, 2, -96, -116, -90, 102, 64, 64, 23, 3, -69, 2, -22, 59, 0, 0, 0, -12, 0, 0, 67, 13, 10, 68, 97, 116, 101, 58, 32, 50, 48, 32, 65, 117, 103, 32, 50, 48, 50, 48, 13, 10, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 13, 10, 72, 101, 108, 108, 111, 32, 97, 108, 108, 44, 13, 10, 118, 101, 114, 121, 32, 100, 97, 114, 107, 32, 99, 108, 111, 117, 100, 115, 32, 119, 105, 116, 104, 32, 104, 101, 97, 118, 121, 32,
				116, 104, 117, 110, 100, 101, 114, 115, 116, 111, 114, 109, 32, 97, 116, 32, 116, 104, 101, 32, 109, 101, 100, 32, 111, 102, 32, 116, 104, 101, 32, 100, 97, 121, 46, 13, 10, 115, 101, 101, 32, 112, 104, 111, 116, 111, 32, 111, 102, 32, 116, 104, 101, 32, 100, 97, 114, 107, 32, 115, 107, 121, 32, 97, 98, 111, 118, 101, 32, 109, 121, 32, 97, 110, 116, 101, 110, 110, 97, 46, 13, 10, 13, 10, 71, 111, 111, 100, 32, 87, 88, 32, 102, 111, 114, 32, 101, 118, 101, 114, 121, 32, 111, 110,
				101, 13, 10, 13, 10, 55, 51, 32, 100, 101, 32, 115, 116, 50, 110, 104, 48, -121 });

		List<Falconsat3Beacon> beacons = new ArrayList<>();
		beacons.add(beacon2);
		beacons.add(beacon1);
		List<PacsatFile> result = FileExtractor.readFiles(beacons);
		assertEquals(1, result.size());
		AssertJson.assertObjectsEqual("PacsatFile.json", result.get(0));
		assertEquals("To  : ALL\r\n" + "From: ST2NH\r\n" + "Time: 084104UTC\r\n" + "Date: 20 Aug 2020\r\n" + "-----------------\r\n" + "Hello all,\r\n" + "very dark clouds with heavy thunderstorm at the med of the day.\r\n" + "see photo of the dark sky above my antenna.\r\n" + "\r\n" + "Good WX for every one\r\n" + "\r\n" + "73 de st2nh", new String(result.get(0).getBody(), StandardCharsets.US_ASCII));
	}

}
