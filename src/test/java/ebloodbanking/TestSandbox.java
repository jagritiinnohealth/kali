package ebloodbanking;

import com.typesafe.config.Config;
import ebloodbanking.config.EnvFactory;
import ebloodbanking.factories.DriverFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class TestSandbox {
    private static Config config = EnvFactory.getInstance().getConfig();
    private static final String HOME_PAGE_URL = config.getString("HOME_PAGE_URL");
    private WebDriver driver = DriverFactory.getDriver();

    @BeforeEach
    void setup() {
        driver.get(HOME_PAGE_URL);
    }

    @Tag("smokeTest")
    @DisplayName("@Amit: This test is for demo purpose only to show that the basic code works. " +
            "Actual one would not involve drivers in the test classes but would work with pages and page actions.")
    @Test
    void assertThatHomePageTitleIsCorrect() {
        assertEquals("eBloodBanking | Jagriti Blood Bank Management System", driver.getTitle());
    }

    @AfterEach
    void teardown() {
        driver.close();
        driver.quit();
        log.info("tear down complete");
    }
}