package com.in28minutes.unittesting.unittesting.business;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SomeDataServiceStub implements com.in28minutes.unittesting.unittesting.data.SomeDataService {

    @Override
    public int[] retrieveAllData() {
        return new int[]{1, 2, 3};
    }
}

class SomeDataServiceEmptyStub implements com.in28minutes.unittesting.unittesting.data.SomeDataService {

    @Override
    public int[] retrieveAllData() {
        return new int[]{};
    }
}

class SomeDataServiceOneElementStub implements com.in28minutes.unittesting.unittesting.data.SomeDataService {

    @Override
    public int[] retrieveAllData() {
        return new int[]{5};
    }
}

@SpringBootTest
public class SomeBusinessStubTest {

    @Test
    public void calculateSumUsingDataService_basic() {
        SomeBusinessImpl someBusinessImpl = new SomeBusinessImpl();
        someBusinessImpl.setSomeDataService(new SomeDataServiceStub());
        int actualResult = someBusinessImpl.calculateSumUsingDataService();
        int expectedResult = 6;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void calculateSumUsingDataService_empty() {
        SomeBusinessImpl someBusinessImpl = new SomeBusinessImpl();
        someBusinessImpl.setSomeDataService(new SomeDataServiceEmptyStub());
        int actualResult = someBusinessImpl.calculateSumUsingDataService();
        int expectedResult = 0;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void calculateSumUsingDataService_oneValue() {
        SomeBusinessImpl someBusinessImpl = new SomeBusinessImpl();
        someBusinessImpl.setSomeDataService(new SomeDataServiceOneElementStub());
        int actualResult = someBusinessImpl.calculateSumUsingDataService();
        int expectedResult = 1;
        assertEquals(expectedResult, actualResult);
    }
}
