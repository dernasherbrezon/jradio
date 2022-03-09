package ru.r2cloud.jradio.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CircularByteArrayTest {

	@Test
	public void testGetByIndex() {
		CircularByteArray array = new CircularByteArray(3);
		array.add((byte) 1);
		assertEquals(0, array.get(0));
		assertEquals(0, array.get(1));
		assertEquals(1, array.get(2));
		array.add((byte) 2);
		assertEquals(0, array.get(0));
		assertEquals(1, array.get(1));
		assertEquals(2, array.get(2));
		array.add((byte) 3);
		assertEquals(1, array.get(0));
		assertEquals(2, array.get(1));
		assertEquals(3, array.get(2));
	}
	
	@Test
	public void testCopy() {
		CircularByteArray array = new CircularByteArray(3);
		array.add((byte) 1);
		array.add((byte) 2);
		array.add((byte) 3);
		array.add((byte) 4);
		byte[] copy = array.getCopy();
		assertEquals(2, copy[0]);
		assertEquals(3, copy[1]);
		assertEquals(4, copy[2]);
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testArrayIndexOutOfBounds() {
		CircularByteArray array = new CircularByteArray(3);
		array.add((byte) 1);
		array.get(3);
	}

}
