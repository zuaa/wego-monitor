package com.qegoo
import groovy.sql.Sql
import groovy.sql.Sql

import java.security.MessageDigest

import com.gmongo.GMongo

class GetFile {
	static def   mongo = new GMongo("192.168.3.103:27017")
	static def    db = mongo.getDB("img")

	static main(args) {
		//		def  f = new File('e:/1.txt').newWriter('UTF-8')
		//		def  f2 = new File('e:/2.txt').newWriter('UTF-8')
		//		f.close()
		//		f2.close();
		//		61.times {
		//			new File("C://AAAAAA_pic/${it}").mkdirs();
		//		}

		//		int i=0;
		//		new File("E:").eachFileRecurse {
		//			i=i+1
		//			//这里的 File 表示的是一个路径
		//			def name="${it.name}" ;
		//			if(db.img.find(name:name).count()>0){
		//				//println name
		//			}else{
		//				//print "|";
		//				db.img.insert([name: name])
		//			}
		//		}
		//		println i
		Thread.start {
			def sql = Sql.newInstance("jdbc:postgresql://192.168.3.163:5432/pm", "gec",
					"root", "org.postgresql.Driver")
			sql.eachRow("SELECT DISTINCT(middle_resource) as resource from imgTable "){ download("${it.resource}"); }
		}




	}
	static public void download(def address) {
		def name=generateMD5(address)+".jpg";
		if(db.img.find(name:name).count()>0){
			//print address +"|"
			//println "yijing cunzai ${name}"
		}else{
			def now= new Date()
			try{
				//println "C://AAAAAA_pic/${now.minutes}/${name}"
				new File("C://AAAAAA_pic/${now.minutes}/${name}").withOutputStream { out ->
					out << new URL(address).openStream()
				}
			}catch(Exception e){
				if(db.imgerror.find([name: name,url:address]).count()>0){

				}else{
					db.imgerror.insert([name: name,url:address])
				}
			}
			db.img.insert([name: name])
			//println "insert:${name}"
		}

	}

	static def generateMD5(String s) {
		MessageDigest digest = MessageDigest.getInstance("MD5")
		digest.update(s.bytes);
		new BigInteger(1, digest.digest()).toString(16).padLeft(32, '0')
	}
}
