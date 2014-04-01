package com.maid.autoCode.bestpricecn.net

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

/**
 * Created with IntelliJ IDEA.
 * User: xuping
 * Date: 13-10-12
 * Time: 下午8:44
 * To change this template use File | Settings | File Templates.
 */
class Xiaohua {
    static main(args){
        //http://www.aizhufu.cn/duanxinku/column/272/1.html
        //w2 readContent
        println getXiaohua()
    }
    static getXiaohua(){
        def url="http://www.aizhufu.cn/duanxinku/column/272/1.html"
        Document doc = Jsoup.connect(url).get();
        def re=[]
        doc.getElementsByClass("readContent").each {
            re<< it.text()
        }
        return re


    }
}
