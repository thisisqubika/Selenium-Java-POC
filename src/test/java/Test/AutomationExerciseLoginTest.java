package Test;

import extentReport.FareyeListener;

import org.openqa.selenium.WebElement;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

import static Utilities.Constants.*;

@Listeners(FareyeListener.class)
public class AutomationExerciseLoginTest extends BaseTest {
    @Test(description = "Test Case 1: Register User")
    public void registerUserTest() throws InterruptedException {
        landingPage = startTest();
        loginPage = landingPage.clickOnSignUpLoginBtn();

        makeAnAssert(loginPage.isWebElementDisplayed(loginPage.getSignUpForm()), "Signup form is not correctly displayed");

        loginPage.sendKeysToAWebElement(loginPage.getNameSignupInput(), utilities.getPropertyByValue(prop, "name-reg"));
        loginPage.sendKeysToAWebElement(loginPage.getEmailSignupInput(), utilities.getPropertyByValue(prop, "mail-reg"));

        signupPage = loginPage.clickOnSignupBtn();

        List<WebElement> signUpTitles = signupPage.getSignuptitleList();
        List<String> signUpTitlesText = signupPage.getTextFromListOfWebElements(signUpTitles);

        for(int i = 0; i < signUpTitlesText.size(); i++){
            makeAnAssert(signUpTitlesText.get(i), SIGN_UP_TITLES_LIST.get(i),
                         signUpTitlesText.get(i) + " does not match with " + SIGN_UP_TITLES_LIST.get(i));

        }
        signupPage.chooseTitleRadiobutton("mr");

        makeAnAssert(signupPage.getAttributeFromWebElement(signupPage.getNameInput(), "value"),
                                                       utilities.getPropertyByValue(prop, "name-reg"),
                                               "name mismatch");
        makeAnAssert(signupPage.getAttributeFromWebElement(signupPage.getEmailInput(),"value"),
                                                       utilities.getPropertyByValue(prop, "mail-reg"),
                                               "email mismatch");
        makeAnAssertFalse(signupPage.isWebElementEnabled(signupPage.getEmailInput()), "Email is enabled");

        signupPage.sendKeysToAWebElement(signupPage.getPasswordInput(), utilities.getPropertyByValue(prop, "pwd-reg"));
        signupPage.setDateOfBirth("25", "December", "1974");

        signupPage.clickOnNewsletterCheckbox();
        signupPage.clickOnSpecialOffersCheckbox();

        signupPage.sendKeysToAWebElement(signupPage.getFirstName(), "name");
        signupPage.sendKeysToAWebElement(signupPage.getLastName(), "last name");
        signupPage.sendKeysToAWebElement(signupPage.getCompany(), fakerClass.getFakerCompany());

        signupPage.sendKeysToAWebElement(signupPage.getFirstAddress(), fakerClass.getFakerAddress());
        signupPage.selectCountriesListOfValues("Australia");

        signupPage.sendKeysToAWebElement(signupPage.getState(), fakerClass.getState());
        signupPage.sendKeysToAWebElement(signupPage.getCity(), fakerClass.getCity());
        signupPage.sendKeysToAWebElement(signupPage.getZipCode(), fakerClass.getZipCode());
        signupPage.sendKeysToAWebElement(signupPage.getMobileNumber(), fakerClass.getMobileNumber());

        accountCreatedPage = signupPage.clickOnCreateAccountBtn();

        makeAnAssert(accountCreatedPage.getTextFromAWebElement(accountCreatedPage.getAccountCreatedTitle()),
                     ACCOUNT_CREATED_TITLE,
                "Title mismatch");

        for (int i = 0; i < accountCreatedPage.getAccountParagraphList().size(); i++) {
            makeAnAssert(accountCreatedPage.getTextFromAWebElement(accountCreatedPage.getAccountParagraphList().get(i)),
                         ACCOUNT_CREATED_PARAGRAPH_LIST.get(i),
                "Paragraph mismatch");

        }
        accountCreatedPage.clickOnContinueBtn();

        makeAnAssert(landingPage.isWebElementDisplayed(landingPage.getLogoutBtn()), "Logout button is not displayed");

        deletedAccountPage = landingPage.clickOnDeletedAccountBtn();
        makeAnAssert(deletedAccountPage.getTextFromAWebElement(deletedAccountPage.getAccountDeletedTitle()),
                     ACCOUNT_DELETED_TITLE,
                "Title mismatch");

        for (int i = 0; i < deletedAccountPage.getAccountParagraphList().size(); i++) {
            makeAnAssert(deletedAccountPage.getTextFromAWebElement(deletedAccountPage.getAccountParagraphList().get(i)),
                    ACCOUNT_DELETED_PARAGRAPH_LIST.get(i),
                    "Paragraph mismatch");

        }
        deletedAccountPage.clickOnContinueBtn();

    }
    @Test(description = "Test Case 2: Login User with correct email and password")
    public void successfulLoginTest(){
        landingPage = startTest();
        loginPage = landingPage.clickOnSignUpLoginBtn();

        makeAnAssert(loginPage.isWebElementDisplayed(loginPage.getLoginForm()), "Login form is not correctly displayed");

        loginPage.sendKeysToAWebElement(loginPage.getLoginEmailInput(), utilities.getPropertyByValue(prop, "mail-success"));
        loginPage.sendKeysToAWebElement(loginPage.getLoginPasswordInput(), utilities.getPropertyByValue(prop, "pwd-success"));

        loginPage.clickOnLoginBtn();

        makeAnAssert(landingPage.getTextFromAWebElement(landingPage.getLoggedAsUsername()),
                     utilities.getPropertyByValue(prop, "name-success"),"Username mismatch");

        makeAnAssert(landingPage.isWebElementDisplayed(landingPage.getLogoutBtn()), "Logout is not displayed");
        makeAnAssert(landingPage.isWebElementDisplayed(landingPage.getDeleteAccountBtn()), "Delete Account is not displayed");

    }
    @Test(description = "Test Case 3: Login User with incorrect email and password")
    public void unsuccessfulLoginTest(){
        landingPage = startTest();
        loginPage = landingPage.clickOnSignUpLoginBtn();

        makeAnAssert(loginPage.isWebElementDisplayed(loginPage.getLoginForm()), "Login form is not correctly displayed");

        loginPage.sendKeysToAWebElement(loginPage.getLoginEmailInput(), "invalid@user.com");
        loginPage.sendKeysToAWebElement(loginPage.getLoginPasswordInput(), "invalidpwd");

        loginPage.clickOnLoginBtn();

        makeAnAssert(loginPage.getTextFromAWebElement(loginPage.getErrorMsg()), INVALID_CREDENTIALS, "the message did not appear");

    }
    @Test(description = "Test Case 4: Logout User")
    public void logoutTest() {
        landingPage = startTest();
        loginPage = landingPage.clickOnSignUpLoginBtn();

        makeAnAssert(loginPage.isWebElementDisplayed(loginPage.getLoginForm()), "Login form is not correctly displayed");

        loginPage.sendKeysToAWebElement(loginPage.getLoginEmailInput(), utilities.getPropertyByValue(prop, "mail-success"));
        loginPage.sendKeysToAWebElement(loginPage.getLoginPasswordInput(), utilities.getPropertyByValue(prop, "pwd-success"));

        loginPage.clickOnLoginBtn();

        makeAnAssert(landingPage.getTextFromAWebElement(landingPage.getLoggedAsUsername()),
                utilities.getPropertyByValue(prop, "name-success"), "Username mismatch");

        makeAnAssert(landingPage.isWebElementDisplayed(landingPage.getLogoutBtn()), "Logout is not displayed");
        makeAnAssert(landingPage.isWebElementDisplayed(landingPage.getDeleteAccountBtn()), "Delete Account is not displayed");

        landingPage.clickOnLogoutBtn();

        makeAnAssert(loginPage.isWebElementDisplayed(loginPage.getLoginForm()), "Login form is not correctly displayed");
        makeAnAssert(loginPage.getSignUpForm().isDisplayed(), "Signup form is not correctly displayed");
        makeAnAssert(loginPage.getCurrentUrl(),
                utilities.getPropertyByValue(prop, "url") + LOGIN_BASEPATH,
                "url mismatch");

    }
    @Test(description = "Test Case 5: Register User with existing email")
    public void registerWithAnExistentEmailTest(){
        landingPage = startTest();
        loginPage = landingPage.clickOnSignUpLoginBtn();

        makeAnAssert(loginPage.isWebElementDisplayed(loginPage.getSignUpForm()), "Signup form is not correctly displayed");

        loginPage.sendKeysToAWebElement(loginPage.getNameSignupInput(), utilities.getPropertyByValue(prop, "name-reg"));
        loginPage.sendKeysToAWebElement(loginPage.getEmailSignupInput(), utilities.getPropertyByValue(prop, "existent-mail"));

        signupPage = loginPage.clickOnSignupBtn();

        makeAnAssert(loginPage.getTextFromAWebElement(loginPage.getErrorMsg()), ALREADY_USED_EMAIL, "the message did not appear");

    }

}
