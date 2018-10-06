package ru.r2cloud.jradio;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.blocks.BinarySlicer;
import ru.r2cloud.jradio.blocks.ClockRecoveryMM;
import ru.r2cloud.jradio.blocks.CorrelateAccessCodeTag;
import ru.r2cloud.jradio.blocks.FixedLengthTagger;
import ru.r2cloud.jradio.blocks.UnpackedToPacked;
import ru.r2cloud.jradio.source.WavFileSource;
import ru.r2cloud.jradio.source.WavFileSourceTest;

public class UnpackedToPackedTest {

	private UnpackedToPacked source;

	@Test
	public void testSuccess() throws Exception {
		source = new UnpackedToPacked(new FixedLengthTagger(new CorrelateAccessCodeTag(new BinarySlicer(new ClockRecoveryMM(new WavFileSource(WavFileSourceTest.class.getClassLoader().getResourceAsStream("aausat-4.wav")), 20.0f, (float) (0.25 * 0.175 * 0.175), 0.005f, 0.175f, 0.005f)), 8, "010011110101101000110100010000110101010101000010"), 2008), 1, Endianness.GR_MSB_FIRST, Byte.class);
		try (InputStream is = BinarySlicerTest.class.getClassLoader().getResourceAsStream("UnpackedToPacked.bin")) {
			int expected = -1;
			while ((expected = is.read()) != -1) {
				byte actual = source.readByte();
				assertEquals((byte) expected, actual);
			}
		}
		Tag tag = getFirst(source.getContext());
		assertEquals(251, tag.get(FixedLengthTagger.LENGTH));
	}

	@After
	public void stop() throws Exception {
		if (source != null) {
			source.close();
		}
	}

	public static Tag getFirst(Context context) {
		Map<String, Tag> tags = context.getTags();
		assertEquals(1, tags.size());
		Iterator<Entry<String, Tag>> it = tags.entrySet().iterator();
		if (it.hasNext()) {
			return it.next().getValue();
		}
		return null;
	}

}
