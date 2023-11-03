package org.canvusapps;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class SignUpScenario {
    public static void main(String[] args) throws InterruptedException {

        Faker fakeDataGenerator = new Faker();
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://prd.canvusapps.com/signup");

        WebElement fullNameTextField = driver.findElement(By.id("user_name"));
        fullNameTextField.clear();
        fullNameTextField.sendKeys(fakeDataGenerator.name().firstName());

        WebElement emailTextField = driver.findElement(By.cssSelector("[id='user_email']"));
        emailTextField.clear();
        emailTextField.sendKeys(fakeDataGenerator.internet().emailAddress());

        String password = fakeDataGenerator.internet().password();
        System.out.println("password = " + password);
        WebElement passwordTextField = driver.findElement(By.cssSelector("[id='user_password']"));
        passwordTextField.clear();
        passwordTextField.sendKeys(password);

        WebElement confirmPasswordTextField = driver.findElement(By.xpath("//*[@id='user_password_confirmation']"));
        confirmPasswordTextField.clear();
        confirmPasswordTextField.sendKeys(password);

        WebElement companyNameTextField = driver.findElement(By.name("company[name]"));
        companyNameTextField.clear();
        companyNameTextField.sendKeys(fakeDataGenerator.gameOfThrones().house());

        driver.findElement(By.name("commit")).click();

        String signUpMessage = driver.findElement(By.cssSelector("[class=\"alert alert-error alert-block error\"]")).getText();

        System.out.println(signUpMessage);
        if (signUpMessage.equals("We couldn't setup your account. Please try a different browser or device.")) {
            System.out.println("Message is correct!");
        }
        else {
            System.out.println("Message is not correct!");
        }

        String currentUrl = driver.getCurrentUrl();

        if(currentUrl.equals("https://prd.canvusapps.com/signup")) {
            System.out.println("A current URL is correct");
            System.out.println(currentUrl);
        }
        else {
            System.out.println("A current URL is incorrect...");
            System.out.println(currentUrl);
        }



        Thread.sleep(2000);
        driver.quit();


    }
}
