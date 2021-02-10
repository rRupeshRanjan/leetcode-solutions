package org.solutions.leetcode;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.solutions.leetcode.dataStructures.TreeNode;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeProblemsTest {

    static BinaryTreeProblems binaryTreeProblems;
    static TestUtils testUtils;

    @BeforeAll
    static void setup() {
        binaryTreeProblems = new BinaryTreeProblems();
        testUtils = new TestUtils();
    }

    @Test
    void testRightSideView() {
        TreeNode head = new TreeNode(1);
        TreeNode temp2 = new TreeNode(2);
        TreeNode temp3 = new TreeNode(3);
        TreeNode temp4 = new TreeNode(4);
        TreeNode temp5 = new TreeNode(5);

        temp2.setRight(temp4);
        temp3.setRight(temp5);
        head.setLeft(temp2);
        head.setRight(temp3);

        assertEquals(Arrays.asList(1, 3, 5), binaryTreeProblems.rightSideView(head));

        temp3.setRight(null);
        assertEquals(Arrays.asList(1, 3, 4), binaryTreeProblems.rightSideView(head));

        head.setRight(null);
        assertEquals(Arrays.asList(1, 2, 4), binaryTreeProblems.rightSideView(head));

        temp2.setRight(null);
        assertEquals(Arrays.asList(1, 2), binaryTreeProblems.rightSideView(head));

        head.setLeft(null);
        assertEquals(Collections.singletonList(1), binaryTreeProblems.rightSideView(head));

        head = null;
        assertEquals(Collections.emptyList(), binaryTreeProblems.rightSideView(head));
    }
}