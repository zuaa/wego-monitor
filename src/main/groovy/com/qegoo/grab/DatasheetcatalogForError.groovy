package com.qegoo.grab

import groovy.sql.Sql

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

import com.gmongo.GMongo



///////////////////////////
///
//抓取出错的链接保存在文件。/home/xuping/temp/datasheetcatalog_error.txt
//将错误的url倒入到mongodb
//然后这个程序就是从mongodb获取链接，并抓取数据，保存在pg数据库
//
//
//
//
///////////////////////////


def selectSql = Sql.newInstance("jdbc:postgresql://192.168.3.150:5432/pm", "qic_pm",
		"mypassword", "org.postgresql.Driver")

//执行之后  将datasheetcatalog_error.txt中的url重新抓取
def insertNewtable = Sql.newInstance("jdbc:postgresql://192.168.3.150:5432/pm", "qic_pm",
		"mypassword", "org.postgresql.Driver")


def   mongo = new GMongo("192.168.3.140:27017")
def    db = mongo.getDB("qegooPachong")
//读取一个url
errorUrl = db.datasheetcataUrl.findOne()

insertNewtable.withBatch(10,""" INSERT INTO datasheetcatalog( Nr, PartName, Description, Manufacturer, url) 
values (:Nr, :PartName, :Description , :Manufacturer , :url) """){ insertIt->

			while(errorUrl!=null) {
				//获取url之后需要截取后半截的链接
				def url="";
				url="http://127.0.0.1/proxy?target_url="+errorUrl?.url?.split("target_url=")[1];
				//				println url
				//拼接新的url
				Document  doc =   null;
				try {
					doc =   Jsoup.connect(url).get();
				} catch (Exception e) {
					println "ERROR1 ${url}"
					try {
						doc =   Jsoup.connect(url).get();
					} catch (Exception e1) {
						println "ERROR2 ${url}"
						try {
							doc =   Jsoup.connect(url).get();
						} catch (Exception e2) {
							println "ERROR3 ${url}"
							//将错误的链接处理掉！
							db.datasheetcataUrlError.save(errorUrl);
							doc=null;
						}
					}
				}
				if(doc!=null){
					//开始抓取数据
					try {
						def trs=doc.select("tr[onmouseover]");
						if(trs.size()<1){
							//如果url内容异常记录到错误日志中
							db.datasheetcataUrlError.save(errorUrl);
						} else{
							println "${url}开始分析内容"
							def firsetTr=doc.select("tr[onmouseover]").first()
							if(checkModelName(firsetTr.select("td")?.get(1).text(),selectSql)<1){
								println "${url}开始保存数据"
								doc.select("tr[onmouseover]").each{
									def m=[:]
									def tds=it.select("td");
									m<<["Nr":tds.get(0).text()]
									m<< ["PartName":tds.get(1).text()]
									m<< ["Description":tds.get(2).text()]
									m<<["Manufacturer":tds.get(3).text()]
									m<<["url":tds.get(1).select("a").get(0).attr("abs:href").toString()]
									insertIt.addBatch(m) ;
								}
							}else{
								println "${url}抓取过了"
							}

						}
					} catch (Exception e) {
						e.printStackTrace()
					}
				}else{
					println "${url}没抓到东西"
				}

				//删除已经处理过的URL
				db.datasheetcataUrl.remove(errorUrl);
				errorUrl = db.datasheetcataUrl.findOne()
				Thread.sleep(100);
			}



		}


def checkModelName(modelname,selectSql){
	def countRows = selectSql.firstRow("select count(*) as numberOfRows from datasheetcatalog where PartName = ${modelname}")
	return countRows.numberOfRows
}

