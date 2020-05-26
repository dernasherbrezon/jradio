package ru.r2cloud.jradio.chomptt;

import java.io.File;
import java.io.FileWriter;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ru.r2cloud.jradio.fec.ViterbiTest;

public class ChompttBeaconTest {
	
	@Test
	public void testSpacecraftTelemetry() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("AE9264B0ACA860AE9264B0ACA860AE9264B0ACA86103F043484F4D50545C3C7E4F622D5E516224472E4F3A4F332C482121604C55212B6C3A40212674244D215F212F632160266B4E216F613B5221292A412321313C676822266F3755213230416D213C3C293C2121212327213F684643355B6D2E5E2B4F605E5935685C557E3E");
		ChompttBeacon result = new ChompttBeacon();
		result.readBeacon(data);
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		FileWriter w = new FileWriter(new File("./src/test/resources/expected/ChompttBeaconSpacecraft.json"));
		gson.toJson(result, w);
		w.close();
//		AssertJson.assertObjectsEqual("ChompttBeaconSpacecraft.json", result);

	}

}
