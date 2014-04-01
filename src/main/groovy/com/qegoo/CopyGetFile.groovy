package com.qegoo
import groovy.sql.Sql
import groovy.sql.Sql

import java.security.MessageDigest

import com.gmongo.GMongo

class CopyGetFile {
	static def   mongo = new GMongo("192.168.3.103:27017")
	static def    db = mongo.getDB("img")

	static main(args) {
		//		def  f = new File('e:/1.txt').newWriter('UTF-8')
		//		def  f2 = new File('e:/2.txt').newWriter('UTF-8')
		//		f.close()
		//		f2.close();
		//		61.times {
		//			new File("e://${it}").mkdirs();
		//		}


		def sql = Sql.newInstance("jdbc:postgresql://192.168.3.163:5432/pm", "gec",
				"root", "org.postgresql.Driver")
		def i=0;
		sql.eachRow("SELECT resource from  pm_img where  length(resource)>1    "){
			//	println it.resource

			i=i+1;
			if(i==10000){
				println i
				i=0;
			}
			save("${it.resource}");

		}

	}



	static public void save(def address) {
		def name=generateMD5(address)+".jpg";
		if(db.img.find(name:name).count()==0){
			//print address +"|"
			//println name
			//更新 抓取状态
		//	db.img.update([name:name], [$set: [url:address,state:"1"]])
			println "没有"
		}else{
			//db.img.insert([name: name,url:address,state:"0"])
		}
		//		def now= new Date()
		//		try{
		//			new File("e://${now.minutes}/${name}").withOutputStream { out ->
		//				out << new URL(address).openStream()
		//			}
		//		}catch(Exception e){
		//			if(db.imgerror.find([name: name,url:address]).count()>0){
		//
		//			}else{
		//				db.imgerror.insert([name: name,url:address])
		//			}
		//		}
	}

	static def generateMD5(String s) {
		MessageDigest digest = MessageDigest.getInstance("MD5")
		digest.update(s.bytes);
		new BigInteger(1, digest.digest()).toString(16).padLeft(32, '0')
	}
}
