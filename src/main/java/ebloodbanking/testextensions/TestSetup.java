package ebloodbanking.testextensions;

import ebloodbanking.factories.DriverFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

@Slf4j
public class TestSetup {
    /**
     * Note: We need a driver instance in test classes while initiating page classes example: loginPage = new LoginPage(driver)
     * Now we got two choices:
     * 1) Either create a private instance of driver in each test class and then we would need to also delete it from
     * the same test class.
     * 2) Or create a protected one here and extend/inherit it in all test classes.
     * Since we are using BeforeEach to initialize/create a new driver instance for each test, we can afford to keep
     * it here as protected and use it in each test class as an inherited variable. This is purely for convenience
     * reasons and to avoid duplication that will otherwise arise by creating a test instance in each test class.
     */
    protected WebDriver driver;

    @BeforeEach
    public void preProcessing() {
        driver = DriverFactory.getDriver();
    }

    @AfterEach
    public void postProcessing() {
        driver.close();
        driver.quit();
        log.info("tear down complete");
    }
}
