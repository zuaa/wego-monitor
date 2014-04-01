package com.qegoo
import groovy.sql.Sql

import java.security.MessageDigest

import com.gmongo.GMongo

class Mysql {
	static def   mongo = new GMongo("192.168.3.103:27017")
	static def    db = mongo.getDB("img")
	static def path="C://AAAAAA_pic/";
	static def logFile=new File("C://AAAAAA_pic/log.txt")
	static main(args) {


		61.times {
			new File("${path}${it}").mkdirs();
		}

		def sql = Sql.newInstance("jdbc:mysql://xiangrujia-pc:3306/huochetou", "root",
				"aaaaaaaa", "com.mysql.jdbc.Driver")


		900.times {
			sql.eachRow("SELECT * from content_digikey  limit ${it*100+90000} ,100 "){
				try{
					save(it.图片)
				}catch(Exception e){
				}
			}
		}
	}



	static public void save(def address) {
		if(address.size()<1){
			return
		}
		def name=generateMD5(address)+".jpg";
		if(db.img.find(name:name).count()==0){
			logFile<< "${new Date()} ${address}没有-抓 \n\r"
			db.img.insert([name: name,url:address,state:"0"])
			def now= new Date()
			try{
				new File("${path}/${now.minutes}/${name}").withOutputStream { out ->
					out << new URL(address).openStream()
				}
			}catch(Exception e){
				if(db.imgerror.find([name: name,url:address]).count()>0){
				}else{
					db.imgerror.insert([name: name,url:address])
				}
			}
		}else{
			logFile<< "${new Date()}--${address} -抓过了\n\r  "
		}
	}

	static def generateMD5(String s) {
		MessageDigest digest = MessageDigest.getInstance("MD5")
		digest.update(s.bytes);
		new BigInteger(1, digest.digest()).toString(16).padLeft(32, '0')
	}
}
