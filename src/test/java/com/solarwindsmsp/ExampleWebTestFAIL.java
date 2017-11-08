package com.solarwindsmsp;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ExampleWebTestFAIL {

    private WebDriver driver;
    private String baseUrl;

    @BeforeMethod
    public void setUp() throws Exception {
        baseUrl = "http://www.letskodeit.com/";
        System.setProperty("webdriver.chrome.driver", "/Applications/selenium-2.53.1/chromedriver");
        driver = new ChromeDriver();

        // Maximize the browser's window
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.get(baseUrl);
    }

    @Test
    public void test1_invalidCredentials() throws Exception {
        WebElement signupLink = driver.findElement(By.id("comp-iiqg1vggactionTitle"));
        signupLink.click();

        WebElement loginLink = driver.findElement(By.id("signUpDialogswitchDialogLink"));
        loginLink.click();

        WebElement emailField = driver.findElement(By.xpath("//div[@id='memberLoginDialogemail']//input"));
        emailField.sendKeys("test1@email.com");

        WebElement passwordField = driver.findElement(By.xpath("//div[@id='memberLoginDialogpassword']//input"));
        passwordField.sendKeys("abcabc");

        // WebElement goButton = driver.findElement(By.id("memberLoginDialogsubmitButton"));
        //goButton.click();

// With recent webdriver and FF update, there might be issues in clicking the goButton.
// If you also encouter the issues, please comment the above code for goButton and un-comment the below code

        WebElement goButton =driver.findElement(By.xpath("//*[@id=\"memberLoginDialogoklink\"]"));
        Actions action = new Actions(driver);
        action.moveToElement(goButton).click().perform();

        Thread.sleep(3000);

        WebElement welcomeText = null;

        try {
            welcomeText = driver.findElement(By.xpath("//div[text()='Hello test@email.com']"));
        }
        catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
        Assert.assertTrue(welcomeText != null);
    }

    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }
}
