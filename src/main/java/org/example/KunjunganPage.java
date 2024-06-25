package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class KunjunganPage {
    private WebDriver driver;
    WebDriverWait wait;
    By queueTableXPath = By.xpath("/html/body/div[3]/div[2]/div/div/div/div/div[2]/div/div[2]/div/table");
    By addPasienLamaButton = By.xpath("/html/body/div[3]/div[2]/div/div/div/div/div[2]/div/p/button[1]");
    By cariPasienInput = By.id("cari-pasien");
    By pilihButton = By.cssSelector("button.pilih-pasien.btn.btn-primary");

    public KunjunganPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public void clickAddPasienLama() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(addPasienLamaButton));
        button.click();
    }
    public void setSearchPasienLama(String query) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(cariPasienInput));
        input.sendKeys(query);
    }
    public void clickFirstPilihButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(pilihButton));
        button.click();
        WebDriverWait waitReload = new WebDriverWait(driver, Duration.ofSeconds(20));
        waitReload.until(ExpectedConditions.stalenessOf(button));

    }
    public boolean hasPatientWithStatus(String status) {
        WebElement queueTable = driver.findElement(queueTableXPath);
        List<WebElement> rows = queueTable.findElements(By.xpath(".//tbody/tr"));

        for (WebElement row : rows) {
            WebElement statusCell = row.findElement(By.xpath(".//td[5]"));

            if (statusCell.getText().equalsIgnoreCase(status)) {
                return true;
            }
        }
        return false;
    }
    public void clickProcessButton() {
        WebElement queueTable = driver.findElement(queueTableXPath);
        List<WebElement> rows = queueTable.findElements(By.xpath(".//tbody/tr"));

        for (WebElement row : rows) {
            WebElement statusCell = row.findElement(By.xpath(".//td[5]"));

            if (statusCell.getText().equalsIgnoreCase("antri")) {
                WebElement processButton = row.findElement(By.xpath(".//a[@title='Proses']"));
                processButton.click();
                break;
            }
        }
    }
}
