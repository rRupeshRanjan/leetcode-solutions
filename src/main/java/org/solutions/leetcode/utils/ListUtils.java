package org.solutions.leetcode.utils;

import java.util.List;

public class ListUtils {

    public boolean areNestedListsEqual(List<List<Integer>> a, List<List<Integer>> b) {
        if (a != null && b != null && a.size() == b.size()) {
            for (int i = 0; i < a.size(); i++) {
                if (a.get(i).size() != b.get(i).size())
                    return false;
                for (int j = 0; j < a.get(i).size(); j++) {
                    if (!a.get(i).get(j).equals(b.get(i).get(j)))
                        return false;
                }
            }
            return true;
        }

        return false;
    }
}
