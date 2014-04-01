package com.qegoo

import groovy.sql.Sql

import java.security.MessageDigest

class DownloadImgForPmResourceTable {

	static main(args) {


		//²éÕÒÍ¼Æ¬
		def sql = Sql.newInstance("jdbc:postgresql://192.168.3.163:5432/pm", "gec",
				"root", "org.postgresql.Driver")
		def sql2 = Sql.newInstance("jdbc:postgresql://192.168.3.163:5432/pm", "gec",
				"root", "org.postgresql.Driver")
		//ÕÒµ½

		sql.eachRow("""SELECT id,url from  pm_resource where length(url)>0 
 order by  id     """){
					//println it	;
					sql2.withBatch(10,"""update pm_resource set local_url = :local_url   where id= :id""" ){ stmt ->
						stmt.addBatch( local_url: generateMD5(it.url )+".jpg",id: it.id )
					}
				}





	}
	static def generateMD5(String s) {
		MessageDigest digest = MessageDigest.getInstance("MD5")
		digest.update(s.bytes);
		new BigInteger(1, digest.digest()).toString(16).padLeft(32, '0')
	}
}
