package org.solutions.leetcode.customClassDesign;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/*
 * Q.284
 * Given an Iterator class interface with methods: next() and hasNext(),
 * design and implement a PeekingIterator that support the peek() operation --
 * it essentially peek() at the element that will be returned by the next call to next().
 * */
class PeekingIterator implements Iterator<Integer> {
    Queue<Integer> q = new LinkedList<>();

    public PeekingIterator(Iterator<Integer> iterator) {
        while (iterator.hasNext()) {
            q.add(iterator.next());
        }
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return q.peek();
    }

    @Override
    public Integer next() {
        return q.poll();
    }

    @Override
    public boolean hasNext() {
        return !q.isEmpty();
    }
}