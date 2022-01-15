package org.solutions.leetcode.dataStructures;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Log {
    private int index;
    private Boolean isStart;
    private int time;

    public Log(String s) {
        String[] arr = s.split(":");
        this.index = Integer.parseInt(arr[0]);
        this.isStart = arr[1].equals("start");
        this.time = Integer.parseInt(arr[2]);
    }
}
