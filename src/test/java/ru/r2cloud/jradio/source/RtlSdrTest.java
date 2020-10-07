package ru.r2cloud.jradio.source;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import ru.r2cloud.jradio.sink.WavFileSink;

public class RtlSdrTest {

	private RtlSdr rtl;

	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();

	@Test
	public void basic() throws Exception {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		dos.writeByte(10);
		dos.writeByte(20);
		dos.writeByte(30);
		dos.writeByte(40);

		rtl = new RtlSdr(new ByteArrayInputStream(baos.toByteArray()), 0.0f);
		assertEquals(-0.91796875, rtl.readFloat(), 0.0000001);
		assertEquals(0.0f, rtl.getContext().getCurrentSample().getValue(), 0.0f);
		assertEquals(-0.83984375, rtl.readFloat(), 0.0000001);
		assertEquals(1.0f, rtl.getContext().getCurrentSample().getValue(), 0.0f);
		assertEquals(-0.76171875, rtl.readFloat(), 0.0000001);
		assertEquals(1.0f, rtl.getContext().getCurrentSample().getValue(), 0.0f);
		assertEquals(-0.68359375, rtl.readFloat(), 0.0000001);
		assertEquals(2.0f, rtl.getContext().getCurrentSample().getValue(), 0.0f);
		try {
			rtl.readFloat();
			fail("eof expected");
		} catch (EOFException e) {
			// do nothing
		}
	}

	@Test
	public void testConvertFromIqToWav() throws Exception {
		int numberOfBytes = 100;
		File rtlSdrSource = new File(tempFolder.getRoot(), "test.bin");
		try (FileOutputStream fos = new FileOutputStream(rtlSdrSource)) {
			for (int i = 0; i < numberOfBytes; i++) {
				fos.write(i);
			}
		}
		rtl = new RtlSdr(rtlSdrSource, 240000);
		WavFileSink sink = null;
		FileOutputStream fos = null;
		File result = new File(tempFolder.getRoot(), "test.wav");
		try {
			sink = new WavFileSink(rtl);
			fos = new FileOutputStream(result);
			sink.process(fos);
		} finally {
			if (sink != null) {
				sink.close();
			}
			if (fos != null) {
				fos.close();
			}
		}
		try (WavFileSource source = new WavFileSource(new BufferedInputStream(new FileInputStream(result)))) {
			assertEquals(240000.0, source.getContext().getSampleRate(), 0.0);
			assertEquals(numberOfBytes / 2, source.getContext().getTotalSamples().intValue());
			assertEquals(8, source.getContext().getSampleSizeInBits());
			assertEquals(2, source.getContext().getChannels());
		}
	}

	@After
	public void stop() throws Exception {
		if (rtl != null) {
			rtl.close();
		}
	}

}
