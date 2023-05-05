package Pages;

import Utilities.Utilities;
import Utilities.FakerClass;

import com.aventstack.extentreports.Status;
import extentReport.ExtentTestManager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import org.testng.Assert;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BasePage {
    public static WebDriver driver;
    protected ChromeOptions chromeOptions;
    protected FirefoxOptions firefoxOptions;
    protected SafariOptions safariOptions;
    protected EdgeOptions edgeOptions;
    public LoginPage loginPage;
    public DeletedAccountPage deletedAccountPage;
    public LandingPage landingPage;
    public SignupPage signupPage;
    public AccountCreatedPage accountCreatedPage;
    public ContactUsPage contactUsPage;
    public ProductsPage productsPage;
    public ProductDetailPage productDetailPage;

    protected static Utilities utilities;
    public static Properties prop;
    public static FakerClass fakerClass;

    public void initElements(WebDriver remoteDriver, Object aPage) {
        PageFactory.initElements(remoteDriver, aPage);

    }
    public static void setProps() throws IOException {
        utilities = new Utilities();
        prop = utilities.init_prop();
        fakerClass = new FakerClass();

    }
    public void getToAnUrl(String anUrl) {
        driver.get(anUrl);

    }
    public String getCurrentUrl(){
        return driver.getCurrentUrl();

    }
    public void switchToIframe(WebDriver remoteDriver, String nameOrId){
        remoteDriver.switchTo()
                    .frame(nameOrId);

    }
    public void switchToIframe(WebDriver remoteDriver, WebElement AWebElement){
        remoteDriver.switchTo()
                    .frame(AWebElement);

    }
    public void switchToDefaultContent(WebDriver remoteDriver){
        remoteDriver.switchTo().defaultContent();

    }
    public LandingPage startTest() {
        landingPage = new LandingPage(driver);
        return landingPage;

    }
    public String getTextFromAWebElement(WebElement aWebElement){
        return aWebElement.getText();

    }
    public List<String> getTextFromListOfWebElements(List<WebElement> aListOfWebElements){
        List<String> aListOfStrings = new ArrayList<String>();
        for(WebElement el : aListOfWebElements){
            aListOfStrings.add(this.getTextFromAWebElement(el));

        }
        return aListOfStrings;

    }
    public boolean isWebElementEnabled(WebElement aWebElement){
        return aWebElement.isEnabled();

    }
    public boolean isWebElementDisplayed(WebElement aWebElement) {
            return aWebElement.isDisplayed();
		
    }
    public boolean isWebElementDisplayed(List<WebElement> aListOfWebElements, int pos) {
        return aListOfWebElements.get(pos)
                                 .isDisplayed();
		
    }   
    public boolean isWebElementSelected(WebElement aWebElement) {
        return aWebElement.isSelected();
		
    }
    public boolean isWebElementSelected(List<WebElement> aListOfWebElements, int pos) {
        return aListOfWebElements.get(pos)
                                 .isSelected();
		
    }
    public String getCssValueFromWebElement(WebElement aWebElement, String propertyName){
        return aWebElement.getCssValue(propertyName);

    }
    public String getAttributeFromWebElement(WebElement aWebElement, String attribute){
        return aWebElement.getAttribute(attribute);

    }
    public void sendKeysToAWebElement(WebElement aWebElement, String key){
        aWebElement.sendKeys(key);

    }
    Select aSelection;
    public Select getSelect(WebElement aWebElement) {
    	aSelection = new Select(aWebElement);
    	return aSelection;
    	
    }
    public void selectByVisibleText(WebElement aWebElement, String aValue) {
    	aSelection = getSelect(aWebElement);
    	aSelection.selectByVisibleText(aValue);
    	
    }
	public List<WebElement> valuesFromSelect(WebElement aWebElement){
		aSelection = getSelect(aWebElement);
    	return aSelection.getOptions();
    	
    }
    public WebElement getValueFromLov(WebElement anElement) {
    	aSelection = new Select(anElement);
        return aSelection.getFirstSelectedOption();
    	
    }
    public void reportAndFail(String aLocalizedMessage) {
        ExtentTestManager.reporterLog(aLocalizedMessage, Status.FAIL);
        Assert.fail(aLocalizedMessage);

    }
    public void makeAnAssert(String actual, String expected, String message) {
        try{
            Assert.assertEquals(actual, expected, message);

        }catch(AssertionError aErr) {
            this.reportAndFail(aErr.getLocalizedMessage());

        }

    }
    public void makeAnAssert(boolean actual, boolean expected, String message) {
        try{
            Assert.assertEquals(actual, expected, message);

        }catch(AssertionError aErr) {
            this.reportAndFail(aErr.getLocalizedMessage());

        }

    }
    public void makeAnAssert(int actual, int expected, String message) {
        try{
            Assert.assertEquals(actual, expected, message);

        }catch(AssertionError aErr) {
            this.reportAndFail(aErr.getLocalizedMessage());

        }

    }
    public void makeAnAssert(boolean condition, String message) {
        try{
            Assert.assertTrue(condition, message);

        }catch(AssertionError aErr) {
            this.reportAndFail(aErr.getLocalizedMessage());

        }

    }
    public void makeAnAssertNot(String actual, String expected, String message) {
        try{
            Assert.assertNotEquals(actual, expected, message);

        }catch(AssertionError aErr) {
            this.reportAndFail(aErr.getLocalizedMessage());

        }

    }
    public void makeAnAssertFalse(boolean condition, String message) {
        try{
            Assert.assertFalse(condition, message);

        }catch(AssertionError aErr) {
            this.reportAndFail(aErr.getLocalizedMessage());

        }

    }
    Wait<WebDriver> waitGeneral;
    /**
     * Increment a value by delta and return the new value.
     *
     * @param  timeToWait   the amount of time the wait object will wait.
     * @param  pollingTime  the amount of time the wait object will try to locate the element.
     * @return a FluentWait object
     */
    public Wait<WebDriver> generalFluentWait(long timeToWait, long pollingTime) {
        waitGeneral = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeToWait))
                .pollingEvery(Duration.ofSeconds(pollingTime))
                .ignoring(NoSuchElementException.class);

        return waitGeneral;

    }
    Wait<WebDriver> waitParticular;
    public void waitForAListOfWebElementsToFullyLoad(List<WebElement> aListOfWebElements, long timeToWait, long pollingTime) {
        waitParticular = generalFluentWait(timeToWait, pollingTime);
        waitParticular.until(ExpectedConditions.visibilityOfAllElements(aListOfWebElements));

    }
    public void waitForAWebElementToFullyLoad(List<WebElement> aListOfWebElements, int pos, long timeToWait, long pollingTime) {
        waitParticular = generalFluentWait(timeToWait, pollingTime);
        waitParticular.until(ExpectedConditions.visibilityOf(aListOfWebElements.get(pos)));

    }
    public void waitForAWebElementToFullyLoad(WebElement anElement, long timeToWait, long pollingTime) {
        waitParticular = generalFluentWait(timeToWait, pollingTime);
        waitParticular.until(ExpectedConditions.visibilityOf(anElement));

    }
    public void waitForAWebElementToBeClickable(WebElement anElement, long timeToWait, long pollingTime) {
        waitParticular = generalFluentWait(timeToWait, pollingTime);
        waitParticular.until(ExpectedConditions.elementToBeClickable(anElement));

    }
    public void waitForAWebElementToBeInvisible(WebElement anElement, long timeToWait, long pollingTime) {
        waitParticular = generalFluentWait(timeToWait, pollingTime);
        waitParticular.until(ExpectedConditions.invisibilityOf(anElement));

    }
    public void waitForAWebElementToBeClickable(List<WebElement> aListOfWebElements, int pos, long timeToWait, long pollingTime) {
        waitParticular = generalFluentWait(timeToWait, pollingTime);
        waitParticular.until(ExpectedConditions.elementToBeClickable(aListOfWebElements.get(pos)));

    }
    public void waitForAWebElementHaveAnAttribute(WebElement anElement, long timeToWait, long pollingTime, String anAttribute, String aValue) {
        waitParticular = generalFluentWait(timeToWait, pollingTime);
        waitParticular.until(ExpectedConditions.attributeToBe(anElement, anAttribute, aValue));

    }
    public void waitForTextToAppear(WebElement anElement, long timeToWait, long pollingTime, String textToAppear) {
        waitParticular = generalFluentWait(timeToWait, pollingTime);
        waitParticular.until(ExpectedConditions.textToBePresentInElement(anElement, textToAppear));

    }
    public void waitForTextToAppear(List<WebElement> aListOfWebElements, int pos, long timeToWait, long pollingTime, String textToAppear) {
        waitParticular = generalFluentWait(timeToWait, pollingTime);
        waitParticular.until(ExpectedConditions.textToBePresentInElement(aListOfWebElements.get(pos), textToAppear));

    }

    @FindBy(id="dismiss-button")
    WebElement dismissBtn;
    public WebElement getDismissBtn(){
        return dismissBtn;

    }
    @FindBy(id="ad_iframe")
    WebElement adIframe;
    public WebElement getAdIframe(){
        return adIframe;

    }
    @FindBy(id="aswift_5")
    WebElement aswiftFiveIframe;
    public WebElement getAswiftFiveIframe(){
        return aswiftFiveIframe;

    }
    @FindBy(id="aswift_1")
    WebElement aswiftOneIframe;
    public WebElement getAswiftOneIframe(){
        return aswiftOneIframe;

    }
    @FindBy(xpath="//div[@id='dismiss-button']//span[normalize-space()='Close']")
    List<WebElement> checkButtonCloseList;
    public List<WebElement> getCheckButtonClose(){
        return checkButtonCloseList;

    }
    /**
     * In case that an AD on continue button is displayed when create an account
     * the close button will be clicked in order to continue the flow
     * adapted from https://anhtester.com/blog/handle-ads-google-using-selenium-b557.html
     *
     * @param  iframe is the number of iframe that came from google ads. Sometimes cames with id="aswift_1"
     *                and in other cases id="aswift_5", the idea is to manage these situations as it appears
     *
     */
    public void clickOnDismissBtn(int iframe) throws InterruptedException {
        WebElement iframeChosen = null;
        if(iframe == 1){
            iframeChosen = this.getAswiftOneIframe();

        }
        if(iframe == 5){
            iframeChosen = this.getAswiftFiveIframe();

        }
        try{
            this.switchToIframe(driver, iframeChosen);
            this.switchToIframe(driver, this.getAdIframe());

            Thread.sleep(1000);
            List<WebElement> checkButtonClose = this.getCheckButtonClose();
            System.out.println(checkButtonClose.size() + " <<<");
            waitForAListOfWebElementsToFullyLoad(checkButtonClose, 30, 3);
            if (checkButtonClose.size() > 0) {
                this.getCheckButtonClose()
                        .get(0)
                        .click();

            } else {
                this.getDismissBtn()
                        .click();

            }
            this.switchToDefaultContent(driver);

        }catch(Exception ignored){

        }

    }
    @FindBy(xpath="//h2[@class='title text-center']")
    List<WebElement> pagesTitlesList;
    public List<WebElement> getPagesTitlesList(){
        return pagesTitlesList;

    }

}
