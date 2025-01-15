package org.utilities;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

public class TestDataGeneratorUtil {

    private static final Random random = new Random();

    //generate random integer of length n
    public static String getRandomInt(int length){
        String characters = "0123456789";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++){
            stringBuilder.append((characters.charAt(random.nextInt(characters.length()))));
        }
        return stringBuilder.toString();
    }

    //generate a random double between min and max
    public static double getRandomDouble(double min, double max){
        return min + (max-min) * random.nextDouble();
    }

    //generate a random boolean
    public static boolean getRandomBoolean(){
        return random.nextBoolean();
    }

    //generate random string of length n
    public static String getRandomString(int length){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++){
            stringBuilder.append((characters.charAt(random.nextInt(characters.length()))));
        }
        return stringBuilder.toString();
    }

    //generate random alphanumeric of length n
    public static String getRandomAlphanumeric(int length){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++){
            stringBuilder.append((characters.charAt(random.nextInt(characters.length()))));
        }
        return stringBuilder.toString();
    }

    //generate random email id of length n
    public static String getRandomEmailID(int length){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++){
            stringBuilder.append((characters.charAt(random.nextInt(characters.length()))));
        }
        return stringBuilder.toString().toLowerCase() + "@test.com";
    }

    //generate random uid
    public static String getRandomUID(){
        return UUID.randomUUID().toString();
    }

    //generate random lacal date between start date and end date
    public static LocalDate getRandomDate(LocalDate startDate, LocalDate endDate){
       long days = ChronoUnit.DAYS.between(startDate, endDate);
       return startDate.plusDays(random.nextInt((int) days +1));
    }

}
