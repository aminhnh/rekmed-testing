package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DataRekamMedisPage {
    WebDriver driver;
    WebDriverWait wait;

    public DataRekamMedisPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public String getWebTitle() {
        return driver.getTitle();
    }
    public boolean pageContains(String text) {
        return driver.getPageSource().contains(text);
    }
}
