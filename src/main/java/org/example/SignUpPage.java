package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpPage {
    private WebDriver driver2;
    By username = By.id("signupform-username");
    By email = By.id("signupform-email");
    By password = By.id("signupform-password");
    By password2 = By.id("signupform-password2");
    By checkbox = By.xpath("/html/body/section/div/div/form/div[5]/div/div/div/label");
    By signupbutton = By.xpath("/html/body/section/div/div/form/div[6]/div/button");

    public SignUpPage(WebDriver driver) { this.driver2 = driver; }
    public void setUsername(String usernameInput) { driver2.findElement(username).sendKeys(usernameInput); }
    public void setEmail(String emailInput) { driver2.findElement(email).sendKeys(emailInput); }
    public void setPassword(String passwordInput) { driver2.findElement(password).sendKeys(passwordInput); }
    public void setPassword2(String password2Input) { driver2.findElement(password2).sendKeys(password2Input); }
    public void clickCheckbox()  {
        driver2.findElement(checkbox).click();
    }
    public ResultPage clickSubmit() {
        driver2.findElement(signupbutton).click();
        return new ResultPage(driver2);
    }


}
