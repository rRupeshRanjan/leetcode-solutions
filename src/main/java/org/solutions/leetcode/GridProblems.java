package org.solutions.leetcode;

import org.apache.commons.lang3.tuple.Pair;
import org.solutions.leetcode.utils.ArrayUtils;

import java.util.*;

public class GridProblems {

    private final ArrayUtils arrayUtils;

    public GridProblems() {
        this.arrayUtils = new ArrayUtils();
    }

    /**
    * Q.1091
    *
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

        Queue<Pair<Integer, Integer>> q = new LinkedList<>();
        q.add(Pair.of(0, 0));
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Pair<Integer, Integer> poll = q.poll();
                int x = poll.getLeft();
                int y = poll.getRight();

                if (x == n && y == n) return level + 1;
                if (grid[x][y] == 1) continue;

                grid[x][y] = 1;
                for (int[] d : dir) {
                    int x_new = x + d[0];
                    int y_new = y + d[1];
                    if (x_new < 0 || y_new < 0 || x_new > n || y_new > n || grid[x_new][y_new] == 1)
                        continue;

                    q.add(Pair.of(x_new, y_new));
                }
            }
            level++;
        }

        return -1;
    }

    /**
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
        for (int node = 0; node < graph.length; node++) {
            if (!colorMap.containsKey(node) && notBipartiteDfs(node, 0, colorMap, graph))
                return false;
        }
        return true;
    }

    /**
     * Helper method to to DFS traversal for bipartite check
     * */
    private boolean notBipartiteDfs(int node, int color, Map<Integer, Integer> colorMap, int[][] graph) {
        if (colorMap.containsKey(node))
            return colorMap.get(node) != color;

        colorMap.put(node, color);
        for (int child : graph[node]) {
            if (notBipartiteDfs(child, color ^ 1, colorMap, graph))
                return true;
        }
        return false;
    }

    /**
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

        for (int i = 1; i <= N + 1; i++)
            graphList.add(new ArrayList<>());

        for (int[] arr : dislikes) {
            graphList.get(arr[0]).add(arr[1]);
            graphList.get(arr[1]).add(arr[0]);
        }

        int[][] graph = arrayUtils.convert2DListTo2DArray(graphList);
        for (int node = 0; node < N + 1; node++) {
            if (!colorMap.containsKey(node) && notBipartiteDfs(node, 0, colorMap, graph))
                return false;
        }

        return true;
    }

    /**
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
        PriorityQueue<Integer> pq = new PriorityQueue<>((i, j) ->
                (count[i] == count[j]) ? (j - i) : (count[j] - count[i])
        );

        for (int i = 0; i < mat.length; i++)
            count[i] = Arrays.stream(mat[i]).sum();

        for (int i = 0; i < count.length; i++) {
            pq.add(i);
            if (pq.size() > k) pq.poll();
        }

        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--)
            result[i] = pq.poll();

        return result;
    }

    /**
     * Q. 841
     *
     * There are N rooms and you start in room 0.  Each room has a distinct number in 0, 1, 2, ..., N-1,
     * and each room may have some keys to access the next room. Formally, each room i has a list of keys rooms[i],
     * and each key rooms[i][j] is an integer in [0, 1, ..., N-1] where N = rooms.length.
     * A key rooms[i][j] = v opens the room with number v.
     * Initially, all the rooms start locked (except for room 0).  You can walk back and forth between rooms freely.
     *
     * Return true if and only if you can enter every room.
     *
     * tags:: dfs, graph
     * */
    public boolean canVisitAllRooms(int[][] rooms) {
        // alternately, can use a set to maintain set of visited rooms, omitting visitedCount variable
        // but choosing boolean array for space optimization.
        boolean[] visited = new boolean[rooms.length];
        Stack<Integer> stack = new Stack<>();
        int visitedCount = 1;

        visited[0] = true;
        stack.push(0);

        while (!stack.isEmpty()) {
            for (int i : rooms[stack.pop()]) {
                if (!visited[i]) {
                    stack.push(i);
                    visited[i] = true;
                    visitedCount++;
                }
            }
        }

        return visitedCount == rooms.length;
    }

