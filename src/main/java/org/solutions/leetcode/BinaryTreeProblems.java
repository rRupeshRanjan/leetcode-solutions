package org.solutions.leetcode;

import org.solutions.leetcode.dataStructures.TreeNode;

import java.util.*;

public class BinaryTreeProblems {

    private int cameraCoverCount;
    private int buildTreePreOrderInorderRootIndex;

    /**
     * Q. 199 Binary Tree Right Side View
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
     * <p>
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
     * Q. 637 Average of Levels in Binary Tree
     *
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
     * Q. 623 Add One Row to Tree
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
     * Q. 1302 Deepest Leaves Sum
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

    /**
     * Q. 105 Construct Binary Tree from Preorder and Inorder Traversal
     * <p>
     * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and
     * inorder is the inorder traversal of the same tree, construct and return the binary tree.
     * <p>
     * tags:: inorder, preorder, tree, dfs, array
     */
    public TreeNode buildTreeFromPreorderInorder(int[] preorder, int[] inorder) {
        buildTreePreOrderInorderRootIndex = 0;
        return recurseBuildTreePreorderInorder(0, preorder.length - 1, preorder, inorder);
    }

    private TreeNode recurseBuildTreePreorderInorder(int start, int end, int[] preorder, int[] inorder) {
        if (start > end)
            return null;

        TreeNode root = new TreeNode(preorder[buildTreePreOrderInorderRootIndex++]);
        if (start == end)
            return root;

        int pos = start;
        for (int i = start; i <= end; i++) {
            if (inorder[i] == root.getVal()) {
                pos = i;
                break;
            }
        }

        root.setLeft(recurseBuildTreePreorderInorder(start, pos - 1, preorder, inorder));
        root.setRight(recurseBuildTreePreorderInorder(pos + 1, end, preorder, inorder));
        return root;
    }

