package Test;

import Utilities.Constants;
import extentReport.FareyeListener;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(FareyeListener.class)
public class PractisTest extends BaseTest {
    @Test(description = "By clicking in Do1 button Do2 will be enabled and Do1 disabled and vice versa")
    public void doButtonsTest(){
        loginPage = startTest();
        
        WebElement username = loginPage.getUsernameInput();
        loginPage.sendKeysToAWebElement(username, utilities.getPropertyByValue(prop, "user"));

        WebElement password = loginPage.getpasswordInput();
        loginPage.sendKeysToAWebElement(password, utilities.getPropertyByValue(prop, "pass"));

        mainPage = loginPage.clickOnLoginBtn();

        WebElement doOneBtn = mainPage.getDoOneBtn();
        WebElement doTwoBtn = mainPage.getDoTwoBtn();

        mainPage.makeAnAssert(mainPage.isWebElementEnabled(doOneBtn), "Do1 Button is not enabled");
        mainPage.makeAnAssertFalse(mainPage.isWebElementEnabled(doTwoBtn), "Do2 Button is enabled");

        doOneBtn.click();

        doOneBtn = mainPage.getDoOneBtn();
        doTwoBtn = mainPage.getDoTwoBtn();

        mainPage.makeAnAssert(mainPage.isWebElementEnabled(doTwoBtn), "Do1 Button is not enabled");
        mainPage.makeAnAssertFalse(mainPage.isWebElementEnabled(doOneBtn), "Do2 Button is enabled");

        doTwoBtn.click();

        doOneBtn = mainPage.getDoOneBtn();
        doTwoBtn = mainPage.getDoTwoBtn();

        mainPage.makeAnAssert(mainPage.isWebElementEnabled(doOneBtn), "Do1 Button is not enabled");
        mainPage.makeAnAssertFalse(mainPage.isWebElementEnabled(doTwoBtn), "Do2 Button is enabled");

    }
    @Test(description = "By clicking in increase and decrease buttons, the font of 'Click the buttons here to change the font size' " +
                        "will increase or decrease by 3px")
    public void fontChangingSizeTest(){
        loginPage = startTest();

        WebElement username = loginPage.getUsernameInput();
        loginPage.sendKeysToAWebElement(username, utilities.getPropertyByValue(prop, "user"));

        WebElement password = loginPage.getpasswordInput();
        loginPage.sendKeysToAWebElement(password, utilities.getPropertyByValue(prop, "pass"));

        mainPage = loginPage.clickOnLoginBtn();

        WebElement fontSize = mainPage.getTextFontSize();
        String fontSizeText = mainPage.getCssValueFromWebElement(fontSize, "font-size");
        utilities.getReportLog(fontSizeText);

        int pixel = utilities.pixelsToInt(fontSizeText);
        mainPage.makeAnAssert(pixel, 20, "the default value in the page changed");

        mainPage.clickOnIncreaseBtn();

        WebElement fontSizeIncreased = mainPage.getTextFontSize();
        String fontSizeIncreasedText = mainPage.getCssValueFromWebElement(fontSizeIncreased, "font-size");
        utilities.getReportLog(fontSizeIncreasedText);

        int pixelIncrease = utilities.pixelsToInt(fontSizeIncreasedText);
        mainPage.makeAnAssert(pixelIncrease, 23, "the font size was not increased");

        mainPage.clickOnDecreaseBtn();
        mainPage.clickOnDecreaseBtn();

        WebElement fontSizeDecreased = mainPage.getTextFontSize();
        String fontSizeDecreasedText = mainPage.getCssValueFromWebElement(fontSizeDecreased, "font-size");
        utilities.getReportLog(fontSizeDecreasedText);

        int pixelDecrease = utilities.pixelsToInt(fontSizeDecreasedText);
        mainPage.makeAnAssert(pixelDecrease, 17, "the font size was not decreased");

    }
    @Test(description = "By entering a valid background color in the input and clicking in 'SET BACKGROUND COLOR' button the div " +
                        "element that contains both input and button will change its background color")
    public void backgroundColorChangingTest(){
        loginPage = startTest();

        WebElement username = loginPage.getUsernameInput();
        loginPage.sendKeysToAWebElement(username, utilities.getPropertyByValue(prop, "user"));

        WebElement password = loginPage.getpasswordInput();
        loginPage.sendKeysToAWebElement(password, utilities.getPropertyByValue(prop, "pass"));

        mainPage = loginPage.clickOnLoginBtn();

        WebElement backgroundColorInput = mainPage.getBgColorInput();
        backgroundColorInput.sendKeys(Constants.RED);

        mainPage.clickOnSetColorBtn();

        WebElement formToColorize = mainPage.getFormToColorize();
        String formToColorizeColorText = mainPage.getCssValueFromWebElement(formToColorize, "background");
        utilities.getReportLog(formToColorizeColorText);

        utilities.makeAnAssert(formToColorizeColorText.contains("rgb(255, 0, 0)"), "the color is not " + Constants.RED);

        backgroundColorInput.clear();
        backgroundColorInput.sendKeys(Constants.BLUE);

        mainPage.clickOnSetColorBtn();

        WebElement newFormToColorize = mainPage.getFormToColorize();
        String newFormToColorizeColorText = mainPage.getCssValueFromWebElement(newFormToColorize, "background");
        utilities.getReportLog(newFormToColorizeColorText);

        utilities.makeAnAssert(newFormToColorizeColorText.contains("rgb(0, 0, 255)"), "the color is not " + Constants.BLUE);

    }

}
