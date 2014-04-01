package com.maid.autoCode.bestpricecn.net;/**
 * Created with IntelliJ IDEA.
 * User: xuping
 * Date: 13-9-30
 * Time: 下午2:48
 * To change this template use File | Settings | File Templates.
 */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
class BaseNet extends SaveUrl{
    static Document doc = null;


    static main(args) {
        def url = "http://www.bestpricecn.com/"
        println getTitle(url)


        println getdescription(url)

        println getkeywords(url)
    }

    static getDoc(url) {
        doc = Jsoup.connect(url).get();

    }

    static String getTitle(url) {
        if (doc == null) {
            //println("没有doc")
            getDoc(url)
        }
        Elements title1 = doc.getElementsByTag("title")
        return title1.first().text()
    }

    static String getkeywords(url) {
        String re = ""
        if (doc == null) {
            //println("没有doc")
            getDoc(url)
        }
        Elements meta = doc.getElementsByTag("meta")
        meta.each {
            // println it.attr("name").toLowerCase()
            if ("keywords".equals(it.attr("name").toLowerCase())) {
                //  println "这里是想要"
                re = it.attr("content")
            }
        }
        return re
    }

    static String getdescription(url) {
        String re = ""
        if (doc == null) {
            //  println("没有doc")
            getDoc(url)
        }
        Elements meta = doc.getElementsByTag("meta")
        meta.each {
            if ("description".equals(it.attr("name").toLowerCase())) {
                // println "这里是想要"
                re = it.attr("content")
            }
        }
        return re
    }

}