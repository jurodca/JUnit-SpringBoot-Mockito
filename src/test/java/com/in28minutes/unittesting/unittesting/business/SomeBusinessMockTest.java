package com.in28minutes.unittesting.unittesting.business;

import com.in28minutes.unittesting.unittesting.data.SomeDataService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SomeBusinessMockTest {

    @InjectMocks
    SomeBusinessImpl someBusinessImpl = new SomeBusinessImpl();
    @Mock
    SomeDataService mock;

    @Test
    public void calculateSumUsingDataService_basic() {
        when(mock.retrieveAllData()).thenReturn(new int[]{1, 2, 3});
        assertEquals(6, someBusinessImpl.calculateSumUsingDataService());
    }

    @Test
    public void calculateSumUsingDataService_empty() {
        when(mock.retrieveAllData()).thenReturn(new int[]{});
        assertEquals(0, someBusinessImpl.calculateSumUsingDataService());
    }

    @Test
    public void calculateSumUsingDataService_oneValue() {
        when(mock.retrieveAllData()).thenReturn(new int[]{5});
        assertEquals(5, someBusinessImpl.calculateSumUsingDataService());
    }
}
