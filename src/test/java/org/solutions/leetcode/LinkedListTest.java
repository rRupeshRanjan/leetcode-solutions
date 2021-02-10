package org.solutions.leetcode;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.solutions.leetcode.dataStructures.LinkedListNode;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {

    static LinkedList linkedList;

    @BeforeAll
    static void setup() {
        linkedList = new LinkedList();
    }

    @Test
    void hasCycle() {
        LinkedListNode head = getLinkedListFromListWithCycle(Arrays.asList(3,2,0,-4), 1);
        assertTrue(linkedList.hasCycle(head));

        head = getLinkedListFromListWithCycle(Arrays.asList(1, 2), 0);
        assertTrue(linkedList.hasCycle(head));

        head = getLinkedListFromListWithCycle(Collections.singletonList(1), -1);
        assertFalse(linkedList.hasCycle(head));
    }

    private LinkedListNode getLinkedListFromListWithCycle(List<Integer> entries, int pos) {
        List<LinkedListNode> nodesList = entries.stream()
                .map(LinkedListNode::new)
                .collect(Collectors.toList());

        for(int i=0; i<entries.size()-1; i++) {
            nodesList.get(i).setNext(nodesList.get(i+1));
        }

        // create a loop from last node to node at index = pos.
        // no cycle if pos = -1
        if (pos >= 0) {
            nodesList.get(entries.size() - 1).setNext(nodesList.get(pos));
        }

        return nodesList.get(0);
    }

    private LinkedListNode getLinkedListFromList(List<Integer> entries) {
        return getLinkedListFromListWithCycle(entries, -1);
    }
}