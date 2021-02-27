package com.githubapi.pages;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;
import com.githubapi.framework.webdriver.WebDriverBase;
import com.google.common.base.Joiner;

public class BasePage {
	public WebDriver driver;
	public Select select;
	public static ExtentTest Logger = WebDriverBase.getLogger();

	public void Click(WebElement pageElement) {
		String elementName = pageElement.toString();
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", pageElement);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", pageElement);
		System.out.println(elementName + "  is clicked");

	}

	public void UpdateDataIntoField(WebElement pageElement, String value) {

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", pageElement);
			pageElement.sendKeys(value);			
			System.out.println("Input text in: "+pageElement);
			
	}
	
	// Java Script Scroll Into View Method to click on Web Element

	public void ScrollIntoView(WebElement pageElement) {

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", pageElement);

	}

	// Select Option From the Dropdown

	public void selectListBox(WebElement pageElement, String value, String by) {
		try {

			Select select = new Select(pageElement);

			if (by.equalsIgnoreCase("ByVisibleText"))
				select.selectByVisibleText(value);

			else if (by.equalsIgnoreCase("Index"))
				select.selectByIndex(Integer.parseInt(value));

			else if (by.equalsIgnoreCase("value"))
				select.selectByValue(value);

			else
				select.selectByVisibleText(value);

		} catch (Exception exception) {

		}
	}
	
	public static String getuniqueIDByCurrentTime() {
		SimpleDateFormat format = new SimpleDateFormat("MMddyyyyHHmm");
		Calendar cal = Calendar.getInstance();
		String uniqID = format.format(cal.getTime());
		return uniqID;
	}
	
	public String convertHashmapToString(Map<String, String> map) {
	    return Joiner.on(",").withKeyValueSeparator("=").join(map);
	}
	
}
