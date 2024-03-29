package org.solutions.leetcode;

import org.apache.commons.lang3.tuple.Pair;
import org.solutions.leetcode.exceptions.BadInputException;
import org.solutions.leetcode.utils.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class StringProblems {

    private final StringUtils stringUtils;

    public StringProblems() {
        this.stringUtils = new StringUtils();
    }

    /**
     * Q. 3 Longest Substring Without Repeating Characters
     * <p>
     * Given a string s, find the length of the longest substring without repeating characters.
     * <p>
     * tags:: string, twoPointer
     */
    public int lengthOfLongestSubstring(String s) {
        int start = 0, end = 0, n = s.length(), maxCount = 0;
        Set<Character> set = new HashSet<>();

        while (end < n) {
            if (!set.contains(s.charAt(end))) {
                set.add(s.charAt(end++));
                maxCount = Math.max(maxCount, set.size());
            } else {
                set.remove(s.charAt(start++));
            }
        }

        return maxCount;
    }

    /**
     * Q. 5 Longest Palindromic Substring
     * <p>
     * Given a string s, return the longest palindromic substring in s.
     * tags::string
     */
    public String longestPalindrome(String s) {
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = longestPalindromeHelper(s, i, i);
            int len2 = longestPalindromeHelper(s, i, i + 1);
            int len = Math.max(len1, len2);

            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    /**
     * Q. 20 Valid Parentheses
     * <p>
     * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']',
     * determine if the input string is valid.
     * <p>
     * An input string is valid if:
     * Open brackets must be closed by the same type of brackets.
     * Open brackets must be closed in the correct order.
     * <p>
     * tags:: string, stack
     */
    public boolean isValid(String s) {
        Map<Character, Character> map = Map.of('}', '{', ')', '(', ']', '[');
        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (map.containsKey(ch)) {
                if (stack.isEmpty() || !stack.pop().equals(map.get(ch)))
                    return false;
            } else
                stack.push(ch);
        }

        return stack.isEmpty();
    }

    /**
     * Q. 22 Generate Parentheses
     * <p>
     * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
     * <p>
     * tags:: backtracking, string, recursion
     */
    public List<String> generateParenthesis(int n) {
        List<String> answer = new ArrayList<>();
        backtrackGenerateParenthesis(new StringBuilder(), answer, 0, 0, n);
        return answer;
    }

    /**
     * Q. 32 Longest Valid Parentheses
     * <p>
     * Given a string containing just characters '(' and ')', find the length of the longest valid parentheses substring.
     * <p>
     * tags:: string
     */
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

    /**
     * Q. 49 Group Anagrams
     * <p>
     * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
     * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using
     * all the original letters exactly once.
     * <p>
     * tags:: string, hashmap
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String key = groupAnagramsGetKey(str);
            map.computeIfAbsent(key, x -> new ArrayList<>()).add(str);
        }

        return new ArrayList<>(map.values());
    }

    /**
     * Q. 71 Simplify Path
     * <p>
     * Given a string path, which is an absolute path (starting with a slash '/') to a file or directory in a
     * Unix-style file system, convert it to the simplified canonical path.
     * In a Unix-style file system, a period '.' refers to the current directory, a double period '..' refers to the
     * directory up a level, and any multiple consecutive slashes (i.e. '//') are treated as a single slash '/'.
     * For this problem, any other format of periods such as '...' are treated as file/directory names.
     * The canonical path should have the following format:
     * The path starts with a single slash '/'.
     * Any two directories are separated by a single slash '/'.
     * The path does not end with a trailing '/'.
     * The path only contains the directories on the path from the root directory to the target file or directory
     * (i.e., no period '.' or double period '..')
     * Return the simplified canonical path.
     * <p>
     * tags::stack, stringbuilder
     */
    public String simplifyPath(String path) {
        Deque<String> deque = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        for (char ch : path.toCharArray()) {
            if (ch == '/') {
                handlePaths(sb, deque);
                sb = new StringBuilder();
            } else {
                sb.append(ch);
            }
        }

        handlePaths(sb, deque);
        sb = new StringBuilder();

        while (!deque.isEmpty()) {
            sb.append("/").append(deque.pollLast());
        }

        return (sb.length() == 0) ? "/" : sb.toString();
    }

    /**
     * Q. 76 Minimum Window Substring
     * <p>
     * Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that
     * every character in t (including duplicates) is included in the window.
     * If there is no such substring, return the empty string "".
     * <p>
     * The testcases will be generated such that the answer is unique.
     * A substring is a contiguous sequence of characters within the string.
     * <p>
     * tags:: string, slidingWindow
     */
    public String minWindow(String s, String t) {
        int[] freq = new int[128];
        int counter = t.length(), start = 0, end = 0, minLength = s.length() + 1, minStart = 0;

        for (char ch : t.toCharArray())
            freq[ch]++;

        while (end < s.length()) {
            char ch1 = s.charAt(end++);
            if (freq[ch1] > 0)
                counter--;
            freq[ch1]--;

            while (counter == 0) {
                if (minLength > end - start) {
                    minLength = end - start;
                    minStart = start;
                }

                char ch2 = s.charAt(start++);
                freq[ch2]++;
                if (freq[ch2] > 0)
                    counter++;
            }
        }

        return minLength == s.length() + 1 ? "" : s.substring(minStart, minStart + minLength);
    }

    /**
     * Q. 125 Valid Palindrome
     * <p>
     * Given a string s, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
     * <p>
     * tags:: string
     */
    public boolean isValidPalindrome(String s) {
        s = s.toLowerCase().chars()
                .mapToObj(i -> (char) i)
                .filter(Character::isLetterOrDigit)
                .map(Object::toString)
                .collect(Collectors.joining());

        return s.equals(new StringBuilder(s).reverse().toString());
    }

    /**
     * Q. 139 Word Break
     * <p>
     * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a
     * space-separated sequence of one or more dictionary words.
     * <p>
     * Note that the same word in the dictionary may be reused multiple times in the segmentation.
     * <p>
     * tags:: dp, string, dynamicProgramming
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                dp[i] = dp[j] && dict.contains(s.substring(j, i));
                if (dp[i])
                    break;
            }
        }

        return dp[s.length()];
    }

    /**
     * Q. 140 Word Break II
     * <p>
     * Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence
     * where each word is a valid dictionary word. Return all such possible sentences in any order.
     * <p>
     * Note that the same word in the dictionary may be reused multiple times in the segmentation.
     * <p>
     * tags:: string, bfs
     */
    public List<String> wordBreakII(String s, List<String> wordDict) {
        List<String> results = new ArrayList<>();
        Queue<Pair<String, Integer>> q = new LinkedList<>();
        Set<String> wordSet = new HashSet<>(wordDict);

        q.offer(Pair.of("", 0));
        while (!q.isEmpty()) {
            Pair<String, Integer> curr = q.remove();

            if (curr.getRight() == s.length()) {
                results.add(curr.getLeft());
                continue;
            }

            for (int i = curr.getRight() + 1; i <= s.length(); i++) {
                String currWord = curr.getLeft();
                String nextWord = s.substring(curr.getRight(), i);

                if (wordSet.contains(nextWord)) {
                    q.offer(Pair.of(
                            (Objects.equals(currWord, "")) ? nextWord : currWord + " " + nextWord, i
                    ));
                }
            }
        }

        return results;
    }

    /**
     * Q. 150 Evaluate Reverse Polish Notation
     * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
     * Valid operators are +, -, *, and /. Each operand may be an integer or another expression.
     * <p>
     * Note that division between two integers should truncate toward zero.
     * <p>
     * It is guaranteed that the given RPN expression is always valid. That means the expression would always evaluate
     * to a result, and there will not be any division by zero operation.
     * <p>
     * tags:: string, stack
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        stack.push(Integer.parseInt(tokens[0]));
        int a = 0, b = 0;

        for (int i = 1; i < tokens.length; i++) {
            switch (tokens[i]) {
                case "+": {
                    a = stack.pop();
                    b = stack.pop();
                    stack.push(b + a);
                    break;
                }
                case "-": {
                    a = stack.pop();
                    b = stack.pop();
                    stack.push(b - a);
                    break;
                }
                case "*": {
                    a = stack.pop();
                    b = stack.pop();
                    stack.push(b * a);
                    break;
                }
                case "/": {
                    a = stack.pop();
                    b = stack.pop();
                    stack.push(b / a);
                    break;
                }
                default:
                    stack.push(Integer.parseInt(tokens[i]));
                    break;
            }
        }

        return stack.pop();
    }

    /**
     * Q. 151 Reverse Words in a String
     * <p>
     * Given an input string s, reverse the order of the words.
     * A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
     * <p>
     * Return a string of the words in reverse order concatenated by a single space.
     * <p>
     * Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string
     * should only have a single space separating the words. Do not include any extra spaces.
     * <p>
     * tags:: string, stack
     */
    public String reverseWords(String s) {
        List<String> list = Arrays.asList(s.trim().split("\\s+"));
        Collections.reverse(list);

        StringBuilder sb = new StringBuilder();
        list.forEach(st -> sb.append(st).append(" "));

        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    /**
     * Q. 159 Longest Substring with At Most Two Distinct Characters
     * <p>
     * Given a string s, return the length of the longest substring that contains at most two distinct characters.
     * <p>
     * tags:: string, slidingWindow
     */
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int[] map = new int[128];
        int start = 0, end = 0, counter = 0, maxLength = 0;

        while (end < s.length()) {
            char ch1 = s.charAt(end++);
            map[ch1]++;
            if (map[ch1] == 1)
                counter++;

            while (counter > 2) {
                char ch2 = s.charAt(start++);
                map[ch2]--;
                if (map[ch2] == 0)
                    counter--;
            }
            maxLength = Math.max(maxLength, end - start);
        }

        return maxLength;
    }

    /**
     * Q. 161 One Edit Distance
     * Given two strings s and t, return true if they are both one edit distance apart, otherwise return false.
     * A string s is said to be one distance apart from a string t if you can:
     * - Insert exactly one character into s to get t.
     * - Delete exactly one character from s to get t.
     * - Replace exactly one character of s with a different character to get t.
     * <p>
     * tags:: string
     */
    public boolean isOneEditDistance(String s, String t) {
        if (Math.abs(s.length() - t.length()) > 1 || s.equals(t))
            return false;

        if (s.length() < t.length())
            return isOneEditDistance(t, s);

        int i = 0, j = 0, count = 0;
        if (s.length() == t.length()) {
            while (i < s.length() && j < t.length()) {
                if (s.charAt(i++) != t.charAt(j++)) {
                    if (count == 0)
                        count++;
                    else
                        return false;
                }
            }
        } else {
            while (i < s.length() && j < t.length()) {
                if (s.charAt(i++) != t.charAt(j)) {
                    if (count == 0)
                        count++;
                    else
                        return false;
                } else {
                    j++;
                }
            }
        }
        return true;
    }

    /**
     * Q. 186 Reverse Words in a String II
     * <p>
     * Given a character array s, reverse the order of the words.
     * A word is defined as a sequence of non-space characters. The words in s will be separated by a single space.
     * <p>
     * Your code must solve the problem in-place, i.e. without allocating extra space.
     * <p>
     * tags:: string, array
     */
    public void reverseWordsII(char[] s) {
        int start = 0, end = 0;
        reverseCharacterArray(s, 0, s.length - 1);

        while (end < s.length) {
            if (s[end] == ' ') {
                reverseCharacterArray(s, start, end - 1);
                start = end + 1;
            }
            end++;
        }

        reverseCharacterArray(s, start, s.length - 1);
    }

    /**
     * Q. 242 Valid Anagram
     * <p>
     * Given two strings s and t , write a function to determine if t is an anagram of s.
     * <p>
     * Tags:: hashmap, string
     */
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

    /**
     * Q. 299 Bulls and Cows
     * <p>
     * You are playing the Bulls and Cows game with your friend.
     * <p>
     * You write down a secret number and ask your friend to guess what the number is.
     * When your friend makes a guess, you provide a hint with the following info:
     * <p>
     * The number of "bulls", which are digits in guess that are in correct position.
     * The number of "cows", which are digits in guess that are in your secret number but are located in wrong position.
     * Specifically, the non-bull digits in the guess that could be rearranged such that they become bulls.
     * <p>
     * Given the secret number secret and your friend's guess guess, return the hint for your friend's guess.
     * <p>
     * The hint should be formatted as "xAyB", where x is the number of bulls and y is the number of cows.
     * Note that both secret and guess may contain duplicate digits.
     * <p>
     * tags:: string
     */
    public String getHint(String secret, String guess) {
        int bulls = 0, cows = 0;
        int[] arr = new int[10];

        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                bulls++;
            } else {
                if (arr[secret.charAt(i) - '0']++ < 0) cows++;
                if (arr[guess.charAt(i) - '0']-- > 0) cows++;
            }
        }

        return bulls + "A" + cows + "B";
    }

    /**
     * Q. 340 Longest Substring with At Most K Distinct Characters
     * <p>
     * Given a string s and an integer k,
     * return the length of the longest substring of s that contains at most k distinct characters.
     * <p>
     * tags:: string, slidingWindow
     */
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int[] freq = new int[256];
        int counter = 0, maxLen = 0, start = 0, end = 0;

        while (end < s.length()) {
            char ch1 = s.charAt(end++);
            if (freq[ch1] == 0)
                counter++;
            freq[ch1]++;

            maxLen = Math.max(maxLen, end - start - 1);
            while (counter > k) {
                char ch2 = s.charAt(start++);
                freq[ch2]--;
                if (freq[ch2] == 0)
                    counter--;
            }
        }

        return Math.max(maxLen, end - start);
    }

    /**
     * Q. 344 Reverse String
     * <p>
     * Write a function that reverses a string. The input string is given as an array of characters s.
     * <p>
     * tags::string
     */
    public void reverseString(char[] str) {
        int start = 0, end = str.length - 1;

        while (start < end) {
            char temp = str[end];
            str[end--] = str[start];
            str[start++] = temp;
        }
    }

    /**
     * Q. 394 Decode String
     * <p>
     * Given an encoded string, return its decoded string.
     * The encoding rule is:
     * k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.
     * Note that k is guaranteed to be a positive integer.
     * <p>
     * You may assume that the input string is always valid, No extra white spaces, square brackets are well-formed, etc.
     * <p>
     * Furthermore, you may assume that the original data does not contain any digits and that digits are
     * only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].
     * <p>
     * tags:: string, stack
     */
    public String decodeString(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> stringStack = new Stack<>();
        StringBuilder res = new StringBuilder();
        int i = 0;

        while (i < s.length()) {
            if (Character.isDigit(s.charAt(i))) {
                int num = 0;
                while (Character.isDigit(s.charAt(i))) {
                    num = num * 10 + (s.charAt(i) - '0');
                    i++;
                }
                countStack.push(num);
            } else if (s.charAt(i) == '[') {
                stringStack.push(res);
                res = new StringBuilder();
                i++;
            } else if (s.charAt(i) == ']') {
                StringBuilder temp = new StringBuilder(stringStack.pop());
                int count = countStack.pop();
                temp.append(String.valueOf(res).repeat(Math.max(0, count)));
                res = temp;
                i++;
            } else {
                res.append(s.charAt(i++));
            }
        }

        return res.toString();
    }

    /**
     * Q. 415 Add Strings
     * <p>
     * Given two non-negative integers, num1 and num2 represented as string, return the sum of num1 and num2 as a string.
     * <p>
     * You must solve the problem without using any built-in library for handling large integers (such as BigInteger).
     * You must also not convert the inputs to integers directly.
     * <p>
     * tags:: string, addition
     */
    public String addStrings(String num1, String num2) {
        int carry = 0;
        int l1 = num1.length() - 1, l2 = num2.length() - 1;
        StringBuilder sb = new StringBuilder();

        while (l1 >= 0 || l2 >= 0) {
            int sum = carry;
            if (l1 >= 0)
                sum += (num1.charAt(l1--) - '0');

            if (l2 >= 0)
                sum += (num2.charAt(l2--) - '0');

            carry = sum / 10;
            sum = sum % 10;
            sb.append(sum);
        }

        if (carry != 0)
            sb.append(carry);

        return sb.reverse().toString();
    }

    /**
     * Q. 418 Sentence Screen Fitting
     * <p>
     * Given a rows x cols screen and a sentence represented as a list of strings, return the number of times the given
     * sentence can be fitted on the screen. The order of words in the sentence must remain unchanged, and a word
     * cannot be split into two lines. A single space must separate two consecutive words in a line.
     * <p>
     * tags:: string
     */
    public int sentenceScreenFitting(String[] sentence, int rows, int cols) {
        String completeString = String.join(" ", sentence) + " ";
        int start = 0, len = completeString.length();
        while (rows > 0) {
            start += cols;
            if (completeString.charAt(start % len) == ' ')
                start++;
            else {
                while (start < 0 && completeString.charAt((start - 1) % len) != ' ')
                    start--;
            }
            rows--;
        }

        return start / completeString.length();
    }

    /**
     * Q. 451 Sort Characters By Frequency
     * <p>
     * Given a string s, sort it in decreasing order based on the frequency of the characters.
     * The frequency of a character is the number of times it appears in the string.
     * Return the sorted string. If there are multiple answers, return any of them.
     * <p>
     * tags::string, hashmap, bucket-sort
     */
    public String frequencySort(String s) {
        List<Character>[] chars = new ArrayList[s.length() + 1];
        Map<Character, Integer> count = new HashMap<>();
        StringBuilder sb = new StringBuilder();

        for (char ch : s.toCharArray())
            count.put(ch, count.getOrDefault(ch, 0) + 1);

        for (char ch : count.keySet()) {
            int index = count.get(ch);
            if (chars[index] == null)
                chars[index] = new ArrayList<>();
            chars[index].add(ch);
        }

        for (int i = s.length(); i >= 0; i--) {
            if (chars[i] != null) {
                for (char ch : chars[i]) {
                    for (int j = 0; j < i; j++)
                        sb.append(ch);
                }
            }
        }

        return sb.toString();
    }

    /**
     * Q. 474 Ones and Zeroes
     * <p>
     * You are given an array of binary strings strs and two integers m and n.
     * Return the size of the largest subset of strs such that there are at most m 0's and n 1's in the subset.
     * A set x is a subset of a set y if all elements of x are also elements of y.
     * <p>
     * tags:: string, dp, knapsack
     */
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

    /**
     * Q. 482 License Key Formatting
     * <p>
     * You are given a license key represented as a string s that consists of only alphanumeric characters and dashes.
     * The string is separated into n + 1 groups by n dashes. You are also given an integer k.
     * <p>
     * We want to reformat the string s such that each group contains exactly k characters, except for the first group,
     * which could be shorter than k but still must contain at least one character. Furthermore, there must be a dash
     * inserted between two groups, and you should convert all lowercase letters to uppercase.
     * <p>
     * Return the reformatted license key.
     * <p>
     * tags:: string
     */
    public String licenseKeyFormatting(String s, int k) {
        StringBuilder sb = new StringBuilder();
        int alphabetCount = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '-')
                continue;

            if (alphabetCount == k) {
                sb.append("-");
                alphabetCount = 0;
            }

            sb.append(Character.toUpperCase(s.charAt(i)));
            alphabetCount++;
        }

        sb.reverse();
        return sb.toString();
    }

    /**
     * Q. 520 Detect Capital
     * <p>
     * We define the usage of capitals in a word to be right when one of the following cases holds:
     * <p>
     * All letters in this word are capitals, like "USA".
     * All letters in this word are not capitals, like "leetcode".
     * Only the first letter in this word is capital, like "Google".
     * Given a string word, return true if the usage of capitals in it is right.
     * <p>
     * tags:: string
     */
    public boolean detectCapitalUse(String word) {
        int count = 0;
        for (char ch : word.toCharArray()) {
            if ((int) ch >= 65 && (int) ch <= 90)
                count++;
        }

        if (count == word.length() || count == 0)
            return true;
        else return count == 1 && (int) word.charAt(0) >= 65 && (int) word.charAt(0) <= 90;
    }

    /**
     * Q. 609 Find Duplicate File in System
     * Given a list paths of directory info, including the directory path, and all the files with contents in this
     * directory, return all the duplicate files in the file system in terms of their paths. You may return the answer
     * in any order. A group of duplicate files consists of at least two files that have the same content.
     * A single directory info string in the input list has the following format:
     * "root/d1/d2/.../dm f1.txt(f1_content) f2.txt(f2_content) ... fn.txt(fn_content)"
     * It means there are n files (f1.txt, f2.txt ... fn.txt) with content (f1_content, f2_content ... fn_content)
     * respectively in the directory "root/d1/d2/.../dm".
     * Note that n >= 1 and m >= 0. If m = 0, it means the directory is just the root directory.
     * <p>
     * The output is a list of groups of duplicate file paths. For each group, it contains all the file paths of the
     * files that have the same content. A file path is a string that has the following format:
     * "directory_path/file_name.txt"
     * <p>
     * tags::string, hashmap
     */
    public List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<String>> map = new HashMap<>();
        for (String path : paths) {
            String[] str = path.split(" ");
            for (int i = 1; i < str.length; i++) {
                String[] temp = str[i].split("\\(");
                String filename = str[0] + "/" + temp[0];
                String content = temp[1];
                map.computeIfAbsent(content, s -> new ArrayList<>()).add(filename);
            }
        }

        return map.values().stream().filter(v -> v.size() > 1).collect(Collectors.toList());
    }

    /**
     * Q. 647 Palindromic Substrings
     * <p>
     * Given a string, your task is to count how many palindromic substrings in this string.
     * The substrings with different start indexes or end indexes are counted as
     * different substrings even they consist of same characters.
     * <p>
     * tags:: palindrome, strings
     */
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

    /**
     * Q. 678 Valid Parenthesis String
     * <p>
     * Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.
     * The following rules define a valid string:
     * 1. Any left parenthesis '(' must have a corresponding right parenthesis ')'.
     * 2. Any right parenthesis ')' must have a corresponding left parenthesis '('.
     * 3. Left parenthesis '(' must go before the corresponding right parenthesis ')'.
     * 4. '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".
     * <p>
     * tags::string, two-pass, braces
     */
    public boolean checkValidString(String s) {
        int leftBalance = 0, rightBalance = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(' || ch == '*')
                leftBalance++;
            else
                leftBalance--;
            if (leftBalance < 0)
                return false;
        }

        if (leftBalance == 0)
            return true;

        for (int i = s.length() - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            if (ch == ')' || ch == '*')
                rightBalance++;
            else
                rightBalance--;

            if (rightBalance < 0)
                return false;
        }

        return true;
    }

    /**
     * Q. 680 Valid Palindrome II
     * <p>
     * Given a string s, return true if the s can be palindrome after deleting at most one character from it.
     * <p>
     * tags:: string, twoPointer
     */
    public boolean isValidPalindromeII(String s) {
        for (int i = 0, j = s.length() - 1; i < s.length() && j >= 0; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                int j2 = j - 1, i2 = i + 1;

                while (i < j2 && s.charAt(i) == s.charAt(j2)) {
                    i++;
                    j2--;
                }

                while (i2 < j && s.charAt(i2) == s.charAt(j)) {
                    i2++;
                    j--;
                }

                return (i >= j2 || i2 >= j);
            }
        }
        return true;
    }

    /**
     * Q. 696 Count Binary Substrings
     * <p>
     * Give a binary string s, return the number of non-empty substrings that have the same number of 0's and 1's,
     * and all the 0's and all the 1's in these substrings are grouped consecutively.
     * Substrings that occur multiple times are counted the number of times they occur.
     * <p>
     * tags:: string
     */
    public int countBinarySubstrings(String s) {
        int result = 0, prev = 0, curr = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != s.charAt(i - 1)) {
                result += Math.min(prev, curr);
                prev = curr;
                curr = 1;
            } else {
                curr++;
            }
        }

        return result + Math.min(prev, curr);
    }

    /**
     * Q. 752 Open the Lock
     * You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5',
     * '6', '7', '8', '9'. The wheels can rotate freely and wrap around: for example we can turn '9' to be '0',
     * or '0' to be '9'. Each move consists of turning one wheel one slot.
     * <p>
     * The lock initially starts at '0000', a string representing the state of the 4 wheels.
     * <p>
     * You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the
     * lock will stop turning and you will be unable to open it.
     * <p>
     * Given a target representing the value of the wheels that will unlock the lock, return the minimum total number
     * of turns required to open the lock, or -1 if it is impossible.
     * <p>
     * tags:: bfs, string
     */
    public int openLock(String[] deadends, String target) {
        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>(Arrays.asList(deadends));
        int level = 0;

        if (visited.contains("0000"))
            return -1;
        q.offer(target);
        while (!q.isEmpty()) {
            for (int i = q.size(); i > 0; i--) {
                String currState = q.poll();
                if (!visited.add(currState)) continue;
                if (currState.equals("0000")) return level;

                for (String state : getNetLockStates(currState)) {
                    if (!visited.contains(state)) {
                        q.offer(state);
                    }
                }
            }
            level++;
        }

        return -1;
    }

    /**
     * Q. 784 Letter Case Permutation
     * <p>
     * Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.
     * Return a list of all possible strings we could create. You can return the output in any order.
     * <p>
     * Tags:: recursion, string
     */
    public List<String> letterCasePermutation(String S) {
        List<String> returnList = new ArrayList<>();

        findAllPermutations(S, returnList, 0);
        return returnList;
    }

    /**
     * Q. 792 Number of Matching Subsequences
     * <p>
     * Given a string s and an array of strings words, return the number of words[i] that is a subsequence of s.
     * <p>
     * A subsequence of a string is a new string generated from the original string with some characters (can be none) 4
     * deleted without changing the relative order of the remaining characters.
     * <p>
     * For example, "ace" is a subsequence of "abcde".
     * <p>
     * tags:: string
     */
    public int numMatchingSubseq(String s, String[] words) {
        List<String>[] str = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            str[i] = new ArrayList<>();
        }

        for (String word : words) {
            str[word.charAt(0) - 'a'].add(word);
        }

        int count = 0;
        for (char ch : s.toCharArray()) {
            List<String> starting = str[ch - 'a'];
            str[ch - 'a'] = new ArrayList<>();
            for (String st : starting) {
                if (st.length() == 1)
                    count++;
                else {
                    String rem = st.substring(1);
                    str[rem.charAt(0) - 'a'].add(rem);
                }
            }
        }

        return count;
    }

    /**
     * Q. 820 Short Encoding of Words
     * <p>
     * A valid encoding of an array of words is any reference string s and array of indices indices such that:
     * words.length == indices.length. The reference string s ends with the '#' character.
     * For each index indices[i], the substring of s starting from indices[i] and up to (but not including)
     * the next '#' character is equal to words[i]. Given an array of words, return the length of the shortest reference
     * string s possible of any valid encoding of words.
     * <p>
     * tags:: string, TrieNode
     *
     * @see org.solutions.leetcode.TrieProblems#minimumLengthEncoding(String[])
     */
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

    /**
     * Q. 821 Shortest Distance to a Character
     * <p>
     * Given a string s and a character c that occurs in s, return an array of integers answer where
     * answer.length == s.length
     * answer[i] is the shortest distance from s[i] to the character c in s.
     * <p>
     * Tags:: string
     */
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

    /**
     * Q. 890 Find and Replace Pattern
     * Given a list of strings words and a string pattern, return a list of words[i] that match pattern. You may return
     * the answer in any order. A word matches the pattern if there exists a permutation of letters p so that after
     * replacing every letter x in the pattern with p(x), we get the desired word.
     * Recall that a permutation of letters is a bijection from letters to letters: every letter maps to another letter,
     * and no two letters map to the same letter.
     * <p>
     * tags:: string, pattern-finding
     */
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> result = new ArrayList<>();
        int[] p = normalizePattern(pattern);

        for (String word : words) {
            if (Arrays.equals(normalizePattern(word), p))
                result.add(word);
        }

        return result;
    }

    /**
     * Q. 916 Word Subsets
     * <p>
     * We are given two arrays A and B of words.  Each word is a string of lowercase letters.
     * Now, say that word b is a subset of word a if every letter in b occurs in a, including multiplicity.
     * For example, "wrr" is a subset of "warrior", but is not a subset of "world".
     * Now say a word a from A is universal if for every b in B, b is a subset of a.
     * Return a list of all universal words in A.  You can return the words in any order.
     * <p>
     * tags:: string, superset, count
     */
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

    /**
     * Q. 921 Minimum Add to Make Parentheses Valid
     * <p>
     * A parentheses string is valid if and only if:
     * It is the empty string,
     * It can be written as AB (A concatenated with B), where A and B are valid strings, or
     * It can be written as (A), where A is a valid string.
     * You are given a parentheses string s. In one move, you can insert a parenthesis at any position of the string.
     * For example,
     * if s = "()))", you can insert an opening parenthesis to be "(()))" or a closing parenthesis to be "())))".
     * Return the minimum number of moves required to make s valid.
     * <p>
     * Tags::string, parenthesis
     */
    public int minAddToMakeValid(String s) {
        int count = 0, score = 0;
        for (char ch : s.toCharArray()) {
            score = (ch == '(') ? score + 1 : score - 1;
            if (score < 0) {
                count++;
                score = 0;
            }
        }

        return count + score;
    }

    /**
     * Q. 926 Flip String to Monotone Increasing
     * <p>
     * A binary string is monotone increasing if it consists of some number of 0's (possibly none),
     * followed by some number of 1's (also possibly none).
     * <p>
     * You are given a binary string s. You can flip s[i] changing it from 0 to 1 or from 1 to 0.
     * Return the minimum number of flips to make s monotone increasing.
     * <p>
     * tags:: string
     */
    public int minFlipsMonoIncreasing(String s) {
        if (s == null || s.length() == 0)
            return 0;

        int flips = 0, ones = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '0')
                flips++;
            else
                ones++;
            flips = Math.min(ones, flips);
        }

        return flips;
    }

    /**
     * Q. 929 Unique Email Addresses
     * <p>
     * Every valid email consists of a local name and a domain name, separated by the '@' sign.
     * Besides lowercase letters, the email may contain one or more '.' or '+'.
     * <p>
     * For example, in "alice@leetcode.com", "alice" is the local name, and "leetcode.com" is the domain name.
     * If you add periods '.' between some characters in the local name part of an email address, mail sent there will
     * be forwarded to the same address without dots in local name. Note that this rule does not apply to domain names.
     * <p>
     * For example, "alice.z@leetcode.com" and "alicez@leetcode.com" forward to the same email address.
     * If you add a plus '+' in the local name, everything after the first plus sign will be ignored.
     * This allows certain emails to be filtered. Note that this rule does not apply to domain names.
     * <p>
     * For example, "m.y+name@email.com" will be forwarded to "my@email.com".
     * It is possible to use both of these rules at the same time.
     * <p>
     * Given an array of strings emails where we send one email to each email[i],
     * return the number of different addresses that actually receive mails.
     * <p>
     * tags:: string
     */
    public int uniqueEmailAddresses(String[] emails) {
        Set<String> uniqueEmailAddress = new HashSet<>();
        for (String email : emails) {

            // [user, domain]
            String[] split = email.split("@");
            String user = split[0].split("\\+")[0].replaceAll("\\.", "");

            uniqueEmailAddress.add(user + "@" + split[1]);
        }

        return uniqueEmailAddress.size();
    }

    /**
     * Q. 1041 Robot Bounded In Circle
     * On an infinite plane, a robot initially stands at (0, 0) and faces north.
     * The robot can receive one of three instructions:
     * "G": go straight 1 unit;
     * "L": turn 90 degrees to the left;
     * "R": turn 90 degrees to the right.
     * The robot performs the instructions given in order, and repeats them forever.
     * <p>
     * Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.
     * <p>
     * tags:: string, math
     */
    public boolean isRobotBounded(String instructions) {
        // north - 0, east - 1, south - 2, west - 3
        int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int index = 0, x = 0, y = 0;

        for (char ch : instructions.toCharArray()) {
            if (ch == 'L')
                index = (index + 1) % 4;
            else if (ch == 'R')
                index = (index + 3) % 4;
            else {
                x += dirs[index][0];
                y += dirs[index][1];
            }
        }

        return (x == 0 && y == 0) || index != 0;
    }

    /**
     * Q. 1047 Remove All Adjacent Duplicates In String
     * <p>
     * Given a string S of lowercase letters, a duplicate removal consists of choosing two adjacent and equal letters,
     * and removing them. We repeatedly make duplicate removals on S until we no longer can.
     * Return the final string after all such duplicate removals have been made.  It is guaranteed the answer is unique.
     * <p>
     * tags:: string, stack
     */
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

    /**
     * Q. 1048 Longest String Chain
     * <p>
     * Given a list of words, each word consists of English lowercase letters. Let's say word1 is a predecessor of word2
     * if and only if we can add exactly one letter anywhere in word1 to make it equal to word2.
     * For example, "abc" is a predecessor of "abac".
     * A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, where word_1 is a predecessor of
     * word_2, word_2 is a predecessor of word_3, and so on. Return the longest possible length of a word chain with
     * words chosen from the given list of words.
     * <p>
     * tags::string, dp
     */
    public int longestStrChain(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        int max = 0;

        Arrays.sort(words, Comparator.comparing(String::length));
        for (String word : words) {
            int curr = 0;
            for (int i = 0; i < word.length(); i++) {
                String key = word.substring(0, i) + word.substring(i + 1);
                curr = Math.max(curr, map.getOrDefault(key, 0) + 1);
            }
            map.put(word, curr);
            max = Math.max(max, curr);
        }

        return max;
    }

    /**
     * Q. 1209 Remove All Adjacent Duplicates in String II
     * <p>
     * You are given a string s and an integer k, a k duplicate removal consists of choosing k adjacent and equal letters
     * from s and removing them, causing the left and the right side of the deleted substring to concatenate together.
     * We repeatedly make k duplicate removals on s until we no longer can.
     * Return the final string after all such duplicate removals have been made.
     * It is guaranteed that the answer is unique.
     * <p>
     * tags:: string, stack
     */
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

    /**
     * Q. 1233 Remove Sub-Folders from the Filesystem
     * <p>
     * Given a list of folders, remove all sub-folders in those folders and return in any order the folders after
     * removing. If a folder[i] is located within another folder[j], it is called a sub-folder of it. The format of a
     * path is one or more concatenated strings of the form: / followed by one or more lowercase English letters.
     * For example, /leetcode and /leetcode/problems are valid paths while an empty string and / are not.
     * <p>
     * tags:: string, sorting
     */
    public List<String> removeSubfolders(String[] folder) {
        List<String> list = new ArrayList<>();
        Arrays.sort(folder);

        for (String f : folder) {
            if (list.isEmpty() || !f.startsWith(list.get(list.size() - 1) + "/"))
                list.add(f);
        }

        return list;
    }

    /**
     * Q. 1249 Minimum Remove to Make Valid Parentheses
     * Given a string s of '(' , ')' and lowercase English characters.
     * <p>
     * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions )
     * so that the resulting parentheses string is valid and return any valid string.
     * <p>
     * Formally, a parentheses string is valid if and only if:
     * <p>
     * It is the empty string, contains only lowercase characters, or
     * It can be written as AB (A concatenated with B), where A and B are valid strings, or
     * It can be written as (A), where A is a valid string.
     * <p>
     * tags:: string, stack
     */
    public String minRemoveToMakeValid(String s) {
        Stack<Integer> stack = new Stack<>();
        Set<Integer> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(')
                stack.add(i);
            else if (ch == ')') {
                if (stack.isEmpty())
                    set.add(i);
                else
                    stack.pop();
            }
        }

        while (!stack.isEmpty()) {
            set.add(stack.pop());
        }

        for (int i = 0; i < s.length(); i++) {
            if (!set.contains(i))
                sb.append(s.charAt(i));
        }

        return sb.toString();
    }

    /**
     * Q. 1461 Check If a String Contains All Binary Codes of Size K
     * <p>
     * Given a binary string s and an integer k.
     * Return True if every binary code of length k is a substring of s. Otherwise, return False.
     * <p>
     * tags: string, hashing, maths
     */
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

    /**
     * Q. 1525 Number of Good Ways to Split a String
     * <p>
     * You are given a string s, a split is called good if you can split s into 2 non-empty strings p and q where its
     * concatenation is equal to s and the number of distinct letters in p and q are the same.
     * <p>
     * Return the number of good splits you can make in s.
     * <p>
     * tags:: string
     */
    public int numSplits(String s) {
        int[] leftFreq = new int[26], rightFreq = new int[26];
        int answer = 0, leftCount = 0, rightCount = 0, n = s.length();

        for (char ch : s.toCharArray()) {
            rightFreq[ch - 'a']++;
            if (rightFreq[ch - 'a'] == 1)
                rightCount++;
        }

        for (int i = 0; i < n - 1; i++) {
            int index = s.charAt(i) - 'a';

            if (++leftFreq[index] == 1) leftCount++;
            if (--rightFreq[index] == 0) rightCount--;

            if (leftCount == rightCount)
                answer++;
        }

        return answer;
    }

    /**
     * Q. 1529 Bulb Switcher IV
     * <p>
     * There is a room with n bulbs, numbered from 0 to n - 1, arranged in a row from left to right. Initially,
     * all the bulbs are turned off. Your task is to obtain the configuration represented by target where target[i] is
     * '1' if the ith bulb is turned on and is '0' if it is turned off. You have a switch to flip the state of the bulb,
     * a flip operation is defined as follows:
     * Choose any bulb (index i) of your current configuration.
     * Flip each bulb from index i to index n - 1.
     * When any bulb is flipped it means that if it is '0' it changes to '1' and if it is '1' it changes to '0'.
     * <p>
     * Return the minimum number of flips required to form target.
     * <p>
     * tags:: string
     */
    public int bulbSwitcherIV(String target) {
        int count = 0;
        char prev = '0';

        for (char c : target.toCharArray()) {
            if (c != prev) {
                prev = c;
                count++;
            }
        }

        return count;
    }

    /**
     * Q. 1647 Minimum Deletions to Make Character Frequencies Unique
     * <p>
     * A string s is called good if there are no two different characters in s that have the same frequency.
     * Given a string s, return the minimum number of characters you need to delete to make s good.
     * The frequency of a character in a string is the number of times it appears in the string.
     * For example, in the string "aab", the frequency of 'a' is 2, while the frequency of 'b' is 1.
     * <p>
     * tags:: string, greedy
     */
    public int minDeletions(String s) {
        int[] count = new int[26];
        int deletionCount = 0, prevGoodFreq;
        for (char ch : s.toCharArray())
            count[ch - 'a']++;

        Arrays.sort(count);
        prevGoodFreq = count[25];
        for (int i = 25; i >= 0; i--) {
            if (count[i] == 0)
                break;

            if (count[i] > prevGoodFreq)
                deletionCount += count[i] - prevGoodFreq;
            else
                prevGoodFreq = count[i];

            prevGoodFreq = Math.max(prevGoodFreq - 1, 0);
        }
        return deletionCount;
    }

    /**
     * Q. 1689 Partitioning Into Minimum Number Of Deci-Binary Numbers
     * <p>
     * A decimal number is called deci-binary if each of its digits is either 0 or 1 without any leading zeros.
     * For example, 101 and 1100 are deci-binary, while 112 and 3001 are not.
     * <p>
     * Given a string n that represents a positive decimal integer, return the minimum number of positive deci-binary
     * numbers needed so that they sum up to n.
     * <p>
     * tags:: string, greedy
     */
    public int minPartitions(String n) {
        int count = 0;
        for (int i = 0; i < n.length(); i++) {
            count = Math.max(count, n.charAt(i) - '0');
            if (count == 9)
                break;
        }

        return count;
    }

    /**
     * Q. 1704 Determine if String Halves Are Alike
     * <p>
     * You are given a string s of even length. Split this string into two halves of equal lengths, and let a be the
     * first half and b be the second half. Two strings are alike if they have the same number of vowels
     * ('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'). Notice that s contains uppercase and lowercase letters.
     * Return true if a and b are alike. Otherwise, return false.
     * <p>
     * tags:: string
     */
    public boolean halvesAreAlike(String s) {
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');
        int count = 0, start = 0, end = s.length() - 1;
        while (start < end) {
            if (vowels.contains(s.charAt(start++))) count++;
            if (vowels.contains(s.charAt(end--))) count--;
        }

        return count == 0;
    }

    /**
     * Q. 2138. Divide a String Into Groups of Size k
     * <p>
     * A string s can be partitioned into groups of size k using the following procedure:
     * <p>
     * The first group consists of the first k characters of the string, the second group consists of the next k
     * characters of the string, and so on. Each character can be a part of exactly one group. For the last group, if
     * the string does not have k characters remaining, a character fill is used to complete the group.
     * Note that the partition is done so that after removing the fill character from the last group (if it exists) and
     * concatenating all the groups in order, the resultant string should be s.
     * <p>
     * Given the string s, the size of each group k and the character fill, return a string array denoting the
     * composition of every group s has been divided into, using the above procedure.
     * <p>
     * tags::string
     */
    public String[] divideString(String s, int k, char fill) {
        int len = (int) Math.ceil(s.length() / (float) k);
        String[] answer = new String[len];
        int currIndex = 0;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (sb.length() == k) {
                answer[currIndex++] = sb.toString();
                sb = new StringBuilder();
            }
            sb.append(s.charAt(i));
        }

        while (sb.length() < k) {
            sb.append(fill);
        }

        if (sb.length() > 0)
            answer[currIndex++] = sb.toString();

        return answer;
    }

    private int longestPalindromeHelper(String s, int start, int end) {
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            start--;
            end++;
        }

        return end - start - 1;
    }

    private String groupAnagramsGetKey(String word) {
        char[] freq = new char[26];
        StringBuilder key = new StringBuilder();
        for (char ch : word.toCharArray())
            freq[ch - 'a']++;

        for (int i = 0; i < 26; i++) {
            if (freq[i] > 0) {
                key.append(i + 'a').append(freq[i]);
            }
        }

        return key.toString();
    }

    private void handlePaths(StringBuilder sb, Deque<String> deque) {
        if (sb.length() == 0 || sb.toString().equals("."))
            return;
        else if (sb.toString().equals(".."))
            deque.pollFirst();
        else
            deque.addFirst(sb.toString());
    }

    private void reverseCharacterArray(char[] s, int start, int end) {
        while (start < end) {
            char temp = s[start];
            s[start++] = s[end];
            s[end--] = temp;
        }
    }

    private List<String> getNetLockStates(String state) {
        List<String> states = new ArrayList<>();
        int n = state.length();
        for (int i = 0; i < 4; i++) {
            states.add(state.substring(0, i) + (((state.charAt(i) - '0') + 1) % 10) + state.substring(i + 1, n));
            states.add(state.substring(0, i) + (((state.charAt(i) - '0') + 9) % 10) + state.substring(i + 1, n));
        }

        return states;
    }

    private void backtrackGenerateParenthesis(StringBuilder sb, List<String> ans, int open, int close, int max) {
        if (sb.length() == 2 * max) {
            ans.add(sb.toString());
            return;
        }

        if (open < max) {
            sb.append('(');
            backtrackGenerateParenthesis(sb, ans, open + 1, close, max);
            sb.deleteCharAt(sb.length() - 1);
        }

        if (close < open) {
            sb.append(')');
            backtrackGenerateParenthesis(sb, ans, open, close + 1, max);
            sb.deleteCharAt(sb.length() - 1);
        }

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

    private int[] normalizePattern(String w) {
        int n = w.length();
        int[] p = new int[n];
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.putIfAbsent(w.charAt(i), map.size());
            p[i] = map.get(w.charAt(i));
        }

        return p;
    }
}
