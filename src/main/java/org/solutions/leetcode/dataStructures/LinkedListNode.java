package org.solutions.leetcode.dataStructures;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkedListNode {
    private int val;
    private LinkedListNode next;

    public LinkedListNode(int val) {
        this.val = val;
    }
}
