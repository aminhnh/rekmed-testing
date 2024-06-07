package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.LoginPage;
import org.example.ResultPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginSteps {
    WebDriver driver;
    LoginPage searchPage;
    ResultPage resultPage;
    public void setup() {
        driver = new ChromeDriver();
    }
    public void cleanup() {
        driver.quit();
    }
    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
    }
    @When("user enters valid credentials")
    public void user_enters_valid_credentials() {
    }
    @Then("the user should be redirected to the beranda page")
    public void the_user_should_be_redirected_to_the_beranda_page() {
    }
}
