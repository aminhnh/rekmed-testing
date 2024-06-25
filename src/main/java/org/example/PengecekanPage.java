package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PengecekanPage {
    WebDriver driver;
    By tekananDarahField = By.id("rekammedis-tekanan_darah");
    By nadiField = By.id("rekammedis-nadi");
    By respirasiRateField = By.id("rekammedis-respirasi_rate");
    By suhuField = By.id("rekammedis-suhu");
    By beratBadanField = By.id("rekammedis-berat_badan");
    By tinggiBadanField = By.id("rekammedis-tinggi_badan");
    By alergiRedactorEditor = By.xpath("//div[contains(@class, 'redactor-box')][1]//div[contains(@class, 'redactor-editor')]");
    By keluhanUtamaRedactorEditor = By.xpath("/html/body/div[3]/div[2]/div/div/form/div[2]/div/div/div[3]/div/div[1]/div[5]/div/div");
    By inputDateButton = By.xpath("//button[contains(@class, 'inputTglbaru')]");
    By simpanSubmitButton = By.id("simpan_submit");

    public PengecekanPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setTekananDarah(String tekananDarah) {
        driver.findElement(tekananDarahField).sendKeys(tekananDarah);
    }
    public void setNadi(String nadi) {
        driver.findElement(nadiField).sendKeys(nadi);
    }
    public void setRespirasiRate(String respirasiRate) {
        driver.findElement(respirasiRateField).sendKeys(respirasiRate);
    }
    public void setSuhu(String suhu) {
        driver.findElement(suhuField).sendKeys(suhu);
    }
    public void setBeratBadan(String beratBadan) {
        driver.findElement(beratBadanField).clear();
        driver.findElement(beratBadanField).sendKeys(beratBadan);
    }
    public void setTinggiBadan(String tinggiBadan) {
        driver.findElement(tinggiBadanField).clear();
        driver.findElement(tinggiBadanField).sendKeys(tinggiBadan);
    }

    public void enterAlergiData(String alergiInfo) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dateButton = wait.until(ExpectedConditions.elementToBeClickable(inputDateButton));
        dateButton.click();
        WebElement editor = wait.until(ExpectedConditions.visibilityOfElementLocated(alergiRedactorEditor));
        editor.sendKeys(alergiInfo);
    }
    public void enterKeluhanUtamaData(String keluhanUtama) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement editor = wait.until(ExpectedConditions.visibilityOfElementLocated(keluhanUtamaRedactorEditor));
        editor.sendKeys(keluhanUtama);
    }
    public void clickSimpanSubmitButton() {
        WebElement button = driver.findElement(simpanSubmitButton);
        button.click();
    }
}
