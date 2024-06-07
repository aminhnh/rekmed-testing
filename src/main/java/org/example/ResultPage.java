package org.example;

import org.openqa.selenium.WebDriver;

public class ResultPage {
    private WebDriver driver;
    public ResultPage(WebDriver driver) {
        this.driver = driver;
    }
    public String getWebTitle() {
        return driver.getTitle();
    }
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
