package org.solutions.leetcode.dataStructures;

import lombok.Data;

import java.util.Arrays;

@Data
public class TrieNode {
    private TrieNode[] children = new TrieNode[26];
    private boolean leaf = true;

    public TrieNode get(char ch) {
        if (children[ch - 'a'] == null) {
            this.children[ch - 'a'] = new TrieNode();
            this.leaf = false;
        }

        return this.children[ch - 'a'];
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        TrieNode trieNode = (TrieNode) o;
        return leaf == trieNode.leaf && Arrays.equals(children, trieNode.children);
    }
}
