package org.solutions.leetcode;

import org.solutions.leetcode.dataStructures.Node;

import java.util.HashMap;
import java.util.Map;

public class LinkedListProblems {

    /*
    * Q.141
    * Detect if there is a loop in linked list
    *
    * Tags:: linkedList
    * */
    public boolean hasCycle(Node head) {
        Node slow = head;
        Node fast = head;

        while(slow != null && fast != null && fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext();
            if (slow == fast) {
                return true;
            }
        }

        return false;
    }

    /*
    * Q.138
    * A linked list of length n is given such that each node contains an additional random pointer,
    * which could point to any node in the list, or null. Construct a deep copy of the list.
    * The deep copy should consist of exactly n brand new nodes, where each new node has its value set to the value of
    * its corresponding original node. Both the next and random pointer of the new nodes should point to new nodes in
    * the copied list such that the pointers in the original list and copied list represent the same list state.
    * None of the pointers in the new list should point to nodes in the original list.
    *
    * Tags:: linkedList
    * */
    public Node copyRandomList(Node head) {
        Map<Node, Node> copyMap = new HashMap<>();
        Node head1 = head;
        Node copy = new Node(-1);
        Node prev = copy;

        while(head1 != null) {
            copyMap.put(head1, new Node(head1.getVal()));
            head1 = head1.getNext();
        }

        while(head != null) {
            Node temp = copyMap.get(head);
            temp.setRandom(copyMap.getOrDefault(head.getRandom(), null));
            prev.setNext(temp);
            prev = prev.getNext();
            head = head.getNext();
        }

        return copy.getNext();
    }
}
