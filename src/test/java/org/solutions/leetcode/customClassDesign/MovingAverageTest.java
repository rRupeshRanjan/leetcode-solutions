package org.solutions.leetcode.customClassDesign;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MovingAverageTest {
    @Test
    void testMovingAverage() {
        MovingAverage movingAverage = new MovingAverage(3);
        assertEquals(1.0, movingAverage.next(1));
        assertEquals(5.5, movingAverage.next(10));
        assertEquals(4.666666666666667, movingAverage.next(3));
        assertEquals(6.0, movingAverage.next(5));
    }
}