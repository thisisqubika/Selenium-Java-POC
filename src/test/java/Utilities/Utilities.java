package Utilities;

import Pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Utilities extends BasePage {
    public Utilities(WebDriver remoteDriver){
        driver = remoteDriver;
        initElements(driver, this);

    }
    public Utilities(){

    }
    /*
     * This method is used tu initialize the properties from config_file
     * */
    public Properties init_prop() throws IOException, FileNotFoundException {
        FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");

        prop = new Properties();
        prop.load(ip);

        return prop;

    }

    public int pixelsToInt(String aPixelSize){
        Assert.assertTrue(aPixelSize.endsWith("px"));

        String newIntToBeParsed = aPixelSize.substring(0, aPixelSize.length() - 2);
        return Integer.parseInt(newIntToBeParsed);

    }

}
