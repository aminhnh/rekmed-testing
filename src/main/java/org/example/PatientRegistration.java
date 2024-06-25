package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PatientRegistration {
    WebDriver driver;

    public PatientRegistration(WebDriver driver) {
        this.driver = driver;
    }

    By tambahPasienButton = By.xpath("/html/body/div[3]/div[2]/div/div/div/div/div[2]/div/p/button[2]");

    public void clickPasienBaruButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(tambahPasienButton));
        button.click();
    }

    public void setNoRekamMedis(String noRekamMedis) {
        driver.findElement(By.id("pasien-mr")).sendKeys(noRekamMedis);
    }

    public void setNama(String nama) {
        driver.findElement(By.id("pasien-nama")).sendKeys(nama);
    }

    public void setNoNik(String noNik) {
        driver.findElement(By.id("pasien-no_nik")).sendKeys(noNik);
    }

    public void setTanggaLahir(String tanggalLahir) {
        driver.findElement(By.id("pasien-tanggal_lahir")).click();
        selectDateFromDatePicker(tanggalLahir);
    }

    public void setJenisKelamin(String jenisKelamin) {
        Select select = new Select(driver.findElement(By.id("pasien-jk")));
        select.selectByVisibleText(jenisKelamin);
    }

    public void setAlamat(String alamat) {
        driver.findElement(By.id("pasien-alamat")).sendKeys(alamat);
    }

    public void setNomorTelepon(String nomorTelepon) {
        driver.findElement(By.id("pasien-no_telp")).sendKeys(nomorTelepon);
    }

    public void setEmail(String email) {
        driver.findElement(By.id("pasien-email")).sendKeys(email);
    }

    public void setPekerjaan(String pekerjaan) {
        driver.findElement(By.id("pasien-pekerjaan")).sendKeys(pekerjaan);
    }

    public void setPenanggungjawab(String penanggungjawab) {
        driver.findElement(By.id("pasien-penanggung_jawab")).sendKeys(penanggungjawab);
    }

    public void setDokterPeriksa(String dokterPeriksa) {
        Select select = new Select(driver.findElement(By.id("kunjungan-dokter_periksa")));
        select.selectByValue(dokterPeriksa);
    }

    public void clickSubmit() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#form-pasien > div:nth-child(14) > input.btn.btn-success")));
        button.click();
    }

    private void selectDateFromDatePicker(String date) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[2]/div/div/div/div/div[2]/div/div[1]/div/div/div[2]/div/div/div/form/div[4]/div[3]/div[1]/table/tbody/tr[2]/td[4]"))).click();
    }
}