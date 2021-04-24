package org.solutions.leetcode;

import org.apache.commons.lang3.tuple.Pair;
import org.solutions.leetcode.exceptions.BadInputException;
import org.solutions.leetcode.utils.StringUtils;

import java.util.*;

public class StringProblems {

    private final StringUtils stringUtils;

    public StringProblems() {
        this.stringUtils = new StringUtils();
    }

    /*
     * Q.821
     * Given a string s and a character c that occurs in s, return an array of integers answer where
     *   answer.length == s.length
     *   answer[i] is the shortest distance from s[i] to the character c in s.
     *
     * Tags:: string
     * */
    public int[] shortestToChar(String s, char c) {
        int[] result = new int[s.length()];

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == c) {
                result[i] = 0;
            } else {
                int lastIndex = s.lastIndexOf(c, i);
                int nextIndex = s.indexOf(c, i);

                if (lastIndex == -1 || nextIndex == -1)
                    result[i] = Math.abs((lastIndex == -1) ? nextIndex - i : lastIndex - i);
                else
                    result[i] = Math.min(Math.abs(nextIndex - i), Math.abs(i - lastIndex));
            }
        }

        return result;
    }

    /*
     * Q.242
     * Given two strings s and t , write a function to determine if t is an anagram of s.
     *
     * Tags:: hashmap, string
     * */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length())
            return false;

        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }

        for (int c : count) {
            if (c != 0)
                return false;
        }
        return true;
    }

    /*
     * Q. 784
     * Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.
     * Return a list of all possible strings we could create. You can return the output in any order.
     *
     * Tags:: recursion, string
     * */
    public List<String> letterCasePermutation(String S) {
        List<String> returnList = new ArrayList<>();

        findAllPermutations(S, returnList, 0);
        return returnList;
    }

    private void findAllPermutations(String S, List<String> returnList, int start) {
        returnList.add(S);

        for (int i = start; i < S.length(); i++) {
            char[] word = S.toCharArray();
            if (Character.isLetter(S.charAt(i))) {
                if (Character.isUpperCase(S.charAt(i))) {
                    word[i] = Character.toLowerCase(S.charAt(i));
                    findAllPermutations(String.valueOf(word), returnList, i + 1);
                } else if (Character.isLowerCase(S.charAt(i))) {
                    word[i] = Character.toUpperCase(S.charAt(i));
                    findAllPermutations(String.valueOf(word), returnList, i + 1);
                }
            }
        }
    }

    /*
     * Q. 820
     *
     * A valid encoding of an array of words is any reference string s and array of indices indices such that:
     * words.length == indices.length. The reference string s ends with the '#' character.
     * For each index indices[i], the substring of s starting from indices[i] and up to (but not including)
     * the next '#' character is equal to words[i]. Given an array of words, return the length of the shortest reference
     * string s possible of any valid encoding of words.
     *
     * Tags: string, TrieNode
     * Also see: TrieProblems.minimumLengthEncoding()
     * */
    public int minimumLengthEncoding(String[] words) {
        Set<String> set = new HashSet<>(Arrays.asList(words));
        int length = 0;

        for (String word : words) {
            for (int i = 1; i < word.length(); i++) {
                set.remove(word.substring(i));
            }
        }

        for (String word : set)
            length += word.length() + 1;
        return length;
    }

    /*
     * Q. 1461
     * Given a binary string s and an integer k.
     * Return True if every binary code of length k is a substring of s. Otherwise, return False.
     *
     * tags: string, hashing, maths
     * */
    public boolean hasAllCodes(String s, int k) {
        int need = 1 << k, allOne = need - 1, hashVal = 0;
        boolean[] got = new boolean[need];

        for (int i = 0; i < s.length(); i++) {
            hashVal = ((hashVal << 1) & allOne) | (s.charAt(i) - '0');

            if (i >= k - 1 && !got[hashVal]) {
                got[hashVal] = true;
                need--;
                if (need == 0)
                    return true;
            }
        }

        return false;
    }

    /*
     * Q. 916
     *
     * We are given two arrays A and B of words.  Each word is a string of lowercase letters.
     * Now, say that word b is a subset of word a if every letter in b occurs in a, including multiplicity.
     * For example, "wrr" is a subset of "warrior", but is not a subset of "world".
     * Now say a word a from A is universal if for every b in B, b is a subset of a.
     * Return a list of all universal words in A.  You can return the words in any order.
     *
     * tags:: string, superset, count
     * */
    public List<String> wordSubsets(String[] A, String[] B) throws BadInputException {
        int[] bCount = new int[26];
        List<String> result = new ArrayList<>();

        for (String s : B) {
            int[] count = stringUtils.getLowercaseCharCount(s);
            for (int i = 0; i < 26; i++) {
                bCount[i] = Math.max(bCount[i], count[i]);
            }
        }

        for (String s : A) {
            int[] count = stringUtils.getLowercaseCharCount(s);
            boolean isCurrentSuperSet = true;
            for (int i = 0; i < 26; i++) {
                if (bCount[i] > count[i]) {
                    isCurrentSuperSet = false;
                    break;
                }
            }

            if (isCurrentSuperSet)
                result.add(s);
        }

        return result;
    }

    /*
     * Q. 647
     *
     * Given a string, your task is to count how many palindromic substrings in this string.
     * The substrings with different start indexes or end indexes are counted as
     * different substrings even they consist of same characters.
     *
     * tags:: palindrome, strings
     * */
    public int countSubstrings(String s) {
        int count = 0;

        for (int i = 0; i < 2 * s.length() - 1; i++) {
            int left = i / 2;
            int right = left + i % 2;

            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
                count++;
            }
        }

        return count;
    }

    /*
     * Q. 474
     *
     * You are given an array of binary strings strs and two integers m and n.
     * Return the size of the largest subset of strs such that there are at most m 0's and n 1's in the subset.
     * A set x is a subset of a set y if all elements of x are also elements of y.
     *
     * tags:: string, dp, knapsack
     * */
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];

        for (String s : strs) {
            int zero = 0, one = 0;
            for (char ch : s.toCharArray()) {
                if (ch == '0') zero++;
                else one++;
            }

            for (int i = m; i >= zero; i--) {
                for (int j = n; j >= one; j--) {
                    dp[i][j] = Math.max(dp[i][j], 1 + dp[i - zero][j - one]);
                }
            }
        }

        return dp[m][n];
    }

    /*
     * Q. 32
     *
     * Given a string containing just characters '(' and ')', find the length of the longest valid parentheses substring.
     *
     * tags:: string
     * */
    public int longestValidParentheses(String s) {
        int maxCount = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(')
                stack.push(i);
            else {
                stack.pop();
                if (stack.isEmpty())
                    stack.push(i);
                else
                    maxCount = Math.max(maxCount, i - stack.peek());
            }
        }

        return maxCount;
    }

    /*
     * Q. 1704
     *
     * You are given a string s of even length. Split this string into two halves of equal lengths, and let a be the
     * first half and b be the second half. Two strings are alike if they have the same number of vowels
     * ('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'). Notice that s contains uppercase and lowercase letters.
     * Return true if a and b are alike. Otherwise, return false.
     *
     * tags:: string
     * */
    public boolean halvesAreAlike(String s) {
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');
        int count = 0, start = 0, end = s.length() - 1;
        while (start < end) {
            if (vowels.contains(s.charAt(start++))) count++;
            if (vowels.contains(s.charAt(end--))) count--;
        }

        return count == 0;
    }

    /*
     * Q. 1047
     *
     * Given a string S of lowercase letters, a duplicate removal consists of choosing two adjacent and equal letters,
     * and removing them. We repeatedly make duplicate removals on S until we no longer can.
     * Return the final string after all such duplicate removals have been made.  It is guaranteed the answer is unique.
     *
     * tags:: string, stack
     * */
    public String removeDuplicates(String s) {
//        return removeDuplicates(s, 2);
        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == ch)
                stack.pop();
            else
                stack.push(ch);
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.reverse().toString();
    }

    /*
     * Q. 1209
     *
     * You are given a string s and an integer k, a k duplicate removal consists of choosing k adjacent and equal letters
     * from s and removing them, causing the left and the right side of the deleted substring to concatenate together.
     * We repeatedly make k duplicate removals on s until we no longer can.
     * Return the final string after all such duplicate removals have been made.
     * It is guaranteed that the answer is unique.
     *
     * tags:: string, stack
     * */
    public String removeDuplicates(String s, int k) {
        Stack<Pair<Character, Integer>> stack = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek().getLeft() == ch) {
                Pair<Character, Integer> temp = stack.pop();
                if (temp.getRight() + 1 < k)
                    stack.push(Pair.of(ch, temp.getRight() + 1));
            } else {
                stack.push(Pair.of(ch, 1));
            }
        }

        StringBuilder sb = new StringBuilder();

        while (!stack.isEmpty()) {
            Pair<Character, Integer> temp = stack.pop();
            for (int i = 0; i < temp.getRight(); i++)
                sb.append(temp.getLeft());
        }

        return sb.reverse().toString();
    }
}
