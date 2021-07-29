package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.ArrayByteInput;

public class HdlcTransmitterTest {

	private HdlcReceiver receiver;
	private HdlcTransmitter transmitter;

	@Test
	public void testSendReceive() throws Exception {
		byte[] data = new byte[] { (byte) 0xF1, (byte) 0xA7, (byte) 0x7C, 0x56 };
		transmitter = new HdlcTransmitter();
		byte[] encoded = transmitter.encode(data);
		receiver = new HdlcReceiver(new ArrayByteInput(encoded), 10);
		byte[] actual = receiver.readBytes();
		assertEquals(data.length, actual.length);
		for (int i = 0; i < data.length; i++) {
			assertEquals(data[i], actual[i]);
		}
	}

	@After
	public void stop() throws Exception {
		if (receiver != null) {
			receiver.close();
		}
	}

}
