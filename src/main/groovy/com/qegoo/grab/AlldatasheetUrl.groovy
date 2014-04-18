
import groovy.sql.Sql

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
def selectSql = Sql.newInstance("jdbc:postgresql://192.168.3.163:5432/pm", "qic_pm",
		"mypassword", "org.postgresql.Driver")
//执行之后  将datasheetcatalog_error.txt中的url重新抓取
def insertNewtable = Sql.newInstance("jdbc:postgresql://192.168.3.163:5432/pm", "qic_pm",
		"mypassword", "org.postgresql.Driver")

insertNewtable.withBatch(40,""" INSERT INTO alldatasheeturl(
              url, state)
    VALUES   (:url, :state) """){ insertIt->

			Document allUrl=Jsoup.connect("http://www.alldatasheet.com/").get();
			int times=0
			allUrl.select("a").each {
				if(it.attr("abs:href").toString().contains("components.alldatasheet")){
					//println it.attr("href")

					try {
						Document allUrlone=Jsoup.connect(getUrl(times++)+it.attr("abs:href")).get();
						allUrlone.select("td[height=20]")?.first()?.select("a").each{ oneurl->
							def url=oneurl.attr("href").trim()
							if(checkUrl(url,selectSql)<1){

								def m=[:]
								m<<["url":url]
								m<<["state":1]
								insertIt.addBatch(m) ;
							}else{
								println "不需要保存${url}"
							}
						}
					} catch (Exception e) {
						//timeout 之后重试
						try {
							Document allUrlone=Jsoup.connect(getUrl(times++)+it.attr("abs:href")).get();
							allUrlone.select("td[height=20]")?.first()?.select("a").each{ oneurl->
								def url=oneurl.attr("href").trim()
								if(checkUrl(url,selectSql)<1){
									println "需要保存${url}"
									def m=[:]
									m<<["url":url]
									m<<["state":1]
									insertIt.addBatch(m) ;
								}else{
									println "不需要2保存${url}"
								}
							}
						} catch (Exception e1) {
							println it.attr("abs:href")
						}
					}

				}
			}
			Thread.sleep(500);
		}

//下面是处理是让云循环使用

def checkUrl(url,selectSql){
	def countRows = selectSql.firstRow("select count(*) as numberOfRows from alldatasheeturl where url = ${url}")
	return countRows.numberOfRows
}

def getUrl(number){
	return "http://192.168.3.19/proxy?target_url="
 
//	int nu=number/3
//	if(number%3==0){
//		return getBaiduUrl(nu);
//	}else if(number%3==1){
//		return getAliUrl(nu);
//	}else{
//		return getGooGleUrl(nu);
//	}
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

