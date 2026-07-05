package com.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SpringParameterizedTest {

    @ParameterizedTest
    @ValueSource(strings = {"racecar", "radar", "level"})
    public void testPalindromes(String candidate) {
        assertTrue(isPalindrome(candidate));
    }

    private boolean isPalindrome(String str) {
        return new StringBuilder(str).reverse().toString().equals(str);
    }

}
