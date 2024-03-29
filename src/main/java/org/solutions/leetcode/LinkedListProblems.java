package org.solutions.leetcode;

import org.solutions.leetcode.dataStructures.ListNode;

import java.util.*;

public class LinkedListProblems {

    /**
     * Q. 2 Add Two Numbers
     * <p>
     * You are given two non-empty linked lists representing two non-negative integers.
     * The digits are stored in reverse order, and each of their nodes contains a single digit.
     * Add the two numbers and return the sum as a linked list.
     * <p>
     * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
     * <p>
     * tags:: linkedList, twoPointer
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0, sum = 0;
        ListNode dummy = new ListNode(0), head = dummy;

        while (l1 != null || l2 != null) {
            int x = (l1 == null) ? 0 : l1.getVal();
            int y = (l2 == null) ? 0 : l2.getVal();
            sum = x + y + carry;
            carry = sum / 10;

            head.setNext(new ListNode(sum % 10));
            head = head.getNext();

            if (l1 != null) l1 = l1.getNext();
            if (l2 != null) l2 = l2.getNext();
        }

        if (carry > 0) {
            head.setNext(new ListNode(carry));
        }

        return dummy.getNext();
    }

    /**
     * Q. 19 Remove Nth Node From End of List
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
     * Q. 21 Merge Two Sorted Lists
     * Merge two sorted linked lists and return it as a sorted list.
     * The list should be made by splicing together the nodes of the first two lists.
     * <p>
     * tags:: twoPointer, linkedList
     */
    public ListNode mergeTwoLists(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(0), head = dummy;

        while (head1 != null && head2 != null) {
            if (head1.getVal() < head2.getVal()) {
                head.setNext(head1);
                head1 = head1.getNext();
            } else {
                head.setNext(head2);
                head2 = head2.getNext();
            }
            head = head.getNext();
        }

        head.setNext(head1 == null ? head2 : head1);
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

    /**
     * Q. 86 Palindrome Linked List
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
     * Q. 138 Copy List with Random Pointer
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
     * Q. 141 Linked List Cycle
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
     * Q. 160 Intersection of Two Linked Lists
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
        ListNode n1 = headA, n2 = headB;
        while (n1 != n2) {
            n1 = (n1 == null) ? headB : n1.getNext();
            n2 = (n2 == null) ? headA : n2.getNext();
        }

        return n1;
    }

    /**
     * Q. 234 Palindrome Linked List
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
     * Q. 430 Flatten a Multilevel Doubly Linked List
     * <p>
     * You are given a doubly linked list which in addition to the next and previous pointers, it could have a child
     * pointer, which may or may not point to a separate doubly linked list. These child lists may have one or more
     * children of their own, and so on, to produce a multilevel data structure.
     * <p>
     * Flatten the list so that all the nodes appear in a single-level, doubly linked list.
     * You are given the head of the first level of the list.
     * <p>
     * tags::stack, doublyLinkedList
     */
    public ListNode flattenLinkedList(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode curr = head;

        while (curr != null || !stack.isEmpty()) {
            if (curr.getChild() != null) {
                if (curr.getNext() != null)
                    stack.push(curr.getNext());
                curr.setNext(curr.getChild());
                curr.getChild().setPrev(curr);
                curr.setChild(null);
            } else if (curr.getNext() == null && !stack.isEmpty()) {
                curr.setNext(stack.pop());
                curr.getNext().setPrev(curr);
            }
            curr = curr.getNext();
        }
        return head;
    }

    /**
     * Q. 1721 Swapping Nodes in a Linked List
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
}
