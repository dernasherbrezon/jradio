package ru.r2cloud.jradio;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.blocks.CorrelateAccessCodeTag;
import ru.r2cloud.jradio.blocks.FixedLengthTagger;
import ru.r2cloud.jradio.blocks.TaggedStreamToPdu;
import ru.r2cloud.jradio.lrpt.LRPT;
import ru.r2cloud.jradio.source.InputStreamSource;

public class LRPTTest {

	private LRPT lrpt;

	@Test
	public void success() throws Exception {
		Context context = new Context();
		InputStreamSource float2char = new InputStreamSource(new FileInputStream("/Users/dernasherbrezon/ubuntu_shared/good bit_stream.s"));
		BufferedByteInput buffer = new BufferedByteInput(float2char, 8160 * 2, 8 * 2);
		CorrelateAccessCodeTag correlate = new CorrelateAccessCodeTag(context, buffer, 9, "1111110010100010101101100011110110110000000011011001011110010100", true);
		TaggedStreamToPdu tag = new TaggedStreamToPdu(context, new FixedLengthTagger(context, correlate, 8160 * 2 + 8 * 2));
		lrpt = new LRPT(context, tag, buffer);
		assertTrue(lrpt.hasNext());
		assertNotNull(lrpt.next());
		lrpt.close();
	}

	@After
	public void stop() throws IOException {
		if (lrpt != null) {
			lrpt.close();
		}
	}

}
