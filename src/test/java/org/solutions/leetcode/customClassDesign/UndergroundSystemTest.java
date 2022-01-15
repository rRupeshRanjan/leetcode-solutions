package org.solutions.leetcode.customClassDesign;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UndergroundSystemTest {
    static UndergroundSystem undergroundSystem;

    @BeforeAll
    static void setup() {
        undergroundSystem = new UndergroundSystem();
    }

    @Test
    void testUndergroundSystem1() {
        undergroundSystem.checkIn(10, "Leyton", 3);
        undergroundSystem.checkOut(10, "Paradise", 8);
        assertEquals(5, undergroundSystem.getAverageTime("Leyton", "Paradise")); // return 5.00000
        undergroundSystem.checkIn(5, "Leyton", 10);
        undergroundSystem.checkOut(5, "Paradise", 16);
        assertEquals(5.5, undergroundSystem.getAverageTime("Leyton", "Paradise")); // return 5.50000
        undergroundSystem.checkIn(2, "Leyton", 21);
        undergroundSystem.checkOut(2, "Paradise", 30);
        assertEquals(6.666666666666667, undergroundSystem.getAverageTime("Leyton", "Paradise")); // return 6.66667
    }

    @Test
    void testUndergroundSystem2() {
        undergroundSystem.checkIn(45, "Leyton", 3);
        undergroundSystem.checkIn(32, "Paradise", 8);
        undergroundSystem.checkIn(27, "Leyton", 10);
        undergroundSystem.checkOut(45, "Waterloo", 15);
        undergroundSystem.checkOut(27, "Waterloo", 20);
        undergroundSystem.checkOut(32, "Cambridge", 22);
        assertEquals(14, undergroundSystem.getAverageTime("Paradise", "Cambridge"));       // return 14.00000. There was only one travel from "Paradise" (at time 8) to "Cambridge" (at time 22)
        assertEquals(11, undergroundSystem.getAverageTime("Leyton", "Waterloo"));          // return 11.00000. There were two travels from "Leyton" to "Waterloo", a customer with id=45 from time=3 to time=15 and a customer with id=27 from time=10 to time=20. So the average time is ( (15-3) + (20-10) ) / 2 = 11.00000
        undergroundSystem.checkIn(10, "Leyton", 24);
        assertEquals(11, undergroundSystem.getAverageTime("Leyton", "Waterloo"));          // return 11.00000
        undergroundSystem.checkOut(10, "Waterloo", 38);
        undergroundSystem.getAverageTime("Leyton", "Waterloo");
    }
}