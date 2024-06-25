package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.PatientRegistration;
import org.example.ResultPage;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

public class PatientRegistrationSteps {
    PatientRegistration patient;


    @Given("the user is on the patient registration page")
    public void the_user_is_on_the_patient_registration_page() {
        Hooks.getDriver().get("https://old-app.rekmed.com/kunjungan/index");
        patient = new PatientRegistration(Hooks.getDriver());
    }

    @When("the user enters valid patient information")
    public void the_user_enters_valid_patient_information() throws InterruptedException {
        patient.clickPasienBaruButton();
        Thread.sleep(2000); //Tunggu sampe halamannya muncul
        patient.setNoRekamMedis("12345");
        patient.setNama("John Doe");
        patient.setNoNik("7187654321");
        patient.setTanggaLahir("1990-01-01");
        patient.setJenisKelamin("Male");
        patient.setAlamat("123 Main St");
        patient.setNomorTelepon("1234567890");
        patient.setEmail("john.doe@example.com");
        patient.setPekerjaan("Engineer");
        patient.setPenanggungjawab("Jane Doe");
        patient.setDokterPeriksa("Dr. Smith");
        patient.setImage("D:\\College\\Semester 4\\Praktikum Pengujian Perangkat Lunak\\RekMed\\img\\NationalGeographic_2572187_square.jpg");
    }

    @And("the user clicks the {string} button")
    public void the_user_clicks_the_button(String button) throws InterruptedException {
        patient.clickSubmit();
        Thread.sleep(3000);
    }

    @Then("the user should see a confirmation message")
    public void the_user_should_see_a_confirmation_message() {
        WebDriverWait wait = new WebDriverWait(Hooks.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://old-app.rekmed.com/kunjungan/index"));

        ResultPage resultPage = new ResultPage(Hooks.getDriver());

        assertAll(
                () -> assertEquals("Pendaftaran", resultPage.getWebTitle()),
                () -> assertEquals("https://old-app.rekmed.com/kunjungan/index", resultPage.getCurrentUrl()),
                () -> assertEquals("Pasien berhasil ditambahkan.", Hooks.getDriver().findElement(By.xpath("/html/body/div[3]/div[2]/div/div[1]/span")).getText())

        );
    }
}