package org.solutions.leetcode;

import org.solutions.leetcode.dataStructures.TrieNode;

import java.util.HashMap;
import java.util.Map;

public class TrieProblems {
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
     * @see org.solutions.leetcode.StringProblems#minimumLengthEncoding(String[])
     */
    public int minimumLengthEncoding(String[] words) {
        Map<TrieNode, Integer> nodes = new HashMap<>();
        TrieNode trie = new TrieNode();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            TrieNode node = trie;
            for (int j = word.length() - 1; j >= 0; j--)
                node = node.get(word.charAt(j));
            nodes.put(node, i);
        }

        int count = 0;
        for (TrieNode node : nodes.keySet()) {
            if (node.isLeaf()) {
                count += words[nodes.get(node)].length() + 1;
            }
        }

        return count;
    }
}
