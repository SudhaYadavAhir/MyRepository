package com.evs.vtiger.generic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.google.common.io.Files;

public class GenericLayer {
	public WebDriver driver;

	public void launchBrowser(String brwserName) {
		if (brwserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
			driver = new ChromeDriver();
		}

		else if (brwserName.equalsIgnoreCase("opera")) {
			System.setProperty("webdriver.opera.driver", "drivers/operadriver.exe");
			driver = new OperaDriver();
		}

		else if (brwserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
			driver = new FirefoxDriver();
		}
	}

	public void openUrl(String url) {
		driver.get(url);
	}

	public void sendInput(WebElement we, String input) {
		we.sendKeys(input);
	}

	public void click(WebElement we) {
		we.click();
	}

	public void clear(WebElement we) {
		we.clear();
	}

	public void screenShot(String fileName) {
		TakesScreenshot tss = (TakesScreenshot) driver;
		File tempFile = tss.getScreenshotAs(OutputType.FILE);
		File sourceFile = new File(fileName);
		try {
			Files.copy(tempFile, sourceFile);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void WindowHandles(String expectedUrl) {
		Set<String> HandlesValue = driver.getWindowHandles();
		for (String handheValue : HandlesValue) {
			driver.switchTo().window(handheValue);
			String actualUrl = driver.getCurrentUrl();
			if (actualUrl.equalsIgnoreCase(expectedUrl)) {
				break;
			}
		}
	}

	public void selectByIndex(WebElement we, int index) {
		Select sel = new Select(we);
		sel.selectByIndex(index);
	}

	public void selectByValue(WebElement we, String valueOfValueAttribute) {
		Select sel = new Select(we);
		sel.selectByValue(valueOfValueAttribute);
	}

	public void selectByVisibleText(WebElement we, String innerText) {
		Select sel = new Select(we);
		sel.selectByVisibleText(innerText);
	}

	public void actClick(WebElement we) {
		Actions act = new Actions(driver);
		act.click(we).build().perform();
	}

	public void actDoubleClick(WebElement we) {
		Actions act = new Actions(driver);
		act.doubleClick(we).build().perform();
	}

	public void actRightClick(WebElement we) {
		Actions act = new Actions(driver);
		act.contextClick(we).build().perform();
	}

	public void mouseHour(WebElement we) {
		Actions act = new Actions(driver);
		act.moveToElement(we).build().perform();
	}

	public void clickAndHold(WebElement we) {
		Actions act = new Actions(driver);
		act.clickAndHold(we).build().perform();
	}

	public void dragAndDrop(WebElement from, WebElement to) {
		Actions act = new Actions(driver);
		act.clickAndHold(from).moveToElement(to).release().build().perform();
	}

	public void dragAndDrop1(WebElement from, WebElement to) {
		Actions act = new Actions(driver);
		act.dragAndDrop(from, to).build().perform();
	}

	public void maximize() {
		driver.manage().window().maximize();
	}

	public void refresh() {
		driver.navigate().refresh();
	}

	public void navigateBack() {
		driver.navigate().back();
	}

	public void navigateForward() {
		driver.navigate().forward();
	}

	public String getAttribute(WebElement we, String attributeName) {
		String attributeValue = we.getAttribute(attributeName);
		return attributeValue;
	}

	public void verifyElementIsVisible(WebElement we) {
		boolean actual = we.isDisplayed();
		Assert.assertEquals(actual, true);
	}

	public void verifyElementIsNotVisible(WebElement we) {
		boolean actual = we.isDisplayed();
		Assert.assertEquals(actual, false);
	}

	public void verifyText(WebElement we, String expectedText) {
		String actualText = we.getText();
		Assert.assertEquals(actualText, expectedText);
	}

	public void verifyAttributeValue(WebElement we, String attributeName, String ExpectedAttributeValue) {
		String actual = we.getAttribute(attributeName);
		Assert.assertEquals(actual, ExpectedAttributeValue);
	}

	public void verifyElementIsSelected(WebElement we) {
		boolean actual = we.isSelected();
		Assert.assertEquals(actual, true);
	}

	Properties prop;

	public Properties loadConfig(String filePath) {
		prop = new Properties();
		InputStream is = null;
		try {
			is = new FileInputStream(filePath);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			prop.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}

	public String getProperty(String keyName) {
		String getProp = prop.getProperty(keyName);
		return getProp;
	}

	public void setProperty(String keyName, String keyValue) {
		prop.setProperty(keyName, keyValue);
	}
	
	
	public Workbook getWorkBook(String filePath) {
		InputStream is = null;
	try {
		is=new FileInputStream(filePath);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	Workbook wbook = null;
	String[] word=filePath.split("//.");
	if (word[1].equalsIgnoreCase("xlsx")) {
	try {
		wbook=new XSSFWorkbook(is);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	}else if (word[1].equalsIgnoreCase("xls")) {
		try {
			wbook=new HSSFWorkbook(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}else {
		System.out.println("extention is wrong");
	}
	return wbook;
	}
	
	public Sheet getSheet(Workbook wbook,String sheetName) {
		Sheet sheetObj=wbook.getSheet(sheetName);
		return sheetObj;
	}
	
	public Row getRow(Sheet sheetObj, int rowNum) {
		Row rowObj= sheetObj.getRow(rowNum)	;
		return rowObj;
	}
	
	
	public int getCellNumByCellName(Row rowObj, String expectedCell) {
		int cellCount=rowObj.getLastCellNum();
		int cellNum = -1;
		for (int i = 0; i < cellCount; i++) {
			Cell  cellObj=rowObj.getCell(i);
			String cellVal=cellObj.getStringCellValue();
			if (cellVal.equalsIgnoreCase(expectedCell)) {
			cellNum=i;	
			}
		}
		return cellNum;
	}
	public void getRowNumByRowName(Sheet sheetObj, String rowName, int cellNum) {
		int rowCount=sheetObj.getLastRowNum();
		for (int i = 0; i < rowCount; i++) {
			sheetObj.getRow(cellNum);
		}
	}
}
