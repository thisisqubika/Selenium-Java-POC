package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AccountsPage extends SignupPage {
    public AccountsPage(WebDriver remoteDriver) {
        super(remoteDriver);

    }
    @FindBy(xpath="//p[@style='font-size: 20px; font-family: garamond;']")
    List<WebElement> accountCreatedParagraphList;
    public List<WebElement> getAccountParagraphList(){
        return accountCreatedParagraphList;

    }
    @FindBy(xpath="//a[@data-qa='continue-button']")
    WebElement continueBtn;
    public void clickOnContinueBtn() throws InterruptedException {
        continueBtn.click();
        clickOnDismissBtn();

    }

}