package org.solutions.leetcode;

import java.util.*;

public class MathProblems {

    int countArrangement = 0;

    /**
     * Q. 1342 Number of steps to reduce a number to zero
     * <p>
     * Given a non-negative integer num, return the number of steps to reduce it to zero.
     * If the current number is even, you have to divide it by 2, otherwise, you have to subtract 1 from it.
     * <p>
     * Tags:: math
     */
    public int numberOfSteps(int num) {
        if (num == 0)
            return 0;

        int count = 0;
        while (num > 1) {
            count += (num % 2) + 1;
            num = num >> 1;
        }
        return ++count;
    }

    /**
     * Q. 575 Distribute Candies
     * <p>
     * Alice has n candies, where the ith candy is of type candyType[i]. Alice noticed that she started to gain weight,
     * so she visited a doctor.The doctor advised Alice to only eat n / 2 of the candies she has (n is always even).
     * Alice likes her candies very much, and she wants to eat the maximum number of different types of candies while
     * still following the doctor's advice.
     * <p>
     * Given the integer array candyType of length n,
     * return the maximum number of different types of candies she can eat if she only eats n / 2 of them.
     * <p>
     * Tags:: math
     */
    public int distributeCandies(int[] candyType) {
        if (candyType == null || candyType.length == 0)
            return 0;

        Set<Integer> set = new HashSet<>();

        for (int c : candyType) {
            set.add(c);

            if (set.size() == candyType.length / 2)
                return candyType.length / 2;
        }

        return set.size();
    }

    /**
     * Q. 645 Set Mismatch
     * <p>
     * You have a set of integers s, which originally contains all the numbers from 1 to n.
     * Unfortunately, due to some error, one of the numbers in s got duplicated to another number in the set,
     * which results in repetition of one number and loss of another number.
     * <p>
     * You are given an integer array nums representing the data status of this set after the error.
     * Find the number that occurs twice and the number that is missing and return them in the form of an array.
     * <p>
     * Tags:: math
     */
    public int[] findErrorNums(int[] nums) {
        if (nums == null || nums.length < 2)
            return new int[]{};

        int diff = 0, sqDiff = 0, sum;
        for (int i = 0; i < nums.length; i++) {
            diff += i + 1 - nums[i];
            sqDiff += (i + 1) * (i + 1) - nums[i] * nums[i];
        }

        sum = sqDiff / diff;
        return new int[]{(sum - diff) / 2, (sum + diff) / 2};
    }

    /**
     * Q. 268 Missing Number
     * <p>
     * Given an array nums containing n distinct numbers in the range [0, n],
     * return the only number in the range that is missing from the array.
     * <p>
     * Tags:: math
     */
    public int missingNumber(int[] nums) {
        if (nums == null || nums.length == 0)
            return -1;

        int n = nums.length, sum = 0;
        for (int num : nums)
            sum += num;

        return (n * (n + 1) / 2 - sum);
    }

    /**
     * Q. 869 Reordered Power of 2
     * <p>
     * Starting with a positive integer N, we reorder the digits in any order (including the original order)
     * such that the leading digit is not zero.
     * Return true if and only if we can do this in a way such that the resulting number is a power of 2.
     * <p>
     * tags:: math
     */
    public boolean reorderedPowerOf2(int N) {
        int[] expectedCount = getCount(N);
        for (int i = 0; i < 31; i++)
            if (Arrays.equals(expectedCount, getCount(1 << i)))
                return true;

        return false;
    }

    /**
     * Given a number, return an array, with occurrence of each digit
     */
    private int[] getCount(int num) {
        int[] count = new int[10];
        while (num > 0) {
            count[num % 10]++;
            num /= 10;
        }

        return count;
    }

    /**
     * Q. 1551 Minimum Operations to Make Array Equal
     * <p>
     * You have an array arr of length n where arr[i] = (2 * i) + 1 for all valid values of i (i.e. 0 <= i < n).
     * In one operation, you can select two indices x and y where 0 <= x, y < n and subtract 1 from arr[x] and add 1
     * to arr[y] (i.e. perform arr[x] -=1 and arr[y] += 1). The goal is to make all the elements of the array equal.
     * It is guaranteed that all the elements of the array can be made equal using some operations. Given an integer n,
     * the length of the array. Return the minimum number of operations needed to make all the elements of arr equal.
     * <p>
     * tags:: array, Math
     */
    public int minOperations(int n) {
        return (n * n) >> 2;
    }

