package org.solutions.leetcode.customClassDesign;

import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * Q. 1396 Design Underground System
 * <p>
 * An underground railway system is keeping track of customer travel times between different stations. They are using
 * this data to calculate the average time it takes to travel from one station to another.
 * <p>
 * Implement the UndergroundSystem class:
 * <p>
 * void checkIn(int id, string stationName, int t)
 * A customer with a card ID equal to id, checks in at the station stationName at time t.
 * A customer can only be checked into one place at a time.
 * void checkOut(int id, string stationName, int t)
 * A customer with a card ID equal to id, checks out from the station stationName at time t.
 * double getAverageTime(string startStation, string endStation)
 * Returns the average time it takes to travel from startStation to endStation.
 * <p>
 * The average time is computed from all the previous traveling times from startStation to endStation that happened
 * directly, meaning a check in at startStation followed by a check out from endStation.
 * <p>
 * The time it takes to travel from startStation to endStation may be different from the time
 * it takes to travel from endStation to startStation.
 * <p>
 * There will be at least one customer that has traveled from startStation to endStation before getAverageTime is called.
 * You may assume all calls to the checkIn and checkOut methods are consistent.
 * If a customer checks in at time t1 then checks out at time t2, then t1 < t2. All events happen in chronological order.
 * <p>
 * tags:: design
 */
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
