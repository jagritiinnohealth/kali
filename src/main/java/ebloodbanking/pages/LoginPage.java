package ebloodbanking.pages;

import com.typesafe.config.Config;
import ebloodbanking.actions.PageActions;
import ebloodbanking.config.TestEnvFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private PageActions pageActions;

    private static Config config = TestEnvFactory.getInstance().getConfig();
    private static final String HOME_PAGE_URL = config.getString("HOME_PAGE_URL");

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        pageActions = new PageActions(driver);
    }

    @FindBy(css = "input[id='edit-name']")
    private WebElement usernameField;

    @FindBy(css = "input[id='edit-pass']")
    private WebElement passwordField;

    @FindBy(css = "button[value='Log in']")
    private WebElement loginButton;

    @FindBy(css = "a[title='Request new password via e-mail.']")
    private WebElement requestNewPasswordLink;

    public LoginPage open() {
        pageActions.waitForPageToLoad();
        pageActions.getPageWithRetry(HOME_PAGE_URL, By.cssSelector("button[value='Log in']"));
        return this;
    }

    /** sugar syntax */
    public LoginPage and() {
        return this;
    }

    public String getTitle() {
        return pageActions.getPageTitle();
    }

    public LoginPage login(String username, String password) {
        pageActions.setTextField(usernameField, username);
        pageActions.setTextField(passwordField, password);
        pageActions.clickViaJavaScript(loginButton);
        return this;
    }
}
