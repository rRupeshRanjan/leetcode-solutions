package org.solutions.leetcode;

import org.solutions.leetcode.dataStructures.TreeNode;

import java.util.*;

public class BinaryTreeProblems {

    private int cameraCoverCount;

    /**
     * Q.199 Binary Tree Right Side View
     * <p>
     * Given a binary tree, imagine yourself standing on the right side of it,
     * return the values of the nodes you can see ordered from top to bottom.
     * <p>
     * Tags:: bfs, binaryTree
     */
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

    /**
     * Q. 94 Binary Tree Inorder Traversal
     * Binary tree inorder traversal
     * <p>
     * Tags:: binaryTree, inorder, dfs
     */
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

    /**
     * Q. 144 Binary Tree Preorder Traversal
     * <p>
     * Given the root of a binary tree, return the preorder traversal of its nodes' values.
     * <p>
     * tags:: binaryTree, preorder, dfs
     */
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

    /**
     * Q. 145 Binary Tree Postorder Traversal
     * <p>
     * Given the root of a binary tree, return the postorder traversal of its nodes' values.
     * <p>
     * tags:: binaryTree, postorder, dfs
     */
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

    /**
     * Q.637 Average of Levels in Binary Tree
     * Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.
     * <p>
     * Tags:: binaryTree, bfs
     */
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

    /**
     * Q.623 Add One Row to Tree
     * <p>
     * Given the root of a binary tree, then value v and depth d, you need to add a row of nodes with value v
     * at the given depth d. The root node is at depth 1.The adding rule is: given a positive integer depth d,
     * for each NOT null tree nodes N in depth d-1, create two tree nodes with value v as N's left subtree root and
     * right subtree root. And N's original left subtree should be the left subtree of the new left subtree root,
     * its original right subtree should be the right subtree of the new right subtree root. If depth d is 1 that means
     * there is no depth d-1 at all, then create a tree node with value v as the new root of the whole original tree,
     * and the original tree is the new root's left subtree.
     * <p>
     * tags: binaryTree, bfs
     */
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
                assert temp != null;
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

    /**
     * Q. 971 Flip Binary Tree To Match Preorder Traversal
     * <p>
     * You are given the root of a binary tree with n nodes, where each node is uniquely assigned a value from 1 to n.
     * You are also given a sequence of n values voyage, which is the desired pre-order traversal of the binary tree.
     * Any node in the binary tree can be flipped by swapping its left and right subtrees.
     * Flip the smallest number of nodes so that the pre-order traversal of the tree matches voyage.
     * Return a list of the values of all flipped nodes. You may return the answer in any order.
     * If it is impossible to flip the nodes in the tree to make the pre-order traversal match voyage, return the list [-1].
     * <p>
     * tags:: binaryTree, dfs, preorder
     */
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

    /**
     * Q. 114 Flatten Binary Tree to Linked List
     * <p>
     * Given the root of a binary tree, flatten the tree into a "linked list":
     * The "linked list" should use the same TreeNode class where the right child pointer points to the
     * next node in the list and the left child pointer is always null.
     * The "linked list" should be in the same order as a pre-order traversal of the binary tree.
     * <p>
     * tags:: binaryTree, preorder
     */
    public void flatten(TreeNode root) {
        if (root == null)
            return;

        TreeNode left = root.getLeft();
        TreeNode right = root.getRight();

        flatten(left);
        flatten(right);

        TreeNode curr = root;
        curr.setLeft(null);
        curr.setRight(left);
        while (curr.getRight() != null)
            curr = curr.getRight();
        curr.setRight(right);
    }

    /**
     * Q.1302 Deepest Leaves Sum
     * <p>
     * Given the root of a binary tree, return the sum of values of its deepest leaves.
     * <p>
     * tags:: binaryTree, bfs
     */
    public int deepestLeavesSum(TreeNode root) {
        int sum = 0, i;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            for (i = q.size() - 1, sum = 0; i >= 0; i--) {
                TreeNode temp = q.poll();
                sum += temp.getVal();
                if (temp.getLeft() != null) q.add(temp.getLeft());
                if (temp.getRight() != null) q.add(temp.getRight());
            }
        }

        return sum;
    }

    /**
     * Q. 968 Binary Tree Cameras
     * <p>
     * Given a binary tree, we install cameras on the nodes of the tree. Each camera at a node can monitor its parent,
     * itself, and its immediate children. Calculate the minimum number of cameras needed to monitor all nodes of tree.
     * <p>
     * tags:: dfs, binaryTree
     */
    public int minCameraCover(TreeNode root) {
        cameraCoverCount = 0;
        if (cameraCoverDfs(root) == CameraType.LEAF)
            cameraCoverCount++;

        return cameraCoverCount;
    }

    private CameraType cameraCoverDfs(TreeNode node) {
        if (node == null)
            return CameraType.COVERED;

        CameraType left = cameraCoverDfs(node.getLeft());
        CameraType right = cameraCoverDfs(node.getRight());

        if (left == CameraType.LEAF || right == CameraType.LEAF) {
            cameraCoverCount++;
            return CameraType.PARENT;
        }

        return (left == CameraType.PARENT || right == CameraType.PARENT) ? CameraType.COVERED : CameraType.LEAF;
    }

    /**
     * Q. 1161 Maximum Level Sum of a Binary Tree
     * <p>
     * Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.
     * Return the smallest level x such that the sum of all the values of nodes at level x is maximal.
     * <p>
     * tags::bfs, binaryTree
     */
    public int maxLevelSum(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        int maxSumLevel = 1, maxSum = Integer.MIN_VALUE, currLevel = 0;

        q.offer(root);
        while (!q.isEmpty()) {
            int sum = 0;
            currLevel++;
            for (int i = q.size(); i > 0; i--) {
                TreeNode poll = q.poll();
                sum += poll.getVal();

                if (poll.getLeft() != null) q.offer(poll.getLeft());
                if (poll.getRight() != null) q.offer(poll.getRight());
            }

            if (sum > maxSum) {
                maxSum = sum;
                maxSumLevel = currLevel;
            }
        }

        return maxSumLevel;
    }

    private enum CameraType {
        LEAF, PARENT, COVERED
    }
}
