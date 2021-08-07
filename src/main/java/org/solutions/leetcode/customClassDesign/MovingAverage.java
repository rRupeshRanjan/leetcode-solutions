package org.solutions.leetcode.customClassDesign;

import java.util.Deque;
import java.util.LinkedList;

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
