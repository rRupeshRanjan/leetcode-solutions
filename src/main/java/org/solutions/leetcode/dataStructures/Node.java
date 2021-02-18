package org.solutions.leetcode.dataStructures;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Node {
    private int val;
    private Node next;

    // specially introduced for a problem
    private Node random;

    public Node(int val) {
        this.val = val;
    }
}
