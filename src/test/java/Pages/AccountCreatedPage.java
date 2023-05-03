package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class AccountCreatedPage extends AccountsPage {
    public AccountCreatedPage(WebDriver remoteDriver) {
        super(remoteDriver);

    }
    @FindBy(xpath="//h2[@data-qa='account-created']")
    WebElement accountCreatedTitle;
    public WebElement getAccountCreatedTitle(){
        return accountCreatedTitle;

    }

}