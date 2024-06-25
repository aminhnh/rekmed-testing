package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.github.javafaker.Faker;

import java.time.Duration;

public class Pharmacy {
    private WebDriver driver;
    private Faker faker;

    public Pharmacy(WebDriver driver) {
        this.driver = driver;
        this.faker = new Faker();
    }

    private By farmasiMenuFullXPath = By.xpath("/html/body/div[3]/div[1]/div/ul/li[4]");
    private By resepButtonFullXPath = By.xpath("/html/body/div[3]/div[2]/div/div/div/div/div[2]/div/div[2]/div/table/tbody/tr[1]/td[5]/button");
    private By tambahResepButtonFullXPath = By.xpath("/html/body/div[3]/div[2]/div/div/div/div/div[2]/div/div[1]/div/div/div[2]/div/div[3]/div[2]/div/form/div[3]/div[2]/button[2]");
    private By medicationNameField = By.name("Obat[nama][resep][]");
    private By medicationAmountField = By.name("Obat[jumlah][resep][]");
    private By medicationSignaField = By.name("Obat[signa][resep][]");
    private By selesaiButtonFullXPath = By.xpath("/html/body/div[3]/div[2]/div/div/div/div/div[2]/div/div[1]/div/div/div[2]/div/div[3]/div[2]/div/form/button[2]");

    private WebElement waitForElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private void clickElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void clickFarmasiMenu() {
        clickElement(farmasiMenuFullXPath);
    }

    public void clickResepButton() {
        clickElement(resepButtonFullXPath);
    }

    public void clickTambahResepButton() {
        clickElement(tambahResepButtonFullXPath);
    }

    public void fillMedicationData() {
        driver.findElement(medicationNameField).sendKeys(faker.medical().medicineName());
        driver.findElement(medicationAmountField).sendKeys(String.valueOf(faker.number().numberBetween(1, 10)));
        driver.findElement(medicationSignaField).sendKeys(faker.lorem().sentence());
    }

    public void clickSelesaiButton() {
        clickElement(selesaiButtonFullXPath);
    }
}