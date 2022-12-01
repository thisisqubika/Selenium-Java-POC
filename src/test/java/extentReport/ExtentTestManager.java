package extentReport;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.MediaEntityBuilder;

import Test.BaseTest;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager extends BaseTest {

   static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
   public static ExtentReports extent = ExtentManager.getInstance();
   private static final ThreadLocal<String> categoryName = new ThreadLocal<String>();

   public static String HTML_BREAK = "<br/>";

   public static synchronized ExtentTest getTest() {
       return extentTestMap.get((int) Thread.currentThread().threadId());
       
   }
   public static ExtentReports getExtent() {
	   return extent;
	   
   }
   public static synchronized void endTest() {
       extent.flush();
       
   }
   public synchronized static void createTest(String testName, String description) {
       extentTestMap.put((int) Thread.currentThread().threadId(),
                         getExtent().createTest(testName, description)
                                    .assignDevice(getImageForCapabilities(getCapabilities(driver))));
       
   }
   public static ThreadLocal<String> getCategoryName() {
       return categoryName;
       
   }
   public static void setCategoryName(String categoryName) {
       getCategoryName().set(categoryName);
       
   }
   public synchronized static void getLog(String aLog) {
	   Reporter.log(aLog + HTML_BREAK);
	   
   }
   public synchronized static void reporterLog(String log) {
           if (ExtentTestManager.getTest() != null) {
               ExtentTestManager.getTest().log(Status.INFO, log);
               getLog(log);
               
           }
           
   }
   public synchronized static void reporterLog(String log, Status aStatus) {
       if (ExtentTestManager.getTest() != null) {
           ExtentTestManager.getTest().log(aStatus, log);
           getLog(log);
           
       }
       
   }
   public synchronized static void reporterJsonLog(String aLog, String aNodeTitle) {
	   ExtentTestManager.getTest().createNode(aNodeTitle).info(aLog);
       
   }
   public synchronized static void takeScreenShot() throws IOException {
   	   String filePathExtent = "Screenshots/" + System.currentTimeMillis() + ".jpg";
       String filePath = ExtentManager.getReportBaseDirectory() + filePathExtent;
   	   ExtentTestManager.getTest().addScreenCaptureFromPath(filePath)
   	   					.pass((Markup) MediaEntityBuilder.createScreenCaptureFromPath(filePath)
						.build());
   	
   }
    public static String getImageForCapabilities(Capabilities capabilities) {
        return capabilities.getBrowserName();

    }
    public static Capabilities getCapabilities(WebDriver driver){
        return ((HasCapabilities) driver).getCapabilities();

    }

}