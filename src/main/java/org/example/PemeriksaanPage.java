package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PemeriksaanPage {
    WebDriver driver;
    WebDriverWait wait;
    By subyekifInput = By.id("rekammedis-anamnesis");
    By obyektifInput = By.id("rekammedis-pemeriksaan_fisik");
    By assessmentInput = By.id("rekammedis-assesment");
    By planInput = By.id("rekammedis-plan");
    By simpanSubmitButton = By.id("selesai_submit");
    public PemeriksaanPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public void enterSubyekif(String text) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(subyekifInput));
        input.clear();
        input.sendKeys(text);
    }
    public void enterObyektif(String text) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(obyektifInput));
        input.sendKeys(text);
    }
    public void enterAssessment(String text) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(assessmentInput));
        input.clear();
        input.sendKeys(text);
    }
    public void enterPlan(String text) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(planInput));
        input.clear();
        input.sendKeys(text);
    }
    public void clickSimpanSelesaiButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(simpanSubmitButton));
        button.click();
    }
}
