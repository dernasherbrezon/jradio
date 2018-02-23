package ru.r2cloud.jradio.meteor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import ru.r2cloud.jradio.BufferedByteInput;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.blocks.CorrelateAccessCodeTag;
import ru.r2cloud.jradio.blocks.FixedLengthTagger;
import ru.r2cloud.jradio.blocks.TaggedStreamToPdu;
import ru.r2cloud.jradio.lrpt.LRPT;
import ru.r2cloud.jradio.source.InputStreamSource;

public class MeteorImageTest {

	@Test
	public void success() throws Exception {
		// byte[] vcduData = toBytes("vcdu.bin");
		// VCDU vcdu = new VCDU();
		// vcdu.readExternal(null, vcduData);
		// List<VCDU> data = new ArrayList<>();
		// data.add(vcdu);
		// MeteorImage image = new MeteorImage(data.iterator());

		Context context = new Context();
		InputStreamSource float2char = new InputStreamSource(new FileInputStream("/Users/dernasherbrezon/ubuntu_shared/good bit_stream.s"));
		BufferedByteInput buffer = new BufferedByteInput(float2char, 8160 * 2, 8 * 2);
		CorrelateAccessCodeTag correlate = new CorrelateAccessCodeTag(context, buffer, 9, "1111110010100010101101100011110110110000000011011001011110010100", true);
		TaggedStreamToPdu tag = new TaggedStreamToPdu(context, new FixedLengthTagger(context, correlate, 8160 * 2 + 8 * 2));
		LRPT lrpt = new LRPT(context, tag, buffer);
		MeteorImage image = new MeteorImage(lrpt);
		BufferedImage off_Image = image.toBufferedImage();
		ImageIO.write(off_Image, "jpg", new File("result.jpg"));
	}

//	private static byte[] toBytes(String source) throws IOException {
//		InputStream is = MeteorImageTest.class.getClassLoader().getResourceAsStream(source);
//		if (is == null) {
//			throw new IllegalArgumentException("cannot find in classpath: " + source);
//		}
//		byte[] buffer = IOUtils.toByteArray(is);
//		is.close();
//		return buffer;
//	}

}
