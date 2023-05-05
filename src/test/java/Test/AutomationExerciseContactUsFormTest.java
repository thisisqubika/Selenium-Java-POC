package Test;

import extentReport.FareyeListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

import static Utilities.Constants.*;

@Listeners(FareyeListener.class)
public class AutomationExerciseContactUsFormTest extends BaseTest {
    @Test(description = "Test Case 6: Contact Us Form")
    public void contactUsFormTest() throws InterruptedException {
        landingPage = startTest();
        contactUsPage = landingPage.clickOnContactUsBtn();

        for (int i = 0; i < contactUsPage.getPagesTitlesList().size(); i++) {
            makeAnAssert(contactUsPage.getTextFromAWebElement(contactUsPage.getPagesTitlesList().get(i)),
                         CONTACT_US_TITLE_LIST.get(i),
                 "Titles in contact page mismatch");

        }
        for (int i = 0; i < contactUsPage.getFeedbackForUsParagraphs().size(); i++) {
            makeAnAssert(contactUsPage.getTextFromAWebElement(contactUsPage.getFeedbackForUsParagraphs().get(i)),
                         FEEDBACK_FOR_US_PARAGRAPHS.get(i),
                 "Paragraphs in feedback for us mismatch");

        }

        contactUsPage.sendKeysToAWebElement(contactUsPage.getNameContactUsInput(), "Name Test");
        contactUsPage.sendKeysToAWebElement(contactUsPage.getEmailContactUsInput(), "mail@mailfortest.com");
        contactUsPage.sendKeysToAWebElement(contactUsPage.getSubjectContactUsInput(), "Subject for test");
        contactUsPage.sendKeysToAWebElement(contactUsPage.getMessageContactUsInput(), "this is a message for test purposes");

        contactUsPage.getSubmitContactUsBtn();

        makeAnAssert(contactUsPage.getTextFromAWebElement(contactUsPage.getSuccessMessageSubmitedForm()),
                     SUCCESS_SUBMITED_CONTACT_FORM_MESSAGE,
            "Success submited message not appeared when clicking submit");

        contactUsPage.clickOnHomeBtn();
        makeAnAssert(landingPage.getCurrentUrl(),
                     utilities.getPropertyByValue(prop, "url"),
            "url mismatch");

    }

}
