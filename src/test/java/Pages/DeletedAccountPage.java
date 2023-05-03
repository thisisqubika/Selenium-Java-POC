package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeletedAccountPage extends AccountsPage{
    public DeletedAccountPage(WebDriver remoteDriver) {
        super(remoteDriver);

    }
    @FindBy(xpath="//h2[@data-qa='account-deleted']")
    WebElement accountDeletedTitle;
    public WebElement getAccountDeletedTitle(){
        return accountDeletedTitle;

    }

}
