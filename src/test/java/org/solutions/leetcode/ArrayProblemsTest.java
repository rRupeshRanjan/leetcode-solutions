package org.solutions.leetcode;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ArrayProblemsTest {

    static ArrayProblems arrayProblems;

    @BeforeAll
    static void setup() {
        arrayProblems = new ArrayProblems();
    }

    @Test
    void findLHS() {
        Map<int[], Integer> scenarios = new HashMap<>();

        scenarios.put(new int[]{-3, -1, -1, -1, -3, -2}, 4);
        scenarios.put(new int[]{1, 2, 2, 3, 4, 5, 1, 1, 1, 1}, 7);
        scenarios.put(new int[]{2, 2, 2, 2, 2, 2, 2, 3, 1, 0, 0, 0, 3, 1, -1, 0, 1, 1, 0, 0, 1, 1, 2, 2,
                2, 0, 1, 2, 2, 3, 2}, 20);
        scenarios.put(new int[]{1, 3, 2, 2, 5, 2, 3, 7}, 5);
        scenarios.put(new int[]{1, 2, 3, 4}, 2);
        scenarios.put(new int[]{1, 1, 1, 1}, 0);

        List<String> ways = List.of("brute-force", "sorting", "hashmap");

        scenarios.forEach((input, length) -> {
            ways.forEach(way -> assertEquals(length, arrayProblems.findLHS(input, way)));
            assertEquals(-1, arrayProblems.findLHS(input, "garbage"));
        });
    }

    @Test
    void testCanFormArray() {
        Map<Pair<int[], int[][]>, Boolean> scenarios = new HashMap<>();

        scenarios.put(Pair.of(new int[]{85}, new int[][]{{85}}), true);
        scenarios.put(Pair.of(new int[]{15, 88}, new int[][]{{88}, {15}}), true);
        scenarios.put(Pair.of(new int[]{49, 18, 16}, new int[][]{{16, 18, 49}}), false);
        scenarios.put(Pair.of(new int[]{49, 18, 16}, new int[][]{{49, 18, 17}}), false);
        scenarios.put(Pair.of(new int[]{91, 4, 64, 78}, new int[][]{{78}, {4, 64}, {91}}), true);
        scenarios.put(Pair.of(new int[]{1, 3, 5, 7}, new int[][]{{2, 4, 6, 8}}), false);

        scenarios.forEach((input, expected) ->
                assertEquals(expected, arrayProblems.canFormArray(input.getLeft(), input.getRight())));
    }

    @Test
    void testMaxWaterContainerArea() {
        Map<int[], Integer> scenarios = new HashMap<>();

        scenarios.put(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}, 49);
        scenarios.put(new int[]{1, 1}, 1);
        scenarios.put(new int[]{4, 3, 2, 1, 4}, 16);
        scenarios.put(new int[]{1, 2, 1}, 2);

        scenarios.forEach((input, expected) -> assertEquals(expected, arrayProblems.maxWaterContainerArea(input)));
    }

    @Test
    void testNumberOfArithmeticSlices() {
        Map<int[], Integer> scenarios = new HashMap<>();

        scenarios.put(new int[]{1, 2, 3, 8, 9, 10}, 2);
        scenarios.put(new int[]{1, 2, 3, 4}, 3);
        scenarios.put(new int[]{1, 2, 3, 8, 9, 10}, 2);

        scenarios.forEach((input, expected) -> assertEquals(expected, arrayProblems.numberOfArithmeticSlices(input)));
    }

    @Test
    void testMaxProfit2() {
        Map<int[], Integer> scenarios = new HashMap<>();

        scenarios.put(new int[]{7, 1, 5, 3, 6, 4}, 7);
        scenarios.put(new int[]{1, 2, 3, 4, 5}, 4);
        scenarios.put(new int[]{5, 4, 3, 2, 1}, 0);
        scenarios.put(new int[]{5}, 0);
        scenarios.put(null, 0);

        scenarios.forEach((input, expected) -> assertEquals(expected, arrayProblems.maxProfit2(input)));
    }

    @Test
    void testMaxProfit5() {
        Map<Pair<Integer, int[]>, Integer> scenarios = new HashMap<>();

        scenarios.put(Pair.of(2, new int[]{1, 3, 2, 8, 4, 9}), 8);
        scenarios.put(Pair.of(3, new int[]{1, 3, 7, 5, 10, 3}), 6);

        scenarios.forEach((input, expected) ->
                assertEquals(expected, arrayProblems.maxProfit5(input.getLeft(), input.getRight())));
    }

    @Test
    void testThreeSumMulti() {
        Map<Pair<int[], Integer>, Integer> scenarios = new HashMap<>();
        scenarios.put(Pair.of(new int[]{1, 1, 2, 2, 3, 3, 4, 4, 5, 5}, 8), 20);
        scenarios.put(Pair.of(new int[]{1, 1, 2, 2, 2, 2}, 5), 12);
        scenarios.put(Pair.of(new int[]{1, 0, 1, 0, 2, 1, 2}, 1), 3);

        scenarios.forEach((input, expected) ->
                assertEquals(expected, arrayProblems.threeSumMulti(input.getLeft(), input.getRight())));
    }

    @Test
    void Count() {
        Map<Pair<int[], int[]>, int[]> scenarios = new HashMap<>();

        scenarios.put(Pair.of(new int[]{2, 7, 11, 15}, new int[]{1, 10, 4, 11}), new int[]{2, 11, 7, 15});
        scenarios.put(Pair.of(new int[]{12, 24, 8, 32}, new int[]{13, 25, 32, 11}), new int[]{24, 32, 8, 12});
        scenarios.put(Pair.of(new int[]{3, 3, 4, 4}, new int[]{2, 2, 3, 3}), new int[]{3, 3, 4, 4});

        scenarios.forEach((input, expected) ->
                assertArrayEquals(expected, arrayProblems.advantageCount(input.getLeft(), input.getRight())));
    }

    @Test
    void testIsIdealPermutation() {
        Map<int[], Boolean> scenarios = new HashMap<>();

        scenarios.put(new int[]{1, 0, 2}, true);
        scenarios.put(new int[]{1, 2, 0}, false);

        scenarios.forEach((input, expected) -> assertEquals(expected, arrayProblems.isIdealPermutation(input)));
    }

    @Test
    void testLargestRectangleArea() {
        Map<int[], Integer> scenarios = new HashMap<>();

        scenarios.put(new int[]{2, 1, 5, 6, 2, 3}, 10);

        scenarios.forEach((input, expected) -> assertEquals(expected, arrayProblems.largestRectangleArea(input)));
    }

    @Test
    void testFurthestBuilding() {
        Map<Triple<int[], Integer, Integer>, Integer> scenarios = new HashMap<>();

        scenarios.put(Triple.of(new int[]{4, 2, 7, 6, 9, 14, 12}, 5, 1), 4);
        scenarios.put(Triple.of(new int[]{4, 12, 2, 7, 3, 18, 20, 3, 19}, 10, 2), 7);
        scenarios.put(Triple.of(new int[]{14, 3, 19, 3}, 17, 0), 3);

        scenarios.forEach((input, expected) ->
                assertEquals(expected,
                        arrayProblems.furthestBuilding(input.getLeft(), input.getMiddle(), input.getRight())));
    }

    @Test
    void testSearchRange() {
        Map<Pair<int[], Integer>, int[]> scenarios = new HashMap<>();

        scenarios.put(Pair.of(new int[]{5, 7, 7, 8, 8, 10}, 8), new int[]{3, 4});
        scenarios.put(Pair.of(new int[]{5, 7, 7, 8, 8, 10}, 6), new int[]{-1, -1});
        scenarios.put(Pair.of(new int[]{}, 0), new int[]{-1, -1});

        scenarios.forEach((input, expected) ->
                assertArrayEquals(expected, arrayProblems.searchRange(input.getLeft(), input.getRight())));
    }

    @Test
    void testRunningSum() {
        Map<int[], int[]> scenarios = new HashMap<>();

        scenarios.put(new int[]{1, 1, 1, 1, 1}, new int[]{1, 2, 3, 4, 5});
        scenarios.put(new int[]{1, 2, 3, 4, 5}, new int[]{1, 3, 6, 10, 15});

        scenarios.forEach((input, expected) -> assertArrayEquals(expected, arrayProblems.runningSum(input)));
    }

    @Test
    void testCheckPossibility() {
        Map<int[], Boolean> scenarios = new HashMap<>();

        scenarios.put(new int[]{3, 4, 2, 3}, false);
        scenarios.put(new int[]{4, 2, 3}, true);
        scenarios.put(new int[]{-1, 4, 2, 3}, true);

        scenarios.forEach((input, expected) -> assertEquals(expected, arrayProblems.checkPossibility(input)));
    }

    @Test
    void testJumpGame() {
        Map<int[], Boolean> scenarios = new HashMap<>();

        scenarios.put(new int[]{2, 3, 1, 1, 4}, true);
        scenarios.put(new int[]{2, 1, 1, 0, 4}, false);
        scenarios.put(new int[]{}, true);
        scenarios.put(null, true);

        scenarios.forEach((input, expected) -> assertEquals(expected, arrayProblems.jumpGame(input)));
    }

    @Test
    void testJumpGameII() {
        Map<int[], Integer> scenarios = new HashMap<>();

        scenarios.put(new int[]{2, 3, 1, 1, 4}, 2);
        scenarios.put(new int[]{2, 3, 0, 1, 4}, 2);
        scenarios.put(new int[]{}, 0);
        scenarios.put(null, 0);

        scenarios.forEach((input, expected) -> assertEquals(expected, arrayProblems.jumpGameII(input)));
    }

    @Test
    void testJumpGameIII() {
        Map<Pair<int[], Integer>, Boolean> scenarios = new HashMap<>();

        scenarios.put(Pair.of(new int[]{4, 2, 3, 0, 3, 1, 2}, 5), true);
        scenarios.put(Pair.of(new int[]{4, 2, 3, 0, 3, 1, 2}, 0), true);
        scenarios.put(Pair.of(new int[]{3, 0, 2, 1, 2}, 2), false);

        scenarios.forEach((input, expected) -> assertEquals(expected,
                arrayProblems.jumpGameIII(input.getLeft(), input.getRight())));
    }

    @Test
    void testJumpGameIV() {
        Map<int[], Integer> scenarios = new HashMap<>();

        scenarios.put(new int[]{100, -23, -23, 404, 100, 23, 23, 23, 3, 404}, 3);
        scenarios.put(new int[]{7, 6, 9, 6, 9, 6, 9, 7}, 1);
        scenarios.put(new int[]{7}, 0);

        scenarios.forEach((input, expected) -> assertEquals(expected, arrayProblems.jumpGameIV(input)));
    }

    @Test
    void testIsPossibleToConstructTargetArray() {
        Map<int[], Boolean> scenarios = new HashMap<>();

        scenarios.put(new int[]{1, 1, 1, 2}, false);
        scenarios.put(new int[]{3, 5, 9}, true);

        scenarios.forEach((input, expected) -> assertEquals(expected, arrayProblems.isPossibleToConstructTargetArray(input)));
    }

    @Test
    void testMaxScore() {
        Map<Pair<int[], Integer>, Integer> scenarios = new HashMap<>();

        scenarios.put(Pair.of(new int[]{1, 2, 3, 4, 5, 6, 1}, 3), 12);
        scenarios.put(Pair.of(new int[]{9, 7, 7, 9, 7, 7, 9}, 7), 55);
        scenarios.put(Pair.of(new int[]{1, 79, 80, 1, 1, 1, 200, 1}, 3), 202);

        scenarios.forEach((input, expected) ->
                assertEquals(expected, arrayProblems.maxScore(input.getLeft(), input.getRight())));
    }

    @Test
    void testMinMoves() {
        Map<int[], Integer> scenarios = new HashMap<>();

        scenarios.put(new int[]{1, 2, 3}, 3);
        scenarios.put(new int[]{1, 1, 1, 9}, 8);
        scenarios.put(new int[]{1, 1, 1}, 0);

        scenarios.forEach((input, expected) -> assertEquals(expected, arrayProblems.minMoves(input)));
    }

    @Test
    void testMinMoves2() {
        Map<int[], Integer> scenarios = new HashMap<>();

        scenarios.put(new int[]{1, 2, 3}, 2);
        scenarios.put(new int[]{1, 10, 2, 9}, 16);

        scenarios.forEach((input, expected) -> assertEquals(expected, arrayProblems.minMoves2(input)));
    }

    @Test
    void testSubsetXORSum() {
        Map<int[], Integer> scenarios = new HashMap<>();

        scenarios.put(new int[]{1, 3}, 6);
        scenarios.put(new int[]{1, 5, 6}, 28);

        scenarios.forEach((input, expected) -> assertEquals(expected, arrayProblems.subsetXORSum(input)));
    }

    @Test
    void testMaximumUniqueSubarray() {
        Map<int[], Integer> scenarios = new HashMap<>();

        scenarios.put(new int[]{5, 2, 1, 2, 5, 2, 1, 2, 5}, 8);
        scenarios.put(new int[]{4, 2, 4, 5, 6}, 17);
        scenarios.put(new int[]{1, 1, 1}, 1);
        scenarios.put(new int[]{1, 1, 1, 2, 2, 2}, 3);
        scenarios.put(new int[]{1, 2, 3}, 6);
        scenarios.put(new int[]{1, 2, 2}, 3);

        scenarios.forEach((input, expected) -> assertEquals(expected, arrayProblems.maximumUniqueSubarray(input)));
    }

    @Test
    void testMaxProduct() {
        Map<String[], Integer> scenarios = new HashMap<>();

        scenarios.put(new String[]{"abcw", "baz", "foo", "bar", "xtfn", "abcdef"}, 16);
        scenarios.put(new String[]{"a", "ab", "abc", "d", "cd", "bcd", "abcd"}, 4);
        scenarios.put(new String[]{"a", "aa", "aaa", "aaaa"}, 0);

        scenarios.forEach((input, expected) -> assertEquals(expected, arrayProblems.maxProduct(input)));
    }

    @Test
    void testMaxArea() {
        Map<Pair<Pair<Integer, Integer>, Pair<int[], int[]>>, Integer> scenarios = new HashMap<>();

        scenarios.put(Pair.of(Pair.of(5, 4), Pair.of(new int[]{3, 1}, new int[]{1})), 6);
        scenarios.put(Pair.of(Pair.of(5, 4), Pair.of(new int[]{3}, new int[]{3})), 9);
        scenarios.put(Pair.of(Pair.of(5, 4), Pair.of(new int[]{1, 2, 4}, new int[]{1, 3})), 4);

        scenarios.forEach((input, expected) -> assertEquals(expected,
                arrayProblems.maxArea(input.getLeft().getLeft(), input.getLeft().getRight(),
                        input.getRight().getLeft(), input.getRight().getRight())));
    }

    @Test
    void testMaxDistToClosest() {
        Map<int[], Integer> scenarios = new HashMap<>();

        scenarios.put(new int[]{1, 0, 0, 0, 1, 0, 1}, 2);
        scenarios.put(new int[]{1, 0, 0, 0}, 3);
        scenarios.put(new int[]{0, 1}, 1);

        scenarios.forEach((input, expected) -> assertEquals(expected, arrayProblems.maxDistToClosest(input)));
    }

    @Test
    void testMaxSumRangeQuery() {
        Map<Pair<int[], int[][]>, Integer> scenarios = new HashMap<>();

        scenarios.put(Pair.of(new int[]{1, 2, 3, 4, 5}, new int[][]{{1, 3}, {0, 1}}), 19);
        scenarios.put(Pair.of(new int[]{1, 2, 3, 4, 5, 6}, new int[][]{{0, 1}}), 11);
        scenarios.put(Pair.of(new int[]{1, 2, 3, 4, 5, 10}, new int[][]{{0, 2}, {1, 3}, {1, 1}}), 47);
        scenarios.put(Pair.of(new int[]{1, 4, 5}, new int[][]{{0, 2}, {0, 1}, {1, 2}}), 25);

        scenarios.forEach((input, expected) -> assertEquals(expected,
                arrayProblems.maxSumRangeQuery(input.getLeft(), input.getRight())));
    }

    @Test
    void testMaxPerformance() {
        Map<Pair<Pair<Integer, Integer>, Pair<int[], int[]>>, Integer> scenarios = new HashMap<>();

        scenarios.put(Pair.of(Pair.of(6, 2), Pair.of(new int[]{2, 10, 3, 1, 5, 8}, new int[]{5, 4, 3, 9, 7, 2})), 60);
        scenarios.put(Pair.of(Pair.of(6, 3), Pair.of(new int[]{2, 10, 3, 1, 5, 8}, new int[]{5, 4, 3, 9, 7, 2})), 68);
        scenarios.put(Pair.of(Pair.of(6, 4), Pair.of(new int[]{2, 10, 3, 1, 5, 8}, new int[]{5, 4, 3, 9, 7, 2})), 72);

        scenarios.forEach((input, expected) -> assertEquals(expected, arrayProblems.maxPerformance(
                input.getLeft().getLeft(), input.getLeft().getRight(),
                input.getRight().getLeft(), input.getRight().getRight()
        )));
    }

    @Test
    void testCarPooling() {
        Map<Pair<int[][], Integer>, Boolean> scenarios = new HashMap<>();

        scenarios.put(Pair.of(new int[][]{{2, 1, 5}, {3, 3, 7}}, 4), false);
        scenarios.put(Pair.of(new int[][]{{2, 1, 5}, {3, 3, 7}}, 5), true);
        scenarios.put(Pair.of(new int[][]{{2, 1, 5}, {3, 5, 7}}, 3), true);
        scenarios.put(Pair.of(new int[][]{{3, 2, 7}, {3, 7, 9}, {8, 3, 9}}, 11), true);

        scenarios.forEach((input, expected) -> assertEquals(expected,
                arrayProblems.carPooling(input.getLeft(), input.getRight())));
    }

    @Test
    void testLongestConsecutive() {
        Map<int[], Integer> scenarios = new HashMap<>();

        scenarios.put(new int[]{100, 4, 200, 1, 3, 2}, 4);
        scenarios.put(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}, 9);

        scenarios.forEach((input, expected) -> assertEquals(expected, arrayProblems.longestConsecutive(input)));
    }

    @Test
    void testRotatedBinarySearch() {
        Map<Pair<int[], Integer>, Integer> scenarios = new HashMap<>();

        scenarios.put(Pair.of(new int[]{4, 5, 6, 7, 0, 1, 2}, 0), 4);
        scenarios.put(Pair.of(new int[]{4, 5, 6, 7, 0, 1, 2}, 1), 5);
        scenarios.put(Pair.of(new int[]{4, 5, 6, 7, 0, 1, 2}, 2), 6);
        scenarios.put(Pair.of(new int[]{4, 5, 6, 7, 0, 1, 2}, 3), -1);
        scenarios.put(Pair.of(new int[]{4, 5, 6, 7, 0, 1, 2}, 4), 0);
        scenarios.put(Pair.of(new int[]{4, 5, 6, 7, 0, 1, 2}, 5), 1);
        scenarios.put(Pair.of(new int[]{4, 5, 6, 7, 0, 1, 2}, 6), 2);
        scenarios.put(Pair.of(new int[]{4, 5, 6, 7, 0, 1, 2}, 7), 3);

        scenarios.forEach((input, expected) -> assertEquals(expected,
                arrayProblems.rotatedBinarySearch(input.getLeft(), input.getRight())));
    }

    @Test
    void testCanCompleteCircuit() {
        Map<Pair<int[], int[]>, Integer> scenarios = new HashMap<>();

        scenarios.put(Pair.of(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2}), 3);
        scenarios.put(Pair.of(new int[]{2, 3, 4}, new int[]{3, 4, 5}), -1);
        scenarios.put(Pair.of(new int[]{2, 3, 4}, new int[]{3, 4, 3}), -1);
        scenarios.put(Pair.of(new int[]{3, 3, 4}, new int[]{3, 4, 3}), 2);

        scenarios.forEach((input, expected) -> assertEquals(expected,
                arrayProblems.canCompleteCircuit(input.getLeft(), input.getRight())));
    }

    @Test
    void testFind132pattern() {
        Map<int[], Boolean> scenarios = new HashMap<>();

        scenarios.put(new int[]{1, 2, 3, 4}, false);
        scenarios.put(new int[]{3, 1, 4, 2}, true);
        scenarios.put(new int[]{-1, 3, 2, 0}, true);
        scenarios.put(new int[]{-1, 3}, false);

        scenarios.forEach((input, expected) -> assertEquals(expected, arrayProblems.find132pattern(input)));
    }

    @Test
    void testMakeSquare() {
        Map<int[], Boolean> scenarios = new HashMap<>();

        scenarios.put(new int[]{1, 1, 2, 2, 2}, true);
        scenarios.put(new int[]{3, 3, 3, 3, 4}, false);
        scenarios.put(new int[]{3, 3, 3}, false);
        scenarios.put(new int[]{3, 3, 3, 3, 3}, false);
        scenarios.put(null, false);

        scenarios.forEach((input, expected) -> assertEquals(expected, arrayProblems.makeSquare(input)));
    }

    @Test
    void testCanPartitionKSubsets() {
        Map<Pair<int[], Integer>, Boolean> scenarios = new HashMap<>();

        scenarios.put(Pair.of(new int[]{4, 3, 2, 3, 5, 2, 1}, 4), true);
        scenarios.put(Pair.of(new int[]{1, 2, 3, 4}, 3), false);
        scenarios.put(Pair.of(new int[]{1, 2}, 3), false);
        scenarios.put(Pair.of(null, 3), false);

        scenarios.forEach((input, expected) ->
                assertEquals(expected, arrayProblems.canPartitionKSubsets(input.getLeft(), input.getRight())));
    }

    @Test
    void testNumSubarrayBoundedMax() {
        Map<Triple<int[], Integer, Integer>, Integer> scenarios = new HashMap<>();

        scenarios.put(Triple.of(new int[]{2, 1, 4, 3}, 2, 3), 3);
        scenarios.put(Triple.of(new int[]{73, 55, 36, 5, 55, 14, 9, 7, 72, 52}, 32, 69), 22);
        scenarios.put(Triple.of(new int[]{73, 55, 36, 5, 55, 14, 9}, 32, 69), 17);
        scenarios.put(Triple.of(new int[]{73, 55, 36, 5, 55, 14, 9, 14}, 32, 69), 21);

        scenarios.forEach((input, expected) -> assertEquals(expected,
                arrayProblems.numSubarrayBoundedMax(input.getLeft(), input.getMiddle(), input.getRight()))
        );
    }

    @Test
    void testBagOfTokensScore() {
        Map<Pair<int[], Integer>, Integer> scenarios = new HashMap<>();

        scenarios.put(Pair.of(new int[]{100}, 50), 0);
        scenarios.put(Pair.of(new int[]{100, 200}, 150), 1);
        scenarios.put(Pair.of(new int[]{100, 400, 300, 200}, 200), 2);

        scenarios.forEach((input, expected) -> assertEquals(expected,
                arrayProblems.bagOfTokensScore(input.getLeft(), input.getRight())));
    }

    @Test
    void testThreeSum() {
        Map<int[], List<List<Integer>>> scenarios = new HashMap<>();

        scenarios.put(new int[]{-1, 0, 1, 2, -1, -4}, List.of(List.of(-1, -1, 2), List.of(-1, 0, 1)));
        scenarios.put(new int[]{-1, 0, 1, 1, 2, 2, -1}, List.of(List.of(-1, -1, 2), List.of(-1, 0, 1)));
        scenarios.put(new int[]{-1, 0, 1, 1, 2, -1}, List.of(List.of(-1, -1, 2), List.of(-1, 0, 1)));
        scenarios.put(new int[]{}, List.of());
        scenarios.put(new int[]{0}, List.of());

        scenarios.forEach((input, expected) -> assertEquals(expected, arrayProblems.threeSum(input)));
    }

    @Test
    void testThreeSumClosest() {
        Map<Pair<int[], Integer>, Integer> scenarios = new HashMap<>();

        scenarios.put(Pair.of(new int[]{-1, 0, 1, 2, -1, -4}, 0), 0);
        scenarios.put(Pair.of(new int[]{-1, 0, 1, 2, 1, -4}, 0), 0);
        scenarios.put(Pair.of(new int[]{-1, 2, 1, -4}, 1), 2);
        scenarios.put(Pair.of(new int[]{0, 2, 1, -3}, 1), 0);
        scenarios.put(Pair.of(new int[]{0, 2, 1}, 3), 3);

        scenarios.forEach((input, expected) -> assertEquals(expected,
                arrayProblems.threeSumClosest(input.getLeft(), input.getRight())));
    }

    @Test
    void testInsertMergeInterval() {
        Map<Pair<int[][], int[]>, int[][]> scenarios = new HashMap<>();

        scenarios.put(Pair.of(new int[][]{{1, 3}, {6, 9}}, new int[]{2, 5}), new int[][]{{1, 5}, {6, 9}});
        scenarios.put(Pair.of(new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[]{4, 8}),
                new int[][]{{1, 2}, {3, 10}, {12, 16}});
        scenarios.put(Pair.of(new int[][]{}, new int[]{5, 7}), new int[][]{{5, 7}});
        scenarios.put(Pair.of(new int[][]{{1, 5}}, new int[]{2, 3}), new int[][]{{1, 5}});
        scenarios.put(Pair.of(new int[][]{{1, 5}}, new int[]{2, 7}), new int[][]{{1, 7}});

        scenarios.forEach((input, expected) ->
                assertArrayEquals(expected, arrayProblems.insertMergeInterval(input.getLeft(), input.getRight())));
    }

    @Test
    void testMergeInterval() {
        Map<int[][], int[][]> scenarios = new HashMap<>();

        scenarios.put(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}}, new int[][]{{1, 6}, {8, 10}, {15, 18}});
        scenarios.put(new int[][]{{1, 4}, {4, 5}}, new int[][]{{1, 5}});
        scenarios.put(new int[][]{{1, 3}, {4, 5}}, new int[][]{{1, 3}, {4, 5}});

        scenarios.forEach((input, expected) -> assertArrayEquals(expected, arrayProblems.mergeInterval(input)));
    }

    @Test
    void testCountSmaller() {
        Map<int[], List<Integer>> scenarios = new HashMap<>();

        scenarios.put(new int[]{5, 2, 6, 1}, List.of(2, 1, 1, 0));
        scenarios.put(new int[]{5, 6, 7, 8}, List.of(0, 0, 0, 0));
        scenarios.put(new int[]{-1, -2}, List.of(1, 0));
        scenarios.put(new int[]{-1, -1}, List.of(0, 0));
        scenarios.put(new int[]{-1}, List.of(0));

        scenarios.forEach((input, expected) -> assertEquals(expected, arrayProblems.countSmaller(input)));
    }

    @Test
    void testMinEatingSpeed() {
        Map<Pair<int[], Integer>, Integer> scenarios = new HashMap<>();

        scenarios.put(Pair.of(new int[]{3, 6, 7, 11}, 8), 4);
        scenarios.put(Pair.of(new int[]{30, 11, 23, 4, 20}, 5), 30);
        scenarios.put(Pair.of(new int[]{30, 11, 23, 4, 20}, 6), 23);

        scenarios.forEach((input, expected) ->
                assertEquals(expected, arrayProblems.minEatingSpeed(input.getLeft(), input.getRight())));
    }

    @Test
    void testShipWithinDays() {
        Map<Pair<int[], Integer>, Integer> scenarios = new HashMap<>();

        scenarios.put(Pair.of(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 5), 15);
        scenarios.put(Pair.of(new int[]{3, 2, 2, 4, 1, 4}, 3), 6);
        scenarios.put(Pair.of(new int[]{1, 2, 3, 1, 1}, 4), 3);

        scenarios.forEach((input, expected) ->
                assertEquals(expected, arrayProblems.shipWithinDays(input.getLeft(), input.getRight())));
    }

    @Test
    void testMinDaysBouquets() {
        Map<Triple<int[], Integer, Integer>, Integer> scenarios = new HashMap<>();

        scenarios.put(Triple.of(new int[]{1, 10, 3, 10, 2}, 3, 1), 3);
        scenarios.put(Triple.of(new int[]{1, 10, 3, 10, 2}, 3, 2), -1);
        scenarios.put(Triple.of(new int[]{7, 7, 7, 7, 12, 7, 7}, 2, 3), 12);
        scenarios.put(Triple.of(new int[]{1000000000, 1000000000}, 1, 1), 1000000000);
        scenarios.put(Triple.of(new int[]{1, 10, 2, 9, 3, 8, 4, 7, 5, 6}, 4, 2), 9);

        scenarios.forEach((input, expected) ->
                assertEquals(expected,
                        arrayProblems.minDaysBouquets(input.getLeft(), input.getMiddle(), input.getRight())));
    }

    @Test
    void testSplitArray() {
        Map<Pair<int[], Integer>, Integer> scenarios = new HashMap<>();

        scenarios.put(Pair.of(new int[]{7, 2, 5, 10, 8}, 2), 18);
        scenarios.put(Pair.of(new int[]{1, 2, 3, 4, 5}, 2), 9);
        scenarios.put(Pair.of(new int[]{1, 4, 4}, 3), 4);

        scenarios.forEach((input, expected) ->
                assertEquals(expected, arrayProblems.splitArray(input.getLeft(), input.getRight())));
    }

    @Test
    void testFindKthNumber() {
        Map<Triple<Integer, Integer, Integer>, Integer> scenarios = new HashMap<>();

        scenarios.put(Triple.of(3, 3, 5), 3);
        scenarios.put(Triple.of(2, 3, 6), 6);

        scenarios.forEach((input, expected) -> assertEquals(expected,
                arrayProblems.findKthNumber(input.getLeft(), input.getMiddle(), input.getRight())));
    }

    @Test
    void testSmallestDistancePair() {
        Map<Pair<int[], Integer>, Integer> scenarios = new HashMap<>();

        scenarios.put(Pair.of(new int[]{1, 3, 1}, 1), 0);
        scenarios.put(Pair.of(new int[]{1, 1, 1}, 2), 0);
        scenarios.put(Pair.of(new int[]{1, 6, 1}, 3), 5);

        scenarios.forEach((input, expected) ->
                assertEquals(expected, arrayProblems.smallestDistancePair(input.getLeft(), input.getRight())));
    }

    @Test
    void testFindMaxConsecutiveOnes() {
        Map<int[], Integer> scenarios = new HashMap<>();

        scenarios.put(new int[]{1, 0, 1, 1, 0}, 4);
        scenarios.put(new int[]{1, 0, 1, 1, 0, 1}, 4);

        scenarios.forEach((input, expected) -> assertEquals(expected, arrayProblems.findMaxConsecutiveOnes(input)));
    }

    @Test
    void testLongestOnes() {
        Map<Pair<int[], Integer>, Integer> scenarios = new HashMap<>();

        scenarios.put(Pair.of(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2), 6);
        scenarios.put(Pair.of(new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3), 10);

        scenarios.forEach((input, expected) ->
                assertEquals(expected, arrayProblems.longestOnes(input.getLeft(), input.getRight())));
    }

    @Test
    void testCarFleet() {
        Map<Triple<Integer, int[], int[]>, Integer> scenarios = new HashMap<>();

        scenarios.put(Triple.of(12, new int[]{10, 8, 0, 5, 3}, new int[]{2, 4, 1, 1, 3}), 3);

        scenarios.forEach((input, expected) -> assertEquals(expected,
                arrayProblems.carFleet(input.getLeft(), input.getMiddle(), input.getRight())));
    }

    @Test
    void testMinDifference() {
        Map<int[], Integer> scenarios = new HashMap<>();

        scenarios.put(new int[]{5, 3, 2, 4}, 0);
        scenarios.put(new int[]{1, 5, 0, 10, 14}, 1);
        scenarios.put(new int[]{6, 6, 0, 1, 1, 4, 6}, 2);
        scenarios.put(new int[]{1, 5, 6, 14, 15}, 1);
        scenarios.put(new int[]{1, 5, 6, 14, 15}, 1);
        scenarios.put(new int[]{82, 81, 95, 75, 20}, 1);

        scenarios.forEach((input, expected) -> assertEquals(expected, arrayProblems.minDifference(input)));
    }

    @Test
    void testTotalFruit() {
        Map<int[], Integer> scenarios = new HashMap<>();

        scenarios.put(new int[]{1, 2, 1}, 3);
        scenarios.put(new int[]{0, 1, 2, 2}, 3);
        scenarios.put(new int[]{1, 2, 3, 2, 2}, 4);
        scenarios.put(new int[]{3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4}, 5);

        scenarios.forEach((input, expected) -> assertEquals(expected, arrayProblems.totalFruit(input)));
    }

    @Test
    void testNextPermutation() {
        Map<int[], int[]> scenarios = new HashMap<>();

        scenarios.put(new int[]{1, 2, 3}, new int[]{1, 3, 2});
        scenarios.put(new int[]{3, 2, 1}, new int[]{1, 2, 3});
        scenarios.put(new int[]{1, 5, 8, 4, 7, 6, 4, 3, 1}, new int[]{1, 5, 8, 6, 1, 3, 4, 4, 7});

        scenarios.forEach((input, expected) -> {
            arrayProblems.nextPermutation(input);
            assertArrayEquals(expected, input);
        });
    }

    @Test
    void testJumpGameV() {
        Map<Pair<int[], Integer>, Integer> scenarios = new HashMap<>();

        scenarios.put(Pair.of(new int[]{6, 4, 14, 6, 8, 13, 9, 7, 10, 6, 12}, 2), 4);
        scenarios.put(Pair.of(new int[]{3, 3, 3, 3, 3}, 3), 1);
        scenarios.put(Pair.of(new int[]{7, 6, 5, 4, 3, 2, 1}, 1), 7);
        scenarios.put(Pair.of(new int[]{7, 1, 7, 1, 7, 1}, 2), 2);
        scenarios.put(Pair.of(new int[]{7}, 1), 1);

        scenarios.forEach((input, expected) ->
                assertEquals(expected, arrayProblems.jumpGameV(input.getLeft(), input.getRight())));
    }

    @Test
    void testJumpGameVI() {
        Map<Pair<int[], Integer>, Integer> scenarios = new HashMap<>();

        scenarios.put(Pair.of(new int[]{1, -1, -2, 4, -7, 3}, 2), 7);
        scenarios.put(Pair.of(new int[]{10, -5, -2, 4, 0, 3}, 3), 17);
        scenarios.put(Pair.of(new int[]{1, -5, -20, 4, -1, 3, -6, -3}, 2), 0);

        scenarios.forEach((input, expected) ->
                assertEquals(expected, arrayProblems.jumpGameVI(input.getLeft(), input.getRight())));
    }

    @Test
    void testJumpGameVII() {
        Map<Triple<String, Integer, Integer>, Boolean> scenarios = new HashMap<>();

        scenarios.put(Triple.of("011010", 2, 3), true);
        scenarios.put(Triple.of("01101110", 2, 3), false);
        scenarios.put(Triple.of("01", 0, 1), false);
        scenarios.put(Triple.of("0000000000", 2, 5), true);

        scenarios.forEach((input, expected) -> assertEquals(expected,
                arrayProblems.jumpGameVII(input.getLeft(), input.getMiddle(), input.getRight())));
    }

    @Test
    void testPlusOne() {
        Map<int[], int[]> scenarios = new HashMap<>();

        scenarios.put(new int[]{1, 2, 3}, new int[]{1, 2, 4});
        scenarios.put(new int[]{1, 2, 9}, new int[]{1, 3, 0});
        scenarios.put(new int[]{9}, new int[]{1, 0});
        scenarios.put(new int[]{0}, new int[]{1});

        scenarios.forEach((input, expected) -> assertArrayEquals(expected, arrayProblems.plusOne(input)));
    }

    @Test
    void testFindKthLargest() {
        Map<Pair<int[], Integer>, Integer> scenarios = new HashMap<>();

        scenarios.put(Pair.of(new int[]{2, 1, 5, 6, 4}, 2), 5);
        scenarios.put(Pair.of(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4), 4);

        scenarios.forEach((input, expected) -> assertEquals(expected,
                arrayProblems.findKthLargest(input.getLeft(), input.getRight())));
    }

    @Test
    void testFindMissingRanges() {
        Map<Triple<int[], Integer, Integer>, List<String>> scenarios = new HashMap<>();

        scenarios.put(Triple.of(new int[]{0, 1, 3, 50, 75}, 0, 99), List.of("2", "4->49", "51->74", "76->99"));
        scenarios.put(Triple.of(new int[]{}, 1, 1), List.of("1"));
        scenarios.put(Triple.of(new int[]{}, -3, -1), List.of("-3->-1"));
        scenarios.put(Triple.of(new int[]{-1}, -1, -1), List.of());
        scenarios.put(Triple.of(new int[]{-1}, -2, -1), List.of("-2"));

        scenarios.forEach((input, expected) -> assertEquals(expected,
                arrayProblems.findMissingRanges(input.getLeft(), input.getMiddle(), input.getRight())));
    }

    @Test
    void testCanAttendMeetings() {
        Map<int[][], Boolean> scenarios = new HashMap<>();

        scenarios.put(new int[][]{{0, 30}, {5, 10}, {15, 20}}, false);
        scenarios.put(new int[][]{{7, 10}, {2, 4}}, true);
        scenarios.put(new int[][]{}, true);

        scenarios.forEach((input, expected) -> assertEquals(expected, arrayProblems.canAttendMeetings(input)));
    }

    @Test
    void testMinMeetingRooms() {
        Map<int[][], Integer> scenarios = new HashMap<>();

        scenarios.put(new int[][]{{0, 30}, {5, 10}, {15, 20}}, 2);
        scenarios.put(new int[][]{{7, 10}, {2, 4}}, 1);
        scenarios.put(new int[][]{}, 0);
        scenarios.put(new int[][]{{1, 10}, {2, 9}, {3, 8}}, 3);

        scenarios.forEach((input, expected) -> assertEquals(expected, arrayProblems.minMeetingRooms(input)));
    }

    @Test
    void testMissingElement() {
        Map<Pair<int[], Integer>, Integer> scenarios = new HashMap<>();

        scenarios.put(Pair.of(new int[]{4, 7, 9, 10}, 1), 5);
        scenarios.put(Pair.of(new int[]{4, 7, 9, 10}, 3), 8);
        scenarios.put(Pair.of(new int[]{1, 2, 4}, 3), 6);

        scenarios.forEach((input, expected) ->
                assertEquals(expected, arrayProblems.missingElement(input.getLeft(), input.getRight())));
    }

    @Test
    void testPeakIndexInMountainArray() {
        Map<int[], Integer> scenarios = new HashMap<>();

        scenarios.put(new int[]{0, 1, 0}, 1);
        scenarios.put(new int[]{0, 2, 1, 0}, 1);
        scenarios.put(new int[]{0, 10, 5, 2}, 1);
        scenarios.put(new int[]{3, 4, 5, 1}, 2);
        scenarios.put(new int[]{24, 69, 100, 99, 79, 78, 67, 36, 26, 19}, 2);

        scenarios.forEach((input, expected) -> assertEquals(expected, arrayProblems.peakIndexInMountainArray(input)));
    }

    @Test
    void testFindMinInRotatedArray() {
        Map<int[], Integer> scenarios = new HashMap<>();

        scenarios.put(new int[]{3, 4, 5, 1, 2}, 1);
        scenarios.put(new int[]{4, 5, 6, 7, 0, 1, 2}, 0);
        scenarios.put(new int[]{11, 13, 15, 17}, 11);

        scenarios.forEach((input, expected) -> assertEquals(expected, arrayProblems.findMinInRotatedArray(input)));
    }

    @Test
    void testFindMinInRotatedArrayII() {
        Map<int[], Integer> scenarios = new HashMap<>();

        scenarios.put(new int[]{2, 2, 2, 0, 1, 2, 2}, 0);
        scenarios.put(new int[]{1, 3, 5}, 1);

        scenarios.forEach((input, expected) -> assertEquals(expected, arrayProblems.findMinInRotatedArrayII(input)));
    }

    @Test
    void testSortColors() {
        Map<int[], int[]> scenarios = new HashMap<>();

        scenarios.put(new int[]{2, 0, 2, 1, 1, 0}, new int[]{0, 0, 1, 1, 2, 2});
        scenarios.put(new int[]{2, 0, 1}, new int[]{0, 1, 2});
        scenarios.put(new int[]{0}, new int[]{0});

        scenarios.forEach((input, expected) -> {
            arrayProblems.sortColors(input);
            assertArrayEquals(expected, input);
        });
    }

    @Test
    void testFindPeakElement() {
        Map<int[], List<Integer>> scenarios = new HashMap<>();

        scenarios.put(new int[]{1, 2, 3, 1}, List.of(2));
        scenarios.put(new int[]{1, 2, 1, 3, 5, 6, 4}, List.of(1, 5));

        scenarios.forEach((input, expected) -> assertTrue(expected.contains(arrayProblems.findPeakElement(input))));
    }

    @Test
    void testAsteroidCollision() {
        Map<int[], int[]> scenarios = new HashMap<>();

        scenarios.put(new int[]{5, 10, -5}, new int[]{5, 10});
        scenarios.put(new int[]{5, -5}, new int[]{});
        scenarios.put(new int[]{10, 2, -5}, new int[]{10});
        scenarios.put(new int[]{-2, -1, 1, 2}, new int[]{-2, -1, 1, 2});
        scenarios.put(new int[]{-2, -2, 1, -2}, new int[]{-2, -2, -2});

        scenarios.forEach((input, expected) -> assertArrayEquals(expected, arrayProblems.asteroidCollision(input)));
    }

    @Test
    void testTrapRainWater() {
        Map<int[], Integer> scenarios = new HashMap<>();

        scenarios.put(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}, 6);
        scenarios.put(new int[]{4, 2, 0, 3, 2, 5}, 9);
        scenarios.put(new int[]{}, 0);

        scenarios.forEach((input, expected) -> assertEquals(expected, arrayProblems.trapRainWater(input)));
    }

    @Test
    void testRemoveDuplicates() {
        Map<int[], Pair<Integer, int[]>> scenarios = new HashMap<>();

        scenarios.put(new int[]{1, 1, 2, 2, 3, 4}, Pair.of(4, new int[]{1, 2, 3, 4}));
        scenarios.put(new int[]{1, 1, 2}, Pair.of(2, new int[]{1, 2}));
        scenarios.put(new int[]{1, 2, 3}, Pair.of(3, new int[]{1, 2, 3}));
        scenarios.put(new int[]{}, Pair.of(0, new int[]{}));
        scenarios.put(null, Pair.of(0, null));

        scenarios.forEach((input, expected) -> {
            int index = arrayProblems.removeDuplicates(input);
            assertEquals(expected.getLeft(), index);

            if (expected.getRight() != null && expected.getRight().length > 0) {
                for (int i = 0; i < index; i++)
                    assertEquals(expected.getRight()[i], input[i]);
            }
        });
    }

    @Test
    void testRemoveDuplicatesII() {
        Map<int[], Pair<Integer, int[]>> scenarios = new HashMap<>();

        scenarios.put(new int[]{1, 1, 2, 2, 3, 4}, Pair.of(6, new int[]{1, 1, 2, 2, 3, 4}));
        scenarios.put(new int[]{1, 1, 1}, Pair.of(2, new int[]{1, 1}));
        scenarios.put(new int[]{1, 2, 3}, Pair.of(3, new int[]{1, 2, 3}));
        scenarios.put(new int[]{}, Pair.of(0, new int[]{}));
        scenarios.put(null, Pair.of(0, null));

        scenarios.forEach((input, expected) -> {
            int index = arrayProblems.removeDuplicatesII(input);
            assertEquals(expected.getLeft(), index);

            if (expected.getRight() != null && expected.getRight().length > 0) {
                for (int i = 0; i < index; i++)
                    assertEquals(expected.getRight()[i], input[i]);
            }
        });
    }

    @Test
    void testProductExceptSelf() {
        Map<int[], int[]> scenarios = new HashMap<>();

        scenarios.put(new int[]{1, 2, 3, 4}, new int[]{24, 12, 8, 6});
        scenarios.put(new int[]{-1, 1, 0, -3, 3}, new int[]{0, 0, 9, 0, 0});
        scenarios.put(new int[]{}, new int[]{});
        scenarios.put(null, new int[]{});

        scenarios.forEach((input, expected) ->
                assertArrayEquals(expected, arrayProblems.productExceptSelf(input)));
    }

    @Test
    void testMoveZeroes() {
        Map<int[], int[]> scenarios = new HashMap<>();

        scenarios.put(new int[]{0, 1, 2, 0, 3}, new int[]{1, 2, 3, 0, 0});
        scenarios.put(new int[]{0, 1, 2, 3}, new int[]{1, 2, 3, 0});
        scenarios.put(new int[]{1, 2, 3}, new int[]{1, 2, 3});
        scenarios.put(new int[]{0}, new int[]{0});
        scenarios.put(new int[]{}, new int[]{});
        scenarios.put(null, null);

        scenarios.forEach((input, expected) -> {
            arrayProblems.moveZeroes(input);
            assertArrayEquals(expected, input);
        });
    }

    @Test
    void testMergeSortedArrays() {
        Map<Pair<Pair<int[], Integer>, Pair<int[], Integer>>, int[]> scenarios = new HashMap<>();

        scenarios.put(Pair.of(Pair.of(new int[]{1, 2, 3, 0, 0, 0}, 3), Pair.of(new int[]{2, 5, 6}, 3)),
                new int[]{1, 2, 2, 3, 5, 6});
        scenarios.put(Pair.of(Pair.of(new int[]{1, 2, 3, 0, 0, 0}, 3), Pair.of(new int[]{-2, -1, 0}, 3)),
                new int[]{-2, -1, 0, 1, 2, 3});
        scenarios.put(Pair.of(Pair.of(new int[]{1, 2, 3, 0, 0, 0}, 3), Pair.of(new int[]{4, 5, 6}, 3)),
                new int[]{1, 2, 3, 4, 5, 6});
        scenarios.put(Pair.of(Pair.of(new int[]{1}, 1), Pair.of(new int[]{}, 0)), new int[]{1});
        scenarios.put(Pair.of(Pair.of(new int[]{0}, 0), Pair.of(new int[]{1}, 1)), new int[]{1});

        scenarios.forEach((input, expected) -> {
            arrayProblems.mergeSortedArrays(input.getLeft().getLeft(), input.getLeft().getRight(),
                    input.getRight().getLeft(), input.getRight().getRight());
            assertArrayEquals(expected, input.getLeft().getLeft());
        });
    }

    @Test
    void testCanThreePartsEqualSum() {
        Map<int[], Boolean> scenarios = new HashMap<>();
        scenarios.put(new int[]{0, 2, 1, -6, 6, -7, 9, 1, 2, 0, 1}, true);
        scenarios.put(new int[]{0, 0, 0, 0}, true);
        scenarios.put(new int[]{0, 2, 1, -6, 6, 7, 9, -1, 2, 0, 1}, false);
        scenarios.put(new int[]{3, 3, 6, 5, -2, 2, 5, 1, -9, 4}, true);

        scenarios.forEach((input, expected) -> assertEquals(expected, arrayProblems.canThreePartsEqualSum(input)));
    }

    @Test
    void testNextGreaterElement() {
        Map<Pair<int[], int[]>, int[]> scenarios = new HashMap<>();
        scenarios.put(Pair.of(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2}), new int[]{-1, 3, -1});
        scenarios.put(Pair.of(new int[]{2, 4}, new int[]{1, 2, 3, 4}), new int[]{3, -1});

        scenarios.forEach((input, expected) ->
                assertArrayEquals(expected, arrayProblems.nextGreaterElement(input.getLeft(), input.getRight())));
    }

    @Test
    void testFindBuildings() {
        Map<int[], int[]> scenarios = new HashMap<>();
        scenarios.put(new int[]{4, 2, 3, 1}, new int[]{0, 2, 3});
        scenarios.put(new int[]{4, 3, 2, 1}, new int[]{0, 1, 2, 3});
        scenarios.put(new int[]{1, 3, 2, 4}, new int[]{3});
        scenarios.put(new int[]{2, 2, 2, 2}, new int[]{3});

        scenarios.forEach((input, expected) -> assertArrayEquals(expected, arrayProblems.findBuildings(input)));
    }

    @Test
    void testExclusiveTime() {
        Map<Pair<Integer, List<String>>, int[]> scenarios = new HashMap<>();
        scenarios.put(Pair.of(2, Arrays.asList("0:start:0", "1:start:2", "1:end:5", "0:end:6")), new int[]{3, 4});
        scenarios.put(Pair.of(1, Arrays.asList("0:start:0", "0:start:2", "0:end:5", "0:start:6", "0:end:6", "0:end:7")),
                new int[]{8});
        scenarios.put(Pair.of(2, Arrays.asList("0:start:0", "0:start:2", "0:end:5", "1:start:6", "1:end:6", "0:end:7")),
                new int[]{7, 1});

        scenarios.forEach((input, expected) -> assertArrayEquals(expected,
                arrayProblems.exclusiveTime(input.getLeft(), input.getRight())));
    }
}
