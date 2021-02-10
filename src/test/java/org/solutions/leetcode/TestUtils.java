package org.solutions.leetcode;

import org.solutions.leetcode.dataStructures.TreeNode;

public class TestUtils {
    public boolean compareTrees(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null)
            return true;
        if (node1 == null || node2 == null)
            return false;
        return (node1.getVal() == node2.getVal() &&
                compareTrees(node1.getLeft(), node2.getLeft()) &&
                compareTrees(node1.getRight(), node2.getRight())
        );
    }
}
