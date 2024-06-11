package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.ResultPage;
import org.example.SignUpPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SignUpSteps {
    WebDriver driver2;
    SignUpPage signUpPage;
    ResultPage resultPage;
    public void setup() {
        driver2 = new ChromeDriver();
        this.driver2.manage().window().maximize();
    }
    public void cleanup() { driver2.quit(); }
    @Given("the user is on the sign up page")
    public void the_user_is_on_the_sign_up_page() {
        setup();
        this.driver2.get("https://old-app.rekmed.com/site/signup");
        signUpPage = new SignUpPage(driver2);
    }
    @When("user enters valid register credentials")
    public void user_enters_valid_register_credentials() {
        signUpPage.setUsername("kelrekmed");
        signUpPage.setEmail("kelrekmed@gmail.com");
        signUpPage.setPassword("kelrekmed123");
        signUpPage.setPassword2("kelrekmed123");
        signUpPage.clickCheckbox();
        resultPage = signUpPage.clickSubmit();
    }
    @Then("the user should be redirected to the beranda page2")
    public void the_user_should_be_redirected_to_the_beranda_page2() {
        WebDriverWait wait = new WebDriverWait(driver2, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://old-app.rekmed.com/site/index"));
        assertAll(
                () -> assertEquals("Rekam Medis", resultPage.getWebTitle()),
                () -> assertEquals("https://old-app.rekmed.com/site/index", resultPage.getCurrentUrl())
        );
        cleanup();
    }
}
