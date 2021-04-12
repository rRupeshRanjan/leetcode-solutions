package org.solutions.leetcode.customClassDesign;

import org.solutions.leetcode.dataStructures.ListNode;

class MyCircularQueue {
    private ListNode rear, head;

    public MyCircularQueue(int k) {
        rear = head = new ListNode(-1);
        for (int i = 1; i < k; i++) {
            ListNode temp = new ListNode(-1);
            rear.setNext(temp);
            temp.setPrev(rear);
            rear = rear.getNext();
        }
        rear.setNext(head);
        head.setPrev(rear);
        rear = head;
    }

    public boolean enQueue(int value) {
        if (isFull())
            return false;

        if (rear.getVal() != -1)
            rear = rear.getNext();
        rear.setVal(value);
        return true;
    }

    public boolean deQueue() {
        if (isEmpty())
            return false;

        head.setVal(-1);
        if (head != rear)
            head = head.getNext();
        return true;
    }

    public int front() {
        return Math.max(-1, head.getVal());
    }

    public int rear() {
        return Math.max(-1, rear.getVal());
    }

    public boolean isEmpty() {
        return (head.getVal() == -1 && rear.getVal() == -1);
    }

    public boolean isFull() {
        return rear.getNext().getVal() != -1;
    }
}