package org.solutions.leetcode.dataStructures;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListNode {
    private Integer val;
    private ListNode next;
    private ListNode prev;

    // specially introduced for a problem
    private ListNode random;

    // specially introduced for Q. 430
    private ListNode child;

    public ListNode(Integer val) {
        this.val = val;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ListNode node = (ListNode) o;
        return (Objects.equals(val, node.val)) && areNodesEqual(next, node.next);
    }

    private boolean areNodesEqual(ListNode a, ListNode b) {
        boolean isEqual = false;
        if (a == null && b == null) {
            isEqual = true;
        } else if (a != null && b != null) {
            isEqual = a.equals(b);
        }
        return isEqual;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
