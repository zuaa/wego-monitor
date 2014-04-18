
import groovy.sql.Sql

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

import com.gmongo.GMongo

int times=0

def insertNewtable = Sql.newInstance("jdbc:postgresql://192.168.3.163:5432/pm", "qic_pm",
		"mypassword", "org.postgresql.Driver")
def selectSql = Sql.newInstance("jdbc:postgresql://192.168.3.163:5432/pm", "qic_pm",
		"mypassword", "org.postgresql.Driver")
def   mongo = new GMongo("192.168.3.140:27017")
def    db = mongo.getDB("qegooPachong")

def dbname="alldatasheeturl"
//读取一个url
row = db."${dbname}".findOne()



insertNewtable.withBatch(40,""" INSERT INTO alldatasheet(
              modelname, html)
    VALUES   (:modelname, :html) """){ insertIt->

			while(row!=null){
				def oneUrl="http://components.alldatasheet.com${row.url.trim()}"
				//开始抓数据
				//获取代理的链接地址
				def url="http://127.0.0.1/proxy?target_url=${oneUrl}"
				println  url
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
							db."${dbname}Error"<<["url":oneUrl];
							doc=null;
						}
					}
				}


				if(doc!=null){
					try {
						//判断拿到的数据是不是对的
						def data=doc.select("center center>font")?.first();
						data.select("center").remove();
						data.select("table").remove();
						def m=[:]
						def attr=[]
						def firstModel=null;
						def needSave=true;
						data.select("a").each{
							if(firstModel==null&&needSave==true){
								firstModel=it.text();
							}else{
								if(needSave==true){
									if(checkModelName(firstModel,selectSql)<1){

									}else{
										needSave=false
										println "error : ${url} 已经抓取了"
									}
								}

							}

							//检测第一个料号是否存在，如果存在则不执行本次添加。
							if(needSave){
								if(m.size()<1){
									m<<["modelname":it.text()]
									m<<["url":it.attr("abs:href")]
									attr<<[it.attr("abs:href")];
								}else{
									attr<<[it.attr("abs:href")];
									if(it.text().contains("Buy")){
										m<<["html":attr.toString()]
										insertIt.addBatch(m) ;
										m=[:]
										attr=[]
									}
								}
							}
						}
					} catch (Exception e) {
						println "出错了："+row.url.trim()
						db."${dbname}Error"<<["url":oneUrl];
					}
				}else{
				}
				//删除已经处理过的URL
				db."${dbname}".remove(row);
				row = db."${dbname}".findOne()
				Thread.sleep(100);
				//////////////////
			}
		}

def checkModelName(modelname,selectSql){
	def countRows = selectSql.firstRow("select count(*) as numberOfRows from alldatasheet where modelname = ${modelname}")
	return countRows.numberOfRows
}


