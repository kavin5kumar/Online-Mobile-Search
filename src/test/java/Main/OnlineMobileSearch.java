package Main;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import InitialSetup.DriverSetup;
import Utility.ElementsUtil;
import Utility.ExcelUtil;

public class OnlineMobileSearch {
	
	static WebDriver driver;
	static String browser;
	static boolean checkList = true;
	
	// -- MAIN FUNCTIONS -- 
	
	public static void getBrowser() {
		Scanner sc = new Scanner(System.in);
		System.out.println("-----------------------------------------");
		System.out.println();
		System.out.println("---------Online Mobile Search------------");
		System.out.println();
		System.out.println("-----------------------------------------");
		System.out.println();
		System.out.println();
		System.out.println("-----------------------------------------");
		System.out.println();
		System.out.println("Enter the browser type (Chrome or Edge):");
		System.out.println();
		System.out.println("-----------------------------------------");
		System.out.println();
		
		browser = sc.nextLine();
		
		System.out.println();
		System.out.println("-----------------------------------------");
		System.out.println();
		sc.close();
	}
	
	public static void setupDriver() {
		driver = DriverSetup.createDriver(browser);
		ElementsUtil.setDriver(driver);
	}

	
	public static void sendInput() throws IOException {
		String input = ExcelUtil.getInput(0,0,"\\testdata\\inputdata.xlsx");
		ElementsUtil.getElement("twotabsearchtextbox", "id").sendKeys(input);
		System.out.println();
		System.out.println("-----------------------------------------");
		System.out.println();
		System.out.println("Input Sent to Input Box");
		System.out.println();
		System.out.println("-----------------------------------------");
		System.out.println();
		screenshotStep("./Screenshots/1.Send Inputs.png");
	}
	
	public static void clickSearchButton() {
		ElementsUtil.getElement("//input[@id='nav-search-submit-button']", "xpath").click();
		System.out.println();
		System.out.println("-----------------------------------------");
		System.out.println();
		System.out.println("Button Clicked");
		System.out.println();
		System.out.println("-----------------------------------------");
		System.out.println();
	}
	
	public static void validateSearchString() throws Exception {
		String search = ElementsUtil.getElement("//span[@class='a-color-state a-text-bold']", "xpath").getText().replaceAll("\"", "");
		System.out.println("Intended Search String" + "mobile phones under 30000");
		System.out.println();
		System.out.println("-----------------------------------------");
		System.out.println();
		System.out.println("Tested Search String : " + search);
		System.out.println();
		System.out.println("-----------------------------------------");
		System.out.println();
		if(search.equalsIgnoreCase("mobile phones under 30000")) {
			System.out.println("Search string validated successfully");
		} else {
			System.out.println("Search string not validated");
			checkList = false;
		}
		System.out.println();
		System.out.println("-----------------------------------------");
		System.out.println();
		screenshotStep("./Screenshots/2.Validate Search String.png");
	}
	
	
	
	public static void validateNoOfPages() throws Exception {
		String noOfPages = getNoOfPages();
		System.out.println("No of pages : " + noOfPages);
		System.out.println();
		System.out.println("-----------------------------------------");
		System.out.println();
		if(noOfPages.matches("[0-9]-[0-9]+")) { // [1-15]
			System.out.println("Page number set validated successfully");
		} else {
			System.out.println("Page number set not matching");
			checkList = false;
		}
		System.out.println();
		System.out.println("-----------------------------------------");
		System.out.println();
		screenshotStep("./Screenshots/3.Validate No of Pages.png");
	}
	
	public static void validateNoOfItems() throws Exception {
		String noOfItems = getNoOfItems();
		System.out.println("No of items : " + noOfItems);
		System.out.println();
		System.out.println("-----------------------------------------");
		System.out.println();
		if(noOfItems.matches("[0-9,]*")) { // 13,000
			System.out.println("No of items validated successfully");
		} else {
			System.out.println("No of items not matching");
			checkList = false;
		}
		System.out.println();
		System.out.println("-----------------------------------------");
		System.out.println();
		screenshotStep("./Screenshots/4.Validate No of Items.png");
	}
	
	
	
