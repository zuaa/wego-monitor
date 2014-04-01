package com.heiji

import org.apache.commons.mail.HtmlEmail
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

import com.tools.HttpUtils

class Lushichuanshuo extends Base {

	static main(args) {


		//		def   mongo = new GMongo("127.0.0.1:27017")
		//		def    db = mongo.getDB("zuaamaid")
		//		GridFS myFS = new GridFS(db,"lushichuanshuo");

		37.times { tims->
			def l=readUrlImage("http://hs.tgbus.com/db/?classname=&Page=${tims}");
			l.each {
				def name="${it["name"]}.jpg";
				def address="${it["url"].trim()}" 
				sendEmail(name,address) 
				sleep(10000);
			}
		}
	}

	//http://hs.tgbus.com/db/?classname=&Page=37

	static readUrlImage(url){
		try{
			def reList=[]
			String html = HttpUtils.get(url.toString());
			Document  doc =   Jsoup.parse(html);
			doc.select(".c_list>li")?.each {
				def re=[:]
				re["url"]=it.select(".c_pic>a>img")?.first()?.attr("abs:data-src")
				re["info"]=it.getElementsByClass("c_txt")?.first()?.text()
				re["name"]=it.select(".c_txt>strong>a")?.text().trim()
				reList.add(re);
			}
			return reList
		}catch(e){
			println e;
		}
	}


	static String sendEmail(String subject, String msg) {
		HtmlEmail email = new HtmlEmail();
		email.setHostName("smtp.163.com");
		email.setCharset("utf-8");
		email.addTo("zu-q@163.com", "黑姬");
		email.setFrom("zuaa-q@163.com", "xuping");
		email.setAuthentication("zuaa-q", "seedcat");
		email.setSubject(subject);
		email.setMsg(msg);
		email.send();
	}
}
