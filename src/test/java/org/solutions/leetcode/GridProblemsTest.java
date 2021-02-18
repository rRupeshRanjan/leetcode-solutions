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
}