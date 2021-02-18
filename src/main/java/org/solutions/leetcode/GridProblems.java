package org.solutions.leetcode;

import org.solutions.leetcode.dataStructures.Pair;

import java.util.LinkedList;
import java.util.Queue;

public class GridProblems {
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
}
