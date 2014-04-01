package com.qegoo
import groovy.sql.Sql

import com.gmongo.GMongo


class TestDbPg {
	static def   mongo = new GMongo("192.168.3.140:27017")
	static def    db = mongo.getDB("pm")
	static main(args) {
		pipei()
	}

	//用旧库中的id替换新库中的id
	static pipei(){
		//		def   mongo = new GMongo("192.168.3.140:27017")
		//		def    db = mongo.getDB("pm")
		//从旧表遍历产品
		db.product.find().each {
			//新表中查询结果
			def product=[model_name:"${it.model_name}", brand_name:"${it.brand_name}", shop_id:"${it.shop_id}",id:"${it.id}"]
			def r=db.product_new.find([model_name:"${it.model_name}", brand_name:"${it.brand_name}", shop_id:"${it.shop_id}"])
			product<<[product_list_size:r.count()]
			if(r.count()==1){
				product<<[old_id:r[0].id]
			}
			db."product_1".insert product
		}

	}
//[  "product_list_size" : [$ne: 1]  ]
	
	
	
	//	static saveObj(dbname,product){
	//		Thread.start {
	//			db."${dbname}".insert product
	//		}
	//	}
	/**
	 * 入库
	 * @param args
	 * @return
	 */
	static pm_new(args) {

		//		def   mongo = new GMongo("192.168.3.140:27017")
		//		def    db = mongo.getDB("pm")
		def sql = Sql.newInstance("jdbc:postgresql://192.168.9.119:5432/pm", "qic_pm",
				"mypasswd", "org.postgresql.Driver")
		60.times {
			db.product_new.insert( sql.rows("SELECT id,model_name,brand_name,\"catalog\",shop_id from pm_product limit 100000 offset ${it*100000}"))
			println it
		}
	}

	static qic_pm_old(args) {
		//		def   mongo = new GMongo("192.168.3.140:27017")
		//		def    db = mongo.getDB("pm")
		def sql = Sql.newInstance("jdbc:postgresql://192.168.9.119:5432/pm_product_20140219", "qic_pm",
				"mypasswd", "org.postgresql.Driver")

		60.times {
			db.product.insert( sql.rows("select id,model_name,brand_name,shop_id,distributor_model_name from pm_product limit 100000 offset ${it*100000}"))
			println it
		}
	}
}
