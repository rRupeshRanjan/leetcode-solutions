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
}
