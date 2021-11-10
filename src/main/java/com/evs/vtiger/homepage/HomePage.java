package com.evs.vtiger.homepage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.evs.vtiger.generic.GenericLayer;

public class HomePage {
	GenericLayer gl;

	public HomePage(GenericLayer gl) {
		this.gl = gl;
		PageFactory.initElements(gl.driver, this);
	}
	
	@FindBy(xpath = "//a[text()='My Home Page']")
	private WebElement homePage;
	
	@FindBy(xpath ="//td[@class='level2UnSelTab']//a[text()='Calendar']")
	private WebElement calendar;
	
	@FindBy(xpath = "//td[@style='padding-left:10px']//img")
	private WebElement plus_BT;
	
	@FindBy(xpath = "//table[@class='hdrTabBg']//a")
	private WebElement allLink;
}
