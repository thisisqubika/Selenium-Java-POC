package extentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.*;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class ExtentManager{
   
   public static ZonedDateTime NOW = ZonedDateTime.now();
   public static int DAY_NUMBER = NOW.getDayOfMonth();
   public static int YEAR_NUMBER = NOW.getYear();
   public static String BACKSLASH = "/";
   public static String MONTH = getMonthInSpanish();
   public static String YEAR = String.valueOf(YEAR_NUMBER);
   public static String DAY = String.valueOf(DAY_NUMBER);
   public static String CURRENT_TIME = getCurrentTime();
   public static String FECHA_COMPLETA = YEAR + BACKSLASH + MONTH + BACKSLASH + DAY + BACKSLASH + getCurrentTime() + BACKSLASH;
   public static String AUTOMATION_REPORT_FOLDER = "/Automation_Reports/";
   private static String reportBaseDirectory;
   private static String reportName;
   private static ExtentReports extent;
   public static final String OUTPUT_FOLDER_SCREENSHOTS ="/Screenshots/";
   public static final String REPORT_FILE_PATH = System.getProperty("user.dir") + AUTOMATION_REPORT_FOLDER + FECHA_COMPLETA;
   public static final String TIMESTAMP_FORMAT = "EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'";
   public static final String DOCUMENT_TITLE = "Moove-IT - Automation Reports";
   public static final String REPORT_NAME = " Automation Reports";
   public static final String ICON = "<img src='https://d17dednewomw88.cloudfront.net/assets/images/logo-36133121.svg' style=\"width: 100px;\">";
   public static final String CP1252_ENCODING = "cp-1252";
   public static final String ARGENTINA_ZONEID = "America/Argentina/Buenos_Aires";
   
   public static ExtentReports getInstance() {
       if (extent == null) createInstance();
       return extent;
       
   }

   public static void createInstance() {
       ExtentManager.initDirectories();
       setReportBaseDirectory(REPORT_FILE_PATH);
       
       ExtentSparkReporter spark = new ExtentSparkReporter(REPORT_FILE_PATH + DOCUMENT_TITLE + " - " +  getReportName() + " - " + CURRENT_TIME + ".html");
       sparkConfig(spark, CP1252_ENCODING, ICON + REPORT_NAME, TIMESTAMP_FORMAT, DOCUMENT_TITLE);
       
       extent = new ExtentReports();
       extent.attachReporter(spark);
       setSystemInfo(extent);
             
   }

   public synchronized static String getReportBaseDirectory() {
       return reportBaseDirectory;
       
   }
   public synchronized static void setReportBaseDirectory(String reportBaseDirectory) {
       ExtentManager.reportBaseDirectory = reportBaseDirectory;
       
   }
   public synchronized static String getReportName() {
       return reportName;
       
   }
   public synchronized static void setReportName(String reportName) {
       ExtentManager.reportName = reportName;
       
   }
   public static void initDirectories() {
       try {
          createFolder(REPORT_FILE_PATH + OUTPUT_FOLDER_SCREENSHOTS);
          
       } catch (Exception e) {
           e.printStackTrace();
           
       }
       
   }
   public static void createFolder(String folderPath) {
       File file = new File(folderPath);
       if (!file.exists()) file.mkdirs();
       
   }

    public static String getMonthInSpanish() {
   	 LocalDate localDate = LocalDate.now();
   	 Locale locale=new Locale("es", "ES");
        String monthInSpanish=localDate.format(DateTimeFormatter.ofPattern("MMMM", locale));

        return upperCaseFirst(monthInSpanish);
        
    }
    public static String upperCaseFirst(String val) {
        char[] arr = val.toCharArray();
        arr[0] = Character.toUpperCase(arr[0]);
        return new String(arr);
        
     }
    public static void setSystemInfo(ExtentReports anExtent) {
    	anExtent.setSystemInfo("OS",  System.getProperty("os.name"));
    	anExtent.setSystemInfo("Java",  System.getProperty("java.specification.version"));
    	anExtent.setSystemInfo("User",  System.getProperty("user.name"));
    	
    }
    public static void sparkConfig(ExtentSparkReporter aSpark, String anEncoding, String aFullReportName,
                                   String aTimeStampFormat, String aDocumentTitle) {
    	aSpark.config().setTheme(Theme.DARK);
    	aSpark.config().setEncoding(anEncoding);
    	aSpark.config().setReportName(aFullReportName);
    	aSpark.config().setProtocol(Protocol.HTTPS);  
    	aSpark.config().setTimeStampFormat(aTimeStampFormat);
    	aSpark.config().setDocumentTitle(aDocumentTitle);

        
    }
    public static String getCurrentTime() {
    	String time = LocalTime.now(ZoneId.of(ARGENTINA_ZONEID)).truncatedTo(ChronoUnit.SECONDS).toString();
        return time.replace(":", "");
    	
    }
    
}