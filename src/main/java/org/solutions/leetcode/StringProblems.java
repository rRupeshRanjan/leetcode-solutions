package org.solutions.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        for(int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            if(ch == c) {
                result[i] = 0;
            } else {
              int lastIndex = s.lastIndexOf(c, i);
              int nextIndex = s.indexOf(c, i);

              if(lastIndex == -1 || nextIndex == -1)
                  result[i] = Math.abs((lastIndex == -1) ? nextIndex-i : lastIndex-i);
              else
                result[i] = Math.min(Math.abs(nextIndex-i), Math.abs(i-lastIndex));
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
        if(s.length() != t.length())
            return false;

        int[] count = new int[26];
        for(int i=0; i<s.length(); i++){
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }

        for(int c: count) {
            if(c != 0)
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

        for(int i=start; i<S.length(); i++) {
            char[] word = S.toCharArray();
            if(Character.isLetter(S.charAt(i))) {
                if(Character.isUpperCase(S.charAt(i))) {
                    word[i] = Character.toLowerCase(S.charAt(i));
                    findAllPermutations(String.valueOf(word), returnList, i+1);
                } else if (Character.isLowerCase(S.charAt(i))) {
                    word[i] = Character.toUpperCase(S.charAt(i));
                    findAllPermutations(String.valueOf(word), returnList, i+1);
                }
            }
        }
    }
}
