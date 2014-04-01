package com.qegoo
import groovy.sql.Sql

import com.gmongo.GMongo


class PgDealCatalog {


	static main(args) {
		println 'start'
	def s=System.currentTimeMillis();
		def sql = Sql.newInstance("jdbc:postgresql://192.168.9.119:5432/pm", "qic_pm",
				"mypassword", "org.postgresql.Driver")
		def sql2 = Sql.newInstance("jdbc:postgresql://192.168.9.119:5432/pm", "qic_pm",
				"mypassword", "org.postgresql.Driver")
		sql.eachRow("select id, catalog   from  pm_new_product where shop_id='ff80808137cc055d0137cf2fc4e20007'"){
			def c=it.catalog
			//println it
			sql2.withBatch(50,"""UPDATE pm_new_product SET catalog = :catalog   WHERE id = :id""" ){ stmt ->
				stmt.addBatch( catalog: it.catalog[0..it.catalog.toString().lastIndexOf(">")-1],id: it.id )
			}
		}
		println 'end ${(System.currentTimeMillis()-s)/1000}'
	}
}
