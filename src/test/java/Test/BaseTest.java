package Test;

import Pages.BasePage;
import Utilities.Utilities;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.IOException;
import java.time.Duration;


public class BaseTest extends BasePage {
    @BeforeTest
    public void setUpProps() throws IOException {
        setProps();

    }
    @Parameters({ "browser", "headless" })
    @BeforeMethod
    public void setUpBrowser(ITestContext context, @Optional String browserName, @Optional String headless) {
        driver = this.setDriver(browserName, Boolean.parseBoolean(headless));
        driver.manage().window().maximize();

        utilities = new Utilities(driver);

        timeoutManager(10);

        initElements(driver, this);
        setContextAttribute(context, utilities.getPropertyByValue(prop, "webdriver-att-val"), driver);

        getToAnUrl(utilities.getPropertyByValue(prop, "url"));

    }
    @AfterMethod
    public void closeDriver(){
        if (driver != null) {
            driver.quit();

        }

    }
    public void addOptionsArgumentsForBrowser(ChromiumOptions<?> aChromiumDriverOptions) {
        aChromiumDriverOptions.addArguments(utilities.getPropertyByValue(prop,"start-max"),
                                            utilities.getPropertyByValue(prop,"disable-extensions"),
                                            utilities.getPropertyByValue(prop,"disable-notifications"),
                                            utilities.getPropertyByValue(prop,"disable-infobars"));
        aChromiumDriverOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);

    }
    public void addOptionsArgumentsForHeadlessRun(ChromiumOptions<?> aChromiumDriverOptions) {
        aChromiumDriverOptions.addArguments(utilities.getPropertyByValue(prop,"headless"),
                                            utilities.getPropertyByValue(prop,"disable-gpu"),
                                            utilities.getPropertyByValue(prop,"window-size"),
                                            utilities.getPropertyByValue(prop,"ignore-certificate-errors"),
                                            utilities.getPropertyByValue(prop,"no-sandbox"),
                                            utilities.getPropertyByValue(prop,"disable-dev-shm-usage"));

    }
    public void setContextAttribute(ITestContext aContext, String aWebDriverAttribute, WebDriver remoteDriver) {
        aContext.setAttribute(aWebDriverAttribute, remoteDriver);

    }
    public void timeoutManager(int seconds) {
        Duration dur = Duration.ofSeconds(seconds);
        driver.manage().timeouts().implicitlyWait(dur);

    }
    public WebDriver setDriver(String browserName, boolean isHeadless){
        WebDriver dvr = null;
        if (browserName == null){
            browserName = "chrome";

        }
        if(!isHeadless){
            isHeadless = false;

        }
        if(browserName.equals("chrome")){
            chromeOptions = new ChromeOptions();
            if(isHeadless){
                addOptionsArgumentsForHeadlessRun(chromeOptions);

            }
            addOptionsArgumentsForBrowser(chromeOptions);
            dvr = new ChromeDriver(chromeOptions);

        }
        if(browserName.equals("firefox")){
            firefoxOptions = new FirefoxOptions();
            if(isHeadless){
                firefoxOptions.setHeadless(true);

            }
            dvr = new FirefoxDriver(firefoxOptions);

        }
        if(browserName.equals("msedge")){
            edgeOptions = new EdgeOptions();
            if(isHeadless){
                //edgeOptions.setHeadless(true);
                addOptionsArgumentsForHeadlessRun(edgeOptions);
            }
            addOptionsArgumentsForBrowser(edgeOptions);
            dvr = new EdgeDriver(edgeOptions);

        }
        if(browserName.equals("safari")){
            safariOptions = new SafariOptions();
            safariOptions.setImplicitWaitTimeout(Duration.ofSeconds(20));

            dvr = new SafariDriver(safariOptions);
            dvr.switchTo().defaultContent();

        }
        return dvr;

    }

}
