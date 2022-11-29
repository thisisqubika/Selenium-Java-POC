package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver remoteDriver) {
        driver = remoteDriver;
        initElements(driver, this);

    }
    @FindBy(name="username")
    WebElement usernameInput;
    public WebElement getUsernameInput(){
        return usernameInput;

    }
    @FindBy(name="password")
    WebElement passwordInput;
    public WebElement getpasswordInput(){
        return passwordInput;

    }
    @FindBy(name="login")
    WebElement loginBtn;
    public MainPage clickOnLoginBtn(){
        loginBtn.click();

        mainPage = new MainPage(driver);
        return mainPage;

    }

}