package org.solutions.leetcode;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GridProblemsTest {

    static GridProblems gridProblems;

    @BeforeEach
    void setUp() {
        gridProblems = new GridProblems();
    }

    @Test
    void testShortestPathBinaryMatrix() {
        Map<int[][], Integer> scenarios = new HashMap<>();
        scenarios.put(new int[][]{{1, 1}, {1, 1}}, -1);
        scenarios.put(new int[][]{{0, 0}, {0, 1}}, -1);
        scenarios.put(new int[][]{{0, 1}, {1, 0}}, 2);
        scenarios.put(new int[][]{{0, 0, 0}, {1, 1, 0}, {1, 1, 0}}, 4);
        scenarios.put(new int[][]{{0, 1, 0}, {0, 1, 0}, {0, 1, 0}}, -1);

        scenarios.forEach((input, expected) -> assertEquals(expected, gridProblems.shortestPathBinaryMatrix(input)));
    }

    @Test
    void testIsBipartite() {
        Map<int[][], Boolean> scenarios = new HashMap<>();
        scenarios.put(new int[][]{{1, 3}, {0, 2}, {1, 3}, {0, 2}}, true);
        scenarios.put(new int[][]{{1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}}, false);
        scenarios.put(new int[][]{{1}, {0, 3}, {3}, {1, 2}}, true);

        scenarios.forEach((input, expected) -> assertEquals(expected, gridProblems.isBipartite(input)));
    }

    @Test
    void testPossiblePartition() {
        Map<Pair<Integer, int[][]>, Boolean> scenarios = new HashMap<>();
        scenarios.put(Pair.of(4, new int[][]{{1, 2}, {1, 3}, {2, 4}}), true);
        scenarios.put(Pair.of(3, new int[][]{{1, 2}, {1, 3}, {2, 3}}), false);
        scenarios.put(Pair.of(5, new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}, {1, 5}}), false);

        scenarios.forEach((input, expected) ->
                assertEquals(expected, gridProblems.possibleBipartition(input.getLeft(), input.getRight())));
    }

    @Test
    void testKWeakestRows() {
        Map<Pair<int[][], Integer>, int[]> scenarios = new HashMap<>();
        scenarios.put(
                Pair.of(
                        new int[][]{{1, 1, 0, 0, 0}, {1, 1, 1, 1, 0}, {1, 0, 0, 0, 0}, {1, 1, 0, 0, 0}, {1, 1, 1, 1, 1}},
                        3
                ),
                new int[]{2, 0, 3});
        scenarios.put(
                Pair.of(
                        new int[][]{{1, 0, 0, 0}, {1, 1, 1, 1}, {1, 0, 0, 0}, {1, 0, 0, 0}},
                        2
                ),
                new int[]{0, 2});

        scenarios.forEach((input, expected) ->
                assertArrayEquals(expected, gridProblems.kWeakestRows(input.getLeft(), input.getRight())));

    }

    @Test
    void testCanVisitAllRooms() {
        Map<int[][], Boolean> scenarios = new HashMap<>();
        scenarios.put(new int[][]{{1}, {2}, {3}, {}}, true);
        scenarios.put(new int[][]{{1, 3}, {3, 0, 1}, {2}, {0}}, false);

        scenarios.forEach((input, expected) -> assertEquals(expected, gridProblems.canVisitAllRooms(input)));
    }

    @Test
    void testPacificAtlantic() {
        gridProblems.pacificAtlantic(new int[][]{{1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1}, {6, 7, 1, 4, 5}, {5, 1, 1, 2, 4}});
        // should output [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]

        gridProblems.pacificAtlantic(new int[][]{{2, 1}, {1, 2}});
        // should output [[0,0],[0,1],[1,0],[1,1]]
    }

    @Test
    void testRotate() {
        Map<int[][], int[][]> scenarios = new HashMap<>();
        scenarios.put(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, new int[][]{{7, 4, 1}, {8, 5, 2}, {9, 6, 3}});
        scenarios.put(new int[][]{{1, 2}, {3, 4}}, new int[][]{{3, 1}, {4, 2}});

        scenarios.forEach((input, expected) -> {
            gridProblems.rotate(input);
            assertArrayEquals(expected, input);
        });
    }

    @Test
    void testCourseScheduleI() {
        Map<Pair<Integer, int[][]>, Boolean> scenarios = new HashMap<>();
        scenarios.put(Pair.of(2, new int[][]{{1, 0}}), true);
        scenarios.put(Pair.of(2, new int[][]{{1, 0}, {0, 1}}), false);
        scenarios.put(Pair.of(3, new int[][]{{1, 0}, {0, 1}}), false);

        scenarios.forEach((input, expected) ->
                assertEquals(expected, gridProblems.courseScheduleI(input.getLeft(), input.getRight())));
    }

    @Test
    void testCourseScheduleII() {
        Map<Pair<Integer, int[][]>, List<int[]>> scenarios = new HashMap<>();
        scenarios.put(Pair.of(2, new int[][]{{1, 0}}), Collections.singletonList(new int[]{0, 1}));
        scenarios.put(Pair.of(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}}),
                Arrays.asList(new int[]{0, 1, 2, 3}, new int[]{0, 2, 1, 3}));

        scenarios.forEach((input, expected) ->
                assertTrue(listContainsArray(expected, gridProblems.courseScheduleII(input.getLeft(), input.getRight()))));
    }

    @Test
    void tesCourseScheduleIII() {
        Map<int[][], Integer> scenarios = new HashMap<>();
        scenarios.put(new int[][]{{100, 200}, {200, 1300}, {1000, 1250}, {2000, 3200}}, 3);
        scenarios.put(new int[][]{{1, 2}}, 1);
        scenarios.put(new int[][]{{3, 2}, {4, 3}}, 0);

        scenarios.forEach((input, expected) -> assertEquals(expected, gridProblems.courseScheduleIII(input)));
    }

    @Test
    void testSolveNQueens() {
        Map<Integer, List<List<String>>> scenarios = new HashMap<>();
        scenarios.put(1, Collections.singletonList(Collections.singletonList("Q")));
        scenarios.put(2, Collections.emptyList());
        scenarios.put(3, Collections.emptyList());
        scenarios.put(4, Arrays.asList(
                Arrays.asList(".Q..", "...Q", "Q...", "..Q."),
                Arrays.asList("..Q.", "Q...", "...Q", ".Q..")));

        scenarios.forEach((input, expected) -> assertEquals(expected, gridProblems.solveNQueens(input)));
    }

    @Test
    void testTotalNQueens() {
        Map<Integer, Integer> scenarios = new HashMap<>();
        scenarios.put(1, 1);
        scenarios.put(2, 0);
        scenarios.put(3, 0);
        scenarios.put(4, 2);
        scenarios.put(5, 10);
        scenarios.put(6, 4);
        scenarios.put(7, 40);
        scenarios.put(8, 92);
        scenarios.put(9, 352);

        scenarios.forEach((input, expected) -> assertEquals(expected, gridProblems.totalNQueens(input)));
    }

    @Test
    void testAllPathsSourceTarget() {
        Map<int[][], List<List<Integer>>> scenarios = new HashMap<>();
        scenarios.put(new int[][]{{1}, {}}, Collections.singletonList(Arrays.asList(0, 1)));
        scenarios.put(new int[][]{{1, 2}, {3}, {3}, {}}, Arrays.asList(Arrays.asList(0, 1, 3), Arrays.asList(0, 2, 3)));
        scenarios.put(new int[][]{{1, 3}, {2}, {3}, {}}, Arrays.asList(Arrays.asList(0, 1, 2, 3), Arrays.asList(0, 3)));

        scenarios.put(new int[][]{{4, 3, 1}, {3, 2, 4}, {3}, {4}, {}}, Arrays.asList(
                Arrays.asList(0, 4),
                Arrays.asList(0, 3, 4),
                Arrays.asList(0, 1, 3, 4),
                Arrays.asList(0, 1, 2, 3, 4),
                Arrays.asList(0, 1, 4)));

        scenarios.put(new int[][]{{1, 2, 3}, {2}, {3}, {}}, Arrays.asList(
                Arrays.asList(0, 1, 2, 3),
                Arrays.asList(0, 2, 3),
                Arrays.asList(0, 3)));

        scenarios.forEach((input, expected) -> assertEquals(expected, gridProblems.allPathsSourceTarget(input)));
    }

    @Test
    void testMaxDistance() {
        Map<int[][], Integer> scenarios = new HashMap<>();
        scenarios.put(new int[][]{{1, 0, 1}, {0, 0, 0}, {1, 0, 1}}, 2);
        scenarios.put(new int[][]{{1, 0, 0}, {0, 0, 0}, {0, 0, 0}}, 4);
        scenarios.put(new int[][]{{1, 1}, {1, 1}}, -1);
        scenarios.put(new int[][]{{0, 0}, {0, 0}}, -1);

        scenarios.forEach((input, expected) -> assertEquals(expected, gridProblems.maxDistance(input)));
    }

    @Test
    void testUpdate01Matrix() {
        Map<int[][], int[][]> scenarios = new HashMap<>();
        scenarios.put(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}}, new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}});
        scenarios.put(new int[][]{{0, 0, 0}, {0, 1, 0}, {1, 1, 1}}, new int[][]{{0, 0, 0}, {0, 1, 0}, {1, 2, 1}});

        scenarios.forEach((input, expected) -> assertArrayEquals(expected, gridProblems.update01Matrix(input)));
    }

    @Test
    void testCoverSurroundedRegions() {
        Map<char[][], char[][]> scenarios = new HashMap<>();
        scenarios.put(
                new char[][]{{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}},
                new char[][]{{'X', 'X', 'X', 'X'}, {'X', 'X', 'X', 'X'}, {'X', 'X', 'X', 'X'}, {'X', 'O', 'X', 'X'}});
        scenarios.put(new char[][]{{'X', 'X'}}, new char[][]{{'X', 'X'}});
        scenarios.put(new char[][]{{'O', 'O'}}, new char[][]{{'O', 'O'}});

        scenarios.forEach((input, expected) -> {
            gridProblems.coverSurroundedRegions(input);
            assertArrayEquals(expected, input);
        });
    }

    @Test
    void testFindRedundantConnection() {
        Map<int[][], int[]> scenarios = new HashMap<>();
        scenarios.put(new int[][]{{1, 2}, {1, 3}, {2, 3}}, new int[]{2, 3});
        scenarios.put(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}}, new int[]{1, 4});
        scenarios.put(new int[][]{}, new int[]{});

        scenarios.forEach((input, expected) -> assertArrayEquals(expected, gridProblems.findRedundantConnection(input)));
    }

    @Test
    void testMaxAreaOfIsland() {
        Map<int[][], Integer> scenarios = new HashMap<>();
        scenarios.put(new int[][]{
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}}, 6);
        scenarios.put(new int[][]{{0, 0, 0, 0, 0, 0, 0, 0}}, 0);
        scenarios.put(new int[][]{{0, 1, 1, 1, 1, 1, 1, 0}}, 6);
        scenarios.put(new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}}, 8);

        scenarios.forEach((input, expected) -> assertEquals(expected, gridProblems.maxAreaOfIsland(input)));
    }

    @Test
    void testNumIslands() {
        Map<char[][], Integer> scenarios = new HashMap<>();
        scenarios.put(new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}}, 1);
        scenarios.put(new char[][]{
                {'1', '0', '1', '1', '0'},
                {'1', '0', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '1'}}, 3);
        scenarios.put(new char[][]{{'1'}}, 1);
        scenarios.put(new char[][]{{'0'}}, 0);

        scenarios.forEach((input, expected) -> assertEquals(expected, gridProblems.numIslands(input)));
    }

    @Test
    void testMakeConnected() {
        Map<Pair<Integer, int[][]>, Integer> scenarios = new HashMap<>();
        scenarios.put(Pair.of(4, new int[][]{{0, 1}, {0, 2}, {1, 2}}), 1);
        scenarios.put(Pair.of(6, new int[][]{{0, 1}, {0, 2}, {0, 3}, {1, 2}, {1, 3}}), 2);
        scenarios.put(Pair.of(6, new int[][]{{0, 1}, {0, 2}, {0, 3}, {1, 2}}), -1);
        scenarios.put(Pair.of(5, new int[][]{{0, 1}, {0, 2}, {3, 4}, {2, 3}}), 0);

        scenarios.forEach((input, expected) ->
                assertEquals(expected, gridProblems.makeConnected(input.getLeft(), input.getRight())));
    }

    private boolean listContainsArray(List<int[]> list, int[] array) {
        for (int[] l : list) {
            if (Arrays.equals(array, l))
                return true;
        }

        return false;
    }
}
