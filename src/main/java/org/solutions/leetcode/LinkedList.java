package org.solutions.leetcode;

import org.solutions.leetcode.dataStructures.LinkedListNode;

public class LinkedList {

    /*
    * Q.141
    * Detect if there is a loop in linked list
    * */
    public boolean hasCycle(LinkedListNode head) {
        LinkedListNode slow = head;
        LinkedListNode fast = head;

        while(slow != null && fast != null && fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext();
            if (slow == fast) {
                return true;
            }
        }

        return false;
    }
}
