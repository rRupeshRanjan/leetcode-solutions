package org.solutions.leetcode;

import org.apache.commons.lang3.tuple.Triple;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.solutions.leetcode.dataStructures.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListProblemsTest {

    static LinkedListProblems linkedListProblems;
    static TestUtils testUtils;

    @BeforeAll
    static void setup() {
        linkedListProblems = new LinkedListProblems();
        testUtils = new TestUtils();
    }

    @Test
    void testHasCycle() {
        assertTrue(linkedListProblems.hasCycle(getLinkedListWithCycle(Arrays.asList(3, 2, 0, -4), 1)));
        assertTrue(linkedListProblems.hasCycle(getLinkedListWithCycle(Arrays.asList(1, 2), 0)));
        assertFalse(linkedListProblems.hasCycle(getLinkedListWithCycle(Collections.singletonList(1), -1)));
    }

    @Test
    void testGetIntersectionNode() {

        // A: 1->2->3 B: 0->2->3
        ListNode headA = getLinkedList(Arrays.asList(1, 2, 3));
        ListNode headB = new ListNode(0);
        headB.setNext(headA.getNext());
        assertEquals(headA.getNext(), linkedListProblems.getIntersectionNode(headA, headB));

        // A: 1->2->3 B: 0
        headB = new ListNode(0);
        assertNull(linkedListProblems.getIntersectionNode(headA, headB));
    }

    @Test
    void testSwapNodes() {
        // Triple of expectation, input head, input position
        List<Triple<ListNode, ListNode, Integer>> inputs = new ArrayList<>() {{
            add(Triple.of(getLinkedList(Arrays.asList(1, 2, 3, 4, 5)), getLinkedList(Arrays.asList(1, 4, 3, 2, 5)), 2));
            add(Triple.of(getLinkedList(Arrays.asList(7, 9, 6, 6, 8, 7, 3, 0, 9, 5)), getLinkedList(Arrays.asList(7, 9, 6, 6, 7, 8, 3, 0, 9, 5)), 5));
            add(Triple.of(getLinkedList(Collections.singletonList(1)), getLinkedList(Collections.singletonList(1)), 1));
            add(Triple.of(getLinkedList(Arrays.asList(2, 1)), getLinkedList(Arrays.asList(1, 2)), 2));
            add(Triple.of(getLinkedList(Arrays.asList(1, 2, 3)), getLinkedList(Arrays.asList(1, 2, 3)), 2));
        }};

        inputs.forEach(scenario ->
                assertTrue(testUtils.areLinkedListsEqualByValue(
                        scenario.getLeft(), linkedListProblems.swapNodes(scenario.getMiddle(), scenario.getRight()))
                )
        );
    }

    private ListNode getLinkedListWithCycle(List<Integer> entries, int pos) {
        List<ListNode> nodesList = entries.stream()
                .map(ListNode::new)
                .collect(Collectors.toList());

        for (int i = 0; i < entries.size() - 1; i++) {
            nodesList.get(i).setNext(nodesList.get(i + 1));
        }

        // create a loop from last node to node at index = pos.
        // no cycle if pos = -1
        if (pos >= 0) {
            nodesList.get(entries.size() - 1).setNext(nodesList.get(pos));
        }

        return nodesList.get(0);
    }

    private ListNode getLinkedList(List<Integer> entries) {
        return getLinkedListWithCycle(entries, -1);
    }
}