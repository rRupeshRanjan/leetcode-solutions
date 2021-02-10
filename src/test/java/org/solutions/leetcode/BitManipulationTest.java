package org.solutions.leetcode;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BitManipulationTest {

    static BitManipulation bitManipulation;

    @BeforeAll
    public static void setup() {
        bitManipulation = new BitManipulation();
    }

    @Test
    public void numberOf1Bits() {
        Map<Integer, Integer> expectationMap = Map.of(
                3, 00000000000000000000000000001011,
                1, 00000000000000000000000010000000
//                31, 11111111111111111111111111111101
        );

        expectationMap.forEach((expected, input) ->
                assertEquals(expected, bitManipulation.NumberOf1Bits(input))
        );
    }
}