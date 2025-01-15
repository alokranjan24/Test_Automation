package org.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.testng.annotations.BeforeSuite;
import org.utilities.ClearCacheUtil;
import org.utilities.HealthCheckUtil;
import org.utilities.KafkaUtil;
import org.utilities.ReadWritePropertiesFileUtil;

import java.io.IOException;

public class Hooks {

    private int retryCount = 2;
    private static final int maxRetryCount = 3; //Set the maximum number of retries

    String filePath = "src/main/java/org/testData/testData.properties";
    String zsdDirectoryPath = ReadWritePropertiesFileUtil.getPropertyValue(filePath, "zsd_directory_path");
    String zspPaymentPath = ReadWritePropertiesFileUtil.getPropertyValue(filePath, "zsp_payment_path");
    String restartZSDDirectory = ReadWritePropertiesFileUtil.getPropertyValue(filePath, "zsd_directory_webservices");
    String restartZSPPayment = ReadWritePropertiesFileUtil.getPropertyValue(filePath, "zsp_payment_webservices");

    public Hooks() throws IOException{

    }

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("First Method");
        if(!HealthCheckUtil.healthCheckDirectoryApp().contains("CXC web services are up")){
            KafkaUtil.getSSHConnection();
            KafkaUtil.restartServer(zsdDirectoryPath, restartZSDDirectory);
        }
        if(!HealthCheckUtil.healthCheckPaymentApp().contains("Payment web services are up")){
            KafkaUtil.getSSHConnection();
            KafkaUtil.restartServer(zspPaymentPath, restartZSPPayment);
        }
    }

    @Before(order = 0)
    public void beforeScenario(Scenario scenario){
        System.out.println("\nStarting Scenarion: " + scenario.getName());
    }

    @After(order = 0)
    public void retryScenario(Scenario scenario){
        if(scenario.isFailed() && retryCount < maxRetryCount){
            retryCount++;
            System.out.println("Retrying Scenario: " + scenario.getName() + " - Attempt " + retryCount);
        } else if (retryCount >= maxRetryCount){
            System.out.println("Scenario failed after " + maxRetryCount + " attempts: " + scenario.getName());
        }
    }

    @After(order = 1)
    public void afterScenario(Scenario scenario){
        System.out.println("\nCompleted Scenario: " +scenario.getName() + ", Status: " + scenario.getStatus() + "\n");
        //ClearCacheUtil.clearCache(); //Clear cache
    }
}
