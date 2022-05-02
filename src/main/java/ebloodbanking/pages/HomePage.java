package ebloodbanking.pages;

import ebloodbanking.actions.PageActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private PageActions pageActions;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        pageActions = new PageActions(driver);
    }

    @FindBy(xpath = "//a[text()='Collection ']")
    private WebElement collectionList;

    @FindBy(xpath = "//a[text()='Add New Donor']")
    private WebElement addNewDonor;

    /** sugar syntax */
    public HomePage and() {
        return this;
    }

    public String getTitle() {
        return pageActions.getPageTitle();
    }

    public void openCollectionMenu() {
        pageActions.clickViaJavaScript(collectionList);
    }

    public void clickAddNewDonor() {
        pageActions.clickViaJavaScript(addNewDonor);
    }

    public String getAddNewDonorText() {
        return pageActions.getTextFromElement(addNewDonor);
    }
}
