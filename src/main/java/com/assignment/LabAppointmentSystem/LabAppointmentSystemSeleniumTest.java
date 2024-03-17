package com.assignment.LabAppointmentSystem;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LabAppointmentSystemSeleniumTest {

    public static void main(String[] args) {
        // Set the path to your ChromeDriver
        System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");

        // Initialize ChromeDriver
        WebDriver driver = new ChromeDriver();

        try {
            // Test registering a user
            testRegisterUser(driver);

            // Test logging in with the registered user
            testLoginUser(driver);

        } finally {
            // Quit the WebDriver
            driver.quit();
        }
    }

    public static void testRegisterUser(WebDriver driver) {
        // Navigate to the registration page
        driver.get("http://localhost:3001/register");

        // Fill in the registration form
        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement emailInput = driver.findElement(By.id("email"));
        WebElement registerButton = driver.findElement(By.id("registerButton"));

        usernameInput.sendKeys("john");
        passwordInput.sendKeys("john123");
        emailInput.sendKeys("john@@gmail.com");

        // Submit the registration form
        registerButton.click();

        // Verify registration success message
        WebElement successMessage = driver.findElement(By.id("successMessage"));
        assert successMessage.getText().equals("User registered successfully");
    }

    public static void testLoginUser(WebDriver driver) {
        // Navigate to the login page
        driver.get("http://localhost:3001/login");

        // Fill in the login form
        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement emailInput = driver.findElement(By.id("email"));
        WebElement loginButton = driver.findElement(By.id("loginButton"));

        usernameInput.sendKeys("john");
        passwordInput.sendKeys("john123");
        emailInput.sendKeys("john@gmail.com");

        // Submit the login form
        loginButton.click();

        // Verify successful login
        WebElement successMessage = driver.findElement(By.id("successMessage"));
        assert successMessage.getText().equals("User");
    }
}

