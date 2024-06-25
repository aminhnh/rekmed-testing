package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
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
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SignUpSteps {
    SignUpPage signUpPage;
    ResultPage resultPage;

    @Given("the user is on the sign up page")
    public void the_user_is_on_the_sign_up_page() {
        Hooks.getDriver().get("https://old-app.rekmed.com/site/signup");
        signUpPage = new SignUpPage(Hooks.getDriver());
    }
    @When("user enters valid register credentials")
    public void user_enters_valid_register_credentials() {
        signUpPage.setUsername(UUID.randomUUID().toString().replaceAll("_", "").replaceAll("-", ""));
        signUpPage.setEmail(UUID.randomUUID().toString().replaceAll("_", "".replaceAll("-", "")) + "@gmail.com");
        signUpPage.setPassword("kelrekmed123");
        signUpPage.setPassword2("kelrekmed123");
        signUpPage.clickCheckbox();
        resultPage = signUpPage.clickSubmit();
    }
    @Then("the user should be redirected to the beranda page2")
    public void the_user_should_be_redirected_to_the_beranda_page2() {
        WebDriverWait wait = new WebDriverWait(Hooks.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://old-app.rekmed.com/site/index"));
        assertAll(
                () -> assertEquals("Rekam Medis", resultPage.getWebTitle()),
                () -> assertEquals("https://old-app.rekmed.com/site/index", resultPage.getCurrentUrl())
        );
    }
}