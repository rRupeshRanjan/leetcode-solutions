package org.solutions.leetcode.dataStructures;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListNode {
    private int val;
    private ListNode next;

    // specially introduced for a problem
    private ListNode random;

    public ListNode(int val) {
        this.val = val;
    }
}
