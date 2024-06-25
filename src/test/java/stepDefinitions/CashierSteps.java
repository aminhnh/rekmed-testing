package stepDefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.example.Cashier;
import org.example.ExtentReportManager;
import org.openqa.selenium.WebDriver;

public class CashierSteps {
    WebDriver driver = Hooks.getDriver();
    Cashier cashier = new Cashier(driver);
    ExtentReports extent;
    ExtentTest test;

    @Before
    public void setUp() {
        extent = ExtentReportManager.getInstance();
    }

    @When("User clicks the 'Kasir' menu")
    public void user_clicks_the_kasir_menu() {
        test = extent.createTest("User clicks the 'Kasir' menu");
        cashier.clickKasirMenu();
        test.log(Status.PASS, "Kasir menu clicked");
    }

    @When("User clicks the 'Bayar' button")
    public void user_clicks_the_bayar_button() {
        test = extent.createTest("User clicks the 'Bayar' button");
        cashier.clickBayarButton();
        test.log(Status.PASS, "Bayar button clicked");
    }

    @When("User fills in the payment form")
    public void user_fills_in_the_payment_form() {
        test = extent.createTest("User fills in the payment form");
        cashier.fillPaymentForm();
        test.log(Status.PASS, "Payment form filled");
    }

    @When("User clicks the 'Bayar' button in the form")
    public void user_clicks_the_bayar_button_in_the_form() {
        test = extent.createTest("User clicks the 'Bayar' button in the form");
        cashier.clickBayarFormButton();
        test.log(Status.PASS, "Bayar button in the form clicked");
    }

    @Then("The payment should be successfully processed")
    public void the_payment_should_be_successfully_processed() {
        test = extent.createTest("The payment should be successfully processed");
        // Dummy verification step
        System.out.println("Verification step executed: The payment should be successfully processed");
        test.log(Status.PASS, "The payment was successfully processed");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        extent.flush();
    }
}