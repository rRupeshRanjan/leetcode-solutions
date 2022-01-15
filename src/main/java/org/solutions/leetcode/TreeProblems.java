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

    /**
     * Q. 1245 Tree Diameter
     * <p>
     * Given an undirected tree, return its diameter: the number of edges in a longest path in that tree.
     * <p>
     * The tree is given as an array of edges where edges[i] = [u, v] is a bidirectional edge between nodes u and v.
     * Each node has labels in the set {0, 1, ..., edges.length}.
     * <p>
     * tags:: tree, dfs, adjacencyGraph
     */
    public int treeDiameter(int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] diameter = new int[1];

        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], x -> new ArrayList<>()).add(edge[1]);
        }

        dfsTreeDiameter(graph, 0, -1, diameter);
        return diameter[0];
    }

    private int dfsTreeDiameter(Map<Integer, List<Integer>> map, int node, int parent, int[] diameter) {
        int topDistance1 = 0, topDistance2 = 0;
        for (int curr : map.getOrDefault(node, new ArrayList<>())) {
            if (curr == parent)
                continue;
            int currDistance = dfsTreeDiameter(map, curr, node, diameter);

            if (currDistance >= topDistance1) {
                topDistance2 = topDistance1;
                topDistance1 = currDistance;
            } else if (currDistance >= topDistance2) {
                topDistance2 = currDistance;
            }
        }

        diameter[0] = Math.max(diameter[0], topDistance1 + topDistance2);
        return topDistance1 + 1;
    }
}
