package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.PatientRegistration;
import org.example.ResultPage;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PatientRegistrationSteps {

    WebDriver driver;
    PatientRegistration patient;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("the user is on the patient registration page")
    public void the_user_is_on_the_patient_registration_page() {
        driver.get("https://old-app.rekmed.com/kunjungan/index");
        patient = new PatientRegistration(driver);
    }

    @When("the user enters valid patient information")
    public void the_user_enters_valid_patient_information() {
        patient.setNoRekamMedis("12345");
        patient.setNama("John Doe");
        patient.setNoNik("7187654321");
        patient.setTanggaLahir("01-01-1990");
        patient.setJenisKelamin("Male");
        patient.setAlamat("123 Main St");
        patient.setNomorTelepon("1234567890");
        patient.setEmail("john.doe@example.com");
        patient.setPekerjaan("Engineer");
        patient.setPenanggungjawab("Jane Doe");
        patient.setDokterPeriksa("Dr. Smith");
        patient.setImage("/path/to/image.jpg");
    }

    @When("the user clicks the {string} button")
    public void the_user_clicks_the_button(String button) {
        if (button.equalsIgnoreCase("simpanTambahButton")) {
            patient.clickSubmit();
        }
    }

    @Then("the user should see a confirmation message")
    public void the_user_should_see_a_confirmation_message() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://old-app.rekmed.com/kunjungan/index"));

        ResultPage resultPage = new ResultPage(driver);

        assertAll(
                () -> assertEquals("Rekam Medis", resultPage.getWebTitle()),
                () -> assertEquals("https://old-app.rekmed.com/kunjungan/index", resultPage.getCurrentUrl())
        );
    }
}
