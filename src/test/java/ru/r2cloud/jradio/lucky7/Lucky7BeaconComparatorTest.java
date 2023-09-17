package ru.r2cloud.jradio.lucky7;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class Lucky7BeaconComparatorTest {

	@Test
	public void testSuccess() throws Exception {
		List<Lucky7Beacon> data = new ArrayList<>();
		data.add(create(2, 1422));
		data.add(create(3, 1422));
		data.add(create(4, 1422));
		data.add(create(2, 1422));
		data.add(create(1, 720));
		Collections.sort(data, Lucky7BeaconComparator.INSTANCE);
		assertEquals(2, data.get(1).getImageChunk().intValue());
		assertEquals(2, data.get(2).getImageChunk().intValue());
	}
	
	private static Lucky7Beacon create(int chunkNumber, int totalChunks) {
		Lucky7Beacon result = new Lucky7Beacon();
		result.setImageChunk(Integer.valueOf(chunkNumber));
		result.setImageTotalChunks(Integer.valueOf(totalChunks));
		return result;
	}
}
