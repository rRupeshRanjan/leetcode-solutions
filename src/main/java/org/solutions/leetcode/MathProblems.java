package org.solutions.leetcode;

public class MathProblems {

    /*
    * Q.1342
    * Given a non-negative integer num, return the number of steps to reduce it to zero.
    * If the current number is even, you have to divide it by 2, otherwise, you have to subtract 1 from it.
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
}
