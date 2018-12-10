package ru.r2cloud.jradio.sat3cat1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.Endianness;
import ru.r2cloud.jradio.blocks.BinarySlicer;
import ru.r2cloud.jradio.blocks.ClockRecoveryMM;
import ru.r2cloud.jradio.blocks.CorrelateAccessCodeTag;
import ru.r2cloud.jradio.blocks.FixedLengthTagger;
import ru.r2cloud.jradio.blocks.LowPassFilter;
import ru.r2cloud.jradio.blocks.MultiplyConst;
import ru.r2cloud.jradio.blocks.TaggedStreamToPdu;
import ru.r2cloud.jradio.blocks.UnpackedToPacked;
import ru.r2cloud.jradio.blocks.Window;
import ru.r2cloud.jradio.source.WavFileSource;

public class Sat3Cat1Test {

	private Sat3Cat1 input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		float gainMu = 0.175f * 3;
		WavFileSource source = new WavFileSource(Sat3Cat1Test.class.getClassLoader().getResourceAsStream("sat_3cat_1.wav"));
		MultiplyConst mc = new MultiplyConst(source, 10.0f);
		LowPassFilter lpf = new LowPassFilter(mc, 1.0, 4800, 600, Window.WIN_HAMMING, 6.76);
		ClockRecoveryMM clockRecovery = new ClockRecoveryMM(lpf, lpf.getContext().getSampleRate() / 9600, (float) (0.25 * gainMu * gainMu), 0.5f, gainMu, 0.005f);
		BinarySlicer bs = new BinarySlicer(clockRecovery);
		CorrelateAccessCodeTag correlateTag = new CorrelateAccessCodeTag(bs, 4, "11010011100100011101001110010001", false);
		TaggedStreamToPdu pdu = new TaggedStreamToPdu(new UnpackedToPacked(new FixedLengthTagger(correlateTag, 255 * 8), 1, Endianness.GR_MSB_FIRST, Byte.class));
		input = new Sat3Cat1(pdu);
		assertTrue(input.hasNext());
		Sat3Cat1Beacon beacon = input.next();
		assertNotNull(beacon);
		assertEquals(1476, beacon.getBeginSample());
		assertEquals(0, beacon.getSensorId());
		assertEquals(1543658358, beacon.getSpacecraftTime());
		assertEquals(88, beacon.getStateOfCharge());
		assertEquals(BeaconType.BEACON_TYPE_TEMP, beacon.getType());
		List<MetricValue> temperatureSensors = beacon.getTemperaturSensors();
		assertEquals(62, temperatureSensors.size());
		MetricValue value = temperatureSensors.get(0);
		assertEquals(1543658340, value.getTime());
		assertEquals(284.0, value.getValue(), 0.0f);
		value = temperatureSensors.get(1);
		assertEquals(1543658195, value.getTime());
		assertEquals(281.0, value.getValue(), 0.0f);
		value = temperatureSensors.get(2);
		assertEquals(1543658050, value.getTime());
		assertEquals(281.0, value.getValue(), 0.0f);
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}
}
