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

    public TreeNode(int val, Object left, Object right) {
        this.val = val;

        if (left instanceof Integer)
            this.setLeft(new TreeNode((Integer) left));
        else
            this.setLeft((TreeNode) left);

        if (right instanceof Integer)
            this.setRight(new TreeNode((Integer) right));
        else
            this.setRight((TreeNode) right);
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

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
