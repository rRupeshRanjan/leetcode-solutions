package org.solutions.leetcode;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.solutions.leetcode.dataStructures.ListNode;
import org.solutions.leetcode.utils.TestUtils;

import java.util.*;

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
        // Triple of expectation, input head, input position
        List<Triple<ListNode, ListNode, Integer>> inputs = new ArrayList<>() {{
            add(
                    Triple.of(testUtils.getLinkedList(Arrays.asList(1, 2, 3, 4, 5)),
                            testUtils.getLinkedList(Arrays.asList(1, 4, 3, 2, 5)), 2));
            add(
                    Triple.of(testUtils.getLinkedList(Arrays.asList(7, 9, 6, 6, 8, 7, 3, 0, 9, 5)),
                            testUtils.getLinkedList(Arrays.asList(7, 9, 6, 6, 7, 8, 3, 0, 9, 5)), 5));
            add(
                    Triple.of(testUtils.getLinkedList(Collections.singletonList(1)),
                            testUtils.getLinkedList(Collections.singletonList(1)), 1));
            add(
                    Triple.of(testUtils.getLinkedList(Arrays.asList(2, 1)),
                            testUtils.getLinkedList(Arrays.asList(1, 2)), 2));
            add(
                    Triple.of(testUtils.getLinkedList(Arrays.asList(1, 2, 3)),
                            testUtils.getLinkedList(Arrays.asList(1, 2, 3)), 2));
        }};

        inputs.forEach(scenario ->
                assertTrue(testUtils.areLinkedListsEqualByValue(
                        scenario.getLeft(), linkedListProblems.swapNodes(scenario.getMiddle(), scenario.getRight()))
                )
        );
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
                assertTrue(testUtils.areLinkedListsEqualByValue(
                        expected, linkedListProblems.partitionList(input.getLeft(), input.getRight()))));
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
                assertTrue(testUtils.areLinkedListsEqualByValue(
                        expected, linkedListProblems.removeNthFromEnd(input.getLeft(), input.getRight()))));
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

        scenarios.forEach((input, expected) -> assertTrue(
                testUtils.areLinkedListsEqualByValue(expected, linkedListProblems.mergeKLists(input))));
    }
}