    /**
     * Q. 366 Find Leaves of Binary Tree
     * <p>
     * Given the root of a binary tree, collect a tree's nodes as if you were doing this:
     * Collect all the leaf nodes.
     * Remove all the leaf nodes.
     * Repeat until the tree is empty.
     * <p>
     * tags:: binaryTree, dfs
     */
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> answer = new ArrayList<>();
        dfsFindLeaves(root, answer);
        return answer;
    }

    private int dfsFindLeaves(TreeNode root, List<List<Integer>> answer) {
        if (root == null)
            return -1;

        int height = Math.max(dfsFindLeaves(root.getLeft(), answer), dfsFindLeaves(root.getRight(), answer)) + 1;

        if (answer.size() == height)
            answer.add(new ArrayList<>());

        answer.get(height).add(root.getVal());
        return height;
    }

    /**
     * Q. 652 Find Duplicate Subtrees
     * <p>
     * Given the root of a binary tree, return all duplicate subtrees.
     * For each kind of duplicate subtrees, you only need to return the root node of any one of them.
     * Two trees are duplicate if they have the same structure with the same node values.
     * <p>
     * tags:: dfs, postorder, binaryTree
     */
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String, List<TreeNode>> map = new HashMap<>();
        List<TreeNode> ans = new ArrayList<>();
        dfsFindDuplicateSubtrees(root, map);

        for (List<TreeNode> values : map.values()) {
            if (values.size() > 1)
                ans.add(values.get(0));
        }

        return ans;
    }

    private String dfsFindDuplicateSubtrees(TreeNode root, Map<String, List<TreeNode>> map) {
        if (root == null)
            return ".";

        String left = dfsFindDuplicateSubtrees(root.getLeft(), map);
        String right = dfsFindDuplicateSubtrees(root.getRight(), map);

        String key = new StringBuilder(root.getVal()).append("-").append(left).append("-").append(right).toString();

        if (!map.containsKey(key))
            map.put(key, new ArrayList<>());

        map.get(key).add(root);

        return key;
    }

    /**
     * Q. 1110 Delete Nodes And Return Forest
     * <p>
     * Given the root of a binary tree, each node in the tree has a distinct value.
     * After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).
     * Return the roots of the trees in the remaining forest. You may return the result in any order.
     * <p>
     * tags:: dfs, binaryTree
     */
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> toDelete = new HashSet<>();
        List<TreeNode> result = new ArrayList<>();

        for (int num : to_delete)
            toDelete.add(num);

        if (!toDelete.contains(root.getVal()))
            result.add(root);

        delNodesHelper(root, toDelete, result);
        return result;
    }

    private TreeNode delNodesHelper(TreeNode node, Set<Integer> toDelete, List<TreeNode> result) {
        if (node == null)
            return null;

        node.setLeft(delNodesHelper(node.getLeft(), toDelete, result));
        node.setRight(delNodesHelper(node.getRight(), toDelete, result));

        if (toDelete.contains(node.getVal())) {
            if (node.getLeft() != null) result.add(node.getLeft());
            if (node.getRight() != null) result.add(node.getRight());
            return null;
        }

        return node;
    }

    /**
     * Q. 515 Find Largest Value in Each Tree Row
     * <p>
     * Given the root of a binary tree, return an array of the largest value in each row of the tree (0-indexed).
     * <p>
     * tags:: bfs, binaryTree
     */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null)
            return result;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int max = Integer.MIN_VALUE;

            for (int i = q.size(); i > 0; i--) {
                TreeNode poll = q.poll();
                max = Math.max(max, poll.getVal());

                if (poll.getLeft() != null) q.offer(poll.getLeft());
                if (poll.getRight() != null) q.offer(poll.getRight());
            }
            result.add(max);
        }

        return result;
    }

    /**
     * Q. 543 Diameter of Binary Tree
     * <p>
     * Given the root of a binary tree, return the length of the diameter of the tree. The diameter of a binary tree is
     * the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
     * The length of a path between two nodes is represented by the number of edges between them.
     * <p>
     * tags::dfs, recursion
     */
    public int diameterOfBinaryTree(TreeNode root) {
        int[] maxLength = new int[1];
        recurseDiameterOfBinaryTree(root, maxLength);
        return maxLength[0];
    }

    private int recurseDiameterOfBinaryTree(TreeNode node, int[] maxLength) {
        if (node == null)
            return 0;

        int left = recurseDiameterOfBinaryTree(node.getLeft(), maxLength);
        int right = recurseDiameterOfBinaryTree(node.getRight(), maxLength);

        maxLength[0] = Math.max(maxLength[0], left + right);
        return Math.max(left, right) + 1;
    }

    /**
     * Q. 337 House Robber III
     * <p>
     * The thief has found himself a new place for his thievery again. There's only one entrance to this area, root.
     * Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that all
     * houses in this place form a binary tree. It will automatically contact the police if two directly-linked houses
     * were broken into on the same night.
     * <p>
     * Given the root of the binary tree, return the maximum amount of money the thief can rob without alerting police.
     * <p>
     * tags::binaryTree, dfs
     */
    public int houseRobberIII(TreeNode root) {
        int[] nums = helperHouseRobberIII(root);
        return Math.max(nums[0], nums[1]);
    }

    private int[] helperHouseRobberIII(TreeNode node) {
        if (node == null)
            return new int[]{0, 0}; // [not rob, rob]

        int[] left = helperHouseRobberIII(node.getLeft());
        int[] right = helperHouseRobberIII(node.getRight());

        int notRob = Math.max(left[0], left[1]) + Math.max(right[0], right[1]); // not rob this node
        int rob = left[0] + right[0] + node.getVal(); // rob this node

        return new int[]{notRob, rob}; // [not rob, rob]
    }

    /**
     * Q. 236 Lowest Common Ancestor of a Binary Tree
     * <p>
     * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
     * According to the definition of LCA on Wikipedia:
     * “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q
     * as descendants (where we allow a node to be a descendant of itself)."
     * <p>
     * tags:: recursion, binaryTree, dfs
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q)
            return root;

        TreeNode left = lowestCommonAncestor(root.getLeft(), p, q);
        TreeNode right = lowestCommonAncestor(root.getRight(), p, q);

        if (left != null && right != null)
            return root;
        return (left == null) ? right : left;
    }

    /**
     * Q. 1644 Lowest Common Ancestor of a Binary Tree II
     * Given the root of a binary tree, return the lowest common ancestor (LCA) of two given nodes, p and q.
     * If either node p or q does not exist in the tree, return null. All values of the nodes in the tree are unique.
     * <p>
     * According to the definition of LCA on Wikipedia:
     * "The lowest common ancestor of two nodes p and q in a binary tree T is the lowest node that has both p and q as
     * descendants (where we allow a node to be a descendant of itself)".
     * A descendant of a node x is a node y that is on the path from node x to some leaf node.
     * <p>
     * tags::binaryTree, dfs
     */
    public TreeNode lowestCommonAncestorII(TreeNode root, TreeNode p, TreeNode q) {
        int[] count = new int[1];
        TreeNode lca = lowestCommonAncestorIIHelper(root, p, q, count);
        return (count[0] == 2) ? lca : null;
    }

    private TreeNode lowestCommonAncestorIIHelper(TreeNode root, TreeNode p, TreeNode q, int[] count) {
        if (root == null)
            return root;

        TreeNode left = lowestCommonAncestorIIHelper(root.getLeft(), p, q, count);
        TreeNode right = lowestCommonAncestorIIHelper(root.getRight(), p, q, count);

        if (root == p || root == q) {
            count[0]++;
            return root;
        }

        return (left == null) ? right : (right == null) ? left : root;
    }

    /**
     * Q. 298 Binary Tree Longest Consecutive Sequence
     * <p>
     * Given the root of a binary tree, return the length of the longest consecutive sequence path.
     * <p>
     * The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child
     * connections. The longest consecutive path needs to be from parent to child (cannot be the reverse).
     * <p>
     * tags:: dfs, recursion
     */
    public int longestConsecutiveSequence(TreeNode root) {
        int[] maxCount = new int[1];
        longestConsecutiveSequenceHelper(root, maxCount);
        return maxCount[0];
    }

    private int longestConsecutiveSequenceHelper(TreeNode root, int[] maxCount) {
        if (root == null)
            return 0;

        int left = longestConsecutiveSequenceHelper(root.getLeft(), maxCount) + 1;
        int right = longestConsecutiveSequenceHelper(root.getRight(), maxCount) + 1;

        if (root.getLeft() != null && root.getVal() + 1 != root.getLeft().getVal())
            left = 1;
        if (root.getRight() != null && root.getVal() + 1 != root.getRight().getVal())
            right = 1;

        int count = Math.max(left, right);
        maxCount[0] = Math.max(maxCount[0], count);
        return count;
    }

    /**
     * Q. 102 Binary Tree Level Order Traversal
     * <p>
     * Given the root of a binary tree, return the level order traversal of its nodes' values.
     * (i.e., from left to right, level by level).
     * <p>
     * tags:: bfs
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> levelOrderTraversal = new ArrayList<>();
        if (root == null)
            return levelOrderTraversal;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            List<Integer> holder = new ArrayList<>();
            for (int i = queue.size(); i > 0; i--) {
                TreeNode poll = queue.poll();
                holder.add(poll.getVal());

                if (poll.getLeft() != null) queue.add(poll.getLeft());
                if (poll.getRight() != null) queue.add(poll.getRight());
            }
            levelOrderTraversal.addAll(List.of(holder));
        }

        return levelOrderTraversal;
    }

    /**
     * Q. 103 Binary Tree Zigzag Level Order Traversal
     * <p>
     * Given the root of a binary tree, return the zigzag level order traversal of its nodes' values.
     * (i.e., from left to right, then right to left for the next level and alternate between).
     * <p>
     * tags:: bfs
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> zigZagTraversal = new ArrayList<>();
        if (root == null)
            return zigZagTraversal;

        boolean flip = false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            List<Integer> holder = new ArrayList<>();
            for (int i = queue.size(); i > 0; i--) {
                TreeNode poll = queue.poll();
                holder.add(poll.getVal());

                if (poll.getLeft() != null) queue.add(poll.getLeft());
                if (poll.getRight() != null) queue.add(poll.getRight());
            }

            if (flip) Collections.reverse(holder);
            flip = !flip;
            zigZagTraversal.addAll(List.of(holder));
        }

        return zigZagTraversal;
    }

    private enum CameraType {
        LEAF, PARENT, COVERED
    }
}
