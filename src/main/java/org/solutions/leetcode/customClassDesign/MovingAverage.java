package org.solutions.leetcode.customClassDesign;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Q. 346 Moving Average from Data Stream
 * <p>
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
 * <p>
 * Implement the MovingAverage class:
 * <p>
 * MovingAverage(int size) Initializes the object with the size of the window size.
 * double next(int val) Returns the moving average of the last size values of the stream.
 * <p>
 * tags:: design
 */
public class MovingAverage {
    Deque<Integer> q;
    int sum, size;

    /**
     * Initialize your data structure here.
     */
    public MovingAverage(int size) {
        this.size = size;
        this.sum = 0;
        this.q = new LinkedList<>();
    }

    public double next(int val) {
        if (q.size() >= size)
            sum -= q.pollLast();

        q.addFirst(val);
        sum += val;
        return sum / (double) q.size();
    }
}
