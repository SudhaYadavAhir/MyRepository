package com.evs.vtiger.testLoginpage;

import org.testng.annotations.Test;

import com.evs.vtiger.login.LoginPage;
import com.evs.vtiger.testBasepage.TestBasePase;

public class TestLoginPage extends TestBasePase {
  @Test
	public void asdf() {
	  LoginPage lp=new LoginPage(gl);
	  lp.validLogin();
  }
}
