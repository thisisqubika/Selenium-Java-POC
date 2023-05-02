package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LandingPage extends BasePage{
    public LandingPage(WebDriver remoteDriver) {
        driver = remoteDriver;
        initElements(driver, this);

    }
    @FindBy(xpath="//a[@href='/login']")
    WebElement signUpLoginBtn;
    public LoginPage clickOnSignUpLoginBtn(){
        signUpLoginBtn.click();

        loginPage = new LoginPage(driver);
        return loginPage;

    }

}
