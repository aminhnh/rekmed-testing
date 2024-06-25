package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogoutPage {
    private WebDriver driver;
    By logoutButton = By.xpath("/html/body/div[3]/div[1]/div/ul/li[10]/form/button");
    By loginPageElement = By.id("loginform-username"); // Sesuaikan dengan selector untuk elemen halaman login di aplikasi Anda

    public LogoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLogout() {
        driver.findElement(logoutButton).click();
    }

    public boolean isLoginPageDisplayed() {
        return driver.findElement(loginPageElement).isDisplayed();
    }
}