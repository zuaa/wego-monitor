package com.qegoo.groovy
import groovy.sql.Sql

import java.security.MessageDigest
class UpdateProductImage {

	static main(args) {
		//查找图片
		def sql = Sql.newInstance("jdbc:postgresql://192.168.3.163:5432/pm", "gec",
				"root", "org.postgresql.Driver")
		def sql2 = Sql.newInstance("jdbc:postgresql://192.168.3.163:5432/pm", "gec",
				"root", "org.postgresql.Driver")
		//找到
		1933.times {time->
			def sqlstring="""SELECT id,big_resource from  pm_product_base where length(big_resource)>0 order by  id  LIMIT 1000   offset ${464000+time*1000}"""
			println sqlstring
			sql.eachRow(sqlstring){
				sql2.withBatch(30,"""update pm_product_base set local_resource = :local_resource   where id= :id""" ){ stmt ->
					stmt.addBatch( local_resource: util.MD5.generateMD5(it.big_resource)+".jpg",id: it.id ) 
				}
			}
		}
	}
}
