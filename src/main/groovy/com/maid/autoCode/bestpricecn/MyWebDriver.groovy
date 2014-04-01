package com.maid.autoCode.bestpricecn

import com.tools.BaseEmail;

import org.openqa.selenium.Alert
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver

import java.util.concurrent.TimeUnit

/**
 * Created with IntelliJ IDEA.
 * User: xuping
 * Date: 13-9-25
 * Time: 下午9:09
 * To change this template use File | Settings | File Templates.
 */
class MyWebDriver extends BaseEmail {
    static WebDriver driver = new FirefoxDriver();

    def static getDriver() {
        if (driver == null) {
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return driver;
    }

    static def login(WebDriver driver) {
        driver.get("http://www.bestpricecn.com/index.php/tadmin/login");
        driver.findElement(By.id("user_name")).sendKeys("zuaa");
        driver.findElement(By.name("password")).sendKeys("zuaa@428");
        driver.findElement(By.cssSelector("input[type=\"image\"]")).click();
        Thread.sleep(10000);
        println("等待登陆完成")
    }

    static def f5(driver) {
        driver.get("http://www.bestpricecn.com/index.php/tadmin/make_html");
        driver.findElement(By.xpath("//input[2]")).click();
        driver.findElement(By.id("sbtn")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        int ii = 0;
        while (ii < 60 * 5) {
            try {
                String text = driver.findElement(By.cssSelector("#process > tbody > tr > td")).getText();
                if (!text.equals("本次页面生成完毕")) {
                    println("本次页面没生成完毕 等待1秒钟")
                    Thread.sleep(1000);
                }
            } catch (Error e) {
                println("没找到对象 等待1秒钟")
                Thread.sleep(1000);
            }
            ii = ii + 1;
        }
        Thread.sleep(3000);
        println("操作完成,等待3秒退出。")
    }

    static def quit(driver) {
        if (driver != null) {
            driver.quit();
        }

    }


}
