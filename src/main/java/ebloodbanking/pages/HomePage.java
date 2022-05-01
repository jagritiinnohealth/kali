package ebloodbanking.pages;

import com.typesafe.config.Config;
import ebloodbanking.actions.PageActions;
import ebloodbanking.config.TestEnvFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private PageActions pageActions;

    private static Config config = TestEnvFactory.getInstance().getConfig();
    private static final String HOME_PAGE_URL = config.getString("HOME_PAGE_URL");

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        pageActions = new PageActions(driver);
    }

    /** sugar syntax */
    public HomePage and() {
        return this;
    }

    public String getTitle() {
        return pageActions.getPageTitle();
    }
}
