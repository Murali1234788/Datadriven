package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Extentreport {
	
	public static ExtentReports Getextentreport() {
		
		String extentreportfilepath=System.getProperty("user.dir")+"\\Reports\\extentreport.html";
		 ExtentSparkReporter reporter=new ExtentSparkReporter(extentreportfilepath);
		 reporter.config().setReportName("Murali JP DD learn");
		 reporter.config().setDocumentTitle("Jayapriya DA");
		 
		 ExtentReports reports=new ExtentReports();
		 reports.attachReporter(reporter);
		 reports.setSystemInfo("Test NG version", "7.10.1");
		 reports.setSystemInfo("Executed By", "JP Da");
		 
		 return reports;
	}

}
