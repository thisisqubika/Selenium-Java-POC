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
    @FindBy(className="login-form")
    WebElement loginForm;
    public WebElement getLoginForm(){
        return loginForm;

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
    @FindBy(xpath="//input[@data-qa='login-email']")
    WebElement loginEmailInput;
    public WebElement getLoginEmailInput(){
        return loginEmailInput;

    }
    @FindBy(name="password")
    WebElement loginPasswordInput;
    public WebElement getLoginPasswordInput(){
        return loginPasswordInput;

    }
    @FindBy(xpath="//button[@data-qa='login-button']")
    WebElement loginBtn;
    public void clickOnLoginBtn(){
        loginBtn.click();

    }
    @FindBy(xpath="//p[@style='color: red;']")
    WebElement errorMsg;
    public WebElement getErrorMsg(){
        return errorMsg;

    }

}