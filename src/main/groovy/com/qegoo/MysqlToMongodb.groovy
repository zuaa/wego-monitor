package com.qegoo
import groovy.sql.Sql

import com.gmongo.GMongo


class MysqlToMongodb {

	static main(args) {
		def   mongo = new GMongo("192.168.9.146:27017")
		def    db = mongo.getDB("maid")
		def sql = Sql.newInstance("jdbc:mysql://xiangrujia-pc:3306/zuaa", "root",
				"aaaaaaaa", "com.mysql.jdbc.Driver")
		sql.eachRow("SELECT * from crm_1  "){

			db.crm_1.insert([count:it.详细信息,title:it.标题])
		 }
	}
}
