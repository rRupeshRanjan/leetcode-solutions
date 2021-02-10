package org.solutions.leetcode;

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
}
