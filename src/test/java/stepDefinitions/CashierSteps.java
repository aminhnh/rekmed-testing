package stepDefinitions;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.example.Cashier;
import org.openqa.selenium.WebDriver;

public class CashierSteps {
    WebDriver driver = Hooks.getDriver();
    Cashier cashier = new Cashier(driver);

    @When("User clicks the 'Farmasi' menu")
    public void user_clicks_the_farmasi_menu() {
        cashier.clickFarmasiMenu();
    }

    @When("User clicks the 'RESEP' button")
    public void user_clicks_the_resep_button() {
        cashier.clickResepButton();
    }

    @When("User clicks the 'Tambah Resep' button")
    public void user_clicks_the_tambah_resep_button() {
        cashier.clickTambahResepButton();
    }

    @When("User fills in the medication data")
    public void user_fills_in_the_medication_data() {
        cashier.fillMedicationData();
    }

    @When("User clicks the 'Selesai' button")
    public void user_clicks_the_selesai_button() {
        cashier.clickSelesaiButton();
    }

    @Then("The patient's data should be successfully saved and added to the payment queue")
    public void the_patient_s_data_should_be_successfully_saved_and_added_to_the_payment_queue() {
        // Dummy implementation for now
        // Add a simple assertion or log to confirm the step is executed
        System.out.println("Verification step executed: The patient's data should be successfully saved and added to the payment queue");
    }
}
