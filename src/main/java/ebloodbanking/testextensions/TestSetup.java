package ebloodbanking.testextensions;

import ebloodbanking.factories.DriverFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

@Slf4j
public class TestSetup {
    /**
     * Note: We are breaking the java encapsulation rule here by making the driver public here. So why do I do this?
     * Because, (1) Each test gets its own copy of driver in BeforeEach method that gets destroyed in AfterEach method
     * (2) Each test class that extends it, uses a unique copy of it in "each testcase", so we can use it without affecting
     * other tests. If we don't make it public, our each test class would need this declaration to be done from within
     * the test classes and would also need to then close it from the same test class.
     */
    public WebDriver driver;

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
