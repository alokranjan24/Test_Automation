package org.sampleCode;

import org.utilities.TestDataGeneratorUtil;

import java.time.LocalDate;

public class DataGeneratorTest {
    public static void main(String[] args){
        System.out.println("Random Integer: " + TestDataGeneratorUtil.getRandomInt(10));
        System.out.println("Random Double: " + TestDataGeneratorUtil.getRandomDouble(0.0, 1.0));
        System.out.println("Random Boolean: " + TestDataGeneratorUtil.getRandomBoolean());
        System.out.println("Random String: " + TestDataGeneratorUtil.getRandomString(5));
        System.out.println("Random Alphanumeric: " + TestDataGeneratorUtil.getRandomAlphanumeric(5));
        System.out.println("Random Email: " + TestDataGeneratorUtil.getRandomEmailID(5));
        System.out.println("Random UID: " + TestDataGeneratorUtil.getRandomUID());
        System.out.println("Random Date: " + TestDataGeneratorUtil.getRandomDate(LocalDate.of(2020, 1, 1),
                LocalDate.of(2024, 1, 1)));
    }
}
