package com.maid.autoCode.bestpricecn.email

import org.apache.commons.mail.HtmlEmail
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver
/**
 * Created with IntelliJ IDEA.
 * User: xuping
 * Date: 13-9-30
 * Time: 上午10:10
 * To change this template use File | Settings | File Templates.
 */
class QqUserAndEmail  {

	static main(args) {
		WebDriver driver = new FirefoxDriver();
		driver.get("http://zc.qq.com/chs/index.html");
		driver.findElement(By.cssSelector("div.box.box_3 > label.item")).click();
		driver.findElement(By.id("nick")).clear();
		driver.findElement(By.id("nick")).sendKeys("zuaa");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("aaaaaaaa");
		driver.findElement(By.id("password_again")).clear();
		driver.findElement(By.id("password_again")).sendKeys("aaaaaaaa");
		driver.findElement(By.id("sex_2")).click();
		driver.findElement(By.id("year_value")).click();
		driver.findElement(By.id("year_32")).click();
		driver.findElement(By.id("month_value")).click();
		driver.findElement(By.id("month_6")).click();
		driver.findElement(By.id("day_value")).click();
		driver.findElement(By.id("day_5")).click();
		//检测是否存在验证码
		if ("".equals(driver.findElement(By.id("code")).getAttribute("value"))) {
			println "验证码存在，需要填写"
			String code = "";
			while (code.length() < 4) {
				code = driver.findElement(By.id("code")).getAttribute("value");
				sleep(2000);
			}
		}
		driver.findElement(By.id("submit")).click();
		String aaa = driver.findElement(By.id("newUin")).getText();
		if (aaa != null) {
			saveMessage "[qq账户信息]" aaa
		}
	}
	
	static String saveMessage(String subject, String massage) {
		sendEmail subject, massage, "zuaa@163.com"
	}

	static String sendEmail(String subject, String msg, String to) {
		HtmlEmail email = new HtmlEmail();
		email.setHostName("113.106.92.173");
		email.setCharset("utf-8");
		email.addTo(to, "黑姬");
		email.setFrom("service@qic.com.cn", "xuping");
		email.setAuthentication("service@qic.com.cn", "qic2010");
		email.setSubject(subject);
		email.setMsg(msg);
		email.send();
	}
}