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
     * Q.821 Shortest Distance to a Character
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
     * Q.242 Valid Anagram
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
     * Q. 1647. Minimum Deletions to Make Character Frequencies Unique
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
     * 1048. Longest String Chain
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
     * 609. Find Duplicate File in System
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
     * Q. 890. Find and Replace Pattern
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

    /**
     * Q. 150. Evaluate Reverse Polish Notation
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

    private List<String> getNetLockStates(String state) {
        List<String> states = new ArrayList<>();
        int n = state.length();
        for (int i = 0; i < 4; i++) {
            states.add(state.substring(0, i) + (((state.charAt(i) - '0') + 1) % 10) + state.substring(i + 1, n));
            states.add(state.substring(0, i) + (((state.charAt(i) - '0') + 9) % 10) + state.substring(i + 1, n));
        }

        return states;
    }
}
