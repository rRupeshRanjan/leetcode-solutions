package org.solutions.leetcode.customClassDesign;

import org.solutions.leetcode.dataStructures.ListNode;

/**
 * Q. 622 Design Circular Queue
 * <p>
 * Design your implementation of the circular queue. The circular queue is a linear data structure in which the
 * operations are performed based on FIFO (First In First Out) principle and the last position is connected back to the
 * first position to make a circle. It is also called "Ring Buffer".
 * <p>
 * One of the benefits of the circular queue is that we can make use of the spaces in front of the queue.
 * In a normal queue, once the queue becomes full, we cannot insert the next element even if there is a space in front
 * of the queue. But using the circular queue, we can use the space to store new values.
 * <p>
 * Implementation the MyCircularQueue class:
 * <p>
 * MyCircularQueue(k) Initializes the object with the size of the queue to be k.
 * int Front() Gets the front item from the queue. If the queue is empty, return -1.
 * int Rear() Gets the last item from the queue. If the queue is empty, return -1.
 * boolean enQueue(int value) Inserts an element into the circular queue. Return true if the operation is successful.
 * boolean deQueue() Deletes an element from the circular queue. Return true if the operation is successful.
 * boolean isEmpty() Checks whether the circular queue is empty or not.
 * boolean isFull() Checks whether the circular queue is full or not.
 * <p>
 * You must solve the problem without using the built-in queue data structure in your programming language.
 * <p>
 * tags:: design
 */
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