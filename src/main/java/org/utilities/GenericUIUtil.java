package org.utilities;

import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class GenericUIUtil {

    WebDriver driver;

    public GenericUIUtil(WebDriver driver) {
    }

    public static int getRandomNumber(){
// create instance of Random class
        Random rand = new Random();

// Generate random integers in range 0 to 999
        int rand_int = rand.nextInt(1000);
        return rand_int;

    }

    public static int getRandomNumber_FC(){
// create instance of Random class
        Random rand = new Random();

// Generate random integers in range 0 to 9999999
        int rand_int = rand.nextInt(10000000);
        return rand_int;

    }

    public void attachScreenshot(Scenario scenario) throws IOException {
        if(scenario.isFailed()) {
            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            byte[] fileContent = FileUtils.readFileToByteArray(screenshot);
// scenario.attach(fileContent, "image/png", "image1");
        }
    }

    public void uploadFile(String fileName) throws AWTException {
// file path passed as parameter to StringSelection
        StringSelection s = new StringSelection(fileName);
// Clipboard copy
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s,null);
// Robot object creation
        Robot r = new Robot();
//pressing enter
        r.keyPress(KeyEvent.VK_ENTER);
//releasing enter
        r.keyRelease(KeyEvent.VK_ENTER);
//pressing ctrl+v
        r.keyPress(KeyEvent.VK_CONTROL);
        r.keyPress(KeyEvent.VK_V);
//releasing ctrl+v
        r.keyRelease(KeyEvent.VK_CONTROL);
        r.keyRelease(KeyEvent.VK_V);
//pressing enter
        r.keyPress(KeyEvent.VK_ENTER);
//releasing enter
        r.keyRelease(KeyEvent.VK_ENTER);
    }

    public void scrollPageDownToElement(By by){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", by);
    }

}