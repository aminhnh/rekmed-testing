package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Cashier {
    private WebDriver driver;

    public Cashier(WebDriver driver) {
        this.driver = driver;
    }

    private By kasirMenuFullXPath = By.xpath("/html/body/div[3]/div[1]/div/ul/li[5]");
    private By bayarButtonSelector = By.cssSelector("#w0 > table > tbody > tr:nth-child(1) > td:nth-child(5) > button");
    private By paymentFormFieldFullXPath = By.xpath("//*[@id='bayar-bayar']");
    private By bayarFormButtonSelector = By.cssSelector("#form-bayar > div.form-group > button");

    private WebElement waitForElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private void clickElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        if (element != null) {
            element.click();
        } else {
            throw new IllegalArgumentException("Element not found: " + locator.toString());
        }
    }

    public void clickKasirMenu() {
        clickElement(kasirMenuFullXPath);
    }

    public void clickBayarButton() {
        clickElement(bayarButtonSelector);
    }

    public void fillPaymentForm() {
        WebElement paymentField = waitForElement(paymentFormFieldFullXPath);
        paymentField.sendKeys("100000");  // Use dummy data
    }

    public void clickBayarFormButton() {
        clickElement(bayarFormButtonSelector);
    }
}
