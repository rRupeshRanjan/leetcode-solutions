package org.solutions.leetcode.customClassDesign;

import java.util.Random;

/*
 * Q. 384
 *
 * Given an integer array nums, design an algorithm to randomly shuffle the array. Implement the Solution class:
 * Solution(int[] nums) Initializes the object with the integer array nums.
 * int[] reset() Resets the array to its original configuration and returns it.
 * int[] shuffle() Returns a random shuffling of the array.
 *
 * tags:: design, fisher-yates, algorithm
 * */
public class ShuffleArray {
    int[] nums;
    Random random;

    public ShuffleArray(int[] nums) {
        this.nums = nums;
        this.random = new Random();
    }

    public int[] reset() {
        return nums;
    }

    public int[] shuffle() {
        int[] shuffle = nums.clone();
        int max = shuffle.length;

        for (int i = 0; i < shuffle.length; i++) {
            int rand = random.nextInt(max - i) + i;
            int temp = shuffle[i];
            shuffle[i] = shuffle[rand];
            shuffle[rand] = temp;
        }

        return shuffle;
    }
}

