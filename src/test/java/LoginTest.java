import org.example.LoginPage;
import org.example.ResultPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginTest {
    WebDriver driver;
    public static String BASE_URL = "";
    @BeforeEach
    public void setup() {
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
    }
    @AfterEach
    public void terminateBrowser() {
        driver.quit();
        this.driver = null;
    }
    @Test
    public void positiveLoginTest() {
        this.driver.get("https://old-app.rekmed.com/site/login");
        LoginPage login = new LoginPage(driver);

        login.setUsername("");
        login.setPassword("");
        ResultPage resultPage =  login.clickSubmit();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://old-app.rekmed.com/"));

        assertAll(
                () -> assertEquals("Rekam Medis", resultPage.getWebTitle()),
                () -> assertEquals("https://old-app.rekmed.com/", resultPage.getCurrentUrl())
        );
    }
}
