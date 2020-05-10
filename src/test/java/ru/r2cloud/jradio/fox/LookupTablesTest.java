package ru.r2cloud.jradio.fox;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LookupTablesTest {

	@Test
	public void testExact() {
		assertEquals(3.75f, LookupTables.lookup("FOX1A_IHUVBATT", 2712), 0.0f);
	}

	@Test
	public void testInterpolation() {
		assertEquals(3.2086208f, LookupTables.lookup("FOX1A_IHUVBATT", 2400), 0.0f);
	}

	@Test
	public void testExtrapolationFromLast() {
		assertEquals(6.6442776f, LookupTables.lookup("FOX1A_IHUVBATT", 4500), 0.0f);
	}

	@Test
	public void testExtrapolationBeforeFirst() {
		assertEquals(-0.1253783f, LookupTables.lookup("FOX1A_IHUVBATT", -100), 0.0f);
	}
}
