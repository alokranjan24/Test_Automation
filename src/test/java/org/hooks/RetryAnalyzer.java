package org.hooks;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 2;
    private static final int maxRetryCount = 3; //Set the maximum number of retries


    @Override
    public boolean retry(ITestResult iTestResult) {
        if(retryCount < maxRetryCount){
            retryCount++;
            return true;
        }
        return false;
    }
}
