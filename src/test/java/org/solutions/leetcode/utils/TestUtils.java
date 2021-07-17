package org.solutions.leetcode.utils;

import org.solutions.leetcode.dataStructures.ListNode;
import org.solutions.leetcode.dataStructures.TreeNode;

import java.util.List;
import java.util.stream.Collectors;

public class TestUtils {
    public boolean areTreesEqualByValue(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null)
            return true;
        if (node1 == null || node2 == null)
            return false;
        return (node1.getVal() == node2.getVal() &&
                areTreesEqualByValue(node1.getLeft(), node2.getLeft()) &&
                areTreesEqualByValue(node1.getRight(), node2.getRight())
        );
    }

    public boolean areLinkedListsEqualByValue(ListNode a, ListNode b) {
        while (a != null && b != null) {
            if (a.getVal() != b.getVal()) {
                return false;
            }
            a = a.getNext();
            b = b.getNext();
        }

        return a == null && b == null;
    }

    public ListNode getLinkedListWithCycle(List<Integer> entries, int pos) {
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

    public ListNode getLinkedList(List<Integer> entries) {
        if (entries.size() == 0)
            return null;
        return getLinkedListWithCycle(entries, -1);
    }
}
