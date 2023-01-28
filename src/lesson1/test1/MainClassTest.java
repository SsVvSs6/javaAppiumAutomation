package lesson1.test1;

import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {

    @Test
    public void testGetLocalNumber() {
        int expectedNumber = 14;
        Assert.assertEquals("Number is not 14", expectedNumber, MainClass.getLocalNumber());
    }
}
