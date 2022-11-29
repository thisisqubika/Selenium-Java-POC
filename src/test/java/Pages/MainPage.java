package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage{
    public MainPage(WebDriver remoteDriver) {
        driver = remoteDriver;
        initElements(driver, this);

    }
    @FindBy(id="do1")
    WebElement doOneBtn;
    public WebElement getDoOneBtn(){
        return doOneBtn;

    }
    @FindBy(id="do2")
    WebElement doTwoBtn;
    public WebElement getDoTwoBtn(){
        return doTwoBtn;

    }
    @FindBy(id="btnIncreaseFont")
    WebElement increaseBtn;
    public void clickOnIncreaseBtn(){
        increaseBtn.click();

    }
    @FindBy(id="btnDecreaseFont")
    WebElement decreaseBtn;
    public void clickOnDecreaseBtn(){
        decreaseBtn.click();

    }
    @FindBy(id="textFontSize")
    WebElement textFontSize;
    public WebElement getTextFontSize(){
        return textFontSize;

    }
    @FindBy(id="formToColorize")
    WebElement formToColorize;
    public WebElement getFormToColorize(){
        return formToColorize;

    }
    @FindBy(id="bgColor")
    WebElement bgColorInput;
    public WebElement getBgColorInput(){
        return bgColorInput;

    }
    @FindBy(id="btnSetBgColor")
    WebElement setColorBtn;
    public void clickOnSetColorBtn(){
        setColorBtn.click();

    }

}
