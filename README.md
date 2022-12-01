# Selenium-Java-POC
Selenium and Java framework for Automation testing
## Getting started with Selenium
Selenium is an open-source tool that automates web browsers, mobile apps and APIs
https://www.selenium.dev/.

### The project directory structure
src/test/java
- extentReport -> contains classes that support FareyeListener.class which allows to create reports via TestNG Listeners.
- Pages -> contains page object model structure of classes that represents each app web page.
    - BasePage, contains:
        - Every object called from the tests.
        - Selenium most common methods that can be used from every page.
        - Everything that is needed to start the tests.
- Test
    - PractisTest -> Contains some tests using https://practis.co.il/automation/
    - BaseTest -> Contains everything that is needed to instanciate tests
- Utilities
  - Utilities -> Contains common methods used by Java language.
  - Constants -> contains global common constants

src/test/resources
- config
    - config.properties -> Contains some environmental variables.

- testrunners
   - testng_regression.xml -> Contains tests that can be run in several browsers, headless or headful mode.

## 1. Requirements
    Java IDE, IntellijIdea Community recommended.
    JDK installed
    Repository Cloned

## 2.Browser Options
BaseTest in setDriver method is prepared to run with this browsers from TestNG xml file:
- Chrome
- Firefox
- Safari
- Edge

### 2.1 How to run with xml file, Basic structure
``` 
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">

<suite name="Regression PW Suite">

    <listeners>
        <listener class-name="extentReport.FareyeListener" />

    </listeners>
    <test name="PractisTest Test_firefox">
        <parameter name="headless" value="false"/>
        <parameter name="browser" value="firefox" />
        <classes>
            <class name="Test.PractisTest" />

        </classes>

    </test>
    

</suite>
```
- listener tag: will manage TestNG listener. In this case, reports
- test tag: will contain everything is needed to run tests
    - parameter tag: will contain parameters to run tests:
        - headless: boolean, if it is false will be headful, otherwise will be headless
        - browser: set which browser will run the tests, accepted values: chrome, firefox, safari
    - classes tag: will contain the class that is going to be called to run tests
    - method tag: will contain a particular method that is going to be called for a specific test
### Note 1
If test runs from the class itself it will be run with Chrome in headful mode
### Note 2

# 3. Reporting
Reports are automatically generated in Automation_Reports folder grouped by Year, Month, Day, Time if:
- is setted in xml file in listener tag
- is setted above the class is being use to run tests after imports, ex:

```
package Test;

import extentReport.FareyeListener;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(FareyeListener.class)
public class PractisTest extends BaseTest{
    @Test(description = "By clicking in Do1 button Do2 will be enabled and Do1 disabled and vice versa")
    public void doButtonsTest(){
        loginPage = startTest();

        WebElement username = loginPage.getUsernameInput();
        loginPage.sendKeysToAWebElement(username, prop.getProperty("user").trim());

        WebElement password = loginPage.getpasswordInput();
        loginPage.sendKeysToAWebElement(password, prop.getProperty("pass").trim());

        mainPage = loginPage.clickOnLoginBtn();

        WebElement doOneBtn = mainPage.getDoOneBtn();
        WebElement doTwoBtn = mainPage.getDoTwoBtn();

        Assert.assertTrue(mainPage.isWebElementEnabled(doOneBtn), "Do1 Button is not enabled");
        Assert.assertFalse(mainPage.isWebElementEnabled(doTwoBtn), "Do2 Button is enabled");

        doOneBtn.click();

        doOneBtn = mainPage.getDoOneBtn();
        doTwoBtn = mainPage.getDoTwoBtn();

        Assert.assertFalse(mainPage.isWebElementEnabled(doOneBtn), "Do1 Button is enabled after Do1 click");
        Assert.assertTrue(mainPage.isWebElementEnabled(doTwoBtn), "Do2 Button is not enabled after Do1 click");

        doTwoBtn.click();

        doOneBtn = mainPage.getDoOneBtn();
        doTwoBtn = mainPage.getDoTwoBtn();

        Assert.assertTrue(mainPage.isWebElementEnabled(doOneBtn), "Do1 Button is not enabled");
        Assert.assertFalse(mainPage.isWebElementEnabled(doTwoBtn), "Do2 Button is enabled");

    }

}
```