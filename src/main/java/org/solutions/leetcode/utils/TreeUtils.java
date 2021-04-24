package org.solutions.leetcode.utils;

import org.solutions.leetcode.dataStructures.NaryTreeNode;

import java.util.ArrayList;
import java.util.List;

public class TreeUtils {

    /*
     * This method is not widely reliable, and works only under certain limited scenarios.
     * Especially when its full tree up to values provided.
     * */
    public NaryTreeNode createTreeFromList(List<Integer> list) {
        NaryTreeNode root = new NaryTreeNode(list.get(0));
        List<NaryTreeNode> parents = new ArrayList<>();
        parents.add(root);

        int i = 1, j = 0;
        while (i < list.size()) {
            List<NaryTreeNode> children = new ArrayList<>();
            for (++i; i < list.size() && list.get(i) != null; i++) {
                NaryTreeNode node = new NaryTreeNode(list.get(i));
                children.add(node);
                parents.add(node);
            }

            parents.get(j++).setChildren(children);
        }

        return root;
    }
}
