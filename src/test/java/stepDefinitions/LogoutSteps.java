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
import org.example.LogoutPage;
import org.example.ResultPage;
import org.example.ExtentReportManager;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LogoutSteps {
    LoginPage loginPage;
    ResultPage resultPage;
    LogoutPage logoutPage;
    ExtentReports extent;
    ExtentTest test;
    WebDriver driver;

    @Before
    public void setUp() {
        extent = ExtentReportManager.getInstance();
        driver = Hooks.getDriver();
    }

    @Given("the user is logged in")
    public void the_user_is_logged_in() {
        test = extent.createTest("Test LogoutSteps");
        test.log(Status.INFO, "Opening login page");

        driver.get("https://old-app.rekmed.com/site/login");
        loginPage = new LoginPage(driver);
        loginPage.setUsername("aminhnh");
        loginPage.setPassword("rekmed123");
        test.log(Status.INFO, "Logging in with username and password");

        resultPage = loginPage.clickSubmit();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://old-app.rekmed.com/"));

        test.log(Status.PASS, "User logged in successfully");
    }

    @When("the user clicks on {string} button")
    public void the_user_clicks_on_button(String button) {
        test = extent.createTest("Test LogoutSteps: User Logout Test");
        test.log(Status.INFO, "Clicking on logout button");

        logoutPage = new LogoutPage(driver);
        logoutPage.clickLogout();

        test.log(Status.PASS, "Logout button clicked successfully");
    }

    @Then("the system successfully displays the login page")
    public void the_system_successfully_displays_the_login_page() {
        test = extent.createTest("Test LogoutSteps: User Logout Verification Test");
        test.log(Status.INFO, "Verifying login page display after logout");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://old-app.rekmed.com/site/login"));

        assertEquals("https://old-app.rekmed.com/site/login", driver.getCurrentUrl());
        assertAll(
                () -> assertEquals(true, logoutPage.isLoginPageDisplayed())
        );

        test.log(Status.PASS, "Login page displayed successfully after logout");
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
}