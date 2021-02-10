package org.solutions.leetcode;

public class BitManipulation {

    /*
    * Q. 191 Write a function that takes an unsigned integer and returns the number of '1' bits it has
    * (also known as the Hamming weight).
    * */
    public int NumberOf1Bits(long n) {
        int count = 0;

        while (n != 0) {
            count++;
            n &= (n - 1);
        }

        return count;
    }
}
