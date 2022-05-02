package ebloodbanking.pages.collection.donormanagement;

import com.typesafe.config.Config;
import ebloodbanking.config.TestEnvFactory;
import ebloodbanking.pages.HomePage;
import ebloodbanking.pages.LoginPage;
import ebloodbanking.pages.collection.AddNewDonor;
import ebloodbanking.testextensions.TestSetup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAddNewDonor extends TestSetup {
    private HomePage homePage;
    private LoginPage loginPage;
    private AddNewDonor addNewDonor;

    private static final Config CONFIG = TestEnvFactory.getInstance().getConfig();

    @BeforeEach
    public void setup() {
        loginPage = new LoginPage(driver)
                .open()
                .login(CONFIG.getString("USERNAME"), CONFIG.getString("PASSWORD"));
        homePage = new HomePage(driver);
        addNewDonor = new AddNewDonor(driver);
    }

    @Test
    void assertThatUserCanAddANewDonorFromCollectionList() {
        homePage.openCollectionMenu();
        homePage.clickAddNewDonor();
        assertEquals("Create Donation Record | eBloodBanking", addNewDonor.getTitle());
    }
}
