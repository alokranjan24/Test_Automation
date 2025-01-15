package org.allureReport;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureListener implements ITestListener {

    @Attachment(value = "Screenshot of {0}", type = "image/png")
    public byte[] saveScreenshot(String name, WebDriver driver) {
        return (byte[]) ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void onTestStart(ITestResult result){

    }

    @Override
    public void onTestSuccess(ITestResult result){

    }

    @Override
    public void onTestFailure(ITestResult result){

    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result){

    }

    @Override
    public void onStart(ITestContext context){

    }

    @Override
    public void onFinish(ITestContext context){

    }

}
