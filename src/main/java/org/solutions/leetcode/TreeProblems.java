package org.solutions.leetcode;

import org.solutions.leetcode.dataStructures.NaryTreeNode;

import java.util.*;

public class TreeProblems {

    /**
     * Q. 429 N-ary Tree Level Order Traversal
     * <p>
     * Given an n-ary tree, return the level order traversal of its nodes' values.
     * <p>
     * tags:: tree, bfs
     */
    public List<List<Integer>> naryLevelOrder(NaryTreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<NaryTreeNode> q = new LinkedList<>();

        if (root != null) {
            q.add(root);
            while (!q.isEmpty()) {
                List<Integer> holder = new ArrayList<>();
                for (int i = q.size() - 1; i >= 0; i--) {
                    NaryTreeNode temp = q.poll();
                    holder.add(temp.getVal());

                    if (temp.getChildren() != null) {
                        q.addAll(temp.getChildren());
                    }
                }
                result.add(new ArrayList<>(holder));
            }
        }

        return result;
    }

    /**
     * Q. 589 N-ary Tree Preorder Traversal
     * <p>
     * Given the root of an n-ary tree, return the preorder traversal of its nodes' values.
     * <p>
     * tags:: preorder
     */
    public List<Integer> naryPreorder(NaryTreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<NaryTreeNode> stack = new Stack<>();

        if (root != null) {
            stack.add(root);

            while (!stack.isEmpty()) {
                NaryTreeNode temp = stack.pop();
                result.add(temp.getVal());
                if (temp.getChildren() != null) {
                    List<NaryTreeNode> children = temp.getChildren();
                    for (int i = children.size() - 1; i >= 0; i--)
                        stack.add(children.get(i));
                }
            }
        }

        return result;
    }

    /**
     * Q. 590 N-ary Tree Postorder Traversal
     * <p>
     * Given the root of an n-ary tree, return the postorder traversal of its nodes' values.
     * <p>
     * tags:: postorder
     */
    public List<Integer> naryPostorder(NaryTreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<NaryTreeNode> stack = new Stack<>();

        if (root != null) {
            stack.add(root);

            while (!stack.isEmpty()) {
                NaryTreeNode temp = stack.pop();
                result.add(temp.getVal());
                if (temp.getChildren() != null) {
                    stack.addAll(temp.getChildren());
                }
            }
        }

        Collections.reverse(result);
        return result;
    }
}
