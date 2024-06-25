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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogoutSteps {
    LoginPage loginPage;
    ResultPage resultPage;
    LogoutPage logoutPage;
    ExtentReports extent;
    ExtentTest test;

    @Before
    public void setUp() {
        extent = ExtentReportManager.getInstance();
    }

    @Given("the user is logged in")
    public void the_user_is_logged_in() {
        test = extent.createTest("the user is logged in");
        Hooks.getDriver().get("https://old-app.rekmed.com/site/login");
        loginPage = new LoginPage(Hooks.getDriver());
        loginPage.setUsername("aminhnh");
        loginPage.setPassword("rekmed123");
        resultPage = loginPage.clickSubmit();
        WebDriverWait wait = new WebDriverWait(Hooks.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://old-app.rekmed.com/"));
        test.log(Status.PASS, "User logged in");
    }

    @When("the user clicks on {string} button")
    public void the_user_clicks_on_button(String button) {
        test = extent.createTest("the user clicks on logout button");
        logoutPage = new LogoutPage(Hooks.getDriver());
        logoutPage.clickLogout();
        test.log(Status.PASS, "Logout button clicked");
    }

    @Then("the system successfully displays the login page")
    public void the_system_successfully_displays_the_login_page() {
        test = extent.createTest("the system successfully displays the login page");
        WebDriverWait wait = new WebDriverWait(Hooks.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://old-app.rekmed.com/site/login"));
        assertEquals("https://old-app.rekmed.com/site/login", Hooks.getDriver().getCurrentUrl());
        assertAll(
                () -> assertEquals(true, logoutPage.isLoginPageDisplayed())
        );
        test.log(Status.PASS, "System displays login page");
    }

    @After
    public void tearDown() {
        if (Hooks.getDriver() != null) {
            Hooks.getDriver().quit();
        }
        extent.flush();
    }
}