package org.solutions.leetcode.customClassDesign;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyCircularQueueTest {

    @Test
    void testMyCircularQueue() {
        MyCircularQueue mcq = new MyCircularQueue(3);
        assertFalse(mcq.deQueue());
        assertTrue(mcq.enQueue(1));
        assertTrue(mcq.enQueue(2));
        assertTrue(mcq.enQueue(3));
        assertFalse(mcq.enQueue(4));
        assertEquals(3, mcq.rear());
        assertTrue(mcq.isFull());
        assertTrue(mcq.deQueue());
        assertTrue(mcq.enQueue(4));
        assertEquals(4, mcq.rear());
        assertEquals(2, mcq.front());
    }
}