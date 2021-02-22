package org.solutions.leetcode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridProblemsTest {

    static GridProblems gridProblems;

    @BeforeEach
    void setUp() {
        gridProblems = new GridProblems();
    }

    @Test
    void testShortestPathBinaryMatrix() {
        assertEquals(2, gridProblems.shortestPathBinaryMatrix(new int[][]{{0,1},{1,0}}));
        assertEquals(4, gridProblems.shortestPathBinaryMatrix(new int[][]{{0,0,0},{1,1,0},{1,1,0}}));
    }

    @Test
    void testIsBipartite() {
        assertTrue(gridProblems.isBipartite(new int[][]{{1,3},{0,2},{1,3},{0,2}}));
        assertFalse(gridProblems.isBipartite(new int[][]{{1,2,3},{0,2},{0,1,3},{0,2}}));
        assertTrue(gridProblems.isBipartite(new int[][]{{1},{0,3},{3},{1,2}}));
    }

    @Test
    void testPossiblePartition() {
        assertTrue(gridProblems.possibleBipartition(4, new int[][]{{1,2},{1,3},{2,4}}));
        assertFalse(gridProblems.possibleBipartition(3, new int[][]{{1,2},{1,3},{2,3}}));
        assertFalse(gridProblems.possibleBipartition(5, new int[][]{{1,2},{2,3},{3,4},{4,5},{1,5}}));
    }

    @Test
    void testKWeakestRows() {
        assertArrayEquals(
                new int[]{2,0,3},
                gridProblems.kWeakestRows(
                        new int[][]{{1,1,0,0,0},{1,1,1,1,0},{1,0,0,0,0},{1,1,0,0,0},{1,1,1,1,1}},
                        3
                )
        );

        assertArrayEquals(
                new int[]{0,2},
                gridProblems.kWeakestRows(
                        new int[][]{{1,0,0,0},{1,1,1,1},{1,0,0,0},{1,0,0,0}},
                        2
                )
        );

    }
}