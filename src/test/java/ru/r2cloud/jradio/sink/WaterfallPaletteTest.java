package ru.r2cloud.jradio.sink;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class WaterfallPaletteTest {
	
	private WaterfallPalette palette;

	@Test
	public void testInterpolation() {
		assertEquals(0xffff0000, palette.getRGB(-20.0f));
		assertEquals(0xff0000e7, palette.getRGB(-140.0f));
		
		assertEquals(0xffff003e, palette.getRGB(-10.0f));
	}
	
	@Test
	public void testCornerCases() {
		assertEquals(0xff000000, palette.getRGB(-160.0f));
		assertEquals(0xff000000, palette.getRGB(-190.0f));
		assertEquals(0xffff007c, palette.getRGB(0.0f));
		assertEquals(0xffff007c, palette.getRGB(10.0f));
	}
	
	@Before
	public void start() {
		palette = new WaterfallPalette(0.0f, -160.0f, 0x000000, 0x0000e7, 0x0094ff, 0x00ffb8, 0x2eff00, 0xffff00, 0xff8800, 0xff0000, 0xff007c);
	}
	
}
