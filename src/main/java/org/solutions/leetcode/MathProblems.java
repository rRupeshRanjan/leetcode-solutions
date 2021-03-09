package org.solutions.leetcode;

import java.util.HashSet;
import java.util.Set;

public class MathProblems {

    /*
    * Q.1342
    * Given a non-negative integer num, return the number of steps to reduce it to zero.
    * If the current number is even, you have to divide it by 2, otherwise, you have to subtract 1 from it.
    *
    * Tags:: math
    * */
    public int numberOfSteps (int num) {
        if(num == 0)
            return 0;

        int count = 0;
        while(num > 1) {
            count += (num % 2) + 1;
            num = num >> 1;
        }
        return ++count;
    }

    /*
    * Q.575
    * Alice has n candies, where the ith candy is of type candyType[i]. Alice noticed that she started to gain weight,
    * so she visited a doctor.The doctor advised Alice to only eat n / 2 of the candies she has (n is always even).
    * Alice likes her candies very much, and she wants to eat the maximum number of different types of candies while
    * still following the doctor's advice.
    *
    * Given the integer array candyType of length n, return the maximum number of different types of candies she can eat if she only eats n / 2 of them.
    *
    * Tags:: math
    * */
    public int distributeCandies(int[] candyType) {
        Set<Integer> set = new HashSet<>();

        for(int c: candyType) {
            set.add(c);

            if(set.size() == candyType.length/2)
                return candyType.length/2;
        }

        return set.size();
    }

    /*
    * Q.645
    * You have a set of integers s, which originally contains all the numbers from 1 to n.
    * Unfortunately, due to some error, one of the numbers in s got duplicated to another number in the set,
    * which results in repetition of one number and loss of another number.
    *
    * You are given an integer array nums representing the data status of this set after the error.
    * Find the number that occurs twice and the number that is missing and return them in the form of an array.
    *
    * Tags:: math
    * */
    public int[] findErrorNums(int[] nums) {
        int diff=0, sqDiff=0, sum=0;
        for(int i=0; i<nums.length; i++) {
            diff += i+1-nums[i];
            sqDiff += (i+1)*(i+1) - nums[i]*nums[i];
        }

        sum = sqDiff/diff;
        return new int[]{(sum-diff)/2, (sum+diff)/2};
    }

    /*
    * Q.268
    * Given an array nums containing n distinct numbers in the range [0, n],
    * return the only number in the range that is missing from the array.
    *
    * Tags:: math
    * */
    public int missingNumber(int[] nums) {
        int n = nums.length, sum = 0;
        for(int num: nums)
            sum += num;

        return( n*(n+1)/2 - sum );
    }
}
