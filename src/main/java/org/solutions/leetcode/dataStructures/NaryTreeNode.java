package org.solutions.leetcode.dataStructures;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NaryTreeNode {
    private int val;
    private List<NaryTreeNode> children;

    public NaryTreeNode(int val) {
        this.val = val;
    }
}
