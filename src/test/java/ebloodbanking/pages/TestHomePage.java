package ebloodbanking.pages;

import com.typesafe.config.Config;
import ebloodbanking.config.TestEnvFactory;
import ebloodbanking.testextensions.TestSetup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestHomePage extends TestSetup {
    private HomePage homePage;
    private LoginPage loginPage;

    private static final Config CONFIG = TestEnvFactory.getInstance().getConfig();

    @BeforeEach
    public void setup() {
        loginPage = new LoginPage(driver)
                .open()
                .login(CONFIG.getString("USERNAME"), CONFIG.getString("PASSWORD"));
        homePage = new HomePage(driver);
    }

    @Test
    void assertThatUserCanAddANewDonorFromCollectionList() {
        homePage.openCollectionMenu();
        // todo: Ideally you want to assert all the list items (or count of them) that you can see here.
        assertEquals("Add New Donor", homePage.getAddNewDonorText());
    }
}