	public static void validatecountDropDown() throws Exception {
		Select selDr = getSelectElement();
		
		int countOfOptions = selDr.getOptions().size();
		System.out.println("Count of options inside 'Sort by' dropdown : " + countOfOptions);
		System.out.println();
		System.out.println("-----------------------------------------");
		System.out.println();
		if(countOfOptions == 5) {
			System.out.println("Count of option validation successful");
		} else {
			System.out.println("Count of option validation failed");
			checkList = false;
		}
		System.out.println();
		System.out.println("-----------------------------------------");
		System.out.println();
		
	}
	
	public static void validateCorrectSelectedOption() throws Exception {
		Select selDr = getSelectElement();
		
		selDr.selectByVisibleText("Newest Arrivals");
		System.out.println("Option Selected : " + selDr.getFirstSelectedOption().getText());
		System.out.println();
		System.out.println("-----------------------------------------");
		System.out.println();
		
		if(selDr.getFirstSelectedOption().getText().equals("Newest Arrivals")) {
			System.out.println("Option selected Successfully");
		} else {
			System.out.println("Option not selected Successfully");
			checkList = false;
		}
		System.out.println();
		System.out.println("-----------------------------------------");
		System.out.println();
		screenshotStep("./Screenshots/5.Validate Count of Dropdown Elements.png");
		screenshotStep("./Screenshots/6.Validate Correct Selected Option.png");
		
	}
	
	
	
	public static void writeValue() throws Exception {
		if(checkList == true) {
			System.out.println("All Conditions Checked and Validated -- Page Tested Successfully");
			ExcelUtil.setValue(1,2 , "Successful","\\testdata\\inputdata.xlsx");
			fillGreen(1,2);
		} else {
			System.out.println("All Conditions Aren't Validated Successfully -- Please Check Your Code");
			ExcelUtil.setValue(1, 2, "Failure","\\testdata\\inputdata.xlsx");
			fillRed(1, 2);
		}
		System.out.println();
		System.out.println("-----------------------------------------");
		System.out.println();
		
	}
	
	public static void fillGreen(int r, int c) throws Exception {
		ExcelUtil.fillGreenColor(r, c, "\\testdata\\inputdata.xlsx");
	}
	
	public static void fillRed(int r, int c) throws Exception {
		ExcelUtil.fillRedColor(r, c, "\\testdata\\inputdata.xlsx");
	}
	
	public static void closeDriver() {
		DriverSetup.endDriver();
	}
	
	// -- * --
	
	// -- Helper Functions --
	
	public static String[] getStringArray() {
		List<WebElement> others = ElementsUtil.getElements("/html/body/div[1]/div[1]/span[2]/div/h1/div/div[1]/div/div", "xpath");
		
		StringBuilder o = new StringBuilder();
		
		for(WebElement w : others) {
			o.append(w.getText());
		}
		return o.toString().split(" ");
	}
	
	public static String getNoOfPages() {
		return getStringArray()[0];
	}
	
	public static String getNoOfItems() {
		return getStringArray()[3];
	}
	
	public static void screenshotStep(String filepath) throws IOException {
		DriverSetup.ScreenShot(filepath);
	}
	
	public static Select getSelectElement() {
		WebElement dropD = ElementsUtil.getElement("s-result-sort-select", "id");
		Select sel = new Select(dropD);
		return sel;
	}
	
	// -- * --
	

	public static void main(String[] args) throws Exception {
		//Initial Setup -- 
		getBrowser();
		setupDriver();
		// *-- 
		
		// Main Functions --
		sendInput();
		clickSearchButton();
		// *--
		
		// Validations --
		validateSearchString();
		validateNoOfPages();
		validateNoOfItems();
		validatecountDropDown();
		validateCorrectSelectedOption();
		// *--
		
		// Post Steps --
		writeValue();
		closeDriver();
		// *--

	}

}
