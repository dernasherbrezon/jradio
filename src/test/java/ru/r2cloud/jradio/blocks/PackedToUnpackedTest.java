package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ru.r2cloud.jradio.ArrayByteInput;
import ru.r2cloud.jradio.Endianness;

public class PackedToUnpackedTest {

	@Test
	public void testSuccess() throws Exception {
		@SuppressWarnings("resource")
		PackedToUnpacked pack2Unpack = new PackedToUnpacked(new ArrayByteInput(0b10101010), 1, Endianness.GR_MSB_FIRST);
		assertEquals(0b1, pack2Unpack.readByte());
		assertEquals(0b0, pack2Unpack.readByte());
		assertEquals(0b1, pack2Unpack.readByte());
		assertEquals(0b0, pack2Unpack.readByte());

		pack2Unpack = new PackedToUnpacked(new ArrayByteInput(0b10101010), 1, Endianness.GR_LSB_FIRST);
		assertEquals(0b0, pack2Unpack.readByte());
		assertEquals(0b1, pack2Unpack.readByte());
		assertEquals(0b0, pack2Unpack.readByte());
		assertEquals(0b1, pack2Unpack.readByte());

		pack2Unpack = new PackedToUnpacked(new ArrayByteInput(0b10101010), 2, Endianness.GR_MSB_FIRST);
		assertEquals(0b10, pack2Unpack.readByte());
		assertEquals(0b10, pack2Unpack.readByte());

		pack2Unpack = new PackedToUnpacked(new ArrayByteInput(0b10101010), 2, Endianness.GR_LSB_FIRST);
		assertEquals(0b01, pack2Unpack.readByte());
		assertEquals(0b01, pack2Unpack.readByte());
	}

}