    /**
     * Q. 417
     *
     * You are given an m x n integer matrix heights representing the height of each unit cell in a continent.
     * The Pacific ocean touches the continent's left and top edges, and the Atlantic ocean touches the continent's
     * right and bottom edges. Water can only flow in four directions: up, down, left, and right.
     * Water flows from a cell to an adjacent one with an equal or lower height.
     * Return a list of grid coordinates where water can flow to both the Pacific and Atlantic oceans.
     *
     * tags:: dfs, grid
     * */
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int rows = heights.length, cols = heights[0].length;
        Set<String> pacific = new HashSet<>(), atlantic = new HashSet<>();
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 || j == 0)
                    dfsPacificAtlantic(pacific, i, j, heights, directions);

                if (i == rows - 1 || j == cols - 1)
                    dfsPacificAtlantic(atlantic, i, j, heights, directions);
            }
        }

        pacific.retainAll(atlantic);

        for (String entry : pacific) {
            String[] s = entry.split("-");
            result.add(Arrays.asList(Integer.parseInt(s[0]), Integer.parseInt(s[1])));
        }

        return result;
    }

    private void dfsPacificAtlantic(Set<String> visited, int i, int j, int[][] heights, int[][] directions) {
        visited.add(i + "-" + j);
        for (int[] dir : directions) {
            int nextI = i + dir[0];
            int nextJ = j + dir[1];

            if (nextI < 0 || nextI == heights.length ||
                    nextJ < 0 || nextJ == heights[0].length ||
                    visited.contains(nextI + "-" + nextJ))
                continue;

            if (heights[i][j] <= heights[nextI][nextJ])
                dfsPacificAtlantic(visited, nextI, nextJ, heights, directions);
        }
    }

    /**
     * Q. 48
     *
     * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
     * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
     * DO NOT allocate another 2D matrix and do the rotation.
     *
     * tags:: grid, logic, transpose
     * */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < (n - i); j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][n - i - 1];
                matrix[n - j - 1][n - i - 1] = temp;
            }
        }

        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = temp;
            }
        }
    }

    /**
     * Q. 207 Course schedule I
     *
     * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an
     * array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to
     * take course ai.
     * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
     * Return true if you can finish all courses. Otherwise, return false.
     *
     * tags:: topologicalSort, graph
     * */
    public boolean courseScheduleI(int numCourses, int[][] prerequisites) {
        List<Integer>[] adjList = new ArrayList[numCourses];
        int[] indegree = new int[numCourses];
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < numCourses; i++)
            adjList[i] = new ArrayList<>();

        for (int[] course : prerequisites) {
            adjList[course[1]].add(course[0]);
            indegree[course[0]]++;
        }

        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0)
                q.add(i);
        }

        int i = 0;
        while (!q.isEmpty()) {
            int node = q.poll();
            i++;

            for (int ch : adjList[node]) {
                indegree[ch]--;
                if (indegree[ch] == 0)
                    q.add(ch);
            }
        }

        return i == numCourses;
    }

    /**
     * Q. 210 Course schedule II
     *
     * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an
     * array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to
     * take course ai.
     * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
     * Return the ordering of courses you should take to finish all courses. If there are many valid answers,
     * return any of them. If it is impossible to finish all courses, return an empty array.
     *
     * tags:: graph, topologicalSort
     * */
    public int[] courseScheduleII(int numCourses, int[][] prerequisites) {
        List<Integer>[] adjList = new ArrayList[numCourses];
        int[] indegree = new int[numCourses];
        Queue<Integer> q = new LinkedList<>();
        int[] answer = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int[] c : prerequisites) {
            adjList[c[1]].add(c[0]);
            indegree[c[0]]++;
        }

        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0)
                q.add(i);
        }

        int i = 0;
        while (!q.isEmpty()) {
            int node = q.poll();
            answer[i++] = node;

            for (int ch : adjList[node]) {
                indegree[ch]--;

                if (indegree[ch] == 0)
                    q.add(ch);
            }
        }

        return (i == numCourses) ? answer : new int[0];
    }

    /**
     * Q. 630 Course schedule III
     *
     * There are n different online courses numbered from 1 to n. You are given an array courses where
     * courses[i] = [durationi, lastDayi] indicate that the ith course should be taken continuously for durationi days
     * and must be finished before or on lastDayi.
     * You will start on the 1st day and you cannot take two or more courses simultaneously.
     *
     * Return the maximum number of courses that you can take.
     *
     * tags:: greedy, priorityQueue
     * */
    public int courseScheduleIII(int[][] courses) {
        Arrays.sort(courses, Comparator.comparingInt(a -> a[1]));
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));

        int time = 0;
        for (int[] c : courses) {
            time += c[0];
            pq.add(c[0]);
            if (time > c[1]) {
                time -= pq.poll();
            }
        }

        return pq.size();
    }
}
