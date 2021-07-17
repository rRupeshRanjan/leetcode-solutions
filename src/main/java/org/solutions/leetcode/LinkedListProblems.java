package org.solutions.leetcode;

import org.solutions.leetcode.dataStructures.ListNode;

import java.util.*;

public class LinkedListProblems {

    /**
     * Q.141
     * Detect if there is a loop in linked list
     * <p>
     * Tags:: linkedList
     */
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (slow != null && fast != null && fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
            if (slow == fast) {
                return true;
            }
        }

        return false;
    }

    /**
     * Q.138
     * A linked list of length n is given such that each node contains an additional random pointer,
     * which could point to any node in the list, or null. Construct a deep copy of the list.
     * The deep copy should consist of exactly n brand new nodes, where each new node has its value set to the value of
     * its corresponding original node. Both the next and random pointer of the new nodes should point to new nodes in
     * the copied list such that the pointers in the original list and copied list represent the same list state.
     * None of the pointers in the new list should point to nodes in the original list.
     * <p>
     * Tags:: linkedList
     */
    public ListNode copyRandomList(ListNode head) {
        Map<ListNode, ListNode> copyMap = new HashMap<>();
        ListNode head1 = head;
        ListNode copy = new ListNode(-1);
        ListNode prev = copy;

        while (head1 != null) {
            copyMap.put(head1, new ListNode(head1.getVal()));
            head1 = head1.getNext();
        }

        while (head != null) {
            ListNode temp = copyMap.get(head);
            temp.setRandom(copyMap.getOrDefault(head.getRandom(), null));
            prev.setNext(temp);
            prev = prev.getNext();
            head = head.getNext();
        }

        return copy.getNext();
    }

    /**
     * Q.160
     * <p>
     * Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect.
     * If the two linked lists have no intersection at all, return null.
     * <p>
     * It is guaranteed that there are no cycles anywhere in the entire linked structure.
     * Note that the linked lists must retain their original structure after the function returns.
     * <p>
     * Tags:: linkedlist
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        ListNode temp = headA;
        while (temp != null) {
            set.add(temp);
            temp = temp.getNext();
        }

        temp = headB;
        while (temp != null) {
            if (set.contains(temp))
                return temp;
            temp = temp.getNext();
        }

        return null;
    }

    /**
     * Q. 1721
     * You are given the head of a linked list, and an integer k.
     * Return the head of the linked list after swapping the values of the kth node from the beginning
     * and the kth node from the end (the list is 1-indexed).
     * <p>
     * tags:: linkedList, twoPointer
     */
    public ListNode swapNodes(ListNode head, int k) {
        ListNode fast = head, slow = head, first = fast;

        for (int i = 0; fast != null; i++) {
            if (i == k - 1) first = fast;
            fast = fast.getNext();
            if (i > k - 1) slow = slow.getNext();
        }

        int temp = slow.getVal();
        slow.setVal(first.getVal());
        first.setVal(temp);

        return head;
    }

    /**
     * Q.234
     * Given the head of a singly linked list, return true if it is a palindrome.
     * <p>
     * tags:: linkedList
     */
    public boolean isPalindrome(ListNode head) {
        Deque<Integer> dq = new LinkedList<>();

        while (head != null) {
            dq.add(head.getVal());
            head = head.getNext();
        }

        while (dq.size() > 1) {
            if (!dq.pollFirst().equals(dq.pollLast()))
                return false;
        }

        return true;
    }

    /**
     * Q. 86
     * <p>
     * Given the head of a linked list and a value x, partition it such that all nodes less than x come before
     * nodes greater than or equal to x. You should preserve the original relative order of the nodes in
     * each of the two partitions.
     * <p>
     * tags:: linkedList, twoPointer
     */
    public ListNode partitionList(ListNode head, int x) {
        ListNode beforeHead = new ListNode(), before = beforeHead;
        ListNode afterHead = new ListNode(), after = afterHead;

        while (head != null) {
            if (head.getVal() < x) {
                before.setNext(head);
                before = before.getNext();
            } else {
                after.setNext(head);
                after = after.getNext();
            }

            head = head.getNext();
        }

        before.setNext(afterHead.getNext());
        after.setNext(null);
        return beforeHead.getNext();
    }

    /**
     * Q. 19
     * <p>
     * Given the head of a linked list, remove the nth node from the end of the list and return its head.
     * <p>
     * tags:: linkedList
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.setNext(head);
        ListNode fast = dummy, slow = dummy;

        for (int i = 1; i <= n + 1; i++) {
            fast = fast.getNext();
        }

        while (fast != null) {
            slow = slow.getNext();
            fast = fast.getNext();
        }

        slow.setNext(slow.getNext().getNext());
        return dummy.getNext();
    }

    /**
     * Q. 23 Merge k Sorted Lists
     * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
     * <p>
     * Merge all the linked-lists into one sorted linked-list and return it.
     * <p>
     * tags:: heap, linkedList
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;

        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(ListNode::getVal));
        ListNode dummy = new ListNode(0), head = dummy;

        for (ListNode list : lists) {
            if (list != null)
                pq.add(list);
        }

        while (!pq.isEmpty()) {
            ListNode temp = pq.poll();
            head.setNext(temp);
            head = head.getNext();

            if (temp.getNext() != null)
                pq.add(temp.getNext());
        }

        return dummy.getNext();
    }
}

