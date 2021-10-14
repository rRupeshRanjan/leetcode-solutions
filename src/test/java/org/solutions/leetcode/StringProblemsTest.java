package org.solutions.leetcode;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.solutions.leetcode.exceptions.BadInputException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StringProblemsTest {

    static StringProblems stringProblems;

    @BeforeAll
    static void setUp() {
        stringProblems = new StringProblems();
    }

    @Test
    void testShortestToChar() {
        Map<Pair<String, Character>, int[]> scenarios = new HashMap<>();

        scenarios.put(Pair.of("loveleetcode", 'e'), new int[]{3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0});
        scenarios.put(Pair.of("aaab", 'b'), new int[]{3, 2, 1, 0});
        scenarios.put(Pair.of("aaab", 'a'), new int[]{0, 0, 0, 1});
        scenarios.put(Pair.of("a", 'a'), new int[]{0});

        scenarios.forEach((input, expected) ->
                assertArrayEquals(expected, stringProblems.shortestToChar(input.getLeft(), input.getRight())));
    }

    @Test
    void testIsAnagram() {
        Map<Pair<String, String>, Boolean> scenarios = new HashMap<>();

        scenarios.put(Pair.of("anagram", "nagamar"), true);
        scenarios.put(Pair.of("anagram", "aaaagrm"), false);
        scenarios.put(Pair.of("ram", "hello"), false);

        scenarios.forEach((input, expected) ->
                assertEquals(expected, stringProblems.isAnagram(input.getLeft(), input.getRight())));
    }

    @Test
    void testMinimumLengthEncoding() {
        Map<String[], Integer> scenarios = new HashMap<>();

        scenarios.put(new String[]{"time", "me", "bell"}, 10);
        scenarios.put(new String[]{"t"}, 2);

        scenarios.forEach((input, expected) -> assertEquals(expected, stringProblems.minimumLengthEncoding(input)));
    }

    @Test
    void testHasAllCodes() {
        Map<Pair<String, Integer>, Boolean> scenarios = new HashMap<>();

        scenarios.put(Pair.of("00110110", 2), true);
        scenarios.put(Pair.of("00110", 2), true);
        scenarios.put(Pair.of("00110", 1), true);
        scenarios.put(Pair.of("0110", 2), false);
        scenarios.put(Pair.of("0000000001011100", 4), false);

        scenarios.forEach((input, expected) ->
                assertEquals(expected, stringProblems.hasAllCodes(input.getLeft(), input.getRight())));
    }

    @Test
    void testWordSubsets() {
        Map<Pair<String[], String[]>, List<String>> scenarios = new HashMap<>();

        String[] A = {"amazon", "apple", "facebook", "google", "leetcode"};

        scenarios.put(Pair.of(A, new String[]{"e", "o"}), List.of("facebook", "google", "leetcode"));
        scenarios.put(Pair.of(A, new String[]{"e", "l"}), List.of("apple", "google", "leetcode"));
        scenarios.put(Pair.of(A, new String[]{"e", "oo"}), List.of("google", "facebook"));
        scenarios.put(Pair.of(A, new String[]{"eo", "lo"}), List.of("google", "leetcode"));
        scenarios.put(Pair.of(A, new String[]{"ec", "oc", "ceo"}), List.of("facebook", "leetcode"));

        scenarios.forEach((input, expected) -> {
                    try {
                        assertTrue(stringProblems.wordSubsets(input.getLeft(), input.getRight()).containsAll(expected));
                    } catch (BadInputException e) {
                        e.printStackTrace();
                    }
                }
        );
    }

    @Test
    void testCountSubstrings() {
        Map<String, Integer> scenarios = new HashMap<>();

        scenarios.put("abc", 3);
        scenarios.put("aaa", 6);

        scenarios.forEach((input, expected) -> assertEquals(expected, stringProblems.countSubstrings(input)));
    }

    @Test
    void testLetterCasePermutation() {
        Map<String, List<String>> scenarios = new HashMap<>();

        scenarios.put("abc", List.of("abc", "Abc", "aBc", "abC", "aBC", "AbC", "ABC", "ABc"));
        scenarios.put("ab9", List.of("ab9", "Ab9", "aB9", "AB9"));
        scenarios.put("AB9", List.of("ab9", "Ab9", "aB9", "AB9"));
        scenarios.put("aB9", List.of("ab9", "Ab9", "aB9", "AB9"));

        scenarios.forEach((input, expected) ->
                assertTrue(expected.containsAll(stringProblems.letterCasePermutation(input))));
    }

    @Test
    void testFindMaxForm() {
        Map<Triple<String[], Integer, Integer>, Integer> scenarios = new HashMap<>();

        scenarios.put(Triple.of(new String[]{"10", "0", "1"}, 1, 1), 2);
        scenarios.put(Triple.of(new String[]{"10", "0001", "111001", "1", "0"}, 5, 3), 4);

        scenarios.forEach((input, expected) -> assertEquals(expected,
                stringProblems.findMaxForm(input.getLeft(), input.getMiddle(), input.getRight())));
    }

    @Test
    void testLongestValidParentheses() {
        Map<String, Integer> scenarios = new HashMap<>();

        scenarios.put("(()))", 4);
        scenarios.put("())", 2);
        scenarios.put(")()())", 4);
        scenarios.put("(((((((", 0);
        scenarios.put("", 0);

        scenarios.forEach((input, expected) -> assertEquals(expected, stringProblems.longestValidParentheses(input)));
    }

    @Test
    void testHalvesAreAlike() {
        Map<String, Boolean> scenarios = new HashMap<>();

        scenarios.put("textbank", true);
        scenarios.put("book", true);
        scenarios.put("textbook", false);
        scenarios.put("ABCdef", true);

        scenarios.forEach((input, expected) -> assertEquals(expected, stringProblems.halvesAreAlike(input)));
    }

    @Test
    void testRemoveDuplicates() {
        Map<String, String> scenarios = new HashMap<>();

        scenarios.put("abbaca", "ca");
        scenarios.put("aabccbe", "e");

        scenarios.forEach((input, expected) -> assertEquals(expected, stringProblems.removeDuplicates(input)));
    }

    @Test
    void testRemoveKDuplicates() {
        Map<Pair<String, Integer>, String> scenarios = new HashMap<>();

        scenarios.put(Pair.of("abcd", 2), "abcd");
        scenarios.put(Pair.of("deeedbbcccbdaa", 3), "aa");
        scenarios.put(Pair.of("pbbcggttciiippooaais", 2), "ps");

        scenarios.forEach((input, expected) ->
                assertEquals(expected, stringProblems.removeDuplicates(input.getLeft(), input.getRight())));
    }

    @Test
    void testCountBinarySubstrings() {
        Map<String, Integer> scenarios = new HashMap<>();

        scenarios.put("00110", 3);
        scenarios.put("00110011", 6);
        scenarios.put("10101", 4);
        scenarios.put("00011100", 5);

        scenarios.forEach((input, expected) -> assertEquals(expected, stringProblems.countBinarySubstrings(input)));
    }

    @Test
    void testBulbSwitcherIV() {
        Map<String, Integer> scenarios = new HashMap<>();

        scenarios.put("101", 3);
        scenarios.put("10111", 3);
        scenarios.put("0100", 2);

        scenarios.forEach((input, expected) -> assertEquals(expected, stringProblems.bulbSwitcherIV(input)));
    }

    @Test
    void testRemoveSubfolders() {
        Map<String[], List<String>> scenarios = new HashMap<>();

        scenarios.put(new String[]{"/a", "/a/b", "/c/d", "/c/d/e", "/c/f"}, List.of("/a", "/c/d", "/c/f"));
        scenarios.put(new String[]{"/a", "/a/b/c", "/a/b/d"}, List.of("/a"));

        scenarios.forEach((input, expected) -> assertTrue(expected.containsAll(stringProblems.removeSubfolders(input))));
    }

    @Test
    void testMinDeletions() {
        Map<String, Integer> scenarios = new HashMap<>();

        scenarios.put("aaabbc", 0);
        scenarios.put("aaabbbccc", 3);
        scenarios.put("aaabbbcc", 2);
        scenarios.put("qwertyuiopasdfghjklzxcvbnm", 25);

        scenarios.forEach((input, expected) -> assertEquals(expected, stringProblems.minDeletions(input)));
    }

    @Test
    void testLongestStrChain() {
        Map<String[], Integer> scenarios = new HashMap<>();

        scenarios.put(new String[]{"xbc", "pcxbcf", "xb", "cxbc", "pcxbc"}, 5);
        scenarios.put(new String[]{"a", "b", "ba", "bca", "bda", "bdca"}, 4);

        scenarios.forEach((input, expected) -> assertEquals(expected, stringProblems.longestStrChain(input)));
    }

    @Test
    void testFindDuplicate() {
        Map<String[], List<List<String>>> scenarios = new HashMap<>();

        scenarios.put(
                new String[]{
                        "root/a 1.txt(abcd) 2.txt(efgh)",
                        "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)",
                        "root 4.txt(efgh)"
                },
                List.of(
                        List.of("root/a/2.txt", "root/c/d/4.txt", "root/4.txt"),
                        List.of("root/a/1.txt", "root/c/3.txt"))
        );

        scenarios.put(
                new String[]{
                        "root/a 1.txt(abcd) 2.txt(efgh)",
                        "root/c 3.txt(abcd)",
                        "root/c/d 4.txt(efgh)"
                },
                List.of(
                        List.of("root/a/2.txt", "root/c/d/4.txt"),
                        List.of("root/a/1.txt", "root/c/3.txt"))
        );

        scenarios.put(
                new String[]{
                        "root/a 1.txt(abcd)",
                        "root/c 3.txt(efgh)"
                },
                List.of(
                        List.of("root/a/1.txt"),
                        List.of("root/c/3.txt"))
        );

        scenarios.forEach((input, expected) -> assertEquals(
                stringProblems.findDuplicate(input).stream()
                        .filter(i -> !expected.contains(i))
                        .count(),
                0));
    }

    @Test
    void testFindAndReplacePattern() {
        Map<Pair<String[], String>, List<String>> scenarios = new HashMap<>();

        scenarios.put(Pair.of(new String[]{"abc", "deq", "mee", "aqq", "dkd", "ccc"}, "abb"), List.of("mee", "aqq"));
        scenarios.put(Pair.of(new String[]{"a", "b", "c"}, "a"), List.of("a", "b", "c"));

        scenarios.forEach((input, expected) -> {
            assertTrue(expected.containsAll(stringProblems.findAndReplacePattern(input.getLeft(), input.getRight())));
        });
    }

    @Test
    void testEvalRPN() {
        Map<String[], Integer> scenarios = new HashMap<>();

        scenarios.put(new String[]{"2", "1", "+", "3", "*"}, 9);
        scenarios.put(new String[]{"4", "13", "5", "/", "+"}, 6);
        scenarios.put(new String[]{"4", "13", "5", "-", "+"}, 12);

        scenarios.forEach((input, expected) -> assertEquals(expected, stringProblems.evalRPN(input)));
    }

    @Test
    void testMinPartitions() {
        Map<String, Integer> scenarios = new HashMap<>();

        scenarios.put("32", 3);
        scenarios.put("12345678", 8);
        scenarios.put("27346209830709182346", 9);

        scenarios.forEach((input, expected) -> assertEquals(expected, stringProblems.minPartitions(input)));
    }

    @Test
    void testLengthOfLongestSubstring() {
        Map<String, Integer> scenarios = new HashMap<>();

        scenarios.put("abcabcbb", 3);
        scenarios.put("bbbbb", 1);
        scenarios.put(" ", 1);

        scenarios.forEach((input, expected) -> assertEquals(expected, stringProblems.lengthOfLongestSubstring(input)));
    }

    @Test
    void testOpenLock() {
        Map<Pair<String[], String>, Integer> scenarios = new HashMap<>();

        scenarios.put(Pair.of(new String[]{"0201", "0101", "0102", "1212", "2002"}, "0202"), 6);
        scenarios.put(Pair.of(new String[]{"0201", "0101", "0102", "1212", "2002"}, "0000"), 0);
        scenarios.put(Pair.of(new String[]{"8888"}, "0009"), 1);
        scenarios.put(Pair.of(new String[]{"8887", "8889", "8878", "8898", "8788", "8988", "7888", "9888"}, "8888"), -1);
        scenarios.put(Pair.of(new String[]{"0000"}, "8888"), -1);

        scenarios.forEach((input, expected) -> assertEquals(expected,
                stringProblems.openLock(input.getLeft(), input.getRight())));
    }

    @Test
    void testGenerateParenthesis() {
        Map<Integer, List<String>> scenarios = new HashMap<>();

        scenarios.put(3, List.of("((()))", "(()())", "(())()", "()(())", "()()()"));
        scenarios.put(1, List.of("()"));

        scenarios.forEach((input, expected) ->
                assertTrue(expected.containsAll(stringProblems.generateParenthesis(input)))
        );
    }

    @Test
    void testNumMatchingSubseq() {
        Map<Pair<String, String[]>, Integer> scenarios = new HashMap<>();

        scenarios.put(Pair.of("abcde", new String[]{"a", "bb", "acd", "ace"}), 3);
        scenarios.put(Pair.of("dsahjpjauf", new String[]{"ahjpjau", "ja", "ahbwzgqnuk", "tnmlanowax"}), 2);
        scenarios.put(Pair.of("btovxbkumc", new String[]{"btovxbku", "to", "zueoxxxjme", "yjkclbkbtl"}), 2);

        scenarios.forEach((input, expected) ->
                assertEquals(expected, stringProblems.numMatchingSubseq(input.getLeft(), input.getRight())));
    }

    @Test
    void testGetHint() {
        Map<Pair<String, String>, String> scenarios = new HashMap<>();

        scenarios.put(Pair.of("1807", "7810"), "1A3B");
        scenarios.put(Pair.of("1123", "0111"), "1A1B");
        scenarios.put(Pair.of("1", "0"), "0A0B");
        scenarios.put(Pair.of("1", "1"), "1A0B");

        scenarios.forEach((input, expected) ->
                assertEquals(expected, stringProblems.getHint(input.getLeft(), input.getRight())));
    }

    @Test
    void testDecodeString() {
        Map<String, String> scenarios = new HashMap<>();

        scenarios.put("3[a]2[bc]", "aaabcbc");
        scenarios.put("3[a2[c]]", "accaccacc");
        scenarios.put("2[abc]3[cd]ef", "abcabccdcdcdef");
        scenarios.put("abc3[cd]xyz", "abccdcdcdxyz");

        scenarios.forEach((input, expected) -> assertEquals(expected, stringProblems.decodeString(input)));
    }

    @Test
    void testSentenceScreenFitting() {
        Map<Triple<String[], Integer, Integer>, Integer> scenarios = new HashMap<>();

        scenarios.put(Triple.of(new String[]{"hello", "world"}, 2, 8), 1);
        scenarios.put(Triple.of(new String[]{"a", "bcd", "e"}, 3, 6), 2);
        scenarios.put(Triple.of(new String[]{"i", "had", "apple", "pie"}, 4, 5), 1);

        scenarios.forEach((input, expected) -> assertEquals(expected,
                stringProblems.sentenceScreenFitting(input.getLeft(), input.getMiddle(), input.getRight())));
    }

    @Test
    void testNumSplits() {
        Map<String, Integer> scenarios = new HashMap<>();

        scenarios.put("aacaba", 2);
        scenarios.put("abcd", 1);
        scenarios.put("aaaaa", 4);
        scenarios.put("acbadbaada", 2);

        scenarios.forEach((input, expected) -> assertEquals(expected, stringProblems.numSplits(input)));
    }

    @Test
    void testWordBreak() {
        Map<Pair<String, List<String>>, Boolean> scenarios = new HashMap<>();

        scenarios.put(Pair.of("leetcode", List.of("leet", "code")), true);
        scenarios.put(Pair.of("applepenapple", List.of("apple", "pen")), true);
        scenarios.put(Pair.of("catsandog", List.of("cats", "sand", "dog", "cat", "and")), false);

        scenarios.forEach((input, expected) -> assertEquals(expected,
                stringProblems.wordBreak(input.getLeft(), input.getRight())));
    }

    @Test
    void testUniqueEmailAddresses() {
        Map<String[], Integer> scenarios = new HashMap<>();

        scenarios.put(new String[]{"test.email+alex@leetcode.com", "test.e.mail+bob.cathy@leetcode.com",
                "testemail+david@lee.tcode.com"}, 2);
        scenarios.put(new String[]{"a@leetcode.com", "b@leetcode.com", "c@leetcode.com"}, 3);

        scenarios.forEach((input, expected) -> assertEquals(expected, stringProblems.uniqueEmailAddresses(input)));
    }

    @Test
    void testLicenseKeyFormatting() {
        Map<Pair<String, Integer>, String> scenarios = new HashMap<>();

        scenarios.put(Pair.of("5F3Z-2e-9-w", 4), "5F3Z-2E9W");
        scenarios.put(Pair.of("2-5g-3-J", 2), "2-5G-3J");

        scenarios.forEach((input, expected) ->
                assertEquals(expected, stringProblems.licenseKeyFormatting(input.getLeft(), input.getRight())));
    }

    @Test
    void testMinWindow() {
        Map<Pair<String, String>, String> scenarios = new HashMap<>();

        scenarios.put(Pair.of("ADOBECODEBANC", "ABC"), "BANC");
        scenarios.put(Pair.of("a", "a"), "a");
        scenarios.put(Pair.of("a", "aa"), "");

        scenarios.forEach((input, expected) ->
                assertEquals(expected, stringProblems.minWindow(input.getLeft(), input.getRight())));
    }

    @Test
    void testLengthOfLongestSubstringKDistinct() {
        Map<Pair<String, Integer>, Integer> scenarios = new HashMap<>();

        scenarios.put(Pair.of("eceba", 2), 3);
        scenarios.put(Pair.of("aaaaa", 2), 5);
        scenarios.put(Pair.of("aa", 1), 2);

        scenarios.forEach((input, expected) -> assertEquals(expected,
                stringProblems.lengthOfLongestSubstringKDistinct(input.getLeft(), input.getRight()))
        );
    }

    @Test
    void testLengthOfLongestSubstringTwoDistinct() {
        Map<String, Integer> scenarios = new HashMap<>();

        scenarios.put("eceba", 3);
        scenarios.put("ccaabbb", 5);
        scenarios.put("aaaaa", 5);
        scenarios.put("aa", 2);

        scenarios.forEach((input, expected) ->
                assertEquals(expected, stringProblems.lengthOfLongestSubstringTwoDistinct(input))
        );
    }

    @Test
    void testIsValid() {
        Map<String, Boolean> scenarios = new HashMap<>();

        scenarios.put("()", true);
        scenarios.put("()[]{}", true);
        scenarios.put("(]", false);
        scenarios.put("([)]", false);
        scenarios.put("{[]}", true);
        scenarios.put("]ar", false);

        scenarios.forEach((input, expected) -> assertEquals(expected, stringProblems.isValid(input)));
    }

    @Test
    void testLongestPalindrome() {
        Map<String, List<String>> scenarios = new HashMap<>();

        scenarios.put("babad", List.of("aba", "bab"));
        scenarios.put("cbbd", List.of("bb"));
        scenarios.put("a", List.of("a"));
        scenarios.put("qc", List.of("q", "c"));

        scenarios.forEach((input, expected) -> assertTrue(expected.contains(stringProblems.longestPalindrome(input))));
    }

    @Test
    void testGroupAnagrams() {
        Map<String[], List<List<String>>> scenarios = new HashMap<>();

        scenarios.put(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"},
                List.of(
                        List.of("bat"),
                        List.of("nat", "tan"),
                        List.of("ate", "eat", "tea")
                ));
        scenarios.put(new String[]{""}, List.of(List.of("")));
        scenarios.put(new String[]{"a"}, List.of(List.of("a")));

        scenarios.forEach((input, expected) -> {
            int count = 0;
            List<List<String>> answer = stringProblems.groupAnagrams(input);
            for (List<String> ans : answer) {
                for (List<String> exp : expected) {
                    if (exp.containsAll(ans)) {
                        count++;
                        break;
                    }
                }
            }
            assertEquals(count, expected.size());
        });
    }

    @Test
    void testReverseWords() {
        Map<String, String> scenarios = new HashMap<>();

        scenarios.put("the sky is blue", "blue is sky the");
        scenarios.put("   hello world  ", "world hello");
        scenarios.put("a good   example", "example good a");

        scenarios.forEach((input, expected) -> assertEquals(expected, stringProblems.reverseWords(input)));
    }

    @Test
    void testReverseWordsII() {
        Map<char[], char[]> scenarios = new HashMap<>();

        scenarios.put(new char[]{'t', 'h', 'e', ' ', 's', 'k', 'y', ' ', 'i', 's', ' ', 'b', 'l', 'u', 'e'},
                new char[]{'b', 'l', 'u', 'e', ' ', 'i', 's', ' ', 's', 'k', 'y', ' ', 't', 'h', 'e'});
        scenarios.put(new char[]{'a'}, new char[]{'a'});

        scenarios.forEach((input, expected) -> {
            stringProblems.reverseWordsII(input);
            assertArrayEquals(expected, input);
        });
    }

    @Test
    void testReverseString() {
        Map<char[], char[]> scenarios = new HashMap<>();

        scenarios.put(new char[]{'h', 'e', 'l', 'l', 'o'}, new char[]{'o', 'l', 'l', 'e', 'h'});
        scenarios.put(new char[]{'h', 'a', 'n', 'n', 'a'}, new char[]{'a', 'n', 'n', 'a', 'h'});

        scenarios.forEach((input, expected) -> {
            stringProblems.reverseString(input);
            assertArrayEquals(expected, input);
        });
    }

    @Test
    void testMinFlipsMonoIncreasing() {
        Map<String, Integer> scenarios = new HashMap<>();

        scenarios.put("00110", 1);
        scenarios.put("010110", 2);
        scenarios.put("00011000", 2);
        scenarios.put("", 0);
        scenarios.put(null, 0);

        scenarios.forEach((input, expected) -> assertEquals(expected, stringProblems.minFlipsMonoIncreasing(input)));
    }

    @Test
    void testCheckValidString() {
        Map<String, Boolean> scenarios = new HashMap<>();

        scenarios.put("()", true);
        scenarios.put("(()", false);
        scenarios.put("((*)", true);
        scenarios.put("(*)", true);
        scenarios.put("(*", true);
        scenarios.put(")(", false);
        scenarios.put("(((())))", true);

        scenarios.forEach((input, expected) -> assertEquals(expected, stringProblems.checkValidString(input)));
    }

    @Test
    void testValidPalindrome() {
        Map<String, Boolean> scenarios = new HashMap<>();

        scenarios.put("A man, a plan, a canal: Panama", true);
        scenarios.put("race a car", false);
        scenarios.put("aA", true);
        scenarios.put("a", true);
        scenarios.put("", true);
        scenarios.put("b,::::::B", true);
        scenarios.put("0P", false);
        scenarios.put("0P0", true);

        scenarios.forEach((input, expected) -> assertEquals(expected, stringProblems.isValidPalindrome(input)));
    }

    @Test
    void testIsOneEditDistance() {
        Map<Pair<String, String>, Boolean> scenarios = new HashMap<>();

        scenarios.put(Pair.of("abc", "ac"), true);
        scenarios.put(Pair.of("abc", "abcd"), true);
        scenarios.put(Pair.of("abc", "ab"), true);
        scenarios.put(Pair.of("abc", "aB"), false);
        scenarios.put(Pair.of("a", ""), true);
        scenarios.put(Pair.of("", "A"), true);
        scenarios.put(Pair.of("", ""), false);
        scenarios.put(Pair.of("a", "a"), false);

        scenarios.forEach((input, expected) ->
                assertEquals(expected, stringProblems.isOneEditDistance(input.getLeft(), input.getRight())));
    }

    @Test
    void testValidPalindromeII() {
        Map<String, Boolean> scenarios = new HashMap<>();

        scenarios.put("aba", true);
        scenarios.put("abca", true);
        scenarios.put("abc", false);
        scenarios.put("a", true);
        scenarios.put("", true);
        scenarios.put("abcdef", false);

        scenarios.forEach((input, expected) -> assertEquals(expected, stringProblems.isValidPalindromeII(input)));
    }

    @Test
    void testMinRemoveToMakeValid() {
        Map<String, String> scenarios = new HashMap<>();
        scenarios.put("lee(t(c)o)de)", "lee(t(c)o)de");
        scenarios.put("a)b(c)d", "ab(c)d");
        scenarios.put("))((", "");
        scenarios.put("(a(b(c)d)", "a(b(c)d)");

        scenarios.forEach((input, expected) -> assertEquals(expected, stringProblems.minRemoveToMakeValid(input)));
    }

    @Test
    void testWordBreakII() {
        Map<Pair<String, List<String>>, List<String>> scenarios = new HashMap<>();
        scenarios.put(Pair.of("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog")),
                Arrays.asList("cats and dog", "cat sand dog"));
        scenarios.put(Pair.of("pineapplepenapple", Arrays.asList("apple", "pen", "applepen", "pine", "pineapple")),
                Arrays.asList("pine apple pen apple", "pineapple pen apple", "pine applepen apple"));
        scenarios.put(Pair.of("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")), List.of());

        scenarios.forEach((input, expected) -> assertTrue(
                expected.containsAll(
                        stringProblems.wordBreakII(input.getLeft(), input.getRight()))));
    }

    @Test
    void testIsRobotBounded() {
        Map<String, Boolean> scenarios = new HashMap<>();
        scenarios.put("GGLLGG", true);
        scenarios.put("GG", false);
        scenarios.put("LL", true);
        scenarios.put("GL", true);

        scenarios.forEach((input, expected) -> assertEquals(expected, stringProblems.isRobotBounded(input)));
    }
}
