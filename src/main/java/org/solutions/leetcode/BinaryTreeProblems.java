package org.solutions.leetcode;

import org.solutions.leetcode.dataStructures.TreeNode;

import java.util.*;

public class BinaryTreeProblems {

    /*
     * Q.199
     *
     * Given a binary tree, imagine yourself standing on the right side of it,
     * return the values of the nodes you can see ordered from top to bottom.
     *
     * Tags:: bfs, binaryTree
     * */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();

        if (root != null) {
            q.add(root);
        }

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode temp = q.poll();

                if (temp.getLeft() != null)
                    q.add(temp.getLeft());

                if (temp.getRight() != null)
                    q.add(temp.getRight());

                if (i == size - 1)
                    result.add(temp.getVal());
            }
        }

        return result;
    }

    /*
     * Q. 94
     * Binary tree inorder traversal
     *
     * Tags:: binaryTree, inorder, dfs
     * */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        while (root != null || !stack.empty()) {
            while (root != null) {
                stack.push(root);
                root = root.getLeft();
            }

            root = stack.pop();
            list.add(root.getVal());
            root = root.getRight();
        }

        return list;
    }

    /*
     * Q. 144
     *
     * Given the root of a binary tree, return the preorder traversal of its nodes' values.
     *
     * tags:: binaryTree, preorder, dfs
     * */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                result.add(root.getVal());
                stack.push(root);
                root = root.getLeft();
            }

            root = stack.pop().getRight();
        }

        return result;
    }

    /*
     * Q. 145
     *
     * Given the root of a binary tree, return the postorder traversal of its nodes' values.
     *
     * tags:: binaryTree, postorder, dfs
     * */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                result.add(root.getVal());
                stack.push(root);
                root = root.getRight();
            }

            root = stack.pop().getLeft();
        }

        Collections.reverse(result);
        return result;
    }

    /*
     * Q.637
     * Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.
     *
     * Tags:: binaryTree, bfs
     * */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();

        if (root != null)
            q.add(root);

        while (!q.isEmpty()) {
            int size = q.size();
            double sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode temp = q.poll();
                sum += temp.getVal();

                if (temp.getLeft() != null) q.add(temp.getLeft());
                if (temp.getRight() != null) q.add(temp.getRight());
            }
            res.add(sum / size);
        }

        return res;
    }

    /*
     * Q.623
     *
     * Given the root of a binary tree, then value v and depth d, you need to add a row of nodes with value v
     * at the given depth d. The root node is at depth 1.The adding rule is: given a positive integer depth d,
     * for each NOT null tree nodes N in depth d-1, create two tree nodes with value v as N's left subtree root and
     * right subtree root. And N's original left subtree should be the left subtree of the new left subtree root,
     * its original right subtree should be the right subtree of the new right subtree root. If depth d is 1 that means
     * there is no depth d-1 at all, then create a tree node with value v as the new root of the whole original tree,
     * and the original tree is the new root's left subtree.
     *
     * tags: binaryTree, bfs
     * */
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (d == 1) {
            TreeNode temp = new TreeNode(v);
            temp.setLeft(root);
            return temp;
        }
        Queue<TreeNode> q = new LinkedList<>();
        int level = 1;

        q.add(root);

        while (!q.isEmpty() && level != d) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode temp = q.poll();
                if (level != d - 1) {
                    if (temp.getLeft() != null) q.add(temp.getLeft());
                    if (temp.getRight() != null) q.add(temp.getRight());
                } else {
                    TreeNode lNode = new TreeNode(v), rNode = new TreeNode(v);
                    lNode.setLeft(temp.getLeft());
                    rNode.setRight(temp.getRight());
                    temp.setLeft(lNode);
                    temp.setRight(rNode);
                }
            }
            level++;
        }
        return root;
    }

    /*
     * Q. 971
     *
     * You are given the root of a binary tree with n nodes, where each node is uniquely assigned a value from 1 to n.
     * You are also given a sequence of n values voyage, which is the desired pre-order traversal of the binary tree.
     * Any node in the binary tree can be flipped by swapping its left and right subtrees.
     * Flip the smallest number of nodes so that the pre-order traversal of the tree matches voyage.
     * Return a list of the values of all flipped nodes. You may return the answer in any order.
     * If it is impossible to flip the nodes in the tree to make the pre-order traversal match voyage, return the list [-1].
     *
     * tags:: binaryTree, dfs, preorder
     * */
    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        int currIndex = 0;

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                if (root.getVal() != voyage[currIndex++])
                    return Collections.singletonList(-1);

                if (currIndex < voyage.length && root.getLeft() != null && root.getLeft().getVal() != voyage[currIndex]) {
                    result.add(root.getVal());
                    TreeNode temp = root.getLeft();
                    root.setLeft(root.getRight());
                    root.setRight(temp);
                }

                stack.push(root);
                root = root.getLeft();
            }

            root = stack.pop().getRight();
        }


        return result;
    }
}
