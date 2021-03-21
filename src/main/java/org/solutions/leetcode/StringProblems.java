package org.solutions.leetcode;

import java.util.*;

public class StringProblems {
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
}
