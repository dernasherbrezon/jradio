package ru.r2cloud.jradio.lrpt;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ru.r2cloud.jradio.TestUtil;
import ru.r2cloud.jradio.meteor.MeteorImage;
import ru.r2cloud.jradio.util.ThrottledInputStream;

public class LRPTInputStreamTest {
	
	@Test
	public void readThrottledInputStream() throws Exception {
		List<Vcdu> data = new ArrayList<>();
		try (LRPTInputStream is = new LRPTInputStream(new ThrottledInputStream(Vcdu.SIZE / 2, LRPTInputStreamTest.class.getClassLoader().getResourceAsStream("vcdu.bin")))) {
			assertTrue(is.hasNext());
			data.add(is.next());
		}
		assertImage(data);
	}

	@Test
	public void readFromStream() throws Exception {
		List<Vcdu> data = new ArrayList<>();
		try (LRPTInputStream is = new LRPTInputStream(LRPTInputStreamTest.class.getClassLoader().getResourceAsStream("vcdu.bin"))) {
			assertTrue(is.hasNext());
			data.add(is.next());
		}
		assertImage(data);
	}

	private static void assertImage(List<Vcdu> data) throws IOException {
		MeteorImage image = new MeteorImage(data.iterator());
		BufferedImage actual = image.toBufferedImage();
		assertNotNull(actual);
		TestUtil.assertImage("expected8bitsoft.png", actual);
	}

}
