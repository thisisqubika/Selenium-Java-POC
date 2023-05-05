package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ContactUsPage extends BasePage {
    public ContactUsPage(WebDriver remoteDriver) {
        driver = remoteDriver;
        initElements(driver, this);

    }
    @FindBy(xpath="//address//p")
    List<WebElement> feedbackForUsParagraphs;
    public List<WebElement> getFeedbackForUsParagraphs(){
        return feedbackForUsParagraphs;

    }
    @FindBy(name="name")
    WebElement nameContactUsInput;
    public WebElement getNameContactUsInput(){
        return nameContactUsInput;

    }
    @FindBy(name="email")
    WebElement emailContactUsInput;
    public WebElement getEmailContactUsInput(){
        return emailContactUsInput;

    }
    @FindBy(name="subject")
    WebElement subjectContactUsInput;
    public WebElement getSubjectContactUsInput(){
        return subjectContactUsInput;

    }
    @FindBy(id="message")
    WebElement messageContactUsInput;
    public WebElement getMessageContactUsInput(){
        return messageContactUsInput;

    }
    @FindBy(name="submit")
    WebElement submitContactUsBtn;
    public void getSubmitContactUsBtn() throws InterruptedException {
        submitContactUsBtn.click();
        utilities.acceptOnOkPrompt();

    }
    @FindBy(xpath="//div[@class='status alert alert-success']")
    WebElement successMessageSubmitedForm;
    public WebElement getSuccessMessageSubmitedForm(){
        return successMessageSubmitedForm;

    }
    @FindBy(xpath="//a[@href='/']")
    WebElement homeBtn;
    public void clickOnHomeBtn() throws InterruptedException {
        homeBtn.click();
        clickOnDismissBtn(1);

    }

}