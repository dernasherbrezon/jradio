package ru.r2cloud.jradio;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;

import org.junit.Test;

public class BeaconOutputStreamTest {

	@Test
	public void testIgnoreEmptyBeacons() throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		BeaconOutputStream bos = new BeaconOutputStream(baos);
		bos.write(new RawBeacon());
		bos.close();
		assertEquals(0, baos.toByteArray().length);
	}

}
