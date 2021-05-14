package org.solutions.leetcode.dataStructures;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode {
    private int val;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, Integer leftVal, Integer rightVal) {
        this.val = val;
        if (leftVal != null) this.setLeft(new TreeNode(leftVal));
        if (rightVal != null) this.setRight(new TreeNode(rightVal));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        TreeNode treeNode = (TreeNode) o;
        return val == treeNode.val && areNodesEqual(left, treeNode.left) && areNodesEqual(right, treeNode.right);
    }

    private boolean areNodesEqual(TreeNode a, TreeNode b) {
        boolean isEqual = false;
        if (a == null && b == null) {
            isEqual = true;
        } else if (a != null && b != null) {
            isEqual = a.equals(b);
        }
        return isEqual;
    }
}
