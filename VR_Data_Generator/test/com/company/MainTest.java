package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    public void testGenerateRand() {
        for(int i = 1; i < 100; i++) {
            int rand = Main.generateRandomWithRange(0, 2);
            System.out.println(rand);
            assertTrue(rand <= 1 && rand >= 0);
        }
    }
}