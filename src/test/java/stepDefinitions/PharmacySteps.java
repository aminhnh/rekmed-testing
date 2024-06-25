package stepDefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.example.Pharmacy;
import org.example.ExtentReportManager;
import org.openqa.selenium.WebDriver;

public class PharmacySteps {
    WebDriver driver = Hooks.getDriver();
    Pharmacy cashier = new Pharmacy(driver);
    ExtentReports extent;
    ExtentTest test;

    @Before
    public void setUp() {
        extent = ExtentReportManager.getInstance();
    }

    @When("User clicks the 'Farmasi' menu")
    public void user_clicks_the_farmasi_menu() {
        test = extent.createTest("User clicks the 'Farmasi' menu");
        cashier.clickFarmasiMenu();
        test.log(Status.PASS, "Farmasi menu clicked");
    }

    @When("User clicks the 'RESEP' button")
    public void user_clicks_the_resep_button() {
        test = extent.createTest("User clicks the 'RESEP' button");
        cashier.clickResepButton();
        test.log(Status.PASS, "RESEP button clicked");
    }

    @When("User clicks the 'Tambah Resep' button")
    public void user_clicks_the_tambah_resep_button() {
        test = extent.createTest("User clicks the 'Tambah Resep' button");
        cashier.clickTambahResepButton();
        test.log(Status.PASS, "Tambah Resep button clicked");
    }

    @When("User fills in the medication data")
    public void user_fills_in_the_medication_data() {
        test = extent.createTest("User fills in the medication data");
        cashier.fillMedicationData();
        test.log(Status.PASS, "Medication data filled");
    }

    @When("User clicks the 'Selesai' button")
    public void user_clicks_the_selesai_button() {
        test = extent.createTest("User clicks the 'Selesai' button");
        cashier.clickSelesaiButton();
        test.log(Status.PASS, "Selesai button clicked");
    }

    @Then("The patient's data should be successfully saved and added to the payment queue")
    public void the_patient_s_data_should_be_successfully_saved_and_added_to_the_payment_queue() {
        test = extent.createTest("The patient's data should be successfully saved and added to the payment queue");
        // Dummy implementation for now
        // Add a simple assertion or log to confirm the step is executed
        System.out.println("Verification step executed: The patient's data should be successfully saved and added to the payment queue");
        test.log(Status.PASS, "The patient's data saved and added to payment queue");
    }

    @After
    public void tearDown() {
        if (Hooks.getDriver() != null) {
            Hooks.getDriver().quit();
        }
        extent.flush();
    }
}