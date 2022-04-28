package com.in28minutes.unittesting.unittesting.business;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ListMockTest {
    @Mock
    List<String> mock;

    @Test
    public void size_basic(){
        when(mock.size()).thenReturn(5);
        assertEquals(5,mock.size());
    }

    @Test
    public void returnDifferentValues(){
        when(mock.size()).thenReturn(5).thenReturn(10);
        assertEquals(5,mock.size());
        assertEquals(10,mock.size());
    }

    @Test
    public void returnWithParameters(){
        when(mock.get(0)).thenReturn("Hello world");
        assertEquals("Hello world",mock.get(0));
        assertEquals(null,mock.get(1));
    }

    @Test
    public void returnWithGenericParameters(){
        when(mock.get(anyInt())).thenReturn("Hello world");
        assertEquals("Hello world",mock.get(0));
        assertEquals("Hello world",mock.get(1));
        assertEquals("Hello world",mock.get(10));
    }

    @Test
    public void verificationBasics(){
        //SUT
        String value = mock.get(0);
        String value1 = mock.get(1);

        //Verify
        verify(mock).get(0);
        verify(mock,times(2)).get(anyInt());
        verify(mock,atLeast(1)).get(anyInt());
        verify(mock,atLeastOnce()).get(anyInt());
        verify(mock, atMost(2)).get(anyInt());
        verify(mock, never()).get(2);
    }

    @Test
    public void argumentCapturing(){
        //SUT
        mock.add("SomeString");

        //Verification
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(mock).add(captor.capture());

        assertEquals("SomeString",captor.getValue());
    }

    @Test
    public void multipleArgumentCapturing(){
        //SUT
        mock.add("SomeString1");
        mock.add("SomeString2");

        //Verification
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(mock,times(2)).add(captor.capture());

        List<String> allValues = captor.getAllValues();
        assertEquals("SomeString1",allValues.get(0));
        assertEquals("SomeString2",allValues.get(1));
    }

    @Test
    public void mocking(){
        ArrayList arrayListMock = mock(ArrayList.class);
        System.out.println(arrayListMock.get(0));
        System.out.println(arrayListMock.size());
        arrayListMock.add("Some1");
        arrayListMock.add("Some2");
        System.out.println(arrayListMock.size());
        when(arrayListMock.size()).thenReturn(5);
        System.out.println(arrayListMock.size());
    }

    @Test
    public void spying(){
        ArrayList arrayListSpy = spy(ArrayList.class);
        arrayListSpy.add("Some0");
        System.out.println(arrayListSpy.get(0));
        System.out.println(arrayListSpy.size());
        arrayListSpy.add("Some1");
        arrayListSpy.add("Some2");
        System.out.println(arrayListSpy.size());
        when(arrayListSpy.size()).thenReturn(5);
        System.out.println(arrayListSpy.size());
    }
}
