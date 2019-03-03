package ru.r2cloud.jradio;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.junit.Test;

public class BeaconInputStreamTest {

	@Test
	public void testReadWrite() throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		BeaconOutputStream bos = new BeaconOutputStream(baos);

		EmptyBeacon data = new EmptyBeacon();
		data.setBeginMillis(1L);
		data.setBeginSample(1L);
		data.setRawData(new byte[] { 0x01, 0x02 });

		bos.write(data);
		bos.close();

		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		BeaconInputStream<EmptyBeacon> bis = new BeaconInputStream<>(bais, EmptyBeacon.class);
		assertTrue(bis.hasNext());
		Beacon actual = bis.next();
		assertNotNull(actual);
		assertEquals(data.getBeginMillis(), actual.getBeginMillis());
		assertEquals(data.getBeginSample(), actual.getBeginSample());
		assertArrayEquals(data.getRawData(), actual.getRawData());
		assertFalse(bis.hasNext());
		bis.close();
	}

}
