package Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentManager {
	private static ExtentReports extent;
	private static ExtentTest test;
	private static ExtentHtmlReporter htmlReporter;
	private static String filePath = "./newextentreport.html";
	
	
	public static ExtentReports getExtent(){
		if (extent != null)
                    return extent; //avoid creating new instance of html file
                extent = new ExtentReports();		
		extent.attachReporter(getHtmlReporter());
		return extent;
	}
 
	private static ExtentHtmlReporter getHtmlReporter() {
                htmlReporter = new ExtentHtmlReporter(filePath);
	        // make the charts visible on report open
//                htmlReporter.config().setChartVisibilityOnOpen(true);
//                htmlReporter.config().setDocumentTitle("Gaurav's Automation Report");
//                htmlReporter.config().setReportName("Gaurav Test Report");
                htmlReporter.loadXMLConfig("./configs/extent-config.xml");
                return htmlReporter;
	}
	
	public static ExtentTest createTest(String name, String description){
		test = extent.createTest(name, description);
		return test;
	}

}
