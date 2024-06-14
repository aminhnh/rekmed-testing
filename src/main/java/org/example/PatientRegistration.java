package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PatientRegistration {
    private WebDriver driver;
    By noRekamMedis = By.id("inputgroup-no_nik");
    By nama = By.id("formgroup-nama");
    By noNik = By.id("formgroup-no_nik");
    By tanggaLahir = By.id("formgroup-tanggal_lahir");
    By jenisKelamin = By.id("formgroup-jk");
    By alamat = By.id("formgroup-alamat");
    By nomorTelepon = By.id("formgroup-no_telp");
    By email = By.id("formgroup-email");
    By pekerjaan = By.id("formgroup-pekerjaan");
    By penanggungjawab = By.id("formgroup-penanggung_jawab");
    By dokterPeriksa = By.id("formgroup-dokter_periksa");
    By image = By.id("formgroup-imagefile");
    By simpanTambahButton = By.xpath("/html/body/div[3]/div[2]/div/div/div/div/div[2]/div/div[1]/div/div/div[2]/div/div/div/form/div[13]/input[2]");

    public PatientRegistration(WebDriver driver) {
        this.driver = driver;
    }
    public void setNoRekamMedis(String noRekamMedisInput) {
        driver.findElement(noRekamMedis).sendKeys(noRekamMedisInput);
    }
    public void setNama(String namaInput) {
        driver.findElement(nama).sendKeys(namaInput);
    }
    public void setNoNik(String noNikInput) {
        driver.findElement(noNik).sendKeys(noNikInput);
    }
    public void setTanggaLahir(String tanggaLahirInput) {
        driver.findElement(tanggaLahir).sendKeys(tanggaLahirInput);
    }
    public void setJenisKelamin(String jenisKelaminInput) {
        driver.findElement(jenisKelamin).sendKeys(jenisKelaminInput);
    }
    public void setAlamat(String alamatInput) {
        driver.findElement(alamat).sendKeys(alamatInput);
    }
    public void setNomorTelepon(String nomorTeleponInput) {
        driver.findElement(nomorTelepon).sendKeys(nomorTeleponInput);
    }
    public void setEmail(String emailInput) {
        driver.findElement(email).sendKeys(emailInput);
    }
    public void setPekerjaan(String pekerjaanInput) {
        driver.findElement(pekerjaan).sendKeys(pekerjaanInput);
    }
    public void setPenanggungjawab(String penanggungjawabInput) {
        driver.findElement(penanggungjawab).sendKeys(penanggungjawabInput);
    }
    public void setDokterPeriksa(String dokterPeriksaInput) {
        driver.findElement(dokterPeriksa).sendKeys(dokterPeriksaInput);
    }
    public void setImage(String imageInput) {
        driver.findElement(image).sendKeys(imageInput);
    }
    public ResultPage clickSubmit() {
        driver.findElement(simpanTambahButton).click();
        return new ResultPage(driver);
    }
}
