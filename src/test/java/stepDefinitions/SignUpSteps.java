package stepDefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.ResultPage;
import org.example.SignUpPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SignUpSteps {
    SignUpPage signUpPage;
    ResultPage resultPage;
    Faker faker = new Faker();

    @Given("the user is on the sign up page")
    public void the_user_is_on_the_sign_up_page() {
        Hooks.getDriver().get("https://old-app.rekmed.com/site/signup");
        signUpPage = new SignUpPage(Hooks.getDriver());
    }

    @When("user enters valid register credentials")
    public void user_enters_valid_register_credentials() {
        String username = faker.name().username();
        String email = faker.internet().emailAddress();
        String password = "kelrekmed123";

        signUpPage.setUsername(username);
        signUpPage.setEmail(email);
        signUpPage.setPassword(password);
        signUpPage.setPassword2(password);
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