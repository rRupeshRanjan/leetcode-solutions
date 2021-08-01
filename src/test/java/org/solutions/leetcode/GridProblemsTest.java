package org.solutions.leetcode;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
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

    @Test
    void testAccountsMerge() {
        Map<List<List<String>>, List<List<String>>> scenarios = new HashMap<>();
        scenarios.put(
                Arrays.asList(
                        Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"),
                        Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"),
                        Arrays.asList("Mary", "mary@mail.com"),
                        Arrays.asList("John", "johnnybravo@mail.com")),
                Arrays.asList(
                        Arrays.asList("John", "johnnybravo@mail.com"),
                        Arrays.asList("Mary", "mary@mail.com"),
                        Arrays.asList("John", "john00@mail.com", "john_newyork@mail.com", "johnsmith@mail.com")));

        scenarios.put(
                Arrays.asList(
                        Arrays.asList("Gabe", "Gabe0@m.co", "Gabe3@m.co", "Gabe1@m.co"),
                        Arrays.asList("Kevin", "Kevin3@m.co", "Kevin5@m.co", "Kevin0@m.co"),
                        Arrays.asList("Ethan", "Ethan5@m.co", "Ethan4@m.co", "Ethan0@m.co"),
                        Arrays.asList("Hanzo", "Hanzo3@m.co", "Hanzo1@m.co", "Hanzo0@m.co"),
                        Arrays.asList("Fern", "Fern5@m.co", "Fern1@m.co", "Fern0@m.co")),

                Arrays.asList(
                        Arrays.asList("Fern", "Fern0@m.co", "Fern1@m.co", "Fern5@m.co"),
                        Arrays.asList("Hanzo", "Hanzo0@m.co", "Hanzo1@m.co", "Hanzo3@m.co"),
                        Arrays.asList("Kevin", "Kevin0@m.co", "Kevin3@m.co", "Kevin5@m.co"),
                        Arrays.asList("Gabe", "Gabe0@m.co", "Gabe1@m.co", "Gabe3@m.co"),
                        Arrays.asList("Ethan", "Ethan0@m.co", "Ethan4@m.co", "Ethan5@m.co")));

        scenarios.forEach((input, expected) -> assertTrue(expected.containsAll(gridProblems.accountsMerge(input))));
    }

    @Test
    void testShortestPathWithObstacleElimination() {
        Map<Pair<int[][], Integer>, Integer> scenarios = new HashMap<>();
        scenarios.put(Pair.of(new int[][]{{0, 0, 0}, {1, 1, 0}, {0, 0, 0}, {0, 1, 1}, {0, 0, 0}}, 1), 6);
        scenarios.put(Pair.of(new int[][]{{0, 1, 1}, {1, 1, 1}, {1, 0, 0}}, 1), -1);

        scenarios.forEach((input, expected) -> assertEquals(expected,
                gridProblems.shortestPathWithObstacleElimination(input.getLeft(), input.getRight())));
    }

    @Test
    void testShortestPathToGetFood() {
        Map<char[][], Integer> scenarios = new HashMap<>();
        scenarios.put(new char[][]{
                {'X', 'X', 'X', 'X', 'X', 'X'},
                {'X', '*', 'O', 'O', 'O', 'X'},
                {'X', 'O', 'O', '#', 'O', 'X'},
                {'X', 'X', 'X', 'X', 'X', 'X'}}, 3);
        scenarios.put(new char[][]{
                {'X', 'X', 'X', 'X', 'X'},
                {'X', '*', 'X', 'O', 'X'},
                {'X', 'O', 'X', '#', 'X'},
                {'X', 'X', 'X', 'X', 'X'}}, -1);
        scenarios.put(new char[][]{
                {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
                {'X', '*', 'O', 'X', 'O', '#', 'O', 'X'},
                {'X', 'O', 'O', 'X', 'O', 'O', 'X', 'X'},
                {'X', 'O', 'O', 'O', 'O', '#', 'O', 'X'},
                {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}}, 6);
        scenarios.put(new char[][]{{'O', '*'}, {'#', 'O'}}, 2);
        scenarios.put(new char[][]{{'X', '*'}, {'#', 'X'}}, -1);

        scenarios.forEach((input, expected) -> assertEquals(expected, gridProblems.shortestPathToGetFood(input)));
    }

    @Test
    void testCalcEquation() {
        Map<Triple<List<List<String>>, double[], List<List<String>>>, double[]> scenarios = new HashMap<>();
        scenarios.put(Triple.of(
                Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("b", "c")),
                new double[]{2.0, 3.0},
                Arrays.asList(Arrays.asList("a", "c"), Arrays.asList("b", "a"), Arrays.asList("a", "e"),
                        Arrays.asList("a", "a"), Arrays.asList("x", "x"))
        ), new double[]{6.00000, 0.50000, -1.00000, 1.00000, -1.00000});

        scenarios.put(Triple.of(
                Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("b", "c"), Arrays.asList("bc", "cd")),
                new double[]{1.5, 2.5, 5.0},
                Arrays.asList(Arrays.asList("a", "c"), Arrays.asList("c", "b"), Arrays.asList("bc", "cd"),
                        Arrays.asList("cd", "bc"))
        ), new double[]{3.75000, 0.40000, 5.00000, 0.20000});

        scenarios.forEach((input, expected) -> assertArrayEquals(expected,
                gridProblems.calcEquation(input.getLeft(), input.getMiddle(), input.getRight())));
    }

    @Test
    void testNumOfMinutes() {
        Map<Pair<Pair<Integer, Integer>, Pair<int[], int[]>>, Integer> scenarios = new HashMap<>();
        scenarios.put(Pair.of(Pair.of(1, 0), Pair.of(new int[]{-1}, new int[]{0})), 0);
        scenarios.put(Pair.of(Pair.of(6, 2),
                Pair.of(new int[]{2, 2, -1, 2, 2, 2}, new int[]{0, 0, 1, 0, 0, 0})), 1);
        scenarios.put(Pair.of(Pair.of(7, 6),
                Pair.of(new int[]{1, 2, 3, 4, 5, 6, -1}, new int[]{0, 6, 5, 4, 3, 2, 1})), 21);
        scenarios.put(Pair.of(Pair.of(15, 0), Pair.of(new int[]{-1, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6},
                new int[]{1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0})), 3);
        scenarios.put(Pair.of(Pair.of(4, 2), Pair.of(new int[]{3, 3, -1, 2}, new int[]{0, 0, 162, 914})), 1076);


        scenarios.forEach((input, expected) -> assertEquals(expected, gridProblems.numOfMinutes(
                input.getLeft().getLeft(), input.getLeft().getRight(),
                input.getRight().getLeft(), input.getRight().getRight())));
    }

    @Test
    void testRemoveStones() {
        Map<int[][], Integer> scenarios = new HashMap<>();
        scenarios.put(new int[][]{{0, 0}, {0, 2}, {1, 1}, {2, 0}, {2, 2}}, 3);
        scenarios.put(new int[][]{{0, 0}}, 0);

        scenarios.forEach((input, expected) -> assertEquals(expected, gridProblems.removeStones(input)));
    }

    private boolean listContainsArray(List<int[]> list, int[] array) {
        for (int[] l : list) {
            if (Arrays.equals(array, l))
                return true;
        }

        return false;
    }
}
