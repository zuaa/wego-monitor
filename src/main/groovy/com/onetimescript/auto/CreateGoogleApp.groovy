package com.onetimescript.auto

import com.thoughtworks.selenium.DefaultSelenium
import com.thoughtworks.selenium.Selenium

/***
 * 利用测工具selenium，来批量创建项目
 */

def username="robinwu2008"
def psw ="helloworld"

Selenium selenium; 
selenium = new DefaultSelenium("localhost", 4444, "*chrome", "https://accounts.google.com/");
selenium.start();

selenium.open("/ServiceLogin?sacu=1&continue=https%3A%2F%2Fappengine.google.com%2F_ah%2Fconflogin%3Fcontinue%3Dhttps%3A%2F%2Fappengine.google.com%2Fstart&service=ah&ltmpl=ae");
 
selenium.type("id=Email", "${username}@gmail.com");
selenium.type("id=Passwd", "${psw}");
selenium.click("id=signIn");
selenium.waitForPageToLoad("30000");


Thread.sleep(50000);


24.times {
	selenium.open("https://appengine.google.com/start/createapp");
	 
	selenium.type("id=ae-createapp-id", "${username}-${it+100}");
	selenium.click("id=ae-createapp-submit");
	selenium.waitForPageToLoad("30000");
}
selenium.stop();













