package org.sampleCode;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SequentialURLTest {

    public static void main(String[] args) {
        // Set path for WebDriver executable (adjust path as necessary)
      //  System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        try {
            // Define URLs to be opened
            String[] urls = {
                    "https://www.example.com",
                    "https://www.google.com",
                    "https://www.bing.com"
            };

            // Loop through each URL
            for (String url : urls) {
               // Open the URL
                driver.get(url);
                System.out.println("Opened URL: " + url);

                // Perform actions on the current page (e.g., wait for elements, assertions, etc.)
                // You can add any specific actions here (e.g., driver.findElement(...), driver.getTitle(), etc.)
                System.out.println("Title of the page: " + driver.getTitle());

                // Optional: Add a wait if necessary (e.g., Thread.sleep() or WebDriverWait)
                Thread.sleep(2000);  // Example wait for 2 seconds

                // Close the current window/tab
                System.out.println("Closing the current window/tab: " + url);
                driver.close();

                // Reinitialize WebDriver if needed to open the next URL
                driver = new ChromeDriver();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Quit the browser (if it's still open)
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
