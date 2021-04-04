package org.solutions.leetcode.utils;

import org.solutions.leetcode.exceptions.BadInputException;

public class StringUtils {

    public int[] getLowercaseCharCount(String s) throws BadInputException {
        int[] count = new int[26];
        for (char ch : s.toCharArray()) {
            if (Character.isUpperCase(ch))
                throw new BadInputException("Only lowercase letter allowed");
            count[ch - 'a']++;
        }

        return count;
    }

    public int[] getUppercaseCharCount(String s) throws BadInputException {
        int[] count = new int[26];
        for (char ch : s.toCharArray()) {
            if (Character.isLowerCase(ch))
                throw new BadInputException("Only uppercase letter allowed");
            count[ch - 'A']++;
        }

        return count;
    }
}
