//package com.maid.autoCode.bestpricecn.weibo
//
//import com.maid.autoCode.bestpricecn.MyWebDriver
//import com.maid.autoCode.bestpricecn.net.Xiaohua
///**
// * Created with IntelliJ IDEA.
// * User: xuping
// * Date: 13-9-30
// * Time: 下午2:28
// * To change this template use File | Settings | File Templates.
// */
//class weibo163 extends MyWebDriver {
//
//    static main(args) {
//
//       login()
//
////        sayWithImg("http://t.163.com/3882350330","今天天气错了。",saveImg("http://f.hiphotos.baidu.com/image/q%3D100%3Ba0%3D+%2C1%2C1/sign=ca6b41cb7b310a55c224daf4877e2299/8b13632762d0f703acd24ee40afa513d2697c5a2.jpg"))
//        //sayWithImg("http://t.163.com/3882350330","屌丝的春天。",saveImg("http://h.hiphotos.baidu.com/image/q%3D100%3Ba0%3D+%2C1%2C1/sign=72c92ef2ad51f3dec5b2bd64a4d5912f/9e3df8dcd100baa122cea1834510b912c8fc2e2c.jpg"))
//        //    addFans(1000)
//
//        //读取baidu的图片
//
////        def meizi= MeiziTu.allImageurl();
//        println meizi
//        int i=0;
//        Xiaohua.xiaohua().each{
//                if(meizi.size()>i){
//                    sayWithImg("http://t.163.com/bestpricecn",it,saveImg("${meizi[i]}"))
//                    i=i+1;
//                }
//        }
//
//
//        quit(driver)
//    }
//
//    static login() {
//        driver.get("http://t.163.com/session#f=topnav");
//
//        driver.findElement(By.id("loginEmailInput")).clear();
//        driver.findElement(By.id("loginEmailInput")).sendKeys("zu-q");
//        driver.findElement(By.id("loginPassword")).clear();
//        driver.findElement(By.id("loginPassword")).sendKeys("seedcat");
//        driver.findElement(By.id("loginBtn")).click();
//    }
//
//
//    static say(url,something) {
//        driver.get(url);
//        driver.findElement(By.cssSelector("textarea.publishBox-textarea")).click();
//        driver.findElement(By.cssSelector("textarea.publishBox-textarea")).clear();
//        driver.findElement(By.cssSelector("textarea.publishBox-textarea")).sendKeys(something);
//        driver.findElement(By.cssSelector("a.submit-trigger")).click();
//        sleep(3000)
//        println("这里还是喘口气，别让人家看不起！")
//    }
//    static sayWithImg(url,something,filename) {
//        driver.get(url);
////        driver.findElement(By.cssSelector("textarea.publishBox-textarea")).click();
////        driver.findElement(By.cssSelector("textarea.publishBox-textarea")).clear();
//        driver.findElement(By.cssSelector("textarea.publishBox-textarea")).sendKeys(something);
//
//        sleep(2000)
//        driver.findElement(By.xpath("//div[@id='publishBox']/div/div[2]/div/a/em")).click();
//
//
//        sleep(2000)
//        println("等等窗口")
//        println("选择文件${filename}")
////        driver.findElement(By.xpath("//input[@name='Filedata']")).clear();
//        driver.findElement(By.xpath("//input[@name='Filedata']")).sendKeys("${filename}");
//
//        println("等等文件")
//        sleep(3000)
//        driver.findElement(By.cssSelector("a.submit-trigger")).click();
//        sleep(3000)
//        println("这里还是喘口气，别让人家看不起！")
//    }
//
///**
// * 添加粉丝，TODO 未完成
// * @param times
// */
//    static addFans(int times){
//        driver.get("http://t.163.com/3882350330/followers");
//        sleep(5000)
//        driver.findElement(By.linkText("+关注")).click();
//        sleep(5000)
//        driver.findElement(By.id("wrapper")).click();
//    }
//
//
//
//   //saveImg("http://b.hiphotos.baidu.com/image/q%3D100%3Ba0%3D+%2C1%2C1/sign=5d2962e6f21f3a295cc8d1cea91edd01/738b4710b912c8fc12b335f5fe039245d6882142.jpg")
//
//}