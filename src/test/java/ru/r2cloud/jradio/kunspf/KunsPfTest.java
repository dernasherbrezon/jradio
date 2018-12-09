package ru.r2cloud.jradio.kunspf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.Endianness;
import ru.r2cloud.jradio.blocks.BinarySlicer;
import ru.r2cloud.jradio.blocks.ClockRecoveryMM;
import ru.r2cloud.jradio.blocks.CorrelateAccessCodeTag;
import ru.r2cloud.jradio.blocks.FixedLengthTagger;
import ru.r2cloud.jradio.blocks.LowPassFilter;
import ru.r2cloud.jradio.blocks.TaggedStreamToPdu;
import ru.r2cloud.jradio.blocks.UnpackedToPacked;
import ru.r2cloud.jradio.blocks.Window;
import ru.r2cloud.jradio.source.WavFileSource;

public class KunsPfTest {

	private KunsPf input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		float gainMu = 0.175f * 3;
		WavFileSource source = new WavFileSource(new BufferedInputStream(new FileInputStream("1kuns_pf.wav")));
		LowPassFilter lpf = new LowPassFilter(source, 1.0, 1000, 600, Window.WIN_HAMMING, 6.76);
		ClockRecoveryMM clockRecovery = new ClockRecoveryMM(lpf, lpf.getContext().getSampleRate() / 1200, (float) (0.25 * gainMu * gainMu), 0.5f, gainMu, 0.005f);
		BinarySlicer bs = new BinarySlicer(clockRecovery);
		CorrelateAccessCodeTag correlateTag = new CorrelateAccessCodeTag(bs, 4, "10010011000010110101000111011110", false);
		TaggedStreamToPdu pdu = new TaggedStreamToPdu(new UnpackedToPacked(new FixedLengthTagger(correlateTag, 255 * 8), 1, Endianness.GR_MSB_FIRST, Byte.class));
		input = new KunsPf(pdu);
		assertTrue(input.hasNext());
		KunsPfBeacon beacon = input.next();
		assertNotNull(beacon);

		assertEquals(10, beacon.getAngVelocityMag());
		assertEquals(0, beacon.getBatteryMode());
		assertEquals(0, beacon.getBatteryTemp());
		assertEquals(8262, beacon.getBatteryVoltage());
		assertEquals(4274, beacon.getBeaconCounter());
		assertEquals(33289, beacon.getBeginSample());
		assertEquals(0, beacon.getBootCause());
		assertEquals(0, beacon.getEpsBootCount());
		assertEquals(3, beacon.getEpsBattMode());
		assertEquals(7, beacon.getEpsBootCause());
		assertEquals(1, beacon.getEpsTemp0());
		assertEquals(3, beacon.getEpsTemp1());
		assertEquals(2, beacon.getEpsTemp2());
		assertEquals(2, beacon.getEpsTemp3());
		assertEquals(0, beacon.getGyroX(), 0.0);
		assertEquals(0, beacon.getGyroY(), 0.0);
		assertEquals(0, beacon.getGyroZ(), 0.0);
		assertEquals(0, beacon.getLastRfError());
		assertEquals(0, beacon.getLastRxRfPower());
		assertEquals(288, beacon.getMagnetometerX(), 0.0);
		assertEquals(0, beacon.getMagnetometerY(), 0.0);
		assertEquals(0, beacon.getMagnetometerZ(), 0.0);
		assertEquals(89, beacon.getMainAxisOfRotation());
		
		assertEquals(1, beacon.getObcTemp0(), 0.0);
		assertEquals(1, beacon.getObcTemp1(), 0.0);
		assertEquals(0, beacon.getRadioAmplifierTemp(), 0.0);
		assertEquals(0, beacon.getRadioBootCount());
		assertEquals(4, beacon.getRadioPATemp());
		
		assertEquals(0, beacon.getRxCount());
		assertEquals(0, beacon.getSolarPanelCurrent());
		assertEquals(0, beacon.getSolarPanelRegulatorCurrent0());
		assertEquals(0, beacon.getSolarPanelRegulatorCurrent1());
		assertEquals(0, beacon.getSolarPanelRegulatorCurrent2());
		
		assertEquals(0, beacon.getSolarPanelRegulatorTemp0());
		assertEquals(0, beacon.getSolarPanelRegulatorTemp0());
		assertEquals(0, beacon.getSolarPanelRegulatorTemp0());

		assertEquals(0, beacon.getSolarPanelTemp0(), 0.0);
		assertEquals(0, beacon.getSolarPanelTemp1(), 0.0);
		assertEquals(0, beacon.getSolarPanelTemp2(), 0.0);
		assertEquals(0, beacon.getSolarPanelTemp3(), 0.0);
		assertEquals(0, beacon.getSolarPanelTemp4(), 0.0);
		assertEquals(0, beacon.getSolarPanelTemp5(), 0.0);
		
		assertEquals(2448, beacon.getSolarPanelVoltageX());
		assertEquals(2448, beacon.getSolarPanelVoltageY());
		assertEquals(2432, beacon.getSolarPanelVoltageZ());
		
		assertEquals(0, beacon.getSunSensor0());
		assertEquals(0, beacon.getSunSensor1());
		assertEquals(0, beacon.getSunSensor2());
		assertEquals(0, beacon.getSunSensor3());
		assertEquals(0, beacon.getSunSensor4());
		assertEquals(0, beacon.getSunSensor5());
		
		assertEquals(0, beacon.getSystemCurrent());
		assertEquals(80, beacon.getSystemInputCurrent());
		assertEquals(0, beacon.getTimestamp());
		assertEquals(45584, beacon.getTxCount());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}
}
