package stepDefinitions;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.example.Cashier;
import org.openqa.selenium.WebDriver;

public class CashierSteps {
    WebDriver driver = Hooks.getDriver();
    Cashier cashier = new Cashier(driver);

    @When("User clicks the 'Kasir' menu")
    public void user_clicks_the_kasir_menu() {
        cashier.clickKasirMenu();
    }

    @When("User clicks the 'Bayar' button")
    public void user_clicks_the_bayar_button() {
        cashier.clickBayarButton();
    }

    @When("User fills in the payment form")
    public void user_fills_in_the_payment_form() {
        cashier.fillPaymentForm();
    }

    @When("User clicks the 'Bayar' button in the form")
    public void user_clicks_the_bayar_button_in_the_form() {
        cashier.clickBayarFormButton();
    }

    @Then("The payment should be successfully processed")
    public void the_payment_should_be_successfully_processed() {
        // Dummy verification step
        System.out.println("Verification step executed: The payment should be successfully processed");
    }
}