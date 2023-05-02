package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver remoteDriver) {
        driver = remoteDriver;
        initElements(driver, this);

    }
    @FindBy(className="signup-form")
    WebElement signUpForm;
    public WebElement getSignUpForm(){
        return signUpForm;

    }
    @FindBy(name="name")
    WebElement nameSignupInput;
    public WebElement getNameSignupInput(){
        return nameSignupInput;

    }
    @FindBy(xpath="//input[@data-qa='signup-email']")
    WebElement emailSignupInput;
    public WebElement getEmailSignupInput(){
        return emailSignupInput;

    }
    @FindBy(xpath="//*[@data-qa='signup-button']")
    WebElement signupBtn;
    public SignupPage clickOnSignupBtn(){
        signupBtn.click();

        signupPage = new SignupPage(driver);
        return signupPage;

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