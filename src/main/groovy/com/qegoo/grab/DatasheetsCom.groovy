package com.qegoo.grab

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

import com.gmongo.GMongo

class DatasheetsCom {

	static main(args) {


		//		println doc.html()
		def   mongo = new GMongo("192.168.3.140:27017")
		def    db = mongo.getDB("qegooPachong")

		int times=1;

		while(true){
			def modelNames=db.datasheets.find(state: [$ne: "1"]).limit(100).sort(timer: 1)
			modelNames.each {
				//http://127.0.0.1/proxy?target_url=
				def url="http://www.datasheets.com/search/insatancepartsearch"
				Document  doc =null
				try {
					doc=Jsoup.connect(url)
							.data("partnumberParam", "${it.modelname}")
							.data("pageNumberParam", "0")
							.data("manIdFilter", "0")
							.data("sponsoredAdID", "false")
							.userAgent("Mozilla")
							.post();
				} catch (Exception e) {
					println "抓取失败1 重试 "
					try {
						doc=Jsoup.connect(url)
								.data("partnumberParam", "${it.modelname}")
								.data("pageNumberParam", "0")
								.data("manIdFilter", "0")
								.data("sponsoredAdID", "false")
								.userAgent("Mozilla")
								.post();
					} catch (Exception e1) {
						println "抓取失败2 重试 "
						try {
							doc=Jsoup.connect(url)
									.data("partnumberParam", "${it.modelname}")
									.data("pageNumberParam", "0")
									.data("manIdFilter", "0")
									.data("sponsoredAdID", "false")
									.userAgent("Mozilla")
									.post();
						} catch (Exception e2) {
							println "抓取失败3 重试 "
							try {
								doc=Jsoup.connect(url)
										.data("partnumberParam", "${it.modelname}")
										.data("pageNumberParam", "0")
										.data("manIdFilter", "0")
										.data("sponsoredAdID", "false")
										.userAgent("Mozilla")
										.post();
							} catch (Exception e3) {
								println "抓取失败 放弃"
							}
						}
					}
					e.printStackTrace()
				}
				if(doc!=null){
					println "抓取到数据（${it.modelname}） 开始分析页面，并保存到数据 "
					save(doc.select(".searchresultsrow1"),db);
					save(doc.select(".searchresultsrow2"),db);
					it<<[state:"1"]
					db.datasheets.save it
				}else{
					//抓取失败，标记一下
					it<<[state:"0"]
					db.datasheets.save it
				}
				//times

				if(times++%5==0){
					println("抓5个休息1妙")
					//抓5个休息1妙
					Thread.sleep(1*1000);
				}
			}
			println("抓一组（100）休息20妙")
			Thread.sleep(10*1000);
		}


	}
	static def save(trs1,db){
		trs1.each {
			if(db.datasheets.findOne(modelname: it.select("td").get(1).select("a")?.first().text())!=null){
				println "${it.select("td").get(1).select("a")?.first().text()}已经存在了,放弃了，下载再取，"
			}else{
				db.datasheets<<[modelname:it.select("td").get(1).select("a")?.first().text(),
					url:it.select("td").get(1).select("a")?.first().attr("href")
					,timer:System.currentTimeMillis()
				]
			}
		}
	}
}

