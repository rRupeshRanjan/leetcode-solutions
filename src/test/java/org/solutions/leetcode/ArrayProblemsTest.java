package org.solutions.leetcode;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

        List<String> ways = Arrays.asList("brute-force", "sorting", "hashmap");

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

        scenarios.forEach((input, expected) -> {
            assertArrayEquals(expected, arrayProblems.advantageCount(input.getLeft(), input.getRight()));
        });
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
        scenarios.put(new int[]{-1, 0, 1, 2, -1, -4}, Arrays.asList(Arrays.asList(-1, -1, 2), Arrays.asList(-1, 0, 1)));
        scenarios.put(new int[]{-1, 0, 1, 1, 2, 2, -1}, Arrays.asList(Arrays.asList(-1, -1, 2), Arrays.asList(-1, 0, 1)));
        scenarios.put(new int[]{-1, 0, 1, 1, 2, -1}, Arrays.asList(Arrays.asList(-1, -1, 2), Arrays.asList(-1, 0, 1)));
        scenarios.put(new int[]{}, Collections.emptyList());
        scenarios.put(new int[]{0}, Collections.emptyList());

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
        scenarios.put(new int[]{5, 2, 6, 1}, Arrays.asList(2, 1, 1, 0));
        scenarios.put(new int[]{5, 6, 7, 8}, Arrays.asList(0, 0, 0, 0));
        scenarios.put(new int[]{-1, -2}, Arrays.asList(1, 0));
        scenarios.put(new int[]{-1, -1}, Arrays.asList(0, 0));
        scenarios.put(new int[]{-1}, Collections.singletonList(0));

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
}
