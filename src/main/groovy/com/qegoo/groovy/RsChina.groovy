package com.qegoo.groovy

import com.thoughtworks.selenium.DefaultSelenium
import com.thoughtworks.selenium.Selenium


class RsChina {


	static main(args){
		Selenium selenium;
		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://china.rs-online.com/");
		selenium.start();
		selenium.setTimeout("1000010")
		int i=0;
		println i++
		selenium.open("/web/p/relay-sockets/0353972/");
		println i++
		selenium.type("name=addToCartForm_147448:qtyWeb", "111111");
		println i++
		selenium.click("id=addToCartForm_147448:rtqLinkTop");
		println i++
		selenium.type("id=rtqCheckerForm:rtqCheckQty", "222222");
		println i++
		selenium.click("id=rtqCheckerForm:rtqCheckButton");
		
		println selenium.getText("//span[@id='rtqCheckerForm:AtpResultPanel']/div");
		 
		
		
		selenium.stop();
	}
}
