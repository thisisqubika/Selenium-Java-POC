package extentReport;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static extentReport.ExtentManager.*;

public class FareyeListener implements ITestListener {
	
	public static String THREE_DASH = " --- ";
	public static String FULL_FORMAT_DATE = "MMM_dd_yyyy_HH_mm_ss_SSS";
	public static String UNDERSCORE = "_";
	
	public static String START_STRING = "===== ";
	public static String FINISH_STRING = " =====";
	
	public static String TEST_SUITE = "Test suite ";
	public static String STARTED = "started ";
	public static String FINISHED = "finished ";
	public static String EXECUTING = "Executing : ";
	
	public static String SUCCESS = "Finish sucessfully: ";
	public static String ERRORS = "Finish with errors: ";
	
	public static String OK = "Test finished successfully";
	public static String SKIPPED = "This test was skipped: ";
	public static String FAILED = " Test Failed: ";
	
	public static String FONT_HTML_SUCCESS = "<br><font color= green>";
	public static String FONT_HTML_FAIL = "<br><font color= red>";
	public static String IMAGE_HTML = "Image: ";
	public static String FONT_CLOSE = "</font></b>";
	
	public static String SCREENSHOT_FAIL = "Failed to take screenshot ";
	
    private static long endTime;
    private static void setStartTime(long startTime) {						
    	
    }
    private static void setEndTime(long endTime) {
        FareyeListener.endTime = endTime;
        
    }
    public synchronized static long getReportName() {
        return endTime;
        
    }
    public synchronized void onStart(ITestContext context) {
    	String outPutDirectory = context.getOutputDirectory();
    	System.out.println(TEST_SUITE + STARTED + outPutDirectory + THREE_DASH + context.getName());

        setReportName(outPutDirectory.substring(58) + UNDERSCORE + context.getName());

    }
    public synchronized void onFinish(ITestContext context) {
        setStartTime(context.getStartDate().getTime());
        setEndTime(context.getEndDate().getTime());
        
        System.out.println(TEST_SUITE + FINISHED + context.getOutputDirectory() + THREE_DASH + context.getName());
        
        
    }
    public synchronized void onTestStart(ITestResult result) {
        System.out.println(START_STRING + EXECUTING + getSimpleMethodName(result) + FINISH_STRING);
        
        ExtentTestManager.createTest(result.getName(),result.getMethod().getDescription());
        ExtentTestManager.setCategoryName(getSimpleClassName(result));
        
    }
    public synchronized void onTestSuccess(ITestResult result) {
        System.out.println(START_STRING + SUCCESS + getSimpleMethodName(result) + FINISH_STRING);
        
        ExtentTestManager.getTest().assignCategory(getSimpleClassName(result));
        ExtentTestManager.getTest().pass(FONT_HTML_SUCCESS + IMAGE_HTML + FONT_CLOSE,
                          MediaEntityBuilder.createScreenCaptureFromBase64String(
                                             takeScreenshot(getSimpleMethodName(result), result)).build());

        addExtentLabelToTest(result);
        ExtentTestManager.endTest();
        
    }
    public synchronized void onTestFailure(ITestResult result) {
        System.out.println(START_STRING + ERRORS + getSimpleMethodName(result) + FINISH_STRING);
        
        ExtentTestManager.getTest().assignCategory(getSimpleClassName(result));
        ExtentTestManager.getTest().log(Status.FAIL, result.getName() + FAILED + result.getThrowable());

        ExtentTestManager.getTest().fail(FONT_HTML_FAIL + IMAGE_HTML + FONT_CLOSE,
                         MediaEntityBuilder.createScreenCaptureFromBase64String(
                                            takeScreenshot(getSimpleMethodName(result), result)).build());

        addExtentLabelToTest(result);
        ExtentTestManager.endTest();
        
    }
    public synchronized void onTestSkipped(ITestResult result) {
        System.out.println(START_STRING + SKIPPED + getSimpleMethodName(result) + FINISH_STRING);
        
        ExtentTestManager.getTest().log(Status.SKIP, result.getName() + " " + SKIPPED +  result.getThrowable());
        
        addExtentLabelToTest(result);
        ExtentTestManager.endTest();
        
    }
    public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    	
    }
    private synchronized String getSimpleClassName(ITestResult result) {
        return result.getMethod().getRealClass().getSimpleName();
        
    }
    private synchronized String getSimpleMethodName(ITestResult result) {
        return result.getName();
        
    }
    private synchronized void addExtentLabelToTest(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS)
            ExtentTestManager.getTest().pass(MarkupHelper.createLabel(OK, ExtentColor.GREEN));
        
        else if (result.getStatus() == ITestResult.FAILURE) {
            ExtentTestManager.getTest().fail(MarkupHelper.createLabel(FAILED, ExtentColor.RED));
            
        } else if(result.getStatus() == ITestResult.SKIP) {
        	ExtentTestManager.getTest().skip(MarkupHelper.createLabel(SKIPPED, ExtentColor.ORANGE));
        
        }
        
    }
    private static synchronized String takeScreenshot(String methodName, ITestResult result) {

        DateFormat dateFormat = new SimpleDateFormat(FULL_FORMAT_DATE);
        Date date = new Date();
        String dateName = dateFormat.format(date);
        
        String filePathExtent = OUTPUT_FOLDER_SCREENSHOTS + methodName + UNDERSCORE + dateName + ".png";
        String filePath = ExtentManager.getReportBaseDirectory() + filePathExtent;
        
        String encodedBase64=null;
        
        ITestContext context = result.getTestContext();
        WebDriver driver = (WebDriver) context.getAttribute("WebDriver");
        
        try {
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileInputStream fileInputStreamReader = new FileInputStream(screenshotFile); 
            
            byte[] bytes = new byte[(int) screenshotFile.length()];
            fileInputStreamReader.read(bytes);
            
            encodedBase64 = Base64.encodeBase64String(bytes);
            FileUtils.copyFile(screenshotFile, new File(filePath));
            
            fileInputStreamReader.close();
            
        }catch (IOException e){
            e.getStackTrace();
            Reporter.log(SCREENSHOT_FAIL + e, true);
            
        }
        return encodedBase64;
        
    }


}