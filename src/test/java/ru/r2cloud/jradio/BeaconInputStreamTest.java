package ru.r2cloud.jradio;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.junit.Test;

public class BeaconInputStreamTest {

	@Test
	public void testReadWrite() throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		BeaconOutputStream bos = new BeaconOutputStream(baos);

		RawBeacon data = new RawBeacon();
		data.setBeginMillis(1L);
		data.setBeginSample(1L);
		data.setRawData(new byte[] { 0x01, 0x02 });
		RxMetadata meta = new RxMetadata();
		meta.setRssi(1.23f);
		meta.setSnr(1.22f);
		meta.setFrequencyError(1001l);
		meta.setBaud(9600);
		data.setRxMeta(meta);
		data.setEndSample(2L);

		bos.write(data);
		bos.close();

		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		BeaconInputStream<RawBeacon> bis = new BeaconInputStream<>(bais, RawBeacon.class);
		assertTrue(bis.hasNext());
		Beacon actual = bis.next();
		assertNotNull(actual);
		assertEquals(data.getBeginMillis(), actual.getBeginMillis());
		assertEquals(data.getBeginSample(), actual.getBeginSample());
		assertEquals(data.getRxMeta().getRssi(), actual.getRxMeta().getRssi(), 0.0001);
		assertEquals(data.getRxMeta().getSnr(), actual.getRxMeta().getSnr(), 0.0001);
		assertEquals(data.getRxMeta().getFrequencyError(), actual.getRxMeta().getFrequencyError());
		assertEquals(data.getRxMeta().getBaud(), actual.getRxMeta().getBaud());
		assertEquals(data.getEndSample(), actual.getEndSample());
		assertArrayEquals(data.getRawData(), actual.getRawData());
		assertFalse(bis.hasNext());
		bis.close();
	}

	@Test
	public void testv2Format() throws Exception {
		byte[] data = new byte[] { 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 2, 1, 2, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 63, -99, 112, -92, 63, -100, 40, -10, 0, 0, 0, 0, 0, 0, 3, -23, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		BeaconInputStream<RawBeacon> bis = new BeaconInputStream<>(bais, RawBeacon.class);
		assertTrue(bis.hasNext());
		Beacon actual = bis.next();
		assertNotNull(actual);
		assertEquals(1L, actual.getBeginMillis());
		assertEquals(1L, actual.getBeginSample());
		assertArrayEquals(new byte[] { 0x01, 0x02 }, actual.getRawData());
		assertNotNull(actual.getRxMeta());
		assertEquals(1.23f, actual.getRxMeta().getRssi(), 0.0f);
		assertEquals(1.22f, actual.getRxMeta().getSnr(), 0.0f);
		assertEquals(1001L, actual.getRxMeta().getFrequencyError().longValue());
		assertFalse(bis.hasNext());
		bis.close();
	}

	@Test
	public void testv1Format() throws Exception {
		byte[] data = new byte[] { 0, 0, 0, 2, 1, 2, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1 };
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		BeaconInputStream<RawBeacon> bis = new BeaconInputStream<>(bais, RawBeacon.class);
		assertTrue(bis.hasNext());
		Beacon actual = bis.next();
		assertNotNull(actual);
		assertEquals(1L, actual.getBeginMillis());
		assertEquals(1L, actual.getBeginSample());
		assertArrayEquals(new byte[] { 0x01, 0x02 }, actual.getRawData());
		assertNull(actual.getRxMeta());
		assertFalse(bis.hasNext());
		bis.close();
	}

}
