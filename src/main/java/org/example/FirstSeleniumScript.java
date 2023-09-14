package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstSeleniumScript {
    public static void main(String[] args) throws InterruptedException {

        String currentUtl;
        String title;

        WebDriverManager.chromedriver().clearDriverCache().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://google.com");

        currentUtl = driver.getCurrentUrl();
        System.out.println("currentUtl = " + currentUtl);

        // Page source
//        String pageSource = driver.getPageSource();
//        System.out.println("pageSource = " + pageSource);

        title = driver.getTitle();
        System.out.println("title = " + title);

        if(currentUtl.equals("https://www.google.com/") && title.equals("Google")) {
            System.out.println("A navigation to Google succeeded!");
        }
        else {
            System.out.println("Oh No! A wrong place");
        }

        driver.navigate().to("https://www.amazon.com");


        title = driver.getTitle();
        System.out.println("title = " + title);

        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();

        currentUtl = driver.getCurrentUrl();
        System.out.println("currentUtl = " + currentUtl);

        if(currentUtl.equals("https://www.amazon.com////") && title.equals("Amazon.com. Spend less. Smile more.")) {
            System.out.println("A navigation to Amazon succeeded!");
        }
        else {
            System.out.println("Oh No! A wrong place");
        }

        driver.quit();

    }
}
