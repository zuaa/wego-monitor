package com.maid.autoCode.bestpricecn

import org.openqa.selenium.Alert
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.Select

/**
 * Created with IntelliJ IDEA.
 * User: xuping
 * Date: 13-9-25
 * Time: 下午9:16
 * To change this template use File | Settings | File Templates.
 */
class AddProduction extends MyWebDriver {
    static main(args) {

            def runall = """丝袜 美腿|,2,53,|丝袜
时尚气质风衣|,2,17,|风衣
性感秋装|,2,20,|连衣裙
女士性感内裤蕾丝透明|,1,23,|女士内裤
女士性感高帮鞋蕾丝|,4,38,|女士高帮鞋
丁字裤|,1,23,|女士内裤
创意产品|,61,|创意产品
生日礼物|,61,63,|生日礼物
创意礼物|,61,62,|创意礼物
限时促销|,57,|限时促销
卫衣|,57,59,|卫衣
日用品|,57,58,|日用品
母婴用品|,54,|母婴用品
尿片/洗护/喂哺/推车床|,54,56,|尿片/洗护/喂哺/推车床
背袋/背带|,54,55,|背袋/背带
品牌女装|,2,|品牌女装
秋装|,2,64,|秋装
打底裤|,2,60,|打底裤
丝袜|,2,53,|丝袜
连衣裙|,2,20,|连衣裙
针织衫|,2,19,|针织衫
雪纺衫|,2,18,|雪纺衫
风衣|,2,17,|风衣
半身裙|,2,16,|半身裙
牛仔裤|,2,15,|牛仔裤
衬衫|,2,14,|衬衫
裤子|,2,13,|裤子
T恤|,2,12,|T恤
精致内衣|,1,|精致内衣
全棉内裤|,1,25,|全棉内裤
塑身上衣|,1,24,|塑身上衣
女士内裤|,1,23,|女士内裤
男士内裤|,1,22,|男士内裤
精品文胸|,1,21,|精品文胸
时尚女鞋|,4,|时尚女鞋
短靴|,4,39,|短靴
高帮鞋|,4,38,|高帮鞋
凉鞋|,4,37,|凉鞋
靴子|,4,36,|靴子
帆布鞋|,4,35,|帆布鞋
单鞋|,4,34,|单鞋
潮流女包|,6,|潮流女包
女士双肩包|,6,45,|女士双肩包
女士手拿|,6,44,|女士手拿
女士手提|,6,43,|女士手提
女士单肩|,6,42,|女士单肩
美容护肤|,8,|美容护肤
眉笔/眉粉|,8,51,|眉笔/眉粉
复方精油|,8,50,|复方精油
单方精油|,8,49,|单方精油
精华液|,8,48,|精华液
香水|,8,47,|香水
面膜|,8,46,|面膜
时尚饰品|,7,|时尚饰品
户外运动|,9,|户外运动
男士用品|,52,|男士用品
精品男装|,52,3,|精品男装
皮衣|,52,3,33,|皮衣
风衣|,52,3,32,|风衣
Polo衫|,52,3,31,|Polo衫
牛仔裤|,52,3,30,|牛仔裤
夹克|,52,3,29,|夹克
休闲裤|,52,3,28,|休闲裤
衬衫|,52,3,27,|衬衫
T恤|,52,3,26,|T恤
流行男鞋|,52,3,5,|流行男鞋
帆布鞋|,52,3,5,41,|帆布鞋
低帮鞋|,52,3,5,40,|低帮鞋
手机数码|,10,|手机数码
电子产品|,65,|电子产品
硬盘|,65,67,|硬盘
混合硬盘|,65,67,68,|混合硬盘
硬盘|,65,66,|硬盘
母婴玩具|,11,|母婴玩具"""
            runall.eachLine {
                try {
                    run(it)
                } catch (Exception e) {
                    quit(driver);
                }
            }
            //f5(driver)
            quit(driver);

    }

    static run(String it) {


        def keys = it.tokenize("|")[0];
        def type = it.tokenize("|")[1]

        WebDriver driver = getDriver();
        login(driver)
        driver.get("http://www.bestpricecn.com/index.php/tadmin/product/search")
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("${keys}");
        driver.findElement(By.cssSelector("input.button-style2")).click();
        driver.findElement(By.cssSelector("td > input[type=\"checkbox\"]")).click();
        driver.findElement(By.cssSelector("input.button-style")).click();
        sleep(1000)
        new Select(driver.findElement(By.xpath("//select[@id='catalog_id']"))).selectByValue(type);

        driver.findElement(By.xpath("//button[2]")).click();
        sleep(20000)
        Alert alert = driver.switchTo().alert();
        alert.accept();

    }
}
