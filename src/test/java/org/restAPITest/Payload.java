package org.restAPITest;

public class Payload {

    public static String addPlace(){

        return "{\n" +
                "\t\t\"location\": {\n" +
                "\t\t\t\"lat\": -38.383494,\n" +
                "\t\t\t\"lng\": 33.427362\n" +
                "\t\t},\n" +
                "\t\t\"accuracy\": 50,\n" +
                "\t\t\"name\": \"Frontline house\",\n" +
                "\t\t\"phone_number\": \"(+91) 983 893 3937\",\n" +
                "\t\t\"address\": \"29, side layout, cohen 09\",\n" +
                "\t\t\"types\": [\n" +
                "\t\t\t\"shoe park\",\n" +
                "\t\t\t\"shop\"\n" +
                "\t\t ],\n" +
                "\t\t\"website\": \"http://google.com\",\n" +
                "\t\t\"language\": \"French-IN\"\n" +
                "\t}";

    }

    public static String coursePrice(){
        return "{\n" +
                "\n" +
                "\"dashboard\": {\n" +
                "\n" +
                "\"purchaseAmount\": 910,\n" +
                "\n" +
                "\"website\": \"rahulshettyacademy.com\"\n" +
                "\n" +
                "},\n" +
                "\n" +
                "\"courses\": [\n" +
                "\n" +
                "{\n" +
                "\n" +
                "\"title\": \"Selenium Python\",\n" +
                "\n" +
                "\"price\": 50,\n" +
                "\n" +
                "\"copies\": 6\n" +
                "\n" +
                "},\n" +
                "\n" +
                "{\n" +
                "\n" +
                "\"title\": \"Cypress\",\n" +
                "\n" +
                "\"price\": 40,\n" +
                "\n" +
                "\"copies\": 4\n" +
                "\n" +
                "},\n" +
                "\n" +
                "{\n" +
                "\n" +
                "\"title\": \"RPA\",\n" +
                "\n" +
                "\"price\": 45,\n" +
                "\n" +
                "\"copies\": 10\n" +
                "\n" +
                "}\n" +
                "\n" +
                "]\n" +
                "\n" +
                "}";

    }

}

