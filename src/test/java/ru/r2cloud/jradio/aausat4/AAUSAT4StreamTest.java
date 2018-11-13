package ru.r2cloud.jradio.aausat4;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.junit.Test;

public class AAUSAT4StreamTest {

	@Test
	public void testSaveAndLoad() throws Exception {
		byte[] raw = new byte[] { 0, 86, 0, -79, -110, 72, 39, 0, 3, 0, 0, 84, 20, 87, 31, 1, 38, 110, 14, -37, -57, -2, -8, 127, 16, 17, -93, 0, 4, 0, 62, 2, 56, -1, -91, 24, 0, 17, 59, 3, 67, -9, 26, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 87, 0, 0, 0, 0, -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 59, 0 };
		AAUSAT4Beacon beacon = new AAUSAT4Beacon();
		beacon.readExternal(raw);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try (AAUSAT4OutputStream os = new AAUSAT4OutputStream(baos)) {
			os.write(beacon);
		}

		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		try (AAUSAT4InputStream is = new AAUSAT4InputStream(bais)) {
			assertTrue(is.hasNext());
			assertNotNull(is.next());
		}
	}

}
