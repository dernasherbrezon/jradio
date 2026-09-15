package ru.r2cloud.jradio.ccsds;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedInputStream;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.RawBeacon;
import ru.r2cloud.jradio.demod.FskDemodulator;
import ru.r2cloud.jradio.fec.ViterbiTest;
import ru.r2cloud.jradio.source.InputStreamSource;
import ru.r2cloud.jradio.source.WavFileSource;

public class CcsdsBeaconSourceTest {

	private CcsdsBeaconSource<?> input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		WavFileSource source = new WavFileSource(CcsdsBeaconSourceTest.class.getClassLoader().getResourceAsStream("trisat.wav"));
		FskDemodulator demod = new FskDemodulator(source, 9766);
		input = new CcsdsBeaconSource<>(demod, TransferFrame.class);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("CcsdsTelemetryBeacon.json", input.next());
	}

	@Test
	public void testUncoded() throws Exception {
		CcsdsFraming framing = new CcsdsFraming();
		framing.setSyncwordThreshold(5);
		framing.setCoding(Coding.UNCODED);
		framing.setScrambler(ScramblerType.NONE);
		framing.setFrameLength(220);

		Context ctx = new Context();
		ctx.setSoftBits(true);
		InputStreamSource is = new InputStreamSource(new BufferedInputStream(CcsdsBeaconSourceTest.class.getClassLoader().getResourceAsStream("ccsds.s8")), ctx);
		input = new CcsdsBeaconSource<>(is, framing, RawBeacon.class);
		assertTrue(input.hasNext());
		assertEquals(
				"50CBC230EC322B2405ABDF440040C1800080008155832EAC97A858789D3CE4EE6A789DAE10EE6A789D3175EE6A789CDDB7EE6A789C35AEEE6A789C73B6EE6A789C3623EE6A789C73BEEE6A789CFDC7EE6A789C2581EE6A789C9593EE6A789D2805EE6A789D3CE4EE6A789EAE10EE6A789F3175EE6A789FDDB7EE6AB2B2B2B2B2B210AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA8218",
				ViterbiTest.bytesToHex(input.next().getRawData()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidArguments() throws Exception {
		CcsdsFraming framing = new CcsdsFraming();
		framing.setSyncwordThreshold(33);
		framing.setCoding(Coding.UNCODED);
		framing.setScrambler(ScramblerType.NONE);
		framing.setFrameLength(220);
		CcsdsBeaconSource<RawBeacon> source = createSource(framing);
		source.close();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUnsupportedCoding() throws Exception {
		CcsdsFraming framing = new CcsdsFraming();
		framing.setSyncwordThreshold(5);
		framing.setCoding(Coding.LDPC2048_1024);
		framing.setScrambler(ScramblerType.NONE);
		framing.setFrameLength(220);
		CcsdsBeaconSource<RawBeacon> source = createSource(framing);
		source.close();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUnsupportedScrambler() throws Exception {
		CcsdsFraming framing = new CcsdsFraming();
		framing.setSyncwordThreshold(5);
		framing.setCoding(Coding.UNCODED);
		framing.setScrambler(ScramblerType.IESS);
		framing.setFrameLength(220);
		CcsdsBeaconSource<RawBeacon> source = createSource(framing);
		source.close();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidFramelegnth() throws Exception {
		CcsdsFraming framing = new CcsdsFraming();
		framing.setSyncwordThreshold(5);
		framing.setCoding(Coding.UNCODED);
		framing.setScrambler(ScramblerType.CCITT);
		framing.setFrameLength(0);
		CcsdsBeaconSource<RawBeacon> source = createSource(framing);
		source.close();
	}
	
	@Test
	public void testLongToBytes() {
		long value = 0x1acffc1dL;
		byte[] bytes = CcsdsBeaconSource.longToBytes(value, 4 * 8);
		assertEquals((byte) 0x1a, bytes[0]);
		assertEquals((byte) 0xcf, bytes[1]);
		assertEquals((byte) 0xfc, bytes[2]);
		assertEquals((byte) 0x1d, bytes[3]);
		
		assertEquals(value, CcsdsBeaconSource.bytesToLong(bytes, 0, bytes.length));
	}

	private static CcsdsBeaconSource<RawBeacon> createSource(CcsdsFraming framing) {
		Context ctx = new Context();
		ctx.setSoftBits(true);
		InputStreamSource is = new InputStreamSource(null, ctx);
		CcsdsBeaconSource<RawBeacon> source = new CcsdsBeaconSource<>(is, framing, RawBeacon.class);
		return source;
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}

}
