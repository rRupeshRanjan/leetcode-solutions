package org.solutions.leetcode;

import org.solutions.leetcode.dataStructures.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

public class BinaryTreeProblems {
    /*
    * Q.199
    * Given a binary tree, imagine yourself standing on the right side of it,
    * return the values of the nodes you can see ordered from top to bottom.
    * */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();

        if (root!= null) {
            q.add(root);
        }

        while(!q.isEmpty()) {
            int size = q.size();
            for(int i=0; i<size; i++) {
                TreeNode temp = q.poll();

                if(temp.getLeft()!=null)
                    q.add(temp.getLeft());

                if(temp.getRight()!=null)
                    q.add(temp.getRight());

                if(i==size-1)
                    result.add(temp.getVal());
            }
        }

        return result;
    }
}
