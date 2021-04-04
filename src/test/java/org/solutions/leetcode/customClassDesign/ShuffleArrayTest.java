package org.solutions.leetcode.customClassDesign;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class ShuffleArrayTest {

    @Test
    void testShuffling() {
        ShuffleArray shuffleArray = new ShuffleArray(new int[]{1, 2, 3});
        System.out.println(Arrays.toString(shuffleArray.shuffle()));
        System.out.println(Arrays.toString(shuffleArray.reset()));
        System.out.println(Arrays.toString(shuffleArray.shuffle()));
    }

}