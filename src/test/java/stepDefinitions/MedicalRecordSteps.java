package stepDefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class MedicalRecordSteps {
    private WebDriver driver;
    private ExtentReports extent;
    private ExtentTest test;
    KunjunganPage kunjunganPage;
    PengecekanPage pengecekanPage;
    PemeriksaanPage pemeriksaanPage;
    DataRekamMedisPage dataRekamMedisPage;

    @Before
    public void setUp() {
        extent = ExtentReportManager.getInstance();
        driver = Hooks.getDriver();
    }

    @After
    public void tearDown() {
        if (Hooks.getDriver() != null) {
            Hooks.getDriver().quit();
        }
        if (test != null) {
            test.log(Status.PASS, "Browser closed");
            extent.flush();
        }
    }

    @Given("the user has added a patient to the queue")
    public void the_user_has_added_a_patient_to_the_queue() {
        test = extent.createTest("Test MedicalRecordSteps");
        test.log(Status.INFO, "Navigating to Kunjungan Page");
        Hooks.getDriver().get("https://old-app.rekmed.com/kunjungan/index");
        kunjunganPage = new KunjunganPage(Hooks.getDriver());
        kunjunganPage.clickAddPasienLama();
        kunjunganPage.setSearchPasienLama("Rikki");
        kunjunganPage.clickFirstPilihButton();
        test.log(Status.PASS, "Patient added to the queue successfully");
    }

    @Given("the user is on the pengecekan page")
    public void the_user_is_on_the_pengecekan_page() {
        test = extent.createTest("Test MedicalRecordSteps: Navigate to Pengecekan Page");
        test.log(Status.INFO, "Navigating to Kunjungan Page");
        Hooks.getDriver().get("https://old-app.rekmed.com/kunjungan/index");
        kunjunganPage = new KunjunganPage(Hooks.getDriver());
        kunjunganPage.clickProcessButton();
        pengecekanPage = new PengecekanPage(Hooks.getDriver());
        test.log(Status.PASS, "Navigated to Pengecekan Page successfully");
    }

    @When("the user enters check-up data")
    public void the_user_enters_check_up_data() {
        test = extent.createTest("Test MedicalRecordSteps: Enter Check-up Data");
        test.log(Status.INFO, "Entering check-up data");
        pengecekanPage.setTekananDarah("120/80");
        pengecekanPage.setNadi("70");
        pengecekanPage.setRespirasiRate("16");
        pengecekanPage.setSuhu("37.5");
        pengecekanPage.setBeratBadan("65");
        pengecekanPage.setTinggiBadan("170");
        pengecekanPage.enterAlergiData("No allergies");
        pengecekanPage.enterKeluhanUtamaData("Fever and headache");
        test.log(Status.PASS, "Check-up data entered successfully");
    }

    @When("the user saves the check-up data")
    public void the_user_saves_the_check_up_data() {
        test = extent.createTest("Test MedicalRecordSteps: Save Check-up Data");
        test.log(Status.INFO, "Saving check-up data");
        pengecekanPage.clickSimpanSubmitButton();
        pemeriksaanPage = new PemeriksaanPage(Hooks.getDriver());
        test.log(Status.PASS, "Check-up data saved successfully");
    }

    @When("the user enters examination data")
    public void the_user_enters_examination_data() {
        test = extent.createTest("Test MedicalRecordSteps: Enter Examination Data");
        test.log(Status.INFO, "Entering examination data");
        pemeriksaanPage.enterSubyekif("Patient complains of fever and headache");
        pemeriksaanPage.enterObyektif("Temperature: 37.5°C, other vital signs stable");
        pemeriksaanPage.enterAssessment("Diagnosis: Mild flu");
        pemeriksaanPage.enterPlan("Prescription: Paracetamol and rest");
        test.log(Status.PASS, "Examination data entered successfully");
    }

    @When("the user saves the examination data")
    public void the_user_saves_the_examination_data() {
        test = extent.createTest("Test MedicalRecordSteps: Save Examination Data");
        test.log(Status.INFO, "Saving examination data");
        pemeriksaanPage.clickSimpanSelesaiButton();
        dataRekamMedisPage = new DataRekamMedisPage(Hooks.getDriver());
        test.log(Status.PASS, "Examination data saved successfully");
    }

    @Then("the examination and check-up data should be saved successfully")
    public void the_examination_and_check_up_data_should_be_saved_successfully() {
        test = extent.createTest("Test MedicalRecordSteps: Verify Data Saved");
        test.log(Status.INFO, "Verifying data saved successfully");
        WebDriverWait wait = new WebDriverWait(Hooks.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleIs("Data Rekam Medis"));

        assertTrue(dataRekamMedisPage.pageContains("Berhasil Menyimpan Data"));
        assertEquals("Data Rekam Medis", dataRekamMedisPage.getWebTitle());

        test.log(Status.PASS, "Data saved successfully in Data Rekam Medis");
    }
}
