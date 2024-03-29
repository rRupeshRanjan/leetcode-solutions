package org.solutions.leetcode;

import org.solutions.leetcode.dataStructures.ListNode;
import org.solutions.leetcode.dataStructures.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinarySearchTreeProblems {

    /**
     * Q. 98 Validate Binary Search Tree
     * <p>
     * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
     * A valid BST is defined as follows:
     * The left subtree of a node contains only nodes with keys less than the node's key.
     * The right subtree of a node contains only nodes with keys greater than the node's key.
     * Both the left and right subtrees must also be binary search trees.
     * <p>
     * tags:: bst, inorder
     */
    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        double inorder = -Double.MAX_VALUE;

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.getLeft();
            }

            root = stack.pop();
            if (root.getVal() <= inorder)
                return false;
            inorder = root.getVal();
            root = root.getRight();
        }

        return true;
    }

    /**
     * Q. 108 Convert Sorted Array to Binary Search Tree
     * <p>
     * Given an integer array nums where the elements are sorted in ascending order, convert it to a height-balanced
     * binary search tree. A height-balanced binary tree is a binary tree in which the depth of the two subtrees of
     * every node never differs by more than one.
     * <p>
     * tags:: array, bst
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null) return null;

        return bstFromArray(nums, 0, nums.length - 1);
    }

    /**
     * Q. 109 Convert Sorted List to Binary Search Tree
     * <p>
     * Given the head of a singly linked list where elements are sorted in ascending order, convert it to a height
     * balanced BST. For this problem, a height-balanced binary tree is defined as a binary tree in which the depth
     * of the two subtrees of every node never differ by more than 1.
     * <p>
     * tags:: linkedList, bst
     */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null)
            return null;
        if (head.getNext() == null)
            return new TreeNode(head.getVal());

        ListNode slow = head, fast = head, pre = head;
        while (fast != null && fast.getNext() != null) {
            pre = slow;
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }

        pre.setNext(null);
        TreeNode root = new TreeNode(slow.getVal());
        root.setLeft(sortedListToBST(head));
        root.setRight(sortedListToBST(slow.getNext()));

        return root;
    }

    /**
     * Q. 235 Lowest Common Ancestor of a Binary Search Tree
     * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
     * According to the definition of LCA on Wikipedia:
     * "The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q
     * as descendants (where we allow a node to be a descendant of itself)."
     * <p>
     * tags:: binarySearchTree, dfs, recursion
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (root.getVal() < p.getVal() && root.getVal() < q.getVal())
                root = root.getRight();
            else if (root.getVal() > p.getVal() && root.getVal() > q.getVal())
                root = root.getLeft();
            else
                break;
        }

        return root;
    }

    /**
     * Q. 669 Trim a Binary Search Tree
     * <p>
     * Given the root of a binary search tree and the lowest and highest boundaries as low and high,
     * trim the tree so that all its elements lies in [low, high].
     * Trimming the tree should not change the relative structure of the elements that will remain in the tree
     * (i.e., any node's descendant should remain a descendant). It can be proven that there is a unique answer.
     * Return the root of the trimmed binary search tree. Note that the root may change depending on the given bounds.
     * <p>
     * Tags:: binarySearchTree, recursion
     */
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

    /**
     * Q. 701 Insert into a Binary Search Tree
     * <p>
     * You are given the root node of a binary search tree (BST) and a value to insert into the tree. Return the root
     * node of the BST after the insertion. It is guaranteed that the new value does not exist in the original BST.
     * Notice that there may exist multiple valid ways for the insertion, as long as the tree remains a BST after
     * insertion. You can return any of them.
     * <p>
     * tags::bst, recursion
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null)
            return new TreeNode(val);

        if (val > root.getVal()) { // go right
            root.setRight(insertIntoBST(root.getRight(), val));
        } else { // go left
            root.setLeft(insertIntoBST(root.getLeft(), val));
        }

        return root;
    }

    /**
     * Q. 1008 Construct Binary Search Tree from Preorder Traversal
     * Given an array of integers representing preorder traversal of a BST, construct the tree and return its root.
     * <p>
     * It is guaranteed to find a binary search tree with the given requirements for the given test cases.
     * <p>
     * A binary search tree is a binary tree where for every node, any descendant of Node.left has a value strictly less
     * than Node.val, and any descendant of Node.right has a value strictly greater than Node.val.
     * <p>
     * A preorder traversal of a binary tree displays the value of the node first,
     * then traverses Node.left, then traverses Node.right.
     * <p>
     * tags:: binaryTree, preorder, bst
     */
    public TreeNode bstFromPreorder(int[] preorder) {
        return recurseBuildBstPreorder(preorder, Integer.MAX_VALUE, new int[]{0});
    }

    /**
     * Q. 1038  Binary Search Tree to Greater Sum Tree
     * Q. 538 Convert BST to Greater Tree
     * <p>
     * Given the root of a Binary Search Tree (BST), convert it to a Greater Tree such that
     * every key of the original BST is changed to the
     * original key plus sum of all keys greater than the original key in BST.
     * <p>
     * IDEA:: reverse in-order traversal
     * Tags:: binarySearchTree, dfs, traversal
     */
    public TreeNode convertBST(TreeNode root) {
        TreeNode copy = root;
        Stack<TreeNode> stack = new Stack<>();
        int temp = 0;

        while (copy != null || !stack.empty()) {
            while (copy != null) {
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

    /**
     * Q. 1305 All Elements in Two Binary Search Trees
     * <p>
     * Given two binary search trees root1 and root2, return a list containing all the integers from
     * both trees sorted in ascending order.
     * <p>
     * tags:: inorder, array-merge, binary-tree
     */
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        BinaryTreeProblems btp = new BinaryTreeProblems();
        return mergeSortedList(btp.inorderTraversal(root1), btp.inorderTraversal(root2));
    }

    private TreeNode bstFromArray(int[] nums, int left, int right) {
        if (left < 0 || right < 0 || left == nums.length || right == nums.length || left > right)
            return null;

        int mid = left + (right - left) / 2;
        TreeNode curr = new TreeNode(nums[mid]);
        curr.setLeft(bstFromArray(nums, left, mid - 1));
        curr.setRight(bstFromArray(nums, mid + 1, right));

        return curr;
    }

    private TreeNode recurseBuildBstPreorder(int[] preorder, int bound, int[] rootIndex) {
        if (rootIndex[0] >= preorder.length || preorder[rootIndex[0]] > bound)
            return null;

        TreeNode root = new TreeNode(preorder[rootIndex[0]++]);
        root.setLeft(recurseBuildBstPreorder(preorder, root.getVal(), rootIndex));
        root.setRight(recurseBuildBstPreorder(preorder, bound, rootIndex));
        return root;
    }

    private List<Integer> mergeSortedList(List<Integer> list1, List<Integer> list2) {
        List<Integer> list = new ArrayList<>();
        int i = 0, j = 0;
        while (i < list1.size() && j < list2.size()) {
            if (list1.get(i) > list2.get(j))
                list.add(list2.get(j++));
            else
                list.add(list1.get(i++));
        }

        while (i < list1.size())
            list.add(list1.get(i++));
        while (j < list2.size())
            list.add(list2.get(j++));

        return (list);
    }

}
