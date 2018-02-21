package ru.r2cloud.jradio.meteor;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import ru.r2cloud.jradio.lrpt.VCDU;

public class MeteorImageTest {

	@Test
	public void success() throws Exception {
		byte[] vcduData = toBytes("vcdu.bin");
		VCDU vcdu = new VCDU();
		vcdu.readExternal(null, vcduData);
		List<VCDU> data = new ArrayList<>();
		data.add(vcdu);
		MeteorImage image = new MeteorImage(data.iterator());
	}

	private static byte[] toBytes(String source) throws IOException {
		InputStream is = MeteorImageTest.class.getClassLoader().getResourceAsStream(source);
		if (is == null) {
			throw new IllegalArgumentException("cannot find in classpath: " + source);
		}
		byte[] buffer = IOUtils.toByteArray(is);
		is.close();
		return buffer;
	}

}