    /**
     * Q. 326 Power of Three
     * <p>
     * Given an integer n, return true if it is a power of three. Otherwise, return false.
     * An integer n is a power of three, if there exists an integer x such that n == 3^x.
     * <p>
     * tags:: math
     */
    public boolean isPowerOfThree(int n) {
        if (n <= 0)
            return false;

        // check decimal part is zero, by taking mod 1
        return (Math.log10(n) / Math.log10(3)) % 1 == 0;
    }

    /**
     * Q. 970 Powerful Integers
     * <p>
     * Given three integers x, y, and bound, return a list of all the powerful integers that have a value less than or
     * equal to bound. An integer is powerful if it can be represented as xi + yj for some integers i >= 0 and j >= 0.
     * You may return the answer in any order. In your answer, each value should occur at most once.
     * <p>
     * tags:: math, array
     */
    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        Set<Integer> ans = new HashSet<>();

        int a = (x == 1) ? bound : (int) (Math.log10(bound) / Math.log10(x));
        int b = (y == 1) ? bound : (int) (Math.log10(bound) / Math.log10(y));

        for (int i = 0; i <= a; i++) {
            for (int j = 0; j <= b; j++) {
                int sum = (int) (Math.pow(x, i) + Math.pow(y, j));

                if (sum <= bound)
                    ans.add(sum);

                if (y == 1) break;
            }

            if (x == 1) break;
        }

