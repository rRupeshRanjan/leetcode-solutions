package org.solutions.leetcode;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.solutions.leetcode.dataStructures.ListNode;
import org.solutions.leetcode.utils.TestUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListProblemsTest {

    static LinkedListProblems linkedListProblems;
    static TestUtils testUtils;

    @BeforeAll
    static void setup() {
        linkedListProblems = new LinkedListProblems();
        testUtils = new TestUtils();
    }

    /**
     * don't add scenarios hashmap here, as it errors with stackoverflow, when there is a cycle with linkedlist
     */
    @Test
    void testHasCycle() {
        assertTrue(linkedListProblems.hasCycle(testUtils.getLinkedListWithCycle(Arrays.asList(3, 2, 0, -4), 1)));
        assertTrue(linkedListProblems.hasCycle(testUtils.getLinkedListWithCycle(Arrays.asList(1, 2), 0)));
        assertFalse(linkedListProblems.hasCycle(testUtils.getLinkedListWithCycle(Arrays.asList(1, 2), -1)));
        assertFalse(linkedListProblems.hasCycle(testUtils.getLinkedListWithCycle(Collections.singletonList(1), -1)));
        assertFalse(linkedListProblems.hasCycle(null));
    }

    @Test
    void testGetIntersectionNode() {
        // A: 1->2->3 B: 0->2->3
        ListNode headA = testUtils.getLinkedList(Arrays.asList(1, 2, 3));
        ListNode headB = new ListNode(0);
        headB.setNext(headA.getNext());
        assertEquals(headA.getNext(), linkedListProblems.getIntersectionNode(headA, headB));

        // A: 1->2->3 B: 0
        headB = new ListNode(0);
        assertNull(linkedListProblems.getIntersectionNode(headA, headB));
    }

    @Test
    void testSwapNodes() {
        Map<Pair<ListNode, Integer>, ListNode> scenarios = new HashMap<>();

        scenarios.put(Pair.of(testUtils.getLinkedList(Arrays.asList(1, 4, 3, 2, 5)), 2),
                testUtils.getLinkedList(Arrays.asList(1, 2, 3, 4, 5)));
        scenarios.put(Pair.of(testUtils.getLinkedList(Arrays.asList(7, 9, 6, 6, 7, 8, 3, 0, 9, 5)), 5),
                testUtils.getLinkedList(Arrays.asList(7, 9, 6, 6, 8, 7, 3, 0, 9, 5)));
        scenarios.put(Pair.of(testUtils.getLinkedList(Collections.singletonList(1)), 1),
                testUtils.getLinkedList(Collections.singletonList(1)));
        scenarios.put(Pair.of(testUtils.getLinkedList(Arrays.asList(1, 2)), 2),
                testUtils.getLinkedList(Arrays.asList(2, 1)));
        scenarios.put(Pair.of(testUtils.getLinkedList(Arrays.asList(1, 2, 3)), 2),
                testUtils.getLinkedList(Arrays.asList(1, 2, 3)));

        scenarios.forEach((input, expected) ->
                assertEquals(expected, linkedListProblems.swapNodes(input.getLeft(), input.getRight())));
    }

    @Test
    void testIsPalindrome() {
        Map<ListNode, Boolean> scenarios = new HashMap<>();
        scenarios.put(testUtils.getLinkedList(Arrays.asList(1, 2, 2, 1)), true);
        scenarios.put(testUtils.getLinkedList(Collections.singletonList(1)), true);
        scenarios.put(testUtils.getLinkedList(Arrays.asList(1, 1, 2, 1)), false);
        scenarios.put(testUtils.getLinkedList(Arrays.asList(1, 21)), false);

        scenarios.forEach((input, expected) -> assertEquals(expected, linkedListProblems.isPalindrome(input)));
    }

    @Test
    void testPartitionList() {
        Map<Pair<ListNode, Integer>, ListNode> scenarios = new HashMap<>();
        scenarios.put(Pair.of(testUtils.getLinkedList(Arrays.asList(2, 1)), 2),
                testUtils.getLinkedList(Arrays.asList(1, 2)));
        scenarios.put(Pair.of(testUtils.getLinkedList(Arrays.asList(1, 4, 3, 2, 5, 2)), 3),
                testUtils.getLinkedList(Arrays.asList(1, 2, 2, 4, 3, 5)));

        scenarios.forEach((input, expected) ->
                assertEquals(expected, linkedListProblems.partitionList(input.getLeft(), input.getRight())));
    }

    @Test
    void testRemoveNthFromEnd() {
        Map<Pair<ListNode, Integer>, ListNode> scenarios = new HashMap<>();
        scenarios.put(Pair.of(testUtils.getLinkedList(Arrays.asList(1, 2, 3, 4, 5)), 2),
                testUtils.getLinkedList(Arrays.asList(1, 2, 3, 5)));
        scenarios.put(Pair.of(testUtils.getLinkedList(Collections.singletonList(1)), 1), null);
        scenarios.put(Pair.of(testUtils.getLinkedList(Arrays.asList(1, 2)), 2),
                testUtils.getLinkedList(Collections.singletonList(2)));

        scenarios.forEach((input, expected) ->
                assertEquals(expected, linkedListProblems.removeNthFromEnd(input.getLeft(), input.getRight())));
    }

    @Test
    void testMergeKLists() {
        Map<ListNode[], ListNode> scenarios = new HashMap<>();
        scenarios.put(new ListNode[]{
                testUtils.getLinkedList(Arrays.asList(1, 4, 5)),
                testUtils.getLinkedList(Arrays.asList(1, 3, 4)),
                testUtils.getLinkedList(Arrays.asList(2, 6))
        }, testUtils.getLinkedList(Arrays.asList(1, 1, 2, 3, 4, 4, 5, 6)));
        scenarios.put(new ListNode[]{}, null);
        scenarios.put(new ListNode[]{null}, null);
        scenarios.put(null, null);

        scenarios.forEach((input, expected) -> assertEquals(expected, linkedListProblems.mergeKLists(input)));
    }

    @Test
    void testMergeTwoLists() {
        Map<Pair<ListNode, ListNode>, ListNode> scenarios = new HashMap<>();
        scenarios.put(Pair.of(
                        testUtils.getLinkedList(Arrays.asList(1, 2, 4)),
                        testUtils.getLinkedList(Arrays.asList(1, 3, 4))),
                testUtils.getLinkedList(Arrays.asList(1, 1, 2, 3, 4, 4)));
        scenarios.put(Pair.of(null, null), null);
        scenarios.put(Pair.of(
                        null,
                        testUtils.getLinkedList(Arrays.asList(1, 3, 4))),
                testUtils.getLinkedList(Arrays.asList(1, 3, 4)));

        scenarios.forEach((input, expected) ->
                assertEquals(expected, linkedListProblems.mergeTwoLists(input.getLeft(), input.getRight())));
    }

    @Test
    void testAddTwoNumbers() {
        Map<Pair<ListNode, ListNode>, ListNode> scenarios = new HashMap<>();
        scenarios.put(Pair.of(
                        testUtils.getLinkedList(Arrays.asList(2, 4, 3)),
                        testUtils.getLinkedList(Arrays.asList(5, 6, 4))),
                testUtils.getLinkedList(Arrays.asList(7, 0, 8)));
        scenarios.put(Pair.of(
                        testUtils.getLinkedList(Arrays.asList(2, 4, 8)),
                        testUtils.getLinkedList(Arrays.asList(5, 6, 4))),
                testUtils.getLinkedList(Arrays.asList(7, 0, 3, 1)));
        scenarios.put(Pair.of(
                        testUtils.getLinkedList(Collections.singletonList(0)),
                        testUtils.getLinkedList(Collections.singletonList(0))),
                testUtils.getLinkedList(Collections.singletonList(0)));
        scenarios.put(Pair.of(
                        testUtils.getLinkedList(Arrays.asList(9, 9, 9, 9, 9, 9, 9)),
                        testUtils.getLinkedList(Arrays.asList(9, 9, 9, 9))),
                testUtils.getLinkedList(Arrays.asList(8, 9, 9, 9, 0, 0, 0, 1)));
        scenarios.put(Pair.of(
                        testUtils.getLinkedList(Arrays.asList(9, 9, 9, 9)),
                        testUtils.getLinkedList(Arrays.asList(9, 9, 9, 9, 9, 9, 9))),
                testUtils.getLinkedList(Arrays.asList(8, 9, 9, 9, 0, 0, 0, 1)));

        scenarios.forEach((input, expected) ->
                assertEquals(expected, linkedListProblems.addTwoNumbers(input.getLeft(), input.getRight())));
    }

    @Test
    void testFlattenLinkedList() {
        Map<ListNode, ListNode> scenarios = new HashMap<>();
        ListNode row1Head = testUtils.getDoubleLinkedList(Arrays.asList(1, 2, 3, 4, 5, 6));
        ListNode row1Layer2 = testUtils.getDoubleLinkedList(Arrays.asList(7, 8, 9, 10));
        ListNode row1Layer3 = testUtils.getDoubleLinkedList(Arrays.asList(11, 12));
        testUtils.connectMultiLevelLinkedList(row1Head, row1Layer2, 2);
        testUtils.connectMultiLevelLinkedList(row1Layer2, row1Layer3, 1);

        ListNode row2Head = testUtils.getDoubleLinkedList(Arrays.asList(1, 2, 3, 4, 5, 6));
        ListNode row2Layer2 = testUtils.getDoubleLinkedList(Arrays.asList(7, 8));
        ListNode row2Layer3 = testUtils.getDoubleLinkedList(Arrays.asList(11, 12));
        testUtils.connectMultiLevelLinkedList(row2Head, row2Layer2, 2);
        testUtils.connectMultiLevelLinkedList(row2Layer2, row2Layer3, 1);

        ListNode row3Head = testUtils.getDoubleLinkedList(Arrays.asList(1, 2));
        ListNode row3Layer2 = testUtils.getDoubleLinkedList(Collections.singletonList(3));
        testUtils.connectMultiLevelLinkedList(row3Head, row3Layer2, 0);

        ListNode row4Head = new ListNode(1);
        row4Head.setChild(new ListNode(2));

        scenarios.put(row1Head, testUtils.getDoubleLinkedList(Arrays.asList(1, 2, 3, 7, 8, 11, 12, 9, 10, 4, 5, 6)));
        scenarios.put(row2Head, testUtils.getDoubleLinkedList(Arrays.asList(1, 2, 3, 7, 8, 11, 12, 4, 5, 6)));
        scenarios.put(row3Head, testUtils.getDoubleLinkedList(Arrays.asList(1, 3, 2)));
        scenarios.put(row4Head, testUtils.getDoubleLinkedList(Arrays.asList(1, 2)));
        scenarios.put(null, null);
        scenarios.put(testUtils.getDoubleLinkedList(Arrays.asList(1, 2, 3)),
                testUtils.getDoubleLinkedList(Arrays.asList(1, 2, 3)));

        scenarios.forEach((input, expected) -> assertEquals(expected, linkedListProblems.flattenLinkedList(input)));
    }
}
