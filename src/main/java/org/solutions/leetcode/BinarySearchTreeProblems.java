package org.solutions.leetcode;

import org.solutions.leetcode.dataStructures.TreeNode;

import java.util.Stack;

public class BinarySearchTreeProblems {

    /*
     * Q. 669
     * Trim a Binary Search Tree
     * Given the root of a binary search tree and the lowest and highest boundaries as low and high,
     * trim the tree so that all its elements lies in [low, high].
     * Trimming the tree should not change the relative structure of the elements that will remain in the tree
     * (i.e., any node's descendant should remain a descendant). It can be proven that there is a unique answer.
     * Return the root of the trimmed binary search tree. Note that the root may change depending on the given bounds.
     * */
    public TreeNode trimBst(TreeNode root, int low, int high) {
        if (root == null)
            return null;

        if (root.getVal() < low)
            return trimBst(root.getRight(), low, high);

        if (root.getVal() > high)
            return trimBst(root.getLeft(), low, high);

        root.setLeft(trimBst(root.getLeft(), low, high));
        root.setRight(trimBst(root.getRight(), low, high));

        return root;
    }

    /*
    * Q.1038 / 538
    * Given the root of a Binary Search Tree (BST), convert it to a Greater Tree such that
    * every key of the original BST is changed to the
    * original key plus sum of all keys greater than the original key in BST.
    *
    * IDEA:: inverse in-order traversal
    * */
    public TreeNode convertBST(TreeNode root) {
        TreeNode copy = root;
        Stack<TreeNode> stack = new Stack<>();
        int temp = 0;

        while(copy != null || !stack.empty()) {
            while(copy != null) {
                stack.push(copy);
                copy = copy.getRight();
            }

            copy = stack.pop();
            temp += copy.getVal();
            copy.setVal(temp);
            copy = copy.getLeft();
        }

        return root;
    }
}
