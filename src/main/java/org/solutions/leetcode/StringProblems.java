package org.solutions.leetcode;

import java.util.HashMap;
import java.util.Map;

public class StringProblems {
    /*
    * Q.821
    * Given a string s and a character c that occurs in s, return an array of integers answer
    * where answer.length == s.length
    * and answer[i] is the shortest distance from s[i] to the character c in s.
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
}
