package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class KunjunganPage {
    private WebDriver driver;
    By queueTableXPath = By.xpath("/html/body/div[3]/div[2]/div/div/div/div/div[2]/div/div[2]/div/table");

    public KunjunganPage(WebDriver driver) {
        this.driver = driver;
    }
    public boolean isPatientInQueue() {
        WebElement queueTable = driver.findElement(queueTableXPath);
        List<WebElement> rows = queueTable.findElements(By.xpath(".//tbody/tr"));

        for (WebElement row : rows) {
            WebElement statusCell = row.findElement(By.xpath(".//td[5]"));

            if (statusCell.getText().equalsIgnoreCase("antri")) {
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
