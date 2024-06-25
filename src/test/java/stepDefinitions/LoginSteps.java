package stepDefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.LoginPage;
import org.example.ResultPage;
import org.example.ExtentReportManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginSteps {
    LoginPage loginPage;
    ResultPage resultPage;
    ExtentReports extent;
    ExtentTest test;

    @Before
    public void setUp() {
        extent = ExtentReportManager.getInstance();
    }

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        test = extent.createTest("the user is on the login page");
        Hooks.getDriver().get("https://old-app.rekmed.com/site/login");
        loginPage = new LoginPage(Hooks.getDriver());
        test.log(Status.PASS, "Navigated to login page");
    }

    @When("user enters valid credentials")
    public void user_enters_valid_credentials() {
        test = extent.createTest("user enters valid credentials");
        loginPage.setUsername("aminhnh");
        loginPage.setPassword("rekmed123");
        resultPage = loginPage.clickSubmit();
        test.log(Status.PASS, "Entered valid credentials");
    }

    @Then("the user should be redirected to the beranda page")
    public void the_user_should_be_redirected_to_the_beranda_page() {
        test = extent.createTest("the user should be redirected to the beranda page");
        WebDriverWait wait = new WebDriverWait(Hooks.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://old-app.rekmed.com/"));
        assertAll(
                () -> assertEquals("Rekam Medis", resultPage.getWebTitle()),
                () -> assertEquals("https://old-app.rekmed.com/", resultPage.getCurrentUrl())
        );
        test.log(Status.PASS, "User redirected to beranda page");
    }

    @After
    public void tearDown() {
        if (Hooks.getDriver() != null) {
            Hooks.getDriver().quit();
        }
        extent.flush();
    }
}