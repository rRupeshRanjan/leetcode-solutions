package org.solutions.leetcode;

import org.solutions.leetcode.dataStructures.TreeNode;

import java.util.*;

public class BinaryTreeProblems {
    /*
     * Q.199
     * Given a binary tree, imagine yourself standing on the right side of it,
     * return the values of the nodes you can see ordered from top to bottom.
     *
     * Tags:: bfs, binaryTree
     * */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();

        if (root!= null) {
            q.add(root);
        }

        while(!q.isEmpty()) {
            int size = q.size();
            for(int i=0; i<size; i++) {
                TreeNode temp = q.poll();

                if(temp.getLeft()!=null)
                    q.add(temp.getLeft());

                if(temp.getRight()!=null)
                    q.add(temp.getRight());

                if(i==size-1)
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

        while(root != null || !stack.empty()) {
            while(root != null) {
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
     * Q.637
     * Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.
     *
     * Tags:: binaryTree, bfs
     * */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();

        if(root != null)
            q.add(root);

        while(!q.isEmpty()) {
            int size = q.size();
            double sum = 0;
            for(int i=0; i<size; i++) {
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
}
