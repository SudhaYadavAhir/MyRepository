package com.evs.vtiger.login;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.evs.vtiger.generic.GenericLayer;


public class LoginPage {
	private GenericLayer gl;
	
	public LoginPage(GenericLayer gl) {
	this.gl=gl;
		PageFactory.initElements(gl.driver, this);
	}
	
@FindBy(xpath="//input[@name='user_name']")
private WebElement userName_ED;

@FindBy(xpath="//input[@name='user_password']")
private WebElement Password_ED;

@FindBy(xpath="//input[@name='Login']")
private WebElement login_BT;

public void validLogin() {
	gl.sendInput(userName_ED, "admin");
	gl.sendInput(Password_ED, "admin");
	gl.click(login_BT);
}
public void invalidLogin() {
	gl.sendInput(userName_ED, "assd");
	gl.sendInput(Password_ED, "asdf");
	gl.click(login_BT);
}

}
