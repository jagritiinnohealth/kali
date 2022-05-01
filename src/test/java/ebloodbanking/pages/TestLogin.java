package ebloodbanking.pages;

import com.typesafe.config.Config;
import ebloodbanking.config.TestEnvFactory;
import ebloodbanking.testextensions.TestSetup;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class TestLogin extends TestSetup {
    private LoginPage loginPage;
    private HomePage homePage;

    private static final Config CONFIG = TestEnvFactory.getInstance().getConfig();

    @BeforeEach
    public void setup() {
        loginPage = new LoginPage(driver)
                .open();
        homePage = new HomePage(driver);
    }

    @Tag("smokeTest")
    @Test
    void assertThatLoginPageTitleIsCorrect() {
        assertEquals("eBloodBanking | Jagriti Blood Bank Management System", loginPage.getTitle());
    }

    @Test
    void assertThatUserCanLoginWithValidDetails() {
        loginPage.login(CONFIG.getString("USERNAME"), CONFIG.getString("PASSWORD"));
        assertEquals("Current Status | eBloodBanking", homePage.getTitle());
    }
}
