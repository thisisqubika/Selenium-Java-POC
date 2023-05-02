package Test;

import extentReport.FareyeListener;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

import static Utilities.Constants.SIGN_UP_TITLES_LIST;

@Listeners(FareyeListener.class)
public class AutomationExerciseTest extends BaseTest {
    @Test(description = "Test Case 1: Register User")
    public void registerUserTest(){
        landingPage = startTest();
        loginPage = landingPage.clickOnSignUpLoginBtn();

        makeAnAssert(loginPage.getSignUpForm().isDisplayed(), "Signup form is not correctly displayed");

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

    }

}
