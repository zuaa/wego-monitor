package com.maid.autoCode.bestpricecn

/**
 * Created with IntelliJ IDEA.
 * User: xuping
 * Date: 13-9-22
 * Time: 下午8:51
 * To change this template use File | Settings | File Templates.
 */
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
class UpdateNews extends MyWebDriver {
    static main(args) {
        while (true) {
            run()
            sleep(1000 * 60 * 60 * 24)
        }
    }

    static void run() {
        WebDriver driver = getDriver();
        login(driver)
        rauOneByOne(driver, "http://www.bestpricecn.com/index.php/tadmin/rule/caiji/10", "明星内衣")
        rauOneByOne(driver, "http://www.bestpricecn.com/index.php/tadmin/rule/caiji/8", "明星内衣")
        rauOneByOne(driver, "http://www.bestpricecn.com/index.php/tadmin/rule/caiji/9", "时尚资讯")
        rauOneByOne(driver, "http://www.bestpricecn.com/index.php/tadmin/rule/caiji/7", "时尚资讯")
        rauOneByOne(driver, "http://www.bestpricecn.com/index.php/tadmin/rule/caiji/6", "潮流趋势")
        rauOneByOne(driver, "http://www.bestpricecn.com/index.php/tadmin/rule/caiji/5", "时尚资讯")
        rauOneByOne(driver, "http://www.bestpricecn.com/index.php/tadmin/rule/caiji/4", "潮流趋势")
        println("抓取完成，刷新静态页面")
        f5(driver);
        quit(driver);
    }



    static void rauOneByOne(def driver, def url, def type) {
        driver.get(url);
        driver.findElement(By.name("is_w")).click();
        new Select(driver.findElement(By.id("catalog_id"))).selectByVisibleText(type);
        driver.findElement(By.cssSelector("input.button-style2")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        int n = driver.findElements(By.cssSelector("font")).size();
        println "有${n}页数据需要采集"
        int i = 0;
        while (i < 30 * n) {
            try {
                String text = driver.findElement(By.cssSelector("#process > tbody > tr > td")).getText();
                if (!text.equals("全部数据采集完毕")) {
                    println("没抓取完成 等待1秒钟")
                    Thread.sleep(1000);
                }
            } catch (Error e) {
                println("没找到对象 等待1秒钟")
                Thread.sleep(1000);
            }
            i = i + 1;
        }
        Thread.sleep(10000);
        println(" 等待10秒钟，进行下一个操作。")
    }

}
