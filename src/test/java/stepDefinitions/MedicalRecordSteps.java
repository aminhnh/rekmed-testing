package stepDefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.DataRekamMedisPage;
import org.example.KunjunganPage;
import org.example.PemeriksaanPage;
import org.example.PengecekanPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MedicalRecordSteps {

    KunjunganPage kunjunganPage;
    PengecekanPage pengecekanPage;
    PemeriksaanPage pemeriksaanPage;
    DataRekamMedisPage dataRekamMedisPage;
    Faker faker = new Faker();
    @Given("the user has added a patient to the queue")
    public void the_user_has_added_a_patient_to_the_queue() {
        Hooks.getDriver().get("https://old-app.rekmed.com/kunjungan/index");
        kunjunganPage = new KunjunganPage(Hooks.getDriver());
        kunjunganPage.clickAddPasienLama();
        kunjunganPage.setSearchPasienLama("Rikki");
        kunjunganPage.clickFirstPilihButton();
    }
    @Given("the user is on the pengecekan page")
    public void the_user_is_on_the_pengecekan_page() {
        Hooks.getDriver().get("https://old-app.rekmed.com/kunjungan/index");
        kunjunganPage = new KunjunganPage(Hooks.getDriver());
        kunjunganPage.clickProcessButton();
        pengecekanPage = new PengecekanPage(Hooks.getDriver());
    }
    @When("the user enters check-up data")
    public void the_user_enters_check_up_data() {
        pengecekanPage.setTekananDarah(faker.number().numberBetween(90, 180) + "/" + faker.number().numberBetween(60, 120));
        pengecekanPage.setNadi(String.valueOf(faker.number().numberBetween(50, 100)));
        pengecekanPage.setRespirasiRate(String.valueOf(faker.number().numberBetween(12, 20)));
        pengecekanPage.setSuhu(String.valueOf(faker.number().randomDouble(1, 35, 39)));
        pengecekanPage.setBeratBadan(String.valueOf(faker.number().numberBetween(50, 120)));
        pengecekanPage.setTinggiBadan(String.valueOf(faker.number().numberBetween(140, 200)));
        pengecekanPage.enterAlergiData(faker.lorem().sentence());
        pengecekanPage.enterKeluhanUtamaData(faker.lorem().sentence());
    }
    @When("the user saves the check-up data")
    public void the_user_saves_the_check_up_data() {
        pengecekanPage.clickSimpanSubmitButton();
        pemeriksaanPage = new PemeriksaanPage(Hooks.getDriver());

    }
    @When("the user enters examination data")
    public void the_user_enters_examination_data() {
        pemeriksaanPage.enterSubyekif(faker.lorem().sentence());
        pemeriksaanPage.enterObyektif(faker.lorem().sentence());
        pemeriksaanPage.enterAssessment(faker.lorem().sentence());
        pemeriksaanPage.enterPlan(faker.lorem().sentence());
    }
    @When("the user saves the examination data")
    public void the_user_saves_the_examination_data() {
        pemeriksaanPage.clickSimpanSelesaiButton();
        dataRekamMedisPage = new DataRekamMedisPage(Hooks.getDriver());
    }
    @Then("the examination and check-up data should be saved successfully")
    public void the_examination_and_check_up_data_should_be_saved_successfully() {
        WebDriverWait wait = new WebDriverWait(Hooks.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleIs("Data Rekam Medis"));

        assertAll(
                () -> assertTrue(dataRekamMedisPage.pageContains("Berhasil Menyimpan Data")),
                () -> assertEquals("Data Rekam Medis", dataRekamMedisPage.getWebTitle())
        );
    }
}
