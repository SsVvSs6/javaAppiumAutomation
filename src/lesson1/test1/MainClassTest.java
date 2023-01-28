package lesson1.test1;

import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {

    @Test
    public void testGetLocalNumber() {
        int expectedNumber = 14;
        Assert.assertEquals("Number is not 14", expectedNumber, MainClass.getLocalNumber());
    }

    @Test
    public void testGetClassNumber() {
        int highNumber = 45;
        Assert.assertTrue("Number is less than 45", highNumber < MainClass.getClassNumber());
    }

    @Test
    public void testGetClassString() {
        String firstText = "hello";
        String secondText = "Hello";
        Assert.assertTrue("Text doesn't contain word 'Hello' or 'hello'",
                isTextContainsAnyOfTwoWords(MainClass.getClassString(), firstText, secondText));
    }

    private boolean isTextContainsAnyOfTwoWords(String text, String firstWord, String secondWord) {
        return text.contains(firstWord) | text.contains(secondWord);
    }
}
