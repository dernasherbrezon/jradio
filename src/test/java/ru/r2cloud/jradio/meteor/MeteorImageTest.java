package ru.r2cloud.jradio.meteor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.PhaseAmbiguityResolver;
import ru.r2cloud.jradio.blocks.AGC;
import ru.r2cloud.jradio.blocks.ClockRecoveryMMComplex;
import ru.r2cloud.jradio.blocks.Constellation;
import ru.r2cloud.jradio.blocks.ConstellationSoftDecoder;
import ru.r2cloud.jradio.blocks.CorrelateAccessCodeTag;
import ru.r2cloud.jradio.blocks.CostasLoop;
import ru.r2cloud.jradio.blocks.FixedLengthTagger;
import ru.r2cloud.jradio.blocks.FloatToChar;
import ru.r2cloud.jradio.blocks.Rail;
import ru.r2cloud.jradio.blocks.RootRaisedCosineFilter;
import ru.r2cloud.jradio.blocks.TaggedStreamToPdu;
import ru.r2cloud.jradio.lrpt.LRPT;
import ru.r2cloud.jradio.lrpt.VCDU;
import ru.r2cloud.jradio.source.WavFileSource;

public class MeteorImageTest {

	private static final Logger LOG = LoggerFactory.getLogger(MeteorImageTest.class);

	@Test
	public void success() throws Exception {
		byte[] vcduData = toBytes("vcdu.bin");
		VCDU vcdu = new VCDU();
		vcdu.readExternal(null, vcduData);
		List<VCDU> data = new ArrayList<>();
		data.add(vcdu);
		MeteorImage image = new MeteorImage(data.iterator());
		BufferedImage actual = image.toBufferedImage();
		try (InputStream is1 = MeteorImageTest.class.getClassLoader().getResourceAsStream("expected8bitsoft.png")) {
			BufferedImage expected = ImageIO.read(is1);
			for (int i = 0; i < expected.getWidth(); i++) {
				for (int j = 0; j < expected.getHeight(); j++) {
					assertEquals(expected.getRGB(i, j), actual.getRGB(i, j));
				}
			}
		}
	}

	@Test
	public void testNoImage() throws Exception {
		MeteorImage image = new MeteorImage(new ArrayList<VCDU>().iterator());
		assertNull(image.toBufferedImage());
	}

	public static byte[] toBytes(String source) throws IOException {
		InputStream is = MeteorImageTest.class.getClassLoader().getResourceAsStream(source);
		if (is == null) {
			throw new IllegalArgumentException("cannot find in classpath: " + source);
		}
		byte[] buffer = IOUtils.toByteArray(is);
		is.close();
		return buffer;
	}

	// performance test
	public static void main(String[] args) throws Exception {
		float symbolRate = 72000f;
		float clockAlpha = 0.01f;
		LOG.info("started");
		String filename = "your file is here";
		WavFileSource source = new WavFileSource(new BufferedInputStream(new FileInputStream(filename)));
		AGC agc = new AGC(source, 1000e-4f, 0.5f, 2.0f, 4000.0f);
		RootRaisedCosineFilter rrcf = new RootRaisedCosineFilter(agc, 1.0f, symbolRate, 0.6f, 361);
		float omega = (float) ((source.getContext().getSampleRate() * 1.0) / (symbolRate * 1.0));
		ClockRecoveryMMComplex clockmm = new ClockRecoveryMMComplex(rrcf, omega, clockAlpha * clockAlpha / 4, 0.5f, clockAlpha, 0.005f);
		CostasLoop costas = new CostasLoop(clockmm, 0.008f, 4, false);
		Constellation constel = new Constellation(new float[] { -1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f, 1.0f, -1.0f }, new int[] { 0, 1, 3, 2 }, 4, 1);
		ConstellationSoftDecoder constelDecoder = new ConstellationSoftDecoder(costas, constel);
		Rail rail = new Rail(constelDecoder, -1.0f, 1.0f);
		FloatToChar f2char = new FloatToChar(rail, 127.0f);
		PhaseAmbiguityResolver phaseAmbiguityResolver = new PhaseAmbiguityResolver(0x035d49c24ff2686bL);

		CorrelateAccessCodeTag correlate = new CorrelateAccessCodeTag(f2char, 17, phaseAmbiguityResolver.getSynchronizationMarkers(), true);
		TaggedStreamToPdu tag = new TaggedStreamToPdu(new FixedLengthTagger(correlate, VCDU.VITERBI_TAIL_SIZE));
		LRPT lrpt = new LRPT(tag, phaseAmbiguityResolver, MeteorImage.METEOR_SPACECRAFT_ID);
		MeteorImage image = new MeteorImage(lrpt);
		LOG.info("decoded");
		BufferedImage actual = image.toBufferedImage();
		if (actual != null) {
			ImageIO.write(actual, "png", new File("./target/output_fixed.png"));
		}
		lrpt.close();
		LOG.info("done");
	}

}
