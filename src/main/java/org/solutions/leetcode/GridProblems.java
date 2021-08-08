package org.solutions.leetcode;

import org.apache.commons.lang3.tuple.Pair;
import org.solutions.leetcode.utils.ArrayUtils;

import java.util.*;

public class GridProblems {

    private final ArrayUtils arrayUtils;

    /**
     * Q. 52 N-Queens II
     * <p>
     * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack
     * each other. Given an integer n, return the number of distinct solutions to the n-queens puzzle.
     * <p>
     * tags:: backtracking, hashSet
     */
    int totalNQueenCount;

    public GridProblems() {
        this.arrayUtils = new ArrayUtils();
    }

    /**
     * Q.1091 Shortest Path in Binary Matrix
     * <p>
     * In an N by N square grid, each cell is either empty (0) or blocked (1).
     * A clear path from top-left to bottom-right has length k if and only if it is composed of cells C_1, C_2, ..., C_k
     * such that:
     * Adjacent cells C_i and C_{i+1} are connected 8-directionally (ie., they are different and share an edge or corner)
     * C_1 is at location (0, 0) (ie. has value grid[0][0])
     * C_k is at location (N-1, N-1) (ie. has value grid[N-1][N-1])
     * If C_i is located at (r, c), then grid[r][c] is empty (ie. grid[r][c] == 0).
     * Return the length of the shortest such clear path from top-left to bottom-right.
     * If such a path does not exist, return -1.
     * <p>
     * IDEA:: Do a BFS, not DP (as we need results from all directions).
     * Tags:: bfs, graph
     */
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
                    int xNew = x + d[0];
                    int yNew = y + d[1];
                    if (xNew < 0 || yNew < 0 || xNew > n || yNew > n || grid[xNew][yNew] == 1)
                        continue;

