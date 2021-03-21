package org.solutions.leetcode.customClassDesign;

import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.Map;

class UndergroundSystem {

    Map<Integer, Pair<String, Double>> journeyMap;
    Map<String, Pair<Double, Integer>> timeMap;

    public UndergroundSystem() {
        journeyMap = new HashMap<>();
        timeMap = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        journeyMap.put(id, Pair.of(stationName, (double) t));
    }

    public void checkOut(int id, String stationName, int t) {
        String startStation = journeyMap.get(id).getLeft();
        Double startTime = journeyMap.get(id).getRight();

        String key = startStation + stationName;
        Pair<Double, Integer> timePair = timeMap.getOrDefault(key, Pair.of(0d, 0));
        timeMap.put(key, Pair.of(timePair.getLeft() + t - startTime, timePair.getRight() + 1));
    }

    public double getAverageTime(String startStation, String endStation) {
        String key = startStation + endStation;
        Pair<Double, Integer> timePair = timeMap.get(key);
        return timePair.getLeft() / timePair.getRight();
    }
}
