package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.LoginPage;
import org.example.ResultPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginSteps {
    WebDriver driver;
    LoginPage loginPage;
    ResultPage resultPage;
    public void setup() {
        driver = new ChromeDriver();
        this.driver.manage().window().maximize();
    }
    public void cleanup() {
        driver.quit();
    }
    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        setup();
        this.driver.get("https://old-app.rekmed.com/site/login");
        loginPage = new LoginPage(driver);
    }
    @When("user enters valid credentials")
    public void user_enters_valid_credentials() {
        loginPage.setUsername("");
        loginPage.setPassword("");
        resultPage =  loginPage.clickSubmit();
    }
    @Then("the user should be redirected to the beranda page")
    public void the_user_should_be_redirected_to_the_beranda_page() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://old-app.rekmed.com/"));
        assertAll(
                () -> assertEquals("Rekam Medis", resultPage.getWebTitle()),
                () -> assertEquals("https://old-app.rekmed.com/", resultPage.getCurrentUrl())
        );
        cleanup();
    }
}
