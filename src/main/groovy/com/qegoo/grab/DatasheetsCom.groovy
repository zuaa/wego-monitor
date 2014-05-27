package com.qegoo.grab

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

import com.gmongo.GMongo

class DatasheetsCom {
	static def kaishishijian = new Date();
	static def chaxuncishu=1;
	static def chaxuncishu2=1;
	static main(args) {

		println "现在开始${new Date()}"

		//		println doc.html()
		def   mongo = new GMongo("192.168.3.140:27017")
		def    db = mongo.getDB("qegooPachong")
		
		int times=1;

		while(true){
			long man=System.currentTimeMillis();
			
			def modelNames=db.datasheets.find(state: [$ne: "1"]).limit(10).sort(timer: 1)
			modelNames.each {
				def searchmodelname=it.modelname
				
				//将型号去掉一位再进行抓取
				searchmodelname=searchmodelname[0..searchmodelname.length()-2]
				
				println "开始抓取 ${it.modelname} 使用： ${searchmodelname}进行搜索" 
				//http://127.0.0.1/proxy?target_url=  这里个代理之后的链接
				def url="http://heiji0zuaa.appspot.com/proxy?target_url=http://www.datasheets.com/search/insatancepartsearch"
				
				//google 总代  http://127.0.0.1:30008/
				
				
				
				
				//下面是tomcat的链接
				//http://127.0.0.1:8080/grabpoint-web/proxy?target_url=http://www.163.com/
				//http://heiji0zuaa.appspot.com/proxy?target_url=
				//http://heiji16.duapp.com/grabpoint/proxy?target_url=

				//qegoo-${it}
				
				
				
				Document  doc =null
				 
				try {
					doc=Jsoup.connect(url)
							.data("partnumberParam", "${searchmodelname}")
							.data("pageNumberParam", "0")
							.data("manIdFilter", "0")
							.data("sponsoredAdID", "false")
							.userAgent("Mozilla")
							.post();
					chaxuncishu2++
				} catch (Exception e) {
					println "抓取失败1 重试 "
					try {
						doc=Jsoup.connect(url)
								.data("partnumberParam", "${searchmodelname}")
								.data("pageNumberParam", "0")
								.data("manIdFilter", "0")
								.data("sponsoredAdID", "false")
								.userAgent("Mozilla")
								.post();
						chaxuncishu2++
					} catch (Exception e1) {
						println "抓取失败2 重试 "
						try {
							doc=Jsoup.connect(url)
									.data("partnumberParam", "${searchmodelname}")
									.data("pageNumberParam", "0")
									.data("manIdFilter", "0")
									.data("sponsoredAdID", "false")
									.userAgent("Mozilla")
									.post();
							chaxuncishu2++
						} catch (Exception e2) {
							println "抓取失败3 重试 "
							try {
								doc=Jsoup.connect(url)
										.data("partnumberParam", "${it.searchmodelname}")
										.data("pageNumberParam", "0")
										.data("manIdFilter", "0")
										.data("sponsoredAdID", "false")
										.userAgent("Mozilla")
										.post();
								chaxuncishu2++
							} catch (Exception e3) {
								println "抓取失败 放弃"
							}
						}
					} 
				}
			 
				if(doc!=null){
					println "抓取到数据（${searchmodelname} ||||||||||||||||||||） 开始分析页面，并保存到数据   " 
					if(doc.html().contains("Sorry")){

						if(doc.html().contains("no results"))  {
							println "没找到内容 XXXXXXXXXX ${doc.text()}";
							it<<[state:"1"]
							db.datasheets.save it
						}else{
							println "被屏蔽-撤退 XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX ${doc.text()}";
						} 
						//println doc.html();
					}else{
						save(doc.select(".searchresultsrow1"),db);
						save(doc.select(".searchresultsrow2"),db);
						it<<[state:"1"]
						db.datasheets.save it
						println "save ${it}";
					}
				}else{
				
					//抓取失败，标记一下
					it<<[state:"0"]
					db.datasheets.save it
				}
				//times
				if(times++%5==0){
					println("抓5个休息1妙（=======暂X停======）")
					//抓5个休息1妙
					Thread.sleep(1*1000);
				} 
				println "执行的时间是 ${(System.currentTimeMillis()-man)/1000}"
				man=System.currentTimeMillis();
				println "开始抓取时间${kaishishijian}      抓取的数据：${chaxuncishu}  网站请求的次数${chaxuncishu2}"
			}
			println("抓一组休息10妙（=======暂X停======）")
			Thread.sleep(3*1000);
		}
 
	}
	static def save(trs1,db){
		println "开始抓取时间${kaishishijian}}"
		trs1.each {
			if(db.datasheets.findOne(modelname: it.select("td").get(1).select("a")?.first().text())!=null){
				println "${it.select("td").get(1).select("a")?.first().text()}已经存在了,放弃了，"
			}else{
				println """modelname:${it.select("td").get(1).select("a")?.first().text()} VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV"""
				println "开始抓取时间${kaishishijian}      抓取的数据：${chaxuncishu++}  网站请求的次数${chaxuncishu2}"
				db.datasheets<<[modelname:it.select("td").get(1).select("a")?.first().text(),
					url:it.select("td").get(1).select("a")?.first().attr("href")
					,timer:System.currentTimeMillis()
				]
			}
		}
	}
}