                    q.add(Pair.of(xNew, yNew));
                }
            }
            level++;
        }

        return -1;
    }

    /**
     * Q. 785 Is Graph Bipartite?
     * There is an undirected graph with n nodes, where each node is numbered between 0 and n - 1.
     * You are given a 2D array graph, where graph[u] is an array of nodes that node u is adjacent to.
     * More formally, for each v in graph[u], there is an undirected edge between node u and node v.
     * The graph has the following properties:
     * There are no self-edges (graph[u] does not contain u).
     * There are no parallel edges (graph[u] does not contain duplicate values).
     * If v is in graph[u], then u is in graph[v] (the graph is undirected).
     * The graph may not be connected, meaning there may be two nodes u and v such that there is no path between them.
     * <p>
     * A graph is bipartite if the nodes can be partitioned into two independent sets A and B such that
     * every edge in the graph connects a node in set A and a node in set B.
     * <p>
     * Return true if and only if it is bipartite.
     * <p>
     * Tags:: graph, recursion, bipartition
     */
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
     */
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
     * Q. 886 Possible Bipartition
     * Given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two groups of any size.
     * Each person may dislike some other people, and they should not go into the same group. Formally,
     * if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.
     * <p>
     * Return true if and only if it is possible to split everyone into two groups in this way.
     * <p>
     * Tags:: graph, recursion, bipartition
     */
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
     * Q. 1337 The K Weakest Rows in a Matrix
     * You are given an m x n binary matrix mat of 1's (representing soldiers) and 0's (representing civilians).
     * The soldiers are positioned in front of the civilians. That is, all the 1's will appear to the left of
     * all the 0's in each row.
     * A row i is weaker than a row j if one of the following is true:
     * The number of soldiers in row i is less than the number of soldiers in row j.
     * Both rows have the same number of soldiers and i < j.
     * <p>
     * Return the indices of the k weakest rows in the matrix ordered from weakest to strongest.
     * <p>
     * Tags:: minHeap,
     */
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
            result[i] = pq.remove();

        return result;
    }

    /**
     * Q. 841 Keys and Rooms
     * <p>
     * There are N rooms and you start in room 0.  Each room has a distinct number in 0, 1, 2, ..., N-1,
     * and each room may have some keys to access the next room. Formally, each room i has a list of keys rooms[i],
     * and each key rooms[i][j] is an integer in [0, 1, ..., N-1] where N = rooms.length.
     * A key rooms[i][j] = v opens the room with number v.
     * Initially, all the rooms start locked (except for room 0).  You can walk back and forth between rooms freely.
     * <p>
     * Return true if and only if you can enter every room.
     * <p>
     * tags:: dfs, graph
     */
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
     * Q. 417 Pacific Atlantic Water Flow
     * <p>
     * You are given an m x n integer matrix heights representing the height of each unit cell in a continent.
     * The Pacific ocean touches the continent's left and top edges, and the Atlantic ocean touches the continent's
     * right and bottom edges. Water can only flow in four directions: up, down, left, and right.
     * Water flows from a cell to an adjacent one with an equal or lower height.
     * Return a list of grid coordinates where water can flow to both the Pacific and Atlantic oceans.
     * <p>
     * tags:: dfs, grid
     */
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int rows = heights.length, cols = heights[0].length;
        boolean[][] pacific = new boolean[rows][cols], atlantic = new boolean[rows][cols];
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 || j == 0)
                    dfsPacificAtlantic(pacific, i, j, heights, directions);

                if (i == rows - 1 || j == cols - 1)
                    dfsPacificAtlantic(atlantic, i, j, heights, directions);
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (pacific[i][j] && atlantic[i][j])
                    result.add(Arrays.asList(i, j));
            }
        }

        return result;
    }

    private void dfsPacificAtlantic(boolean[][] visited, int i, int j, int[][] heights, int[][] directions) {
        visited[i][j] = true;
        for (int[] dir : directions) {
            int nextI = i + dir[0];
            int nextJ = j + dir[1];

            if (nextI < 0 || nextI == heights.length ||
                    nextJ < 0 || nextJ == heights[0].length ||
                    visited[nextI][nextJ])
                continue;

            if (heights[i][j] <= heights[nextI][nextJ])
                dfsPacificAtlantic(visited, nextI, nextJ, heights, directions);
        }
    }

    /**
     * Q. 48 Rotate Image
     * <p>
     * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
     * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
     * DO NOT allocate another 2D matrix and do the rotation.
     * <p>
     * tags:: grid, logic, transpose
     */
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
     * <p>
     * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an
     * array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to
     * take course ai.
     * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
     * Return true if you can finish all courses. Otherwise, return false.
     * <p>
     * tags:: topologicalSort, graph
     */
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
     * <p>
     * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an
     * array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to
     * take course ai.
     * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
     * Return the ordering of courses you should take to finish all courses. If there are many valid answers,
     * return any of them. If it is impossible to finish all courses, return an empty array.
     * <p>
     * tags:: graph, topologicalSort
     */
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
     * <p>
     * There are n different online courses numbered from 1 to n. You are given an array courses where
     * courses[i] = [durationi, lastDayi] indicate that the ith course should be taken continuously for durationi days
     * and must be finished before or on lastDayi.
     * You will start on the 1st day and you cannot take two or more courses simultaneously.
     * <p>
     * Return the maximum number of courses that you can take.
     * <p>
     * tags:: greedy, priorityQueue
     */
    public int courseScheduleIII(int[][] courses) {
        Arrays.sort(courses, Comparator.comparingInt(a -> a[1]));
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));

        int time = 0;
        for (int[] c : courses) {
            time += c[0];
            pq.add(c[0]);
            if (time > c[1]) {
                time -= pq.remove();
            }
        }

        return pq.size();
    }

    /**
     * Q. 51. N-Queens
     * <p>
     * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack
     * each other. Given an integer n, return all distinct solutions to the n-queens puzzle.
     * <p>
     * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both
     * indicate a queen and an empty space, respectively.
     * <p>
     * tags:: backtracking, string, grid
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] state = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                state[i][j] = '.';
            }
        }
        backtrackNQueens(state, n, 0, new boolean[2 * n], new boolean[2 * n], new boolean[n], result);
        return result;
    }

    private void backtrackNQueens(char[][] state, int n, int row, boolean[] diagonals, boolean[] antiDiagonals,
                                  boolean[] cols, List<List<String>> result) {
        if (row == n) {
            result.add(createBoardNqueens(state));
            return;
        }

        for (int col = 0; col < n; col++) {
            int currDiagonal = row - col + n;
            int currAntiDiagonal = row + col;

            if (diagonals[currDiagonal] || antiDiagonals[currAntiDiagonal] || cols[col])
                continue;

            diagonals[currDiagonal] = true;
            antiDiagonals[currAntiDiagonal] = true;
            cols[col] = true;

            state[row][col] = 'Q';

            backtrackNQueens(state, n, row + 1, diagonals, antiDiagonals, cols, result);

            diagonals[currDiagonal] = false;
            antiDiagonals[currAntiDiagonal] = false;
            cols[col] = false;
            state[row][col] = '.';
        }
    }

    private List<String> createBoardNqueens(char[][] state) {
        List<String> board = new ArrayList<>();
        for (char[] chars : state) {
            board.add(new String(chars));
        }
        return board;
    }

    /**
     * Q. 52 N-Queens II
     * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack
     * each other. Given an integer n, return the number of distinct solutions to the n-queens puzzle.
     * <p>
     * tags:: backtracking, string, grid
     */
    public int totalNQueens(int n) {
        totalNQueenCount = 0;
        backtrackTotalNQueens(0, n, new boolean[n], new boolean[2 * n], new boolean[2 * n]);
        return totalNQueenCount;
    }

    private void backtrackTotalNQueens(int row, int n, boolean[] cols, boolean[] diagonals, boolean[] antiDiagonals) {
        if (row == n) {
            totalNQueenCount++;
            return;
        }

        for (int col = 0; col < n; col++) {
            int diag = row - col + n;
            int antiDiag = row + col;
            if (cols[col] || diagonals[diag] || antiDiagonals[antiDiag])
                continue;

            cols[col] = true;
            diagonals[diag] = true;
            antiDiagonals[antiDiag] = true;

            backtrackTotalNQueens(row + 1, n, cols, diagonals, antiDiagonals);

            cols[col] = false;
            diagonals[diag] = false;
            antiDiagonals[antiDiag] = false;
        }
    }

    /**
     * Q. 797 All Paths From Source to Target
     * Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths from node 0 to
     * node n - 1, and return them in any order.
     * <p>
     * The graph is given as follows:
     * graph[i] is a list of all nodes you can visit from node i
     * (i.e., there is a directed edge from node i to node graph[i][j]).
     * <p>
     * tags:: dfs, backtracking, graph
     */
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> result = new ArrayList<>();
        dfsAllPathsSourceTarget(graph, 0, new ArrayList<>(Collections.singletonList(0)), result);
        return result;
    }

    private void dfsAllPathsSourceTarget(int[][] graph, int index, List<Integer> holder, List<List<Integer>> result) {
        if (index == graph.length - 1) {
            result.add(new ArrayList<>(holder));
            return;
        }

        for (int num : graph[index]) {
            holder.add(num);
            dfsAllPathsSourceTarget(graph, num, holder, result);
            holder.remove(holder.size() - 1);
        }
    }

    /**
     * Q. 1162 As Far from Land as Possible
     * <p>
     * Given an n x n grid containing only values 0 and 1, where 0 represents water and 1 represents land, find a water
     * cell such that its distance to the nearest land cell is maximized, and return the distance.
     * If no land or water exists in the grid, return -1.
     * <p>
     * The distance used in this problem is the Manhattan distance:
     * the distance between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.
     * <p>
     * tags:: bfs
     */
    public int maxDistance(int[][] grid) {
        Queue<int[]> q = new LinkedList<>();
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        int n = grid.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    q.add(new int[]{i, j});
                }
            }
        }
        if (q.isEmpty() || q.size() == n * n)
            return -1;

        int count = -1;
        while (!q.isEmpty()) {
            count++;
            for (int i = q.size(); i > 0; i--) {
                int[] poll = q.poll();
                for (int[] dir : dirs) {
                    int nextI = poll[0] + dir[0];
                    int nextJ = poll[1] + dir[1];

                    if (nextI < 0 || nextJ < 0 || nextI == n || nextJ == n || grid[nextI][nextJ] == 1)
                        continue;
                    grid[nextI][nextJ] = 1;
                    q.add(new int[]{nextI, nextJ});
                }
            }
        }

        return count;
    }

    /**
     * Q. 542 01 Matrix
     * <p>
     * Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
     * The distance between two adjacent cells is 1.
     * <p>
     * tags:: bfs
     */
    public int[][] update01Matrix(int[][] mat) {
        Queue<int[]> q = new LinkedList<>();
        int m = mat.length, n = mat[0].length;
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        int[][] dist = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    q.offer(new int[]{i, j});
                } else {
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        while (!q.isEmpty()) {
            for (int i = q.size(); i > 0; i--) {
                int[] poll = q.poll();
                for (int[] dir : dirs) {
                    int nextI = poll[0] + dir[0];
                    int nextJ = poll[1] + dir[1];

                    if (nextI < 0 || nextJ < 0 || nextI == m || nextJ == n || dist[nextI][nextJ] <= dist[poll[0]][poll[1]] + 1)
                        continue;

                    dist[nextI][nextJ] = dist[poll[0]][poll[1]] + 1;
                    q.offer(new int[]{nextI, nextJ});
                }
            }
        }

        return dist;
    }

    /**
     * Q. 130. Surrounded Regions
     * <p>
     * Given an m x n matrix board containing 'X' and 'O', capture all regions surrounded by 'X'.
     * A region is captured by flipping all 'O's into 'X's in that surrounded region.
     * <p>
     * tags:: dfs, bfs
     */
    public void coverSurroundedRegions(char[][] board) {
        int rows = board.length, cols = board[0].length;
        List<int[]> borders = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            borders.add(new int[]{i, 0});
            borders.add(new int[]{i, cols - 1});
        }

        for (int i = 0; i < cols; i++) {
            borders.add(new int[]{0, i});
            borders.add(new int[]{rows - 1, i});
        }

        for (int[] border : borders) {
            dfsCoverSurroundedRegions(board, border[0], border[1]);
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
                else if (board[i][j] == 'E')
                    board[i][j] = 'O';
            }
        }
    }

    private void dfsCoverSurroundedRegions(char[][] board, int i, int j) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] != 'O')
            return;

        board[i][j] = 'E';
        dfsCoverSurroundedRegions(board, i, j + 1);
        dfsCoverSurroundedRegions(board, i, j - 1);
        dfsCoverSurroundedRegions(board, i + 1, j);
        dfsCoverSurroundedRegions(board, i - 1, j);
    }

    /**
     * Q. 684. Redundant Connection
     * In this problem, a tree is an undirected graph that is connected and has no cycles.
     * <p>
     * You are given a graph that started as a tree with n nodes labeled from 1 to n, with one additional edge added.
     * The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed.
     * The graph is represented as an array edges of length n where edges[i] = [ai, bi] indicates
     * that there is an edge between nodes ai and bi in the graph.
     * <p>
     * Return an edge that can be removed so that the resulting graph is a tree of n nodes.
     * If there are multiple answers, return the answer that occurs last in the input.
     * <p>
     * tags:: graph, union-find
     */
    public int[] findRedundantConnection(int[][] edges) {
        int MAX_EDGE_VAL = 1000;
        int[] parent = new int[MAX_EDGE_VAL + 1];
        for (int i = 0; i < MAX_EDGE_VAL; i++)
            parent[i] = i;

        for (int[] edge : edges) {
            int xParent = find(parent, edge[0]);
            int yParent = find(parent, edge[1]);

            if (xParent == yParent)
                return edge;
            else
                parent[xParent] = yParent;
        }

        return new int[]{};
    }

    private int find(int[] parent, int x) {
        if (parent[x] != x)
            parent[x] = find(parent, parent[x]);
        return parent[x];
    }

    /**
     * Q. 695. Max Area of Island
     * <p>
     * You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected
     * 4-directionally (horizontal or vertical) You may assume all four edges of the grid are surrounded by water.
     * <p>
     * The area of an island is the number of cells with a value 1 in the island.
     * Return the maximum area of an island in grid. If there is no island, return 0.
     * <p>
     * tags:: bfs
     */
    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1)
                    maxArea = Math.max(maxArea, bfsMaxAreaOfIsland(grid, i, j));
            }
        }

        return maxArea;
    }

    private int bfsMaxAreaOfIsland(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != 1)
            return 0;

        grid[i][j] = 0;
        return 1 + bfsMaxAreaOfIsland(grid, i - 1, j) +
                bfsMaxAreaOfIsland(grid, i + 1, j) +
                bfsMaxAreaOfIsland(grid, i, j - 1) +
                bfsMaxAreaOfIsland(grid, i, j + 1);
    }

    /**
     * Q. 200. Number of Islands
     * <p>
     * Given an m x n 2D binary grid grid which represents a map of '1' (land) and '0' (water), return number of islands.
     * <p>
     * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
     * You may assume all four edges of the grid are all surrounded by water.
     * <p>
     * tags:: bfs
     */
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    bfsNumIslands(grid, i, j);
                }
            }
        }

        return count;
    }

    private void bfsNumIslands(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != '1')
            return;

        grid[i][j] = '0';
        bfsNumIslands(grid, i - 1, j);
        bfsNumIslands(grid, i + 1, j);
        bfsNumIslands(grid, i, j - 1);
        bfsNumIslands(grid, i, j + 1);
    }

    /**
     * Q. 1319. Number of Operations to Make Network Connected
     * <p>
     * There are n computers numbered from 0 to n-1 connected by ethernet cables connections forming a network
     * where connections[i] = [a, b] represents a connection between computers a and b. Any computer can reach any other
     * computer directly or indirectly through the network.
     * <p>
     * Given an initial computer network connections. You can extract certain cables between two directly connected
     * computers, and place them between any pair of disconnected computers to make them directly connected.
     * Return the minimum number of times you need to do this in order to make all the computers connected.
     * If it's not possible, return -1.
     * <p>
     * tags:: union-find
     */
    public int makeConnected(int n, int[][] connections) {
        int[] parent = new int[n];
        int cycles = 0, unlinked = -1;

        for (int i = 0; i < n; i++)
            parent[i] = i;

        for (int[] conn : connections) {
            int xParent = find(parent, conn[0]);
            int yParent = find(parent, conn[1]);

            if (xParent == yParent)
                cycles++;
            else
                parent[xParent] = yParent;
        }

        for (int i = 0; i < n; i++) {
            if (parent[i] == i)
                unlinked++;
        }

        return (cycles >= unlinked) ? unlinked : -1;
    }

    /**
     * Q. 721 Accounts Merge
     * <p>
     * Given a list of accounts where each element accounts[i] is a list of strings, where the first element
     * accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.
     * Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some
     * common email to both accounts. Note that even if two accounts have the same name, they may belong to different
     * people as people could have the same name. A person can have any number of accounts initially, but all of their
     * accounts definitely have the same name.
     * <p>
     * After merging the accounts, return the accounts in the following format:
     * The first element of each account is the name, and the rest of the elements are emails in sorted order.
     * The accounts themselves can be returned in any order.
     * <p>
     * tags:: dfs, graph
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, String> emailToName = new HashMap<>();
        Set<String> seen = new HashSet<>();
        List<List<String>> answer = new ArrayList<>();

        for (List<String> account : accounts) {
            String email = account.get(0);
            for (int i = 1; i < account.size(); i++) {
                graph.computeIfAbsent(account.get(i), x -> new ArrayList<>()).add(account.get(1));
                graph.computeIfAbsent(account.get(1), x -> new ArrayList<>()).add(account.get(i));
                emailToName.put(account.get(i), email);
            }
        }

        for (String email : graph.keySet()) {
            if (!seen.contains(email)) {
                Stack<String> stack = new Stack<>();
                List<String> component = new ArrayList<>();
                seen.add(email);
                stack.push(email);
                while (!stack.isEmpty()) {
                    String node = stack.pop();
                    component.add(node);
                    for (String neighbour : graph.get(node)) {
                        if (!seen.contains(neighbour)) {
                            seen.add(neighbour);
                            stack.add(neighbour);
                        }
                    }
                }

                Collections.sort(component);
                component.add(0, emailToName.get(component.get(0)));
                answer.add(component);
            }
        }

        return answer;
    }

    /**
     * Q. 1293. Shortest Path in a Grid with Obstacles Elimination
     * <p>
     * Given a m * n grid, where each cell is either 0 (empty) or 1 (obstacle).
     * In one step, you can move up, down, left or right from and to an empty cell.
     * <p>
     * Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner (m-1, n-1)
     * given that you can eliminate at most k obstacles.
     * If it is not possible to find such walk return -1.
     * <p>
     * tags:: bfs
     */
    public int shortestPathWithObstacleElimination(int[][] grid, int k) {
        int rows = grid.length, cols = grid[0].length, steps = 0;
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        int[][] obstacles = new int[rows][cols];
        for (int i = 0; i < rows; i++)
            Arrays.fill(obstacles[i], Integer.MAX_VALUE);
        obstacles[0][0] = 0;

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0, 0});

        while (!q.isEmpty()) {
            for (int i = q.size(); i > 0; i--) {
                int[] poll = q.poll();

                if (poll[0] == rows - 1 && poll[1] == cols - 1)
                    return steps;

                for (int[] dir : dirs) {
                    int nextI = poll[0] + dir[0];
                    int nextJ = poll[1] + dir[1];

                    if (nextI < 0 || nextI >= rows || nextJ < 0 || nextJ >= cols)
                        continue;

                    int nextK = grid[nextI][nextJ] + poll[2];
                    if (nextK >= obstacles[nextI][nextJ] || nextK > k)
                        continue;

                    obstacles[nextI][nextJ] = nextK;
                    q.offer(new int[]{nextI, nextJ, nextK});
                }
            }
            steps++;
        }

        return -1;
    }

    /**
     * Q. 1730 Shortest Path to Get Food
     * <p>
     * You are starving and you want to eat food as quickly as possible. You want to find the shortest path to arrive
     * at any food cell. You are given an m x n character matrix, grid, of these different types of cells:
     * <p>
     * '*' is your location. There is exactly one '*' cell.
     * '#' is a food cell. There may be multiple food cells.
     * 'O' is free space, and you can travel through these cells.
     * 'X' is an obstacle, and you cannot travel through these cells.
     * <p>
     * You can travel to north, east, south, or west of your current location if there is not an obstacle.
     * Return the length of the shortest path for you to reach any food cell.
     * If there is no path for you to reach food, return -1.
     * <p>
     * tags:: bfs
     */
    public int shortestPathToGetFood(char[][] grid) {
        int rows = grid.length, cols = grid[0].length, steps = 0;
        int[][] dist = new int[rows][cols];
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                dist[i][j] = Integer.MAX_VALUE - 1;
                if (grid[i][j] == '*') {
                    dist[i][j] = 0;
                    q.offer(new int[]{i, j});
                }
            }
        }

        while (!q.isEmpty()) {
            for (int i = q.size(); i > 0; i--) {
                int[] poll = q.poll();

                if (grid[poll[0]][poll[1]] == '#')
                    return steps;

                for (int[] dir : dirs) {
                    int nextI = poll[0] + dir[0];
                    int nextJ = poll[1] + dir[1];

                    if (nextI < 0 || nextJ < 0 || nextI >= rows || nextJ >= cols ||
                            grid[nextI][nextJ] == 'X' || dist[nextI][nextJ] <= dist[poll[0]][poll[1]] + 1)
                        continue;

                    dist[nextI][nextJ] = dist[poll[0]][poll[1]] + 1;
                    q.offer(new int[]{nextI, nextJ});
                }
            }
            steps++;
        }

        return -1;
    }

    /**
     * Q. 1376 Time Needed to Inform All Employees
     * <p>
     * A company has n employees with a unique ID for each employee from 0 to n - 1. The head of the company is the one
     * with headID. Each employee has one direct manager given in the manager array where manager[i] is the direct
     * manager of the i-th employee, manager[headID] = -1. Also, it is guaranteed that the subordination relationships
     * have a tree structure. The head of the company wants to inform all the company employees of an urgent piece of
     * news. He will inform his direct subordinates, and they will inform their subordinates, and so on until all
     * employees know about the urgent news. The i-th employee needs informTime[i] minutes to inform all of his direct
     * subordinates (i.e., After informTime[i] minutes, all his direct subordinates can start spreading the news).
     * <p>
     * Return the number of minutes needed to inform all the employees about the urgent news.
     * <p>
     * tags:: dfs, nAryTree, n-ary tree
     */
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        Map<Integer, List<Integer>> tree = new HashMap<>();

        for (int i = 0; i < n; i++) {
            tree.computeIfAbsent(manager[i], x -> new ArrayList<>()).add((i));
        }
        return dfsNumOfMinutes(tree, informTime, headID);
    }

    private int dfsNumOfMinutes(Map<Integer, List<Integer>> tree, int[] informTime, int headId) {
        int currTime = 0;
        if (!tree.containsKey(headId))
            return currTime;

        for (int subordinate : tree.get(headId)) {
            currTime = Math.max(currTime, dfsNumOfMinutes(tree, informTime, subordinate) + informTime[headId]);
        }

        return currTime;
    }

    /**
     * Q. 399 Evaluate Division
     * <p>
     * You are given an array of variable pairs equations and an array of real numbers values,
     * where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i].
     * Each Ai or Bi is a string that represents a single variable.
     * <p>
     * You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query
     * where you must find the answer for Cj / Dj = ?.
     * <p>
     * Return the answers to all queries. If a single answer cannot be determined, return -1.0.
     * Note: The input is always valid.
     * You may assume that evaluating queries will not result in division by zero and that there is no contradiction.
     * <p>
     * tags:: dfs, graph
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = new HashMap<>();
        double[] answer = new double[queries.size()];

        for (int i = 0; i < equations.size(); i++) {
            String divisor = equations.get(i).get(1);
            String dividend = equations.get(i).get(0);
            double ratio = values[i];

            graph.computeIfAbsent(divisor, x -> new HashMap<>()).put(dividend, 1 / ratio);
            graph.computeIfAbsent(dividend, x -> new HashMap<>()).put(divisor, ratio);
        }

        for (int i = 0; i < queries.size(); i++) {
            answer[i] = dfsCalcEquation(graph, queries.get(i).get(0), queries.get(i).get(1),
                    1.0, new HashSet<>());
        }

        return answer;
    }

    private double dfsCalcEquation(Map<String, Map<String, Double>> graph, String curr, String target,
                                   double accumulatedProduct, HashSet<String> seen) {
        seen.add(curr);
        double product = -1.0;

        if (!graph.containsKey(curr) || !graph.containsKey(target))
            return product;
        else if (curr.equals(target))
            return 1.0;

        Map<String, Double> neighbours = graph.get(curr);
        if (neighbours.containsKey(target))
            return accumulatedProduct * neighbours.get(target);
        else {
            for (Map.Entry<String, Double> pair : neighbours.entrySet()) {
                if (seen.contains(pair.getKey()))
                    continue;

                product = dfsCalcEquation(graph, pair.getKey(), target,
                        pair.getValue() * accumulatedProduct, seen);

                if (product != -1.0)
                    break;
            }
        }
        seen.remove(curr);
        return product;
    }

    /**
     * Q. 947 Most Stones Removed with Same Row or Column
     * <p>
     * On a 2D plane, we place n stones at some integer coordinate points. Each coordinate point may have at most one
     * stone. A stone can be removed if it shares either the same row or the same column as another stone that has not
     * been removed.
     * <p>
     * Given an array stones of length n where stones[i] = [xi, yi] represents the location of the ith stone,
     * return the largest possible number of stones that can be removed.
     * <p>
     * constraints:
     * 1 <= stones.length <= 1000
     * 0 <= xi, yi <= 104
     * tags:: union-find, graph
     */
    public int removeStones(int[][] stones) {
        Map<Integer, Integer> graph = new HashMap<>();
        int[] islandCount = new int[1];

        for (int[] stone : stones) {
            unionRemoveStones(stone[0], stone[1] + 10001, graph, islandCount);
        }

        return stones.length - islandCount[0];
    }

    private void unionRemoveStones(int x, int y, Map<Integer, Integer> graph, int[] islandCount) {
        int xParent = findRemoveStones(x, graph, islandCount);
        int yParent = findRemoveStones(y, graph, islandCount);

        if (xParent != yParent) {
            graph.put(xParent, yParent);
            islandCount[0]--;
        }
    }

    private int findRemoveStones(int node, Map<Integer, Integer> graph, int[] islandCount) {
        if (graph.putIfAbsent(node, node) == null) {
            islandCount[0]++;
        }
        if (node != graph.get(node)) {
            graph.put(node, findRemoveStones(graph.get(node), graph, islandCount));
        }
        return graph.get(node);
    }

    /**
     * Q. 74 Search a 2D Matrix
     * <p>
     * Write an efficient algorithm that searches for a value in an m x n matrix.
     * This matrix has the following properties:
     * Integers in each row are sorted from left to right.
     * The first integer of each row is greater than the last integer of the previous row.
     * <p>
     * Q. 240 Search a 2D Matrix II
     * <p>
     * Write an efficient algorithm that searches for a target value in an m x n integer matrix.
     * The matrix has the following properties:
     * Integers in each row are sorted in ascending from left to right.
     * Integers in each column are sorted in ascending from top to bottom.
     * <p>
     * tags::binarySearch
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length, cols = matrix[0].length;
        int row = 0, col = cols - 1;

        while (row >= 0 && row < rows && col >= 0 && col < cols) {
            if (matrix[row][col] == target)
                return true;
            else if (matrix[row][col] > target)
                col--;
            else
                row++;
        }

        return false;
    }

    /**
     * Q. 419 Battleships in a Board
     * <p>
     * Given an m x n matrix board where each cell is a battleship 'X' or empty '.',
     * return the number of the battleships on board.
     * <p>
     * Battleships can only be placed horizontally or vertically on board. In other words, they can only be made of the
     * shape 1 x k (1 row, k columns) or k x 1 (k rows, 1 column), where k can be of any size.
     * At least one horizontal or vertical cell separates between two battleships (there are no adjacent battleships).
     * <p>
     * tags::dfs
     */
    public int countBattleships(char[][] board) {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'X') {
                    countBattleshipsDfs(board, i, j);
                    count++;
                }
            }
        }

        return count;
    }

    private void countBattleshipsDfs(char[][] board, int i, int j) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] == '.')
            return;

        board[i][j] = '.';
        countBattleshipsDfs(board, i + 1, j);
        countBattleshipsDfs(board, i - 1, j);
        countBattleshipsDfs(board, i, j + 1);
        countBattleshipsDfs(board, i, j - 1);
    }

    /**
     * Q. 54. Spiral Matrix
     * Given an m x n matrix, return all elements of the matrix in spiral order.
     * <p>
     * tags:: grid
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> answer = new ArrayList<>();
        int rows = matrix.length, cols = matrix[0].length;
        int[] rowRange = new int[]{0, rows - 1}, colRange = new int[]{0, cols - 1};

        while (answer.size() < rows * cols) {
            for (int i = colRange[0]; i <= colRange[1] && answer.size() < rows * cols; i++)
                answer.add(matrix[rowRange[0]][i]);
            rowRange[0]++;

            for (int i = rowRange[0]; i <= rowRange[1] && answer.size() < rows * cols; i++)
                answer.add(matrix[i][colRange[1]]);
            colRange[1]--;

            for (int i = colRange[1]; i >= colRange[0] && answer.size() < rows * cols; i--)
                answer.add(matrix[rowRange[1]][i]);
            rowRange[1]--;

            for (int i = rowRange[1]; i >= rowRange[0] && answer.size() < rows * cols; i--)
                answer.add(matrix[i][colRange[0]]);
            colRange[0]++;
        }

        return answer;
    }
}
