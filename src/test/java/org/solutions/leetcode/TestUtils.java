package org.solutions.leetcode;

import org.solutions.leetcode.dataStructures.ListNode;
import org.solutions.leetcode.dataStructures.TreeNode;

public class TestUtils {
    public boolean areTreeEqualByValue(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null)
            return true;
        if (node1 == null || node2 == null)
            return false;
        return (node1.getVal() == node2.getVal() &&
                areTreeEqualByValue(node1.getLeft(), node2.getLeft()) &&
                areTreeEqualByValue(node1.getRight(), node2.getRight())
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
}
