package Pages;

import Utilities.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.Properties;

public class BasePage {
    public static WebDriver driver;

    protected ChromeOptions chromeOptions;
    protected FirefoxOptions firefoxOptions;
    protected SafariOptions safariOptions;
    public LoginPage loginPage;
    public MainPage mainPage;
    protected static Utilities utilities;
    public static Properties prop;
    public void initElements(WebDriver remoteDriver, Object aPage) {
        PageFactory.initElements(remoteDriver, aPage);

    }
    public static void setProps() throws IOException {
        utilities = new Utilities();
        prop = utilities.init_prop();

    }

    public void getToAnUrl(String anUrl) {
        driver.get(anUrl);

    }
    public LoginPage startTest() {
        loginPage = new LoginPage(driver);
        return loginPage;

    }
    public boolean isWebElementEnabled(WebElement aWebElement){
        return aWebElement.isEnabled();

    }
    public String getCssValueFromWebElement(WebElement aWebElement, String propertyName){
        return aWebElement.getCssValue(propertyName);
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
    	WebElement aWebElement = aSelection.getFirstSelectedOption();
        
    	return aWebElement;
    	
    }

}
