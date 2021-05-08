package ru.r2cloud.jradio.bobcat1;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;
import java.io.FileWriter;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.fec.ViterbiTest;

public class Bobcat1BeaconTest {

	@Test
	public void testLeopBeacon() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("000000005738505a5300424f424341542d3100407800ab00ee032e040400005ba602f600000100009d010f04f6007b007a0086fea90082ff585ff05f1e");
		Bobcat1Beacon result = new Bobcat1Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Bobcat1LeopFrame.json", result);
	}

	@Test
	public void testLongBeacon() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("00000000010102070cc22c5ff08c6e00045738505a5300424f424341542d3100409800e200bc01590697009f195401a51954000100010000015703032e0404000088f6ffc700060071005700870064005fff68ff40ff0efede002dfffdfffdfffefffcfffbfffa03e0000000fbe00000f2075ff08c6e0005ffccfffdff9800469d450016895e31a20f7d039a95b602f600000100");
		Bobcat1Beacon result = new Bobcat1Beacon();
		result.readBeacon(data);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		FileWriter w = new FileWriter(new File("./src/test/resources/expected/Bobcat1LongFrame.json"));
		gson.toJson(result, w);
		w.close();

		AssertJson.assertObjectsEqual("Bobcat1LongFrame.json", result);
	}

	@Test
	public void testUnknownFrame() throws Exception {
		byte[] data = ViterbiTest.hexStringToByteArray("000000000010");
		Bobcat1Beacon result = new Bobcat1Beacon();
		result.readBeacon(data);
		AssertJson.assertObjectsEqual("Bobcat1Unknown.json", result);
	}

	@Test
	public void testPojo() {
		assertThat(Bobcat1Beacon.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(LeopFrame.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(LongFrame.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		assertThat(BeaconElementHeader.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}
