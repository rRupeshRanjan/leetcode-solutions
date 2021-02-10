package org.solutions.leetcode;

import javax.swing.plaf.IconUIResource;
import java.util.*;

public class ArrayProblems {
    /*
    * Q. 594
    * We define a harmonious array as an array where the difference between its maximum value and its minimum value is exactly 1.
    * Given an integer array nums, return the length of its longest harmonious subsequence among all its possible subsequences.
    * A subsequence of array is a sequence that can be derived from the array by deleting some or no elements without changing the order of the remaining elements.
    * */
    public int findLHS(int[] nums, String way) {
        int maxLen = 0;

        switch (way) {
            case "brute-force": {
                for(int i=0; i<nums.length; i++) {
                    int currLen = 0;
                    boolean flag = false;
                    for(int j=0; j<nums.length; j++) {
                        if (nums[i] == nums[j])
                            currLen++;
                        else if(nums[j] == nums[i]+1) {
                            currLen++;
                            flag = true;
                        }
                    }

                    if (flag)
                        maxLen = Math.max(maxLen, currLen);
                }
                return maxLen;
            }

            case "sorting": {
                Arrays.sort(nums);
                int prevLen = 0;

                for(int i=0; i<nums.length; i++) {
                    int currLen = 1;
                    if(i>0 && nums[i]-nums[i-1]==1) {
                        while(i<nums.length-1 && nums[i]==nums[i+1]) {
                            currLen++;
                            i++;
                        }
                        maxLen = Math.max(maxLen, currLen + prevLen);
                        prevLen = currLen;
                    } else {
                        while(i<nums.length-1 && nums[i]==nums[i+1]) {
                            currLen++;
                            i++;
                        }
                        prevLen = currLen;
                    }
                }
                return maxLen;
            }

            case "hashmap": {
                Map<Integer, Integer> map = new HashMap<>();
                for(int num: nums) {
                    map.put(num, map.getOrDefault(num, 0) + 1);
                }

                for(int key: map.keySet()) {
                    if(map.containsKey(key+1)) {
                        maxLen = Math.max(maxLen, map.get(key) + map.get(key+1));
                    }
                }
                return maxLen;
            }

            default:
                return -1;
        }
    }

    /*
    * Q.1640
    * You are given an array of distinct integers arr and an array of integer arrays pieces,
    * where the integers in pieces are distinct.
    * Your goal is to form arr by concatenating the arrays in pieces in any order.
    * However, you are not allowed to reorder the integers in each array pieces[i].
    * Return true if it is possible to form the array arr from pieces. Otherwise, return false.
    * */
    public boolean canFormArray(int[] arr, int[][] pieces) {
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int[] piece : pieces) {
            map.put(piece[0], new ArrayList<>());
            for (int j = 1; j < piece.length; j++) {
                map.get(piece[0]).add(piece[j]);
            }
        }

        for(int i=0; i<arr.length; i++) {
            if(!map.containsKey(arr[i])) {
                return false;
            } else {
                for (Integer value : map.get(arr[i])) {
                    if (arr[++i] != value)
                        return false;
                }
            }
        }

        return true;
    }
}
