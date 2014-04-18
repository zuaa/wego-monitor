package com.qegoo.grab

import groovy.sql.Sql

import org.jsoup.Jsoup
import org.jsoup.nodes.Document


def selectSql = Sql.newInstance("jdbc:postgresql://192.168.3.163:5432/pm", "qic_pm",
		"mypassword", "org.postgresql.Driver")

//执行之后  将datasheetcatalog_error.txt中的url重新抓取
def insertNewtable = Sql.newInstance("jdbc:postgresql://192.168.3.163:5432/pm", "qic_pm",
		"mypassword", "org.postgresql.Driver")
def longt=System.currentTimeMillis();
//清空数据
//insertNewtable.execute("truncate TABLE datasheetcatalog");
def logFile_error =new File("/home/xuping/temp/datasheetcatalog_error.txt")

int urltime=0

insertNewtable.withBatch(40,""" INSERT INTO datasheetcatalog( Nr, PartName, Description, Manufacturer, url) 
values (:Nr, :PartName, :Description , :Manufacturer , :url) """){ insertIt->
			26046.times{time->
				def url="${getUrl(urltime++)}http://www.datasheetcatalog.com/catalog/p${747920+time*40}.shtml"
				//println  url
				try {
					Document  doc =   Jsoup.connect(url).get();
					def trs=doc.select("tr[onmouseover]");
					if(trs.size()<1){
						//如果url内容异常记录到错误日志中
						logFile_error<<url+"\n"
					} else{
						def firsetTr=doc.select("tr[onmouseover]").first()
						if(checkModelName(firsetTr.select("td")?.get(1).text(),selectSql)<1){
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
					println "没有拿到内容再试试${url}"
					try {
						url="${getUrl(urltime++)}http://www.datasheetcatalog.com/catalog/p${747920+time*40}.shtml"
						Document  doc =   Jsoup.connect(url).get();
						def trs=doc.select("tr[onmouseover]");
						if(trs.size()<1){
							//如果url内容异常记录到错误日志中
							logFile_error<<url+"\n"
						} else{
							def firsetTr=doc.select("tr[onmouseover]").first()
							if(checkModelName(firsetTr.select("td")?.get(1).text(),selectSql)<1){
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
					} catch (Exception e1) {
						println "没有拿到内容${url}"
						logFile_error<<url+"\n"
					}
				}
				Thread.sleep(500);
			}
		}

def checkModelName(modelname,selectSql){
	def countRows = selectSql.firstRow("select count(*) as numberOfRows from datasheetcatalog where PartName = ${modelname}")
	return countRows.numberOfRows
}

def getUrl(number){
	int nu=number/3
	if(number%3==0){
		return getBaiduUrl(nu);
	}else if(number%3==1){
		return getAliUrl(nu);
	}else{
		return getGooGleUrl(nu);
	}
}

def getBaiduUrl(number){
	def yunlist=[]
	yunlist<<"http://heiji6.duapp.com/grabpoint/proxy?target_url="
	yunlist<<"http://heiji7.duapp.com/grabpoint/proxy?target_url="
	yunlist<<"http://heiji8.duapp.com/grabpoint/proxy?target_url="
	yunlist<<"http://heiji9.duapp.com/grabpoint/proxy?target_url="
	yunlist<<"http://heiji10.duapp.com/grabpoint/proxy?target_url="
	yunlist<<"http://heiji11.duapp.com/grabpoint/proxy?target_url="
	yunlist<<"http://heiji12.duapp.com/grabpoint/proxy?target_url="
	yunlist<<"http://heiji13.duapp.com/grabpoint/proxy?target_url="
	yunlist<<"http://heiji14.duapp.com/grabpoint/proxy?target_url="
	yunlist<<"http://heiji15.duapp.com/grabpoint/proxy?target_url="
	yunlist<<"http://heiji16.duapp.com/grabpoint/proxy?target_url="
	return yunlist.get(number%yunlist.size())
}

def getAliUrl(number){
	def yunlist=[]
	yunlist<<"http://heiji1.aliapp.com/proxy?target_url="
	yunlist<<"http://heiji2.aliapp.com/proxy?target_url="
	yunlist<<"http://heiji3.aliapp.com/proxy?target_url="
	return yunlist.get(number%yunlist.size())
}
def getGooGleUrl(number){
	def yunlist=[];
	yunlist<<"http://heiji0zuaa.appspot.com/proxy?target_url="
	yunlist<<"http://heiji1zuaa.appspot.com/proxy?target_url="
	yunlist<<"http://heiji2zuaa.appspot.com/proxy?target_url="
	yunlist<<"http://heiji3zuaa.appspot.com/proxy?target_url="
	yunlist<<"http://heiji4zuaa.appspot.com/proxy?target_url="
	yunlist<<"http://heiji5zuaa.appspot.com/proxy?target_url="
	yunlist<<"http://heiji6-zuaa.appspot.com/proxy?target_url="
	yunlist<<"http://heiji7zuaa.appspot.com/proxy?target_url="
	yunlist<<"http://heiji8zuaa.appspot.com/proxy?target_url="
	yunlist<<"http://heiji9zuaa.appspot.com/proxy?target_url="
	yunlist<<"http://heiji10zuaa.appspot.com/proxy?target_url="
	yunlist<<"http://heiji11zuaa.appspot.com/proxy?target_url="
	yunlist<<"http://heiji12zuaa.appspot.com/proxy?target_url="
	yunlist<<"http://heiji13zuaa.appspot.com/proxy?target_url="
	yunlist<<"http://heiji14zuaa.appspot.com/proxy?target_url="
	yunlist<<"http://heiji15zuaa.appspot.com/proxy?target_url="
	yunlist<<"http://heiji16zuaa.appspot.com/proxy?target_url="
	yunlist<<"http://heiji17zuaa.appspot.com/proxy?target_url="
	yunlist<<"http://heiji18zuaa.appspot.com/proxy?target_url="
	yunlist<<"http://heiji19zuaa.appspot.com/proxy?target_url="
	yunlist<<"http://heiji20zuaa.appspot.com/proxy?target_url="
	yunlist<<"http://heiji21zuaa.appspot.com/proxy?target_url="
	yunlist<<"http://heiji22zuaa.appspot.com/proxy?target_url="
	yunlist<<"http://heiji23zuaa.appspot.com/proxy?target_url="
	yunlist<<"http://qegoo-1.appspot.com/proxy?target_url="
	yunlist<<"http://qegoo-2.appspot.com/proxy?target_url="
	yunlist<<"http://qegoo-3.appspot.com/proxy?target_url="
	yunlist<<"http://qegoo-4.appspot.com/proxy?target_url="
	yunlist<<"http://qegoo-5.appspot.com/proxy?target_url="
	yunlist<<"http://qegoo-6.appspot.com/proxy?target_url="
	yunlist<<"http://qegoo-7.appspot.com/proxy?target_url="
	yunlist<<"http://qegoo-8.appspot.com/proxy?target_url="
	yunlist<<"http://qegoo-9.appspot.com/proxy?target_url="
	yunlist<<"http://qegoo-10.appspot.com/proxy?target_url="
	return yunlist.get(number%yunlist.size())
}

//
//CREATE TABLE datasheetcatalog
//(
//  id serial NOT NULL,
//   Nr  text,
//  PartName  text,
//   Description  text,
//   Manufacturer  text,
//  url text
//)
