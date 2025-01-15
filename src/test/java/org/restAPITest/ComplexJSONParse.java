package org.restAPITest;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;

public class ComplexJSONParse {

    public static void main(String[] args){

        JsonPath js = new JsonPath(Payload.coursePrice());

//1. Print No of courses returned by API
        int size = js.getInt("courses.size()");
        System.out.println("No of courses: "+size +"\n");

//2.Print Purchase Amount
        int purchaseAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println("Purchase Amount: "+purchaseAmount +"\n");

//3. Print Title of the first course
        String titleFirstCourse = js.getString("courses[0].title");
        System.out.println("Title of the first course: " +titleFirstCourse +"\n");

//4. Print All course titles and their respective Prices
        System.out.println("All course titles and their respective Prices: ");
        for(int i = 0; i<size;i++){
            System.out.println(js.getString("courses["+i+"].title"));
            System.out.println(js.getInt("courses["+i+"].price"));
        }

//5. Print no of copies sold by RPA Course
        System.out.println( "\n"+ "No. of copies sold by RPA Course: ");
        for(int i = 0; i<size;i++){
            if(js.getString("courses["+i+"].title").equals("RPA")){
                System.out.println("Course: "+js.getString("courses["+i+"].title"));
                System.out.println("Copies: "+js.getInt("courses["+i+"].copies"));
                break;
            }
        }

//6. Verify if Sum of all Course prices matches with Purchase Amount
        int coursePrice=0;
        for(int i = 0; i<size;i++){
            int sum = js.getInt("courses["+i+"].price");
            int copies = js.getInt("courses["+i+"].copies");
            coursePrice = coursePrice+(sum*copies);
        }

        Assert.assertEquals(coursePrice,purchaseAmount);
    }
}
