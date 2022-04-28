package com.in28minutes.unittesting.unittesting.xpo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class DateValidatorTest {

    @Test
    public void validateDate() {
        assertTrue(DateValidatorDateTimeFormatter.isValid("02/31/2021"));
    }
}
