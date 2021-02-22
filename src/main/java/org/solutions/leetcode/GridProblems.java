package org.solutions.leetcode;

import org.solutions.leetcode.dataStructures.Pair;
import org.solutions.leetcode.utils.ArrayUtils;

import java.util.*;

public class GridProblems {

    private ArrayUtils arrayUtils;

    public GridProblems() {
        this.arrayUtils = new ArrayUtils();
    }

    /*
    * Q.1091
    * In an N by N square grid, each cell is either empty (0) or blocked (1).
    * A clear path from top-left to bottom-right has length k if and only if it is composed of cells C_1, C_2, ..., C_k such that:
        Adjacent cells C_i and C_{i+1} are connected 8-directionally (ie., they are different and share an edge or corner)
        C_1 is at location (0, 0) (ie. has value grid[0][0])
        C_k is at location (N-1, N-1) (ie. has value grid[N-1][N-1])
        If C_i is located at (r, c), then grid[r][c] is empty (ie. grid[r][c] == 0).
    * Return the length of the shortest such clear path from top-left to bottom-right.  If such a path does not exist, return -1.
    *
    * IDEA:: Do a BFS, not DP (as we need results from all directions).
    * Tags:: bfs, graph
    * */
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length - 1;
        int level = 0;
        int[][] dir = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};

        if (grid[0][0] == 1 || grid[n][n] == 1) {
            return -1;
        }

        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0, 0));
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Pair poll = q.poll();
                int x = poll.getX();
                int y = poll.getY();

                if (x == n && y == n)
                    return level + 1;

                if (x < 0 || y < 0 || x > n || y > n || grid[x][y] == 1)
                    continue;

                grid[x][y] = 1;
                for (int[] d : dir) {
                    int x_new = x + d[0];
                    int y_new = y + d[1];
                    if (x_new < 0 || y_new < 0 || x_new > n || y_new > n || grid[x_new][y_new] == 1)
                        continue;

                    q.add(new Pair(x_new, y_new));
                }
            }
            level++;
        }

        return -1;
    }

    /*
    * Q. 785
    * There is an undirected graph with n nodes, where each node is numbered between 0 and n - 1.
    * You are given a 2D array graph, where graph[u] is an array of nodes that node u is adjacent to.
    * More formally, for each v in graph[u], there is an undirected edge between node u and node v.
    * The graph has the following properties:
    *   There are no self-edges (graph[u] does not contain u).
    *   There are no parallel edges (graph[u] does not contain duplicate values).
    *   If v is in graph[u], then u is in graph[v] (the graph is undirected).
    *   The graph may not be connected, meaning there may be two nodes u and v such that there is no path between them.
    *
    * A graph is bipartite if the nodes can be partitioned into two independent sets A and B such that
    * every edge in the graph connects a node in set A and a node in set B.
    *
    * Return true if and only if it is bipartite.
    *
    * Tags:: graph, recursion, bipartition
    * */
    public boolean isBipartite(int[][] graph) {
        Map<Integer, Integer> colorMap = new HashMap<>();
        for(int node = 0; node<graph.length; node++) {
            if(!colorMap.containsKey(node) && notBipartiteDfs(node, 0, colorMap, graph))
                return false;
        }
        return true;
    }

    /*
    * Helper method to to DFS traversal for bipartite check
    * */
    private boolean notBipartiteDfs(int node, int color, Map<Integer, Integer> colorMap, int[][] graph) {
        if(colorMap.containsKey(node))
            return colorMap.get(node) != color;

        colorMap.put(node, color);
        for(int child: graph[node]) {
            if(notBipartiteDfs(child, color ^ 1, colorMap, graph))
                return true;
        }
        return false;
    }

    /*
    * Q. 886
    * Given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two groups of any size.
    * Each person may dislike some other people, and they should not go into the same group.
    * Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.
    *
    * Return true if and only if it is possible to split everyone into two groups in this way.
    *
    * Tags:: graph, recursion, bipartition
    * */
    public boolean possibleBipartition(int N, int[][] dislikes) {
        List<List<Integer>> graphList = new ArrayList<>();
        Map<Integer, Integer> colorMap = new HashMap<>();

        for(int i=1; i<=N+1; i++)
            graphList.add(new ArrayList<>());

        for(int[] arr: dislikes) {
            graphList.get(arr[0]).add(arr[1]);
            graphList.get(arr[1]).add(arr[0]);
        }

        int[][] graph = arrayUtils.convert2DListTo2DArray(graphList);
        for(int node=0; node<N+1; node++) {
            if(!colorMap.containsKey(node) && notBipartiteDfs(node, 0, colorMap, graph))
                return false;
        }

        return true;
    }

    /*
    * Q. 1337
    * You are given an m x n binary matrix mat of 1's (representing soldiers) and 0's (representing civilians).
    * The soldiers are positioned in front of the civilians. That is, all the 1's will appear to the left of all the 0's in each row.
    * A row i is weaker than a row j if one of the following is true:
    *   The number of soldiers in row i is less than the number of soldiers in row j.
    *   Both rows have the same number of soldiers and i < j.
    *
    * Return the indices of the k weakest rows in the matrix ordered from weakest to strongest.
    *
    * Tags:: minHeap,
    * */
    public int[] kWeakestRows(int[][] mat, int k) {
        int[] count = new int[mat.length];
        PriorityQueue<Integer> pq = new PriorityQueue<>((i,j) ->
                (count[i] == count[j]) ? (j-i) : (count[j] - count[i])
        );

        for(int i=0; i<mat.length; i++)
            count[i] = Arrays.stream(mat[i]).sum();

        for(int i=0; i<count.length; i++) {
            pq.add(i);
            if(pq.size() > k) pq.poll();
        }

        int[] result = new int[k];
        for(int i=k-1; i>=0; i--)
            result[i] = pq.poll();

        return result;
    }
}
