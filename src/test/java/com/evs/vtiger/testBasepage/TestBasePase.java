package com.evs.vtiger.testBasepage;

import org.testng.annotations.BeforeMethod;
import com.evs.vtiger.generic.GenericLayer;

public class TestBasePase {
	protected GenericLayer gl=new GenericLayer();	

	@BeforeMethod
	public void validLogin() {
		gl.loadConfig("src/test/resources/Config.properties");
		String browser=gl.getProperty("BrowserName");
		gl.launchBrowser(browser);
		String url=gl.getProperty("Url");
		gl.openUrl(url);
	}
	

}