        return new ArrayList<>(ans);
    }

    /**
     * Q. 319 Bulb Switcher
     * <p>
     * There are n bulbs that are initially off. You first turn on all the bulbs, then you turn off every second bulb.
     * On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on).
     * For the ith round, you toggle every i bulb. For the nth round, you only toggle the last bulb.Return the number of
     * bulbs that are on after n rounds.
     * <p>
     * tags:: math
     */
    public int bulbSwitcher(int n) {
        return (int) Math.sqrt(n);
    }

    /**
     * Q. 204 Count primes
     * <p>
     * Count the number of prime numbers less than a non-negative number, n.
     */
    public int countPrimes(int n) {
        if (n <= 2) return 0;

        boolean[] primes = new boolean[n];
        Arrays.fill(primes, true);
        int count = n - 2;
        for (int i = 2; i <= (int) Math.sqrt(n); i++) {
            if (primes[i]) {
                for (int j = i * i; j < n; j += i) {
                    if (primes[j]) {
                        primes[j] = false;
                        count--;
                    }
                }
            }
        }
        return count;
    }

    /**
     * Q. 191 Number of 1 bits
     * <p>
     * Write a function that takes an unsigned integer and returns the number of '1' bits it has
     * (also known as the Hamming weight).
     * <p>
     * Tags:: math
     */
    public int numberOf1Bits(long n) {
        int count = 0;

        while (n != 0) {
            count++;
            n &= (n - 1);
        }

        return count;
    }

    /**
     * Q. 526 Beautiful Arrangement
     * Suppose you have n integers labeled 1 through n. A permutation of those n integers perm (1-indexed) is considered
     * a beautiful arrangement if for every i (1 <= i <= n), either of the following is true:
     * perm[i] is divisible by i.
     * i is divisible by perm[i].
     * Given an integer n, return the number of the beautiful arrangements that you can construct.
     * <p>
     * tags::backtracking
     */
    public int countArrangement(int n) {
        backtrackCountArrangement(new boolean[n + 1], n, 1);
        return countArrangement;
    }

    private void backtrackCountArrangement(boolean[] used, int n, int pos) {
        if (pos > n) {
            countArrangement++;
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (!used[i] && (i % pos == 0 || pos % i == 0)) {
                used[i] = true;
                backtrackCountArrangement(used, n, pos + 1);
                used[i] = false;
            }
        }
    }

    /**
     * Q. 973 K Closest Points to Origin
     * <p>
     * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k,
     * return the k closest points to the origin (0, 0).
     * The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).
     * You may return the answer in any order.
     * The answer is guaranteed to be unique (except for the order that it is in).
     * <p>
     * tags:: math, euclidean distance
     */
    public int[][] kClosest(int[][] points, int k) {
        int[][] answer = new int[k][2];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] * b[0] + b[1] * b[1] - a[0] * a[0] - a[1] * a[1]);

        for (int[] point : points) {
            pq.add(point);
            if (pq.size() > k)
                pq.poll();
        }

        for (int i = 0; i < k; i++) {
            answer[i] = pq.poll();
        }

        return answer;
    }

    /**
     * Q. 670 Maximum Swap
     * You are given an integer num. You can swap two digits at most once to get the maximum valued number.
     * Return the maximum valued number you can get.
     * <p>
     * tags::array, math, greedy
     */
    public int maximumSwap(int num) {
        char[] numArray = Integer.toString(num).toCharArray();
        int[] buckets = new int[10];

        for (int i = 0; i < numArray.length; i++)
            buckets[numArray[i] - '0'] = i;

        for (int i = 0; i < numArray.length; i++) {
            for (int j = 9; j > numArray[i] - '0'; j--) {
                if (buckets[j] > i) {
                    char temp = numArray[i];
                    numArray[i] = numArray[buckets[j]];
                    numArray[buckets[j]] = temp;

                    return Integer.parseInt(new String(numArray));
                }
            }
        }

        return num;
    }

    /**
     * Q. 9 Palindrome Number
     * <p>
     * Given an integer x, return true if x is palindrome integer.
     * An integer is a palindrome when it reads the same backward as forward.
     * For example, 121 is palindrome while 123 is not.
     * <p>
     * tags::math, reverse, palindrome
     */
    public boolean isPalindrome(int x) {
        if (x < 0)
            return false;

        int reversed = 0, remainder, original = x;

        while (x != 0) {
            remainder = x % 10;
            reversed = reversed * 10 + remainder;
            x /= 10;
        }


        return original == reversed;
    }

    /**
     * Q. 70 Climbing Stairs
     * <p>
     * You are climbing a staircase. It takes n steps to reach the top.
     * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
     * <p>
     * tags:: math, fibonacci
     */
    public int climbStairs(int n) {
        if (n <= 2)
            return n;

        int n1 = 1, n2 = 2;
        for (int i = 3; i <= n; i++) {
            int temp = n2;
            n2 = n1 + n2;
            n1 = temp;
        }

        return n2;
    }

    /**
     * Q. 50 Pow(x, n)
     * <p>
     * Implement pow(x, n), which calculates x raised to the power n (i.e., x^n).
     * <p>
     * tags:: math
     */
    public double myPow(double x, int n) {
        if (n == 0) return 1;

        double temp = myPow(x, n / 2);
        double factor = 1;

        if (n % 2 != 0) {
            factor = (n > 0) ? x : (1 / x);
        }

        return temp * temp * factor;
    }

    /**
     * Q. 227 Basic Calculator II
     * <p>
     * Given a string s which represents an expression, evaluate this expression and return its value.
     * The integer division should truncate toward zero.
     * You may assume that the given expression is always valid.
     * All intermediate results will be in the range of [-2^31, 2^31 - 1].
     * <p>
     * Note: You are not allowed to use any built-in function which evaluates strings
     * as mathematical expressions, such as eval().
     * <p>
     * tags::math, calculator
     */
    public int basicCalculatorII(String s) {
        Stack<Integer> stack = new Stack<>();
        int currNumber = 0, answer = 0;
        char op = '+';

        s = s.replaceAll(" ", "");

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                currNumber = currNumber * 10 + (ch - '0');
            }

            if (!Character.isDigit(ch) || i == s.length() - 1) {
                if (op == '-')
                    stack.push(-currNumber);
                else if (op == '+')
                    stack.push(currNumber);
                else if (op == '*')
                    stack.push(stack.pop() * currNumber);
                else if (op == '/')
                    stack.push(stack.pop() / currNumber);

                op = ch;
                currNumber = 0;
            }
        }

        while (!stack.isEmpty()) {
            answer += stack.pop();
        }

        return answer;
    }

    /**
     * Q. 137 Single Number II
     * <p>
     * Given an integer array nums where every element appears three times except for one, which appears exactly once.
     * Find the single element and return it.
     * <p>
     * You must implement a solution with a linear runtime complexity and use only constant extra space.
     * <p>
     * tags:: math, bitManipulation
     */
    public int singleNumberII(int[] nums) {
        int seenOnce = 0, seenTwice = 0;

        for (int num : nums) {
            seenOnce = ~seenTwice & (seenOnce ^ num);
            seenTwice = ~seenOnce & (seenTwice ^ num);
        }

        return seenOnce;
    }
}
