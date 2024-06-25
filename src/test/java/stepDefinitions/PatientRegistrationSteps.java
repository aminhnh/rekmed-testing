package stepDefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.github.javafaker.Faker;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.PatientRegistration;
import org.example.ResultPage;
import org.example.ExtentReportManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PatientRegistrationSteps {
    PatientRegistration patient;
    Faker faker = new Faker();
    ExtentReports extent;
    ExtentTest test;

    @Before
    public void setUp() {
        extent = ExtentReportManager.getInstance();
    }

    @Given("the user is on the patient registration page")
    public void the_user_is_on_the_patient_registration_page() {
        test = extent.createTest("the user is on the patient registration page");
        Hooks.getDriver().get("https://old-app.rekmed.com/kunjungan/index");
        patient = new PatientRegistration(Hooks.getDriver());
        test.log(Status.PASS, "Navigated to patient registration page");
    }

    @When("the user enters valid patient information")
    public void the_user_enters_valid_patient_information() throws InterruptedException {
        test = extent.createTest("the user enters valid patient information");
        patient.clickPasienBaruButton();

        WebDriverWait wait = new WebDriverWait(Hooks.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pasien-nama")));

        String noRekamMedis = String.valueOf(faker.number().randomNumber(5, true));
        String nama = faker.name().fullName();
        String noNik = faker.number().digits(10);
        String jenisKelamin = faker.options().option("Laki-Laki", "Perempuan");
        String alamat = faker.address().fullAddress();
        String nomorTelepon = faker.phoneNumber().phoneNumber();
        String email = faker.internet().emailAddress();
        String pekerjaan = faker.job().title();
        String penanggungjawab = faker.name().fullName();

        patient.setNoRekamMedis(noRekamMedis);
        patient.setNama(nama);
        patient.setNoNik(noNik);

        WebDriverWait waitDatePicker = new WebDriverWait(Hooks.getDriver(), Duration.ofSeconds(10));
        waitDatePicker.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[2]/div/div/div/div/div[2]/div/div[1]/div/div/div[2]/div/div/div/form/div[4]/div[3]/div[1]/table/tbody/tr[2]/td[4]"))).click();

        patient.setJenisKelamin(jenisKelamin);
        patient.setAlamat(alamat);
        patient.setNomorTelepon(nomorTelepon);
        patient.setEmail(email);
        patient.setPekerjaan(pekerjaan);
        patient.setPenanggungjawab(penanggungjawab);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#kunjungan-dokter_periksa > option:nth-child(2)")));
        Select selectDokter = new Select(Hooks.getDriver().findElement(By.id("kunjungan-dokter_periksa")));
        selectDokter.selectByIndex(1);

        test.log(Status.PASS, "Entered valid patient information");
    }

    @And("the user clicks the {string} button")
    public void the_user_clicks_the_button(String button) throws InterruptedException {
        test = extent.createTest("the user clicks the " + button + " button");
        WebElement submitButton = Hooks.getDriver().findElement(By.cssSelector("#form-pasien > div:nth-child(14) > input.btn.btn-success"));
        submitButton.click();
        Thread.sleep(3000);
        test.log(Status.PASS, button + " button clicked");
    }

    @Then("the user should see a confirmation message")
    public void the_user_should_see_a_confirmation_message() {
        test = extent.createTest("the user should see a confirmation message");
        WebDriverWait wait = new WebDriverWait(Hooks.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://old-app.rekmed.com/pasien/index"));

        ResultPage resultPage = new ResultPage(Hooks.getDriver());

        assertAll(
                () -> assertEquals("Pasien", resultPage.getWebTitle()),
                () -> assertEquals("https://old-app.rekmed.com/pasien/index", resultPage.getCurrentUrl()),
                () -> assertEquals("Pasien berhasil ditambahkan.", Hooks.getDriver().findElement(By.xpath("/html/body/div[3]/div[2]/div/div[1]/span")).getText())
        );
        test.log(Status.PASS, "Confirmation message displayed");
    }

    @After
    public void tearDown() {
        if (Hooks.getDriver() != null) {
            Hooks.getDriver().quit();
        }
        extent.flush();
    }
}