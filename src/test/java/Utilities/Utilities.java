package Utilities;

import Pages.BasePage;
import extentReport.ExtentTestManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class Utilities extends BasePage {
    public Utilities(WebDriver remoteDriver){
        driver = remoteDriver;
        initElements(driver, this);

    }
    public Utilities(){

    }
    public static final String WINDOW_OPEN = "window.open()";
	public static final String SCROLL_INTO_VIEW = "arguments[0].scrollIntoView();";
	public static final String SCROLL_TO_DIR_START = "window.scrollBy(0,";
	public static final String SCROLL_TO_DIR_END = ")";

    /*
     * This method is used tu initialize the properties from config_file
     * */
    public Properties init_prop() throws IOException {
        FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");

        prop = new Properties();
        prop.load(ip);
        return prop;

    }
    public String getPropertyByValue(Properties properties, String aValue){
        return properties.getProperty(aValue).trim();

    }
    public JavascriptExecutor createJse() {
        return (JavascriptExecutor)driver;
    	
    }
    public void scrollToADirection(String aDirection) {
    	JavascriptExecutor jse = createJse();
    	jse.executeScript(SCROLL_TO_DIR_START + aDirection + SCROLL_TO_DIR_END);
    	
    }
    public void scrollToADirectionPixel() {
    	JavascriptExecutor jse = createJse();
    	jse.executeScript("window.scrollBy(0,12500)");
    	
    }
    public void scrollIntoView(WebElement anElement) {
    	JavascriptExecutor jse = createJse();
    	jse.executeScript(SCROLL_INTO_VIEW, anElement);
    
    }
    public void getTabs(int aTab){
    	ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
    	driver.switchTo().window(tabs.get(aTab));
    	
    }
    public Alert getSwitchToAlert() {
    	return driver.switchTo()
                     .alert();
    	
    }
    public void acceptOnOkPrompt() throws InterruptedException {
    	this.getSwitchToAlert().accept();
        Thread.sleep(1000);
        
    }
    public String AlertGetText() {
        return this.getSwitchToAlert().getText();
        
    }

    public int pixelsToInt(String aPixelSize){
        Assert.assertTrue(aPixelSize.endsWith("px"));

        String newIntToBeParsed = aPixelSize.substring(0, aPixelSize.length() - 2);
        return Integer.parseInt(newIntToBeParsed);

    }
    public void getReportLog(String aString) {
        ExtentTestManager.reporterLog(aString);

    }
    public void getReportJsonLog(String aString, String aNodeTitle) {
        ExtentTestManager.reporterJsonLog(aString, aNodeTitle);

    }

}
