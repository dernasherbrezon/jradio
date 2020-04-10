package ru.r2cloud.jradio.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BoundedQueueTest {

	@Test
	public void testBasic() {
		BoundedQueue queue = new BoundedQueue(3);
		queue.add(1.0f);
		queue.add(2.0f);
		queue.add(3.0f);
		assertEquals(1.0f, queue.poll(), 0.0f);
		assertEquals(2.0f, queue.poll(), 0.0f);
		assertEquals(3.0f, queue.poll(), 0.0f);
	}

	@Test(expected = IllegalStateException.class)
	public void testNotEnoughElements() {
		BoundedQueue queue = new BoundedQueue(3);
		queue.add(1.0f);
		assertEquals(1.0f, queue.poll(), 0.0f);
		queue.poll();
	}

	@Test(expected = IllegalStateException.class)
	public void testOverflow() {
		int queueSize = 3;
		BoundedQueue queue = new BoundedQueue(queueSize);
		for (int i = 0; i < (queueSize + 1); i++) {
			queue.add(i);
		}
	}

}
