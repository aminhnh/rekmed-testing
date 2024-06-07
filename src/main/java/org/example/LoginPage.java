package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;
    By username = By.id("loginform-username");
    By password = By.id("loginform-password");
    By loginButton = By.xpath("/html/body/section/div[1]/div/form[1]/div[4]/div/button");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    public void setUsername(String usernameInput) {
        driver.findElement(username).sendKeys(usernameInput);
    }
    public void setPassword(String passwordInput) {
        driver.findElement(password).sendKeys(passwordInput);
    }
    public ResultPage clickSubmit() {
        driver.findElement(loginButton).click();
        return new ResultPage(driver);
    }
}
