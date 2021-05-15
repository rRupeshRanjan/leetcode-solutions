package org.solutions.leetcode.dataStructures;

import lombok.Data;

@Data
public class TrieNode {
    private TrieNode[] children = new TrieNode[26];
    private boolean leaf = true;

    public TrieNode get(char ch) {
        if (children[ch - 'a'] == null) {
            children[ch - 'a'] = new TrieNode();
            leaf = false;
        }

        return children[ch - 'a'];
    }
}
