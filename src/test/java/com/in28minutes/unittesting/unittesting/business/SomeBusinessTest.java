package com.in28minutes.unittesting.unittesting.business;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class SomeBusinessTest {

    @Test
    public void calculateSum_basic() {
        SomeBusinessImpl someBusinessImpl = new SomeBusinessImpl();
        int actualResult = someBusinessImpl.calculateSum(new int[]{1,2,3});
        int expectedResult = 6;
        assertEquals(expectedResult,actualResult);
    }

    @Test
    public void calculateSum_empty() {
        SomeBusinessImpl someBusinessImpl = new SomeBusinessImpl();
        int actualResult = someBusinessImpl.calculateSum(new int[]{});
        int expectedResult = 0;
        assertEquals(expectedResult,actualResult);
    }

    @Test
    public void calculateSum_oneValue() {
        SomeBusinessImpl someBusinessImpl = new SomeBusinessImpl();
        int actualResult = someBusinessImpl.calculateSum(new int[]{1});
        int expectedResult = 1;
        assertEquals(expectedResult,actualResult);
    }
}
