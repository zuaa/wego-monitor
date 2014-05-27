package com.qegoo.grab.onetime
/**
 * 复制所有的料号进入mongodb
 */
import groovy.sql.Sql

import com.gmongo.GMongo
import com.mongodb.BasicDBObject
import com.mongodb.DBCollection

//////////////////////////////////////
//////////////////////////////////////
//////////////////////////////////////
//////////////////////////////////////
///////////////禁止使用////////////////
///////////////禁止运行////////////////
//////////////////////////////////////
//////////////////////////////////////
//////////////////////////////////////
//////////////////////////////////////
//////////////////////////////////////

def selectSql = Sql.newInstance("jdbc:postgresql://192.168.3.150:5432/pm", "qic_pm",
		"mypassword", "org.postgresql.Driver")
def   mongo = new GMongo("192.168.3.140:27017")
def    db = mongo.getDB("qegooPachong")
def size=0;
selectSql.eachRow("SELECT count(*) as c from alldatasheet"){ size=it.c }
def tname="alldatasheetcn"
println "drop 表 ${tname}"
db."${tname}".drop();
println "  表 ${tname} 创建索引"
DBCollection mindex = db.getCollection("${tname}")
mindex.ensureIndex(new BasicDBObject("modelname", 1))
def doit=true;
def nowsize=0;
def timer=System.currentTimeMillis();
while (doit){
	println "nowsize${nowsize}"
	if(size>nowsize){
		selectSql.eachRow("SELECT * from alldatasheet limit 1000  offset  "+nowsize ){
			db."${tname}"<<[modelname:"${it.modelname}",url:"${it.html}",timer:timer++]
		}
		nowsize=nowsize+1000
	}else{
		doit=false;
	}
}










